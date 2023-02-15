# User Guide


# Hero Bot :superhero:
The Hero Bot is a graphical user interface (GUI) desktop app that helps you track your  tasks. 
To start using Hero Bot, you have to:
- Download the v0.2.jar file under releases
- Go to the directory containing it in your terminal
- Run java -jar v0.2.jar, and you are all set!

## Features :joystick:
- [ ] Add
      Create new tasks of one of the three types, ToDo, Deadline or Event track it in a list.
- [ ] Delete
      Remove previously created Tasks from the list
- [ ] Mark
      Indicate if you have done a Tasks or have not done it yet
- [ ] List
      Show the entire list of all tasks you have added.
- [ ] Find
      Search for tasks in the list by using keywords


## Usage

### `todo` - Add a ToDo task 

Add a ToDo type task to the TaskList for tracking

Example of usage: 

`todo Homework`

A new todo task to do Homework to the  TaskList and is marked not done by default.

```
    Got it. I have added this task:
    [T][] Homework
    Now you have 1 tasks in the list.
________________________________________________________
```
___
### `list` - show all existing tasks lis Tasklist

Displays all the current tasks 

Example of usage:

`list`

lists our all tasks and are numbered starting from 1

```
    Here are the missions:
    1.[T][] Homework

________________________________________________________
```
___
### `deadline` - Add a Deadline task

Add a Deadline type task to the TaskList for tracking, needs a specification of the date-time for the deadline in this format:

- yyyy-MM-HHmm

Example of usage:

`deadline return Book /by 2023-03-01 1800`

Adds a task to return book by the deadline on March 01 0600 PM to Tasklist and  is marked not done by default.

```
    Got it. I have added this task:
    [D][] return Book (by: Mar 01 2023 0600 PM)
    Now you have 2 tasks in the list.
________________________________________________________
```
___
### `event` - Add a Events task

Add a Events type task to the TaskList for tracking, needs a specification of the date-time for the start and end times in this format:

- yyyy-MM-HHmm (for /from)
- HHmm(for /to)

Example of usage:

`event part-time /from 2023-03-02 1000 /to 1800`

Adds a task for a part time from March 02 2023 1000 AM to 0600 PM into Tasklist and  is marked not done by default.

```
    Got it. I have added this task:
    [E][] part-time (from: Mar 02 2023 1000 AM  to:0600 PM)
    Now you have 3 tasks in the list.
________________________________________________________
```
___
### `mark` or `unmark` - mark or unmark a task

Marks or unmarks tasks by indicating thorugh the X if marks and blank and unmarked. Requires specification of task number.

Example of usage:

`mark 1`

Marks the first task in this case being todo homework task as marked by indicating through the X in '[ ]'.

```
    Nice! I've finally finished the mission:
    [T][X] Homework
________________________________________________________
```
`unmark 1`

Unmarks the first task in this case being todo homework task as marked by indicating through the removal of X in '[ ]'.

```
    Uhh, T have not done this:
    [T][] Homework
________________________________________________________
```
___
### `find` - searches for a specific task with keyword

looks in to current task list and show task with specific key word that is queried:

Example of usage:

`find Homework`

Finds the Homework task in tasklist that matched the field queried.

```
    Here are your matching missions in your listOfTasks:
    1.[T][] Homework
________________________________________________________
```
___
### `delete` - removes a specific task from listOfTasks

Delete an existing task from listOfTasks, requires specific number tagged to task to be deleted:

Example of usage:

`delete 1`

Deletes the fist task which is todo Homework.

```
    Okay. that's one off the list :
    1.[T][] Homework removed
    Now you have 2 missions in the listOfTasks
________________________________________________________
```
___