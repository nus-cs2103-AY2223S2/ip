# User Guide

## Features 

### Feature-List

Words in UPPER_CASE are the parameters to be supplied by the user.

Lists all current tasks in tasklist.
Format: view


### Feature-Delete

Description of the feature.

Format: delete INDEX

Examples: - delete 1
          - delete 3

### Feature-Add Todo Task

Adds Todo Task to tasklist.

Format: todo TASK_DESCRIPTION

Examples: - todo gym
          - todo run

### Feature-Add Deadline Task

Adds Deadline Task to tasklist.

Format: deadline TASK_DESCRIPTION /by DATE/DATETIME

Examples: - deadline report /by 8/12/2020
          - deadline homework /by 10/12/2020 1600

### Feature-Add Event Task

Adds Event Task to tasklist.

Format: event TASK_DESCRIPTION /from DATE /to DATE

Examples: - event carnival /from 8/12/2020 /to 10/12/2020
          - event festival /from 10/12/2020 /to 20/12/2020

### Feature-Mark

Marks a task in tasklist as completed.

Format: mark INDEX

Examples: - mark 1
          - mark 5

### Feature-Unmark

Unmarks a task as uncompleted.

Format: unmark INDEX

Examples: - unmark 1
          - unmark 5


