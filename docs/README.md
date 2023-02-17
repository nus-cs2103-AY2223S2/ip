# User Guide

## Features

---

### Add Tasks

You can type `todo` tasks, `deadline` tasks, or `event` tasks into the text box, and 
click on `Send` - The app will then store them for you!

### View Tasks

You can ask the app to show the tasks already stored. `Deadline` tasks will be displayed in 
chronological order.

### Find Tasks

You can search for tasks containing certain keywords.

### Manipulate Tasks

You can *mark* tasks, *unmark* them , or *delete* them by typing in corresponding numbers.

### Store Tasks

The app can store tasks in the current list when it is closed and load this record
when it starts again.


## Usage

---

### `list` - List all the tasks

By typing in the `list` command, *Duke* will print out a list of current tasks *(with the mark of done or undone)*.
`Deadline` tasks will be displayed in chronological order.

#### Example

Command: `list`

Expected outcome:
```
Here are the tasks in your list:
1. [T][ ]: borrow book
2. [D][X]: return book (by: Oct 15 2019 12:00)
3. [E][ ]: project meeting (at: Mon 2-4pm)
```

---

### Add new tasks

---

### `todo` - Add a Todo task

With the command "todo {task}", *Duke* adds a `Todo` task to the list.

#### Example

`todo {task_description}`

Expected outcome:

```text
Got it. I've added this task: 
  [T][ ] {task_description}
Now you have {num_of_tasks} tasks in the list:D
```

This indicates that the *Todo* task has been successfully added to the list.

### `deadline` - Add a Deadline task

*Duke* adds a `Deadline` task to the list with the given description and deadline.
It only accepts datetime inputs in `yyyy-MM-dd HHmm` format.

#### Example

`deadline {task_description} /by yyyy-MM-dd HHmm`

Expected outcome:

```text
Got it. I've added this task: 
  [D][ ] {task_description} (by: MMM dd yyyy HH:mm)
Now you have {num_of_tasks} tasks in the list.
```

This indicates that the *Deadline* has been successfully added to the list.

### `event` - Add a Event task

*Duke* adds an `Event` task to the storage with the given description, and start/end time. 

#### Example

`event {task_description} /from {start time} /to {end time}`

Expected outcome:

```text
Got it. I've added this task: 
  [E][ ] {task_description} (from: {start time} to: {end time})
Now you have {num_of_tasks} tasks in the list.
```

This indicates that the *Event* has been successfully added to the list.

---

### `find` - Find task(s)

*Duke* searches for tasks in the list whose descriptions contain the certain keywords.
Keywords cannot be empty queries.

#### Example

`find eat assignment`

Expected outcome:

```text
1. [T][X] eat cake
2. [T][ ] eat Double McSpicy
3. [T][ ] do math assignment
...
```

This displays all tasks in the list that contain the keywords in
their descriptions.

---

### `mark` - Mark task(s) as completed

Given an index, multiple indexes, a range of indexes, or a task description
filter or a date on which tasks occur, marks those task(s) as completed.

*Indexes* refer to the **numerical** index as indicated by the response to the
[`list`](#list---view-all-tasks) command.

Examples of usage:
- Mark Individual task: `mark 1`
- Mark Multiple tasks: `mark 1 3 5`
- Mark Range of tasks: `mark 1 - 3` OR `mark 1-3` OR `mark 1 to 3`
- Mark tasks occurring on a Date: `mark /on 2/2/23` OR `mark -o 2/2/23` OR
  `mark --o 2/2/23`
- Mark tasks containing a Description: `mark /contains desc` OR
  `mark -c desc` OR `mark --c desc`
- Mark All tasks: `mark /all` OR `mark -a` or `mark --a`

Expected Outcome:
```text
Got it. I've marked these {num_of_tasks} tasks as done:
[T][X] Task XXX
[D][X] Deadline YYY (by: Feb 02 2023 11:59PM)
[E][X] Event ZZZ (from: Feb 02 2023 12:00PM to: Feb 02 2023 1:00 PM)
...
```

Marks all the *specified* tasks in the storage as completed. If there are
errors in the flags passed to the command, it will indicate them after
listing the tasks successfully marked as completed.

---

### `unmark` - Mark task(s) as ***NOT*** completed

Given an index, multiple indexes, a range of indexes, or a task description
filter or a date on which tasks occur, marks those task(s) as **not**
completed.

*Indexes* refer to the **numerical** index as indicated by the response to the
[`list`](#list---view-all-tasks) command.

Examples of usage:
- Unmark Individual task: `unmark 1`
- Unmark Multiple tasks: `unmark 1 3 5`
- Unmark Range of tasks: `unmark 1 - 3` OR `unmark 1-3` OR `unmark 1 to 3`
- Unmark tasks occurring on a Date: `unmark /on 2/2/23` OR `unmark -o 2/2/23`
  OR `unmark --o 2/2/23`
- Unmark tasks containing a Description: `unmark /contains desc` OR
  `unmark -c desc` OR `unmark --c desc`
- Unmark All tasks: `unmark /all` OR `unmark -a` or `unmark --a`

Expected Outcome:
```text
Got it. I've marked these {num_of_tasks} tasks as not completed:
[T][ ] Task XXX
[D][ ] Deadline YYY (by: Feb 02 2023 11:59PM)
[E][ ] Event ZZZ (from: Feb 02 2023 12:00PM to: Feb 02 2023 1:00 PM)
...
```

Marks all the *specified* tasks in the storage as not completed. If there are
errors in the flags passed to the command, it will indicate them after
listing the tasks successfully marked as not completed.

---

### `delete` - Delete task(s)

Given an index, multiple indexes, a range of indexes, or a task description or
a date on which those tasks occur, deletes those task(s).

*Indexes* refer to the **numerical** index as indicated in the response to the
[`list`](#list---view-all-tasks) command.

Examples of usage:
- Delete Individual task: `delete 1`
- Delete Multiple tasks: `delete 1 3 5`
- Delete Range of tasks: `delete 1 - 3` OR `delete 1-3` OR `delete 1 to 3`
- Delete tasks occurring on a Date: `delete /on 2/2/23` OR `delete -o 2/2/23`
  OR `delete --o 2/2/23`
- Delete tasks containing a Description: `delete /contains desc` OR
  `delete -c desc` OR `delete --c desc`
- Delete All tasks: `delete /all` OR `delete -a` or `delete --a`

Expected Outcome:
```text
Got it. I've deleted these {num_of_tasks} tasks:
[T][X] Task XXX
[D][X] Deadline YYY (by: Feb 02 2023 11:59PM)
[E][X] Event ZZZ (from: Feb 02 2023 12:00PM to: Feb 02 2023 1:00 PM)
...
Now you have {num_of_tasks} tasks in the list.
```

Deletes all the *specified* tasks in the storage. If there are
errors in the flags passed to the command, it will indicate them after listing
the successfully deleted tasks.

---

### `bye` - Exit the app

You may either enter this command or close the window with the ❌ button.

#### Example

`bye`

Expected outcome:

```text
Bye~ Hope to see you again soon:)
```


