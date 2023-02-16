# Tohtoro User Guide
Tohtoro is a Java app for managing the happenings in your daily life via a Command Line Interface (CLI) while still
having the benefit of a Graphical User Interface (GUI).

## Features
Users of the Tohtoro program are able to:
- 👋 Say **Bye** and close the program
- ➕ **Add** tasks
- 🗑️ **Delete** tasks
- `[X]` **Mark** a task to be done
- `[ ]` **Unmark** a task to be undone
- 👓 **Find** tasks
- 🖥️ **Edit** tasks
- 💼 View the **list** of tasks

## Usage

### `bye` - Closes the program

Format: `bye`

Example of usage:

`bye`

Expected outcome:

Tohtoro says goodbye to you and the program closes.

```
Meowww. Hope to see you soon!
```

### `todo` - Adds a ToDo task

Format: `todo <TASKNAME>`

Parameters:
- TASKNAME: Name of the to do task

Example of usage:

`todo Meet parents`

Expected outcome:

Tohtoro adds a Todo task with the respective description.

```
Meowww, I've added this task:
Added: [T][] Meet parents
Now you have 1 task(s) in your list
```

### `deadline` - Adds a Deadline task

Format: `deadline <TASKNAME> /by <DATE>`

Parameters:
- TASKNAME: Name of the deadline task
- DATE: Date of the task in d/MM/yyyy HHmm

Example of usage:

`deadline Return library book /by 13/12/2023 1450`

Expected outcome:

Tohtoro adds a Deadline task with the respective description and time.

```
Meowww, I've added this task:
Added: [D][] Return library book (by: Wed, 13 Dec 2023 02:50PM)
Now you have 2 task(s) in your list
```

### `event` - Adds an Event task
Format: `event <DESCRIPTION> /from <DATE1> /to <DATE2>`

Parameters:
- DESCRIPTION: Name of the event
- DATE1: Date of the start of the task in d/MM/yyyy HHmm
- DATE2: Date of the end of the task in d/MM/yyyy HHmm

Example of usage:

`event Attend concert /from 27/12/2023 1700 /to 27/12/2023 1900`

Expected outcome:

Tohtoro adds an Event task with the respective description and time.

```
Meowww, I've added this task:
Added: [E][] Attend Concert (from: Wed, 27 Dec 2023 05:00PM to: Wed, 27 Dec 2023 07:00PM)
Now you have 3 task(s) in your list
```

### `delete` - Removes a task

Format: `delete <INDEX>`

Parameters:
- INDEX: Number index of the task to be deleted

Example of usage:

`delete 1`

Expected outcome:

Tohtoro delete the task at index 1.

```
Meowww. I've removed this task:
[T][] Meet parents
Now you have 2 tasks in your list
```

### `mark` - Marks a task as done

Format: `mark <INDEX>`

Parameters:
- INDEX: Number index of the task to be marked

Example of usage:

`mark 1`

Expected outcome:

Tohtoro marks the task at index 1.

```
Nice! I've marked this task as done!
[X] Return library book
```

### `unmark` - Marks a task as not done

Format: `unmark <INDEX>`

Parameters:
- INDEX: Number index of the task to be unmarked

Example of usage:

`unmark 1`

Expected outcome:

Tohtoro unmarks the task at position 1.

```
I've marked this task as undone, you lazy bum
[ ] Return library book
```

### `edit` - Updates the details of a task

Format:
- `edit <INDEX> <TASK>`

Parameters:
- INDEX: Number index of the task to be editted
- TASK: The task you're replacing the old task with

Example of usage:

`edit 1 todo Borrow new library book`

Expected outcome:

Tohtoro edits the task at position 1 to a todo Borrow new library book.

```
Meowwww, I've edited this task for you.
[D][] Return library book (by: Wed, 13 Dec 2023 02:50PM)
meow changed into: [T][] Borrow new library book
```

### `list` - Displays all tasks currently

Format: `list`

Example of usage:

`list`

Expected outcome:

Displays all tasks currently

```
    Here are the tasks in your list:
    1. [T][ ] Borrow new library book
    2. [E][ ] Attend concert (from: Wed, 27 Dec 2023 05:00PM to: Wed, 27 Dec 2023 07:00PM)
```

### `find` - Displays all tasks that have the keyword

Format: `find <KEYWORD>`

Parameters:
- KEYWORD: The word that will be used to find tasks which has this word

Example of usage:

`find library`

Expected outcome:

Displays all tasks that has the word "Get" currently

```
    Meowww, here are the tasks you asked for:
    1. [T][ ] Borrow new library book
```