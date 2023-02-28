# User Guide

## Features 

### Adding task

The product allows for the addition of 3 different types of tasks :
- Todo
- Event
- Deadline

### Deleting task

Deletion of specific tasks.

### Searching of task

Searching for a task through a keyword.

### Marking and Unmarking task

Mark or Unmark a task.

### Display list of tasks

The whole list of tasks both done and undone will be displayed.

### Undo task

Undo your previous action. Actions that are able to be undo :
1. `todo`
2. `event`
3. `deadline`
4. `mark`
5. `unmark`
6. `delete`

## Usage

### `todo` - Adding todo

Adds a todo task to your list.

Example of usage: 

`todo homework`

Expected outcome:

Successful addition will result in this feedback by Tiger
```
~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
Aite letsgetit you added:
  [T][ ] homework
currently you have 1 tasks
~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
```

### `event` - Adding event

Adds an event task to your list

Example of usage:

`event movie /from 18/02/23 1200 / to 18/02/23 1400`

Expected outcome:

Successful addition will result in this feedback by Tiger
```
~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
Aite letsgetit you added:
  [E][ ] movie |from: 18 Feb 2023, 12:00 |to: 18 Feb 2023, 14:00|
currently you have 1 tasks
~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~   
```

### `deadline` - Adding deadline

Adds a deadline task to your list

Example of usage:

`deadline project /by 20/02/23 2359`

Expected outcome:

Successful addition will result in this feedback by Tiger
```
~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
Aite letsgetit you added:
  [D][ ] project |by: 20 Feb 2023, 23:59|
currently you have 1 tasks
~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~  
```

### `delete` - Deleting task

Deletes a certain task following task list serial number.

Example of usage:

`delete 1`

Expected outcome:

Successful delete will result in this feedback by Tiger
```
~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
Alright, deleted task:
 [T][ ] read book
 
3 tasks left!
~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
```

### `mark` - Marking task

Marks a certain task following task list serial number

Example of usage:

`mark 2`

Expected outcome:

Successful mark will result in this feedback by Tiger
```
~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
Congrats this has been done:
 [T][X] pick flowers
 
Leskooo!
~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~ 
```

### `unmark` - Unmarking task

Unmarks a certain task following task list serial number

Example of usage:

`unmark 2`

Expected outcome:

Successful unmark will result in this feedback by Tiger
```
~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~    
Alright, new task:
 [T][ ] pick flowers
 
We can do dis!
~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~ 
```

### `find` - Search task

Search for task with the specified keyword given

Example of usage:

`find read`

Expected outcome:

Successful find will result in this feedback by Tiger
```
~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~ 
Aye this is what i found according to your keyword:
1. [T][ ] read
~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
```

### `list` - List task

Lists the whole list of tasks

Example of usage: 

`list`

Expected outcome:

Successful list will result in this feedback by Tiger
```
~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
Hurry up and finish these tasks:
1.  [T][ ] homework
2.  [E][ ] movie |from: 18 Feb 2023, 12:00 |to: 18 Feb 2023, 14:00|
3.  [D][ ] project |by: 20 Feb 2023, 23:59|
~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
```

### `undo` - Undo previous action

Undo the previous action done

Example of usage:

`undo`

Expected outcome:

Successful undo will result in this feedback by Tiger
```
~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
Undone!
~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
```