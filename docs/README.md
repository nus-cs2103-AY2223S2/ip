# User Guide

## Running DukePro for Pros

1. Download Duke.jar [here](https://github.com/Jxleejiaxin/ip/releases/tag/A-Release)
2. Right-click the downloaded Duke.jar and click 'open with'.
3. Click `Java(TM) Platform SE binary`.
4. DukePro for Pros is now ready to manage your tasks!

## Features 
- Keep track of a list of tasks and their status(done/undone)
- Add ToDos, Deadlines and Events to the task list
- Delete tasks from the task list
- Find tasks that match a specific keyword
- Categorise tasks your own way by switching between multiple data sources
### Keep track of a list of tasks
DukePro saves your task lists into a text file and will load from this text file whenever it is booted up.

DukePro will also display if the task has been marked as done or not done.

### ToDos, Deadlines, Events
Tasks that DukePro recognises can be separated into three types: ToDo, Deadline, Event.

ToDo is a task that is not required to be completed within a frame of time.

Deadline is a task that should be completed by the specified deadline.

Event is a task that happens from a specified time to another time.

### Mark tasks as done
Tasks can be marked as done to facilitate organisation of tasks.

### Find tasks

Search for specific tasks that match a keyword

### Switch between multiple sources
DukePro can read a specific text file to load a task list. Doing so also changes the "write destination" of Duke.

## Usage

### 1. `todo TODO_DESCRIPTION` - adds a ToDo task to the task list

Example of usage:

`todo read book`

This will add a ToDo task to read book.

### 2. `deadline DEADLINE_DESCRIPTION /by yyyy-mm-dd` - adds a Deadline task to the task list

Example of usage:

`deadline return book /by 2024-05-17`

This will add a Deadline task to return book by 17 May 2024.

### 3. `event EVENT_DESCRIPTION /from EVENT_START /to EVENT_END` - adds a Event task to the task list

Example of usage:

`event project meeting /from May 5th 2pm /to 4pm`

This will add a Event task for a project meeting occurring on May 5th 2pm to 4pm.

### 4. `delete INDEX` - deletes a task at the specified INDEX

Example of usage:

`delete 1`

This will delete the first task in the task list.

### 5. `list` - displays the task list

### 6. `mark INDEX` - marks task at specified INDEX as done

Example of usage:

`mark 1`

This will mark the first task in the task list as done.

### 7. `unmark INDEX`- marks task at specified INDEX as not done

Example of usage:

`unmark 1`

This will mark the first task in the task list as not done.

### 8. `find KEYWORD` - displays a list of tasks with descriptions matching the KEYWORD

### 9. `source PATH_TO_FILE` - switches data source of task list to specified text file at PATH_TO_FILE

Example of usage:

`source data/newsource.txt`

This will load all tasks from the new data source. If specified file does not exist, a new text file will be created.




