# User Guide

## Features
### 3 Types of Tasks
1. To-Do
2. Deadline
3. Event

### Summary of operations
- `todo/deadline/event abc` : Adds a To-Do/Deadline/Event named 'abc' into TaskList
- `mark 1` : Marks your first Task as done
- `delete 1` : Deletes your first Task
- `list` : Lists out all your Tasks
- `undo` : Undos the last change


## Usage

- `todo run` : Adds a To-Do named 'run' into TaskList
```
Got it. I've added this task:
[T][ ] run
Now you have 1 tasks in the list.
```

- `deadline homework /by 2024-01-01` : Adds a Deadline named 'homework' by Jan 1 2024 into TaskList
```
Got it. I've added this task:
[D][ ]homework (by: Jan 1 2024)
Now you have 2 tasks in the list.
```

- `event camp /from Sunday /to Monday` : Adds an Event named 'camp' from Sunday to Monday
```
Got it. I've added this task:
[E][ ] camp (from: Sunday to: Monday)
Now you have 3 tasks in the list.
```

- `mark 1` : Marks your first Task as done
```
Nice! I've marked this task as done
[T][X] run
```

- `unmark 1` : Unmarks your first Task
```
Nice! I've marked this task as not done yet
[T][ ] run
```

- `list` : Lists out all your Tasks
```
Here are the tasks in your list:
1. [T][ ] run
2. [D][ ]homework (by: Jan 1 2024)
3. [E][ ] camp (from: Sunday to: Monday)
```

- `delete 1` : Deletes your first Task
```
Noted. I've removed this task:
[T][ ] run
Now you have 2 tasks in the list.
```

- `undo` : Undos your previous action and displays current TaskList
```
Here are the tasks in your list:
1. [T][ ] run
2. [D][ ]homework (by: Jan 1 2024)
3. [E][ ] camp (from: Sunday to: Monday)
```
