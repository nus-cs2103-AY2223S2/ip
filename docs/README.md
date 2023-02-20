# User Guide

## Features 

### Add a task

3 types of tasks are available to be added. (Todo, Deadline, Event)

### Mark a task

A task can be marked as done with a 'X'

### Unmark a task

A task can be marked as undone

### Find tasks by keyword
All tasks which contains the given keyword will be shown 

### Sort
The list of tasks can be sorted in alphabetical order

### Save a task
Tasks added to the list can be stored and loaded when the program starts

## Usage

### `bye` - exit the program

Exit the program 

Example of usage:  
`bye`

Expected outcome:

Returns a string 'bye' as output before closing the program window

```
"Byeee! Hope to see you again! Signing off, duke."
```

### `deadline` - create a task with its deadline

Add a task which has a deadline to the list of tasks

Example of usage: 
```
deadline return book /by 2019-06-15`
```

Expected outcome:

Task with the stipulated deadline will be added to the list of tasks

```
Got it. I've added this task: 
[D][ ] return book (by: Jun 15 2019)
Now you have 1 task(s) in the list.
```

### `event` - create a task with its start time and end time 

Add a task which has a start time and end time to the list of tasks.

Example of usage: 
```
event project meeting /from 2019-06-15 /to 2019-06-19
```
Expected outcome:

Task with the stipulated start and end time will be added to the list of tasks

```
Got it. I've added this task: 
[E][ ] project meeting (from: Jun 15 2019 to: Jun 19 2019)
Now you have 1 task(s) in the list. 
```

### `delete` - delete a task 

Delete a task at its given index. 

Example of usage: 
```
delete [index]
```

```
delete 1
```

Expected outcome:

Task with the given index will be removed from the list

```
Noted. I've removed this task. 
[E][ ] project meeting (from: Jun 15 2019 to: Jun 19 2019)
Now you have 0 task(s) in the list.
```

### `find` - find by keyword 

Find all tasks which contains the keyword input 

Example of usage: 
```
find [keyword]
```

```
find project
```

Expected outcome:

All tasks which contains the keyword input will be returned as a list
```
Tasks:
1.[E][ ] project meeting (from: Jun 15 2019 to: Jun 19 2019)
```




