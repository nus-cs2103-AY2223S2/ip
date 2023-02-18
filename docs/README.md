# User Guide

## Features 

### Feature-Add tasks to do!

You can add different tasks to manage your time.
ToDo task: a simple task to be done at any time
Deadline task: a task that has a due date
Event task: a task that lasts during a certain period

### Feature-Mark tasks as completed!

You can mark tasks as completed to keep yourself on track.
It is also possible to unmark tasks if you realise that it is actually not yet completed.

### Feature-List the tasks you have!

You can list the tasks you have for a general overview of what you have to do.

### Feature-Too many tasks? Find the task you have!

You can find the tasks that matches a particular description for easier reference.

### Feature-Task changed? Update your task details!

You can update your task details without having to create a new one.

### Feature-Absolutely sure you are done with the task? Delete your task!

You can delete your task from your task list when you are done.

### Feature-Save your tasks for future reference!

You can save your tasks so that you can return in the future to reference it or update it.
Like most cats, Lulu is a cranky cat, so make sure to say bye to him before closing the application to make sure he saves your tasks!

## Usage

### `todo` - Add a todo task

Adds a todo task to your task list that has a description.
todo (description)

Example of usage: 

`todo run`

Expected outcome:

Prints the description of the todo task and adds it to your task list.

```
______________________________________
Got it. I've added this task:
 [T][ ] run
Now you have 1 tasks in the list.
______________________________________ 
```

### `deadline` - Add a deadline task

Adds a deadline task to your task list that has a description and a due date.
deadline (description) /by (due date)

Example of usage: 

`deadline run /by today`

Expected outcome:

Prints the description of the deadline task and adds it to your task list.

```
______________________________________
Got it. I've added this task:
 [D][ ] run (by: today)
Now you have 2 tasks in the list.
______________________________________ 
```

### `event` - Add an event task

Adds an event task to your task list that has a description and the period of the event.
event (description) /from (starting time) /to (ending time)

Example of usage: 

`event run /from afternonon /to evening`

Expected outcome:

Prints the description of the event task and adds it to your task list.

```
______________________________________
Got it. I've added this task:
 [E][ ] run (from : afternoon to: evening)
Now you have 3 tasks in the list.
______________________________________ 
```

### `mark` - mark a task as completed

Marks a task in your tasklist as completed, using its relative number in the tasklist.
mark (the number of the task to be marked)

Example of usage: 

`mark 1`

Expected outcome:

Prints the description of the marked task and keeps it marked in your task list.

```
______________________________________
Nice! I've marked this task as done:
 [T][X] run
______________________________________ 
```

### `unmark` - mark a task as uncompleted

Marks a task in your tasklist as uncompleted, using its relative number in the tasklist.
Note that all tasks added are initially marked as uncompleted.
unmark (the number of the task to be marked)

Example of usage: 

`unmark 1`

Expected outcome:

Prints the description of the unmarked task and keeps it unmarked in your task list.

```
______________________________________
OK, I've marked this task as not done yet:
 [T][ ] run
______________________________________ 
```

### `list` - List your current tasks

Lists the tasks that you currently have, both marked as completed and uncompleted.
This command has no arguments, simply use
list

Example of usage: 

`list`

Expected outcome:

Prints the description of every task in a list format, with their corresponding number.

```
______________________________________
Here are the tasks in your list:

1. [T][ ] run
2. [D][ ] run (by: today)
3. [E][ ] run (from : afternoon to: evening)
______________________________________ 
```

### `find` - find tasks that matches a certain description

Finds the tasks that matches a given description.
find (description)

Example of usage: 

`find run`

Expected outcome:

Prints the description of every matching task in a list format.

```
______________________________________
Here are the matching tasks in your list:

1. [T][ ] run
2. [D][ ] run (by: today)
3. [E][ ] run (from : afternoon to: evening)
______________________________________ 
```

### `update` - update a task's details

Updates the details of a task that is already in your task list.
Depending on the type of task (todo/deadline/event), you can update the respective task's details.
todo: update (the number of the task to be marked) DESCRIPTION (description)
deadline: update (the number of the task to be marked) DESCRIPTION/BY (description/due date)
event: update (the number of the task to be marked) DESCRIPTION/TO/FROM (description/start time/end time)

Example of usage: 

`update 2 BY tomorrow`

Expected outcome:

Prints the updated description of the task and keeps the updated details in your task list.

```
______________________________________
 [D][ ] run (by: tomorrow)
______________________________________ 
```

### `delete` - Deletes a task

Deletes a task from your task list.
Use this command when you are done with the task and no longer want the task to appear in your task list.
delete (the number of the task to be deleted)

Example of usage: 

`delete 1`

Expected outcome:

Prints the description of the deleted task and removes it from your task list.

```
______________________________________
Noted! I've removed this task:
 [T][ ] run
Now you have 2 tasks in the list.
______________________________________ 
```

### `bye` - Save your tasks

Save your tasks as a txt file in ./data/lulu.txt
You must use this command if you want your tasks to be saved, so that your tasks can be loaded for future reference.
If you close the application without the bye command, your tasks will not be saved.
bye

Example of usage: 

`bye`

Expected outcome:

Prints the goodbye text and saves your tasks to ./data/lulu/txt

```
______________________________________
Bye! Hope to see you again soon!
______________________________________ 
```

### `Keyword` - Describe action