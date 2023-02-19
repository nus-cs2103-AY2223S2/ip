# User Guide

## Features 

### List existing tasks

Displays all existing tasks saved. 

`list`

```
Here are the tasks in your list:
1. [T][ ] eat medicine
2. [D][ ] return library book (Feb 28 2022 1500hrs)
```

### Add a Task `todo`, `event`, `deadline`

By using the following commands, users can add a *todo*, *event* or *deadline* task to the list. 

`todo eat medicine`

```
Got it. I've added this task:
[T][ ] eat medicine
Now you have 1 tasks in the list.
```

`event meet sally /2021-12-13 2200/2021-12-13 2300`

```
Got it. I've added this task:
[E][ ] meet sally (From: Dec 13 2021 2200hrs To: Dec 13 2021 2300hrs)
Now you have 2 tasks in the list.
```
`deadline return library book /2022-02-28 1500`

```
Got it. I've added this task:
[D][ ] return library book (Feb 28 2022 1500hrs)
Now you have 3 tasks in the list.
```

### Delete a Task

Allows users to remove a task from the existing list by inputting the task serial number

`delete 2`

```
Noted. I've removed this task:
[E][ ] meet sally (From: Dec 13 2021 2200hrs To: Dec 13 2021 2300hrs)
Now you have 2 tasks in the list.
```

### Set task as done

Users can mark tasks as done by using the `mark` command and inputting the task serial number

`mark 1`

```
Nice! I've marked this task as done: 
1. [T][X] eat medicine
```

### Set task as undone

Users can mark tasks as undone by using the `unmark` command. 
`unmark 1`

```
Nice! I've marked this task as undone: 
1. [T][ ] eat medicine
```

### Find tasks with keyword

The `find` command gives users the ability to filter existing tasks based on the keyword provided. 

`find return`

```
Here are the matching tasks in your list: 
1. [D][ ] return library book (Feb 28 2022 1500hrs)
```

### Reminders for upcoming tasks

Users can view upcoming tasks for the next 7 days by using the `remind` command.

`list`

```
Here are the tasks in your list:
1. [T][ ] eat medicine
2. [D][ ] return library book (Feb 28 2022 1500hrs)
3. [E][ ] meet sally (From: Feb 23 2023 2200hrs To: Feb 26 2023 2300hrs)
```

`remind`

```
Here are your tasks for the next 7 days:
1. [E][ ] meet sally (From: Feb 23 2023 2200hrs To: Feb 26 2023 2300hrs)
```

### Save and exit

The command `bye` saves the list and users can now exit the application. 
