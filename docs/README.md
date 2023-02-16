# User Guide

## Features 

### Feature-Add Tasks

Duke can keep help you keep track of your tasks.
You could add three types of tasks:
  1. Todos
  2. Deadlines
  3. Events
  
### Feature-Undo Your Command

Duke supports undoing your most recent command, excluding undoing an undo.

You do not have to worry about accidentally deleting your task now!


## Usage

### `TODO DESCRIPTION` - Add a Todo Task

This will add a todo task to your task list.

Example of usage: 

`todo read textbooks`

Expected outcome:

```
Got it, I've added this task:
 [T][ ] read textbooks
Now you have 1 tasks in the list
```

### `DEADLINE DESCRIPTION /BY YYYY-MM-DD` - Add a Deadline Task

This will add a task with a deadline to your task list.

Example of usage: 

`deadline finish homework /by 2023-02-01`

Note that the **/by** is essential

Duke currently only supports format with YYYY-MM-DD

Expected outcome:

```
Got it, I've added this task:
 [D][ ] finish homework (by: Feb 01 2023)
Now you have 2 tasks in the list
```

### `EVENT DESCRIPTION /FROM TIME /TO TIME` - Add a Deadline Task

This will add an event with a duration to your task list.

Example of usage: 

`event attend a meeting /from 4pm /to 6pm`

Note that the **/from** and **/to** is essential

Duke supports any forms of time inputs after the /from and /to keywords

Expected outcome:

```
Got it, I've added this task:
 [E][ ] attend a meeting (from: 4pm to: 6pm)
Now you have 3 tasks in the list
```

### `LIST` - Add a Deadline Task

This will prompt Duke to show the list of tasks you currently have.

Example of usage: 

`list`

Expected outcome:

```
Here are the tasks in your list:
1.[T][ ] read textbooks
2.[D][ ] finish homework (by: Feb 01 2023)
3.[E][ ] attend a meeting (from: 4pm to: 6pm)
```

### `MARK INDEX` - Mark A Task as Completed

This will mark an event as completed.

Note that the index must follow the ones shown on Duke's task list

Example of usage: 

`mark 2`

Expected outcome:

```
Nice! I 've marked this task as done:
 [D][X] finish homework (by: Feb 01 2023)
```

### `UNMARK INDEX` - Mark a Task as Incomplete

This will mark an event as not completed.

Note that the index must follow the one shown on Duke's task list

Example of usage: 

`unmark 2`

Expected outcome:

```
OK! I 've marked this task as not done yet:
 [D][ ] finish homework (by: Feb 01 2023)
```

### `DELETE INDEX` - Delete a Task From the List

This will delete the corresponding task from the list.

Example of usage: 

`delete 2`

Expected outcome:

```
Noted! I 've removed this task:
 [D][ ] finish homework (by: Feb 01 2023)
Now you have 5 tasks in the list.
```

### `FIND DESCRIPTION` - Find all Tasks in the List that Contain the Description

This will return all the relevant tasks as well as their status and details.

Example of usage: 

`find "homework"`

Expected outcome:

```
Here are the matching tasks in your list:
1.[D][ ] finish homework (by: Feb 01 2023)
```

### `UNDO` - Undo the Previous Command

This will reverse the action of your last command, except for list, find and undo commands.

Example of usage: 

`undo`

For an example, if the previous command deletes a deadline task from the list, the expected outcome is:

Expected outcome:

```
Got it, I've added this task:
 [D][ ] finish homework (by: Feb 01 2023)
Now you have 3 tasks in the list
```

### `BYE` - Exits the Program

This will exit Duke.

Example of usage: 

`bye`

Expected outcome:

```
Bye. Hope to see you again soon!
```

The program then closes automatically.
