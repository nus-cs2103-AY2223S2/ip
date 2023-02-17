# User Guide
![Ui.png](Ui.png)
## Quick Start
1. Have Java 11 installed on your device.
2. Download the latest version of Duke from [here](https://github.com/runoutofit/ip/releases).
3. Copy the file to the folder you want to use as the home folder for Duke.
4. Double-click the file to start the app.
5. Enter your command in the text box and press enter to execute command. Refer to the features below on the various commands.
## Features 

### Feature-ToDo

Adds a Todo task into the list of tasks.

### Usage

### `todo` or `t`

Example of usage: 

`todo <description>`

Expected outcome:

```
Got it. I've added this task:
[T][]<description>
Now you have <number of tasks> tasks in the list.
```
### Feature-Deadline

Adds a deadline task into the list of tasks.

### Usage

### `deadline` or `d`

Example of usage:

`deadline <description> /by <MMM-dd-yyyy HHmm>`

Expected outcome:

```
Got it. I've added this task:
[D][]<description> (by: MMMM-dd-yyyy HHmm)
Now you have <number of tasks> tasks in the list.
```
### Feature-Event

Adds a event task into the list of tasks.

### Usage

### `event` or `e`

Example of usage:

`event <description> /from <start> /to <end>`

Expected outcome:

```
Got it. I've added this task:
[E][]<description> (from: <start> to: <end>)
Now you have <number of tasks> tasks in the list.
```
### Feature-list

Displays the list of tasks.

### Usage

### `list`

Example of usage:

`list`

Expected outcome:

```
1:[T][]return book
2[D][]assignment 1 (by: Jan-11-2023 2359)
```
### Feature-Mark

Sets task as done.

### Usage

### `mark`

Example of usage:

`mark 1`

Expected outcome:

```
Nice! I've marked this task as done:
[T][X]return book
```
### Feature-unMark

Sets task as not done.

### Usage

### `unmark`

Example of usage:

`unmark 1`

Expected outcome:

```
OK, I've marked this task as not done yet:
[T][]return book
```
### Feature-Delete

Removes a task from the list.

### Usage

### `delete`

Example of usage:

`delete 1`

Expected outcome:

```
Noted. I've removed this task:
[T][]return book
```
### Feature-Find

Find tasks containing the given keyword.

### Usage

### `find`

Example of usage:

`find <keyword>`

Expected outcome:

```
Here are the matching tasks in your list:
[[T][]<keyword>, [D][]<keyword> (by:Jan-11-2023 2359)]
```
### Feature-Bye

Saves current list to file and exit the program.

### Usage

### `bye`

Example of usage:

`bye`

Expected outcome:

```
Bye! Have a good day!
```