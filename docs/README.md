# User Guide

## Features 

### Task management

The program serves as a basic task 

### Add Task

The program supports 3 types of task - `todo`, `deadline` and `event`.
While any of them allows you to have a descriptive body text and a state (`COMPLETED` or `INCOMPLETE`), `todo` does not have a date or time field.
However, `deadline` has a due and `event` has both a beginning and an end.

#### Examples

- `todo Buy coffee`
- `deadline CS2101 OP999 /by 2023-02-29`
- `event CS2103 Presentation /from 2023-03-01 /to 2023-04-01`

## Usage

### `todo <Description>` - Add a `todo`

Create a new `todo` with the description provided.

Example of usage: 

`todo Buy coffee`

Expected outcome:
```
Got it. I've added this task:
[T][ ] Buy coffee
Now you have 1 task(s) in the list.
```

### `deadline <Description> /by <Date\Time String>` - Add a `deadline`

Create a new `deadline` with the description and due provided.

Example of usage:

`deadline CS2101 OP999 /by 2023-02-29`

Expected outcome:
```
Got it. I've added this task:
[T][ ] CS2101 OP999 (by: 2023-01-01 00:00)
Now you have 1 task(s) in the list.
```

### `event <Description> /from <Date\Time String> /to <Date\Time String>` - Add a `event`

Create a new `deadline` with the description and due provided.

Example of usage:

`event CS2103 Presentation /from 2023-03-01 /to 2023-04-01`

Expected outcome:
```
Got it. I've added this task:
[E][ ] CS2103 Presentation (from: 2023-03-01 00:00, to: 2023-04-01 00:00)
Now you have 1 task(s) in the list.
```

### `delete <Number>` - Delete a Task

Deletes the task with specified task number.

Example of usage:

`delete 1`

### `find <Keyword>` - Find a Task

Find all tasks that include the keyword as a substring.

Example of usage:

`find CS2103`

### `(un)mark <Number>` - (un)Mark a Task

Declare a task as incomplete or completed.
