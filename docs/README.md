# PurrfectPlanner

PurrfectPlanner is a desktop application for managing your tasks. While it has a GUI, most of the user interactions happen using a CLI (Command Line Interface).

![](Ui.png)


## Commands
1. todo
2. deadline
3. event
4. delete
5. list
6. mark
7. unmark
8. find
9. sort

### 1. Adding a todo task: `todo`
`todo <task description>`

example: `todo study`

### 2. Adding a deadline task: `deadline`
`deadline <task description> /by <deadline>`

example: `deadline study /by 2023-02-17`

### 3. Adding a event task: `event`
`event <task description> /from <start time> /to <end time>`

example: `event study /from 2023-02-17 /to 2023-02-18`

### 4. Delete a task: `delete`
`delete <task index>`

example: `delete 0`

### 5. Listing all tasks: `list`
`list`

example: `list`

### 6. Mark a task as done: `mark`
`mark <task index>`

example: `mark 1`

### 7. Mark a task as not done: `unmark`
`unmark <task index>`

example: `unmark 1`

### 8. Find tasks that match the given keywords: `find`
`find <keywords>`

example: `find study read play`

### 9. Sort tasks: `sort`
`sort`

example: `sort`