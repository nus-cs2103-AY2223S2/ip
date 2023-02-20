# 🤖 DUKE User Guide 👽

## Introduction

DUKE is a project that is based on the CS2103T Duke Individual Project that helps you keep track of all your tasks.

## Features

- Add 3 types of tasks ( `ToDo` , `Deadline` and `Event` ).
- `mark` or `unmark` tasks.
- `delete` unwanted tasks.
- `list` all your tasks.
- Search for tasks with keywords using `find`.
- Change where data is saved using `change_file_location`.

### Create Tasks

Use the `todo`, `deadline` or `event` commands to add tasks.

- `ToDo` tasks require only a description (any characters after the `todo` command will be treated as a description.

For example, `todo read a book`.

- `deadline` tasks require a description followed by a date and time.

For example, `deadline do laundry /by 2023-02-12 18:00`.

- `Event` tasks require a description, start date and time and an end date and time.

For example `event birthday party /from 2023-02-12 18:00 /to 2023-02-12 21:00`.

### Mark and Unmark task

to mark or unmark a tast as done simply use `mark` or `unmark` commands followed by the number of the task respectively. For example, "mark 1".

### Delete tasks

use the `delete` command followed by the number of the task in the list to remove it.

### List all current tasks

Use the `list` command do view all current task in the task list.

### Find task using keyword

Use the `find` command followed by keywords to search for task with matching descriptions to your keyword.

### Change file storing location

Use `change_file_location` command to change where the files will be stored on your device. The files are independent and will not overwrite each other.

### Exiting the program - `bye`

This command closes the program and saves the current list into your locla storage.

## 🤖 Have fun using DUKE! 👽
