# User Guide

---

# User Guide to Henz!

![Ui](./Ui.png)

-   [Introduction](#introduction)
-   [Quick Start](#quick-start)
    -   [Run the project in terminal](#run-the-project-in-terminal)
-   [Command Summary](#command-summary)
-   [Features](#features)
    -   [Adds Todo Tasks](#adds-todo-tasks)
    -   [Adds Deadline Tasks](#adds-deadline-tasks)
    -   [Adds Event Tasks](#adds-event-tasks)
    -   [Edit Tasks](#edit-tasks)
    -   [Lists All Tasks](#lists-all-tasks)
    -   [Mark Tasks as Done](#mark-tasks-as-done)
    -   [Unmark Tasks](#unmark-tasks)
    -   [Delete Tasks](#delete-tasks)
    -   [Find Tasks](#find-tasks)
    -   [Bye](#bye)
-   [Miscellaneous](#miscellaneous)
    -   [Acceptable Formats for `<DateTime>`](#acceptable-formats-for---datetime--)
-   [Useful Resources](#useful-resources)

## Introduction

The Henz Application is a chatbot designed to assist users with tasks management, made by [Justin Leng](https://www.linkedin.com/in/justin-leng-a31390151/) under the module CS2103T 2022/23 Sem 2.

This project, Henz, is based off [Project Duke](https://github.com/nus-cs2103-AY2223S2/ip), a project that helps developers gradually learn and improve their software engineering skills.

This is a simple user guide for the usage of Henz Application.

## Quick Start

### Run the project in terminal

1. Download the latest **henz.jar** file from [here]()
2. Create an empty folder and place **henz.jar** in it.
3. Open your terminal and navigate your terminal directory to the folder that contains your **henz.jar** file
4. To launch the Henz Application, you have two options:
    1. Double-click the "henz.jar" file on your device.
    2. Open the command prompt and enter the command:
        ```
        java -jar henz.jar
        ```
5. You should expect the Graphical User Interface (GUI) to appear.

## Command Summary

| Index |            Command to Use            |                        Format                         |                          examples                          |
| :---: | :----------------------------------: | :---------------------------------------------------: | :--------------------------------------------------------: |
|   1   |         Adds a Deadline task         |        `deadline <Description> /by <DateTime>`        |          `deadline homework /by 2023-02-15 1500`           |
|   2   |          Adds an Event task          | `event <Description> /from <DateTime> /to <DateTime>` | `event homework /from 2023-02-15 1500 /to 2023-02-15 2359` |
|   3   |           Adds a Todo task           |                 `todo <Description>`                  |                `todo lunch with prof henz`                 |
|   4   |            Deletes a Task            |                 `delete <TaskIndex>`                  |                         `delete 1`                         |
|   5   | Finds Tasks according to description |                `find <Description...>`                |     `find homework`, `find homework meeting exercise`      |
|   7   |         Lists out all Tasks          |                        `list`                         |                           `list`                           |
|   8   |         Marks a Task as done         |                  `mark <TaskIndex>`                   |                          `mark 1`                          |
|  12   |            Unmarks a Task            |                 `unmark <TaskIndex>`                  |                         `unmark 1`                         |
|  13   |   Edits the description of a Task    |           `edit <TaskIndex> <Description>`            |                  `edit 1 Do Assignment 1`                  |
|  15   |           Ending a session           |                         `bye`                         |                           `bye`                            |

## Features

### Adds Todo Tasks

The `todo <Description>` command allows you to add a Todo task to your task list.

You should provide the following:

1. Description

For example, running the command, `todo lunch with prof henz`, would add a Todo task with the description "lunch with prof henz" to your task list and return the following output:

> Got it. I've added this task:
> \[T][ ] lunch with prof henz
> Now you have 1 tasks in the list.

### Adds Deadline Tasks

The `deadline <Description> /by <DateTime>` command allows you to add a Deadline task to your task list.

You should provide the following:

1. Description
2. By (format: yyyy-MM-dd HHmm)

For example, running the command, `deadline lunch with prof henz /by 2023-02-15 1500`, would add the task "lunch with prof henz" to your task list with a deadline of February 15th, 2023 at 3:00pm and return the following output:

> Got it. I've added this task:
> \[D][ ] lunch with prof henz ( by: Feb 15 2023, 3:00pm )
> Now you have 1 tasks in the list.

### Adds Event Tasks

The `event <Description> /from <DateTime> /to <DateTime>` command allows you to add an Event task to your task list.

You should provide the following:

1. Description
2. from (format: yyyy-MM-dd HHmm)
3. to (format: yyyy-MM-dd HHmm)

For example, running`event party with prof henz /from 2023-02-15 1500 /to 2023-02-16 0400`, would add the task "party with prof henz" as an event to your task list with a start time of February 15th, 2023 at 3:00pm and end time of February 16th, 2023 at 4:00am and return the following output

> Got it. I've added this task:
> \[E][ ] party with prof henz ( from: Feb 15 2023, 3:00pm to: Feb 16 2023, 4:00am )
> Now you have 1 tasks in the list.

### Edit Tasks

The `edit <TaskIndex> <Description>` command allows you to edit the description of a task in your task list.

You should provide the following:

1. Task index
2. Description

For example, running the command, `edit 1 yum cha with prof hez`, would edit the task with index 1 to be "yum cha with prof henz" and return the following output:

> Nice! I've editd the description of this task:
> \[D][ ] Yum cha with prof henz ( by: Feb 15 2023 3:00pm )

### Lists All Tasks

The `list` command allows you to view all tasks in your task list.

For example, running the command `list` will show the content of the list return the following output:

> 1. \[D][ ] yum cha with prof henz ( by: Feb 15 2023, 3:00pm )
> 2. \[E][ ] party with prof henz ( from: Feb 15 2023, 3:00pm to: Feb 16 2023, 4:00am )
> 3. \[T][X] grocery shopping

### Mark Tasks as Done

The `mark <TaskIndex>` command allows you to mark a task as done in your task list.

You should provide the following:

1. Task index

For example, running the command, `mark 1`, would mark the task "yum cha with prof henz" as done and return the following output:

> Nice! I've marked this task as done:
> \[D][X] yum cha with prof henz ( by: Feb 15 2023, 3:00pm )

### Unmark Tasks

The `unmark <TaskIndex>` command allows you to unmark a task that you previously marked as done.

You should provide the following:

1. Task index

For example: running the command, `unmark 1`, would unmark the task previously marked as done at index 1 and return the following output:

> Ok, I've marked this task as not done yet:
> \[D][ ] yum cha with prof henz ( by: Feb 15 2023, 3:00pm )

### Delete Tasks

The `delete <TaskIndex>` command allows you to delete a task from your task list.

You should provide the following:

1. Task index

For example: running the command, `delete 1`, would unmark the task previously marked as done at index 1 and return the following output:

> Ok, I've removed this task:
> \[D][ ] yum cha with prof henz ( by: Feb 15 2023, 3:00pm )
> Now you have 0 tasks in the list.

### Find Tasks

The `find <Description...>` command allows you to find tasks in your task list based on their descriptions.

For example, running the command `find prof henz`, will find all the task with description that matches or partially matches the word "find prof henz" and return the following output:

> Here are the tasks in your list:
>
> 1. \[D][ ] yum cha with prof henz ( by: Feb 15 2023, 3:00pm )
> 2. \[E][ ] party with prof henz ( from: Feb 15 2023, 3:00pm to: Feb 16 2023, 4:00am )

### Bye

The `bye` command allows you to exit the task list application.

## Miscellaneous

### Acceptable Formats for `<DateTime>`

-   “yyyy-MM-dd HHmm”, e.g. `2022-08-10 13:30`

**Caution:** Avoid from modifying the program data in the data file.

## Useful Resources

1. [CheckStyle Tutorial](https://se-education.org/guides/tutorials/checkstyle.html)
2. [Text UI Testing Tutorial](https://se-education.org/guides/tutorials/textUiTesting.html)
3. [Working with Jar files Tutorial](https://se-education.org/guides/tutorials/jar.html)
4. [Gradle Tutorial](https://se-education.org/guides/tutorials/gradle.html)
5. [JavaFX Tutorial](https://se-education.org/guides/tutorials/javaFx.html)
