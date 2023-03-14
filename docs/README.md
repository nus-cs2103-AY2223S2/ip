# User Guide

## Features 
### `list`
Prints a list of all your tasks.
### `mark <index>`
Marks a task at the index as done.
### `unmark <index>`
Un-marks a task at the index as done.
### `todo <task description>`
Adds a todo task.
### `deadline <task description> /by <date> <time>`
Adds a deadline task with its deadline and time.
### `event <task description> /from <date> <time> /to <date> <time>`
Adds an event task with its duration date and time.
### `find <keyword>`
Queries a Task by its keyword and prints them.
### `snooze <deadline/event index>`
Snooze deadline or event task.
### `unsnooze <deadline/event index>`
Unsnooze deadline or event task.
### `bye`
Exits the application.

## Usage

### `list` - Displays a list of all your tasks.

This command displays a lists of all the tasks.

Example:

`list`

```
1.[T][ ] Buy chocolate
2.[T][X] Finish iP
3.[D][X] CS2105 Assignment 1
 (by: Feb 17 2023 11:59 pm)
4.[E][ ] Team Meeting
 (from: Feb 20 2023 4:00 am
    to: Feb 20 2023 5:00 am)
```

### `mark <index>` - Marks the task as completed.

This command marks a task as done.

Example:
`mark 1`

Expected outcome:
```
Nice! I've marked this task as done:
 [T][X] Buy chocolate
```

### `unmark <index>` - Un-marks the task at the index as incompleted.

This command un-marks a task as not done

Example of usage:
`unmark 2`

Expected outcome:
```
Ok! I've marked this task as not done yet
 [T][ ] Finish iP
```

### `todo <task>` - Adds a ToDo Task to the task list.

This commands adds a ToDo Task to the task list

Example of usage:
`todo CS2103T iP A-UserGuide`

Expected outcome:
```
Got it. I've added this task:
 [T][ ] CS2103T iP A-UserGuide
Now you have 8 tasks in the list.
```

### `deadline <task> /by <date> <time>` - Adds a Deadline Task to the task list.

This command adds a Deadline task to the task list with its due date and the time.

Example of usage:
`deadline MA3252 Quiz 4 /by 2023-02-21 2359`

Expected outcome:
```
Got it. I've added this task:
 [D][ ] MA3252 Quiz 4
 (by: Feb 21 2023 11:59 pm)
Now you have 9 tasks in the list.
```


### `event <task> /from <date> <time> /to <date> <time>` - Adds an Event Task to the task list.

This command adds an Event task to the task list.

Example of usage:
`event Interview /from 2023-05-13 1600 /to 2023-05-13 2000`

Expected outcome:
```
Got it. I've added this task:
 [E][ ] Interview
 (from: May 13 2023 4:00 pm
    to: May 13 2023 8:00 pm)
Now you have 10 tasks in the list.
```

### `find<keyword>` - Queries a list of task which contains the keyword.

This command will search the list for tasks that match the keyword

Example of usage:
`find chocolate`

Expected outcome:
```
Here are the matching tasks in your list:
1.[T][ ] Buy chocolate
```

### `snooze <event/deadline index>` - Snoozes the respective event or deadline.

This command snoozes the respective event or deadline.

Example of usage:
`snooze 1`

Expected outcome:
```
OK! I've snoozed this task:
1.[D][ ] CS2105 Assignment
 (by: Feb 14 2023 11:59 pm) (snoozed)
```

### `unsnooze <event/deadline index>` - Snoozes the respective event or deadline.

This command snoozes the respective event or deadline.

Example of usage:
`unsnooze 1`

Expected outcome:
```
OK! I've unsnoozed this task:
1.[D][ ] CS2105 Assignment
 (by: Feb 14 2023 11:59 pm)

```
### `bye` - Exits the application.

Example of usage:
`bye`

Expected outcome:
```
Bye. Hope to see you again soon!
```
