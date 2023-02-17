# User Guide for Duke

## Features

### Managing Tasks

- Add or delete tasks, deadlines and events
- Mark or unmark tasks as completed.

### Finding Tasks

- Find tasks with specified keywords.

### Updating Tasks

- Update dates of existing tasks.

## Usage

### `todo` - Adds a todo to your task list.

How to use:

`todo (task description)`

Example of usage:

`todo readbook`

Expected outcome:

```
Heard! I've added this task:
    [T][] readbook
Now you have 1 task(s) in the list.
```

### `deadline` - Adds a deadline to your task list.

How to use:

`deadline (description of task) /by (yyyy-mm-dd)`

Example of usage:

`deadline return book /by 2023-02-10`

Expected outcome:

```
Heard! I've added this task:
    [D][] return book (by: Feb 10 2023)
Now you have 2 task(s) in the list.
```
### `event` - Adds an event to your task list.

How to use:

`event (description of task) /from (yyyy-mm-dd) /to (yyyy-mm-dd)`

Example of usage:

`event reading /from 2022-02-10 /to 2023-01-23`

Expected outcome:

```
Heard! I've added this task:
    [E][] reading (from: Feb 10 2022 to: Jan 23 2023)
Now you have 3 task(s) in the list.
```


### `delete` - Deletes a task from your task list.

How to use:

`delete (index of task)`

Example of usage:

`delete 1`

Expected outcome:

```
Understood! I've removed this task:
    [T][] readbook
Now you have 2 task(s) in the list.
```

### `mark` - Marks a task from your task list.

How to use:

`mark (index of task)`

Example of usage:

`mark 1`

Expected outcome:

```
Hooray! I've marked this task as completed:
    [D][X] return book (by: Feb 10 2023)
```

### `unmark` - Unmarks a task from your task list.

How to use:

`unmark (index of task)`

Example of usage:

`unmark 1`

Expected outcome:

```
Alright, I've marked this task as not completed yet:
    [D][] return book (by: Feb 10 2023)
```

### `list` - Displays your task list.

How to use:

`list`

Example of usage:

`list`

Expected outcome:

```
Here are the task(s) in your list:
    1. [D][] return book (by: Feb 10 2023)
    2. [E][] reading (from: Feb 10 2022 to: Jan 23 2023)
```
### `find` - Finds tasks with specified keyword(s) from your task list.

How to use:

`find (keywords)`

Example of usage:

`find reading`

`find reading return`

Expected outcome:

```
Here are the matching task(s) in your list:
    1. [E][] reading (from: Feb 10 2022 to: Jan 23 2023)
```

```
Here are the matching task(s) in your list:
    1. [D][] return book (by: Feb 10 2023)
    2. [E][] reading (from: Feb 10 2022 to: Jan 23 2023))
```
### `reminders` - Updates the date of a task in your task list.

How to use:

`reminders`

Example of usage:

`reminders`

Expected outcome:

```
Here are your tasks with deadlines:
    1. [D][] return book (by: Feb 10 2023) - This is due in (-n) day(s)!
    2. [E][] reading (from: Feb 10 2022 to: Jan 23 2023)) - This event starts in (-n) day(s)!
```
```
n is the number of days calculated from the current date to the deadline
```
### `bye` - Exits the program.

How to use:

`bye`

Example of usage:

`bye`

Expected outcome:

```
Bye! Hope to see you soon!
