# User Guide

## Features 

### Creating a task list
The app allows you to create tasks to maintain. There are 3 types of tasks
you can add:
- Todo
- Deadline
- Event

Each task can be marked as done (and un-marked), or deleted.

### Tags
Every task can be created with any number of tags added to it.

### Easy searching
A `find` command allows you to easily search for tasks previously added using keywords.

### Saving tasks on drive
The current task list is automatically saved on disk upon exit of the program in a secure format.

### Error handling
Errors are handled and displayed in a way that helps users understand the correct format of a command.

## Usage

### `todo {name} [/tags tags]` - Add a ToDo task

- `name` = name of the task
- `tags` = any number of tags, separated by a space "` `"

Example of usage: 

`todo Task 1 /tags work`

Expected outcome:

```
PixlBot says:
Added new todo!
    [T][] Task 1 {#work}
You now have <n> task(s) in the list.
```

### `deadline {name} /by {date} [/tags tags]` - Add a Deadline task

- `name` = name of the task
- `date` = due date in "yyyy-mm-dd" format
- `tags` = any number of tags, separated by a space "` `"

Example of usage:

`deadline submit a thing /by 2023-02-17 /tags work fun`

Expected outcome:

```
PixlBot says:
Added new deadline!
    [D][] submit a thing (by: 17 Feb 2023) {#work, #fun}
You now have <n> task(s) in the list.
```

### `event {name} /from {start} /to {end} [/tags tags]` - Add a Event task

- `name` = name of the task
- `start` = start date/time of the event
- `end` = end date/time of the event
- `tags` = any number of tags, separated by a space "` `"

Example of usage:

`event Meeting /from 4pm /to 5pm`

Expected outcome:

```
PixlBot says:
Added new event!
    [E][] Meeting (from: 4pm to: 5pm)
You now have <n> task(s) in the list.
```

### `list` - Display the current task list

Example of usage:

`list`

Expected outcome:

```
PixlBot says:
Your current task list:
    1. [T][] Task 1 {#work}
    2. [D][] submit a thing (by: 17 Feb 2023) {#work, #fun}
    3. [E][] Meeting (from: 4pm to: 5pm)
```

### `mark {task-number}` - Mark a task as done

- `task-number` = the list number of the task

Example of usage:

`mark 1`

Expected outcome:

```
PixlBot says:
You completed a task!
    [T][X] Task 1 {#work}
```

### `unmark {task-number}` - Mark a task as not done

- `task-number` = the list number of the task

Example of usage:

`unmark 1`

Expected outcome:

```
PixlBot says:
Un-doing the task...
    [T][] Task 1 {#work}
```

### `find {keyword}` - Search the task list

- `keyword` = the string to search for

Example of usage:

`find strange`

Expected outcome:

```
PixlBot says:
No matching tasks found :/
```

### `delete {task-number}` - Delete a task

- `task-number` = the list number of the task

Example of usage:

`delete 1`

Expected outcome:

```
PixlBot says:
Removed the task:
    [T][] Task 1 {#work}
You now have <n> task(s) in the list.
```

### `bye` - Exit the program

Example of usage:

`bye`

Expected outcome:

Program ends

## Acknowledgements
- Logo generated at https://inkpx.com/templates/xw5/white-outline-text-effect
