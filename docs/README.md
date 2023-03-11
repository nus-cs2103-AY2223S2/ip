# User Guide for TaskGenie
TaskGenie frees your mind of having to remember things you need to do.  
It is optimized for use via Command Line Interface (CLI) while still having the benefits of a Graphical User Interface (GUI).  
If you can type fast, TaskGenie can help keeps track of your tasks faster than traditional applications.  

- [Quick Start](#quick-start)
- [Features](#features)
  * [Add a ToDo Task](#add-a-todo-task)
  * [Add a Deadline](#add-a-deadline)
  * [Add an Event](#add-an-event)
  * [Edit a task](#edit-a-task)
  * [Find Task by Keyword](#find-task-by-keyword)
  * [View Task List](#view-task-list)
  * [Mark Task as Done](#mark-task-as-done)
  * [Mark Task as Undone](#mark-task-as-undone)
  * [Delete a Task](#delete-a-task)
  * [Exiting the Program](#exiting-the-program)
  * [Saving the data](#saving-the-data)
- [FAQ](#faq)
- [Command Summary](#command-summary)

## Quick Start

1. Ensure you have Java `11` or above installed in your Computer.
2. Download the latest `TaskGenie.jar` from [here](https://github.com/engenhui1999/ip/releases/tag/v1.0).
3. Copy the file to the folder you want to use as the *home folder* for your TaskGenie.
4. Open a command terminal, `cd` into the folder you put the `jar` file in, and use the `java -jar TaskGenie.jar` command to run the application. A GUI similar to the below should appear in a few seconds. Note how the app contains some sample data. ![alt text](https://engenhui1999.github.io/ip/Ui.png?raw=true)
5. Refer to the Features below for details of each command and how to use TaskGenie.

## Features 

### Add A ToDo Task

Creates a ToDo task.  
Format: `todo DESCRIPTION`  
Examples:
- `todo Buy Pencil` Creates a ToDo task to buy pencil.

### Add a Deadline

Creates a Deadline task.  
Format: `deadline DESCRIPTION /by DATE`  
Examples:
- `deadline Pay Tax /by 2023-03-11 2359` Creates a Deadline task to pay for tax before 11 March 2023 2359H.

### Add an Event

Creates a Event task.  
Format: `event DESCRIPTION /from DATE /to DATE`  
Examples:
- `event Career Fair /from 2023-03-11 0000 /to 2023-03-13 2359` Creates a Event task for a Career Fair that goes from 11 March 2023 0000H to 13 March 2023 2359H.

### Find Task by Keyword

Finds a task that contains the keyword.  
Format: `find DESCRIPTION`  
Examples:
- `find book` Finds all tasks that has the keyword *book* in its description.

### View Task List

Views the current tasks you have.  
Format: `list`  
Examples:
- `list` Shows all the tasks that you have.

### Edit a task

Edits a task based on the number in the task list.  
Format: `update NUMBER /ATTRIBUTE DESCRIPTION`  
Examples:
- `update 1 /description Buy Eraser` Edits the first task on the task list with the new description to buy eraser.
- `update 2 /by 2023-03-11 2359` Edits the second task on the task list with the new deadline set at 11 March 2023 2359H.
- `update 3 /from 2023-03-11 2359` Edits the third task on the task list with the new starting date of the event set at 11 March 2023 2359H.
- `update 3 /to 2023-03-11 2359` Edits the second task on the task list with the new ending date of the event set at 11 March 2023 2359H.

### Mark Task as Done

Marks task based on the number in the task list as done.  
Format: `mark NUMBER`  
Examples:
- `mark 2` Marks the second task on the task list as done.

### Mark Task as UnDone

Unmarks task based on the number in the task list as not done.  
Format: `unmark NUMBER`  
Examples:
- `unmark 2` Unmarks the second task on the task list as not done.

### Delete a Task

Deletes task based on the number in the task list.  
Format: `delete NUMBER`  
Examples:
- `delete 2` Deletes the second task on the task list.

### Exit the Program

Exit the Program and save your tasks.  
Format: `bye`  
Examples:
- `bye` Exits the program and save your tasks.

### Saving the data

TaskGenie data are saved in the hard disk automatically exiting the program. There is no need to save manually.

## FAQ

**Q**: How do I transfer my data to another Computer?  
**A**: Install the app in the other computer and overwrite the empty data file it creates with the file that contains the data of your previous TaskGenie home folder.

## Command Summary
|                   Commands                    |                     Command Format                      |                        Example Usage                          | 
|:---------------------------------------------:|:-------------------------------------------------------:|:-------------------------------------------------------------:|
|      [Add a ToDo Task](#add-a-todo-task)      |                   `todo DESCRIPTION`                    |                      `todo Buy Pencil`                        |
|  [Add a Deadline Task](#add-a-deadline)       |             `deadline DESCRIPTION /by DATE`             |            `deadline Pay Tax /by 2023-03-11 2359`             |
|         [Add an Event](#add-an-event)         |         `event DESCRIPTION /from DATE /to DATE`         | `event Career Fair /from 2023-03-11 0000 /to 2023-03-13 2359` |
| [Find Task by Keyword](#find-task-by-keyword) |                   `find DESCRIPTION`                    |                         `find book`                           |
|       [View Task List](#view-task-list)       |                         `list`                          |                            `list`                             |
|          [Edit a Task](#edit-a-task)          |          `update NUMBER /ATTRIBUTE DESCRIPTION`         |               `update 1 /description Buy Eraser`              |
|    [Mark Task as Done](#mark-task-as-done)    |                      `mark NUMBER`                      |                            `mark 2`                           |
|  [Mark Task as Undone](#mark-task-as-undone)  |                     `unmark NUMBER`                     |                           `unmark 1`                          |
|        [Delete a Task](#delete-a-task)        |                     `delete NUMBER`                     |                           `delete 3`                          |
|  [Exiting the Program](#exiting-the-program)  |                          `bye`                          |                             `bye`                             |

[Back to top](#user-guide-for-taskgenie)