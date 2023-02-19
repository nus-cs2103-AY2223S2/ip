# Saul
Saul is a task manager with a variety of features.

## Features 

### Tasks

Add tasks of different types:
1. ToDos: Tasks with descriptions.
2. Deadlines: Tasks with a description and a deadline.
3. Events: Tasks with a description and a duration (indicated by a 'from' date-time and a 'to' date-time)

### List tasks

Lists your tasks along with completion status.

### Delete task

Delete a task from your current list.

### Mark/unmark tasks

Set your task completion status.

### Find tasks

Search for tasks with descriptions matching input keyword.

### Tag tasks

Add category tags to your tasks.

### Save tasks

Save tasks to storage to be automatically loaded in the next time you call Saul!

## Usage

### `todo` - Create a ToDo task

Creates a ToDo task and adds it to your current list.

Example of usage: 

`todo Call Mom`

Expected outcome:
```
Got it. I've added this task:
[T][] Call Mom
Now you have 1 tasks in the list.
```

### `deadline` - Create a Deadline task

Creates a Deadline task and adds it to your current list.

Example of usage:

`deadline Call Dad /by 2023-02-22 19:30`

Expected outcome:
```
Got it. I've added this task:
[D][] Call Dad (by: Feb 22 2023 19:30)
Now you have 2 tasks in the list.
```

### `event` - Create an Event task

Creates an Event task and adds it to your current list.

Example of usage:

`event birthday party /from 2023-02-24 17:00 /to 2023-02-24 19:30`

Expected outcome:
```
Got it. I've added this task:
[E][] birthday party (from: Feb 24 2023 17:00 to: Feb 24 2023 19:30)
Now you have 3 tasks in the list.
```

### `list` - List tasks

Displays your current list of tasks with completion status.

Example of usage:

`list`

Expected outcome:
```
Here are the tasks in your list:
1. [T][] Call Mom
2. [D][] Call Dad (by: Feb 22 2023 19:30)
3. [E][] birthday party (from: Feb 24 2023 17:00 to: Feb 24 2023 19:30)
```
### `delete` - Delete task

Deletes a task from the list.

Example of usage:

`delete 2`

Expected outcome:
```
Noted. I've removed this task:
[D][] Call Dad (by: Feb 22 2023 19:30)
Now you have 2 tasks in the list.
```

### `mark` - Mark task

Marks a specific task (specified by position in list) as completed.

Example of usage:

`mark 1`

Expected outcome:
```
Nice! I've marked this task as done:
[T][X] Call Mom
```

### `unmark` - Unmark task

Sets a specific task (specified by position in list) as incomplete.

Example of usage:

`unmark 1`

Expected outcome:
```
OK, I've marked this task as not done yet:
[T][] Call Mom
```

### `find` - Find tasks

Find tasks in list matching specified keywords.

Example of usage:

`find Call`

Expected outcome:
```
Here are the matching tasks in your list:
1.[T][] Call Mom
```

### `tag` - Tag task

Assign tags to specific tasks.

Example of usage:

`tag 1 family`

Expected outcome:
```
I've tagged this task as #family
[T][] Call Mom (#family)
```

### `bye` - Save tasks

Save current list of tasks to storage for subsequent loads.

Example of usage:

`bye`

Expected outcome:
```
Until next time... Better call Saul!
```

### Exit Saul

To exit the program, click the close button in the top-right corner of the window.
Don't forget to tell Saul you're leaving first (`bye`) so that he remembers your tasks 
for the next time you meet him!