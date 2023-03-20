# **Cluck!** User guide

## Table of contents:
- [Quick start](#quick-start)
- [Commands](#commands)
  - [View all tasks](#view-all-tasks-list)
  - [Add task:](#adding-tasks)
    - [To-do](#to-do-todo-taskdescription)
    - [Deadline](#deadline-deadline-deadlinedescription-by-datetime)
    - [Event](#event-event-eventdescription-from-startdatetime-to-enddatetime)
  - [Mark task](#mark-task-mark-tasknumber)
  - [Un-mark task](#un-mark-task-unmark-tasknumber)
  - [Delete task](#delete-task-delete-tasknumber)
  - [Find task](#find-task-find-keyword)
  - [Exiting Cluck!](#exit-cluck-bye)
- [Date Time formatting](#date-time-formatting)

## Quick start
1. Ensure you have Java 11 or above installed in your Computer.
2. Download _Cluck!_ [here.](https://github.com/ChickenChiang/ip/)
3. Open a command terminal, cd into the folder you put the jar file in, and use the java -jar addressbook.jar command to run the application.A GUI similar to the below should appear in a few seconds.
4. Type your commands into the command box!
___
## Commands
  - The input format of the commands are in `this format` beside the header describing each command.
___
### View all tasks `list`
  - Use this function to list all the tasks in your **_Cluck!_** task list.
___
### Adding tasks
There are 3 types of tasks that can be added in this version of **_Cluck!_**: 
___

#### To-do `todo <TASK_DESCRIPTION>`
   - Creates a simple **to-do** task, with a description an a _marked_ or _un-marked_ check box.
___
#### Deadline  `deadline <DEADLINE_DESCRIPTION> /by <DATE_TIME>`
  - Creates a task with a **deadline** at the _given date_. 
  - Example: `deadline Homework /by 23 Mar 25 2359` creates a deadline called "**Homework**", with a due date at **23rd March 2025 11:59 pm** _(24-hour format for time)_
  - _See [date time formatting](#date-time-formatting) for the right input format_.
___
#### Event `event <EVENT_DESCRIPTION> /from <START_DATE_TIME> /to <END_DATE_TIME>`

  - Creats an **event** with a _start date_ and _end date_. 

  - Example: `event Dinner with Boss /from 24 Apr 23 2000 /to 24 Apr 23 2230` creates an **event** called "**Dinner with Boss**", that starts at **24th April 2023 8 pm** and ends at **24th April 2023 10:30 pm**.
- _See [date time formatting](#date-time-formatting) for the right input format_.
___
### Mark task `mark <TASK_NUMBER>`

  - Marks the task in tasklist at the index given. 
  - _To see the task number, use the [list](#view-all-tasks-list) command._
___
### Un-mark task `unmark <TASK_NUMBER>`

  - Un-marks the task in tasklist at the index given.
  - _To see the task number, use the [list](#view-all-tasks-list) command._
___
### Delete task `delete <TASK_NUMBER>`

  - Deletes in tasklist at the index given. 
  - _To see the task number, use the [list](#view-all-tasks-list) command._
___
### Find task `find <KEYWORD>`

  - Displays all the tasks containing the keyword given. Note that it is not case-sensitive
___
### Exit **_Cluck!_** `bye`
  
  - Closes the Cluck! program.

## Date-time formatting

  - Date-time inputs should have the general format of `dd MMM yyyy HHmm`, where:
    - `dd` is the day of the month in numerical form; _e.g. `dd` for 3rd May would be `03`_, for 24th it would be `24`
    - `MMM` is the first 3 letters of the month; _e.g, August in `MMM` format is `Aug` (case-insensitive)_
    - `yy` is the last two digits of the year; _e.g. 2034 in `yy` format is `34`_
    - `HHmm` is the time in 24-hour format
  - Additionally, users can use the numerical format of the month instead of the shorted string format
  - Users can also omit the first two digits of the year and the minutes of the time
  
Sample date time inputs: Both `03 Mar 2023 1200` and `03 03 23 12` are equivalent to **_3rd March 2023 12:00pm_**
