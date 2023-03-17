# User Guide

## Features 

### Feature-Task Adding

Users are able to add different types of tasks into a list, which Duke will keep track.

## Usage

Can add tasks of type todo, deadline and event.
Todo (Only has description)
Deadline (Has a description and deadline in date format)
Event (Has a description, start date and end date) 

### `todo\deadline\event` - Add tasks to list

Input keyword into dialogbox, and add respective items to list.

Example of usage: 

`todo description `
`deadline description /by DD-MM-YY`
`event description /from DD-MM-YY to DD-MM-YY`

Expected outcome: Tasks added to list.

Description of the outcome: Tasks will be added to list and saved in your home directory.

```
Duke:Roger!I've added this task to the list.
T[][] description
Now you have 1 tasks in the list
```

### Feature-Priority

Users are able to set the priority of each task which Duke will also keep track.

## Usage

Can change or set priority of tasks in the list with 3 levels , 0 = High, 1 = Normal, 2 = Low.

### `priority` - Change or set priority of tasks in list

Example of usage: 

`priority 1 0 `

Expected outcome: Tasks priority changed to high.

```
Duke:Roger!I've changed this task;s priority to high.
T[][] description priority : high
```

### Feature-Marking/Unmarking

Users are able to mark each task as done or undone.

## Usage

Example of usage:

`mark/unmark 1`

Expected outcome: Task 1 would be understood as completed by the bot.

List would show task1 with a symbol [X] to mark as complete

```
1.[T][X] ...
```


### Feature-List

View your current list of tasks.

## Usage

Example of usage:

`list`

Expected outcome: List of tasks added so far, loaded from user directory or current list on first time use.

List ouput, with dates for events and deadlines, together with indicator of completion.
```
Here are the remaining tasks in your list:
1. T[][] buy food
2. T[][] borrow book
```

### Feature-Delete

Users are able to delete tasks that are completed or wrongly added.

## Usage

Example of usage:

`delete 1`

Expected outcome: delete first task in the list.

Expected Ouput:
```
Ok! Ive deleted the task from your list.
Here are the remaining tasks in your list:
1. T[][] buy food
2. T[][] borrow book
```

### Feature-Find

Users are able to find a task in the list.

## Usage

### `Find task` - Find tasks in the list

Finds tasks with matching description in the task list.

Example of usage: 

`Find read book`

Expected outcome: Duke returns the task found if in the list.


```
Here are the matching tasks in your list.
T[][] read book priority : Low
```
