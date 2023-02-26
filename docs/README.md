# Ava User Guide

## Features 

### `todo` Task 

Adds a Task with text description.

### `deadline` Task `\by <dd/mm/YYYY HHMM>`

Adds a Task with text description and the deadline date.

### `event` Task `\from <dd/mm/YYYY HHMM> \to <dd/mm/YYYY HHMM>`

Adds a Task with text description and the event from and to date.

### `mark X`

Marks a task as completed `X: Index of task to be marked`

### `unmark X`

Unmarks the completed task. `X: Index of task to be unmarked`

### `delete X`

Deletes selected task `X: Index of task to be deleted`

### `find W`

Searches for a task whose text description contains the keyword `W: Keyword`

### `list`

Lists all tasks made.

### `sort`

Sorts All tasks made by deadline date (`deadline`) , event start date(`Event`) and message length(`todo`).

### `bye`

exits the program.



## Usage
>*NOTE: To Enter commands press `Send Button` or **Enter ↵** on Keyboard.*
---
### `todo [DESCRIPTION]` - Add todo task

Adds a Task with text `[DESCRIPTION]`.

Example of usage: 

`todo read book`

Expected outcome:

```
Added this task for you:
  [T][ ] read book 
```
---
### `deadline [DESCRIPTION] /by [DUEDATE]` - Add deadline task

Adds a Task with text `[DESCRIPTION]` and the deadline date `[DUEDATE]`  .

Example of usage:

`deadline return book /by 20/02/2023 2300`

Expected outcome:

```
Added this task for you:
  [D][ ] return book
  (By: Mon,20th
  February,2023 11PM)
```
---
### `event [DESCRIPTION] /from [FROMDATE] /to [TODATE]` - Add event task

Adds a Task with text `[DESCRIPTION]`,the from date `[FROMDATE]` and the to date `[TODATE]` .

Example of usage:

`event party /from 20/02/2023 2100 /to 20/02/2023 2200`

Expected outcome:

```
Added this task for you:
  [E][ ] party (FROM:
  Mon,20th
  February,2023 9PM
  TO: Mon,20th
  February,2023 10PM)
```
---
### `list` - Lists all tasks

Displays all stored tasks with their Index number, Task Sign (`[T],[D] or [E]`) and Task Content.

Example of usage:

`list`

Expected outcome:
```
You've got a busy day 
ahead:
    1.[T][ ] read book
    2.[D][ ] return book
    (By: Mon,20th
    February,2023 11PM)
    3.[E][ ] party (FROM:
    Mon,20th
    February,2023 9PM
    TO: Mon,20th
    February,2023 10PM)
```
---
### `mark [INDEX]` - Mark task as complete

Marks the task with the list [INDEX] as completed.

Example of usage:

`mark 1`

Expected outcome:
```
Yayy! One more Task done: 
    [T][X] read book
```
---
### `unmark [INDEX]` - Unmark completed task

Unmarks the completed task with the list [INDEX] .

Example of usage:

`unmark 1`

Expected outcome:
```
Ahh! More Work to be 
done on this task: 
    [T][ ] read book
```
---
### `delete [INDEX]` - deletes Task

deletes the task with the list [INDEX] .

Example of usage:

`delete 1`

Expected outcome:
```
Oof! I have deleted 
this Task: 
    [T][ ] read book
```

---
### `find [KEYWORD]` - Finds tasks

Searches the tasks whose text description contains [KEYWORD] and lists the result found with their list index.

Example of usage:

`find book`

Expected outcome:
```
Here's what I found:
    2. [T][ ] read book
    3. [D][ ] return book
    (By: Mon,20th
    February,2023 11PM)
```
---
### `sort` - sorts task added

Sorts All tasks on different basis:
1. Todo by description length
2. Deadline by deadline date
3. event by from date

>*NOTE: Todo's are always displayed first and then deadline and event are displayed by their
respective dates.*

>**Warning: sort changes position of current tasks. So `list` before performing task
specific action such as `mark`,`unmark` and `delete`.**

Example of usage:

`sort`

Expected outcome:
```
I have organised it
for you:
    1. [T][ ] read book
    2. [E][ ] [E][ ] party (FROM:
    Mon,20th
    February,2023 9PM
    TO: Mon,20th
    February,2023 10PM)
    3. [D][ ] return book
    (By: Mon,20th
    February,2023 11PM)
```
---
### `bye` - exits the program

Ends Ava operations.
>*NOTE: To Exit the GUI(Program) press the `Close button` or **Enter ↵** on Keyboard.*

Example of usage:

`find book`

Expected outcome:
```
Have a nice day !!
```
---
