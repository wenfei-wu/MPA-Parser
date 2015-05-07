parser grammar JuniperFirewallGrammar;

options {
   superClass = 'mpa.grammar.MpaParser';
   tokenVocab = JuniperFirewallLexer;
}


@header {
package mpa.grammar.juniperfirewall;
}

juniperfirewall_configuration
:
   (
      service
      | zone
      | vrouter
      | iface
      | address
      | group_address
      | policy
   )* NEWLINE* EOF
;

service
:
    serviceWithoutIcmp | serviceWithIcmp
;

serviceWithoutIcmp
:
    SET SERVICE serviceName = NAME (PROTOCOL|NAME) protocolName = NAME SRC_PORT srcPortRange = NAME DST_PORT dstPortRange = NAME NEWLINE
;

serviceWithIcmp
:
    SET SERVICE serviceName = NAME (PROTOCOL|NAME) protocolName = NAME TYPE typeName = NAME CODE codeName = NAME NEWLINE
;

zone
:
    zoneWithVrouter | zoneWithoutVrouter
;

zoneWithVrouter
:
    SET ZONE zoneName = NAME VROUTER vrouterName = NAME NEWLINE
;

zoneWithoutVrouter
:
    SET ZONE zoneName = NAME (~NEWLINE)* NEWLINE
;

vrouter
:
    SET VROUTER vrouterName = NAME (~NEWLINE)* NEWLINE
;

iface
:
    interfaceWithZone | interfaceSimple | interfaceWithID | interfaceWithTAG
;


interfaceWithZone
:
    SET INTERFACE interfaceName = NAME ZONE zoneName = NAME NEWLINE
;

interfaceSimple
:
    SET INTERFACE interfaceName = NAME (~NEWLINE)* NEWLINE
;

interfaceWithID
:
    SET INTERFACE ID NAME interfaceName = NAME ZONE zoneName = NAME NEWLINE
;

interfaceWithTAG
:
    SET INTERFACE interfaceName = NAME TAG NAME ZONE zoneName = NAME NEWLINE
;

address
:
    SET ADDRESS zoneName = NAME addressName = NAME NAME NAME (~NEWLINE)* NEWLINE
;

group_address
:
    groupAddressSimple | groupAddressWithAddress
;

groupAddressSimple
:
    SET GROUP ADDRESS zoneName = NAME groupName = NAME NEWLINE
;

groupAddressWithAddress
:
    SET GROUP ADDRESS zoneName = NAME groupName = NAME ADD addressName = NAME NEWLINE
;

policy
:
    SET POLICY (GLOBAL)? ID policyName = NAME FROM fromZoneName = NAME TO toZoneName = NAME addr1 = NAME addr2 = NAME serviceName = NAME (~NEWLINE)* NEWLINE
    ( policyDstAddress | policySrcAddress | policyService | policyAnythingElse)*
;

policyDstAddress
:
    SET DST_ADDRESS dstAddress = NAME NEWLINE
;

policySrcAddress
:
    SET SRC_ADDRESS srcAddress = NAME NEWLINE
;

policyService
:
    SET SERVICE serviceName = NAME NEWLINE
;

policyAnythingElse
:
    SET (~NEWLINE)* NEWLINE
;