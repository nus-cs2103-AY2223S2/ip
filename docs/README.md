# User Guide

## Introduction

Hello and welcome to PlanPal, designed to help you manage your personal tasks!
With PlanPal, it's simple to stay organized and keep track of everything you need to do throughout the day.
All you have to do is let me know what your tasks are, and I'll take care of the rest. Let's begin!

## Contents

1. [Features](#features)
2. [Usage](#usage)
3. [Commands](#commands)
4. [Command Summary](#command-summary)

## Features

1. Todo Task
2. Deadline Task
3. Event Task
4. List Task
5. Find Task
6. Delete Task
7. Mark Task
8. Unmark Task

## Usage

### Quick Start

1. Make sure that Java 11 is installed on your computer.
2. Obtain the jar file for this software by downloading it from this [link](https://github.com/pzaiming/ip/releases).
3. Open cmd on the directory you installed the file on and run `java -jar {name}.jar` in terminal.
4. Use the chatbox by entering commands mentioned below.

**The UI of PlanPal should look like this:**
![Ui.png](Ui.png)

## Commands

### Todo:

`todo {task}`

Format: `todo borrow book`

Result:

```
Got it. I've added this task:
[T][ ] borrow book
Now you have 1 tasks in the list
```

### Deadline:

`deadline {task} /by {YYYY-MM-DD} {HHMM}`

Format: `deadline return book /by 2024-10-15 1800`

Result:

```
Got it. I've added this task:
[D][ ] return book (by: Oct 15 2024 0600 PM)
Now you have 2 tasks in the list
```

### Event:

`event {task} /from {datetime} /to {datetime}`

Format: `event project meeting /from Aug 6th 2pm /to 4pm`

Result:

```
Got it. I've added this task:
[E][ ] project meeting (from: Aug 6th 2pm to: 4pm)
Now you have 3 tasks in the list
```

### List:

`list`

Format: `list`

Result:

```
1. [T][ ] borrow book
2. [D][ ] return book (by: Oct 15 2024 0600 PM)
3. [E][ ] project meeting (from: Aug 6th 2pm to: 4pm)
```

### Mark

`mark {Task Index}`

Format: `mark 1`

Result:

```
Nice! I've marked this task as done:
[T][X] borrow book
```

### Unmark

`unmark [Task Index]`

Format: `unmark 1`

Result:

```
Ok, I've marked this task as not done yet:
[T][ ] borrow book
```

### Delete:

Format: `delete 3`

Result:

```
Noted. I've removed this task: 
[E][ ] project meeting (from: Aug 6th 2pm to: 4pm)
Now you have 2 tasks in the list.
```

### Find:

`find {keyword}` (Case sensitive)

Format: `find book`

Result:

```
Here are the matching tasks in your list: 
List of Tasks: 
1. [T][ ] borrow book
2. [D][ ] return book (by: Oct 15 2024 0600 PM)
```

### Exit:

`bye`

Format: `bye`

Example:

```
Bye! Hope to see you again soon!
```

## Command Summary

|                     Command                     |                            Description                            |
|:-----------------------------------------------:|:-----------------------------------------------------------------:|
|               todo `description`                |       Adds a todo with the given `description` to the list        |
| deadline `description` /by `YYYY-MM-DD` `HH:MM` | Adds a deadline with the given `description` set by the datetime. |
|   event `description` /from `when` /to `when`   |  Adds a event with the given `description` set by the datetime.   |
|                      list                       |                   Lists all the current tasks.                    |
|                mark `Task Index`                |        Marks task `Task Index` from the list as complete.         |
|               unmark `Task Index`               |       Marks task `Task Index` from the list as incomplete.        |
|               delete `Task Index`               |             Deletes task `Task Index` from the list.              |
|                 find `keyword`                  |      Finds tasks which matches the `keyword` from the list.       |
|                       bye                       |                        Exit from PlanPal.                         |
