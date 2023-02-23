# User Guide

## Features 

### Add tasks

The ability to add various different tasks
- Todo tasks
- Deadline tasks
- Event tasks
- Duration tasks

### Mark them as done or undone

Keep track of whether you have completed your tasks by marking them

## Usage

### `list` - Lists all tasks currently stored

Example of list:

```
Here are the tasks you asked for!
1: [T] [ ] he
2: [Du] [ ] hello ( 5 hours 0 mins )
You now have 2 items in your list.
```

### `todo` - A standard todo task

Example of usage: 

`todo Assignment 1`

### `deadline` - A task with an assigned deadline

`/by`: used to specify date that task should be done by

Date in `yyyy-mm-dd` format

Example of usage:

`deadline Submit critical reflection /by 2024-01-01`

### `event` - A task with a start and end date

`/from` and `/to` used to specify start and end dates

Dates are in `yyyy-mm-dd` format

Example of usage:

`event Sports carnival /from 2023-03-03 /to 2023-03-10`

### `duration` - A task with a given duration to complete

`/for` used to specify the time period given for task

Time is in `hh:mm` format

Example of usage:

`duration Mock practical exam /for 2:00`

### `mark` - To check a task

Example:

`mark 2` - checks the second item on the list as done

### `unmark` - Unchecks a task

`unmark 1` - unchecks the first item on the list

### `delete` - Deletes a task from the list

`delete 3` - Permanently deletes the third item from the list

### `save` - Saves the list to hard drive

### `find` - Filters tasks by a search

Example:

```
1: [T] [ ] hehe
2: [Du] [ ] hello ( 5 hours 0 mins )
3: [D] [X] Return home (by: 09 Sep 2023)
```

`find he` gives

```
1: [T] [ ] hehe
2: [Du] [ ] hello ( 5 hours 0 mins )
```

