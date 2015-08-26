package mpa.representation;

import mpa.util.Util;

public class Statistics {

    L2Protocols l2protocols;
    L3Protocols l3protocols;
    Vlans vlans;
    References references;

    boolean hasWarning;

    public Statistics() {
        l2protocols = new L2Protocols();
        l3protocols = new L3Protocols();
        vlans = new Vlans();
        references = new References();
        hasWarning = false;
    }

    public void SetWarning() {
        hasWarning = true;
    }

    public boolean HasWarning() {
        return hasWarning;
    }

   // References
    // intra
    public void AddRefEntity(String type, String name) {
        references.AddRefEntity(type, name);
    }

    // intra ref

    public void AddIntraRef(String from_t, String from_n, String to_t,
            String to_n) {
        references.AddIntraReferences(from_t, from_n, to_t, to_n);
    }

    // VLAN
    public void DeclareVlan(String vlan) {
        vlans.DeclareVlan(vlan);
    }

    public void IfaceVlan(String iface, String vlan) {
        vlans.IfaceVlan(iface, vlan);
    }

    // l2 protocols
    public void DeclareL2Proto(String proto) {
        l2protocols.DeclareProtocol(proto);
    }

    public void L2ProtoInst(String proto, String info) {
        l2protocols.ProtoInst(proto, info);
    }

   // l3 protocols  
    // OSPF
    public void IfaceIp(String iface, String addr, String mask) {
        l3protocols.IfaceIp(iface, addr, mask);
    }

    public void IfaceIP(String iface, String prefix) {
        l3protocols.IfaceIP(iface, prefix);
    }

    public void IfaceOspfArea(String iface, String proc, String area) {
        l3protocols.IfaceOspfArea(iface, proc, area);
    }
    
    public void OspfProcess(String pid) {
        l3protocols.OspfProcess(pid);
    }

    public void OspfNetworkArea(String proc, String addr, String mask,
            String area) {
        l3protocols.OspfNetworkArea(proc, addr, mask, area);
    }

    public void OspfNetworkArea(String proc, String prefix, String area) {
        l3protocols.OspfNetworkArea(proc, prefix, area);
    }

    public void OspfAreaIface(String area, String iface) {
        l3protocols.OspfAreaIface(area, iface);
    }

    // BGP
    
    public void BgpProcess(String local_as) {
        l3protocols.BgpProcess(local_as);
    }

    public void BgpNeighborAs(String local_as, String addr, String mask,
            String remote_as) {
        l3protocols.BgpNeighborAs(local_as, addr, mask, remote_as);
    }

    public void BgpTemplateAs(String name, String local_as, String remote_as) {
        l3protocols.BgpTemplateAs(name, local_as, remote_as);
    }

    public void BgpGroupAs(String name, String local_as, String remote_as) {
        l3protocols.BgpGroupAs(name, local_as, remote_as);
    }

    public void BgpNeighborTemplate(String local_as, String addr, String mask,
            String template_name) {
        l3protocols.BgpNeighborTemplate(local_as, addr, mask, template_name);
    }

    public void BgpNeighborGroup(String local_as, String addr, String mask,
            String group_name) {
        l3protocols.BgpNeighborGroup(local_as, addr, mask, group_name);
    }

    public void BgpTemplateInheritTemplate(String local_as,
            String currentTemplate, String toInherit) {
        l3protocols.BgpTemplateInheritTemplate(local_as, currentTemplate,
                toInherit);
    }

    @Override
    public String toString() {
        String fields[] = new String[]{vlans.toString(), l2protocols.toString(),
            l3protocols.toString(), references.toString()};
        return Util.Join(",", fields);
    }

    public void L2NumIface(int size) {
        l2protocols.L2NumIface(size);
    }

    public L2Protocols getL2protocols() {
        return l2protocols;
    }

    public void setL2protocols(L2Protocols l2protocols) {
        this.l2protocols = l2protocols;
    }

    public L3Protocols getL3protocols() {
        return l3protocols;
    }

    public void setL3protocols(L3Protocols l3protocols) {
        this.l3protocols = l3protocols;
    }

    public Vlans getVlans() {
        return vlans;
    }

    public void setVlans(Vlans vlans) {
        this.vlans = vlans;
    }

    public References getReferences() {
        return references;
    }

    public void setReferences(References references) {
        this.references = references;
    }
    
    
}
