# User Guide

## Quick Start
1. Ensure you have java `11` or above installed in your Computer.
2. Download the latest `puke.jar` from [here](https://github.com/shuimeihe/ip/releases)
3. Copy the file to the folder you want to use as the home folder for your Puke.
4. Open a command terminal, `cd` into the folder you put the jar file in, and use the `java -jar puke.jar` command to run the application.
5. Type the command in the command box and press Enter to execute it.
6. Refer to the detailed [User Guide](#User-Guide) below for details of each command.


## Features

### Adding Todo : `todo`
Adds a Todo task to the task list.
Format: `todo DESCRIPTION`
Examples: `todo read book`

### Adding Deadline : `deadline`
Adds a Deadline to the task list.
Format: `deadline DESCRIPTION /by DEADLINE DAY`
:::warning
:bulb: Note: Dates should have the format `yyyy/MM/dd HHmm` 24-hour time format
:::
Examples: `deadline return book /by 2023/02/18 2359`

### Adding Event : `event`
Adds an Event to the task list.
Format: `event DESCRIPTION /from START_DATE_TIME /to END_DATE_TIME`
:::warning
:bulb: Note: Dates should have the format `yyyy/MM/dd HHmm` 24-hour time format
:::
Examples: `event book reading event /from 2023/02/15 0800 /to 2023/03/01 1800`

### List : `list`
Lists out all the tasks in the task list.
Format: `list`

### Mark (Done/ Not done) : `mark` `unmark`
Marks task as done or not done.
Format: `mark TASK_NUMBER` | `unmark TASK_NUMBER`
:::warning
:bulb: Note: `TASK_NUMBER` is the number as appears in the list
:::
Examples: `mark 1` | `unmark 3`

### Delete : `delete`
Deletes a task from the task list.
Format: `delete TASK_NUMBER`
:::warning
:bulb: Note: `TASK_NUMBER` is the number as appears in the list
:::
Examples: `delete 1`

### Find : `find`
Searches for tasks whose descriptions contains the keyword.
Format: `find KEYWORD`
:::info
Note: keywords are case-sensitive (for now)
:::
Examples: `find book`

### Archive: `archive`
Stores your current task list into an archive file in `\data\archive.txt`
Format: `archive`
:::danger
:warning: This command clears your current task list
:::

### Exiting the program: `bye`
Saves your task lists and exit program.
Format: `exit`
