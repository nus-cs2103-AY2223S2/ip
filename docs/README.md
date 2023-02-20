# C-3PO
C-3PO is a human-cyborg relations that manages your tasks.

## Tasks
There are 3 types of tasks:
1. Todo: Task with only description.
2. Deadline: Task with description and deadline date time.
3. Event: Task with description, and start and end date time.

## Features 

### Viewing list of commands: `help`

### Adding a todo task: `todo`

### Adding a deadline task: `deadline`

### Adding an event task: `event`

### Listing all tasks: `list`

### Marking a task: `mark`

### Unmarking a task: `unmark`

### Finding tasks by keywords: `find`

### Deleting a task: `delete`

### Exiting the program: `bye`

## Usage

### `help` - Prints the list of commands.

Reads from help.txt and prints the list of commands.

Format:

`help`

Example of usage: 

`help`

Expected outcome:

List of commands with explanation.

```
List of Commands
--------------------
1. Viewing list of commands: help

   Prints the list of commands.
   Format: help

2. Adding a todo task: todo

   Adds a todo task to the list of tasks.
   Format: todo DESCRIPTION 
   Example: todo read book
   Explanation: Adds the todo task read book to the list of tasks
   Note: Description cannot be empty

3. Adding a deadline task: deadline

   Adds a deadline task to the list of tasks.
   Format: deadline DESCRIPTION /by DATE TIME
   Example: deadline assignment /by 22/04/2023 1500
   Explanation: Adds the deadline task assignment to the list of tasks
   Note: Description and Date Time cannot be empty
         Date Time format is dd/mm/yyyy hhmm

4. Adding an event task: event

   Adds an event task to the list of tasks.
   Format: event DESCRIPTION /from DATE TIME /to DATE TIME
   Example: event project meeting /from 22/04/2023 1500 /to 22/04/2023 1800
   Explanation: Adds the event task project meeting to the list of tasks
   Note: Description and Date Time cannot be empty
         Date Time format is dd/mm/yyyy hhmm

5. Listing all tasks: list

    Lists all the tasks in the list.
    Format: list

6. Marking a task: mark

   Marks the task as done.
   Format: mark INDEX
   Example: mark 2
   Explanation: Marks the task as done at index 2 in the list of tasks

7. Unmarking a task: unmark

   Marks the task as not done.
   Format: unmark INDEX
   Example: unmark 2
   Explanation: Marks the task as not done at index 2 in the list of tasks
   
8. Finding tasks by keywords: find

   Finds the task that contain the keywords.
   Format: find KEYWORD [MORE_KEYWORDS] 
   Example: find book
   Explanation: Lists all the tasks with the keyword book in the list of tasks

9. Deleting a task: delete

   Deletes the task from the list of tasks.
   Format: delete INDEX
   Example: delete 2
   Explanation: Deletes the task at index 2 in the list of tasks

10. Exiting the program: bye

    Exits the program.
    Format: bye
```

### `todo` - Adds a todo task to the list of tasks.

Creates a todo task and adds it to the list of tasks.

Format:

`todo DESCRIPTION`

Example of usage: 

`todo read book`

Expected outcome:

Todo task read book is added to the list of tasks.

```
Got it. I've added this task:
  [T][] read book
Now you have 1 tasks in the list.
```

### `deadline` - Adds a deadline task to the list of tasks.

Creates a deadline task and adds it to the list of tasks.

Format:

`deadline DESCRIPTION /by DATE TIME`

Example of usage: 

`deadline assignment /by 16/05/2023 1500`

Expected outcome:

Deadline task assignment is added to the list of tasks.

```
Got it. I've added this task:
  [D][] assignment (by: May 16 2023 3:00PM)
Now you have 2 tasks in the list.
```

### `event` - Adds an event task to the list of tasks.

Creates an event task and adds it to the list of tasks.

Format:

`event DESCRIPTION /from DATE TIME /to DATE TIME`

Example of usage: 

`event project meeting /from 22/02/2023 1400 /to 22/02/2023 1600`

Expected outcome:

Event task project meeting is added to the list of tasks.

```
Got it. I've added this task:
  [E][] project meeting (from: Feb 22 2023 2:00PM to: Feb 22 2023 4:00PM)
Now you have 3 tasks in the list.
```

### `list` - Lists all the tasks in the list.

Reads from the list of tasks and prints it.

Format:

`list`

Example of usage: 

`list`

Expected outcome:

List of tasks.

```
Here are the tasks in your list:
1. [T][] read book
2. [D][] assignment (by: May 16 2023 3:00PM)
3. [E][] project meeting (from: Feb 22 2023 2:00PM to: Feb 22 2023 4:00PM)
```

### `mark` - Marks the task as done.

Finds the task by index and marks it as done.

Format:

`mark INDEX`

Example of usage: 

`mark 1`

Expected outcome:

Task at index 1 is marked as done.

```
Nice! I've marked this task as done:
  [T][X] read book
```

### `unmark` - Marks the task as not done.

Finds the task by index and marks it as not done.

Format:

`unmark INDEX`

Example of usage: 

`unmark 1`

Expected outcome:

Task at index 1 is marked as not done.

```
OK, I've marked this task as not done yet:
  [T][] read book
```

### `find` - Finds the task that contain the keywords.

Finds the tasks that contain the keywords and prints it.

Format:

`find KEYWORD [MORE_KEYWORDS]`

Example of usage: 

`find book`

Expected outcome:

Lists all the tasks with the keyword book in the description.

```
Here are the matching tasks in your list:
1. [T][] read book
```

### `delete` - Deletes the task from the list of tasks.

Finds the task by index and deletes it from the list of tasks.

Format:

`delete INDEX`

Example of usage: 

`delete 1`

Expected outcome:

Task at index 1 is deleted.

```
Noted. I've removed this task:
  [T][] read book
Now you have 2 tasks in the list.
```

### `bye` - Exits the program.

Exits the program with a 2 second delay.

Format:

`bye`

Example of usage: 

`bye`

Expected outcome:

Prints a goodbye message and exits the program.

```
Bye. Hope to see you again soon!
```
