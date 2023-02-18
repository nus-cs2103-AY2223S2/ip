# User Guide

Welcome to Duke! Duke manages your tasks and frees your mind of having to remember things you need to do. 

![UI](Ui.jpg)

## Quick Start

## Features

### Adding a todo task: `todo`

Adds a todo task to the task-list.

Format: `todo (DESCRIPTION)`

Examples:
* `todo Call mum`
* `todo Homework`

### Adding a deadline task: `deadline`

Adds a deadline task to the task-list

Format: `deadline (DESCRIPTION) /by (DATE) (TIME)

```
* `DATE` should be in yyyy-MM-dd format.
* `TIME` should be in hh:mm format.
```

Examples:
* `deadline Assignment 1 /by 2022-03-14 23:58`

### Adding an event task: `event`

Adds a event task to the task-list

Format: `event (DESCRIPTION) /from (FROM) /to (TO)`

Examples:
* `event Party /from 7:30pm /to 9.30pm`
* `event Orientation camp /from Saturday /to Tuesday`

### Finding tasks: `find`

Find tasks from the tasklist

Format: `find (DESCRIPTION)`

Examples:
* `find math`

### Marking tasks: `mark`

Mark a task as done

Format: `mark (INDEX)`

```
* `INDEX` is the index of the task to mark. Must be greater than 1.
```
  
Examples:
* `mark 2`

### Unmarking tasks: `unmark`

Mark a task as undone

Format: `unmark (INDEX)`

```
* `INDEX` is the index of the task to unmark. Must be greater than 1.
```
  
Examples:
* `unmark 2`

### Deleting tasks: `delete`

Delete a task

Format: `delete (INDEX)`

```
* `INDEX` is the index of the task to delete. Must be greater than 1.
```

Examples:
* `delete 2`

### List tasks: `list`

List out all the tasks

Format: `list`

### Saving tasks: `save`

Save a task

Format: `save (SAVE_INDEX)`

```
* `SAVE_INDEX` can only be an integer from 1 to 3.
```

Examples:
* `save 1`

### Loading tasks: `load`

Load a save file.

Format: `load (SAVE_INDEX)`

```
* `SAVE_INDEX` needs to refer to an available save
```

Examples:
* `load 1`

### Showing saves: `showSaves`

Show all the saves available.

Format: `showSaves`

### Exit application: `bye`

Exit the application

Format: `bye`

