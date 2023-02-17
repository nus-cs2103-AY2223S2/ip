# User Guide

##  Quick start
1) Ensure you have Java 11 or above installed in your Computer.

2) Download the latest Duke.jar from [here](https://github.com/igezt/ip/releases).

3) Copy the file to the folder you want to use as the home folder for Duke.

4) Open a command terminal, cd into the folder you put the jar file in, and use the java -jar duke.jar command to run the application.
A GUI appear in a few seconds.

5) Type the command in the command box and press Enter to execute it.
Some example commands you can try:

* list : Lists all tasks. (Should be empty for you right now)

* todo Work : Adds a Todo task for Duke to remember.
* bye : Saves all relevant data for Duke and exits.

Refer to the Features below for details of each command.

## Features 

---
### `list` List Command

#### Syntax:
`list`

#### Description:

Lists out all the tasks in the task list of Duke 
along with its details, completion state and type of task.



---
### `todo` - Adds a Todo task


#### Syntax:
`todo {DETAILS}`

#### Example of usage:

`todo work` saves a todo task with "work" as its description

#### Description:

Adds a ToDo task to the task list of Duke with the details specified.
Defaults as incomplete.
---
### `deadline` - Add a Deadline
#### Syntax:
`deadline {DETAILS} /by {DEADLINE DATETIME}`

#### Example usage:

`deadline work /by 18-02-2023 18:00` saves a deadline with 
"work" as its description and has a deadline of 18 February 2023
6pm.

#### Description:

Adds a Deadline to the task list of Duke with the details 
 and the deadline datetime specified.
Defaults as incomplete.

Datetime are currently only accepted in the following format:

DD-MM-YYY HH:MM where HH:MM is in 24-hour form.

An error will occur if "/by" is not included in your command  or if
the datetimes are not in the right format, so make sure
format your command properly.
---
### `event` - Add an Event
#### Syntax:
`event {DETAILS} /from {FROM DATETIME} /to {TO DATETIME}`

#### Example Usage:
`event work /from 18-02-2023 14:00 /to 18-02-2023 18:00` saves an event with "work" 
as its description and will last from 18 February 2023 2pm to 18 February 2023
6pm.

#### Description:
Adds an event task to the task list of Duke with the details,
the from datetime and the to datetime specified.
Defaults as incomplete.

Datetime are currently only accepted in the following format:

DD-MM-YYY HH:MM where HH:MM is in 24-hour form.

An error will occur if "/from" or "/to" is not included in your command or if
the datetimes are not in the right format,
so make sure to format your command properly
---
### `delete` - Delete a task
#### Syntax:
`delete {TASK NUMBER}`

#### Example Usage:
`delete 1` will delete the very first task in the list.

#### Description:

Deletes the task according to the task number given.
To find out what number a task is, use the `list` command.
---

### `find` - Finds tasks
#### Syntax:
`find {details}`

#### Example Usage:
`find CS2103T` will list out all the tasks that have CS2103T in its description.


#### Description:
Shows all tasks that have descriptions which contain the details specified
in the command.
---

### `mark` - Mark task as complete
#### Syntax:
`mark {TASK NUMBER}`

#### Example Usage:
`mark 1` will make the first task in the list as complete.

#### Description:
Marks the task as complete according to the task number given.
To find out what number a task is, use the `list` command.
---

### `unmark` - Marks task as incomplete
#### Syntax:
`unmark {TASK NUMBER}`

#### Example Usage:
`unmark 1` will make the first task in the list as incomplete.

#### Description:
Marks the task as incomplete according to the task number given.
To find out what number a task is, use the `list` command.
---

### `edit` - Edit task details
#### Syntax:
`edit {TASK NUMBER} {NEW DETAILS}`
#### Example Usage:
`edit 1 Finish CS2103T project` will edit the first task in the list to have the description of
"Finish CS2103T project".

#### Description:
Edits the task's details according to the task number given to the
new details specified in the command.

To find out what number a task is, use the `list` command.

---

### `bye` - Exit Command
#### Syntax:
`bye`

#### Description:
Closes Duke and saves all relevant information.

__**IMPORTANT:**__
-
Use the bye command to exit Duke, 
or your progress and/or changes may not be saved.
