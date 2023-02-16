# User Guide

## Features 

### Feature - Task saving

Create a new task in any of the following types:
1. todo
2. deadline
3. event

### Feature - Task deletion

Delete existing task in the current ToDoList.

### Feature - Task completion

Mark the task specified as completed!

### Feature - Undo task completion

Undo the specified task as incomplete!

### Feature - Find next upcoming deadline

Shows the next task with the nearest deadline.

---

## Usage

### `list` - List all tasks

Jarvis will return a list of all tasks of the user.<br>
Example of usage: `list`

Expected outcome:

```
Tasks for Tony Stark:
-------------------
-------------------
```

### `todo` - Creates a 'todo' type Task

This task only has a description.<br>
No time parameters taken.<br>
Example of usage: `todo cook dinner`

Expected outcome:

```
Tasks for Tony Stark:
-------------------
1[T][ ] cook dinner
-------------------
```

### `deadline` - Creates a 'deadline' type Task

This task has a description and a deadline.<br>
Example of usage: `deadline finish essay /2023-02-22`

Expected outcome:

```
Tasks for Tony Stark:
-------------------
1[T][ ] cook dinner
2[D][ ] finish essay  (Due: Feb 22 2023)
-------------------
```

### `event` - Creates a 'event' type Task

This task has a description and a time span.<br>
Example of usage: `event travel /2023-02-25 /2023-02-28`

Expected outcome:

```
Tasks for Tony Stark:
-------------------
1[T][ ] cook dinner
2[D][ ] finish essay  (Due: Feb 22 2023)
3[E][ ] travel  (Start: Feb 25 2023 | End: Feb 28 2023)
-------------------
```

### `mark` - Marks a task as done

This task has a description and a time span.<br>
Example of usage: `mark 1`

Expected outcome:

```
Tasks for Tony Stark:
-------------------
1[T][X] cook dinner
2[D][ ] finish essay  (Due: Feb 22 2023)
3[E][ ] travel  (Start: Feb 25 2023 | End: Feb 28 2023)
-------------------
```

### `unmark` - Un-marks a task as done

This task has a description and a time span.<br>
Example of usage: `unmark 1`

Expected outcome:

```
Tasks for Tony Stark:
-------------------
1[T][ ] cook dinner
2[D][ ] finish essay  (Due: Feb 22 2023)
3[E][ ] travel  (Start: Feb 25 2023 | End: Feb 28 2023)
-------------------
```

### `unmark` - Un-marks a task as done

This task has a description and a time span.<br>
Example of usage: `unmark 1`

Expected outcome:

```
Tasks for Tony Stark:
-------------------
1[T][ ] cook dinner
2[D][ ] finish essay  (Due: Feb 22 2023)
3[E][ ] travel  (Start: Feb 25 2023 | End: Feb 28 2023)
-------------------
```
