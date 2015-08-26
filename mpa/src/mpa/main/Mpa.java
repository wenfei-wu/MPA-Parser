package mpa.main;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;
import mpa.grammar.MpaCombinedParser;
import mpa.grammar.arista.control.AristaCombinedParser;
import mpa.grammar.arista.control.AristaExtractor;
import mpa.grammar.cisco.control.CiscoCombinedParser;
import mpa.grammar.cisco.control.CiscoExtractor;
import mpa.grammar.citrix.control.CitrixCombinedParser;
import mpa.grammar.citrix.control.CitrixExtractor;
import mpa.grammar.f5.control.F5CombinedParser;
import mpa.grammar.f5.control.F5Extractor;
import mpa.grammar.flatjuniper.control.FlatJuniperCombinedParser;
import mpa.grammar.flatjuniper.control.FlatJuniperExtractor;
import mpa.grammar.juniperfirewall.control.JuniperFirewallCombinedParser;
import mpa.grammar.juniperfirewall.control.JuniperFirewallExtractor;
import mpa.grammar.quanta.control.QuantaCombinedParser;
import mpa.grammar.quanta.control.QuantaExtractor;
import mpa.representation.Configs;
import mpa.representation.Statistics;
import mpa.util.FileIO;
import mpa.util.Preprocessor;
import mpa.util.Util;
import org.antlr.v4.runtime.ParserRuleContext;
import org.antlr.v4.runtime.tree.ParseTreeWalker;

public class Mpa {

    private final int PER_THREAD_FILES_PER_ROUND = 10;
    private final int PER_THREAD_FILES_PER_REPORT = 100;

    String fileList;
    int numThread;
    String root;
    Map<String, Statistics> stats;
    String statfile;
    String failfile;
    String failures;
    String warnings;
    int count;

    Lock inputLock, outputLock;
    MpaThread mpaThreads[];
    Queue<String> files = new LinkedList<String>();
    int numFiles;

    long startTime;

    public Mpa(String flist, String source_root, int nThread, String sfile,
            String ffile) {
        fileList = flist;
        numThread = nThread;
        root = source_root;
        stats = new HashMap<String, Statistics>();
        statfile = sfile;
        if (statfile != null) {        	
            FileIO.WriteToFile("Stamp,Device,ConfigFile,Vendor,Role,Model,"
            		+"Vlans,VlanIfaces,NumVlans,UDLD,LACP,DOT1Q,DHCP,HSRP,MST,"
            		+"LLDP,VRRP,NSRP,OspfInst,OspfProcesses,BgpInst,BgpProcesses,"
            		+"IntraRefComplex\n", statfile, false);
        }
        failfile = ffile;
        if (failfile != null) {
            FileIO.WriteToFile("", failfile, false);
        }
        failures = "";
        warnings = "";
        count = 0;
        inputLock = new ReentrantLock();
        outputLock = new ReentrantLock();

        mpaThreads = new MpaThread[numThread];
        for (int i = 0; i < numThread; i++) {
            mpaThreads[i] = new MpaThread(i);
        }
    }

    public void Start() {
        Input();
        startTime = System.nanoTime();
        Process();
    }

    public Map<String, Statistics> GetStat() {
        return stats;
    }

    private void Process() {
        Thread t[] = new Thread[numThread];
        for (int i = 0; i < numThread; i++) {
            t[i] = new Thread(mpaThreads[i], "thread" + i);
        }
        for (int i = 0; i < numThread; i++) {
            t[i].start();
//         System.out.println("===========");
        }
        for (int i = 0; i < numThread; i++) {
            try {
                t[i].join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    private void Input() {
        BufferedReader br = null;
        try {
            br = new BufferedReader(new FileReader(fileList));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        String line = null;
        try {
            while ((line = br.readLine()) != null) {
                files.add(line);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        numFiles = files.size();
        System.out.println(files.size() + " files to process.");
    }

    private class MpaThread implements Runnable {

        int index;
        Queue<String> localFiles;
        HashMap<String, Statistics> localStat;

        int countInRound;
        int localCount;
        String localFailure;
        String localWarning;

        public MpaThread(int i) {
            index = i;
            localFiles = new LinkedList<String>();
            localStat = new HashMap<String, Statistics>();
            localCount = 0;
            localFailure = "";
            localWarning = "";
            //     System.out.println("MpaThread "+i+" created");
        }

        @Override
        public void run() {
            //       System.out.println("MpaThread "+index+" start");
            while (true) {
                // input
                boolean hasData = input();
                if (!hasData) {
                    break;
                }
                // process
                process();
                // output
                output(false);
            }
            output(true);
        }

        private void output(boolean last) {
            // localCount+=countInRound;
            outputLock.lock();
            if (last || localCount >= PER_THREAD_FILES_PER_REPORT) {
                if (statfile != null) {
                    String out = "";
                    List<String> toRemove = new ArrayList<String>();
                    for (Map.Entry<String, Statistics> entry : localStat
                            .entrySet()) {
                        try {
                            out += entry.getKey() + "," 
                            		+ entry.getValue().toString() + "\n";
                        } catch (Exception e) {
                            localFailure += entry.getKey() + "\n";
                            toRemove.add(entry.getKey());
                        }
                    }
                    FileIO.WriteToFile(out, statfile, true);
                    for (String remove : toRemove) {
                        localStat.remove(remove);
                    }
                }
                if (failfile != null) {
                    FileIO.WriteToFile(localFailure, failfile, true);
                }
                //stats.putAll(localStat);
                count += localCount;
                failures += localFailure;
                warnings += localWarning;
                long endTime = System.nanoTime();
                long elapsed = (endTime - startTime) / 1000000000; // in second
                System.out.println(
                        count + " file processed in " + elapsed + " seconds, reported from thread " + index);
                  //+", localCount is "+localCount);
                //  System.out.println("localFalures: "+localFailure);
                // clean for next output
                //System.out.println("localCount is "+localCount+", localOutput size is "+localOutput.size());
                localStat.clear();
                localCount = 0;
                localFailure = "";
                localWarning = "";
            }
            outputLock.unlock();
            countInRound = 0;
        }

        private void process() {
            while (!localFiles.isEmpty()) {
                String line = localFiles.remove();
                localCount++;
                //System.out.println(localCount);            

                String[] fields = line.split(",");
                if (fields.length != 6) {
                    localFailure += line + "\n";
                    continue;
                }
                String stamp = fields[0];
                String device = fields[1];
                String config = fields[2];
                String vendor = fields[3];
                String file = root + '/' + stamp + '/' + device + '/' + config;
                Statistics stat = parseVendorConfigurations(vendor, file);
                if (stat != null) {
                    localStat.put(line, stat);
                    if (stat.HasWarning()) {
                        localWarning += line + "\n";
                    }
                } else {
                    System.out.println("FAILURE: " + line);
                    localFailure += line + "\n";
                }
            }
        }

        private boolean input() {
            inputLock.lock();
            countInRound = 0;
            while (!files.isEmpty()) {
                String file = files.remove();
                countInRound++;
                localFiles.add(file);
                //System.out.println("*");
                if (countInRound >= PER_THREAD_FILES_PER_ROUND) {
                    break;
                }
            }
            inputLock.unlock();
            //    System.out.println("Thread "+index+" read "+countInRound+" files.");
            if (countInRound == 0) {
                return false;
            } else {
                return true;
            }
        }
    }

    public static Statistics parseVendorConfigurations(String vendor,
            String file) {
        String content;
        Preprocessor prep = new Preprocessor();
        try {
            content = prep.Process(vendor, file);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
        Statistics stat = null;
        try {
            if (vendor.equals("Cisco")) {
                MpaCombinedParser<?, ?> parser
                        = new CiscoCombinedParser(content);
                ParserRuleContext tree = parser.parse();
                CiscoExtractor extractor = new CiscoExtractor();
                ParseTreeWalker walker = new ParseTreeWalker();
                walker.walk(extractor, tree);
                stat = extractor.getVendorConfiguration();
            } else if (vendor.equals("Arista")) {
                MpaCombinedParser<?, ?> parser = new AristaCombinedParser(
                        content);
                ParserRuleContext tree = parser.parse();
                AristaExtractor extractor = new AristaExtractor();
                ParseTreeWalker walker = new ParseTreeWalker();
                walker.walk(extractor, tree);
                stat = extractor.getVendorConfiguration();
            } else if (vendor.equals("Quanta")) {
                MpaCombinedParser<?, ?> parser = new QuantaCombinedParser(
                        content);
                ParserRuleContext tree = parser.parse();
                QuantaExtractor extractor = new QuantaExtractor();
                ParseTreeWalker walker = new ParseTreeWalker();
                walker.walk(extractor, tree);
                stat = extractor.getVendorConfiguration();
            } else if (vendor.equals("Juniper") || vendor.equals("Juniper-Flat")) {
                MpaCombinedParser<?, ?> parser = new FlatJuniperCombinedParser(
                        content);
                ParserRuleContext tree = parser.parse();
                FlatJuniperExtractor extractor = new FlatJuniperExtractor();
                ParseTreeWalker walker = new ParseTreeWalker();
                walker.walk(extractor, tree);
                stat = extractor.getVendorConfiguration();
            } else if (vendor.equals("F5")) {
                MpaCombinedParser<?, ?> parser = new F5CombinedParser(content);
                ParserRuleContext tree = parser.parse();
                F5Extractor extractor = new F5Extractor();
                ParseTreeWalker walker = new ParseTreeWalker();
                walker.walk(extractor, tree);
                stat = extractor.getVendorConfiguration();
            } else if (vendor.equals("Citrix")) {
                MpaCombinedParser<?, ?> parser = new CitrixCombinedParser(
                        content);
                ParserRuleContext tree = parser.parse();
                CitrixExtractor extractor = new CitrixExtractor();
                ParseTreeWalker walker = new ParseTreeWalker();
                walker.walk(extractor, tree);
                stat = extractor.getVendorConfiguration();
            } else if (vendor.equals("Juniper-Firewall")) {
                MpaCombinedParser<?, ?> parser = new JuniperFirewallCombinedParser(
                        content);
                ParserRuleContext tree = parser.parse();
                JuniperFirewallExtractor extractor = new JuniperFirewallExtractor();
                ParseTreeWalker walker = new ParseTreeWalker();
                walker.walk(extractor, tree);
                stat = extractor.getVendorConfiguration();
            }
        } catch (Exception e) {
            e.printStackTrace();
            //   System.out.println("Parse Error: "+file);
        }
        return stat;
    }

    public void WriteFailures(String filename) {
        if (filename != null) {
            FileIO.WriteToFile(failures, filename, false);
        }
    }

    public void WriteWarnings(String filename) {
        FileIO.WriteToFile(warnings, filename, false);
    }

    public void WriteStatistics(String filename) {
        System.out.println("Wrting Statistics:");
        System.out.println(
                "stamp, device, config, vendor, model, role, architecture");
        System.out.println("vlanDeclared, vlanInst");
        System.out.println(Util.Join(", ", Configs.L2Protocols));
        System.out.println("OSPF, BGP");
        System.out.println("References");
        if (filename == null) {
            return;
        }
        String out = "";
        List<String> toRemove = new ArrayList<String>();
        for (Map.Entry<String, Statistics> entry : stats.entrySet()) {
            try {
                out += entry.getKey() + "," + entry.getValue().toString() + "\n";
            } catch (Exception e) {
                failures += entry.getKey() + "\n";
                toRemove.add(entry.getKey());
            }
        }
        for (String remove : toRemove) {
            stats.remove(remove);
        }
        FileIO.WriteToFile(out, "statistics.csv", false);
    }
}
