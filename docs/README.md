# User Guide

Welcome to Duke! Let's get started with what it can do!

## Features 

### [Feature - Add tasks](#todo-description---add-a-todo-task)

Add a variety of tasks to your list:
- [ToDo tasks](#todo-description---add-a-todo-task) have no time constraints
- [Deadline tasks](#deadline-description-by-ddmmyyyy-hhmm---add-a-deadline-task) have a deadline
- [Event tasks](#event-description-from-ddmmyyyy-hhmm-to-ddmmyyyy-hhmm---add-an-event-task) take place over a specified time and date

### [Feature - Delete tasks](#delete-index---delete-a-task)

### [Feature - Mark tasks as done](#mark-index---mark-a-task-as-done)

### [Feature - Mark tasks as undone](#unmark-index---mark-a-task-as-undone)

### [Feature - List all tasks](#list---list-all-tasks)

### [Feature - Find tasks](#find-keyword---find-a-task)

Find tasks that contain a certain keyword in their description.

### [Feature - Update tasks](#update-index-new-description---update-a-task-description)

You can update:
- [A task's description](#update-index-new-description---update-a-task-description)
- [A Deadline task's deadline](#update-index-by-ddmmyyyy-hhmm---update-a-deadline-task-deadline)
- [An Event task's starting date and time](#update-index-from-ddmmyyyy-hhmm---update-an-event-task-starting-date-and-time)
- [An Event task's ending date and time](#update-index-to-ddmmyyyy-hhmm---update-an-event-task-ending-date-and-time)

### [Feature - Exit Duke](#bye---exit-duke)

## Usage

### `todo [description]` - Add a ToDo task 

A task with no time constraints is added to the list of tasks.

Example of usage: 

`todo buy bread`

Expected outcome:

Duke prints out the ToDo task you just added and the new number of tasks on your list.

[T] indicates that this task is of type ToDo.

[![todo.png](https://raw.githubusercontent.com/kohkaixun/ip/master/src/main/resources/images/todo.png)](https://raw.githubusercontent.com/kohkaixun/ip/master/src/main/resources/images/todo.png)

### `deadline [description] /by [dd/MM/yyyy hhmm]` - Add a Deadline task

A task with a deadline is added to the list of tasks.
The format for day and month is up to the user but the year must be inputted in full.
Time format must adhere to the 24-hour notation.

Example of usage:

`deadline submit iP /by 31/12/2023 2359`

Expected outcome:

Duke prints out the Deadline task you just added and the new number of tasks on your list.

[D] indicates that this task is of type Deadline.

[![deadline.png](https://raw.githubusercontent.com/kohkaixun/ip/master/src/main/resources/images/deadline.png)](https://raw.githubusercontent.com/kohkaixun/ip/master/src/main/resources/images/deadline.png)

### `event [description] /from [dd/MM/yyyy hhmm] /to [dd/MM/yyyy hhmm]` - Add an Event task

A task that takes place over a specific time is added to the list of tasks.
The format for day and month is up to the user but the year must be inputted in full.
Time format must adhere to the 24-hour notation.

Example of usage:

`event competition /from 22/10/2022 0800 /to 22/10/2022 1900`

Expected outcome:

Duke prints out the event task you just added and the new number of tasks on your list.

[E] indicates that this task is of type Event.

[![event.png](https://raw.githubusercontent.com/kohkaixun/ip/master/src/main/resources/images/event.png)](https://raw.githubusercontent.com/kohkaixun/ip/master/src/main/resources/images/event.png)

### `delete [index]` - Delete a task

Deletes the task at the index given on the list.
If the index given is out of range or not a number, Duke will inform you.

Example of usage:

`delete 3`

Expected outcome:

Duke prints out the task you deleted and the number of tasks left on your list.

[![delete.png](https://raw.githubusercontent.com/kohkaixun/ip/master/src/main/resources/images/delete.png)](https://raw.githubusercontent.com/kohkaixun/ip/master/src/main/resources/images/delete.png)

### `mark [index]` - Mark a task as done

Marks the task at the index given as done.
If the index given is out of range or not a number, Duke will inform you.

Example of usage:

`mark 1`

Expected outcome:

Duke marks the task as done and congratulates you before printing the task.

[X] means that the task is done.

[![mark.png](https://raw.githubusercontent.com/kohkaixun/ip/master/src/main/resources/images/mark.png)](https://raw.githubusercontent.com/kohkaixun/ip/master/src/main/resources/images/mark.png)

### `unmark [index]` - Mark a task as undone

Marks the task at the index given as undone.
If the index given is out of range or not a number, Duke will inform you.

Example of usage:

`unmark 1`

Expected outcome:

Duke marks the task as undone and prints it.

[ ] means that the task is not done yet.

[![unmark.png](https://raw.githubusercontent.com/kohkaixun/ip/master/src/main/resources/images/unmark.png)](https://raw.githubusercontent.com/kohkaixun/ip/master/src/main/resources/images/unmark.png)

### `list` - List all tasks

A list of all the tasks are printed.

Example of usage:

`list`

Expected outcome:

All tasks are printed out.

[T] indicates that this task is of type ToDo.

[D] indicates that this task is of type Deadline.

[E] indicates that this task is of type Event.

[X] indicates that this task is done.

[ ] indicates that this task is not done yet.

[![list.png](https://raw.githubusercontent.com/kohkaixun/ip/master/src/main/resources/images/list.png)](https://raw.githubusercontent.com/kohkaixun/ip/master/src/main/resources/images/list.png)

### `find [keyword]` - Find a task

Given a keyword, Duke will find tasks with matching descriptions and return them.
Duke will inform you that there are no matching tasks.

Note:
- The task or tasks returned are not reindexed and their indexes correspond to their actual index in the list.

Example of usage:

`find bread`

Expected outcome:

Duke marks the task as undone and prints it.

[![list.png](https://raw.githubusercontent.com/kohkaixun/ip/master/src/main/resources/images/find.png)](https://raw.githubusercontent.com/kohkaixun/ip/master/src/main/resources/images/find.png)

### `update [index] [new description]` - Update a task description

Updates the description of the task at the index given.

When updating task description, just type the new description after the index.
Names including any of the fields (/by, /from and /event) will be treated as an update of date and time.
The date and time field is not necessary when updating task description.

Example of usage:

`update 1 buy bread from breadtalk`

Expected outcome:

Updates the task description.

[![list.png](https://raw.githubusercontent.com/kohkaixun/ip/master/src/main/resources/images/updatedes.png)](https://raw.githubusercontent.com/kohkaixun/ip/master/src/main/resources/images/updatedes.png)

### `update [index] /by [dd/MM/yyyy hhmm]` - Update a Deadline task deadline

Updates a Deadline task's deadline at the given index.

To update a deadline task's deadline, enter the index followed by the field /by and the date and time in the correct format.

Note:
- Updating deadline for ToDo and Event tasks are not allowed.

Example of usage:

`update 2 /by 1/4/2023 2359`

Expected outcome:

Updates Deadline task's deadline.

[![list.png](https://raw.githubusercontent.com/kohkaixun/ip/master/src/main/resources/images/updateby.png)](https://raw.githubusercontent.com/kohkaixun/ip/master/src/main/resources/images/updateby.png)

### `update [index] /from [dd/MM/yyyy hhmm]` - Update an Event task starting date and time

Updates an Event task's starting date and time at the given index.

To update an event task's starting date and time, enter the index followed by the field /from and the date and time in the correct format.

Note:
- Updating event starting date and time for ToDo and Deadline tasks are not allowed.
- Updated starting date and time cannot be after ending date and time

Example of usage:

`update 3 /from 22/10/2022 0900`

Expected outcome:

Updates Event task's starting date and time.

[![list.png](https://raw.githubusercontent.com/kohkaixun/ip/master/src/main/resources/images/updatefrom.png)](https://raw.githubusercontent.com/kohkaixun/ip/master/src/main/resources/images/updatefrom.png)

### `update [index] /to [dd/MM/yyyy hhmm]` - Update an Event task ending date and time

Updates an Event task's ending date and time at the given index.

To update a event task's ending date and time, enter the index followed by the field /by and the date and time in the correct format.

Note:
- Updating ending date and time for ToDo and Event tasks are not allowed.
- Updated ending date and time cannot be before starting date and time

Example of usage:

`update 3 /to 22/10/2022 1700`

Expected outcome:

Updates Event task's ending date and time.

[![list.png](https://raw.githubusercontent.com/kohkaixun/ip/master/src/main/resources/images/updateto.png)](https://raw.githubusercontent.com/kohkaixun/ip/master/src/main/resources/images/updateto.png)

### `bye` - Exit Duke

Closes the Duke chatbot.

Example of usage:

`bye`

Expected outcome:

Duke replies with a goodbye message and then waits for a second before closing the window.

[![list.png](https://raw.githubusercontent.com/kohkaixun/ip/master/src/main/resources/images/bye.png)](https://raw.githubusercontent.com/kohkaixun/ip/master/src/main/resources/images/bye.png)