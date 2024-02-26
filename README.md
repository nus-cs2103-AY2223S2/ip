# Task Manager User Guide

This task manager allows you to organize and manage your tasks efficiently. Here are the commands you can use:

## Adding Tasks

### Todo
To add a todo task, use the following command:
```todo <description>```

Replace `<description>` with the details of your todo task.

### Event
To add an event task with a specified date range, use the following command:
``` event <description> /from <date> /to <date>```

Replace `<description>` with the event details and `<date>` with the start and end dates.

### Deadline
To add a deadline task with a specified due date, use the following command:
```deadline <description> /by <date>```

Replace `<description>` with the task details and `<date>` with the due date.

## Listing Tasks
To view the list of tasks, simply use the command:
```list```


## Managing Tasks

### Marking a Task as Done
To mark a task as done, use the following command:
```mark <index>```

Replace `<index>` with the index of the task in the list.

### Unmarking a Task
To unmark a task, use the following command:
```unmark <index>```

### Deleting a Task
To remove a task from the list, use the following command:
```delete <index>```

Replace `<index>` with the index of the task in the list.

## Searching for Tasks
To find a task in the list using a keyword, use the following command:

```find <keyword>```

Replace `<keyword>` with the search term.

## Undoing the Latest Action
To undo the most recent action, use the command:
```undo```


## Additional Features

### Saving Tasks
Tasks are automatically saved in a file named `duke.txt` in the current directory.

Make sure to check the file for your tasks or to transfer them to another device.

---

