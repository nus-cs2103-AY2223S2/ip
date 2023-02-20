# User Guide

## Features 

### Add Tasks

Add `Todos`, `Events`, and `Deadlines` to the `Task` list.

### Mark tasks as done / undone

Mark tasks on the list as **done / undone**.

### Delete tasks

Delete tasks when they are no longer relevant.

### Find tasks

Find tasks that contain a certain keyword or key phrase.

### Store and List tasks

List all tasks currently stored in the task list, and show their completion status.

### Check duplicate tasks

Alert users when they add duplicate tasks and list all duplicates.


## Usage

### `todo` - Add todos

Add a `todo` task to the task list.

Example of usage: 

`todo borrow book`

Expected outcome:

The todo item of borrowing book gets added to the task list, initialized with a `T` badge and an unfinished status.

```
Got it. I've added this task:
	[T][ ] borrow book
Now you have 1 task in the list.
```

### `event` - Add events

Add an `event` with starting and finishing timings to the task list.

Example of usage:

`event attend talk /from 2023-02-23 18:00 /to 2023-02-23 20:00`

Expected outcome:

The event of attending talk is added to the list, initialized with a `E` badge and an unfinished status.
The date format also gets transformed to be more user-friendly.

```
Got it. I've added this task:
	[E][ ] attend talk (from: Aug 30 2020, 06:00 PM to Aug 30 2020, 08:00 PM)
Now you have 2 tasks in the list.
```

### `deadline` - Add deadlines

Add an `deadline` with end timing to the task list.

Example of usage:

`deadline submit essay /by 2023-02-23 18:00`

Expected outcome:

The deadline gets added, initialized with a `D` badge and an unfinished status.
The date format also gets transformed to be more user-friendly.

```
Got it. I've added this task:
    [D][ ] submit essay (by: Aug 30 2020, 06:00 PM)
Now you have 3 tasks in the list.
```

### `list` - List all saved tasks

List all tasks currently added to the list.

Example of usage:

`list`

Expected outcome:

All tasks inclusive of todos, deadlines, and events as well as their completion status get listed.

```
"Valid request:D. I've got this for you:

1.[T][ ] read book
2.[D][ ] return book (by: June 6th)
5.[E][ ] project meeting (from: Mon 2pm to 4pm)
```

### `mark` - Mark task as done

Mark a task as done status.

Example of usage:

`mark 1`

Expected outcome:

The first task saved on the list is marked as done.

```
Nice! I've marked this task as done:
	[T][X] borrow book
```

### `unmark` - Mark task as undone

Mark a task as undone status. The opposite of `mark` operation.

Example of usage:

`unmark 2`

Expected outcome:

The second task saved on the list is marked as undone.

```
OK, I've marked this task as not done yet:
	[E][ ] attend talk (from: Aug 30 2020, 06:00 PM to Aug 30 2020, 08:00 PM)
```

### `delete` - Delete a task

Delete a task when it is no longer relevant.

Example of usage:

`delete 1`

Expected outcome:

The first task of borrowing book gets deleted from the list.

```
Noted. I've removed this task.
    [T][X] borrow book
Now you have 0 tasks in the list.
```

### `find` - Search a task

Search tasks that contain certain keywords or key phrases.

Example of usage:

`find talk`

Expected outcome:

All tasks that have descriptions that contain `talk` will be matched and returned.

```
"Valid request:D. I've got this for you:

[E][ ] attend talk (from: Aug 30 2020, 06:00 PM to Aug 30 2020, 08:00 PM)
```