# ChadBot 
Chadbot application is designed for user to **manage their day-to-day tasks**. It
is optimized for use via Command Line Interface (CLI) and Graphical User Interface (GUI).

- [Quick Start](#quick-start)
- [Features](#features)
  * [Add a Todo Task](#add-a-todo-task)
  * [Add a Deadline](#add-a-deadline)
  * [Add an Event](#add-an-event)
  * [Find Task by Keyword](#find-task-by-keyword)
  * [View Task List](#view-task-list)
  * [Mark Task as Done](#mark-task-as-done)
  * [Mark Task as Undone](#mark-task-as-undone)
  * [Delete a Task](#delete-a-task)
  * [Exiting the Program](#exiting-the-program)
- [Command Summary](#command-summary)

## Quick Start
> **Prerequisites**
> + JDK11
> + Intellij Idea 

## Features 

### Add A Todo Task

Adds a Todo task into the current task list.

Parameter(s):
- Description - Short description of the todo task you wish to add into your task list.

Command Format: `todo <description>`

### Add a Deadline

Adds a deadline task into the current task list.

Parameter(s):
- Description - Short description of the todo task you wish to add into your task list.
- Due Date - The date 

Command Format: `deadline <description> /by <due date>`

### Add an Event

Description of the feature.

### Find Task by Keyword

Description of the feature.

### View Task List

Description of the feature.

### Mark Task as Done

Description of the feature.

### Mark Task as UnDone

Description of the feature.

### Delete a Task

Description of the feature.

### Exiting the Program

Description of the feature.


## Command Summary
| Index |                   Commands                    |                     Command Format                      |                        Example Usage                         | 
|:-----:|:---------------------------------------------:|:-------------------------------------------------------:|:------------------------------------------------------------:|
|   1   |      [Add a Todo Task](#add-a-todo-task)      |                  `todo <description>`                   |                       `todo homework`                        |
|   2   |    [Add a Deadline Task](#add-a-deadline)     |         `deadline <description> /by <due date>`         |           `deadline homework /by 2023-02-17 2359`            |
|   3   |         [Add an Event](#add-an-event)         | `event <description> /from <start date> /to <end date>` | `event internship /from 2023-05-08 0800 /to 2023-07-28 1800` |
|   4   | [Find Task by Keyword](#find-task-by-keyword) |                    `find <keyword>`                     |                        `find CS2103T`                        |
| 5 |       [View Task List](#view-task-list)       |`list`|`list`|
| 6 |    [Mark Task as Done](#mark-task-as-done)    | `mark <index>` | `mark 5`|
| 7 |  [Mark Task as Undone](#mark-task-as-undone)  |`unmark <index>`|`unmark 5`|
|8|        [Delete a Task](#delete-a-task)        |`delete <index>`|`delete 5`|
|9|  [Exiting the Program](#exiting-the-program)  |`bye`|`bye`|


## Usage

### `Keyword` - Describe action

Describe the action and its outcome.

Example of usage: 

`keyword (optional arguments)`

Expected outcome:

Description of the outcome.

```
expected output
```
