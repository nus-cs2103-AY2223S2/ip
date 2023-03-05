# Duke User Guide

> “Your mind is for having ideas, not holding them.” – David Allen ([source](https://dansilvestre.com/productivity-quotes/))

Duke frees your mind by remembering things you need to do. It is a desktop chat-based app with a user-friendly interface that is easy to learn and ~~Fast~~ _SUPER FAST_ to use.

- [Steps to run](https://github.com/anchengyang/ip/tree/master/docs#steps-to-run)
- [Features](https://github.com/anchengyang/ip/tree/master/docs#features)
- [Usage](https://github.com/anchengyang/ip/tree/master/docs#usage)
  - [Todo command](https://github.com/anchengyang/ip/tree/master/docs#1-create-todo-items-todo)
  - [Deadline command](https://github.com/anchengyang/ip/tree/master/docs#2-create-deadlines-deadline)
  - [Event command](https://github.com/anchengyang/ip/tree/master/docs#3-create-events-event)
  - [Find command](https://github.com/anchengyang/ip/tree/master/docs#4-filter-by-keyword-find)
  - [Finddate command](https://github.com/anchengyang/ip/tree/master/docs#5-filter-by-date-finddate)
  - [Mark command](https://github.com/anchengyang/ip/tree/master/docs#6-mark-items-as-completed--mark)
  - [Unmark command](https://github.com/anchengyang/ip/tree/master/docs#7-unmark-items-as-uncompleted--unmark)
  - [Delete command](https://github.com/anchengyang/ip/tree/master/docs#8-delete-items-delete)
  - [Update description command](https://github.com/anchengyang/ip/tree/master/docs#9-update-the-description-of-existing-tasks-update)
  - [Update deadline command](https://github.com/anchengyang/ip/tree/master/docs#10-update-the-deadline-of-existing-deadlines-update)
  - [Exit application](https://github.com/anchengyang/ip/tree/master/docs#11-quits-the-application-on-command-bye)
- [Command summary](https://github.com/anchengyang/ip/tree/master/docs#command-summary)

## Steps to run
1. Ensure you have `Java 11` installed in your device
2. Download the JAR file from the latest release [here](https://github.com/anchengyang/ip/releases)
3. Launch the app from the terminal using this command `java -jar duke.jar`
4. Start adding your tasks using the commands [here](https://github.com/anchengyang/ip/tree/master/docs#usage) 
5. Let it manage your tasks for you 👍

## Features 

### Manages your tasks for you

Add or delete tasks from the todo list using the index.

### Filter tasks

You can easily locate the task you want based on specific keywords or dates.

### Update existing tasks

Easily update the description or deadline of existing tasks without the hassle of deleting and creating new tasks.

### Track completed and uncompleted tasks

Mark or unmark tasks that you have completed.


## Usage

### 1. Create todo items: `todo`

Create todo items with the given description

`[T] [ ] My todo item`

Example of usage: 

`todo <description>`

### 2. Create deadlines: `deadline`


Create deadline items with the given description and deadline

HHmm (hour minute in 24 hour time format) is optional to include in the deadline

`[D] [ ] My deadline item (by: Oct 14 2023 04:30 PM)`

Example of usage: 

`deadline <description> /by <yyyy-MM-dd HHmm(optional)>`


### 3. Create events: `event`

Create events with the given description and start and end dates

HHmm (hour minute in 24 hour time format) is optional to include in the deadline

`[E] [ ] My event item (from: Oct 14 2023 04:30 PM to: Oct 19 2023 05:00 PM)`

Example of usage: 

`event <description> /from <yyyy-MM-dd HHmm(optional)> /to <yyyy-MM-dd HHmm(optional)>`


### 4. Filter by Keyword: `find`

Finds items in the list with descriptions that match the given keyword(s)

Example of usage: 

`find <keyword(s)>`


### 5. Filter by Date: `finddate`

Finds items in the list with dates that match the given date

Example of usage: 

`find <date>`


### 6. Mark items as completed:  `mark`

Marks items in the list as completed

Example of usage: 

`mark <task number>`

### 7. Unmark items as uncompleted:  `unmark`

Unmarks items in the list as uncompleted

Example of usage: 

`unmark <task number>`

### 8. Delete items: `delete`

Deletes items in the list

Example of usage: 

`delete <task number>`

### 9. Update the description of existing tasks: `update`

Updates the description of existing tasks in the list without having to create a new task

Example of usage: 

`update <index> /description <new changes>`

### 10. Update the deadline of existing deadlines: `update`

Updates the deadline of existing deadline tasks in the list without having to create a new deadline

Example of usage: 

`update <index> /deadline <new changes>`

### 11. Quits the application on command: `bye`

Quits the application on command without having to click on the cross button of the window

Example of usage:

`bye`

## Command summary
| No. | Feature                                | Command                                                                                     |
|-----|----------------------------------------|---------------------------------------------------------------------------------------------|
| 1.  | Create todo items                      | `todo <description>`                                                                        |
| 2.  | Create deadlines                       | `deadline <description> /by <yyyy-MM-dd\n HHmm(optional)>`                                  |
| 3.  | Create events                          | `event <description> /from <yyyy-MM-dd\n HHmm(optional)> /to <yyyy-MM-dd\n HHmm(optional)>` |
| 4.  | Filter by keyword                      | `find <keyword(s)>`                                                                         |
| 5.  | Filter by date                         | `finddate <date>`                                                                           |
| 6.  | Mark items as completed                | `mark <task number>`                                                                        |
| 7.  | Unmark items                           | `unmark <task number>`                                                                      |
| 8.  | Delete items                           | `delete <task number>`                                                                      |
| 9.  | Update description of existing tasks   | `update <index> /description <new changes>`                                                 |
| 10. | Update deadlines of existing deadlines | `update <index> /deadline <new changes>`                                                    |
| 11. | Quits the application                  | `bye`                                                                                       |

## Screenshots
This is a screenshot of the application on start:

  <img src="https://user-images.githubusercontent.com/65301406/218518527-19dfc310-9304-4625-a7ec-072b36cd9072.png" width="50%" height="60%">

This is a screenshot of the chat:

  <img src="https://user-images.githubusercontent.com/65301406/218518232-d2dfd186-1b83-433b-ae53-83002b008f7c.png" width="50%" height="70%">
