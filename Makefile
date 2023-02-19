.PHONY: run compile test

run:
	./gradlew run

compile: 
	./gradlew build

test: 
	cd text-ui-test; bash runtest.sh

checkstyle:
	./gradlew checkstyleMain checkstyleTest