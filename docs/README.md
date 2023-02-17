Smudge is a c(h)at 🐱 bot that frees you from the burden of having to remember what's coming up soon.

1. [Quick Start](#quick-start)
2. [Features](#features)
3. [Commands](#commands)
4. [Summary](#summary)

## Quick Start
Follow these simple steps and you'll never miss another event or deadline!

1. Download Smudge from [here](https://github.com/nevinlim/ip/releases).
2. Open a command window (Powershell, Terminal) in the directory you downloaded Smudge
3. Using the command window, type the following command:
`java -jar Smudge.jar`
4. Start freeing your mind from event dates 😼

## Features 

### Task Manager

Have you forgotten about an important date or an impending deadline ? <br>
Fret not, Smudge the cat is here to ease your worries ! 😸 <br>
 Smudge will store and display to you all your events with specific date and time so you'll never forget them again.


## Commands

### `Keyword` - Describe action

### `todo` - Add a task without date or time.

Adds a to-do task to be completed, but without any specified date or time.

  Format: `todo [task description]`

  Example of usage: `todo eat sushi`

### `deadline` - Add a task which has a deadline

Adds a deadline task which should be completed by the specified deadline in the following format: DD/MM/YYYY HHmm.

  Format: `deadline [task description] /by [DD/MM/YYYY HHmm]`

  Example of usage: `deadline get a valentine's day gift /by 14/02/2023 0000`

### `event` - Add a task which has a specific timeframe

Adds an event task which is ongoing during the specified timeframe in the following format: DD/MM/YYYY HHmm.

  Format: `event [task description] /from [start date and time] /to [end date and time]`

  Example of usage: `event CS2103T Lecture /from 17/02/2023 1400 /to 17/02/2023 1600`

### `delete` - Delete a task

Deletes a task (by specified index).

  Format: `delete [index]`

  Example of usage: `delete 6`

### `list` - A list of every existing tasks.

Displays the cumulative list of all tasks added by the user.

  Format: `list`

  Example of usage: `list`

### `mark` - Marks a task as done

Marks a task (by specified index) as done.

  Format: `mark [index]`

  Example of usage: `mark 4`

### `unmark` - Marks a task as not done

Marks a task (by specified index) as not done.

  Format: `unmark [index]`

  Example of usage: `unmark 3`


### `find` - Finds tasks using specified keyword(s)

Retrieves matching tasks according to specified keyword(s).

  Format: `find [keyword]`

  Example of usage: `find run`

### `help` - Get help

Shows the link to this User Guide.

  Format: `help`

  Example of usage: `help`
  
### `bye` - Exits

Closes and quits the program, events inputted are stored in local file.

  Format: `exit`

  Example of usage: `exit`

## Summary

Description of all commands.

|    Command Format  | Description |
| ------------------ | ----------- |
| deadline [task description] /by [DD/MM/YYYY HHmm] | Creates a task with specified deadline |
| delete [index] | Deletes the task by specified index |
| event [task description] /from [DD/MM/YYYY HHmm] /to [DD/MM/YYYY HHmm] | Creates a task within the specified timeframe |
| exit | Quits and closes the Smudge program |
| find [keyword] | Finds a list of tasks which contain specified keyword in their description |
| help | Returns link to this user-guide for help with commands |
| list | Displays the cumulative list of tasks added by the user |
| mark [index] | Marks the task at the specified index as done |
| todo [task description] | Creates a task without date or time |
| unmark [index] | Marks the task at the specified index as not done |

