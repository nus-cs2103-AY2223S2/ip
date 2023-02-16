# Squirtle(daDuke)'s User Guide
Ever felt like there's way too many things your have to keep track of?
To the point it's too much for your brain capacity that you end up 
forgetting to do something way too often!
No worries as Squirtle is here to help you! 

![This is an image](/squirtle.png)


## Features 

### Add Task
There are 3 types are task you can ask Squirtle to help you remember!

**1. ToDo task**

Query: `todo {task description}` 

E.g:
```
todo Call Grandma
```

Expected Outcome:
```
OK! I've added:
[T][ ] Call Grandma
Now you have 1 task(s) in the list.
```

**2. Deadline task**

Query: `deadline {task description} /by {YYYY-MM-DD HHMM}`

E.g:
```
deadline CS2103 iP /by 2023-02-17 2359
```

Expected Outcome:

```
OK! I've added:
[D][ ] CS2103 iP [by Feb 17 2023 23:59]
Now you have 2 task(s) in the list.
```

**3. Event task**

Query: `deadline {task description} /from {YYYY-MM-DD HHMM} /to {YYYY-MM-DD HHMM}`

E.g:
```
event Family Vacation /from 2023-06-01 0900 /to 2023-06-08 1700
```

Expected Outcome:

```
OK! I've added:
[E][ ] Family Vacation [from: Jun 01 2023 09:00 to: Jun 08 2023 17:00]
Now you have 3 task(s) in the list.
```
### List All Tasks
Want to know everything you have to do at a glance? Just tell Squirtle to list them all!

Query:
`List`

Expected Outcome: List of all the tasks you asked Squirtle to memorise
```
Here are the tasks in your list:
1. [T][ ] Call Grandma
2. [D][ ] CS2103 iP [by Feb 17 2023 23:59]
3. [E][ ] Family Vacation [from: Jun 01 2023 09:00 to: Jun 08 2023 17:00]

```


### Delete Task
You can tell Squirtle to forget a certain task that you no longer need!

Query: `delete {task index}`

E.g:
```
delete 2
```

Expected Outcome:

```
Noted. I've removed this task: 
[D][ ] CS2103 iP [by Feb 17 2023 23:59]
Now you have 2 tasks in the list.
```

### Mark Task As Done
Once you've found the motivation to complete a task, you can boast about it to Squirtle 
who would mark the task as done to acknowledge your effort!

Query: `mark {task index}`

E.g:
```
mark 2
```

Expected Outcome:

```
Nice! I've marked this task as done:
[D][X] CS2103 iP [by Feb 17 2023 23:59]
```

### Unmark Task
Realised you messed up a marked task? No worries, Squirtle understands and can
unmark the task so that you will be reminded that it is still a work in progress!

Query: `unmark {task index}`

E.g:
```
unmark 2
```

Expected Outcome:

```
OK, I've marked this task as not done yet:
[D][ ] CS2103 iP [by Feb 17 2023 23:59]
```
### Find Task(s)
Looking for a certain task? Tell Squirtle the keyword and he will filter all the relevant tasks for you!

Query: `find {keyword}`

E.g:
```
find Vacation
```

Expected Outcome:

```
Here are the matching tasks in your list:
1. [E][X] Family Vacation [from: Jun 01 2023 09:00 to: Jun 08 2023 17:00]
```

### Sort Task(s)
Deadlines all over the place? Don't know what to do first? 
Just tell Squirtle to sort them!

Query:

`Sort` 

Expected outcome:
```
Task list has been sorted!
```

Here's sneak peak at how your convo with Squirtle would be like!

![This is an image](/Ui.png)