># User Guide

>**_WARNING_**: Do not delete dukeapp directory or all data will be lost.

---

>## Features 

### Adding todo task

Adds a todo task to the list of tasks.

### Usage

`todo <TASK_DESCRIPTION>` 

Adds the todo task with the description to the list
of tasks.

Example of usage: 

`todo Grocery Shopping`

Expected outcome:

Duke will reply that he has successfully added your todo task
with the todo task shown in the chat box. He will also tell you about
the number of tasks in the list.

```
Duke:
On it, my MaStEr. I've successfully added this task:
[T] [] Gorcery Shopping
Now you have 1 tasks(s) in the list.
```

---

### Adding deadline task

Adds a deadline task to the list of tasks.

### Usage

`deadline <TASK_DESCRIPTION> /by <TASK_DEADLINE_IN_YYYY_MM_DD_FORMAT>` 

Adds the deadline task with the description and deadline to the list
of tasks.
The deadline must be in this format YYYY-MM-DD.

The deadline task will be added to the list of tasks.

Example of usage:

`deadline Shopping /by 2019-12-14`

Expected outcome:

Duke will reply that he has successfully added your deadline task
with the deadline task shown in the chat box. He will also tell you about
the number of tasks in the list.

```
Duke:
Deadline time!!! I've successfully added this task:
[D] [] Shopping (by: Dec 14 2019)
Now you have 1 tasks(s) in the list.
```

---

### Adding event task

Adds an event task to the list of tasks.

### Usage

`event <TASK_DESCRIPTION> /from <TASK_EVENT_FROM> /to <TASK_EVENT_TO>`

Adds the event task with the description and duration to the list
of tasks.


Example of usage:

`event Wedding /from 12pm /to 5pm`

Expected outcome:

Duke will reply that he has successfully added your event task
with the event task shown in the chat box. He will also tell you about
the number of tasks in the list.

```
Duke:
Oohhh look what we have here. I've added this task:
[E] [] Wedding (from: 12pm to: 5pm)
Now you have 1 tasks(s) in the list.
```

---

### Marking a task as done

Marks a task in the list.

### Usage

`mark <INDEX_NUMBER_IN_THE_LIST>`

Duke will mark the task with the given index number in the list.
Command only available after user use list or find.

Example of usage:

`mark 1`

Expected outcome:

Duke will display a message that he has marked the task as done and
show the marked task.

```
Duke:
Beep Boop! I've marked this task as done:
[T] [X] Read Books
```

---

### Marking a task as not done

Unmarks a task in the list. 
(which is also marking a task as not done)

### Usage

`unmark <INDEX_NUMBER_IN_THE_LIST>`

Duke will unmark the task with the given index number in the list.
Command only available after user use list or find.

Example of usage:

`unmark 1`

Expected outcome:

Duke will display a message that he has marked the task as not done and
show the unmarked task. 

```
Duke:
Beep Boop! I've marked this task as not done yet:
[T] [] Read Books
```

---

### Deleting a task

Deletes a task in the list.

### Usage

`delete <INDEX_NUMBER_IN_THE_LIST>`

Duke will delete the task with the given index number in the list.
Command only available after user use list or find.

Example of usage:

`delete 1`

Expected outcome:

Duke will display a message that he has deleted the task and
show the deleted task. He will also tell you about
the number of tasks in the list.

```
Duke:
Time to eliminate this task:
*slays this task*
[T] [] Read Books
*tasks disappeared successfully*
Now you have 0 task(s) in the list.
```

---

### Tagging a task

Tags a task in the list.

### Usage

`tag <INDEX_NUMBER_IN_THE_LIST> <TAG_DESCRIPTION>`

Duke will tag the task with the given index number in the list.
Command only available after user use list or find.

Example of usage:

`tag 1 fun`

Expected outcome:

Duke will display a message that he has tagged the task and
show the tagged task.

```
Duke:
HashTag Time!!! I've tagged this task:
[T] [] Read books
#fun
```

---

### Listing a task

Lists the tasks in a list.

### Usage

`list`

Duke will list all the task in its system.

Example of usage:

`list`

Expected outcome:

Duke will display a list of tasks with all its details.

```
Duke:
Here are the list of tasks:
1. [T] [] Read Books
#fun
```

---

### Finding a task

Finds any matching tasks in a list.

### Usage

`find <TASK_DESCRIPTION>`

Duke will find any matching tasks in its system including tasks with 
descriptions containing the same find description.

Example of usage:

`find Read`

Expected outcome:

Duke will display a list of tasks of matching description
with all its details.

```
Duke:
Here are the matching tasks in your list:
1. [T] [] Read Books
#fun
```

---

### Going back to original list after using find command

Displays the original list after using find command.

### Usage

`originalList`

Duke will return all operations back to the original list. Any
future operations will be on the original list instead.

Example of usage:

`originalList`

Expected outcome:

Duke will display the original list of tasks.

```
Duke:
Here are the list of tasks:
1. [T] [] Read Books
#fun
```

---

### Bye

Exiting out of the program.

### Usage

`bye`

Duke will say goodbye to you for your exit. 

Example of usage:

`bye`

Expected outcome:

Duke will display a bye message.

```
Duke:
Good Bye, my youngling.
```
