# User Guide
Welcome to CorkyNotes! CorkyNotes is a desktop app for managing your tasks, optimized for use via a Command Line Interface (CLI) while still having the benefits of a Graphical User Interface (GUI). If you can type fast, CorkyNotes can get your task management done faster than traditional GUI apps. Enjoy!

![Screenshot](Ui.png)



## Features


### Create different tasks
CorkyNotes supports 4 different types of tasks:

- Todos
- Deadlines
- Events
- DoAfter

1. Todos
Todos are tasks with a description and completion status.

1. Deadlines
Deadlines are tasks with a description, due date, and completion status.

1. Events
Events are tasks with a description, from date, to date, and completion status.

1. DoAfter
DoAfter are tasks with a description, after date, and completion status.


### View your tasks
Forgot what you added? Corky tracks them for you. You can even mark and delete task as and when they are completed.


## Usage


### Add todo: `todo`
Adds a todo to the list of tasks.

Command format:

`todo [description]`

Example of usage:

`todo see the sunrise`

Expected outcome:

```
Got it. I've added this task:
[T][ ] see the sunrise
Now you have 1 task in the list.
```

### Add deadline: `deadline`
Adds a deadline to the list of tasks.

Command format:

`deadline [description] /by [due_date]`

💡 Ensure `due_date` is `[dd-MM-yyyy] [HHmm]` 

Example of usage:

`deadline do the dishes /by 24-11-2023 1200`

Expected outcome:

```
Got it. I've added this task:
[D][ ] do the dishes (by: Nov 24 2023, 1200)
Now you have 2 tasks in the list.
```

### Add event: `event`
Adds an event to the list of tasks.

Command format:

`event [description] /from [start_date] /to [end date]`

💡 Ensure `start_date` and `end_date` are `[dd-MM-yyyy] [HHmm]` 

Example of usage:

`event watch football /from 24-11-2023 1200 /to 24-11-2023 1400`

Expected outcome:

```
Got it. I've added this task:
[E][ ] watch football (from: Nov 24 2023, 1400 to Nov 24 2023, 1400)
Now you have 3 tasks in the list.
```

### Add DoAfter: `doafter`
Adds an doafter task to the list of tasks.

Command format:

`doafter [description] /after [after_date]`

💡 Ensure `after_date` is `[dd-MM-yyyy] [HHmm]` 

Example of usage:

`doafter watch football /after 24-11-2023 1400`

Expected outcome:

```
Got it. I've added this task:
[A][ ] watch football (after: Nov 24 2023, 1400)
Now you have 4 tasks in the list.
```

### Mark Task as Done: `mark`
Marks the task as completed.

Format: `mark [index]`

💡 Ensure index is within `{1, 2, ... list_size}`

Example of usage:

`mark 2` marks the second task in the list as done.

Expected outcome:

```
Nice! I've marked this task as done:
[D][X] do the dishes (by: Nov 24 2023, 1200)
```

### Undo Mark Task as Undone: `unmark`
Unmarks the task as not completed.

Format: `unmark [index]`

💡 Ensure index is within `{1, 2, ... list_size}`

Example of usage:

`unmark 2` unmarks the second task in the list as not done.

Expected outcome:

```
Noted. I've marked this task as undone:
[D][ ] do the dishes (by: Nov 24 2023, 1200)
```

### Find Tasks: `find`
Lists all tasks that match the keyword.

Command format:

`find [keyword]`

Example of usage:

`find dishes` lists all tasks that contain dishes.


### Help Command: `help`
Lists all commands and their usage.

Example of usage:

`help`

Expected outcome:

```
Here are the commands you can use:
1. todo <task name> - adds a todo task
2. deadline <task name> /by <deadline> - adds a deadline task
3. event <task name> /at <start time> to <end time> - adds an event task  
4. doafter <task name> /after <task number> - adds a doafter task
5. list - lists all tasks
6. done <task number> - marks a task as done
7. delete <task number> - deletes a task
8. help - shows this help message
9. find <keyword> - finds tasks with the keyword
10. bye - exits the program
```

### List tasks: `list`
Lists all tasks that have been added.

Sample task list:

```
1. [T][ ] see the sunrise
2. [D][X] do the dishes (by: Nov 24 2023, 12:00 PM)
3. [E][ ] watch football (from: Nov 24 2023, 12:00 PM to Nov 24 2023, 2:00 PM)
4. [P][ ] watch football (from: Nov 24 2023, 12:00 PM to Nov 24 2023, 2:00 PM)
```

### Exit the program: `bye`
Exits the program and closes the GUI window.

Example of usage:

`bye`

Expected outcome:

GUI closes