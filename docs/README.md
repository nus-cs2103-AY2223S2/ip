# User Guide

Duke is a desktop app for managing tasks, tracking deadlines and documenting upcoming events. In a way,
Duke is your personal assistant that conveniently remembers and notes your tasks for you. 

<img src="Ui.png" width="600" />

## Quick Start

1. Ensure you have Java `11` or above installed in your Computer.
2. Download the latest `duke.jar` from [here](https://github.com/sarthak181/ip)
3. Copy the file to the folder you want to use as the _home folder_ for your Duke.
4. Open a command terminal, `cd` into the folder you put the jar file in, and use the `java -jar duke.jar` command to run the application.
5. Type the command in the command box and press Enter to execute it.
6. Enjoy 😄
## Features
<div markdown="block" class="alert alert-info">

**:information_source: Notes about the command format:**<br>

* Words in `UPPER_CASE` are the parameters to be supplied by the user.<br>
  e.g. in `todo CONTENT`, `CONTENT` is a parameter which can be used as `todo test`.

</div>

### Viewing Help : `help`

Command: `help`

Shows a message explaining the list of commands available to a user and how to use them.

### Adding a todo: `todo`

Command: `todo CONTENT`

Adds a todo task to the task list.

### Adding a deadline: `deadline`

Command: `deadline CONTENT /by BY`

Adds a deadline task to the task list. To keep track of tasks with deadlines.

### Adding an event: `event`

Command: `event CONTENT /from FROM /to TO`

Adds an event task to the task list. To keep track of events.

### Listing all tasks: `list`

Command: `list`

Shows a list of all tasks in the list.

### Marking a task as done: `mark`

Command: `mark INDEX`

Marks the task at the `INDEX` as done.

### Marking a task as not done: `unmark`

Command: `unmark INDEX`

Marks the task at the specified `INDEX` as not done.

### Deleting a task: `delete`

Command: `delete INDEX`

Deletes the task at the `INDEX`.

### Finding tasks by keyword: `find`

Command: `find KEYWORD`

Finds tasks in task list whose content contains the specified `KEYWORD`.

## Usage

### `help` - Viewing help

Displays help message to the user with list of available commands.

Example of usage:

`help`

Expected outcome:
Help message with list of available commands.

```agsl
Here are the commands to use:
todo <description>: adds a todo task
deadline <description> /by <by>: adds a deadline task
event <description> /from <from> /to <to>: adds an event task
mark <task number>: marks a task as done
unmark <task number>: marks a task as not done
delete <task number>: deletes a task
list: lists all tasks
find <keyword>: finds tasks with the keyword
bye: exits the program
```

### `list` - Listing all tasks

Displays all tasks in the task list.

Example of usage:

`list`

Expected outcome:
Enumerated list of all tasks in the task list.

```agsl
Here are your tasks, you legend!:
1. T[] Laundry
2. D[] Project Submission (by: 17 Feb 2023, 2359)
3. E[] Wedding (from: 14 Feb 2023 to: 17 Feb 2023)
```

### `todo`, `deadline`, `event` - Creates a task

Creates a task of the specified type and adds it to the task list.

Example of usage:

`event Wedding /from 2023-02-14 /to 2023-02-17`

Expected outcome:
Returns a message indicating that the task has been added to the task list together with the number of tasks in the task list.

```agsl
Got it, I've added this task: 
 E[] Wedding (from: 14 Feb 2023 to: 17 Feb 2023) 
You have 3 tasks in your list.
```

### `mark/unmark` - Marks a task as done/not done

Marks the task at the specified index as done/not done.

Example of usage:

`mark 1`

Expected outcome:
Returns a message indicating that the task has been marked as completed/incomplete.

```agsl
Nice, I've marked this task as done:
 1. T[X] Laundry

```

### `delete` - Deletes a task

Deletes the task at the specified index.

Example of usage:

`delete 1`

Expected outcome:

Returns a message indicating that the task has been deleted from the task list together with the number of tasks in the task list.

```agsl
Noted. I've removed this task:
 1. T[X] Laundry
Now you have 2 tasks in the list. 
 
```

### `find` - Finds tasks by keyword

Finds tasks in task list whose content contains the specified keyword.

Example of usage:

`find Submission`

Expected outcome:
Returns a message indicating the tasks that contain the keyword.

```agsl
Here are the matching tasks in your list:
1) [D][] Project submission. 
```

### `bye` - Exits the program

Exits the program.

Example of usage:

`bye`

Expected outcome:

Terminates the program.

