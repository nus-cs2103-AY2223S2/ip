# User Guide for `Duuk`
Muahahahh this is Duuk, Duke's evil twin brother. He might be evil but he is ironically good at whatever tasks you throw at him. A chatbot is what he is! Surprisingly, he makes himself useful by helping to make your life more organised.

## Features

## Installation <a name="installation"></a>

1. Download the JAR file.
2. Open the JAR file by typing the command `java -jar duke.jar` on your command line interface (CLI). Make sure that on the CLI, you are in the same directory (folder) that contains the downloaded JAR file.
3. Proceed to start a chat session with Prof Duke through the graphical user interface (GUI)!

### Feature - Add a task

Add a task to be recorded.

### Feature - Delete a task

Delete an existing task.

### Feature - List

Displays all tasks that are recorded.

### Feature - Tag

Tag an existing task with a '#' prefix along with the description.

### Feature - Find

Find all tasks that include the keyword.

### Feature - Mark

Record a task as done.

### Feature - Unmark

Record a task as undone.


## Usage

### `todo` - Add a todo task to the list

Adds a todo task to the list

Example of usage:

`todo task`

Expected outcome:

A todo task will be saved and the following output will be displayed:

```
WHAT A BOTHER! Whatever...I've added this task:
    [T][] task
Boy now you have 1 tasks in the list.
```

### `deadline` - Add a deadline task to the list

Adds a deadline task to the list

Example of usage:

`deadline task /by 2023-03-11`

Expected outcome:

A task with a due date will be record and the following output will be displayed:

```
WHAT A BOTHER! Whatever...I've added this task:
    [D][] task (11 March 2023)
Boy now you have 1 tasks in the list.
```

### `event` - Adds an event to the list

Adds an event with a start date and an end date to the list

Example of usage:

`event task /from 2023-02-17 /to 2023-02-25`

Expected outcome:

An event task will be saved and the following output will be displayed:

```
WHAT A BOTHER! Whatever...I've added this task:
    [E][] task (17 February 2023 to 25 February 2023)
Boy now you have 1 tasks in the list.
```

### `delete` - Deletes a task

Deletes the task at a given index.

Example of usage:

```
todo task
delete 1
```

Expected outcome:

The task will be deleted and the following output will be displayed:

```
FINE. I've removed this task
    [T][] task
Boy you are not bad yourself...you have 0 tasks in the list.   
```

### `mark` - Mark a task as done

Marks the task at a given index as done.

Example of usage:

```
todo task
mark 1
```

Expected outcome:

The task will be marked as done and the following output will be displayed:

```
MARK MY WORDS...and this task:
    [T][X] task
```

### `unmark` - Marks a task as not done

Marks the task at a given index as not done.

Example of usage:

```
todo task
mark 1
unmark 1
```

Expected outcome:

The task will be marked as not done and the following output will be displayed:

```
MARK MY WORDS...and this task:
    [T][X] task
    
Make up your mind! I will unmark just this once
    [T][ ] task
```

### `list` - Lists all tasks

Displays all the tasks being tracked.

Example of usage:

```
todo task A
deadline task B /by 2023-03-25
event task C /from 2023-03-23 /to 2023-03-28
```

Expected outcome:

The following output will be displayed:

```
Here are the tasks in your list:
1. [T][ ] task A
2. [D][ ] task B (25 March 2023)
3. [E][ ] task C (23 March 2023 to 28 March 2023)
```

### `tag` - Lists all tasks on a given date

Tag a task with a '#' prefix along with the description.

Example of usage:

```
tag 1 fun
```

Expected outcome:

The following output will be displayed:

```
You want me to what? Tag? Fine.
1. [D][] task A #fun (20 February 2023)
```

### `find` - Finds tasks by a search keyword

Displays all the tasks matching a given keyword

Example of usage:

```
todo I
todo am
todo a
todo happy
todo man

find man
```

Expected outcome:

The following output will be displayed.
```
I dug out the WHOLE BEDROOM and found want you wanted NOW LEAVE:
1. [T][ ] man
```