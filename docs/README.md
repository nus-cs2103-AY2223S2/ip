# User Guide

## Features 

### Feature Add

Add one of 4 types of task:
1. Todo task
2. Deadline task
3. Event task
4. Duration task

### Feature Mark/Unmark

Mark a task done or unmark it.

### Feature Delete

Delete a task .

### Feature Find

Find a task with keywords.

## Usage

### `todo description` - Add general task

Add a task with the given description.

Example of usage: 

`todo read books`

Expected outcome:

```
Got it. I've added this task:
[T][ ] read book
Now I have 1 tasks in the list.
```

### `deadline description /by time` - Add task with deadline

Add a task with the given description and deadline.

Example of usage:

`deadline return book /by Sunday`

Expected outcome:

```
Got it. I've added this task:
[D][ ] return book (by: Sunday)
Now I have 2 tasks in the list.
```

### `event description /from start time /to end time` - Add task with begin and end time

Add a task with the given description and begin and end time.

Example of usage:

`event programming study /from 5pm /to 9pm`

Expected outcome:

```
Got it. I've added this task:
[E][ ] programming study (from: 5pm to: 9pm)
Now I have 3 tasks in the list.
```

### `duration description /need start time` - Add task with duration

Add a task with the given description and duration.

Example of usage:

`duration practice singing /needs 2 days`

Expected outcome:

```
Got it. I've added this task:
[Du][ ] practice singing (needs: 2 days)
Now I have 4 tasks in the list.
```

### `list` - show list of tasks

Display list of tasks so far.

Example of usage:

`list`

Expected outcome:

```
1. [T][ ] read book
2. [D][ ] return book (by: Sunday)
3. [E][ ] programming study (from: 5pm to: 9pm)
4. [Du][ ] practice singing (needs: 2 days)
```

### `mark index` - mark task done

Mark task at the given index as done.

Example of usage:

`mark 1`

Expected outcome:

```
Nice! I've marked this task as done:
[T][X] read book
```

### `unmark index` - unmark task done

unMark task at the given index as done.

Example of usage:

`unmark 1`

Expected outcome:

```
OK, I've marked this task as not done yet:
[T][ ] read book
```

### `delete index` - delete task from the list

Delete task at the given index.

Example of usage:

`delete 1`

Expected outcome:

```
Noted. I've removed this task:
[T][ ] read book
Now I have 3 tasks in the list.
```

### `find keyword` - find task

Find task containing keyword in the list.

Example of usage:

`find book`

Expected outcome:

```
Here are the matching tasks in your list:
1. [D][ ] return book (by: Sunday)
```
