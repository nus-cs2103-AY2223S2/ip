# User Guide

KiraBot is a Command Line task management application with a GUI support to display information!

## Features Summary
- Add todo task: **todo**
- Add task with deadline: **deadline**
- Add event: **event**
- Show all tasks: **list**
- Remove a task: **delete**
- Mark a task: **mark**
- Unmark a task: **unmark**
- Find by keyword: **find**
- Show all tasks today: **today**
- Figure out a command: **help**
- Exit the application: **bye**
- Autosaving
- Manual manipulation of savefile

## Features

### Feature - Add ToDo task

Adds a simple task to the list

**Usage**
*Syntax*: `todo <description>`

*Example*: `todo Create this README`
Creates a todo task with the description "Create this README"

### Feature - Add task with deadline

Adds a task with a deadline to the list

**Usage**
*Syntax*: `deadline <description> /by YYYY-MM-DD HHmm`

*Example*: `deadline CS2107 Assignment /by 2023-02-28 2359`
Creates a task with the description "CS2107 Assignment" by the deadline "28 Feb 2023, 11:59 pm"

### Feature - Add event

Adds an event with a set duration to the list

**Usage**
*Syntax*: `event <description> /from YYYY-MM-DD HHmm /to YYYY-MM-DD HHmm`

*Example*: `event OP1 /from 2023-02-17 1600 /to 2023-02-17 1800`
Creates an event with the description "OP1" that happens between "17 Feb 2023, 4pm" and "17 Feb 2023, 6pm"

### List

Shows all tasks that are currently stored

**Usage**
*Syntax*: `list`

### Delete

Removes a task from the list

**Usage**
*Syntax*: `delete <index>`
Index is determined by the `list` command

### Mark

Marks a task as completed in the list

**Usage**
*Syntax*: `mark <index>`
Index is determined by the `list` command

### Unmark

Reverts the task as incomplete

**Usage**
*Syntax*: `unmark <index>`
Index is determined by the `list` command

### Find

Finds all tasks given the keyword searched

**Usage**
*Syntax*: `find <keyword>`

*Example*: `find sleep`
Kirabot will show you all the tasks that contains the word `sleep`

### Today

Shows all deadlines after and events happening today.

**Usage**
*Syntax*: `today`

### Help

Gives in-app description for these commands.

**Usage**
*Syntax*: `help <command>`

*Example*: `help mark`
Kirabot will show you how to use the `mark` command.

### Bye

Exits the application after a few seconds

**Usage**
*Syntax*: `bye`

### Saving

Kirabot autosaves your task list for you! The save file can be accessed in the same directory as KiraBot.jar.

### Manual manipulation of savefile

If you wanted to, you can manually alter the data of the savefile. However, keep in mind that a corrupted savefile will be **overwritten**.

The savefile is a csv file, where each line is a task. The format is as shown:
```
TODO","<Description>","<y/n>
EVENT","<Description>","<y/n>","<From_Date>","<To_Date>
DEADLINE","<Description>","<y/n>","<By_Date>
```
All dates are in the format `YYYY-MM-DD HHmm`.
`<y/n>` denotes the current completion status of the task. y for completed, n for not completed.