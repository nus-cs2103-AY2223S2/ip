# Duke Task Manager
> Too many tasks floating around? Fret Not, Duke is here to save the day!

Duke is your personal task manager, helping you easily keep up to date with your tasks.

## Features

### Add a ToDo task: `add`
Adds a todo.

Format: `todo TASK DESCRIPTION`

Example: `todo return book`

Expected outcome:
```
Got it. I've added this task:
[T][ ] TASK_DESCRIPTION
Now you have X task(s) in the list.
```

### Add a DeadlineTask: `deadline`
Adds a DeadlineTask.

Format: `deadline TASK DESCRIPTION /by DD/MM/YYYY HHmm`

Example: `deadline return book /by 14/02/2023 1800`

Expected outcome:
```
Got it. I've added this task:
[D][ ] TASK_DESCRIPTION (by: MMM d YYYY HHmm)
Now you have X task(s) in the list.
```

### Add an Event task: `event`
Adds an Event task.
Format: `event TASK DESCRIPTION /from DD/MM/YYYY HHmm /to DD/MM/YYYY HHmm`
Example: `event borrow book /from 14/02/2023 1800 /to 22/02/2023 1800`

Expected outcome:
```
Got it. I've added this task:
[E][ ] TASK_DESCRIPTION (from: MMM d YYYY HHmm to: MMM d YYYY)
Now you have X task(s) in the list.
```

### List all tasks: `list`
Displays all tasks added.
Format: `list`

Expected outcome:
```
Here are the tasks in your list:
1. ...
2. ...
```

### Deleting a task: `delete`
Deletes a task from task list.

Format: `delete INDEX`
- Deletes the task specified by the User

Example: `delete 1`
- Deletes the task if there is a task at index 1.

Expected outcome:
```
Noted. I've removed this task:
 [T][ ] TASK_DESCRIPTION
Now you have X tasks in the list.
```

### Finding specific tasks: `find`
Display tasks where the description matches given keywords.

Format: `find KEYWORD`

Example: `find KEYWORD` 
- Returns a list of all tasks which contains the KEYWORD.

Expected outcome:

```
Here are the matching tasks in your list:
1. ...
2. ...
```

### Marking a task: `mark`
Marks a task as completed.

Format: `mark INDEX`
- INDEX refers to the task to be marked as completed.

Example: `mark 1` marks the first task as completed if it exists.

Expected outcome:
```
OK! I've marked this task as done:
[T][X] TASK_DESCRIPTION
```

### Unmarking a task: `unmark`
Marks a task as incomplete.

Format: `unmark INDEX`
- INDEX refers to the task to be marked as incomplete.

Example: `unmark 1` marks the first task as uncompleted if it exists.

Expected outcome:

Marks a task as incomplete when valid index is given.

```
OK, I've marked this task as not done yet:
[T][ ] TASK_DESCRIPTION
```

### Snoozing a task: `snooze`
Shifts back the deadline of a Task.

Format: `snooze INDEX /day DAY /hour HOUR /minutes MINUTES`
- INDEX refers to the task to be snoozed
- Note: ToDo has no deadline

Example: `snooze 1 /day 10 /hour 10 /minutes 20`

Expected outcome:

Outputs the new deadline of the Task.
```
New deadline is MMM d YYYY HHmm!
```

### Exiting the program: `bye`
Exits the program.

Format: `bye`

Expected outcome:
`Bye. Hope to see you again soon!`
