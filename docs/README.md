# User Guide - Baymax

## Features: 

- ### `todo` : add todo
- ### `deadline` : add deadline
- ### `event` : add event
- ### `list` : view all task
- ### `delete` : delete task
- ### `mark` : mark as done
- ### `unmark` : mark as not done
- ### `find` : search for task by name
- ### `help` : show available commands
- ### `bye` : exit the program



## Usage

### `todo` - Adds a todo task to Baymax

Baymax receives the todo task and stores it

Example of usage: 

`todo walk the dog`

Expected outcome: Baymax successfully added the todo: todo walk the dog


```
New ToDo task has been added successfully: [T][ ] walk the dog
```

### `deadline` - Adds a deadline task to Baymax

Baymax receives the deadline task and stores it

Example of usage:

`deadline submit project /by 2023-02-17 (23:59)`

Expected outcome: Baymax successfully added the deadline: deadline submit project



```
New Deadline task has been added successfully: [D][ ] submit project (by: Feb 17 2023, 23:59)
```

### `event` - Adds an event task to Baymax

Baymax receives the event task and stores it

Example of usage:

`event recess week /from 2023-02-20 (00:00) /to 2023-02-26 (23:59)`

Expected outcome: Baymax successfully added the event: event recess week


```
New Event task has been added successfully: [E][ ] recess week (from Feb 20 2023, 0:0 to: Feb 26 2023, 23:59)
```

### `list` - Allows user to view all tasks stored in Baymax

Baymax displays all the users tasks in a numbered list

Example of usage:

`list`

Expected outcome: numbered list of all task


```
1: [T][ ] walk the dog
2: [D][ ] submit project (by: Feb 17 2023, 23:59)
3: [E][ ] recess week (from Feb 20 2023, 0:0 to: Feb 26 2023, 23:59)
```

### `delete` - Delete a task from Baymax

Baymax deletes a task accordign to the index given by the user

Example of usage:

`delete 2`

Expected outcome: Baymax successfully deleted the task: task deleted


```
Task has been delted: [D][ ] submit project (by: Feb 17 2023, 23:59)
```
### `mark` - Baymax marks a task as done

Baymax receive index from the user and marks the corresponding task as done

Example of usage:

`mark 1`

Expected outcome: Baymax marked the task as done


```
Task marked as done: [T][X] walk the dog
```

### `unmark` - Baymax marks a task as not done

Baymax receive index from the user and marks the corresponding task as not done

Example of usage:

`unmark 1`

Expected outcome: Baymax marked the task as not done


```
Task marked as done: [T][ ] walk the dog
```

### `find` - Baymax searches for tasks

Baymax searches for tasks with the keyword given by the user

Example of usage:

`find walk`

Expected outcome: Baymax found these tasks with your keyword


```
1: [T][ ] walk the dog
```
### `help` - Baymax shows the user a list of available commands

User is displayed with a list of possible commands

Example of usage:

`help`

Expected outcome: list of commands is displayed


```
Commands you can give Baymax (examples):
 - todo water the plants
 - deadline complete homework
   /by 2023-05-17 23:59
 - event movie night /from 2023-11-03 21:30
   /to 2023-11-03 23:00
 - list
 - delete
 - mark 4
 - unmark 4
 - find water
 - help
 - bye
```

### `bye` - Exit command for Baymax

This command exits the Baymax program. Tasks are stored for the next time you visit Baymax.

Example of usage:

`bye`

Expected outcome: Baymax closes.



```
See you soon!
```

