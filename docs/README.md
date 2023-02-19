# nAkIri 😈
# User Guide

nAkIri is a **simple app to keep track of tasks**.

# Quick start

1. Download the jar file from [here](https://github.com/Sheemo/ip).
2. In the folder where the jar file is downloaded, open the terminal.
   1. Alternatively, open the terminal and enter `cd [the folder where the jar file is]`.
3. Enter `java -jar ip.jar`.
4. Enjoy! 😀

# Features

💡 Notes about the command format

- Words in `UPPER_CASE` are the parameters to be supplied by the user.
  e.g. in `todo NAME`, `NAME` is a parameter which can be used as `todo My homework`.
- Parameters in square brackets are optional.
  e.g. in `deadline NAME /by DATE [TIME]`, `TIME` is an optional parameter.
- Extraneous parameters for commands that do not take in parameters (such as `list` and `bye`) will be ignored.
  e.g. if the command specifies `list 123`, it will be interpreted as `list`.

### Exiting the program: `bye`

Exits the program.

> Format: `bye`

### Listing all tasks: `list`

Shows a list of the saved tasks in order of their priority.
Tasks with highest priority will be shown at the top.

> Format: `list`

### Adding a TODO task: `todo`

Adds a TODO task to the task list.

> Format: `todo NAME`

Examples:

- `todo Have fun`
- `todo Watch Ayame's streams`

### Adding a task with deadline: `deadline`

Adds a task with deadline to the task list.

> Format: `deadline NAME /by DATE [TIME]`
- The date must be in the format `yyyy-MM-dd`.
- The time, if specified, must in the format `HH:mm`.

Examples:

- `deadline CS2103T iP /by 2023-02-17`
- `deadline CS2103T iP /by 2023-02-17 23:59`

### Adding an event: `event`

Adds an event to the task list.

> Format: `event NAME /from DATE [TIME] /to DATE [TIME]`
- The dates must be in the format `yyyy-MM-dd`.
- The times, if specified, must in the format `HH:mm`.

Examples:

- `event Nakiri Ayame 4th Anniversary Live /from 2022-09-03 20:00 /to 2022-09-03 21:30`

### Delete a task: `delete`

Deletes the specified task from the task list.

> Format: `delete INDEX`
- Deletes the task at the specified `INDEX`.
- The index refers to the index number shown in the displayed task list.
- The index **must be a positive integer** 1, 2, 3, …

Examples:

- `list` followed by `delete 2` deletes the 2nd task in the task list.

### Find tasks by keyword: `find`

Finds tasks that contain a specified keyword

> Format: `find KEYWORD`
- If multiple keywords are specified, it will try to search for the keywords exactly.
  eg. `find bake cake` would return `bake cake with susan` but not `bake a cake`.

### Mark task as completed: `mark`

Marks a task/event as completed

> Format: `mark INDEX`
- Marks the task at the specified `INDEX` as completed.
- The index refers to the index number shown in the displayed task list.
- The index **must be a positive integer** 1, 2, 3, …

Examples:

- `list` followed by `mark 2` marks the 2nd task in the task list as completed.

### Unmark task as completed: `unmark`

Unmarks a task/event as completed

> Format: `unmark INDEX`
- Unmarks the task at the specified `INDEX` as completed.
- The index refers to the index number shown in the displayed task list.
- The index **must be a positive integer** 1, 2, 3, …

Examples:

- `list` followed by `unmark 2` unmarks the 2nd task in the task list as completed.

### Set task priority: `priority`

Sets the priority of a task.

> Format: `unmark INDEX PRIORITY`
- Sets the priority of the task at the specified `INDEX`.
- The index refers to the index number shown in the displayed task list.
- The index **must be a positive integer** 1, 2, 3, …
- `PRIORITY` can be `[h]igh`, `[m]edium` or `[l]ow`.

Examples:

- `list` followed by `priority 2 h` sets the priority of the 2nd task in the task list to high.
- `list` followed by `priority 2 low` sets the priority of the 2nd task in the task list to low.

### Saving the data

nAkIri data are saved in the hard disk automatically after any command that changes the data. There is no need to save manually 🙂

### Editing the data file

❗ **Caution:** If your changes to the data file makes its format invalid, nAkIri will discard all data and start with an empty data file at the next run.

---

# Command summary

| Action   | Format, Examples                                                                                      |
|----------|-------------------------------------------------------------------------------------------------------|
| Quit     | `bye`                                                                                                 |
| Todo     | `todo NAME`<br>eg. `todo homework`                                                                    |
| Deadline | `deadline NAME /by DATE [TIME]`<br>eg. `deadline homework /by 2023-02-17`                             |
| Event    | `event NAME /from DATE [TIME] /to DATE [TIME]`<br>eg. `event 4th Fes /from 2023-03-18 /to 2023-03-19` |
| Delete   | `delete INDEX`<br>eg. `delete 2`                                                                      |
| Find     | `find KEYWORD`<br>eg. `find CS2103T`                                                                  |
| Mark     | `mark INDEX`<br>eg. `mark 2`                                                                          |
| Unmark   | `unmark INDEX`<br>eg. `unmark 2`                                                                      |
| Priority | `priority INDEX PRIORITY`<br>eg. `priority 2 l`, `priority 2 high`                                    |
| List     | `list`                                                                                                |