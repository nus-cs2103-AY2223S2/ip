# User Guide

_Chungus_ is a demo application built for a university software engineering class, based on the [Duke](https://github.com/se-edu/duke) template.

![A screenshot of the application](./Ui.png)

## Features

- **Persistence**: Chungus will save your tasks to disk
- **Text-based**: All interactions are through text commands
- **Cross-platform**: Thanks to groundbreaking Java 'write once, run anywhere' technology
- **Tags**: Chungus supports tagging tasks as an alternative way of categorization

## Usage

### List available tasks: `list`

Lists all tasks previously created and not deleted.

**Example of usage**:

`list`

**Expected outcome**:

All tasks previously created and not deleted are listed in order of creation.

### Create a todo: `todo`

Creates one todo. A todo is the simplest type of task, with just a text description and no associated date or time.

**Example of usage**:

`todo find atlantis`

**Expected outcome**:

A new task with the description "find atlantis" is created.

### Create a deadline: `deadline`

Creates a task with a deadline. The deadline must be in the `dd/MM/yyyy HHmm` format.

**Example of usage**:

`deadline write a user guide /by 11/02/2023 2359`

**Expected outcome**:

A new task with the description "write a user guide" is created, with the associated deadline of 2nd February 2023, 11:59pm.

### Create an event: `event`

Creates an event. An event is a type of task with a specified start and end time. The date and time must be in `dd/MM/yyyy HHmm` format.

**Example of usage**:

`event gotoubun movie preview /from 12/10/2022 1500 /to 12/10/2022 1700`

**Expected outcome**:

A new event with the description "gotoubun movie preview" is created, with the associated duration of 12th October 2022 3:00pm to 5:00pm.

### Complete a task: `mark`

Marks a task as completed. To select a task to complete, first use `list` to observe its current index. Then proceed to mark the task by its index, as per the example below. If the task was already marked as completed, then nothing happens.

Example of usage:

`mark 1`

Expected outcome:

The task with index 1 (obtained via `list`) is marked as completed.

### Undo completion: `unmark`

Undoes a completion of a task. This is the reverse of `mark`. The task is selected by its index, obtainable through the `list` command. If the task was already incomplete, then nothing happens.

**Example of usage**:

`unmark 1`

**Expected outcome**:

The task with index 1 is marked as incomplete.

### Delete a task: `delete`

Deletes a task. As with `mark`, a task can be selected using its index, which is checked by first using `list`. Note that deletion is not reversible.

**Example of usage**:

`delete 1`

**Expected outcome**:

The task with index 1 is deleted.

### Find a task: `find`

Finds tasks via a simple keyword search. All tasks with descriptions containing the exact phrase provided will be returned.

**Example of usage**:

`find date`

**Expected outcome**:

All tasks with the word "date" in its description are listed.

### Tag a task: `tag`

Tags a task with some tags. A task can have any number of tags. A tag must be a single token, e.g. `foo` or `bar` is fine, but `foo bar` is not. The task is selected by its index from running the `list` command.

**Example of usage**:

`tag 1 work urgent`

**Expected outcome**:

The task at index 1 is tagged with `work` and `urgent`.

### Match tasks by all tags: `tagall`

Finds all tasks which have at least _all_ the tags provided. For example, if the task is tagged with at least `work` and `urgent`, then `tagall work urgent` will match it. However, if the task is tagged with `work` but not `urgent`, then `tagall work urgent` will not match it.

**Example of usage**:

`tagall work urgent`

**Expected outcome**:

All tasks which were previously tagged with `work` and `urgent` are listed.

### Match tasks by any tag: `tagany`

Finds all tasks which have at least _one of_ the tags provided. For example, if the task is tagged with at least `work` or `urgent`, then `tagany work urgent` will match it. However, if the task is tagged with neither `work` nor `urgent`, then `tagany work urgent` will not match it.

**Example of usage**:

`tagany work urgent`

**Expected outcome**:

All tasks which were previously tagged with `work` or `urgent` are listed.

### Inspect tags: `tagsee`

Inspects the tags for a task. The task is selected by its index from running the `list` command.

**Example of usage**:

`tagsee 1`

**Expected outcome**:

The tags associated with task 1 are listed.
