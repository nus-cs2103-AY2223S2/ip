# User Guide - Better Call Saul!

## Features 

### `list`

Lists all task in the todo list along with their information.

### `todo` Task

Adds a Todo type task with text descriptor only.

### `deadline` Task

Adds a Deadline type task with text descriptor and `dueDate` date and time.

### `event` Task

Adds an Event type task with text desciptor and start and end date and time.

### `mark X`

Marks a task as completed `X: Index of task to be marked`

### `unmark X`

Marks a task as not completed `X: Index of task to be unmarked`

### `delete X`

Deletes selected task `X: Index of task to be deleted`

### `find W`

Searches for a task whose text descriptor contains the keyword `W: Keyword`


## Usage

### `list` - Lists all tasks

Displays all the tasks stored with their Index number, Text Descriptor and whether they have been completed.

Example of usage: 

`list`

Expected outcome:
```
Here are the tasks in your list
1.[T][ ] clean room
2.[D][X] submit assignment (by: Jan 1 2000 12:11)
3.[E][ ] party (from: Jan 1 2020 12:12 to: Jan 1 2021 09:21)
```
All the tasks in the list are displayed. [T] represents a todo task, [D] represents a deadline task and [E] represents an event class. [X] means the task is complete and [ ] means the task is not completed.


### `todo [DESCRIPTOR]` - Add todo task

Creates and adds a todo task with [DESCRIPTOR] as the text description to the task list.

Example of usage: 

`todo clean room`

Expected outcome:
```
Ok boss. Added task:
[T][ ] clean room
Now you have 1 in the list.
```
The todo task with the description `clean room` has been created and added to the list. Now there is a total of 1 task in the list.

### `deadline [DESCRIPTOR] /by [DUEDATE]` - Add deadline task

Creates and adds a deadline task with [DESCRIPTOR] as the text description and [DUEDATE] as the due date to the task list.

Example of usage: 

`deadline submit assignment /by 2000-01-01 00:11`

Expected outcome:
```
Ok boss. Added task:
[D][ ] submit assignment (by: Jan 1 2000 12:11)
Now you have 2 in the list.
```
The deadline task with the description `submit assignment` and due date `2000-01-01 00:11` has been created and added to the list. Now there are a total of 2 tasks in the list.

### `event [DESCRIPTOR] /from [START] /to [END]` - Add event task

Creates and adds an event task with [DESCRIPTOR] as the text description, [START] as the start date and [END] as the end date to the task list.

Example of usage: 

`event party /from 2020-01-01 12:12 /to 2021-01-01 21:21`

Expected outcome:
```
Ok boss. Added task:
[E][ ] party (from: Jan 1 2020 12:12 to: Jan 1 2021 09:21)
Now you have 3 in the list.
```
The event task with the description `party`, `2020-01-01 12:12` as the start date and `2021-01-01 21:21` as the end date has been created and added to the list. Now there are a total of 3 tasks in the list.

### `mark [INDEX]` - Mark task as complete

Marks the task with the index number of [INDEX] as completed.

Example of usage: 

`mark 1`

Expected outcome:
```
Ok boss! Marked this task as done: 
[T][X] clean room
```
The task with index 1 `clean room` has been marked as completed as represented by `[X]`.

### `unmark [INDEX]` - Mark task as not complete

Marks the task with the index number of [INDEX] as not completed.

Example of usage: 

`unmark 1`

Expected outcome:
```
Ok boss! Marked this task as not done yet: 
[T][ ] clean room
```
The task with index 1 `clean room` has been marked as not completed as represented by `[ ]`.

### `delete [INDEX]` - Delete a task

Deletes the task with the index number of [INDEX] from the task list.

Example of usage: 

`delete 1`

Expected outcome:
```
Ok boss! task removed: 
[T][ ] clean room
Now you have 2 in the list.
```
The task with index 1 `clean room` has been deleted from the list. Now there are 2 tasks left in the list.

### `find [KEYWORD]` - Finds tasks

Searches the task lists for tasks whose text descriptor contains [KEYWORD] and returns all tasks found in as a list.

Example of usage: 

`find submit`

Expected outcome:
```
Here are the matching tasks from your list:
1.[D][ ] submit assignment (by: Jan 1 2000 12:11)
```
There is 1 task found that contains the keyword `submit` which is `submit assignment`. That task has been returned as a list containing 1 task.
