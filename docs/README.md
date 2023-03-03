# User Guide

## Introduction

---
Jizo is a helpful CLI application that can help you keep track of
your tasks in a simple and concise manner. You can also use it with
the designed GUI.

## Features 

---

### Managing tasks

Jizo allows you to add and delete tasks to your task list. There are
three different types of tasks you can add:
- todo
- deadline
- event

### Viewing your tasks

Jizo can show you a list of current tasks in your task list.

### Saving tasks

Jizo will save your tasks every time you make a change to them.
After you have closed the application, you can open it again and Jizo will 
load up your task list for you.

### Searching for tasks

Jizo can help find matching tasks for you when you input a keyword.

### Marking tasks as done

Jizo allows you to mark tasks as done and undo a marking if needed.

### Setting priority of tasks

Jizo allows you to set three different priority levels for your tasks:
- low
- medium
- high

A task will be set at medium priority by default.

## Usage

---
### `todo` - Adds a todo task

Adds a todo task to your task list.

Format: `todo (task description)`

Example of usage: `todo read book`

### `deadline` - Adds a deadline task

Adds a deadline task to your task list.

Format: `deadline (task description) /by (date in yyyy-mm-dd format)`

Example of usage: `deadline submit assignment /by 2023-02-17`

### `event` - Adds an event task

Adds an event task to your task list.

Format: `event (task description) /from (start time) /to (end time)`

Example of usage: `event meet up with friends /from 2pm /to 4pm`

### `list` - Shows all current tasks

Shows all tasks in your task list.

Format: `list`

### `delete` - Deletes a specific task

Deletes the task that has the index you inputted.

Format: `delete (task index)`

Example of usage: `delete 3`

Expected outcome: The 3rd task in your list will be deleted.

### `mark` - Marks a specified task as done

Marks the task that has the index you inputted as done.

Format: `mark (task index)`

Example of usage: `mark 2`

Expected outcome: The 2nd task in your list will be marked as done.

### `unmark` - Unmarks a specified task

Unmarks the task that has the index you inputted.

Format: `unmark (task index)`

Example of usage: `unmark 2`

Expected outcome: The 2nd task in your list will no longer be marked as done.

### `find` - Finds tasks based on keyword

Shows matching tasks based on keyword you have inputted.

Format: `find (keyword)`

Example of usage: `find read book`

### `low` - Sets priority of specified task to low

Sets the task that has the index you inputted to low priority.

Format: `low (task index)`

Example of usage: `low 1`

Expected outcome: The 1st task in your list will be set to low priority.

### `medium` - Sets priority of specified task to medium

Sets the task that has the index you inputted to medium priority.

Format: `medium (task index)`

Example of usage: `medium 1`

Expected outcome: The 1st task in your list will be set to medium priority.

### `high` - Sets priority of specified task to high

Sets the task that has the index you inputted to high priority.

Format: `high (task index)`

Example of usage: `high 1`

Expected outcome: The 1st task in your list will be set to high priority.

### `bye` - Ends the session with Jizo

Ends the current session with Jizo.

Format: `bye`
