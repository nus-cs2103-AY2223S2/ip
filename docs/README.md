# Welcome to Duke

>App that helps you to manage daily tasks e.g. deadlines, events, to do lists.
## Environment settings
This app requires Java version 11.

You can go to your terminal and cd into the directory you downloaded the app into.
```
java -jar duke.jar
```
## Features 
1. Add a todo, deadline, or event task.
2. Delete a task.
3. Mark/ unmark a task as done.
4. Archive your tasks.
5. Search your tasks by keyword.
6. Find out what tasks are happening/ due on a certain day.

## Usage

### `Keyword` - Describe action

### `todo {description}` - Adds a Todo task
Example of usage: 

`todo read book`

Expected outcome:

### `deadline {description} /by {deadline date}` - Adds a Deadline task
Example of usage:

`deadline read book /by 2020-01-01 1800`

Expected outcome:
### `event {description} /from {event start time} /to {event end time}` - Adds an event task
Example of usage:

`event read book /from 2020-01-01 1800 /to 2020-01-01 2000`

Expected outcome:
### `mark {index of task}` - Marks a task as done
Example of usage:

`mark 1`

Expected outcome:
### `unmark {index of task}` - Unmarks a task
Example of usage:

`unmark 1`

Expected outcome:
### `delete {index of task}` - Deletes a task
Example of usage:

`delete 1`

Expected outcome:
### `find {description}` - Finds tasks containing description
Example of usage:

`find book`

Expected outcome:
### `show deadlines or events on {date}` - Shows tasks happening on specified date
Example of usage:

`show deadlines or events on 2020-01-01`

Expected outcome:
### `archive` - Archives current list of tasks into archiveAll.txt
note: archive.txt contains all historical tasks.
Example of usage:

`archive`

Expected outcome:
### `list` - Shows current list of tasks
Example of usage:

`list`

Expected outcome:


