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

> ⚠️  important: all dates must follow the format YYYY-MM-DD

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

### `Keyword` - Describe action

Describe the action and its outcome.

Example of usage:

`keyword (optional arguments)`

Expected outcome:

Description of the outcome.

```
expected output
```
