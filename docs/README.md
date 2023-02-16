# build settings
theme: modernist

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
- `event <description> /at <date>` - Adds a new event task
- `done <task index>` - Marks a task as done
- `delete <task index>` - Deletes a task
- `find <keyword>` - Searches for tasks containing a keyword

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
