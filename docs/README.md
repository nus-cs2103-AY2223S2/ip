# Duke Atreides Guide 

![Atreides](https://i.pinimg.com/originals/a8/86/10/a8861028676de1e4f70c617e4e41dcd0.png) 

## Features 

Duke Atreides has 4 main features for you to keep track of your daily tasks.

### 1. Adding tasks :heavy_plus_sign:

You can add 4 different types of tasks, specifically todo, deadline, event and within.
More details on how to use these classes below.

### 2. Deleting tasks :x:

Finished your task? You can delete the task by index.

### 3. Mark tasks :heavy_check_mark:

You can mark your tasks as done and unmark them, should you wish to keep a task
but note its completion.

### 4. Automatic save :floppy_disk:

After every command, your set of tasks is automatically saved.
It is also saved across usage so you can see your set of tasks automatically 
everytime you start the application.

## Usage

### `todo` - Adds a todo task

Todo task is a simple task with no associated date and time

Example of usage: 

`todo [taskname] (optional arguments)`

Expected outcome:

![todo task](https://i.imgur.com/l137CDf.png)


The addition of the todo will be acknowledged.

### `deadline` - Adds a deadline task

Deadline task is a task associated with a deadline that it must be completed by.
Note that formatting of the deadline must be of `YYYY-MM-DD` e.g. `2023-02-14'

Example of usage: 

`deadline finish duke /by 2023-02-16`

Expected outcome:

The addition of the deadline will be acknowledged.
The dateline will be parsed into a text format.
![deadline](https://i.imgur.com/hM61HBv.png)

### `event` - Adds an event task.

Event task is a task associated with a period where it must be done during
The formatting for this period is not as strict as deadline.

Example of usage: 

`event Do Advent of Code /from 1 Dec /to 25 Dec`

Expected outcome:

The addition of the event task will be acknowledged.
![event](https://i.imgur.com/KY9yCTw.png)

### `within` - Adds a within task.

Within task is a task associated with a period where it must be done in
Similar to event task.

Example of usage: 

`within get dinner /between 5:30pm and /and 8:30pm.

Expected outcome:
The addition of the within task will be acknowledged.
![within](https://i.imgur.com/df6x8oG.png)

### `delete` - Deletes a task from the list.

Deletes a task from the list by index

Example of usage: 

`delete 1`

Expected outcome:

Deletes the task of the first index.
The deleted task is shown before being deleted, and the number of remaining task listed.
![delete](https://i.imgur.com/X36tF6O.png)

### `list` - List out all the tasks to be done.

Lists out the tasks in your task list.

Example of usage: 

`list`

Expected outcome:

Shows the list of tasks.
![list](https://i.imgur.com/ka3MMXp.png)

```
expected output
```
### `mark and unmark` - Describe action

Marks a task as complete for unmarked taskk
Unmarks a task as incomplete for completed task.

Example of usage: 

`mark 3`

Expected outcome:

Marks the 3rd task as done

![mark](https://i.imgur.com/tgVrdlZ.png)

```
expected output
```

## Future updates

Duke Atreides promises to improve his AI in the next iteration. Hopefully.
