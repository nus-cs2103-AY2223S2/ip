# Broccoli Task Manager

Welcome to Broccoli, your personal task manager! <|°▿▿▿▿°|/

Broccoli is a simple command line chat bot that helps you manage your daily tasks. You can easily add, delete, and update tasks using simple commands, and Broccoli will keep track of everything for you.

## Getting Started

1. Download the source code from the repository 
2. import it into your Java development environment. 
You will also need to install JavaFX to run the application.

## Usage

Once you've launched Duke, you can enter commands to manage your tasks. Here are some examples:

- `list` - Lists all tasks
- `todo <description>` - Adds a new todo task
- `deadline <description> /by <date>` - Adds a new deadline task
- `event <description> /from <date> /to <date>` - Adds a new event task
- `mark <task index>` - Marks a task as done
- `unmark <task index>` - Unmarks a task
- `delete <task index>` - Deletes a task
- `find <keyword>` - Searches for tasks containing a keyword
- `help` - Shows a list of commands

## Examples

### `list`

Shows a list of all the tasks currently stored.

Example of usage:
`list`

Expected outcome:

Here are the tasks in your list:

1. [T][ ] do homework
2. [D][ ] submit assignment (by: Sep 15 2022)
3. [E][ ] attend meeting (at: Sep 20 2022)

### `todo`

Adds a new todo task to the list.

Example of usage:
`todo read a book`

Expected outcome:

Got it. I've added this task:

1.[T][ ] read a book

Now you have 4 tasks in the list.

### `deadline`

Adds a new deadline task to the list.

Example of usage:
`deadline submit report /by 2022-09-30`

Expected outcome:

Got it. I've added this task:

[D][ ] submit report (by: Sep 30 2022)

Now you have 5 tasks in the list.


### `event`

Adds a new event task to the list.

Example of usage:
`event team meeting /from 2022-09-25 /to 2022-09-26`

Expected outcome:

Got it. I've added this task:

[E][ ] team meeting (from: Sep 25 2022 to: Sep 26 2022)

Now you have 6 tasks in the list.

### `mark`

Marks a task as done.

Example of usage:
`mark 1`

Expected outcome:

Nice! I've marked this task as done:

[T][X] read a book

### `unmark`

Unmarks a task.

Example of usage:
`unmark 1`

Expected outcome:

Noted. I've unmarked this task:

[T][ ] read a book

### `delete`

Deletes a task from the list.

Example of usage:
`delete 1`

Expected outcome:

Noted. I've removed this task:

[T][ ] read a book

Now you have 5 tasks in the list.

### `find`

Searches for tasks containing a keyword.

Example of usage:
`find book`

Expected outcome:

Here are the matching tasks in your list:

1. [T][ ] read a book
2. [D][ ] submit book report (by: Sep 30 2022)

### `help`

Shows a list of commands.

Example of usage:
`help`

Expected outcome:

Hello! Looks like you need help.
Here's a list of my commands
✤ todo <task> - Adds a todo task to the list
✤ deadline <task> /by <date> - Adds a deadline task to the list
✤ event <task> /from <date> /to <date> - Adds an event task to the list
✤ list - Displays the list of tasks
✤ mark <task number> - Marks a task as done
✤ unmark <task number> - Marks a task as not done
✤ delete <task number> - Deletes a task from the list
✤ find <keyword> - Finds tasks with the keyword
✤ bye - Exits the program
✤ help - Displays this message
•────────•°•❀•°•────────•
Cool fact!
if your <date> is in the format YYYY-MM-DD it will be saved in MMM d yyyy format
Your tasks list will be saved even if you exit the program
Hope this helps! Try it now!!!!!


