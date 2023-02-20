# User Guide

## Introduction
The Duke personal text-based task manager is here to help you keep your tasks organised! 

Simply hand Duke upcoming tasks, and they will help store and manage them! With our innovative text-based command system, keeping track of your tasks has never been faster!

## Quick start
1. Download the latest jar release of Duke [here](https://github.com/szejiancheng/ip/releases)
2. Ensure you have the latest version of java downloaded and have your system PATH variables updated
3. Open your command line to the folder that you downloaded Duke to
4. type the command ```java -jar Duke.jar```
5. the Duke application will open shortly

## Features 

### Tasks

Add tasks to be tracked by Duke. In its current implementation, Duke has three categories of tasks. 

- Todos
  - tasks with no date attributed to them. These tasks are meant to be completed on an as-needed basis.
- Deadlines 
  - tasks with a date attributed to them to finish them by. The date given to the deadline will impact whether this task is flagged when using the ```remind``` command.
- Events
  - tasks that have a date range attributed to them. The ```/from``` date given to Duke will impact whether this task is flagged when using the ```remind``` command.

In Duke, you can freely add and remove these tasks from your task list.

### Change the completion status of tasks

In Duke, you can mark and unmark tasks to indicate whether they are completed or not. 

### View upcoming/previous tasks

Duke offers you the option to list out all your tasks, or to provide a number of days to look ahead to find upcoming tasks due, or to provide keywords to find specific tasks.

## Usage

### `Todo` 

Creates and puts a todo on the task list. Command syntax is `todo <name of todo>` where the name of todo created can include whitespace.

Example of usage: 

`todo cook dinner`

Expected outcome:

Duke prints a completion messsage to the user, together with a string representation of a todo


> Got it, I've added this task
>
> [T][ ] cook dinner


### `Deadline` 

Creates and puts a deadline on the task list. Command syntax is `deadline <name of deadline> /by <YYYY-MM-DD>` where the date following "/by" is the deadline.

Example of usage:

`deadline do something /by 2023-02-20`

Expected outcome:

Duke prints a completion message to the user, together with a string representation of a deadline


> Got it, I've added this task
> 
> [D][ ] do something (by: Feb 20 2023)


### `Event`

Creates and puts an event on the task list. Command syntax is:

`event <name of deadline> /from <YYYY-MM-DD> /to <YYYY-MM-DD>` 

where the dates following "/from"  and "/to" are the start and end dates of the event.

Example of usage:

`event do something that takes time /from 2023-02-20 /to 2023-02-21`

Expected outcome:

Duke prints a completion message to the user, together with a string representation of an event


> Got it, I've added this task
> 
>[E][ ] do something that takes time (from: Feb 20 2023 to: Feb 21 2023)


### `List`

Displays all tasks currently being tracked by Duke to the user. Command syntax is:

`list`

Expected outcome:

Duke displays all currently tracked tasks to the user


> Here are the tasks currently in your list:
>
>1. [D][ ]do something (by: Feb 20 2023)
> 
>2. [E][ ]do something that takes time (from: Feb 20 2023 to: Feb 21 2023)
>
> end of task list. (currently contains 2 tasks)

### `Mark`

Marks the indicated task as done. Command syntax is:

`mark <index of task to mark>`

where the index is the index attributed to the task when command `list` is called.

Example of usage:
`mark 1`

Expected outcome:
Duke prints a completion message to the user after marking the task as done.

> Nice! I've marked this task as done:
> 
> [D][X] do something (by: Feb 20 2023)

### `Unmark`

Marks the indicated task as not done. Command syntax is:

`unmark <index of task to mark as undone>`

where the index is the index attributed to the task when command `list` is called.

Example of usage:
`unmark 1`

Expected outcome:
Duke prints a completion message to the user after marking the task as done.

> Okay, I've marked this task as not done yet:
>
> [D][ ] do something (by: Feb 20 2023)

### `Delete`

Deletes the indicated task from the task list. Command syntax is:

`delete <index of task to delete>`

where the index is the index attributed to the task when command `list` is called.

Example of usage:
`delete 1`

Expected outcome:
Duke prints a completion message to the user after marking the task as done.

> Noted, I've removed this task:
> 
> [D][ ] do something (by: Feb 20 2023)

### `Find`

Searches the task list for tasks of names that match the keyword given. Command syntax is:

`find <keyword>`

where the keyword is characters that are given to match task names.

Example of usage: 
`find as`

Expected outcome:

Duke prints all tasks with the name matching the keyword given to the user.

>These are the 2 tasks I found:
> 
> 1. [T][ ] task 1
> 2. [D][ ] task 2 (by: Feb 20 2023)

if keyword does not match any tasks currently being tracked:

>No tasks found!

### `Remind`

Prints all upcoming tasks to the user given the number of days to look ahead. Command syntax is:

`remind <number of days>`

where number of days is the days ahead you want to look ahead to preempt tasks.

Example of usage: 
`remind 3`

Expected outcome:

Duke looks ahead `3` days and prints all upcoming events starting in, as well as all deadlines due by, the specified number of days. Todos are always printed when the `remind` command is invoked. 

>It looks like you have 2 tasks upcoming:
> 
> [T][ ] task 1
> 
> [D][ ] task 2 (by: Feb 20 2023)

### `help`

Prints a help message detailing all currently implemented commands. Command syntax is :

`help`

Expected outcome:

> My objective is to help you keep your tasks and deadlines in order. The commands I'm currently familiar with are:
> 
> todo [todo name]\
> deadline [deadline name] /by [YYYY-MM-DD]\
> event [event name] /from [YYYY-MM-DD] /to [YYYY-MM-DD]\
> delete [index of task to delete]\
> find [keyword to search for]\
> list\
> mark [index of task to mark as done]\
> unmark [index of task to mark as not done]\
> remind [number of days to preempt tasks]\ 
> 
> type a command without any parameters to learn more about the command!

