# Duke (With Less User Respect) User Guide

Note: This project contains some code reused and modified from third-party sources. (Stack Overflow)
## features 

### Task-keeping

Store, list, mark, find and delete 3 different types of tasks.

(e.g. ToDo, Deadline, Event.)

### Fun Facts

Duke (With Less User Respect) can tell you over 400 different, random fun facts!

## Usage

### `bye` - Wishes you goodbye

Prints out a goodbye statement.

Example of usage: 

`bye`

Expected outcome:

A goodbye statement.

```
Goodbye, then!
```

### `deadline` - Creates and stores a deadline task

Creates a deadline-type task to be done by the given 'by' datetime and stores it in the task list.

Example of usage:

`deadline (description) /by (deadline)`

Typing the datetime in the following format `yyyy/MM/dd HH:mm` will change its representation.

e.g. typing `2023/02/13 21:13` into the deadline section will show as `Feb 13 2023, 2113 hrs`

Expected outcome:

Confirmation of the task being added.

```
Task added:
[D][](description)(by: (deadline))
There are now (number of tasks) task(s) in your list.
```

### `delete` - Deletes a task

Deletes task from the user's task list at a given index. (starting from 1)

Example of usage:

`delete (INTEGER)`

Expected outcome:

Confirmation of the task being deleted.

```
Okay, i've deleted the following task!
(task description)
```

### `event` - Creates and stores an event task

Creates an event-type task to be done from the given 'from' datetime but before
the given 'to' datetime, and stores it in the task list.

Example of usage:

`event (description) /from (from) /to (to)`

Typing the datetime in the following format `yyyy/MM/dd HH:mm` will change its representation.

e.g. typing `2023/02/13 21:13` into the from / to section will show as `Feb 13 2023, 2113 hrs`

Expected outcome:

Confirmation of the task being added.

```
Task added:
[E][](description)(from: (from) to: (to))
There are now (number of tasks) task(s) in your list.
```

### `fact` - Gives user a random fun fact

Prints out a random fun fact.

Example of usage:

`fact`

Expected outcome:

A random fun fact.

```
[DID YOU KNOW?]
(random fun fact)
```

### `find` - Finds one or more tasks using a query

Searches the user's task list for a given query, and lists out all those that match.

Example of usage:

`find (query)`

Expected outcome:

List of tasks that contain the query.

```
Here are the tasks that matched your query:
1. (task that contains query)
...
```

### `list` - Lists out all current tasks

Lists out all the user's tasks in the task list, if any.

Example of usage:

`list`

Expected outcome:

List of all tasks in the task list.

```
Here are your tasks:
1. (task)
...
```

### `mark` - Marks a task as done

Marks a task from the user's task list at a given index (starting from 1) as done.

Example of usage:

`mark (INTEGER)`

Expected outcome:

Confirmation of the task being marked as done.

```
Okay, the following task has been marked as done!
(task description)
```

### `todo` - Creates and stores a deadline task

Creates a ToDo-type task to be done and stores it in the task list.

Example of usage:

`todo (description)`

Expected outcome:

Confirmation of the task being added.

```
Task added:
[T][](description)
There are now (number of tasks) task(s) in your list.
```

### `unmark` - Marks a task as not done

Marks a task from the user's task list at a given index (starting from 1) as not done.

Example of usage:

`unmark (INTEGER)`

Expected outcome:

Confirmation of the task being marked as not done.

```
Okay, the following task has been marked as NOT done!
(task description)
```