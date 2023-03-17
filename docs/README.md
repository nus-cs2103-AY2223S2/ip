# User Guide

## Features

- [List all tasks](#list-all-tasks--list)
- [Mark a task](#mark-a-task--mark-index)
- [Unmark a task](#unmark-a-task--unmark-index)
- [Delete a task](#delete-a-task--delete-index)
- [Find a task](#find-a-task--find-keywords)
- [Remind of tasks happening soon](#remind-of-tasks-happening-soon--remind)
- Add a task
  - [Add a todo item](#add-a-todo-item--todo-description)
  - [Add a deadline item](#add-a-deadline-item--deadline-description-by-deadline-datetime)
  - [Add an event item](#add-an-event-item--event-description-from-start-datetime-to-end-datetime)
- [End the chatbot](#end-the-chatbot--bye)

## Usage 

### List all tasks: `list`
Returns a list of all your current tasks.

Example of usage: `list`

Expected outcome:
```agsl
OK OK Uncle Roger tell you what to do:
1. [T][ ] buy chilli jam
2. [D][ ] cook egg fried rice (by: 01 Feb 2023)
3. [E][ ] annoy Uncle Roger (from: 31 Jan 2023 to: 01 Feb 2023)
```

Description of outcome: The chatbot returns formatted information about the current tasks.

### Mark a task: `mark <index>`
Marks the task at the given index.

Example of usage: `mark 2`

Expected outcome:
```agsl
OK OK Uncle Roger mark for you:
[D][X] cook egg fried rice (by: 01 Feb 2023)
```

Description of outcome: The chatbot marks the task at index 2, and this is notated by an [X].

### Unmark a task: `unmark <index>`
Unmarks the task at the given index.

Example of usage: `unmark 2`

Expected outcome:
```agsl
OK OK Uncle Roger unmark for you:
[D][ ] cook egg fried rice (by: 01 Feb 2023)
```

Description of outcome: The chatbot unmarks the task at index 2, and this is notated by a reversion from [X] to [ ].

### Delete a task: `delete <index>`
Deletes the task at the given index.

Example of usage: `delete 1`

Expected outcome:
```agsl
Haiya so weak deleted:
[T][ ] buy chilli jam
```

Description of outcome: The chatbot deletes the task at index 1, and this task will no longer be in the list.

### Find a task: `find <keywords>`
Filters and returns a list of tasks that match the keywords.

Example of usage: `find rice jam`

Expected outcome:
```agsl
OK OK Uncle Roger tell you what to do:
1. [T][ ] buy chilli jam
2. [D][ ] cook egg fried rice (by: 01 Feb 2023)
```

Description of outcome: The chatbot filters the task list for those which contains the keywords "rice" or "jam".

### Remind of tasks happening soon: `remind`
Filters and returns a list of deadlines and events which are occurring within the next week.

Example of usage: `remind`

Expected outcome:
```agsl
OK OK Uncle Roger tell you what to do:
1. [D][ ] cook egg fried rice (by: 01 Feb 2023)
2. [E][ ] annoy Uncle Roger (from: 31 Jan 2023 to: 01 Feb 2023)
```

Description of outcome: Assuming the date this command was run was 31 Jan 2023, the chatbot returns the tasks which occur within one week of the date. 

### Add a todo item: `todo <description>`
Creates a todo item with the given description.

Example of usage: `todo buy chilli jam`

Expected outcome:
```agsl
OK OK Uncle Roger add for you:
[T][ ] buy chilli jam
```

Description of outcome: The chatbot creates a new todo item with the description "buy chilli jam".

### Add a deadline item: `deadline <description> /by <deadline datetime>`
Creates a deadline item with the given description and end time.

Example of usage: `deadline cook egg fried rice /by 2023-02-01 15:00`

Expected outcome:
```agsl
OK OK Uncle Roger add for you:
[D][ ] cook egg fried rice (by: 01 Feb 2023 @ 15:00)
```

Description of outcome: The chatbot creates a new deadline item with description "cook egg fried rice" and deadline "2023-02-01 15:00" in "YYYY-MM-dd HH:mm" format.

Example of usage: `deadline cook egg fried rice /by 2023-02-01`

Expected outcome:
```agsl
OK OK Uncle Roger add for you:
[D][ ] cook egg fried rice (by: 01 Feb 2023)
```

Description of outcome: It is also acceptable to exclude the time when creating a deadline, as the chatbot will default it to midnight.

### Add an event item: `event <description> /from <start datetime> /to <end datetime>`
Creates an event item with the given description, start and end times.

Example of usage: `event annoy Uncle Roger /from 2023-01-31 12:00 /to 2023-02-01 15:00`

Expected outcome:
```agsl
OK OK Uncle Roger add for you:
[E][ ] annoy Uncle Roger (from: 31 Jan 2023 @ 12:00 to: 01 Feb 2023 @ 15:00)
```

Description of outcome: The chatbot creates a new event item with description "annoy Uncle Roger", start date time "2023-01-31 12:00" and end date time "2023-02-01 15:00" in "YYYY-MM-dd HH:mm" format.

Example of usage: `event annoy Uncle Roger /from 2023-01-31 12:00 /to 2023-02-01`

Expected outcome:
```agsl
OK OK Uncle Roger add for you:
[E][ ] annoy Uncle Roger (from: 31 Jan 2023 @ 12:00 to: 01 Feb 2023)
```

Description of outcome: It is also acceptable to exclude the time when creating a deadline, as the chatbot will default it to midnight.

### End the chatbot: `bye`
Ends the program.

Example of usage: `bye`

Expected outcome:
```agsl
Leave Uncle Roger a good review! PLEASE!
```

Description of outcome: The chatbot greets farewell to the user.
