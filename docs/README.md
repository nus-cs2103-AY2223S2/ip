# Duke Crayon Shin Chan Chat Box Guide

## Features 

### Track Todo Tasks
Todo tasks with descriptions can be added to the list of tasks. 
They can be deleted and marked as either done or undone.

### Track Deadlines
Deadlines with description and date can be added to the list of tasks.
They deleted and marked as either done or undone.

### Track Events
Deadlines with description, starting date and ending date can be added to the list of tasks.
They deleted and marked as either done or undone.

### Have an overview of all tasks
All tasks can be listed out for user to have an overview of all todo tasks, deadlines and events.

### Find a task by description
A specific task can be found by searching for keywords it contains. 

## Usage

### `list` - show all tasks

All todo tasks, deadlines, events will be shown in a 
chronological order (sequence following the time they are added to the list)

Example of usage: 

`list`

Expected outcome:

List of all tasks will be shown

```
Here are the tasks in your list:
1. [T][X] homework1
2. [T][ ] homework2
3. [D][ ] quiz (by Feb 10 2001)
```

### `find` - find tasks by searching keyword
By searching using a keyword, which can be a word, a substring of a word or a character,
todo tasks, deadlines and events that contains the keyword will be shown in a
chronological order (sequence following the time they are added to the list)

Example of usage:

`find quiz` or `find q`

Expected outcome:

List of tasks containing the keyword will be shown

```
Here are the tasks in your list:
1. [D][ ] quiz (by Feb 10 2001)
```

### `todo` - add a todo to the task list
Create a new todo task with description and the task will be added
to the task list, the todo task will be mark as undone. 

Example of usage:

`todo homework1` 

Expected outcome:

The new task created will be shown. The total number of tasks in the list 
including the newly added task will be reflected.
```
Got it. I've added this task:
[T][ ] homework1
Now you have 2 tasks in the list.
```

### `deadline` - add a Deadline to the task list
Create a new deadline with description and date. The deadline will be added
to the task list, the deadline will be mark as undone.

Example of usage:

`deadline quiz /2001-02-10`

Expected outcome:

The new deadline created will be shown. The total number of tasks in the list
including the newly added deadline will be reflected.
```
Got it. I've added this task:
[D][ ] quiz (by Feb 10 2001)
Now you have 3 tasks in the list.
```

### `event` - add an event to the task list
Create a new event with description, starting and ending date. 
The event will be added to the task list, the event will be mark as undone.

Example of usage:

`event quiz /2001-02-10 /2001-02-11`

Expected outcome:

The new deadline created will be shown. The total number of tasks in the list
including the newly added event will be reflected.
```
Got it. I've added this task:
[E][ ] quiz (from Feb 10 2001 to Feb 11 2001)
Now you have 4 tasks in the list.
```

### `mark` - mark a task as done
mark followed by the task number will mark the corresponding task from 
"undone" to "done". The task will remain unchanged if it is already done.

Example of usage:

`mark 1`

Expected outcome:

The second [] for the task will contain X, indicating that the task is already
done.
```
Nice! I've marked this task as done
[X] homework1
```

### `unmark` - mark a task as undone
unmark followed by the task number will mark the corresponding task from
"done" to "undone". The task will remain unchanged if it is already undone.

Example of usage:

`unmark 1`

Expected outcome:

The second [] for the task will be empty, indicating that the task is not yet
done.
```
Nice! I've unmarked this task as done
[ ] homework1
```

### `delete` - mark a task as undone
delete followed by the task number will delete the corresponding task from
the list. 

Example of usage:

`delete 1`

Expected outcome:

The second [] for the task will be empty, indicating that the task is not yet
done. The total number of tasks in the list excluding 
the newly added event will be reflected.
```
Noted. I've removed this task:
[T][ ] homework1
Now you have 3 tasks in the list
```