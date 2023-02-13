# User Guide
Duke is a **desktop application for managing your tasks via a Command Lind Interface** (CLI) while still 
having 
the benefits of a Graphical User Interface (GUI).

***
## Quick Start
1. Ensure you have Java 11 or above installed in your computer. Else, you can install it from [here]
   (https://www.oracle.com/java/technologies/downloads/#java11).
2. Download the latest duke.Duke.jar from [here](duke.Duke.jar).
3. Copy the file to the folder you want to use as the home folder for your Duke.
4. Open a command terminal. `cd` into the folder you put the jar file in, and use the `java -jar duke.Duke.
   jar` command to run the application. A GUI similar to the below should appear in a few seconds.
<figure>
    <img src="/docs/images/starting_display.png"
         alt="Starting GUI">
</figure>

5. Type the command in the input box and press `Send` to execute it. Some example commands you can try:
- `list`: List all tasks.
- `todo grcoeries`: Adds a todo task with `groceries` as its description to the task list.
- `mark 1`: Marks the 1st contact as done.
- `delete 1`: Deletes the 1st contact as done.
- `bye`: Exits the app.
6. Refer to the [Features](##features) below for details of each command.

***
## Features
### Listing all tasks: list
Shows a list of all tasks in the task list.


### Adding a todo: `todo`
Adds a todo task to the task list.  
Format: `todo [task]`
Examples:
- `todo homework`
- `todo laundry`


### Adding a deadline: `deadline`
Adds a deadline to the task list.  
Format: `deadline [task] /by [YYYY-MM-DD]`  
Examples:
- `deadline return book /by 2019-10-15`
- `deadline homework /by 2019-10-30`


### Adding an event: `event`
Adds an event to the task list.  
Format: `event [task] /from [YYYY-MM-DD] /to [YYYY-MM-DD]`  
Examples:
- `event project /from 2018-03-10 /to 2018-03-11`
- `event camp /from 2018-05-23 /to 2018-05-26`


### Marking a task: `mark`
Marks an existing task as done.  
Format: `mark [task number]`  
- Marks the task of the specified `task number`.
- The `task number` refers to the index number shown in the displayed task list.
- The index must be a positive integer starting from 1.  
Examples:
- `mark 1`
- `mark 3`


### Un-marking a task: `unmark`
Un-marks an existing task as not done.  
Format: `unmark [task number]`  
- Un-marks the task of the specified `task number`.
- The `task number` refers to the index number shown in the displayed task list.
- The index must be a positive integer starting from 1.  
Examples:
- `unmark 1`
- `unmark 3`


### Prioritizing a task: `prioritize`
Prioritize an existing task with the priority level.  
Format: `prioritize [task number] [priority level]`  
- Prioritizes the task at the specified `task number` with the specified level.
- There are 3 priority levels: high, medium and low
- The `task number` refers to the index number shown in the displayed task list.
- The index must be a positive integer starting from 1.  
Examples:
- `prioritize 1 high`
- `prioritize 2 medium`
- `prioritize 3 low`


### Tagging a task: `tag`
Tags an existing task with the tag name specified.  
Format: `tag [task number] [tag name]`  
- Tags the task at the specified `task number`.
- The `task number` refers to the index number shown in the displayed task list.
- The index must be a positive integer starting from 1.  
Examples:
- `tag 1 school`
- `tag 2 work`


### Locating a task: `find`
Find tasks whose description or date contain the given keyword.  
Format: `find [keyword]`  
- The search is case-sensitive  
Examples:
- `find groceries` returns `[T][X] groceries`
- `find 10 Mar` returns `[D][ ] return toy (by: 10 Mar 2019)`


### Deleting a task: `delete`
Deletes the task at the specified task number from the task list.  
Format: `delete [task number]`  
- Deletes the task at the specified `task number`
- The `task number` refers to the index number shown in the displayed task list.
- The index must be a positive integer starting from 1.  
Examples:
- `delete 1`
- `delete 2`


### Exiting the program: `bye`
Exits the program.  
Format: `bye`


### Saving the data
Duke data are saved in the hard disk automatically after any command that changes the data. There is no 
need to save manually.

***
## Commands Summary
| Action     | Format, Examples                                                                                             |
|------------|--------------------------------------------------------------------------------------------------------------|
| list       | `list`                                                                                                       |
| todo       | `todo [task]`<br/>e.g., `todo homework`                                                                      |
| deadline   | `deadline [task] /by [YYYY-MM-DD]`<br/>e.g., `deadline return book/by 2019-10-15`                            |
| event      | `event [task] /from [YYYY-MM-DD] /to [YYYY-MM-DD]`<br/>e.g., `event project /from 2018-03-10 /to 2018-03-11` |
| mark       | `mark [task number]`<br/>e.g., `mark 1`                                                                      |
| unmark     | `unmark [task number]`<br/>e.g., `unmark 1`                                                                  |
| prioritize | `prioritize [task number] [priority level (high/medium/low)]`<br/>e.g., `prioritize 1 high`                  |
| tag        | `tag [task number] [tag name]`<br/>e.g., `tag 1 school`                                                      |
| find       | `find [keyword]`<br/>e.g., `find school`                                                                     |
| delete     | `delete [task number]`<br/>e.g., `delete 1`                                                                  |
| bye        | `bye`                                                                                                        |




