# Grand Duke User Guide

Grand Duke helps you keep track of our tasks so that you don't have to.

## Features

### Add task, Delete task
Add todos, events, and deadlines tasks to your list.
Remove tasks that you do not want to keep track of anymore.

### List tasks
Display your list of tasks.

### Find task
Search for a certain task using a keyword.

### Mark task, Unmark task
Mark and unmark a task as done.

## Usage

### `todo` - Add a todo task

**Format:**
`todo DESCRIPTION`

**Example of usage:**
`todo homework`

**Expected outcome:**
A to-do task with the description of homework is added to your list of tasks.

### `event` - Add an event

**Format:**
`event DESCRIPTION /from START_TIME /to END_TIME`

**Example of usage:**
`event Midterms /from 2023-02-13 /to 2023-02-15`

**Expected outcome:**
An event task with the description of `Midterms (from: Feb 13 2023 to Feb 15 2023)` is added to your list of tasks.

**Note:** 
The date must be in the format of `YYYY-MM-DD`.

### `deadline` - Add a deadline

**Format:**
`deadline DESCRIPTION /by DEADLINE`

**Example of usage:**
`deadline Assignment 1 /by 2023-02-15`

**Expected outcome:**
A deadline task with the description of `Assignment 1 (by: Feb 15 2023)` is added to your list of tasks.

**Note:**
The date must be in the format of `YYYY-MM-DD`.

### `delete` - Delete a task

**Format:**
`delete TASK_INDEX`

**Example of usage:**
`delete 1`

**Expected outcome:**
The first task on your list is deleted.

### `list` - Display your list of tasks

**Format:**
`list`

**Example of usage:**
`list`

**Expected outcome:**
Your list of tasks is displayed.

### `find` - Search for tasks

**Format:**
`find SEARCH_TERM`

**Example of usage:**
`find Midterms`

**Expected outcome:**
Tasks with the search term `Midterms` in their description are displayed.

**Note:**
The `SEARCH_TERM` is case-sensitive.

### `mark` - Mark a task

**Format:**
`mark TASK_INDEX`

**Example of usage:**
`mark 1`

**Expected outcome:**
The first task is marked as done.

### `unmark` - Unmark a task

**Format:**
`unmark TASK_INDEX`

**Example of usage:**
`unmark 1`

**Expected outcome:**
The first task is marked as undone.

### `tag` - tag a task

**Format:**
`tag TASK_INDEX #YOUR_TAG`

**Example of usage:**
`tag 1 #important`

**Expected outcome:**
Your first task is tagged as important.

### `bye` - Exit the app

**Format:**
`bye`

**Example of usage:**
`bye`

**Expected outcome:**
The session with Grand Duke ends. The window will be automatically closed.