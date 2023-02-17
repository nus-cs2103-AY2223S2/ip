# User Guide

Bob is a chat-bot made to help you manage your tasks. He accepts CLI commands while having a GUI for display.

## Installation
1. Ensure that Java 11 or higher is installed.
2. Download the latest release from [here](https://github.com/LeonardYam/ip/releases).
3. Copy the executable to folder of choice.
4. Launch Bob from terminal with `java -jar`.
5. Enter supported commands to interact with Bob, and close the GUI window once done. 

## Command cheatsheet
Actions | Format | Example 
--- | --- | --- 
add todo | `todo <description>` | `todo assignment` 
add deadline | `deadline <description> /by <date>` | `deadline submission /by 2023-02-16`
add event | `event <description> /from <startDate> /to <endDate>` | `event Holiday /from 2023-06-01 /to 2023-09-01`
mark task | `mark <index>` | `mark 1`
unmark task | `unmark <index>` | `unmark 1`
delete task | `delete <index>` | `delete 1`
find tasks | `find <keyword>` | `find appointment`
show reminders | `remind` | `remind`

## Features 
### :warning: Usage notes
- Fields which are enclosed by `<>` are to be input by user
- `<date>`, `<startDate>`, `<endDate>` require the format YYYY-mm-dd
- `<index>` refers to the index of the task given by `list` command

### List current tasks: `list`
Shows all tasks and their status

Format: `list`

### Add a task todo: `todo`
Add a todo with a description
Format: `todo <description>`

Example: `todo assignment`

### Add a task deadline: `deadline`
Add a task with a deadline for completion

Format: `deadline <description> /by <date>`

Example: `deadline submission /by 2023-02-16`

### Add an event: `event`
Add an event that has a start and end date

Format: `event <description> /from <startDate> /to <endDate>`

Example: `event Holiday /from 2023-06-01 /to 2023-09-01`

### Mark a task: `mark`
Marks a task as done

Format: `mark <index>`

Example: `mark 1`

### Unmark a task: `unmark`
Unmarks a task as not done

Format: `unmark <index>`

Example: `unmark 1`

### Delete a task: `delete`
Deletes a task from the task list 

Format: `delete <index>`

Example: `delete 1`

### Filter tasks: `find`
Find tasks whose description contains the keyword phrase.

Format: `find <keyword>`

Example: `find appointment`

### Show reminders: `remind`
Show all deadlines, and highlights those which are overdue.

Format: `remind`

### Saving tasks
The tasks are saved to a .txt file automatically after every input.

### Loading tasks
Any previous tasks are loaded from the .txt file upon starting Bob
