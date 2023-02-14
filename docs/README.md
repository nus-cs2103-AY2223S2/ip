# User Guide

## Features 

### Feature-CRUD
User can add and delete different task( `Todo`, `Deadline`, `Event`) in a task list. A task can be updated by marking or unmarking its status. 


### Feature-Find

User can find a task using a keyword


### Feature-Help

User can search up all commands by entering `help`


### Feature-Auto Save

Tasks added to the task list are auto-saved to a local file. When a user reopens the application on the same device, previously added task can be seen.

## Usage
Note: Commands are **non case-sensitive** as commands are automatically capitalised except for descriptions
### `Todo` - Add Todo task

Adds a Todo task into the list. A todo task has no date attached to it. 


Example of usage: 

`Todo Clean the house`

Expected outcome:

<img width="759" alt="image" src="https://user-images.githubusercontent.com/80683209/218316948-7b6241be-c7d9-4956-8e96-5a4f6d279420.png">

### `Deadline` - Add Deadline task

Adds a Deadline task into the list. A deadline task has a deadline date attached to it. 


Example of usage: 

`Deadline Finish 2103T iP /by 12/02/2023 2359`

Expected outcome:

<img width="617" alt="image" src="https://user-images.githubusercontent.com/80683209/218319235-deb56b9c-d652-45a3-bfe3-54e18f37b8b5.png">

### `Event` - Add Event task

Adds an Event task into the list. An event task has a starting and ending date attached to it. 


Example of usage: 

`Event Staycation /from 01/01/2023 0000 /to 27/01/2023 2359`

Expected outcome:

<img width="617" alt="image" src="https://user-images.githubusercontent.com/80683209/218319375-b733c130-249e-4438-a6da-857cbd71c644.png">

### `Delete` - Delete a task

Delete a task in the list given an index.


Example of usage: 

`Delete 7`

Expected outcome:

<img width="617" alt="image" src="https://user-images.githubusercontent.com/80683209/218319553-d291cd9b-c124-4dad-9971-ee6da65015b6.png">

### `Mark` - Mark a task

Mark a task as completed in the list given an index.


Example of usage: 

`Mark 6`

Expected outcome:

<img width="617" alt="image" src="https://user-images.githubusercontent.com/80683209/218319585-9391410f-4df6-4191-be18-7c107775590e.png">

### `Unmark` - Unmark a task

Unmark a task as uncompleted in the list given an index.


Example of usage: 

`Unmark 6`

Expected outcome:

<img width="617" alt="image" src="https://user-images.githubusercontent.com/80683209/218319610-35c9dd52-186d-4f99-94b0-edaf5470b9b9.png">

### `Find` - Find a task

Find a task in the list given a keyword.

Note: Keywords are **non case-sensitive.**

Example of usage: 

`Find Staycation`

Expected outcome:

<img width="617" alt="image" src="https://user-images.githubusercontent.com/80683209/218319692-2ad56eaf-1080-4ab0-86dd-d284cb72bf06.png">


### `List` - List all task

List all task in the list.


Example of usage: 

`List`

Expected outcome:

<img width="931" alt="image" src="https://user-images.githubusercontent.com/80683209/218319987-fc27540e-651f-4799-a924-3f1d5f862321.png">



### `Help` - Show all command

Shows all possible commands.


Example of usage: 

`Help`

Expected outcome:

<img width="931" alt="image" src="https://user-images.githubusercontent.com/80683209/218319754-17c741dc-a086-4f34-be3c-8468feb077ca.png">



