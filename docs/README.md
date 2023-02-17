# User Guide

## Features 

### Add Entries

Add tasks to the list in the chatbot. These can be one of 3 categories: To-do, Deadline, and Event.
To-do tasks are just tasks to do, containing only the task name. 
Deadlines are to-do tasks with a defined deadline.
Events are to-do tasks with a defined time range.

### Mark and Unmark Completion

Mark each of the tasks within the list as completed or not completed.

### Delete Tasks

Easily update the list by deleting entries.

### Undo

Undo the last command you issued that changed the list.

### View List

Be able to view the entire list at any point.

## Usage

### `todo` - Adds todo task

Adds a new todo task with the defined title to the list of tasks.

Example of usage: 

`todo <task title>`

Expected outcome:

If correctly inputted, a new todo task will be added to the list and a confirmation message will be displayed; otherwise an error message will display.

```
Gotcha. Just added this task to the list:
[T] [ ] <Task Name>
You have 1 tasks left. Anything else?
```

### `deadline` - Adds deadline task

Adds a new deadline task with the defined title and end date to the list of tasks.

Example of usage:

`deadline <task title> /by <deadline date>`

Expected outcome:

If correctly inputted, a new deadline task will be added to the list and a confirmation message will be displayed; otherwise an error message will display.

```
Gotcha. Just added this task to the list:
[D] [ ] <Task Name> (by: <deadline date>) 
You have 1 tasks left. Anything else?
```

### `event` - Adds deadline task

Adds a new event task with the defined title, start time and end time to the list of tasks.

Example of usage:

`event <task title> /from <start time> /to <end time> `

Expected outcome:

If correctly inputted, a new event task will be added to the list and a confirmation message will be displayed; otherwise an error message will display.

```
Gotcha. Just added this task to the list:
[E] [ ] <Task Name> (from: <start time> to: <end time>) 
You have 1 tasks left. Anything else?
```

### `mark` - Marks a task

Marks a task as completed in the list.

Example of usage:

`mark <index>`

Expected outcome:

If correctly inputted, the chosen task is marked as completed and a confirmation message will be displayed; otherwise an error message will display.

```
Gotcha. Just marked this task as done:
[T] [X] <Task Name>
```

### `unmark` - Unmarks a task

Marks a task as not completed in the list.

Example of usage:

`unmark <index>`

Expected outcome:

If correctly inputted, the chosen task is marked as not completed a confirmation message will be displayed; otherwise an error message will display.

```
Gotcha. Just marked this task as not done:
[T] [X] <Task Name>
```

### `delete` - Deletes a task

Removes a task from the list.

Example of usage:

`delete <index>`

Expected outcome:

If correctly inputted, a confirmation message will be displayed; otherwise an error message will display.

```
Uh huh. Just removed this task:
[T] [X] <Task Name>
You have 0 tasks left. Anything else?
```

### `find` - Searches for tasks

Finds all tasks in the list currently that fit the given keyword

Example of usage:

`find <keyword>`

Expected outcome:

If correctly inputted, all tasks that contain the keyword will be listed, and a confirmation message will be displayed; otherwise an error message will display.

```
Righto, here are the tasks that contain the word <keyword>:
[T] [X] <Task Name>
```

### `find` - Searches for tasks

Finds all tasks in the list currently that fit the given keyword

Example of usage:

`find <keyword>`

Expected outcome:

If correctly inputted, all tasks that contain the keyword will be listed, and a confirmation message will be displayed; otherwise an error message will display.

```
Righto, here are the tasks that contain the word <keyword>:
[T] [X] <Task Name>
```

### `list` - View list

Displays all entries in the list

Example of usage:

`list`

Expected outcome:

If correctly inputted, all tasks will be listed; otherwise an error message will display.

```
Uh huh yeah. Here's your list yo:
1. [T] [X] <Task Name>
```

### `undo` - Reverts to previous list state

Undos the last action that edited the list.

Example of usage:

`undo`

Expected outcome:

If correctly inputted, the list will be reverted back to its previous state and a confirmation message will be displayed, otherwise an error message will display.

```
Alright yo, just dialed back the list to its previous iteration. Here's what it looks like now:
1. [T] [X] <Task Name>
```

### `bye` - Exits program

Exits the program and stores the current list in a text file.

Example of usage:

`bye`

Expected outcome:

If correctly inputted, the program will quit and a confirmation message will be displayed, otherwise an error message will display.

```
Pleasure doing business with you.
```