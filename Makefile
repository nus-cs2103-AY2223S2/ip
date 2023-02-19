.PHONY: run compile test

run:
	./gradlew shadowJar
	java -jar build/libs/duke.jar

compile: 
	./gradlew build

test: 
	cd text-ui-test; bash runtest.sh

checkstyle:
	./gradlew checkstyleMain checkstyleTest