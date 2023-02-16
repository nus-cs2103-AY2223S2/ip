# JenoBot User Guide

#### JenoBot is a personal Command-Line-Interface desktop application that helps you to keep track of your daily tasks.

![](Ui.png)

## Content
1. [Command Summary](https://afiqzu.github.io/ip/#command-summary)
2. [Features](https://afiqzu.github.io/ip/#features)

## Command Summary

1. [todo](https://afiqzu.github.io/ip/#1-adding-a-todo-task)
2. [deadline](https://afiqzu.github.io/ip/#2-adding-a-deadline-task)
3. [event](https://afiqzu.github.io/ip/#3-adding-an-event-task)
4. [list](https://afiqzu.github.io/ip/#4-list-all-tasks)
5. [delete](https://afiqzu.github.io/ip/#5-delete-task)
6. [mark](https://afiqzu.github.io/ip/#6-mark-task-as-done)
7. [unmark](https://afiqzu.github.io/ip/#7-unmark-task)
8. [clear](https://afiqzu.github.io/ip/#8-clear-all-tasks)
9. [note](https://afiqzu.github.io/ip/#9-add-a-new-note)
10. [opennotes](https://afiqzu.github.io/ip/#10-open-current-notes-with-opennotes)
11. [clearnotes](https://afiqzu.github.io/ip/#11-clear-current-notes-with-clearnotes)
12. [help](https://afiqzu.github.io/ip/#12-open-command-list-with-help)


## Features

### 1. Adding a `todo` task
#### `todo <todo name>`
Examples:
1. `todo read book`
2. `todo buy milk`

-------------------
### 2. Adding a `deadline` task
#### `deadline <deadline name> /by <deadline>`
Examples:
1. `deadline return book /by 17/02/2023 15:00`
2. `deadline CS2105 assignment 1 /by 18/02/2023 23:59`

Note that time has to be in DD/MM/YYYY HH:mm format.

-------------------
### 3. Adding an `event` task
#### `event <event name> /from <start time> /to <end time>`
Examples:
1. `event CS2103T Lecture /from 17/02/2023 12:00 /to 17/02/2023 14:00`
2. `event soccer practice /from 18/02/2023 08:00 /to 18/02/2023 10:00`

Note that time has to be in DD/MM/YYYY HH:mm format.

-------------------
### 4. `list` all tasks
#### `list`
Tasks will be loaded from task log file.

-------------------
### 5. `delete` task
#### `delete <task index>`
Example:
`delete 2`

Task corresponding to its task index in task list will be deleted.
Warning: this command is irreversible.

-------------------
### 6. `mark` task as done
#### `mark <task index>`
Example:
`mark 3`

Task corresponding to its task index in task list will be marked as done.

-------------------
### 7. `unmark` task
#### `unmark <task index>`
Example:
`unmark 3`

Task corresponding to its task index in task list will be unmarked.

-------------------
### 8. `clear` all tasks
#### `clear`

Clears all active tasks from task list.

-------------------
### 9. Add a new `note`
#### `note <note>`
Example:
1. `note Jack wants a watch as a birthday gift`
2. `note My waist size is 30`

-------------------
### 10. Open current notes with `opennotes`
#### `opennotes`

-------------------
### 11. Clear current notes with `clearnotes`
#### `clearnotes`

Warning: this command is irreversible.

-------------------
### 12. Open command list with `help`
#### `help`


