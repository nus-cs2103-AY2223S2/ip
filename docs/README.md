# User Guide

## Features 

### Add task 

Add a todo, deadline or event. ``` todo, deadline /by, event /from /to ```

### Display tasks

Display all of the tasks you have added. ``` list ```


### Mark/Unmark tasks

Toggle whether a task has been done or not. ``` mark, unmark ```

### Delete tasks

Delete a task from your list. ``` delete ```

### Sort

Display sorted tasks in your list ``` sort ```

### Find

Find specific tasks using keywords ``` find ```

## Usage

### `todo` - adds todo

Example of usage: 

`todo (description of task)`

Expected outcome:

todo is added.

```
added (new todo)
```

### `deadline` - adds deadline

Example of usage: 

`deadline (description of task) /by (dd-MM-yyyy)`

Expected outcome:

deadline is added.

```
added (new deadline)
```

### `event` - adds event

Example of usage:

`event (description of task) /from (dd-MM-yyyy HH:mm) /to (HH:mm)`

Expected outcome:

event is added.

```
added (new event)
```
### `list` - displays all tasks

Example of usage:

`list`

Expected outcome:

list of tasks are displayed.

```
1. T [X] Go home
2. T [ ] Go for a run
```

### `mark` - marks a task as done

Example of usage:

`mark (index)`

Expected outcome:

task is marked as done.

```
Nice! I've marked this task as done: (task)
```

### `unmark` - marks a task as not done

Example of usage:

`unmark (index)`

Expected outcome:

task is marked as not done.

```
OK, I've marked this task as not done yet: (task)
```

### `delete` - deletes a task from the list

Example of usage:

`delete (index)`

Expected outcome:

task is deleted from the list.

```
Noted. I've removed this task (task). 
Now you have (task number) tasks in the list
```

### `sort` - displays sorted list of tasks 

Example of usage:

`sort`

Expected outcome:

sorted list of tasks is displayed.

```
1. T [X] Go home
2. T [ ] Go for a run

1. D [X] Go home (by: 23/02/2023)
```

### `find` - finds task based on keywords

Example of usage:

`find (keyword)`

Expected outcome:

list of tasks containing the keyword is displayed.

```
find work

1. T [X] Finish work
2. T [ ] Work on assignments

1. D [X] Workout (by: 23/02/2023)
```