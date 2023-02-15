# User Guide

Welcome to Clippy, your favourite task manager!
With Clippy, you no longer need to worry about missing
deadlines as all you need is in this app.

## Features 

### Add Tasks
You can add tasks with no deadlines, one deadline or two dates signifying the date range for the task!

### Mark/Unmark as Complete
Done with your task? Let Clippy know and he'll be happy to help you strike it off your task list!

### List Tasks
Unsure what tasks you have left? Let Clippy recall them for you!

### Delete Tasks
You can delete any tasks that you think no longer deserves to be on your task list.

### Find Task(s)
Remembered only part of what you need to do? Let Clippy find tasks that match what you have in mind!

### Prioritise Tasks
Need to ensure certain tasks are done first? Clippy recognises up to 4 levels of priorities to help prioritise your tasks!

## Usage

### `list` - List all tasks  

Lists all the tasks currently in your task list.


Example of usage: 

`list`

Expected outcome:

A description of the tasks in the task list.
```
1. [T][ ] World Domination (Priority: URGENT)
```

### `todo` - Adds a To-Do
Adds a task with no deadline(s) to the task list.

Example of usage:
`todo World Domination`

Expected outcome:
Addition of a new to-do to the task list.
```
Got it! I've added this task:
[T][ ] World Domination (Priority: NONE)
Now you have 1 task in the list.
```

### `deadline` - Adds a Deadline
Adds a task with a deadline to the task list.

Example of usage:
`deadline World Domination /by 2023-01-01`

Expected outcome:
Addition of a new deadline to the task list.
```
Got it! I've added this task:
[D][ ] World Domination (Priority: NONE) (by: Sun 01 Jan)
Now you have 1 task in the list.
```

### `event` - Adds an Event 
Adds a task with a start and end date to the task list.

Example of usage:
`event World Domination /from 2023-01-01 /to 2023-12-01`

Expected outcome:
Addition of a new deadline to the task list.
```
Got it! I've added this task:
[E][ ] World Domination (Priority: NONE) (from: Sun 01 Jan to: Fri 01 Dec)
Now you have 1 task in the list.
```

### `mark` - Marks a task as complete
Marks a task as complete.

Example of usage:
`mark 1`

Expected outcome:
The task with ID of 1 is marked as complete.
```
Great job! I've marked this task as done:
[T][X] World Domination (Priority: URGENT)
```

### `unmark` - Marks a task as incomplete
Marks a task as incomplete.

Example of usage:
`unmark 1`

Expected outcome:
The task with ID of 1 is marked as incomplete.
```
Aww... I've marked this task as not done yet:
[T][ ] World Domination (Priority: URGENT)
```

### `delete` -- Deletes a task
Deletes a task.

Example of usage:
`delete 1`

Expected outcome:
The task with ID of 1 is deleted.
```
Got it! I've removed this task:
[T][X] World Domination (Priority: URGENT)
```

### `find` -- Searches for a task
Searches for a task.

Example of usage:
`find World`

Expected outcome:
The task(s) with the word "World" in their description is shown.
```
Here are the matching tasks in your list!
[T][X] World Domination (Priority: URGENT)
[T][ ] Become Mr. Worldwide (Priority: HIGH)
```

### `priority` -- Assigns a priority to a task
Assigns a priority to a task.

Example of usage:
`priority 1 HIGH`

Expected outcome:
The task with the ID of 1 is marked as having HIGH priority.
```
Got it! I've marked this task with HIGH priority:
[T][ ] World Domination (Priority: HIGH)
```

### `bye` -- Saves and exits the program
Saves and exits the program.

Example of usage:
`bye`

Expected outcome:
The program saves the current task list and exits.
```
Saving state, please wait...
State successfully saved.
Hope I helped. Goodbye!
```
