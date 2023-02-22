# Duke User Guide

Duke is a chatbot that helps you to track your tasks via a **Command Line Interface (CLI)**. 
Duke allows you to quickly perform tasks by typing commands into the chatbox.

## Features 
- [X] Track Deadlines, Todos and Events
- [X] Mark tasks as done
- [X] Store data on your local device
- [X] Sort your task list for easy management
- [X] Search for relevant tasks

## Installation Guide
1. Ensure that you have Java 11 installed on your computer. If you don't have it installed, you can get it 
[here](https://www.oracle.com/java/technologies/downloads/#java11).
4. Download the latest release of Duke from [here](https://github.com/clydelhui/ip/releases)
5. Open a terminal in the directory which contains the `duke.Launcher.jar` file that you downloaded
6. Run the command `java -jar .\duke.Launcher.jar`

## Usage

### Listing items currently in the list: `list`

Lists all the items currently stored by Duke in the order it is stored.
<br /> Format: `list`
<br />
<br />

### Adding a todo: `todo`
Adds a todo to Duke's task list.<br />
Format: `todo [task]`<br /><br />
Examples:
- `todo buy eggs`
- `todo feed cat`
<br />
<br />


### Adding a deadline: `deadline`
Adds a deadline to Duke's task list.<br />
Format: `deadline [task] /[YYYY-MM-DD]`<br /><br />
Examples:
- `deadline file taxes /2023-03-15`
- `deadline buy presents /2023-04-13`
<br />
<br />


### Adding an event: `event`
Adds an event to Duke's task list.<br />
Format: `event [task] /[YYYY-MM-DD]/[YYYY-MM-DD]`<br /><br />
- The first date is the start date of the event and the second is the end date
Examples:
- `event food fair /2018-03-10/2018-03-11`
- `event project showcase /2018-05-23/2018-05-26`
<br />
<br />

### Marking a task: `mark`
Marks an existing task as done.<br />
Format: `mark [task number]`<br />
- Marks the task as done
- The `task number` refers to the index number shown in the displayed task list.
- The index must be a positive integer starting from 1.<br /><br />
  Examples:
- `mark 1`
- `mark 3`
<br />
<br />

### Un-marking a task: `unmark`
Un-marks an existing task as not done.<br />
Format: `unmark [task number]`<br />
- Un-marks the task
- The `task number` refers to the index number shown in the displayed task list.
- The index must be a positive integer starting from 1.<br /><br />
  Examples:
- `unmark 1`
- `unmark 3`
<br />
<br />

### Locating a task: `find`
Find tasks whose description contain the given keyword.<br />
Format: `find [keyword]`<br />
- The search is case-sensitive.<br /><br />
  Examples:
- `find assignment` returns `[T][X]todo math assignment`
- `find science` returns `[D][X]deadline science homework /2023-03-15`
<br />
<br />


### Deleting a task: `delete`
Deletes the task at the specified task number from the task list.<br />
Format: `delete [task number]`<br />
- Deletes the task
- The `task number` refers to the index number shown in the displayed task list.
- The index must be a positive integer starting from 1.<br /><br />
  Examples:
- `delete 1`
- `delete 2`
<br />
<br />

### Sorting the task list: `sort`
Sorts the items in the task list by description.<br />
Format: `sort`
<br />
<br />

### Exiting the program: `bye`
Exits the program after saving the data in the task list.<br />
Format: `bye`
<br />
<br />

### Exiting the program forcefully: `forcequit`
Exits the program even if the data cannot be saved.<br />
Format: `forcequit`
<br />
<br />


### Saving the data
Duke's data is saved in the hard disk automatically in your local directory where your `duke.Launcher.jar` file 
is saved in after any command that changes the data. You do not need to save the data manually.
<br />
<br />
***