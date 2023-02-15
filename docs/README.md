# Duke User Guide

## Features 

### Keeps track of your tasks 

Duke can help you to keep a list of tasks you have to complete

### Searching for specific tasks

Duke allows you to search for specfic tasks that contain keywords that you are looking for

## Usage

### Add task into Duke

Use the following commands to add either a todo, deadline or event task into Duke

```
todo assignment 1

Got it. I've added this task:
  [T][] assignment 1
Now you have 1 task in the list
```

```
deadline grocery shopping /by 19/03/2023

Got it. I've added this task:
  [D][] grocery shopping (by: 19 Mar 2023)
Now you have 2 task in the list
```

```
event birthday party /from 20/03/2023 /to 21/03/2023

Got it. I've added this task:
  [E][] birthday party (from: 20 Mar 2023 to: 21 Mar 2023) 
Now you have 3 task in the list
```

### Look at what tasks you currently have

Use `list` to list out all previously recorded tasks

```
list
  1. [T][] assignment 1
  2. [D][] grocery shopping (by: 19 Mar 2023)
  3. [E][] birthday party (from: 20 Mar 2023 to: 21 Mar 2023) 
```

### Mark/unmark a task you have completed

Use `mark` and `unmark` to mark or unmark a task that you have done

```
mark 2

Nice! I've marked this task as done:
  [D][X] grocery shopping (by: 19 Mar 2023)
```

```
unmark 2

OK, I've marked this task as not done yet:
  [D][] grocery shopping (by: 19 Mar 2023)
```

### Search for task with specific keywords

Use `find` to only show tasks that contain a specific keyword

```
find grocery

1. [D][] grocery shopping (by: 19 Mar 2023)
```

### Delete a task

Use `delete` to delete a task that you no longer need

```
delete 2

Got it. I've removed this task:
  [D][] grocery shopping (by: 19 Mar 2023)
Now you have 2 task in the list
```

### Exiting Duke
 
Use `bye` to close Duke