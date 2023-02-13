# User Guide

## Features

---
### Add, Delete, Edit, List, Find

Track a list of tasks, which can be any of these three types - Todo, Deadline, and Event.
Use standard operations to manipulate the list of tasks to create new, update, and show
the currently stored list of tasks. Search tasks by keyword using the 'Find' function.

### Sorted Summary

See a reader-friendly overview of the tasks, sorted into task types and ordered by 
time.

### Mark as done, Unmark done status

Toggle between the state of done-ness of each task.

### Storage

Automatic saving of your list of tasks whenever changes are made. Load in saved data
whenever the app is started.

---

## Usage

---
### `todo` - Add a ToDo task

Add a ToDo type task to the list of tasks.

`todo Buy eggs from the supermarket`

A new todo task to buy eggs from the supermarket is added to the list.
It is marked not done by default.

```
New task added:
[T][ ] Buy eggs from the supermarket
You now have 1 task(s) in your list.
Changes are saved to storage.
```

---

### `deadline` - Add a Deadline

Add a deadline to the list of tasks. Requires a specification of the 
date-time for the deadline, in one of the supported formats:
- yyyy-MM-dd HHmm
- yyyy/MM/dd HHmm
- ISO 8601 format: YYYY-MM-DD[T]HH:mm:ss

Example of usage:

`deadline Submit visa application /by 2023-02-20 2100`

Adds a task to submit a visa application by the deadline on Mon, 20 Feb 23 9PM.
It is marked not done by default.

```
New task added:
[D][ ] Submit visa application (by: Mon, 20 Feb 23 09:00PM)
You now have 13 task(s) in your list.
Changes are saved to storage.
```

---

### `event` - Add an Event

Add an event to the list of tasks. Requires a specification of the
date-time for the start (from) and end (to) of the event, in one
of the supported formats:
- yyyy-MM-dd HHmm
- yyyy/MM/dd HHmm
- ISO 8601 format: YYYY-MM-DD[T]HH:mm:ss

Example of usage:

`event Mario Party with the boys /from 2023-02-21 2300 /to 2023-02-22 0200`

Adds an event to play Mario Party with the boys, from 21 Feb 23 11pm to 22 Feb
23 2am.

```
New task added:
[E][ ] Mario Party with the boys (from: Tue, 21 Feb 23 
11:00PM, to: Wed, 22 Feb 23 02:00AM)
You now have 13 task(s) in your list.
Changes are saved to storage.
```
---

### `summary` - View an organised overview of the list

Shows a summary of all tasks listed. Tasks divided among their types. Within
each type, tasks are sorted from earliest to latest (if they are associated
with date-time), and then by lexicographical order. Note that the tasks'
index are not shown (use `find` or `list` instead). 

Example of usage:

`summary`

All tasks listed according to their types, and grouped by their type.

```
Good day sir, here's a summary of what's on your plate.

Events - 3 items
[E][] Midterm exams (from: Mon, 20 Feb 23 12:00PM,
to: Mon, 20 Feb 23 02:00PM)
[E][] Dinner with pals (from: Mon, 20 Feb 23 06:00PM,
to: Mon, 20 Feb 23 08:00PM)
[E][] My presidential inauguration speech (from: Wed,
01 Mar 23 03:00PM, to: Wed, 01 Mar 23 05:00PM)

Deadlines - 4 items
[D][X] Submit iP (by: Fri, 17 Feb 23 11:59PM)
[D][X] Pay the bills (by: Sat, 18 Feb 23 06:00PM)
[D][] Send out invitations (by: Sat, 18 Feb 23 08:00PM)
[D][] Settle the score with Tom (by: Sun, 19 Feb 23
09:00PM)

Todo - 3 items
[T][] Buy some food for my pet chicken
[T][X] Feed my pet chicken
[T][X] Throw out the garbage

Use 'list', or 'find <keyword>' to get a specific task and
its index to modify at.
```

---

### `list` - View all items in input order

Shows all tasks that are in the list. Tasks are displayed in the sequence 
that they have been input. Each task is labelled with their actual index 
number.

Example of usage:

`list`

All tasks are listed in input order, with their index number.

```
Eh this is what you've written down so far:
1: [D][X] Submit iP (by: Fri, 17 Feb 23 11:59PM)
2: [E][] Dinner with pals (from: Mon, 20 Feb 23
06:00PM, to: Mon, 20 Feb 23 08:00PM)
3: [T][] Buy some food for my pet chicken
4: [E][] My presidential inauguration speech (from: Wed,
01 Mar 23 03:00PM, to: Wed, 01 Mar 23 05:00PM)
5: [T][X] Feed my pet chicken
6: [T][X] Throw out the garbage
7: [E][] Midterm exams (from: Mon, 20 Feb 23 12:00PM,
to: Mon, 20 Feb 23 02:00PM)
8: [D][] Settle the score with Tom (by: Sun, 19 Feb 23
09:00PM)
9: [D] [X] Pay the bills (by: Sat, 18 Feb 23 06:00PM)
10: [D][] Send out invitations (by: Sat, 18 Feb 23
08:00PM)

You have 10 tasks listed.
```
---

### `find` - Search for a specific task description

Finds a task in the list whose description matches the keyword(s), and shows
its information, including its index number.

Example of usage:

`find chicken`

Lists all tasks that contain 'chicken' in their description, and shows
their index number.

```
Here's the tasks matching 'chicken':
3: [T][] Buy some food for my pet chicken
5: [T][X] Feed my pet chicken

```

---

### `edit` - Change a task's field

Changes the content of a task at a specific index number,
given the specific fields to change.

Does not support changing the task type. For example, a Todo cannot be
edited to become a Deadline.

Example of usage:

`edit 8 /description Make friends with Tom`

Expected outcome:

Description of the Deadline changed as depicted below. Note that the actual
deadline date-time has not changed (specify `/by <new_deadline>` to change).

```
Edited task at index 8!
Old:
[D][] Settle the score with Tom (by: Sun, 19 Feb 23
09:00PM)

New:
[D][] Make friends with Tom (by: Sun, 19 Feb 23 09:00PM)
Changes are saved to storage.
```
Note that the index of the task does not change.

Using the command `find Tom`:
```
Here's the tasks matching 'tom':
8: [D][] Make friends with Tom (by: Sun, 19 Feb 23
09:00PM)
```

---

### `delete` - Remove a task from the list

Removes a task given at an index number from the list. If it doesn't exist,
does nothing.

Example of usage:

`delete 8`


The task with index number 8 is removed from the list. The deleted task is
printed for reference.

```
Removed 'Make friends with Tom' from the list. You now have
10 task(s) left.
Changes are saved to storage.
```

---

### `mark`/`unmark` - Mark or Unmark a task as done

Marks or unmarks a task at an index number to be done.
If the task is already done or not done, notifies the user.

Example of usage:

`mark 3`

Marks the task at index 3 (to buy some food for pet chicken) as done.

```
Marked 'Buy some food for my pet chicken' as done!
Changes are saved to storage.
```

If the task was already done, shows the following message instead:

```
'Buy some food for my pet chicken' is already marked as done!
```

