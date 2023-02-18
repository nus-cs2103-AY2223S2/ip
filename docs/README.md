# User Guide

## Quick start
1. Ensure you have Java `11` or above installed on your computer.
2. Download the latest `duke.jar` from here.
3. Copy the file to the folder you want to use as the _home_ folder for Duke.
4. Run the application using `java -jar duke.jar`.
5. Type the command in the command box and press Enter to execute it.
   Some example commands you can try:
   - `list`: Lists all currently stored Tasks.
   - `todo read book`: adds a Todo `read book` for Duke to store.
   - `bye`: Terminates the Duke application.
6. Refer to the Features below for details of each command.

## Features 
Note that commands should all be in **lower case**.

DateTime should be in the format dd-MM-yyyy HHmm. E.g.: `10-02-2023 1800` to represent Feb 10th 2023, 6pm.
### Adding a Todo: `todo`
Adds a Todo task to Duke.

Format: `todo TASK_DESCRIPTION`

Examples:
- `todo read book`

### Adding a Deadline: `deadline`
Adds a Deadline task to Duke.

Format: `deadline TASK_DESCRIPTION /by dd-MM-yyyy HHmm`

Examples:
- `deadline finish book /by 10-02-2023 1800`

### Adding a Event: `event`
Adds an Event task to Duke.

Format: `event TASK_DESCRIPTION /from dd-MM-yyyy HHmm /to dd-MM-yyyy HHmm`

Examples:
- `event book review /from 10-02-2023 1800 /to 10-02-2023 2000`

### Listing all tasks: `list`
Lists all currently stored tasks.

Format: `list`

### Updates a task's description: `update`
Updates the description of a task.

Format: `update INDEX TASK_DESCRIPTION`
- Updates the description of the task at the specified `INDEX`. The index refers to the index number shown in the displayed task list. The index must be a positive integer 1, 2, 3, …​

Examples:
- `update 1 borrow book`

### Marking a task as done: `mark`
Marks a task as done.

Format: `mark INDEX`
- Marks the task at the specified `INDEX` as done. The index refers to the index number shown in the displayed task list. The index must be a positive integer 1, 2, 3, …​

Examples:
- `mark 1`

### Marking a task as not done yet: `unmark`
Marks a task as not done yet.

Format: `unmark INDEX`
- Marks the task at the specified `INDEX` as not done yet. The index refers to the index number shown in the displayed task list. The index must be a positive integer 1, 2, 3, …​

Examples:
- `unmark 1`

### Finding a task: `find`
Finds a task by searching for a keyword in its description.

Format: `find KEYWORD`

Examples:
- `find book`

### Deleting a task: `delete`
Deletes a task from Duke.

Format: `delete INDEX`
- Deletes the task at the specified `INDEX`. The index refers to the index number shown in the displayed task list. The index must be a positive integer 1, 2, 3, …​

Examples:
- `delete 1`

### Terminating Duke: `bye`
Terminates the Duke program.

Format: `bye`

## Saving the data
Duke data are saved in the hard disk automatically after any command that changes the data. There is no need to save manually.
