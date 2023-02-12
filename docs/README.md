# User Guide

## Features 
* Add Tasks - Todo, Deadline, Event
* View Tasks
* Mark/ Unmark Tasks
* Delete Tasks
* Find Tasks
* Archive Tasks
* Save Tasks

## Usage

### `todo` - Add Todo task.

Add a todo (task with no dates) to the task list. 
Note that the task cannot be empty. 

Example of usage: 

`todo clean litter box`

`todo read book`

Expected outcome:

Toto will acknowledge you added a todo task by replying, 

```
    Meow! Just added:
    [T][ ] clean litter box
```
### `deadline` - Add Deadline task.

Add a deadline (task that has a due date) to the task list. 
Note that the task cannot be empty and the date format. 

Example of usage:

`deadline homework//2022-03-03 09:00`

`deadline banana bread//2023-08-09 19:00`

Expected outcome:

Toto will acknowledge you added a deadline task by replying, 

```
  Meow! Just added:
  [D][ ] homework (by: Mar 3 2022 09:00)
```
### `event` - Add Event task.

Add a event (task that has a start and end date) to the task list.
Note that the task cannot be empty and the date format. 

Example of usage:
`event ballroom dancing//2022-03-03 09:30//2022-03-03 10:30`

`event ballroom dancing//2022-11-06 18:00//2022-11-07 10:00`

Toto will acknowledge you added an event task by replying, 

```
  Meow! Just added:
  [E][ ] ballroom dancing (from: Mar 3 2022 09:30 to: Mar 3 2022 10:30)
```
### `list` - View Tasks.
View tasks in the list as a list. 

example of usage: 
`list` 
Note that there cannot be any spaces. 

Toto will list the tasks you have by replying, 

```
    1. [T][ ] clean litter box
    2. [D][ ] homework (by: Mar 3 2022 09:00)
    3. [E][ ] ballroom dancing (from: Mar 3 2022 09:30 to: Mar 3 2022 10:30)
```

### `mark` - Mark a task as done.

Mark a task in the task list as done by index.

Example of usage: 
`mark 1`
Note that index must be in bounds. 

Toto will acknowledge that you have marked task 1 and update the list, 
   
``` 
    I've marked this task as done: [T][X] clean litter box
```

### `unmark` - Mark a task as not done.

Mark a task in the task list as not done by index. 

Example of usage: 
`unmark 1`
Note that index must be in bounds. 

Toto will acknowledge that you have unmarked task 1 and update the list, 

``` 
    I've marked this task as not done yet: [T][ ] clean litter box
```

### `delete` - Delete a task. 

Delete a task from the task list by index. 

Example of usage: 
`delete 2`

Note that index must be in bounds. 

Toto will acknowledge that you have deleted task 2 and update the list, deleted tasks are archived, 


``` 
    I've deleted this task: [D][ ] homework (by: Mar 3 2022 09:00)
```

### `find` - Find a task.

Find a task in the task list with the given keyword. 

Example of usage: 
`find ballroom`

Toto will list out all the tasks containing the keyword, 

```
    Here are the tasks with the keyword:
    1. [E][X] ballroom dancing (from: Mar 15 2023 18:00 to: Mar 15 2023 19:00)
    2. [T][ ] disco and ballroom
```

### `archive all` - Archive tasks. 

Archive all tasks in the main list. 

Example of usage:
`archive all`

Toto will acknowledge you have removed all the tasks and archive them, 

```
    All your tasks are now archived, your task list is now empty!
```

### `list archive` - Access archives. 

Access all deleted/ archived tasks. 

Example of usage: 
`list archive`

Toto will list out all the tasks that have been archived, 

```
    1. [D][ ] homework (by: Mar 3 2022 09:00)
    2. [E][ ] ballroom dancing (from: Mar 3 2022 09:30 to: Mar 3 2022 10:30)
    3. [T][ ] clean litter box
```
### `save` - Save current tasks. 

Saves the tasks and archives into the computer. 

Example of usage: 
`save`

Toto will acknowledge that you have saved, 

```
    Content is saved! Enter exit to quit the program. CATch you later!
```

### `exit` - Exit Toto. 

Exit out of the program. 

Example of usage: 
`exit`

Toto will exit. 
