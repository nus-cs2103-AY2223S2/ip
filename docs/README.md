# User Guide

## Quick start

1. Ensure you have Java `11` or above installed in your computer.
2. Download the latest duke.jar from [here](https://github.com/bojie3/ip/releases/tag/BCD-Extension).
3. Cd to the duke.jar file.
4. Run the jar file using `java -jar duke.jar`.
5. Type the commands into the GUI. 
6. Some example commands you can try:
- `list`: lists all tasks.
- `todo borrow book`: adds a todo task called `borrow book` to Duke.

## Features 
Note that commands are case sensitive and should all be lower case
Datetime should follow format YYYY-MM-DD HH:MM. EG `2021-12-12 12:00`

### Adding to-do: `todo`

Adds a todo task to Duke.

Format: `todo TASK`

Example: 
- `todo eat`


### Adding deadline: `deadline`

Adds a deadline task to Duke.

Format: `deadline TASK /by YYYY-MM-DD HH:MM`

Example:
- `deadline excersice /by 2021-12-12 12:00`


### Adding event: `event`

Adds an event task to Duke.

Format: `event TASK /from TIME /to TIME`

Example:
- `event lunch with friends /at 2021-12-12 12:00 /to 2021-12-12 13:00`


### Listing all tasks: `list`

Shows a list of all tasks in Duke.

Format: `list`


### Marking task as done: `mark`

Marks a task as done.

Format: `mark INDEX`
- Marks as done the task at specified `INDEX`. 
- The index refers to the index number shown in the displayed task list. 
- The index **must be a positive integer** 1, 2, 3, …

Example:
- `mark 1`


### Marking task as not done: `unmark`

Marks a task as not done.

Format: `unmark INDEX`
- Marks as not done the task at specified `INDEX`. 
- The index refers to the index number shown in the displayed task list. 
- The index **must be a positive integer** 1, 2, 3, …

Example:
- `unmark 1`


### Deleting a task: `delete`

Deletes the specified task from Duke.

Format: `delete INDEX`
- Deletes the task at specified `INDEX`. 
- The index refers to the index number shown in the displayed task list. 
- The index **must be a positive integer** 1, 2, 3, …

Example:
- `delete 1`


### Locating tasks by name: `find`

Find tasks which names contain given keyword.

Format: `find KEYWORD`
- The search is case-sensitive. e.g book will not match Book
- Partial words will be matched. e.g book will match books

Example:
- `find book`


### Sorting the tasks: `sort`

Sort the tasks according to the time the tasks needs to be completed

Format: `sort`


### Saying bye to Duke: `bye`

Close Duke and stop running

Format: 'bye'


### Saving the data
Duke data are saved in the hard disk automatically after any command that changes the data. 
There is no need to save manually.
