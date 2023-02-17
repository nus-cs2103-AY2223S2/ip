# User Guide

## Features

### Keep track of a list of tasks

*Duke* Helps users manages a range of tasks, including `todo`, `event`, or `deadline`.
Users will be able to add a new task, delete a task, and mark a task as done or undone. Also, *Duke* has the storage function, so it loads the
tasks from data file in disk every time it initializes.

## Usage

The output examples are delivered to users as text through *Duke* in GUI application.

### `list` - List out tasks

When receive `list` command, *Duke* gives a list of current tasks, with types [T] todo, [D] deadline, and [E] event, and mark [X] and unmark [  ] tags.

#### Example

Command: `list`

```
Here are the tasks in your list:
1. [T][ ]: watch lecture
2. [D][X]: finish essay (by: 2023-02-15T0000)
3. [E][ ]: CS2103 final (from: 2023-04-26T0900 to: 2023-04-22T1030)
```

### Add new Task

There are three categories of tasks that can be added: `Todo`, `Event` and `Deadline`.

- `Todo`: `todo {description}`. For instance, `todo watch lecture` will add a new `Todo` with string description `watch lecture`
  in the task list;
- `Event`: `event {description} /from {time} /to {time}`. For example, `CS2103 final /from 2023-04-26 0900 /to 2023-04-22 1030` will add a new `Event` with
  description `CS2103 final` with the start time and end time specified; Note that Duke only recognizes date input in form of `yy-mm-dd hhmm`.
- `Deadline`: `deadline {description} /by {time}`. For example, `finish essay /by 2023-02-15 0000` adds a new
  `Deadline` with the description `finish essay` at the date specified as `2023-02-15 0000`. Again, the date input should be in the form of `yy-mm-dd hhmm`.

#### Example

Command: `todo watch lecture`

```
Got it. I've added this task:
[T][ ]: watch lecture
```

Command: `event CS2103 final /from: 2023-04-26 0900 /to 2023-04-22T1030`

```
Got it. I've added this task:
[E][ ]: CS2103 final /from: 2023-04-26 0900 /to 2023-04-22 1030)
```

Command: `deadline finish essay /by 2023-02-15 0000`

```
Got it. I've added this task:
[D][ ]: finish essay (by: 2023-02-15T0000)
```

### `mark` - Mark Task done and undone

The command `mark {index}` can mark the task numbered at `index` position as done.
The command, `unmark {index}` can mark the task in the `index` position as undone.

#### Example

Command: `mark 1` mark the first task in the list of the previous example `[T][ ]: watch lecture`

```
Nice! I've marked this task as done:
[T][X]: watch lecture
```

Command: `unmark 1` can mark the same task as undone.

```
Nice! I've marked this task as not done yet:
[T][ ]: watch lecture
```

### `delete` - Delete Task

`delete {index}` can delete the task in the position of the `index`.

#### Example

Command: `delete 6` if the 6-th item is `[D][ ] finish quiz (by: 2023-12-12 0000)`

```
Noted. I've removed this task:
[D][ ]: finish quiz (by: 2023-12-12T0000)
Now you have 8 tasks in the list.
```

### `Find` - Search in list of Tasks

`find {keyword}` will find tasks with descriptions of more than two same characters in order with `{keyword}`.

#### Example

Command: `find part` if there is a task `[T][ ]: party` and no other task has three characters matching with keyword `part` in its description.

```
Here are the matching tasks in your list:
1. [T][ ]: party
```

### `SortEvent` - Sort task of event type

`SortEvent` command will get *Duke* to print a list of events sorted by their start time.

#### Example

Command: `sortEvent` 

```
Here are your events sorted by time:
[E][ ] discussion (from: 2023-02-17T16:00 to:
2023-02-17T17:30)
[E][ ] shopping (from: 2023-02-19T12:00 to:
2023-02-19T13:00)
[E][ ] sing (from: 2023-02-20T00:00 to: 2023-02-20T01:00)
```

### `SortDeadline` - Sort task of deadline type

`SortDeadline` command will get *Duke* to print a list of deadlines sorted by their due time.

#### Example

Command: `sortDeadline`

```
Here are your deadlines sorted by time:
[D][] code (by: 2023-02-08T12:10)
[D][ ] ip (by: 2023-02-17T00:00)
[D][] essay (by: 2023-02-18T00:10)
```

### `Help` - Search sample commands

`help` command can get access to a list of sample commands for in-app reference.

### Exit from the Duke program

`bye` command can exit from the program with the current task saved in duke.txt data file.
When restart the app, *Duke* loads tasks from the disk and performs its functions as usual.

#### Example

Command: `bye`

```
The programme terminates and the window automatically closes
```


