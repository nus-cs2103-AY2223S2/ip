# User Guide to  Duke 
<h2 align="center"> Your Personal Chatbot Assistant </h2>

<p align="center">
<img width="513" src="./images/start.png" alt="main view of application">
</p>

- [Introduction](#introduction)
- [Quick Start](#quick-start)
- [Command Summary](#command-summary)
- [Features](#features)
    * [Create a Task](#create-a-task)
      * [Add Todo task](#add-todo-task)
      * [Add Deadline task](#add-deadline-task)
      * [Add Event task](#add-event-task)
    * [List All Tasks](#list-all-tasks)
      * [List All Events](#list-all-events) 
      * [List All Todos](#list-all-todos)
      * [List All Deadlines](#list-all-deadlines)
    * [Mark Task](#mark-task) 
    * [Unmark Task](#unmark-task)
    * [Delete Task](#delete-task) 
    * [Find Task](#find-tasks)
    * [Undo command](#undo)
    * [Checkout version](#checkout)
    * [Saving data](#saving-the-data)
    * [Bye](#bye)

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
|:-----:|:-------------------:|:-------------------------------------------:|:-------------------------------------------------------:|
|   1   | Add a Deadline task |   `deadline <description> /by <deadline>`   |         `deadline homework due /by 2023-02-02`          |
|   2   |  Add an Event task  | `event <description> /from <from> /to <to>` | `event project meeting /from 2023-02-02 /to 2023-12-01` |
|   3   |   Add a Todo task   |            `todo <description>`             |                   `todo borrow book`                    |
|   4   |    Delete a task    |               `delete <task>`               |                       `delete 1`                        |
|   5   |    Find task(s)     |            `find <description>`             |                     `find homework`                     |
|   6   |   List all tasks    |        `list <event/deadline/todo>?`        |                         `list`                          |
|  10   |     Mark a task     |                `mark <task>`                |                        `mark 1`                         |
|  11   |    Unmark a task    |               `unmark <task>`               |                       `unmark 1`                        |
|  12   |     Undo action     |                   `undo`                    |                         `undo`                          |
|  13   |  Checkout version   |            `checkout <version>?`            |                      `checkout 1`                       |
|  14   |  Terminate session  |                    `bye`                    |                          `bye`                          |


## Features

**Notes about the command format**

- Words enclosed in diamond brackets `<>` are the parameters to be supplied by the user.
- `?` after `<>` indicates that the argument is optional, and the user may choose not to specify it.
- Parameters must respect the order of the command format
- Extra parameters for commands that do not take in parameters will be ignored.

In Duke, there are 3 types of tasks:
- **Deadline**: Used when there are deadlines to meet, and you need to keep track of what to do
- **Event**: Used when there is an event that you need to attend, and you need to keep track of it
- **Todo**: The most fundamental type of tasks that denote an item that you intend to accomplish

All tasks come with the ability to be described, and support marking or unmarking tasks as completed.

### Create a task

Tasks are created and stored locally in a `.txt` file under the `data` folder.

#### Add Todo Task
The `todo <description>` command adds a Todo task to your task list. It takes in a single argument, which is the description of the task.

Example:  `todo homework`

Expected Outcome: 
```
Got it! I've added this task:
    [T] [ ] homework
Now you have 1 task(s) in the list.
```

#### Add Deadline Task
The `deadline <description> /by <deadline>` command adds a Deadline task to your task list. It takes in two arguments, the first is the description of the task and the second is the deadline of the task in the format of `/by <DateTime>`.

Example: `deadline return book /by 2023-02-13`

Expected Outcome:
```
Got it! I've added this task:
    [D] [ ] return book (by: monday, Feb 13 2023)
Now you have 1 task(s) in the list.
```

#### Add Event Task
The `event <description> /from <DateTime> /to <DateTime>` command allows you to add an Event task to your task list. It takes in three arguments, the first is the description of the task, the second is the start time of the event in the format of `/from <DateTime>`, and the third is the end time of the event in the format of `/to <DateTime>`.

Example: `event homework /from 2023-02-13 /to 2023-03-11`

Expected Outcome:
```
Got it! I've added this task:
    [E] [ ] project meeting (from: Feb 13 2023 to Mar 11 2023)
Now you have 1 task(s) in the list.
```
### List All Tasks
In Duke, you can easily view the list of tasks. The `list` command allows you to view all tasks in your task list. 
It takes in 3 optional arguments and will show the description, deadline (if any), and start and end time (if any) for each task in the list.

#### List All Events

Example: `list event`

Expected Outcome: 
```
1.  [E] [ ] project meeting (from: Feb 13 2023 to Mar 11 2023)
```

#### List All Todos

Example: `list todo`

Expected Outcome:
```
1.  [T] [ ] project meeting 
```

#### List All Deadlines

Example: `list deadline`

Expected Outcome:
```
1.  [D] [ ] return book (by: monday, Feb 13 2023)
```


### Mark Task
The `mark <task>` command allows you to mark a task as done in your task list. It takes in one argument, the index of the task you want to mark as done.

Example: `mark 1`

Expected Outcome: 
```
Nice! I've marked this task as done:
    [X] Do Assignment 1 
```

### Unmark Task
The `unmark <task>` command allows you to unmark a task that you previously marked as done. It takes in a single argument which is the index of the task you want to unmark.

Example: `unmark 1`

Expected Outcome:
```
Ok, I've marked this task as not done yet:
    [ ] Do Assignment 1
```

### Delete Task
The `delete <task>` command allows you to delete a task from your task list. It takes in a single argument which is the index of the task you want to delete.

Example: `delete 1`

Expected Outcome:
```
Noted. I've removed this task:
    [T][X] Do Assignment 1 
Now you have 1 task in the list.
```

### Find Tasks
The `find <description...>` command allows you to find tasks in your task list based on their descriptions. 
It takes in one or more arguments which are the keywords you want to search for,

Example: `find homework`

Expected Outcome:
```
Here are the tasks matching "homework":
[T][ ] homework 
[T][ ] Do homework for math class
```

### Undo
The "undo" action refers to the ability to reverse or cancel the previous action taken by the user. 
For example, if the user accidentally adds a task, they can use the undo action to remove it from the task list. 
The undo action can also be used to revert any changes made to an existing task, such as editing its description or 
marking it as complete. Essentially, the undo action allows the user to correct mistakes or change their mind without having to start over from scratch.

Example: `undo`

Expected Outcome: 
```
State has successfully reverted to the last changes.
```

### Checkout
The application records each change made to the state of the application and provides an overview of the complete history of the project.
This makes it possible to revert to a previous version of the project if necessary, or to compare different versions to see how the project has evolved.

Example: `checkout`

### Saving the data

Duke's data is saved in a local file automatically after any command that changes the data. There is no need to save manually.

### Bye
Terminates the application.

Example of usage:

`bye`

Expected outcome:

```
Bye. Hope to see you again soon!
```
