# User Guide

## **\*\*\*LEVEL 10 CLEARANCE REQUIRED\*\*\***
UNAUTHORIZED PERSONNEL LEAVE IMMEDIATELY

Product is the first successful test of an experiment to incorporate the mind of the great Sith Lord Darth Vader into normal software. Hydra scientists have been able to infest a simple, text-based task logging application called Duke with the Dark Side Energy, thereby relieving personnel of remedial jobs like remembering tasks, allowing Hydra to focus on taking over the world. Product is offered with both CLI and GUI versions.

Use with caution

## Setting up and Running Duke
1. Install Java SDK 11
2. Download 'Duke.jar'
3. Open terminal
4. Run the default command 'java -jar Duke.jar' to launch the application in CLI version
5. User will be asked whether he wants to run Duke. Answer with `YES` or `NO`
6. Upon exiting Duke, user will be asked whether he wants to save the current list of tasks. Answer with `YES` or `NO`

## Running different versions of Duke

1. To launch CLI (default) version:

```
java -jar Duke.jar
```

2. To launch GUI version:

```
java -jar Duke.jar GUI
```



## Features

### `TODO`: Add a To-Do task

Add a To-Do task to the list of tasks.

Format: `TODO {action}`
**`action`** cannot be empty

Example: `TODO DRINK WATER`
Add a To-Do task to the list of tasks with the action *DRINK WATER*

### `DEADLINE`: Add a Deadline task

Add a task with deadline to the list of tasks.

Format: `DEADLINE {action} /BY {deadline}`

**`action`** cannot be empty
**`deadline`** must be of format `dd-MM-yyyy HHmm`

Example: `DEADLINE SUBMIT HW /BY 20-02-2023 2359`
Add a Deadline task to the list of tasks with the action *SUBMIT HW* and deadline *20-02-2023 2359*

### `EVENT`: Add an Event task

Add an Event task to the list of tasks.

Format: `EVENT {action} /FROM {date_begin} /TO {date_end}`

**`action`** cannot be empty
**`date_begin`** and **`date_end`** must be of format `dd-MM-yyyy HHmm`

Example: `EVENT BINGE MOVIES /FROM 20-02-2023 2359 /TO 24-02-2023 2300`
Add an Event to the list of tasks with the action *BINGE MOVIES*, date_begin *20-02-2023 2359*, and date_end *24-02-2023 2300*

### `LIST`: List all tasks

List all available tasks in the list of tasks

Format: `LIST`

### `MARK`/ `UNMARK`: Mark/ Unmark a task

Mark or unmark a task in the list of tasks with a specified index

Format: `MARK {index}` or `UNMARK {index}`

**`index`** cannot be lower than 0, or higher than the current number of tasks

Example: `MARK 4`/ `UNMARK 5`

### `DELETE`: Delete a task

Delete a task in the list of tasks with a specified index

Format: `DELETE {index}`

**`index`** cannot be lower than 0, or higher than the current number of tasks

Example: `DELETE 2`

### `BYE`: Exiting Duke

Format: `BYE`

### `FIND`: Find a task using containning a specified keyword

Find all tasks in the list of tasks with a specified keyword. User can also search for all To-Do, Deadline, and Event tasks by searching using `TODO`, `DEADLINE`, and `EVENT` as the keyword

Format: `FIND {keyword}`

Example: `FIND WATCH`

### `SCHEDULE`: Find a schedule of all events on a specified date

Find all tasks in the list of tasks that occurs on a specific date. These includes `DEADLINE`, and `EVENT`. `DEADLINE` tasks will be organized according to the time of the deadline, while `EVENT` tasks are considered all-day

Format: `FIND {keyword}`

Example: `FIND WATCH`
