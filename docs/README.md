# Chagee User Guide

**Chagee** is **a simple to use task managing chat app**. You can tell **Chagee** to execute **commands** via **Command Line Interface** (CLI) or a **Graphical User Interface** (GUI).

## Table of Content

1. [Quick start](#quick-start)
1. [Features/Commands](#featurescommands)
   - [Types of tasks](#types-of-tasks)
   - [Data Storage](#data-storage)
   - [List all tasks: **list**](#list-all-tasks-list)
   - [Create a new **TODO**: **todo**](#create-a-new-todo-todo)
   - [Create a new **DEADLINE**: **deadline**](#create-a-new-deadline-deadline)
   - [Create a new **EVENT**: **event**](#create-a-new-event-event)
   - [Mark a task as complete: **mark**](#mark-a-task-as-complete-mark)
   - [Mark a task as incomplete: **unmark**](#mark-a-task-as-incomplete-unmark)
   - [Delete a task: **delete**](#delete-a-task-delete)
   - [Find tasks with a keyword: **find**](#find-tasks-with-a-keyword-find)
   - [View ongoing task on a given date: **view_schedule**](#view-ongoing-task-on-a-given-date-view_schedule)
   - [Exit the application: **bye**](#exit-the-application-bye)
1. [Acknowledgements](#acknowledgements)

## Quick start

1. Check that your Java is at version 11 by running `java --version` on your terminal. If it is not, download it [here](https://www.oracle.com/sg/java/technologies/downloads/).
1. Download the lastest version of **Chagee** [here](https://github.com/bipbipboopboop/ip/releases).
1. Using your desired terminal, `cd` into the folder where you had download **Chagee** then use `java -jar chagee.jar` to run it.
1. Use the various commands(#featurescommands) in the input field and press **Enter** or click on **Send** to let **Chagee** help you shedule your tasks!.

## Features/Commands

### Types of tasks

There are 3 types of tasks in **Chagee**: **Todo**, **Deadline** and **Event**.

- All task require `description`.
- **DEADLINE** requires an additional date as its `deadline`.
- **EVENT** requires 2 additional dates. The first one as _starting date_ and the second one _ending date_.

### Data Storage

**Chagee** saves your progess in the hard disk automatically, every time. Hence there is no need manually save the data .

- Data is stored at `src/main/java/data/Storage.ser`.

### List all tasks: **list**

Usage:<br>`list`

List down all your tasks, complete or incomplete along with its index, which is used to indentify a task in certain commands, such as `mark <index>`, `unmark <index>`, `delete <index>`.

### Create a new **TODO**: **todo**

Usage:<br>`todo <description>`

Creates a new **TODO** task to your list.

- `description` can't be empty.

### Create a new **DEADLINE**: **deadline**

Usage:<br>`deadline <description> /by <deadline_date>`

Creates a new **DEADLINE** task to your list.

- `description` can't be empty.
- `deadline_date` cannot be empty.
- `deadline_date` has to follow the `yyyy-MM-dd` format, e.g(`2023-03-14`).

### Create a new **EVENT**: **event**

Usage:<br>`event <description> /from <starting_date> /to <ending_date>`

Creates a new **EVENT** tasks to your list.

- `description` can't be empty.
- `starting_date` and `ending_date` cannot be empty.
- `starting_date` and `ending_date` have to follow the `yyyy-MM-dd` format, e.g(`2023-03-14`).

### Mark a task as complete: **mark**

Usage:<br>`mark <index>`

Marks a `task` of `index` as completed. This command can be used multiple times with `unmark <index>` to toggle the status of the task.

- You can use `list` to find the indices of the tasks.
- `index` must be a `positive` integer.

### Mark a task as incomplete: **unmark**

Usage:<br>`unmark <index>`

Marks a `task` of `index` as incomplete. This command can be used multiple times with `mark <index>` to toggle the status of the task.

- You can use `list` to find the indices of the tasks.
- `index` must be a `positive` integer.

### Delete a task: **delete**

Usage:<br>`delete <index>`

Deletes a `task` of `index`.

- You can use `list` to find the indices of the tasks.
- `index` must be a `positive` integer.

### Find tasks with a keyword: **find**

Usage:<br>`find <keyword>`

Finds and lists all tasks that has `<keyword>` as part of its `description`.

### View ongoing task on a given date: **view_schedule**

Usage: <br> `view_schedule <date>`

Finds and lists all tasks that are ongoing/incomplete on `<date>`.

### Exit the application: **bye**

Usage:<br>`bye`

Exits the application.

## Acknowledgements

Chagee is built with Java 11 and the GUI with [JavaFX](https://openjfx.io/).
