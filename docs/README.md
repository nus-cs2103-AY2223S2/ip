# Muse: The task manager!

Muse is a simple app that organises your tasks.

## Features
We will cover the basic instructions here for you to get started. 
More commands can be found by our comprehensive guide within the app, with
help
.

### Add a task
There are three types of tasks: todo, deadline and event. 
Date (for todo and event can be specified by
```dd/mm/yyyy tttt```, where ```tttt``` is the 24 hour time)

### Delete a task
By specifying the index which you wish to delete, Muse removes a task from your list of tasks.

### Find a task
Finds you the tasks whose description/title matches your search term. 

### List all tasks
To get a view of all the current tasks.

### Mark/Unmark tasks as done or not done
With an "X", Muse helps to keep track of the tasks that you wish to be marked "done" or "undone". Similar to delete, you specify the index and duke will mark/unmark that task.

## Here are the basic commands that you can do: 
- list out tasks ```list```
- add a task ```todo mop the floor```
- Register actual dates (for events) ```event fireworks /from 14/01/2023 1600/to 16/01/2023 2000```
- mark/unmark tasks as done (or undone) ```mark 6```
- set deadlines: ```deadline do assignment /by 14/01/2023 1800```
- find a task: ```find homework``` (and returns all tasks with homework in its title)
- For the full instructions, simply ask muse with the following command: ```help```

## To use the app (JAR file) 
1. Go into the CLI, and navicate to the directory this JAR file is in. 
2. Execute the command ```java -jar duke.jar``` in order to start the app.
