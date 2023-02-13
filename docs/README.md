# User Guide
Duke is a **desktop app for managing user tasks, optimized for use via a Command Line Interface** while still having the benefits of a Graphical User Interface.

![Sample Gui Image](/Ui.png)

## Features 

### `list` - Lists all tasks

Show a list of all tasks.

Format: `list`  
<br>

### `bye` - Closes the application

Format: `bye` 

<br>

### `todo` - Add a todo

Adds a todo task to your list of tasks.

Format: `todo DESCRIPTION`

Example of usage:
- `todo Read Chainsaw Man`
- `todo Wash the dishes` 

<br>

### `deadline` - Add a deadline

Adds a deadline task to your list of tasks. A deadline is a task with a deadline date.

Format: `deadline DESCRIPTION /by DEADLINE_DATE_AND_TIME`  
DEADLINE_DATE_AND_TIME should be in the form DD-MM-YYYY HH:mm. Time is optional.

Example of usage:
- `deadline Finish homework /by 13-08-2000`
- `deadline Submit assignment /by 01-05-2000 23:59` 

<br>

### `event` - Add an event

Adds an event task to your list of tasks. An event is a task with a start and end date.

Format: `event DESCRIPTION /from START_DATE_AND_TIME /to END_DATE_DATE_AND_TIME`  
Date and time should be in the form DD-MM-YYYY HH:mm. Time is optional.

Example of usage:
- `event Birthday Celeberation /from 01-05-2020 /to 02-05-2020`
- `event BTS Concert /from 13-08-2023 16:00 /to 13-08-2023 18:00` 

<br>

### `edit` - Edits a task

Edits a single field of a currently existing task

Format: `event TASK_NUMBER FIELD_TO_EDIT NEW_VALUE`  

FIELD_TO_EDIT can take on these values:
- `/desc` corresponds to `DESCRIPTION` found in all tasks. 
- `/by` corresponds to `DEADLINE_DATE_AND_TIME` found in deadline tasks. 
- `/from` corresponds to `START_DATE_AND_TIME` found in event tasks. 
- `/to/` corresponds to `END_DATE_AND_TIME` found in event tasks. 

Only tasks that contain the relevant fields can have those fields edited.

Example of usage:
- `edit 1 /by 13-08-2000` - Task 1 is a deadline task whose deadline date is set to 13 Aug 2000.
- `edit 2 /desc New Task Description` - Task 2 is any task whose description is set to "New Task Description". 

<br>

### `find` - Finds a subset of tasks.

Filters your list of tasks and returns those that contain the word: `KEYWORD`. 

Format: `find KEYWORD`  

Example of usage: 

![Example for find](/findExample.png) 

<br>

### `mark` - Marks a task as done

Marks a task as done.

Format: `mark TASK_NUMBER`

Example of usage:
- `mark 1` 

<br>

### `unmark` - Unmarks a task

Unmarks a marked task.

Format: `unmark TASK_NUMBER`

Example of usage:
- `unmark 1` 

<br>
