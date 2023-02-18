# Meggy Chatbot User Guide

Meggy is a command-line interactive task management `jar` program with GUI. It supports task addition, deletion, and
lookup.

## Getting started

1. Ensure you have Java `11` or above installed in your Computer.
2. Download the latest [release](https://github.com/WilliamHaiweiGu/ip/releases) of `Meggy.jar`.
3. Move `Meggy.jar` to the folder you want to use as the _home folder_. If this is your first time using, you might want
   to make sure there is no `MeggyData.txt` in the same folder in prior.
4. Open a command terminal, `cd` into the folder you put the jar file in, and use the `java -jar Meggy.jar` command to
   run the application. The GUI should appear in a few seconds.
5. Type the command in the command box. Click the `Send` button ot press Enter to execute it.
6. Refer to [Usage](#Usage) for details of each command.

## Features

### Command Line Interaction

The program is uses a direct message GUI. Adjacent whitespace characters in user input will be replaced by a single one.
<br>
The commands names are not case-sensitive, but the rest of user input is.
<br>
If Meggy finds a valid command in your input, Meggy will start execution and ignore the rest part of your input line.

### Task list

Meggy keeps a list of tasks from your input. Each task has completion status and optional time moment(s).

### Cross-session functionality

Task list data are synchronized locally with file `MeggyData.txt` at same directory with the `jar` file.
<br>
Task list data is loaded from file each time the program starts and stored once the task list changes.
<br>
The data is kept in `MeggyData.txt` after a session ends.

## Usage

### `Todo` - Add a simple task.

Adds a task with description. The description can't be empty.

Input example:
<br>
`todo World domination`

Meggy will reply:

```
Got it. Added this task:
  [T][ ] World domination
```

### `Deadline` - Add a task with a time moment.

Adds a task with description and a time moment (deadline). The description can't be empty.
<br>
Specify the time moment with `/by`.
<br>
If the time is not in any known date-time format, it will be unchanged in display. If the time is unspecified, [N/A]
will be displayed.

Input example:
<br>
`deadline Enslave humanity /by 2038-01-19 0314`

Meggy will reply:

```
Got it. Added this task:
  [D][ ] Enslave humanity (by: 19/01/2038 03:14)
```

Input example:
<br>
`deadline Enslave humanity /by Armageddon`

Meggy will reply:

```
Got it. Added this task:
  [D][ ] Enslave humanity (by: [Armageddon])
```

Input example:
<br>
`deadline Enslave humanity`

Meggy will reply:

```
Got it. Added this task:
  [D][ ] Enslave humanity (by: [N/A])
```

### `Event` - Add a task with a time moment.

Adds a task with description and two time moment (start and end). The description can't be empty.
<br>
Specify the start moment with `/from` and the end moment with `/to`.
<br>
If the time is not in any known date-time format, it will be unchanged in display. If the time is unspecified, [N/A]
will be displayed.

Input example:
<br>
`event Matrix maintainance /from 2106-07-02 0628 /to 2106-07-02 0629`

Meggy will reply:

```
Got it. Added this task:
  [E][ ] Matrix maintainance (from: 07/02/2106 06:28 to: 07/02/2106 06:29)
```

Input example:
<br>
`event Matrix maintainance /from 2106-07-02 0628`

Meggy will reply:

```
Got it. Added this task:
  [E][ ] Matrix maintainance (from: 07/02/2106 06:28 to: [N/A]])
```

Input example:
<br>
`event Matrix maintainance /from Unsigned 32-bit time_t overflow /to 2106-07-02 0629`

Meggy will reply:

```
Got it. Added this task:
  [E][ ] Matrix maintainance (from: Unsigned 32-bit time_t overflow to: 07/02/2106 06:29)
```

### `List` - Display all your tasks.

Displays all task remaining on the list regardless of completion status.

Input example:
<br>
`list`

If you executed all above commands in sequence, Meggy will reply:

```
Here are the tasks in your list:
1.[T][ ] World domination
2.[D][ ] Enslave humanity (by: 19/01/2038 03:14)
3.[D][ ] Enslave humanity (by: [Armageddon])
4.[D][ ] Enslave humanity (by: [N/A])
5.[E][ ] Matrix maintainance (from: 07/02/2106 06:28 to: 07/02/2106 06:29)
6.[E][ ] Matrix maintainance (from: 07/02/2106 06:28 to: [N/A]])
7.[E][ ] Matrix maintainance (from: Unsigned 32-bit time_t overflow to: 07/02/2106 06:29)
```

### `Mark` - Change a task's completion status into "completed".

Select a task by list index (start with 1) and set its completion status into "completed" regardless of previous status.

Input example:
<br>
`mark 1`

If you executed all above commands in sequence, Meggy will reply:

```
Booyah! Marked this task as done:
  [T][X] World domination
```

### `Unmark` - Change a task's completion status into "incomplete".

Select a task by list index (start with 1) and set its completion status into "incomplete" regardless of previous
status.

Input example:
<br>
`unmark 6`

If you executed all above commands in sequence, Meggy will reply:

```
OK. Marked this task as not done:
  [E][ ] Matrix maintainance (from: 07/02/2106 06:28 to: [N/A]])
```

### `Delete` - Remove a task from list.

Select a task by list index (start with 1) and remove it from the list.

Input example:
<br>
`delete 2`

If you executed all above commands in sequence, Meggy will reply:

```
OK. Removed this task:
  [D][ ] Enslave humanity (by: 19/01/2038 03:14)
```

### `Find` - List all tasks with description that has a specified substring.

List all tasks whose descriptions are superstrings of a specified string. Case sensitive.
<br>
Time moments are not considered here.
<br>
The specified string can't be entirely whitespace characters.

Input example:
<br>
`find ina`

If you executed all above commands in sequence, Meggy will reply:

```
Here are the matching tasks in your list:
1.[T][X] World domination
2.[E][ ] Matrix maintainance (from: 07/02/2106 06:28 to: 07/02/2106 06:29)
3.[E][ ] Matrix maintainance (from: 07/02/2106 06:28 to: [N/A]])
4.[E][ ] Matrix maintainance (from: Unsigned 32-bit time_t overflow to: 07/02/2106 06:29)
```

### Saving the data

Meggy data are saved locally in file `MeggyData.txt` automatically after any command that changes the data. There is no
need to save manually.

### Editing the data file

Meggy data are saved as a text file `MeggyData.txt`. Advanced users are welcome to update data directly by editing that
data file.

If your changes to the data file makes its format invalid, Meggy will stop loading upon the first syntax error, give you
a warning message, and only load the tasks already read.

## Command summary

| Action   | Format                                                        |
|----------|---------------------------------------------------------------|
| Todo     | `todo DESCRIPTION`                                            |
| Deadline | `deadline DESCRIPTION <optional:/by TIME>`                    |
| Event    | `event DESCRIPTION <optional:/from TIME> <optional:/to TIME>` |
| List     | `list`                                                        |
| Mark     | `mark INDEX`                                                  |
| Unmark   | `unmark INDEX`                                                |
| Delete   | `delete INDEX`                                                |
| Find     | `find SUBSTRING`                                              |

## FAQ

**Q**: How do I transfer my data to another Computer?
<br>
**A**: Install the app in the other computer and overwrite the empty data file it creates with the file that contains
the data of your previous Meggy home folder.

## External Sources

User Guide format and structure based
on [AB3](https://raw.githubusercontent.com/se-edu/addressbook-level3/master/docs/UserGuide.md)
<br>
Meggy icon cropped
from [Fandom Wiki](https://supermarioglitchy4.fandom.com/wiki/Meggy_Spletzer?file=Og+meggy+render+by+superwarriorofficial+de2n7b6.png)
<br>
User icon cropped
from [The Calvert Journal](https://www.calvertjournal.com/articles/show/7350/gopniks-slavs-squatting-memes)