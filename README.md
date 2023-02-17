# User Guide
```agsl
Due tomorrow, do tomorrow - CHS 4-1 2016
```
Duke is the personal assistant that everybody needs to stop them from procrastinating.

## Features

### Adding tasks

You can add To-Do, Deadline or Event tasks to Duke.

These are the commands for adding the different types of tasks:

> DATE_TIME inputs must be in the format of yyyy-MM-dd HHmm

- To-Do tasks `todo TASK_DESCRIPTION`

Example Input: `todo Study`

Expected Outcome:
```
Got it, I've added this task:
[T][] Study
Now you have 1 task in your list.
```

- Deadline `deadline TASK_DESCRIPTION /by DATE_TIME`

Example Input: `deadline Homework /by 2023-02-17 2359`

Expected Outcome:
```
Got it, I've added this task:
[D][] Homework (By: 17 Feb 2023, 2359)
Now you have 2 tasks in your list.
```

- Event `event TASK_DESCRIPTION /from DATE_TIME /to DATE_TIME`

Example Input: `event Party /from 2023-02-17 1600 /to 2023-02-17 1900`

Expected Outcome:
```
Got it, I've added this task:
[E][] Party (From: 17 Feb 2023, 1600 To: 17 Feb 2023, 1900)
Now you have 3 tasks in your list.
```

### Checklist for tasks

Mark or unmark the checkbox associated to the specified task.

- Mark task: `mark TASK_NUMBER`

Example Input: `mark 2`

Example Output:
```
Nice! I've marke this task as done:
[D][X] Homework (By: 17 Feb 2023, 2359)
```

- Unmark task: `unmark TASK_NUMBER`

Example Input: `mark 2`

Example Output:
```
Ok, I've marke this task as not done yet:
[D][] Homework (By: 17 Feb 2023, 2359)
```

### Listing all tasks

Lists out every task that has been registered by Duke.

Command: `list`

Expected Outcome:
```
Here are the tasks in your list:
1.[T][] Study
2.[D][] Homework (By: 17 Feb 2023, 2359)
3.[E][] Party (From: 17 Feb 2023, 1600 To: 17 Feb 2023, 1900)
```

### Find tasks

Provide a case-sensitive keyword for Duke to filter out the tasks containing the keyword

Command: `find KEYWORD`

Example Input: `find Homewo`

Example Output:
```
Here are the matching tasks in your list:
1.[D][] Homework (By: 17 Feb 2023, 2359)
```

### Delete tasks

Deletes the specified tasks associated to the task number provided

Command: `delete TASK_NUMBER`

Example Input: `delete 1`

Example Output:
```
Noted, I've removed this task:
[T][] Study 
Now you have 2 tasks in the list.
```

### Schedule

Lists out all the tasks on a specific date

Command: `schedule DATE_TIME`

Example Input: `schedule 2023-02-17`

Expected Outcome:
```
Here are the scheduled tasks for 17 Feb 2023:
1.[D][] Homework (By: 17 Feb 2023, 2359)
2.[E][] Party (From: 17 Feb 2023, 1600 To: 17 Feb 2023, 1900)
```

### Bye

Exits from the application

Command: `bye`

