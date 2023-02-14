# User Guide
**Babe** is a task manager with a Command Line Interface (CLI) built inside a Graphic User Interface (GUI). 
It is more efficient as it relieves the user from the need of a keyboard and enables fast-typers to manage tasks faster
with test-based commands. 

## Features 

### Adding Tasks
There are three type of Tasks available in Babe, namely a To-Do, a Deadline  and an Event. The differences are outlined 
below: 
1. ToDo 
   * A standard Task with only a description of the Task, without any additional info. 
   * Identified by the letter "T"
2. Deadline
   * A Task with a description, and a deadline consisting of a date and time.
   * Identified by the letter "D"
3. Event
   * A Task with a description, as well as a starting date/time and an ending date/time.
   * Identified by the letter "E"
   

### Viewing your tasklist
View all Tasks added to the Babe's tasklist at once. In the printed list, the done status and the type of the task
list (represented by the task's identifying letter) will be displayed, other than any extra information carried by the 
task. 

### Deleting a Task
Delete any existing Task in the tasklist. The user may choose to delete multiple tasks at once. 

### Changing status of a Task
Each Task has a done status that can be altered between "Done" and "Not done". "Done" is indicated with a "X".

### Finding a Task
A user may search for a Task by entering part of a Task's description. 


## Usage
`Tip: Under Format, any round-bracketed (i.e. ( )) arguments are optional. Curly-braces indicate a placeholder
which user may substitute with their own text.`

### `todo` - Adding a To-Do

Format: 

`todo {description}`

Example of Usage: 

`todo eat`

Expected outcome:

The added To-do will be displayed. Babe will also inform you of the existing number of tasks in the task list. 

```
Got it, babe. Added this for you:
[T] [ ] eat
Now you have 4 task in the list.
```
### `deadline` - Adding a Deadline

**Format:**

`deadline {description} /by {date} ({time})`
* The date should take the format of YYYY-MM-DD.

**Example of Usage:**

`deadline sleep /by 2023-02-14 2AM`

**Expected outcome:**

The added Deadline will be displayed. Babe will also inform you of the existing number of tasks in the task list.

```
Got it, babe. Added this for you:
[D] [ ] sleep (by: 14 FEBRUARY 2023 2AM)
Now you have 5 task in the list.
```
### `event` - Adding an Event

**Format:**

`event {description} /from {date} ({time}) /to {date} ({time})`
* The date should take the format of YYYY-MM-DD.

**Example of Usage:**

`event work /from 2023-02-14 8AM /to 2023-02-14 8PM`

**Expected outcome:**

The added Event will be displayed. Babe will also inform you of the existing number of tasks in the task list.

```
(ɔ˘ ³˘)ɔ: Got it, babe. Added this for you:
[E] [ ] work (from: 14 FEBRUARY 2023 8AM, to: 14 FEBRUARY 2023 8PM)
Now you have 6 task in the list.
```

### `list` - Listing all Tasks

**Format:**

`list`

**Example of Usage:**

`list`

**Expected outcome:**

The task list in Babe will be printed as described under Features. 

```
(ɔ˘ ³˘)ɔ: This is your list so far:
1. [T] [ ] eat
2. [D] [ ] sleep (by: 14 FEBRUARY 2023 2AM)
3. [E] [ ] work (from: 14 FEBRUARY 2023 8AM, to: 14 FEBRUARY 2023 8PM)
```
### `delete` - Deleting a Task

**Format:**

`delete {index1}, ({index2}, ..., {indexN})` where N is the total number of Tasks in the list. 
- `index` refers to the numbering of the Task(s) to be deleted according to the task list. 
  <br> E.g., based on the task list under the _Expected outcome_ segment of _Listing all Tasks_, 
  the index of the To-Do Task with description 'eat' would be 1.

**Example of Usage:**

`delete 1`

**Expected outcome:**

Babe will inform user of the Task that has been deleted. 
```
Love that work ethics of yours! Here's what I removed from the list:
[T] [ ] eat

Now you have 2 task(s) left!
```
### `mark` - Marking a Task as 'Done'

**Format:**

`mark {index}`
 - `index` refers to the numbering of the Task to be marked as 'Done' according to the task list.

**Example of Usage:**

`mark 1`

**Expected outcome:**

Babe will display the Task marked as 'Done'. 
The 'Done' status is indicated with an 'X' in the second square bracket '[ ]'. 
```
(ɔ˘ ³˘)ɔ: Okay, babygorl. I've marked this as Done:
[D] [X] sleep (by: 14 FEBRUARY 2023 2AM)
```
### `unmark` - Marking a Task as 'Not Done'

**Format:**

`unmark {index}`
- `index` refers to the numbering of the Task to be marked as 'Done' according to the task list.

**Example of Usage:**

`unmark 1`

**Expected outcome:**

Babe will display the Task marked as 'Not Done'.
The 'Not Done' status is with an empty square bracket '[ ]'following the type identifier 
of the Task (i.e. [T], [D], or [E].)

```
(ɔ˘ ³˘)ɔ: We have un-Done this for you:
[D] [ ] sleep (by: 14 FEBRUARY 2023 2AM)
```

### `find` - Finding Task(s)

**Format:**
`find {keyword}`
- `keyword` refers to any search string that the user intends to use.

**Example of Usage:**

`find e`

**Expected outcome:**

All Tasks whose descriptions contain the search key partially will be printed by Babe. 

```
(ɔ˘ ³˘)ɔ: Here's what we could find:
1. [D] [ ] sleep (by: 14 FEBRUARY 2023 2AM)
2. [T] [ ] eat
```