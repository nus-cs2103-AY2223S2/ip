# User Guide

## Features 

### Feature-Add

Add tasks of different types to your list.

### Feature-Delete

Delete tasks

### Feature-List

View list of tasks

### Feature-Tagging

Tag your tasks with tag descriptions, view your tasks tagged with 
respective tags, and clear tags for given item.

## Usage

### `todo/event/deadline` 

Input keyword into dialogbox, and add respective items to list.

Example of usage: 

`todo description `
`deadline description /by DD-MM-YY`
`event description /from DD-MM-YY to DD-MM-YY`

Expected outcome: Tasks added to list

Description of the outcome.
Tasks will be added to list and saved in your home directory.

### `List` 

View your current list of items

Example of usage:

`list`

Expected outcome: List of tasks added so far, loaded from user directory or current list on first time use.

List ouput, with dates for events and deadlines, together with indicator of completion.
```
Here are the tasks in your list:
1. buy food
2. borrow book
```

### `mark/unmark` 

Mark and unmark current tasks on list

Example of usage:

`mark/unmark 1`

Expected outcome: Task 1 would be understood as completed by the bot.

List would show task1 with a symbol [X] to mark as complete

```
1.[T][X] ...
```
### `tag/tagged/cleartags`

Tagging functionality to tag different items with additional descriptions

Example of usage:

`tag1 fun`

Expected outcome: Item 1 would be tagged as fun.

`tagged fun`
List would show Item 1 

```
The following items has been tagged as fun, Item1...
```

`cleartags`
Expected outcome: Item 1 would no longer be tagged as fun. 

`tagged` will now output the following
```
The following items has been tagged as fun, no such tags.
```
