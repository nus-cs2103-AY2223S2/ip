# User Guide

## Features

### ADD

Add tasks to the list. Task types consist of To-do, deadline and event.

### DELETE

Delete tasks from the list corresponding to its index.

### FIND

Find tasks that contain the input word in them.

### UPDATE

Update the task of the same task type.

### LIST

Display a column of all the tasks in the list.

### MARK

Mark tasks from the list corresponding to its index.

### UN-MARK

Un-mark tasks from the list corresponding to its index.

### BYE

Exit and terminate the program.

## Usage

### `Add`

*Add item of your choice to the list by following the format given*.

Example of usage:

**todo**: `todo [task name]`

**deadline**: `deadline [task name] /by [date: YYYY/MM/DD] [time]`

**event**: `event [task name] /from [date: YYYY/MM/DD] [time] /to [date: YYYY/MM/DD] [time]`

Below shows how each tasks outcome look like:

```
todo: [T][] bring books

deadline: [D][] bring books(by: 9 AUGUST 2022, 8pm )

event: [E][] bring books(from: 9 AUGUST 2022, 8pm to: 9 AUGUST 2023, 8pm )
```

---

### `Delete`

*Delete task from the list using the sequence numbering on the list*

Example of usage:

**delete**: `delete [index]`

Before:

```
Take a look at ye dream GOALS for 2023:
     1. [T][] bring books
     2. [D][] bring books(by: 9 AUGUST 2022, 8pm )
     3. [E][] bring books(from: 9 AUGUST 2022, 8pm to: 9 AUGUST 2023, 8pm )
```

Expected outcome after entering `delete 1`:

```
Take a look at ye dream GOALS for 2023:
     1. [D][] bring books(by: 9 AUGUST 2022, 8pm )
     2. [E][] bring books(from: 9 AUGUST 2022, 8pm to: 9 AUGUST 2023, 8pm )
```

---

### `Find`

*Find tasks that contain similar word from the list*

Example of usage:

**find**: `find [word]`

Expected outcome after entering `find buy`:

```
Take a look at ye dream GOALS for 2023:
     1. [T][X] buy books
     2. [D][] buy dinner(by: 9 AUGUST 2022, 8pm )
```

---

### `Update`

*Update task from the list by inserting its index followed by the name and time periods that the user wishes to change*

Example of usage:

**todo**: `update [index] [task name]`

**deadline**: `update [index] [task name] /by [date: YYYY/MM/DD] [time]`

**event**: `update [index] [task name] /from [date: YYYY/MM/DD] [time] /to [date: YYYY/MM/DD] [time]`

Before:

```
Take a look at ye dream GOALS for 2023:
     1. [T][] bring books
     2. [D][] bring books(by: 9 AUGUST 2022, 8pm )
     3. [E][] bring books(from: 9 AUGUST 2022, 8pm to: 9 AUGUST 2023, 8pm )
```

Expected outcome after entering `update 1 meet friends`:

```
Take a look at ye dream GOALS for 2023:
     1. [T][] meet friends
     2. [D][] bring books(by: 9 AUGUST 2022, 8pm )
     3. [E][] bring books(from: 9 AUGUST 2022, 8pm to: 9 AUGUST 2023, 8pm )
```

---

### `List`

*Display all the tasks from the list*

Example of usage:

**list**: `list`

Expected outcome:

```
Take a look at ye dream GOALS for 2023:
     1. [T][] bring books
     2. [D][] bring books(by: 9 AUGUST 2022, 8pm )
     3. [E][] bring books(from: 9 AUGUST 2022, 8pm to: 9 AUGUST 2023, 8pm )
```

---

### `Mark`

*Mark tasks from the list based on its index*

Example of usage:

**mark**: `mark [index]`

Expected outcome after entering `mark 1`:

```
Take a look at ye dream GOALS for 2023:
     1. [T][X] bring books
     2. [D][] bring books(by: 9 AUGUST 2022, 8pm )
     3. [E][] bring books(from: 9 AUGUST 2022, 8pm to: 9 AUGUST 2023, 8pm )
```

---

### `Unmark`

*Un-mark tasks from the list based on its index*

Example of usage:

**unmark**: `unmark [index]`

Expected outcome after entering `unmark 1`:

```
Take a look at ye dream GOALS for 2023:
     1. [T][] bring books
     2. [D][] bring books(by: 9 AUGUST 2022, 8pm )
     3. [E][] bring books(from: 9 AUGUST 2022, 8pm to: 9 AUGUST 2023, 8pm )
```

---

### `Bye`

*Terminate the program*

Example of usage:

**bye**: `bye`

---
