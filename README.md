# User Guide

## DukeyList
DukeyList a great and easy way to manage and keep track of your tasks! Using DukeyList, you can add and remove tasks, 
and mark them as completed.

### DukeyList is able to handle these types of tasks:
* **ToDos** - Tasks that need to completed
* **Deadlines** - Tasks that need to be completed by a certain date
* **Events** - Tasks that span over a period
* __Loans__ (extension) - Tasks to keep track of debt

## DukeyList Features

### Task list
DukeyList keeps track of all your tasks. You can view your tasks, the task status (whether they are complete or not),
the type of tasks and the deadline/dates associated with your tasks.
```ignorelang
1. [T][ ] Buy Fruits
2. [D][X] Submit forms (by 12 Feb 2023)
3. [E][ ] Seminar (15 Feb 2023 to 16 Feb 2023)
4. [L][ ] Gas money: Brother owes $10 to Mother
```

### Add Tasks
Add a new task to DukeyList. DukeyList will continue to keep track of the task until it is deleted from the list.

### Mark/Unmark Tasks
Should you be done with a task, you can mark it as done. DukeyList represents marked tasks with an 'X':
```ignorelang
2. [D][X] Submit forms (by 12 Feb 2023)
```
Similarly, tasks can be unmarked as well.

### Delete Tasks
You can delete a task from DukeyList. The task will no longer be shown on the list after deletion.

### Find Tasks
You can search for tasks using a certain keyword. DukeyList will return you a list of tasks containing
the keyword, if there are any.
```ignorelang
DukeyList found these tasks matching the keyword "buy":
1. [T][ ] Buy Fruits
```

### Save
You can save DukeyList locally, meaning that your tasks will be stored even after you exit the DukeyList app.
DukeyList automatically loads up the most recent save whenever it starts.

## Starting up DukeyList
Once you start up DukeyList, it will automatically load up your most recent save, if any.
Should there be a saved list, you will see the following message:
```
Saved list loaded:
```
If there is no previous save, the following message will show instead. This means that you will
start with a new empty list.
```
DukeyList empty, starting a new list:
```
You can now start typing commands into DukeyList!



##Using DukeyList
To use DukeyList, type in a `command`, followed by necessary fields and press enter. Each `field` is separated by a '/'
character. The `command` is also followed by the '/' character.
The format and usages of various commands are shown below. Note that commands are not case-sensitive. 
Be sure to follow the formats as closely as you can! Using a command with the incorrect format for fields will lead
to __errors__. Invalid commands or fields will also result in __errors__.


### `list` - Shows DukeyList tasks
The `list` commands lists out all the tasks in DukeyList.

**Usage of `list`:**
```ignorelang
list
```

**Expected outcome:**
```ignorelang
1. [T][ ] Buy Fruits
2. [D][X] Submit forms (by 12 Feb 2023)
3. [E][ ] Seminar (15 Feb 2023 to 16 Feb 2023)
4. [L][ ] Gas money: Brother owes $10 to Mother
```
###

### `todo` - Add a new ToDo
The `todo` command adds a new ToDo to the DukeyList.
A ToDo requires the following `fields`:
1. name

The **format** for adding a Todo is shown below:
```ignorelang
todo / <name>
```

**Example of usage:** 

`todo / Buy Fruits`

**Expected outcome:**
```ignorelang
DukeyList just added a new todo:
[T][ ] Buy Fruits
DukeyList now has 5 tasks.
```

DukeyList will show a confirmation message along with the created ToDo.

###
### `deadline` - Add a new Deadline
The `deadline` command adds a new Deadline to the DukeyList.
A Deadline requires the following `fields`:
1. name
2. deadline in the form `yyyy-mm-dd`

The **format** for adding a Deadline is shown below:
```ignorelang
deadline / <name> / <deadline>
```

**Example of usage:**
```ignorelang
deadline / Submit Forms / 2023-02-12
```

**Expected outcome:**
```ignorelang
DukeyList just added a new deadline:
[D][ ] Submit Forms (by 12 Feb 2023)
DukeyList now has 5 tasks.
```

DukeyList will show a confirmation message along with the created ToDo.

###
### `event` - Add a new Event
The `event` command adds a new Event to the DukeyList.
An Event requires the following `fields`:
1. name
2. start date in the form `yyyy-mm-dd`
3. end date in the form `yyyy-mm-dd`

The **format** for adding an Event is shown below:
```ignorelang
event / <name> / <start> / <end> 
```

**Example of usage:**
```ignorelang
event / Seminar / 2023-02-15 / 2023-02-16
```

**Expected outcome:**
```ignorelang
DukeyList just added a new event:
[E][ ] Seminar (15 Feb 2023 to 16 Feb 2023)
DukeyList now has 5 tasks.
```

DukeyList will show a confirmation message along with the created Event.

###
### `loan` - Add a new Loan
The `loan` command adds a new Event to the DukeyList.
A Loan requires the following `fields`:
1. name
2. borrower
3. lender
4. amount (must be a __number__)

The **format** for adding an Event is shown below:
```ignorelang
loan / <name> / <borrower> / <lender> / <amount> 
```

**Example of usage:**
```ignorelang
loan / Gas money / Brother / Mother / $10
```

**Expected outcome:**
```ignorelang
DukeyList just added a new loan:
[L][ ] Gas money: Brother owes $10 to Mother
DukeyList now has 5 tasks.
```

DukeyList will show a confirmation message along with the created Loan.

###
### `mark` - Mark a task
The `mark` command marks a task as completed.
`mark` requires the following `fields`:
1. task number (must be a __number__)

The **format** for adding an Event is shown below:
```ignorelang
mark / <task number> 
```

**Example of usage:**
```ignorelang
mark / 1
```

**Expected outcome:**
```ignorelang
Task number 1 has been marked as done!
1. [T][X] Buy Fruits
```

DukeyList will show a confirmation message along with the marked task.

###
### `unmark` - Unmark a task
The `unmark` command marks a task as incomplete.
`unmark` requires the following `fields`:
1. task number (must be a __number__)

The **format** for adding an Event is shown below:
```ignorelang
unmark / <task number> 
```

**Example of usage:**
```ignorelang
unmark / 1
```

**Expected outcome:**
```ignorelang
Task number 1 has been unmarked.
1. [T][ ] Buy Fruits
```

DukeyList will show a confirmation message along with the unmarked task.

###
### `delete` - delete a task
The `delete` command marks a task as incomplete.
`delete` requires the following `fields`:
1. task number (must be a __number__)

The **format** for adding an Event is shown below:
```ignorelang
delete / <task number> 
```

**Example of usage:**
```ignorelang
delete / 1
```

**Expected outcome:**
```ignorelang
The following task has been removed!
[T][ ] Buy Fruits
DukeyList now has 4 tasks.
```

DukeyList will show a confirmation message along with the deleted task.

###
### `find` - Find tasks
The `find` command finds and shows all tasks with a certain keyword.
`find` requires the following `fields`:
1. keyword

The **format** for adding an Event is shown below:
```ignorelang
delete / <keyword> 
```

**Example of usage:**
```ignorelang
find / buy
```

**Expected outcome:**
```ignorelang
DukeyList found the following tasks with the keyword 'buy':
[T][ ] Buy Fruits
[T][X] Buy Water
```

DukeyList will show a confirmation message along with the list of tasks with the keyword.

###
### `clearList` - Clears DukeyList
The `clearList` command deletes all the tasks currently on DukeyList.

**Usage:**
```ignorelang
clearList
```

**Expected outcome:**
```ignorelang
DukeyList cleared!
```

DukeyList will show a confirmation message.

###
### `save` - Save DukeyList
The `save` command saves a copy of the current DukeyList locally.

**Usage:**
```ignorelang
save
```

**Expected outcome:**
```ignorelang
DukeyList saved!
```

DukeyList will show a confirmation message.


###
### `bye` - Exits DukeyList
The `bye` command autosaves DukeyList and exits the application.

**Usage:**
```ignorelang
bye
```

**Expected outcome:**
```ignorelang
DukeyList saved! Goodbye! Please return to DukeyList soon!
Exiting...
```

DukeyList will show a confirmation message.
