# User Guide

## Features 

### Add todo

Add a todo task that consists of a description.

### Add deadline

Add a deadline task that consists of a description and a finish-by date.

### Add event

Add an event task that consists of a description, a start date and an end date.

### View task list

See all the tasks you have added so far.

### Delete task

Delete a task in your list by index number.

### Mark finish/not finished

Mark a task in your list by index number as finished or not finished.


## Usage


### `todo DESCRIPTION`

Add a todo task to the list.

Example of usage: 

`todo buy groceries`

Expected outcome:

Add a todo task called "buy groceries" to the list.

```
[T][] buy groceries
```


### `deadline DESCRIPTION /by DATE(DD/MM/YYYY hh:mm)`

Add a deadline task to the list with the corresponding deadline date.

Example of usage: 

`deadline return book /by 30/08/2023 8:00`

Expected outcome:

Add a deadline task called "return book" with a finish-by date of Aug 30 2023 8:00 to the list.

```
[D][] return book (by: Aug 30 2023 08:00)
```


### `event DESCRIPTION /from DATE(DD/MM/YYYY hh:mm) /to DATE(DD/MM/YYYY hh:mm)`

Add an event task to the list with the given start and end dates.

Example of usage: 

`event surprise holiday /from 08/05/2023 0:00 /to 15/05/2023 0:00`

Expected outcome:

Add an event task called "surprise holiday" with a start date of May 8 2023 0:00 and an end date of May 15 2023 0:00 to the list.

```
[E][] surprise holiday (from: May 8 2023 00:00 to: May 15 2023 00:00)
```


### `list`

View a list of all added tasks.

Example of usage: 

`list'


### `delete INDEX`

Delete a task from the list based on its index number.

Example of usage: 

`delete 2`


### `mark INDEX`

Mark a task from the list as finished based on its index number.

Example of usage: 

`mark 3`


### `unmark INDEX`

Mark a task from the list as NOT finished based on its index number.

Example of usage: 

`unmark 1`
