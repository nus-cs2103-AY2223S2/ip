# User Guide

## Features 

### Add Tasks

Add tasks to your task list to track

Types of tasks that can be added:
- Todo : simple task with just a description
- Deadline: task with a deadline to complete by
- Event: task that spans over 2 dates

### Delete Tasks

Delete tasks that are already in the task list

### List Tasks

Lists tasks that are current in the task list

## Usage

### `List` - Lists all tasks
Lists all statuses of tasks that are currently in the task list
Syntax: `list`
Outcome: numbered of iists of tasks and status of whether each task is marked

### `Todo`, `Deadline`, `Event` - Add task of specified type
Adds a Todo, Deadline or Event task to the task list

Adds a new simple task with just a description
Syntax: `Todo <description>`
Example: `Todo read notes`
Outcome: task with description "read notes" is added into task list

Adds a new task with a description and a deadline to complete by
Syntax: `Deadline <description> /<yyyy-MM-ddThh:mm>`
Example: `Deadline cs2103 iP Submission /2023-02-17T23:59`
Outcome: task with description "cs2103 iP Submission" and deadline of 17 Feb 2023, 11:59pm is added into the task list

Adds a new task with a description that spans over 2 dates
Syntax: `Event <description> /<yyyy-MM-ddThh:mm> /<yyyy-MM-ddThh:mm>`
Example: `Event iP Extension /2023-02-18T00:00 /2023-02-20T23:59`
Outcome: task with description "iP Extension" spanning from 18 Feb 2023, 12am to 20 Feb 2023, 11:59pm is added into the task list

### `Mark`, `Unmark` - Mark task of specified index as completed or not completed
Marks task in task list as complete or incomplete

Marks an existing task at specified index of task list as complete.
Prompt differs if the task was already marked as complete before this command.
Syntax: `Mark <index>`
Example: `List` followed by `Mark 1`
Outcome: task at index 1 of the task list is marked as complete. separate prompt for if the task was already marked as complete.
<details>
    <summary> If task was not previously marked as complete </summary>

U - `List`
> Currently, your list has 3 tasks.
>        1) [T] [ ] todo
>        2) [D] [ ] deadline ( by: 17 FEB 2023, FRI, 11:59PM )
>        3) [E] [ ] event ( from: 18 FEB 2023, SAT, 12:00AM ) ( to: 20 FEB 2023, MON, 11:59PM )
U - `Mark 1`
> This task is marked as done:
>        [T] [X] todo

</details>

<details>
    <summary> If task was previously marked as complete </summary>

U - `List`
> Currently, your list has 3 tasks.
>        1) [T] [X] todo
>        2) [D] [ ] deadline ( by: 17 FEB 2023, FRI, 11:59PM )
>        3) [E] [ ] event ( from: 18 FEB 2023, SAT, 12:00AM ) ( to: 20 FEB 2023, MON, 11:59PM )
U - `Mark 1`
> This task 
>        [T] [X] todo
> was already marked as done! Did you forget? OR U PLAYIN ME?!

</details>

Unmarks an existing task at specified index of task list as incomplete.
Prompt differs if the task was already marked as incomplete before this command.
Syntax: `Unmark <index>`
Example: `List` followed by `Unmark 2`
Outcome: task at index 2 of the task list is marked as incomplete. separate prompt for if the task was already marked as incomplete.
<details>
    <summary> If task was previously marked as incomplete </summary>

U - `List`
> Currently, your list has 3 tasks.
>        1) [T] [ ] todo
>        2) [D] [ ] deadline ( by: 17 FEB 2023, FRI, 11:59PM )
>        3) [E] [ ] event ( from: 18 FEB 2023, SAT, 12:00AM ) ( to: 20 FEB 2023, MON, 11:59PM )
U - `Unmark 2`
> 
Example of usage: 

`keyword (optional arguments)`

Expected outcome:

Description of the outcome.

```
expected output
```
