# User Guide
Duke is a command-line chatbot to help you to keep track of tasks.

<img height="400px" src="Ui.png">

## Quick Start
1. Ensure that you have Java `11` or above installed in your Computer.
2. Download the latest `duke.jar` from [here](https://github.com/potty10/ip/releases).
3. Copy or move `duke.jar` to the folder that you want to use as the home folder for Duke.
4. Open a command terminal, `cd` into the folder in step 3, and run `java -jar duke.jar` to run Duke. A graphical user interface (GUI) should open.
5. Type any commands in the command box, then click `Send` or press `Enter` to run any commands.

## Features 
Users primary interact with the chatbot with commands. Commands primarily have the syntax `command command_arg [/option option_arg]`.

### 1. Exiting and saving
Exiting the application automatically saves any data.

### 2. Add tasks

There are 3 types of tasks that can be recorded in the duke application:
- `event`: An event has a start time and end time.
- `deadline`: A deadline must have a specified end time.
- `todo`: A todo task has no start time or end time.

Note that any time strings must have both `date` and `time`, where `time` 
is precise up to minutes. The 24-hour time format, commonly known as military time, should be used.

### 3. Delete tasks

Delete tasks when you no longer need to record them.

### 4. Mark or unmark tasks

Each task has a corresponding checkbox that can be checked or unchecked. It is useful if you want to mark tasks that are done or over.

### 5. List tasks

List all tasks that are recorded in the application.

### 6. Find tasks

Find all tasks by keyword.

## Usage

### 1. `bye` - Exits application

Format: `bye`

Exits the application and saves any data.
 
### 2. `todo` - Add a Todo task

Format: `todo task_name`

Adds a todo task with `task_name` as the name of the task.

Example of usage: `todo homework 1`

Expected outcome:

```
OK! I've added this task:
[ ][T] homework 1
Now you have 1 tasks in the list.
```

### 3. `deadline` - Adds a task with a deadline

Format: `deadline task_name /by time`

Adds a task with `task_name` as the name of the task and `time` as the deadline. `time` must be specified in the format
`year-month-day-hour-minute`.

Example of usage:  `deadline homework 1 /by 2023-02-02-23-59`

Expected outcome:

```
OK! I've added this task:
[ ][D] homework 1 (by: 02 Feb 2023, 23.59 PM)
Now you have 2 tasks in the list.
```

### 4. `event` - Adds a Event task

Format: `event event_name /from start_time /to end_time`

An event is considered as a task with start time and end time. This command adds a 
task with `event_name` as the name of the task, `start_time` as the start time and `end_time` as the end time.
`time` must be specified in the format `year-month-day-hour-minute`.

Example of usage: `event project meeting /from 2023-02-02-16-00 /to 2023-02-02-17-00`

Expected outcome:

```
OK! I've added this task:
[ ][E] project meeting (from: 02 Feb 2023, 16.00 PM to 02 Feb 2023, 17.00 PM)
Now you have 3 tasks in the list.
```

### 5. `mark` - Marks a task

Format: `mark index`

Marks a task with the `index`. Index of a task can be found by listing all tasks using the command `list`.

Example of usage: `mark 1`

Expected outcome:

Marks a task with the index `1`.

```
Nice! I've marked this task as done:
[X][T] homework 1
```

### 6. `unmark` - Unmarks a task

Format: `unmark index`

Unmarks a task with the `index`. Index of a task can be found by listing all tasks using the command `list`.

Example of usage: `unmark 1`

Expected outcome:

Unmarks a task with the index `1`.

```
OK! I've marked this task as not done yet:
[ ][T] homework 1
```

### 7. `list` - List all tasks

Format: `list`

List all tasks in the application. For each task, `list` will show whether it is
1. Checked and unchecked
2. The type of task (`event`, `todo` or `deadline`)
3. Name of the task.

Example outcome:

```
Here are the tasks in your list:
1. [ ][T] homework 1
2. [ ][D] homework 1 (by: 02 Feb 2023, 23.59 PM)
3. [ ][E] project meeting (from: 02 Feb 2023, 16.00 PM to 02 Feb 2023, 17.00 PM)
```

### 8. `find` - Find all tasks with the specified keyword

Format: `find keyword`

Lists all tasks with the specified `keyword` in the name.

Example of usage:

`find homework`

Expected outcome:

List all tasks whose name contains the keyword `homework`.

```
Here are the tasks that you want:
1. [ ][T] homework 1
2. [ ][D] homework 1 (by: 02 Feb 2023, 23.59 PM)
```
### 9. `delete` - Delete tasks

Format: `delete arg1 ...`

Delete all tasks with the index `arg1, arg2, ...`. Index of a task can be found by listing all tasks using the 
command `list`. Note that all indexes must be unique.

Example of usage:

`delete 1 2 3`

Expected outcome:

Delete all tasks with index `1`, `2`, `3`.

```
OK! I've removed this task:
1. [ ][T] homework 1
2. [ ][D] homework 1 (by: 02 Feb 2023, 23.59 PM)
3. [ ][E] project meeting (from: 02 Feb 2023, 16.00 PM to 02 Feb 2023, 17.00 PM)
Now you have 0 tasks in the list.
```