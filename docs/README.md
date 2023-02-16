# The Karen User Guide

## 👋 Introduction
The Karen is a chatbot to help keep track of your tasks. Be careful though, she is not the most polite woman out there.

## 👩 Features

### Add a Todo task

Adds a simple todo task with a description.

**Example of usage**:

`todo buy dinner`

**Expected outcome**:

```
You better finish this soon:
[T][ ] buy dinner
Can you finish all <number of existing tasks> tasks in your list?
```


### Add a Deadline task

Adds a deadline task with a description and deadline.

**Example of usage**:

`deadline do CS2103T quiz /by friday 2023-02-17 14:00`

**Expected outcome**:

```
You better finish this soon:
[D][ ] do CS2103T quiz (by: Feb 17 2023 2:00PM)
Can you finish all <number of existing tasks> tasks in your list?
```


### Add an Event task

Adds an event task with a description, start date and end date.

**Example of usage**:

`event pool party /from 2023-02-14 14:00 /to 2023-02-14 20:00`

**Expected outcome**:

```
You better finish this soon:
[E][ ] pool party (from: Feb 14 2023 2:00PM to: Feb 14 2023 8:00PM)
Can you finish all <number of existing tasks> tasks in your list?
```


### Mark a task

Marks a task as completed.

**Example of usage**:

`mark 1`

**Expected outcome**:

```
Congrats, I guess you get a medal?
[T][X] buy dinner
```

### Unmark a task

Marks a previously completed task as not done.

**Example of usage**:

`unmark 1`

**Expected outcome**:

```
Why are you so lazy?
[T][ ] buy dinner
```

### Delete a task

Removes a task from the list.

**Example of usage**:

`delete 1`

**Expected outcome**:

```
Okay okay, this has been removed:
[T][ ] buy dinner
Now you have <number of existing tasks> tasks left.
```

### Delete tasks

Removes all tasks from the list which contain the specified keyword.

**Example of usage**:

`deleteAll party`

**Expected outcome**:

```
Okay fine, I have removed these tasks which contain party:
[E][ ] pool party (from: Feb 14 2023 2:00PM to: Feb 14 2023 8:00PM)
Now you have <number of existing tasks> tasks left.
```


### List tasks

Lists out all existing tasks in the list.

**Example of usage**:

`list`

**Expected outcome**:

```
1. [D][ ] do CS2103T quiz (by: Feb 17 2023 2:00PM)
```

### Find tasks

Lists out all tasks which contain the specified keyword.

**Example of usage**:

`find CS2103T`

**Expected outcome**:

```
Here you go, do you need anything else?
1. [D][ ] do CS2103T quiz (by: Feb 17 2023 2:00PM)
```

### Quit program

Quits The Karen program.

**Example of usage**:

`bye`