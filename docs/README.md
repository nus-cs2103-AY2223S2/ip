# User Guide

## Features 
* Add Tasks - Todo, Deadline, Event
* Mark/ Unmark Tasks
* Delete Tasks
* View Tasks
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
### `event` - Add Event task.


