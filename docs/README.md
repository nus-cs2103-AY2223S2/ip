# User Guide

## Features 

### Feature-Adding Tasks

Adds a task to the tasklist, of types `todo`, `event` and `deadline`

### Feature-Removing Tasks

Removes a task from the list by its selected index.

### Feature-Marking Tasks

Marks a task from the list as done by its selected index.

### Feature-Unmarking Tasks

Unmarks a task from the list as done by its selected index.

### Feature-Listing Tasks

Lists all tasks added to the list.

### Feature-Finding Tasks

Search for tasks in the list which match a certain keyword.

### Feature-Help

Displays help for all the commands, and provides specific help for commands if required.

### Feature-Exit

Exits the application.

## Usage

### `todo` - Add Todo Task

Adds a `todo` task to the list.

Example of usage: 

`todo (tasks)`

Expected outcome:

A todo task without a specific date attached to it is added to the task list. 
It can be marked as done or undone.


### `event` - Add Event Task

Adds a `event` task to the list.

Example of usage:

`event (tasks) /at (time, no specific format)`

Expected outcome:

An event task with a date attached to it is added to the task list.
It can be marked as done or undone.


### `deadline` - Add Deadline Task

Adds a `deadline` task to the list.

Example of usage:

`deadline (tasks) /by yyyy-mm-dd`

Expected outcome:

A deadline task with a specific date attached to it is added to the task list.
It can be marked as done or undone.


### `remove` - Removing Tasks

Removes a task from the task list.

Example of usage:

`remove (index)`

Expected outcome:

Removes the task with index `index` from the task list.


### `mark` - Mark Task

Marks a task in the list as done.

Example of usage:

`mark (index)`

Expected outcome:

Marks the task with index `index` from the task list as done.

### `unmark` - Unmark Task

Marks a task in the list as undone.

Example of usage:

`unmark (index)`

Expected outcome:

Marks the task with index `index` from the task list as undone.

### `list` - List Tasks

List all tasks added to the task list.

Example of usage:

`list`

Expected outcome:

All tasks added to the task list will be displayed, together with their done status and their
task type.

### `find` - Find Tasks

Find all tasks in the task list which match a certain keyword.

Example of usage:

`find (keyword)`

Expected outcome:

All tasks which have a substring which matches the keyword will be displayed.

### `help` - Help for using Commands

Displays help on how to use the commands for the application.

Example of usage:

`help`
`help (command)`

Expected outcome:

`help` displays a list of commands the application uses.

`help (command)` displays specific formats on how the commands are used.

### `exit` - Exit Application

Exits from the application.

Example of usage:

`exit`

Expected outcome:

Tasks added to the tasklist are saved and the application exits.