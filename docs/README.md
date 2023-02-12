# User Guide

A basic todo list to store your todos, deadlines and events.

## Features 

### Add tasks to do, deadlines and events

Track your tasks by adding them into our task list!

### Find and view all your tasks easily

See all your todos, deadlines and events in one place, or even find a task with keywords!

### Delete tasks

Made a mistake? Delete the task from the task list easily!

### Port your tasks

Need to bring your tasks into another computer? simply copy over the data files!

### Duplicate detection

Automatically detects duplicate tasks and stops them from cluttering up the task list!

## Command Usage

### `todo`: Add a task to do.
Adds a basic task to do into the task list.

See the added task using the `list` command.

#### Usage

`todo <description>`

#### Example of usage: 

`todo buy groceries`

`todo clean up store room`

#### Expected outcome:

The todo is added to the task list.

```
Added task: [T][] <description>
```

### `deadline`: Add a deadline.
Adds a task with a deadline into the task list. Dates should be in the format `yyyy-mm-dd tttt`.

See the added task using the `list` command.

#### Usage

`deadline <description> /by <date>`

#### Example of usage:

`deadline complete project proposal /by 2023-02-13 2359`

`deadline pay rent /by 2023-04-30 2359`

#### Expected outcome:

The deadline is added to the task list.

```
Added task: [D][] <description> (by: <date>)
```

### `event`: Add an event.
Adds a task with start and end dates into the task list. Dates should be in the format `yyyy-mm-dd tttt`.

See the added task using the `list` command.

#### Usage

`event <description> /from <start date> /to <end date>`

#### Example of usage:

`event dentist appointment /from 2023-06-15 0900 /to 2023-06-15 1000`

`event NASA job interview /from 2023-04-02 1200 /to 2023-04-02 1300`

#### Expected outcome:

The event is added to the task list.

```
Added task: [E][] <description> (from: <start date> to: <end date>)
```

### `list`: List all events.
Lists down all added todos, deadlines and events.

Each task is numbered in the order they are added, starting from 0.
This is followed by 2 brackets showing the task type and its completion status.

##### Task types

`[T]` todos

`[D]` deadlines

`[E]` events

#### Completion status

`[]` not completed

`[X]` completed

This is followed by the description of the task and any relevant dates.

#### Usage

`list`

#### Example of usage:

`list`

#### Expected outcome:

The list of todos, deadlines and events are displayed.
If there are no tasks, Duke shows an empty response.

```
0. [T][] <description1>
1. [D][] <description2> (by: <date>)
2. [E][] <description3> (from: <start date> to: <end date>)
3. [T][X] <description4>
```

### `mark`: Mark a task as completed or not completed.

Marks a task at a particular index as completed or not completed.

See the index of a task using the `list` command.
Index must be larger than 0 and smaller than number of items in the list.

#### Usage

`mark <index>`

#### Example of usage:

`mark 0`

`mark 5`

#### Expected outcome:

If the task is previously incomplete, marks the task as completed.

```
Marked task <task> as done
```

Otherwise, mark the task as incomplete.

```
Marked task <task> as not done
```

### `delete`: Deletes a task.
Deletes a task at a particular index from the task list.

See the index of a task using the `list` command.
Index must be larger than 0 and smaller than number of items in the list.

#### Usage

`delete <index>`

#### Example of usage:

`delete 0`

`delete 3`

#### Expected outcome:

Task is deleted.

```
Deleted task <task>
```

### `find`: Find tasks using key phrases.
Find tasks in task list that contains key phrases in the description.

Tasks found will only contain the full key phrases in the same order.
Each task is shown in the order they are added, skipping tasks that do not contain the keyphrase.
This is followed by 2 brackets showing the task type and its completion status.

##### Task types

`[T]` todos

`[D]` deadlines

`[E]` events

#### Completion status

`[]` not completed

`[X]` completed

This is followed by the description of the task and any relevant dates.

#### Usage

`find <key phrases>`

#### Example of usage:

`find birthday`

`find assignment 4`

#### Expected outcome:

The list of todos, deadlines and events containing the key phrases are displayed.
If there are no tasks, Duke shows an empty response.

```
0. [T][] <description1>
1. [D][] <description2> (by: <date>)
3. [T][X] <description4>
```

### `bye`: Exits the Duke.
Saves the task list to disk and exits.

#### Usage

`bye`

#### Example of usage:

`bye`

#### Expected outcome:

Duke saves the task to the disk and exits.
The task list is restored from disk when the app is run again.

```
Good bye!
Closing in 5 seconds...
```
