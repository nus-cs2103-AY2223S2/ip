# Duke User Guide ૮ ˶ᵔ ᵕ ᵔ˶ ა

Duke is a task manager application designed for fast typers that helps manage all the things going on in your life. It features a cute little bunny to guide you along the way! 🐰

## Quick Start

1. Ensure you have Java 11 installed in your computer.
2. Download the latest `duke.jar`.
3. Copy the file to the folder you want to use as the home folder for your Duke.
4. Open a command terminal, cd into the folder you put the jar file in, and use the `java -jar duke.jar` command to run the application.
5. Type the command in the command box and press Enter to execute it. Refer to the guide below for details of each command.

## Features

1. Add tasks 📎
2. Delete tasks 🗑️
3. Edit task descriptions 📝
4. Find tasks matching a certain keyword 👀
5. List all tasks 📄
6. Mark a task as done ✔️
7. Unmark a task 😢
8. Automatically save tasks as an editable `task.txt` file 💾

## Usage

### `todo` - Adds a todo

Adds a To-Do to the task list.

Format: `todo DESC`

Example: `todo read book`

Expected outcome: Adds a To-Do to the task list with the description "Take out the trash".

### `deadline` - Adds a deadline

Adds a Deadline to the task list.

Format: `deadline DESC /by DD/MM/YYYY HHMM`

Example: `deadline return book /by 17/02/2023 2100`

Expected outcome: Adds a Deadline to the task list with the description "return book" and deadline 17/02/2023 2100.

### `event` - Adds an event

Adds an Event to the task list.

Format: `event DESC /from DD/MM/YYYY HHMM /to DD/MM/YYYY HHMM`

Example: `event project meeting /from 17/02/2023 1200 /to 17/02/2023 1400`

Expected outcome: Adds an Event to the task list with the description "project meeting", start time 17/02/2023 1200, and end time 17/02/2023 1400.

### `list` - Lists all tasks

Lists all tasks in the task list with their corresponding index.

Format: `list`

### `find` - Finds all tasks containing the given keyword

Lists all tasks in the task list whose description contains the given keyword.

Format: `find KEYWORD`

Example: `find book`

Expected outcome: Lists all tasks containing the word "book".

### `edit` - Edits a task

Edits the description of the task at the corresponding index.

Format: `edit TASK_NUM NEW_DESC`

Example: `edit 1 sleep`

Expected outcome: Edits the task description of the task at index 1 to "sleep".

### `mark` - Marks a task as done

Marks the task in the task list at the corresponding index as done.

Format: `mark TASK_NUM`

Example: `mark 1`

Expected outcome: Marks the task at index 1 as done.

### `unmark` - Marks a task as undone

Marks the task in the task list at the corresponding index as undone (i.e. unmarks it).

Format: `unmark TASK_NUM`

Example: `unmark 1`

Expected outcome: Marks the task at index 1 as undone (i.e. unmarks it).

### `delete` - Deletes a task

Deletes the task in the task list at the corresponding index.

Format: `delete TASK_NUM`

Example: `delete 1`

Expected outcome: Deletes the task at index 1.

### `bye` - Exits the application

Exits the application.

Format: `bye`
