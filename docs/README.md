# User Guide to  Duke 
<h2 align="center"> Your Personal Chatbot Assistant </h2>

<p align="center">
<img width="500" src="./images/start.png" alt="main view of application">
</p>

- [Introduction](#introduction)
- [Quick Start](#quick-start)
- [Command Summary](#command-summary)
- [Features](#features)
    * [Add Todo Tasks](#adds-todo-tasks)
    * [Add Deadline Tasks](#adds-deadline-tasks)
    * [Add Event Tasks](#adds-event-tasks)
    * [List All Tasks](#lists-all-tasks)
    * [List All Events](#lists-all-tasks)
    * [List All Todos](#lists-all-tasks)
    * [Mark Task](#mark-tasks-as-done)
    * [Unmark Task](#unmark-tasks)
    * [Delete Task](#delete-tasks) 
    * [Find Task](#find-tasks)
    * [Undo command](#undo-command)
    * [Checkout version](#checkout-version)
    * [Bye](#bye)
- [Useful Resources](#useful-resources)

## Introduction
Hello! I am a chatbot designed to help you keep track of your tasks and manage your projects. 
With me, you can create and organize to-do lists, set deadlines, and assign tasks to team members. 
I also have version control capabilities, which means you can keep track of changes and updates to your projects, 
ensuring that everyone is on the same page. Whether you're working on a personal project or collaborating with a team, 
I am here to make task management simple and efficient. Let's get started!

## Quick Start
1. Ensure you have Java 11 or above installed in your Computer.
2. You can check whether Java 11 is installed by opening your terminal window and running the command `java -version`
3. If Java 11 is not installed, please download Java 11 from [Oracle](https://www.oracle.com/java/technologies/downloads/#java11).
4. For Mac users, you may use the [Azul build of OpenJDK 11 (JDK FX) version](https://www.azul.com/downloads/?version=java-11-lts&os=macos&architecture=arm-64-bit&package=jdk-fx).
5. Download the latest duke.jar from [the application's release page](https://github.com/Zxun2/ip/releases/tag/v0.2).
6. Copy the file to the folder you want to use as the home folder for your Duke.
7. Double-click the file to start the app.
8. When the application is running, click on the "help" icon on the top right to view a list of available commands.

## Command Summary

| Index |   Command to Use    |                   Format                    |                        examples                         |
| :---: |:-------------------:|:-------------------------------------------:|:-------------------------------------------------------:|
| 1 | Add a Deadline task |   `deadline <description> /by <deadline>`   |         `deadline homework due /by 2023-02-02`          |
| 2 |  Add an Event task  | `event <description> /from <from> /to <to>` | `event project meeting /from 2023-02-02 /to 2023-12-01` |
| 4 |   Add a Todo task   |            `todo <description>`             |                   `todo borrow book`                    |
| 5 |    Delete a task    |               `delete <task>`               |                       `delete 1`                        |
| 6 |    Find task(s)     |            `find <description>`             |                     `find homework`                     |
| 8 |   List all tasks    |                   `list`                    |                         `list`                          |
| 8 |   List all events   |                `list event`                 |                      `list event`                       |
| 8 |   List all todos    |                 `list todo`                 |                       `list todo`                       |
| 8 | List all deadlines  |               `list deadline`               |                     `list deadline`                     |
| 9 |     Mark a task     |                `mark <task>`                |                        `mark 1`                         |
| 13 |    Unmark a task    |            `unmark <TaskIndex>`             |                       `unmark 1`                        |
| 16 |  Terminate session  |                    `bye`                    |                          `bye`                          |


## Features

### Add Todo Tasks
The `todo <description>` command allows you to easily add a Todo task to your task list. It takes in a single argument, which is the description of the task.

For example, running the command, `todo homework`,  would add a Todo task with the description "homework" to your task list and return the following output:

>Got it. I've added this task:
>
>\[T][ ] homework
>
>Now you have 5 tasks in the list.

### Add Deadline Tasks
The `deadline <description> /by <DateTime>` command allows you to add a Deadline task to your task list. It takes in two arguments, the first is the description of the task and the second is the deadline of the task in the format of `/by <DateTime>`.

For example, running the command, `deadline homework /by 16/01/2023 1500`, would add the task "homework" to your task list with a deadline of January 16th, 2023 at 3:00pm and return the following output:

>Got it. I've added this task:
>
>\[D][ ] homework ( by: Jan 16 2023 15:00 )
>
>Now you have 5 tasks in the list.

### Add Event Tasks
The `event <description> /from <DateTime> /to <DateTime>` command allows you to add an Event task to your task list. It takes in three arguments, the first is the description of the task, the second is the start time of the event in the format of `/from <DateTime>`, and the third is the end time of the event in the format of `/to <DateTime>`.

For example, running`event homework /from 16/01/2023 1500 /to 16/01/2023 1900`, would add the task "homework" as an event to your task list with a start time of January 16th, 2023 at 3:00pm and end time of January 16th, 2023 at 7:00pm and return the following output

> Got it. I've added this task:
>
> \[E][ ] homework ( from: Jan 16 2023 15:00 to: Jan 16 2023 19:00 )
>
> Now you have 7 tasks in the list.


### Add Fixed Duration Tasks
The `fixed <description> /within <Duration>` command allows you to add a task with fixed duration to your task list. It takes in two arguments, the first is the description of the task and the second is the duration of the task in the format of `/within <Duration>`.

For example, running the command, `fixed homework /within 2`, would add the task "homework" to your task list with a duration of 2 hours and return the following output:

> Got it. I've added this task:
>
> \[F][ ] homework ( duration: 2h )
>
> Now you have 5 tasks in the list.

### Updates Tasks
The `update <TaskIndex> <description>` command allows you to update the description of a task in your task list. It takes in two arguments, the first is the index of the task you want to update, and the second is the new description.

For example, running the command, `update 1 Do Assignment 1`, would update the task with index 1 to be "Do Assignment 1" and return the following output:

> Nice! I've updated the description of this task:
>
> \[D][ ] Do Assignment 1 ( by: Jan 15 2021 03: 00 )

### Lists All Tasks
The `list` command allows you to view all tasks in your task list. It takes in no arguments and will show the description, deadline (if any), and start and end time (if any) for each task in the list.

For example, running the command `list` will show the content of the list return the following output:

> 1. \[D][ ] homework ( by: 16/01/2023 1500 )
> 2. \[E][ ] meeting ( from: 16/01/2023 1500 to: 16/01/2023 1700 )
> 3. \[T][X] grocery shopping

### Mark Tasks as Done
The `mark <TaskIndex>` command allows you to mark a task as done in your task list. It takes in one argument, the index of the task you want to mark as done.

For example, running the command, `mark 1`,  would mark the task "homework" as done and return the following output:

> Nice! I've marked this task as done:
>
> \[D][X] Do Assignment 1 ( by: Jan 15 2021 03: 00 )


### Unmark Tasks
The `unmark <TaskIndex>` command allows you to unmark a task that you previously marked as done. It takes in a single argument which is the index of the task you want to unmark.

For example: running the command, `unmark 1`,  would unmark the task previously marked as done at index 1 and return the following output:

> Ok, I've marked this task as not done yet:
>
> \[D][ ] Do Assignment 1 ( by: Jan 15 2021 03: 00 )

### Delete Tasks
The `delete <TaskIndex>` command allows you to delete a task from your task list. It takes in a single argument which is the index of the task you want to delete.

For example: running the command, `delete 1`, would unmark the task previously marked as done at index 1 and return the following output:

> Ok, I've removed this task:
>
> \[D][X] Do Assignment 1 ( by: Jan 15 2021 03: 00 )
>
> Now you have 8 tasks in the list.

### Find Tasks
The `find <description...>` command allows you to find tasks in your task list based on their descriptions. It takes in one or more arguments which are the keywords you want to search for,

For example, running the command `find homework`, will find all the task with description that matches or partially matches the word homework and return the following output:
> Here are the tasks matching "homework":
>
> 1. \[D][ ] homework ( by: 16/01/2023 1500 )
> 2. \[T][ ] Do homework for math class
> 3. \[T][ ] Read chapter 2 and do the homework exercises

running the command `find homework eat meeting`, will find all the tasks with description that matches or partially matches each input description and return the following output:
> Here are the tasks matching "homework":
>
> 1. \[D][ ] homework ( by: 16/01/2023 1500 )
> 2. \[T][ ] Do homework for math class
> 3. \[T][ ] Read chapter 2 and do the homework exercises
     >
     >   Here are the tasks matching "eat":
>
> 1. \[D][ ] eat breakfast ( by: 16/01/2023 0900 )
> 2. \[T][ ] eat chicken breast
     >
     >   Here are the tasks matching "meeting":
>
> 1. \[E][ ] project team meeting ( from: 17/01/2023 1500 to 17/01/2023 1700 )

### Find Next Free Day
It takes in no arguments, and will return the next available day in the next month with no tasks or events scheduled on it.

For example, running the command `free` will return the following output:
> Next free day: 02/15/2023

### Mass Delete Done Tasks
The `massDelete` command allows you to delete all the done tasks in your task list.

For example, running the command `free` will return the following output:
> I have deleted all the tasks that have been marked as done.

### Sort Tasks by Category
The `sort` command allows you to sort all the events in your task list by categories.

For example, running the command, `sort`, will return the following output:
> Here are all your Deadline Task:
> 1. \[D][ ] grade all students' homework ( by: Jan 16 2023 15:00 )
> 2. \[D][ ] do Assignment 1 ( by: Jan 19 2023 23:59 )
>
> Here are all your Event Task:
> 1. \[E][ ] project team meeting ( from: Jan 17 2023 15:00 to Jan 17 2023 17:00 )
>
> Here are all your Fixed Duration Task:
> 1. \[F][X] exercise ( duration: 1h )
> 2. \[F][ ] Coding Time Practice ( duration: 2h )
>
> Here are all your Todo Task:
> 1. \[T][ ] manage hoemework files
> 2. \[T][ ] buy milk
> 3. \[T][ ] mop the floor

### View Tasks on a Given Date
The `view <Date>` command allows you to view all the tasks on a given date. It takes in a single argument which is the date in the format of DD/MM/YYYY.

For example, runing the command, `view 16/01/2023` would display all tasks on January 16th, 2023 and return the following output:
> Here are the tasks on the specified date:
> 1. \[D][ ] grade homework of students ( by: Jan 16 2023 15:00 )
> 2. \[E][ ] do Statistic Assignment ( from: Jan 16 2023 16:00 to: Jan 16 2023 19:00 )

### Bye
The `bye` command allows you to exit the task list application.

## Useful Resources
1. [CheckStyle Tutorial](https://se-education.org/guides/tutorials/checkstyle.html)
2. [Text UI Testing Tutorial](https://se-education.org/guides/tutorials/textUiTesting.html)
3. [Working with Jar files Tutorial](https://se-education.org/guides/tutorials/jar.html)
4. [Gradle Tutorial](https://se-education.org/guides/tutorials/gradle.html)
5. [JavaFX Tutorial](https://se-education.org/guides/tutorials/javaFx.html)