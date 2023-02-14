# User Guide

C.R.Y.S.T.A.L is a desktop app for managing tasks. It has been optimized for use either 
by a Command Line Interface (CLI) or a Graphical User Interface (GUI). If you need a personalise
tasklist, C.R.Y.S.T.A.L is here for you.


- [Quick Start](#Quick Start)
- [Features](#Features)
- [Usage](#Usage)
- [Command Summary](#Command Summary)

## Quick Start
1. Ensure that you have Java 11 or above installed.
2. Download the latest version of `Crystal` from [here](https://github.com/thennant/ip/releases) 

## Features 

### Feature-todo

Creates a task that is to be done.

### Feature-deadline

Creates a task that has a deadline.

### Feature-event

Creates a event that has a start date and an end date

### Feature-mark

Marks a task as done or completed

### Feature-unmark

Unmarks a task that was previously marked.

### Feature-list

Lists out all the current task in the tasklist.

### Feature-delete

Deletes a specific task specified by the user.

### Feature-priority

Sets a priority tag to a task that has to be prioritized.

### Feature-find

Finds all the tasks that contain a specific keyword

### Feature-bye

Ends the program.

## Usage

### `Keyword` - todo 

User enters the command `todo` together with the description of the task.


Example of usage: 

`todo buy books`

Expected outcome:

The task is then stored into the tasklist and indicated as "[T]".

```
expected output
------------------------------------------------
Alright, I've added this task:
[T] [ ] buy books
Current number of task: 1
------------------------------------------------
```
### `Keyword` - deadline

User enters the command `deadline` together with the description of the task and the date and time.


Example of usage:

`deadline sell books /by 2020-10-11T15:00`

Expected outcome:

The task is then stored into the tasklist and indicated as "[D]".

```
expected output
------------------------------------------------
Alright, I've added this task:
[D] [ ] sell books (by: Oct 11 2020 0300 PM)
Current number of task: 1
------------------------------------------------
```
### `Keyword` - event

User enters the command `event` together with the description of the task and the date and time.


Example of usage:

`event return books /from 2020-10-11T15:00 /to 2020-11-12T13:00`

Expected outcome:

The task is then stored into the tasklist and indicated as "[E]".

```
expected output
------------------------------------------------
Alright, I've added this task:
[E] [ ] return books (by: Oct 11 2020 0300 PM 
to Nov 12 2020 0100 PM)
Current number of task: 1
------------------------------------------------
```
### `Keyword` - mark

User enters the command `mark` together with the task number in the list.


Example of usage:

`mark 1`

Expected outcome:

The task is then marked and indicated with "[X]".

```
expected output
------------------------------------------------
Alright, I've marked the task as done:
[T] [X] buy books
------------------------------------------------
```
### `Keyword` - unmark

User enters the command `unmark` together with the task number in the list.


Example of usage:

`unmark 1`

Expected outcome:

The task is then unmarked and indicated with "[ ]".

```
expected output
------------------------------------------------
Alright, I've marked the task as not done:
[T] [ ] buy books
------------------------------------------------
```
### `Keyword` - list

User enters the command `list`.


Example of usage:

`list`

Expected outcome:

The task list is then displayed for the user.

```
expected output
------------------------------------------------
Here are your current tasks:
1. [T] [X] buy books
2. [D] [ ] sell books (by: Oct 11 2020 0300 PM)
3. [E] [ ] return books (by: Oct 11 2020 0300 
PM to Nov 12 2020 0100 PM)
------------------------------------------------
```
### `Keyword` - delete

User enters the command `delete` together with the task number in the list.


Example of usage:

`delete 1`

Expected outcome:

The task is then deleted and removed from the list.

```
expected output
------------------------------------------------
Alright, I've removed this task:
[T] [ ] buy books
Current number of tasks: 2
------------------------------------------------
```
### `Keyword` - priority

User enters the command `priority` together with the task number in the list
and the priority level.


Example of usage:

`priority item 1 level 2`

Expected outcome:

The task is given a priority level.

```
expected output
------------------------------------------------
Alright, I've marked this task as priority level 2
[T] [ ] buy books (priority level 2)

------------------------------------------------
```
### `Keyword` - find

User enters the command `find` together with the keyword they want to look 
for.


Example of usage:

`find book`

Expected outcome:

Displays all the tasks that contain the keyword.

```
expected output
------------------------------------------------
Here are the matching tasks in your list:
1. [T] [ ] buy books (priority level 2)
2. [D] [ ] sell books (by: Oct 11 2020 0300 PM)
3. [E] [ ] return books (by: Oct 11 2020 0300 
PM to Nov 12 2020 0100 PM)

------------------------------------------------
```
### `Keyword` - bye

User enters the command `bye`.


Example of usage:

`bye`

Expected outcome:

Displays a good bye message to the user.

```
expected output
------------------------------------------------
Thank You. Please come by again~!

------------------------------------------------
```
### Command Summary

1. `todo`: Creates todo task
2. `deadline`: Creates deadline task
3. `event`: Creates event task
4. `mark`: Marks a task
5. `unmark`: Unmarks a task
6. `list`: Displays tasklist
7. `delete`: Deletes a task
8. `priority`: Set a priority level to a task
9. `find`: Finds all the tasks with the specific keyword
10. `bye`: Displays goodbye message