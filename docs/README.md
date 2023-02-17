# User Guide

## Features 

### Feature - Manage your tasks

The Duke chatbot is able to keep track of all of the tasks in your task list and mark them as done or undone.

You can add or delete tasks as you require.


### Feature - Postpone deadlines

You are able to change the dates of your deadlines should you need to postpone any.

You do not need to delete an old deadline and add a new one to do so.

## Usage

### `Bye` - Terminates session

Terminates the current Duke chatbot session.

Example of usage: 

`bye`

Expected outcome:

Returns the following goodbye message and closes the application.

```
Goodbye friend. Hope to see you again soon!
```

### `list` - Show task list
Displays the current list of tasks.

Example of usage:

`list`

Expected outcome:

Returns the current list of tasks in the following format:
```
Here are the tasks in your list:
1.[T][ ] task 1
2.[D][ ] task 2 (by: 2022-01-01)
3.[E][X] task 3 (at: 2022-02-02)

```
### `delete` - Remove task
Removes a task from the task list.

Example of usage:

`delete 3`

Expected outcome:

Removes task 3 from the task list and returns the following message:

```
Noted. I've removed this task:
[E][X] task 3 (at: 2022-02-02)
Now you have 2 tasks in the list.
```
### `mark` - Mark task as done
Marks a task in the task list as done.

Example of usage:

`mark 1`

Expected outcome:

Marks task 1 as done and returns the following message:

```
Nice! I've marked this task as done:
[T][X] task 1
```
### `unmark` - Mark task as not done
Marks a task in the task list as not done.

Example of usage:

`unmark 1`

Expected outcome:

Marks task 1 as not done and returns the following message:

```Alright! I've marked this task as not done:
[T][ ] task 1
```
### `find` - Find tasks
Finds tasks in the task list that contain a given keyword.

Example of usage:

`find <keyword>`

Expected outcome:

Returns a list of tasks that contain the keyword in the following format:

```
Here are the matching tasks in your list:
1.[T][ ] task containing keyword
2.[D][ ] task 2 containing keyword (by: 2022-01-01)
```
### `todo` - Add todo task
Adds a todo task to the task list.

Example of usage:

`todo <description>`

Expected outcome:

Adds a todo task to the task list with the given description and returns the following message:

```
Got it. I've added this task:
[T][ ] <description>
Now you have n tasks in the list.
```
### `deadline` - Add deadline task
Adds a deadline task to the task list.

Example of usage:

`deadline <description> /by <date>`

Expected outcome:

Adds a deadline task to the task list with the given description and deadline date and returns the following message:

```
Got it. I've added this task:
[D][ ] <description> (by: <date>)
Now you have n tasks in the list.
```
### `event` - Add event task
Adds an event task to the task list.

Example of usage:

`event <description> /from <start> /to <end>`

Expected outcome:

Adds an event task to the task list with the given description and event date and returns the following message:

```
Got it. I've added this task:
[E][ ] <description> (from: <start> to: <end>)
Now you have n tasks in the list.
```

### `Postpone` - Postpone a task
Postpones a task to a new date and time. The task can be a deadline or an event.

Example of usage:

`postpone <description> /to <new date>`

Returns the following message upon successful postponement of the task.
```
Deadline has been changed!
```
