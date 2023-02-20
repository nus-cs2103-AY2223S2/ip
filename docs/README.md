# User Guide

## Features 

### Adding a todo task: `todo`
Adds a task to be done without specifying timeline\
Format:`todo TASK`\
Examples:
- `todo attend CS2103T tutorial`
- `todo submit CS2103 iP`

### Adding a deadline task: `deadline`
Adds a task to be done by a deadline\
Format:`deadline TASK /by DATE`\
Examples:
- `deadline submit iP /by 2023-02-16`

### Adding an event task: `event`
Adds a task to be done within a time period\
Format:`deadline TASK /from DATE /to DATE`\
Examples:
- `deadline event submit iP /from 2023-02-13 /to 2023-02-16` 

### Update existing task: `update`
Update fields of existing task\
Note that ordering of fields should match that of values\
Format:`update INDEX /f FIELDS /v VALUES`\
Examples:
- `update 1 /f desc, from, to /v submit iP, 2023-02-13, 2023-02-16`
### Listing all tasks: `list`
Lists all stored tasks\
Format:`list`

### Mark task: `mark`
Mark a certain task as complete\
Format:`mark INDEX`

### Unmark task: `unmark`
Unmark a certain task as incomplete\
Format:`unmark INDEX`

### Delete task: `delete`
Delete a task from current list
Format:`delete INDEX`

### Find task: `find`
Find tasks that exactly match given description\
Format:`find DESCRIPTION`

### Exit program: `bye`
Exits the program