# User Guide
---
## Features 

### Adding tasks

Three types of tasks can be added to the list:
- ToDo
- Deadline
- Event

### Viewing list of task

Task list can be displayed.

### Task deletion

Specified task can deleted

### Marking/Unmarking of tasks

Specified tasks can be marked/unmarked as done/undone.

### Find tasks with specific _word_ or _phrase_

Tasks containing specific words or phrases can be listed out.

### Task tagging

Tasks can be tagged to be grouped under a certain category.

### Clearing of tasklist

List of task can be cleared in a single command.

---

## Usage

### `todo [TASK]` - Adding todo task.

- `todo` keyword to create todo task.
- `[TASK]` is task to be done.
- `[TASK]` can be word/phrase but cannot be empty.

Example of usage: 

`todo increment ip`

Expected outcome:

Task *increment ip* will be added to the list.

```
<img width="598" alt="image" src="https://user-images.githubusercontent.com/65648138/219869153-851fa751-4765-4d37-b763-1951be8ed441.png">
```

### `deadline [TASK] by [DATETIME]` - Adding deadline task.

- `deadline` keyword to create deadline task.
- `[TASK]` is task to be done.
- `[TASK]` can be word/phrase but cannot be empty.
- `[DATETIME]` is the deadline of the task.
- `[DATETIME]` must be in format *_yyyy/MM/dd HHmm_*.

Example of usage: 

`deadline increment ip by 2023/02/15 1800`

Expected outcome:

Task *increment ip* with deadline *2023/02/15 1800* will be added to the list.

```
<img width="597" alt="image" src="https://user-images.githubusercontent.com/65648138/219869171-a56904f0-9b56-4f8c-8e4c-b5060fe7f5a1.png">
```

### `event [TASK] from [DATETIME] to [DATETIME]` - Adding event task.

- `event` keyword to create event task.
- `[TASK]` is task to be done.
- `[TASK]` can be word/phrase but cannot be empty.
- First `[DATETIME]` argument is the start of the event.
- Second `[DATETIME]` argument is the end of the event. 
- `[DATETIME]` must be in format *_yyyy/MM/dd HHmm_*.

Example of usage: 

`event increment ip from 2023/02/10 1800 to 2023/02/15 1800`

Expected outcome:

Task *increment ip* from *2023/02/10 1800* to *2023/02/15 1800* will be added to the list.

```
<img width="599" alt="image" src="https://user-images.githubusercontent.com/65648138/219869193-062bdf83-262e-45db-8f64-529afd3d8284.png">
```

`list` - Shows current list of tasks.

Example of usage:
