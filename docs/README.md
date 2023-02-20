# User Guide for Kal, Your Calendar Pal
## Introduction
>"Don't forget stuff like I always do" - Jia-Ao Shi, _Kal Developer_

Kal is a cat-themed calendar helper that helps you keep track of your tasks!

## Installation
- Install Java 11 on your computer
- Download **latest** JAR release of Kal [here](https://github.com/ShiJiaAo/ip/releases)

## Launching Kal
1. Open a terminal
2. Navigate to the folder where `kal.jar` is stored
3. Run the command `java -jar kal.jar` in the terminal
4. Learn the commands in the *Usage* section below
5. You are ready to go!

## Usage

### `todo` - Add new todo task

Adds a new to-do task to Kal's task list

Format: `todo {TASK_DESCRIPTION}`
- `{TASK_DESCRIPTION}` cannot be blank

Example(s) of usage:
- `todo go to school`
- `todo submit project`

### `deadline` - Add new deadline task

Adds a new task with a deadline to Kal's task list

Format: `deadline {TASK_DESCRIPTION} /by {DEADLINE}`
- `{TASK_DESCRIPTION}` and `{DEADLINE}` cannot be blank
- `{DEADLINE}` is in the format `yyyy-mm-dd`

Example(s) of usage:
- `deadline go to school /by 2023-02-27`
- `deadline submit project /by 2023-02-20`

### `event` - Add new event task

Adds a new event task to Kal's task list

Format: `event {TASK_DESCRIPTION} /from {START_DATE} /to {END_DATE}`
- `{TASK_DESCRIPTION}` cannot be blank

Example(s) of usage:
- `event hackathon /from 2023-01-01 /to 2023-01-03`
- `event project week /from 2023-02-20 /to 2023-02-27`

### `delete` - Delete a task

Deletes a task from Kal's task list according to the specified index

Format: `delete {INDEX}`
- `{INDEX}` is 1-indexed
- `{INDEX}` must fall within the bounds of the task list

Example(s) of usage:
- `delete 1`
- `delete 7`

### `find` - Search for a task

Searches for a task from Kal's task list according to the specified keyword

Format: `find {KEYWORD}`

Example(s) of usage:
- `find hackathon`
- `find school`

### `mark` - Mark a task as complete

Marks a task as complete according to the specified index

Format: `mark {INDEX}`
- `{INDEX}` is 1-indexed
- `{INDEX}` must fall within the bounds of the task list

Example(s) of usage:
- `mark 1`
- `mark 7`


### `unmark` - Mark a task as incomplete

Marks a task as incomplete according to the specified index

Format: `unmark {INDEX}`
- `{INDEX}` is 1-indexed
- `{INDEX}` must fall within the bounds of the task list

Example(s) of usage:
- `unmark 2`
- `unmark 4`


### `list` - List entire task list

Lists all the tasks in Kal's task list

Format: `list`

### `bye` - Exits program

Say goodbye

Format: `bye`
