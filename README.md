# DOggi

Woof!
DOggi is a friendly chatbot dog software that helps you manage your tasks.
<sub>DOggi extends Duke</sub>

## Features

### Viewing Guide: guide
Format: `guide`<br>
Shows a guide for commands

### Listing all the tasks: list
Format: `list`<br>
Shows the list of tasks added

### Adding a Todo Task: todo
Format: `todo (content)`<br>
Adds the content as a Todo Task

### Adding a Deadline Task: deadline
Format: `deadline (content) /by (dd/MM/yyyy HH:mm)`<br>
Adds the content as a Deadline Task that should be done by the date

### Adding an Event Task: event
Format: `event (content) /from (dd/MM/yyyy HH:mm) /by (dd/MM/yyyy HH:mm)`<br>
Adds the content as an Event Task that is going on between the dates

### Marking a Task as done: mark
Format: `mark (index)`<br>
Marks the task with the given index as done


### Marking a Task as undone: unmark
Format: `unmark (index)`<br>
Marks the task with the given index as undone

### Deleting a Task: delete
Format: `delete (index)`<br>
Deletes the task with the given index

### Deleting all the tasks
Format: `delete all`<br>
Deletes all the tasks

### Closing Duke: bye
Format: `bye`<br>
Closes Duke
