# User Guide

## Setup

Go to [Releases](https://github.com/swxk19/ip/releases) page and retrieve latest release. Download `duke.jar` from the release.

### Dependencies

1. All OS: JRE/JDK
2. Linux: x11, Wayland

### Running

First, move the `duke.jar` to and empty folder of your choice.

1. Windows/MacOS: Double-click the file.
2. Linux (Ubuntu 22.04):

    1. Take note of dependencies
    2. Open terminal
    3. `cd` to the folder containing `duke.jar`
    4. Run `sudo chdmod +x duke.jar`
    5. Run `java -jar duke.jar`

#### Troubleshooting

##### Linux (Ubuntu 22.04)

1. Run using `java -Djdk.gtk.version=2 -jar duke.jar` instead.

**Further troubleshooting/Bug reporting:**

1. Telegram [@kiseow](t.me/kiseow) (Help desk)
2. [Create an issue ](https://github.com/swxk19/ip/issues)

## Features

### Add Tasks

Description of the feature.

### Remove Tasks

Description of the feature.

### Search Tasks

Search tasks by their name.

### View Tasks by Date

View tasks based on a specified date.

## Usage

### `todo` - Adds a "To Do" task

---

Adds a "To Do" task to the task list.

##### Format:

`todo <task_name>`

##### Expected Response:

> _I'll remember this task:_
>
> _[T][ ] <task_name>_
>
> _Now you have <list_size> items in the list._

### `deadline` - Adds a "Deadline" task

---

Adds a "Deadline" task to the task list.

##### Format:

`deadline <task_name>`

##### Expected Response:

> _Well, well... another task to the list:_
>
> _[D][ ] <task_name> (by: <due_date>)_
>
> _Now you have <list_size> items in the list._

### `event` - Adds an "Event" task

---

Adds a "Event" task to the task list.

##### Format:

`event <task_name> /from <start_date> /to <end_date>`

##### Expected Response:

> _A new event! I hope it's at night:_
>
> _[E][ ] <task_name> (from: <start_date> to: <end_date>)_
>
> _Now you have <list_size> items in the list._

### `delete` - Removes a task

---

Removes a task from the task list.

##### Format:

`delete <list_index>`

-   `list_index` refers to the index of the task in the list

##### Expected Response:

```
The task has disappeared into the night...:
<Task>
Now you have <list_size> tasks in the list
```

### `list` - Displays the task list

---

Displays the task list

##### Format:

`list`

##### Expected Response:

```
Here's your list:
1. <task>
2. <task>
3. <task>
...
```

### `mark` - Mark a task as complete

---

Mark a task as complete

##### Format:

`mark <list_index>`

-   `list_index` refers to the index of the task in the list

##### Expected Response:

```
Wonderful. You've completed this task:
	<task>
```

### `unmark` - Mark a task as incomplete

---

Mark a task as incomplete

##### Format:

`unmark <list_index>`

-   `list_index` refers to the index of the task in the list

##### Expected Response:

```
I've marked this task as incomplete:
<task>
```

### `find` - Search for task

---

Search for a task by it's name according to a specified keyword

##### Format:

`find <search_keyword>`

##### Expected Response:

```
Here's what I spotted:
1. <search_result>
2. <search_result>
...
```

### `view` - View tasks up to a date

---

View tasks which will end on a specified date or before.

##### Format:

`view <end_date>`

-   `<end_date>` must be in the format of `YYYY/MM/DD`. e.g. `2023/11/01`

##### Expected Response:

```
Here's what you have on <end_date> or before:
<task>
...
```

### `bye` - Exits the program

---

Exits the program

##### Format:

`bye`

##### Expected outcome:

Application closes.
