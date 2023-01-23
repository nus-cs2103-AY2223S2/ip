@ECHO OFF

REM create bin directory if it doesn't exist
if not exist ..\bin mkdir ..\bin

REM delete output from previous run
if exist ACTUAL.TXT del ACTUAL.TXT
if exist duke.txt del duke.txt

REM compile the code into the bin folder
dir /s /B ..\src\main\java\kude\*.java > ..\bin\sources.txt
javac -sourcepath ..\src\main\java\kude -Xlint:none -d ..\bin @..\bin\sources.txt
IF ERRORLEVEL 1 (
    echo ********** BUILD FAILURE **********
    exit /b 1
)
REM no error here, errorlevel == 0

REM run the program, feed commands from input.txt file and redirect the output to the ACTUAL.TXT
java -classpath ..\bin kude.Duke < input.txt > ACTUAL.TXT

REM compare the output to the expected output
FC ACTUAL.TXT EXPECTED.TXT
