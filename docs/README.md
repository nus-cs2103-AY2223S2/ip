# User Guide

Duke chatbot is a GUI based application with basic task management functions.

## Features 

### List all tasks `list`

Lists all tasks are stored.

### Add todo task `todo`

Adds a todo task with a description.

### Add deadline task `deadline`

Adds a deadline task with a description and deadline datetime.

### Add event task `event`

Adds an event task with a description, start datetime and end datetime.

### Delete a task `delete`

Deletes a specific task.

### Find tasks `find`

Finds all tasks that contain the user input.

### Mark task `mark`

Marks a specific task.

### Unmark task `unmark`

Unmarks a specific task.

### Exit program `bye`

Exit the program.

## Usage

### `list` - list

Lists all tasks are stored.

Example of usage: 
`list`

Expected outcome:

```
Current tasks are:
1.[T][] Read Operating System Book
2.[D][] CS2109S Quiz (by:Feb 17 2023 23:59)
```
### `todo` - todo TaskDescription

Adds a todo task.

Example of usage: 
`todo Buy Book`

Expected outcome:

```
Got it, I've added this task:
	[T][] Buy Book
Now you have 1 tasks in the list
```
### `deadline` - deadline TaskDescription /by yyyy-mm-dd HHmm

Adds a deadline task.

Example of usage: 
`deadline CS2109S Quiz /by 2023-02-17 2359`

Expected outcome:

```
Got it. I've added this task:
	[D][] CS2109S Quiz (by:Feb 17 2023 23:59)
Now you have 2 tasks in the list
```
### `event` - event TaskDescription /from yyyy-mm-dd HHmm /to yyyy-mm-dd HHmm

Adds an event task.

Example of usage: 
`event Tennis Party /from 2023-02-17 0800 /to 2023-02-18 1800`

Expected outcome:

```
Got it. I've added this task:
	[E][] Tennis Party (from:Feb 17 2023 08:00 to:Feb 18 2023 18:00)
Now you have 2 tasks in the list
```
### `delete` - delete TaskIndex

Deletes a specific task.

Example of usage: 
`delete 1`

Expected outcome:

```
Noted, I've removed this task:
	[T][] Buy Book
Now you have 1 tasks in the list
```
### `find` - delete TaskDescription

Finds all tasks that contain task description.

Example of usage: 
`find Book`

Expected outcome:

```
1.[T][] Read Operating System Book
2.[T][] Buy Book
```
### `mark` - mark TaskIndex

Marks a specific task.

Example of usage: 
`mark 1`

Expected outcome:

```
Nice! I've marked this task as done:
	[T][X] Read Operating System Book
```
### `unmark` - unmark TaskIndex

Unmarks a specific task.

Example of usage: 
`unmark 1`

Expected outcome:

```
OK, I've marked this task as not done yet:
	[T][] Read Operating System Book
```
### `bye` - bye

Exits the program.

Example of usage: 
`bye`

Expected outcome:

```
Bye. Hope to see you again soon!
```

