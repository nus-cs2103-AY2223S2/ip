# User Guide

## Features 

1. Add differen types of tasks easily in to the schedule list
    - Todo
    - Deadline with time
    - Event with start time and end time
2. Check all tasks in a list view
3. Mark / unmark tasks on the list (hey keep it for some sense of completion)
4. Delete a task so that you won't be bothered by it anymore
5. Find a task by partial information 
6. Help page to remind you if you forget some commands

## Usage

### `todo TASK_DESCRIPTION` - Add a todo task into the list

Example of usage: `todo homework`

Expected outcome: A todo task named homework added into current task list

Description of the outcome.

```
    This todo has been added!
    [T][] homework

```

### `deadline TASK_DESCRIPTION DATE_TIME` - Add a deadline task into the list

Example of usage: `deadline homework /23022023 2359`

Expected outcome: A deadline task named homework added into current task list

Description of the outcome.

```
    This deadline has been added!
    [D][] homework (by:2023/02/23 23:59)

```

### `event TASK_DESCRIPTION START_DATE_TIME END_DATE_TIME` - Add an event into the list

Example of usage: `event Midterm /27022023 1000 /27022023/1200`

Expected outcome: An event named Midterm added into current task list

Description of the outcome.

```
    This event has been added!
    [E][] midterm (from: 2023/02/27 10:00 to: 2023/02/27 12:00)

```

### `find TASK_DESCRIPTION` - Returns tasks with matched descriptions

Example of usage: `find homework`

Expected outcome: all tasks with homework in their description will be returned 

Description of the outcome.

```
    Here are the matching tasks in your list:
    1.[T][] Maths homework
    2.[T][] CS homework
```

### `mark TASK_INDEX` - Mark the indexed task as done

Example of usage: `mark 1`

Expected outcome: task 1 marked as done

Description of the outcome.

```
    Nice! Great job for completing this task:
    [D][X] homework (by:2023/02/23 23:59)

```

### `unmark TASK_INDEX` - Unmark the indexed task as done

Example of usage: `unmark 1`

Expected outcome: task 1 marked as not done

Description of the outcome.

```
    This item is marked as not done yesy:
    [D][] homework (by:2023/02/23 23:59)

```

### `delete TASK_INDEX` - Delete the indexed task from the task list

Example of usage: `delete 1`

Expected outcome: task 1 removed from the list

Description of the outcome.

```
    This task is deleted from the list:
    [D][] homework (by:2023/02/23 23:59)

```


### `help` - Opens the help page for some commands

Example of usage: `help`

Expected outcome: help page displayed

Description of the outcome.

```
    Here are the commands that you may use this application:
    1. Check current task list: list
    ...
```


### `bye` - Save the current task list to local

Example of usage: `delete`

Expected outcome: current task list saved in local file

Description of the outcome.

```
    bye~ Hope to see you next time! >w<
    Please click the X on the top right to close the program

```
