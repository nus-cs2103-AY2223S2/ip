# Joe not Duke - User Guide

>Listen up, I am not Duke, but Joe. I do what Duke does but I'm called Joe.

Itchy fingers ah? Want to type things down for fun? Ok then tell Joe.

<p align="center">
<img src="Ui.png"/>
</p>

## Table of Contents

---

- **[Features](#features)**
- **[Usage](#usage)**

## Features

---

### Add Tasks

You type a task(todo, deadline, event) into the text box in the correct format,
and Duke will save it for you. I mean Joe.

### View Tasks

You can list the tasks you have entered. Joe will show it to you.

### Sort Tasks

You can sort the tasks you have stored alphabetically.

### Mark and unmark Tasks

You can mark tasks as *done*, mark them as *undone*, or *delete* them.

### Save Tasks

The app stores tasks as a `.txt` file as `duke.txt` in the same
directory.

## Usage

---

### `todo {Description}` - Add a Todo task

Adds a `Todo` task to the storage with the given description

Example of usage:

`todo Make my bedsheets`

Expected outcome:

```text
Added: 
  [T][ ] Make my bedsheets
Now you have {num_of_tasks} tasks in the list.
```

This indicates that the *Todo* task has been added to the task list.

### `deadline {description} /by {d/M/yyyy HHmm}` - Add a Deadline task

Adds a `Deadline` task to the storage with the given description, and deadline.
Accepts datetime inputs in `d/M/yyyy HHmm` format (e.g. `14/02/2023 1800`).

Example of usage:

`deadline Eat dinner /by 14/02/2023 1800`

Expected outcome:

```text
Added: 
  [D][ ] Eat dinner by: Tue, 14 Feb 2023 06:00PM
Now you have {num_of_tasks} tasks in the list.
```

This indicates that the *Deadline* has been added to the task list.

### `event {description /from {d/M/yyyy HHmm}  /to {d/M/yyyy HHmm}` - Add a Event task 

Adds a `Event` task to the storage with the given description, start
datetime and end datetime. Accepts datetime inputs in `d/M/yyyy HHmm`
format (e.g. `14/02/2023 1800`).


Example of usage:

`event Sing birthday song /from 16/02/2023 0000 /to 16/02/2023 0001`

Expected outcome:

```text
Added: 
  [E][ ] Sing birthday song from: Thu, 14 Feb 2023 12:00AM to: Thu, 14 Feb 2023 12:01AM
Now you have {num_of_tasks} tasks in the list.
```

This indicates that the *Event* has been added to the task list.

### `list` - View all tasks

Views all tasks in the storage. If there are none, consider adding
a task.

Example of usage:

`list`

Expected outcome:

```text
1. [T][ ] Make my bedsheets
2. [D][ ] Eat dinner by: Tue, 14 Feb 2023 06:00PM
3. [E][ ] Sing birthday song from: Thu, 14 Feb 2023 12:00AM to: Thu, 14 Feb 2023 12:01AM
```

This displays the tasks in the storage, as they are currently stored.

### `sort` - Sort the task list

Sorts the task list

Example of usage:

`sort`

Expected outcome:

```text
1. [D][ ] Eat dinner by: Tue, 14 Feb 2023 06:00PM
2. [T][ ] Make my bedsheets
3. [E][ ] Sing birthday song from: Thu, 14 Feb 2023 12:00AM to: Thu, 14 Feb 2023 12:01AM
```

This sorts all tasks in the task list, saves it storage and displays it.

### `find {name}` - Find the task with a similar description

Sorts the task list

Example of usage:

`find Eat`

Expected outcome:

```text
1. [D][ ] Eat dinner by: Tue, 14 Feb 2023 06:00PM
```

This finds all tasks in the task list, with a similar name and displays it.


### `mark/unmark {index}` - Mark task as completed

Given an index marks that task as completed.

*Indexes* refer to the **numerical** index as shown by the `list`
command.

Examples of usage:

`mark 1`
`unmark 1`

Expected Outcome:
```text
Nice! I've marked this task as done:
  [D][X] Eat dinner by: Tue, 14 Feb 2023 06:00PM
```

Marks the task at that index as done.

### `delete` - Delete task(s)

Given an index marks that task as completed.

*Indexes* refer to the **numerical** index as shown by the `list`
command.

Examples of usage:

`delete 1`
`unmark 1`

Expected Outcome:
```text
Noted. I've removed this task:
  [D][X] Eat dinner by: Tue, 14 Feb 2023 06:00PM
Now you have {num_of_tasks} in the list.
```

Given an index delete that task.

### `bye` - Exit the app

You may either enter `bye` or close the window with the close
button on the top right of your screen.

Examples of usage:

`bye`

Expected outcome:

```text
Bye. Hope to see you again soon!
```

Duke... I mean Joe saying bye when the app is closing,
and shuts down the app.
