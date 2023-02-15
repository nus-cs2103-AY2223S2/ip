# Duke the Task Management Chat Bot

This is a task management application built under Java framework. It's named after the Java mascot _Duke_. You can use this Duke application to monitor to-do tasks, tasks that require completion before a deadline, or that span over a certain period. 

Given below are the instructions to use it.

## Quick Start

1. Ensure you have Java `11` or above installed in your Computer.
2. Download the latest `individual.jar`.
3. Copy the file to the folder which you want to use as the *home folder* for your Duke application.
4. Open a command terminal, `cd` into the folder you put the jar file in, and use the `java -jar individual.jar` command to run the application.
A GUI similar to the below should appear. Note that when the app runs for the first time, it contains no sample data.

![StartImage](https://user-images.githubusercontent.com/97591125/219017325-ea973d80-1003-4cc0-9c6e-11a15d36e775.png)

5. Type the command in the command box and press `Send` button or Enter on keyboard to execute.

Here are all the commands:
* `todo [content]`: add a todo task to the task list. Example: `todo play football`
* `deadline [content] /by [date in dd/mm/yyyy format]`: add a deadline task to the task list, that need to be completed by `date`. Example: `deadline assignment 02/05/2023`
* `event [content] /from [date1 in dd/mm/yyyy format] /to [date2 in dd/mm/yyyy format]`: add an event task to the task list, that starts from `date1` and ends at `date2`. Example: `event project /from 02/03/2023 /to 05/03/2023`
* `list`: List out all the tasks in list and their indexes. Tasks are indexed based on their sequences added to the list. 
* `mark [task_index]`: Mark the task with `index` as completed. Example: `mark 2`
* `unmark [task_index]: Mark the task with `index` as not completed. Example: `unmark 3`
* `delete [task_index]`: Delete the task with `index` from the task list. Example: `delete 7`
* `find [key_word]`: Find all the tasks that have `key_word` in their descriptions. Example: `find project`
* `search [date]`: Find all the tasks that have deadline at the `date` or span over the `date` during the event. Example: `search 03/03/2023`
* `update [task_index] [content] </by [date] (for deadline task only)> </from [date] (for event task only)> </to [date] (for event task only)>: Update a specific task in the task list at `index`. Examples:
   * `update 3 play basketball` (for todo task)
   * `update 5 homework` or `update 5 homework /by 02/03/2023` (for deadline task)
   * `update 7 AI competition` or `update 7 AI competition /from 01/02/2023` or `update 7 AI competition /to 02/04/2023` or `update 7 AI competition /from 01/02/2023 /to 02/04/2023` (for event task)

