# User Guide

Duke is a desktop app for managing your tasks.

- [Features](#features)
  - [Adding new tasks]()
    - [Adding a new todo: `todo`](#adding-a-new-todo--todo)
    - [Adding a new deadline: `deadline`](#adding-a-new-deadline--deadline)
    - [Adding a new event: `event`](#adding-a-new-event--event)
  - [Listing all tasks: `list`](#listing-all-tasks--list)
  - [Marking a task as done: `mark`](#marking-a-task-as-done--mark)
  - [Unmarking a task: `unmark`](#unmarking-a-task--unmark)
  - [Deleting a task: `delete`](#deleting-a-task--delete)
  - [Locating a task by name: `find`](#locating-tasks-by-name--find)
  - [Leaving EeveeBot: `bye`](#leaving-eeveebot--bye)
  - [Saving the data](#saving-the-data)
- [Command summary](#command-summary)

---

## Features 

### Adding new tasks

<br>

#### Adding a new todo: `todo`

Adds a new todo to the task list.

Format: `todo TASK`

Example: `todo submit assignment`

<br>

#### Adding a new deadline: `deadline`

Adds a new deadline to the task list.

Format: `deadline /by DEADLINE`
- Format of `DEADLINE` must be in the form `yyyy-MM-dd HH:mm`. 
e.g. 30 April 2022 11am would be `2022-04-31 11:00`.

Example: `deadline complete assignment /by 2022-02-17 23:59`

<br>

#### Adding a new event: `event`

Adds a  new event to the task list.
- The given event will not be added if it clashes with an event already inside the task list.

Format: `event /from START /to END`
- Format of `START` and `END` Must be in the form `yyyy-MM-dd HH:mm`.
  e.g. 30 April 2022 11am would be `2022-04-31 11:00`.

Example: `event nus career fest /from 2023-02-10 11:00 /to 2023-02-10 15:00`

---

### Listing all tasks: `list`

Shows a list of all tasks in the task list.

Format: `list`

---

### Marking a task as done: `mark`

Marks the specified task as done.

Format: `mark INDEX`
- Marks the task at the specified `INDEX` as done.
- The index refers to the index number shown in the displayed task list when `list` is called.
- The index **must be a positive integer** 1, 2, 3, ...

Example: `mark 2` marks the 2nd task in the task list as done.

---

### Unmarking a task: `unmark`

Unmarks the specified task if the task was marked as done.

Format: `unmark INDEX`
- Unmarks the task at the specified `INDEX`.
- The index refers to the index number shown in the displayed task list when `list` is called.
- The index **must be a positive integer** 1, 2, 3, ...
  
Example: `unmark 1` unmarks the 1st task in the task list.

---

### Deleting a task: `delete`

Deletes the specified task from the task list.

Format: `delete INDEX`
- Deletes the task at the specified `INDEX`.
- The index refers to the index number shown in the displayed task list when `list` is called.
- The index **must be a positive integer** 1, 2, 3, ...

Example: `delete 3` deletes the 3rd task in the task list.

---

### Locating tasks by name: `find`

Finds tasks with names containing the given keyword.

Format: `find KEYWORD`
- The search is case-sensitive. e.g. `COMPLETE` will not match `complete`
- Only the name of the task is searched.
- Only **one** `KEYWORD` will be accepted. e.g. `find complete` will match `complete assignment` 
but `find complete assignment` will not match `complete assignment`
- Only full words will be matched. e.g. `com` will not match `complete`

Examples:
- `find complete` returns `complete assignment` and `complete mission`
- `find nus` returns `nus career fest` and `nus well-being day`
![`find` example](/find.png)

---

### Leaving EeveeBot: `bye`

Says bye to EeveeBot and exits the program.

Format: `bye`

---

### Saving the data

EeveeBot data are saved in the hard disk automatically after any command that changes the data.
There is no need to save manually.

---

## Command Summary

| Action       | Format, Examples                                                                                         |
|--------------|----------------------------------------------------------------------------------------------------------|
| Add ToDo     | `todo TASK`<br/>e.g. `todo submit assignment`                                                            |
| Add Deadline | `deadline /by DEADLINE`<br/>e.g. `deadline complete assignment /by 2022-02-17 23:59`                     |                                                      |
| Add Event    | `event /from START /to END`<br/>e.g. `event nus career fest /from 2023-02-10 11:00 /to 2023-02-10 15:00` |
| List         | `list`                                                                                                   |
| Mark         | `mark INDEX`<br/>e.g. `mark 2`                                                                           |
| Unmark       | `unmark INDEX`<br/>e.g. `unmark 1`                                                                       |
| Delete       | `delete INDEX`<br/>e.g. `delete 3`                                                                       |
| Find         | `find KEYWORD`<br/>e.g. `find complete`                                                                  |
| Exit         | `bye`                                                                                                    |
