# User Guide for **NerdBot**
> Your personalized task manager 🤓🤓🤓
## Features:
1. Managing tasks
2. Managing Deadlines and Events with dates
3. Reminders 
4. Find tasks 
5. list all the tasks

### Feature 1: task management
* Add a task to the Nerdbot through the todo, deadline or event command.
* Each task can be marked or unmarked.

### Feature 2: Deadlines and Events
* Add deadlines where a date is specified as the deadline of the task.
* Dates are in the format YYYY-MM-DD.
* Same basic features as a task where you can mark and unmark deadlines.

### Feature 3: Reminders
* Make use of the reminder command to search for any deadlines and events within the specified days from your current date.

### Feature 4: Find tasks
* Make use of the find command to search for any task description.
* The Nerdbot will display the tasks that associate with the user input.

### Feature 5: list all tasks
* Make use of the list command to display all current tasks managed by the Nerdbot.

## Usage

### todo [description] - Adds a todo task
Creates a new todo task that is saved to the Nerdbot

Example of usage: 

`todo buy book`

Expected outcome:
Nerdbot will add the todo task "buy book" to the list of managed tasks.

```
Sure!, I've added the following todo task:
  [T][] buy book
Now you have 1 tasks in the list.
  
```

### deadline [description] /by [date] - Adds a deadline task
Creates a new deadline task that is saved to the Nerdbot. Date format is YYYY-MM-DD.

Example of usage: 

`deadline return book /by 2023-12-25`

Expected outcome:
Nerdbot will add the deadlined task "return book" to the list of managed tasks.

```
Sure!, I've added the following deadline:
  [D][] buy book (by: Dec 25 2023)
Now you have 1 tasks in the list.
Be sure to finish these tasks before the deadline!
```

### event [description] /from [date] /to [date] - Adds an event task
Creates a new event task that is saved to the Nerdbot. Date format is YYYY-MM-DD.

Example of usage: 

`event book event /from 2023-12-25 /to 2023-12-27`

Expected outcome:
Nerdbot will add the event "book event" to the list of managed tasks.

```
Sure!, I've added the following event:
  [E][] book event (from: Dec 25 2023 to: Dec 27 2023)
Now you have 1 tasks in the list.
```

### list - View all current tasks
View all current tasks currently managed by Nerdbot.

Example of usage: 

`list`

Expected outcome:
Nerdbot will show all tasks currently managed.

```
Here is the current list:
 1.[T][] buy book
```

### reminder [days] - View all tasks that are "days" from deadline
View all tasks that needs reminders.

Example of usage: 

`reminder 10`

Expected outcome:
Nerdbot will show all upcoming deadlines/events

```
REMINDERS!:
4 days to Event:
[E][] book event (from: Dec 25 2023 to: Dec 27 2023)
```

### find [description] - Find a specific task
Find and displays all tasks that associate with the given description.

Example of usage: 

`find book`

Expected outcome:
Display all tasks that have the given description.

```
Easy command for me! Here are the tasks that are associated with book:
[T][] read book
```

### mark [task index] - Mark a specific task
Marks the task of the given index

Example of usage: 

`mark 1`

Expected outcome:
Marks the task and displays a message confirming the action.

```
Nice, this task has been marked as done:
  [T][X] return book
```

### unmark [task index] - unmark a specific task
Unmarks the task of the given index

Example of usage: 

`unmark 1`

Expected outcome:
Unmarks the task and displays a message confirming the action.

```
Nice, this task has been unmarked:
  [T][] return book
```

### delete [task index] - deletes a specific task
Deletes the task of the given index

Example of usage: 

`delete 1`

Expected outcome:
Delete the task and displays a message confirming the action.

```
Finally done?!, Sure, this task has been removed:
  [T][] return book
Now you have 0 tasks in the list
```

### date [date] - searches for tasks of a given date
Finds tasks that fall on the given date and displays them.
Dates are in the format YYYY-MM-DD

Example of usage: 

`date 2023-12-25`

Expected outcome:
Searches all tasks that have the same date and displays them in the Nerdbot

```
Tasks occurring on 2023-12-25:
  [T][] return book
```
