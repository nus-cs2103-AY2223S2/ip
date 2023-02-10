.PHONY: run compile test

run:
	./gradlew run -q --console=plain  

compile: 
	./gradlew build

test: 
	cd text-ui-test; bash runtest.sh