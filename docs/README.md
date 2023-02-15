# User Guide

## Features 
Feature 1 - Add Task
Add a task.
The task can be: todo, event, deadline

Feature 2 - Mark/Unmark Task as Done
Feature 3 - Delete Task
Feature 4 - Find task
Find a task by a given keyword.

Feature 5 - List tasks
Usage
todo - Adds a todo task
Adds a todo task to the list of tasks.

Example of usage:

todo assignment

Expected outcome:

Got it. I've added this task:
[T][] assignment
 Now you have 3 tasks in the list

event - Adds an event task
Adds an event task to the list of tasks. Date specified must be in dd/mm/yyyy HHmm format.

Example of usage:

event lesson /from 15/09/2000 1700 /to 16/09/2001 1600

Expected outcome:

Got it. I've added this task:
[E][X] lesson at: Sep 15 2020
 Now you have 3 tasks in the list

deadline - Adds a deadline task
Adds a deadline task to the list of tasks. Date specified must be in dd/mm/yyyy HHmm format.

Example of usage:

deadline project /by 15/09/2000 1700

Expected outcome:

Got it. I've added this task:
[D][] project by 15/09/2000 1800
 Now you have 3 tasks in the list

list - Lists all tasks
Lists all tasks. Each task is given an index starting from 1.

Example of usage:

list

Expected outcome:

Here are the tasks in your list:
1. [T][] assignment
2. [E][] lesson 15/09/2000 1800 to 16/09/2000 1900
3. [D][] project 15/09/2000 1800
done - Mark task as done
Mark a task specified by the index as done. Index is obtained through list.

Example of usage:

done 1

Expected outcome:

Nice I've marked this task as done:
1. [T][X] assignment

delete - Delete a task
Deletes a task specified by the index. Index is obtained through list.

Example of usage:

delete 1

Expected outcome:

Noted. I've removed this task:
[T][] assignment
Now you have 2 tasks in the list

find - Find a task
Finds a task specified by the given keyword. The search is case insensitive.

Example of usage:

find project

Expected outcome:

Here are the matching tasks in your list:
2. [D][] project 15/09/2000 1800

help - Displays help page for commands
Displays help page for an individual command or all commands.

Example of usage:

help todo
help

Excepted outcome for help todo:
todo command
    Create a todo task.
    Format: todo <task name>
    Eg. todo homework1
    
### credits
Help command was inspired by sgn00
