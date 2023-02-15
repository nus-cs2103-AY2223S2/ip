# Genie User Guide

## Features 

### Task tracking

Conveniently store 3 types of tasks (Todo, Deadline, Event), and update
them as you go.

## Usage

### `todo` - Creates a Todo

Creates and stores a Todo task with the specified description.

Example of usage: 

`todo buy groceries`

Expected outcome:

A remark of the Todo task added to the list.

```
Got it. I've added this task:
  [T][ ] buy groceries
Now you have (total #) tasks in the list.
```
### `deadline` - Creates a Deadline

Creates and stores a Deadline task with the specified description and deadline timing. Parses the timing format from (YYYY-MM-DD HH:MM) to
(DAY, DD/MM/YYYY, HH:MM AM/PM).

Example of usage:

`deadline assignment /by 2023-03-04 23:59`

Expected outcome:

A remark of the Deadline task added to the list.

```
Got it. I've added this task:
  [D][ ] assignment (by: Sat, 04/03/2023, 23:59 PM)
Now you have (total #) tasks in the list.
```
### `event` - Creates an Event

Creates and stores an Event task with the specified description and event timing.

Example of usage:

`event team meeting /from Tuesday 4pm /to 6pm`

Expected outcome:

A remark of the Event task added to the list.

```
Got it. I've added this task:
  [E][ ] team meeting (from: Tuesday 4pm to: 6pm)
Now you have (total #) tasks in the list.
```
### `delete` - Deletes a task

Deletes the task corresponding to the specified index on the list.

Example of usage:

`delete 3`

Expected outcome:

A remark of the task deleted from the list.

```
Noted. I've removed this task:
  [T][ ] buy books
Now you have (total #) tasks in the list.
```
### `mark` - Marks a task

Marks the task corresponding to the specified index on the list as
done.

Example of usage:

`mark 1`

Expected outcome:

A remark of the marked task on the list.

```
Nice! I've marked this task as done:
  [T][X] buy groceries
```
### `unmark` - Unmarks a task

Marks the task corresponding to the specified index on the list as
not done.

Example of usage:

`unmark 1`

Expected outcome:

A remark of the unmarked task on the list.

```
Okay, I've marked this task as not done yet:
  [T][ ] buy groceries
```
### `list` - Shows task list

Shows a consolidated list of tasks from the user.

Example of usage:

`list`

Expected outcome:

The consolidated task list.

```
Here are the tasks in your list:
  [T][X] buy groceries
  [E][ ] birthday party (from: 6pm to: 8pm)
  [D][ ] assignment (by: Mon, 04/03/2023, 23:59 PM)
```
### `find` - finds a task

Finds the tasks that matches the specified keyword from the user.

Example of usage:

`find buy`

Expected outcome:

A consolidated list of tasks that matches the specified keyword.

```
Here are the matching tasks in your list:
  [T][X] buy books
  [T][ ] buy groceries
  [T][ ] buy laptop
```
### `help` - Shows help message

Shows a list of commands and its correct format usage. Provides a
brief explanation of its usage.

Example of usage:

`help`

Expected outcome:

A remark of the marked task on the list.

```
No problem! Here is a list of commands I can recognise:
• todo <task>
• event <task> /from <time> /to <time>
• deadline <task> /by <time>
    - <time> (optional): YYYY-MM-DD HH:MM

• delete <list number>
    - deletes that task on the list
• mark <list number>
    - marks that task as done
• unmark <list number>
    - marks that task as undone
• list
    - shows your consolidated task list
• find <keyword>
    - finds all tasks with the specified keyword
• help
    - shows help page
• bye
    - saves your task list and terminates the application
```
### `bye` - Saves and terminates application

Saves current task list to a .txt file.

Example of usage:

`bye`

Expected outcome:

Show a farewell message.

```
Bye! Hope to see you again soon!
```