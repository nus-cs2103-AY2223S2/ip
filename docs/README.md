# User Guide

# Features 

## Command Syntax
| Syntax      | Meaning |
| ----------- | ----------- |
| `plain text`| Enter this literally, exactly as shown |
| `(argumentName)`| Required argument that should be replaced with an appropriate value |

## Add Deadlines
Adds a deadline with a task name and end time. <br>

Format: `deadline (taskName) /by (time)` <br>

Examples: 
<ul> 
  <li> deadline Do Stuff /by today </li>
  <li> deadline Sleep /by 18/2/2023 2359 </li>
</ul>

Notes:
<ul>
  <li> 
    (time) can be stored internally as either a String or a DateTime object. <br>
    To store (time) as a DateTime object, format it as "dd/mm/yyyy hhmm", example: 15/3/2010 1500 <br>
    (time) must be stored as a DateTime object for reminders to work.
  </li> 
</ul>

## Add Events
Adds an event with an event name, both a start time and an end time.

Format: `event (eventName) /from (startTime) /to (endTime)` <br>

Examples: 
<ul> 
  <li> event Cool Event /from today /to tomorrow </li>
</ul>

## Add ToDos
Adds a To-Do with a task name

Format: `todo (task)` <br>

Examples: 
<ul> 
  <li> todo Something </li>
</ul>

## List all tasks
Lists out all tasks, showing all relevant information about the tasks.

Format: `list` <br>

Notes:
<ul>
  <li> All items that are seen in this list will be saved to storage on program exit </li>
</ul>

## Delete task
Deletes the task with the given index.

Format: `delete (index)` <br>

Examples: 
<ul> 
  <li> delete 2 </li>
</ul>

Notes:
<ul>
  <li> Tasks are 1-indexed, meaning the first task is given index 1 </li> 
  <li> To find the index of the task you want to delete, use the list feature </li>
  <li> Index should be a positive integer</li>
</ul>

## Find task
Attempts to find all tasks with items that match the supplied keyword. Will search through all information about all tasks.

Format: `find (keyword)` <br>

Examples:
<ul> 
  <li> find homework </li>
  <li> find 18/2/2023 </li>
</ul>

## Mark task
Marks a task as done.

Format: `mark (index)` <br>

Examples: 
<ul> 
  <li> mark 2 </li>
</ul>

Notes:
<ul>
  <li> Tasks are 1-indexed, meaning the first task is given index 1 </li> 
  <li> To find the index of the task you want to mark, use the list feature </li>
  <li> Index should be a positive integer</li>
</ul>

## Unmark task
Marks a task as not done.

Format: `unmark (index)` <br>

Examples: 
<ul> 
  <li> mark 2 </li>
</ul>

Notes:
<ul>
  <li> Tasks are 1-indexed, meaning the first task is given index 1 </li> 
  <li> To find the index of the task you want to mark, use the list feature </li>
  <li> Index should be a positive integer</li>
</ul>

## Exit Program
Exits the program gracefully.

Format: `bye` <br>

Notes:
<ul>
  <li> Please exit the program using the given command, or your tasks may not be saved properly</li>
</ul>
