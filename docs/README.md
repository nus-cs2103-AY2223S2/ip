<style>
img[src*="Ui"] {
   width:400px;
   height:600px;
    display: flex
}
h1, h2, h3, p {
  font-family: helvetica
}
</style>

# Rick and Duke - User Guide

>Listen to me, Morty. I know that new situations can be intimidating. You 
>lookin’ around and it’s all scary and different, but y’know …
>meeting them head-on, charging into ‘em like a bull — that’s how we grow as
>people.  - Rick Sanchez ([source](https://www.adultswim.com/videos/rick-and-morty))

Having issues with your memory? Always been wanting to do things, but somehow they are *erased* from your mind?
Well, that's because you're not using Rick and ~~Morty~~ ***Duuuke***

<p align="center">
<img src="Ui.png"/>
</p>

## Table of Contents

---

- **[Features](#features)**
- **[Usage](#usage)**
- **[Frequently Asked Questions](#frequently-asked-questions)**

## Features 

---

### Add Tasks

You can type tasks, deadlines, and events into the text box, and hit `Enter` or
click on `Send` - The app will store the tasks for you!

### View Tasks

You can query the storage to see what tasks you have stored.

### Find Tasks

You can query the storage to see which tasks contain a certain keyword, or fall
on a certain date.

### Manipulate Tasks

You can mark tasks as *done*, mark them as *not completed*, or *delete* them
- all by text description, list index, or date!

### Import Tasks

The app stores tasks as a `.txt` file as `data/RICKS_BRAIN.TXT` in the same 
folder as the app - simply rename your own `TXT` file and our app can import 
your tasks from elsewhere! This also allows the app to save tasks to storage 
when you quit the app.

## Usage

---

### `todo` - Add a Todo task

Adds a `Todo` task to the storage with the given description

Example of usage: 

`todo {task_description}`

Expected outcome:

```text
Got it. I've added this task: 
  [T][ ] {task_description}
Now you have {num_of_tasks} tasks in the list.
```

This indicates that the *Todo* task has been successfully added to the storage.

### `deadline` - Add a Deadline task

Adds a `Deadline` task to the storage with the given description, and deadline. 
Accepts datetime inputs in `d/M/yy HHmm` format (e.g. `16/5/23 1200` for
12 p.m. on the 16th of May 2023)

Example of usage:

`deadline {task_description} /by d/M/yy HHmm`

Expected outcome:

```text
Got it. I've added this task: 
  [D][ ] {task_description} (by: d MMM YYYY HH:mm)
Now you have {num_of_tasks} tasks in the list.
```

This indicates that the *Deadline* has been successfully added to the storage.

### `event` - Add a Event task

Adds a `Event` task to the storage with the given description, and start/end 
datetime. Accepts datetime inputs in `d/M/yy HHmm` format (e.g. `16/5/23 1200` 
for 12 p.m. on the 16th of May 2023)

Example of usage:

`event {task_description} /from d/M/yy HHmm /to d/M/yy HHmm`

Expected outcome:

```text
Got it. I've added this task: 
  [E][ ] {task_description} (from: d MMM YYYY HH:mm to: d MMM YYYY HH:mm)
Now you have {num_of_tasks} tasks in the list.
```

This indicates that the *Event* has been successfully added to the storage.

### `list` - View all tasks

Views all tasks in the storage. If there are none, add a 
[Todo](#todo---add-a-todo-task), [Event](#event---add-a-event-task), or a 
[Deadline](#deadline---add-a-deadline-task) first!

Example of usage:

`list`

Expected outcome:

```text
1. [T][X] Task One
2. [D][ ] Deadline Two (by: Feb 02 2023 12:00PM)
3. [E][ ] Event Three (from: Feb 03 2023 12:15PM to: Feb 04 2023 12:30PM)
...
```

This displays the tasks in the storage, in the *sequence* they were added.

### `find` - Find a task

Searches for a task in the storage whose description contains the search term.
Does not accept empty queries.

Example of usage (replace `{search_term}` with your own):

`find {search_term}`

Expected outcome:

```text
1. [T][ ] {search_term} task
2. [T][ ] Task {search_term}
...
```

This displays all tasks in the storage that contain the search query term in 
their description, and lists them in the order they were added.

### `tasks /on` - Find tasks on a certain date

Given a date, searches the storage for tasks that occur on that date. If there
are none, no tasks will be listed. Accepts dates in `d/M/yy` format, or 21/5/23
for 21 May 2023.

Example of usage:

`tasks /on 2/2/23`

Expected outcome:

```text
- [D][ ] Deadline Two (by: Feb 02 2023 12:00PM)
- [E][ ] Event Three (from: Feb 02 2023 12:15PM to: Feb 03 2023 12:30PM)
...
```

Lists the [Deadlines](#deadline---add-a-deadline-task) or 
[Events](#event---add-a-event-task) that occur on that date, if there are any
*AND* in the order they were added to the storage.

### `mark` - Mark task(s) as completed

Given an index, multiple indexes, a range of indexes, or a task description 
filter or a date on which tasks occur, marks those task(s) as completed. 

*Indexes* refer to the **numerical** index as indicated by the response to the
[`list`](#list---view-all-tasks) command.

Examples of usage:
- Mark Individual task: `mark 1`
- Mark Multiple tasks: `mark 1 3 5`
- Mark Range of tasks: `mark 1 - 3` OR `mark 1-3` OR `mark 1 to 3`
- Mark tasks occurring on a Date: `mark /on 2/2/23` OR `mark -o 2/2/23` OR
  `mark --o 2/2/23`
- Mark tasks containing a Description: `mark /contains desc` OR 
  `mark -c desc` OR `mark --c desc`
- Mark All tasks: `mark /all` OR `mark -a` or `mark --a`

Expected Outcome:
```text
Got it. I've marked these {num_of_tasks} tasks as done:
[T][X] Task XXX
[D][X] Deadline YYY (by: Feb 02 2023 11:59PM)
[E][X] Event ZZZ (from: Feb 02 2023 12:00PM to: Feb 02 2023 1:00 PM)
...
```

Marks all the *specified* tasks in the storage as completed. If there are
errors in the flags passed to the command, it will indicate them after
listing the tasks successfully marked as completed.

### `unmark` - Mark task(s) as ***NOT*** completed

Given an index, multiple indexes, a range of indexes, or a task description
filter or a date on which tasks occur, marks those task(s) as **not**
completed.

*Indexes* refer to the **numerical** index as indicated by the response to the
[`list`](#list---view-all-tasks) command.

Examples of usage:
- Unmark Individual task: `unmark 1`
- Unmark Multiple tasks: `unmark 1 3 5`
- Unmark Range of tasks: `unmark 1 - 3` OR `unmark 1-3` OR `unmark 1 to 3`
- Unmark tasks occurring on a Date: `unmark /on 2/2/23` OR `unmark -o 2/2/23`
  OR `unmark --o 2/2/23`
- Unmark tasks containing a Description: `unmark /contains desc` OR 
  `unmark -c desc` OR `unmark --c desc`
- Unmark All tasks: `unmark /all` OR `unmark -a` or `unmark --a`

Expected Outcome:
```text
Got it. I've marked these {num_of_tasks} tasks as not completed:
[T][ ] Task XXX
[D][ ] Deadline YYY (by: Feb 02 2023 11:59PM)
[E][ ] Event ZZZ (from: Feb 02 2023 12:00PM to: Feb 02 2023 1:00 PM)
...
```

Marks all the *specified* tasks in the storage as not completed. If there are
errors in the flags passed to the command, it will indicate them after
listing the tasks successfully marked as not completed.

### `delete` - Delete task(s)

Given an index, multiple indexes, a range of indexes, or a task description or
a date on which those tasks occur, deletes those task(s).

*Indexes* refer to the **numerical** index as indicated in the response to the
[`list`](#list---view-all-tasks) command.

Examples of usage:
- Delete Individual task: `delete 1`
- Delete Multiple tasks: `delete 1 3 5`
- Delete Range of tasks: `delete 1 - 3` OR `delete 1-3` OR `delete 1 to 3`
- Delete tasks occurring on a Date: `delete /on 2/2/23` OR `delete -o 2/2/23`
  OR `delete --o 2/2/23`
- Delete tasks containing a Description: `delete /contains desc` OR 
  `delete -c desc` OR `delete --c desc`
- Delete All tasks: `delete /all` OR `delete -a` or `delete --a`

Expected Outcome:
```text
Got it. I've deleted these {num_of_tasks} tasks:
[T][X] Task XXX
[D][X] Deadline YYY (by: Feb 02 2023 11:59PM)
[E][X] Event ZZZ (from: Feb 02 2023 12:00PM to: Feb 02 2023 1:00 PM)
...
Now you have {num_of_tasks} tasks in the list.
```

Deletes all the *specified* tasks in the storage. If there are
errors in the flags passed to the command, it will indicate them after listing
the successfully deleted tasks.

### `bye` - Exit the app

You may either enter this command or close the window with the ❌ button.

Examples of usage:

`bye`

Expected outcome:

```text
It was okay serving you. Might/might not see you again.
Exiting...
```

Rick indicates that the app is closing, and shuts down the app.

## Frequently Asked Questions

---

**Q**: How do I import a text file?<br/>
**A**: Here's a view of the file directory where you store your app:
```text
. (Folder you are in)
├── data (new folder)
│   └── RICKSBRAIN.TXT
└── rick.jar
```
Simply rename your text file as `RICKS_BRAIN.TXT`, and replace the existing
`.txt` file. Restart the app, and your tasks should be imported! You may also
transfer this file to another machine so that you can access your tasks there.

**Q**: Cool! Now how do I format this file?<br/>
**A**: The file is simply formatted in this way:
- `Todo`: `T|1|{desc}` - `T` for Todo, **1** for completed, task description
- `Deadline`: `D|0|{desc}|deadline` `D` for Deadline, **0** for not completed,
  task description, deadline in `d/M/yy HHmm` format
- `Event`: `E|1|{desc}|start|end` `E` for Event, **1** for completed, event
  description, start and end datetime each in `d/M/yy HHmm` format

Example:
```shell
##data/RICKS_BRAIN.TXT
T|1|task one
D|0|deadline one|2/2/23 1200
E|1|event one|2/2/23 1200|3/2/23 1200
```
An improper format may crash the app, and your file may be corrupted.

**Q**: Must I use the graphic interface (GUI)? Can't it be used in a terminal
environment? <br/>
**A**: It can! Simply update the `src/main/rick/Rick.java` file to print and
receive commands from the terminal, instead of sending the responses to the GUI
package classes.