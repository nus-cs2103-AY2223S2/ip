# User Guide
>Duke will remind you at the start if you have any deadlines that are due on the day.

## Quick Start
1. Ensure you have Java 11 or above installed in your Computer.
2. Download the latest duke.jar from [here](https://github.com/wz2k/ip/releases).
3. Copy the file to the folder you want to use as the home folder for your Duke.
4. Open a command terminal, cd into the folder you put the jar file in, and use the java -jar duke.jar command to run the application.
## Commands
Type your commands into the text box and either click send or press the enter button.

### todo
Creates a todo task.
```java
todo <description>
```
eg. ```todo read book```
### deadline
Creates a deadline task.
```java
deadline <description> /by <date>
```
```<date>``` has to follow `YYYY-MM-DD` format.

eg. ```deadline return book /by 2023-05-12```

### event
Creates a event task.
```java
event <description> /from <start date> /to <end date>
```
```<start date>``` and ```<end date>``` have to follow `YYYY-MM-DD` format.

eg. ```deadline book carnival /from 2023-05-12 /to 2023-06-10```

### list
List all tasks.
```java
list
```
eg. ```list``

### find
Finds tasks using a keyword.
```java
find <keyword>
```
eg. ```find read```

### mark
Marks a task as done.
```java
mark <task number>
```
```<task number>``` can be obtained from ```list``` or ```find```

eg. ```mark 3```
### unmark
Marks a task as not done.
```java
unmark <task number>
```
```<task number>``` can be obtained from ```list``` or ```find```

eg. ```unmark 3```

### delete
Deletes a task
```java
delete <task number>
```
```<task number>``` can be obtained from ```list``` or ```find```

eg. ```delete 3```

### bye
Provides information on how to exit the program.
```java
bye
```
eg. ```bye```

To exit the program, click on the X button in the top right corner.
