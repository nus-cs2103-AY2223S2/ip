# User Guide

The Knight is a chat bot which is designed to help free you from life's smallest worries, by remembering all the tasks that you will need to do.

Be grateful, for the Knight helps anyone, even orcs.

1. [Quick Start][#quick-start]
2. [Features][#features]
3. [Usage][#usage]
4. [Summary][#summary]

## Quick Start
Ready to remember less, and achieve more?
All you need to do is:
1. Download it from here.
2. Open a command window in the same directory
3. Using the command window, type the following command:
`java -jar Duke.jar`
4. Add your tasks.
5. Let it manage your tasks for you 😉

## Features 

### Task Manager

Have you always been a klutz, and scramble to finish your assignments due to your forgetfulness?
Not to worry, Knight is here to (degradingly) remind you to do them all!

### Tagging

Want to tag an event as #fun? Or feeling #sad that you need to study for the upcoming midterms?
Fret not, for Knight can show you events of the same tags!

## Usage

### `todo` - Add a task without a timeframe.

Adds a task to be completed, but without any need for a specific timeframe.

  Format: `todo [task description]`

  Example of usage: 
  `todo Get new eyeglasses`

### `deadline` - Add a task which has a deadline

Adds a task which should be completed by the specified deadline in the following format: YYYY-MM-DD.

  Format: `deadline [task description] /by [YYYY-MM-DD]`

  Example of usage:
  `deadline Study for CS2105 Midterms /by 2023-02-28`

### `event` - Add a task which has a specific timeframe

Adds a task which is ongoing during the specified timeframe.

  Format: `event [task description] /from [start date] /to [end date]`

  Example of usage:
  `event Celebrating CNY! /from 23rd Jan 2023 /to 8th Feb 2023`

### `delete` - Delete a task

Deletes a task (by index) as specified.

  Format: `delete [index]`
  
  Example of usage:
  `delete 3`

### `list` - A list of all tasks currently scheduled

Displays the cumulative list of all tasks added by the user.

  Format: `list`

  Example of usage:
  `list`

### `mark` - Marks a task as completed

Marks a specified task (by index) as completed.

  Format: `mark [index]`

  Example of usage:
  `mark 2`
  
### `unmark` - Marks a task as not completed

Marks a specified task (by index) as not completed.

  Format: `unmark [index]`
  
  Example of usage:
  `unmark 2`
  
### `tag` - Sets a tag for a task

Sets the tag for a specified task (by index).

  Format: `tag [index] [tag name]`
  
  Example of usage:
  `tag 3 #EnjoyLife`
  
### `find` - Finds all tasks using specified keyword

Finds all matching tasks that have the specified keyword in their description.

  Format: `find [keyword]`
  
  Example of usage:
  `find assignment`
  
### `findtag` - Finds all tasks with matching tag

Finds all tasks which are exactly tagged with the specified tag name.

  Format: `find [tag name]`
  
  Example of usage:
  `findtag #EnjoyLife`
  
### `checkdue` - Checks the due date of a deadline

Finds the due date of a deadline using the specified index.

  Format: `checkdue [index]`
  
  Example of usage:
  `checkdue 3`
  
## Summary


|    Command Format  | Description |
| ------------------ | ----------- |
| todo [task description] | Creates a task with specified description |
| deadline [task description] /by [YYYY-MM-DD]                | Creates a task with specified deadline         |
| event [task description] /from [starting date] /to [ending date] | Creates a task within the specified timeframe |
| delete [index] | Deletes the task at the specified index |
| list | Displays the cumulative list of tasks added by the user |
| mark [index] | Marks the task at the specified index as completed |
| unmark [index] | Marks the task at the specified index as not completed |
| tag [index] [tag name] | Sets the tag name of the task at the specified index |
| find [keyword] | Finds a list of tasks which contain specified keyword in their description |
| findtag [tag name] | Finds a list of tasks which are exactly tagged with the specified tag name |
| checkdue [index] | Gets the due date of a task with deadline at the specified index (if applicable) |
