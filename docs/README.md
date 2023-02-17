# User Guide
This is a project template for a greenfield Java project. Kyle is a desktop application for managing your tasks and notes via a Command Lind Interface (CLI) while still having the benefits of a Graphical User Interface (GUI).
## Setting up
1. Make sure to download JAVA installed in your computer. If not, you can download from [here](https://www.azul.com/downloads/?version=java-11-lts&os=macos&architecture=arm-64-bit&package=jdk-fx).
2. Install the latest duke.jar from [here](https://github.com/dohaduong/ip/releases/tag/A-Release).
3. Start using your Kyle by typing in command and press *Send* or Enter to execute it. You can try one of the commands from below:
- `list`
- `find`
- `mark`
- `unmark`
- `delete`
- `undo`
- `todo`
- `deadline`
- `event`
- `bye`
## Features 

### Listing all tasks - `list`

List out all the existing tasks you have stored in Kyle's. 

Format: `list`

### Marking task as done - `mark`
Marks an existing task as done.

Format: `mark [task number]`

- Marks the task of the specified `task number` - the index as shown in the displayed task list.
- The index must be a positive integer starting from 1.

Example:
`mark 1`

### Un-marking task as un-done - `unmark`
Un-marks an existing task as un-done.

Format: `unmark [task number]`

- Un-marks the task of the specified `task number` - the index as shown in the displayed task list. 
- The index must be a positive integer starting from 1.

Example:
`unmark 1`

### Finding task - `find`

Finds a task using a given keyword.

Format: `find [keyword]`

- Kyle will attempt to find any tasks that contain your given keyword. 
- The search is case-sensitive.

Example: `find home`

### Deleting a task - `delete`

Deletes a task at a given task number.

Format: `delete [task number]`

- The task will be deleted based on the given task number - which is the task's index in the displayed task list.
- The index must be a positive integer starting from 1.

Example: `delete 1`

### Deleting all tasks - `delete all`

Clears all tasks in the existing task list.

Format: `delete all`

### Undoing the most recent valid command - `undo`

Undo the most recent valid command, revert the task list back to before that command.

Format: `undo`

- Only able to undo valid command.
- Only able to undo commands that are undo-able (Example: `delete`)

### Adding a to-do: `todo`

Adds a to-do to the task list.

Format: `todo [task content/detail]`

Example:
- `todo walk the dog`
- `todo clean the house`

### Adding a deadline: `deadline`

Adds a deadline to the task list.

Format: `deadline [task content/detail] /by [deadline date]`

Example:
- `deadline submit the assignment /by 2023-02-17`
- `deadline buy grocery /by 2023-02-20`

### Adding an event: `event`

Adds an event to the task list.

Format: `event [task content/detail] /from [event start date and time] /to [event end date and time]`

Example:

`event meeting with teammates /from 2023-02-16 1000 /to 2023-02-16 1500`

### Exiting - `bye`

Exits from the program.

Format: `bye`
