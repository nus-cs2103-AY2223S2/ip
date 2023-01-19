.PHONY: run compile test

run: compile
	java -cp bin Main

compile: src/main/java/*.java
	javac -cp src/main/java -Xlint:none -d bin src/main/java/*.java

test: 
	cd text-ui-test; bash runtest.sh