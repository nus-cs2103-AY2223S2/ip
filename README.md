# Chad Personal Assistant User Guide

## Features 

- Maintain a list of Tasks, Deadlines and Events.
- View your list at any time with a simple command `list`.
- Mark each item as Done or Not Done.
- Delete obsolete tasks.

## Quick Start Guide
1. Download the latest Chad version from (here](https://github.com/fahim-tazz/ip/releases).
2. Open your Terminal/Command Prompt.
3. Type `cd Downloads` (or wherever the `chad.jar` file is stored).
4. Type in `java -jar chad.jar`.
5. Type in commands to use Chad.
6. Enjoy!

---

## Usage

### `list` - List all current tasks.

Lists down all current tasks saved by Chad.
- T, D and E represent Todo, Deadline and Event tasks respectively.
- `[ ]` and `[X]` show whether a task is `Not Done` or `Done`, respectively.


### `todo [task name]` - Add a new Todo item.

Adds a new Todo task to your list.

### `deadline [task name] /by [due date YYYY-MM-DD]` - Add a new Deadline item.

Adds a new Deadline task to your list. 
- The name should be followed by a ` /by ` keyword. 
- The due date should come after the ` by `, and be in the format `YYYY-MM-DD`.

### `event [event name] /from [start time] /to [end time]` - Add a new Event item.

Adds a new Event to your list.
- The name should be followed by a ` /from ` keyword.
- The start time should come after the ` /from ` keyword, followed by ` /to `.
- The end time should come after the ` /to ` keyoword.
- Start and End times do not have any format constraints.

### `mark [task no.]` - Marks a task as done.

Marks the task at that serial number as done.
- Task numbers start from 1, and follow the ordering shown by the `list` command.

### `unmark [task no.]` - Marks a task as NOT done.

Marks the task at that serial number as NOT done.
- Task numbers start from 1, and follow the ordering shown by the `list` command.

### `delete [task no.]` - Removes a task from the list.

Permanently deletes the task at that serial number from the list.
- Task numbers start from 1, and follow the ordering shown by the `list` command.
- Deleted tasks CANNOT be recovered.

### `find [search phrase]` - Finds any task that matches with the search phrase.

Searches for any task that contains the search phrase.
- Search is CASE-SENSITIVE. `laundry` will NOT match with a task named `Do Laundry`.

### `bye` - Saves the current tasks and exits Chad.

Saves the current task list on your computer, and exits Chad. Tasks will be retrieved the next time you open Chad.
- You can also exit by clicking the [X] button on the Chad window. Don't worry, Chad will save your tasks no matter what!
