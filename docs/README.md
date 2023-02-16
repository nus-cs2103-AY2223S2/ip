# User Guide

## Features 

### Add task: todo

Adds a task without any deadline.

### Add deadline task: deadline

Adds a task with deadline.

### Add event task: event

Adds an event occuring within a specific timeline.

### Check task: mark

Checks off task as completed.

### Uncheck task: unmark

Unchecks task as incomplete.

### Delete task: delete

Deletes task.

### View all tasks: list

Lists all completed and incompleted tasks.

## Usage

#### todo

Adds a task into task list and outputs success message.

Example of usage: 

`todo read a book`

Expected outcome:

Outputs success message.

```
____________________________________________________________
  Got it. I've added this task:
    [T][ ] read a book
  Now you have 1 task in the list.
____________________________________________________________
```

#### deadline

Adds a task with deadline into task list and outputs success message.

Example of usage: 

`deadline return book /by 2050-01-01`

Expected outcome:

Outputs success message.

```
____________________________________________________________
  Got it. I've added this task:
    [D][ ] return book (by: Jan 1 2050)
  Now you have 2 tasks in the list.
____________________________________________________________
```


### `Keyword` - Describe action

Describe the action and its outcome.

Example of usage: 

`keyword (optional arguments)`

Expected outcome:

Description of the outcome.

```
expected output
```
