# User Guide for Duke

## List of Contents
1. [Quick Start](#quick-start)
2. [Features](#features)
3. [Commands](#commands)

## Quick Start
1. Ensure you have [Java 11](https://www.oracle.com/sg/java/technologies/javase/jdk11-archive-downloads.html) installed
2. Download the latest release of Duke [here](https://github.com/lukkesreysandeur/ip/releases/latest)
3. Double-click the file to open it. Go to your privacy settings and allow the file to be opened if needed.

## Features 
- [x] Add tasks(Todo, Events, Deadlines).
- [x] Mark tasks as done or not done.
- [x] Delete tasks.
- [x] Find tasks by keyword.
- [x] List all remaining tasks.
- [ ] Commands on multiple tasks.
- [ ] Task reminders.

## Commands

| Name                          | What it does                                  |
|-------------------------------|-----------------------------------------------|
| [Todo](#todo-command)         | Adds a task to do                             |
| [Deadline](#deadline-command) | Adds a task with a deadline                   |
| [Event](#event-command)       | Adds a task that has a start and end duration |
| [Mark](#mark-command)         | Marks a task as done                          |
| [Unmark](#unmark-command)     | Marks a task as not done                      |   
| [Delete](#delete-command)     | Deletes the specified task in the list        |
| [List](#list-command)         | Lists the tasks currently tracked by Duke     |
| [Find](#find-command)         | Finds a task based on keyword                 |
| [Help](#help-command)         | Displays a list of available commands         |
| [Bye](#bye-command)           | Exits the program                             |

## Usage

### `todo` command

- Adds a task to be done to the list. 
- Name is whatever comes after the todo keyword.

Example: 

`todo homework`

Expected outcome: 

Todo with a description of homework is created.

```
Sure no problem. I've added this task:
    [T][ ] homework
Now you have 1 task in the list
```

### `deadline` command
- Adds a task with a deadline.
- Name is everything before the /by and the deadline is everything after.
- Date will be reformatted into text if inputted in yyyy-mm-dd format.

Example: 

`deadline assignment /by 2023-02-17`

Expected outcome: 

```
Sure no problem. I've added this task:
    [D][ ] assignment (by: Feb 17 2023)
Now you have 2 tasks in the list
```

### `event` command

- Adds a task with a start and end duration.
- Name is before the /from, start time is between /from and /to, end time is everything after.
- Date will be reformatted into text if inputted in yyyy-mm-dd format.

Example:

`event concert /from 7pm /to 9pm`

Expected outcome:

```
Sure no problem. I've added this task:
    [E][ ] concert (from: 7pm to: 9pm)
Now you have 3 tasks in the list
```

### `mark` command

- Marks a task done based on the index of the task.
- Index starts at 1, ends at the length of the list.
- If task is marked done, nothing happens.

Example:

`mark 1`

Expected outcome:

```
Well done! I've marked this task as done:
    [T][X] homework
```

### `unmark` command

- Marks a task as not done bases on the index of the task.
- Index starts at 1, ends at the length of the list.
- If task is marked not done, nothing happens.

Example:

`unmark 1`

Expected outcome:

```
Okay, I've marked this task as not done:
    [T][ ] homework
```

### `delete` command

- Deletes a task based on index.
- Index starts at 1, ends at the length of the list.

Example: 

`delete 3`

Expected outcome:

```
Sure thing. This task has been deleted:
    [E][ ] concert (from: 7pm to: 9pm)
Now you have 2 tasks in the list
```

### `list` command

- Lists all remaining tasks in the list.

Example:

`list`

Expected outcome:

```
Here are the tasks in your list:
1. [T][ ] homework
2. [D][ ] assignment (by: Feb 17 2023)
```

### `find` command

- Finds all tasks with a matching keyword in their name.

Example:

`find home`

Expected outcome:

```
These are the matching tasks that I have found:
1. [T][ ] homework
```

### `help` command

- Shows the user what commands are available and how to use them

Example:

`help`

Expected outcome: 

A list of commands and their uses.

### Bye command

- Exits Duke.

Example:

`bye`

Expected outcome: 

The Duke window is closed.
