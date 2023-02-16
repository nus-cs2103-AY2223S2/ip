# JenoBot User Guide

#### JenoBot is a personal Command-Line-Interface desktop application that helps you to keep track of your daily tasks.

![](docs/Ui.png)

## Content
1. [Command Summary](https://github.com/afiqzu/ip#command-summary)
2. [Features](https://github.com/afiqzu/ip#features)

## Command Summary

1. [todo](https://github.com/afiqzu/ip#1-adding-a-todo-task)
2. [deadline](https://github.com/afiqzu/ip#2-adding-a-deadline-task)
3. [event](https://github.com/afiqzu/ip#3-adding-an-event-task)
4. [list](https://github.com/afiqzu/ip#4-list-all-tasks)
5. [find]
6. [delete](https://github.com/afiqzu/ip#6-delete-task)
7. [mark](https://github.com/afiqzu/ip#7-mark-task-as-done)
8. [unmark](https://github.com/afiqzu/ip#8-unmark-task)
9. [clear](https://github.com/afiqzu/ip#9-clear-all-tasks)
10. [note](https://github.com/afiqzu/ip#10-add-a-new-note)
11. [opennotes](https://github.com/afiqzu/ip#11-open-current-notes-with-opennotes)
12. [clearnotes](https://github.com/afiqzu/ip#12-clear-current-notes-with-clearnotes)
13. [help](https://github.com/afiqzu/ip#13-open-command-list-with-help)


## Features

### 1. Adding a `todo` task
#### `todo <todo name>`
Examples:
1. `todo read book`
2. `todo buy milk`

[Go back to command summary](https://github.com/afiqzu/ip#command-summary)

-------------------
### 2. Adding a `deadline` task
#### `deadline <deadline name> /by <deadline>`
Examples:
1. `deadline return book /by 17/02/2023 15:00`
2. `deadline CS2105 assignment 1 /by 18/02/2023 23:59`

Note that time has to be in DD/MM/YYYY HH:mm format.

[Go back to command summary](https://github.com/afiqzu/ip#command-summary)

-------------------
### 3. Adding an `event` task
#### `event <event name> /from <start time> /to <end time>`
Examples:
1. `event CS2103T Lecture /from 17/02/2023 12:00 /to 17/02/2023 14:00`
2. `event soccer practice /from 18/02/2023 08:00 /to 18/02/2023 10:00`

Note that time has to be in DD/MM/YYYY HH:mm format.

[Go back to command summary](https://github.com/afiqzu/ip#command-summary)

-------------------
### 4. `list` all tasks
#### `list`
Tasks will be loaded from task log file.

[Go back to command summary](https://github.com/afiqzu/ip#command-summary)

-------------------
### 5. `find` task
#### `find <keyword>`
Display all tasks containing keyword.

Example:
1. `find book`
2. `find CS2103T`

[Go back to command summary](https://github.com/afiqzu/ip#command-summary)

-------------------
### 6. `delete` task
#### `delete <task index>`
Example:
`delete 2`

Task corresponding to its task index in task list will be deleted.
Warning: this command is irreversible.

[Go back to command summary](https://github.com/afiqzu/ip#command-summary)

-------------------
### 7. `mark` task as done
#### `mark <task index>`
Example:
`mark 3`

Task corresponding to its task index in task list will be marked as done.

[Go back to command summary](https://github.com/afiqzu/ip#command-summary)

-------------------
### 8. `unmark` task
#### `unmark <task index>`
Example:
`unmark 3`

Task corresponding to its task index in task list will be unmarked.

[Go back to command summary](https://github.com/afiqzu/ip#command-summary)

-------------------
### 9. `clear` all tasks
#### `clear`

Clears all active tasks from task list.

[Go back to command summary](https://github.com/afiqzu/ip#command-summary)

-------------------
### 10. Add a new `note`
#### `note <note>`
Example:
1. `note Jack wants a watch as a birthday gift`
2. `note My waist size is 30`

[Go back to command summary](https://github.com/afiqzu/ip#command-summary)

-------------------
### 11. Open current notes with `opennotes`
#### `opennotes`

[Go back to command summary](https://github.com/afiqzu/ip#command-summary)

-------------------
### 12. Clear current notes with `clearnotes`
#### `clearnotes`

Warning: this command is irreversible.

[Go back to command summary](https://github.com/afiqzu/ip#command-summary)

-------------------
### 13. Open command list with `help`
#### `help`

[Go back to command summary](https://github.com/afiqzu/ip#command-summary)

