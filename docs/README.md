# User Guide

## Features 

### Add an Event Task

Event tasks have start and end parameters.

### Add a Deadline Task

Deadline tasks have a deadline parameter. 

### Add a Todo Task

Todo tasks are tasks that are not time bound.

### Find a Task

Easily search for a task in your list.

### Save your Tasks

Save tasks and get back to your list again.

## Usage

#### Note: Please follow the format exactly and save tasks before closing the window

### `event` - Creates an event type task

Format:

`event {description} /from dd/mm/yy hhmm /to dd/mm/yy hhmm`

where **/from** and **/to** specify the start and end timings respectively. Time is in 24hr format.

Example of usage:

`event party /from 12/2/23 0600 /to 12/2/23 1000`

Expected outcome:

**[E]** indicates an event type task.

**[ ]** indicates the task is not done.

**[X]** indicates the task is done.

```
I have added: [E][] party (Start: 12 Feb 2023 06:00AM | End: 12 Feb 2023 10:00AM)!
There are currently x tasks(s) in the list!
```

### `deadline` - Creates a deadline type task

Format:

`deadline {description} /by dd/mm/yy hhmm`

where **/by** specifies the task completion date and time. Time is in 24hr format.

Example of usage:

`deadline test program /by 25/12/23 1150`

Expected outcome:

**[D]** indicates a deadline type task.

**[ ]** indicates the task is not done.

**[X]** indicates the task is done.

```
I have added: [D][] test program (By: 25 Dec 2023 11:50AM)!
There are currently x tasks(s) in the list!
```

### `todo` - Creates a todo type task

Format:

`todo {description}`

Example of usage:

`todo homework`

Expected outcome:

**[T]** indicates a todo type task.

**[ ]** indicates the task is not done.

**[X]** indicates the task is done.

```
I have added: [T][] homework!
There are currently x tasks(s) in the list!
```

### `mark` - Marks a task as completed

Format:

`mark x` 

where x is the index of your task in the list.

Example of usage:

`mark 1`

### `unmark` - Marks a task as incomplete

Format:

`unmark x`

where x is the index of your task in the list.

Example of usage:

`unmark 1`

### `delete` - Deletes a task from the list

Format:

`delete x`

where x is the index of your task in the list.

Example of usage:

`delete 1`

### `list` - Displays all tasks in your list


Example of usage: 

`list`


### `find` - Lists all tasks that match a given keyword

Example of usage:

`find par`

Expected outcome:

The following list displays all the items that match the "par" keyword provided.

```
1. [E][X] party (Start: 12 Feb 2023 06:00AM | End: 12 Feb 2023 10:00AM)
2. [T][] park the car
```
### `help` - Displays a list of all commands and example usages

If you ever forget a command, not to worry just key in 'help' !

Example of usage:

`help`

### `bye` - Saves all tasks in your list

**_Do remember to save your tasks if not all new updates since the last session will be lost!_**

Example of usage:

`bye`