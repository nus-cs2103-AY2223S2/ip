# User Guide


## Features


### Feature-Adding tasks


Supports the tracking of 3 types of tasks :
* To-do
* Events
* Deadlines


### Feature-Delete

Deletes tasks from the list of tasks. 

### Feature-Mark / Unmark

Marks or Unmarks a task as done. 

### Feature-List

Lists out all the tasks that are added.

### Feature-Add contact:

Adds a contact's name and their handphone number to the list.

### Feature-Delete contact :

Removes a contact from the list of contacts .

### Feature-listfriends : 

Lists all the contacts currently on the contact list. 


### Feature-Find:

Displays all tasks matching a given substring.


## Usage

### `Keyword` - Describe action
### `todo` - Adds a todo task to the list

Adds a todo task to the list

Example of usage:

`todo stuff`

Expected outcome:

The tasks 'stuff' will be saved and the following is shown:

```
Gotchu fam
I've added
[T][] stuff
to all the shit you need to do 
shag bro now u got x tasks
```

### `deadline` - Adds a deadline task to the list

Adds a deadline task to the list

Example of usage:

`deadline task/2020-02-02`

Expected outcome:

A task with a deadline will be saved and the following is shown:

```
Gotchu fam
I've added
[D][] task (by:2020-02-02)
to all the shit you need to do 
shag bro now u got x tasks

```

### `event` - Adds an event to the list

Adds an event with start and end date to the list

First date given is the start time, and the second given 
is the end time.

`event task/2020-02-15/2021-02-20`

An event task will be saved and the following is shown:

```
Gotchu fam
I've added
[E][] task (from:2020-02-15 to: 2021-02-20)
to all the shit you need to do 
shag bro now u got x tasks

```

### `delete` - Deletes a task

Deletes the task at the given index (1-based).
If want to clear the whole list, can use 'delete 1' repeatedly. 

Example of usage:

`keyword (optional arguments)`

```
delete 1
```

Expected outcome:


The task will be deleted and the following is shown:

```
Gotchu fam
I've added
[T][] stuff
to all the shit you need to do 
shag bro now u got x tasks

Ok bro I've removed this:
[T][] stuff
from all the shit you need to do 
Hope you have a better life now

```

### `mark` - Mark a task as done

Marks the task at the given index as done (1-based).

Example of usage:

```

mark 1
```

Expected outcome:

The task will be marked as done and the following is shown:

```
marked that for you brother 
```

### `unmark` - Marks a task as not done

Unmarks the task at the given index (1-based).

Example of usage:

```
unmark 1
```

Expected outcome:

The task will be marked as not done and the following is shown:

```
unmarked that for u u useless prick
```

### `list` - Lists all tasks

Lists all tasks in the current list.

Example of usage:

```
todo task1
deadline task2/2000-02-20
event task3/2000-02-06/2023-02-06
```

Expected outcome:

The following is shown: 

```
1.[T][] task1
2.[D][] deadline task2 (by: 2000-02-20)
3.[E][] event task3 (from: 2000-02-06 to:
2023-02-06)
```

### `friend` - Adds a contact to the contact list. 

Adds a given contact to the list of contact


In the format 

friend name handphonenumber

Example of usage:

```
friend matthew 999
```

Expected outcome:

The following output 
```
gratz bro this fella [C] matthew 999 is your new buddy
```

### `listfriends` - List all the contacts.


Example of usage:

```
friend matthew 999
friend ashley 995
listfriends
```

Expected outcome:

The following output will be shown. 
```
1.[C] matthew 999
2.[C] ashley 995
```

### `unfriend` - Removes a friend from the contact list

Removes a friend from the list of contacts. 

Example of usage:

```
unfriend 1 
```

Expected outcome:

The following output
```
Ok bro I've removed this: 
[C] matthew 999
from the list of your friends
Hope you have a better life now without them
```

### `find` - Finds tasks by a search keyword

Displays all the tasks matching a given keyword

Example of usage:

```
todo work things 
todo workout
todo work - by rihanna
todo things
todo cry 
find work
```

Expected outcome:

The following output is shown. 

```
1. [T][] work things
2. [T][] workout
3. [T][] work - by rihanna
```

