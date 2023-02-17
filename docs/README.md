# User Guide

JamesBot is **a chatbot that helps you keep track of all your tasks, optimized for use via a Command Line Interface** (CLI)
while still having the benefits of a Graphical User Interface (GUI). If you can type fast, JamesBot can get your tasks
management done faster than traditional GUI apps.

## Features 

### Viewing all commands: `help`
Shows a message listing all the commands available in the program.

**Command Format:**
- `help`

### Adding a task: `todo` `event` `deadline`

Adds a task of type todo, event, or deadline.

**Command Format:**
- `todo DESCRIPTION`
- `deadline DESCRIPTION /by DATE_TIME(d/MM/yyyy HHmm)`
- `event DESCRIPTION /from DATE_TIME(d/MM/yyyy HHmm) /to DATE_TIME(d/MM/yyyy HHmm)`

**Examples:**
- `todo read book`
- `deadline project /by 25/03/2000 1800`
- `event project meeting /from 25/03/2000 1800 /to 25/03/2000 1900`


### Listing tasks: `list`
Lists all of your tasks in a message, in the following format:
```
1. [TASK_TYPE][IS_DONE] DESCRIPTION
2. [TASK_TYPE][IS_DONE] DESCRIPTION
```

**Command Format:**
- `list`

### Marking tasks: `mark` `unmark`
Mark your task as complete or incomplete using `mark` or `unmark` respectively.

**Command Format:**
- `mark INDEX_OF_TASK`
- `unmark INDEX_OF_TASK`

**Examples:**
- `mark 1`
- `unmark 1`

### Deleting tasks: `delete`
Deletes a task from your list.

**Command Format:**
- `delete INDEX_OF_TASK`

**Examples:**
- `delete 1`

### Finding tasks: `find`
Find tasks that contain a keyword.

**Command Format:**
- `find KEYWORD`

**Examples:**
- `find book`

### Exiting the program: `bye`
Exits the program.

**Command Format:**
- `bye`

## Command Summary
| Command      | Format, Examples                                                                                                        |
|--------------|-------------------------------------------------------------------------------------------------------------------------|
| **todo**     | `todo DESCRIPTION` e.g `todo read book`                                                                                 |
| **deadline** | `deadline DESCRIPTION /to DATE_TIME` e.g `deadline project /by 25/03/2000 1800`                                         |
| **event**    | `event DESCRIPTION /from DATE_TIME /to DATE_TIME` e.g `event project meeting /from 25/03/2000 1800 /to 25/03/2000 1900` |
| **mark**     | `mark INDEX_OF_TASK` e.g `mark 1`                                                                                       |
| **unmark**   | `unmark INDEX_OF_TASK` e.g `unmark 1`                                                                                   |
| **delete**   | `delete INDEX_OF_TASK` e.g `delete 1`                                                                                   |
| **find**     | `find KEYWORD` e.g `find book`                                                                                          |
| **list**     | `list`                                                                                                                  |
| **help**     | `help`                                                                                                                  |
| **bye**      | `bye`                                                                                                                   |
