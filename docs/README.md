# User Guide
Welcome to CorkyNotes! CorkyNotes is a desktop app for managing your tasks, optimized for use via a Command Line Interface (CLI) while still having the benefits of a Graphical User Interface (GUI). If you can type fast, CorkyNotes can get your task management done faster than traditional GUI apps.



## Features


### Create different tasks
CorkyNotes supports 4 different types of tasks:

- Todos
- Deadlines
- Events
- Period Tasks

1. Todos
Todos are tasks with a description and completion status.

2. Deadlines
Deadlines are tasks with a description, due date, and completion status.

3. Events
Events are tasks with a description, from date, to date, and completion status.

4. Period Tasks
Period Taks are tasks with a description, from date, to date, and completion status. Period Tasks are similar to that of Event tasks.


### View your tasks
Forgot what you added? Corky tracks them for you. You can even mark and delete task as and when they are completed.


Prefer CLI to GUI?
java -jar ip-0.2.jar -mode cli to switch to CLI mode.

java -jar ip-0.2.jar -mode gui or simply java -jar ip-0.2.jar to use the GUI instead.


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

💡 Ensure `due_date` is `[dd-MM-yyyy] [HH:mm]` 

Example of usage:

`deadline do the dishes /by 24-11-2023 12:00`

Expected outcome:

```
Got it. I've added this task:
[D][ ] do the dishes (by: Nov 24 2023, 12:00 PM)
Now you have 2 tasks in the list.
```

### Add event: `event`
Adds an event to the list of tasks.

Command format:

`event [description] /from [start_date] /to [end date]`

💡 Ensure `start_date` and `end_date` are `[dd-MM-yyyy] [HH:mm]` 

Example of usage:

`event watch football /from 24-11-2023 12:00 /to 24-11-2023 14:00`

Expected outcome:

```
Got it. I've added this task:
[E][ ] watch football (from: Nov 24 2023, 12:00 PM to Nov 24 2023, 2:00 PM)
Now you have 3 tasks in the list.
```

### Add event: `Period Task`
Adds an period task to the list of tasks.

Command format:

`period [description] /from [start_date] /to [end_date]`

💡 Ensure `start_date` and `end_date` are `[dd-MM-yyyy] [HH:mm]` 

Example of usage:

`period watch football /from 24-11-2023 12:00 /to 24-11-2023 14:00`

Expected outcome:

```
Got it. I've added this task:
[P][ ] watch football (from: Nov 24 2023, 12:00 PM to Nov 24 2023, 2:00 PM)
Now you have 4 tasks in the list.
```

### Mark Task as Done: `done`
Marks the task as completed.

Format: `done [index]`

💡 Ensure index is within `{1, 2, ... list_size}`

Example of usage:

`done 2` marks the second task in the list as done.

Expected outcome:

```
Nice! I've marked this task as done:
[D][X] do the dishes (by: Nov 24 2023, 12:00 PM)
```

### Find Tasks: `find`
Lists all tasks that match the keyword.

Command format:

`find [keyword]`

Example of usage:

`find dishes` lists all tasks that contain dishes.



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