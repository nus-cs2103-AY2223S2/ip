# User Guide for Spongebob Chatbox
Here is Spongebob, a friendly task manager robot.

## Features 

### Keep track of your tasks

Spongebob chatbox help you to keep track of three type of tasks: ToDo, Deadline, Event.
You can also delete an old task.
You can also mark a task as done or uncompleted.

### Detect overlapping event

Spongebob chatbox will alert you when there are more than 2 events overlap with each other.

### Find the tasks with specific keyword

Spongebob chatbox allows you to find tasks that contain keyword that you input.

### Auto-save

Spongebob chatbox will automatically save all your tasks in local storage, so you don't need to worry
data loss.

## Usage

### `todo` - Add a todo task

Add a "todo" task into your tasklist.

Format: todo {description}

Example of usage: 

`todo do homework`

Expected outcome:

```
Got it. I've added this task:
  [T][0] do homework
Now you have 1 tasks in the list.
```

### `deadline` - Add a deadline task

Add a "deadline" task into your tasklist.

Format: deadline {description} /by {dd/mm/yyyy hh:mm}

Example of usage: 

`deadline do ip /by 17/02/2023 23:59`

Expected outcome:

```
Got it. I've added this task:
  [T][0] do ip (by 17/02/2023 23:59)
Now you have 2 tasks in the list.
```

### `event` - Add a event task

Add a "event" task into your tasklist.

Format: event {description} /from {dd/mm/yyyy hh:mm} /to {dd/mm/yyyy hh:mm}

Example of usage: 

`event attend CS2103T lecture /from 17/02/2023 14:00 /to 17/02/2023 15:00`

Expected outcome:

```
Got it. I've added this task:
  [T][0] attend CS2103T lecture (from: 17/02/2023 14:00 to: 17/02/2023 15:00)
Now you have 3 tasks in the list.
```

### `list` - List all tasks

Show all the tasks in your tasklist.

Format: list

Example of usage: 

`list`

Expected outcome:

```
Here are the tasks in your list:
1.[T][0] do homework
2.[D][0] do ip (by 17/02/2023 23:59)
3.[E][0] attend CS2103T lecture (from: 17/02/2023 14:00 to: 17/02/2023 15:00)
```

### `mark` - Mark a task as completed

Show the task is done.

Format: mark {number}

Example of usage: 

`mark 1`

Expected outcome:

```
Nice! I've marked this task as done:
  [T][1] do homework
```

### `unmark` - Mark a task as uncompleted

Show the task is not done yet.

Format: unmark {number}

Example of usage: 

`unmark 1`

Expected outcome:

```
Ok! I've marked this task as not done yet:
  [T][0] do homework
```

### `delete` - Delete a task 

Delete the task from your tasklist.

Format: delete {number}

Example of usage: 

`delete 1`

Expected outcome:

```
Noted! I've removed this task:
  [T][0] do homework
Now you have 2 tasks in the list.
```

### `find` - Find a task

Search for a task that match the keyword

Format: find {keyword}

Example of usage: 

`find ip`

Expected outcome:

```
Here are the matching tasks in your list:
1.[D][0] do ip (by 17/02/2023 23:59)

```
