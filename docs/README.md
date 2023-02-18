# User Guide

## Features 

### Getting help: `help`

Shows a list of available commands and the command format.

### Adding a Deadline: `deadline`

Adds a task that needs to be done by a specific date and time.

### Adding an Event: `event`

Adds a task that starts and ends at a specific date and time.

### Adding a Todo: `todo`

Adds a task that needs to be completed.

### Listing all tasks: `list`

Lists out all current tasks.

### Marking a task: `mark` 

Marks a task as done.

### Unmarking a task: `unmark`

Marks a task as undone.

### Finding a task: `find`

Finds a task that matches a specific keyword.

### Deleting a task: `delete`

Deletes a specific task.

### Saying goodbye: `bye`

Exits from the program.

## Usage

### `help` - Shows all commands
Shows all the available commands and their respective formats.

Format: `help`

Expected outcome:
```
I see you asked for help? YOU GOT IT!
Try the following commands:
> [Deadline] deadline DESCRIPTION /by YYYY-MM-DDTHH:MM
> [Event] event DESCRIPTION /from YYYY-MM-DDTHH:MM /to YYYY-MM-DDTHH:MM
> [Todo] todo DESCRIPTION
> [Find] find KEYWORD
> [Mark] mark INDEX
> [Unmark] unmark INDEX
> [Delete] delete INDEX
> [List] list
> [Bye] bye
> [Help] help
```
***
### `deadline` - Adds a deadline task
Adds a task that needs to be done by a specific date and time.

Format: `deadline DESCRIPTION /by YYYY-MM-DDTHH:mm`

Example of usage: `deadline report /by 2023-01-01T16:30`

Expected outcome:
```
Gotcha! I have added this task:
[D][ ] report (BY: Sunday, January 1 2023, 4:30 PM)
Now you have a total of 1 tasks.
```
***
### `event` - Adds an event task
Adds a task that starts and ends at a specific date and time.

Format: `event DESCRIPTION /from YYYY-MM-DDTHH:mm /to YYYY-MM-DDTHH:mm`

Example of usage: `event study /from 2023-02-18T00:00 /to 2023-02-26T23:59`

Expected outcome:
```
Gotcha! I have added this task:
[E][ ] study (FROM: Saturday, February 18 2023, 12:00 AM, TO: Sunday, February 26 2023, 11:59 PM)
Now you have a total of 1 tasks.
```
***
### `todo` - Adds a todo task
Adds a task that needs to be completed.

Format: `todo DESCRIPTION`

Example of usage: `todo sleep`

Expected outcome: 
```
Gotcha! I have added this task:
[T][ ] sleep
Now you have a total of 1 tasks.
```
***
### `list` - Lists all tasks
Lists out all current tasks.

Format: `list`

Expected outcome:
```
Here are the current tasks you have:
1. [T][ ] sleep
2. [D][ ] report (BY: Sunday, January 1 2023, 4:30 PM)
3. [E][ ] study (FROM: Saturday, February 18 2023, 12:00 AM, TO: Sunday, February 26 2023, 11:59 PM)
```
### `mark` - Marks a task
Marks a task as done.

Format: `mark INDEX`

Example of usage: `mark 2`

Expected outcome:
```
Ok, I've marked this task as done:
[T][X] sleep
```
***
### `unmark` - Unmarks a task
Unmarks a task if it is marked as done.

Format: `unmark INDEX`

Example of usage: `unmark 1`

Expected outcome:
```
Ok, I've marked this task as undone:
[T][ ] sleep
```
***
### `find` - Find matching tasks
Finds a task that matches a specific keyword.

Format: `find KEYWORD`

Example of usage: `find sleep`

Expected outcome:
```
I found these tasks:
1. [T][ ] sleep early
2. [T][ ] stop sleeping in the afternoon
```
***
### `delete` - Delete a task
Deletes a specific task.

Format: `delete INDEX`

Example of usage: `delete 1`

Expected outcome:
```
Alright, I have removed this task:
[T][ ] sleep
Now you have a total of 6 tasks.
```
***
### `bye` - Exits from program
Saves all existing tasks into data file and exits the program.

Format: `bye`

Expected outcome:
```
Exiting Duke now...
Bye, see you again!
```
