<!-- omit from toc -->
# User Guide

<!-- omit from toc -->
## Table of Contents

- [Feature Syntax](#feature-syntax)
  - [List all tasks: `list`](#list-all-tasks-list)
  - [Create a new task: `todo`](#create-a-new-task-todo)
  - [Create a new task that has a deadline: `deadline`](#create-a-new-task-that-has-a-deadline-deadline)
  - [Create a new task that has a start time and an end time: `event`](#create-a-new-task-that-has-a-start-time-and-an-end-time-event)
  - [Delete a task: `delete`](#delete-a-task-delete)
  - [Mark a task as completed: `mark`](#mark-a-task-as-completed-mark)
  - [Unmark a task as completed: `unmark`](#unmark-a-task-as-completed-unmark)
  - [Search for a task by name: `find`](#search-for-a-task-by-name-find)
  - [Find all tasks that are due on a particular date: `dueon`](#find-all-tasks-that-are-due-on-a-particular-date-dueon)
  - [Undo previous commands: `undo`](#undo-previous-commands-undo)

## Feature Syntax

### List all tasks: `list`

Format: `list`

### Create a new task: `todo`

Format: `todo <task name>`

Example:

![`todo` example](example_todo.png)

### Create a new task that has a deadline: `deadline`

Format: `deadline <task name> /by <yyyy-mm-dd>`

Example:

![`deadline` example](example_deadline.png)

### Create a new task that has a start time and an end time: `event`

Format: `event <task name> /from <start time> /to <end time>`

Example:

![`event` example](example_event.png)

### Delete a task: `delete`

Format: `delete <task number>`

Example:

![`delete` example](example_delete.png)

### Mark a task as completed: `mark`

Format: `mark <task number>`

Example:

![`mark` example](example_mark.png)

### Unmark a task as completed: `unmark`

Format: `unmark <task number>`

Example:

![`unmark` example](example_unmark.png)

### Search for a task by name: `find`

Format: `find <task name>`

Example:

![`find` example](example_find.png)

### Find all tasks that are due on a particular date: `dueon`

Format: `dueon <yyyy-mm-dd>`

Example:

![`dueon` example](example_dueon.png)

### Undo previous commands: `undo`

Format: `undo`

Example:

![`undo` example](example_undo.png)
