# User Guide

## Features

* Create up to 3 types of tasks: `Todo`, `Deadline`, `Event`
* Find tasks easily with `find` command
* Search for tasks happening on a date with `on` command
* Stay updated with `reminder` feature

### Create, update and delete tasks

Use the `todo`, `deadline` or `event` command to create a task. The default
status will be uncompleted. Use the `mark` or `unmark` command to change the
status of the task. `index` command allows you to view all tasks. Lastly, 
`delete` command removes a task from the list.

> ⚠️ important: all dates must follow the format YYYY-MM-DD

### Find task with query string

Use the `find` command, followed the query string, to find tasks matching the 
string. Duke will perform a string comparison to find tasks which contains the
substring provided.

### Search for task on a specific date

Use the `on` command, followed by a date, to find `Deadline` or `Event` on that
date.

### Stay updated

Use the `reminder` command to list out all uncompleted `Deadline` and `Event`
and their due dates with respect to the current date.

## Usage

### `Todo` - Add todo to list

This command creates an unmarked todo item and add it to the list.

Example of usage:

`todo <description>`

Expected outcome:

```
Got it. I've added this task:
[T][ ] Read book
Now you have 4 tasks in the list.
```

### `Deadline` - Add deadline to list

This command creates an unmarked deadline item and add it to the list.

Example of usage:

`deadline <description> /by <date>`

> ⚠️ important: all dates must follow the format YYYY-MM-DD

Expected outcome:

```
Got it. I've added this task:
[D][ ] Write reflection (by: Mar 1 2023)
Now you have 4 tasks in the list.
```

### `Event` - Add event to list

This command creates an unmarked event item and add it to the list.

Example of usage:

`event <description> /from <date> /to <date>`

> ⚠️ important: all dates must follow the format YYYY-MM-DD

Expected outcome:

```
Got it. I've added this task:
[E][ ] Book fair (from: Mar 1 2023 to: Mar 31 2023)
Now you have 4 tasks in the list.
```

### `List` - Show all tasks

This command shows all tasks and the respective completion status.

Example of usage:

`list`

Expected outcome:

```
Here are the tasks in your list:
1. [E][ ] Book fair (from: Mar 1 2023 to: Mar 31 2023)
2. [T][X] Read book
3. [D][ ] Write reflection (by: Mar 1 2023)
```

### `Mark` - Mark a task as completed

This command marks a task as completed.

Example of usage:

`mark <index>`

> 💡 the index can be obtained with the `list` command

Expected outcome:

```
Nice! I've marked this task as done:
[T][X] Read book
```

### `Unmark` - Mark a task as uncompleted

This command marks a task as uncompleted.

Example of usage:

`unmark <index>`

> 💡 the index can be obtained with the `list` command

Expected outcome:

```
OK, I've marked this task as not done yet:
[T][ ] Read book
```

### `Delete` - Delete a task

This command removes a task from the list.

Example of usage:

`delete <index>`

> 💡 the index can be obtained with the `list` command

Expected outcome:

```
Noted. I've removed this task:
[T][ ] Read book
Now you have 2 tasks in the list.
```

### `Find` - Search for a task

This command searches for tasks containing the provided text.

Example of usage:

`find <query>`

Expected outcome _(the query is "book")_:

```
Here are the matching tasks in your list:
1.[E][ ] Book fair (from: Mar 1 2023 to: Mar 31 2023)
2.[T][ ] Read book
```

### `On` - Search for tasks on a date

This command searches for `Deadline` and `Event` happening on that date.

Example of usage:

`on <date>`

> ⚠️ important: all dates must follow the format YYYY-MM-DD

Expected outcome _(the date is "2023-03-01")_:

```
There are 2 tasks on Mar 1 2023:
1. [E][ ] Book fair (from: Mar 1 2023 to: Mar 31 2023)
2. [D][ ] Write reflection (by: Mar 1 2023)
```

### `Reminder` - Show uncompleted tasks with due date

This command searches for uncompleted `Deadline` and `Event`, and reminds you
the amount of time left.

Example of usage:

`reminder`

Expected outcome _(today is 2023-02-17)_:

```
Here are your reminders:
1. 12 more days to Book fair
2. 12 more days to Write reflection
```

### `Bye` - Exit the program

This command saves the list to your local storage and quits the program.

Example of usage:

`bye`

Expected outcome:

```
Bye. Hope to see you again soon!
```

