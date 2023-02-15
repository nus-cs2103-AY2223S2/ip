# User Guide

## Features 

### Personal Task Manager

Bond is a text-based task manager that helps you keep track of your 
tasks. You can create / delete todos, deadlines, and events, and view 
their completion status. 


### Task Finder

Finds a list of tasks that meet the input string entered.

### Help Page
Prints a list of commands that can be used in Bond. 

## Usage

### `Todo <description>` - Creates a todo task
Adds a new todo instance to the list.

Example of usage:

`todo submit internship application`

Expected outcome:

```
[T][ ] submit internship application has been added
```

### `Deadline <description> /yyyy-mm-dd` - Creates a new deadline task
Adds a new deadline instance to the list.

Example of usage:

`deadline return library books /2023-02-26`

Expected outcome:

```
[D][ ] return library books (by: FEBRUARY 26, 2023) has been added
```

### `Event <description> /yyyy-mm-dd /yyyy-mm-dd` - Creates a new event task
Adds a new event instance to the list.

Example of usage:

`event hackathon /2023-03-12 /2023-03-15`

Expected outcome:

```
[E][ ] hackathon (from: MARCH 12, 2023 to: MARCH 15, 2023), has been added
```

### `Missions` - List tasks
Prints a list of tasks stored in program, includes marked and
unmarked tasks. 

Example of usage:

`missions`

Expected outcome:

```
1. [D][X] cs2102 assignment 1 (by: FEBRUARY 25, 2023)
2. [T][ ] submit internship application
```

### `Mark <index>` - Mark a task as completed
Marks task stored at index as completed.

Example of usage:

`mark 2`

Expected outcome:

```
1. [D][X] cs2102 assignment 1 (by: FEBRUARY 25, 2023)
2. [T][X] submit internship application
```

### `Unmark <index>` - Unmark a task
Marks task stored at index as incomplete.

Example of usage:

`unmark 2`

Expected outcome:

```
1. [D][X] cs2102 assignment 1 (by: FEBRUARY 25, 2023)
2. [T][ ] submit internship application
```

### `Delete <index>` - Unmark a task
Delete task stored at index indicated.

Example of usage:

`delete 1`

Expected outcome:

```
[D][X] cs2102 assignment 1 (by: FEBRUARY 25, 2023) has been removed
```

### `Find <wordss>` - Search for matching tasks
Finds and lists all tasks that match the given keyword.

Example of usage:

`find application`

Expected outcome:

```
These are what I found:
1. [T][X] submit internship application
```

### `help` - Print command guide
Prints a guide on the list of commands that can be used within Bond.

Example of usage:

`Help`

Expected outcome:

```
Here is a list of commands that you may use:
todo <task> - creates a new task
deadline <task> /<yyyy-mm-dd> - creates a task with an end date
event <task> /<yyyy-mm-dd> /<yyyy-mm-dd> - creates a task with start and end date
mark/unmark <int> - change status of task
delete <int> - deletes a task in the list
find <word> - returns tasks in list which matches word\
missions - prints out a todo list of existing tasks
help - prints out this list of commmands
bye - to close program
```

### `Bye` - Closes Application

Saves task data and closes program.

Example of usage:

`bye`

Expected outcome:
[application closed]