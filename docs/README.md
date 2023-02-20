# User Guide

## Features 

### Add tasks

Add tasks to a task list.

### Mark tasks

Mark tasks as done or not done.

### Delete tasks

Delete tasks from the task list.

### Find tasks

Search for tasks using keywords.

### Save data

Save data to continue with in the future.

## Usage

### 1. `help`

Displays the help page

Example usage:

![img_10.png](img_10.png)

### 2. `retrieve data`

Retrieve saved data to continue where you left off.

Recommended to do so at start up.

If command is not given, panpan will override the previous task list with a new one.

Example of usage: 

![img.png](img.png)

### 3. `list`

Displays the task list.

Example of usage:

![img_1.png](img_1.png)

### 4. `todo [description]`

Adds a todo task to the task list.

Enter the description of the task in `[description]`. 

Example of usage:

![img_2.png](img_2.png)

### 5. `deadline [description] /by [deadline]`

Adds a deadline task to the task list.

Enter the description of the task in `[description]`.

Enter the deadline of the task in `[deadline]` in the form YYYY-MM-DD.

Example of usage:

![img_3.png](img_3.png)

### 6. `event [description] /from [start] /to [end]`

Adds an event task to the task list.

Enter the description of the task in `[description]`.

Enter the start of the duration of the task in `[start]`.

Enter the end of the duration of the task in `[end]`.

Both `[start]` and `[end]` should be in the form YYYY-MM-DD.

Example of usage:

![img_4.png](img_4.png)

### 7. `mark [task number]`

Marks the task as done.

Enter the task number in `[task number]`.

Task numbers start from 1.

Status of the task will not change if it was originally marked as done.

Example usage:

![img_5.png](img_5.png)

### 8. `unmark [task number]`

Marks the task as not done.

Enter the task number in `[task number]`.

Task numbers start from 1.

Status of the task will not change if it was originally marked as not done.

Example usage:

![img_6.png](img_6.png)

### 9. `delete [task number]`

Deletes task from the task list.

Enter the task number in `[task number]`.

Task numbers start from 1.

Example usage:

![img_7.png](img_7.png)

### 10. `find [keyword]`

Searches for tasks that match the keyword given.

Enter the desired keyword in `[keyword]`.

Keyword is case-sensitive.

Example usage:

![img_8.png](img_8.png)

### 11. `bye`

Saves the data and exits the program.

If not run, the current data will not be saved for future uses.

Example usage:

![img_9.png](img_9.png)


# Thank you for using panpan!