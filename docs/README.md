# User Guide
This is the user guide for Duke Bunny.
It contains the following section:
* Features
* Usage

Do use the user guide if you have any difficulties using Duke Bunny.

## Features 
### Manage Tasks
Duke Bunny allows the users to manage their tasks via the following functions:
* Add tasks
* Delete tasks
* Mark / Unmark tasks
* Tag tasks
* List tasks
* Find tasks based on keywords

### Categorize Tasks
Duke Bunny allows the users to categorize their tasks into 3 category:
* Todo - Todo tasks are prefixed with [T]
* Event - Event tasks are prefixed with [E]
* Deadline - Deadline tasks are prefixed with [D]

### Save and Load Tasks
Duke Bunny allows the users to save and load tasks onto a storage device as a txt file.

## Usage
### `list` - Shows the current tasks in your list
After sending `list` to Duke Bunny, he will reply with all
the current tasks that you currently have.

Example of usage:
`list`

Expected outcome:
```
Here are the tasks in your list:
1.[T][] return book
```

### `mark` - Mark a task in your list as done with an index
After sending `mark index` to Duke Bunny, he will reply with the 
task that was marked.

Example of usage:
`mark 1`

Expected outcome:
```
Nice! I've marked this task as done:
[T][X] return book
```

### `unmark` - Unmark a task that was previously marked with an index
After sending `unmark index` to Duke Bunny, he will reply with the
task that was unmarked.

Example of usage:
`unmark 1`

Expected outcome:
```
OK, I've marked this task as not done yet:
[T][] return book
```

### `todo` - Add a task into the tasklist as a todo task
After sending `todo description` to Duke Bunny, he will reply with
the confirmation message that a task has been successfully added 
into the task list, followed by the current number of task in the list.

Example of usage:
`todo return book`

Expected outcome:
```
Got it. I've added this task:
[T][] return book
Now you have 1 tasks in the list.
```

### `event` - Add a task into the tasklist as a event task
After sending `event description /from start /to finish` to Duke Bunny,
he will reply with the confirmation message that a task has been successfully
added into the task list, followed by the current number of task in the list.

Example of usage:
`event project meeting /from Mon 2pm /to 4pm`

Expected outcome:
```
Got it. I've added this task:
[E][] project meeting (from: Mon 2pm to: 4pm)
Now you have 1 tasks in the list.
```

### `deadline` - Add a task into the tasklist as a deadline task
After sending `deadline description /by yyyy-mm-dd hhmm` to
Duke Bunny, he will reply with the confirmation message that a task has
been successfully added into the task list, followed by the current
number of task in the list.

Example of usage:
`deadline do assignment /by 2023-01-30 0800`

Expected outcome:
```
Got it. I've added this task:
[D][] do assignment (by: 30 Jan 2023 8:00 AM)
Now you have 1 tasks in the list.
```

### `delete` - Remove a task from the task list with an index
After sending `delete index` to Duke Bunny, he will reply with the confirmation
message that a task has been successfully removed from the task list, followed by
the number of task in the list.

Example of usage:
`delete 1`

Expected outcome:
```
Noted. I've removed this task:
[T][] return book
Now you have 0 tasks in the list.
```

### `find` - Find a task with the keyword specified
After sending `find keyword` to Duke Bunny, he will reply with the list of tasks
that contains the `keyword`.

Example of usage:
`find assignment`

Expected outcome:
```
Here are the matching tasks in your list:
1. [D][] do assignment (by: 30 Jan 2023 8:00 AM)
```

### `tag` - Tag a task in the tasklist with an index
After sending `tag index description` to Duke Bunny, he will reply with
the task that was tagged

Example of usage:
`tag 1 troublesome`

Expected outcome:
```
Ok, I've added 1 new tag to the task:
[D][] do assignment (by: 30 Jan 2023 8:00 AM) #troublesome
```
