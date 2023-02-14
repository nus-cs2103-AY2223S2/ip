# User Guide

Connor is your personal task manager to keep track of your tasks. Tasks are defined to be a todo (no date), a deadline (one date) or an Event (two dates). 

Connor is completely capable of sorting your tasks by the date, read on further to learn the format that Connor utilizes to show a sorted list.

## Definitions 

#### **Tasks**
- Tasks are defined to be a todo (no date), a deadline (one date) or an Event (two dates). 
- A task must have a non-empty name.
- Tasks that requires a date (deadline and event) requires a valid date.

#### **Date**
- YYYY-MM-DD TTTT ```2023-02-17 1800```
- Date must be valid (31st Feb 2023 will not be accepted).

#### **Commands**
- Commands are case in-sensitive


## Features 

### **```todo```**

Adds a task that has no time constraint. This task will be stored in the memory in a form of a text file. 

#### Usage format: 

```todo {TASK}```

#### Connor's reply:

```
I have added {TASK} to my memory
  [T][ ] {TASK}
You have {Number of tasks in memory} tasks in the list  
```

#### Stored format:

```
T|false|{TASK}
```

### **```deadline```**

Adds a task that has a deadline. This task will be stored in the memory in a form of a text file. 

#### Usage format: 

```deadline {TASK} /by {DATE}```

#### Connor's reply:

```
I have added {TASK} to my memory
  [D][ ] {TASK}
You have {Number of tasks in memory} tasks in the list  
```

#### Stored format:

```
D|false|{TASK}|{deadline}
```

### **```event```**

Adds a task that has a start and end date. This task will be stored in the memory in a form of a text file. 

#### Usage format: 

```Event {TASK} /from {STARTDATE} /to {ENDDATE}```

#### Connor's reply:

```
I have added {TASK} to my memory
  [E][ ] {TASK}
You have {Number of tasks in memory} tasks in the list  
```

#### Stored format:

```
E|false|{TASK}|{STARTDATE}|{ENDDATE}
```

### **```list```**

Gets all tasks in the list.

#### Usage format: 

```list```

#### Connor's reply:

```
1.[T][ ] {TASK}
2.[D][ ] {TASK} {DATE}
3.[E][ ] {TASK} {STARTDATE} {ENDDATE}
I have 3 tasks in my memory
```

### **```mark {INDEX}```**

Marks a certain task on the list (according to its index) as done. 

#### Usage format: 

```mark {INDEX}```

#### Connor's reply:

```
Understood, I have marked the task as done:
  [T][X] {TASK}
```

### **```unmark {INDEX}```**

Marks a certain task on the list (according to its index) as undone. 

#### Usage format: 

```unmark 1```

#### Connor's reply:

```
Understood, I have marked the task as undone:
  [T][ ] {TASK}
```

### **```delete {INDEX}```**

Deletes a certain task on the list (according to its index).

#### Usage format: 

```delete 1```

#### Connor's reply:

```
I have removed {TASK} from my memory
  [T][ ] {TASK}
You have {Number of tasks in memory} tasks in the list.  
```

### **```deleteall```**

Deletes all tasks on the list.

#### Usage format: 

```deleteall```

#### Connor's reply:

```
All tasks on the list have been cleared. 
```

### **```find {KEYWORD}```**

Deletes a certain task on the list (according to its index).

#### Usage format: 

```find work```

#### Connor's reply:
*Assuming that are 3 todo tasks on the list named : school work, project work and exercise* 

```
Here are the matching results:
  1.[T][ ] school work
  2.[T][ ] project work
```

### **```sort```**

Sorts the list according to the following rules: 
- todo will be placed higher than deadline and event
  - todo tasks are sorted in chronological order
- deadline will be placed higher than event, but lower than todo
  - deadline tasks are sorted based on earliest time (the earliest goes on top)
  - if both have the same deadline, they are sorted in chronological order.
- event will be placed lower than todo and deadline.
  - event tasks are first sorted based on the earliest start time (the earliest goes on top).
  - if both have the same earliest start time, they are then sorted based on earliest end time.
  - if both have same earliest and start time, they are then sorted in chronological order.

#### Usage format: 

```sort```

#### Connor's reply:

```
I have successfully sorted the tasks.
```

### **```hi```**

Prompts Connor to greet you.

#### Usage format: 

```hi```

#### Connor's reply:

```
Hi, I hope that you are having a nice day  
```

### **```bye```**

Prompts Connor to say goodbye. Program will exit after a 1-second delay. 

#### Usage format: 

```bye```

#### Connor's reply:

```
It was a good session Hank. Bye.
```

## **Acknowledgements:**

The gui package contains code that is adapted from: https://se-education.org/guides/tutorials/javaFxPart4.html.

