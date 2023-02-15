CatBot (CB) is a desktop app for keeping track of various tasks, supporting those with deadlines as well as time windows.

# Quick Start

1. Ensure you have Java `11` or above installed on your Computer.

2. Download the latest `Duke.jar` from here.

3. Copy the file to the folder you want to use as the _home folder_ for your CatBot.

4. Open a command terminal, `cd` into the folder you put the jar file in, and use the `java -jar Duke.jar` command to run the application.
A GUI similar to the below should appear in a few seconds. Now you can start using CatBot!
![GUI](https://creationsv2.github.io/ip/Ui.png)

# User Guide

## Features 

### Keep track of various tasks

Tasks can have a deadline (done by when) or be an event (time period start and end)

### Mark and unmark tasks

Able to mark and unmark tasks as done or undone

### Update tasks

Supports easy update of tasks without needing for a re-entry

## Usage

### `todo TASK_DESC` - Adds a task

This adds a task with no timeframe specified.
Replace `TASK_DESC` with the description of the task.
`TASK_DESC` cannot be empty.

Example of usage: 

`todo Pet cat`

Expected outcome:

The task will be added.

```
Got it. I've added this task:
	[T][ ] Pet cat
Now you have 1 tasks in the list.
```

### `deadline TASK_DESC /by END_DATETIME` - Adds a task with a deadline

This adds a task with no timeframe specified.
Replace `TASK_DESC` with the description of the task.
`TASK_DESC` cannot be empty.

Replace `END_DATETIME` with the date and time of the deadline.
`END_DATETIME` cannot be empty.
`END_DATETIME` has to be in the format `YYYY-MM-DD HHMM`.

Example of usage: 

`deadline Buy Catfood /by 2023-02-01 1400`

Expected outcome:

The task will be added.

```
Got it. I've added this task:
	[D][ ] Buy Catfood (By Feb 1 2023 2:00 PM)
Now you have 1 tasks in the list.
```

### `event TASK_DESC /from START_DATETIME /to END_DATETIME` - Adds an event with a timeframe

This adds a task with no timeframe specified.
Replace `TASK_DESC` with the description of the task.
`TASK_DESC` cannot be empty.

Replace `START_DATETIME` with the date and time of the deadline.
`START_DATETIME` cannot be empty.
`START_DATETIME` has to be in the format `YYYY-MM-DD HHMM`.

Replace `END_DATETIME` with the date and time of the deadline.
`END_DATETIME` cannot be empty.
`END_DATETIME` has to be in the format `YYYY-MM-DD HHMM`.

Example of usage: 

`event Walk cat /from 2023-02-01 1400 /to 2023-02-01 1700`

Expected outcome:

The task will be added.

```
Got it. I've added this task:
	[E][ ] Walk cat (from: Feb 1 2023 2:00 PM to: Feb 1 2023 5:00 PM)
Now you have 1 tasks in the list.
```

### `list` - Lists all tasks

This list all tasks regardless of type in order of addition.

Example of usage: 

`list`

Expected outcome:

All tasks will be listed.

```
Here are the tasks in your list:
1. [T][ ] Pet cat
2. [D][ ] Buy Catfood (By Feb 1 2023 2:00 PM)
3. [E][ ] Walk cat (From: Feb 1 2023 2:00 PM to: Feb 1 2023 5:00 PM)
```

### `delete TASK_ID` - Delete a task

Deletes a task specified by `TASK_ID` as done.
`TASK_ID` can be viewed using the `list` command above.

Example of usage: 

`delete 1`

Expected outcome:

Task with id `TASK_ID` will be deleted.

```
Meow-ted. I've removed this task:
	[T][X] Pet cat
Now you have 0 tasks in the list.
```

### `mark TASK_ID` - Marks a task as done

Marks a task specified by `TASK_ID` as done.
`TASK_ID` can be viewed using the `list` command above.

Example of usage: 

`mark 1`

Expected outcome:

Task with id `TASK_ID` will be marked as done.

```
Nice! I've marked this task as done:
	[T][X] Pet cat
```

### `unmark TASK_ID` - Marks a task as undone

Marks a task specified by `TASK_ID` as undone.
`TASK_ID` can be viewed using the `list` command above.

Example of usage: 

`unmark 1`

Expected outcome:

Task with id `TASK_ID` will be marked as undone.

```
Meow-k! I've marked this task as not done yet:
	[T][ ] Pet cat
```

### `update TASK_ID TASK_DESCRIPTION` - Updates the description of a task

Updates the description of a task specified by `TASK_ID` to the new description specified by `TASK_DESCRIPTION`.
`TASK_ID` can be viewed using the `list` command above.
`TASK_DESCRIPTION` cannot be empty.

Example of usage: 

`update 1 Pet dog`

Expected outcome:

Task with id TASK_ID will have description changed to `TASK_DESCRIPTION`.

```
Meow-k. I've updated the description of this task:
	[T][ ] Pet dog
```

## FAQ

**Q:** How do I transfer my data to another Computer?
**A:** Install the app in the other computer and overwrite the empty `list.txt` file it creates in the `data` folder with the corresponding file in your previous CatBot home folder.

## Command Summary

Action | Format, Examples
--------|------------------
**Add task** | `todo TASK_DESC` <br> e.g., `todo Pet cat`
**Add deadline** | `deadline TASK_DESC /by END_DATETIME` <br> e.g., `deadline Buy Catfood /by 2023-02-01 1400`
**Add event** | `event TASK_DESC /from START_DATETIME /to END_DATETIME` <br> e.g., `event Walk cat /from 2023-02-01 1400 /to 2023-02-01 1700`
**List** | `list`
**Mark as Done** | `mark TASK_ID` <br> e.g., `mark 1`
**Mark as Undone** | `unmark TASK_ID` <br> e.g., `unmark 1`
**Delete task** | `delete TASK_ID` <br> e.g., `delete 1`
**Update task** | `update TASK_ID TASK_DESCRIPTION` <br> e.g., `update 1 Pet dog`