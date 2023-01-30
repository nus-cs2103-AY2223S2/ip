#!/usr/bin/env bash

# create bin directory if it doesn't exist
if [ ! -d "../bin" ]
then
    mkdir ../bin
fi

# delete output from previous run
if [ -e "./ACTUAL.TXT" ]
then
    rm ACTUAL.TXT
fi

# compile the code into the bin folder, terminates if error occurred
# if ! javac -cp ../src/main/java/ -Xlint:none -d ../bin ../src/main/java/*.java
if ! javac -cp /Users/tituswe/Desktop/Y2S2/CS2103T/ip/src/main/java/saturday -Xlint:none -d /Users/tituswe/Desktop/Y2S2/CS2103T/ip/bin /Users/tituswe/Desktop/Y2S2/CS2103T/ip/src/main/java/saturday/*.java \
  /Users/tituswe/Desktop/Y2S2/CS2103T/ip/src/main/java/saturday/collections/*.java \
  /Users/tituswe/Desktop/Y2S2/CS2103T/ip/src/main/java/saturday/command/*.java \
  /Users/tituswe/Desktop/Y2S2/CS2103T/ip/src/main/java/saturday/exceptions/*.java \
  /Users/tituswe/Desktop/Y2S2/CS2103T/ip/src/main/java/saturday/models/*.java \
  /Users/tituswe/Desktop/Y2S2/CS2103T/ip/src/main/java/saturday/utilities/*.java 
then
    echo "********** BUILD FAILURE **********"
    exit 1
fi

# run the program, feed commands from input.txt file and redirect the output to the ACTUAL.TXT
# java -classpath ../bin Saturday < input.txt > ACTUAL.TXT
java -classpath /Users/tituswe/Desktop/Y2S2/CS2103T/ip/bin Saturday < input.txt > ACTUAL.TXT

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
