### DukeBot

> "A goal without a plan is just a wish." Antoine de Saint-Exupéry [source](https://www.quotespedia.org/authors/a/antoine-de-saint-exupery/a-goal-without-a-plan-is-just-a-wish-antoine-de-saint-exupery/)

DukeBot makes planning so much easier, so you can finally reach your goals. It is:

- command-line friendly
- intuitive
- simple
- ~beautiful~  **ULTRA SEXY** user interface

_You're only one step away from making planning so much more fun and easy!_

All you need to do is download it from [here](https://github.com/Oliverloo0909/ip/releases/tag/A-Jar).

1. wake up duke
2. tell it your tasks
3. That's it!

You'll never worry about missing any tasks 😉
it is **FREE**!

Features:
- [ ]  Managing tasks
- [ ]  Managing deadlines
- [ ] Managing Events


# User Guide

## Features

### Add tasks

Add three types of tasks : todo, deadline or event. ``` todo, deadline /by, event /from /to ```

### Show All tasks

Show all tasks you have added. ``` list ```

### Mark/Unmark tasks

Mark a task as done or not done. ``` mark, unmark ```

### Delete tasks

Delete a task from your list. ``` delete ```

### Find

Find specific tasks using keywords ``` find ```

### Snooze

Snooze tasks in your list ``` snooze /to ```


## Usage

### `todo` - adds todo

Example of usage:

`todo run`

Expected outcome:

todo is added.

```
added: [T][] run
```

### `deadline` - adds deadline

Example of usage:

`deadline pee /by 2023-10-10 10:00`

Expected outcome:

deadline is added.

```
added [D][] pee (by:Oct 10 2023 10 AM)
```

### `event` - adds event

Example of usage:

`event dancing /from 2023-10-10 10:00 /to 2023-10-10 11:00`

Expected outcome:

event is added.

```
added added [E][] dancing (from:Oct 10 2023 10 AM to:Oct 10 2023 11 AM)
```
### `list` - displays all tasks

Example of usage:

`list`

Expected outcome:

list of tasks are displayed.

```
1. T [X] poo
2. T [ ] pee
```

### `mark` - marks a task as done

Example of usage:

`mark 1`

Expected outcome:

task is marked as done.

```
Nice! I've marked this task as done: T [X] poo
```

### `unmark` - marks a task as not done

Example of usage:

`unmark 1`

Expected outcome:

task is marked as not done.

```
OK, I've marked this task as not done yet: T [ ] poo
```

### `delete` - deletes a task from the list

Example of usage:

`delete 1`

Expected outcome:

task is deleted from the list.

```
Noted. I've removed this task  T [ ] poo. 
Now you have 2 tasks in the list
```

### `snooze` - snoozes deadlines to a later date

Example of usage:

`snooze 1 /to 2022-10-10 10:00`

Expected outcome:

deadline is snoozed.

```
[D] [ ] pee (by: Oct 10 2020 10 AM) has been snoozed to pee (by: Oct 10 2020 11 AM)
