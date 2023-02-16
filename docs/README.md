# Cbot User Guide
### Upgrade your to-do list, today.

---
Is speaking too much of a hassle? Does your voice-recognition not recognize your voice?
Really wish you could go back to the ol' typing days?
Well, here's the solution you need!

#### Meet *Cbot*, your very own text-based chat-bot secretary.

```java
  _____  _            _           |
 /  ___]| |   ^-^   _| |_         |        for (Person p : everyone) {
|  / :D | |___  ___[_ + _]        |            cbot.hug(p);    // cbot says hi!
|  \___ | / . \/ . \ | |          |        }
 \_____]|_,_*_/\_*_/ |_/          |
```

---

## Features

***Cbot*** helps you keep track of your: **To-Do** tasks, **Deadlines** & **Events**.
* **Add** & **Remove**!
* **Mark** & **Unmark** as completed!
* **Edit** descriptions! (*editing times coming soon* >:D)
* **Filter**, **Find** & **Sort**!

---

## Usage

### `bye` - Close Cbot

Ends the Cbot session.

#### Format: `bye`

#### Aliases: `close`, `end`, `exit`

<br/>

### `list` - List all tasks

Displays the current list of tasks.

#### Format: `list`

<br/>

### `todo` - Add a To-Do task

Adds a **To-Do** task to the list, with the given **description**.

#### Format: `todo <description>`

#### Example: `todo Do laundry`

#### Aliases: `td`, `t`, `+`

<br/>

### `deadline` - Add a Deadline

Adds a Deadline task to the list, with the given **description** and **deadline**.
There is a set pool of accepted datetime formats,
including (but not limited to): `yyyy-MM-DD HHmm`, `d/M/y H:m`, and `MMM d y`.

#### Format: `deadline <description> /by <deadline>`

#### Example: `deadline Submit homework /by 17/2/2023 2359`

#### Aliases: `dl`, `d`

<br/>

### `event` - Add an Event

Adds an Event task to the list, with the given **description**, **start date** and **end date**.
There is a set pool of accepted datetime formats,
including (but not limited to): `yyyy-MM-DD HHmm`, `d/M/y H:m`, and `MMM d y`.

#### Format: `event <description> /from <start date> /to <end date>`

#### Example: `event Japan trip! /from Dec 1 2022 6 pm /to Dec 12 2022 5:30am`

#### Aliases: `ev`, `e`

<br/>

### `mark` - Mark as complete

Marks the stipulated task as done.
Multiple tasks may be selected at once.

#### Format: `mark <task index(es)>`

#### Example: `mark 1 2 3`

#### Aliases: `x`

<br/>

### `unmark` - Mark as incomplete

Marks the stipulated task as *not* done.
Multiple tasks may be selected at once.

#### Format: `unmark <task index(es)>`

#### Example: `unmark 1 2 3`

<br/>

### `edit` - Edit a task

Changes the description of the selected task to the given **new description**.

#### Format: `edit <task index> <new description>`

#### Example: `edit 3 Korea trip!`

#### Aliases: `change`, `fix`

<br/>

### `delete` - Delete a task

Deletes the stipulated task from the list.
Multiple tasks may be selected at once.

#### Format: `delete <task index(es)>`

#### Example: `delete 1 2 3`

#### Aliases: `del`, `remove`, `rem`, `-`

<br/>

### `sort` - Sort all tasks

Sorts the list by date, then by description.
To-Do tasks will be sent to the top of the list.

#### Format: `sort`

<br/>

### `find` - Find tasks by description

Displays the tasks that contain the given **phrase** in their description.

#### Format: `find <phrase>`

#### Example: `find homework`

#### Aliases: `search`

<br/>

### `after` - Filter tasks after a date

Displays the tasks that fall *before* the given **datetime**.
There is a set pool of accepted datetime formats,
including (but not limited to): `yyyy-MM-DD HHmm`, `d/M/y H:m`, and `MMM d y`.

#### Format: `after <datetime>`

#### Example: `after 1 Jan 2023`

<br/>

### `before` - Filter tasks before a date

Displays the tasks that fall *before* the given **datetime**.
There is a set pool of accepted datetime formats,
including (but not limited to): `yyyy-MM-DD HHmm`, `d/M/y H:m`, and `MMM d y`.

#### Format: `before <datetime>`

#### Example: `before 1 Jan 2023`

#### Aliases: `bef`

<br/>

### `filter` - Filter tasks by type

Displays the tasks that match the given **filter type**.
The categories (and accepted keywords) are as follows:
* To-Do Tasks:      `todo`, `td`, `t`
* Deadlines:        `deadline`, `dl`, `d`
* Events:           `event`, `ev`, `e`
* Marked Tasks:     `complete`, `completed`, `done`, `marked`, `x`
* Unmarked Tasks:   `!done`, `incomplete`, `not done`, `undone`, `unmarked`

#### Format: `filter <filter type>`

#### Example: `filter marked`