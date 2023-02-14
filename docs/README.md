# User Guide

## Features 

### Feature-Todo List

Duke can help you manage you deadlines and todos.

## Usage

### `list` - View all task

list all your existing tasks.

Example of usage: 

`list`

Expected outcome:

```
Here are the tasks in your list:
1. [T][ ] Watch TV
```

### `todo/deadline/event` - Create a task

Add a task using todo/deadline/event command.

Example of usage: 

`todo watch tv`  
`deadline assignment 1 /by 14/02/2023`  
`event Mun trip /from 01/01/2033 /to 10/01/2033`  

Expected outcome:

```
Here are the tasks in your list:
1. [T][ ] watch tv
2. [D][ ] Assignment 1
    Fed 14 2023.
3. [E][ ] Mun trip
    Jan 1 2033 -> Jan 10 2033.
```

### `find` - Search by keyword

find task by keyword.

Example of usage: 

`find assignment`

Expected outcome:

```
Here are the tasks in your list:
1. [T][ ] Assignment 1
```


### `date` - Search by date

find task by date.

Example of usage: 

`date 14/02/2023`

Expected outcome:

```
Here are the tasks in your list:
1. [T][ ] Assignment 1
    Fed 14 2023.
```

### `mark` - Complete task

You can mark a task as done.

Example of usage: 

`mark 1`

Expected outcome:

```
Here are the tasks in your list:
1. [T][ ] Assignment 1

mark 1

✅ I've marked this task as done:
[T][✔️] Assignment 1
```

### `unmark` - Revert compeleted task

You can unmark a task if necessary.

Example of usage: 

`unmark 1`

Expected outcome:

```
Here are the tasks in your list:
1. [T][✔️] Assignment 1

unmark 1

OK, I've marked this task as not done yet:
[T][ ] Assignment 1
```

### `delete` - Remove tasks

You can remove a task if necessary.

Example of usage: 

`delete 1`

Expected outcome:

```
Here are the tasks in your list:
1. [T][ ] Assignment 1

delete 1

Noted. I've removed this task:
[T][ ] Assignment 1
Now you have 2 tasks in the list.
```

### `bye` - Save and Quit

Say bye to store your todo list.

Example of usage: 

`bye`

Expected outcome:

```
👋. Hope to see you again soon!
```
