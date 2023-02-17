# User Guide
t4skiezxc is a chat bot that allows helps you manage your tasks.

1. [Quick Start](#quick-start)
2. [Features]()

## Quick Start
1. Ensure that you have Java 11 or above installed 
2. Download the program from [here](https://github.com/randallnhr/ip/releases)
3. Move the file into a new folder
4. Open your command terminal in the folder and run the command `java -jar t4skiezxc.jar`

## Features
> **:information_source: Note on command format**
> - All commands should be in lower-case
>- Words in `<>` are the parameters to be supplied by the user<br> 
e.g. in `todo <name>`, name is a parameter which can be used as `todo Homework`
>- All dates provided should be in the form of `yyyy-mm-dd`<br>
e.g. `deadline Assignment /by 2023-05-25`


|                         Command                          | Format                                                             | Example                               |
|:--------------------------------------------------------:|:-------------------------------------------------------------------|---------------------------------------|
|              [**help**](#help---view-help)               | `help`                                                             ||
|            [**list**](#list---list-of-tasks)             | `list`                                                             ||
|            [**todo**](#todo---add-todo-task)             | `todo <name>`                                                      | `todo Chores`                         |
|      [**deadline**](#deadline---add-deadline-task)       | `deadline <name> /by <yyyy-mm-dd>`                                 | `deadline Homework /by 2023-05-20`    |
|  [**reschedule**](#reschedule---reschedule-a-task-date)  | `reschedule <deadline/event> <index> </by /from /to> <yyyy-mm-dd>` | `reschedule event 2 /from 2023-05-22` |
|              [**mark**](#mark---mark-task)               | `mark <index>`                                                     | `mark 1`                              |
|           [**unmark**](#unmark---unmark-task)            | `unmark <index>`                                                   | `unmark 1`                            |
|          [**find**](#find---find-similar-tasks)          | `find <keyword>`                                                   | `find Homework`                       |

## Usage

### `help` - View help

Displays a list of commands with its format.

Format: `help`

Example:
```
help
```

### `list` - List of tasks

Displays a list of tasks added.

Format: `list`

Example:
```
list
```

### `todo` - Add todo task

Adds a todo task that has to be completed with no deadline.

Format: `todo <name>`

Example: 
```
todo Chores
```

### `deadline` - Add deadline task

Adds a deadline task that has to be completed by a given date.<br>

Format: `deadline <name> /by <yyyy-mm-dd>`
- Date has to be in the format yyyy-mm-dd.

Example:
```
deadline Homework /by 2023-05-20
```

### `event` - Add event task

Adds an event task that is happening within two given dates.<br>

Format: `event <name> /from <yyyy-mm-dd> /to <yyyy-mm-dd>`
- Date has to be in the format yyyy-mm-dd.

Example:
```
event Camping /from 2023-05-25 /to 2023-05-28
```

### `reschedule` - Reschedule a task date

Change the date of deadline or event task.<br>

Format: `reschedule <task type> <index> </by /from /to> <yyyy-mm-dd>`
- Date has to be in the format yyyy-mm-dd.
- `<deadline/event>` refers to the type of task you wish to reschedule, **deadline or event  only**.
- `<index>` refers to the index of the task as shown in the `list` command. **Must be a positive integer**
- `</by /from /to>` refers to the date you wish to change, `/by` for deadline tasks and `/from` or `/to` for event tasks

Example:
```
reschedule deadline 1 /by 2023-05-20
reschedule event 2 /from 2023-05-22
reschedule event 3 /to 2023-05-24
```

### `mark` - Mark task

Marks a task as completed. 

Format: `mark <index>`
- `<Index>` refers to the index of the task as shown in the `list` command. **Must be a positive integer and within number of tasks added**

Example:
```
mark 1
```

### `unmark` - Unmark task

Marks a task as not completed.

Format: `unmark <index>`
- `<Index>` refers to the index of the task as shown in the `list` command. **Must be a positive integer and within number of tasks added**

Example:
```
unmark 1
```

### `find` - Find similar tasks

Displays a list of task similar to the provided keyword.

Format: `find <keyword>`


Example:
```
find Homework
```