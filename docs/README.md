# User Guide

## Features 

## Usage

###  `todo` - adds a todo task
Allows user to add a todo task to the list

**Command Format:**

- `todo TASK_DESCRIPTION`

**Example of usage:** 

- `todo read book`

**Expected outcome:**
```
Got it. I've added this task:
[T][ ] read book
Now you have n tasks in the list
```

**Description of the outcome:**

```
A todo task has been saved into the list
```

### `Deadline` - adds a task with deadline
Allows user to add a task with deadline to the list

**Command Format:**

- `deadline /by DATE_TIME`

**Example of usage:**

- `deadline meeting /by 2019-02-02 1800`

**Expected outcome:**
```
Got it. I've added this task:
[D][ ] (by:2019-02-02 1830)
Now you have n tasks in the list
```

**Description of the outcome:**

```
A task with deadline has been saved into the list
```

### `Event` - adds an event
Allows user to add an event to the list

**Command Format:**

- `event /from DATE_TIME /to DATE_TIME`

**Example of usage:**

- `event meeting /from 2023-02-02 1800 /to 2023-02-02 1830`

**Expected outcome:**
```
Got it. I've added this task:
[E][ ] meeting  (from:2023-02-02 1800 to:2023-02-02 1830)
Now you have n tasks in the list
```

**Description of the outcome:**

```
An event has been saved into the list
```

### `tag` - tag a comment on the task
Allows user to tag a comment on a task

**Command Format:**

- `tag INDEX_OF_TASK`

**Example of usage:**

- `tag 1 fun`

**Expected outcome:**
```
Task has been tagged: read book #fun
```

**Description of the outcome:**

```
Based on the index of the task, it will be tagged with the description users have inputted
```

### `unmark` - mark a task as undone
Allows user to mark as a task as undone

**Command Format:**

- `unmark INDEX_OF_TASK`


**Example of usage:**

- `unmark 1`

**Expected outcome:**
```
OK, I've marked this task as not done yet:
[T][ ] read book #fun
```

**Description of the outcome:**

```
Based on the index of the task, the task will be marked as undone
```

### `mark` - mark a task as done
Allows user to mark as a task as done

**Command Format:**

- `mark INDEX_OF_TASK`

**Example of usage:**

- `mark 1`

**Expected outcome:**
```
Nice! I've marked this task as done:
[T][X] read book #fun
```

**Description of the outcome:**

```
Based on the index of the task, the task will be marked as done
```

### `list` - shows a list of task
Shows users the list of tasks that have been added previously

**Command Format:**

- `list`

**Example of usage:**

- `list`

**Expected outcome:**
```
Here are the tasks in your list:

1.[T][X] read book #fun
2.[E][ ] project meeting (from:2019-02-02 1800 to:2019-02-02 1830)
3.[D][ ] (by:2019-02-02 1830)
4.[T][ ] read book
5.[E][ ] meeting  (from:2023-02-02 1800 to:2023-02-02 1830)
```

**Description of the outcome:**

```
Shows a list of tasks that were previously added
```

### `delete` - deletes a task
deletes the task based on index

**Command Format:**

- `delete INDEX_OF_TASK`

**Example of usage:**

- `delete 1`

**Expected outcome:**
```
Noted. I've removed this task: 
[T][X] read book #fun
Now you have 4 tasks in the list
```

**Description of the outcome:**

```
Deletes the first task on the list
```

### `find` - finds task based on a keyword
Returns a list with the given keyword

**Command Format:**

- `find KEYWORD`

**Example of usage:**

- `find book`

**Expected outcome:**
```
1.[T][ ] read book
```

**Description of the outcome:**

```
There is only one task in the whole list, with the keyword "book"
```

### `bye` - Ends conversation with Duke
Stops the conversation with the Duke

**Command Format:**

- `bye`

**Example of usage:**

- `bye`

**Expected outcome:**
```
Bye, Hope to see you again soon!
```

**Description of the outcome:**

```
Duke will return his greetings
```