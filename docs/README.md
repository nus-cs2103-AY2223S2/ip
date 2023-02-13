# User Guide

## Features 

### list all tasks: `list`

List all tasks.

### Add a todo task: `todo [task name]`

Add a todo task to the list. A todo task is one that does not have start or end dates. 

### Add a deadline: `deadline [task name] /by [deadline date]`

Add a deadline task to the list. A deadline task has an end date, specified in `yyyy-mm-dd` format, e.g., `2022-07-25`.

### Add an event: `event [event name] /from [start date] /to [end date]`

Add an event to the list. An event should have a start and an end date, both in `yyyy-mm-dd` format. 

### Find a task: `find [keyword 1] [keyword 2] ...`

Search tasks by keywords. At least one keyword is required, and there is no constraint on the number of keywords. Each
keyword is separated by a blank space. 

### Delete a task: `delete [task index]`

Delete a task by index. The index of a task can be found in the task list given by the `list` command, and is assigned
in chronological order of when the tasks are added to the list. 


## Usage

### `List` - List all tasks

All current tasks are stored, and can be listed by `list`. Tasks include todos, deadlines, and events.

Example usage: 

`list`

One possible outcome:

```
Here are the tasks in your list:
1. [D][ ] test (by: Oct 22 2922)
2. [E][ ] event (from: Oct 22 2021 to: Oct 22 2022)
```

There are two tasks in the list, a deadline indicated by `D` and an event indicated by `E`. The tasks are sorted
in chronological order of when they are added to the list (the first will appear at the top).

### `todo [task name]` - Add a todo task

Add a todo task to the list. A todo task is one that does not have start or end dates. 

Example usage:

`todo task`

One possible outcome:

```
Got it. I've added this task:
  [T][ ] task
Now you have 3 tasks in the list.
```

The program will show that the user adds a todo task, indicated by `T`, and informs the user the total number of tasks 
in the list.

### `deadline [task name] /by [deadline date]` - Add a deadline task

Add a deadline task to the list. A deadline task has an end date, specified in `yyyy-mm-dd` format, e.g., `2022-07-25`.

Example of usage:

`deadline homework /by 2023-02-14`

Expected outcome:

```
Got it. I've added this task:
  [D][ ] homework (by: Feb 14 2023)
Now you have 4 tasks in the list.
```

The program will show that the user adds a deadline task, indicated by `D`, and informs the user the total number of tasks
in the list. Also, the date will be printed in a reader-friendly format.

### `event [event name] /from [start date] /to [end date]` - Add an event

Add an event to the list. An event should have a start and an end date, both in `yyyy-mm-dd` format.

Example of usage:

`event holiday /from 2023-05-02 /to 2023-08-13`

Expected outcome:

```
Got it. I've added this task:
  [E][ ] holiday (from: May 2 2023 to: Aug 13 2023)
Now you have 5 tasks in the list.
```

The program will show that the user adds an event, indicated by `E`, and informs the user the total number of tasks
in the list. Also, the date will be printed in a reader-friendly format.

### `find [keyword 1] [keyword 2] ...` - Find a task

Search tasks by keywords. At least one keyword is required, and there is no constraint on the number of keywords. Each
keyword is separated by a blank space. The program returns all tasks that fully or partially match the given strings.
Partial matches are ones that contain at least one of the given keywords, but not all keywords; full matches are ones
that contain all given keywords.

Example of usage:

`find Oct 2022`

Expected outcome:

```
Here are the fully matched tasks in your list:
1. [E][ ] event (from: Oct 22 2021 to: Oct 22 2022)

Additionally, here are some partially matched tasks:
1. [D][ ] test (by: Oct 22 2922)
```

The search is based on the task string printed in task list, which might include the dates of the task, other than task
name. `[D][ ] test (by: Oct 22 2922)` is a partial match because it does not contain the string `Nov` in the `find` 
command. However, `[E][ ] event (from: Oct 22 2021 to: Oct 22 2022)` is a full match because it contains all given
strings. 

### `delete [task index]` - Delete a task

Delete a task by index. The index of a task can be found in the task list given by the `list` command, and is assigned
in chronological order to when the tasks are added to the list. 

Example of usage:

`delete 1`

Expected outcome:

```
Noted. I've removed this task:
  [D][ ] test (by: Oct 22 2922)
Now you have 4 tasks in the list.
```

As the task `[D][ ] test (by: Oct 22 2922)` appears the first listed by `list`, it corresponds to the index `1`. When
a task is deleted, the program also shows the number of tasks left.

## Command Summary
| Action              |                        Format                        |                                        Examples |
|:--------------------|:----------------------------------------------------:|------------------------------------------------:|
| List tasks          |                         list                         |                                            list |
| Add a todo task     |                   todo [task name]                   |                                       todo task |
| Add a deadline task |       deadline [task name] /by [deadline date]       |                     deadline task /by 2023-2-14 |
| Add an event        | event [event name] /from [start date] /to [end date] | event recess week /from 2023-2-20 /to 2023-2-26 |
| Find a task         |                 find [keyword 1] ...                 |                                find recess 2022 |
| delete a task       |                    delete [index]                    |                                       delete 10 |

## FAQ
If you have any issues and enquiries, please contact us at `bo.ai@u.nus.edu`.
