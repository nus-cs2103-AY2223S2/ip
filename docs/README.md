# Duke User Guide

> “Your mind is for having ideas, not holding them.” – David Allen ([source](https://dansilvestre.com/productivity-quotes/))

Duke frees your mind by remembering things you need to do

It is 
- Chat-based
- Has a user-friendly interface
- easy to learn
- ~~Fast~~ _SUPER FAST_ to use

## Steps to run
1. Download the JAR file from the latest release [here](https://github.com/anchengyang/ip/releases)
2. Open it
3. Start adding your tasks
4. Let it manage your tasks for you 👍

## Features 

### 1. Create todo items

Allows the user to create a todo item
`[T] [ ] My todo item`

### 2. Create deadlines

Allows the user to create a deadline
`[D] [ ] My deadline item (by: Oct 14 2023 04:30 PM)`

### 3. Create events

Allows the user to create an event
`[E] [ ] My event item (from: Oct 14 2023 04:30 PM to : Oct 19 2023 05:00 PM)`

### 4. Filter by Keyword

Allows the user to filter items in the list by a specific keyword

### 5. Filter by Date

Allows the user to filter deadlines in the list by a specific date

### 6. Mark/Unmark items as completed

Allows the user to mark items in the list as completed or uncompleted

### 7. Delete items

Allows the user to delete items in the list

### 8. Update the description of existing tasks

Allows the user to update the description of existing tasks in the list without having to create a new task

### 9. Update the deadline of existing deadlines

Allows the user to update the deadline of existing deadline tasks in the list without having to create a new deadline


## Usage

### `todo`

Create todo items with the given description

Example of usage: 

`todo <description>`

### `deadline`

Create deadline items with the given description and deadline

Example of usage: 

`deadline <description> /by <yyyy-MM-dd\n HHmm(optional)>`

### `event`

Create events with the given description and start and end dates

Example of usage: 

`event <description> /from <yyyy-MM-dd\n HHmm(optional)> /to <yyyy-MM-dd\n HHmm(optional)>`

### `find`

Finds items in the list with descriptions that match the given keyword(s)

Example of usage: 

`find <keyword(s)>`

### `finddate`

Finds items in the list with dates that match the given date

Example of usage: 

`find <date>`

### `mark`

Marks items in the list as completed

Example of usage: 

`mark <task number>`

### `unmark`

Unmarks items in the list as uncompleted

Example of usage: 

`unmark <task number>`

### `delete`

Deletes items in the list

Example of usage: 

`delete <task number>`

### `update`

Updates description or deadline of items in the list

Example of usage: 

`update <index> /description <new changes>`
`update <index> /deadline <new changes>`
