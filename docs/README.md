# User Guide

Duke is a simple task/todo list app.

## Features

### Exit app: `quit` / `q` / `exit` / `bye`

Exits the app.

> Example usage: `quit`

### List tasks: `list`

Lists all your tasks.

> Example usage: `list`

### Add todo: `todo DESCRIPTION`

A todo is a task that has a description, but no start/end date.

> Example usage: `todo watch demon slayer`

### Add deadline: `deadline DESCRIPTION /by YYYY-MM-DD`

A deadline is a task with a end date.

> Example usage: `deadline Finish ip /by 2023-02-17`

### Add event: `event DESCRIPTION /from YYYY-MM-DD /to YYYY-MM-DD`

A event is a task with a end date.

> Example usage: `event Free food /to 2023-02-15 /from 2023-02-14`

### Mark task as done: `mark INDEX`

Marks a task as done by index. Do `list` to get the index.

> Example usage: `mark 1`

### Unmark task as done: `unmark INDEX`

Marks a task as not done by index. Do `list` to get the index.

> Example usage: `unmark 1`

### Delete task: `delete INDEX`

Deletes a task by index. Do `list` to get the index.

> Example usage: `delete 1`

### Find task(s): `find KEYWORD`

Filters the tasks by keyword(s).

> Example usage: `find chio bu`

### Sort tasks: `sort`

Sorts the tasks alphabetically by description.

> Example usage: `sort`

## Command summary

| Feature               | Command                                             |
| --------------------- | --------------------------------------------------- |
| Exit                  | `quit`                                              |
| Add todo              | `todo DESCRIPTION`                                  |
| Add deadline          | `todo DESCRIPTION`                                  |
| Add event             | `deadline DESCRIPTION /by YYYY-MM-DD`               |
| Add event             | `event DESCRIPTION /from YYYY-MM-DD /to YYYY-MM-DD` |
| Mark task as done     | `mark INDEX`                                        |
| Mark task as not done | `unmark INDEX`                                      |
| Delete task           | `delete INDEX`                                      |
| Find task(s)          | `find KEYWORD`                                      |
| Sort tasks            | `sort`                                              |
