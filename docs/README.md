# User Guide
Welcome to CorkyNotes! CorkyNotes is a desktop app for managing your tasks, optimized for use via a Command Line Interface (CLI) while still having the benefits of a Graphical User Interface (GUI). If you can type fast, CorkyNotes can get your task management done faster than traditional GUI apps.



## Features
</br>


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
</br>

### Add todo: `todo`
Adds a todo to the list of tasks.

Command format:

`todo [description]`

Example of usage:

`todo see the sunrise`

Expected outcome:

Got it. I've added this task:
[T][ ] see the sunrise
Now you have 1 task in the list.

Add deadline: deadline
Adds a deadline to the list of tasks.

Command format:

deadline [description] /by [due_date]

💡 Ensure due_date is [dd/MM/yy] [HHmm] 

Example of usage:

deadline do ip /by 20/9/21 2359

Expected outcome:

Nee added this task:
  [D][ ] do ip (by: Sep 20 2021, 11:59 PM)
Nee has 1 task in the list.

Add event: event
Adds an event to the list of tasks.

Command format:

event [description] /at [time]

💡 Ensure time is [dd/MM/yy] [HHmm] 

Example of usage:

event play ctf /at 20/9/21 2359

Expected outcome:

Nee added this task:
  [E][ ] play ctf (at: Sep 20 2021, 11:59 PM)
Nee has 1 task in the list.

Mark Task as Done: done
Marks the task as completed.

Format: done [index]

💡 Ensure index is within {1, 2, ... list_size}

Example of usage:

done 2 marks the second task in the list as done.

Expected outcome:

Nice! Task done:
  [T][X] get coffee

Find Tasks: find
Lists all tasks that match the keyword.

Command format:

find [keyword]

Example of usage:

find apple lists all tasks that contain apple.


Undo commands: undo
Command format:

undo [number_of_commands]

Example of usage:

undo 1 Undos one command and returns the list of tasks.

💡 Ensure there is a previous command to undo.


List tasks: list
Lists all tasks that have been added.

Sample task list:

[T][X] get coffee
[D][ ] do ip (by: Dec 12 2021, 11:59 PM)
[E][ ] play ctf (at: Sep 9 2021, 8:00 PM)

Exit the program: bye
Exits the program and closes the GUI window.

Example of usage:

bye

Expected outcome:

GUI closes