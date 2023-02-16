# User Guide

Bob is a chat-bot made to help you manage your tasks. He accepts CLI commands while having a GUI for display.

## Getting started


## Command cheatsheet
| Actions | Format | Example
| --- | --- | --- |
| add todo | `todo <description>` | `todo assignment`
| add deadline | `deadline <description> /by <date>` | `deadline submission /by 2023-02-16`
| add event | `event <description> /from <startDate> /to <endDate>` | `event Holiday /from 2023-06-01 /to 2023-09-01`
| mark task | `mark <index>` | `mark 1`
| unmark task | `unmark <index>` | `unmark 1`
| delete task | `delete <index>` | `delete 1`
| find tasks | `find <keyword>` | `find appointment`
| show reminders | `remind` | `remind`

## Features 

### List current tasks: `list`
Shows all tasks and their status

Format: `list`

### Add a task todo: `todo`
Format: `todo <description>`

Example: `todo assignment`

### Add a task deadline: `deadline`
Format: `deadline <description> /by <date>`

Example: `deadline submission /by 2023-02-16`

### Add an event: `event`
Format: `event <description> /from <startDate> /to <endDate>`

Example: `event Holiday /from 2023-06-01 /to 2023-09-01`

### Mark a task: `mark`
Format: `mark <index>`

Example: `mark 1`

### Unmark a task: `unmark`
Format: `unmark <index>`

Example: `unmark 1`

### Delete a task: `delete`
Format: `delete <index>`

Example: `delete 1`

### Filter tasks: `find`
Format: `find <keyword>

Example: `find appointment`

### Show reminders: `remind`
Format: `remind`

## Usage

### `Keyword` - Describe action

Describe the action and its outcome.

Example of usage: 

`keyword (optional arguments)`

Expected outcome:

Description of the outcome.

```
expected output
```
