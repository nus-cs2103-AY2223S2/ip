# User Guide
---
## Features 

### Adding tasks

Three types of tasks can be added to the list:
- ToDo
- Deadline
- Event

### Viewing list of task

Task list can be displayed.

### Task deletion

Specified task can deleted

### Marking/Unmarking of tasks

Specified tasks can be marked/unmarked as done/undone.

### Find tasks with specific _word_ or _phrase_

Tasks containing specific words or phrases can be listed out.

### Task tagging

Tasks can be tagged to be grouped under a certain category.

### Clearing of tasklist

List of task can be cleared in a single command.

---

## Usage

### `todo [TASK]` - Adding todo task.

- `todo` keyword to create todo task.
- `[TASK]` is task to be done.
- `[TASK]` can be word/phrase but cannot be empty.

Example of usage: 

`todo increment ip`

Expected outcome:
Task *increment ip* will be added to the list.


### `deadline [TASK] by [DATETIME]` - Adding deadline task.

- `deadline` keyword to create deadline task.
- `[TASK]` is task to be done.
- `[TASK]` can be word/phrase but cannot be empty.
- `[DATETIME]` is the deadline of the task.
- `[DATETIME]` must be in format *_yyyy/MM/dd HHmm_*.

Example of usage: 

`deadline increment ip by 2023/02/15 1800`

Expected outcome:
Task *increment ip* with deadline *2023/02/15 1800* will be added to the list.


### `event [TASK] from [DATETIME] to [DATETIME]` - Adding event task.

- `event` keyword to create event task.
- `[TASK]` is task to be done.
- `[TASK]` can be word/phrase but cannot be empty.
- First `[DATETIME]` argument is the start of the event.
- Second `[DATETIME]` argument is the end of the event. 
- `[DATETIME]` must be in format *_yyyy/MM/dd HHmm_*.

Example of usage: 

`event increment ip from 2023/02/10 1800 to 2023/02/15 1800`

Expected outcome:
Task *increment ip* from *2023/02/10 1800* to *2023/02/15 1800* will be added to the list.


### `list` - Shows current list of tasks.

Expected outcome:
list of task will be displayed.


### `clear` - Clears current list of tasks.

Expected outcome:
list of task will be cleared.


### `mark [INDX]` - Marks task at specified index.

- `[INDX]` has to be more than 0 and has to be a number currently existing in the list of tasks.

Example of usage:

`mark 1`

Expected outcome:
Task 1 is marked.


### `unmark [INDX]` - Unmarks task at specified index.

- `[INDX]` has to be more than 0 and has to be a number currently existing in the list of tasks.

Example of usage:

`unmark 1`

Expected outcome:
Task 1 is unmarked.


### `delete [INDX]` - Deletes task at specified index.

- `[INDX]` has to be more than 0 and has to be a number currently existing in the list of tasks.

Example of usage:

`delete 1`

Expected outcome:
Task 1 is deleted.


### `find [KEYWORD]` - Finds task with specified keyword.

- `[Keyword]` can be a word or phrase.

Example of usage:

`find project`

Expected outcome:
Tasks containing the word "project" is displayed.


### `tag [INDX] [TAGGING]` - Tags a task at specified index with the specified tagging.

- `[INDX]` has to be more than 0 and has to be a number currently existing in the list of tasks.
- `[TAGGING]` can be word or phrase but will concatenated if is phrase.

Example of usage:

`tag 1 Easy Task`

Expected outcome:
Task 1 is tagged as *"#EasyTask"*.


### `bye` - Saves current list of tasks and exits program.

Expected outcome:
Program is exited and any existing list will be loaded upon next start-up.
