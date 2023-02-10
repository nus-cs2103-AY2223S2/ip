# DukePro - User Guide

DukePro is a **desktop chatbot for managing your tasks, optimized for use via a Command Line Interface** (CLI) while still having the benefits of a Graphical User Interface (GUI). If you can type fast, DukePro can get your task management game on par or even faster than traditional GUI apps.

- [Quick Start](#quick-start)
- [Features](#features)
  - [Viewing help: `help`](#viewing-help-help)
  - [Exiting the app: `bye`](#exiting-the-app-bye)
  - [Adding a Todo: `todo`](#adding-a-todo-todo)
  - [Adding a Deadline: `deadline`](#adding-a-deadline-deadline)
  - [Adding an Event: `event`](#adding-an-event-event)
  - [Listing tasks: `list`](#listing-tasks-list)
  - [Finding a task: `find`](#finding-a-task-find)
  - [Marking a task as done: `mark`](#marking-a-task-as-done-mark)
  - [Unmark a task: `unmark`](#unmark-a-task-unmark)
  - [Deleting a task: `delete](#deleting-a-task-delete)
- [Command Summary](#command-summary)

## Quick Start

1. Ensure you have Java `11` or above installed in your Computer.
2. Download the latest `duke.jar` from [here](https://github.com/gremmyz/ip/releases/tag/BCD-Extension).
3. Copy the file to an empty folder that you want to use as the *home folder* for your DukePro.
4. Open a command terminal, `cd` into the folder you put the jar file in, and use the `java -jar duke.jar` command to run the application.
5. Type the command that you wish to execute into the command box and hit Enter to execute it. e.g. typing `help` and pressing Enter will provide you with the support link.
6. Some example commands that you can try are:
- `list`: Lists all the tasks.
- `todo NewTask`: Adds a Todo named NewTask into the task list.
- `delete 3`: Deletes the 3rd task in the current list.
- `bye`: Exits the app.
7. Refer to the [features](#features) below for the details of each command!

---

## Features 

### Viewing help: `help`
Returns a message explaining how to access the help page.<br>
Format: `help`

### Exiting the app: `bye`
Exits the application.<br>
Format: `bye`

### Adding a Todo: `todo`
Adds a Todo task to the task list.<br>
Command format: `todo TASK_NAME`<br>
Returns: `[T][ ] TASK_NAME`


### Adding a Deadline: `deadline`
Adds a Deadline task to the task list.<br>
Command format: `deadline TASK_NAME /by YYYY-MM-DD`<br>
Returns: `[D][ ] TASK_NAME (by: Month Day Year)`

### Adding an Event: `event`
Adds a Event task to the task list.<br>
Command format: `event TASK_NAME /at YYYY-MM-DD`<br>
Returns: `[E][ ] TASK_NAME (at: Month Day Year)`

### Listing tasks: `list`
Lists all the current tasks on the task list.<br>
Command format: `list`

### Finding a task: `find`
Finds a task based on the given keyword.<br>
Command format: `find KEYWORD`

### Marking a task as done: `mark`
Marks the task at the specified index as done. Note that the index must be an integer.<br>
Command format: `mark TASK_NUMBER`<br>
Returns: `[T][X] TASK_NAME`

### Unmark a task: `unmark`
Unmarks the task at the specified index to indicate that the task is not done. Note that the index must be an integer.<br>
Command format: `unmark TASK_NUMBER`<br>
Returns: `[T][ ] TASK_NAME`

### Deleting a task: `delete`
Deletes the task at the specified index. Note that the index must be an integer.<br>
Command format: `delete TASK_NUMBER`

---

## Command summary

Action | Format
--------|------------------
**Todo** | `todo TASK_NAME`
**Deadline** | `deadline TASK_NAME /by YYYY-MM-DD`
**Event** | `event TASK_NAME /at YYYY-MM-DD`
**Mark** | `mark TASK_NUMBER`
**Unmark** | `unmark TASK_NUMBER`
**Delete** | `delete TASK_NUMBER`
**Find** | `find KEYWORD`
**List** | `list`
**Help** | `help`
**Exit** | `bye`


