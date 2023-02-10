# User Guide
Adapted from https://github.com/fantablack/ip/blob/master/docs/README.md with slight modification

Adapted from https://raw.githubusercontent.com/chuashihong/ip/master/docs/README.md with slight modification 

Duke is a task manager which, designed using Java, and has a chatting GUI which makes it easy and fast to learn to use!

## Quick start

1. Ensure you have Java `11` or above installed in your Computer. Download Java 11 [here](https://www.oracle.com/java/technologies/downloads/)d

2. Download the latest `duke.jar` from [here](https://github.com/junlee1991/ip/releases).

3. Copy the file to the folder you want to use as the _home folder_.

4. Double-click the file to start the app. The GUI similar to the below should appear in a few seconds.


![Ui](Ui.png)

## Features 

### Todos

Add all the tasks you need to complete! 

### Event

Include all the events you need to attend! 

### Deadlines

Be on top of your deadlines! 

## Usage

### 1. Add a Todo task
Adds a new todo `<task todo>`

No duplicate todo task is allowed.

Syntax:

`todo <task todo>`

Example of usage:

`todo play games`

Expected outcome:

Adds a new Todo task `play games`.

### 2. Add an Event Task
Adds a new event `<event-name>` from `dd/MM/yyyy HHmm` to `dd/MM/yyyy HHmm`

Syntax:

`event <event-name> /from dd/MM/yyyy HHmm /to dd/MM/yyyy HHmm`

**Caution:** Please adhere to the date and time format exactly. 
 
For instance, instead of `3`, please put `03`.

No duplicate event is allowed.

Example of usage:

`event party /at 10/11/2023 1830`

Expected outcome:

Event `party` at `10/11/2023 1830` gets added.

### 3. Add a Deadline task
Adds a Deadline `<deadline-name>` to be done by `dd/MM/yyyy HHmm`

**Caution:** Please adhere to the date and time format exactly.

For instance, instead of `3`, please put `03`.

No duplicate deadline task is allowed.

Syntax:

`deadline <task with deadline> /by dd/MM/yyyy HHmm`

Example of usage:

`deadline submit thesis /by 15/03/2022 2359`

Expected outcome:

Adds a new Deadline `submit thesis` to be handed in by `15/03/2022 2359`

### 4. List all Tasks
Syntax:

`list`

Expected Outcome:

Lists all tasks saved in file.

### 5. Find
Find task(s) that contains `<search term>`

Example of usage:

`find <search term>`

Expected outcome:

List of task(s) containing `search term`. 

### 6. Delete <task index>
Deletes a task at a specified `<index>`

Syntax:

`delete <index>`

Example of usage:

`delete 2`

Expected outcome:

Deletes the task at index `2` from the list. 

### 7. Mark
Mark a task at `<index>` as done

Syntax:

`mark <index>`

Example of usage:

`mark 1`

Expected outcome:
Tasks at index `1` is marked done.

### 8. Unmark
Mark a task at `<index>` as not done

Syntax:

`unmark <index>`

Example of usage:

`unmark 1`

Expected outcome:
Tasks at index `1` is marked as not done.

## 9.Exit
Exit the Duke chat bot with a `bye` command

Syntax:
`bye`

