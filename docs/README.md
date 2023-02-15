# User Guide

## Features 

### Management of Todos/Deadlines/Events
Users are able to create and delete Todos, Deadlines and Events.
Users are also to find these in the list.
### Save/Load
Users are able to save to the file system the tasks they have added before.
### Priority
Users are able to set priorities for tasks. Tasks are then sorted by priority.
## Usage
### `list` - Lists all tasks
### `todo` - Adds a todo
A todo is added to the list.

Example of usage: `todo new todo`

Expected outcome: "new todo" is added to the list as a new Todo task.

### `event` - Adds a event
An event is added to the list.

Example of usage: `event new event /from 2023-02-14 /to 2023-02-15`

Date is to be in YYYY-MM-DD format.

Expected outcome: "new event" is added to the list as a new event.
### `deadline` - Adds a task with a deadline
A task with a deadline is added to the list.

Example of usage: 
`deadline new deadline task /by 2023-02-15`

Date is to be in YYYY-MM-DD format.

Expected outcome: "new deadline task" is added to the list.
### `find` - Finds tasks that contain a keyword

Example of usage: `find abc`

Expected outcome: Tasks with 'abc' in their descriptions are returned.

### `mark` - Marks a task as done
Example of usage: `mark 1`

Expected outcome: First task is marked as done.

### `unmark` - Marks a task as not done
Example of usage: `unmark 1`

Expected outcome: First task is marked as not done.

### `priority` - Sets priority for a task
Example of usage: `priority 1 HIGH`

Available options for priority are LOW, MEDIUM and HIGH

Expected outcome: First task is given HIGH priority.

### `bye` - Exits the program.