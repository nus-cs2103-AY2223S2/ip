# User Guide 📖

## Features 📄

### Add task: todo

Adds a task without any deadline.

### Add deadline task: deadline

Adds a task with deadline.

### Add event task: event

Adds an event occuring within a specific timeline.

### Check task: mark

Checks off task as completed.

### Uncheck task: unmark

Unchecks task as incomplete.

### Delete task: delete

Deletes task.

### View all tasks: list

Lists all completed and incompleted tasks.

### Sort task list: sort

Sorts the task list by task name.

## Keyword Usage 🔑

### todo

Adds a task into task list and outputs success message.

Example of usage: 

`todo read a book`

Expected outcome:

Outputs success message.

```
  Got it. I've added this task:
    [T][ ] read a book
  Now you have 1 task in the list.
```

### deadline

Adds a task with deadline into task list and outputs success message.

Example of usage: 

`deadline return book /by 2050-01-01`

Expected outcome:

Outputs success message.

```
  Got it. I've added this task:
    [D][ ] return book (by: Jan 1 2050)
  Now you have 2 tasks in the list.
```

### event

Adds an event task occurring over a period into task list and outputs success message.

Example of usage: 

`event reading event /from 2050-01-01 /to 2050-01-02`

Expected outcome:

Outputs success message.

```
  Got it. I've added this task:
    [E][ ] reading event (from: Jan 1 2050 to: Jan 2 2050)
  Now you have 3 tasks in the list.
```

### mark

Marks a task from task list as complete and outputs success message.

Example of usage: 

`mark 1`

Expected outcome:

Outputs success message.

```
  Got it. I've marked this task:
    [T][X] read a book
  Now you have 3 task in the list.
```

### unmark

Unmarks a task from task list as incomplete and outputs success message.

Example of usage: 

`unmark 1`

Expected outcome:

Outputs success message.

```
  Got it. I've unmarked this task:
    [T][ ] read a book
  Now you have 3 task in the list.
```

### delete

Deletes a task from task list and outputs success message.

Example of usage: 

`delete 1`

Expected outcome:

Outputs success message.

```
  Noted. I've removed this task:
    [T][ ] read a book
  Now you have 2 tasks in the list.
```

### list

Lists all task index from task list as complete and outputs success message.

Example of usage: 

`list`

Expected outcome:

Outputs the list of tasks.

```
  Here are the tasks in your list:
  1.[T][ ] borrow book
  2.[D][ ] return book (by: Jan 1 2050)
  3.[E][ ] book event (from: Jan 1 2050 to: Jan 2 2050)
```

### sort

Sorts task list by task name and outputs success message.

Example of usage: 

`sort`

Expected outcome:

Outputs the list of tasks.

```
The list has been sorted.
```
