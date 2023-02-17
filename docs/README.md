### ***Smudge*** is a c(h)at 🐱 bot that frees you from the burden of having to remember what's coming up soon.

|Contents|
| ------ |
| 1. [Prerequisite](#prerequisite) |
|2. [Quick Start](#quick-start) |
|3. [Features](#features) |
|4. [Commands](#commands) |
|5. [Summary](#summary) |

## Prerequisite
You will need Java 11 on your computer. (It is recommended to use Java 11) <br>
If you already have Java JDK or JRE 11, you may **skip** this segment. <br>
Otherwise, you can download Java 11 from [here](https://www.oracle.com/sg/java/technologies/javase/jdk11-archive-downloads.html).

## Quick Start
Follow these simple steps and you'll never miss another event or deadline!

1. Download Smudge from [here](https://github.com/nevinlim/ip/releases).
2. Double click it. (if this does not work (weird text comes out), go to step 3 - 4)
3. (You may skip this step if step 2 works) <br>Open a command window (Powershell, Terminal) in the directory you downloaded Smudge
4. (You may skip this step if step 2 works) <br> Using the command window, type the following command:
`java -jar Smudge.jar`
4. Start freeing your mind from event dates 😼

## Features 

### Task Manager

Have you forgotten about an important date or an impending deadline ? <br>
Fret not, Smudge the cat is here to ease your worries ! 😸 <br>
 Smudge will store and display to you all your events with specific date and time so you'll never forget them again.


## Commands

### `Keyword` - Describe action

<br>

### `list` - A list of every existing tasks.

Displays the cumulative list of all tasks added by the user.

  Format: `list`

  Example of usage: `list`

<br>

### `todo` - Add a task without date or time.

Adds a to-do task to be completed, but without any specified date or time.

  Format: `todo [task description]`

  Example of usage: `todo eat sushi`
  
<br>

### `deadline` - Add a task which has a deadline

Adds a deadline task which should be completed by the specified deadline in the following format: DD/MM/YYYY HHmm.

  Format: `deadline [task description] /by [DD/MM/YYYY HHmm]`

  Example of usage: `deadline get a valentine's day gift /by 14/02/2023 0000`
  
<br>

### `event` - Add a task which has a specific timeframe

Adds an event task which is ongoing during the specified timeframe in the following format: DD/MM/YYYY HHmm.

  Format: `event [task description] /from [start date and time] /to [end date and time]`

  Example of usage: `event CS2103T Lecture /from 17/02/2023 1400 /to 17/02/2023 1600`

<br>

### `delete` - Delete a task

Deletes a task (by specified index).

  Format: `delete [index]`

  Example of usage: `delete 6`

<br>

### `mark` - Marks a task as done

Marks a task (by specified index) as done.

  Format: `mark [index]`

  Example of usage: `mark 4`

<br>

### `unmark` - Marks a task as not done

Marks a task (by specified index) as not done.

  Format: `unmark [index]`

  Example of usage: `unmark 3`

<br>

### `find` - Finds tasks using specified keyword(s)

Retrieves matching tasks according to specified keyword(s).

  Format: `find [keyword]`

  Example of usage: `find run`
  
<br>

### `help` - Get help

Shows the link to this User Guide.

  Format: `help`

  Example of usage: `help`
  
<br>
  
### `bye` - Exits

Closes and quits the program, events inputted are stored in local file.

  Format: `exit`

  Example of usage: `exit`

## Summary

Description of all commands.

|    Command Format  | Description |
| ------------------ | ----------- |
| [deadline](#deadline) [task description] /by [DD/MM/YYYY HHmm] | Creates a task with specified deadline |
| [delete](#delete) [index] | Deletes the task by specified index |
| [event](#event) [task description] /from [DD/MM/YYYY HHmm] /to [DD/MM/YYYY HHmm] | Creates a task within the specified timeframe |
| [bye](#bye) | Quits and closes the Smudge program |
| [find](#find) [keyword] | Finds a list of tasks which contain specified keyword in their description |
| [help](#help) | Returns link to this user-guide for help with commands |
| [list](#list) | Displays the cumulative list of tasks added by the user |
| [mark](#mark) [index] | Marks the task at the specified index as done |
| [todo](#todo) [task description] | Creates a task without date or time |
| [unmark](#unmark) [index] | Marks the task at the specified index as not done |

