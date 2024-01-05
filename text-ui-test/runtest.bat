@ECHO OFF

REM create bin directory if it doesn't exist
if not exist ..\bin mkdir ..\bin

REM delete output from previous run
if exist ACTUAL.TXT del ACTUAL.TXT

REM compile the code into the bin folder

@REM @@author Yufannnn-reused
@REM Reused from https://github.com/JeremyLoh/duke/blob/master/text-ui-test/runtest.bat
@REM with minor modifications to address the path issue in the given runtest.bat file
dir /s /B ..\src\main\java\*.java > source.txt
javac -cp ..\src\duke -Xlint:none -d ..\bin @source.txt
@REM @@author

IF ERRORLEVEL 1 (
    echo ********** BUILD FAILURE **********
    exit /b 1
)
REM no error here, errorlevel == 0

REM run the program, feed commands from input.txt file and redirect the output to the ACTUAL.TXT
java -classpath ..\bin duke.Duke < input.txt > ACTUAL.TXT

REM compare the output to the expected output
FC ACTUAL.TXT EXPECTED.TXT
