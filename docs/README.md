# User Guide

- Quick Start
- Features
  - Add
    - [Adding an event task: ```event```](#Event)
    - [Adding a deadline task: ```deadline```](#Deadline)
    - [Adding a todo task: ```todo```](#ToDo)
  - Delete
    - [Deleting a task: ```delete```](#Delete)
  - List
    - [Listing all your tasks: ```list```](#List)
  - Find
    - [Finding a task: ```find```](#Find)
  - Mark/Unmark
    - [Marking your task as done: ```mark```](#Mark)
    - [Unmarking your task as not done: ```unmark```](#Unmark)
  - Tag
    - [Tagging a task: ```tag```](#Tag)
  - Bye
    - [Ending the program: ```bye```](#Bye)
 
## Features 

### Event
Command format: 
```
event [description] /from [Start Date: DD/MM/YY HHMM] /to [End Date: DD/MM/YY HHMM] 
```
Adds an event task 

![Event image](https://github.com/FireRadical22/ip/blob/master/docs/UG%20images/Event.PNG)

### Deadline 
Command format: 
```
deadline [description] /by [Deadline Date: DD/MM/YY HHMM]
```
Adds a deadline task

![Deadline image](https://github.com/FireRadical22/ip/blob/master/docs/UG%20images/Deadline.PNG)

### ToDo
Command format:
```
todo [description]
```
Adds a todo task

![Todo image](https://github.com/FireRadical22/ip/blob/master/docs/UG%20images/Todo.PNG)

### Delete
Command format:
```
delete [number]
```
Deletes a task at the specified task number

![Delete image](https://github.com/FireRadical22/ip/blob/master/docs/UG%20images/Delete.PNG)

### List
Command format:
```
list
```
List all tasks that have been added by the user

![List image](https://github.com/FireRadical22/ip/blob/master/docs/UG%20images/List.PNG)

### Find
Command format:
```
find [word/phrase]
```
Find tasks whose description contains the word/phrase specified

![Find image](https://github.com/FireRadical22/ip/blob/master/docs/UG%20images/Find.PNG)

### Mark
Command format:
```
mark [number]
```
Marks a task with the specified task number as completed

![Mark image](https://github.com/FireRadical22/ip/blob/master/docs/UG%20images/Mark.PNG)

### Unmark
Command format:
```
unmark [number]
```
Unmarks a task with the specified task number as not completed

![Unmark image](https://github.com/FireRadical22/ip/blob/master/docs/UG%20images/Unmark.PNG)

### Tag
Command format:
```
tag [name] [number]
```
Gives a tag to a task with the specified task number

![Tag_image](https://github.com/FireRadical22/ip/blob/master/docs/UG%20images/Tag.PNG)

### Bye
```
bye
```
Exits program

