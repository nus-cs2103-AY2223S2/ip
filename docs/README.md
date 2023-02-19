# Duke User Guide

## Features 

### Add and track these 3 different types of tasks:
1. Todos - Tasks with a description
2. Deadlines - Tasks with a description and a due date
3. Events - Tasks with a description and start and end date


### List tasks
- View all tasks added and their completion status.

### Mark tasks as completed
- Mark  tasks to indicate completion.

### Mark tasks as incomplete
- Unmark completed tasks.

### Delete tasks
- Delete task from the list.

### Sort Deadlines
- View filtered out Deadlines sorted by due date.

### Find tasks
- Search for tasks with description matching keyword input.


## Usage

### `todo` - Add a todo task to the list

Creates a new todo task with a specified description and adds it to the list. 

Example of usage:

`todo Clean my room`

Expected outcome:

- Todo task would be added to the list and stored across user sessions
- Confirmation message for adding of todo will be shown

```
Got it. I've added this task:
  [T][ ] Clean my room
Now you have 8 tasks in the list.
```

### `deadline` - Add a Deadline task to the list

Creates a new deadline task with a specified description, due date and time and adds it to the list.
Due date should be provided in the `DD/MM/YYYY HH:MM` format.

Example of usage:

`deadline Complete math assignment /by 23/10/2023 23:59`

Expected outcome:

- Deadline task will be added to the list and stored across user sessions
- Confirmation message for adding Deadline will be shown

```
Got it. I've added this task:
  [D][ ] Complete math assignment (by: 23 Oct 2023 23:59)
Now you have 9 tasks in the list.
```

### `event` - Add an Event task to the list

Creates an event task with specified description, start date and time, end date and time.
Add the created event to the list. Start and end date and time should be provided in 
`DD/MM/YYYY HH:MM` format.

Example of usage:

`event Science symposium /from 23/03/2024 08:00 /to 25/03/2024 18:00`

Expected outcome:

- Event task will be added to the list and stored across user sessions
- Confirmation message for adding Event will be shown

```
Got it. I've added this task:
  [E][ ] Science symposium (from: 23 Mar 2024 08:00, to: 25 Mar 2024 18:00 )
Now you have 10 tasks in the list.
```

### `list` - View all added tasks

View all added tasks in the order that they were added. View the task type, completion status, description 
and related date and times. 

Example of usage:

`list`

Expected outcome:

```
1.[T][ ] Clean my room
2.[D][ ] Complete math assignment (by: 23 Oct 2023 23:59)
3.[E][ ] Science symposium (from: 23 Mar 2024 08:00, to: 25 Mar 2024 18:00)
```

### `delete` - Delete task from list

Deletes a task from the list based on its index.

Example of usage:

`delete 1`

Expected outcome:

- Confirmation message for task to be deleted
- Returns the number of remaining tasks in the list

```
Noted. I've removed this task:
  [T][ ] Clean my room
Now you have 2 tasks in the list.
```

### `find` - Search for specific tasks

Search for tasks with a matching description to the keywords provided. 

Example of usage:

`find Science`

Expected outcome:

- Tasks containing the word "Science" in their description are returned 
- Tasks are ordered from earliest added to latest added 

```
Here are the matching tasks in your list
1. [E][ ] Science symposium (from: 23 Mar 2024 08:00, to: 25 Mar 2024 18:00)
2. [T][ ] Science project
```

### `sort deadlines` - Get Deadlines sorted by due date and time

Deadlines are filtered out of the task list and sorted in ascending due date and time. 

Example of usage:

`sort deadlines`

Expected outcome:

- Returns only deadlines 
- Sorted in ascending order according to due date and time

```
[D][ ] Send Letter (by: 20 Feb 2023 12:00)
[D][ ] Complete math assignment (by: 23 Oct 2023 23:59)
```

### `mark` - Mark task as completed

Set a task to be completed. Select a task to be marked based on the index of the task in the list.

Example of usage:

`mark 2`

Expected outcome:

- Confirmation message that the task has been marked
- Task selected is displayed and marked as completed with [X]

```
Nice! I've marked this task as done:
  [E][X] Science symposium (from: 23 Mar 2024 08:00, to: 25 Mar 2024 18:00)
```

### `unmark` - Mark task as incomplete

Set a task to be incomplete. Select a task to be unmarked based on the index of the task in the list.

Example of usage:

`unmark 1`

Expected outcome:

- Confirmation message that the task has been unmarked
- Task selected is displayed and marked as incomplete with [ ]

```
OK, I've marked this task as not done yet:
  [D][ ] Complete math assignment (by: 23 Oct 2023 23:59)
```

