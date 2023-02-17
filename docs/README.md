# *Panav*

## *Panav* is a chatbot to help you through you day-to-day tasks with ease using a user-friendly interface!

What all can *Panav* do - 
- Add 3 types of tasks: todos, deadlines and events
- Listing all the available tasks
- Mark, unmark or delete tasks according to need
- Search through all the tasks in your lists matching your keyword
- Store your list of tasks when you shut down *Panav* and restore the list upon opening

## Features

### Listing all tasks : `list`
Lists all your tasks which you have added
Syntax: `list`

### Adding a ToDo : `todo`
Adds a ToDo task to your list
Syntax: `todo {DESCRIPTION}` 
Exmaple: 'todo work'

### Adding a deadline : `deadline`
Adds a deadline task to your list
Syntax: `deadline {DESCRIPTION} /by {DEADLINE_TIMEFRAME}` 
Example: `deadline work /by tonight

### Adding an event : `event`
Adds an event task to your list
Syntax: `event {DESCRIPTION} /from {START_TIME} /to {END_TIME}` 
Example: `CS2103T exam /from 26th April 4pm /to 6pm`

### Find a task : `find`
Searches through your whole list for tasks matching the keyword you input
Syntax: `find {KEYWORD}`
Example `find exam`

### Mark a task : `mark`
Marks a task in your list as done according to the index you input
Syntax: `mark {TASK_INDEX}`
Example `mark 1`

### Unmark a task : `unmark`
Unmarks a task in your list which was previously marked as done according to the index you input
Syntax: `unmark {TASK_INDEX}`
Example `unmark 1`

### Delete a task: `delete`
Deletes the task correspinding to the index you input from the list
Syntax: `delete {TASK_INDEX}`
Example `delete 1`

### Exit : `bye`
Exits from the chatbot after saving your list
Syntax: `bye`
 