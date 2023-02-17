# Duke the Task Management Chat Bot

This is a task management application built under Java framework. It's named after the Java mascot _Duke_. You can use this Duke application to monitor to-do tasks, tasks that require completion before a deadline, or that span over a certain period. 

Given below are the instructions to use it.

## Quick Start

1. Ensure you have Java `11` or above installed in your Computer.
2. Download the latest `duke.jar`.
3. Copy the file to the folder which you want to use as the *home folder* for your Duke application.
4. Open a command terminal, `cd` into the folder you put the jar file in, and use the `java -jar duke.jar` command to run the application.
A GUI similar to the below should appear. Note that when the app runs for the first time, it contains no sample data.

![StartImage](https://user-images.githubusercontent.com/97591125/219017325-ea973d80-1003-4cc0-9c6e-11a15d36e775.png)

5. Type the command in the command box and press `Send` button or Enter on keyboard to execute.

## Features

Here are all of the features:

### Add Task

Add new tasks to the task list. 
When a task is added, it is automatically given an index, based on its sequence in the task list.
Arguments inside `[]` should be replaced with the wanted contents (without the `[]` itself)

*The 3 types of tasks that can be added:* 
* `todo`: tasks to be done at future
* `deadline`: tasks with a specific deadline
* `event`: tasks that continue for a certain period

*The available commands:*
* `todo [content]`: add a todo task to the task list. Example: `todo play football`
* `deadline [content] /by [date in dd/mm/yyyy format]`: add a deadline task to the task list, that need to be completed by `date`. Example: `deadline assignment 02/05/2023`
* `event [content] /from [date1 in dd/mm/yyyy format] /to [date2 in dd/mm/yyyy format]`: add an event task to the task list, that starts from `date1` and ends at `date2`. Example: `event project /from 02/03/2023 /to 05/03/2023`

### Mark/Unmark Task Completion

Mark a specific task to be completed or uncompleted, based on task index.

*The available commands:*
* `mark [task_index]`: Mark the task with `index` as completed. Example: `mark 2`
* `unmark [task_index]: Mark the task with `index` as not completed. Example: `unmark 3`

### View Tasks

View all, or a proportion of the tasks, based on keywords or dates.

*The filter criteria:*
* `list`: View all the tasks in list.
* `find`: View tasks based on specific keywords.
* `search`: View tasks based on specific dates.

*The available commands:*
* `list`: List out all the tasks in list and their indexes. Tasks are indexed based on their sequences added to the list. 
* `find [key_word]`: Find all the tasks that have `key_word` in their descriptions. Example: `find project`
* `search [date]`: Find all the tasks that have deadline at the `date` or span over the `date` during the event. Example: `search 03/03/2023`

### Delete Tasks

Delete a specific task, based on task index.

*The available commands:*
* `delete [task_index]`: Delete the task with `index` from the task list. Example: `delete 7`

### Update Tasks

Update a specific task, based on task index. 
For each update, the content of the task must be updated, while update of date is optional. Content inside `<>` are optional.

*The avaialble commands:*
* `update [task_index] [content] </by [date] (for deadline task only)> </from [date] (for event task only)> </to [date] (for event task only)>: Update a specific task in the task list at `index`. Examples:
   * `update 3 play basketball` (for todo task)
   * `update 5 homework` or `update 5 homework /by 02/03/2023` (for deadline task)
   * `update 7 AI competition` or `update 7 AI competition /from 01/02/2023` or `update 7 AI competition /to 02/04/2023` or `update 7 AI competition /from 01/02/2023 /to 02/04/2023` (for event task)

