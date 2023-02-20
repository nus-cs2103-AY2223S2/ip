# Vincent

_Vincent_ is a task-management application optimized for use via Command Line Interface `CLI`
while still retaining the benefits of a traditional Graphical User Interface `GUI`.

- Quick Start
- Features
    - Adding tasks:
        - Adding a ToDo : `todo`
        - Adding a Deadline : `deadline`
        - Adding an Event: `event`
    - Listing all tasks: `list`
    - Marking a task as done: `mark`
    - Marking a task as undone: `unmark`
    - Finding tasks: `find`
    - Sorting deadlines in ascending order: `sort`
    - Exiting the application: `bye`
    - Saving data
- Command Summary

## Quick Start

* Ensure that you have `Java 11` or above installed on your PC.
* Download the latest `duke.jar` from [here](https://github.com/redHat-arko/ip/releases).
* Copy the file to the folder you want to use as the home folder for `Vincent`.
* Open a `command terminal`, `cd` into the folder you put the `jar`
  file in, and use the `java -jar duke.jar` command to run the application. A GUI should appear in a few seconds.
* Type the `command` in the text box and press Enter or click on the "Send" button to execute it.

## Usage

### Adding tasks

#### Adding a `ToDo`

ToDos are tasks without any date/time attached.

Format: `todo <description>`

`description` is the description of the `ToDo`.

For example: `todo borrow book`

#### Adding a Deadline

Deadlines are tasks that need to be done before a specific date.

Format: `deadline <description> /<by>`

`description` is the description of the `Deadline`.

`by` is the date the task is due. Dates have to be formatted in the `YYYY-MM-DD` format.

For example: `deadline return book /by 2023-02-28`

#### Adding an Event

Events are tasks that start at a specific date/time and ends at a specific date/time.

Format: `event <description> /<from> /<to>`

`description`: the description of the `Event`.

`from`: when the Event starts

`to`: when the Event ends

For example: `event study /from Monday /to Wednesday`

### Listing all tasks

You can list all tasks using the following command: `list`

### Marking a task as done

You can mark a task at a specified index as done.

Format: `mark <index>`

`index` is the position of the task in the list.

For example: `mark 1` marks the task at position `1` as done.

### Marking a task as undone

You can mark a task at a specified index as undone.

Format: `unmark <index>`

`index` is the position of the task in the list.

For example: `unmark 1` marks the task at position `1` as undone.

### Finding tasks

You can find all tasks matching a keyword from the list of tasks.

Format: `find <keyword>`

For example: `find book` will list all tasks with the word `book` in their `description`.

### Sorting deadlines in ascending order

You can view all tasks of type `Deadline` sorted in ascending order of when they are
due with the following command: `sort`

### Exiting the application

You can exit the application with the command: `bye`

### Saving data

The data is automatically saved to the hard drive upon application exit using the command `bye`.

## Command Summary

| Command    | Functionality                        |
|:-----------|:-------------------------------------|
| `todo`     | Add a ToDo                           |
| `deadline` | Add a Deadline                       |
| `event`    | Add an Event                         |
| `list`     | List all tasks                       |
| `mark`     | Mark a task as done                  |
| `unmark`   | Mark a task as undone                |
| `find`     | Find all tasks matching a keyword(s) |
| `sort`     | Sort deadlines in ascending order    |
| `bye`      | Exit the application                 |