# User Guide
CookieBot is a desktop CLI application for keeping track of the status of various tasks, including tasks with deadlines

#Quick start

1) Ensure that you have Java 11 installed on the computer.
2) Download Duke.jar from here.
3) Open the command terminal and `cd` into the folder where Duke.java is in and use `java -jar Duke.jar` command to run the application.



## User Guide
___
##Features:

### Keep track of different tasks

Keeps track of various tasks including tasks with timeframes or deadlines

### Mark and unmark tasks

Able to mark and unmark tasks upon completion

### Use shorter aliases for keywords

Recognises shorter commands when executing commands

## Usage

***
***
### `todo TASK_DESC` or `TD TASK_DESC` - Adds a task

<br> Adds the name of the task into the List.
<br> Replace `TASK_DESC` with the name of the task.`TASK_DESC` cannot be empty.


Example of usage:

`todo eat cookies` or
`TD eat cookies`

Expected outcome:

The task will be sucessfully added

```
Got it. I've added this task:
	[T][ ] eat cookies
Now you have 1 tasks in the list.
```
***
***

### `deadline TASK_DESC /by END_DATETIME` or `DL TASK_DESC /by END_DATETIME` - Adds a task with a deadline

<br> Adds the name of the task with a dateline into the List.
<br> Replace `TASK_DESC` with the name of the task. `TASK_DESC` cannot be empty.
<br> Replace `END_DATETIME` with the date and time of the deadline. `END_DATETIME` cannot be empty. `END_DATETIME` has to be in the format `YYYY-MM-DD HHMM`. 


Example of usage:

`deadline clear up cookies /by 01/03/2023 2359` or
`DL clear up cookies /by 01/02/2023 2359`

Expected outcome:

The task will be sucessfully added

```
Got it. I've added this task:
	[D][ ] clear up cookies (By Mar 1 2023 11:59 PM)
Now you have 1 tasks in the list.
```

***
***
### `event TASK_DESC /from START_DATETIME /to END_DATETIME` or `EV TASK_DESC /from START_DATETIME /to END_DATETIME` - Adds a task with a timeframe - start and end

Adds the name of the task with a timeframe into the List.

<br> Replace `TASK_DESC` with the name of the task. `TASK_DESC` cannot be empty.
<br> Replace `START_DATETIME` with the date and time of the deadline. `START_DATETIME` cannot be empty. `START_DATETIME` has to be in the format `YYYY-MM-DD HHMM`.
<br> Replace `END_DATETIME` with the date and time of the deadline. `END_DATETIME` cannot be empty. `END_DATETIME` has to be in the format `YYYY-MM-DD HHMM`.


Example of usage:

`event stock up cookies /from 01/03/2023 2359 /to 02/03/2023 2359` or
`EV stock up cookies /from 01/03/2023 2359 /to 02/03/2023 2359`

Expected outcome:

The task will be sucessfully added

```
Got it. I've added this task:
	[E][ ] Walk cat (from: Mar 1 2023 11:59 PM to: Mar 2 2023 1:59 PM)
Now you have 1 tasks in the list.
```

***
***
### `list` or `L` - Lists all tasks

List all the tasks that have been added in order of addition.

Example of usage:

`list` or `L`

Expected outcome:

The task will be sucessfully added

```
1.[T][]eat cookies
2.[D][]clear cookies (by:Feb 1 2023 1159pm)
3.[E][]stock up cookies (from:Mar 12023 1159pm to:Mar 2 2023 1159pm)
```

***
***
### `mark TASK_ID` or `M TASK_ID` - Marks the completion of a task

<br> Marks a task as specified by `TASK_ID` as done.
<br> `TASK_ID` can be viewed using `list` command.`TASK_ID` must be a valid ID.


Example of usage:

`mark 1` or `M 1`

Expected outcome:

Task with task id matching `TASK_ID` will be marked as done.

```
NOM NOM NOM! I've marked this task as done:
[T][X]eat cookies

Now you have 3 tasks in the list
```

***
***
### `unmark TASK_ID` or `U TASK_ID` - Unmarks the completion of a task

<br> Unmark a task as specified by `TASK_ID` as done.
<br> `TASK_ID` can be viewed using `list` command.`TASK_ID` must be a valid ID.


Example of usage:

`unmark 1` or `U 1`

Expected outcome:

Task with task id matching `TASK_ID` will be marked as done.

```
NOM NOM NOM! I've marked this task as not done yet:
[T][]eat cookies

Now you have 3 tasks in the list
```

***
***
### `delete TASK_ID` or `D TASK_ID` - Deletes a task from the List as specified by `TASK_ID`

<br> Deletes a task as specified by `TASK_ID`.
<br> `TASK_ID` can be viewed using `list` command.`TASK_ID` must be a valid ID.


Example of usage:

`delete 1` or `D 1`

Expected outcome:

Task with task id matching `TASK_ID` will be removed from the list.

```
Noted I've removed this task:
[D][X]clear up cookies (by:Feb 1 2023 1159pm)

Now you have 2 tasks in the list
```

***
***
### `find TASK_STRING` or `F TASK_STRING` - Finds a particular task name

Finds all tasks containing the string given in `TASK_STRING`.

Example of usage:

`find eat` or `F eat`

Expected outcome:

Task containing the string eat in its name will be returned.

```
1.[T][]eat cookies
```

###Command Summary

| Command              | Examples                                                                                                                         |
|----------------------|----------------------------------------------------------------------------------------------------------------------------------|
| Add task             | `todo TASK_DESC` <br/> eg. todo eat cookies                                                                                      |
| Add deadline         | `deadline TASK_DESC /by END_DATETIME` <br/> eg. deadline buy cookies /by 02/03/2023 2000                                         |
| Add event            | `event TASK_DESC /from START_DATETIME /to END_DATETIME` <br/> eg. event clear cookies /from 02/03/2023 2000 /to eat cookies 2200 |
| List                 | `list`                                                                                                                           |
| Mark as completed    | `mark TASK_ID` <br/> eg. mark 1                                                                                                  |
| Mark as not complete | `unmark TASK_ID` <br/> eg. unmark 1                                                                                              |
| Delete task          | `delete TASK_ID` <br/> eg. delete 1                                                                                              |
| Find task            | `find TASK_string` <br/> eg. find do                                                                                             |
 

