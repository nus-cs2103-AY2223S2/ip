# User Guide

## Features 

### Logs tasks

Can add or delete tasks and save them for later loading.

### Mark and Unmark Tasks

Mark done tasks and unmark tasks not done.

### Find Tasks

Find certain tasks based on given keywords.

### List Tasks

Look at entire list of tasks.

## Usage

### `todo` - Add todo task

Provide task name as input to create a todo task.

Example of usage: 

`todo run`

Expected outcome:

Creates a new todo task

```
I'll help add this for now:
[T][ ] run
Take your 1 task(s)!
```

### `deadline` - Add deadline task

Provide task name as input to create a deadline task.

Example of usage:

`deadline run /by 2023-12-12`

Expected outcome:

Creates a new deadline task

```
I'll help add this for now:
[D][ ] run (by: Dec 12 2023)
Take your 1 task(s)!
```

### `event` - Add event task

Provide task name as input to create an event task.

Example of usage:

`event run /from 2023-12-11 /to 2023-12-12`

Expected outcome:

Creates a new todo task

```
I'll help add this for now:
[E][ ] run (from: 2023-12-11 to: 2023-12-12)
Take your 1 task(s)!
```

### `delete` - Delete task

Provide index of task to delete.

Example of usage:

`delete 1`

Expected outcome:

Deletes the task at given index.

```
Less stuff for me to keep note hehe, removed:
[T][ ] run
Take your 0 task(s)!
```

### `mark` - Marks task

Provide index of task to mark as done.

Example of usage:

`mark 1`

Expected outcome:

Marks the task at given index.

```
You finally did it huh:
[T][X] run
```

### `unmark` - Unmarks task

Provide index of task to mark as undone.

Example of usage:

`unmark 1`

Expected outcome:

Unmarks the task at given index.

```
How did you end up undoing this:
[T][ ] run
```

### `find` - Find specific tasks

Provide keyword as input to find tasks.

Example of usage:

`find run`

Expected outcome:

Find all tasks with the keyword run.

```
Here you go, now stop wasting my time:
1. [T][X] run
```

### `list` - Lists all tasks

Displays entire list of tasks.

Example of usage:

`list`

Expected outcome:

Lists all tasks.

```
1. [T][ ] run
2. [D][ ] swim (by: Dec 12 2023)
3. [E][X] fly (from: 2023-12-11 to: 2023-12-12)
```

### `bye` - Ends the program

Ends the Reborn program by saving tasks.

Example of usage:

`bye`

Expected outcome:

Displays goodbye message and saves tasks.

```
Tasks saved. Now get out of here!
```
