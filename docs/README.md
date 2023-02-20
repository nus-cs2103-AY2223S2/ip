# Duke Task Reminder
> "What gets measured gets managed." - Peter Drucker [(source)](https://static.store.tax.thomsonreuters.com/static/relatedresource/CMJ--15-01%20sample-article.pdf) 
> 
> 

Duke Task Reminder is an application that can help you manage your daily tasks. 

![image](https://dunliang0513.github.io/ip/Ui.png)
## Features

### Duke Task Reminder currently supports the following features:
- [X]  Managing tasks/deadlines/events
- [X]  List all the tasks/events/deadlines recorded inside the task list.
- [X]  Delete the tasks/events/deadlines.
- [X]  Remind the user when there is an upcoming deadline/event.
- [X]  Search the related tasks/events/deadlines using specific keywords.



## Installation
1. download the file from [here](https://github.com/dunliang0513/ip/releases/download/A-Release/Duke_Task_Reminder.jar)
2. run the command below in your terminal
```dtd
java -jar Duke_Task_Reminder.jar
```
3. can start adding your task/event/deadline


## How to Use
### 1. Creating a todo task: `todo`
`todo <task description>`

example: `todo Study for midterm`

### 2. Creating a deadline: `deadline`
`deadline <deadline description> /by <YYYY-MM-DD>`

example: `deadline CS2101 OP2 Reflection /by 2022-02-26`

### 3. Creating an event: `event`
`event <event description> /from <YYYY-MM-DD> /to <YYYY-MM-DD>`

example: `event Midterm week /from 2022-02-27 /to 2022-03-05`

### 4. Listing all tasks: `list`
`list`

example: `list`

### 5. Mark a task as completed: `mark`
`mark <task index>`

example: `mark 1`

### 6. Mark a task as incomplet: `mark`
`unmark <task index>`

example: `unmark 1`

### 7. Delete task from list: `delete`
`delete <task index>`

example: `delete 1`

### 8. Find tasks that match the provided keywords: `find`
`find <keywords>`

example: `find midterm`

### 9. Remind if there is any events happening in three days: `remind`
`remind`

example: `remind`
