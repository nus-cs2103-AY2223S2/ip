# User Guide :technologist:

Duke is a task planner chat bot that is optimized for Command Line Interface while also providing Graphic User Interface. The name *Duke* was chosen as a placeholder name, in honor of [Duke, the Java Mascot]( https://www.oracle.com/java/duke/).

## Features 

### Adds a todo task
A todo task is a task to be done, without any due dates. A todo task is represented by a 'T' in the first box.

Input format:

`todo <description>`

Example input:

`todo buy groceries`

Example output:

```
Got it. I’ve added this task:

[T][ ][ ] buy groceries
```

### Adds a deadline task
A dealine task is a task to be done, with a due dates. A deadline task is represented by a 'D' in the first box.

Input format:

`deadline <description> /by YYYY-MM-DD`

Example input:

`deadline quiz /by 2023-01-01`

Example output:

```
Got it. I’ve added this task:

[D][ ][ ] quiz (by: Jan-01-2023)
```

### Adds a event task
A event task is a task to be done, that has a starting date and an ending date. An event task is represented by a 'E' in the first box.

Input format:

`event <description> /from YYYY-MM-DD /to YYYY-MM-DD`

Example input:

`event CNY holiday /from 2023-01-23 /to 2023-01-24`

Example output:

```
Got it. I’ve added this task:

[E][ ][ ] CNY holiday (from: Jan-23-2023 to: Jan-24-2023)
```

### List all task
Users can view all task stored in the task planner. 

Input format:

`list`

Example output:

```
You have 3 tasks in the list.
1.[T][ ][ ] buy groceries
2.[D][ ][ ] quiz (by: Jan-01-2023)
3.[E][ ][ ] CNY holiday (from: Jan-23-2023 to: Jan-24-2023)
```

### Mark a task as done
A task can be marked as done when it is completed. A task marked as done is represented by a ‘X’ in the second box.

Input format:

`mark <task number>`

Example input:

`mark 1`

Example output:

```
Nice! I’ve marked this task as done:

[T][X][ ] buy groceries
```

### Unmark a task as done
A task can be unmarked as not done when it is not completed. A task marked as not done is represented by an empty space in the second box.

Input format:

`unmark <task number>`

Example input:

`unmark 1`

Example output:

```
OK, I’ve marked this task as not done yet:

[T][ ][ ] buy groceries
```

### Delete a task
A task can be delete when users want to remove it completely. A task marked as deleted will not be in the list again.

Input format:

`delete <task number>`

Example input:

`delete 1`

Example output:

```
Noted. I’ve removed this task:

[T][ ][ ] buy groceries
```

### Find a task
Users can search certain task with a keyword. All task with description matching to the keyword will be shown. 

Input format:

`find <keyword>`

Example input:

`find quiz`

Example output:

```
Here are the matching tasks in your list:
1. [D][ ][ ] quiz (by: Jan-01-2023)
```

### Add recurrence to a task
Users can add recurrence for task that is happening on a regular basis. The available frequencies are: daily, weekly, monthly, yearly. A task with recurrence is represented by the initial of the frequency in the third box.

At the start of the day/week/month/year, the task will be automatically marked as not done. All dates related to the task (if any) will automatically be updated.

Input format:

`recurrence <task number> <frequency>`

Example input:

`recurrence 1 weekly`

Example output:

```
Noted. I’ve added recurrence for this task:
[D][ ][W] quiz (by: Jan-01-2023)
```

### Exit
Users can exit the task planner.

Input format:

`bye`

Example output:

```
Bye. Hope to see you again soon!
```

