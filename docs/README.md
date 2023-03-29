# User Guide

Duke is a **desktop app for managing tasks, optimized for use via a Command Line Interface** (CLI) while still having the benefits of a Graphical User Interface (GUI). If you can type fast, Duke can get your tasks management done faster than traditional GUI apps.

## Quick start

1. Ensure you have Java `11` or above installed in your Computer.

1. Download the latest `duke.jar` from [here](https://github.com/kenzantonius/ip/releases).

1. Copy the file to the folder you want to use as the _home folder_ for your Duke.

1. Double click the jar file to run the application.<br>
   A GUI similar to the below should appear in a few seconds. Note how the app contains some sample data.<br>
   ![Ui](images/Ui.png)

1. Type the command in the command box and press Enter to execute it.<br>
   Some example commands you can try:

   * `list` : Lists all tasks.

   * `delete 3` : Deletes the 3rd task shown in the task list.

   * `mark 3` : Mark the 3rd task shown in the task list.

   * `bye` : Exits the app.

1. Refer to the [Features](#features) below for details of each command.

--------------------------------------------------------------------------------------------------------------------

## Features

### Adding a todo task: `todo`

Adds a todo task to the task list.

Format: `todo TASK_NAME`

Examples:
* `todo borrow book`

### Adding a deadline task: `deadline`

Adds a event task to the task list.

Format: `deadline TASK_NAME /by DUE_DATE`

Examples:
* `deadline return book /by Sunday`

### Adding a event task: `event`

Adds a event task to the task list.

Format: `event TASK_NAME /from START_DATE /to END_DATE`

Examples:
* `event project meeting /from Mon 2pm /to 4pm`

### Mark a task : `mark`

Marks the specified task from the task list.

Format: `mark INDEX`

* Deletes the task at the specified `INDEX`.
* The index refers to the index number shown in the displayed task list.
* The index **must be a positive integer** 1, 2, 3, …​

Examples:
* `mark 2` marks the 2nd task in the task list.

### Unmark a task : `unmark`

Unmarks the specified task from the task list.

Format: `unmark INDEX`

* Deletes the task at the specified `INDEX`.
* The index refers to the index number shown in the displayed task list.
* The index **must be a positive integer** 1, 2, 3, …​

Examples:
* `unmark 2` unmarks the 2nd task in the task list.

### Listing all tasks : `list`

Shows a list of all tasks in the task list.

Format: `list`

### Locating tasks by keyword: `find`

Finds tasks whose tasks contain any of the given keywords.

Format: `find KEYWORD`

Examples:
* `find John` returns tasks that have a keyword `John`

### Deleting a task : `delete`

Deletes the specified task from the task list.

Format: `delete INDEX`

* Deletes the task at the specified `INDEX`.
* The index refers to the index number shown in the displayed task list.
* The index **must be a positive integer** 1, 2, 3, …​

Examples:
* `delete 2` deletes the 2nd task in the task list.

### Exiting the program : `bye`

Exits the program.

Format: `bye`

### Saving the data

Duke data are saved in the hard disk automatically after any command that changes the data. There is no need to save manually.

### Editing the data file

Duke data are saved as a txt file `[JAR file location]/data/duke.txt`. Advanced users are welcome to update data directly by editing that data file.

<div markdown="span" class="alert alert-warning">:exclamation: **Caution:**
If your changes to the data file makes its format invalid, AddressBook will discard all data and start with an empty data file at the next run.
</div>

### Archiving data files `[coming in v2.0]`

_Details coming soon ..._

--------------------------------------------------------------------------------------------------------------------

## FAQ

**Q**: How do I transfer my data to another Computer?<br>
**A**: Install the app in the other computer and overwrite the empty data file it creates with the file that contains the data of your previous Duke home folder.
