# User Guide for Duke Chatbot!
> It is only when one goes through CS2103T, does a man truly know the value of time.

## Description
Introducing Duke Chatbot to improve your productivity! Manage tasks in a convenient and intuitive list and get to work!

## Installation
1. Download the JAR file from this [link](https://github.com/Bisceto/ip/releases).
2. Open command-line interface (Powershell for Windows, Terminal for Mac, Ubuntu for Linux)
3. Navigate to the folder with the JAR file and run the application via typing the comman `java -jar duke.jar`
4. Get to work with Duke!

![Duke chatbot](./Ui.png)


## Features
1. Add task
2. Delete task
3. List tasks
4. Mark/Unmark tasks
5. Find tasks
6. Exit 


## Usage
### `Add task`
Add a task from 3 different categories: Todo, Deadline or Event.

| Task Type | Command | Example Use |
| --------- | ------------ | ----------- |
| Todo      | `todo <description>` | `todo homework` |
| Deadline  | `deadline <description>/<yyyy-MM-dd>` | `deadline project submission/2023-03-15` |
| Event     | `event <description>/<yyyy-MM-dd HHmm> to <yyyy-MM-dd HHmm>` | `event midterms/2023-04-20 0900 to 2023-04-20 1100` |


### `Delete task`
Deletes a task from the to-do list based on its number in the list.

Command: `delete <task number>`
Example usage: `delete 1`


### `List tasks`
Display all tasks that are recorded in the list.

Command: `list`

Example outcome:
```
Here are the tasks you have due!
1. [T] [] homework
2. [D] [] project submission (Mar 15 2023)
3. [E] [] midterms (Apr 20 2023 0900 to Apr 20 2023 1100)
```


### `Mark/Unmark task`
Mark/Unmark a task of a given task number in the task list as completed/uncompleted.

Command: `mark <task number>` / `unmark <task number>`

Example usage: `mark 1` / `unmark 2`


### `Find tasks`
Find a task by its descrption or by its date. Tasks containing the search query specified will be returned.

Command: `find <task description>` / `find <yyyy-MM-dd>` / `find <MMM dd yyyy>`

Example usage: `find homework` / `find 2023-03-15 / `find Mar 15 2023`

Expected outcome (for `find Mar 15 2023`):
```
Here are the matching tasks in your list:
2. [D] [] project submission (Mar 15 2023)
```


### `Exit`
End the current session. The application will close upon execution of the exit command. The list of tasks will be saved.

Command: `bye`
