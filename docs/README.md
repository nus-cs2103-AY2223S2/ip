# User Guide

## Features

### Adding Different Task Types
This app allows you add 3 different task types: a Todo item, a Deadline item and an Event item. 
Supporting marking, searching and more, this app has got you covered in managing all the tasks
you'll ever have!

### Tagging
In addition to simply creating tasks, you can also tag tasks as well! Simply by using the `/tag` flag 
when creating your tasks, you can add tags to make your tasks more organised and neat. You can search 
for your tags easily through the `find` command as well. 

## Usage

### `list` - List all tasks

This command lists all the tasks currently in the task list.

Command format:

```
list
```

### `mark` - Mark a task as complete

This command marks a given task as complete. The task ID corresponds to the number listed in the `list` command.

Command format:

```
mark TASK_ID
```

### `unmark` - Unmark a task as complete

This command unmarks a given task as complete. The task ID corresponds to the number listed in the `list` command.

Command format:

```
unmark TASK_ID
```

### `find` - Find tasks by keyword

This command finds a list of tasks corresponding to a given keyword.

The keyword could represent any part of the task. For example, if I want to find all tasks with the "High Priority" tag, I could run `find High` or `find High Priority`.

Command format:

```
find KEYWORD
```

### `delete` - Delete a task

This command deletes a task from the task list. The task ID corresponds to the number listed in the `list` command.

Command format:

```
delete TASK_ID
```

### `todo` - Adds a new Todo item

This command allows you to add a new "Todo" item into the task list. A "Todo" item must have a non-empty description, and can have optional tags.

Command format:

```
todo DESCRIPTION [/tag TAGS]
```

Example usage:

```
todo Sweep the floor /tag Low Priority, Fast
```

### `deadline` - Adds a new Deadline item

This command allows you to add a new "Deadline" item into the task list. A "Deadline" item must have the following:

- a non-empty description
- a "by" date. This can be inputed as a arbitrary string, or a string of the following format: `day-month-year [HHmm]`. For example: `3-16-2002 1400` or `10-10-1995`
  Optional tags are supported as well.

Command format:

```
deadline DESCRIPTION /by BY_DATE [/tag TAGS]
```

Example usage:

```
deadline Project Assignment /by 19-2-2023 /tag High Priority, Deadline Soon
```

### `event` - Adds a new Event item

This command allows you to add a new "Event" item into the task list.

An "Event" item must have the following:

- a non-empty description
- a "start" date. This can be inputed as a arbitrary string, or a string of the following format: `day-month-year [HHmm]`. For example: `3-16-2002 1400` or `10-10-1995`
- an "end" date. This can be inputed as a arbitrary string, or a string of the following format: `day-month-year [HHmm]`. For example: `3-16-2002 1400` or `10-10-1995`

Optional tags are supported as well.

Command format:

```
event DESCRIPTION /from FROM_DATE /to TO_DATE [/tag TAGS]
```

Example usage:

```
event Trip to Universal Studios /from 14-4-2023 1400 /to 15-4-2023 1000 /tag Family, Vacation
```

### `bye` - Exit the application

This command closes the application window.

Command format:

```
bye
```
