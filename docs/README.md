# User Guide

## Features 

### Feature-Add tasks

Add different types of Tasks. The types of tasks available are:
- To Dos
- Deadlines
- Events

### Feature-Delete tasks

Remove unwanted tasks from the list.

### Feature-Mark tasks

Mark tasks as **done** or **undone**

### Feature-Find tasks

Find tasks using a specific keyword

## Usage

### `todo` - Adds a todo to the list

Specify a task and it will be added to the list.

Example of usage:
todo Download Duke

Expected outcome:
[T][ ] Download Duke added to the list

### `deadline` - Adds a deadline to the list

Specify a task and it's deadline. It will be added to the list.

Example of usage:
deadline Download Duke /by 2000-10-15

Expected outcome:
[D][ ] Download Duke (By: Oct 15 2000) added to the list

### `event` - Adds an event to the list

Specify a task, its starting date and time and ending date and time.
This event will be added to the list.

Example of usage:
event Download Duke /from 2000-10-15 12:00 /by 2000-10-20 13:45

Expected outcome:
[E][ ] Download Duke (From: Sunday October 15 2000 12:00 By: Friday October 20 2000 13:45)

### `mark` - Marks a task as completed

Specify a task to be marked as completed. Tasks are specified by their numbering in the list.

Example of usage:
todo Download Duke
mark 1

Expected outcome:
[T][X] Download Duke is marked as done

### `unmark` - Marks a task as completed

Specify a task to be marked as incomplete. Tasks are specified by their numbering in the list.

Example of usage:
todo Download Duke
unmark 1

Expected outcome:
[T][ ] Download Duke is unmarked

### `delete` - Deletes a task

Specify a task to be deleted from the list. Tasks are specified by their numbering in the list.

Example of usage:
todo Download Duke
delete 1

Expected outcome:
[T][X] Download Duke is deleted

### `find` - Finds tasks related to keyword

Input a keyword and all tasks related to the keyword in the list are returned.

Example of usage:
todo Download Duke
find Download

Expected outcome:
[T][ ] Download Duke

