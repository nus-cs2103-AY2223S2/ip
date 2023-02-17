# User Guide (Alfred)

![DaAlfred](https://user-images.githubusercontent.com/85063215/218469100-ec5f495a-85c8-417f-93ed-3da3d3940505.png)
## Alfred
Alfred is Bruce Wayne (Batman)'s loyal butler. He is a wise and intelligent man who acts as a mentor and father figure to Bruce Wayne (Batman) and provides support and assistance to him in his crime-fighting activities. 

- [Quick Start](https://github.com/ruiyigan/ip/tree/master/docs#quick-start)
- [Summary of Commands](https://github.com/ruiyigan/ip/tree/master/docs#summary-of-commands)
- [Features](https://github.com/ruiyigan/ip/tree/master/docs#features)
- [Usage](https://github.com/ruiyigan/ip/tree/master/docs#usage)
- [Acknowledgements](https://github.com/ruiyigan/ip/tree/master/docs#acknowledgements)

## Quick Start

1. Ensure you have Java `11` or above installed on your Computer.
2. Download Alfred [here](https://github.com/ruiyigan/ip/releases/download/A-Release/alfred.jar).
3. Copy the jar file into an empty folder.
4. Open a command window in that folder.
5. Run the command java -jar {filename}.jar e.g., java -jar alfred.jar (i.e., run the command in the same folder as the jar file).


## Summary of Commands

Function | Command
---------|---------
`greets Alfred` | `evening`
`add a todo` | `todo <DESCRIPTION>`
`add a deadline` | `deadline <DESCRIPTION> /by <YYYY-MM-DD>`
`add an event` | `event <DESCRIPTION> /from <YYYY-MM-DD> /to <YYYY-MM-DD>`
`mark a task as completed` | `mark <INDEX>`
`mark a task as incomplete` | `unmark <INDEX>`
`deletes a task` | `delete <INDEX>`
`list all tasks` | `list`
`find tasks with a keyword` | `find <KEYWORD>`
`display stats` | `stats`
`exit the application` | `bye`

## Features 

### Manages todos 

Add, delete, mark, and unmark todos

### Manages deadlines

Add, delete, mark, and unmark deadlines

### Manages event

Add, delete, mark, and unmark event

### Views all tasks

Sees all tasks

### Find tasks

Find tasks based on keywords 

### View stats

View stats of tasks

### Persistant storage

Data will be saved automatically after each command

## Usage

### `list` - See all tasks

View all tasks of all types, mark or unmark. 

Example of usage: 

`list`

Expected outcome:

![Screenshot 2023-02-13 at 21 29 22](https://user-images.githubusercontent.com/85063215/218470870-2c8bb963-2d63-453c-8805-84c252459d79.png)

### `todo <DESCRIPTION>` - Create a todo task

Adds a new todo to the application

Example of usage: 

`todo solve crime by joker`

Expected outcome:

![Screenshot 2023-02-13 at 21 34 58](https://user-images.githubusercontent.com/85063215/218472050-f828f00e-8445-417f-a991-5a197b8fd3af.png)

### `deadline <DESCRIPTION> /by <YYYY-MM-DD>` - Create a deadline task

Adds a new deadline to the application

Example of usage: 

`deadline reduce crime by 60% /by 2025-01-01`

Expected outcome:

![Screenshot 2023-02-13 at 21 36 29](https://user-images.githubusercontent.com/85063215/218472432-5588e21a-f9de-491f-b7a2-0d1bd90a22e1.png)

### `event <DESCRIPTION> /from <YYYY-MM-DD> /to <YYYY-MM-DD>` - Create an event task

Adds a new event to the application

Example of usage: 

`event Damian training /from 2023-01-01 /to 2023-04-05`

Expected outcome:

![Screenshot 2023-02-13 at 21 37 47](https://user-images.githubusercontent.com/85063215/218472726-afb87ef2-b9d5-424c-9f10-c9d5ca779796.png)

### `mark <INDEX>` - Marks a task as completed by index

Adds a completed `X` to the task. 

Example of usage: 

`mark 1`

Expected outcome:

![Screenshot 2023-02-13 at 21 39 34](https://user-images.githubusercontent.com/85063215/218473127-1b3b9259-b088-4a42-9e4d-89dcd03fa279.png)


### `unmark <INDEX>` - Unmarks a task by index

Removes `X` from task.

Example of usage: 

`unmark 1`

Expected outcome:

![Screenshot 2023-02-13 at 21 40 26](https://user-images.githubusercontent.com/85063215/218473317-d70732a0-2e02-4739-84dc-a70c0cb6c7f7.png)

### `delete <INDEX>` - Deletes a task by index

Removes task from database.

Example of usage: 

`delete 1`

Expected outcome:

![Screenshot 2023-02-13 at 21 41 16](https://user-images.githubusercontent.com/85063215/218473499-1fe294b4-ff59-4aa7-a9a1-8584db2fc352.png)


### `find <KEYWORD>` - Finds tasks by keyword

List down all tasks that contains keyword

Example of usage: 

`find joker`

Expected outcome:

![Screenshot 2023-02-13 at 21 42 30](https://user-images.githubusercontent.com/85063215/218473751-5a0e74a9-7490-4487-9abf-2341e3a50408.png)


### `stats` - Find out statistics of tasks

List down statistics of tasks

Example of usage: 

`stats`

Expected outcome:

![Screenshot 2023-02-13 at 21 43 44](https://user-images.githubusercontent.com/85063215/218474054-9cee871f-0a93-4b89-b6f5-048c7d8c7c85.png)

### `bye` - Close application

End application 

Example of usage: 

`bye`

Expected outcome: Application closes.

### `evening` - Greet Alfred

Sends a greeting message to Alfred, starting crime fighting proper.

Example of usage: 

`evening`

Expected outcome:

![Screenshot 2023-02-13 at 21 45 13](https://user-images.githubusercontent.com/85063215/218474377-7d601085-74be-42ba-9f51-0d01fe6c7dd7.png)



## Acknowledgements
Images from Nathan Clendenin and Keona Events
