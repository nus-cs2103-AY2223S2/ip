<a id="top"></a>
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
 - [Summary](#Summary)
 
## Features 

### Event
Command format: 
```
event [description] /from [Start Date: DD/MM/YY HHMM] /to [End Date: DD/MM/YY HHMM] 
```
Adds an event task 

![Event image](https://github.com/FireRadical22/ip/blob/master/docs/UG%20images/Event.PNG)

[Back to top](#top)

### Deadline 
Command format: 
```
deadline [description] /by [Deadline Date: DD/MM/YY HHMM]
```
Adds a deadline task

![Deadline image](https://github.com/FireRadical22/ip/blob/master/docs/UG%20images/Deadline.PNG)

[Back to top](#top)

### ToDo
Command format:
```
todo [description]
```
Adds a todo task

![Todo image](https://github.com/FireRadical22/ip/blob/master/docs/UG%20images/Todo.PNG)

[Back to top](#top)

### Delete
Command format:
```
delete [number]
```
Deletes a task at the specified task number

![Delete image](https://github.com/FireRadical22/ip/blob/master/docs/UG%20images/Delete.PNG)

[Back to top](#top)

### List
Command format:
```
list
```
List all tasks that have been added by the user

![List image](https://github.com/FireRadical22/ip/blob/master/docs/UG%20images/List.PNG)

[Back to top](#top)

### Find
Command format:
```
find [word/phrase]
```
Find tasks whose description contains the word/phrase specified

![Find image](https://github.com/FireRadical22/ip/blob/master/docs/UG%20images/Find.PNG)

[Back to top](#top)

### Mark
Command format:
```
mark [number]
```
Marks a task with the specified task number as completed

![Mark image](https://github.com/FireRadical22/ip/blob/master/docs/UG%20images/Mark.PNG)

[Back to top](#top)

### Unmark
Command format:
```
unmark [number]
```
Unmarks a task with the specified task number as not completed

![Unmark image](https://github.com/FireRadical22/ip/blob/master/docs/UG%20images/Unmark.PNG)

[Back to top](#top)

### Tag
Command format:
```
tag [name] [number]
```
Gives a tag to a task with the specified task number

![Tag_image](https://github.com/FireRadical22/ip/blob/master/docs/UG%20images/Tag.PNG)

[Back to top](#top)

### Bye
```
bye
```
Exits program

[Back to top](#top)

## Summary
|Command|Format|
|-------|------|
|list|`list`|
|todo|`todo [description]`|
|deadline|`deadline [description] /by [Deadline Date: DD/MM/YY HHMM]`|
|event|`event [description] /from [Start Date: DD/MM/YY HHMM] /to [End Date: DD/MM/YY HHMM] `|
|mark|`mark [number]`
|unmark|`unmark [number]`|
|delete|`delete [number]`|
|find|`find [word/phrase]`|
|tag|`tag [name] [number]`|
|bye|`bye`|

[Back to top](#top)
