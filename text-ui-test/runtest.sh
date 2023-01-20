#!/usr/bin/env bash

# delete output from previous run
if [ -e "./ACTUAL.TXT" ]
then
    rm ACTUAL.TXT
fi

# Uses gradle shadowJar to build
cd ..
./gradlew clean shadowJar
if ! [ -d "./build" ]
then
    echo "********** BUILD FAILURE **********"
    exit 1
fi

# Go back to test directory
cd text-ui-test
# run the program, feed commands from input.txt file and redirect the output to the ACTUAL.TXT
java -cp "../build/libs/ip-1.0-SNAPSHOT-all.jar:../build/classes/java/main" Main < input.txt > ACTUAL.TXT

# convert to UNIX format
cp EXPECTED.TXT EXPECTED-UNIX.TXT
dos2unix ACTUAL.TXT EXPECTED-UNIX.TXT

# compare the output to the expected output
diff ACTUAL.TXT EXPECTED-UNIX.TXT
if [ $? -eq 0 ]
then
    echo "Test result: PASSED"
    exit 0
else
    echo "Test result: FAILED"
    exit 1
fi