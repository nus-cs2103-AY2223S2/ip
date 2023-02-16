# User Guide

## Features 

### Adding a task
Adds a task to the list of tasks.

Format:`todo [description]`

Format:`deadline [description] /by [yyyy-mm-dd]`

Format:`event [description] /from [yyyy-mm-dd] /to [yyyy-mm-dd]`
###

### Listing all tasks: `list`
Shows a list of all tasks in the TaskList.

Format: `list`
###

### Locating tasks by description: `find`
Shows a list of all tasks in the TaskList that matches the keyword.

Format: `find [keyword]`
* The search is case-insensitive. e.g `read` will match `Read`
* Only the task's description is searched.
* Partial words will be matched e.g. `Read` will match `Read book`
###

### Deleting a task: `delete`
Deletes the specified task from the TaskList.

Format: `delete [index]`
* Deletes the task at the specified `index`.
* The index refers to the index number shown in the displayed task list.
* The index must be a positive integer 1, 2, 3, …
###

### Marking a task as done: `mark`
Changes the status of the specified task to done.

Format: `mark [index]`
* Marks the task at the specified `index`.
* The index refers to the index number shown in the displayed task list.
* The index must be a positive integer 1, 2, 3, …
###

### Changing a task's status to not done: `unmark`
Changes the status of the specified task to not done.

Format: `unmark [index]`
* Unmarks the task at the specified `index`.
* The index refers to the index number shown in the displayed task list.
* The index must be a positive integer 1, 2, 3, …
###

### Exiting the program : exit
Exits the program.

Format: `exit`
###

### Duplicate data
Duke checks for duplicate tasks when adding a new task to the list of task.


###

### Saving the data
Task data are saved in the hard disk automatically after any command
that changes the data. There is no need to save manually.
###

### Editing the data file
Task data are saved as a text file
`[JAR file location]/data/tasks.txt`.
Advanced users are welcome to update data directly by editing that data file.

```
:exclamation: **Caution** If your changes to the data file makes its format invalid, 
TaskList will discard all data and start with an empty data file at the next run.
```
###
___
### FAQ
Q: How do I transfer my data to another Computer?

A: Install the app in the other computer and overwrite the empty data file it creates with the file that contains the data of your previous Duke home folder.
###
___
## Command summary

|  Action  | Format                                                    | Examples                                                                    |
|:--------:|:----------------------------------------------------------|:----------------------------------------------------------------------------|
|   todo   | `todo [description]`                                      | Adds a ToDo task<br/>e.g., `todo Read book`                                 |
| deadline | `deadline [description] /by [yyyy-mm-dd]`                 | Adds a Deadline task<br/>e.g., `deadline Return book /by 2024-01-01`        |
|  event   | `event [description] /from [yyyy-mm-dd] /to [yyyy-mm-dd]` | Adds an event <br/>e.g., `event Book  fair /from 2024-01-01 /to 2024-02-01` |
|  delete  | `delete [index]`                                          | Removes the task at index 1<br/>e.g., `delete 1`                            |
|   exit   | `exit`                                                    | Exits duke<br/>e.g., `exit`                                                 |
|   find   | `find [keyword]`                                          | Looks for tasks with `book`<br/>e.g., `find book`                           |
|   list   | `list`                                                    | Shows the list of tasks in the TaskList<br/>e.g., `list`                    |
|   mark   | `mark [index]`                                            | Marks task at index 1 as done<br/>e.g., `mark 1`                            |
|  unmark  | `unmark [index]`                                          | Marks task at index 1 as not yet done<br/>e.g., `unmark 1`                  |

