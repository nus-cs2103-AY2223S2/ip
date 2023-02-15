# User Guide

Duke is a program that lets users keep track of their tasks easily with simple commands.

---
## Features 
+ Add & delete tasks to Duke's list along with their due date or start and end date
+ Mark & unmark tasks to show if it is completed or not
+ Display all tasks in Duke's list to the user
+ Find tasks using keywords
+ Saves Duke's list when you want to exit & loads it when you open again
+ Reminds users of tasks happening in 10 days time when you open Duke

---
## Usage

### `todo` - Adds a task with no date

It will add a task to Duke's list without requiring any date.

Usage:

`todo keyword [more keywords]`

Example of usage: 

`todo task naming`

#

### `deadline` - Adds a task with a due date

It will add a task to Duke's list with its due date.
It will also convert the month of date to from numbers to word if the input date is given as `d/m/yyyy`

Usage:

`deadline keyword [more keywords] /by date [more keywords]`

Example of usage:

`deadline project /by 10/05/2023`

#

### `event` - Adds a task with a start & end date

It will add a task to Duke's list with its start & end date.
It will also convert the month of date to from numbers to word if the input date is given as `d/m/yyyy`

Usage:

`event keyword [more keywords] /from date [more keywords] /to date [more keywords]`

Example of usage:

`event reading week /from 10/05/2023 /to 20/5/2023`

#

### `list` - Lists all tasks in Duke's list

It will list all tasks user have added to Duke and their status (marked or unmarked)

Usage:

`list`

#

### `mark` - Marks a task 

It will mark the specific task at the given index in Duke's list.

Usage:

`mark index`

Example of usage:

`mark 3`

#

### `unmark` - Unmarks a task

It will unmark the specific task at the given index in Duke's list.

Usage:

`unmark index`

Example of usage:

`unmark 1`

#

### `delete` - Deletes a task

It will delete the specific task at the given index in Duke's list.

Usage:

`delete index`

Example of usage:

`delete 2`

#

### `find` - Finds a task

It will find tasks in Duke's list that contains the given keyword.
If more than 1 keyword are given, it will find only tasks that contains the entire keywords in its exact order.

Usage:

`find keyword [more keywords]`

Example of usage:

`find reading week`

---
## Command Summary

| **Command** | **Format**                     | 
|:-----------:|:-------------------------------|
|    todo     | `todo keyword [more keywords]` | 
|  deadline   | `deadline keyword [more keywords] /by date [more keywords]` | 
|    event    | `event keyword [more keywords] /from date [more keywords] /to date [more keywords]` | 
|    list     | `list`                         | 
|    mark     | `mark index`                   | 
|   unmark    | `unmark index`                 | 
|   delete    | `delete index`                 | 
|    find     | `find keyword [more keywords]` | 