# User Guide

Fake Duke is a GUI-based chat bot that allows you to manage your tasks easily on a single platform!

## Features 

### Manage Tasks Easily

You can manage your tasks easily using the actions listed below.
Moreover, you can also find specific tasks easily from a large pool of tasks.
Fake Duke also helps to sort deadlines and events!

### Use of Local Storage

Fake Duke is able to retrieve tasks from your local storage (`/data/tasks.txt`) when you execute it.
Any actions performed on the tasks will be stored locally.

## Usage

All the actions are non case-sensitive. For example, you can enter `lISt` and it will be interpreted as `list`.
Datetimes specified should be in the future and should follow this format: `YYYY-MM-DD HH:mm`.

### `todo` - Adds a Todo task

Adds a Todo task to the list.

Format: 

`todo {description}`

Example of usage:

`todo Read 1 chapter of a book`

Expected outcome:

Adds a Todo task to the list. Fake Duke replies with a confirmation message.

```
Got it. I've added this task:

[T][ ] Read 1 chapter of a book

Now you have 1 task in the list.
```

### `deadline` - Adds a Deadline

Adds a Deadline to the list.

Format:

`deadline {description} /by {YYYY-MM-DD HH:mm}`

Example of usage:

`deadline CS2103 IP /by 2023-02-17 23:59`

Expected outcome:

Adds a Deadline to the list. Fake Duke replies with a confirmation message.

```
Got it. I've added this task:

[D][ ] CS2103 IP
(by: Fri 17-02-2023 23:59PM)

Now you have 2 tasks in the list.
```

### `event` - Adds an Event

Adds an Event to the list. The end datetime specified should be after the start datetime.

Format:

`event {description} /from {YYYY-MM-DD HH:mm} /to {YYYY-MM-DD HH:mm}`

Example of usage:

`event CS2103 Finals /from 2023-04-26 09:00 /to 2023-04-26 11:00`

Expected outcome:

Adds an Event to the list. Fake Duke replies with a confirmation message.

```
Got it. I've added this task:

[E][ ] CS2103 Finals
(from: Wed 26-04-2023 09:00AM
to: Wed 26-04-2023 11:00AM)

Now you have 3 tasks in the list.
```

### `list` - Lists all tasks

Lists all the tasks, including those stored locally and those added by you.
Fake Duke will also indicate if the tasks are expired, or going to expire within a week.

Example of usage: 

`list`

Expected outcome:

All the tasks will be listed on the GUI by Fake Duke.

```
Here are the tasks in your list:
------------------------------------------
1. [T][ ] Read 1 chapter of a book
------------------------------------------
2. [D][ ] CS2103 IP
(by: Fri 17-02-2023 23:59PM)
------------------------------------------
3. [E][ ] CS2103 Finals
(from: Wed 26-04-2023 09:00AM
to: Wed 26-04-2023 11:00AM)
```

### `mark` - Marks one or more tasks

Marks all the tasks whose indexes are specified by you.

Format:

`mark {taskIndex(es)}`

Example of usage:

`mark 1 2`

Expected outcome:

Tasks 1 and 2 will be marked as done. Fake Duke will respond with the actions it has taken.

```
Nice! I've marked:
------------------------------------------
[T][X] Read 1 chapter of a book
------------------------------------------
[D][X] CS2103 IP
(by: Fri 17-02-2023 23:59PM)
```

### `unmark` - Unmarks one or more tasks

Unmarks all the tasks whose indexes are specified by you.

Format:

`unmark {taskIndex(es)}`

Example of usage:

`unmark 1 2`

Expected outcome:

Tasks 1 and 2 will be marked as undone. Fake Duke will respond with the actions it has taken.

```
OK, I've unmarked:
------------------------------------------
[T][ ] Read 1 chapter of a book
------------------------------------------
[D][ ] CS2103 IP
(by: Fri 17-02-2023 23:59PM)
```

### `delete` - Deletes one or more tasks

Deletes all the tasks whose indexes are specified by you.

Format:

`delete {taskIndex(es)}`

Example of usage:

`delete 1 2`

Expected outcome:

Tasks 1 and 2 will be deleted from the list of tasks. Fake Duke will respond with the actions it has taken.

```
Noted. I've removed:
------------------------------------------
[T][ ] Read 1 chapter of a book 
------------------------------------------
[D][ ] CS2103 IP 
(by: Fri 17-02-2023 23:59PM)

Now you have 1 task in the list. 
```

### `find` - Finds tasks based on keyword

Returns a list of tasks that match the specified keyword.

Format:

`find {keyword}`

Example of usage:

`find CS`

Expected outcome:

Tasks with descriptions containing 'CS' will be replied by Fake Duke.

```
Here are the matching tasks in your list:
------------------------------------------
1. [D][ ] CS2103 IP 
(by: Fri 17-02-2023 23:59PM)
------------------------------------------
2. [E][ ] CS2103 Finals 
(from: Wed 26-04-2023 09:OOAM 
to: Wed 26-04-2023 11:00AM) 
```

### `sort` - Sorts all the deadlines or events based on date time

Returns either a list of sorted deadlines or events in ascending date time order, 
depending on your input.

Format:

`sort {d/e}`

Example of usage:

`sort d`

Expected outcome:

A list of sorted deadlines will be replied by Fake Duke.

```
Here are the matching tasks in your list:
------------------------------------------
1. [D][ ] CS2103 IP 
(by: Fri 17-02-2023 23:59PM)
------------------------------------------
2. [D][ ] submit proposal 
(by: Sat 17-02-2024 23:59PM) 
```

### `bye` - Exiting from Fake Duke

Exits from Fake Duke app upon your `bye` command.

Example of usage:

`bye`

Expected outcome:

Fake Duke application will exit on its own.
