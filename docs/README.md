# User Guide

Duke is a CLI-based desktop app for tracking your daily task and schedule. It is your personal chatbot 
which you can talk to him and get his immediate response.

## Quick start
1. Ensure you have Java 11 or above installed in your device.
2. Download the latest duke.jar from [here](www.google.com).
3. Create a folder to be the home folder and move duke.jar to this folder. 
4. Open a command terminal, `cd` into the folder and enter this command `java -jar duke.jar` to get started.

## Features 
- Note that the commands below are case sensitive.
- 
### Adding a todo task `todo`

Adds a todo task with description of the task. This is a task without explicit deadline.

Format: `todo DESCRIPTION`

Examples:
- `todo Do assignment` Adds a task with description: 
Do assignment.
- `todo Buy glossary` Adds a task with description: Buy glossary.

*Note that: The description cannot be empty.*

### Adding a task with deadline `deadline`

Adds a task with explicit deadline.

Format: `deadline DESCRIPTION /by DATE_AND_TIME`

Examples:
- `deadline Submit homework /by 2023-02-02 23:59` Adds a task
with deadline 2023-02-02 23:59

### Adding an Event with start and end time `event`

Adds a task with start and end time.

Format: `event DESCRIPTION /from START_DATE_AND_TIME /to
END_DATE_AND_TIME`

Examples:
- `event Meeting /from 2023-01-09 21:00 /to 2023-01-09 22:00` Adds an event of 
meeting from 9 Jan 2023 9:00 pm to 10:00 pm.

### Marking a task as done `mark`

Mark a task as done.

Format: `mark TASK_NUMBER`
- Mark the task at the TASK_NUMBER position of the task list as done.
- The TASK_NUMBER starts from 1. 

Examples:
- `mark 2` Mark the second task in the list as done.

### Marking a task as not done `unmark`

Mark a task as not done.

Format: `unmark TASK_NUMBER`
- Mark the task at the TASK_NUMBER position of the task list as **not** done.
- The TASK_NUMBER starts from 1.

Examples:
- `unmark 3` Mark the third task in the list as not done.

### Listing all tasks `list`

Shows all the tasks that have been added to the list.

Format: `list`

### Exit `bye` 

Exit the application.

Format: `bye`

### Finding a task `find`

Finds a task by specifying a part of the description of the task.

Format: `find SOME_KEYWORD`

Examples:
- `find meeting` Finds a task with the keyword meeting in its description.

### Deleting a task `delete`

Delete a task from the list.

Format: `delete TASK_NUMBER`

Examples:
- `delete 2` Deletes the second task from the list. The tasks behind this task will shift to the front.

### Check for duplicate task `check_duplicate`

Checks if there're any duplicate tasks.

Format: `check_duplicate` 
- Checks for duplicate tasks in the list.
- Shows them with their task number.



