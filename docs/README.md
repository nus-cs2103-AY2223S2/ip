# User Guide

## Features 

### Feature - Add Task

Allow users to add different types of tasks from the following list: 
1. Todo
2. Deadline
3. Event

### Feature - Task List

Allow users to view a list of taks.

### Feature - Mark/Unmark Task

Allow users to mark a task as done or mark a task as undone. 

### Feature - Delete Task

Allow users to delete a task from the task list.

### Feature - Find Tasks

Allow users to find tasks in their task list based on a given keyword.

## Usage

### `todo` - Adds a Todo task

Adds a Todo task to the list of tasks using the command `todo`

Example of usage: `todo go to school`

Expected outcome:

```
Got it. I've added this task:
  [T][ ] go to school
Now you have 2 tasks in the list.
```

### `deadline` - Adds a Deadline task

Adds a Deadline task to the list of tasks using the command `deadline`

Example of usage: `deadline have dinner /by 14/10/2023 1800`

Expected outcome:

```
Got it. I've added this task:
  [D][ ] have dinner (by: Oct 14 2023 18:00)
Now you have 3 tasks in the list.
```

### `event` - Adds a Event task

Adds a Event task to the list of tasks using the command `event`

Example of usage: `event open house /from 20/11/2023 1200 /to 21/11/2023 1700`

Expected outcome:

```
Got it. I've added this task:
  [E][ ] open house (from: Nov 20 2023 12:00 to: Nov 21 2023 17:00)
Now you have 4 tasks in the list.
```
### `list` - view task list

Shows the current list of tasks

Example of usage: `list`

Expected outcome:

```
1: [T][] assignment
2: [T][] go to school
3: [D][] have dinner (by: Oct 14 2023 18:00)
4: [E][] open house (from: Nov 20 2023 12:00 to: Nov 21 2023 17:00)
```

### `mark` - Marks a task as done

Marks a task as done 

Example of usage: `mark 2`

Expected outcome:

```
Nice! I've marked this task as done:
[T][X] go to school
```

### `unmark` - Marks a task as undone

Marks a task as undone 

Example of usage: `unmark 2`

Expected outcome:

```
OK, I've marked this task as not done yet:
[T][] go to school
```

### `delete` - Deletes a task

Delete the task from the task list 

Example of usage: `delete 2`

Expected outcome:

```
Noted. I've removed this task:
[T][] go to school
Now you have 3 tasks in the list.
```

### `find` - Find tasks with keyword

List all tasks in task list with the given keyword

Example of usage: `find dinner`

Expected outcome:

```
Here are the matching tasks in your list:
[D][] have dinner (by: Oct 14 2023 18:00)
```

### `bye` - Exits the program

Exits the program, updates and save task list in duke.txt

Example of usage: `bye`

Expected outcome:

```
Bye. Hope to see you again soon!
```
