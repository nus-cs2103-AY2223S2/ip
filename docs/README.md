# User Guide

Welcome to Duke, a chatbot that will help you with task management! 
This user guide will provide you with all you need to know to get
started!

## Features 

### Add task
You can add 3 types of tasks: to do, deadline or event. You also 
have the option to tag your tasks with a keyword.

### Delete task
You can remove tasks from your task list.

### List task
You can list all your tasks, list tasks with a keyword in its
description, or list all tasks tagged to a certain word. 

### Change task status
You can mark your tasks as completed or not completed yet. All 
tasks will be marked as uncompleted by default.

### Auto-saving
Duke will save all tasks each time you make a modification, so you
don't have to manually save it!

## Notes:
- Duke is not case-sensitive. Typing ```list``` and ```LIST``` is equivalent
- Items in square brackets are optional.
- ```/tag``` should be followed by one word. Any additional parameters will
be ignored.
- All date and time should follow the following format ```{YYYY-MM-DD HH:MM}```

## Usage

### 1. `list` - Lists tasks

Format: `list [{tag}]`

Example of usage: 

`list`

`list school` lists all tasks tagged 'school'


### 2. `todo` - Creates a new to do

Example of usage:

`todo get groceries`

`todo clean floors /tag home`


### 3. `deadline` - Creates a new deadline

Example of usage:

`deadline submit feedback /by 2023-04-01 10:30`

`deadline math homework /by 2022-02-14 01:27 /tag school`

### 4. `event` - Creates a new event

Example of usage:

`event meeting /from 2022-01-27 10:30 /to 2022-01-27 11:30`

`event party /from 2023-12-25 19:00 /to 2023-12-25 21:00 /tag friends`

### 5. `mark` - Marks task as completed

Example of usage:

`mark 3` marks the 3rd task in the task list

### 6. `unmark` - Marks task as uncompleted

Example of usage:

`unmark 6` unmarks the 6th task in the task list

### 7. `find` - Finds tasks with keyword

Example of usage:

`find buy` lists tasks with "find" in its description

### 8. `delete` - Deletes task

Example of usage:

`delete 1` deletes 1st task in the task list
