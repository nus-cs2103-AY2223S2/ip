.PHONY: run compile

run: compile
	java -cp bin Duke

compile: src/main/java/*.java
	javac -cp src/main/java -Xlint:none -d bin src/main/java/*.java

