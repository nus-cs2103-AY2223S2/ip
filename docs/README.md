# User Guide

## The TheshBot

> "Your mind is for having ideas, not holding them." - David Allen ([source](https://nus-cs2103-ay2223s2.github.io/website/schedule/week4/project.html))

Quick and easy way to remember to the things you have in your life 

## Features 

### Adding Tasks

You can add tasks such as:
- Todos
- Deadlines
- Events

### Marking Tasks

You can mark these tasks as done when you've completed them. 
Furthermore, you can unmark them if needed

### Finding Tasks

You can find tasks based on certain keywords

### Deleting Tasks

Supposed, you are done with a certain task, you can delete it from the list!

## Usage

### `deadline` - Add a deadline

Add a deadline to the list of tasks. Requires a specification of the date-time for the deadline, in the format of YYYY-MM-DD.

Example of usage: 

`deadline (CS2103 project) /by (2023-02-17)`

Expected outcome:

Adds a task for CS2103 project by the deadline on 17th of February 2023. It is marked not done by default.

```
Got it. I've added this task:
[D][] 2103 project (by: Feb 17 2023)
Now you have ... tasks in the list.
```

### `todo` - Add a ToDo task

Add a ToDo type task to the list of tasks.

`todo Buy eggs from the supermarket`

A new todo task to buy eggs from the supermarket is added to the list.
It is marked not done by default.

```
Got it. I've added this task:
[T][] Buy eggs from the supermarket
Now you have ... tasks in the list.
```

### `event` - Add an Event

Add an event to the list of tasks. Requires a specification of the
date-time for the start (from) and end (to) of the event, in one
of the supported format yyyy-MM-dd

Example of usage:

`event Mario Party with the boys /from 2023-02-21 2300 /to 2023-02-22 0200`

Adds an event to play Mario Party with the boys, from 21 Feb 23 to 22 Feb
23.

```
New task added:
[E][ ] Mario Party with the boys (from: Feb 21 2023, to: Wed, Feb 22 2023)
You now have 13 task(s) in your list.
Changes are saved to storage.
```

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
06:00PM, to: Mon, 20 Feb 23)
3: [T][] Buy some food for my pet chicken

You have 3 tasks listed.
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
