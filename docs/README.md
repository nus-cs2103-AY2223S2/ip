# User Guide

## Features that Wessy has 

### Able to keep track of the tasks you need to do.
Different types of tasks can specify different things. 
For example, the `deadline` task can keep track of the specified deadline.
The task with fixed duration (using the `fix` command) can record exactly how much time is required to do the task.

### Able to tick off the tasks that you have completed.
This can be achieved by marking those tasks that are completed via the `mark` command. This action can be undone using the `unmark`.

### Able to continue from where you left when reopening the program each other
The tasks and their status will be exactly the same as those when you quited the program previously.

## Commands that Wessy accepts and how to use them

### `todo` - To create a "todo" task.
To create a task that only consists of the task description.

If you send: `todo Water the plants.`

Wessy's response:
```
Wessy:
Got it. I've added this task:
  [T][ ] Water the plants
Now you have 1 task in the list.
```

### `deadline` - To create a deadline.
To create a task that consists of the task description and a specified deadline, using the `/by` time specifier.

If you send: `deadline Finish writing ES2660 essay /by 12/2/2023 16:00`

Wessy's response:
```
Wessy:
Got it. I've added this task:
  [D][ ] Finish writing ES2660 essay (by: 2023-02/12 16:00)
Now you have 2 tasks in the list.
```

### `event` - To create a event.
To create a event with specified start and end times, using the `/from` and `/to` time specifiers.

If you send:
`event CS2103T project meeting /from 12-2-2023 09.00 /to 12-2-2023 11.00`

Wessy's response:
```
Wessy:
Got it. I've added this task:
  [E][ ] CS2103T project meeting (from: 12-2-2023 09.00 to: 12-2-2023 11.00)
Now you have 3 tasks in the list.
```

### `doafter` - To create a task that is to be done after a specific time/task.
To create a task that is to be done after a specific time/task, using the `/after` specifier.

If you send: `doafter Google search for abs workouts /after midterms`

Wessy's response:
```
Wessy:
Got it. I've added this task:
  [A][ ] Google search for abs workouts (after: midterms)
Now you have 4 tasks in the list.
```

### `fix` - To create a task with a fixed duration.
To create a task with a fixed duration, using the `/need` specifier.

If you send:
`fix Do abs workouts /for 30 minutes`

Wessy's response:
```
Wessy:
Got it. I've added this task:
  [F][ ] Do abs workouts (for: 30 minutes)
Now you have 5 tasks in the list.
```

### `mark` - To mark the specified task as done.
Specify which task to mark by inserting the task number after the `mark` command, using 1-based indexing.

If you send: `mark 2`

Wessy's response:
```
Wessy:
You mark this task as done already:
  [D][X] Finish writing ES2660 essay (by: 2023-02/12 16:00)
```

### `unmark` - To mark the specified task as not done.
Specify which task to mark by inserting the task number after the `unmark` command, using 1-based indexing.

If you send: `unmark 3`

Wessy's response:
```
Wessy:
You mark this task as not done yet:
  [E][ ] CS2103T project meeting (from: 12-2-2023 09.00 to: 12-2-2023 11.00)
```

### `find` - To search for tasks that have the specific phrase in their descriptions.
Specify what phrase to search for by entering it after the `find` command.

If you send: `find abs workouts`

Wessy's response:
```
Wessy:
Here are the matching tasks on your list:
1.[A][ ] Google search for abs workouts (after: midterms)
2.[F][ ] Do abs workouts (for: 30 minutes)
```

### `delete` - To delete the specified task.
Specify which task to delete by inserting the task number after the `delete` command, using 1-based indexing.

If you send: `delete 4`

Wessy's response:
```
Wessy:
Noted! I've removed this task:
  [A][ ] Google search for abs workouts (after: midterms)
Now you have 4 tasks on the list.
```

### `list` - To list all the tasks that are currently on the list.

Wessy's response:
```
Wessy:
Here are the tasks on your list:
1.[T][ ] Water the plants
2.[D][X] Finish writing ES2660 essay (by: 2023-02/12 16:00)
3.[E][ ] CS2103T project meeting (from: 12-2-2023 09.00 to: 12-2-2023 11.00)
4.[F][ ] Do abs workouts (for: 30 minutes)
```

### `clear` - To clear all the tasks that are currently on the list.

Wessy's response:
```
Wessy:
You have cleared your task list. The list is empty now.
```

### `bye` - To quit the program.

Wessy's response:
```
Wessy:
Bye. Hope to see you again soon!
```