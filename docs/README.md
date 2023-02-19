# User Guide

- Quick Start
- Features
  - Add
    - [Adding an event task: ```event```](#Event)
    - Adding a deadline task: ```deadline```
    - Adding a todo task: ```todo```
  - Delete
    - Deleting a task: ```delete```
  - List
    - Listing all your tasks: ```list```
  - Find
    - Finding a task: ```find```
  - Mark/Unmark
    - Marking your task as done: ```mark```
    - Unmarking your task as not done: ```unmark```
  - Tag
    - Tagging a task: ```tag```
  - Bye
    - Ending the program: ```bye```
 
## Features 

### Event
Command format: 
```
event [description] /from [Start Date: DD/MM/YY HHMM] /to [End Date: DD/MM/YY HHMM] 
```
Adds an event task 

![Event image](UG_images/Event.png)

### Deadline 
Command format: 
```
deadline [description] /by [Deadline Date: DD/MM/YY HHMM]
```
Adds a deadline task

![Deadline image](UG_images/Deadline.png)

### ToDo
Command format:
```
todo [description]
```
Adds a todo task

![Todo image](UG_images/Todo.png)

### Delete
Command format:
```
delete [number]
```
Deletes a task at the specified task number

![Delete image](UG_images/Delete.png)

### List
Command format:
```
list
```
List all tasks that have been added by the user

![List image](UG_images/List.png)

### Find
Command format:
```
find [word/phrase]
```
Find tasks whose description contains the word/phrase specified

![Find image](UG_images/Find.png)

### Mark
Command format:
```
mark [number]
```
Marks a task with the specified task number as completed

![Mark image](UG_images/Mark.png)

### Unmark
Command format:
```
unmark [number]
```
Unmarks a task with the specified task number as not completed

![Unmark image](UG_images/Unmark.png)

### Tag
Command format:
```
tag [name] [number]
```
Gives a tag to a task with the specified task number

![Tag_image](UG_images/Tag.png)

### Bye
```
bye
```
Exits program

