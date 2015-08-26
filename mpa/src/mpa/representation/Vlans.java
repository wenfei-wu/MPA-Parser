package mpa.representation;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import mpa.util.Util;

public class Vlans {

    Set<Integer> vlan_declared;
    // format :  iface, vlans
    List<String[]> iface_vlans;

    public Vlans() {
        vlan_declared = new HashSet<Integer>();
        iface_vlans = new ArrayList<String[]>();
    }

    public void DeclareVlan(String vlan) {
        vlan_declared.add(Integer.parseInt(vlan));
    }

    public void IfaceVlan(String iface, String vlan) {
        iface_vlans.add(new String[]{iface, vlan});
    }

    @Override
    public String toString() {
        String out = "";
        int inst_count = 0;
        Map<String, Set<Integer>> iface_vlan_map
                = new HashMap<String, Set<Integer>>();
        for (String[] iface_vlan : iface_vlans) {
            String iface = iface_vlan[0];
            String vlan = iface_vlan[1];
            List<Integer> vlan_int = Util.range2Array(vlan);
            if (!iface_vlan_map.containsKey(iface)) {
                Set<Integer> vlans = new HashSet<Integer>();
                vlan_int.retainAll(vlan_declared);
                vlans.addAll(vlan_int);
                iface_vlan_map.put(iface, vlans);
            } else {
                iface_vlan_map.get(iface).addAll(vlan_int);
            }
        }
        for (String iface : iface_vlan_map.keySet()) {
            Set<Integer> vlans = iface_vlan_map.get(iface);
            inst_count += vlans.size();
        } 
        
        String vlanList = "";
        for (int vlan : vlan_declared) {
        	vlanList += vlan + "-";
        }
        if (vlanList.length() > 0) {
        	vlanList = vlanList.substring(0, vlanList.length()-1);
        }
        
        out += vlanList + "," + inst_count + "," + vlan_declared.size();
        return out;
    }
}
