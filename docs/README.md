# User Guide

## Features 

### Feature-Manage Tasks

Users are able to use duke to:
1. Add and delete tasks of 3 categories (Todo, Deadline, Event)
2. Mark and unmark tasks as completed
3. List out all the tasks added
4. Find tasks with a specific keyword
5. Find tasks happening through a certain date
6. Update tasks

### Feature-Save Tasks locally

Tasks added will be automatically stored locally. Users will
be able to go back to view the same TaskList whenever they need.

## Usage
### 1.  `todo` - Adds a Todo task

Input format: `todo <task name>`

Example of usage: 

`todo Do something`

Expected outcome:

```
Got it. I've added this task:
[T][] Do something
Now you have 1 task in the list.
```

### 2. `deadline` - Adds a Deadline task

Input format: `deadline <task name> /by <DateTime>`

Date time to be formatted as: YYYY-MM-DDTHH:MM

Example of usage:

`deadline Reach School /by 2023-02-17T14:00` 

Expected outcome:

```
Got it. I've added this task:
[D][] Reach School (by: Feb 17 2023 1400)
Now you have 2 tasks in the list.
```

### 3. `event` - Adds a Event task

Input format: `event <task name> /from <DateTime> / to <DateTime>`

Date time to be formatted as: YYYY-MM-DDTHH:MM

Example of usage:

`event Cs Midterms /from 2023-02-18T14:00 /to 2023-02-18T16:00`


Expected outcome:

```
Got it. I've added this task:
[E][] Cs Midterms (From: Feb 18 2023 1400 To: Feb 18 2023 1600)
Now you have 3 Tasks in the list.
```

### 4. `List` - Lists out all the task

Example of usage:

`list`

Expected outcome:

```
There is a total of 3 tasks currently:
1. [T][] Do something
2. [D][] Reach School (by: Feb 17 2023 1400)
3. [E][] Cs Midterms (From: Feb 18 2023 1400 To: Feb 18 2023 1600)
```

### 5. `Mark` - Mark task as completed

Input format: `mark <Index of task in list>`

Example of usage:

`mark 1`

Expected outcome:

```
Nice! I've marked this task as done:
[T][X] Do something
```
### 6. `Unmark` - Unmark task as completed

Input format: `unmark <Index of task in list>`

Example of usage:

`unmark 1`

Expected outcome:

```
Okay. i've unmarked the following task:
[T][] Do something
```

### 7. `Delete` - Delete task in list

Input format: `delete <Index of task in list>`

Example of usage:

`delete 1`

Expected outcome:

```
Noted. I've removed this task:
[T][X] Do something
Now you have 2 tasks in the list.
```

### 8. `Update` - Update task with new details

Input format: `update <Index of task in list> /change <Full details of task>`

Example of usage:

`update 2 /change Leave School /by 2023-02-20T12:30' `

Expected outcome:

```
I have updated the task to the following
[D][] Leave School (by: Feb 20 2023 1230)
```

### 9. `Through` - Finds tasks happening through a date

Input format: `through <DateTime>`

Date time to be formatted as: YYYY-MM-DDTHH:MM

Example of usage:

`through 2023-02-19T23:59`

Expected outcome:

```
There are the tasks occuring through Feb 19 2023 2359:
1. [D][] Leave School (by: Feb 20 2023 1230)
```

### 10. `Find` - Finds tasks with a specific keyword

Input format: `Find <Keyword>`

Keyword can only be 1 word

Example of usage:

`find Cs`

Expected outcome:

```
There are the matching tasks in your list:
1. [E][] Cs Midterms (From: Feb 18 2023 1400 To: Feb 18 2023 1600)
```

### 11. `Bye` - Exits Duke program

Example of usage:

`bye`
