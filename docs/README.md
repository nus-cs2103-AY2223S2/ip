# User Guide

## Features 

### List tasks

Command: `list`
```
1. [T][ ] Eat salad
2. [T][X] Study for midterms
```

### Add a todo

Command: `todo ...`
```
Ok! I've added this todo! [T][ ] Eat salad
You now have this many tasks: 3
```

### Add a deadline

Command: `deadline ... /by dd/mm/yyyy hhmm`
```
Ok! I've added this deadline! [D][ ] Play squash (by: Dec 12 2000 12:12PM)
You now have this many tasks: 3
```

### Add an event

Command: `event ... /from dd/mm/yyyy hhmm /to dd/mm/yyyy hhmm`
```
Ok! I've added this event! [E] [ ] Brother's wedding (from: Dec 12 2000 12:12PM to: Dec 13 2000 12:33PM)
You now have this many tasks: 3
```

### Delete a task

Command: `delete ...`
```
I've removed this task as u lazily requested:
[T] [X] Eat salad
Now you have like this many tasks left: 4
```

### Mark a task as done

Command: `mark ...`
```
Niceoooo you're done wit this: 
[T] [X] Eat salad
```

### Unmark a task as undone

Command: `unmark ...`
```
Ok lah you haven't finish this yet
[T] [ ] Study for midterms
```

### Exit the program

Command: `bye`