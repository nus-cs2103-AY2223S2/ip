# 📖 Guide to Berry the Task Manager Bot

## 🍒Features🍒

### 1. Quick Task Management

<p> Use Berry just like a Command Line Interface (CLI)! </p>
<p> This means that if you are a quick typer, Berry makes your task managament even faster! ⚡️ </p>

### 2. Interactive ChatBot

<p> Finished your task and want to finally delete it or mark it as done?</p>

<p> Berry feels happy for you~ See how her apperance changes when you are being productive!</p>

## 🍒Usage🍒

### `todo <task_name>` - adds a task to do 

This adds a new task for berry to manage.

Example of usage: 

`todo get dinner for family`

Expected outcome:


```
You can do it! I've added this task for ya
    [T][ ] get dinner for family
```

---

### `deadline <task_name> /by<YYYY-MM-DD>` - adds a task with a deadline

This adds a new task with a deadline for berry to manage.

Example of usage:

`deadline finish assignment /by 2023-12-30`

Expected outcome:


```
You can do it! I've added this task for ya
    [D][ ] finish assignment (by: Dec 30 2023)
```

---

### `event <task_name> /from<YYYY-MM-DD> /to<YYYY-MM-DD>` - adds an event

This adds a new task as an event between a time period for berry to manage.

Example of usage:

`event flea market popup /from 2023-02-19 /to 2023-03-19`

Expected outcome:


```
You can do it! I've added this task for ya
    [E][ ] flea market popup (from: Feb 19 2023 to: Mar 19 2023)
```

---

### `list` - show all tasks

This asks berry to show all tasks that is being managed at the moment.

Example of usage:

`list`

Expected outcome:


```
Here's what I have for you:
    1. [T][ ] get dinner for family
    2. [D][ ] finish assignment (by: Dec 30 2023)
    3. [E][ ] flea market popup (from: Feb 19 2023 to: Mar 19 2023) 
```

---
### `mark <list_index>` - mark a task with the list index as done

This asks berry to mark the task indexed by the _list index_, that is seen in your most recent call to `list`, as done.

Example of usage:

`mark 1`

Expected outcome:


```
Alright~ I'll set the task as done!
    [T][X] get dinner for family
```

---
### `unmark <list_index>` - mark a task with the list index as not done

This asks berry to mark the task indexed by the _list index_, that is seen in your most recent call to `list`, as not done.

Example of usage:

`unmark 1`

Expected outcome:


```
Okay! I'll set the task as not done
    [T][ ] get dinner for family
```

---

---
### `find <keyword>` - searches for tasks which contain the keyword

This asks berry to search for tasks which contains the keyword given.

Example of usage:

`find dinner`

Expected outcome:


```
Here's what I found for ya
    1. [T][ ] get dinner for family
```

---

---
### `bye` - exits the program

Berry will say its goodbyes and wait for your return!

Example of usage:

`bye`

Expected outcome:


```
Bye! I hope Berry was helpful to you <:
```

---



