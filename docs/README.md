# User Guide

<br>

## Hello from Duke!
Duke is a chatbot that helps you manage your day-to-day tasks by keeping a list.
It provides you with many features to manage your tasks effectively.
> This chatbot is named after Duke, the Mascot of Java - the language it is built in.

<br>

## What is a **_Task_**?
Tasks can be of three types:
1. **_To-do_** - tasks that have no time constraints
2. **_Deadline_** - tasks that have to be completed within a particular date-time
3. **_Event_** - tasks that have particular start and end date-time's

<br>

All tasks have a status associated with them - done (`"X"`) and undone (`" "`).
> All tasks are set to be _undone_ by default. However, you can set the status of a task to be _done_.

<br>

All tasks also have a priority associated with them - high / medium / low.
> All tasks are set to medium priority by default. However, you can change it later.

<br>

### To-do
A to-do is represented in the form:
```
[T]{<priority>}[<status>] <description>
```

Example representation:
```
[T]{low}[ ] read book
```

<br>

### Deadline
A deadline is represented in the form:
```
[D]{<priority>}[<status>] <description> (by: <date-time this deadline is to be completed by>)
```

Example representation:
```
[D]{high}[X] assignment (by: Feb 20 2023 11:59 PM)
```

<br>

### Event
An event is represented in the form:
```
[E]{<priority>}[<status>] <description> (from: <start date-time> ; to: <end date-time>)
```

Example representation:
```
[E]{medium}[ ] lecture (from: Feb 21 2023 03:00 PM ; to: Feb 21 2023 05:00 PM)
```


<br>


## Features

### 1a. Adding a To-do: `todo`
Adds a to-do with the given description to the list.

<br>

Command format: `todo <description>`

<br>

### 1b. Adding a Deadline: `deadline`
Adds a deadline with the given description and date-time to the list.

<br>

Command format: `deadline <description> /by <date-time the deadline is to be completed by>`

> The date-time is to be given in the format _**DD-MM-YYYY hhmm (in 24hrs format)**_

<br>

### 1c. Adding a Event: `event`
Adds an event with the given description, and start and end date-time's to the list.

<br>

Command formats:
- `event <description> /from <start date-time> /to <end date-time>`
- `event <description> /to <end date-time> /from <start date-time>`

> The date-time's are to be given in the format _**DD-MM-YYYY hhmm (in 24hrs format)**_

<br>

### 2. Listing all Tasks: `list`
Lists all the tasks that are present in the list.

<br>

Command format: `list`

Output format: `<task number>) <task>`

<br>

Example output:
```
Listing all tasks...
1) [T]{low}[ ] read book
2) [D]{high}[X] assignment (by: Feb 20 2023 11:59 PM)
3) [E]{medium}[ ] lecture (from: Feb 21 2023 03:00 PM ; to: Feb 21 2023 05:00 PM)
4) [T]{medium}[ ] return book
```

### 3a. Marking a task as _done_: `mark`
Marks a task denoted by the given task number as _done_.

<br>

Command format: `mark <task number>`
> Use the [list](#2-listing-all-tasks-list) command to know the task numbers of each of the tasks.

<br>

### 3b. Marking a task as _undone_: `unmark`
Marks a task denoted by the given task number as _undone_.

<br>

Command format: `unmark <task number>`
> Use the [list](#2-listing-all-tasks-list) command to know the task numbers of each of the tasks.

<br>

### 4. Deleting a task: `delete`
Deletes a task denoted by the given task number, from the list.

<br>

Command format: `delete <task number>`
> Use the [list](#2-listing-all-tasks-list) command to know the task numbers of each of the tasks.

<br>

### 5. Finding a task: `find`
Finds a task with the given keyword, from the list of tasks.

<br>

Command format: `find <keyword>`

Output format: `<task number>) <task>`

<br>

Example output:
```
Listing all tasks with the keyword 'book'...
1) [T]{low}[ ] read book
4) [T]{medium}[ ] return book
```

<br>

### 6. Prioritize tasks: `prioritize`
Sets the priority of the task denoted by the task number, to the given priority.

<br>

Command format: `prioritize <task number> <high/medium/low>`
> Use the [list](#2-listing-all-tasks-list) command to know the task numbers of each of the tasks.

<br>

### 7. Storing the data
Stores the list of the tasks in a text file, so that it can be retrieved when you close and open the application.

> ❗WARNING: If the data file is modified to become of invalid form, Duke will erase all the data without any warning.

<br>

### 8. Exiting the application: `bye`
[Stores](#7-storing-the-tasks) the data into the text file and exits the application.

<br>

Command format: `bye`
