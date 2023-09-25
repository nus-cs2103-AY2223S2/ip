# User Guide for 👑👑👑ClashPlanner👑👑👑
ClashPlanner is a chatbot that helps your organise your tasks like how a **King** does it. Anyone can download it for free, good news for the goblins!
## Features 

### Text-based
You can interact with ClashPlanner the way you talk to your friends online: simply type your commamds to the text bar and press the **SEND** button.


### Add 3 types of tasks (todo, deadline, event)
Want to visit Paris someday, have a report due this Sunday, going for a conference in the afternoon? ClashPlanner can help you plan for all those with 3 types of tasks for you to choose from.

### Search, tag your events
Having a hard time finding a task? Simply search for a word that you think may occur in the task. You can also add multiple tags to a task for ease of searching.

## Usage


### `list` - list down all tasks
This command tells ClashPlanner to display all the tasks you have saved, in the order in which you have added them

Example of usage: 

`list`

Expected outcome:

```
All Tasks:
1.[T][ ] visit Paris
2.[D][ ] submit report (by: Dec 08 2023 at 23:59)
3.[E][ ] school conference (from Feb 01 2023 at 14:00 to Feb 01 2023 at 17:00)
```

### `todo <task name>` - add a Todo Task
This command adds a Todo task to your task list. A Todo task does not have any time frame associated.

Example of usage: 

`todo visit Paris`

Expected outcome:

```
Task successfully added:
[T][ ] visit Paris
```
### `deadline <task name> /<deadline in the format dd-mm-yyyy HHmm>` - add a Deadline Task
This command adds a Deadline task to your task list. A Deadline task has a due time.

Example of usage: 

`deadline submit report /03-02-2023 2359`

Expected outcome:

```
Task successfully added:
[D][ ] submit report (by Feb 03 2023 at 23:59)
```
### `event <task name> /<start time in the format dd-mm-yyyy HHmm> /<end time in the format dd-mm-yyyy HHmm>` - add an Event Task
This command adds an Event task to your task list. An Event task has a period of time during which the event will occur.

Example of usage: 

`event school conference /01-02-2023 1400 /01-02-2023 1700`

Expected outcome:

```
Task successfully added:
[E][ ] school conference (from Feb 01 2023 at 14:00 to Feb 01 2023 at 17:00)
```

### `mark <task index>` - mark a task as done
This command marks the task at the specified index as done.

Example of usage: 

`mark 2`

Expected outcome:

```
Marked as completed:
[D][X] submit report (by: Dec 08 2023 at 23:59)
```

### `unmark <task index>` - mark a task as undone
This command marks the task at the specified index as undone.

Example of usage: 

`unmark 2`

Expected outcome:

```
Marked as completed:
[D][ ] submit report (by: Dec 08 2023 at 23:59)
```

### `delete <task index>` - remove a task
This command removes the task at the specified index.

Example of usage: 

`delete 2`

Expected outcome:

```
The folloing task is removed:
[D][ ] submit report (by: Dec 08 2023 at 23:59)
```

### `find <keyword(s)>` - finds all tasks that contain the keyword(s), if any
This command tells ClashPlanner to search for all tasks that contain the keyword(s), and display them in order of date added

Example of usage: 

`find Paris`

Expected outcome:

```
Found related tasks:
1. [T][ ] visit Paris
```

### `tag <task index> <tag name>` - add a tag to a task
This command adds a tag to the task at the specified index. The tag can be helpful when using the "find" command to search for tasks.

Example of usage: 

`tag 1 #trip`

Expected outcome:

```
Tag added to the following task:
[T][ ] visit Paris Tags: #trip
```

### `bye` - exits from ClashPlanner
This command quits the application ClashPlanner. Fret not, all your tasks are saved locally.

Example of usage: 

`bye`

Expected outcome:

```
Bye, have a nice day
```
