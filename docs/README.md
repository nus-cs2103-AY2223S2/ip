# Duke: User Guide

**Duke** is a command-based note-taking desktop app that allows you to quickly add, remove, and view your tasks.

## Features

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

Marks the task at `INDEX` as done.

### Mark task as undone: `unmark`

Syntax: `unmark <INDEX>`

Marks the task at `INDEX` as not done yet.

### Delete task: `delete`

Syntax: `delete <INDEX>`

Deletes the task at `INDEX`.
