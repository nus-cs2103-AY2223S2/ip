# User Guide

## Features 
### `list`
Prints a list of all your tasks.
### `mark <index>`
Marks a task at the index as done.
### `unmark <index>`
Un-marks a task at the index as done.
### `todo <task description>`
Adds a ToDo task.
### `deadline <task description> /by <date> <time>`
Adds a Deadline task with its deadline and time.
### `event <task description> /from <date> <time> /to <date> <time>`
Adds an Event Task with its duration date and time.
### `find <keyword>`
Queries a Task by its keyword and prints them.
### `sort deadlines`
Sorts Deadline Tasks by earliest deadline and prints them.
### `bye`
Exits the application.

## Usage

### `list` - Print a list of all your tasks.

This command prints a lists of all your tasks or will notify user of an empty list.

Example of usage: 

`list`

Expected outcome for non-empty task list:
```
1.[T][ ] Fix Wi-Fi for neighbour
2.[T][X] Buy Yu Sheng for LNY
3.[D][X] CS203T iP final version
 (by: Feb 17 2023 11:59 pm)
4.[E][X] SoC Career Fair
 (from: Feb 7 2023 8:00 am
    to: Feb 8 2023 5:00 pm)
5.[D][ ] MA2104 Assignment 1
 (by: Feb 17 2023 11:59 pm)
6.[E][ ] Internship Interview
 (from: Feb 20 2023 4:00 am
    to: Feb 20 2023 5:00 am)
7.[D][ ] MA3252 Quiz 3
 (by: Feb 14 2023 11:59 pm)
```
Description of the outcome:

A list of all tasks will be printed with their relevant information.
`[T]` represents a ToDo task type, likewise for `[E]` and `[D]` for an Event Task and Deadline Task respectively.
An `[X]` appears next to the task type if the user has marked the task to be completed. On the other hand, an empty `[]` indicates an un-marked task.

Expected outcome for empty task list:
```
Empty, this list is !
```

### `mark <index>` - Marks the task as completed.

This command marks a task as done.

Example of usage:
`mark 1`

Expected outcome:
```
Nice! Marked this task as done, I have:
 [T][X] Fix Wi-Fi for neighbour
```
Description of the outcome:

The command results in the first task's status to be checked done.

### `unmark <index>` - Un-marks the task at the index as incompleted.

This command un-marks a task as incompleted

Example of usage:
`unmark 4`

Expected outcome:
```
Ok! Marked this task as not done yet, I have:
 [E][ ] SoC Career Fair(from: Feb 7 2023 8:00 am to: Feb 8 2023 5:00 pm)
```
Description of the outcome:

The command results in the 4th task's status to become incompleted status.

### `todo <task>` - Adds a ToDo Task to the task list.

This commands adds a ToDo Task to the task list

Example of usage:
`todo CS2103T iP A-UserGuide`

Expected outcome:
```
Got it. Added this task, I have:
 [T][ ] CS2103T iP A-UserGuide
8 tasks in the list, you have now.
```
Description of the outcome:
Assuming there are already 7 tasks in the task list, then the command will add the task of completing A-UserGuide for CS2103T as the 8th task in the task list.

### `deadline <task> /by <date> <time>` - Adds a Deadline Task to the task list.

This command adds a Deadline task to the task list with its due date and the time.

Example of usage:
`deadline MA3252 Quiz 4 /by 2023-02-21 2359`

Expected outcome:
```
Got it. Added this task, I have:
 [D][ ] MA3252 Quiz 4
 (by: Feb 21 2023 11:59 pm)
9 tasks in the list, you have now.
```

Description of the outcome:
Assuming there are already 8 tasks in the task list, then the command will add the MA3252 quiz 4 task which is due 11.59pm on the 21st of February 2023 as the 9th task in the task list.

### `event <task> /from <date> <time> /to <date> <time>` - Adds an Event Task to the task list.

This command adds an Event task to the task list.

Example of usage:
`event Blackpink Concert /from 2023-05-13 1600 /to 2023-05-13 2000`

Expected outcome:
```
Got it. Added this task, I have:
 [E][ ] Blackpink Concert
 (from: May 13 2023 4:00 pm
    to: May 13 2023 8:00 pm)
10 tasks in the list, you have now.
```

Description of the outcome:
Assuming there are already 9 tasks in the task list, then the command will add the Blackpink Concert event with lasts 4 hours on the 13th of May 2023 as the 10th task to the task list. 

### `find <keyword>` - Queries a list of task which contains the keyword.

This command will search the list for tasks whose string representation contains the keyword and prints them out.

Example of usage:
`find MA3252`

Expected outcome:
```
2 tasks in the Jedi Archives, I find
1.[D][ ] MA3252 Quiz 3
 (by: Feb 14 2023 11:59 pm)
2.[D][ ] MA3252 Quiz 4
 (by: Feb 21 2023 11:59 pm)
```

Description of the outcome:
The task list has only 2 tasks that relate to MA3252 and thus these 2 tasks are printed.

### `sort deadlines` - Prints all deadlines in sorted order.

This command prints all deadline tasks in sorted order from tasks that are due the earliest to the latest.

Example of usage:
`sort deadlines`

Expected outcome:
```
1.[D][ ] MA3252 Quiz 3
 (by: Feb 14 2023 11:59 pm)
2.[D][X] CS203T iP final version
 (by: Feb 17 2023 11:59 pm)
3.[D][ ] MA2104 Assignment 1
 (by: Feb 17 2023 11:59 pm)
4.[D][ ] MA3252 Quiz 4
 (by: Feb 21 2023 11:59 pm)
```

Description of the outcome:
This command will take all the deadline tasks in the Task List and then sort them by their earliest deadline.

### `bye` - Exits the application.

This command will first notify the user that he/she has just opted to exit the application and will seek for confirmation again. The application will close when the user hits `Enter` afterwards.

Example of usage:
`bye`

Expected outcome:
```
Be Gone, You Must. May the Force be with You!
Saving your tasks my padawan, I am
Press Enter again to exit
```

Description of the outcome:
In case of any accidental `bye` command being entered, the application seeks another confirmation to the user again. It will also save the user's tasks just in case. When `Enter` is pressed, the application window will be gone and program terminates.

