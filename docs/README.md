# User Guide for AOT Chatbot

## Features 

### List of tasks

Lists out all the current task(s) that has been previously added.

### Add Tasks

Three types of tasks that can be added to the bot.
1.  `Todo` - Simple task to complete.
2. `Deadline`- Task to complete, with a deadline.
3. `Event` - Event to complete, with a duration.

### Delete Tasks

Remove task from the list.

### Find Tasks

Find task(s) based on a keyword.

### Mark / Unmark Tasks

Mark / Unmark task.


## Usage

### `list` - Displays list of task(s) previously added

Displays list of all the task(s) that was added previously.

Example of usage: 

`list`

Expected outcome: List of all the task(s)

Description of the outcome.

```
Here are the task(s) in your list:
  1. [T][ ] Read book
  2. [D][ ] CS2103T project (By: Feb 17 2023)
  3. [E][ ] Hackathon Project (From: Mar 3 2023 | To: Mar 4 2023)
```

### `todo <task_description>` - Add a Todo task

Adds a Todo task to the list.

Example of usage:

`todo Read book`

Expected outcome: 'Read book' added into the list

Description of the outcome.
```
I've added this task for you!
  [T][ ] Read book
Now you have 1 task(s) in your list ~~
```

### `deadline <task_description> /by <date>` - Add a Deadline task

Adds a Deadline task to the list.

Example of usage:

`deadline CS2103T Project /by 17/02/2023 `

Expected outcome: 'CS2103T project' added into the list

Description of the outcome.
```
I've added this task for you!
  [D][ ] CS2103T Project (By: Feb 17 2023)
Now you have 2 task(s) in your list ~~
```

### `event <task_description> /from <date> /to <date>` - Add an Event task

Adds an Event task to the list.

Example of usage:

`event Hackathon Project /from 03/03/2023 /to 04/03/2023`

Expected outcome: 'Hackathon Project' added to the list.

Description of the outcome.
```
I've added this task for you!
  [E][ ] Hackathon Project (From: Mar 03 2023 | To: Mar 04 2023)
Now you have 3 task(s) in your list ~~
```

### `delete <task_index>` - Deletes a task.

Removes a task from the list.

Example of usage:

`delete 1`

Expected outcome: Deletes the 1st task in the list.

Description of the outcome.
```
I've deleted this task for you!
  [T][ ] read book
Now you have 2 task(s) in your list ~~
```

### `find <keyword>` - Finds task(s) according to the keyword

Displays all task(s) that contains the keyword.

Example of usage:

`find book`

Expected outcome: Displays all task(s) that contains the word 'book'.

Description of the outcome.
```
Task(s) that contains the word 'book':
  1. [T][ ] read book
  2. [T][ ] book restaurant
```
### `mark <task_index>` - Marks a task.

Marks a task from the list according to the index.

Example of usage:

`mark 1`

Expected outcome: Marks the 1st task in the list.

Description of the outcome.
```
Okay!I've marked this task as done:
  [T][X] read book
```