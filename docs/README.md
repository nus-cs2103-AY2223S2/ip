# User Guide

## Features 

### Fuss-free way to store your tasks!

You can use this app to store a list of your tasks. 
Once you are done with a task, either mark it as done, 
or delete it from storage!

### Task Priority

Some tasks are more important or urgent than others?
Simply tell us which tasks are higher in priority and we'll
remember it for you! You can also sort your tasks based on
priority level.

### Finding Tasks

Too many tasks and finding it hard to filter to what you 
want? You can use our search algorithm to find tasks
with a specific keyword you are looking for, and filter from
the hundreds of tasks you may have added!

## Usage

### `list` - List out all your tasks

Example of usage: 

`list`

Expected outcome:

A numbered overview of all your tasks.

```
You said: list

Attempting to print out your tasks...
1.[T][] [Priority Level: LOW] Do Math homework
2.[D][X] [Priority Level: HIGH] Submit essay (by: Apr 21 2023)
3.[E][] [Priority Level: MEDIUM] Eat (from: May 22 2013 5pm to: May 22 2013 6.15pm)
```
### `find X` - List out all your tasks containing the keyword X

Example of usage:

`find essay`

Expected outcome:

All tasks containing the keyword.   

```
You said: find essay    

Finding in progress...
Found 1 task with this keyword
1.[D][X] [Priority Level: HIGH] Submit essay (by: Apr 21 2023)
```

### `sort` - Sort your tasks based on priority!

Example of usage:

`sort`

Expected outcome:

A sorted overview of your tasks based on priority.

```
You said: sort

Sorting tasks according to highest priority level to lowest...
[D][X] [Priority Level: HIGH] Submit essay (by: Apr 21 2023)
[E][] [Priority Level: MEDIUM] Eat (from: May 22 2013 5pm to: May 22 2013 6.15pm)
[T][] [Priority Level: LOW] Do Math homework
```
### `mark X` or `unmark X` - Mark/Unmark tasks as done or incomplete respectively

Example of usage:

`mark 1` or `unmark 1`

Expected outcome:

Respectively mark or unmark a specific task numbered X

```
You said: mark 1

Marking task in progress...
Ok, I've marked this Task as completed:
[T][X] [Priority Level: LOW] Do Math homework
```

### `todo` or `deadline` or `event` - Add tasks to your list!

Example of usage:

`todo Eat` or `deadline submit essay /by 2021-03-21` 
or `event Go school /from now /to 6pm later`

Expected outcome:

Your task being added to the list while showing the total number of tasks currently.

```
You said: todo Eat

Adding new task in progress...
Got it. I have added: [T][] [Priority Level: LOW] Eat
Now you have 4 tasks in the list
```