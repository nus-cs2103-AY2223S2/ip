# User Guide
Duke is a simplistic text-based task management application.
With Duke, you can manage all your tasks with just the keyboard!

## Features 

Duke supports the following features:

### Text-based

All actions with Duke are done through the keyboard, 
allowing for quick and intuitive management of tasks if you are a fast typer.

### Persistance

All tasks are serialised and stored in the `./data/duke.txt` folder relative to where the application is started up.
This allows tasks to be stored across multiple runs.

## Usage

### `bye` - Ends the program

Ends the session with Duke. All new messages will no longer be accepted.

Example of usage: 

`bye`

Expected outcome:

```
Bye. Hope to see you again soon!
```

### `list` - List all tasks

Displays all tasks stored on Duke.

Example of usage: 

`list`

Expected outcome:

```
Here are the tasks in your list:
1. *task 1*
2. *task 2*
...
```

### `mark` - Mark a task as completed

Marks a task of at certain index as completed. 
The index of a task can be obtained from the `list` command.

Format:

Example of usage: `mark 1`

Expected outcome:

Marks task at index 1 as completed.

```
Nice! I've marked this task as done:
=> [T][X] *task that has just been marked*
```

### `unmark` - Mark a task as not completed

Marks a task of at certain index as incompleted. 
The index of a task can be obtained from the `list` command.

Example of usage: `unmark 1`

Expected outcome:

Marks task at index 1 as incompleted.

```
OK, I've marked this task as not done yet:
=> [T][ ] *task that has just been unmarked*
```

### `todo` - Creates a **todo** task

Creates a todo task with a certain task description.

Example of usage: `todo clean the dishes`

Expected outcome:

```
Got it. I've added this task:
=> [T][] clean the dishes

Now you have 5 tasks in the list
```

### `deadline` - Creates a **deadline** task

Creates a deadline task with a task description, and a time the task has to be completed by.

Example of usage: `deadline submit CS2103T IP /by 17/2/2023 2359`

Expected outcome:

```
Got it, I've added this task:
=> [D][] submit CS2103T IP (by: 17-Feb-2023,Fri,11:59PM)

Now you have 6 tasks in the list
```

### `event` - Creates an **event** task

Creates an event task with a task description, a start timing and an end timing for the event.

Example of usage: `event OP1 rehearsal meeting /from 17/2/2023 0900 /to 17/2/2023 1000`

Expected outcome:

```
Got it. I've added this task:
=> [E][] OP1 rehearsal meeting (from: 17-Feb-20233,Fri,09:00AM to: 17-Feb-2023,Fri,10:00AM)

Now you have 7 tasks in the list
```

### `delete` - Deletes a task

Deletes a task of at certain index. 
The index of a task can be obtained from the `list` command.

Example of usage: `delete 7`

Expected outcome:

Deletes task at index 7.

```
Noted. I've removed this task
=> [E][] *task that has just been deleted*

Now you have 6 tasks in the list
```

### `after` - Creates a **after** task

Creates an after task with a task description, and a time which the task has to be done after.

Example of usage: `after Check internship updates /after 17/2/2023 1200`

Expected outcome:

```
Got it. I've added this task:
=> [A][] Check internship updates (after: 17-Feb-2023,Fri,12:00PM)

Now you have 7 tasks in the list
```

### `find` - Finds all task with a certain substring

Finds all tasks with a given string contained in its task description.

Example of usage: `find IP`

Expected outcome:

```
Here are the matching tasks in your list:
1. [D][] Finish IP (by: 17-Feb-2023,Fri,11:59PM)
2. [T][X] User guide for IP
3. [D][] submit CS2103T IP (by: 17-Feb-2023,Fri,11:59PM)
```