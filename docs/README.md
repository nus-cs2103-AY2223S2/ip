# User Guide
Duke is a desktop app for keeping track of tasks, optimised for use via a Command Line Interface (CLI) while having the benefits of a Graphical User Interface (GUI).
## Quick Start
1. Ensure you have `Java 11` or above installed in your computer
2. Download the latest repos.jar
3. Copy the file to the folder you want to use as the home folder for your app.
4. Open a command terminal, cd into the folder you copied the jar file in, and use java -jar repos.jar command to run it. A GUI siimilar to the example below should appear.

![](C:\repos\docs\Ui.png)

## Features

### List all tasks
* Command: list
### Create a todo task
* Command: todo [a todo task]
* Example: todo go for therapy
### Create a deadline task
* Command: deadline [a deadline task] /by: yyyy-mm-dd
* Example: deadline cry /by: 2023-12-12
### Create an event task
* Command: event [an event task] /from: yyyy-mm-dd /to: yyyy-mm-dd 
* Example: event suffer /from: 2002-05-23 /to: 2023-12-25
### Mark task as done
* Command: mark [task index]
* Example: mark 2
### Mark task as not done
* Command: unmark [task index]
* Example: unmark 2
### Delete a task
* Command: delete [task index]
* Example: delete 3
### Find tasks with common keyword
* Command: find [keyword]
* Example: find cry
* Explanation: this retrieves all task containing "cry" keyword
### Attach priority level to task
* Command: priority [task index] [priority level]
* Example: priority 2 1
* Explanation: this sets the task at index 2 to be of HIGH priority (1)



```
expected output
```
