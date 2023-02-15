# User Guide
Angela is a **desktop app for managing tasks, optimized for use via a Command Line Interface** (CLI) while still having the benefits of a Graphical User Interface (GUI).

## Quick start
1. Ensure you have Java `11` or above installed in your Computer.
2. Download the latest `angela.jar`.
3. Copy the file to the folder you want to use as the *home folder* for Angela.
4. Open a command terminal, `cd` into the folder you put the jar file in, and use the `java -jar angela.jar` command to run the application.
6. Refer to the Features below for details of each command.

## Features
Notes about the command format:
* Words in `UPPER_CASE` are the parameters to be supplied by the user
* Words in `<angle brackets>` are the format to follow for a parameter
* Extraneous parameters for commands for commands that do not take in parameters will be ignored

### Adding a todo task: `todo`

Adds a todo task to the task list.
Format: `todo DESCRIPTION`
Examples:
* `todo watch TV`
* `todo buy a charger`

### Adding a deadline task: `deadline`

Adds a deadline task to the task list.
Format: `deadline DESCRIPTION /by <dd-MM-yyyy HH:mm>`
Examples:
* `deadline submit assignment /by 17-03-2023 11:00`
* `deadline eat /by 25-11-2024 12:12`

### Adding an event task: `event`

Adds an event task to the task list.
Format: `event DESCRIPTION /from <dd-MM-yyyy HH:mm> /to <dd-MM-yyyy HH:mm>`
Examples:
* `event school /from 21-03-2023 09:00 /to 21-03-2023 11:00`
* `event long holiday /from 22-03-2024 00:00 /to 30-04-2024 23:59`

### Listing all tasks: `list`

Shows all tasks in the task list.
Format: `list`

### Marking a task as done: `mark`

Marks a task in the task list as done.
Format: `mark INDEX`
* Marks the task at the specified `INDEX` as done. The index refers to the index number shown in the full displayed task list. The index **must be a positive value** 1, 2, 3...

### Marking a task as not done: `unmark`

Marks a task in the task list as not done.
Format: `unmark INDEX`
* Marks the task at the specified `INDEX` as not done. The index refers to the index number shown in the full displayed task list. The index **must be a positive value** 1, 2, 3...

### Deleting a task: `delete`

Deletes a task in the task list.
Format: `delete INDEX`
* Deletes the task at the specified `INDEX`. The index refers to the index number shown in the full displayed task list. The index **must be a positive value** 1, 2, 3...

### Finding tasks that match a phrase: `find`

Shows tasks in the task list which contain the given phrase in their descriptions.
Format: `find PHRASE`
Example:
* `find go to school`

### Showing upcoming tasks: `remind`

Shows deadlines that end and events that start within 2 days of the current time.
Format: `remind`

### Exiting the program: `bye`

Exits the program.
Format: `bye`

### Saving the data

Task list data are saved in the hard disk automatically after every command. There is no need to save manually.

## Command summary
| Command | Format | Examples |
| --- | --- | --- |
| todo | `todo DESCRIPTION` | `todo watch TV` |
| deadline | `deadline DESCRIPTION /by <dd-MM-yyyy HH:mm>` | `deadline submit assignment /by 17-03-2023 11:00` |
| event | `event DESCRIPTION /from <dd-MM-yyyy HH:mm> /to <dd-MM-yyyy HH:mm>` | `event school /from 21-03-2023 09:00 /to 21-03-2023 11:00` |
| list | `list` |  |
| mark | `mark INDEX` | `mark 1` |
| unmark | `unmark INDEX` | `unmark 1` |
| delete | `delete INDEX` | `delete 1` |
| find | `find` |  |
| remind | `remind` |  |
| bye | `bye` |  |