# User Guide

## Features 

### Adding tasks -- Todo

You can add a todo task to Duke, and Duke will remember it for you. A todo is just a task without time of start or end.

### Adding tasks -- Deadline

You can add a deadline task to Duke, and Duke will remember it for you.
A deadline is a task that has contains a date in which the task is due by.

### Adding tasks -- Event

You can add an event task to Duke, and Duke will remember it for you
An event is a task that has a start date, as well as an end date.

### Delete tasks

You can delete a task from Duke.

### Find tasks

You can find tasks that have a description that matches whatever keyword you type in. Your keyword does not need to
be an actual word. 

### Mark tasks as done

You can mark tasks as done based on their indexing.

### Mark tasks as undone

You can mark tasks as undone based on their indexing. 

### Prioritise task

You can prioritise a task by marking it as prioritised and bumping it up the task.

### Unprioritise task

You can unprioritise a task by removing its prioritised marking and lowering it down the list of tasks

## Usage

### `todo` - Adds todo task to Duke

Adds todo task to Duke. It will add the todo task to the Duke file. You will then be shown your task being added, if 
it is added successfully, and it will also show the number of tasks you currently have in your Duke file.

Example of usage: 

`todo clean the house`

Expected outcome: Your todo task with the description 'clean the house' will be stored in the Duke file and remembered by
Duke.

Your todo task will be appended the the current Duke file, and by default, it will not have priority, or be marked.

```
Got it. I've added this task:
 [T][ ][ ] clean the house
Now you have 1 task in the list.
```
### `deadline` - Adds deadline task to Duke

Adds deadline task to Duke. It will add the deadline task to the Duke file. 

Deadline format is 'deadline [description] /by [local date time]'

You will then be shown your deadline being added, if
it is added successfully, and it will also show the number of tasks you currently have in your Duke file.

Example of usage:

`deadline clean the house /by 2023-02-17 14:30`

Expected outcome: Your deadline task with the description 'clean the house' and your local date time 2023-02-17 14:30 will be stored in the Duke file and remembered by
Duke.

Your deadline task will be appended the the current Duke file, and by default, it will not have priority, or be marked.

```
Got it. I've added this task:
 [D][ ][ ] clean the house (by: 17 Feb 2023 2.30pm)
Now you have 2 tasks in the list.
```

### `event` - Adds event task to Duke

Adds event task to Duke. It will add the event task to the Duke file.

Deadline format is 'event [description] /from [local date time] /to [local date time]'

You will then be shown your event being added, if
it is added successfully, and it will also show the number of tasks you currently have in your Duke file.

Example of usage:

`event house cleanign party /from 2023-02-17 14:30 /to 2023-02-17 16:30`

Expected outcome: Your event task with the description 'clean the house' and your start time 2023-02-17 14:30 and your end time 2023-02-17 16:30 will be stored in the Duke file and remembered by
Duke.

Your event task will be appended the current Duke file, and by default, it will not have priority, or be marked.

```
Got it. I've added this task:
 [E][ ][ ] house cleaning party (from: 17 Feb 2023 2.30pm to: 17 Feb 2023 4.30pm)
Now you have 2 tasks in the list.
```

### `delete` - Deletes your task from Duke

Deletes a task based on its specified index from Duke
Delete format is 'delete [index]'
It will then show you which task you have deleted as well as the remaining number of tasks in Duke.

Example of usage:

`delete 1`

Expected outcome: Your task at the specified index will then be deleted from Duke.

Regardless of the task being a todo, deadline, or event, the task will be permanently deleted from Duke.


```
Noted. I'm removing this task:
 [E][ ][ ] house cleaning party (from: 17 Feb 2023 2.30pm to: 17 Feb 2023 4.30pm)
Now you have 1 task in the list.
```

### `find` - Finds all the tasks that matches your keyword

Finds all the tasks that have a description that contains your keyword.

Example of usage:

`find cleaning`

Expected outcome: Any task that has a description that contains the keyword would be collected in 
a list, and displayed to the user.

All matching tasks will be shown to the user.

```
Here are the matching tasks in your list:
1.[D][ ][ ] clean the house (by: 17 Feb 2023 2.30pm)
2.[T][ ][ ] clean the house
```

### `mark` - Marks task as done

Marks the task at the specified index as done. It will be marked with the `X` symbol.

Example of usage:

`mark 1`

Expected outcome:
If the operation is successful, it will display a success message and 
show which task is marked as done.

```
Nice! I've marked this task as done!:
 [D][X][ ] clean the house (by: 17 Feb 2023 2.30pm)
```

### `unmark` - Mark tasks as undone

Unmarks the task at the specified index as done. If it was previously marked, the `X`
symbol will be removed for a whitespace, else nothing will be changed.

Example of usage:

`unmark 1`

Expected outcome:
If the operation is successful, it will display a success message and
show which task is marked as undone.

```
OK, I've marked this task as not done yet:
 [D][ ][ ] clean the house (by: 17 Feb 2023 2.30pm)
```

### `prioritise` - Prioritise tasks

Prioritise the task at the specified index. The task will be marked with `!!!` and will be
bumped to the top of the list at index 1.

Example of usage:

`prioritise 2`

Expected outcome:
If the operation is successful, it will display a success message and
show which task is prioritised. This task will be moved to the top of the list at
index 1.

```
Got it. I've prioritised this task:
 [T][ ][!!!] clean the house
```

### `unprioritise` - Unprioritise task

Unprioritise the task at the specified index. If the task was previously prioritised, the `!!!`
symbol will be replaced with a whitespace. It will also be moved to the bottom of the list at index -1.
Example of usage:

`unprioritise 1`

Expected outcome:
If the operation is successful, it will display a success message and
show which task is unprioritised. This task will be moved to bottom of the list at index -1.

```
Got it. I've unprioritised this task:
 [D][ ][ ] clean the house (by: 17 Feb 2023 2.30pm)
```