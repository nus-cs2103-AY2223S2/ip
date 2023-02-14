# PandoraBot User Guide

## Features 

### Basic features

- `todo`, `deadline` & `deadline` to add your desired task

- `delete` together with the task number removes the task you want to get rid of

- `mark` & `unmark` to mark and unmark tasks that you have completed

- `list` displaya all existing tasks

### Special featues

- `sort` - sorts the existing task in alphabetical order.

- `find` - finds the tasks that contains the given keyword.

- `help` - displays the format of basic features.

## Commands & Format

>### `todo` - Task reminder without deadlines

_Takes down task in a to-do-list form._

Example of usage: 

`todo borrow book`

Description of the outcome.

`[T][ ] borrow book`

>### `deadline` - Task reminder with deadlines

_Takes down task together with a deadline date._

Example of usage:

`deadline return book /by 2020-12-31`

Description of the outcome.

`[D][ ] return book (BY: 31 Dec 2020)`

>### `event` - Task with start & end date

_Takes down task that spans a duration._

Example of usage:

`event outdoor camp /from 2022-11-20 /to 2022-11-23`

Description of the outcome.

`[E][ ] outdoor camp (FROM: 20 Nov 2022 TO: 23 Nov 2022)`

>### `mark` & `unmark` - Marks or unmarks the specified task with an 'X'

_Helps you keep track of any completed or uncompleted task._

Example of usage:

`mark 1`

`unmark 1`

Description of the outcome.

`[T][X] borrow book`

`[T][ ] borrow book`

>### `delete` - Removes the specified task

_given the task number, deletes that specified task_

Example of usage:

`delete 1`

Before:

`1.[T][ ] borrow book`

`2.[D][ ] return book (BY: 31 Dec 2020)`

`3.[E][ ] outdoor camp (FROM: 20 Nov 2022 TO: 23 Nov 2022)`

After:

`1)[D][ ] return book (BY: 31 Dec 2020)`

`2)[E][ ] outdoor camp (FROM: 20 Nov 2022 TO: 23 Nov 2022)`

>### `sort` - Arrange tasks in alphabetical order

_sorts based on task description in alphabetical order_

Example of usage:

`sort`

Before:

`1.[T][ ] borrow book`

`2.[D][ ] return book (BY: 31 Dec 2020)`

`3.[E][ ] outdoor camp (FROM: 20 Nov 2022 TO: 23 Nov 2022)`

After:

`1.[T][ ] borrow book`

`2.[E][ ] outdoor camp (FROM: 20 Nov 2022 TO: 23 Nov 2022)`

`3.[D][ ] return book (BY: 31 Dec 2020)`

### Features not explained above can be tried out without any alteration to the existing list.
### So go on and try it out for yourself!
