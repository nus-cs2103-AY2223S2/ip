# User Guide

Introducing Duke! Tasks made easier.

![UI](Ui.jpg)

## Quick Start

## Features

## Usage

### Adding a todo task: `todo`

Adds a todo task to the task-list.

Format: `todo (DESCRIPTION)`

Examples:
* `todo Call mum`
* `todo Homework`

Expected outcome:

Todo task will be added to the task-list

```
Got it. I've added this task:
  [T][ ] Call mum
Now you have 1 task in the list.
```

### Adding a deadline task: `deadline`

Adds a deadline task to the task-list

Format: `deadline (DESCRIPTION) /by (DATE) (TIME)

```
* `DATE` should be in yyyy-MM-dd format.
* `TIME` should be in hh:mm format.
```

Examples:
* `deadline Assignment 1 /by 2022-03-14 23:58`

Expected outcome:

Deadline task will be added to the task-list

```
Got it. I've added this task:
  [D][ ] Assignment 1 (by: Mar 14, 2022 23:58)
Now you have 2 tasks in the list.
```

### Adding an event task: `event`

Adds a event task to the task-list

Format: `event (DESCRIPTION) /from (FROM) /to (TO)`

Examples:
* `event Party /from 7:30pm /to 9.30pm`
* `event Orientation camp /from Saturday /to Tuesday`

Expected outcome:

Event task will be added to the task-list

```
Got it. I've added this task:
  [E][ ] Party (from: 7:30pm to: 9.30pm)
Now you have 3 tasks in the list.
```

### Finding tasks: `find`

Find tasks from the tasklist

Format: `find (DESCRIPTION)`

Examples:
* `find math`

Expected outcome:

All matching tasks will be listed out


```
Here are some tasks found!
 [T][ ] Math homework
```

### Marking tasks: `mark`

Mark a task as done

Format: `mark (INDEX)`

```
* `INDEX` is the index of the task to mark. Must be greater than 1.
```
  
Examples:
* `mark 2`

Expected outcome:

The task with the specified index will be marked as done

```
Nice! I've marked this task as done
  [E][X] Party (from: 7:30pm to: 9.30pm)
```

### Unmarking tasks: `unmark`

Mark a task as undone

Format: `unmark (INDEX)`

```
* `INDEX` is the index of the task to unmark. Must be greater than 1.
```
  
Examples:
* `unmark 2`

Expected outcome:

The task with the specified index will be marked as undone

```
Nice! I've marked this task as undone
  [E][ ] Party (from: 7:30pm to: 9.30pm)
```

### Deleting tasks: `delete`

Delete a task

Format: `delete (INDEX)`

```
* `INDEX` is the index of the task to delete. Must be greater than 1.
```

Examples:
* `delete 2`

Expected outcome:

The task with the specified index will be deleted

```
Got it. Deleting this task:
  [E][ ] Party (from: 7:30pm to: 9.30pm)
Now you have 2 tasks in the list.
```

### List tasks: `list`

List out all the tasks

Format: `list`

Expected outcome:

All the tasks would be listed

```
Here are all the tasks in the list!
1. [T][ ] Call mum
2. [D][ ] Assignment 1 (by: Mar 14, 2022 23:58)
```

### Saving tasks: `save`

Save a task

Format: `save (SAVE_INDEX) (SAVE_MSG)`

```
* `SAVE_INDEX` can only be an integer from 1 to 3.
```

Examples:
* `save 1 John's Tasklist`

Expected outcome:

The tasklist is saved to the specified slot.

```
Saved to slot number 1!
```

### Loading tasks: `load`

Load a save file.

Format: `load (SAVE_INDEX)`

```
* `SAVE_INDEX` needs to refer to an available save
```

Examples:
* `load 1`

Expected outcome:

Loads the specified save file.

```
Loaded from slot number 1!
```

### Showing saves: `showSaves`

Show all the saves available.

Format: `showSaves`

Expected outcome:

Lists out all the saves available

```
Here are the current save files!
1. Tom's tasklist
2. John's tasklist
3. Tim's tasklist
```

### Exit application: `bye`

Exit the application

Format: `bye`

