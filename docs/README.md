# User Guide

Duke is a command-based task manager that makes your life easier 
by keeping track of your todos, deadlines as well as events, so that
you can live stress free.


## Features

### Adding tasks

You can add three kinds of tasks:
* To-do
* Events
* Deadlines

### Deleting tasks

Delete tasks that are no longer necessary (or just don't want to see anymore).

### Mark & Unmark

Mark a task as completed when you are done, or unmark it if you think you need to something more.

### List

Display all tasks being tracked in an easy to understand way.

### Find:

Displays all tasks matching a given search key.

### Natural Language
Input date and times as natural language.

## Usage

### `todo` - Adds a todo task to the list

Adds a todo task with the description to the list

Format:

`todo [description]`

### `deadline` - Adds a deadline task to the list

Adds a deadline task with the description and due date to the list

> Tip: You can use natural language to provide the date

Format:

`deadline [description] /by [date]`


### `event` - Adds an event task to the list

Adds a event task with a description and start and end timings to the 
list

> Tip: You can use natural language to provide the dates here as well!

Format:

`event [description] /from [fromDate] /to [endDate]`

### `delete` - Delete a task from the list

Deletes the task at a given index.

> Tip: Run `list` to see all the tasks with their indexes.

Format:

`delete [index]`


### `list` - See all the tasks in the list

See a list of all the tasks in the list.

Each task shows the kind (**T**odo, **D**eadline,  **E**vent) and if it's completed along with the description.

Format:

`list`

### `mark` - Mark a task as done

Marks the task as done for the given index.

Format:

`mark [index]`

### `unmark` - Mark a task as pending

Marks the task as pending for the given index.

Format:

`unmark [index]`


### `find` - Searches for tasks by keywords

Searches for tasks in the list by the 
given keywords

Format:

`find [...keywords]`