# User Guide

## Features

### Keep track of a list of tasks and do operations on them

*WindyCall* will help the user to keep track of a list of tasks. Tasks can be in the form of `todo`, `deadline`, or `event`.
This list of tasks can be added, deleted and marked as done or undone. They can be also added with tags specified by user.
If properly triggered, *WindyCall* will record the list of tasks locally whenever it is updated and load this record 
when it is opened next time.

## Usage

Please note that all the sample output below are displayed as text sent from the *WindyCall* in GUI application.

### `list` - List out tasks

By entering the `list` command, *WindyCall* will print out a list of current tasks *(both marked as done and undone)*.

Note that the format for a single task would be: `[Tasktype][mark status][#tag] description (#time)`

#### Example

Command: `list`

```
Here are the tasks in your list:
1. [T][ ] borrow book
2. [D][X] return book (by: 14 October 2023)
3. [E][X][#study] project meeting (from: 14 March 2023 to: 15 March 2023)
```

### Add new Task

Depending on the nature of the task, you can add `Todo`, `Deadline` and `Event`.

- `Todo`: `todo {description}`. For example, `todo buy milk` will add a new `Todo` with description `buy milk`
  to the task list;
- `Deadline`: `deadline {description} /by {time}`. For example, `deadline homework /by 2019-10-10` will add a new
    `Deadline` with description `homework` at the date specified as `Oct 10 2019`.
- `Event`: `event {description} /from {time} /to {time}`. For example, `event party /from 2019-10-10 /to 2019-10-11` will add a new `Event` with
  description `party` at the date specified from `Oct 10 2019` to `Oct 11 2019`;


Please note that *WindyCall* only supports the time format of `yyyy-MM-dd` and `dd/mm/yyyy`. Otherwise, the date cannot be recognised.

#### Example

Command: `todo buy milk`

```
Got it. I've added this task:
[T][ ] buy milk
```

Command: `deadline homework /by 2019-10-10`

```
Got it. I've added this task:
[D][ ] homework (by: 10 October 2019)
```

Command: `event party /from 2019-10-10 /to 2019-10-11`

```
Got it. I've added this task:
[E][ ] party (from: 10 October 2019 to: 11 October 2019)
```

### `mark` - Mark Task done and undone

`mark {index}` will mark the task in the `index` position to be done.
Similarly, `unmark {index}` will mark the task in the `index` position to be undone.

#### Example

Command: `mark 1` assuming the first item is `[T][ ] buy milk`

```
Nice! I've marked this task as done:
[T][X] buy milk
```

Command: `unmark 1` after the previous command.

```
Nice! I've marked this task as not done yet:
[T][ ] buy milk
```

### `delete` - Delete Task

`delete {index}` will delete the task at the `index` position.

#### Example

Command: `delete 5` assuming the 5-th item is `[T][ ] buy milk`

```
Noted. I've removed this task:
[T][ ] buy milk
Now you have 4 tasks in the list.
```

### `Find` - Search in list of Tasks

`find {argument}` will find all the tasks with `{argument}` included in its description.

#### Example

Command: `find midterm` assuming there is one task as `[T][ ] prepare for CS2105 midterm` and there is no other task
which contains the keyword `midterm` in its description.

```
Here are the tasks in your list:
1. [T][ ] prepare for CS2105 midterm
```

### `Tag` - Add tag to Task

`tag {index} {name}` will add task at the `index` position with tag `name`

#### Example

Command: `tag 1 study` assuming the first task is `[T][] prepare for CS2105 midterm`

```
I've successfully added tag #study to this task:
[T][][#study] prepare for CS2105 midterm
```

