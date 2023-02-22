# User Guide

## Features 

### Add Tasks

`todo`, `deadline`, `event`

### Manage Tasks

`delete`, `mark`, `unmark`

### Display Tasks

`list`, `find`

### Save & Load

`save`, `load`

### Exit

`bye`

## Usage

### `todo` - Add a To-do Task

Adds a To-do task to the end of the list.

```
todo OBJECTIVE
```

### `deadline` - Add a Deadline Task

Adds a Deadline task to the end of the list.

```
deadline OBJECTIVE /by TIME
```

### `event` - Add an Event Task

Adds an Event task to the end of the list.

```
event OBJECTIVE /from TIME /to TIME
```

### `delete` - Delete a Task

Deletes the task at any given index.

```
delete INDEX
```

### `mark` - Mark a Task as complete

Marks the task at any given index as complete.

```
mark INDEX
```

### `unmark` - Mark a Task as not complete

Marks the task at any given index as not complete.

```
unmark INDEX
```

### `list` - Display list of Tasks

Displays the tasks in the list. Optionally, filter only for tasks within a given time range.

```
list [/from TIME] [/to TIME]
```

### `find` - Find matching Tasks

Displays the tasks in the list matching a given regular expression.

```
find REGEX
```

### `save` - Save Tasks to file

Saves the list of Tasks to a given relative file path.

```
save PATH
```

### `load` - Load Tasks to file

Loads the list of Tasks from a given relative file path.

```
load PATH
```

### `bye` - Exit

Exits the application.

```
bye
```
