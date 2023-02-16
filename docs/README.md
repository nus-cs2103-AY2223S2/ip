# User Guide
Duke is a desktop application that helps you manage your daily tasks. It's,
- easy to learn
- ~~FAST~~ _SUPER_ FAST to use

[Quick start](#quick-start)

[Features](#features)
  - [Add a task](#add-a-task)
  - [Mark/Unmark a task](#markunmark-a-task)
  - [Delete a task](#delete-a-task)
  - [Find a task](#find-a-task)

[Summary of Commands](#summary-of-commands)

## Quick start

1. Ensure you have Java `11` or above installed in your Computer.

2. Download the latest `duke.jar` from [here](https://github.com/sanjevi13/ip/releases/tag/v0.2).

3. Copy the file to the folder you want to use as the _home folder_ for your app.

4. Double-click the file to run the app.

5. Refer to the [Features](#features) below for details of each command.

## Features 

### Add a task
Simply add/delete a task in your list.
Your task can be a/an:
1. todo
2. event
3. deadline

Example 1: `deadline assignment /by 21/03/2023`

Expected outcome:
```
Yeah boi...You have a new task:
[D][ ] assignment (by: Mar 21 2023)
Now you have 1 tasks in the list.
```

Example 2: `event party /from 13/03/2023 19:00 /to 14/03/2023 13:00`

Expected outcome:
```
Yeah boi...You have a new task:
[E][ ] party (from: Mar 13 2023 19:00 to: Mar 14 2023 13:00)
Now you have 2 tasks in the list.
```

### Mark/Unmark a task
Mark the task as complete/incomplete.

Example 1: `mark 1`

Expected outcome:
```
Good job! You have completed this task:
[D][X] assignment (by: Mar 21 2023)
```

Example 2: `unmark 1`

Expected outcome:
```
OK, I've marked this task as not done yet:
[D][ ] assignment (by: Mar 21 2023)
```
### Delete a task
Delete a task from a list

Example: `delete 1`

Expected outcome:
```
Noted. I've removed this task:
[D][ ] assignment (by: Mar 21 2023)
Now you have 1 tasks in the list.
```

### Find a task
Find a task by searching a keyword.

Example: `find assignment`

Expected outcome:
```
Are you looking for one of these?
1. [D][ ] assignment (by: Mar 21 2023)
```
### Exit the program
Simply exit the program by typing `bye`

Expected outcome:
`Bye. Hope to see you again soon!
`

## Summary of Commands
***cmd***: shows list of commands

***todo*** description: adds a todo task

***deadline*** description /***by*** <date>: adds a deadline task (Note: date should be in format dd/mm/yyyy)

***event*** description /***from*** <date/time> /***to*** <date/time>: adds an event task (Note: date/time is in format dd/mm/yyyy hh:mm)

***mark*** taskNumber: marks a task as done

***unmark*** taskNumber: marks a task as incomplete

***list*** : shows your task list

***delete*** taskNumber: deletes a task from the list

***find*** keyword: searches for a task in the list

## Acknowledgements
Adapted from: https://github.com/se-edu/addressbook-level3/blob/master/docs/UserGuide.md

