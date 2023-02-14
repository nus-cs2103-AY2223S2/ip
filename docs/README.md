# User Guide


![Ui.png](Ui.png)
## Duke Setup
1. Download the latest `duke.jar` file from [releases](https://github.com/weekiat-douze/ip/releases)
2. Put `duke.jar` in where you want to run it
3. `java -jar duke.jar` to start Duke

## Features 

### Tracking Tasks

You can add tasks of types **todo**, **deadline**, or **events**.
Deadline tracks the date of the task's deadline.
Event tracks the start and end time of the event. <br>
All the tasks are saved locally so next time you use Duke, your tasks will still be available.<br>
After you have completed the tasks you can mark them as complete.
If there is no need for Duke to track the task, deletion can be done as well.

### Listing Tasks

Duke allows you to see all the tasks you have currently. 
Listing lets you see the description, type and status of your tasks.<br>
Any operations you would like perform on the tasks (e.g. delete, mark, unmark) 
can be done in mass by referencing the task id in shown in the list.


### Searching for Tasks
To quicky find tasks, Duke has a `find` feature that allows you to quickly bring up tasks. 
Just give Duke any part of the description of your tasks. 

## Usage
____

### `todo` - Add Todos
*  `todo [description]`

This adds a task of type "todo" into your list of tasks.

____

### `deadline` - Add Deadlines
* Usage: `deadline [description] /by [yyyy-MM-dd HHmm]`

This adds a task of type "deadline" together with the date-time provided. 
___

### `event ` - Add Events
* Usage: `event [description] /from [yyyy-MM-dd HHmm] /to [HHmm]`

This adds a task of type "event" which contains start datetime and end time. 

___

### `list` - List all existing tasks
* Usage: `list`

This command shows all tasks you have added. 
Tasks are numbered which is used to reference during mark, unmark, delete.

___

### `delete [id]` - Delete Tasks
* Usage: `delete [id 1] [id 2] [id 3] ...`

Deletes task with the referenced id (see [list task](#list---list-all-existing-tasks)).<br>
You can delete one or more tasks by just referencing their id. 

___

### `mark [id]` - Mark Task as complete
* Usage: `mark [id 1] [id 2] [id 3] ...`

Mark tasks as complete with referenced id (see [list task](#list---list-all-existing-tasks)).<br>
You can mark multiple tasks at once by referencing their id.

___

### `unmark [id]` - Mark Task as incomplete
* Usage: `unmark [id 1] [id 2] [id 3] ...`

Unmark task's complete status with referenced id (see [list task](#list---list-all-existing-tasks)).<br>
You can unmark multiple tasks at once by referencing their id.

___

### `find [description]` - Search for tasks
* Usage: `find part of description]`

To quickly find tasks use `find` command and enter the search term.<br>
Duke will return all tasks whose description contains the search term. 

___

