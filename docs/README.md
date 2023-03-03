# DukeMeister3000
> "Tasks not complete, until DukeMeister3000 I used" - Mister Yoda

DukeMeister3000 tracks your tasks and gives you that edge to complete them on time. It is
- extremely intuitive
- fast
- Makes you a sigma male/female
- ~~quite~~ ~~very~~ ABSOLUTELY simple to use

Simply
1. Download it [here](https://github.com/daytona65/ip/releases/tag/v0.2-fix)
2. Run it as a jar file
3. Start typing your tasks
4. And there you have it!

Let DukeMeister3000 and Gigachad manage your tasks for you! 💯

Some features:
- [ ] Deadline setting
- [ ] GIGACHAD Sigma Role play for your entertainment
- [ ] Display your current tasks
- [ ] Stores your tasks when you stop the program and accesses them again on the next startup

If you are a Java programmer, you can review the code easily with its well designed code and JavaDocs. Here's the `newEvent` method:
```
    /**
     * Creates a new Event task and prints a message for the user
     * confirming the addition of the task to the Task List.
     *
     * @param input The user input.
     * @param taskSize The current Task List size.
     * @return The created task.
     */
    public Task newEvent(String input, int taskSize) throws DukeException {
        int fromIndex = input.indexOf(" /from ");
        int toIndex = input.indexOf(" /to ");
        Task task = new Event(input.substring(6, fromIndex), input.substring(fromIndex + 7, toIndex),
                input.substring(toIndex + 5));
        Ui.addMessage(task, taskSize);
        return task;
    }
```



# User Guide

## Features 

### Add and delete tasks

Add and delete tasks like todo, deadline and event to your task list.

### Marking and un-marking tasks

Mark tasks as done or not done.

### Find

Find tasks with specified keywords from your task list.

### List

View all of your tasks.

### Prioritise tasks

View and assign levels of priority to each task.

### Store tasks

DukeMeister3000 remembers tasks from your previous session.

## Usage

### `todo DESCRIPTION` - Add todo

Adds a todo task to your task list.

Example of usage: 

`todo return book`

Expected outcome:

Adds a todo task with description "return book" to your task list.

```
[T][][LOW] return book
```
<br/>

### `deadline DESCRIPTION /by DATE(DD/MM/YYYY HH:MM)` - Add deadline

Adds a deadline task with deadline date and time to your task list.

Example of usage:

`deadline return book /by 10/02/2023 14:00`

Expected outcome:

Adds a deadline task with description "return book", deadline 10 Feb 2023 2 pm to your task list.

```
[D][][LOW] return book (by: 10 Feb 2023 2 PM)
```
<br/>

### `event DESCRIPTION /from DATE /to DATE` - Add event

Adds an event task to your task list with start and end dates and times.

Example of usage:

`event dinner /from 10/08/2023 19:00 /to 10/08/2023 21:00`

Expected outcome:

Adds an event task with description "dinner", from 7pm to 9pm on 10 Aug 2023 to your task list.

```
[E][][LOW] dinner (from: 10 Aug 2023 7 PM to: 10 Aug 2023 9 PM)
```
<br/>

### `list` - View all tasks


Example of usage:

`list`

<br/>

### `mark INDEX` - Mark task as done

Mark task at INDEX as done.

Example of usage:

`mark 1`

Expected outcome:

```
[E][X][LOW] return book
```
<br/>

### `unmark INDEX` - Mark task as not done

Mark task at INDEX as not done.

Example of usage:

`unmark 1`

Expected outcome:

```
[E][][LOW] return book
```
<br/>

### `delete INDEX` - Delete task

Delete task at INDEX.

Example of usage:

`delete 1`

<br/>

### `find KEYWORD` - Find task

Show tasks with KEYWORD in description.

Example of usage:

`find book`

<br/>

### `pget INDEX` - Get Priority of task

Show priority of task at INDEX.

Example of usage:

`pget 1`

Expected outcome:

```
Priority of this task is: LOW
```

<br/>

### `pset PRIORITY_VALUE INDEX` - Set Priority of task

Sets priority of task at INDEX.

Example of usage:

`pset h 1`

Expected outcome:

```
Priority of this task has been set to: HIGH
```

<br/>

### `bye` - Ends the program

Ends the program.

Example of usage:
`bye`


