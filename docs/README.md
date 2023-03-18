# DukeDeluxe User Guide

> DukeDeluxe is a desktop app that organizes your task using CLI
![image](https://user-images.githubusercontent.com/82088609/225520779-7ee8eda8-52d3-4de4-a320-f58f27c00170.png)

DukeDeluxe frees your mind of having to remember things you need to do. It's,
- Organized
- has different types of tasks
- Quick

All you need to do is:
1. Download duke.jar from this page
2. Navigate to the directory containing the jar file in terminal
3. Run java -jar duke.jar
4. Give DukeDeluxe commands to complete your tasks!
5. Say bye to Deluxe to exit and save your tasks for next time


Features:
- [x] manage tasks efficiently
- [x] Intuitive GUI

## User Guide

### 1. todo

adds a todo task to the list

Format: todo DESCRIPTION

The description refers to the task details

Examples:

todo eat burger
![image](https://user-images.githubusercontent.com/82088609/225622232-10474dc9-4055-4130-9b4b-7bd908d30d79.png)



### 2. deadline

adds a deadline task to the list

Format: deadline DESCRIPTION /by DATE

The description refers to the task details

the date refers to the date in YYYY-MM-DD

Examples:

deadline eat burger /by 2018-01-01
![image](https://user-images.githubusercontent.com/82088609/225622348-81b3867e-9739-4393-a7f5-9e1f50e42e06.png)


### 3. event

adds an event task to the list

Format: event DESCRIPTION /from STARTDATE /to ENDDATE

The description refers to the task details

the from date refers to the starting date in YYYY-MM-DD format

the end date refers to the ending date in YYYY-MM-DD format

Examples:

event eat burger /from 2018-01-01 /to 2019-01-01
![image](https://user-images.githubusercontent.com/82088609/225622447-6f24761f-1aa1-465e-bdee-1e865d360663.png)


### 4. list

shows the tasks in the list

Format: list

Examples:

list
![image](https://user-images.githubusercontent.com/82088609/225622487-54a717d2-1d69-4ead-9919-5af60c6faa65.png)



### 5. delete

Deletes the specified item from the address book

Format: delete INDEX

Deletes the item at the specified INDEX.

The index refers to the index number shown in the displayed list.

The index must be a positive integer 1, 2, 3, …

Examples:

list followed by delete 2 deletes the 2nd task.
![image](https://user-images.githubusercontent.com/82088609/225622530-8e6317cd-9906-401d-a519-d01fb7377dda.png)


### 6. mark

marks a list as done

Format: mark INDEX

marks the item at the specified INDEX.

The index refers to the index number shown in the displayed list.

The index must be a positive integer 1, 2, 3, …

Examples:

list followed by mark 2 marks the 2nd task as done.
![image](https://user-images.githubusercontent.com/82088609/225622565-3a46e371-e014-43ae-8bc5-ad68b1757c0e.png)


### 7. unmark

unmarks a list as done

Format: unmark INDEX

unmarks the item at the specified INDEX.

The index refers to the index number shown in the displayed list.

The index must be a positive integer 1, 2, 3, …

Examples:

list followed by unmark 2 marks the 2nd task as done.
![image](https://user-images.githubusercontent.com/82088609/225622615-4c42c47e-f18a-4c62-be25-7bad6994a553.png)


### 8. bye
Exit DukeDeluxe.

Please only use this command to exit DukeDeluxe instead of closing the window, or Hachi will not be able to save your tasks to storage!

Tell DukeDeluxe your command in the format:

bye
![image](https://user-images.githubusercontent.com/82088609/225622681-e95c9966-98f5-488e-8ab4-0347b283e762.png)


<!-- ## Usage

### `Keyword` - Describe action

Describe the action and its outcome.

Example of usage: 

`keyword (optional arguments)`

Expected outcome:

Description of the outcome.

```
expected output
``` -->
