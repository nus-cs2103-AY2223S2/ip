## :chicken: Cluck! User guide

## Table of contents:
- [Quick start](#quick-start)
- [Commands](#commands)
  - [`list`](#listing-all-tasks-list)
  - [Adding tasks:](#adding-tasks)
    - [`todo`](#to-do-todo-taskdescription)
    - [`deadline`](#deadline-deadline-deadlinedescription-by-datetime)
    - [`event`](#event-event-eventdescription-from-startdatetime-to-enddatetime)
  - [Mark tasks as complete](#mark-tasks-as-complete-mark-tasknumber)
  - [Unmark tasks as complete](#unmark-tasks-as-complete-unmark-tasknumber)
  - [Delete tasks](#delete-tasks-delete-tasknumber)
  - [Find tasks](#find-tasks-find-keyword)
  - [Exiting Cluck!](#exit-cluck-bye)

## Quick start
1. Ensure you have Java 11 or above installed in your Computer.
2. Download _Cluck!_ [here](https://github.com/ChickenChiang/ip/)
3. Open a command terminal, cd into the folder you put the jar file in, and use the java -jar addressbook.jar command to run the application.A GUI similar to the below should appear in a few seconds.
4. Type your commands into the command box!

## Commands
### Listing all tasks `list`
- Use this function to list all the tasks in your **_Cluck!_** task list.
### Adding tasks
There are 3 types of tasks that can be added in this version of **_Cluck!_**
#### To do `todo <TASK_DESCRIPTION>`
#### Deadline  `deadline <DEADLINE_DESCRIPTION> /by <DATE_TIME>`
- Ensure that your date time is formatted as such: "dd MMM yy HHmm"
- Example: `deadline Homework /by 23 Mar 25 2359` creates a deadline called "Homework", with the due date being 23rd March 2025 2359 _(24-hour format for time)_
#### Event `event <EVENT_DESCRIPTION> /from <START_DATE_TIME> /to <END_DATE_TIME>`
- Ensure that your date time is formatted as such: "dd MMM yy HHmm"
- Example: `event Dinner with Boss /from 24 Apr 23 2000 /to 24 Apr 23 2230` creates an event called "Dinner with Boss", that starts at 24th April 2023 8pm and ends at 24th Apri 2023 10:30pm.
### Mark tasks as complete `mark <TASK_NUMBER>`
- Marks the task on the list that is oftask number given. To see the task number, use the `list` command.
### Unmark tasks as complete `unmark <TASK_NUMBER>`
- Un-marks the task on the list that is of the task number given. To see the task number, use the `list` command.
### Delete tasks `delete <TASK_NUMBER>`
- Deletes the task on the list at the task number given
### Find tasks `find <KEYWORD>`
- Displays all the tasks containing the keyword given. Note that it is not case-sensitive
### Exit **_Cluck!_** `bye`
- Closes cluck (not yet implemented... coming soon! For now you have to manually close the GUI.)
