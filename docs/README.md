# User Guide for **NerdBot**
> 🤓🤓🤓
## Features:
1. Managing tasks
2. Managing Deadlines and Events with dates
3. Reminders 
4. Find tasks 
5. list all the task currently in the Nerdbot

### Feature 1: task management
* Add a task to the Nerdbot through the todo, deadline or event command.
* Each task has a description. 
* Each task can be marked or unmarked.
* The NerdBot will notify the user whenever a task is added, deleted, marked or unmarked.

### Feature 2: Deadlines and Events
* Add deadlines where a date is specified as the deadline of the task.
* Dates are in the format YYYY-MM-DD.
* Same basic features as a task where you can mark and unmark deadlines.

### Feature 3: Reminders
* Make use of the reminder command to search for any deadlines and events within the specified days from your current date.
* Dates are in the format YYYY-MM-DD.
* The Nerdbot will display the tasks that are within the day range.

### Feature 4: Find tasks
* Make use of the find command to search for any task description.
* The Nerdbot will display the tasks that associate with the user input.

### Feature 5: list all tasks
* Make use of the list command to display all current tasks managed by the Nerdbot.
* The Nerdbot will display each task indexed from 1 and whether they are marked.
* Deadline and Events will show their respective dates.

## Usage

### todo - Adds a todo task
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

### deadline - Adds a deadline task
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

### event - Adds an event task
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
