Duke is a task manager to help you organise tasks efficiently!

# User Guide

1. Ensure that you have `Java 11` or above installed
2. Download the latest `duke.jar` from releases
3. After unzipping to desired location, double-click `duke.jar` to start Duke.

## Features

`Duke` is a Task manager that...

* Allows you to keep track of your Events, Deadlines and Todos
    * Mark tasks as complete / not complete
    * Delete tasks
* Find tasks by keywords
* Save details into text file

## Usage

### Adding a task:

`Todo`: `todo [task description]`

* Example of usage: `todo slap a cat`

`Deadline`: `deadline [task Description] /by [start date and time]`

* Example of usage: `deadline return a the plate after eating /by 2025-01-12T12:34:56`

`Event`: `event [task description] /from [start date and time] /to [end date and time]`

* Example of usage: `event sleep /from 2021-01-12T00:00:00 /to 2022-01-12T00:00:00`

### List tasks - `list`

`list` : `list`

Example of usage: `list`

### Delete a task - `delete`

`delete` : `delete [index]`
Example of usage: `delete 1`

### Mark a task a complete - `mark`

`mark` : `mark [index]`
Example of usage: `mark 1`

`unmark` : `unmark [index]`
Example of usage: `unmark 1`

### Find a keyword - `find`

`find` : `find [keyword]`
Example of usage: `find Bob`

## Notes

Commands are not case-sensitive, so `dEADLINE` and `dEaDlInE` will be registered as `deadline`.

All edits to tasks will be saved automatically.
