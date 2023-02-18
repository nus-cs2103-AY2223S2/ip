# User Guide

## Features 

### Logs tasks

Can add new tasks and save them for later loading.

### Mark Tasks

Mark done tasks, which can also be removed.

### Find Tasks

Find certain tasks based on given keywords.

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
```

### `mark` - Add todo task

Provide index of task to mark as done.

Example of usage:

`mark 1`

Expected outcome:

Marks the task at given index.

```
You finally did it huh:
[T][X] run
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