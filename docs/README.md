# User Guide - DUKE!
Duke is the **NUMBER ONE** to-do list app on the market!
Simply tell DUKE what you need to do, and let DUKE take care of the rest!

## Features 

### Add tasks
Add a variety of tasks to DUKE! You can even specify the timings of the task!

Tasks supported are todo, deadline and event tasks.

### Find tasks
Too many tasks to keep track of? DUKE can help you find a task that you forgot!

### List tasks
Want to see what you still need to do? DUKE can list out all your tasks for you!

### Mark and unmark tasks
So you've completed a task? Simply tell DUKE to mark it as done!

### Delete tasks
You can also tell DUKE to forget tasks that are no longer relevant!


## Usage

### `todo` - Adds a todo task
DUKE adds a todo task with the given task description.

Command format: `todo <description>`

Example usage: 
```
You: todo stuff turkey
Duke: Added: [T][ ] stuff turkey
```

### `deadline` - Adds a deadline task
DUKE adds a deadline task with the given task description and deadline.
Deadline can be specified as a date, time or a date + time.
If only time is given, date defaults to the current date.
If only date is given, time defaults to 12:00am.
Many date and time formats are supported.

Command format: `deadline <description> /by <deadline>`

Example usage:
```
You: deadline bake turkey /by 14 feb
Duke: Added: [D][ ] bake turkey (by: 14 Feb 2023 12:00am)
```

### `event` - Adds a event task
DUKE adds a event task with the given task description, start and end time.
Start and end times can be specified as a date, time or a date + time.
If only time is given, date defaults to the current date.
If only date is given, time defaults to 12:00am.
Many date and time formats are supported.

Command format: `event <description> /from <start> /to <end>`

Example usage:
```
You: event eat turkey /from 14 feb 7pm /to 14 feb 11pm
Duke: Added: [E][ ] eat turkey (14 Feb 2023 6:00pm - 14 Feb 2023 11:00pm)
```

### `mark` - Marks a task as done

Marks the task, specified by the task index, as done.
Task index can be obtained using the `list` command.

Command format: `mark <index>`

Example usage:
```
You: mark 1
Duke: Okay! I've marked this task as done!
      [T][X] stuff turkey
```

### `unmark` - Marks a task as undone

Marks the task, specified by the task index, as undone.
Task index can be obtained using the `list` command.

Command format: `unmark <index>`

Example usage:
```
You: unmark 1
Duke: Okay! I've marked this task as undone!
      [T][ ] stuff turkey
```

### `list` - Shows all tasks

Shows all tasks, along with the task index.

Command format: `list`

Example usage:
```
You: list
Duke: 1. [T][ ] stuff turkey
      2. [D][ ] bake turkey (by: 14 Feb 2023 12:00am)
      3. [E][ ] eat turkey (14 Feb 2023 6:00pm - 14 Feb 2023 11:00pm)
```

### `find` - Finds specified tasks

Finds all tasks containing the specified keyword.

Command format: `find <keyword>`

Example usage:
```
You: find turkey
Duke: I found these tasks in your task list!
      1. [T][ ] stuff turkey
      2. [D][ ] bake turkey (by: 14 Feb 2023 12:00am)
      3. [E][ ] eat turkey (14 Feb 2023 6:00pm - 14 Feb 2023 11:00pm)
```

### `delete` - Deletes specified task

Deletes task specified by the given task index.

Command format: `delete <index>`

Example usage:
```
You: delete 1
Duke: Okay! I deleted task [T][ ] stuff turkey
```

### `bye` - Ends DUKE

Closes the DUKE program.

Command format: `bye`


