### User Guide

# Features
* Table of Contents
{:toc}

### list : `list`

Lists all tasks stored by you.

Example of usage:
`list`

Expected outcome:
```
1. [T][X] Wash Clothes
2. [D][ ] Do CS2106 Lab (by: 01 Aug 2022)
3. [E][ ] NUS Career Fair (from: 01 Aug 2022 to: 01 Sep 2022)
```

### todo TASK_DESCRIPTION : `todo`

Adds a ToDo task.

Example of usage:
`todo Wash Clothes`

Expected outcome:
```
Got it. I've added this task:
  [T][ ] Wash Clothes
 Now you have 1 tasks in the list
```

### deadline TASK_DESCRIPTION /by YYYY-MM-DD: `deadline`

Adds a Deadline task with deadline specified in the format below.

Example of usage:
`deadline Do CS2106 Lab /by 2022-08-01`

Expected outcome:
```
Got it. I've added this task:
  [D][ ] Do CS2106 Lab (by: 01 Aug 2022)
 Now you have 2 tasks in the list
```

### event TASK_DESCRIPTION /from YYYY-MM-DD /to YYYY-MM-DD: `event`

Adds a Event task with timeline specified in the format below.

Example of usage:
`event NUS Career Fair /from 2022-08-01 /to 2022-09-01`

Expected outcome:
```
Got it. I've added this task:
  [E][ ] NUS Career Fair (from: 01 Aug 2022 to: 01 Sep 2022)
 Now you have 3 tasks in the list
```

### mark INDEX: `mark`

Marks a task with the specified index.

Example of usage:
`mark 1`

Expected outcome:
```
Nice! I've marked this task as done:
[x] Wash Clothes
```

### unmark INDEX: `unmark`

Unmarks a task with the specified index.

Example of usage:
`unmark 1`

Expected outcome:
```
OK, I've marked this task as not done yet:
[ ] Wash Clothes
```

### find KEYWORD: `find`

Finds a task with the specified keyword.

Example of usage:
`find NUS Career Fair`

Expected outcome:
```
1. [E][ ] NUS Career Fair (from: 01 Aug 2022 to: 01 Sep 2022)
```

### delete INDEX: `delete`

Deletes a task with the specified index.

Example of usage:
`delete 2`

Expected outcome:
```
Noted. I've removed this task:
  [D][ ] Do CS2106 Lab (by: 01 Aug 2022)
Now you have 2 tasks in the list.
```

### changefile FILE_PATH: `changefile`

Changes data storage location specified by you, remember to always store in txt file.

Example of usage:
`changefile src/data/storedata.txt`

Expected outcome:
```
File path successfully changed
```

### bye: `bye`

Exits the application.

Example of usage:
`bye`

Expected outcome:
```
Bye. Hope to see you again soon!
```