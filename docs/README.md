# User Guide

Ever unsure of what to do? __Watt Toodu__ is here to help!
Be polite though, he might show a little attitude...

## Task tracking

Keep track of the tasks you have:

- Completion status
- Type of task (todo, deadline, event)
- Dates (YYYY-MM-DD format)

### List your tasks

Enter `list` to show your recorded tasks, and their respective types, statuses, descriptions and dates.

```
Here! Now leave me alone...
1. [D] [ ] This is an incomplete deadline task to be done (by: Feb 27 2023)
```

### Add a task

Use these commands to add either a _todo_, _deadline_ or _event_ task to your list.

```
todo task1

Clearly, having good memory isn't one of your strengths...
[T] [ ] task1
```

```
deadline task2 /by 2023-02-14

Clearly, having good memory isn't one of your strengths...
[D] [ ] task2 (by: Feb 2 2023)
```

```
event task3 /from 2023-02-15 /to 2023-03-12

Clearly, having good memory isn't one of your strengths...
[E] [ ] task3 (from: Feb 15 2023 to: Mar 12 2023)
```

### Remove a task

Remove a task by referencing its number.

```
remove 2

Please clean up after yourself:
[D] [ ] task2 (by: Feb 2 2023)
Now you have 2 tasks in the list.

```

## Task marking

Mark tasks as either done or not done.
### Set task as done

Mark a task as done by referencing its number.

```
mark 1

Wow, reaaal impressive work.
[T] [X] task1
```

### Set task as not done

Unmark a task as not done by referencing its number.

```
unmark 1

So did you complete it or not?!?
[T] [ ] task1
```

## Task finding

Search for tasks using a keyword from the description.

```
find task1

Needle in a haystack...
[T] [ ] task1
```

## Command syntax

Simplify commands using only the first letter of the word or setting your own aliases

```
t task4

Clearly, having good memory isn't one of your strengths...
[T] [ ] task4
```

```
set todo create

Po-tay-toes, po-ta-toes, "create" is now the same as "todo".

create task5

Clearly, having good memory isn't one of your strengths...
[T] [ ] task4
```

## Save and exit

Enter `bye` to save your tasks and close the application.
