# User Guide for Duke by Bo kuan
> Simple task tracker

##Getting started
1. Download this repository.
2. Open your command prompt
3. Navigate to the directory containing the jar file eg. `cd <path to clone>/build/libs`
4. run the command `java -jar duke.jar`
## Features 

- [x] Add, delete, list and mark tasks - 
Track your `todo`, `deadline` and `event` tasks

- [x]  Find tasks - 
Find tasks using keywords from your task list

- [x]  Reminders -
Automatic reminders for `event` and `deadline` tasks

- [x] Storage - Remembers previous tasks after closing the program
## Usage
- Add tasks
  - `todo <TASK_NAME>` - create a `todo` task
  - `deadline <TASK_NAME> /by dd/mm/yyyy HHMM` - create a `deadline` task
  - `event <TASK_NAME> /from dd/mm/yyyy HHMM /to dd/mm/yyyy HHMM` - create an `event` task
- View tasks
  - `list` - see all tasks in task list
  - `find <KEYWORD>` see all tasks containing keyword
- Remove tasks
  - `delete <INDEX>` - delete task number index
  - `clear` - delete all tasks
- Changing task status
  - `mark <INDEX>` - marks task number index as done
  - `unmark <INDEX>` - unmarks task number index
- Exit
  - `bye` - exit program

