# User Guide

## Features 

### Add tasks

Add todo, deadlines and events to the task list

### Store and see list of all tasks

All tasks are stored in the app can be seen through the `list` command 

### Mark tasks as done or not done

Tasks can be marked as done or not done to keep track of what needs to be done

### Delete tasks

Tasks can easily be deleted

### Reset task list

The stored lists of tasks can be reset so you can start ov

### Filter tasks

Get list of all deadlines and tasks on a certain day or within a certain period

### Find tasks 

You can find tasks by their name

## Usage

### `todo` - Add todo

Add a todo task to the task list

Example of usage: 

`todo read book`

Expected outcome:

The task "read book" is added to your list.

```
Added your task: [T][ ] read book
You have 1 tasks.
```

### `deadline` - Add deadline

Add a deadline to the task list.

Example of usage:

`deadline return book /by 12-02-2021 1800`

Expected outcome:

Deadline "return book" by 12th Feb 2021 is added to your tasks.

```
Added your task: [D][ ] return book (by: 12 Feb 2021 06:00 PM)
You have 2 tasks
```

### `event` - Add event

Add an event to list of tasks.

Example of usage:

`event hackathon /from 18-03-2023 1000 /to 19-04-2023 1800`

Expected outcome:

Event "hackathon" from 18 March 2023 to 19 April 2023 is added to your tasks

```
Added your task: [E][ ] hackathon (from: 18 Mar 2023 10:00 AM, to 19 Apr 2023 06:00 PM)
You have 3 tasks.
```

### `list` - List tasks

Lists all tasks in your task list

Example of usage:

`list`

Expected outcome:

list of your tasks

```
You have the following tasks: (So few~ good going!)
1. [T][ ] read book
2. [D][ ] return book (by: 12 Feb 2021 06:00 PM)
3. [E][ ] hackathon (from: 18 Mar 2023 10:00 AM, to 19 Apr 2023 06:00 PM)
You have 3 tasks.
```

### `mark` - Mark task

Mark task at a certain index as done.

Example of usage:

`mark 2`

Expected outcome:

The 2nd task (```[D][ ] return book (by: 12 Feb 2021 06:00 PM)``` in this example) is marked done

```
Good job! I've marked this task as done:
[D][X] return book (by: 12 Feb 2021 06:00 PM)
```

### `unmark` - Unmark task

Mark the task at a certain index as not done.

Example of usage:

`unmark 2`

Expected outcome:

The 2nd task (```[D][X] return book (by: 12 Feb 2021 06:00 PM)``` in this example) is marked not done

```
Bummer! Have fun doing this:
[D][ ] return book (by: 12 Feb 2021 06:00 PM)
```

### `delete` - Delete a task

Delete task at a certain index

Example of usage:

`delete 1`

Expected outcome:

The 1st task (```[T][ ] read book``` in this example) is deleted

```
I've removed this task
[T][ ] read book
```

### `filter` - Filter tasks by date

Get all events and deadline within a certain period or on a certain day

Example of usage:

`filter 29-03-2023`

Expected outcome:

List of events and deadline on ```29-03-2023```

```
Tasks in this period: 
1. [E][ ] hackathon (from: 18 Mar 2023 10:00 AM, to 19 Apr 2023 06:00 PM)
```

Example of usage:

`filter 19-04-2020 1800 /to 20-05-2024 1000`

Expected outcome:

List of events and deadline in between ```19-04-2020 1800``` and ```20-05-2024 1000```.

```
Tasks in this period: 
1. [D][ ] return book (by: 12 Feb 2021 06:00 PM)
2. [E][ ] hackathon (from: 18 Mar 2023 10:00 AM, to 19 Apr 2023 06:00 PM)
```

### `find` - Find task

Find task by name.

Example of usage:

`find book`

Expected outcome:

List of all tasks with ```book``` in their name.

```
Here are the matching tasks in your list:
1. [T][ ] read book
2. [D][ ] return book (by: 12 Feb 2021 06:00 PM)
2 tasks match the keyword.
```

### `help` - List commands

Displays a list of all possible commands and how to use them.

Example of usage:

`help`

Expected outcome:

Description of the outcome.

```
Hello! You can use the following commands:
To add a todo task, type "todo " + your task.
To add a task with a deadline, type "deadline " + your task + "/by " + your deadline.
To add an event, type "event " + your event + " "/from " + event start time + "/to " + event end time.
For deadlines and events, the date and time should by in the format "dd-MM-yyyy HHmm". Eg: 15-05-2019 1800.
To see an list of your tasks, type "list".
To mark a task as done, type "mark <task number>".
To mark a task as not done, type "unmark <task number>".
To delete a task, type "delete <task number>".
A task marked with a X is done.
To reset the task list type "reset".
To get a list of deadlines and events on a particular day, type "filter " + date (dd-MM-yyyy).
To get a list of deadlines and events during a period of time, type "filter " + start of period " /to " end of period (dd-MM-yyyy HHmm).
To find a task, type "find <keywords>".
To close me, type "bye".
Have fun!
```

### `reset` - Reset task list

Removes all tasks in task list.

Example of usage:

`reset`

Expected outcome:

Task list has no tasks.

```
Your task list has been resetted. You have no tasks
```

### `bye` - Ends chat

Displays a final message and closes the chatbot in 2 seconds.

Example of usage:

`bye`

Expected outcome:

The chatbot closes after the following message is displayed.

```
Bye! Hope to see you soon!
```