# User Guide
# User Guide
**Treebot** is a desktop application to help you manage your tasks, optimized for users who are familiar
with Command Line Interface (CLI) applications, or simply prefer typing, while still having the
benefits of a Graphical User Interface (GUI)

## Table of Contents
* [Quick Start](#quick-start)
* [Features](#features)
    * [Adding tasks](#adding-tasks)
        * [ToDo](#todo): `todo`
        * [Event](#event): `event`
        * [Deadline](#deadline): `deadline`
    * [Listing all your tasks](#listing-tasks): `list`
    * [Marking a task as done](#marking-a-task): `mark`
    * [Unmarking a task](#unmarking-a-task): `unmark`
    * [Deleting a task](#deleting-a-task): `delete`
    * [Finding a task](#finding-tasks): `find`
    * [Undo action](#undo-tasks--undo) `undo`

---

## Quick Start
1. Ensure you have Java 11 or above installed on your computer.
2. Download the latest `treebot.jar` file from [here](https://github.com/pkwangjoo/ip/releases/tag/A-Release).
3. Copy the `.jar` file to the folder that you want to use as the _home folder_ for the application.
4. Double-click on the file to start the application. You should see a similar graphical user interface.
   <img src = "Ui.png" width = "400px">
## Features


## Adding Tasks

Adds a task to be tracked by the application. Tasks can range from 3 types (ToDo, Event, Deadline).

**Format:** `todo/event/deadline ...`

**Examples:**
* `todo Some Task` creates a ToDo task.
* `event Some Event ...` creates an Event task. 
* `deadline Some Deadline ...` creates a Deadline task.

### ToDo: `todo`

Todo tasks.

**Format:** `todo <TASK_DESCRIPTION>`

**Examples:**
* `todo read book`

**Expected Output:**
```
I have added the following task:
[T][] read book
Now you have 1 tasks in the list
```

### Event: `event`

Tasks that happen over a range of datetime.

**Format:** `event <TASK_DESCRIPTION> /from <START_DATE> /to <END_DATE>` 

**Examples:**
* `event project meeting /from 18/2/2023 1800 /to 18/2/2023 1900`


**Expected Output:**
```
I have added the following task:
[E][] project meeting (from: Feb 18 2023 1800 to: Feb 18 2023 1900)
Now you have 1 tasks in the list
```

### Deadline: `deadline`

Tasks that have to be completed before a specific date/time.

**Format:** `deadline <TASK_DESCRIPTION> /by <DEADLINE>`

**Examples:**
* `deadline return book to libary /by 18/2/2023 2000`

**Expected Output:**
```
I have added the following task:
[D][] return book to libary (by: Feb 18 2023 2000)
Now you have 1 tasks in the list
```
## Listing Tasks: `list`

Lists all the tasks that are currently being tracked in the application.

**Format:** `list`

**Expected Output:**
```
Here are the tasks you have at hand: 
1.[T][] read
2.[T][] do
3.[E][] project meeting (from: Feb 18 2023 1800 to: Feb 18 2023 1900)
4.[D][] return book to libary (by: Feb 18 2023 2000)
```

## Marking a Task: `mark`

Marks a task as done

**Format:** `mark <TASK_INDEX>`

**Examples:**
* `list` followed by `mark 1` marks the first task in the list as done.

**Expected Output:**
```
I have marked the following task as done: 
[T][X] read
```

## Unmarking a Task: `unmark`

Unmarks a task as done.

**Format:** `unmark <TASK_INDEX>`

**Examples:**
* `list` followed by `unmark 1` unmarks the first task in the list as done.

**Expected Output:**
```
I have marked the following task as not Done yet: 
[T][] read
```

## Deleting a Task: `delete`

Deletes a task from the list of tracked tasks.

**Format:** `delete <TASK_INDEX>`

**Examples:**
* `list` followed by `delete 1` deletes the first task in the list.

**Expected Output:**
```
Tree has removed the following task: 
[T][] read
Now you have 3 tasks remaining.
```

## Finding Tasks: `find`

Finds tasks that have descriptions containing the given keyword.

**Format:** `find <KEYWORD>`

**Examples:**
* `find book` finds and lists all tasks that contain `book` in their task descriptions.

**Expected Output:**
```
Here are the tasks that I have found with the keyword: book
1.[D][] return book to libary (by: Feb 18 2023 2000)
2.[D][] write book club report (by: Feb 18 2023 2100)
```


## undo tasks: `undo`
Undoes the most recent command.
**Format:** `undo`

**Examples:**
* `deadline write book club report /by 18/2/2023 2100` adds a deadline task to the list
* `undo` will remove the task that was recently added

**Expected Output:**
```
Here are the tasks you have at hand: 
1.[E][] project meeting (from: Feb 18 2023 1800 to: Feb 18 2023 1900)
2.[D][] return book to libary (by: Feb 18 2023 2000)
3.[D][] write book club report (by: Feb 18 2023 2100)
```

```
Successfully Undo action:
```

```
Here are the tasks you have at hand: 
1.[E][] project meeting (from: Feb 18 2023 1800 to: Feb 18 2023 1900)
2.[D][] return book to libary (by: Feb 18 2023 2000)
```