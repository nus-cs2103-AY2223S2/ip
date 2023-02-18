# User Guide for DUKE

## Overview

Duke is a task management chatbot that allows you to keep track
of your tasks and manage them. You can ask Duke to create new tasks 
and tick them off when done. You can also edit, delete, and find your 
tasks as you wish. Both GUI and command line interface are present in DUKE

## Features 

### 1.  `todo`

Add a todo to your tasks list.
>Use GUI for faster access.
> 
> Alternatively, command line:
> 
> todo [description]  
> 
> todo read book


### 2.  `deadline`

Add a deadline to your tasks list. Specify date in YYYY-MM-DD HHMM (24HR time)
>Use GUI for faster access.
>
> Alternatively, command line:
>
> deadline [description] /by [YYYY-MM-DD HHMM]
>
> deadline Assignment /by 2023-02-10 1800

### 3.  `event`

Add an event to your tasks list. Specify start and end date in YYYY-MM-DD HHMM (24HR time)
>Use GUI for faster access.
>
> Alternatively, command line:
>
> event [description] /from [YYYY-MM-DD HHMM] /to [YYYY-MM-DD HHMM]
>
> event project meeting /from 2023-02-10 1800 /to 2023-02-11-1800
### 4.  `list`

Print out the tasks in DUKE
>Use GUI for faster access.
>
> Alternatively, command line:
> 
> list

 ### 5.  `mark`

Mark the task as done
>Use GUI for faster access.
>
> Alternatively, command line:
>
> mark [index number starting from 1]
> 
> mark 2


### 6.  `unmark`

Mark the task as not done
>Use GUI for faster access.
>
> Alternatively, command line:
>
> unmark [index number starting from 1]
>
> unmark 2


### 7.  `delete`

Deletes a specified task in DUKE
>Use GUI for faster access.
>
> Alternatively, command line:
>
> delete [index number starting from 1]
>
> delete 2

### 8.  `find`

Find a task with the same keyword
>Use GUI for faster access.
>
> Alternatively, command line:
>
> find [keyword]
>
> find read book

### 9.  `tag`

Tag a task 
>Use GUI for faster access.
>
> Alternatively, command line:
>
> tag [task index starting from 1][description]
>
> tag 1 important

### 10.  `bye`

Say goodbye to DUKE
>Command line only:
> 
> bye

