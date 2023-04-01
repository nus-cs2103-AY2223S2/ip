# User Guide
There are several commands that Duke supports. To run a command, simply type your command into the textbox and press enter or click "submit". The output of the command will be displayed in the textbox.

Pressing the up and down arrow keys will let you see previous commands you have entered.

# Commands

## Summary

| Command    | Syntax                                               | Description                                     |
|------------|------------------------------------------------------|-------------------------------------------------|
| `todo`     | `todo <task> [/priority priority]`                   | Create a ToDo.                                  |
| `deadline` | `deadline [/by date] [/priority priority]`           | Create a deadline with the given date.          |
| `event`    | `event [/from date] [/to date] [/priority priority]` | Create an event with the given time window.     |
| `find`     | `find <keywords...>`                                 | Find tasks that contain the given keywords      |
| `delete`   | `delete <index>`                                     | Delete the task with the given index            |
| `list`     | `list`                                               | List all current tasks                          |
| `mark`     | `mark <index>`                                       | Marks the task with the given index as done     |
| `unmark`   | `unmark <index>`                                     | Marks the task with the given index as not done |
| `save`     | `save`                                               | Saves tasks to disk                             |

## Task Creation
Duke supports three types of tasks:
1. ToDos, for tasks that have no deadline associated with them
2. Deadlines, for tasks that must be completed by a certain time
3. Events, for tasks that have to be completed within a time window

Each task can be created using its respective command, i.e. `deadline /by 03/05`.
Each task command can also be used with the `/priority` argument to provided a priority. If no priority is given, the created task
will be assigned a default priority value.

For each date option, both the date and time can be given. If either is not provided, the missing detail will be filled with the value
returned by `LocalDateTime.now()`:
```
Current Time: 05/07 1700
deadline /by 03/05 1400  Create a deadline for 3rd of May 2pm
deadline /by 03/05       Create a deadline for 3rd of May 5pm
deadline /by 1400        Create a deadline for 5th of July 2pm
```

## Task Deletion
To remove a task, simply use the `delete` command and provide it the index of the task you wish to delete.

## Task Completion
To mark a task as completed, run the `mark` command and provide it the index of the task you wish to mark. `unmark` works identically for
marking tasks as not completed.

## Task Filtering and Display
To see all currently stored tasks, use the `list` command. The `find` command can be used to narrow down this list using provided
keywords.

## Saving to Disk
Use the `save` command to save your tasks to disk. Otherwise, the tasks you have added may be lost when Duke closes.
