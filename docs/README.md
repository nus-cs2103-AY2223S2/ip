# User Guide

Duke is a **light weight, easy to use and interactive task tracker**. Users
interact with it using **Command Line Interface** (CLI) to keep track of tasks 
like _Events, Deadlines_ and _ToDos_. Users can _add, delete, find, list, mark, 
unmark_ and _undo_ these tasks. At the end of the session, this will be saved 
and can be restored for the next session.

## Quick Start

### Installation

1. Ensure you have Java `11` or above installed in your Computer.
2. Download the latest `duke.jar` 
from [here](https://github.com/alson001/ip/releases).
3. Locate where the file is and open a command terminal in that directory.
4. Use the `java -jar duke.jar` command to run the application.

## Features 

### Adding a task: `event`, `deadline`, `todo`

### `event` 
Creates an Event task with a specified description from a start date
to an end date. Date must be in **yyyy-mm-dd** format.

Example of usage:\
`event attend class /from 2023-02-12 /to 2023-02-13`

Expected outcome:\
Creates an Event with the description `attend class` with occurrence from
`2023-02-12` to `2023-02-13`.

```
________________________________________________________
Got it. I've added this task:
[E][] attend class (from: Feb 12 2023 to: Feb 13 2023)
Now you have 1 tasks in the list.
________________________________________________________
```

### `deadline`  
Creates a Deadline task with a specified description with a due 
date. Date must be in **yyyy-mm-dd** format.

Example of usage:\
`deadline complete duke /by 2023-02-12`

Expected outcome:\
Creates a Deadline with the description `complete duke` to be done by
`2023-02-12`.
```
________________________________________________________
Got it. I've added this task:
[D][] complete duke (by: Feb 12 2023)
Now you have 2 tasks in the list.
________________________________________________________
```

### `todo` 
Creates a ToDo task with a specified description.

Example of usage:\
`todo ride a bicycle`

Expected outcome:\
Creates a ToDo with the description `ride a bicycle`.
```
________________________________________________________
Got it. I've added this task:
[T][] ride a bicycle
Now you have 3 tasks in the list.
________________________________________________________
```


### Deleting a task: `delete`

Deletes the specified task with given **index**.

Example of usage:\
`delete 3`

Expected outcome:\
Delete the **3rd** task on the task list.
```
________________________________________________________
Noted. I've removed this task:
[T][] ride a bicycle
Now you have 2 tasks in the list.
________________________________________________________
```

### Finding tasks: `find`
Find tasks with description that matches the 
**specified string**.

Example of usage:\
`find class`

Expected outcome:\
Finds all tasks with description that includes
`class`.
```
________________________________________________________
Here are the matching tasks in your list:
1. [E][] attend class (from: Feb 12 2023 to: Feb 13 2023)
________________________________________________________
```

### Listing tasks: `list`
List all tasks in the tasks list.

Example of usage:\
`list`

Expected outcome:\
List all tasks in order of when they were added.
```
________________________________________________________
Here are the tasks in your list:
1. [E][] attend class (from: Feb 12 2023 to: Feb 13 2023)
2. [D][] complete duke (by: Feb 12 2023)
________________________________________________________
```

### Marking task: `mark`
Marks the specified task with given **index**.

Example of usage:\
`mark 2`

Expected outcome:\
Marks the **2nd** task on the task list.
```
________________________________________________________
Nice! I've marked this task as done:
[D][X] complete duke (by: Feb 12 2023)
________________________________________________________
```

### Unmarking task: `unmark`

Unmarks the specified task with given **index**.

Example of usage:\
`unmark 2`

Expected outcome:\
Unmarks the **2nd** task on the task list.
```
________________________________________________________
OK, I've marked this task as not done yet:
[D][] complete duke (by: Feb 12 2023)
________________________________________________________
```
### Undoing task: `undo`

Undo the previous command. Commands that can be undone
includes _Add, Delete, Mark, Unmark_ while commands that 
cannot are _List, Find, Exit, Undo_.

Example of usage:\
`undo`

Expected outcome:\
Undo the previous command.
```
________________________________________________________
The previous command has been undone by:
________________________________________________________
Nice! I've marked this task as done:
[D][X] complete duke (by: Feb 12 2023)
________________________________________________________
________________________________________________________
```
### Exit: `bye`
Exits the Duke application.

Example of usage:\
`bye`

Expected outcome:\
Prints the following line of text on the screen and exits 
the application after a short while.

```
________________________________________________________
Bye. Hope to see you again soon!
________________________________________________________
```

