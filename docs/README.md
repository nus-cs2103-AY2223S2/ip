# User Guide

Welcome to Krusty Krab. Here are a list of features and commands that will make your life easier. 

## Features 

| Command                 | Description |
|-------------------------|-----|
| `bye` `exit`            | Exit the app |
| `todo`<br/> `t`         | Create a new task 
| `deadline` <br/> `d`    | Create a new task to be completed by a given date
| `event`<br/>`e`         | Create a new event with a start and end date
| `list`<br/>`ls`<br/>`l` | List all tasks
| `delete`<br/>`rm`       | Delete a task
| `mark`<br/>`m`          | Mark task as completed
| `unmark` <br/>`um`      | Unmark completed task
| `find`<br/>`f`           | Search for a task

### Feature - Quit Program

Close and quit the application automatically using the command `exit` or `bye`.

### Feature - Add ToDo

A ToDo task can be added using `todo {description}`.

Usage: `todo Jogging`

Entry in Duke: `[T][ ] Jogging`

### Feature - Add Deadline

A Deadline task with a deadline date can be added using `deadline {description} \by {date}`.
The date must be in DD-MM-YYYY format.

Usage: `deadline read book /by 28-05-2023`

Entry in Duke: `[D][ ] read book by: 28/05/2023`

### Feature - Add Event

An Event task with 2 dates (from and to) can be added using `event {description} \from {date} /to {date}`.
The date can be in any format.

Usage: `event John's birthday party /from 4pm /to 6pm`

Entry in Duke: `[E][ ] John's birthday party from: 4pm to: 6pm`

## List all tasks

All tasks currently saved can be listed using the `list` command.

Usage: `list`

Entry in Duke: `1. [T][] Jogging`

## Delete a task
Any task can be deleted by index using `delete {index}`.

Usage: `delete 1`

Sample output:
`Ok I have removed 1. [T][ ] Jogging`

## Marking task as completed
A task can be marked as completed by its index, using `mark {index}`.

Usage: `mark 1`

Sample output, where the `X` denotes being marked:
`1. [T][X] Jogging`

## Unmarking completed tasks

A completed task can be reverted to an incomplete state by its index, using `unmark {index}`.

Sample sage: `unmark 1`

Sample output:
`1. [T][ ] Jogging`

## Searching for tasks

A search bar functionality can be obtained with the `find` command. 
If the search query is a part of the task description, the task will be returned as the search result.

Syntax: `find {string}`

Example usage: `find jogging`



Sample output:
```
1. [T][ ] Jogging
5. [D][ ] Jogging with Jacob /by 29-05-2023
```