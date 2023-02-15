# Duke User Guide

## Features

### Task management

Add/remove/mark tasks that you need to do!

Task can be in the form of:
- Todo: Task that needs to be done.
- Deadline: Task that needs to be done by a certain time.
- Event: Task with a starting and ending time.

### GUI-based program with CLI (command line interface)

Task management is done easily through CLI and also presented through a GUI.

## Usage

### `list` - Prints all registered tasks

View all tasks that you have registered with this command in a numbered list.

No additional parameters are required.

Example of usage:

`list`

Expected outcome:

```
Here are the tasks in your list
1. [T][ ] CS2103T User Guide
```

There is a Todo task named "CS2103T User Guide" currently registered.

### `mark/unmark {index}` - Mark a specific task

- `mark` marks a specific task as not done.
- `unmark` marks a specific task as not done.

Takes in a parameter `{index}` which is the order (number shown using the command `list`) of the specific task to mark/unmark.

Example of usage:

`mark 1`

Expected outcome:

```
Nice! I've marked this task as done:
1. [T][X] CS2103T User Guide
```

Todo task named "CS2103T User Guide" has been marked as done.

### Adding tasks to Duke

- `todo {description}` adds a Todo to Duke.
- `deadline {description} /by {date}` adds a Deadline to Duke.
- `event {description} /from {date} /to {date}` adds an Event to Duke.

All commands takes in a parameter `{description}` which is the name of the task.

Adding Deadlines and Events require additional parameters `{date}` in the `yyyy-mm-dd` format (e.g., `2023-02-22`).

Example of usage:

`event My Birthday /from 2023-02-22 /to 2023-02-23`

Expected outcome:

```
Got it! I've marked this event:
[E][ ] My Birthday (from: Feb 22 2023 to: Feb 23 2023)
Now you have 2 tasks in the list.
```

Event task named "My Birthday" that runs from Feb 22 2023 to Feb 23 2023 has been added to Duke.

### `delete {index}` - Delete a specific task from Duke

Delete specific unwanted task from the list.

Takes in a parameter `{index}` which is the order (number shown using the command `list`) of the specific task to delete.


Example of usage:

`delete 2`

Expected outcome:

```
Noted. I've removed this task:
[E][ ] My Birthday (from: Feb 22 2023 to: Feb 23 2023)
Now you have 1 tasks in the list.
```

Event task named "My Birthday" that runs from Feb 22 2023 to Feb 23 2023 has been deleted from Duke.

### `find {keyword}` - Find all tasks with matching description

Find all tasks with description that contains `{keyword}` and prints them as list.

Takes in a parameter `{keyword}` which is a case-sensitive keyword or keyphrase.

Example of usage:

`find CS2103T`

Expected outcome:

```
Here are the matching tasks in your list:
1. [T][X] CS2103T User Guide
```

### `bye` - Exits the program

Duke closes after a brief delay and all currently registered tasks gets saved.

Example of usage:

`bye`

Expected outcome:

```
Bye. Hope to see you again soon!
```

## Remarks
Duke is able to handle cases where wrong command or command with wrong parameters are entered.

Duke saves the data in your home directory, more specifically at `home/data/data.txt`. Please be cautious as corruption of data would result in a full reset.