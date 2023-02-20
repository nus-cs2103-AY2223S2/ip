# User Guide for `Duke`

## Features 

### Add your task

Add the task that you have to be saved in Duke.

1. **_To-do_** : Your to-do task
  - Command: `todo [description]`
2. **_Deadline_** : Your upcoming deadline
  - Command: `deadline [description] /by YYYY-MM-DD HHmm`
3. **_Event_** : An upcoming event
  - Command : `event [description] /from YYYY-MM-DD HHmm /to YYYY-MM-DD HHmm`

### Mark/Unmark a task to be done

Mark or unmark you task to be done or undone, and it will be crossed out for you.

### Delete a task

Delete unwanted tasks

### Undo

Regret to add or delete a task? You can undo your previous delete or add command!

### Find tasks using keywords

Have too many tasks? Use find to find tasks that contains the keyword you input.

## Usage

### `list` - Shows the whole list of task that you have added

Example Output:
```
1. [T][ ] read chapter 12
2. [E][X] John's Birthday Party (from: Feb 15 2023 1800 to: Feb 15 2023 2000)
```

### `todo` - adds a todo task

Example of usage: `todo laundry`

Example Output:
```
added: laundry
Now you have X tasks in the list
```

### `deadline` - adds a deadline

Example of usage: `deadline essay /by 2023-02-15 2359`

Example Output:
```
added: essay (by: Feb 15 2023 2359)
Now you have X tasks in the list
```

### `event` - adds an event

Example of usage: `event John's Birthday Party /from 2023-02-15 1800 /to 2023-02-15 2000`

Example Output:
```
added: John's Birthday Party (from: Feb 15 2023 1800 to: Feb 15 2023 2000)
Now you have X tasks in the list
```

### `delete` - deletes a task

_Prerequisite_: `list`
Delete a task using its index number seen in your list.

Example of usage:
```
list
1. [T][ ] read chapter 12
2. [E][X] John's Birthday Party (from: Feb 15 2023 1800 to: Feb 15 2023 2000)
delete 2
list
1. [T][ ] read chapter 12
```

### `undo` - undo the previous add or delete call

Example of usage: 
```
list
1. [T][ ] read chapter 12
2. [E][X] John's Birthday Party (from: Feb 15 2023 1800 to: Feb 15 2023 2000)
delete 2
list
1. [T][ ] read chapter 12
undo
list
1. [T][ ] read chapter 12
2. [E][X] John's Birthday Party (from: Feb 15 2023 1800 to: Feb 15 2023 2000)
```

### `mark` / `unmark` - marks or unmarks a task

Example of usage:
```
lis
1. [T][ ] read chapter 12
2. [E][X] John's Birthday Party (from: Feb 15 2023 1800 to: Feb 15 2023 2000)
mark 1
list
1. [T][X] read chapter 12
2. [E][X] John's Birthday Party (from: Feb 15 2023 1800 to: Feb 15 2023 2000)
unmark 2
list
1. [T][X] read chapter 12
2. [E][ ] John's Birthday Party (from: Feb 15 2023 1800 to: Feb 15 2023 2000)
```

### `find` - finds tasks using keywords

Example of usage:
```
list
1. [T][X] read chapter 12
2. [E][ ] John's Birthday Party (from: Feb 15 2023 1800 to: Feb 15 2023 2000)
find read
1. [T][X] read chapter 12
```
