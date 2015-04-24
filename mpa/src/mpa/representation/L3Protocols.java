package mpa.representation;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import mpa.util.Util;

public class L3Protocols {   
   public L3Protocols(){
      iface_ip = new ArrayList<String[]>();
      iface_proc_area = new ArrayList<String[]>();
      ospf_subnet_mask_area =new ArrayList<String[]>();
      neighbor_as = new ArrayList<String[]>();
      template_as = new ArrayList<String[]>();
      group_as = new ArrayList<String[]>();
      neighbor_template = new ArrayList<String[]>();
      neighbor_group = new ArrayList<String[]>();
   }
   
   // interface
      // <interface, ip, mask>
   List<String[]> iface_ip;
      // <interface, ospf_proc, area>
   List<String[]> iface_proc_area;
   public void IfaceIp(String iface, String addr, String mask){
      iface_ip.add(new String[]{iface, addr, mask});
   }
   public void IfaceIP(String iface, String prefix){
      String []fields = prefix.split("/");
      String addr_str = fields[0];
      String numMaskBits= fields[1];
      int numBits = Integer.parseInt(numMaskBits);
      long maskLong = Util.numSubnetBitsToSubnetLong(numBits);
      String mask_str = Util.longToIp(maskLong);
      iface_ip.add(new String[]{iface, addr_str, mask_str });
   }
   public void IfaceOspfArea(String iface, String proc, String area){
      iface_proc_area.add(new String[]{iface, proc, area});
   }
   // OSPF
      // <opsf_proc, subnet, mask, area>
   List<String[]> ospf_subnet_mask_area;
   public void OspfNetworkArea(String proc, String addr, String mask, String area){
      // the mask needs a bit processing. In OSPF configure, the mask is in format of 0.0.255.255
      long mask_long = Util.ipToLong(mask);
      mask_long ^= 4294967295L;
      String mask_str = Util.longToIp(mask_long);
      ospf_subnet_mask_area.add(new String[]{proc, addr, mask_str, area});
   }
   public void OspfNetworkArea(String proc, String prefix, String area){
      String []fields = prefix.split("/");
      String addr_str = fields[0];
      String numMaskBits= fields[1];
      int numBits = Integer.parseInt(numMaskBits);
      long maskLong = Util.numSubnetBitsToSubnetLong(numBits);
      String mask_str = Util.longToIp(maskLong);
      ospf_subnet_mask_area.add(new String[]{proc, addr_str, mask_str, area});
   }
   // OSPF instance:   <process_id, area, iface, addr, mask> where addr in subnet
   public int OspfInst(){
      int count = 0;
      // part 1: ospf configuration in interface
      Map<String, Integer> iface_ip_count = new HashMap<String, Integer>();
      for(String[] ifaceIp: iface_ip){
         String iface = ifaceIp[0];
         if(iface_ip_count.containsKey(iface))
            iface_ip_count.put(iface, iface_ip_count.get(iface) + 1);
         else
            iface_ip_count.put(iface, 1);
      }
      for(String[] ipa: iface_proc_area){
         String iface = ipa[0];
         if(iface_ip_count.containsKey(iface))
            count+= iface_ip_count.get(iface);
      }
      // part 2: ospf configuration in router ospf
      for(String[] osma: ospf_subnet_mask_area){
         String subnet = osma[1];
         String mask = osma[2];
         long subnetLong = Util.ipToLong(subnet);
         long maskLong = Util.ipToLong(mask);
         for(String[] ifaceIp: iface_ip){
            String ip = ifaceIp[1];
            long ipLong = Util.ipToLong(ip);
            if( (ipLong & maskLong)==(subnetLong & maskLong) )
               count+=1;
         }
      }
      return count;
   }

   // BGP 
      // Neighbor <local-as, addr, mask, remote-as>
   List<String[]> neighbor_as;
      // template <name, local-as, remote-as>
   List<String[]> template_as;
      // peer-group <name, local-as, remote-as>
   List<String[]> group_as;
      // neighbor-inherit-template <local-as, addr, mask, template>
   List<String[]> neighbor_template;
      // neighbor-inherit-group <local-as, addr, mask, group>
   List<String[]> neighbor_group;
   
   public void BgpNeighborAs(String local_as, String addr, String mask, String remote_as){
      neighbor_as.add(new String[]{local_as, addr, mask, remote_as});
   }
   public void BgpTemplateAs(String name, String local_as, String remote_as){
      template_as.add(new String[]{name, local_as, remote_as});
   }
   public void BgpGroupAs(String name, String local_as, String remote_as){
      group_as.add(new String[]{name, local_as, remote_as});
   }
   public void BgpNeighborTemplate(String local_as, String addr, String mask, String template_name){
      neighbor_template.add(new String[]{local_as, addr, mask, template_name});
   }
   public void BgpNeighborGroup(String local_as, String addr, String mask, String group_name){
      neighbor_group.add(new String[]{local_as, addr, mask, group_name});
   }
   public void BgpTemplateInheritTemplate(String local_as,
         String template, String toInherit) {
      String as = null;
      for(String[] item: template_as){
         if(item[0].equals(toInherit)){
            as = item[2];
            break;
         }
      }
      if (as != null)
         template_as.add(new String[]{template, local_as, as});      
   }
   public int BgpInst(){
      int count = 0;
      count += neighbor_as.size();
      Set<String> templates = new HashSet<String>();
      for(String[] item: template_as)
         templates.add(item[0]);
      Set<String> groups = new HashSet<String>();
      for(String[] item: group_as)
         groups.add(item[0]);
      for(String[] item: neighbor_template){
         if(templates.contains(item[3]))
            count++;
      }
      for(String[] item: neighbor_group){
         if(groups.contains(item[3]));
      }
      return count;
   }
   
   @Override
   public String toString(){
      return OspfInst()+","+BgpInst();
   }
}
