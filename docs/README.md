# DonkeyChat User Guide

DonkeyChat is a chatbot with functionality to keep track of your tasks and to-dos within a simple GUI interface!

## Features

### Storing Tasks in a Task List

#### Showing all Tasks in the List - `list`

Displays the contents of the task list.

Each task is displayed in the following form: `TASK_INDEX.[TASK_TYPE][MARKED] TASK_NAME`

Format: `list`

Sample Output:

```
Here are the tasks in your list:
1.[T][] Buy groceries
2.[D][] CS2103 Assg 1 (by: 13-Jul-2026 23:59)
3.[E][] Jumping session (from: 10-Aug-2023 18:00 to: 10-Aug-2023 20:00)
```

#### Finding a specific Task in the List - `find`

Displays every task with a matching name.

Format: `find TASK_NAME_SUBSTRING`

### Adding and Removing Tasks

Tasks can be ToDos, Events and Deadlines.

#### Adding ToDos - `todo`

Adds a simple ToDo task to the task list.

Format: `todo TASK_NAME`

#### Adding Deadlines - `deadline`

Adds a Deadline task to the task list with the given complete-by date. Date should follow the following
format: `12-10-2023 16:00`

Format: `deadline TASK_NAME /by BY_DATE`

#### Adding Events - `event`

Adds an Event task to the task list with the given start and end date. Date should follow the following
format: `12-10-2023 16:00`

Format: `event TASK_NAME /from FROM_DATE /to TO_DATE`

#### Removing Tasks - `delete`

Removes the specified task from the task list. Task index corresponds to the displayed index from the `list` command.

Format: `delete TASK_INDEX`

### Marking and Unmarking Tasks

Track the completion status of tasks by marking them.

#### Mark Task - `mark`

Marks a task as complete. Marked tasks are displayed with `[X]` when `list` or `find` are used.

Format: `mark TASK_INDEX`

#### Unmark Task - `unmark`

Removes completion mark from a task. Unmarked tasks are displayed as `[ ]` when `list` or `find` are used.

Format: `unmark TASK_INDEX`

### Miscellaneous Commands

#### Snooze a deadline - `snooze`

Easily extend the complete-by date of a Deadline task by any number of days!

Format: `snooze TASK_INDEX EXTEND_BY_DAYS`

#### Exiting the Application - `bye`

Ends the application and saves your existing task list.

## Save File

DonkeyChat saves your information in plaintext format in the root folder of your application.

Savefile Name: `donkey.txt`.

This savefile is loaded at the start of every session with DonkeyChat, and your tasks are saved to it after every
command.