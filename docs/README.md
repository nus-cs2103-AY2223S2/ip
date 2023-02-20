# User Guide

Fideline is a chatbot that records and manages tasks.

## Features 

- Add new tasks (`TODO`, `DEADLINE`, `EVENT`)
- View all existing tasks
- Mark a task as complete
- Find tasks by a keyword
- Delete existing tasks

## Commands

### `todo` - Adding a TODO task

Creates a basic task with only a description

Format: `todo [description]`

Example: 
```
todo mop the floor
```

Expected Output:
```
ok! i've added to your list:
  [T][ ] mop the floor
wow! there is 1 task in the list now! :0
```

> **Feature:** Fideline prevents the user from creating
> duplicate tasks by accident! 
<br />

### `deadline` - Adding a DEADLINE task

Creates a task with a deadline

Format: `deadline [description] /by [deadline]`

Examples:
```
deadline history essay /by next friday
```
```
deadline buy jackson's present /by 16/01/2023
```
Expected Outputs:
```
ok! i've added to your list:
  [D][ ] history essay (by: next friday)
wow! there are 2 tasks in the list now! :0
```
```
ok! i've added to your list:
  [D][ ] buy jackson's present (by: Jan 16 2023)
wow! there are 3 tasks in the list now! :0
```
<br />

### `event` - Adding an EVENT task

Creates a task with a start and end time

Format: `event [description] /from [start time] /to [end time]`

Examples:
```
event halloween party at jackson's /from friday 5pm /to 10pm
```
Expected Output:
```
ok! i've added to your list:
  [E][ ] halloween party at jackson's (from: friday 5pm 
  to: 10pm)
wow! there are 4 tasks in the list now! :0
```
<br />

### `mark` - Marking a task as complete

Marks a task's checkbox (second checkbox)

Format: `mark [task index]`

Example:
```
mark 2
```
Expected Output:
```
nice work! i've taken note!:
  [D][X] history essay (by: next friday)
```

<br />

### `unmark` - Reverting a task back to unmarked

Unmarks a task's checkbox (second checkbox)

Format: `unmark [task index]`

Example:
```
unmark 2
```
Expected Output:
```
uhh okay... i've unmarked your task:
  [D][ ] history essay (by: next friday)
```

<br />

### `list` - Showing all tasks

Shows a list of all existing tasks in the order they were created

Format: `list`

Expected Output:
```
here! your list:
1. [T][X] mop the floor
2. [D][ ] history essay (by: next friday)
3. [C][X] buy jackson's present (by: Jan 16 2023)
4. [E][ ] halloween party at jackson's (from: friday 5pm
to 10pm)
```

### `find` - Finding tasks with keyword

Finds all tasks containing a keyword

Format: `find [keyword]`

Example:
```
find jackson
```
Expected Output:
```
here is everything that matched:
1. [D][X] buy jackson's present (by: Jan 16 2023)
2. [E][ ] halloween party at jackson's (from friday 5pm
to: 10pm)
```
<br />

### `delete` - Deleting a task

Removes a task (warning: irreversible!)

Format: `delete [index]`

Example:
```
delete 2
```
Expected Output:
```
okay i've deleted this task:
  [D][ ] history essay (by: next friday)
now there are 3 tasks in the list now!
```
<br />

### `bye` - Terminating the program

Closes Fideline

Format: `bye`
are 3 tasks in the list now!
<br />
