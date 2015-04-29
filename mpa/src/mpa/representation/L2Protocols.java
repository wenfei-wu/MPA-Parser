package mpa.representation;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import mpa.util.Util;

public class L2Protocols {

    // UDLD, LACP, 802.1Q, HSRP, DHCP, MSTP

    Map<String, Integer> protoInst;
    List<String> MSTP_instances;
    int numIface = 0;
    Set<String> LLDP_instances;

   // some protocols need special processing
    // for example:
    // MSTP
    //====================================
    // spanning-tree mst configuration
    //    name config1
    //    revision 1
    //    instance 1 vlan 1-999
    //    instance 2 vlan 2000-2999
    //====================================
    // here there are 2 spanning trees configured. however,
    // the vlan range is 1-4096, the remaining vlans that are not 
    // in instance 1 or 2 form the 3rd spanning tree. So there should
    // be 3 spanning tree instances.
    // I process them in the function ProcessSpecialProtocols()
   // LLDP: may configure "all", then we need the total number of interfaces
    public L2Protocols() {
        protoInst = new HashMap<String, Integer>();

        for (int i = 0; i < Configs.L2Protocols.length; i++) {
            protoInst.put(Configs.L2Protocols[i], 0);
        }

        MSTP_instances = new ArrayList<String>();
        LLDP_instances = new HashSet<String>();
    }

    public void DeclareProtocol(String proto) {
        if (!protoInst.containsKey(proto)) {
            protoInst.put(proto, 0);
        }
    }

    public void ProtoInst(String proto, String info) {
        // some protocols need special processing, e.g. MSTP
        if (proto.equals("MSTP")) {
            MSTP_instances.add(info);
            return;
        } else if (proto.equals("LLDP")) {
            LLDP_instances.add(info);
            return;
        }

        if (!protoInst.containsKey(proto)) {
            protoInst.put(proto, 1);
        } else {
            int value = protoInst.get(proto);
            protoInst.put(proto, value + 1);
        }
    }

    public void L2NumIface(int count) {
        numIface = count;
    }

    boolean processed = false;

    public void ProcessSpecialProtocols() {
        // MSTP
        int vlan[] = new int[4097];
        for (int i = 0; i < 4097; i++) {
            vlan[i] = 0;
        }
        int count = 0;
        for (String instance : MSTP_instances) {
            count++;
            // ranges to int array
            List<Integer> range_int = Util.range2Array(instance);
            for (Integer i : range_int) {
                vlan[i] = 1;
            }
        }
        for (int i = 1; i <= 4096; i++) {
            if (vlan[i] == 0) {
                count++;
                break;
            }
        }
        protoInst.put("MSTP", count);
        // LLDP
        if (LLDP_instances.contains("all")) {
            protoInst.put("LLDP", numIface);
        } else {
            protoInst.put("LLDP", LLDP_instances.size());
        }
        processed = true;
    }

    public String toString() {
        if (!processed) {
            ProcessSpecialProtocols();
        }
        String count[] = new String[Configs.L2Protocols.length];
        for (int i = 0; i < Configs.L2Protocols.length; i++) {
            count[i] = Integer.toString(protoInst.get(Configs.L2Protocols[i]));
        }
        String out = Util.Join(",", count);
        return out;
    }
}
