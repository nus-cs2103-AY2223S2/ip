# Duke
![Image of Duke](Ui.png)

Duke is an application that keeps track of your various tasks. This project was built on the Duke greenfield Java template.

## Requirements
1. Java 11 or above must be installed

## Getting Started
1.	Download the JAR file
2.	Move the JAR file to any directory on your computer
3.	Run the file by double clicking on it.

If step 3 fails, open the command prompt in the directory with the Jar file and run:
```
java -jar Duke.java
```
## Commands

### Add a Todo Task
```
todo DESCRRIPTION
```
Creates a Todo task. A Todo task is a basic task with only a description.

### Add a Deadline Task
```
deadline DESCRRIPTION /by DATE
```
Creates a Deadline task. A Deadline task is a task with a date attached.

### Add an Event Task
```
event DESCRRIPTION /from DATE /to DATE
```
Creates an Event task. A Event task is a task with two dates attached.

### List All Recorded Tasks
```
list
```
Shows the list of all tasks

### Find a Task
```
find KEYWORD
```
Finds tasks with the keyword. Regex can be used for advanced users.

### Mark/Unmark a Task
```
mark/unmark INDEX
```
Marks a task with the index shown in the list command as done or undone.

### Delete a Task
```
delete INDEX
```
Deletes a task with the index shown in the list command. Note that the index of other tasks will be updated to maintain a continuous order.

## Acknowledgements
Code for GUI was taken from SE-EDU.
