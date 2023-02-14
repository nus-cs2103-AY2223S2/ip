# User Guide

## Features 

### Create _various_ types of tasks!

Todo, deadline and event tasks are available tasks to use to suit to the various tasks you have to keep track of!

### Tasks are already sorted

When you view tasks, they are sorted to help you prioritise what is the most time-sensitive tasks to focus on!

### Simple search by keywords or by date

You can easily look for tasks by a specific key term or find tasks that occur at a speicific date!

## Usage

### `todo`

Create a new todo, a task that has no deadlines or timeframe.

Example of usage: 

`todo Finish my bananas`

Expected outcome:

Creates a new todo task.

```
Hey new task added!
[T][ ] Finish my bananas
```

### `deadline`

Create a new deadline task, a task with a deadline date  
`/by` has a format of _YYYY-MM-DD_

Example of usage:

`deadline Complete sprint 1 /by 2023-01-02`

Expected outcome:

Creates a new deadline task.

```
Hey new task added!
[D][ ] Complete sprint 1 (by: 2023/01/02)
```

### `event`

Create a new event task, a task with a timeframe
`/from` and `/to` has a format of _YYYY-MM-DD_

Example of usage:

`event Chinese new year /from 2023-01-22 /to 2023-01-23`

Expected outcome:

Creates a new event task.

```
Hey new task added!
[E][ ] Chinese new year (from: 2023/01/22 to: 2023/01/23)
```

### `list`

Displays the list of tasks left, sorted.

Example of usage:

`list`

Expected outcome:

A list of tasks.

```
Arii has retrieved your current tasks...
1. [T][ ] Finish my bananas
2. [D][ ] Complete sprint 1 (by: 2023/01/02)
3. [E][ ] Chinese new year (from: 2023/01/22 to: 2023/01/23)
```


### `find`

Displays a list of tasks that contains the given keyword(s).

Example of usage:

`find banana year`

Expected outcome:

A list of tasks relevant to the keywords

```
Found tasks with keywords: banana, year
[T][ ] Finish my bananas
[E][ ] Chinese new year (from: 2023/01/22 to: 2023/01/23)
```

### `check`

Displays a list of tasks that is relevant to the given date.  
A deadline task is considered relevant if its _by_ date is before the given date.  
An event task is considered relevant if the given date is between the _from_ and _to_ dates, inclusive.  

Given date should have a format of _YYYY-MM-DD_

Example of usage:

`check 2023-01-22`

Expected outcome:

A list of tasks relevant to the date given.

```
Relevant tasks on specified date:  
[E][ ] Chinese new year (from: 2023/01/22 to: 2023/01/23)
```

### `mark`

Mark a task as completed.

Example of usage:

`mark 1`

Expected outcome:

The task is updated as completed.

```
This task is now done, what's next?
[T][X] Finish my bananas
```

### `unmark`

Mark a task as not completed.

Example of usage:

`unmark 1`

Expected outcome:

The task is updated as completed.

```
This task is now not done, get to work!
[T][ ] Finish my bananas
```

### `delete`

Removes a task from the current set of tasks.

Example of usage:

`delete 1`

Expected outcome:

Deletes the task.

```
Task deleted. Are you skipping on work again?
```

### `save`

Saves your current set of tasks to the local disk.

Example of usage:

`save`

Expected outcome:

Saves all tasks for future use.

```
Your tasks is now safely stored.
```


### `bye`

Ends the interaction and closes the application.

Example of usage:

`bye`

Expected outcome:

Closes the application. 

