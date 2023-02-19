# User Guide

## About Duke

Duke is a to-do list manager, that allows you to keep track 
of your tasks! Use text commands to add, delete, and manage 
the different tasks on your list!

## Features

- Add and delete tasks
- Add tags to tasks
- Save and restore lists between sessions
- Search for tasks using keywords

##Usage

### `todo` Adds a to-do

Example of usage: `todo borrow book`\
Expected outcome: Adds a new to-do to the list with the given name\
Output: 
```
Got it. I've added this task:  
[T][ ] borrow book
Now you have 1 tasks in the list.
```


### `deadline` Adds a deadlines

Example of usage: `deadline return book /by 12/12/22 1800`\
Expected outcome: Adds a new deadline to the list with the given name
and deadline\
Output:
```
Got it. I've added this task:
[D][ ] return book (by: DEC 12 22 1800)
Now you have 2 tasks in the list.
```


### `event` Adds a events

Example of usage: `event party /from mon 10am /to mon 6pm`\
Expected outcome: Adds a new event to the list with the given name, 
starting date and ending date.\
Output:
```
Got it. I've added this task:
[E][ ] party (from: mon 10am to: mon 6pm)
Now you have 3 tasks in the list.
```

### `delete` Deletes a task

Example of usage: `delete 1`\
Expected outcome: Deletes the first task on the list\
Output:
```
Noted. I've removed this task:
[T][ ] borrow book
Now you have 2 tasks in the list.
```

### `list` Displays the current list

Example of usage: `list`\
Expected outcome: A display of the current task list\
Output:
```
1. [D][ ] return book (by: DEC 12 22 1800)
2. [E][ ] party (from: mon 10am to: mon 6pm)
```

### `find` Searches a task using a keyword

Example of usage: `find book`\
Expected outcome: Returns all tasks that contain the keyword\
Output:
```
Here are the matching tasks in your list:
1. [D][ ] return book (by: DEC 12 22 1800)
```

### `mark` Marks a task as complete

Example of usage: `mark 1`\
Expected outcome: Marks the task at the indicated index 
as complete\
Output:
```
Nice! I've marked this task as done:
[D][X] return book (by: DEC 12 22 1800)
```

### `unmark` Marks a task as incomplete

Example of usage: `unmark 1`\
Expected outcome: Marks the task at the indicated index
as incomplete\
Output:
```
Nice! I've marked this task as done:
[D][ ] return book (by: DEC 12 22 1800)
```

### `tag` Gives a tag to a task

Example of usage: `tag 1 urgent`\
Expected outcome: Gives the task at the corresponding index 
a given tag\
Output:
```
Noted. I've added the tag to this task:
[D][ ] return book [urgent] (by: DEC 12 22 1800)
```

### `bye` Exits the application

Example of usage: `bye`\
Expected outcome: Exits the application
