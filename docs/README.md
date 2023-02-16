# User Guide
Duke is a desktop software built in Java that allows users to create, delete, update and view tasks of different types - Todos, Events and Deadlines. Users have access to the highly intuitive GUI which allows them to manage the tasks much more efficiently.
- [Features](#features)
  - [Adding a task : `todo | event | deadline`](#adding a task)
  - [Deleting a task : `delete`](#deleting-a-task)
  - [Listing all tasks : `list`](#listing-all-tasks)
  - [Searching a task : `search`](#searching-a-task)
  - [Updating task dates : `update`](#updating-task-dates)
  - [Exit application : `bye`](#exiting-application)

## Features

### Adding a task : `todo | event | deadline`
Add a new task of the chosen type to the existing task list. 

Format: 
- `todo [TASKCONTENT]` 
- `event [TASKCONTENT] /from [D/MM/YYYY HHMM] /to [D/MM/YYYY HHMM]`
- `deadline [TASKCONTENT] /by [D/MM/YYYY HHMM]`

Example of usage:
- `todo Wash Clothes`
- `event Sam wedding /from 1/12/2022 1600 /to 1/12/2022 2100`
- `deadline assignment 5 /by 1/01/2023 2359`

### Deleting a task : `delete`
Delete a task with the given task number from the existing task list.

Format:
- `delete [TASKNUMBER]`

Example of usage:
- `delete 2`

### Listing all tasks : `list`

List all the tasks in the current task list.

Format:
- `list`

### Searching a task : `search`
Search and list all the tasks with the given content.

Format:
- `search [TASKCONTENT]`

Example of usage:
- `search quiz`

### Updating task dates : `update`
Update the task with the given id with the new given dates.

Format:
- if task with the number 1 is an Event:
  - `update [TASKNUMBER] /from [D/MM/YYYY HHMM] /to [D/MM/YYYY HHMM]` 
- if task with the number 1 is a Deadline:
  - `update [TASKNUMBER] /by [D/MM/YYYY HHMM]`
- if task with the number 1 is a Todo:
  -  not compatible with the command

Example of usage:
- if task with the number 1 is an Event:
  - `update 1 /from 1/12/2022 1600 /to 1/12/2022 2100`
- if task with the number 1 is a Deadline:
  - `update 1 /by 1/12/2022`
- if task with the number 1 is a Todo:
  -  not compatible with the command

### Exit application : `bye`

Exit the Java application and GUI.

Format:
- `bye`
