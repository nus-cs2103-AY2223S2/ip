# User Guide

## Features

### Feature - Adding a task

Types of tasks that can be added:
* Todo
* Event
* Deadline

### Feature - Deleting a task

Delete tasks that are unnecessary.

### Feature - Mark and unmark a task

Mark tasks when you're done, or unmark it when you're not.

### Feature - List all tasks

Show all existing tasks.

### Feature - List all tasks that occur during a given date

Show events that occur on a given date, or deadlines that end on a given date.

### Feature - Find tasks containing a word

Show all tasks that contain a given word.

### Feature - Update an existing task

Update a task with new data.

### Feature - Exit the application

Shuts down the application.

## Usage

### `todo` - Add a todo task

Adds a todo task to the list

Example of usage:

`todo task`

Expected outcome:

A todo task is added and the current task list is displayed:

```
Got it. I've added this task:
[T][ ] task
Now you have 1 task in the list.
1. [T][ ] task
```

### `deadline` - Add a deadline task

Adds a deadline task to the list

Example of usage:

`deadline task /by 01/12/2000 1900`

Expected outcome:

A deadline task is added and the current task list is displayed:

```
Got it. I've added this task:
[D][ ] task (by: Dec 1 2000 19:00)
Now you have 1 task in the list.
1. [D][ ] task (by: Dec 1 2000 19:00)
```

### `event` - Add an event task

Adds an event task to the list

Example of usage:

`event task /from 12/01/2000 1900 /to 15/01/2023 2200`

Expected outcome:

An event task is added and the current task list is displayed:

```
Got it. I've added this task:
[E][ ] task (from: Jan 12 2000 19:00 to: Jan 15 2023 22:00)
Now you have 1 task in the list.
1. [E][ ] task (from: Jan 12 2000 19:00 to: Jan 15 2023 22:00)
```

### `delete` - Delete a task

Deletes the task at a given index.

Example of usage:

`delete 1`

Expected outcome:

The task at the corresponding index is deleted and the current task list is displayed.

```
Noted. I've removed this task:
[T][ ] task
Now you have 0 task in the list.
```

### `mark` - Mark a task as done

Marks the task at a given index as done.

Example of usage:

`mark 1`

Expected outcome:

The task at the corresponding index will be marked as done and the current task list is displayed:

```
Nice! I've marked this task as done:
[T][X] task
1. [T][X] task
```

### `unmark` - Mark a task as not done

Marks the task at a given index as not done.

Example of usage:

`unmark 1`

Expected outcome:

The task at the corresponding index will be marked as not done and the current task list is displayed:

```
OK, I've marked this task as not done yet:
[T][ ] task
1. [T][ ] task
```

### `list` - List all tasks

Displays all existing tasks.

Example of usage:

`list`

Expected outcome:

All existing tasks are displayed:

```
Here are your tasks!
1. [T][ ] task
2. [D][ ] task (by: Dec 1 2000 19:00)
3. [E][ ] task (from: Jan 12 2000 19:00 to: Jan 15 2023 22:00)
```

### `occurs` - List all tasks that occur during a given date

Displays all tasks that occur on a given date.

Example of usage:

`occurs 01/12/2000 1900`

Expected outcome:

All existing tasks that occur on the given date are displayed:

```
Found the following tasks: 
1. [D][ ] task (by: Dec 1 2000 19:00)
2. [E][ ] task (from: Jan 12 2000 19:00 to: Jan 15 2023 22:00)
```

### `find` - Find tasks containing a word

Displays all the tasks that contain a given word

Example of usage:

`find task`

Expected outcome:

All tasks containing the given word "task" are displayed:

```
Found tasks containing: task
1. [T][ ] task
2. [D][ ] task (by: Dec 1 2000 19:00)
3. [E][ ] task (from: Jan 12 2000 19:00 to: Jan 15 2023 22:00)
```

### `update` - Update an existing task

Update an existing task with new data.

Example of usage:

`update 1 hello world!`

Expected outcome:

The task at index 1 is updated and the current task list is displayed:

```
Noted. I've updated this task:
[T][ ] hello world!
Now you have 3 tasks in the list.
1. [T][ ] hello world!
2. [D][ ] task (by: Dec 1 2000 19:00)
3. [E][ ] task (from: Jan 12 2000 19:00 to: Jan 15 2023 22:00)
```

### `bye` - Exit the application

Close the application.

Example of usage:

`bye`

Expected outcome:

Application shuts down.