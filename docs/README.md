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

1. ### `list` - List all tasks

    Jarvis will return a list of all tasks of the user.

    Example of usage: `list`

Expected outcome:

```
Tasks for Tony Stark:
-------------------
-------------------
```

2. ### `todo` - Creates a 'todo' type Task

    This task only has a description. No time parameters taken.
    Example of usage: `todo cook dinner`

Expected outcome:

```
Tasks for Tony Stark:
-------------------
1[T][ ] cook dinner
-------------------
```

3. ### `deadline` - Creates a 'deadline' type Task

    This task has a description and a deadline.
    Example of usage: `deadline finish essay /2023-02-22`

Expected outcome:

```
Tasks for Tony Stark:
-------------------
1[T][ ] cook dinner
2[D][ ] finish essay  (Due: Feb 22 2023)
-------------------
```

4. ### `event` - Creates a 'event' type Task

    This task has a description and a time span.
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

5. ### `mark` - Marks a task as done

    This task has a description and a time span.
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

6. ### `unmark` - Un-marks a task as done

    This task has a description and a time span.
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

7. ### `unmark` - Un-marks a task as done

    This task has a description and a time span.
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
