# User Guide
Hello! My name is Thanos, your personal task manager.

<p align = "left">
<img src= "Ui.png" width = "300">
</p>

## Table of Content
* Quick Start
* Features
* Usage

## Quick Start
* Install Java 11 or above is installed in your computer.
* Download the jar file from the repository.
* Copy the file to the preferred location you want to use and run the application.
* You can also try running the application by opening the terminal and typing `java -jar duke.jar`.
* Please press *Enter* after that.

## <a name = "feature"></a>Features
<input type="checkbox" checked="checked" disabled="disabled">&nbsp;Add a ToDo task</a><br>
<input type="checkbox" checked="checked" disabled="disabled">&nbsp;Add a Deadline task</a><br>
<input type="checkbox" checked="checked" disabled="disabled">&nbsp;Add an Event task</a><br>
<input type="checkbox" checked="checked" disabled="disabled">&nbsp;Add a period task</a><br>
<input type="checkbox" checked="checked" disabled="disabled">&nbsp;View a list of your tasks</a><br>
<input type="checkbox" checked="checked" disabled="disabled">&nbsp;Mark task as done</a><br>
<input type="checkbox" checked="checked" disabled="disabled">&nbsp;Mark task as not done</a><br>
<input type="checkbox" checked="checked" disabled="disabled">&nbsp;Delete a task</a><br>
<input type="checkbox" checked="checked" disabled="disabled">&nbsp;Find the task by keyword</a><br>
<input type="checkbox" checked="checked" disabled="disabled">&nbsp;Exit chatbot</a>

## Usage

### `todo` - Add a ToDo task

Adds a To Do kind of task to your list of tasks.

**Command Format :** todo [task description]

**Example of usage:**

```
todo play video game
> Got it. I've added this task:
> [T][ ] play video game
> Now you have 1 tasks in the list
```
----
### `deadline` - Add a Deadline task

Adds task with deadline.

**Command Format :** deadline [task desc] /by [yyyy-mm-dd]

**Example of usage:**

```
deadline go on a date /by 2019-10-15
> [D][ ] go on a date Oct 15 2019
> Now you have 2 tasks in the list
```
----
### `event` - Add an Event task

Adds an Event task to your task manager.

**Command Format :** event [task desc] /from [yyyy-mm-dd] /to [yyyy-mm-dd]

**Example of usage:**

```
event wedding /from 2023-09-12 /to 2023-10-12
> [E][ ] wedding Sep 12 2023-Oct 12 2023
> Now you have 3 tasks in the list

```
----
### `periodTask` - Add a period task

Adds a period task to your task manager.

**Command Format :** periodTask [task desc] /between [yyyy-mm-dd] /and [yyyy-mm-dd]

**Example of usage:**

```
periodTask submit book /between 2023-09-12 /and 2023-10-12
> [E][ ] submit book Sep 12 2023-Oct 12 2023
> Now you have 3 tasks in the list

```
----
### `list` - View a list of your tasks

Shows the list of all tasks.

**Command Format :** list

**Example of usage:**

```
list
> 1.[D][ ] go on a date Oct 15 2019
> 2.[E][ ] wedding Sep 12 2023-Oct 12 2023

```
----
### `mark` - Mark task as done

Marks the task as done.

**Command Format :** mark [index]

**Example of usage:**

```
mark 1
> Nice! I've marked this task as done:
> [T][X] eat
```
----
### `unmark` - Mark task as not done

Marks the task as not done by replacing "X" sign with " ".

**Command Format :** unmark [index]

**Example of usage:**

```
unmark 1
> OK, I've marked this task as not done yet:
> [T][ ] eat
```
----
### `delete` - Delete a task

Removes the task from the task manager.

**Command Format :** delete [index]

**Example of usage:**

```
delete 1
> Noted. I've removed this task:
> [T][ ] eat
> Now you have 2 tasks in the list

```
----
### `find` - Find the task by keyword

Finds the tasks with the specified keyword.

**Command Format :** find [keyword]

**Example of usage:**

```
find eat
> 1.[E][ ] eat

```
----
### `bye` - Exit chatbot

Closes the application.

**Command Format :** bye

**Example of usage:**

```
bye
> Bye mortal, I will get back to destroying galaxies
```


