# User Guide

Duke is a task manager with an interface that has two Pokémon talking each other, namely Bounsweet and Mimikyu.
It also has the ability to track expenses as well.

## Features 

### `list`

Prints a list of all your tasks.

### `mark <index>`

Marks a task at the index as done.

### `unmark <index>`

Marks a task at the index as undone.

### `todo <task description>`

Adds a ToDo task.

### `deadline <task description> /by <date> <time>`

Adds a deadline task with its deadline and time.

### `event <task description> /from <date> <time> /to <date>`

Adds an Event Task with its duration date and time.

### `delete <index>` or `remove <index>`

Removes a task at given index as shown in the list.

### `find <keyword>`

Prints a list of tasks that contain the given keyword in its description.

### `e list`

Prints the list of expenses.

### `e total`

Prints the total amount spent on expenses.

### `e delete <index>` or `e remove <index>`

Removes the expense in the given index in the expense list.

### `e food /<description> /<amount spent>`

Adds a FoodExpense object with its description and amount spent.

### `e misc <descriptopn> /<amount spent>`

Adds a MiscExpense object with its description and amount spent.

### `e transport <description> /<amount spent>`

Adds a TransportExpense object with its description and amount spent.

### `bye`

Exits the application

## Usage

### `list` - Lists all task

It displays all the tasks that are currently in the list.

Example of usage: 

`list`

Expected outcome:

Lists out all the tasks that are currently in the list.

```
    OK, Here are the items in your list:
    1. [T][] Buy an orange
    2. [T][] Buy an apple
    3. [T][X] Study for test
```

### `mark <index>` - Mark task as done

It marks the task of given index as done.

Example of usage:

`mark 1`

Expected outcome:

Marks task in the first position of the list as done.

```
    OK I've marked this task as done:
    [T][X] Buy an orange
```

### `unmark <index>` - Mark task as undone

It marks the task of given index as undone.

Example of usage:

`unmark 2`

Expected outcome:

Marks task in the second position of the list as undone

```
    OK I've marked this task as not done yet:
    [T][] Buy an apple
```

### `todo <task>` - Adds ToDo

Adds a ToDo task to the task list.

Example of usage:

`todo Catch Pikachu`

Expected outcome:

Adds a ToDo task of given description, and tells the user how many task he has.

```
    Bounsweet says:
    Added
    [T][] Catch Pikachu
    Now you have 4 task(s) in the list.
```

### `deadline <task> /by <date> <time>` - Adds Deadline

Adds a Deadline task to the task list

Example of usage:

`deadline Fight Roxanne /by 2023-02-21 2359`

Expected outcome:

Adds a Deadline task to the task list with its due date and time, and tells the user how many tasks he has.

```
    Bounsweet says
    Added
    [D][] Fight Roxanne (by: 2023-02-21 23:59)
    Now you have 5 task(s) in the list.
```

### `event <task> /from <date> <time> /to <date> <time>` - Adds Event

Adds a Event task to the task list.

Example of usage:

`deadline Fight Brawly /from 2023-03-01 2359 /to 2023-03-02 2359`

Expected outcome:

Adds an Event Task to the task list with its duration, and tells the user how many task he has.

```
    Bounsweet says:
    Added
    [E][] Fight Brawly (from: 2023-03-01 23:59 to: 2023-03-02 2359)
    Now you have 6 task(s) in the list.
```

### `remove <index>` or `delete <index>` - Deletes task

Deletes a task at given index.

Example of usage:

`remove 1`

Expected outcome:

Removes the first task in the list, and tell the users the number of tasks he has.

```
    Bounsweet says:
    Noted. I have removed this task:
    [T][X] Buy an orange
    Now you have 5 task(s) in the list.
```

### `find <keyword>` - Queries list of tasks

Searches the list for tasks whose description includes given string.

Example of usage:

`find buy`

Expected outcome:

Prints a list that has tasks that contain the word buy in its description.

```
    Bounsweet says:
    Here are the matching tasks in your list:
    1. [T][] Buy an orange
    2. [T][] Buy an apple
```

### `e list` - List all expenses

Prints list of all expenses logged.

Example of usage:

`e list`

Expected outcome:

Prints list of all logged expenses.

```
    Bounsweet says:
    OK here's the expenses in your list:
    1. Misc: Lunch $30.0
    2. MiscL Dinner $30.0
```

### `e total` - Gives the total spent

Prints the total cost of all expenses.

Example of usage:

`e total`

Expected outcome:

Prints total cost of all expenses.

```
    Bounsweet says:
    OK Here's the total amount of expenses in your list:
    $60.0
```

### `e delete <index>` or `e remove <index>` - Remove expense

Deletes the expense at the given index.

Example of usage:

`e remove 1`

Expected outcome:

Deletes the first expense in the expense list.

```
    Bounsweet says:
    Noted. I have removed this expense:
    Misc: Lunch $30.0
```

### `e food <description> /<amount spent>` - Add FoodExpense

Adds a FoodExpense with given description and amount spent to the list

Example of usage:

`e food Breakfast /10`

Expected outcome:

Adds a FoodExpense to the expense list.

```
    Bounsweet says:
    Added
    Food: Breakfast $10.0
```

### `e misc <description> /<amount spent>` - Add MiscExpense

Adds a MiscExpense with given description and amount spent to the list.

Example of usage:

`e misc Bought Pen /1`

Expected outcome:

Adds a MiscExpense to the expense list.

```
    Bounsweet says:
    Added
    Misc: Bought Pen $1.0
```

### `e transport <description> /<amount spent>` - Add TransportExpense

Adds a TransportExpense with given description and amount spent to the list.

Example of usage:

`e transport Grabbed to NUS /40`

Expected outcome:

Adds a TransportExpense to the list

```
    Bounsweet says:
    Added
    Transport: Grabbed to NUS $40.0
```

### `bye` - Quit program

Quits the program

Example of usage:

`bye`

Expected outcome:

Quits the program after saving

```

```