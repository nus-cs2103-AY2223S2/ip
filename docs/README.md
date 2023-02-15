# User Guide for `/* ask-prof-duke */`


## Installation

1. Download the JAR file from here. 
2. Move the JAR file into an empty folder. The folder name is up to you.
3. Open the JAR file and proceed to start a chat session with prof duke!


## Features

### Add task
Add a task that can be any of the three supported types: Todo, Deadline, Event.

The command for adding each task type is given as follows:

| Task Type | Command                                                            | Example of usage                                            |
|-----------|--------------------------------------------------------------------|-------------------------------------------------------------|
| Todo      | `todo <task title>`                                                | `todo go shopping`                                          |
| Deadline  | `deadline <task title> /by <YYYY-M-d> <HHmm>`                      | `deadline do homework /by 2023-2-15 1500`                   |
| Event     | `event <task title> /from <YYYY-M-d> <HHmm> /to <YYYY-M-d> <HHmm>` | `event career fair /from 2023-2-10 1200 /to 2023-2-11 1700` |


### Delete task
Delete a task of a provided task number in the task list.<br>(can be displayed by `list` command. See next section on **List tasks**.)

**Command:** `delete <task number>`


### List tasks
Display all tasks that are currently recorded in the task list for the current session.

**Command:** `list`

**Expected outcome**:
```
Here are the tasks in your list:
[T][] do calculus assignment
[D][] write up user guide for cs2103T ip (by: 17 Feb 2023 2359)
[E][X] christmas party @ ben's (from: 25 Dec 2023 1800 to: 26 Dec 2023 0200)
```


### Mark task
Record a task of a given task number as completed.

**Command:** `mark <task number>`


### Mark task
Record a task of a given task number as not completed.

**Command:** `unmark <task number>`


### Find tasks
Find a task by title. A list of tasks containing the search query specified will be returned.

**Command:** `find <search query>`

**Example of usage:** `find homework`

**Expected outcome:**
```
Here are the matching tasks in your list:
[T][] math homework
[T][X] science homework
```


### Undo
Undo the previous command executed.

**Command:** `undo`


### Exit chat session
End the current chat session. The updated list of tasks will be saved in the local storage.

**Command:** `bye`

