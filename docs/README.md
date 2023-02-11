# User Guide - C4PO Pro (Student Edition)

![C4PO](https://static.thenounproject.com/png/1454734-200.png)

> "For a student, you seem to do an incessant amount of thinking."
>
>   -- C4PO, 'NUS Wars: Episode II- Attack Of The Students'.

C4PO frees your mind from having to keep track of your tasks to do.
C4PO is:
- text-based
- easy to learn
- ~~FAST~~ EXTREMELY FAST to use

All you need to do is,
1) download it from [here](https://github.com/ezeAng/ip).
2) double-click it.
3) add your tasks.
4) let it manage your tasks for you 😉

And it is **FREE**!


## Features 

### Tracking Tasks

## Usage

### Adding Tasks
Tasks can be **added** to C4PO's memory, to allow yourself to keep track of your tasks.
There are a few types of tasks to add, namely:

- Todo `todo {taskname} /priority {int}`
- Deadline `deadline {taskname} /by {yyyy-MM-dd} /priority {int}`
- Event `event {taskname} /from {start} /to {end} /priority {int}`

### Marking or Un-marking Tasks
Tasks can be marked as done with the `mark` command, followed by the index of the task (based on task-list) separated by a space. 
For example `mark 1` will result in the first task in the list to be marked as done `[X] FirstTask`


### Sorting by priority

Users can sort their tasks by priority using the `priority` command.
An example given this list:
#### Current List:
```
1. [T][ ] Clean Room (priority: 2)
2. [E][X] Dinner with friends (priority: 6) (from:
5pm to: 7pm)
3. [D][ ] First Essay Draft (priority: 10) (by: Friday Feb 11 2022)

```

#### Output:
```
Sorted by priority:
Master, here are the items in your list:
1. [D][ ] First Essay Draft (priority: 10) (by:
Fridav Feb 11 2022 )
2. [E][X] Dinner with friends (priority: 6) (from:
5pm to: 7pm)
3. [T][X] Clean Room (priority: 2)
```








