# User Guide

`Kuromi クロミ` is a desktop application that acts as a *personal assistant chatbot* to **keep track of your tasks**, **optimized for use via a Command Line Interface** (CLI) while still having the benefits of a Graphical User Interface (GUI). The character `Kuromi クロミ` (the bot) is taken from a [Sanrio Character](https://www.sanrio.com/collections/kuromi), and the user will play the role as `Kuromi クロミ`'s **rival**, `My Melody マイメロディ`.

## Table of Contents
- [Quick Start](#quick-start)
- [Main Features](#main-features)
- [Usage](#usage)
  - [Viewing help: `help`](#viewing-help-help)
  - [Adding a task](#adding-a-task)
    - [`todo`](#1-todo)
    - [`deadline`](#2-deadline)
    - [`event`](#3-event)
  - [Listing all tasks: `list`](#listing-all-tasks-list)
  - [Editing a task](#editing-a-task)
    - [`mark`](#1-mark)
    - [`unmark`](#2-unmark)
  - [Deleting a task: `delete`](#deleting-a-task-delete)
  - [Finding a keyword: `find`](#finding-a-keyword-find)
  - [Getting a reminder: `remind`](#getting-a-reminder-remind)
  - [Exiting the application: `bye`](#exiting-the-application-bye)
  - [Getting My Melody's mistakes: `mistakes`](#getting-my-melodys-mistakes-mistakes)
- [FAQ](#faq)
- [Command Summary](#command-summary)
    
  
--------------------------------------------------------------------------------------------------------------------

## Quick Start

1. Ensure you have Java `11` or above installed in your Computer.
2. Download the latest `kuromi.jar` from [here]().
3. Copy the file to the folder you want to use as the *home folder* for your Kuromi.
4. Double click the jar file to run the application.\
A GUI similar to the below should appear in a few seconds.
5. Type the command in the chat box and press Enter (or click the Send button) to execute it.
6. Refer to the [Usage](#usage) below for details of each command.

## Main Features

### Manage your tasks

Kuromi **keeps track** of your tasks by allowing you to `add`, `view`, `delete`, `mark`, and `unmark` by typing specific commands through the application. Kuromi is talkative and will give comments to your commands! :D

### Get upcoming deadlines

Kuromi can **remind** you of your upcoming deadlines by sending the command `remind`. You can set the number of upcoming tasks that should be shown.

### Find keyword

To easily search for specific tasks, Kuromi is able to find the tasks which include a specific keyword.

### Autosave

Data loss? Fret not! The tasks are autosaved everytime you send a command.

### Get My Melody's Mistakes

From this [source](https://hellokitty.fandom.com/wiki/Kuromi#With_Friends_and_Family), Kuromi has a notebook `Kuromi Note` that **lists all the mistakes** that My Melody made to Kuromi. You can get ~~My Melody's~~ your mistakes by specifying the command `mistakes`.

## Usage

> **Notes about the command format:**
> - Words in `angle brackets <>` are the parameters to be filled by the user.\
    - e.g. in `todo <description>`, `<description>` should be changed into the description of the todo : `todo borrow book`
> - Dates should be in the format of `yyyy-MM-dd HH:mm`.\
    - e.g. `deadline return book /by 2030-01-01 10:00`

## Viewing help: `help`

### `help`

#### Function
Get a list of available commands that Kuromi understands.

#### Format
- `help`

## Listing all tasks: `list`

### `list`

#### Function
Lists all the tasks stored by Kuromi.

#### Format
- `list`

#### Expected outcome

```
Here are the tasks in your list:
1. [T][] borrow book
2. [D][] return book (by: 2030-01-01 10:30)
3. [E][] project meeting (from: 2030-01-01 10:30 to: 2030-01-01 12:30)
-----
Note:
Please finish the tasks soon -_-
```

## Adding a task

### 1. `todo`

#### Function
Adds a ToDo &mdash; a task that does not have a deadline.

#### Format
`todo <description>`

#### Example of usage

- `todo borrow book`
- `todo learn hiragana`
- `todo week 6 iP tasks`

#### Expected outcome

```
Got it. I've added this task:
[T][] borrow book
-----
Note:
Now you have 4 tasks in the list.
```

### 2. `deadline`

#### Function
Adds a Deadline &mdash; a task that has a deadline date.

#### Format
- `deadline <description> /by <deadline>`
- `<deadline>` format: `yyyy-MM-dd HH:mm`

#### Example of usage

- `deadline return book /by 2030-01-01 10:30`
- `deadline submit iP /by 2023-02-17 23:59`
- `deadline katakana practice /by 2023-03-01 14:00`

#### Expected outcome

```
Got it. I've added this task:
[D][] return book (by: 2030-01-01 10:30)
-----
Note:
Now you have 5 tasks in the list.
```

### 3. `event`

#### Function
Adds an Event &mdash; a task that has a start date and end date.

#### Format
- `event <description> /from <start_date> /to <end_date>`
- `<start_date>` & `<end_date>` format: `yyyy-MM-dd HH:mm`

#### Example of usage

- `event project meeting /from 2030-01-01 10:30 /to 2030-01-01 12:30`
- `event tP meeting /from 2023-01-13 16:00 /to 2023-01-13 18:00`
- `event cmang's birthday party /from 2023-03-12 17:00 /to 2023-03-12 19:00`

#### Expected outcome

```
Got it. I've added this task:
[E][] project meeting (from: 2030-01-01 10:30 to 2030-01-01 12:30)
-----
Note:
Now you have 6 tasks in the list.
```

## Editing a task

### 1. `mark`

#### Function
Mark the task with the index specified as done.

#### Format
- `mark <index>`

#### Example of usage

- `mark 1`

#### Expected outcome

```
Nice! I've marked this task as done:
[T][X] borrow book
-----
Note:
You finally finished the task! :D
```

### 2. `unmark`

#### Function
Mark the task with the index specified as undone.

#### Format
- `unmark <index>`

#### Example of usage
- `unmark 1`

#### Expected outcome

```
OK, I've marked this task as not done yet:
[T][] borrow book
-----
Note:
Please do the task soon -_-
```

## Deleting a task: `delete`

### `delete`

#### Function
Delete the task with the index specified.

#### Format
`delete <index>`

#### Example of usage
`delete 1`

#### Expected outcome

```
Noted. I've removed this task:
[T][X] borrow book
-----
Note:
Now you have 5 tasks in the list.
```

## Finding a keyword: `find`

### `find`

#### Function
Find tasks with descriptions that contain the keyword. Keyword may be a partial word or a full word.

#### Format
- `find <keyword>`
- `<keyword>` format: 1 word

#### Example of usage
- `find bo`
- `find book`

#### Expected outcome

```
Here are the matching tasks in your list:
1. [D][] return book (by: 2030-01-01 10:30)
```

## Getting a reminder: `remind`

### `remind`

#### Function
Get a reminder of the upcoming tasks. ToDo tasks will be least prioritised because they don't have an end date.

#### Format
- `remind` &mdash; lists upcoming 5 tasks
- `remind <number_of_tasks>` &mdash; lists upcoming <number_of_tasks> tasks

#### Example of usage
- `remind`
- `remind 3`

#### Expected outcome

```
Here are your upcoming 3 tasks:
1. [E][] project meeting (from: 2023-02-13 16:00 to: 2023-02-13 18:00)
2. [D][] iP (by: 2023-02-17 23:59)
3. [D][] return book (by: 2030-01-01 10:30)
-----
Note:
I know you won't finish the tasks on time :D
```

## Exiting the application: `bye`

### `bye`

#### Function
Ends the conversation with Kuromi and closes the application windw.

#### Format
- `bye`

#### Expected outcome
```
Bye Melody. Hope to see you again soon!
-----
Note:
You're annoying but I'll definitely miss you :D
```

## Getting My Melody's mistakes: `mistakes`

### mistakes

#### Function
Shows Kuromi's notebook `Kuromi Note` that contains a list of My Melody's mistakes.

#### Format
- `mistakes`

## FAQ
  
**Q**: Where is my data saved?\
**A**: Your data is saved in `kuromi.txt`, in a `data` folder created when you first add a task in the application.
  
**Q**: How do I transfer my data to another computer?\
**A**: Install the application in the other computer and replace the empty data file it creates with the file that contains the data you want to transfer.

## Command Summary
| Action | Format | Examples |
| ----- | ------ | ----- |
| Add ToDo | `todo <description>` | `todo borrow book` |
| Add Deadline | `deadline <description> /by <deadline>` | `deadline return book /by 2030-01-01 10:30` |
| Add Event | `event <description> /from <start_date> /to <end_date>` | `event project meeting /from 2030-01-01 10:30 /to 2030-01-01 12:00` |
| List | `list` | `list` |
| Mark | `mark <index>` | `mark 1` |
| Unmark | `unmark <index>` | `unmark 2` |
| Delete | `delete <index>` | `delete 3` |
| Find | `find <keyword>` | `find book` |
| Remind | `remind <number_of_tasks>` | `remind 3` |
| Exit | `bye` | `bye` |
| Show Kuromi Note | `mistakes` | `mistakes` |
