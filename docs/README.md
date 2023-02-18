# User Guide

## Setting up in Intellij

Prerequisites: JDK 11, update Intellij to the most recent version.

1. Open Intellij (if you are not in the welcome screen, click `File` > `Close Project` to close the existing project first)
2. Open the project into Intellij as follows:
    1. Click `Open`.
    1. Select the project directory, and click `OK`.
    1. If there are any further prompts, accept the defaults.
3. Configure the project to use **JDK 11** (not other versions) as explained in [here](https://www.jetbrains.com/help/idea/sdk.html#set-up-jdk).<br>
   In the same dialog, set the **Project language level** field to the `SDK default` option.
   1. After that, locate the `src/main/java/duke.Duke.java` file, right-click it, and choose `Run duke.Duke.main()` (if the code editor is showing compile errors, try restarting the IDE). If the setup is correct, you should see something like the below as the output:
      ```
      Hello from
            _     _            _                 
           | |   (_)          | |                
        ___| |__  _ _ __   ___| |__   __ _ _ __  
       / __| '_ \| | '_ \ / __| '_ \ / _` | '_ \
       \__ \ | | | | | | | (__| | | | (_| | | | |
       |___/_| |_|_|_| |_|\___|_| |_|\__,_|_| |_|

       ```

## Features 

### Feature-Add Task
Allow users to add tasks that are of type: 
1. Todo 
2. Deadline
3. Events 

### Feature-Delete Task
Allow users to delete a specific task when it is done. 

### Feature-List Task 
Allow users to view all the tasks that they have created, the task description and the task status. 

### Feature-Mark/Unmark Task 
Allow users to mark a specific task that is done or unmark a task that is done. 

### Feature-Find Task 
Allow users to find task by inputting the keyword that they want. 

### Feature-Reminder 
Send reminders to the users if a deadline task is less than 24 hours away from due date. 

## Usage

### `todo` - Add a Todo task

Adds a todo task to the list of tasks using the command todo description of task

Example of usage: `todo gym`

Expected outcome: 
````
Got it. I've added this task:
  [T][ ] gym
Now you have 4 tasks in the list.
````

Description of the outcome.

### `deadline` - Add a Deadline task 
Adds a deadline task to the list of tasks using the command `deadline [task description] /by [deadline of task]` where the format of 
the due date follow **dd-mm-yyyy hhmm** and the time is of 24 hours format. 

Example of usage: `deadline assigment /by 15-02-2023 1800`

Expected outcome: 
````
    Got it. I've added this task: 
      [D][ ] assigment (by: Feb 15 2023 0600 pm)´
    Now you have 5 tasks in the list.
````

### `event` - Add an Event task 
Adds an event task to the list of tasks using the command 
`event [task description] /from [date when the event starts] /to [date when the event ends]`. The dates do not have to 
follow a specific format. 

Example of usage: `event formal dinner /from tues 10am /to tues 11am`

Expected outcome:
````
Got it. I've added this task:
  [E][ ] formal dinner (from: tues 10am to: tues 11am)
Now you have 6 tasks in the list.
````
### `list` - List the tasks created
View all the tasks that have been created using the command `list`

Example of usage: `list`
Expected outcome: 
````
Here are the tasks in your list:
1.[D][ ] return book (by: Feb 16 2023 0600 pm)
2.[E][ ] bfast with friends (from: tues 9am to: tues 10am)
3.[T][ ] gym
4.[D][ ] assignment (by: Feb 15 2023 0600 pm)
5.[E][ ] formal dinner (from: tues 10am to: tues 11am)

````

### `delete` - Delete the task 
Delete a specific task using the command `delete [task number]`

Example of usage: `delete 3`

Expected outcome: 
````
Noted. I've removed this task:
  [T][ ] gym
Now you have 5 tasks in the list.
````

### `mark` - Mark a task as completed 
Mark the specific task as completed using the command `mark [task number]`

Example of usage: `mark 1`

Expected outcome: 
````
Nice! I've marked this task as done:
[D][X] return book (by: Feb 16 2023 0600 pm)
````
### unmark - Mark task as incomplete
Mark the task as incomplete using the command `unmark [task number]`

Example of usage: `unmark 1`

Expected outcome: 
````
Ok, I've marked this as not done yet:
[D][ ] return book (by: Feb 16 2023 0600 pm)
````

### `find` - Find tasks using keyword 
Display the list of tasks that contains the keyword in their task description using the 
command `find [keyword]`

Example of usage: `find friends`

Expected outcome: 
````
Here are the matching tasks in your list:
1.[E][ ] bfast with friends (from: tues 9am to: tues 10am)
````
### help - Show the commands available
Display all the commands that the users can use and how to use the commands

Example of usage: `help`

Expected outcome: 
````
These are the commands ShinChan recognise given his age:
- todo [task description]: add a task of type Todo
- deadline [task description] /by [due date]: add a task of type Deadline, shinchan only can comprehend date of format dd-mm-yyyy HHmm :(
- event [task description] /from [from when] /by [by when]: add a task of type Event
- delete [task number to be deleted]: to delete a task
- find [keyword]: to find a task that contain certain work
- list: to list out all your tasks
- mark [task number you wish to mark]: mark a task completed
- unmark [task number you wish to unmark]: mark a task as incompleted
- edit /[task number u want to edit] /[desc/from/to/by] /[content you want change to]: edit task
- bye: shinchan pangkang!
````

### bye - Exits the program
Exits the program using command `bye`. It will saves all the tasks in a local text file called tasks.txt which can be 
found in a folder called data 

Example of usage: `bye`

Expected outcome: `Bye. Hope to see you again soon!`
