# JeoBot's User Guide
Hello! JeoBot is a 🔥 _fiery_ 🔥 chatbot focused on helping you manage your tasks. Given below are instructions on how to use it.

<img src="https://raw.githubusercontent.com/Jun-How/ip/master/src/main/resources/images/DaJeo.png" width="250" height="250">

## Table of Contents
1. [Setting Up](#setting-up)
2. [Features](#features)
   - [Manage different tasks](#manage-different-tasks)
   - [Find specific tasks](#find-specific-tasks)
   - [Set customisations](#set-customisations)
3. [Commands](#commands)
   - [todo](#todo)
   - [deadline](#deadline)
   - [event](#event)
   - [mark](#mark)
   - [unmark](#unmark)
   - [delete](#delete)
   - [list](#list)
   - [find](#find)
   - [due](#due)
   - [bye](#bye)

## Setting Up
1. Download the latest JAR release file from [here](https://github.com/Jun-How/ip/releases).
2. Double-click it.
3. Add your tasks.
4. Let it manage your tasks for you 😉

## Features
### Manage different tasks
- Manage tasks of various types (ToDo, Deadline, Event).

### Find specific tasks
- Find tasks based on a keyword or due date.

### Set customisations
- Optionally add tags to categorise tasks.
- Multiple (`...`) tags can be added by specifying the command followed by its detail `/tag <detail>` for each tag.


## Commands
### `todo`
Adds a Todo task to your list. Tags are optional (indicated by []).

Example of usage:
`todo buy groceries [/tag impt...]`

### `deadline`
Adds a Deadline task to your list with the specified due date and time in the format `yyyy-MM-dd HH:mm`. Tags are optional (indicated by []).

Example of usage:
`deadline assignment /by 2023-03-02 23:59 [/tag impt...]`

### `event`
Adds an Event task to your list with the specified start, end dates and times in the format `yyyy-MM-dd HH:mm`. Tags are optional (indicated by []).

Example of usage:
`event business trip /from 2023-03-01 08:00 /to 2023-03-03 23:00 [/tag impt...]`

### `mark`
Marks the specified task as complete.

Example of usage:
`mark 2` -> marks the 2nd task in your list as done

### `unmark`
Marks the specified task as incomplete.

Example of usage:
`unmark 2` -> marks the 2nd task in your list as not done

### `delete`
Deletes the specified task from the list.

Example of usage:
`delete 2` -> deletes the 2nd task from your list


### `list`
Displays your current list of tasks

Example of usage:
`list`

### `find`
Finds tasks that contain the specified keyword

Example of usage:
`find assignment`

### `due`
Finds tasks that are due on the specified date in the format `yyyy-MM-dd`

Example of usage:
`due 2023-03-01`

### `bye`
Exits the program

Example of usage:
`bye`
