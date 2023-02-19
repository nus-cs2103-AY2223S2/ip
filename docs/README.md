# User Guide

## Features 

### Add and delete tasks

Add and delete tasks like todo, deadline and event to your task list.

### Marking and unmarking tasks

Mark tasks as done or not done.

### Find

Find tasks with specified keywords from your task list.

### List

View all of your tasks.

### Prioritise tasks

View and assign levels of priority to each task.

### Store tasks

DukeMeister3000 remembers tasks from your previous session.

## Usage

### `todo DESCRIPTION` - Add todo

Adds a todo task to your task list.

Example of usage: 

`todo return book`

Expected outcome:

Adds a todo task with description "return book" to your task list.

```
[T][][LOW] return book
```
<br/>

### `deadline DESCRIPTION /by DATE(DD/MM/YYYY HH:MM)` - Add deadline

Adds a deadline task with deadline date and time to your task list.

Example of usage:

`deadline return book /by 10/02/2023 14:00`

Expected outcome:

Adds a deadline task with description "return book", deadline 10 Feb 2023 2 pm to your task list.

```
[D][][LOW] return book (by: 10 Feb 2023 2 PM)
```
<br/>

### `event DESCRIPTION /from DATE /to DATE` - Add event

Adds a event task to your task list with start and end dates and times.

Example of usage:

`event dinner /from 10/08/2023 19:00 /to 10/08/2023 21:00`

Expected outcome:

Adds an event task with description "dinner", from 7pm to 9pm on 10 Aug 2023 to your task list.

```
[E][][LOW] dinner (from: 10 Aug 2023 7 PM to: 10 Aug 2023 9 PM)
```
<br/>

### `event DESCRIPTION /from DATE /to DATE` - Add event

Adds a event task to your task list with start and end dates and times.

Example of usage:

`event dinner /from 10/08/2023 19:00 /to 10/08/2023 21:00`

Expected outcome:

Adds an event task with description "dinner", from 7pm to 9pm on 10 Aug 2023 to your task list.

```
[E][][LOW] dinner (from: 10 Aug 2023 7 PM to: 10 Aug 2023 9 PM)
```
<br/>

### `list` - View all tasks


Example of usage:

`list`

<br/>

### `mark INDEX` - Mark task as done

Mark task at INDEX as done.

Example of usage:

`mark 1`

Expected outcome:

```
[E][X][LOW] return book
```
<br/>

### `unmark INDEX` - Mark task as not done

Mark task at INDEX as not done.

Example of usage:

`unmark 1`

Expected outcome:

```
[E][][LOW] return book
```
<br/>

### `delete INDEX` - Delete task

Delete task at INDEX.

Example of usage:

`delete 1`
