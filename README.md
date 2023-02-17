# Duke
## User Guide
Duke **is a chat box for recording down and managing your daily task** easily.

### - Quick start
### - Features
+ **todo**
+ **deadline**
+ **event**
+ **list**
+ **mark**
+ **unmark**
+ **delete**
+ **find**
+ **sort**
+ **bye**


## Quick start
+ Ensure you have Java 11 in your computer.
+ Download the latest duke.jar.
+ Put duke.jar inside a folder.
+ Right-click on the folder and open the terminal.
+ Type command **java -jar myIP.jar**.
+ A GUI similar to the below should appear in a fel seconds.
![This is an image](docs/Ui.png)



## Features

### `todo`
Add in the task that you are planing to do.

**Format:** `todo + [task name]`

**Example:** `todo play`


---
### `deadline`
Record down a deadline for a task.

**Format:** `deadline + [task name] + /by + dd/MM/yyyy HHmm`

**Example:** `deadline homework /by /01/01/2019 1830`


---
### `event`
Record down the start date and end date for an event.

**Format:** `event + [task name] + /from + dd/MM/yyyy HHmm + /to + dd/MM/yyyy HHmm`

**Example:** `event assign1 /from 01/01/2023 09:00 /to 01/01/2023 12:00`

---
### `list`
Display all the tasks.

**Format:** `list`


**Sample result:**
```
1.[T][] play
2.[D][] home (by:Jan 1 2019 18:30pm)
3.[E][] assign1 (from:Jan 1 2023 09:00 to:Jan 1 2023 12:00)
```

---
### `mark`
Mark a task as done.

**Format:** `mark + [task number]`

**Example of usage:** `mark 1`

**Expected result:**
```
Nice! I've marked this task as done:
1.[T][X] play
```

---
### `unmark`
Unmark a task as done.

**Format:** `unmark + [task number]`

**Example:** `unmark 1`

**Expected result:**
```
Nice! I've marked this task as undone:
1.[T][ ] play
```

---
### `delete`
Delete a task.

**Format:** `delete + [task number]`

**Example:** `delete 1`

**Expected result:**
``` 
Noted. I've removed this task:
[T][ ] play
Now you have 0 task in the list.
```
---
### `find`
Find tasks.

**Format:** `Find + [search key]`

**Example:** `Find play`

**Expected result:**
``` 
1.[T][ ] play
```

---
### sort
Sort tasks by todo, deadline or event.

**Format:** `sort + todo/deadline/event`

**Example**: `sort todo`

**Expected result:**
``` 
1.[T][ ] play
```
---
### bye
Exit system.

**Format:** bye

## Let's start using DUKE!