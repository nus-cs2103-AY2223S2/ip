# User Guide for Duke: chatbot with personal schedule tracker

# Features 

## Feature-Greet

Duke greets the user on startup. Who doesn't like a friendly bot?

---
## Feature-Exit

Command to safely exit Duke session. This saves your data.

### Usage

### `bye` - Closes Duke, and saves your changes.

Example of usage: 

`bye`

---
## Feature-List

Lists your currently added tasks that Duke is tracking.

### Usage

### `list` - Lists your tasks.

Example of usage: 

`list`

---
## Feature-Add todo

Adds a task of type `todo` to Duke

### Usage

### `todo` - Creates and adds a todo

Example of usage: 

`todo return book`

Expected outcome:

```
Got it. I've added this task:
[T][] return book
Now you have 3 tasks in the list
```

---
## Feature-Add deadline

Adds a task of type `deadline` to Duke. Deadlines have a due datetime.

### Usage

### `deadline` - Creates and adds a deadline

Example of usage: 

`deadline CS2107 assignment 1 /by 2023-03-02/23:59`

Expected outcome:

```
Got it. I've added this task:
[D][] CS2107 assignment 1 (by: MARCH 2 2023 23:59, THURSDAY)
Now you have 3 tasks in the list
```

---
## Feature-Add event

Adds a task of type `event` to Duke. Events have a start and end datetime.

### Usage

### `event` - Creates and adds an event

Example of usage: 

`event project meeting /from 2023-02-15/11:00 /to 2023-02-15/12:00`

Expected outcome:

```
Got it. I've added this task:
[E][] project meeting (from FEBRUARY 15 2023 11:00, to: FEBRUARY 15 2023 12:00)
Now you have 3 tasks in the list
```

---
## Feature-Mark

Marks the specified task as done.

### Usage

### `mark` - marks specified task

Example of usage: 

`mark 1`

Expected outcome:

```
Nice! I've marked this task:
[T][X] return book
```

---
## Feature-Unmark

Unmarks the specified task.

### Usage

### `unmark` - unmarks specified task

Example of usage: 

`unmark 1`

Expected outcome:

```
OK, I've marked this task as not done yet:
[T][] return book
```

---
## Feature-Delete

Deletes a task from the task list in Duke.

### Usage

### `delete` - deletes an event

Example of usage: 

`delete 1`

Expected outcome:

```
Noted. I've removed this task:
[T][] return book
Now you have 2 tasks in the list
```

---
## Feature-Find

Searches for an event containing a matching expression in the tasklist.

### Usage

### `find [keyword]` - Searches the tasklist for any event matching the regex `.*[keyword].*`

Example of usage: 

`find book`

Expected outcome:

```
Here are the wanted tasks in your list:
1.[T][] return book
```

---
## Feature-Tag

Attaches `#[tag]` to a task specified at the index

### Usage

### `tag [list index to be tagged] [tag content]` - Adds tag with tag content to item at index specified.

Example of usage: 

`tag 1 urgent`

Expected outcome:

```
Got it. I've tagged this task:
[T][] return book #urgent
```
