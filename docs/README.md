# Duke User Guide
Duke is a desktop app for managing tasks optimized for use via a Command Line Interface (CLI),
while still having the benefits of a Graphical User Interface (GUI).

## Usage

### `todo` - Creates a task

Creates a task to be done and adds it to the user's list.

Example of usage: 

`todo Borrow book`

Expected outcome:

```
I've added this task to your list:
[T][ ] Borrow book [tag: ]
```

### `deadline` - Creates a task to be done by a specified deadline

Creates a task to be done by a specified deadline and adds it to the user's list.
> Dates must be given using the following format: HH:mm dd-MM-yyyy

Example of usage: 

`deadline Visit library /by 12:00 20-02-2023`

Expected outcome:

```
I've added this task to your list:
[D][ ] Visit library [tag: ] (by: 12:00, Monday, Feb 20 2023)
```

### `event` - Creates a task to be done within a specified time period

Creates a task to be done within a specified time period and adds it to the user's list.
> Dates must be given using the following format: HH:mm dd-MM-yyyy

Example of usage: 

`event Visit library /from 12:00 20-02-2023 /to 13:00 20-02-2023`

Expected outcome:

```
I've added this task to your list:
[E][ ] Visit library [tag: ] (from: 12:00, Monday, Feb 20 2023 to: 13:00, Monday, Feb 20 2023)
```
### `mark` - Marks a task
Marks a specified task as completed.

Example of usage: 

`mark 1`

Expected outcome:

```
Nice! I've marked this task as done:
[T][X] Borrow book [tag: ]
```

### `unmark` - Unmarks a task
Marks a specified task as not completed yet.

Example of usage: 

`unmark 1`

Expected outcome:

```
OK, I've marked this task as not done yet:
[T][ ] Borrow book [tag: ]
```

### `list` - Lists all tasks
Provides a list of all the tasks that have been added by the user.

Example of usage: 

`list`

Expected outcome:

```
Here is your list:
1. [T][ ] Borrow book [tag: ]
2. [D][ ] Visit library [tag: ] (by: 12:00, Monday, Feb 20 2023)
3. [E][ ] Visit library [tag: ] (from: 12:00, Monday, Feb 20 2023 to: 13:00, Monday, Feb 20 2023)
```

### `delete` - Deletes a task
Deletes a specified task from the list

Example of usage: 

`delete 1`

Expected outcome:

```
Noted. I've removed this task:
[T][ ] Borrow book [tag: ]
```

### `find` - Searches for a task
Searches and returns all tasks that include a specified keyword.

Example of usage: 

`find library`

Expected outcome:

```
Here are the matching tasks in your list:
1. [D][ ] Visit library [tag: ] (by: 12:00, Monday, Feb 20 2023)
2. [E][ ] Visit library [tag: ] (from: 12:00, Monday, Feb 20 2023 to: 13:00, Monday, Feb 20 2023)
```

### `tag` - Categorizes tasks
Adds a tag to a specified task to distinguish its category.

Example of usage: 

`tag 1 School`

Expected outcome:

```
I have tagged the following task:
[D][ ] Visit library [tag: School] (by: 12:00, Monday, Feb 20 2023)
```

### `bye` - Exits the app
Returns a goodbye message and exits the app

Example of usage: 

`bye`

Expected outcome:

```
Bye. Hope to see you again soon!
```
