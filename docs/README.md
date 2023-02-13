# User Guide

## Features 

### Task Tracker

Keep an organised list of tasks! Add or delete things to do, tasks with deadlines, and events!

### Search

Have a long list of tasks? You can easily find the task you're looking for by providing a keyword!

### Auto-save

Your list is automatically saved :smile: Simply reopen Duke to revisit your list!

## Usage

### `todo` or `t` - Adds a todo task

A todo task will be added to your list.

Example of usage: 

`todo return book`

Expected outcome:

Your task will be added to the list.

```
i've added this task:
[T][] return book
you have 1 task in the list
```

### `deadline` or `d` - Adds a deadline task

A deadline task will be added to your list.

Example of usage: 

`deadline return book /by 2023-07-07 2359`

Expected outcome:

Your task will be added to the list.

```
i've added this task with deadline:
[D][] return book (by: Jul 07 2023 23:59)
you have 2 tasks in the list
```

### `event` or `e` - Adds an event task

An event task will be added to your list.

Example of usage: 

`event party /from 2023-07-07 1600 /to 2023-07-07 2200`

Expected outcome:

Your task will be added to the list.

```
i've added this event:
[E][] party (from: Jul 07 2023 16:00 to: Jul 07 2023 22:00)
you have 3 tasks in the list
```

### `list` or `l` - Lists tasks

Shows the user the tasks in the list.

Example of usage: 

`list`

Expected outcome:

The user can view the tasks they've added (if any).

```
1. [T][] return book
2. [D][] return book (by: Jul 07 2023 23:59)
3. [E][] party (from: Jul 07 2023 16:00 to: Jul 07 2023 22:00)
```

### `mark` - Mark a task as done

Marks a task in the list as done.

Example of usage: 

`mark 1`

Expected outcome:

Task specified is marked as done.

```
nice! i've marked this task as done:
[T][X] return book
```

### `unmark` - Unmark a task from being done

Unmarks a task in the list from being done.

Example of usage: 

`unmark 1`

Expected outcome:

Task specified is not marked as done.

```
ok, i've marked this task as not done yet:
[T][] return book
```

### `find` - Search for tasks

Finds tasks containing the keyword provided.

Example of usage: 

`find book`

Expected outcome:

User views the tasks with the keyword provided.

```
here are the matching items in your list:
1. [T][] return book
2. [D][] return book (by: Jul 07 2023 23:59)
```

### `delete` - Delete a task

Deletes a task from the list.

Example of usage: 

`delete 1`

Expected outcome:

The specified task is deleted from the list.

```
i've removed this task:
[T][] return book
you have 2 tasks in the list
```
