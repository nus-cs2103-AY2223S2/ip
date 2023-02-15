# User Guide
<hr/>

 ## Dudu

> Dudu is a task manager which help you to keep track of your todo, deadline and event tasks

## Features 
<hr/>


## Usage
<hr/>

### `Hi` - Greeting to Dudu 

> Send a greeting to Dudu, and Dudu will request for your service.

Example of usage: 

`hi`

Expected outcome:

Dudu should send you the following

```
Hello! I'm Dudu
What can I help you?
```
![This is a screenshot of the outcome](image/greeting.png)

<hr/>

### `list` - List down all the tasks

> Dudu will send you a list containing all the saved task.

Example of usage:

`list`

Expected outcome:

Dudu should send you the following

```
Here are the tasks in your list:
1.[T][X] read book
2.[D][X] return book (by: Nov 12 2022)
3.[D][ ] iP (by: Feb 17 2023)
```

![This is a screenshot of the outcome](image/list.png)

<hr/>

### `todo [KEYWORD]` - Add a TODO task

> Dudu will help you add the todo task to the list, and tells you the total number of tasks you have.

Example of usage:

`todo study chapter 1`

Expected outcome:

Dudu should send you the following

```
Got it. I've added this task:
  [T][ ] study chapter 1
Now you have 4 tasks in the list.
```
![This is a screenshot of the outcome](image/todo.png)

<hr/>

### `deadline [KEYWORD] /by [yyyy-MM-dd]` - Add a DEADLINE task

> Dudu will help you add the deadline task to the list, and tells you the total number of tasks you have.

Example of usage:

`deadline assignment 1 /by 2023-02-20`

Expected outcome:

Dudu should send you the following

```
Got it. I've added this task:
  [D][ ] assignment 1 (by: Feb 20 2023)
Now you have 5 tasks in the list.
```
![This is a screenshot of the outcome](image/deadline.png)

<hr/>

### `event [KEYWORD] /from [yyyy-MM-dd] /to [yyyy-MM-dd]` - Add a EVENT task

> Dudu will help you add the event task to the list, and tells you the total number of tasks you have.


Example of usage:

`event career faire /from 2023-02-06 /to 2023-02-10`

Expected outcome:

Dudu should send you the following

```
Got it. I've added this task:
  [E][ ] career faire (from: Feb 6 2023 to: Feb 10 2023)
Now you have 6 tasks in the list.
```
![This is a screenshot of the outcome](image/event.png)

<hr/>

### `delete [INDEX]` - Delete a task

Describe the action and its outcome.

Example of usage:

`keyword (optional arguments)`

Expected outcome:

Description of the outcome.

```
expected output
```

<hr/>

### `mark [INDEX]` - Mark a task as DONE

Describe the action and its outcome.

Example of usage:

`keyword (optional arguments)`

Expected outcome:

Description of the outcome.

```
expected output
```

<hr/>

### `unmark [INDEX]` - Mark a task as UNDONE

Describe the action and its outcome.

Example of usage:

`keyword (optional arguments)`

Expected outcome:

Description of the outcome.

```
expected output
```

<hr/>

### `find [KEYWORD]` - Find a task

Describe the action and its outcome.

Example of usage:

`keyword (optional arguments)`

Expected outcome:

Description of the outcome.

```
expected output
```
<hr/>

### `bye` - Exit application

Describe the action and its outcome.

Example of usage:

`keyword (optional arguments)`

Expected outcome:

Description of the outcome.

```
expected output
```