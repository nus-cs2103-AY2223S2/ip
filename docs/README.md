# User Guide

Duke is a chatbot for storing a user's to-do-list


## Quick start
Ensure you have Java 11 or above installed in your Computer.

Download the latest duke.jar from [here](https://github.com/jerrrren/ip/releases/download/A-Release/duke.jar).

Copy the file to the folder you want to use as the home folder for your chatbot.

Open a command terminal, cd into the folder you put the jar file in, and use the java -jar addressbook.jar command to run the application.

Type the command in the command box and press Enter to execute it.

## Features 
- [Adding a Deadline task](#Adding a Deadline task)
- [Adding a Todo task](#Adding a Todo task)
- [Adding a Event task](#Adding a Event task)
- [Deleting a task](#Deleting a task)
- [Marking a Task](#Marking a Task)
- [Unmarking a Task](#Unmarking a Task)
- [List all tasks](#List all tasks)
- [List upcoming tasks](#List upcoming tasks)

### Adding a Deadline task : `deadline`
Adds a deadline task to the to-do-list.

Command Format: `deadline [description] [/by yyyy-mm-dd]]`

where `[description]` is the description of the deadline  
`[/by yyyy-mm-dd]` is a optional input where `yyyy-mm-dd` is date of deadline in yyyy-mm-dd form


Example command :
`deadline newdeadline`
Corresponding output :
`[D][ ] newdeadline`


Example command :
`deadline newdeadline /by 2023-12-24`
Corresponding output :
`[D][ ] newdeadline by Dec 24 2023`



### Adding a Todo task : `todo`
Adds a Todo task to the to-do-list.

Command Format: `todo [description] [/by yyyy-mm-dd]]`
where `[description]` is the description of the todo 
`[/by yyyy-mm-dd]` is a optional input where `yyyy-mm-dd` is date of todo in yyyy-mm-dd form


Example command :
`todo newtodo`
Corresponding output :
`[T][ ] newtodo`



Example command :
`todo newtodo /by 2023-12-24`
Corresponding output :
`[T][ ] newtodo by Dec 24 2023`




### Adding a Event task : `event`
Adds a Event task to the to-do-list.

Command Format: `event [description] [/by yyyy-mm-dd]]`
where `[description]` is the description of the event 
`[/by yyyy-mm-dd]` is a optional input where `yyyy-mm-dd` is date of event in yyyy-mm-dd form


Example command :
`event newevent`
Corresponding output :
`[E][ ] newevent`


Example command :
`event newevent /by 2023-12-24`
Corresponding output :
`[E][ ] newevent by Dec 24 2023`

### List all tasks : `list`
Lists all tasks saved in the todo list

Command Format: `list`

Example command :
`list`
Corresponding output :
`1.[D][ ] newdeadline`
`2.[D][ ] newdeadline Dec 24 2023`


### Marking a Task : `mark`
Marks a task as done
Command Format: `mark [index]`
where `[index]` is the index of task that is marked on the list


Example command :
`mark 1`  
Corresponding output :
`Nice! I've marked this task as done:`
`[D][X] newdeadline by Dec 24 2023`

### Unmarking a Task : `unmark`
Marks a task as done
Command Format: `unmark [index]`
where `[index]` is the index of task that is unmarked on the list


Example command :
`unmark 1`  
Corresponding output :
`Nice! I've marked this task as not done yet:`
`[D][X] newdeadline by Dec 24 2023`


### Deleting a task : `delete`
Deletes a task

Command Format: `delete [index]`
where `[index]` is the index of task that is deleted from the list


Example command :
`delete 1`  
Corresponding output :
`Noted I've removed this task`
`[D][ ] newdeadline`
`Now you have 1 tasks on the list` 


### List upcoming tasks : `upcoming`
List the tasks which has deadline after today's date

Command Format: `upcoming`


Example command :
`upcoming`
Corresponding output :
`2.[D][ ] newdeadline by Dec 24 2023`
`3.[D][ ] second deadline by Jan 23 2024`



