# User Guide

## Features 
Notes about the command format:
- Words in `UPPER_CASE` are the parameters to be supplied by the user.
e.g. in `todo DESCRIPTION`, `DESCRIPTION` is a parameter which can be used as `todo borrow book`
- Extraneous parameters for commands that do not take in parameters (such as `list` and `bye`) will be ignored.
e.g. if the command specifies `list 123`, it will be interpreted as `list`

## Usage

### `todo` - Creating a new Todo task.

Creates a new Todo task and adds it to the list of tasks.
Format: `todo DESCRIPTION`

Example of usage: 

`todo borrow book`

Expected outcome:

Shows task have been added and the current number of tasks in the list.

```
Got it. I've added this task:
[T][] borrow book
Now you have 1 tasks in the list.
```

### `deadline` - Creating a new Deadline task.

Creates a new Deadline task and adds it to the list of tasks.

Format: `deadline DESCRIPTION /by DATE`

Example of usage: 

`deadline return book /by 2019-10-15`

Expected outcome:

Shows task have been added and the current number of tasks in the list.

```
Got it. I've added this task:
[D][] return book (by: Oct 15 2019)
Now you have 1 tasks in the list.
```

### `event` - Creating a new Event task.

Creates a new Event task and adds it to the list of tasks.

Format: `event DESCRIPTION /from START TIME /to END TIME`

Example of usage: 

`event project meeting /from 2pm /to 4pm`

Expected outcome:

Shows task have been added and the current number of tasks in the list.

```
Got it. I've added this task:
[E][] project meeting (from: 2pm to: 4pm)
Now you have 1 tasks in the list.
```

### `mark` - Marking a task.

Marks a task as done.

Format: `mark NUMBER`

Example of usage: 

`mark 1`

Expected outcome:

Shows which task have been marked.

```
Nice! I've marked this task as done:
[T][X] borrow book
```

### `unmark` - Unmarking a task.

Unmarks a task as done.

Format: `unmark NUMBER`

Example of usage: 

`unmark 1`

Expected outcome:

Shows which task have been unmarked.

```
OK, I've marked this task as not done yet:
[T][] borrow book
```

### `list` - Listing all tasks.

List all the tasks.

Example of usage: 

`list`

Expected outcome:

Shows all the tasks in the list.

```
Here's the tasks in your list:
1.[T][] borrow book
2.[D][] return book (by: Oct 15 2019)
3.[E][] project meeting (from: 2pm to: 4pm)
```

### `find` - Finding a task.

Find tasks with matching keywords.

Format: `find KEYWORD`

Example of usage: 

`find book`

Expected outcome:

Shows all tasks with matching keywords.

```
Here are the matching tasks in your list:
1.[D][] return book (by: Oct 15 2019)
2.[E][] project meeting (from: 2pm to: 4pm)
```

### `delete` - Deleting an existing task.

Removes a task from the list.

Format: `delete NUMBER`

Example of usage: 

`delete 1`

Expected outcome:

Shows task have been removed and the remaining number of tasks.

```
Noted. I've removed this task:
[T][] borrow book
Now you have 2 tasks in the list.
```

### `update` - Updating a task.

Updates details of a task (e.g. end time).

Format: `update NUMBER /by DATE` or `update NUMBER /from START TIME /to END TIME`

Example of usage: 

`update 2 /by 2019-10-19` or `update 2 /from 4pm /to 6pm`

Expected outcome:

Shows task have been updated.

```
OK, I've updated this task:
[D][] return book (by: Oct 19 2019)
```

### `bye` - Exiting the program.

Exits the program.

Example of usage: 

`bye`
