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
```text
Here are the tasks in your list:
1. [T][ ]: borrow book
2. [D][X]: return book (by: Oct 15 2019 12:00)
3. [E][ ]: project meeting (at: Mon 2-4pm)
```

---

### Add new Task

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
```

This displays all tasks in the list that contain the keywords in
their descriptions.

---

### `mark` - Mark Task as done

Given an index, mark the corresponding task as done.

*Index* refer to the **numerical** index as indicated by the response to the `list` command.

#### Example

`mark 1`

Expected Outcome:
```text
Nice! I've marked this task as done:
[T][X]: borrow book
```

---

### `unmark` - Mark Task as undone

Given an index, mark the corresponding task as undone.

*Index* refer to the **numerical** index as indicated by the response to the `list`command.

#### Example
`unmark 1`

Expected Outcome:
```text
OK, I've marked this task as not done yet:
[T][ ]: borrow book
```

---

### `delete` - Delete Task

Given an index, delete the corresponding task.

*Index* refer to the **numerical** index as indicated in the response to the `list`command.

#### Example
`delete 1`

Expected Outcome:
```text
Noted. I've removed this task:
[T][ ]: borrow book
Now you have {num_of_tasks} tasks in the list.
```

---

### `bye` - Exit the app

You may either enter this command or close the window with the ❌ button.

#### Example

`bye`

Expected outcome:

```text
Bye~ Hope to see you again soon:)
```

@@author lxz333-reused
Reused from https://github.com/SeeuSim/ip/blob/master/docs/README.md with modifications.

