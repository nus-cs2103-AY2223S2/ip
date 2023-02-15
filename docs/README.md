# Duncan User Guide

Duncan is here to help manage your busy schedule!

## Features 

### 1. Add and delete:

Available tasks to be added include Todos, Deadlines and Events

### 2. Mark:
Mark or unmark tasks that are completed or incomplete

### 3. Archive :

Transfer tasks from the main list to a separate file for storage and future reference

## Usage

### `todo` - Add a Todo task to the list

Adds a named Todo task to the list

Example: `todo Homework`

Expected outcome:  Adds a Todo task to the main list

```
Sure. Imma add that real quick

[T][] Homework
```

### `deadline` - Add a Deadline to the list

Adds a Deadline with a name and specified date, separated by a `/by`

Date should be specified in `YYYY-MM-DD`

Example: `deadline Project /by 2023-02-25`

Expected outcome: Adds a Deadline task called Project that is due on 25th February 2023

```
Sure, Imma add that real quick

[D][] Project (by: 26 February 2023 Anno Domini)
```

### `event` - Add an Event to the list

Adds an Event with a name and a start date, separated by a `/from` and an end date, separated by a `/to`

Date should be specified in `YYYY-MM-DD`

Example: `event Holiday /from 2023-02-20 /to 2023-02-22`

Expected outcome: Adds an Event called Holiday from 20th February 2023 to  22th February 2023

```
Sure, Imma add that real quick

[E]  [] Holiday (from: 2023-02-20 to: 2023-02-22)
```

### `delete` - Delete a Task

Delete a currently stored Task by specifiying its position in the list

Example: `delete 1`

Expected outcome: The first Task is deleted

```
Got it, this task is gone:
[T][] Homework
```

### `list` - List all stored Tasks

Lists out all tasks by order of addition, with the earliest at the top

Example: `list`

Expected outcome: Lists out all tasks that are stored

```
1. [T][] Homework
2. [D][] Project (by: 26 February 2023 Anno Domini)
3. [E][] Holiday (from: 2023-02-20 to: 2023-02-22)
```

If the list is empty

```
Yo there's nothing in the list
```

### `mark` - Mark currently unmarked Tasks

Marks out tasks by their names

Example `mark Homework`

Expected outcome: The Homework task should have an X in the second box of its description

```
Alright, I've marked it out:

[T][X] Homework
```

If the task is already marked:

```
Hey how I can change this mark?
```

### `unmark` - Unmark currently marked Tasks

Removes the mark from currently marked Tasks

Example: `unmark Homework`

Expected Outcome: The Homework task should have its X removed from its description

```
Alright, I've erased the mark:

[T][] Homework
```

If the Task is already marked

```
Hey how I can change this mark?
```
### `archive`

List out currently archived tasks

Example: `archive`

Expected outcome: A list of currently archived tasks would be displayed

```
1. [T][] Homework 
```

### `archive <task number>`

Archive the selected task from the main list

Example: `archive 1`

Expected Outcome: The first task is archived

```
Got it, this task is gone:
[T][] Homework
Now you've got 1 task

Ok, transferred [T][] Homework
```

### `bye` - Exits the application

Exits and Closes the programme


