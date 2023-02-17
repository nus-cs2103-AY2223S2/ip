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

### Unmark task `mark`

Unmarks a specific task.

### Explanation of commands `help`

Displays all commands explanation.

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
### `help` - help

Displays explanations about all commands.

Example of usage: 
`help`

Expected outcome:

```
Available commands:

-help: show the list of all commands.
 	   e.g. "help"

-bye: exit the application.
      e.g. "bye"

-list: list all the tasks that are stored.
       e.g. "list"

-todo: add a todo task with a description.
       e.g. "todo borrow book"

-deadline: add a deadline task with description and due date time (after "/by").
           e.g. "deadline watch lecture /by 2019-09-08 1900"

-event: add an event task with description, start date time (after "/from")
        and end date time (after "/to").
        e.g. "event Chinese New Year /from 2023-02-12 0000 /to 2023-02-18 0000"

-find: find any tasks contains the description, support partially search.
       e.g. "find book" "find bo"

-delete: delete the specific task based on the task number.
         e.g. "delete 2"

-mark: mark the specific task as completed based on the task number.
       e.g. "mark 3"

-unmark: unmark the specific task as not completed based on the taks number.
                 e.g. "unmark 5"

Note: 1. all the date time format is "yyyy-mm-dd HHmm".
     2. task number must be within the task list size.
     3. storage file at "./src/data/duke.txt"
```
### `bye` - bye

Exits the program.

Example of usage: 
`bye`

Expected outcome:

```
Bye. Hope to see you again soon!
```

