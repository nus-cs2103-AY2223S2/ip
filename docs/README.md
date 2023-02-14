# User Guide

Welcome to the T-Rex Task Manager! You can play with this T-Rex and let it to help you manage your tasks!

## Quick Start

1. Download [Java 11](https://www.oracle.com/java/technologies/downloads/#java11) to your computer.
You can skip this step if you have Java 11 or above on your computer.

2. Download the newest version of [T-Rex.jar](https://github.com/Mr-Teal/ip/docs/T-Rex.jar).

3. Create a new folder to store the T-Rex.jar and the task list file.

4. Open a command terminal such as `cmd` or `powershell` by press Win+R and type `cmd` or `powershell`. `cd` into the folder you put the jar file in, and use the `java -jar T-Rex.jar` command to run the application. A GUI similar to the below should appear in a few seconds.

![This is an example use of T-Rex task manager](https://raw.githubusercontent.com/Mr-Teal/ip/master/docs/Ui.png)

5. Try typing some commands!

- `list`: list all the tasks saved

- `todo lash`: add a todo task called `lash`

- `mark 1`: mark the first task you added into the task list as finished

- `delete 1`: delete the first task you added into the task list

- `bye`: exit the task manager

6. Try even more commands with the help of the features below!

## Features 

### `list`

Lists all the task you have added into the task list. (Auto saved locally)

### `todo {task name}`

Adds a todo task with the name typed to the task list.

e.g. `todo lash`

### `deadline /by {time}`

Adds a deadline task with the name typed and the due time given to the task list.

`{time}` format: `yyyy-MM-dd` or `yyyy-MM-dd HH:mm` or any string

e.g. `deadline lash /by 2023-04-27 12:00`

### `event /from {time1} /to {time2}`

Adds an event task with the named typed and the start time and the end time given to the task list.

`{time 1/2}` format: any string

e.g. `event lash /from lapapa /to papala`

### `mark {task_index}`

Marks the task with given index as finished. Remember not to input an index larger than the number of tasks you added into the task list!

e.g. `mark 1` marks the first task in the task list as finished

### `unmark {task_index}`

Unmarks the task with given index as unfinished. Remember not to input an index larger than the number of tasks you added into the task list!

e.g. `unmark 1` unmarks the first task in the task list as unfinished

### `delete {task_index}`

Deletes the task with given index. Remember not to input an index larger than the number of tasks you added into the task list!

e.g. `delete 1` deletes the first task

### `find {keyword}`

Finds the tasks containing the given keyword.

e.g. If the task list currently contains tasks named `lash` `lashpi` `lapapa` `lashpilash` `labrary` (no matter which task type they are) respectively,
`find lash` will return `lash lashpi lashpilash`.

### `bye`

Exits the task manager.

### The task list is automatically saved in your local disk in a text file called `tasklist.txt` after each operation which changes the task list.

## Any bugs or suggestions please reflect to [Mr.Teal](https://github.com/Mr-Teal/).
