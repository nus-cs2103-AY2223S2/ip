# User Guide



## Features 

### List
List all your task that have been added into the task list.

### Add
Add a Todo, Deadline or Event into your task list.

### Mark, Un-mark and Delete
Mark or un-mark one or more task(s) as completed simultaneously.
You can also delete multiple tasks at the same time.

### Search
Search for tasks in the tasklist where the task description contains the words you search for.

### Auto-save
After modifying your task list, the changes are saved automatically.

## Usage
Note: In the usage, the following notation are used. They are not part of the command.
* `{` and `}` are shown around a parameter
* `(` and `)` are shown around **optional** parameter
* `...` indicates that the previous parameter can be repeated for multiple times 

### `todo` - Add a *Todo* Task
Add a *Todo* task, which need to be completed but has no completion timeline.\
Usage: `todo {task description}`

Expected outcome:\
The program will give a preview of the newly added *Todo*, 
and the current number of tasks in the task list.

<details>
<summary>Example</summary>

Sample input: `todo first task`\
Sample output:
```
Got it. I've added this task:
	[T][ ] first task
Now you have 1 task(s) in the list.
```
</details>


### `deadline` - Add a *Deadline* Task
Add a *Deadline* task, which is expected to be completed by a certain time.\
Usage: `deadline {task description} /by {due date}`\
Note: `{due date}` should be in **YYYY-MM-DD HH:MM(:SS)** format (seconds is optional)

Expected outcome:\
The program will give a preview of the newly added *Deadline*,
and the current number of tasks in the task list.

<details>
<summary>Example</summary>

Sample input: `deadline some other task /by 2023-01-01 00:00`\
Sample output:
```
Got it. I've added this task:
	[D][ ] some other task (By: 01 JANUARY 2023 00:00)
Now you have 2 task(s) in the list.
```
</details>

### `event` - Add an *Event* Task
Add a *Event* task, which is expected to be completed within a certian start and end time.\
Usage: `event {task description} /from {start time} /to {end time}`\
Note: `{start time}` and `{end time}` should be in **YYYY-MM-DD HH:MM(:SS)** format (seconds is optional)

Expected outcome:\
The program will give a preview of the newly added *Event*, and the current number of tasks in the task list.

<details>
<summary>Example</summary>

Sample input: `event even more tasks /from 2023-01-01 00:00 /to 2023-12-31 23:59`\
Sample output:
```
Got it. I've added this task:
	[E][ ] even more tasks (From: 01 JANUARY 2023 00:00 To: 31 DECEMBER 2023 23:59)
Now you have 3 task(s) in the list.
```
</details>

### `list` - List all tasks
List all the task in the task list.\
Usage: `list`,`ls`

Expected outcome:\
The program will print the type, status (completed or uncompleted), task description, and other revelent inforation
of the task for every single task in the task list.

<details>
<summary>Example</summary>

Sample input: `list`\
Sample output:
```
You have the following task(s):
	1. [T][ ] first task
	2. [D][ ] some other task (By: 01 JANUARY 2023 00:00)
	3. [E][ ] even more tasks (From: 01 JANUARY 2023 00:00 To: 31 DECEMBER 2023 23:59)
```
</details>

### `find` - Find tasks containing search words
Find task(s) that contains your search words.\
Usage: `find {search words}`

Expected outcome:
* Displays a list of tasks which the description contains the search words, or
* Indicate that there are no match found

<details>
<summary>Example</summary>

Sample input: `find tasks`\
Sample output:
```
Here are the matching tasks in your list:
	3. [E][ ] even more tasks (From: 01 JANUARY 2023 00:00 To: 31 DECEMBER 2023 23:59)
```
</details>

### `mark` - Indicate task(s) as completed
Mark one or more tasks as completed.\
Usage: `mark {task index} ({task index} ...)`

Expected outcome:\
The task(s) that are marked as completed will be indicated as `[X]`.

<details>
<summary>Example</summary>

Example #1:\
Sample input: `mark 2`\
Sample output:
```
Nice I've marked the task(s) as done:
	2. [D][X] some other task (By: 01 JANUARY 2023 00:00)
```

Example #2:\
Sample input: `mark 1 3`\
Sample output:
```
Nice I've marked the task(s) as done:
	1. [T][X] first task
	3. [E][X] even more tasks (From: 01 JANUARY 2023 00:00 To: 31 DECEMBER 2023 23:59)
```
</details>

### `unmark` - Indicate task(s) as not completed
Un-mark one or more tasks to indicate that the task has yet to be completed.\
Usage: `unmark {task index} ({task index} ...)`

Expected outcome:\
The task(s) that are marked as not completed will be indicated as `[ ]`.

<details>
<summary>Example</summary>

Example #1:\
Sample input: `unmark 2`\
Sample output:
```
OK, I've marked the task(s) as not done yet:
	2. [D][ ] some other task (By: 01 JANUARY 2023 00:00)
```

Example #2:\
Sample input: `mark 3 1`\
Sample output:
```
OK, I've marked the task(s) as not done yet:
	1. [T][ ] first task
	3. [E][ ] even more tasks (From: 01 JANUARY 2023 00:00 To: 31 DECEMBER 2023 23:59)
```
</details>

### `delete` - Delete task(s)
Delete one or more tasks from the task list.\
Usage: `delete {task index} ({task index} ...)`

Expected outcome:\
The task(s) are removed from the task list.

<details>
<summary>Example</summary>

Example #1:\
Sample input: `delete 2`\
Sample output:
```
I've removed the task(s):
	[D][ ] some other task (By: 01 JANUARY 2023 00:00)
Now you have 2 task(s) in the list.
```

Example #2:\
Sample input: `delete 2 1`\
Sample output:
```
I've removed the task(s):
	[E][ ] even more tasks (From: 01 JANUARY 2023 00:00 To: 31 DECEMBER 2023 23:59)
	[T][ ] first task
You do not have any task!
```
</details>

### `save` - Save changes
For peace of mind, manually save your task list to file. 
>  The program will auto-save your tasks whenever you make changes

Usage: `save`

### `exit` - Exit program
Save and exit the program.\
Usage: `exit`,`exit()`,`quit`,`quit()`,`:q`,`bye`,`goodbye`



## Program Arguments

You can use the following when launching the program to control its startup behaviour.

`--no-gui` : Launch program as a Command Line Interface (CLI)

`--no-load-saves` : Do not attempt to load save file, even if a save file exists. (Note: this will override existing 
save file)