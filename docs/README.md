# User Guide

Welcome to Duke! Your preferred task management platform.

Duke supports the storing of tasks with/without deadlines, as well as events that are time-sensitive!

## Contents
* Quick Start
* Features
  * Add task
    * ToDo
    * Deadline
    * Event
  * List tasks
  * Mark task
  * Delete task
  * Find task
* Exiting the application
* FAQ
* Command Summary

## Quick Start
* Make sure that you have Java 11 or above installed on your computer. 
* Download the latest jar file (duke-v0.2.jar) from our Github Releases ([link here](https://github.com/praveenkrishna0512/ip/releases))
* Place the jar file into a new folder. This folder will be used as the home folder for Duke. 
* Open a command terminal within the home folder.
  * The option to do so should be within the context menu that pops up when you right-click within the folder.
* Enter `java -jar duke-v0.2.jar` in the command terminal.
  * You should notice the GUI of the application pop up.
* You are ready to use Duke!
  * Refer to the Features section below for details on each command!

## Features
Below, you can find the description and usage of each command within Duke.

### `Add Task` - Adds a task to the task list
There are 3 types of tasks within Duke; namely ToDo, Deadline, and Event.
Thus, there are 3 different commands to add these 3 types of tasks.
We will now cover how to add each of these types of tasks.

#### `ToDo` - Adds a ToDo task to the task list
Command: `todo <name of task>` or `t <name of task>`

A ToDo task has no time restrictions.
The command above adds a ToDo task to your task list.

Example usage: `todo CS2103T assignment`

Expected output:

A response from Duke will acknowledge the addition of the task.

___<INSERT IMAGE>___

#### `Deadline` - Adds a Deadline task to the task list
Command: `deadline <name of task> /by <deadline>` or `d <name of task> /by <deadline>`

A Deadline task has a deadline. 
The command above adds a Deadline task to your task list.

Remember to use the following format for the deadline field: ***yyyy-MM-dd HH:mm***

Example usage: `deadline CS2103T assignment /by 2023-02-16 23:59`

Expected output:

A response from Duke will acknowledge the addition of the task.

___<INSERT IMAGE>___

#### `Event` - Adds an Event task to the task list
Command: `event <name of task> /from <startDateTime> /to <endDateTime>`
or `e <name of task> /from <startDateTime> /to <endDateTime>`

An Event task has a start date and time, as well as an end date and time.
The command above adds an Event task to your task list.

Remember to use the following format for the startDateTime and endDateTime field: ***yyyy-MM-dd HH:mm***

Example usage: `event CS2103T assignment /from 2023-02-16 22:00 /to 2023-02-16 23:59`

Expected output:

A response from Duke will acknowledge the addition of the task.

___<INSERT IMAGE>___

### Feature-XYZ

Description of the feature.

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

