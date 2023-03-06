# Chika Duke User Guide
![Image of ChikaDuke](Ui.png)
## Features 

### Feature- add delete and view tasks

Allows you to change and view your task list

## Basic Tutorial Usage

### `todo myproject` 
Add todo with name myproject

#### Expected output:
```
Got it I've added a todo
[T][] myproject
```
***

### `deadline tutorial /by 2023-05-01`
Add deadline tutorial by a date

#### Expected output:
```
Got it I've added a deadline
[D][] tutorial (by: 2023-05-01)
```
***

### `event party /from 2023-05-01 /to 2023-05-02`
Add event party from a date to another

#### Expected output:
```
Got it I've added an event
[E][] party (from: 2023-05-01 to: 2023-05-02)
```
***

### `list`
List all tasks

#### Expected output:
```
Here are the tasks in your list
1.[T][] myproject
2.[D][] tutorial (by: 2023-05-01)
3.[E][] party (from: 2023-05-01 to: 2023-05-02)
```
***

### `find yproj`
find task based on substring

#### Expected output:
```
Here are the matching tasks in your list
1.[T][] myproject
```
***

### `mark 1`
mark 1st task

#### Expected output:
```
Nice! I've marked this task as done:
[T][X] myproject
```
***

### `unmark 1`
unmark 1st task

#### Expected output:
```
OK, I've marked this task as not done yet:
[T][] myproject
```
***

### `delete 1`
delete 1st task

#### Expected output:
```
I have removed this task
[T][] myproject
```

Thats it! feel free to use the help function in the app
whenever you are lost or get a recap of the function in the app itself!
