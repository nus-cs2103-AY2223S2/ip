# User Guide

> "Habits are the compound interest of self-improvement." - James Clear ([source](https://www.goodreads.com/work/quotes/62221762-atomic-habits-an-easy-proven-way-to-build-good-habits-break-bad-one))

Persona Duke is your personal companion, designed for you to finish your tasks.

## Features

### Add Task (Todo/Deadline/Event)

Add a task, todo or a deadline or an event, to your task list.

### Delete Task (Todo/Deadline/Event)

Delete a task, todo or a deadline or an event, to your task list.

### Mark/Unmark Task

Mark an uncompleted task as completed or unmark a completed as uncompleted.

### Find Tasks

Find tasks by query, comparing all the tasks' description in the task list.

### List Tasks

List all the tasks (with their additional information if there is) in the task list. For event tasks, the starting date and ending date is displayed. For deadline tasks, the due date is displayed.

## Usage

### `todo` - Add todo to task list

Adds todo to task list.

Example of usage: 

`todo description /place location`

Expected outcome:

Task list contains the new todo.

```
Got it Sir! I've added this task:
[T][ ] description @ location
Now you have 1 tasks in the list.
```

### `deadline` - Add deadline to task list

Adds deadline to task list.

Example of usage:

`deadline description /place location /by 31/12/2025 12:59`

Expected outcome:

Task list contains the new deadline.

```
Got it Sir! I've added this task:
[D][ ] description @ location (by: 31 Dec 2025 12:59)
Now you have 1 tasks in the list.
```

### `event` - Add event to task list

Adds event to task list.

Example of usage:

`event description /place location /from 31/12/2025 12:59 /to 31/12/2025 13:59`

Expected outcome:

Task list contains the new event.

```
Got it Sir! I've added this task:
[E][ ] description @ location (from: 31 Dec 2025 12:59 to: 31 Dec 2025 13:59)
Now you have 1 tasks in the list.
```

### `delete` - Delete task from task list

Delete task from task list.

Example of usage:

`delete 1`

Expected outcome:

Task number 1 will be removed from task list.

```
Yes Sir! I've removed this task:
[T][ ] description @ location
Now you have 0 tasks in the list.
```

### `mark` - Mark task as done

Mark task as done.

Example of usage:

`mark 1`

Expected outcome:

Task number 1 will be marked as done.

```
Well done Sir! I've marked this task as done:
[T][X] description @ location
```

### `unmark` - Mark task as not done

Mark task as not done.

Example of usage:

`unmark 1`

Expected outcome:

Task number 1 will be marked as not done.

```
Okay Sir! I've marked this task as not done yet:
[T][ ] description @ location
```

### `find` - Find tasks by query

Find tasks by query. Will show tasks with description matching the query.

Example of usage:

`find description`

Expected outcome:

Task with description matching the query will be displayed.

```
Let me try to search your tasks, Sir:
[T][ ] description @ location
My search has completed, Sir!
```

### `list` - List all tasks in task list

List all tasks in task list.

Example of usage:

`list`

Expected outcome:

All tasks will be displayed.

```
Here are the tasks in your list, Sir:
1. [T][ ] description @ location
2. [D][ ] description @ location (by: 31 Dec 2025 12:59)
3. [E][ ] description @ location (from: 31 Dec 2025 12:59 to: 31 Dec 2025 13:59)
```