# User Guide

## Features 

> Things to note:
> - Words in UPPER_CASE are the parameters to be supplied by the user.
e.g. in add TODO, TODO is a parameter which can be used as add Study physics.
> - Dates should be in the format dd/mm/yyyy HHmm. 
e.g. 12/12/2023 1800
> - TASK_INDEX for all commands refers to the task index given by the list command.

***

## Usage

### `list` - Listing all tasks

Returns all the tasks in the task list.

Example of usage: 

`list`

Expected outcome:
```
Here are the tasks in your list:
  <task description>
  <task description>
  ...
```

Description of the outcome.

```
Returns all the tasks description in the task list.
```

***
### `todo` - Add Todo

Adds a todo task to the task list.

Example of usage: 

`todo TODO_NAME`

Expected outcome:
```
Got it. I've added this task:
  <task description>
Now you have X tasks in the list.
```

Description of the outcome.

```
Returns a message that the todo is added successfully.
```
***

### `deadline` - Add Deadline

Adds a deadline task to the task list.

Example of usage: 

`deadline DEADLINE_NAME \by DEADLINE_DATE`

Expected outcome:
```
Got it. I've added this task:
  <task description>
Now you have X tasks in the list.
```

Description of the outcome.

```
Returns a message that the deadline is added successfully.
```
***

### `event` - Add Event

Adds an event task to the task list.

Example of usage: 

`event EVENT_NAME \from FROM_DATE \to TO_DATE`

Expected outcome:
```
Got it. I've added this task:
  <task description>
Now you have X tasks in the list.
```

Description of the outcome.

```
Returns a message that the event is added successfully.
```
***

### `delete` - Delete tasks

Delete tasks in the task list.

Example of usage: 

`delete TASK_INDEX`

Expected outcome:
```
Noted. I've removed this task:
  <task description>
```

Description of the outcome.

```
Returns a message that the task is deleted successfully.
```
***

### `find` - Find tasks

Find tasks based on a keyword in the task list.

Example of usage: 

`find KEYWORD`

Expected outcome:
```
Here are the matching tasks in your list:
  <task description>
```

Description of the outcome.

```
Returns a message of all tasks that contains the keyword.
```
***
### `mark/unmark` - Mark tasks as done

Mark tasks as done or not done given a task index in the task list.

Example of usage: 

`mark/unmark TASK_INDEX`

Expected outcome:
```
Nice! I've marked this task as done:
  <task description>
```

Description of the outcome.

```
Returns a message of the task to be done or not done.
```
***
### `reminder` - Set reminder for task

Set reminder for a task at a certain specified time.

Example of usage: 

`reminder TASK_INDEX /at REMINDER_DATE`

Expected outcome:
```
Nice! I've set a reminder for this task:
  <task description>
```

Description of the outcome.

```
Returns a message of the reminder set successfully.
```
***
### `bye` - exit

Exits the program.

Example of usage: 

`bye`

Description of the outcome.

```
Exits the program.
```