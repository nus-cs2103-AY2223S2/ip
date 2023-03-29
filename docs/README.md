# User Guide
Welcome to Jamie, your ultimate command-line with a GUI task management tool! Jamie is a simple yet powerful application designed to help you easily manage your daily tasks, events, and deadlines. With Jamie, you can quickly record, track, and most of all, stay on top of your schedule.

This user guide will walk you through all the features of Jamie, so you can get the most out of this tool. By the end of this guide, you will be able to use Jamie to manage your tasks, events, and deadlines with ease. So, let's get started!

## Features 
Jamie features a user-friendly interface that allows you to access all its functionalities by typing commands. You can add, edit, delete, and mark your tasks as you complete them.

**Notes about the command format:**
- All `commands` must be in lower case.
- The date time format is as such, `dd/mm/yyyy HHmm`.
- For `event` type commands, ensure that the dates are in correct order.
- Jamie will remind users of the correct format if the input is incorrect. Follow the instructions and you will be fine.


### Add a - `todo`

Adds a todo to your list.

### Add a - `deadline`

Adds a deadline to your list.

### Add a - `event`

Adds a event to your list.

### Display everything - `list`

Print out everything in your list in chronological order.

### Remove task - `delete`

Removes a task at a particular index.

### Mark - `mark`

Modifies the state of a task at a particular index to be marked.

### Un-mark - `unmark`

Modifies the state of a task at a particular index to be un-marked.

### Search for description - `find`

Looks for exact matches and similar matches in your list when given a description of a task and prints out the result of the matches.

### Logout - `bye`

Terminates the programme.

## Usage

### `todo` - adds a todo into list

Adds a todo to your list and returns a message if it has been added successfully.

Example of usage: 

`todo water plants`

Expected outcome:

A message will be printed.

```
Got it. I've added this task:
[T][] water plants
Now we have 1 task(s) in the list.
```

### `deadline` - adds a deadline into list

Adds a deadline to your list and returns a message if it has been added successfully.

Example of usage: 

`deadline water plants /by 02/02/2023 2359`

Expected outcome:

A message will be printed.

```
Got it. I've added this task:
[D][] water plants (by: 2 February 2023, 2359H)
Now we have 2 task(s) in the list.
```

### `event` - adds a event into list

Adds a event to your list and returns a message if it has been added successfully.

Example of usage: 

`event shopee sale /from 01/02/2023 2359 /to 02/02/2023 2359`

Expected outcome:

A message will be printed.

```
Got it. I've added this task:
[E][] shopee sale (from: 1 February 2023, 2359H to: 2 February 2023, 2359H)
Now we have 3 task(s) in the list.
```

### `list` - prints all tasks in list

Prints current state of all tasks.

Example of usage: 

`list`

Expected outcome:

Prints a number list of tasks in chronological order.

```
Here are the task(s) in your list:
1. [T][] water plants
2. [D][] submit documents (by: 2 February 2023, 2359H)
3. [E][] shopee sale (from: 1 February 2023, 2359H to: 2 February 2023, 2359H)
```

### `delete` - removes a task in list

Removes task in list.

Example of usage: 

`delete 1`

Expected outcome:

Prints the task that is being deleted.

```
Noted. I've removed this task:
[T][] water plants
Now we have 2 task(s) in the list.
```

### `mark` - mark a task in list as completed

Marks a task at as completed with a [X].

Example of usage: 

`mark 1`

Expected outcome:

Prints the task that is being marked.

```
Nice! I've marked this task as done:
[T][] water plants
```

### `unmark` - un-mark a task in list as incomplete

Reverts the task completion status from [X] to [], else it does nothing.

Example of usage: 

`unmark 1`

Expected outcome:

Prints the task that is being un-marked.

```
OK, I've marked this task as not done yet:
[T][X] water plants
```

### `find` - search for the task by description

Given a description, the matching task will be displayed in chronological ordering.

Example of usage: 

`find water`

Expected outcome:

Prints the task if there exists (1) exact matches, (2) substring pattern matches or (3) similar matches based on levenshtein distance.

```
Here are the task(s) matched in your list:
1. [T][] water plants
2. [T][] water
```

### `bye` - terminate application

Prints a message to simulate termination of application.

Example of usage: 

`bye`

Expected outcome:

Prints a goodbye message.

```
Thank you and goodbye.
```
