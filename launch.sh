#!/usr/bin/env bash

# create bin directory if it doesn't exist
if [ ! -d "./bin" ]
then
    mkdir ./bin
fi

# compile the code into the bin folder, terminates if error occurred
if ! javac -cp ./src/main/java -Xlint:none -d ./bin ./src/main/java/Duke.java
then
    echo "********** BUILD FAILURE **********"
    exit 1
fi

# run the program
java -cp ./bin Duke