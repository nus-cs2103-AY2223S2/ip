# User Guide

## Features 

### Support for events and deadlines
Apart from regular todos, this app also supports deadlines - tasks with a due date and time, and events - tasks with a start and end date and time.

### Persistence
Your task list is stored to persistent storage, so you can see your tasks from an earlier session.
### Sorting and filtering functionality
Search for tasks containing a word, and sort your deadlines by how much time is left to the due date.

## Usage

### `todo <description>` - Create a Todo

Create a simple task with the supplied description.

Example of usage: 

`todo ip submission`

Expected outcome:
A new task with description "ip submission" is added to the task list.

Description of outcome: 
```
Got it. I've added this task:
[T][ ] ip submission
You have 1 task in your list.
```
### `deadline <description> \by <date> (<time>)` - Create a deadline

Create a task that has a date and time for its deadline, apart from having a description.

Example of usage:

`deadline ip submission \by 20/02/2023 2359`

Expected outcome:
A new task with description "ip submission" is added to the task list.

Description of outcome:
```
Got it. I've added this task:
[D][ ] ip submission (by: 20 Feb 2023 23:59)
Now you have 2 tasks on your list.
```
### `event <description> /from <start date> (<time>) /to <end date> (<time>)` - Create an event

Create a task that has a starting date-time as well as an ending date and time.

Example of usage:

`event ip submission window /from 19/02/2023 1200 /to 20/02/2023 2359`

Expected outcome:
Create an event with the given description, start and end dates.

Description of outcome:
```
Got it. I've added this task:
[D][ ] ip submission window (from: 19 Feb 2023 12:00 to: 20 Feb 2023 23:59)
Now you have 3 tasks on your list.
```
### `list (<date> <time>)` - List tasks

List all your tasks. The optional date and time argument can be used to list all tasks on or before the given time.

Example of usage:`list`

Expected outcome:
A new task with description "ip submission" is added to the task list.

Description of outcome:
```
Here are the tasks on your list:
1. [T][ ] ip submission
2. [D][ ] ip submission (by 20 Feb 2023 23:59)
...
```
Example 2:
`list 20/02/2023 2359`

Expected outcome:
List of all tasks due on or before 20/02/2023 2359. Note that todos will not be listed since they don't have an associated date.

### `delete <index>` - Delete a task
Deletes the task at the index-th position in the tasks currently being displayed.

Example of usage:`delete 1`

Expected outcome:
The first task in the displayed list is deleted.

Description of outcome:
```
Noted, I've removed this task:
[T][ ] ip submission
Now you have 2 tasks on your list.
```
### `mark/unmark <index>` - Mark a task as done or not done respectively
Marks the task at the index-th position in the list of tasks currently displayed as done/undone.

Example of usage: `mark 1`

Expected outcome:
The first task in the displayed list is marked as done.

Description of output:
```
Nice! I've marked this task as done.
[D][X] ip submission (by 20 Feb 2023 23:59)
```
### `find <substring>` - Find task(s) using their description
Displays all tasks (ie todos, deadlines and events) that contain the given substring.


Example of usage: `find ip`

Expected outcome:
All tasks that start with 'ip' are displayed.

Description of output:
```
Here are the tasks on your list:
1. [T][ ] ip submission
... a list of tasks containing ip
```
### `sort` - Sort deadlines by their deadline
Displays all your deadlines starting from the earliest and going to the latest.

Example of usage: `sort`

Expected outcome:
The first task in the displayed list is marked as done.

Description of output:
```
Here are the tasks on your list:
1. [D][ ] deadline 1 (by 21 Feb 2023 23:59)
2. [D][ ] deadline 2 (by 22 Feb 2023 23:59)
...
```
### `bye` - Exit
Exit the Duke application. The tasks are stored to persistent memory, so you'll see them the next time you launch the app.
Expected output:
```
See you on the other side, human! Don't forget to bring a spacesuit!
```
