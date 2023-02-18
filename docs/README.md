# User Guide
Duke Task Manager is a desktop application build in Java for managing your tasks! This is project template for a
greenfield Java Project.

## Quick Start
1. Ensure that you have Java `11` or above installed.
2. Download the latest Duke Task Manager from here [ins link here]
3. Copy the file into any folder that you like.
4. Open command terminal and `cd` to the location that you placed the jar file in.
5. Issue the command `java -jar duke.jar` to begin!

## Features 

### Add deadline task. `deadline`

Add a new deadline task completed with description and deadline.

### Delete task. `delete`

Delete a specific task.

### Add event task. `event`

Add a new event task completed with description, start and end time.

### List all task. `list`

List all task you have.

### Mark task as done. `mark`

Mark a task as completed.

### Search for task. `search`

Search for a task in the tasklist.

### Add todo task. `todo`

Add a new todo task completed with description.

### Unmark task as done. `unmark`

Unmark a task as completed.

## Usage

### `deadline` - Creates a task with Deadline

Create a task that has a deadline.

Example of usage:

`deadline NAME_OF_TASK /by END_TIME`

### `delete` - Deletes a task.

Delete any task.

Example of usage:

`delete INDEX`

### `event` - Creates a Event task.

Create a task that has a start and end time.

Example of usage:

`event NAME_OF_TASK /from START_TIME /to END_TIME`

### `list` - List down all tasks.

List all tasks in the task manager.

Example of usage:

`list`

### `mark` - Marks a task as done.

Mark the selected task as done.

Example of usage:

`mark INDEX`

### `search` - Searches the task list.

Searches the task list using the given search criteria.

Example of usage:

`search SEARCH_STRING`

### `todo` - Creates a ToDo task.

Create a task that has a name.

Example of usage:

`todo NAME_OF_TASK`

### `unmark` - Unmark a task as done.

Unmark the selected task as done.

Example of usage:

`unmark INDEX`