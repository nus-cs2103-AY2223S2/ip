# User Guide
Duke is a **desktop application for managing your tasks and notes via a Command Lind Interface** (CLI) while still
having
the benefits of a Graphical User Interface (GUI).

***
## Quick Start
1. Ensure you have Java 11 or above installed in your computer. Else, you can install it from [here](https://www.azul.com/downloads/?version=java-11-lts&os=macos&architecture=arm-64-bit&package=jdk-fx).
2. Download the latest duke.Launcher.jar from [here]().
3. Copy the file to the folder you want to use as the home folder for your Duke.
4. Open a command terminal. `cd` into the folder you put the jar file in, and use the `java -jar duke.Launcher.jar` command to run the application. You should see a GUI similar to this image below.
<figure>
    <img src="/docs/images/Ui.png"
         alt="GUI example">
</figure>

5. Type the command in the input box and press `Send` to execute it. Some example commands you can try:
- `list`: List all tasks.
- `deadline read book /by 2023-02-14`: Adds a task with deadline by 14-02-2023 named `read book`.
- `mark 1`: Marks the 1st task as done.
- `delete 1`: Deletes the 1st task.
- `bye`: Exits the app.
6. Refer to the [Features](##Features) below for details of each command.
   <br />
   <br />

***
## Features
### Listing all tasks: list
Show a list of all tasks in the task list.<br />
Format: `list`
<br />
<br />

### Listing all notes: list
Show a list of all tasks in the task list.<br />
Format: `notes`
<br />
<br />

### Adding a note: `note`
Add a note to the list of notes.<br />
Format: `note [things to note]`<br /><br />
Examples:
- `note I lent Alice my shirt`
- `note new song: Flowers`
  <br />
  <br />


### Adding a todo: `todo`
Add a todo task to the task list.<br />
Format: `todo [task]`<br /><br />
Examples:
- `todo go to the supermarket`
- `todo homework`
  <br />
  <br />


### Adding a deadline: `deadline`
Add a deadline to the task list.<br />
Format: `deadline [task] /by [YYYY-MM-DD]`<br /><br />
Examples:
- `deadline read book /by 2023-02-14`
- `deadline presentation /by 2023-02-20`
<br />
  <br />


### Adding an event: `event`
Add an event to the task list.<br />
Format: `event [task] /from [time] /to [time]`<br /><br />
Examples:
- `event meeting /from Monday 2pm /to 4pm`
- `event showcase /from 23rd Jan /to 24th Jan`
  <br />
  <br />

### Marking a task: `mark`
Mark an existing task as done.<br />
Format: `mark [task number]`<br />
- Mark the task with the specified `task number` as done.
- The `task number` refers to the index number shown in the displayed task list.
- The index must be a positive integer starting from 1 and smaller or equal to the number of tasks in the list.<br /><br />
  Examples:
- `mark 1`
- `mark 3`
  <br />
  <br />

### Un-marking a task: `unmark`
Mark an existing task as not done.<br />
Format: `unmark [task number]`<br />
- Un-mark the task with the specified `task number`.
- The `task number` refers to the index number shown in the displayed task list.
- The index must be a positive integer starting from 1 and smaller or equal to the number of tasks in the list.<br /><br />
  Examples:
- `unmark 2`
- `unmark 5`
  <br />
  <br />


### Find a task: `find`
Find tasks that contain the given keyword.<br />
Format: `find [keyword]`<br />
- The search is case-sensitive.<br /><br />
  Examples:
- `find buy book` returns `T | X | buy book`
- `find 23rd Jan` returns `E | O | showcase | 23rd Jan - 24th Jan`
  <br />
  <br />


### Deleting a task: `delete`
Deletes the task with the specified task number from the task list.<br />
Format: `delete [task number]`<br />
- Deletes the task with the specified `task number`
- The `task number` refers to the index number shown in the displayed task list.
- The index must be a positive integer starting from 1 and smaller or equal to the number of tasks in the list.<br /><br />
  Examples:
- `delete 1`
- `delete 2`
  <br />
  <br />


### Deleting a note: `n-delete`
Deletes the note with the specified note number from the list of notes.<br />
Format: `n-delete [note number]`<br />
- Deletes the note with the specified `note number`
- The `note number` refers to the index number shown in the displayed note list.
- The index must be a positive integer starting from 1 and smaller or equal to the number of notes in the list.<br /><br />
  Examples:
- `n-delete 1`
- `n-delete 2`
  <br />
  <br />


### Exiting the program: `bye`
Exits the program.<br />
Format: `bye`
<br />
<br />


### Saving the data
Duke data are saved in the hard disk automatically after any command that changes the data. There is no
need to save manually.
<br />
<br />
***
## Commands Summary
| Action     | Format, Examples                                                                                             |
|------------|--------------------------------------------------------------------------------------------------------------|
| list       | `list`                                                                                                       |
| notes      | `notes`                                                                                                      |
| note       | `note [things to note]`<br/>e.g., `note new song: Flowers`                                                   |
| todo       | `todo [task]`<br/>e.g., `todo homework`                                                                      |
| deadline   | `deadline [task] /by [YYYY-MM-DD]`<br/>e.g., `deadline return book /by 2019-10-15`                           |
| event      | `event [task] /from [YYYY-MM-DD] /to [YYYY-MM-DD]`<br/>e.g., `event project /from 2018-03-10 /to 2018-03-11` |
| mark       | `mark [task number]`<br/>e.g., `mark 1`                                                                      |
| unmark     | `unmark [task number]`<br/>e.g., `unmark 1`                                                                  |
| find       | `find [keyword]`<br/>e.g., `find school`                                                                     |
| delete     | `delete [task number]`<br/>e.g., `delete 1`                                                                  |
| n-delete   | `n-delete [note number]`<br/>e.g., `n-delete 1`                                                              |
| bye        | `bye`                                                                                                        |


