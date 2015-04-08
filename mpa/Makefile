all:
	java -jar lib/antlr-4.4-complete.jar CiscoGrammarCommonLexer.g4 
	java -jar lib/antlr-4.4-complete.jar CiscoGrammar.g4 -no-listener 
	javac -classpath .:lib/antlr-4.4-complete.jar  *.java
run:
	java -classpath .:lib/antlr-4.4-complete.jar Driver cisco_test.cfg

clean:
	rm *.tokens CiscoGrammar*.java *.class
