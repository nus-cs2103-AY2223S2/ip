# Duke 🤖

Duke is a graphical user interface (GUI) desktop app that helps you track your upcoming tasks. 

To start using Duke, you can:
- Ensure that you have java 11 installed on your computer
- Download the latest `.jar` file from [here](https://github.com/PawanPatil19/ip/releases),
- Navigate to the directory in terminal, 
- Use the `java -jar CS2103T-iP.jar` command to run the application.

## Features 👾

Duke is packed with various features, such as:

* Addition and deletion of tasks
    * Todos
    * Events
    * Deadlines
* Marking tasks as done / incomplete
* Searching tasks with keywords
* Searching tasks by dates
* Store tasks

## User Guide 🕹

### `list`

View a list of your tasks.

#### Usage

`list`

```
> list
  Here are your tasks :
  
  1. [T][X] Complete CS2103T Quiz
  2. [D][ ] iP Submission (by: Feb 17 2023 11:59 PM)
  3. [T][ ] Prepare TA slides
  4. [E][ ] 2101 OP1 presentation (from: Feb 16 2023 12:00 PM to: Feb 16 2023 02:00 PM)
  5. [T][ ] Meet Alice for lunch
```

### `todo`

Create a todo.

#### Usage

`todo {TASK_NAME}`

```
> todo Complete 2105 Assignment
  Got it. I've added this task:
      [T][ ] Complete CS2105 Assignment
  Now you have 6 tasks in the list.
```

### `event`

Create an event.

#### Usage

`event {EVENT_NAME} /from {START_TIME} /to {END_TIME}`

`{START_TIME}` and `{END_TIME}` should be `DD/MM/YY HH:MM`.

```
> event 2101 OP1 presentation /from 16/02/2023 12:00 /to 16/02/2023 14:00
  Got it. I've added this task:
      [E][ ] 2101 OP1 presentation (from: Feb 16 2023 12:00 PM to: Feb 16 2023 02:00 PM)
  You have 3 tasks in the list.
```

### `deadline`

Create a deadline.

#### Usage

`deadline {DEADLINE_NAME} /by {TIME}`

`{TIME}` should be in the form of `DD/MM/YY HH:MM`.

```
> deadline CS2106 quiz /by 16/09/23 23:59
   Got it. I've added this task:
      [D][ ] iP Submission (by: Feb 17 2023 11:59 PM)
   You have 1 tasks in the list.
```

### `mark`

Mark a task as completed.

#### Usage

`mark {TASK_INDEX}`

```
> mark 3, 5
  Nice! I've marked the tasks as done:
      [T][X] Prepare TA slides
      [T][X] Meet Alice for lunch
```


### `unmark`

Mark a task as incomplete.

#### Usage

`unmark {TASK_INDEX}`

```
> unmark 1
  OK, I've marked this task as not done yet:
      [T][ ] Complete CS2103T Quiz
```


### `delete`

Delete a specific task.

#### Usage

`delete {TASK_INDEX}`

```
> delete 3, 5
  Noted. I've removed the tasks:
	[T][X] Prepare TA slides
	[T][X] Meet Alice for lunch
  Now you have 4 tasks in the list.
```

### `find`

Search a task based on the keyword provided ignoring the case.

#### Usage

`find {KEYWORD}`

```
> find complete
  Here are your tasks:
  
    1. [T][ ] Complete CS2103T Quiz
    2. [T][ ] Complete CS2105 Assignment
```

### `listdate`

Search a tasks which start/end on a particular date

#### Usage

`listdate {DATE}`

`{DATE}` should in the form of `{DD/MM/YYYY}`


```
> listdate 17/02/2023
Here are your tasks: 
	1. [D][ ] iP Submission (by: Feb 17 2023 11:59 PM)
```

### `bye`

Quit the app.

#### Usage

`bye`

```
Bye, hope to see you again!
```

### Saving the data
Duke task are saved in the hard disk automatically after any command that changes the data. There is no need to save manually.