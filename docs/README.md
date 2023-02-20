# User Guide

***Duke*** is a desktop app for managing your tasks and keeping track of upcoming deadlines or events easily. The app shares benefits from using both the Graphical User Interface (GUI) as well as the more optimized Command Line Interface (CLI). 

<br />

## Features 

### Adding a to-do: `todo`

Adds a new to-do task to the list of tasks.  

Format: `todo DESCRIPTION`

Examples:
- `todo go to the gym`
- `todo buy apples`

<br />

### Adding a deadline: `deadline`

Adds a new deadline task with the given due date to the list of tasks.  

Format: `deadline DESCRIPTION /by DUE_DATE`
> ⚠️ Note: `DUE_DATE` must be in this format: ***dd-MM-YYYY HH:mm***

Examples:
- `deadline Homework /by 12-06-2023 09:30`
- `deadline CS2103T Project /by 17-02-2023 23:59`

<br />

### Adding an event: `event`

Adds a new event task with the given start and end date-times to the list of tasks.  

Format: `deadline DESCRIPTION /from START_DATE_TIME /to END_DATE_TIME`
> ⚠️ Note: `START_DATE_TIME` and `END_DATE_TIME` must be in this format: ***dd-MM-YYYY HH:mm***

Examples:
- `event meeting /from 12-06-2023 09:30 /to 12-06-2023 11:30`
- `event hackathon /from 17-02-2023 12:00 /to 18-02-2023 12:00`

<br />

### Listing tasks: `list`

Lists all the tasks in an ordered list. 

Format: `list`

Example of usage: 
1. `todo Go shopping`  
2. `deadline Homework /by 12-06-2023 09:30`  
3. `list`  

Expected outcome:

```
Your Tasks:
1. [T][ ] Go shopping
2. [D][X] Homework (by: Mon, 12 Jun 2023, 09:30 AM)
```

<br />

### Marking a task as done: `mark`

Marks the specified task as done.

Format: `mark TASK_NO`

- Marks the task with the specified `TASK_NO` as done. 
- The `TASK_NO` refers to the index number shown in the displayed task list. 
- The `TASK_NO` must be a positive integer 1, 2, 3, ... 

Example of usage:
`mark 1` changes the state of the first task in the list:  
for eg. from `[T][ ] Go shopping` to `[T][X] Go shopping`


<br />

### Marking a task as not done: `unmark`

Marks the specified task as not done.

Format: `unmark TASK_NO`

- Marks the task with the specified `TASK_NO` as not done. 
- The `TASK_NO` refers to the index number shown in the displayed task list. 
- The `TASK_NO` must be a positive integer 1, 2, 3, ... 

Example of usage:
`unmark 2` changes the state of the second task in the list:  
for eg. from `[T][X] Buy medicines` to `[T][ ] Buy medicines`

<br />

### Deleting a task: `delete`

Deletes the specified task.

Format: `delete TASK_NO`

- Deltes the task with the specified `TASK_NO` from the list of tasks. 
- The `TASK_NO` refers to the index number shown in the displayed task list. 
- The `TASK_NO` must be a positive integer 1, 2, 3, ... 

Example of usage:
`delete 1` deletes the first task in the list.

<br />

### Finding a task: `find`

Finds and lists all tasks that contain the specified keyword(s).

Format: `find KEYWORD`  

Example of usage:  
`find project`

Expected Outcome:  
```
Search results for "project":
1. [T][ ] CS2103T project
2. [T][ ] Trend projection calculation
3. [D][ ] team project: apollo (by: Mon, 19 Jun 2023, 11:00 PM)
```

<br />

### Sort tasks by deadline: `sort`  

Sorts the list of tasks based on the due date of deadlines.

Format: `sort`

Example of usage:

Before:
```
1. [D][ ] CS3230 PA2 (by: Fri, 3 Mar 2023, 11:59 PM)
2. [D][X] CS2103T iP (by: Fri, 17 Feb 2023, 11:59 PM)
3. [D][ ] CS2101 Reflection (by: Mon, 27 Feb 2023, 09:59 PM)
```

After:
```
1. [D][X] CS2103T iP (by: Fri, 17 Feb 2023, 11:59 PM)
2. [D][ ] CS2101 Reflection (by: Mon, 27 Feb 2023, 09:59 PM)
3. [D][ ] CS3230 PA2 (by: Fri, 3 Mar 2023, 11:59 PM)
```

<br />

### Exiting the program: `bye`  
Exits the Duke chatbot application.  

Format: `bye`

<br />

### Saving the data

Any changes to the tasks data by a command are saved automatically to the hard disk. No manual steps are required. 
> ⚠️ Note: However, if the data file is corrupted, all tasks are deleted and a new list of tasks is created without notifying the user.
