# User Guide
Duke is a desktop app for managing tasks, optimized for use via a Command Line Interface (CLI) while still having the benefits of a Graphical User Interface (GUI). Duke allows the tracking of various types of tasks including todo list, events and deadlines.

- [Quick start](#quick-start)
- [Features](#features)
- [Usage](#usage)
  - [Adding a todo: `todo`](#adding-a-todo--todo)
  - [Adding a deadline: `deadline`](#adding-a-deadline--deadline)
  - [Adding a event: `event`](#adding-an-event--event)
  - [Listing all tasks: `list`](#listing-all-tasks--list)
  - [Deleting a task: `delete`](#deleting-a-task--delete)
  - [Finding specific tasks: `find`](#finding-specific-tasks--find)
  - [Marking a task: `mark`](#marking-a-task--mark)
  - [Unmarking a task: `unmark`](#unmarking-a-task--unmark)
  - [Exiting the program: `bye`](#exiting-the-program--bye)
- [Command summary](#command-summary)


--------------------------------------------------------------------------------------------------------------------

## Quick start

1. Ensure you have Java `11` or above installed in your Computer.

1. Download the latest `duke.jar` file from [here](https://github.com/bofeng1999/ip/releases/tag/A-Release).

1. Locate the file and launch the application using the command `java -jar duke.jar`.

![Ui.png](Ui.png)

1. Type a command in the command box and press Enter to execute it.

1. Refer to the [Features](#features) below for details of each command.

## Features 

### Task management

Add tasks by indicating type of task to add or delete tasks by indicating index of task to delete.

### Track task status

Mark or unmark task to track completed and incomplete tasks.

### View tasks

Display all tasks in a list or filter tasks using a keyword.

### Detect duplicates

Throw an error by indicating task already exists.

## Usage
### Adding a task
You can add todo, event (occurs and ends on a specific date) or deadline(due by a specified date) as tasks.

#### Adding a todo: `todo`
Adds a todo.

#Format: `todo TASK_DESCRIPTION`

Examples: 
- `todo assignment 1`

Expected outcome:

Todo added to task list when valid description given.

Description of the outcome.

```
Got it. I've added this task:
[T][ ] TASK_DESCRIPTION
Now you have X task(s) in the list.
```

#### Adding a deadline: `deadline`
Adds a deadline.

Format: `deadline TASK_DESCRIPTION /by YYYY-MM-DD HH:mm`

Examples:

- `deadline return book /by 2022-12-20 16:00`

Expected outcome:

Deadline added to task list when valid description and date given.

```
Got it. I've added this task:
[D][ ] TASK_DESCRIPTION (by: MMM DD YYYY HH:mm)
Now you have X task(s) in the list.
```

#### Adding an event: `event`
Adds an event.

Format: `event TASK_DESCRIPTION /from YYYY-MM-DD HH:mm /to YYYY-MM-DD HH:mm`

Examples:

- `event NUS Open House /from 2022-12-22 16:00 /to 2022-12-29 16:00`

Expected outcome:

Event added to task list when valid description and date given.

```
Got it. I've added this task:
[E][ ] TASK_DESCRIPTION (from: MMM DD YYYY HH:mm) (to: MMM DD YYYY HH:mm)
Now you have X task(s) in the list.
```

### Listing all tasks: `list`
Displays a list of tasks added.

Format: `list`

Expected outcome:

```
Here are the tasks in your list:
1. ...
2. ...
```

### Deleting a task: `delete`
Deletes the task from task list.

Format: `delete INDEX`
- Deletes the task at the specified `INDEX`.
- `INDEX` refers to the index number shown after using `list`.

Examples:

- `delete 1` deletes the 1st task in `list` if it exists.

Expected outcome:

Deletes a task when valid index is given.

```
Noted, I've removed this task:
[X][ ] TASK_DESCRIPTION
Now you have X task(s) in the list.
```

### Finding specific tasks: `find`
Display tasks where the description matches given keywords.

Format: `find KEYWORD`
- Search is case-sensitive. e.g. `homework` will not match with `Homework`
- Partial keywords can be used.

Examples:
- `find a` returns a list of all tasks with description containing at least the keyword `a`

Expected outcome:

```
Here are the matching task in your list:
1. ...
2. ...
```

### Marking a task: `mark`
Marks a task as completed.

Format: `mark INDEX`
- Marks the task at the specified `INDEX` as completed.
- `INDEX` refers to the index number shown after using `list`.

Examples:
- `mark 1` marks the first task in `list` if it exists.

Expected outcome:

Marks a task as completed when valid index is given.

```
Nice! I've marked this task as done:
[X][X] TASK_DESCRIPTION
```

### Unmarking a task: `unmark`
Marks a task as incomplete.

Format: `unmark INDEX`
- Marks the task at the specified `INDEX` as incomplete.
- `INDEX` refers to the index number shown after using `list`.

Examples:
- `unmark 1` marks the first task in `list` as incomplete if it exists.

Expected outcome:

Marks a task as incomplete when valid index is given.

```
OK, I've marked this task as not done yet:
[X][ ] TASK_DESCRIPTION
```

### Exiting the program: `bye`
Exits the program.

Format: `bye`

Expected outcome:

Exits the program.

## Command summary

| **Action** | **Format, Examples**                                                                                                                                   |
|------------|--------------------------------------------------------------------------------------------------------------------------------------------------------|
| todo       | `todo TASK_DESCRIPTION` <br> e.g. `todo assignment 1`                                                                                                  |
| deadline   | `deadline TASK_DESCRIPTION /by YYYY-MM-DD HH:mm` <br> e.g. `deadline return book /by 2022-12-20 16:00`                                                 |
| event      | `event TASK_DESCRIPTION /from YYYY-MM-DD HH:mm /to YYYY-MM-DD HH:mm` <br> e.g. `event NUS Open House <br/>/from 2022-12-22 16:00 /to 2022-12-29 16:00` |
| list       | `list`                                                                                                                                                 |
| delete     | `delete INDEX` <br> e.g. `delete 1`                                                                                                                    |
| find       | `find KEYWORD` <br> e.g. `find a`                                                                                                                      |
| mark       | `mark INDEX` <br> e.g. `mark 1`                                                                                                                        |
| unmark     | `unmark INDEX` <br> e.g. `unmark 1`                                                                                                                    |
| bye        | `bye`                                                                                                                                                  |
