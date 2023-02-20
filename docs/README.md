# User Guide

<img src="Ui.png" align="right"/>

* [Features](#features)
* [Usage](#usage)
    * [List](#list---lists-all-tasks)
    * [Add Tasks](#todo,-deadline,-event---add-task-of-specified-type)
    * [Mark Tasks](#mark-unmark---mark-task-of-specified-index-as-completed-or-not-completed)
    * [Delete Tasks](#delete---deletes-a-task-at-a-specified-index)
    * [Search](#find-check---searches-through-task-list)
    * [Exit](#bye---say-bye-to-duke)


## Features 

### Keep track of tasks
Use Duke to keep track of your many tasks in life!

### Reminders
Upon launching the application, Duke will remind you of the deadline tasks that are due on the current day, and the event tasks that span over the current day.
To get this list of tasks again, either scroll up or use the [`Check` command](#check---search-using-date) with today's date.

## Usage

### `List` - Lists all tasks

Lists all statuses of tasks that are currently in the overall task list

Syntax: `list`

Outcome: numbered of iists of tasks and status of whether each task is marked

<br>
<br>

### `Todo`, `Deadline`, `Event` - Add task of specified type

Adds a Todo, Deadline or Event task to the task list


#### `Todo` - add task of type todo

Adds a new simple task with just a description

Syntax: `Todo <description>`

Example: `Todo read notes`

Outcome: task with description "read notes" is added into task list


#### `Deadline` - add task of type deadline

Adds a new task with a description and a deadline to complete by

Syntax: `Deadline <description> /<yyyy-MM-ddThh:mm>`

Example: `Deadline cs2103 iP Submission /2023-02-17T23:59`

Outcome: task with description "cs2103 iP Submission" and deadline of 17 Feb 2023, 11:59pm is added into the task list


#### `Event` - add task of type event

Adds a new task with a description that spans over 2 dates

Syntax: `Event <description> /<yyyy-MM-ddThh:mm> /<yyyy-MM-ddThh:mm>`

Example: `Event iP Extension /2023-02-18T00:00 /2023-02-20T23:59`

Outcome: task with description "iP Extension" spanning from 18 Feb 2023, 12am to 20 Feb 2023, 11:59pm is added into the task list

<br>
<br>

### `Mark`, `Unmark` - Mark task of specified index as completed or not completed

Marks task in task list as complete or incomplete


#### `Mark` - mark task as complete

Marks an existing task at specified index of the overall task list as complete.

Prompt differs if the task was already marked as complete before this command.

Syntax: `Mark <index>`

Example: `List` followed by `Mark 1`

Outcome: task at index 1 of the task list is marked as complete. separate prompt for if the task was already marked as complete.

<details>
    <summary> If task was previously marked as <b>incomplete</b> </summary>

> Current list: <br>
>        1) [T] [ ] todo <br>
>        2) [D] [ ] deadline ( by: 17 FEB 2023, FRI, 11:59PM ) <br>
>        3) [E] [ ] event ( from: 18 FEB 2023, SAT, 12:00AM ) ( to: 20 FEB 2023, MON, 11:59PM )

U - `Mark 1`
> This task is marked as done: <br>
>        [T] [X] todo

</details>

<details>
    <summary> If task was previously marked as <b>complete</b> </summary>

> Current list: <br>
>        1) [T] [X] todo <br>
>        2) [D] [ ] deadline ( by: 17 FEB 2023, FRI, 11:59PM ) <br>
>        3) [E] [ ] event ( from: 18 FEB 2023, SAT, 12:00AM ) ( to: 20 FEB 2023, MON, 11:59PM )

U - `Mark 1`
> This task <br>
>        [T] [X] todo <br>
> was already marked as done! Did you forget? OR U PLAYIN ME?!

</details>

#### `Unmark` - mark as incomplete

Unmarks an existing task at specified index of the overall task list as incomplete.

Prompt differs if the task was already marked as incomplete before this command.

Syntax: `Unmark <index>`

Example: `Unmark 2`

Outcome: task at index 2 of the task list is marked as incomplete. separate prompt for if the task was already marked as incomplete.

<details>
    <summary> If task was previously marked as <b>incomplete</b> </summary>

> Current list: <br>
>        1) [T] [ ] todo <br>
>        2) [D] [ ] deadline ( by: 17 FEB 2023, FRI, 11:59PM ) <br>
>        3) [E] [ ] event ( from: 18 FEB 2023, SAT, 12:00AM ) ( to: 20 FEB 2023, MON, 11:59PM )

U - `Unmark 2`
> This task <br>
>       [D] [ ] deadline ( by: 17 FEB 2023, FRI, 11:59PM ) <br>
> hasn't been done! How can you not know?!

</details>

<details>
    <summary> If task was previously marked as <b>complete</b> </summary>

> Current list: <br>
>        1) [T] [ ] todo <br>
>        2) [D] [X] deadline ( by: 17 FEB 2023, FRI, 11:59PM ) <br>
>        3) [E] [ ] event ( from: 18 FEB 2023, SAT, 12:00AM ) ( to: 20 FEB 2023, MON, 11:59PM )

U - `Unmark 2`
> Okay... Being unproductive I see...: <br>
>        [D] [ ] deadline ( by: 17 FEB 2023, FRI, 11:59PM )

</details>

<br>
<br>

### `Delete` - Deletes a task at a specified index

Deletes an existing task from the current task list

Syntax: `Delete <index>`

Example: `Delete 3`

Outcome: deletes task at index 3 of the overall list

> Current list: <br>
>        1) [T] [ ] todo <br>
>        2) [D] [X] deadline ( by: 17 FEB 2023, FRI, 11:59PM ) <br>
>        3) [E] [ ] event ( from: 18 FEB 2023, SAT, 12:00AM ) ( to: 20 FEB 2023, MON, 11:59PM )

U - `Delete 3`
> The following task has been removed: <br>
>        [E] [ ] event ( from: 18 FEB 2023, SAT, 12:00AM ) ( to: 20 FEB 2023, MON, 11:59PM ) <br>
> Currently, your list has 2 tasks.

<br>
<br>

### `Find`, `Check` - Searches through task list

Searches through the task list and returns a list of tasks that match the search term


#### `Check` - search using date

Searches for tasks that are due on a date (deadline task) and span over a specific date (event task)

Syntax: `Check <yyyy-MM-dd>`

Example: `Check 2023-02-12`

Outcome: finds all deadline tasks that are due on 12 FEB 2023 and all event tasks that start on or before 12 FEB 2023 and end on or after 12 FEB 2023


#### `Find` - search using description

Searches for tasks that contain a specific search term in the task description

Syntax: `Find <search term>`

Example: `Find famil`

Outcome: finds all tasks that contain the term "famil" (case sensitive)

> Current list: <br>
>        1) [T] [ ] get family to use Duke <br>
>        2) [D] [X] homework ( by: 17 FEB 2023, FRI, 11:59PM ) <br>
>        3) [E] [ ] families connect event ( from: 18 FEB 2023, SAT, 12:00AM ) ( to: 20 FEB 2023, MON, 11:59PM )

U - `Find famil`
> You have 1 task containing the following search term: <br>
>        famil <br>
> <br>
>        1) [T] [ ] get family to use Duke <br>
>        2) [E] [ ] families connect event ( from: 18 FEB 2023, SAT, 12:00AM ) ( to: 20 FEB 2023, MON, 11:59PM ) 

<br><br>

### `Bye` - say bye to duke :(

Ends current session

Syntax: `Bye`

Outcome: Duke will be sad to see you leave but duke knows you will return
