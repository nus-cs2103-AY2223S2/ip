# User Guide

## Features

- Create and manage a list of tasks
  - Tasks take the the form of todo tasks, deadlines, events and timed tasks
- Mark tasks as completed, or otherwise
- List and filter the tasks based keywords and date ranges

## Usage

### `hello`

Greetings to Jarvis!

Expected outcome:

Jarvis greets you back!

Aliases:

- `hi`
- `hey`

### `list [KEYWORDS] [/from yyyy-MM-dd] [/to yyyy-MM-dd]`

Shows a list of tasks, filtered by keywords and date range.

Aliases:

- `find`

Example of usage:

`list humanity /from 2012-01-01 /to 2077-12-31`

Expected outcome:

The filtered list of tasks is shown.

```
| 	Here are the tasks in your list:
| 	1. [T][ ] buy cheese
| 	2. [D][ ] final essay (by: 2023-02-09)
| 	3. [E][ ] games carnival (from: 2023-03-01, to: 2023-03-03)
| 	4. [TM][ ] coding assessment (duration: 2 hours, 30 minutes)
```

### `todo TASK_DESCRIPTION`

Creates a task to be done.

Example of usage:

`todo Save humanity`

Expected outcome:

A todo task is created.

```
| 	Got it! I've added task 5 to the list.
| 		[T][ ] Save humanity
```

### `deadline TASK_DESCRIPTION /by yyyy-MM-dd`

Creates a task with deadline.

Example of usage:

`deadline Save humanity /by 2015-04-23`

Expected outcome:

A deadline is created.

```
| 	Got it! I've added task 6 to the list.
| 		[D][ ] Save humanity (by: 2015-04-23)
```

### `event TASK_DESCRIPTION /from yyyy-MM-dd /to yyyy-MM-dd`

Creates an event over a specific date range.

Example of usage:

`event Age of Ultron /from 2015-04-23 /to 2016-04-22`

Expected outcome:

An event is created.

```
| 	Got it! I've added task 7 to the list.
| 		[E][ ] Age of Ultron (from: 2015-04-23, to: 2016-04-22)
```

### `timed TASK_DESCRIPTION [/days DAYS] [/hours HOURS] [/minutes MINUTES]`

Creates a timed task with a specific duration.

Example of usage:

`timed Save Sokovia /days 1 /hours 2 /minutes 3`

Expected outcome:

A timed task is created.

```
| 	Got it! I've added task 8 to the list.
| 		[TM][ ] Save Sokovia (duration: 1 days, 2 hours, 3 minutes)
```

### `mark TASK_NUMBER`

Marks the specified task as completed.

Example of usage:

`mark 5`

Expected outcome:

Expected outcome:

The 5th task in the list is marked.

```
| 	Got it! I've marked task 5 as done.
| 		[T][X] Save humanity
```

### `unmark TASK_NUMBER`

Marks the specified task as not completed.

Example of usage:

`unmark 5`

Expected outcome:

The 5th task in the list is marked.

```
| 	Got it! I've marked task 5 as undone.
| 		[T][ ] Save humanity
```

### `delete TASK_NUMBER`

Deletes the specified task from the list.

Example of usage:

`delete 1`

Expected outcome:

The 5th task in the list is deleted.

```
| 	Got it, I've removed task 5.
| 		[T][ ] Save humanity
```

### `bye`

Saves your task list and exits.

Expected outcome:

Jarvis takes his leave into cyberspace.

Aliases:

- `goodbye`