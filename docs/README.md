# User Guide
>Duke will remind at the start you if you have any deadlines that are due on the day.

## Commands
Type your commands into the text box and eithe click send or press the enter button.

### todo
Creates a todo task.
```java
todo <description>
```
### deadline
Creates a deadline task.
```java
deadline <description> /by <date>
```
```<date>``` has to follow `YYYY-MM-DD` format.


### event
Creates a event task.
```java
event <description> /from <start date> /to <end date>
```
```<start date>``` and ```<end date>``` has to follow `YYYY-MM-DD` format.


### list
List all tasks.
```java
list
```


### find
Finds tasks using a keyword.
```java
find <keyword>
```

### mark
Marks a task as done.
```java
mark <task number>
```
```<task number>``` can be obtained from ```list``` or ```find```

### unmark
Marks a task as not done.
```java
unmark <task number>
```
```<task number>``` can be obtained from ```list``` or ```find```

### delete
Deletes a task
```java
delete <task number>
```
```<task number>``` can be obtained from ```list``` or ```find```

### bye
Provides information on how to exit the program.
```java
bye
```

To exit the program, click on the X button in the top right corner.
