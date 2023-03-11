# User Guide

Duke is a classy offline task manager written in Java, and it is CLI-based. It functions like a personal assistant, in which a user can chat with Duke and get appopriate responses.

## Quick start
1. Ensure you have Java 11 or above installed in your device.
2. Download the latest duke.jar from [here](https://r.mtdv.me/dukedownload).
3. Copy the file to the folder to be the home folder for Duke. 
4. Open a command terminal, `cd` into the folder and use the `java -jar duke.jar` to run the application.

## Features 

### Listing all tasks `list`

Shows all the tasks that have been added to the list.

Format: `list`

### Finding specific tasks `find`

Finds a task that contains a specific keyword in the task description.

Format: `find KEYWORD`

Examples:

- `find CS2103T` Finds tasks that contains the keyword **CS2103T** in their description.

### Adding a todo task `todo`

Adds a todo task.

Format: `todo TASK_DESCRIPTION`

Examples:
- `todo Do homework` Adds the task **Do homework**.

### Adding a task with deadline `deadline`

Adds a task with deadline.

Format: `deadline TASK_DESCRIPTION /by DATE_AND_TIME`

Examples:
- `deadline Submit CS2103T quiz /by 23:59` Adds the task **Submit CS2103T quiz** by **23:59**.
- `deadline Hand in assignment /by 2023-06-09` Adds the task **Hand in assignment** by **2023-06-09**.

> *Note: The DATE_AND_TIME can consist of any date and time components.*

> *DATE_AND_TIME must be of this format: yyyy-MM-dd HH:mm.*

### Adding an event `event`

Adds an event task with start and end time.

Format: `event TASK_DESCRIPTION /from START_DATE_AND_TIME /to END_DATE_AND_TIME`

Examples:
- `event Meeting /from 15:00 /to 17:00` Adds an event **Meeting** from **15:00** to **17:00**.
- `event Hackathon /from 2023-09-06 00:00 /to 2023-09-09` Adds an event **Hackathon** from **2023-09-06** to **2023-09-09**.

> *Note: The DATE_AND_TIME can consist of any date and time components.*

> *DATE_AND_TIME must be of this format: yyyy-MM-dd HH:mm.*

### Marking a task as done `mark`

Marks a task as done.

Format: `mark TASK_NUMBER`

Examples:
- `mark 3` Marks the third task as done.

> *Note: The TASK_NUMBER must be **non-negative integer**, and must range from **1 to TASK_NUMBER** .*

### Marking a task as undone `unmark`

Marks a previously marked done task as undone.

Format: `unmark TASK_NUMBER`

Examples:
- `mark 5` Marks the fifth task as undone.

> *Note: The TASK_NUMBER must be **non-negative integer**, and must range from **1 to TASK_NUMBER** .*

### Checking of the existence of duplicate tasks `check_duplicate`

Checks if there are any duplicate tasks. Returns a positive outcome if duplicate tasks are found and returns their respective task numbers, negative outcome otherwise.

Format: `check_duplicate`

### Deleting a task `delete`

Deletes a task from the list.

Format: `delete TASK_NUMBER`

Examples:
- `delete 7` Deletes the seventh task from the list. 

> *Note: The tasks after TASK_NUMBER will be moved forward by 1.*

> *Note: The TASK_NUMBER must be **non-negative integer**, and must range from **1 to TASK_NUMBER** .*

### Exiting Duke `bye`

Exits the application.

Format: `bye` 
