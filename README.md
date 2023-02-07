# Duke

Duke is a chatbot software that helps you manage your tasks.

## Features
### Viewing Guide: guide
Format: `guide`
Shows a guide for commands
### Listing all the tasks: list
Format: `list`
Shows the list of tasks added
### Adding a Todo Task: todo
Format: `todo (content)`
Adds the content as a Todo Task
### Adding a Deadline Task: deadline
Format: `deadline (content) /by (dd/MM/yyyy HH:mm)`
Adds the content as a Deadline Task that should be done by the date
### Adding an Event Task: event
Format: `event (content) /from (dd/MM/yyyy HH:mm) /by (dd/MM/yyyy HH:mm)`
Adds the content as an Event Task that is going on between the dates
### Marking a Task as done: mark
Format: `mark (index)`
Mark the task with the given index as done
### Marking a Task as undone: unmark
Format: `unmark (index)`
Mark the task with the given index as undone
### Deleting a Task: delete
Format: `delete (index)`
Delete the task with the given index
### Closing Duke: bye
Format: `bye`
Closes Duke
