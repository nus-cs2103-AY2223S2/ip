# User Guide

## Introduction

Space Lin is a personal assistant that can help you to keep track of any task.
![Ui](Ui.png)

## Features 

### Add task

Add a task to the task list. There are three different types of tasks in total. Each type of type can be added using different command.

### Find task

Find a task in the task list according to certain keywords.

### Delete task

Delete a task from the task list according to the task sequence number.

### Mark task

Mark a task as done or not done yet.

### List task

List the tasks that are currently stored in the task list.

## Usage

Action | Format, Examples
--------|------------------
**Todo** | `todo TASK_NAME` <br> e.g., `todo go home`
**Deadline** | `deadline TASK_NAME /by yyyy-MM-dd [HH:mm]` <br> e.g., `deadline go home /by 2023-02-17 18:00`
**Event** | `event TASK_NAME /from yyyy-MM-dd [HH:mm] /to yyyy-MM-dd [HH:mm]` <br> e.g., `event go home /from 2023-02-17 18:00 /to 2023-02-18`
**Delete** | `delete INDEX` <br> e.g., `delete 3`
**Mark** | `mark INDEX`<br> e.g.,`mark 1`
**Unmark** | `unmark INDEX`<br> e.g.,`unmark 2`
**Find** | `find KEYWORD [MORE_KEYWORDS]`<br> e.g., `find go home`
**List** | `list`
**Bye** | `bye`
