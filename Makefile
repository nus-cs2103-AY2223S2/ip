.PHONY: run compile test

run:
	./gradlew shadowJar
	java -jar build/libs/duke.jar

compile: 
	./gradlew build

test: 
	./gradlew test

checkstyle:
	./gradlew checkstyleMain checkstyleTest