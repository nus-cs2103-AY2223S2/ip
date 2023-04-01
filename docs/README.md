# User Guide: Peppa

## Introduction
Peppa 🐽 is a desktop application that helps you manage your tasks efficiently. It is optimised for use via a Command
Line Interface (CLI), but still has benefits of a Graphical User Interface (GUI) so that you get to experience the
**_best_** of both worlds. With Peppa, you will no longer miss your deadlines or forget your todos 🙌🏼.

## Features 

### Viewing all tasks: `list` 

Displays all tasks in the current tasklist, along with details like the id, status and type of each task.

Format: `list`

<br/>

### Viewing all data sources: `files`

Displays all possible files from which Peppa can try to load task data. Only checks for files in the `/data` directory.  

Format: `files`

<br/>

### Adding a todo task: `todo` 

Adds a todo item to the tasklist.

Format: `todo <task_description>`

Examples: 
* `todo Watch CS2106 lecture`
* `todo Water plants`

<br/>

### Adding a deadline task: `deadline`

Adds a deadline to the tasklist.

Format: `deadline <task_description> /by <date_due_in_dd/mm/yyyy> <time_due_in_HHmm>`

Examples:
* `deadline Finish CS2107 assignment /by 02/03/2023 1200`
* `deadline Complete essay draft /by 20/02/2023 2359`

<br/>

### Adding an event task: `event`

Adds an event to the tasklist.

Format: `event <task_description> /from <start_date_in_dd/mm/yyyy> /to <end_date_in_dd/mm/yyyy>`

Examples:
* `event Family trip to Japan /from 21/02/2023 /to 27/02/2023`
* `event Chalet with friends /from 06/06/2023 /to /08/06/2023`

<br/>

### Deleting a task: `delete`

Removes a task from the tasklist at the specified index. 

Format: `delete <task_id>`

Example:
* `delete 1`

<br/>

### Finding a task: `find`

Searches for all matching tasks that contain the specified keyword(s). 
To search using multiple keywords, separate them with a space in between. 

Format: `find <keyword_1> (keyword_2 ... keyword_n)`

Example:
* `find CS2106`
* `find proposal draft presentation`

<br/>

### Selecting another data source: `select`

Selects another file to load data from, if possible. Should be used after the `files` command. 

Format: `select <file_id>`

Example:
* `select 2`

<br/>

### Marking a task as complete: `mark`

Indicates a task is done by updating its status to complete.  

Format: `mark <task_id>`

Example:
* `mark 1`

<br/>

### Unmarking a task as complete: `unmark`

Indicates a task is not done by updating its status to incomplete.

Format: `unmark <task_id>`

Example:
* `unmark 1`

<br/>

### Exiting the application: `bye`

Closes the application.

Format: `bye`
