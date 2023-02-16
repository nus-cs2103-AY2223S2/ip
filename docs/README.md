# Duke Todo Bot

## Features 

### Adding Tasks

3 types of Tasks (Todo, Deadline, Event).\
- Track your daily todos with Duke Todo Bot.\
- Keep track of your dates with Deadline and Event!

### Marking/Unmarking Tasks

(Un)Mark tasks as done for easy tracking.

### Deleting Tasks
Delete tasks that you no longer want to track.

### Finding Tasks
Search up related tasks conveniently by keywords.

### Snoozing Tasks
Push back or bring forward tasks dates.\
Only works for Deadline and Event.

### Viewing Tasks
See all your tasks at a glance.

## Usage

### `Todo/Deadline/Event` - Add a task.

Add a task to your list of tasks.

Example of usage:

`todo (task name)`\
`deadline (task name) /by (date)`\
`event (task name) /from (date) /to (date)`\
*- date in YYYY-MM-DD form*

### `Mark/Unmark` - Mark a task as done/undone.

Mark a task as done.\
Unmark a task as undone.

Example of usage:

`mark (task number)`\
`unmark (task number)`

Expected outcome:

The task will be tagged as done or undone.

### `Delete` - Delete a task.

Remove the task from your list.

Example of usage:

`delete (task number)`

Expected outcome:

The task will be deleted from the list forever.

### `Find` - Find related tasks.

Search task by keyword.

Example of usage:

`find (keyword)`

Expected outcome:

The tasks containing the keyword.

```
1. [ ] borrow keyword
2. [x] watch keyword
```

### `Snooze` - Change date of a task.

Change date of a Deadline or Event task.

Example of usage:

`snooze (task number) (new task date)`

Expected outcome:

The date of the task will be changed to the new date.

### `List` - View all tasks.

Present a list of all tasks.

Example of usage:

`list`

Expected outcome:

```
1. [ ] borrow book
2. [x] watch movie
3. [ ] project proposal (by: 5 Aug 2023)
```