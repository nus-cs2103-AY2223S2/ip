# User Guide
This user guide has been adapted from [SE-EDU's AddressBook-Level3]

Duke is a desktop app for managing tasks, deadlines and events, with an input format suited for individuals who
are familiar with text-based UIs.

<img src="Ui.png" width="600" />

- [Quick Start](#quick-start)
- [Features](#features)
    - [Adding a todo task: `todo`](#adding-a-todo-task--todo)
    - [Adding a deadline task: `deadline`](#adding-a-deadline-task--deadline)
    - [Adding an event task: `event`](#adding-an-event-task--event)
    - [Listing all tasks: `list`](#listing-all-tasks--list)
    - [Marking a task as done: `mark`](#marking-a-task-as-done--mark)
    - [Marking a task as not done: `unmark`](#marking-a-task-as-not-done--unmark)
    - [Deleting a task: `delete`](#deleting-a-task--delete)
    - [Finding tasks by keyword: `find`](#finding-tasks-by-keyword--find)
- [Usage](#usage)

## Quick Start

1. Ensure you have Java `11` or above installed in your Computer.
2. Download the latest `duke.jar` from [here](https://github.com/owen-yap/ip/releases/tag/A-Release)
3. Copy the file to the folder you want to use as the _home folder_ for Duke.
4. Open a command terminal, `cd` into the folder you put the jar file in, and use the `java -jar duke.jar` command to run the application.
5. Type the command in the command box and press Enter to execute it.
6. Refer to the [Features](#features) below for details of each command.
## Features

### Adding a todo task: `todo`

Command: `todo DESCRIPTION`

Adds a todo task to the task list with the given description.

E.g.  `todo read book`

### Adding a deadline task: `deadline`

Command: `deadline DESCRIPTION /by DATE`

Adds a deadline task to the task list. Date has to be given in the format M/d/yyyy HHmm.

E.g.  `deadline finish ip /by 02/17/2023 2359`

### Adding an event task: `event`

Command: `event DESCRIPTION /from DATE /to DATE`

Adds an event task to the task list. Date has to be given in the format M/d/yyyy HHmm.

E.g.  `event tp meeting /from 02/18/2023 1400 /to 02/18/2023 1500`

### Listing all tasks: `list`

Command: `list`

Lists all tasks in your task list.

### Marking a task as done: `mark`

Command: `mark INDEX`

Marks the task at the specified index as done.

E.g.  `mark 2`

### Marking a task as not done: `unmark`

Command: `unmark INDEX`

Marks the task at the specified index as not done.

E.g.  `unmark 2`

### Deleting a task: `delete`

Command: `delete INDEX`

Deletes the task at the specified index.

E.g.  `delete 2`

### Finding tasks by keyword: `find`

Command: `find QUERY`

Find tasks with the description containing the query specified.
The query does not have to contain the full description (can be partial).

E.g.  `find meeting`

## Usage

### `list` - Listing all tasks

Displays all tasks in the task list.

Example of usage:

`list`

Expected outcome:

```
1. [T][] return book 
2. [D][X] iP Project (by: Feb 17 2023 11:59PM)
3. [E][] tP meeting (from: Feb 18 2023 02:00PM to: Feb 18 2023 03:00PM)
```

### `todo`, `deadline`, `event` - Creates a task

Creates a task of the specified type and adds it to the task list.

Example of usage:

`todo return book`

`deadline iP Project /by 02/17/2023 2359`

`event tP meeting /from 02/18/2023 1400 /to 02/18/2023 1500`

Expected outcome:

```
Alright, I've added this task:
[D][X] iP Project (by: Feb 17 2023 11:59PM)
You have 2 tasks in your list.
```

### `mark`, `unmark` - Marks a task as done / not done

Marks the task at the specified index as done / not done.

Example of usage:

`mark 1`

Expected outcome:

```
Nice! I've marked this task as done:
[T][X] return book
```

### `delete` - Deletes a task

Deletes the task at the specified index.

Example of usage:

`delete 1`

Expected outcome:

```
Noted. I've removed this task:
[E][] tP meeting (from: Feb 18 2023 02:00PM to: Feb 18 2023 03:00PM)
```

### `find` - Finds tasks by keyword

Find tasks in task list whose content contains the specified keyword even if the query is only partial.

Example of usage:

`find project`

Expected outcome:

```
Here are the matching tasks in your list:
1. [D][X] iP Project (by: Feb 17 2023 11:59PM)
```
