# User Guide
Welcome to Duke! This is a chat bot that helps you 
remember your tasks and keep track of which of them
you have completed and are yet to complete!

## Commands
**list** - view all your tasks and their completion status <br>
**todo** - add a task with only a description <br>
**deadline** - add a task with a description and a deadline date <br>
**event** - add a task with a start/end date and time <br>
**mark** - mark any type of task as done <br>
**unmark** - mark any type of task as not done yet <br>
**list** - list all the tasks you are tracking <br>
**delete** - delete a task <br>
**bye** - exit the app

### View all your tasks and their completion status

Use the command `list` without any arguments to view
a list of all your tasks, their index numbers, and their
completion status.

### Add a task with only a description
Use the command `todo`, followed by a space and a description
of the task. Note that the description cannot be empty.

Syntax: `todo {insert description of task}` <br>
Example: `todo science homework`

### Add a task with a deadline 
Use the command `deadline`, followed by a space, then the 
description, followed by a forward slash, then the deadline date.
 Note that the format of the
**date** must be in this format: **dd-Mmm-yyyy**, where
Mmm refers to the first three letters of the month, the **first letter
being capitalised** If the date is a single digit, please prefix it with a 0.

Syntax: `deadline {insert description} / {insert deadline}` <br>
Example: `deadline programming assignment / 12-Feb-2024`

### Add an event with a start/end date and time
Use the command `event`, followed by a space, then the 
description, followed by a forward slash, followed by
the starting date and time, followed by another forward slash, 
and finally the ending date and time. The **time** needs to be in this format:
**HHMM**, where HHMM refers to the time in 24-hour format.
Note that the format of the **date** must be in this format: **dd-Mmm-yyyy**, where
Mmm refers to the first three letters of the month, the **first letter
being capitalised**. If a date is a single digit, please
prefix it with a 0.

Syntax: `event {insert description} / {insert start date 
and time} / {insert end date and time}` <br>
Example: `event music performance / 12-Feb-2023 1200 / 
12-Feb-2023 1300`

### Mark a task as completed
Use the comand `mark`, followed by a space and a task number based
on your list (use the command `list` to refer to your list).

A task that is marked as done appears in this format:
[T][X], where the 'X' indicates that it has been completed

Syntax: `mark {insert task number}` <br>
Example: `mark 1`

### Mark a task as not completed
Use the command `unmark`, followed by a space and a task number
based on your list (use the command `list` to refer to your list).

A task that is marked as yet to be done appears in this format:
[T][ ], where the empty [ ] indicates that it has not been completed

Syntax: `unmark {insert task number}` <br>
Example: `unmark 1`


### Delete a task
Use the command `delete`, followed by a space and the 
task number to be deleted, based on your list (use the command `list` 
to refer to your list)

Syntax: `delete {insert task number}` <br>
Example: `delete 3`

### Find a task with a keyword
Use the command `find`, followed by a space, and a
keyword, to search your list for a task that matches
the keyword.

Syntax: `find {insert keyword here}`
Example: `find homework`

### View the list of available commands in the app
Use the command `help`



### Exit the app
Use the command `bye` to exit the app
