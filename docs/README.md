# User Guide
Duke is a Personal Assistant Chatbot that helps a person to keep track of various things.

## Usage
Open a command terminal, `cd` into the folder you put the jar file in, and use the `java -jar dukeApplication.jar` command to run the application.

Type any of the commands below into the user input box and press Enter to execute.

### `todo` - Todo task

Adds a simple Todo task to the list

Format: `todo {task name}`

Example of usage: 

`todo exampleTask`

Expected outcome:

```
Task added:
[Todo] [] exampleTask
Remaining task count: 1
```
Acknowledge and display the added task, alongside the number of tasks in the list.

### `deadline` - Deadline task

Adds a Deadline task with the due date, to the list.
Due date should be of the form `YYYY-MM-DD`

Format: `deadline {task name} /by {due date}`

Example of usage:

`deadline exampleTask /by 2023-02-18`

Expected outcome:

```
Task added:
[Deadline] [] exampleTask (BY: Feb 18 2023)
Remaining task count: 1
```
Acknowledge and display the added task, alongside the number of tasks in the list.
Due date is also displayed in a more readable format.

### `event` - Event task

Adds a Event task with the start date and end date, to the list.
Dates should be of the form `YYYY-MM-DD`

Format: `event {task name} /from {start date} /to {end date}`

Example of usage:

`event exampleTask /from 2023-02-18 /to 2023-02-19`

Expected outcome:

```
Task added:
[Event] [] exampleTask (FROM: Feb 18 2023 | TO: Feb 19 2023)
Remaining task count: 1
```
Acknowledge and display the added task, alongside the number of tasks in the list.
Start and end dates are also displayed in a more readable format.

### `list` - List all tasks

Displays all the tasks in the list

Example of usage:

`list`

Expected outcome:

```
Current tasks in list:
1. [Todo] [] exampleTask
```
If there are any tasks in the list, they are displayed.

### `mark` - Mark task

Changes the status of a task to completed in the list
Task is specified by its position in the list

Format: `mark {position number}`

Example of usage:

`mark 1`

Given the current list below, 

```
Current tasks in list:
1. [Todo] [] exampleTask
```

Expected outcome:

```
Well done. Task has been marked as completed:
[Todo] [O] exampleTask
```
Acknowledge marking of task and displays current details of task.
Task should now have an `[O]` indication. 


### `unmark` - Unmark task

Removes the completed status of a task in the list
Task is specified by its position in the list

Format: `unmark {position number}`

Example of usage:

`unmark 1`

Given the current list below,

```
Current tasks in list:
1. [Todo] [O] exampleTask
```

Expected outcome:

```
Got it. Task has been unmarked:
[Todo] [] exampleTask
```

Acknowledge marking of task and displays current details of task.
Task should now have an `[]` indication instead of `[O]`.


### `delete` - Delete task
 
Removes a task from the list
Task is specified by its position in the list

Format: `delete {position number}`

Example of usage:

`delete 1`

Given the current list below,

```
Current tasks in list:
1. [Todo] [] exampleTask
```

Expected outcome:

```
Task removed:
[Todo] [] exampleTask
Remaining task count: 0
```
Acknowledge deleting of task and displays details of removed task.
The remaining number of tasks in the list is also shown. 

### `search` - Search amongst task list

Searches through the names of the tasks in the list with a given keyword.

Format: `search {keyword phrase}`

Example of usage:

`search example`

Given the current list below,

```
Current tasks in list:
1. [Todo] [] exampleTask
```

Expected outcome:

```
Here are the search results:
1. [Todo] [] exampleTask
```

Tasks that matches the keyword (even if only partially matching), will be listed out.

### `bye` - Exit

Exits the application

Example of usage:

`bye`

Expected outcome:

```
Farewell, closing in a bit...
```
Displays acknowledgement message and application will close after 2 seconds.