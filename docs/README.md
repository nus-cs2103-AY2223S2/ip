# User Guide

## Features 

### Record your tasks

Record your tasks as a todo, deadline, or event!

### Save and load

When you exit Duke, Duke will automatically save your tasks, and load it when you open Duke again.

## Usage

### `List` - Get your current tasks

Duke will list out all your current tasks, complete or incomplete

Example of usage: 

`list`

Expected outcome:

Get list of current tasks.

```
1. [T][X] Do homework
2. [D][] Finish assignment (by 23/02/2023)
```
### `todo` - Add a todo

Example of usage:

`todo Get laundry`

Expected outcome:

Add task "Get laundry" to task list.

```
Got it. I've added this task:
    [T][]Get laundry
Now you have 1 task(s) in the list.
```

### `deadline` - Add a deadline

Example of usage:

`deadline Finish homework /by 2023-02-02`

Expected outcome:

Add deadline to "Finish homework" by 02/02/2023.

```
Got it. I've added this task:
    [D][]Get laundry (by: 02/02/2023)
Now you have 1 task(s) in the list.
```

### `event` - Add an event

Example of usage:

`event hackathon /from 2023-02-02 /to 2023/02-03`

Expected outcome:

Add event to "hackathon" from 02/02/2023 to 03/02/2023.

```
Got it. I've added this task:
    [E][]hackathon (from: 02/02/2023 to: 03/02/2023)
Now you have 1 task(s) in the list.
```

### `delete` - Delete a task

Example of usage:

`delete 1`

Expected outcome:

Delete first task in list.

```
I've deleted the task: Get Laundry
```
### `mark` - Mark task as done.

Example of usage:

`mark 1`

Expected outcome:

Mark first task as done.

```
Nice! I've marked this task as done: Get laundry
```

### `unmark` - Mark task as not done.

Example of usage:

`unmark 1`

Expected outcome:

Mark first task as not done.

```
OK, I've marked this task as not done yet: Get laundry
```

### `find` - Find tasks with stated keyword.

Example of usage:

`find cs2103t`

Expected outcome:

Return all tasks containing the string "cs2103t".

```
Here are the matching tasks I found:
[T][] Do cs2103t ip
[D][] Finish UG (by 20/02/2023)
```