# Duke: User Guide

**Duke** is a command-based note-taking desktop app that allows you to quickly add, remove, and view your tasks.

## Quick Start

1. Ensure you have Java 11 or above installed on your computer.

2. Download the latest `duke.jar` from [here](https://github.com/filbertphang/ip/releases)

3. Copy the file to the folder you want to use as the home folder for Duke.

4. Open a command terminal, `cd` into the folder you put the jar file in, and run the application with `java -jar duke.jar`. A GUI similar to the below should appear in a few seconds.
![UI Screenshot](Ui.png)

5. Type a command in the chat box and press "Enter" or press the "Send" button to execute it. See below for a list of possible commands.

## Commands

### List all tasks: `list`

Syntax: `list`

Displays a list of all saved tasks and their statuses.

### Add To-Do: `todo`

Syntax: `todo <DESCRIPTION>`

Adds a To-Do task to Duke. To-Do tasks only contain a description of a task to be completed.

### Add Deadline: `deadline`

Syntax: `deadline <DESCRIPTION> /by <DATE>`

Adds a Deadline task to Duke. Deadline tasks contain a description of a task to be completed, and a deadline for the task to be completed by.

_Note_: The date field must be given in the format "dd-MM-yyyy".

### Add Event: `event`

Syntax: `event <DESCRIPTION> /start <DATE> /end <DATE>`

Adds a Event task to Duke. Event tasks contain a description of a task to be completed, a start date, and an end date for that Event.

_Note_: Both date fields must be given in the format "dd-MM-yyyy".

### Mark task as done: `mark`

Syntax: `mark <INDEX>`

Marks the task at the given index as done.

### Mark task as undone: `unmark`

Syntax: `unmark <INDEX>`

Marks the task at the given index as not done yet.

### Delete task: `delete`

Syntax: `delete <INDEX>`

Deletes the task atthe given index .

### Find task: `find`

Syntax: `find <KEYWORD>`

Lists all tasks that match the given keyword.

### Archive all tasks: `archive`

Syntax: `archive` OR `archive <FILENAME>`

Archives all saved tasks to a file, then clears the task list.

Tasks will be saved to the given filename if provided, otherwise it will be saved to "archive-dd-MM-yyyy-HH-mm.txt" in the current directory.

### Exit Duke: `bye`

Syntax: `bye`

Bye!
