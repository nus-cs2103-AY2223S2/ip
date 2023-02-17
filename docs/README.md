# User Guide:

## Add

### Todo
Add a to do task.

Usage: `todo [description]`

Example:
> todo Homework

### Deadline
Add a deadline task.

Deadline tasks differ from todo in a sense that there is a dead line that you
must finish by.

You will receive reminders for deadline tasks.

Usage : `deadline [description] /by [yyyy/MM/dd HHmm]`

Example:
> deadline homework /by 2022/10/05 1000

### Event
Add an event task.

Event tasks differ from todo in a sense that it happens during a specific range
of time only.

Usage : `event [description] /from [yyyy/MM/dd HHmm] /to [yyyy/MM/dd HHmm]`

Example:
> event Gymkhana filming with Ken /from 2018/11/20 1000 /to 2018/11/24 1800

## List / Find
### List
Returns a list of all tasks store in Waffles!

Usage : `list`

### Find
Find tasks that contain a particular keyword (not case sensitive).

Usage : `find [keyword]`

Example:
> find work

Output:
> All tasks containing the word 'work'

## Mark
### Mark
Marks a particular task as completed.

This integer is based on the value provided by `list` command

Usage: `mark [integer]`

Example:
> mark 1

### Unmark
Marks a particular task as incomplete.

This integer is based on the value provided by `list` command

Usage: `unmark [integer]`

Example:
> unmark 1

## Delete
### Delete
Deletes a particular task.

This integer is based on the value provided by `list` command

usage: `delete [integer]`

Example:
> delete 1

## Load / Save
### Load
Loads save file that contains the list.

Usage : `load`

### Save
Saves the current list into a file.

Usage : `save`

## Help
### Help
Prints out this user guide in text format!

Usage: `help`

## Quit
### Bye
Quits Waffles safely and automatically saves the list into a file!

Usage: `bye`
