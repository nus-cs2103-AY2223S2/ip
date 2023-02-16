# User Guide

## Features

### Listing all tasks `list`

Lists all the tasks that are stored currently.

### Mark task `mark`

Marks the specified task as completed.

### Unmark task `unmark`

Unmarks the specified task as not being complete.

### Listing all commands `help`

Lists all the commands, together with the format and its uses.

### Add todo task `todo`

Adds a todo task with a description.

### Add deadline task `deadline`

Adds a deadline task with a description and due date for the task.

### Add event task `event`

Adds an event task with a description, together with start and end dates for the event.

### Delete a task `delete`

Delete the specified task.

### Find task `find`

Finds all tasks that have substring that match the keyword provided.

### Exit program `bye`

Saves all the tasks and exits the program.

## Usage

### `list` - Listing all tasks

Lists all the tasks that are stored currently.

Syntax: `list`

Example of usage: `list`

Expected outcome:

```
Current data in the list are:
1. [T][X] Buy masks
2. [D][ ] Finish CS2103T ip (by: Feb 17 2023)
3. [E][ ] CS2103T tp project (from: Feb 17 2023, to: Apr 12 2023)
```

This shows 3 items currently present within the list.

Firstly, it is a todo task `[T]` for buying masks as completed `[X]`.

Secondly, it is a deadline task `[D]` for finishing CS2103T ip by Feb 17 2023 `(by: *)` that is not completed `[ ]`.

Lastly, it is an event task `[E]` for CS2103T tp project from February 17, 2023 to Apr 12, 2023 `(from: *, to: *)` as not being completed yet `[ ]`.

### `mark` - Mark task

Marks the specified task as completed.

Syntax: `mark <index>`

Example of usage: `mark 2`

Expected outcome:

```
I've marked this task as done:
[D][X] Finish CS2103T ip (by: Feb 17 2023)
```

This shows the second task marked as done by the `[X]` representation.


### `unmark` - Unmarks task

Unmarks the specified task as not being complete.

Syntax: `unmark <index>`

Example of usage: `unmark 1`

Expected outcome:

```
I've unmarked this task as not done:
[T][ ] Buy masks
```

This shows the first task unmarked as not being done by the `[ ]` representation.

### `help` - Listing all commands

Lists all the commands, together with the format and its uses.

Syntax: `help`

Example of usage: `help`

Expected outcome:

```
These are the commands that are available:
help : Enter the command "help" to show the list of all commands.
bye : Enter the command "bye" to close the application.
list : Enter the command "list" to show a list of all tasks that are stored.
delete : Enter the command "delete x" to delete the x-th task from the task list.
         Note: x is a number and needs to be within the range 1 and the number of tasks stored.
mark : Enter the command "mark x" to mark the x-th task as being completed.
       Note: x is a number and needs to be within the range 1 and the number of tasks stored.
unmark : Enter the command "unmark x" to unmark the x-th task as not being completed.
         Note: x is a number and needs to be within the range 1 and the number of tasks stored.
find : Enter the command "find x" to find any tasks whose description contains "x" within it.
todo : Enter the command "todo x" where x is the description to add a todo task with the provided description.
deadline : Enter the command "deadline x /by y" where x is the description and y is the deadline of the task to add a
           deadline task with the provided description and deadline.
           Note: y has to be in the format yyyy-mm-dd (i.e. 2023-01-01).
event : Enter the command "event x /from y /to z" where x is the description of the event, y is the start date of the
        event and z is the end date of the event. An event task will be added with the provided description, start and
        end date.
        Note: y and z has to be in the format yyyy-mm-dd (i.e. 2023-01-01).
```

This shows all the commands with its corresponding syntax. It states its function, together with what needs to be noted.

For example:
```
mark : Enter the command "mark x" to mark the x-th task as being completed.
Note: x is a number and needs to be within the range 1 and the number of tasks stored.
```

This states that the `mark` command has a syntax of `mark x` where `x` represents the x-th task. It states that the corresponding task is marked as complete. The user also needs to `Note:` that x needs to be within the size of the task list.



