# User Guide for Duke: The task-list chatbot.
<div align="center">
  <img src="./Ui.png" />
</div>

## Contents
1. [Quick Start](#quick-start)
2. [Command Summary](#command-summary)
3. [Features](#features)
    - [Exit](#bye---saves-and-exits-duke)
    - [Add Todo](#todo---adds-todo-task)
    - [Add Deadline](#deadline---adds-deadline-task)
    - [Add Event](#event---adds-event-task)
    - [Delete Tasks](#delete---removes-task-from-task-list)
    - [Mark Tasks](#mark---marks-task-as-done)
    - [Unmark Tasks](#unmark---marks-task-as-not-done)
    - [List Tasks](#list---lists-all-tasks)
    - [Find Task](#find---searches-task-list-for-keyword)

## Quick Start

1. Ensure you have Java 11 or later installed. Get it [here](https://www.oracle.com/sg/java/technologies/javase/jdk11-archive-downloads.html).
2. Download the latest version of Duke [here](https://github.com/pzhengze/iP/releases/latest).
3. Place `duke.jar` into a folder of your choice.
4. Double click `duke.jar` to run.

## Command Summary

| Index | Description | Format | Example |
| :-: | :-: | :-: | :-: |
| 1 | [Exit session](#bye---saves-and-exits-duke) | `bye` | `bye` |
| 2 | [Add Todo Task](#todo---adds-todo-task) | `todo <Description>` | `todo iP` |
| 3 | [Add Deadline Task](#deadline---adds-deadline-task) | `deadline <Description> /by <Due Date>` | `deadline submission /by 17/02/2023 2359` |
| 4 | [Add Event Task](#event---adds-event-task) | `event <Description> /from <Duration> /to <Duration>` | `event workout /from 17/02/2023 1800 /to 17/02/2023 2000` |
| 5 | [Delete Task](#delete---removes-task-from-task-list) | `delete <Index>` | `delete 1` |
| 6 | [Mark Task](#mark---marks-task-as-done) | `mark <Index>` | `mark 1` |
| 7 | [Unmark Task](#unmark---marks-task-as-not-done) | `unmark <Index>` | `unmark 1` |
| 8 | [List Tasks](#list---lists-all-tasks) | `list` | `list` |
| 9 | [Find Task](#find---searches-task-list-for-keyword) | `find <keyword>` | `find submission` |

## Features

### `bye` - Saves and exits Duke

Saves task-list data and closes session with Duke.

Example of usage: 

`bye`

Expected outcome:

Saves and exits Duke.

```
Bye. Hope to see you again soon!
```

### `todo` - Adds Todo task

Creates a todo task and adds it into Duke's task-list.

Example of usage: 

`todo description`

Expected outcome:

Duke's task-list contains new todo.

```
Got it. I've added this task:
 [T][] description
Now you have 1 tasks in the list.
```

### `deadline` - Adds Deadline task

Creates a deadline task and adds it into Duke's task-list.
Due date should be inputted in the format `DD/MM/YYYY HHMM` else it will be treated as normal text.

Example of usage: 

`deadline description /by dueDate`

Expected outcome:

Duke's task-list contains new deadline.

```
Got it. I've added this task:
 [D][] description (by: dueDate)
Now you have 1 tasks in the list.
```

Example of usage: 

`deadline description /by 17/02/2023 2359`

Expected outcome:

Duke's task-list contains new deadline.

```
Got it. I've added this task:
 [D][] description (by: 17 Feb 2023 2359)
Now you have 1 tasks in the list.
```

### `event` - Adds Event task

Creates a event task and adds it into Duke's task-list.
Duration should be inputted in the format `DD/MM/YYYY HHMM` else it will be treated as normal text.

Example of usage: 

`event description /from duration /to duration`

Expected outcome:

Duke's task-list contains new event.

```
Got it. I've added this task:
 [E][] description (from: duration /to duration)
Now you have 1 tasks in the list.
```

Example of usage: 

`event description /from 17/02/2023 2359 /to 18/02/2023 2359`

Expected outcome:

Duke's task-list contains new event.

```
Got it. I've added this task:
 [E][] description (from: 17 Feb 2023 2359 to: 18 Feb 2023 2359)
Now you have 1 tasks in the list.
```

### `delete` - Removes task from task-list

Removes specified task from Duke's tasklist.
Index starts from 1.

Example of usage: 

`delete 1`

Expected outcome:

Duke's task-list does not contain specified task.

```
Noted. I've removed this task:
 [T][] description
Now you have 0 tasks in the list.
```

### `mark` - Marks task as done

Marks specified task in Duke's tasklist as done.
Index starts from 1.

Example of usage: 

`mark 1`

Expected outcome:

Task numbered 1 in Duke's task-list is marked as completed.

```
Nice! I've marked this task as done:
 [T][X] description
```

### `unmark` - Marks task as not done

Marks specified task in Duke's tasklist as not done.
Index starts from 1.

Example of usage: 

`unmark 1`

Expected outcome:

Task numbered 1 in Duke's task-list is marked as not completed.

```
Ok, I've marked this task as not done yet:
 [T][] description
```

### `list` - Lists all tasks

Lists all tasks in Duke's task-list

Example of usage: 

`list`

Expected outcome:

All tasks in Duke's task-list will be displayed.

```
Here are the tasks in your list: 
1.[T][] description
2.[D][] description (by: 17 Feb 2023 2359)
3.[E][] description (from: 17 Feb 2023 2359 to: 18 Feb 2023 2359)
```

### `find` - Searches task-list for keyword

Searches Duke's task-list for tasks whose description contains the keyword specified.

Example of usage: 

`find description`

Expected outcome:

Tasks whose description contains keyword will be displayed.

```
Here are the matching tasks in your list: 
1.[T][] description
```
