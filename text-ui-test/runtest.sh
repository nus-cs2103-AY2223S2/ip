#!/usr/bin/env bash

# create bin directory if it doesn't exist
if [ ! -d "../bin" ]
then
    mkdir ../bin
fi

# delete output from previous run
if [ -e "./ACTUAL.TXT" ]
then
    rm ACTUAL1.TXT
fi

# compile the code into the bin folder, terminates if error occurred
if ! javac -cp ../src/main/java -Xlint:none -d ../bin ../src/main/java/*.java
then
    echo "********** BUILD FAILURE **********"
    exit 1
fi

# run the program, feed commands from input1.txt file and redirect the output to the ACTUAL1.TXT
java -classpath ../bin Duke < input1.txt > ACTUAL1.TXT

# compare the output to the expected output
diff ACTUAL1.TXT EXPECTED1.TXT
if [ $? -eq 0 ]
then
    echo "Test result 1: PASSED"
else
    echo "Test result 1: FAILED"
fi

# run the program, feed commands from input2.txt file and redirect the output to the ACTUAL2.TXT
java -classpath ../bin Duke < input2.txt > ACTUAL2.TXT

# compare the output to the expected output
diff ACTUAL2.TXT EXPECTED2.TXT
if [ $? -eq 0 ]
then
    echo "Test result 2: PASSED"
else
    echo "Test result 2: FAILED"
fi

# run the program, feed commands from input3.txt file and redirect the output to the ACTUAL3.TXT
java -classpath ../bin Duke < input3.txt > ACTUAL3.TXT

# compare the output to the expected output
diff ACTUAL3.TXT EXPECTED3.TXT
if [ $? -eq 0 ]
then
    echo "Test result 3: PASSED"
else
    echo "Test result 3: FAILED"
fi

# run the program, feed commands from input4.txt file and redirect the output to the ACTUAL4.TXT
java -classpath ../bin Duke < input4.txt > ACTUAL4.TXT

# compare the output to the expected output
diff ACTUAL4.TXT EXPECTED4.TXT
if [ $? -eq 0 ]
then
    echo "Test result 4: PASSED"
else
    echo "Test result 4: FAILED"
fi

# run the program, feed commands from input5.txt file and redirect the output to the ACTUAL5.TXT
java -classpath ../bin Duke < input5.txt > ACTUAL5.TXT

# compare the output to the expected output
diff ACTUAL5.TXT EXPECTED5.TXT
if [ $? -eq 0 ]
then
    echo "Test result 5: PASSED"
    exit 0
else
    echo "Test result 5: FAILED"
    exit 1
fi