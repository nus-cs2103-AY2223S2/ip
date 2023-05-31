# User Guide
Duke helps you keep track of your tasks.

## Features

### Add and Delete tasks

Add todo, deadline, and event tasks to your list.
Delete tasks that do not require keeping track anymore from your list.

### List tasks

Displays the list of tasks.

### Mark and Unmark tasks

Mark a task as completed or not completed.

### Find task

Search and displays tasks by specified keyword.

### Sort tasks

Displays sorted task list based on chronological date.
Todo tasks will always be at the top of the list.

## Usage

### `todo` - Add a todo

Adds an unmarked todo task to the list.

Format: `todo description`
- The `description` can contain any string.

Example of usage: 

`todo cs2100 homework`
`todo buy cake`

### `deadline` - Add a deadline

Adds an unmarked deadline task to the list.

Format: `todo description /by YYYY-MM-DD`
- The `description` can contain any string.

Example of usage:

`deadline cs2100 homework /by 2023-02-20`
`deadline buy a house /by 2026-12-12`

### `event` - Add an event

Adds an unmarked event task to the list.

Format: `event description /from YYYY-MM-DD /to YYYY-MM-DD`
- The `description` can contain any string.

Example of usage:

`event recess week /from 2023-02-20 /to 2023-02-27`
`event week six /from 2023-02-13 /to 2023-02-19`

### `sort` - Sorts the list

Sorts the list by date, in chronological order.

Format: `sort`

Example of usage:

`sort`
