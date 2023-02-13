# User Guide

**Sebastian** is a **desktop app for managing tasks, optimised for use via a Command Line Interface**(CLI) while still having the benefit of a Graphical User Interface(GUI).

# Quick Start

1. Ensure you have `Java 11` or above installed in your computer.

2. Download the latest `Sebastian.jar`.

3. Copy the file to the folder you want to use as the *home folder* for your Sebastian app.

4. Open a command terminal, `cd` into the folder you put the jar file in, and use the `java -jar sebastian.jar` command to run the application. A GUI similar to the below should appear in a few seconds. Note the app contains some sample data.

   <img src="http://Emrysil.github.io/ip/Ui.png" style="zoom:50%;" />

# Features

## List all tasks

- Syntax: list

## Add a todo

- Syntax: todo [a todo task]
- Example: todo grind leetcode

## Add a deadline

- Syntax: deadline [a deadline] /by yyyy-MM-dd HHmm
- Example: deadline cs2102 assignment /by 2023-03-04 2359

## Add an event

- Syntax: event [an event] /from yyyy-MM-dd HHmm /to yyyy-MM-dd HHmm
- Example: event life science symposium /from 2023-02-15 1600 /to 2023-02-15 1800

## Mark a task as done

- Syntax: mark [task index]
- Example: mark 2
- Explanation: `list` followed by `mark 2` marks the 2nd task in the task list as done.

## Mark a task as not done

- Syntax: unmark [task index]
- Example: unmark 2
- Explanation: `list` followed by `unmark 2` marks the 2nd task in the task list as not done.

## Delete a task

- Syntax: delete [task index]
- Example: delete 2
- Explanation: `list` followed by `delete 2` deletes the 2nd person in the task list.

## Retrieve tasks on a specific date

- Syntax: get yyyy-MM-dd
- Example: get 2023-02-15
- Explanation: this retrieves all the tasks happening on 2023-02-15

## Find a task with specific keyword

- Syntax: find [keyword]
- Example: find assignment
- Explanation: this retrieves all tasks containing the "assignment" keyword

## Update details of a task

- Syntax for updating a **todo**: 
  - Update [task index] /desc [new description] 
- Syntax for updating a **deadline**
  - Update [task index] /desc [new description] /by [new deadline due date]
  - You can use 1 to 2 flags, the relative order should not be changed.
- Syntax for updating an **event**:
  - Update [task index] /desc [new description] /from [new event start time] /to [new end time for event]
  - You can use 1 to 3 flags, the relative order should not be changed.
- Example: 
  - update 2 /desc read Harry Potter and Half-blood Prince
  - update 3 /by 2023-02-29
  - update 4 /from 2023-01-22 1200
