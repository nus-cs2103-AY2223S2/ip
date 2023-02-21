# User Guide
Page is a Medieval-themed desktop app for managing your tasks.
Your loyal Page will help you keep track of your exploits in the Quest Log!
It has the speed and efficiency of a Command Line Interface (CLI),
with the aesthetics of a Graphical User Interface (GUI).
## Features

### Quest Log

- Add or delete quests in the Quest Log, and mark them as complete when you're done!
- You can also view the full Quest Log at any time, to see everything you have to accomplish at once.

### Different Quest Types for Different Needs

- Todos are quests with a simple task description.
- Deadlines are quests with a finish-by time.
- Events are quests with a start and end time.

Use the quest type that suits your needs!

### Find Your Quests
Have too many quests? Don't worry! <br>
Page can search through your Quests to find everything containing a given keyword.
Use it to find all your "Math" assignments, for example.

### Edit Your Quests
Made a mistake while adding a quest? Or perhaps your deadlines have changed? <br>
Page allows you to make edits to your quests, without having to delete and re-add them.

## Usage

### How To Read This Guide

- Words in `UPPER CASE` are the parameters to be supplied by the user.<br>
e.g. in `todo DESCRIPTION`, `DESCRIPTION` is a parameter. 
You could use it like `todo Clean my room`, or `todo Read math notes`.
- Commands are case-sensitive. <br>
Only `todo Clean my room` is a valid command, not `Todo` or `ToDo` or `TODO Clean my room`.

### `help` - View the help message

Displays the help message. <br>
Here you can view instructions on how to use all the commands available in Page.

Format: `help`

### `todo` - Add a Todo quest

Adds a Todo quest to the Quest Log. <br>
A Todo quest has only a text description.

Format: `todo DESCRIPTION`

Example of usage: `todo Clean my room`

Expected outcome:

A Todo quest with the given description will be added to the Quest Log.

```
Added to Quest Log:
[T] [_] Clean my room
```

### `deadline` - Add a Deadline quest

Adds a Deadline quest to the Quest Log. <br>
A Deadline quest has a text description and a complete-by date and time.

Format: `deadline DESCRIPTION /by DATETIME` <br>
`DATETIME` must be in the `HHmm dd/MM/yy` format, e.g. `2359 31/12/99`

Example of usage: `deadline Submit assignment /by 2359 20/02/23`

Expected outcome:<br>
A Deadline quest with the given description and complete-by date and time will be added to the Quest Log.

```
Added to Quest Log:
[D] [_] Submit assignment by: 20 Feb 2023 11:59PM
```

### `event` - Add an Event quest

Adds an Event quest to the Quest Log. <br>
A Event quest has a text description, a start date and time, and an end date and time.

Format: `event DESCRIPTION /from DATETIME /to DATETIME` <br>
`DATETIME` must be in the `HHmm dd/MM/yy` format, e.g. `2359 31/12/99`

Example of usage: `event Math exam /from 1000 21/02/23 /to 1200 21/02/23`

Expected outcome:<br>
An Event quest with the given description, start date and time, and end date and time will be added to the Quest Log.

```
Added to Quest Log:
[E] [_] Math exam from: 21 Feb 2023 10:00AM to: 21 Feb 2023 12:00PM
```

### `log` - Display the Quest Log

Displays the Quest Log, containing all quests you've added to Page so far. <br>

Format: `log`

Expected outcome:<br>
All the quests you've added to Page so far will be shown.

```
Quest Log:
1: [T] [_] Clean my room
2: [D] [_] Submit assignment by: 20 Feb 2023 11:59PM
3: [E] [_] Math exam from: 20 Feb 2023 10:00AM to: 20 Feb 2023 12:00PM
```

### `complete` - Mark a quest as complete

Marks a specified quest as complete. <br>
The quest is specified by its index number in the Quest Log (which can be seen with `log`).

Format: `complete INDEX` <br>
`INDEX` should be an integer from 1 up to the number of quests in the Quest Log (inclusive).

Example of usage: `complete 2`

Expected outcome:<br>
The quest with the given index will be marked complete with an `X`.

```
Quest Complete! The bards shall sing of your victory!
[D] [X] Submit assignment by: 20 Feb 2023 11:59PM
```

### `incomplete` - Mark a quest as incomplete

Marks a specified quest as incomplete. <br>
The quest is specified by its index number in the Quest Log (which can be seen with `log`).

Format: `incomplete INDEX` <br>
`INDEX` should be an integer from 1 up to the number of quests in the Quest Log (inclusive).

Example of usage: `incomplete 2`

Expected outcome:<br>
The quest with the given index will be marked incomplete (the `X` will be removed).

```
Quest Incomplete?? Oh no!
[D] [_] Submit assignment by: 20 Feb 2023 11:59PM
```

### `find` - Finding quests by keyword

Displays all quests containing the given keyword in their description.

Format: `find KEYWORD` <br>
The keyword matching is not case-sensitive, 
so `find Math`, `find math`, or `find MATH` will all give the same results.

Example of usage: `find clean`

Expected outcome:<br>
All quests containing the given keyword will be displayed.

```
Greetings sire, here are the matching quests in the Quest Log:
[T] [_] Clean my room
```

### `edit` - Edit a quest

Edits the details of a specified quest. <br>
The quest is specified by its index number in the Quest Log (which can be seen with `log`).


Format:
1. for Todos: `edit INDEX [DESCRIPTION]`
2. for Deadlines: `edit INDEX [DESCRIPTION] [/by DATETIME]`
3. for Events: `edit INDEX [DESCRIPTION] [/from DATETIME] [/to DATETIME]`

`INDEX` should be an integer from 1 up to the number of quests in the Quest Log (inclusive). <br>
The parameters in square brackets are optional, although at least 1 should be present. <br>
The format of the command must match the type of quest at the given index, or errors may occur.

Example of usage: `edit 3 Stats exam /from 1000 20/03/23 /to 1200 20/03/23`


Expected outcome:<br>
The quest at the given index will be edited with the new input values.

```
Quest Edited!
[E] [_] Stats exam from: 20 Mar 2023 10:00AM to: 20 Mar 2023 12:00PM
```


### `bye` - Exits the program

Saves the current Quest Log onto your hard drive, then closes the Page program.

Format: `bye`

Expected outcome: <br>
Page is closed. The Quest Log is saved in `data/questlog.txt`, in the same folder as the Page program.


