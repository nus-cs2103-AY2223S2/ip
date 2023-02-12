# Welcome to Duke!

Welcome to Duke, a bot you can use to manage your tasks conveniently! If you are a fast typer, you could find Duke especially helpful as all operations can be done by typing.

## Commands

| Command | Description |
|--|--|
| `bye` | Exits the app |
| `todo`| Creates a new task to be done
| `deadline` | Creates a new task to be completed by a given date
| `event` | Creates a new event with a defined start and end date
| `list` | Lists all tasks
| `delete` | Deletes a task
| `mark` | Marks task as completed
| `unmark` | Unmarks completed task
| `find` | Searches for a task

## Quitting the app

Other than clicking the red cross button on the top right hand corner, the user can gracefully exit the app with the `bye` command.

## Adding a task
A ToDo task without a deadline or time attached to it can be added using the syntax `todo {description}`.

Usage: `todo Bake muffins`

Entry in Duke: `[T][ ] Bake muffins`

## Adding an deadline
An task with a deadline attached to it can be added with the syntax `deadline {description} /by {deadline}`.

The dealine has to be specified in YYYY-MM-DD format.

Usage: `deadline Return books /by 2020-10-09`

Entry in Duke: `[D][ ] Return books (by: Oct 9 2020)`

## Adding an event

An event with a starting date and ending date can be added to Duke with the syntax `event {description} /from {starting date} /to {ending date}`.

Dates have to be specified in YYYY-MM-DD format.

Usage: `event Malaysia trip /from 2020-10-03 /to 2020-10-30`

Entry in Duke: `[E][ ] Malaysia trip (from: Oct 3 2020 to: Oct 30 2020)`

## List all tasks

All tasks currently saved can be listed using the `list` command. This will also display the number of tasks currently present.

The tasks added in first are displayed at the top of the list.

Usage: `list`

Sample output:
```
1. [E][ ] Malaysia trip (from: Oct 3 2020 to: Oct 30 2020)
2. [D][ ] Return books (by: Oct 9 2020)
```

## Delete a task
Any task can be deleted by index. This command has the syntax `delete {index}`.

Usage: `delete 1`

Sample output (if the tasks are as listed in the section above):
`1. [D][ ] Return books (by: Oct 9 2020)`

## Marking tasks as completed
A task can be marked as completed by its index. The syntax is `mark {index}`.

Usage: `mark 1`

Sample output (if the tasks are as listed in the section above):
`1. [D][X] Return books (by: Oct 9 2020)`

## Unmarking completed tasks

A completed task can be reverted to an incomplete state by its index; this has the syntax `unmark {index}`. 

Sample sage: `unmark 1`

Sample output (if the tasks are as listed in the section above):
`1. [D][ ] Return books (by: Oct 9 2020)`

## Searching for tasks

Tasks matching a query can be displayed upon entering the `find` command. If the search query is a part of the task description, the task will be returned as the search result.

Syntax: `find {substring}`

Example usage: `find Trip to`

Sample Duke state:
```
1. [E][ ] Trip to Malaysia (from: Oct 2 2020 to: Oct 3 2020)
2. [T][ ] Return book 
3. [E][ ] Trip to Thailand (from: Nov 10 2020 to: Nov 15 2020)
```

Sample output: 
```
You have 2 matching tasks
4. [E][ ] Trip to Malaysia (from: Oct 2 2020 to: Oct 3 2020)
5. [E][ ] Trip to Thailand (from: Nov 10 2020 to: Nov 15 2020)
```