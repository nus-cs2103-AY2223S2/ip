# User Guide

## Duke Bot
> "Your mind is for having ideas, not holding them." - David Allen ([source](https://dansilvestre.com/productivity-quotes/))

## Features 

### Feature-Adding Tasks

You can add different types of tasks:
- To Do
- Deadline
- Event

### Feature-Marking Tasks

You can mark tasks as completed when you are done with them.
You can also unmark tasks, indicating incompleted tasks.

### Feature-Delete Tasks

You can delete tasks as and when you wish!

### Feature-List

You can ask Duke to display all tasks that you have currently.

### Feature-Finding Tasks

You can pass in specific keywords to find tasks that match them! 

### Feature-Statistics

You can gather certain user Statistics and Insights:
1. How many tasks have you completed in the past week?
2. How many tasks have you added in the past week?
3. How productive you are?

## Usage

### `Deadline' - Deadline task

Example of usage: 

`deadline cs2102 assignment /by 2023-02-25`

Expected outcome:

Description of the outcome.

```
Got it. I've added this task:
[D][] cs2102 assignment (by: Feb 17 2023)
Now you have ... tasks in the list.
```

### `Event' - Event task

Example of usage: 

`event career fair /from 2023-02-12 /to 2023-02-13`

Expected outcome:

Description of the outcome.

```
Got it. I've added this task:
[E][] career fair (from: Feb 12 2023 2359 to: Feb 13 2023 2359)
Now you have ... tasks in the list.
```

### `ToDo' - ToDo task

Example of usage: 

`todo borrow book`

Expected outcome:

Description of the outcome.

```
Got it. I've added this task:
[T][] borrow book
Now you have ... tasks in the list.
```

### `Mark / Unmark' - Describe action

You need to have tasks in your list in the first place.

Example of usage: 

`mark 1`

Expected outcome:

Marking Deadline cs2102 assignment.

```
Nice! I have marked this Task as Done:
[D][X] cs2102 assignment (by: Feb 17 2023)
```

`unmark 1`

Expected outcome:

Unmarking Deadline cs2102 assignment.

```
Ok! I have unmarked this Task as not done yet:
[D][] cs2102 assignment (by: Feb 17 2023)
```

### `Delete` - Deletes Task:

`delete 1`

Expected outcome:

Suppose you only have added one To Do task of borrow book.

```
Noted!I have deleted the task for you:
[T][] borrow book
You have 0 tasks in this list.
```


### `List` - Shows all Tasks in List:

`list`

Expected outcome:

Suppose you only added one To Do task of borrow book.

```
1.[T][X] borrow book
```

### `Find` - Find Tasks with matching keywords:

`find book`

Expected outcome:

Suppose you have added one To Do task of borrow book.

```
Here are the matching tasks in your list:
1.[T][X] borrow book
```

### `Statistics` - Displays User Statistics:

`statistics`

Expected outcome:

Suppose you have added two task and completed one task.
And you have 5 urgent tasks.

```
You have 5 Urgent tasks! You got to be more productive!
You have added 2 tasks within the past week!
You have completed 1 tasks within the past week!Good Job!
```


