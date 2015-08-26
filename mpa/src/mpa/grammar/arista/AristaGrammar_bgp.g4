parser grammar AristaGrammar_bgp;

options {
   tokenVocab = AristaLexer;
}

router_bgp_stanza
:
    ROUTER BGP asnumber = DEC NEWLINE	
	(neighbor_route_map | neighbor | anything_else)*
;

anything_else
:
	(ANYTHING ~NEWLINE* NEWLINE)
	    | (BGP ~NEWLINE* NEWLINE)
        | (NEIGHBOR ~NEWLINE* NEWLINE)
;

neighbor
:
	(NEIGHBOR ipaddress = NAME REMOTE_AS asnumber = NAME NEWLINE)		        		
;

neighbor_route_map
:
        (NEIGHBOR NAME ROUTE_MAP routeMap = NAME ~NEWLINE* NEWLINE)
;
 