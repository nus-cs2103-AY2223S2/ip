# User Guide

## Features 

### Keep track of tasks (of type Deadline,ToDo, Event)

Each task has title and dates(if applicable) recorded on disk.

### Sort tasks

Tasks can be sorted according to the start dates(if applicable).

### Mark/Unmark tasks

Tasks can be marked as completed/incompleted.

### Delete tasks

Tasks can be deleted from disk.

## Usage

### `list` - List tasks

List out all tasks in the order they were recorded.

Example of usage: 

`list`

Expected outcome (example):


```
Here are the tasks in your list:
1.[T][] read up
2.[E][] return book (from: 1400 Dec 2 2019 to: 1600 Dec 2 2019)
```

### `sort` - List sorted tasks

List out all tasks with earliest dates first.

Example of usage:

`sort`

Expected outcome (example):

```
Here are the sorted tasks in your list:
1.[E][] return book (from: 1400 Dec 2 2019 to: 1600 Dec 2 2019)
2.[T][] read up
```

### `mark` `unmark` - Mark/Unmark tasks

Mark/Unmark tasks deemed as completed/incompleted.

Example of usage:

`mark [Task number]`

`mark 1` (sample usage)

Expected outcome (example):

```
Nice! I've marked this task as done:
[T][X] read up
```

`unmark [Task number]`

`unmark 1` (sample usage)

Expected outcome (example):

```
Ok, I've marked this task as not done yet:
[T][] read up
```

### `delete` - Delete tasks

Delete tasks from disk.

Example of usage:

`delete [Task number]`

`delete 1` (sample usage)

Expected outcome (example):

```
Noted. I've removed this task:
[T][X] read up
Now you have 2 tasks in the list.
```

### `todo` - Add ToDo task

Add tasks of type Todos.

Example of usage:

`todo [title of task]`

`todo read up` (sample usage)

Expected outcome (example):

```
Got it. I've added this task:
[T][] read up
Now you have 3 tasks in the list.
```

### `deadline` - Add Deadline task

Add tasks of type Deadlines.

Example of usage:

`deadline [title of task] /by DD/MM/YYYY HHmm`

`deadline return book /by 02/12/2019 1800` (sample usage)

Expected outcome (example):

```
Got it. I've added this task:
[D][] return book (by: 1800 Dec 2 2019)
Now you have 4 tasks in the list.
```

### `event` - Add Event task

Add tasks of type Events.

Example of usage:

`event [title of task] /from DD/MM/YYYY HHmm /to DD/MM/YYYY HHmm`

`event return book /from 02/12/2019 1400 /to 02/12/2019 1600` (sample usage)

Expected outcome (example):

```
Got it. I've added this task:
[E][] return book (from: 1400 Dec 2 2019 to: 1600 Dec 2 2019)
Now you have 4 tasks in the list.
```

### `bye` - Exit

End conversation with duke.

Example of usage:

`bye`


Expected outcome (example):

```
Bye. Hope to see you again soon!
```