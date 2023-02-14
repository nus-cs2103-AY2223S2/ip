# User Guide

Nyako is a task tracker to help you keep track of your tasks!

![Ui](https://user-images.githubusercontent.com/59087730/218805747-f485af1e-4b17-4c61-88b7-1e54635f74a9.png)


## Features 

delete
list
todo
deadline
event
reminder

### Feature-delete

Deletes a task of task_id from the task list. 

Format: delete {task_id}

### Feature-list

Lists out all tasks in the task list.

Format: list

### Feature-todo

Adds a new todo task with task_description.

Format: todo {task_description}

### Feature-deadline

Adds a new deadline task with task_description and deadline. Deadline given should be in LocalDateTime format.

Format: deadline {task_description} /by {deadline}
 
### Feature-event

Adds a new event task with task_description, start_time and end_time. start_time and end_time given should be in LocalDateTime format.

Format: deadline {task_description} /from {start_time} /to {end_time}


### Feature-reminder

Lists out all tasks that are within 1 week of the deadline.

Format: reminder

