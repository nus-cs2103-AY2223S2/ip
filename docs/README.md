# User Guide for BooBot

## Overview of Features 

### 1. List All Tasks - `list`

Provides a list of all existing tasks. 

### 2. Add ToDo Task - `todo`

Adds a new todo task to the list of existing tasks.

### 3. Add Deadline Task - `deadline`

Adds a new deadline task to the list of existing tasks.

### 4. Add Event Task - `event`

Adds a new event task to the list of existing tasks.

### 5. Mark Task - `mark`

Marks a task as done.

### 6. Unmark Task - `unmark`

Marks a task as undone.

### 7. Delete Task - `delete`

Deletes a task from the list of existing tasks.

### 8. Get Tasks on a Specific Day - `on`

Displays all the tasks that occur on a given day.

### 9. Find Tasks - `find`
Displays all the tasks whose names contain a given key word/phrase.

### 10. Get Reminders for Tasks - `reminder`
Provides a reminder for upcoming or due tasks.

### 11. Get Help - `help`
Displays the list of commands supported by BooBot.

### 12. Exit - `exit`
Exits BooBot.


## Usage

### `list` - List All Tasks

Displays a list of all existing tasks.

Format: `list`
<br/>
<br/>

### `todo` - Create ToDo Task

Adds a new todo task to the list of existing tasks.

Format: `todo TASK_NAME`

Remarks:
- `TASK_NAME` is compulsory.

Examples:
- `todo read book` Adds a new todo task with name _read book_ to the list of existing tasks.
- `todo` Displays an error message because the task name is missing.
  <br/>
  <br/>

### `deadline` - Create Deadline Task

Adds a new deadline task to the list of existing tasks.

Format: `deadline TASK_NAME /by START_DATE`

Remarks:
- `TASK_NAME` and `START_DATE` are compulsory.
- `START_DATE` has to be in the format of either `yyyy-MM-dd hh:mm` or `yyyy-MM-dd`.

Examples:
- `deadline return book /by 2023-02-03 14:00` Adds a new deadline task with name _return book_ and deadline
  _2023-02-03 14:00_ to the list of existing tasks.
- `deadline return book /by 2023-02-03` Adds a new deadline task with name _return book_ and deadline
  _2023-02-03_ to the list of existing tasks.
- `deadline return book /by 3rd February 2023` Displays an error message because the date is of incorrect
  format.
- `deadline return book` Displays an error message because the deadline is missing.
  <br/>
  <br/>

### `event` - Create Event Task

Adds a new event task to the list of existing tasks.

Format: `event TASK_NAME /from START_DATE /to END_DATE`

Remarks:
- `TASK_NAME`, `START_DATE` and `END_DATE` are compulsory.
- `START_DATE` and `END_DATE` have to be in the format of either `yyyy-MM-dd hh:mm` or `yyyy-MM-dd`.
- `START_DATE` must be before or equal to `END_DATE`.

Examples:
- `event project meeting /from 2023-02-03 14:00 /to 2023-02-03 18:00` Adds a new event task with name
  _project meeting_ and duration from _2023-02-03 14:00_ to _2023-02-03 18:00_.
- `event project meeting /from 2023-02-03 /to 2023-02-03` Adds a new event task with name
  _project meeting_ and duration from _2023-02-03_ to _2023-02-03_.
- `event project meeting /from 2023-02-03 /to 2023-02-01` Displays an error message because the start
  date is after the date end.
- `event project meeting /from 2023-02-03` Displays an error message because the end date is missing.
<br/>
<br/>

### `mark` - Mark Task

Marks the status of a task as done.

Format: `mark TASK_NUMBER`

Remarks:

- `TASK_NUMBER` is compulsory.
- `TASK_NUMBER` must be a valid task number as shown in `list`.

Examples:

- `mark 2` Marks the status of task number 2 as done.
-  `mark 0` Displays an error message because task numbers start from 1.
<br/>
<br/>

### `mark` - Unmark Task

Marks the status of a task as undone.

Format: `unmark TASK_NUMBER`

Remarks:
- `TASK_NUMBER` is compulsory.
- `TASK_NUMBER` entered must be a valid task number as shown in `list`.

Examples:

- `unmark 2` Marks the status of task number 2 as undone.
-  `mark 0` Displays an error message because task numbers start from 1.
<br/>
<br/>

### `delete` - Delete Task

Deletes a task in the list of existing tasks.

Format: `delete TASK_NUMBER`

Remarks:
- `TASK_NUMBER` is compulsory.
- `TASK_NUMBER` entered must be a valid task number as shown in `list`.

Examples:

- `delete 2` Deletes task number 2 from the list of existing tasks.
-  `delete 0` Displays an error message because task numbers start from 1.
<br/>
<br/>

### `on` - Get Tasks on a Specific Day

Displays a list of tasks that fall on a given day.

Format: `on SPECIFIC_DAY`

Remarks:

- `SPECIFIC_DAY` is compulsory.
- `SPECIFIC_DAY` has to be in the format of either `yyyy-MM-dd hh:mm` or `yyyy-MM-dd`.

Examples:

- `on 2023-02-03` Displays all tasks that fall on _2023-02-03_.
-  `on 3rd February 2023` Displays an error message because the date is of incorrect format.
<br/>
<br/>

### `find` - Find Tasks

Displays a list of tasks whose names contain a given key phrase/word.

Format: `find KEY_PHRASE`

Remarks:

- `SPECIFIC_DAY` is compulsory.
- `SPECIFIC_DAY` has to be in the format of either `yyyy-MM-dd hh:mm` or `yyyy-MM-dd`.

Examples:

- `on 2023-02-03` Displays all tasks that fall on _2023-02-03_.
-  `on 3rd February 2023` Displays an error message because the date is of incorrect format.
<br/>
<br/>












