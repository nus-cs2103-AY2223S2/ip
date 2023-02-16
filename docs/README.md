# User Guide for `/* ask-prof-duke */`
Too much to :poop: to remember? FRET NOT! Prof Duke is here to manage your woes! Start using **`/* ask-prof-duke */`**, a chatbot that automates your task
tracking endeavours and make life so much simpler for you!

![demo](https://user-images.githubusercontent.com/87931905/219407500-f8e047ab-b3b0-4fc3-9560-79630b30ff20.gif)


## Table of Contents

1. [Installation](#installation)
1. [Features](#features)
1. [Usage](#usage)
    1. [Add task](#add-task)
    1. [Delete task](#delete-task)
    1. [List tasks](#list-tasks)
    1. [Mark task](#mark-task)    
    1. [Unmark task](#unmark-task)
    1. [Find tasks](#find-tasks)    
    1. [Undo](#undo)    
    1. [Exit chat session](#exit)     
1. [Frequently Asked Questions (FAQ)](#faq)
    
    
## Installation <a name="installation"></a>

1. Download the JAR file from [here](https://github.com/jmestxr/ip/releases/download/v0.2/duke.jar). 
2. Open the JAR file by typing the command `java -jar duke.jar` on your command line interface (CLI). Make sure that on the CLI, you are in the same directory (folder) that contains the downloaded JAR file.
3. Proceed to start a chat session with Prof Duke through the graphical user interface (GUI)!

If all goes well, your app should look like this:<br>
<img width="612" alt="showcase" src="https://user-images.githubusercontent.com/87931905/219394138-73503f76-dbf7-47e4-8712-e11a2d536eff.png">

    
## Features <a name="features"></a>
1. **Add task**: Add a new task to be recorded.
1. **Delete task**: Delete an existing task.
1. **List tasks**: List all tasks that are currently recorded.
1. **Mark task**: Record a task as complete.
1. **Unmark task**: Record a task as incomplete.
1. **Find tasks**: Find a task by title.
1. **Undo**: Undo the previous command executed.
1. **Exit**: End the current chat session.



## Usage <a name="usage"></a>

### Add task <a name="add-task"></a>
Add a task that can be any of the three supported types: Todo, Deadline, Event.
The command for adding each task type is given as follows:

| Task Type | Command                                                            | Example of usage                                            |
|-----------|--------------------------------------------------------------------|-------------------------------------------------------------|
| Todo      | `todo <task title>`                                                | `todo go shopping`                                          |
| Deadline  | `deadline <task title> /by <YYYY-M-d> <HHmm>`                      | `deadline do homework /by 2023-2-15 1500`                   |
| Event     | `event <task title> /from <YYYY-M-d> <HHmm> /to <YYYY-M-d> <HHmm>` | `event career fair /from 2023-2-10 1200 /to 2023-2-11 1700` |


### Delete task <a name="delete-task"></a>
Delete a task of a provided task number in the task list.<br>(can be displayed by `list` command. See next section on **List tasks**.)

**Command:** `delete <task number>`


### List tasks <a name="list-tasks"></a>
Display all tasks that are currently recorded in the task list for the current session.

**Command:** `list`

**Expected outcome**:
```
Here are the tasks in your list:
[T][] do calculus assignment
[D][] write up user guide for cs2103T ip (by: 17 Feb 2023 2359)
[E][X] christmas party @ ben's (from: 25 Dec 2023 1800 to: 26 Dec 2023 0200)
```


### Mark task <a name="mark-task"></a>
Record a task of a given task number as completed.

**Command:** `mark <task number>`


### Unmark task <a name="unmark-task"></a>
Record a task of a given task number as not completed.

**Command:** `unmark <task number>`


### Find tasks <a name="find-tasks"></a>
Find a task by title. A list of tasks containing the search query specified will be returned.

**Command:** `find <search query>`

**Example of usage:** `find homework`

**Expected outcome:**
```
Here are the matching tasks in your list:
[T][] math homework
[T][X] science homework
```


### Undo <a name="undo"></a>
Undo the previous command executed.

**Command:** `undo`


### Exit chat session <a name="exit"></a>
End the current chat session. The updated list of tasks will be saved in the local storage.

**Command:** `bye`


## Frequently Asked Questions (FAQ) <a name="faq"></a>

> **I can't seem to save my changes made to the task list from the previous session. What is happening?**<br>

*You have probably closed the GUI rather than ending the chat session through the `bye` command. At the moment, the app is only able to save your changes upon exiting the chat session through the `bye` command.*<br><br>


> **Help! I'm unable to open the app and nothing happens after double clicling the JAR file...**<br>

*You should run the app's JAR file through the command `java -jar duke.txt` on your CLI.*<br><br>



> **I'm still unable to open the app's JAR file after running the command `java -jar duke.txt` on my CLI! I keep getting this error: `Error: Unable to access jarfile duke.jar`.**<br>

*Before running the command, make sure that on the CLI, you are in the same directory (folder) that contains the downloaded JAR file.*<br><br>

