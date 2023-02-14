# DukeDeluxe User Guide

> Where you talk to a robot instead of your friends
DukePro frees your mind of having to remember things you need to do. It's,
- a robot
- software
- and most importantly homework for CS2103
All you need to do is:
1. download it
2. use it
Only for the price of **FREE** + 500$ *(Optional)*

Features:
- [ ] manage tasks without bugs
- [x] manage tasks sub-optimally
- [x] complicated code

if you are an experienced Java programmer **do not** look at the code
```
public static void main() {
  System.out.println("Do not look);
}
```

This site was built using [GitHub Pages](https://pages.github.com/)..

## Features 

### Feature-todo

adds a todo task to the list
Format: todo DESCRIPTION
The description refers to the task details
Examples:
todo eat burger


### Feature-deadline

adds a deadline task to the list
Format: deadline DESCRIPTION /by DATE
The description refers to the task details
the date refers to the date in YYYY-MM-DD
Examples:
deadline eat burger /by 2018-01-01

### Feature-event

adds an event task to the list
Format: event DESCRIPTION /from STARTDATE /to ENDDATE
The description refers to the task details
the from date refers to the starting date in YYYY-MM-DD format
the end date refers to the ending date in YYYY-MM-DD format
Examples:
event eat burger /from 2018-01-01 /to 2019-01-01

### Feature-list

shows the tasks in the list
Format: list
Examples:
list

### Feature-delete

Deletes the specified item from the address book
Format: delete INDEX
Deletes the item at the specified INDEX.
The index refers to the index number shown in the displayed list.
The index must be a positive integer 1, 2, 3, …
Examples:
list followed by delete 2 deletes the 2nd task.

### Feature-mark

marks a list as done
Format: mark INDEX
marks the item at the specified INDEX.
The index refers to the index number shown in the displayed list.
The index must be a positive integer 1, 2, 3, …
Examples:
list followed by mark 2 marks the 2nd task as done.

### Feature-unmark

unmarks a list as done
Format: unmark INDEX
unmarks the item at the specified INDEX.
The index refers to the index number shown in the displayed list.
The index must be a positive integer 1, 2, 3, …
Examples:
list followed by unmark 2 marks the 2nd task as done.

<!-- ## Usage

### `Keyword` - Describe action

Describe the action and its outcome.

Example of usage: 

`keyword (optional arguments)`

Expected outcome:

Description of the outcome.

```
expected output
``` -->
