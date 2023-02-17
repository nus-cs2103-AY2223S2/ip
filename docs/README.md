# User Guide

## Features 

### Feature-Add Task

Add different types of Task, wich are:
+ Todo
+ Deadline
+ Event

### Feature-Delete Task

Remove the task that are done/unwanted

### Feature-Mark Tasks

Mark tasks as done

### Feature-unmark Tasks

Mark tasks as undone

### Feature- find Task

Find tasks with a specific work

## Usage

### `Keyword` - Describe action

Describe the action and its outcome.

Example of usage: 

`keyword (optional arguments)`

### `todo` - Adds todo to the list
Add a todo task to the list

Example of usage: todo do work

Expected outcome: [T][ ] do work

### `deadline` - Adds deadline to the list
Specify the task as well as its deadline

Example of usage: deadline do work |2022-02-03T10:30

Expected outcome: [D][ ] do work (by: Feb 3 2022)

### `event` - Adds event to the list
Specify a task, the start date and time, and end date and time. 

Example of usage: event do work |2022-02-03T10:30|2022-02-03T23:59

Expected outcome: [E][] do work (from: Feb 3 2022 10:30 by: Feb 3 2022 23:59)


### `mark` - marks a task as done
Example of usage: mark 1

Expected outcome: Nice! 1.[T][X] do work marked as done!

### `unmark` - marks a task as undone
Example of usage: unmark 1

Expected Outcome: Ok, I have unmarked it! [T][] do work

### `find` - find task in list
input a word and all the related tasks will be returned

Example of usage: find meeting

Expected outcome:1. [T][] meeting

### `delete` - deletes task from list
Example of usage: delete 2

Expected outcome:2. [T][] meeting has been deleted.


