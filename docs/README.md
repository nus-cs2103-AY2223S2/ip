# Panda 🐼
> "Those who plan do better than those who do not plan even though they rarely stick to their plan."  ~ Winston Churchill [Source](https://www.inloox.com/company/blog/articles/the-62-most-inspiring-project-management-quotes/)

🐼Panda is a task manager chatbot that can assists you with managing your tasks. It's

* Text-based ⌨️
* Easy to use 😌
## Features 

### Managing Tasks 📋
Multiple types of actions to perform on the app to manage the task list. Such as :

`Todo` : Adds a to-do task

`Deadline` : Adds a deadline task

`Event` : Adds an event task

`List` :  Lists all tasks in the task list

`Mark`  : Marks a specific task as **done**

`Unmark` : Marks a specific task as **undone**

`Delete` : Deletes a specific task

`Find` : Finds all relevant tasks based on given keyword(s)

`Help` : Displays in-app user guide

`Bye` : Exits Panda app

## Usage

### `Todo` - Adds a to-do task 📝

Creates a to-do task and adds it to the task list

Example of usage: 

`todo run at night`

Expected outcome:
Displays to-do task added and number of tasks in the task list

```
Nice! I've added this task to your task list :
[] Todo : run at night
Now you have 1 tasks in your task list
```

### `Help` - Displays in app user guide ❓

An instruction manual will be displayed

Example of usage:

`help`

Expected outcome:

```
"Here are what you can do :

 TODO  :  Adds a task to task list
 Example :  TODO (TASK_DESCRIPTION)
 
 LIST  :  List ALL tasks from task list
 Example : LIST
 
 MARK/UNMARK  :  Mark/Unmark a task from the task list
 Example : MARK/UNMARK (Task Number)
 
 EVENT :  Adds an event from and to with specific date and time
 Example : EVENT (EVENT_DESCRIPTION) /(DATE_TIME_FROM) /(DATE_TIME_TO)
 [DATE_TIME = YYYY-MM-DD]
 
 DEADLINE  :  Adds a task with a deadline at specific date and time
 Example : DEADLINE (TASK_DESCRIPTION) /(DATE_TIME_BY)
 [DATE_TIME = YYYY-MM-DD]
 
 DELETE  :  Remove task from task list
 Example :  DELETE (TASK_NUMBER)
 
 BYE  :  Exits the app
 Example : BYE

```