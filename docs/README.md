# User Guide

## Features

### Feature-Adding tasks

Allows for the addition of three types of tasks:
* To-do
* Events
* Deadlines

### Feature-Deleting tasks

Delete tasks that are no longer necessary.

### Feature-Mark & Unmark

Mark a task as done or unmark a task.

### Feature-List

Displays all tasks being tracked.

### Feature-Get events on:

Displays all tasks that are due/occurring on a given date.

### Feature-Find:

Displays all tasks matching a given search key.


## Usage

### `todo` - Adds a todo task to the list

Adds a todo task to the list

Example of usage:

`todo task`

Expected outcome:

A todo task will be saved and the following output will be displayed:

```
Got it. I've added this task:
    [T][] task
Now you have 1 task(s) in the list.
```

### `deadline` - Adds a deadline task to the list

Adds a deadline task to the list

Example of usage:

`deadline task /by 2023-02-15`

Expected outcome:

A task with a deadline will be saved and the following output will be displayed:

```
Got it. I've added this task:
    [D][] task | BY: Feb 15 2023
Now you have 1 task(s) in the list.
```

### `event` - Adds an event to the list

Adds am event with start and end date to the list

Example of usage:

`event task /from 2023-02-15 /to 2023-02-20`

Expected outcome:

An event task will be saved and the following output will be displayed:

```
Got it. I've added your task:
    [E][] task | FROM: Feb 15 2023 TO: Feb 20 2023
Now you have 1 task(s) in the list.
```

### `delete` - Deletes a task

Deletes the task at a given index.

Example of usage:

```
todo task
delete 1
```

Expected outcome:

The task will be deleted and the following output will be displayed:

```
Got it. I've added this task:
    [T][] task
Now you have 1 task(s) in the list.

Noted. I've removed this task:
    [T][] task
Now you have 0 task(s) in the list.    
```

### `mark` - Mark a task as done

Marks the task at a given index as done.

Example of usage:

```
todo task
mark 1
```

Expected outcome:

The task will be marked as done and the following output will be displayed:

```
Got it. I've added this task:
    [T][] task
Now you have 1 task(s) in the list.

Nice! I've marked this task as done:
    [T][X] task
```

### `unmark` - Marks a task as not done

Marks the task at a given index as not done.

Example of usage:

```
todo task
mark 1
unmark 1
```

Expected outcome:

The task will be marked as not done and the following output will be displayed:

```
Got it. I've added this task:
    [T][] task
Now you have 1 task(s) in the list.

Nice! I've marked this task as done:
    [T][X] task
    
OK, I've marked this task as not done yet:
    [T][] task
```

### `list` - Lists all tasks

Displays all the tasks being tracked.

Example of usage:

```
todo task1
deadline task2 /by 2023-02-20
event task3 /from 2023-02-15 /to 2023-02-21
```

Expected outcome:

The following output will be displayed. Note **only the output from the list command** is shown:

```
Here are the tasks in your list:
1. [T][] task1
2. [D][] task2 | BY: Feb 20 2023
3. [E][] task 3 | FROM: Feb 15 2023 TO: Feb 21 2023
```

### `geteventson` - Lists all tasks on a given date

Displays all the tasks on or occurring/ongoing a given date.

Example of usage:

```
todo task1
deadline task2 /by 2023-02-20
event task3 /from 2023-02-15 /to 2023-02-21
geteventson 2023-02-20
```

Expected outcome:

The following output will be displayed. Note **only the output from the get events on command** is shown:

```
Here are the deadlines/events on Feb 20 2023:
1. [D][] task2 | BY: Feb 20 2023
2. [E][] task3 | FROM: Feb 15 2023 TO: Feb 21 2023
```

### `find` - Finds tasks by a search keyword

Displays all the tasks matching a given keyword

Example of usage:

```
todo hello world
todo hello pi
todo hello kitty
todo hellonora
todo goodbyebean

find hello
```

Expected outcome:

The following output will be displayed. Note **only the output from the find command** is shown:

```
1. [T][] hello world
2. [T][] hello pi
3. [T][] hello kitty
4. [T][] hellonora
```

### `getreminders` - List all incomplete tasks that are over orwithin a specified days from the current date. 

Displays all incomplete tasks that are over its deadline / 
start date or within a specified days from the current date.
Optional argument which represents the number of days from the current date.
Default is 3 days from the current date if no argument was passed.

Example of usage (note current date is taken to be 15 Feb 2023):

```
deadline proj1 /by 2023-02-15
deadline proj2 /by 2023-02-17
deadline proj3 /by 2023-02-19
event event1 /from 2023-02-17 /to 2023-02-25
event event2 /from 2023-02-28 /to 2023-03-25

getreminders

getreminders 15
```

Expected outcome:

The following output will be displayed for the first get reminders command. 
Note **only the output from the get reminders command** is shown:

```
Here are the incomplete deadlines/events before Feb 18 2023:
1. [D][] proj1 | BY: Feb 15 2023
2. [D][] proj2 | BY: Feb 17 2023
3. [E][] event1 | FROM: Feb 17 2023 TO: Feb 25 2023
```

The following output will be displayed after the second get reminders command.

```
Here are the incomplete deadlines/events before Mar 2 2023:
1. [D][] proj1 | BY: Feb 15 2023
2. [D][] proj2 | BY: Feb 17 2023
3. [E][] event1 | FROM: Feb 17 2023 TO: Feb 25 2023
4. [E][] event2 | FROM: Feb 28 2023 TO: Mar 25 2023
```
