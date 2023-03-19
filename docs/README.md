# User Guide
Beginner's guide to use Duke Bot
## Features
Duke Bot comes with a number of features that can help you
manage daily tasks efficiently.

### Feature-Mark

Mark the task that you have done

### Feature-List

Show all the task in your Task List

### Feature-Update

Modify the previous task you have input

### Feature-Find

Quickly search the tasks that match with your
key word

## Usage
All Duke bot command should be written in lower-case.
Date format is strictly yyyy-mm-dd.

## List of Commands
### `todo` - Create a To Do task

Example of usage:

`todo buy groceries`

Expected outcome:
```
Got it. I've added this task:
[T][ ] Buy Groceries
```
### `deadline` - Create a Deadline task
Before typing the date should be followed with '/by'
Example of usage:

`deadline assignment /by 2021-12-12`

Expected outcome:
```
	Got it. I've added this task:
	[D][ ] assignment (by 12 Dec 2021)
	Now you have 1 tasks in the list
```

### `event` - Create a Event task
Before typing the from and to dates should be followed with '/from' and '/to'
Example of usage:

`event new year /from 2021-12-30 /to 2022-01-01`

Expected outcome:
```
	Got it. I've added this task:
	[E][ ] new year  (from 30 Dec 2021 to 01 Jan 2022)
	Now you have 1 tasks in the list
```

### 'mark' - Mark the selected task
Example of usage:

`event new year /from 2021-12-30 /to 2022-01-01`
`mark 1`

Expected outcome:
```
	Got it. I've added this task:
	[E][ ] new year  (from 30 Dec 2021 to 01 Jan 2022)
	Now you have 1 tasks in the list
  
  OK, I've marked this task as done:
	[E][X] new year  (from 30 Dec 2021 to 01 Jan 2022)
```

### 'unmark' - Un-mark the selected task
Example of usage:

`event new year /from 2021-12-30 /to 2022-01-01`
`mark 1`
`unmark 1`

Expected outcome:
```
	Got it. I've added this task:
	[E][ ] new year  (from 30 Dec 2021 to 01 Jan 2022)
	Now you have 1 tasks in the list
  
  OK, I've marked this task as done:
	[E][X] new year  (from 30 Dec 2021 to 01 Jan 2022)
  
  Nice! I've unmarked this task as not done yet:
	[E][ ] new year  (from 30 Dec 2021 to 01 Jan 2022)
```

### 'list' - Prints all the task in your bucket
Example of usage:

`event new year /from 2021-12-30 /to 2022-01-01`
`deadline assignment /by 2021-12-12`
`todo buy groceries`
`list`

Expected outcome:
```
  1. [E][ ] new year  (from 30 Dec 2021 to 01 Jan 2022)
	2. [D][ ] assignment (by 12 Dec 2021)
	3. [T][ ] buy groceries
```

### 'find' - Prints all the task in your bucket that matched with the key word
Example of usage:

`event new year /from 2021-12-30 /to 2022-01-01`
`deadline assignment /by 2021-12-12`
`todo buy groceries`
`find groceries`

Expected outcome:
```
  1. [T][ ] buy groceries
```

### 'update' - Update the selected task in the list
Example of usage:

`event new year /from 2021-12-30 /to 2022-01-01`
`deadline assignment /by 2021-12-12`
`todo buy groceries`
`update 1 todo play soccer`
`list`

Expected outcome:
```
  1. [T][ ] play soccer
	2. [D][ ] assignment (by 12 Dec 2021)
	3. [T][ ] buy groceries
```