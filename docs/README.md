
# User Guide
Forgetful? Come let Dude remember your task for you!
## Features

### Manage Tasks

With the ability to create, delete, find tasks such as todo, deadlines and event, you will never be forgetful again.

### Save Tasks

Tasks are saved into a text file upon new change so there is no need to save manually.

### Edit Tasks

The text file that contains the saved task data can be found under `[JAR file location]/data/tasks.txt`. Simply head over there and edit the data file as you wish.

## Command Usage

### `todo` - Add a todo

Adds a todo to your task list.

Usage format:  `todo (DESCRIPTION)`

Examples:
- `todo running`
- `todo buy groceries`

### `deadline` - Add a deadline

Adds a deadline to your task list.

Usage format:  `deadline (DESCRIPTION) /by (YYYY-MM-DD) (HHmm) `

Examples:
- `deadline cooking /by 2023-02-12 1800`
- `deadline individual project /by 2023-02-17 2359`

### `event` - Add an event

Adds an event to your task list.

Usage format:  `event (DESCRIPTION) /from (YYYY-MM-DD) (HHmm) /to (YYYY-MM-DD) (HHmm) `

Examples:
- `event bash /from 2022-09-02 1800 /to 2022-09-02 2300`
- `event tech show /from 2022-12-12 1000 /to 2022-12-14 1800`

### `list` - List all tasks

Displays all tasks in your task list

Usage format:  `list`

Examples:
- `list`

### `delete` - Delete a task

Deletes a task from your task list.

Usage format:  `delete (TASK_NUMBER)`

Examples:
- `delete 1`

### `mark` - Mark a task

Marks a task as done from your task list.

Usage format:  `mark (TASK_NUMBER)`

Examples:
- `mark 1`

### `unmark` - Unmark a task

Unmark a task as undone from your task list.

Usage format:  `unmark (TASK_NUMBER)`

Examples:
- `unmark 1`

### `find` - Find task description with keyword(s)

Finds tasks which descriptions contains the given keyword(s)

Usage format:  `find (KEYWORD_1) (KEYWORD_2) .... (KEYWORD_N)`

Examples:
- `find sleep`
- `find lectures tutorials`

### `undo` - Undo the previous change

Reverts the last command that made changes to the task list

Usage format:  `undo`

Examples:
- `undo`

### `bye` - Exit the program

Exits the program

Usage format:  `bye`

Examples:
- `bye`   