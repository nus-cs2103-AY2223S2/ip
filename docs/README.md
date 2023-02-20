# User Guide

## Features 

### Feature - Add Tasks

Add tasks to the list.

### Feature - 3 types of Tasks

You can add 3 types of tasks, namely Todo, Deadline and Event.
- Todo is a task without a date.
- Deadline is a task with a deadline.
- Event is a task with a from and to date/timing.
- All tasks have a description.

### Feature - Mark Tasks as Done/Undone
You can keep track of completed tasks with the mark as done feature. If you accidentally marked the task as done, you 
can easily mark it as undone

### Feature - Delete Tasks
You can delete unwanted tasks from the list.

### Feature - Find Tasks
You can find tasks that contain a keyword.

### Feature - Archive Tasks
You can archive your current tasks to a new file and start a new task list.

## Usage

### 1. `list` - lists your tasks

Lists all task in our current list.

Example of usage: 
`list`

Expected outcome:

A numbered list of your tasks.

```
Here are the tasks in your list:
1. task 1
2. task 2
```

### 2. `todo <description of task>` - add a ToDo task

Add a Todo Task to your list.

Example of usage:
`todo <description of task>`

Expected outcome:

A ToDo task is added to your list.

```
Got it I have added this task:
 [T][] <description of task>
Now you have <total number of tasks> task in the list.
```

### 3. `deadline <description of task> /by <date>` - add a Deadline task

Add a Deadline Task to your list.Timing is optional. Default time is 2359.

Example of usage:
`deadline <description of task> /by <YYYY-MM-dd> <HHmm>`

Expected outcome:

A deadline task is added to your list.

```
Got it I have added this task:
 [D][] <description of task> (by: <date> <time>)
Now you have <total number of tasks> task in the list.
```

### 4. `event <description of task> /from <date> /to <date>` - add an Event task

Add an Event Task to your list. time parameter is optional. default will be 0000 for start time and 2359 for end time.

Example of usage:
`event <description of task> /from <date> <time> /to <date> <time>`

Expected outcome:

An Event task is added to your list.

```
Got it I have added this task:
 [E][] <description of task> (from: <date> <time> to: <date> <time>)
Now you have <total number of tasks> task in the list.
```
### 5. `mark <number of task>` - mark a Task as done

marks a task as done.

Example of usage:
`mark <number of task>`

Expected outcome:

The specified task is marked as done.

```
Nice I have marked this task as done:
 [T][X] <description of task>
```
### 6. `unmark <number of task>` - mark a Task as not done

Marks a task as not done.

Example of usage:
`unmark <number of task>`

Expected outcome:

The specified task is marked as not done.

```
OK, I have marked this task as not done yet:
 [T][X] <description of task>
```

### 7. `find <keyword>` - finds Tasks with keyword

Returns a filtered list of tasks that contain the keyword.

Example of usage:
`find <keyword>`

Expected outcome:

A list of tasks containing the keyword.

```
Here are the matching tasks in your list:
1.[T][X] <description of task>
```
or if no tasks are found

```
No tasks in list matching the keyword.
```

### 8. `delete <number of task>` - deletes the specified task

Deletes the specified task from your list.

Example of usage:
`delete <number of task>`

Expected outcome:

task is removed from your list permanently.

```
Noted I have removed this task:
 [T][X] <description of task>
Now you have <total number of remaining tasks> task in the list.
```
or if such a task does not exist

```
Task does not exist!
```

### 9. `archive` - archives your current list

Archives your current list to a new file and starts a new list.

Example of usage:
`archive`

Expected outcome:

current task list is archived in a new file called archive.txt and current list is emptied.

```
Archived the following tasks:
1.[T][X] <description of task>
```
or if no tasks are found

```
No tasks in list to archive.
```