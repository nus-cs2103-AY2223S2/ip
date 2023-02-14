# Duke User Guide
Duke is a personal, lightweight and portable task manager that helps you manage your daily tasks.

![Duke UI](Ui.png)

## Features 

- Adding 3 types of tasks - Todos, Events, Deadlines
- Listing all tasks
- Marking/un-marking tasks as done or undone
- Finding tasks based on a keyword
- Finding the nearest free day (from Events)

## Usage of Commands

### Add a Todo: 
`todo [name]`

Example: `todo FooBar`

Outcome:
```
Added To-do: [T][ ] FooBar
You now have 1 task(s) in your list
```

### Add a Event:
`event [name] /from [dd/mm/yyyy hhmm] /to [dd/mm/yyyy hhmm]`

Example: `event Career Fest /from 10/02/2023 1000 /to 10/02/2023 1700`

Outcome:
```
Added event: [E][ ] Career Fest  (Starts: 1000, Feb 10 2023, ends: 1700, Feb 10 2023)
You now have 2 task(s) in your list
```

### Add a Deadline:
`deadline [name] /by [dd/mm/yyyy hhmm]`

Example: `deadline PS3 /by 18/02/2023 0000`

Outcome:
```
Added deadline: [D][ ] PS3  (by: 0000, Feb 18 2023)
You now have 3 task(s) in your list
```

### List all tasks:
`list`

Outcome:
```
List of Tasks: 
1. [T][ ] FooBar
2. [E][ ] Career Fest  (Starts: 1000, Feb 10 2023, ends: 1700, Feb 10 2023)
3. [D][ ] PS3  (by: 0000, Feb 18 2023)
```

### Mark/Unmark tasks as done/undone:
`mark [index]` or `unmark [index]`

Example: `mark 1`

Outcome:
```
Good job! I have marked this task as done! 
	[T][X] FooBar
```

Example: `unmark 1`
```
Oof! I have marked this task as undone for you! 
    [T][ ] FooBar
```

### Delete:
Example: `delete 3`

Outcome:
```
Removed task: [D][ ] PS3  (by: 0000, Feb 18 2023)
You now have 2 task(s) in your list.
```

### Find a task based on keywords:
`find [keyword]` (Case sensitive)

Example: `find Foo`

Outcome:
```
Unable to find any tasks with that description!
```
Example: `find FooBar`

Outcome:
```
Here are the matching tasks in your list: 
List of Tasks: 
1. [T][ ] FooBar
```

### Find the closest free day without any events:
`free`

Example: 
```
free
event hackathon /from 13/02/2023 0000 /to 14/02/2023 1300
free
```
Outcome:
```
You are free as you have no pending events!

Added event: [E][ ] hackathon  (Starts: 0000, Feb 13 2023, ends: 1300, Feb 14 2023)
You now have 3 task(s) in your list

Your closest free day is in 15/02/2023, 2 day(s) from now!
```

### Exit the program:
`bye`

Example:
```
Exit command executed!
I hope you've managed to be productive today. Bye!
```
