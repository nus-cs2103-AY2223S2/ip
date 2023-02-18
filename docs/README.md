# User Guide

**Duke** is **an application for managing your upcoming tasks**. You can use application with either a **Command Line Interface** (CLI) or a **Graphical User Interface** (GUI), by typing commands into the application.

## Table of Content

1. [Quick start](#quick-start)
1. [Features](#features)
    - [Automatically save data](#automatically-save-data)
    - [Manage 3 kinds of tasks](#manage-3-kinds-of-tasks)
    - [Show help: **help**](#show-help-help)
    - [Show all tasks: **list**](#show-all-tasks-list)
    - [Add a new **TODO** task: **todo**](#add-a-new-todo-task-todo)
    - [Add a new **DEADLINE** task: **deadline**](#add-a-new-deadline-task-deadline)
    - [Add a new **EVENT** task: **event**](#add-a-new-event-task-event)
    - [Mark a task as done: **mark**](#mark-a-task-as-done-mark)
    - [Mark a task as not done: **unmark**](#mark-a-task-as-not-done-unmark)
    - [Delete a task: **delete**](#delete-a-task-delete)
    - [Delete all tasks: **clear**](#delete-all-tasks-clear)
    - [Find tasks with a keyword: **find**](#find-tasks-with-a-keyword-find)
    - [Sort and show all deadlines: **sort-deadlines**](#sort-and-show-all-deadlines-sort-deadlines)
    - [Edit a task's description: **set-description**](#edit-a-tasks-description-set-description)
    - [Exit the application: **bye**](#exit-the-application-bye)
1. [Acknowledgements](#acknowledgements)

## Quick start

1. Ensure you have **Java 11** or above installed in your computer. You can check by opening a command terminal, and run `java --version`. If **Java** is not installed, or the version is **10** or below, you can install the lastest **Java** version [here](https://www.oracle.com/sg/java/technologies/downloads/).
1. Download the lastest version of **Duke** [here](https://github.com/VietAnh1010/ip/releases).
1. Copy the downloaded file to the folder you want to use as the _home folder_ for the application.
1. Open a command terminal, `cd` into the folder you put the file in, and run `java -jar Duke.jar`. A **GUI** simmlar to below should appear in a few seconds.<br>If you want to use the **CLI** instead, you can run `java -jar Duke.jar cli`.
1. Start typing commands in the command box and press **Enter** to execute it. Type `help` for more details about the commands that you can use in the application.

## Features

### Notes about the command format

- Arguments in square brackets are optional. For example, `help [command]` can be used as `help` or `help list`.
- Arguments in angle brackets are compulsory. For example, `todo <description>` must be used as `todo watch football`.
- In `EEE` day format, the first character is an uppercase character, the rest are lowercase characters. For example, `Mon` follows `EEE` format, but `mon` does not. However, some commands will automatically convert your input to the correct case - you can use `help` command, or read this user guide for the exact details.

### Automatically save data

**Duke** data are saved in the hard disk automatically after you exit the application. There is no need to save the data manually.

- If you are using the **CLI** version, **do not** interupt the application using **Ctrl+C**.
- Saved data is stored at `<home-folder>/.duke/tasklist.ser`, where `home-foler` is the home folder of the application.
- Data will not be saved if your application exit abnormally.

### Manage 3 kinds of tasks

There are 3 kinds of tasks in **Duke**: **TODO**, **DEADLINE** and **EVENT**. All tasks store _descriptions_ about them - these _descriptions_ are specified by you and can be modified when needed.

- A **DEADLINE** task stores an additional date as its _deadline_.
- An **EVENT** tasks stores 2 additional dates. The first one is its _start time_, and the second one is its _end time_.

### Show help: **help**

Usage:<br>`help [command]`

Display the list of commands supported by **Duke**. Include `command` for more details about a particular command.

### Show all tasks: **list**

Usage:<br>`list`

Show all tasks in your list. Tasks will be displyed with their indicies in the list - you can use these indicies with other commands to manipulate the tasks.

### Add a new **TODO** task: **todo**

Usage:<br>`todo <description>`

Add a new **TODO** task to your list.

- `description` cannot be empty. Also, leading and trailing whitespaces will be removed.

### Add a new **DEADLINE** task: **deadline**

Usage:<br>`deadline <description> --by <deadline>`

Add a new **DEADLINE** task to your list.

- `description` cannot be empty. Also, leading and trailing whitespaces will be removed.
- `deadline` is either: a date with `yyyy-MM-dd` format (`2023-01-01`), or a day of week with `EEE` format (`Mon`/`Tue`).
- If you supply a day of week as argument, the nearest day of week in the future will be used. Also, your input will be converted to the correct format automatically, for example, `mon` will be converted to `Mon`.

### Add a new **EVENT** task: **event**

Usage:<br>`event <description> --from <start-time> --to <end-time>`

Add a new **EVENT** tasks to your list.

- `description` can contain whitespaces, however, leading and trailing whitespaces will be removed.
- `start-time` and `end-time` are either: dates with `yyyy-MM-dd` format (`2023-01-01`), or days of week with `EEE` format (`Mon`/`Tue`).
- If you supply a day of week as argument, the nearest day of week in the future will be used. Also, your input will be converted to the correct format automatically, for example, `mon` will be converted to `Mon`.

### Mark a task as done: **mark**

Usage:<br>`mark <index>`

Mark a task at the given index as done. Completed tasks are checked with an `X`.

- You can use `list` to find the exact indicies of the tasks.
- `index` must be a number that is greater than `0`.

### Mark a task as not done: **unmark**

Usage:<br>`unmark <index>`

Mark a task at the given index as not done yet. Use this command if you mistakenly marked a task as done.

- You can use `list` to find the exact indicies of the tasks.
- `index` must be a number that is greater than `0`.

### Delete a task: **delete**

Usage:<br>`delete <index>`

Delete a task at the given index.

- You can use `list` to find the exact indicies of the tasks in your list.
- `index` must be a number that is greater than `0`.

### Delete all tasks: **clear**

Usage:<br>`clear`

Delete all tasks in your list.

### Find tasks with a keyword: **find**

Usage:<br>`find <keyword>`

Find and list all tasks, whose descriptions contain the given keyword.

### Sort and show all deadlines: **sort-deadlines**

Usage:<br>`sort-deadlines`

Sort and show all **DEADLINE** tasks in your list.

### Edit a task's description: **set-description**

Usage:<br>`set-description <index> <description>`

Change the description of a particular task at the given index.

- You can use `list` to find the exact indicies of the tasks in your list.
- `index` must be a number that is greater than `0`.
- `description` cannot be empty. Also, leading and trailing whitespaces will be removed.

### Exit the application: **bye**

Usage:<br>`bye`

Exit the application.

## Acknowledgements

Duke is built with Java 11. The GUI of Duke is developed with [JavaFX](https://openjfx.io/).
