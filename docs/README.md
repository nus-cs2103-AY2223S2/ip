# User Guide for Duke: The task-list chatbot

## Quick Start

1. Ensure you have Java 11 or later installed. Get it [here](https://www.oracle.com/sg/java/technologies/javase/jdk11-archive-downloads.html).
2. Download the latest version of Duke [here](https://github.com/pzhengze/iP/releases/latest).
3. Place `duke.jar` into a folder of your choice.
4. Double click `duke.jar` to run.

## Features 

### Feature-Greets user

Duke greets the user on startup.

### Feature-Exit

Closes session with Duke and saves task-list data.

### Add Tasks

Add Tasks to Duke's task-list.
There are 3 types of tasks available:
- Todos 
- Deadlines
- Events

### Delete Tasks

Delete Tasks from Duke's task-list.

### Mark/Unmark Tasks

Mark/Unmark Tasks in Duke's task-list as complete/incomplete.

### List Tasks

List all Tasks in Duke's task-list.

### Find Tasks

Searches for Tasks that contains the keyword from Duke's task-list.

## Usage

### `bye` - Saves and exits Duke

Saves task-list data and closes session with Duke.

Example of usage: 

`bye`

Expected outcome:

Saves and exits Duke.

```
bye
```

### `todo` - Adds Todo task

Creates a todo task and adds it into Duke's task-list.

Example of usage: 

`todo description`

Expected outcome:

Duke's task-list contains new todo.

```
Got it. I've added this task:
 [T][] description
Now you have 1 tasks in the list.
```

### `deadline` - Adds Deadline task

Creates a deadline task and adds it into Duke's task-list.
Due date should be inputted in the format `DD/MM/YYYY HHMM` else it will be treated as normal text.

Example of usage: 

`deadline description /by dueDate`

Expected outcome:

Duke's task-list contains new deadline.

```
Got it. I've added this task:
 [D][] description (by: dueDate)
Now you have 1 tasks in the list.
```

Example of usage: 

`deadline description /by 17/02/2023 2359`

Expected outcome:

Duke's task-list contains new deadline.

```
Got it. I've added this task:
 [D][] description (by: 17 Feb 2023 2359)
Now you have 1 tasks in the list.
```

### `event` - Adds Event task

Creates a event task and adds it into Duke's task-list.
Duration should be inputted in the format `DD/MM/YYYY HHMM` else it will be treated as normal text.

Example of usage: 

`event description /from duration /to duration`

Expected outcome:

Duke's task-list contains new event.

```
Got it. I've added this task:
 [E][] description (from: duration /to duration)
Now you have 1 tasks in the list.
```

Example of usage: 

`event description /from 17/02/2023 2359 /to 18/02/2023 2359`

Expected outcome:

Duke's task-list contains new event.

```
Got it. I've added this task:
 [E][] description (from: 17 Feb 2023 2359 to: 18 Feb 2023 2359)
Now you have 1 tasks in the list.
```

### `delete` - Removes task from task-list

Removes specified task from Duke's tasklist.
Index starts from 1.

Example of usage: 

`delete 1`

Expected outcome:

Duke's task-list does not contain specified task.

```
Noted. I've removed this task:
 [T][] description
Now you have 0 tasks in the list.
```

### `mark` - Marks task as done

Marks specified task in Duke's tasklist as done.
Index starts from 1.

Example of usage: 

`mark 1`

Expected outcome:

Task numbered 1 in Duke's task-list is marked as completed.

```
Nice! I've marked this task as done:
 [T][X] description
```

### `unmark` - Marks task as not done

Marks specified task in Duke's tasklist as not done.
Index starts from 1.

Example of usage: 

`unmark 1`

Expected outcome:

Task numbered 1 in Duke's task-list is marked as not completed.

```
Ok, I've marked this task as not done yet:
 [T][] description
```

### `list` - Lists all tasks

Lists all tasks in Duke's task-list

Example of usage: 

`list`

Expected outcome:

All tasks in Duke's task-list will be displayed.

```
Here are the tasks in your list: 
1.[T][] description
2.[D][] description (by: 17 Feb 2023 2359)
3.[E][] description (from: 17 Feb 2023 2359 to: 18 Feb 2023 2359)
```

### `find` - Searches task-list for keyword

Searches Duke's task-list for tasks whose description contains the keyword specified.

Example of usage: 

`find description`

Expected outcome:

Tasks whose description contains keyword will be displayed.

```
Here are the matching tasks in your list: 
1.[T][] description
```
