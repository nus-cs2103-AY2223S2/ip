# User Guide

Are you a fast typist looking for a way to organise your mind? Then Duke has you covered. Duke is a desktop task tracking application designed for fast typists, offering both a terminal CLI and chatbot GUI. With Duke, you can spend less time remembering and more time doing.

## Prerequisites

* Install Java 11
* Download `duke.jar` from [here](https://github.com/hingen/ip/releases)

## Getting Started

1. Open up a terminal
2. Navigate to the directory containing the `duke.jar` file
3. Run the command `java -jar duke.jar` to launch the application in GUI chatbot mode
4. Type the command to execute in the command box of the application
5. Refer to the [Usage](https://hingen.github.io/ip/#usage) section below

## Launch Options
To launch the application in chatbot GUI mode, run the command:
```
java -jar duke.jar
```

To launch the application in terminal CLI mode, run the command:
```
java -jar duke.jar --cli
```

## Usage

### Adding a To-Do : `todo`

Adds a To-Do to the task list.

Format: `todo {DESCRIPTION}`
* `DESCRIPTION` cannot be empty

Examples:
* `todo Take out the trash` : Adds a To-Do to the task list with the description "Take out the trash".

### Adding a deadline : `deadline`

Adds a deadline to the task list.

Format: `deadline {DESCRIPTION} /by {CUTOFF}`
* `DESCRIPTION` and `CUTOFF` cannot be empty
* `CUTOFF` must be a valid date and time of the format `dd/MM/yyyy HHmm` (e.g. `17/02/2023 2359`)

Examples:
* `deadline Complete CS2103 Quiz /by 17/02/2023 2359` : Adds a deadline to the task list with the description "Complete CS2103 Quiz" and cutoff of 17 Feb 2023 11:59 PM.

### Adding an event : `event`

Adds an event to the task list.

Format: `event {DESCRIPTION} /from {START} /to {END}`
* `DESCRIPTION`, `START`, and `END` cannot be empty
* `START` and `END` must be valid dates and time of the format `dd/MM/yyyy HHmm` (e.g. `17/02/2023 2359`)

Examples:
* `event CS2103 Final Exam /from 26/04/2023 0900 /to 26/04/2023 1100` : Adds an event to the task list with the description "CS2103 Final Exam" that starts at 26 Apr 2023 09:00 AM and ends at 26 Apr 2023 11:00 AM.

### List all tasks : `list`

List out all tasks in the task list along with their corresponding index.

Format: `list`

### Searching for a task : `find`

List out all tasks in the task list (along with their corresponding index) which contain a specified keyphrase in it's description.

Format: `find {KEYPHRASE}`
* `KEYPHRASE` cannot be empty

Examples:
* `find Final Exam` : List out all tasks with the the keyphrase "Final Exam" in their description.

### Mark a task as completed : `mark`

Marks a task in the task list as completed.

Format: `mark {INDEX}`
* `INDEX` cannot be empty
* `INDEX` must be an integer
* `INDEX` must be associated with a task in the task list (refer to [list](https://hingen.github.io/ip/#list-all-tasks--list))

Examples:
* `mark 1` : Marks the first task in the task list as completed.

### Mark a task as not completed : `unmark`

Marks a task in the task list as not completed.

Format: `unmark {INDEX}`
* `INDEX` cannot be empty
* `INDEX` must be an integer
* `INDEX` must be associated with a task in the task list (refer to [list](https://hingen.github.io/ip/#list-all-tasks--list))

Examples:
* `unmark 1` : Marks the first task in the task list as not completed.

### Deleting a task : `delete`

Deletes a task from the task list.

Format: `delete {INDEX}`
* `INDEX` cannot be empty
* `INDEX` must be an integer
* `INDEX` must be associated with a task in the task list (refer to [list](https://hingen.github.io/ip/#list-all-tasks--list))

Examples:
* `delete 1` : Deletes the first task in the task list.

### Exiting the application : `bye`

Exits the application.

Format: `bye`

### Archiving task list : `archive`

Archives the current task list to a file, then clears the task list.

Format: `archive {PATH}`
* `PATH` cannot be empty
* `PATH` must be a valid file path
* `PATH` must refer to a non-existing file or an existing file which the application has write access to

Examples:
* `archive ./archive-task-list.csv` : Archives the current task list to a file at `./archive-task-list.csv` and clears the task list.
