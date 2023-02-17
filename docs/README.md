# User Guide

Kude is a app that feels like interacting with an unfeeling system that only cares about getting tasks done.

## Command definitions

1. We will use the word "**word**" to describe a sequence of characters delimited by whitespace.
1. Commands may accept a single positional argument string, known as a **main argument**, beginning after the command itself and terminating at a newline or the first matching option, *leftmost-first*.
   `event <title> /from <start> /to <end>`
3. Words starting with a backslash `/` are interpreted as _compulsory_, _position-independent_ **options**.
   `todo /by`
1. Words starting with "<" and ending with ">" are interpreted as **arguments**.
1. Options can have arguments specified after a whitespace.
   `todo /by <date>`
1. An option's arguments can be *whitespace-containing* strings. They are terminated by a newline or a different matching option, *leftmost-first*.
   `event /from <start> /to <end>`
1. Options (and arguments) starting with "[" and ending with "]" are *optional*.
   `event /from <start> /to <end> [/place <place>]`
1. All other words that do not follow the above conventions of `/options` or `<arguments>` are interpreted as **commands** and **subcommands**.
   `list`
1. Date-time is in `yyyy-MM-dd HH:mm` format e.g. `2023-02-25 23:00`

<br>

## Features

### Adding a ToDo: `todo`
Adds a ToDo with the given content to the list of tasks.

Format: `todo <content> [/place place]`

### Adding a Deadline: `deadline`
Adds a Deadline with the given content and date-time to the list of tasks.

Format: `deadline <content> /by <date-time> [/place place]`

### Adding an Event: `event`
Adds an event with the content, start and end date-times to the list of tasks.

Format: `event <content> /from <start date-time> /to <end date-time> [/place place]`


### Listing tasks: `list`
Lists all the tasks in the list.
Format: `list`


### Marking a task: `mark/unmark`
Marks/unmark a task
Format: `mark <task number>`
or
Format: `unmark <task number>`


### Deleting tasks: `delete`
Delete any task
Format: `delete <task number>`


### Finding tasks: `find`
Find tasks using efficient search terms.
Format: `find <query>`

<br>

### Saving the data

Kude tasks are saved in the hard disk automatically after any modification command. The data is stored in `duke.txt` file next to the Jar file.

### Editing the data file
Unfortunately, the file is not human-readable nor human-editable.