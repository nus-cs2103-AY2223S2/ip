# DUKE MK-II User Guide
## Features 
- Keep track of normal todo tasks
- Keep track of tasks with deadlines
- Keep track of events with a start date and time as well as end date and time
- Retrieve an overview of all tasks
- Find tasks in the list
- Save all tasks into an editable save file

### Tracking Todo Tasks
Todo tasks can be quickly added and deleted with no need to specify any date and time. In addition, these tasks
can either be marked as done or undone.

### Tracking Deadlines
Deadlines can be easily added and deleted, specifying an end date and time. Deadlines can be either be marked done 
or undone.

### Tracking Events
Events can be easily added and deleted, specifying a start date and time, as well as an end date and time. Events can
also be marked as done or undone.

### Getting An Overview Of All Tasks
Users can quickly list out all added tasks and get an overview of whether they are todo tasks, deadlines or events.
Users can also find out whether each task is completed or not.
---
## Usage
### `help` - Show all possible commands

Shows all command formats.

Example of usage:

`help`

Expected outcome:

Displays line by line of all accepted command formats for manipulating tasks.

```
DUKE MK-II Command Formats:
1) Add a todo task: todo <task description>
2) List all tasks: list
3) Mark a task as completed: mark <task number>
4) Unmark a task to incompleted: unmark <task number>
5) Delete a task: delete <task number>
6) Add a task with end deadline: deadline <task description> /by <YYYY-MM-DD HH:MM>
7) Add a task with start and end date: event <task description> /from <YYYY-MM-DD HH:MM> 
   /to <YYYY-MM-DD HH:MM>
8) Find a task: find <task description>
```
---
### `list` - Show all tasks

Shows all tasks, task types, completion status and relevant date and time (if any).

Example of usage: 

`list`

Expected outcome:

Displays line by line of all tasks and their details.

```
Here are the tasks in your list:
1. [T] [X] borrow book
2. [D] [ ] return book (by: Apr 5 2022 1:00 AM)
3. [T] [ ] buy grocery
```
---
### `bye` - Quit application

Shows all tasks, task types and their completion status.

Example of usage:

`bye`

Expected outcome:

Alternative way of quiting the application, other than clicking the exit button.

---
### `find` - Find specific tasks

Shows all tasks with a matching user specified substring in the task description.

Example of usage:

`find bo`

Expected outcome:

A list of tasks that contains a user specified substring.

```
Here are the matching tasks in your list:
1. [T] [X] borrow book
2. [D] [ ] return book (by: Apr 5 2022 1:00 AM)
```
---
### `todo` - Add a specific task

Add a todo task with the given description.

Example of usage:

`todo buy bread`

Expected outcome:

A response message from Duke on successful adding of a todo task.

```
Got it. I've added this task:
[T] [ ] buy bread
Now you have (some number) tasks in the list.
```
---
### `deadline` - Add a specific deadline

Add a deadline task with the given description as well as a date and time.

Example of usage:

`deadline write essay /by 2023-03-25 10:00`

Expected outcome:

A response message from Duke on successful adding of a deadline task.

```
Got it. I've added this task:
[D] [ ] write essay (by: Mar 25 2023 10:00 AM)
Now you have (some number) tasks in the list.
```
---
### `event` - Add a specific event

Add an event task with the given description, together with start date and time, as well as end date and time.

Example of usage:

`event attend lecture /from 2023-03-26 11:00 /to 2023-03-26 13:00`

Expected outcome:

A response message from Duke on successful adding of an event task.

```
Got it. I've added this task:
[E] [ ] attend lecture (from: Mar 26 2023 11:00 AM to: Mar 26 2023 1:00 PM)
Now you have (some number) tasks in the list.
```
---
### `delete` - Remove a specific event

Remove a task from the list.

Example of usage:

`delete 6`

Expected outcome:

A response message from Duke on successful removal of any tasks.

```
Noted, I've removed this task:
[E] [ ] attend lecture (from: Mar 26 2023 11:00 AM to: Mar 26 2023 1:00 PM)
Now you have (some number) tasks in the list.
```
---
### `mark` - Mark a task as complete

Mark a user specified task as completed.

Example of usage:

`mark 1`

Expected outcome:

A response message from Duke on successful completion marking of a task.

```
Nice! I've marked this task as done:
[D] [X] borrow book (by: Apr 5 2022 1:00 AM)
```
---
### `unmark` - Mark a task as incomplete

Mark a user specified task as uncompleted.

Example of usage:

`unmark 1`

Expected outcome:

A response message from Duke on successful completion unmarking of a task.

```
OK, I've marked this task as not done yet:
[D] [] return book (by: Apr 5 2022 1:00 AM)
```
