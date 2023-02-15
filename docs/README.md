# User Guide
Riddle is an intelligent tasks controller, which could help you improve working and learning efficiency.

## Features 
1. Add
2. Mark / Unmark
3. Delete
4. Find
5. List
6. Undo

### Feature - Add
Task (Todo, Deadline, and Event) could be added to a TaskList.

### Feature - Mark/Unmark
If a task is done already or is more important, you could mark them to indicate.

Likewise, unmark them if they are still in progress or not significant.

### Feature - Delete
Delete the task you think meaningless already in your TaskList.

### Feature - Find
Find the tasks containing the same keywords.

### Feature - List
Show your personal TaskList.

### Feature - Undo
Undo the most recent action (add, mark/unmark, delete).

## Usage

### `todo`
Example: `todo return book`

Expected outcome:
```
Got it. I've added this task:
[T][] return book
Now you have 1 tasks in the list.
```

### `deadline`
Example: `deadline read book /by 2021-10-21`

Expected outcome:
```
Got it. I've added this task:
[D][] read book (by:Oct 21 2021)
Now you have 2 tasks in the list.
```

### `event`
Example: `event write book /from Jan /to Oct`

Expected outcome:
```
Got it. I've added this task:
[E][] write book (from:Jan to:Oct)
Now you have 3 tasks in the list.
```

### `list`
Example: `list`

Expected outcome:
```
Here are the tasks in your list:
1.[T][] return book
```

### `mark` `unmark`
Example: `mark 1`

Expected outcome:
```
Nice!I've marked this task as done:
[X] return book
```

Example: `unmark 1`

Expected outcome:
```
OK, I've marked this task as not done yet:
[] return book
```

### `delete`
Example: `delete 1`

Expected outcome:
```
Noted.I've removed this task:
[T][] return book
Now you have 2 tasks in the list.
```

### `undo`
Example: `undo`

Expected outcome:
```
Got it. I've added this task:
[T][] return book
Now you have 3 tasks in the list.
```