# User Guide

## Features 

### Add New Tasks

This could be any of the following:
- **Todo**
- **Deadline** (1 date representing deadline)
- **Event** (2 dates representing start and end period)

All tasks should come with a short description.

### Delete Task
Use this when you want to pretend life isn't crumbling around you.

### List Existing Tasks
Displays a list of all your tasks.

### Mark or Unmark Task
Finished _(or unfinished)_ a task? You can indicate that as well!

### Find Task
Search for a task with a keyword or phrase.

## Usage

### `list` - Display all tasks

Duke will tell you how many tasks you have and what they are, including their important dates (if any) and whether they are completed.

Just type the command `list`

**Expected output:**
```
Here are the tasks in your list:
    <Task 1 information>
    <Task 2 information>
    ...
```

### `todo` - Create new Todo task

Something you want to do but no given deadline.

**Command**: `todo <description>`

**Expected output:**
```
Got it! I've added this task:
    [T][] <description>
Now you have <number> tasks in the list.
```

### `deadline` - Create new Deadline task

Something you want to do that has a given deadline.

**Command**: `deadline <description> /by <date>`

**Expected output:**
```
Got it! I've added this task:
    [D][] <description> (by: <date>)
Now you have <number> tasks in the list.
```

### `event` - Create new Event

An upcoming event with a start and end date.

**Command**: `event <description> /from <date> /to <date>`

**Expected output:**
```
Got it! I've added this task:
    [E][] <description> (from: <date> to: <date>)
Now you have <number> tasks in the list.
```

### `mark` - Mark task as complete

**Command**: `mark <task number>`

**Expected output:**
```
Nice! I've marked this task as done:
    <Task information>
```

### `unmark` - Unmark task to indicate it is not complete

**Command**: `unmark <task number>`

**Expected output:**
```
Okay! I've marked this task as undone:
    <Task information>
```

### `find` - Search task by keyword or phrase

Makes finding of relevant tasks easier.

**Command**: `find <keyword or phrase>`

**Expected output:**
```
Here are the matching tasks in your list:
    <Task 1 information>
    <Task 2 information>
    ...
```

### `bye` - Exit from app

**Command**: `bye`

**Expected output:**
```
Bye. Hope to see you again soon!
```
