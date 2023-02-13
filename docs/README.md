# User Guide

## Features 

### Adding tasks

There are 3 types of task that you can add.

* ToDo "todo [task description]"
* Deadline "deadline [task description] /by [date in a yyyy/MM/d HHmm format]"
* Event "event [task description] /from [start] /to [end]"

### Getting all the tasks in the list

Gets a list of all current tasks.

### Marking a task

Marks a task as done.

### Unmarking a task

Unmarks a task as undone.

### Deleting a task

Deletes a task.

### Finding tasks by keyword

Find all tasks that contains the keyword

### Saving and exiting the chatbot

Saves all current tasks in a txt file and exits the program.

Description of the feature.

## Usage

### todo - Adds a ToDo task to the list

Example of usage: 
`todo borrow book`

Expected outcome:
![image](https://user-images.githubusercontent.com/77615600/218534220-3ef76d5c-5758-427e-bccd-730d35975b7b.png)

### deadline - Adds a Deadline task to the list
Format: "deadline [task description] /by [date in a yyyy/MM/d HHmm format]"

Example of usage: 
`deadline submit assignment /by 2023/06/23 1000`

Expected outcome:
![image](https://user-images.githubusercontent.com/77615600/218534900-a8383ae7-e9c7-4b66-91fb-597f0f7c6d20.png)

### event - Adds a Event task to the list
Format: "event [task description] /from [start] /to [end]"

Example of usage: 
`event attend wedding /from 6pm /to 10pm`

Expected outcome:
![image](https://user-images.githubusercontent.com/77615600/218535153-26ba2011-c5d9-4a8b-8ec7-5214fa583ba2.png)

### mark - Marks a task as done

Example of usage: 
`mark 1`

Expected outcome:
![image](https://user-images.githubusercontent.com/77615600/218535370-f2ca1355-b670-4aa0-bc10-c9ad38c5f9d6.png)

### unmark - Unmarks a task as undone

Example of usage: 
`unmark 1`

Expected outcome:
![image](https://user-images.githubusercontent.com/77615600/218535468-c5cc1350-8f8c-470c-a935-5c09048ebb5a.png)

### list - Show all the tasks in the list

Example of usage: 
`list`

Expected outcome:
![image](https://user-images.githubusercontent.com/77615600/218535595-8ac1f2c5-b8ec-495c-98cb-ccaf524de6cb.png)

### delete - Show all the tasks in the list

Example of usage: 
`delete 1`

Expected outcome:
![image](https://user-images.githubusercontent.com/77615600/218535906-83da2d54-88e2-4570-8d7f-345dcb2c0523.png)


### find - Show all the tasks in the list

Example of usage: 
`find wedding`

Expected outcome:
![image](https://user-images.githubusercontent.com/77615600/218535965-d2b453b0-5cf9-48c4-bfe4-0eb4687c7cea.png)


### bye - Saves all tasks in the current list and ends the program.

Example of usage: 
`bye`

Expected outcome:
![image](https://user-images.githubusercontent.com/77615600/218536117-e343ceaf-6c81-45cc-9b01-5ab8fc73e964.png)

