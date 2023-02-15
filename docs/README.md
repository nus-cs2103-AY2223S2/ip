# User Guide
Roody is a Java application that builds upon a Command-Line Interface to allow 
users to manage their tasks and displays them via a Graphical Interface.
His bright personality comes from an 8-year-old dog named Roody.

![Picture of Roody](/src/main/resources/images/DaRoody%20-%20Docs.png)

## Requirements
- Java 11

## Quick Start
1. Download the latest release of Roody [here](https://github.com/ryanchua00/ip/releases).
    1. Place the jar in a seperate directory named Roody.
2. From the command line, `cd` to the directory.
3. Execute `java -jar roody.jar` to start.

## Features

### Feature - Adding Tasks

Roody can create three different types of tasks:
- To Do
- Deadline
- Event

### Feature - Marking Tasks

Roody can mark tasks as completed or incomplete, tracking your progress.

### Feature - Delete Tasks

Roody can delete tasks.

### Feature - List

Roody can display all tasks that you have currently.

### Feature - Finding Tasks

Roody can take in specific keywords to find tasks that match.

### Feature - Help

Roody can inform you of different commands and their usages.

## Usage 

### `help` - Asking for Help

Gives descriptions of commands and their usage.

Example of usage:

- `help`
- `help c`
- `help tc`
- `help tm`

### `list` - Listing all Tasks

Lists all tasks present in the database.

Example of usage:

- `list`

### `todo` - Creating a Todo

Creates a Todo task and displays it.

Example of usage:

- `todo (description)`

### `deadline` - Creating a Deadline

Creates a Deadline task with a specified deadline and displays it.

Example of usage:

- `deadline (description) /by (date)`

### `event` - Creating an Event

Creates an Event task with a specified start and end date and displays it.

Example of usage:

- `event (description) /from (date) /to (date)`

### `mark` - Marking a Task

Marks the task with a specified index as complete.

Example of usage:

- `mark (index)`

### `unmark` - Unmarking a Task

Marks the task with a specified index as incomplete.

Example of usage:

- `unmark (index)`

### `delete` - Deleting a Task

Deletes a task with a specified index.

Example of usage:

- `delete (index)`

### `find` - Finding relevant Tasks

Searches for relevant tasks using the specified keyword.

Example of usage:

- `find (keyword)`

### `bye` - Saving Tasks

Saves the current tasks in the list.

Example of usage:

- `bye`
