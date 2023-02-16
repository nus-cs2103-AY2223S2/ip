# User Guide
Duke is a **desktop application for managing your tasks via a Command Lind Interface** (CLI) while still
having the benefits of a Graphical User Interface (GUI).

## Quick start
1. Ensure you have Java 11 or above installed in your Computer.
2. Download the latest duke.jar from [here]().
3. Copy the file to the folder you want to use as the home folder for Duke.
4. Open a command terminal, cd into the folder you put the jar file in, and use the java -jar duke.jar command to run the application.
A GUI similar to the below should appear in a few seconds.
![This is an image](/docs/Starting.png)
5. Type the command in the command box and press Enter to execute it. e.g. typing help and pressing Enter will open the help window.
Some example commands you can try:

- `list` : Lists all contacts.
- `todo gym` : Adds a todo task with task description `gym`.
- `mark 1` : Marks the first task as done.
- `delete 1` : Deletes the first task shown in the current list.

6. Refer to the [Features](##Features) below for details of each command.

## Features 

### Listing all tasks: `list`
Shows a list of all tasks in the task list.

Format: `list` or 'l'

### Adding a todo task: `todo`
Adds a todo task to the task list.

Format: `todo [TASK]` or `t [TASK]`

Examples:
- `todo gym`
- `t drink protein`

### Adding a deadline task: `deadline`
Adds a deadline task to the task list.

Format: `deadline [TASK] /by [YYYY-MM-DD]` or `dl [Task]`

Examples:
- `deadline lift 100kg /by 2023-01-01`
- `dl squat 300kg /by 2023-12-12`

### Adding a deadline task: `event`
Adds an Event task to the task list.

Format: `event [TASK] /from [START] /to [END]` or `e [task] /from [start] /to [end]`

Examples:
- `event gym /from morning /to night`
- `e eat lunch /from 2pm /to 3pm`

### Marking a task `mark`
Marks a task in the task list as done.

Format: `mark INDEX` or `m INDEX`

- Marks the task of the specified `INDEX`.
- The `INDEX` refers to the index number shown in the displayed task list.
- The index **must be a positive integer** 1, 2, 3, ...

Examples:

- `list` followed by `mark 1` marks the 1st task in the task list as done.
- `find gym` followed by `m 2` marks the 2nd person in the results of the `find` command.

### Un-marking a task: `unmark`
Undo task in the task list as not done.

Format: `unmark INDEX` or `u INDEX`

- Un-marks the task of the specified `INDEX`.
- The `INDEX` refers to the index number shown in the displayed task list.
- The index **must be a positive integer** 1, 2, 3, ...

Examples:

- `list` followed by `unmark 1` un-marks the 1st task in the task list as done.
- `find gym` followed by `u 2` un-marks the 2nd person in the results of the `find` command.

### Adding a deadline task: `find`
Find tasks whose description contains any of the given keywords.

Format: `find KEYWORD` or `f KEYWORD`

Examples:
- `find gym` returns `[T][] gym`
- `f eating` returns `[T][] eating`

### Adding a deadline task: `delete`
Deletes the specified task in the task list.
Format: `delete INDEX` or `d INDEX`

- Deletes the task of the specified `INDEX`.
- The `INDEX` refers to the index number shown in the displayed task list.
- The index **must be a positive integer** 1, 2, 3, ...

Examples:

- `list` followed by `delete 1` un-marks the 1st task in the task list as done.
- `find gym` followed by `d 2` un-marks the 2nd person in the results of the `find` command.

### Saying farewell: `bye`
Say farewell to Duke.
Format: `bye` or `b`


### Saving the data
AddressBook data are saved in the hard disk automatically after any command that changes the data. 
There is no need to save manually.

## Commands Summary
| Action     | Format, Examples                                                                   |
|------------|------------------------------------------------------------------------------------|
| list       | `list`                                                                             |
| todo       | `todo [TASK]`<br/>e.g., `todo GYM`                                                 |
| deadline   | `deadline [TASK] /by [YYYY-MM-DD]`<br/>e.g., `deadline lift 1000kg /by 2023-12-12` |
| event      | `event [TASK] /from [START] /to [END]`<br/>e.g., `event eat /from 1pm /to 2pm`     |
| mark       | `mark [INDEX]`<br/>e.g., `mark 1`                                                  |
| unmark     | `unmark [INDEX]`<br/>e.g., `unmark 1`                                              |
| find       | `find [keyword]`<br/>e.g., `find gym`                                              |
| delete     | `delete [INDEX]`<br/>e.g., `delete 1`                                              |
| bye        | `bye`                                                                              |