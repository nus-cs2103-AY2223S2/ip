# User Guide
Duke is a desktop app for managing tasks, optimised for use via a Command Line Interface (CLI) while still having the benefits of a Graphical User Interface (GUI). If you can type fast, Duke can get your task management done faster than traditional GUI apps.

- Quick start
- Features
- Usage
  - Listing all tasks: `list`
  - Adding a todo: `todo`
  - Adding a deadline: `deadline`
  - Adding an event: `event`
  - Marking a task as done: `mark`
  - Marking a task as undone: `unmark`
  - Locating tasks by description: `find`
  - Deleting a task: `delete`
  - Exiting the program: `bye`

## Quick start
1. Ensure you have Java `11` or above installed in your computer.
2. Download the lastest `duke.jar` from [here](https://github.com/zxisatree/ip/releases).
3. Copy the file to the folder you want to use as the home folder for your task management.
4. Open a command terminal in the folder you put the jar file in, then run `java -jar duke.jar` to start the application. A GUI should appear in a few seconds.
5. Type the command in the text box and press Enter or click on the "Send" button to execute it.

## Features

### Add tasks with deadlines or timed events

Duke can manage various types of tasks for you, including simple tasks with descriptions, tasks with deadlines as well as events that happen only within a certain time frame.

### Give tasks priority

Tasks can have priority levels, so that you can track what needs to be done right now, and what can be done later.

### Mark tasks as complete or incomplete

Tasks can be marked as complete, so that you know what tasks you have yet to do.

### View all tasks at a glance

Duke provides a command (`list`) to view all your tasks in a simple and readable list.

### Delete tasks

Tasks can be easily deleted via the command line.

## Usage

- Words in `UPPER_CASE` are the parameters to be supplied by the user. e.g. in `todo DESCRIPTION`, `DESCRIPTION` is a parameter that can be anything the user wants like `todo Read a book`.
- For date time commands, `DD` refers to the date of the month in 2 digits (so the first day of the month will be `01`), `MM` refers to the month in 2 digits, `YYYY` refers to the year in 4 digits, `HH` refers to the hour of the day in 2 digits (so 1a.m. is 01 and 3p.m. is 15) and `mm` refers to the current minute in 2 digits.
- Commands with `INDEX` parameters should have positive integers used as the `INDEX`. Indexes start at 1 and are the same as the numbers on the list when the `list` command is used.

### Listing all tasks: `list`
Shows a list of all tasks in your task list.

Format:

`list`

Expected outcome:

Duke replies with your task list.

```
1.[T][ ][1] hi
2.[D][ ][8] read book /by Jan 22 2023, 16:00
3.[E][ ][5] swim /from Jan 24 2023, 09:15 /to Jan 24 2023, 11:00
```

### Adding a todo: `todo`
Adds a todo task to the task list.

Format:

`todo DESCRIPTION /prio PRIORITY_NUMBER`

Example usage:

`todo TP UG /prio 1`

Expected outcome:

A todo task with a description of "TP UG" and a priority of "1" is added to the task list.

```
Got it. I've added this task:
  [T][ ][1] TP UG
Now you have 5 tasks in the list.
```

### Adding a deadline: `deadline`
Adds a deadline task to the task list.

Format:

`deadline DESCRIPTION /by DEADLINE /prio PRIORITY_NUMBER`
where `DEADLINE` is of the format `DD-MM-YYYY HH:mm`.

Example usage:

`deadline Read book /by 22-01-2023 16:00 /prio 2`

Expected outcome:

A deadline task with a description of "Read book", a deadline of "January 22, 2023, 4p.m." and a priority of "2" is added to the task list.

```
Got it. I've added this task:
  [D][ ][2] Read book (by: Jan 22 2023, 16:00)
Now you have 6 tasks in the list.
```

### Adding an event: `event`
Adds an event task to the task list.

Format:

`event DESCRIPTION /from START_TIME /to END_TIME /prio PRIORITY_NUMBER`
where both `START_TIME` and `END_TIME` are of the format `DD-MM-YYYY HH:mm`.

Example usage:

`event Swim /from 24-01-2023 09:15 /to 24-01-2023 11:00 /prio 3`

Expected outcome:

An event task with a description of "Swim", a starting time of "January 24, 2023, 9.15a.m.", a ending time of "January 24, 2023, 11a.m." and a priority of "3" is added to the task list.

```
Got it. I've added this task:
  [E][ ][3] Swim (from: Jan 24 2023, 09:15 to: Jan 24 2023, 11:00)
Now you have 7 tasks in the list.
```

### Marking a task as done: `mark`
Marks a task at the given index as complete.

Format:

`mark INDEX`

Example usage:

`mark 2`

Expected outcome:

The task at the given index will be marked as complete.

```
Nice! I've marked this task as done:
  [E][X][3] Swim (from: Jan 24 2023, 09:15 to: Jan 24 2023, 11:00)
```

### Marking a task as undone: `unmark`
Marks a task at the given index as incomplete.

Format:

`unmark INDEX`

Example usage:

`unmark 2`

Expected outcome:

The task at the given index will be marked as incomplete.

```
OK, I've marked this task as not done yet:
  [E][ ][3] Swim (from: Jan 24 2023, 09:15 to: Jan 24 2023, 11:00)
```

### Locating tasks by description: `find`
Find a task based on the given search string. All tasks that have a description containing the given input will be shown.

Format:

`find SEARCH_STRING`

Example usage:

`find IP`

Expected outcome:

A list of all tasks that have a description containing the given input will be shown.

```
Here are the matching tasks in your list:
1.[T][ ][1] IP UG
```

### Deleting a task: `delete`
Deletes the task at the given index.

Format:

`delete INDEX`

Example usage:

`delete 1`

Expected outcome:

The task at the given index is deleted.

```
Noted. I've removed this task:
  [T][ ][1] TP UG
Now you have 6 tasks in the list.
```

### Exiting the program: `bye`
Exits the program gracefully. Closes the app after 1 second.

Format:

`bye`

Expected outcome:

The chatbot exits after 1 second.

```
"Bye. Hope to see you again soon!"
```