# User Guide

## Features

### Keep track of your tasks

You can add and manage three types of tasks: **To Do**, **Deadline**, and **Event**.

### Find tasks quickly

Search for specific tasks with a keyword.

### Clone tasks

Create a duplicate of tasks and edit them.

## Usage

### `todo`

Create a **To Do**. A **To Do** is the most basic task.

```
todo {title}
```

**Example**

Create a todo with the title "keep track of my tasks".

```
todo keep track of my tasks
```

### `deadline`

Create a **Deadline**. A **Deadline** is a task with a due date.

```
deadline {title} /by {date}
```

**Properties**

* `by` due date

**Example**

Create a deadline with the title "cs2103t ip".

```
deadline cs2103t ip /by 17/2/2023
```

### `event`

Create an **Event**. An **Event** is a task with a start and end date.

```
event {title} /from {date} /to {date}
```

**Properties**

* `from` start date
* `to` end date

**Example**

Create an event with the title "recess week".

```
event recess week /from 20/2/2023 /to 24/2/2023
```

### `list`

Displays a list of your tasks with the task's `id`.

```
list
```

The `id` given by the output will be used in the subsequent commands to identify a task.

### `mark`

Marks a task as **done**.

```
mark {id}
```

**Example**

Mark the task with id `1` as done.

```
mark 1
```

### `unmark`

Marks a task as **not done**.

```
unmark {id}
```

**Example**

Mark the task with id `1` as not done.

```
unmark 1
```

### `edit`

Edits a task.

```
edit {id} /key {value} [/key {value} ...]
```

A `key` would be properties of the task such as `from` and `to` for events. To change a task's title, use the key `title`.

**Example**

Edit the event with id `2` by changing the title to "japan trip" and end date to 24th February 2023.

```
edit 2 /title japan trip /to 24/2/2023
```

### `clone`

Clones a task.

Optionally, the task can be edited in place by providing values in the same format as `edit`.

```
clone {id} [/key {value} ...]
```

**Example**

Clone the deadline with id `2` and change its due date to 2nd March 2023.

```
clone 2 /by 2/3/2023
```

### `delete`

Deletes a task.

```
delete {id}
```

**Example**

Delete the task with id `4`.

```
delete 4
```

### `find`

Finds the tasks with the given `keyword`.

```
find {keyword}
```

**Example**

Find tasks with the keyword `star`. This could return tasks such as "draw a star" and "start assignment".

```
find star
```

### `bye`

Exits the application.

```
bye
```
