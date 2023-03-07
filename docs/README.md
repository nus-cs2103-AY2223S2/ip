# Nemo User Guide
:ocean::tropical_fish:**If you're a little forgetful (like Dory), don't worry, Nemo's got your back.** :ocean::tropical_fish:

**Nemo is a chatbot task tracker that takes in CLI inputs to store and manage tasks, deadlines and events.**

> "I forget things almost instantly. It runs in my family… well, at least I think it does... hmm, where are they?" - Dory

## Quick Start

1. Install `Java 11`.
2. Download Nemo from [here](https://github.com/unfazing/ip/releases).
3. Put the Nemo `jar` file into your desired folder.
4. Run `java -jar .\nemo.jar` to start the app.

## Features

### Track your Tasks!
:writing_hand: Nemo supports the following 3 types of tasks. 
1. **ToDos** - a task that needs to be completed eventually
2. **Deadlines** - a task that has a deadline for completion
3. **Events** - a task that has a start and end date

### Save and Load your Tasks!
:sunglasses: Nemo will save any changes to your task list into a data directory. This way you can load tasks that you had added in a previous session, or share these tasks with a friend.

### Undo and Redo any changes!
:astonished: Nemo supports undoing and redoing any changes you make to the task tracker in that session.

![Ui](Ui.png)

## Usages

### Todo Task - `todo <task_description>`
Adds a new todo task with the given description. 

**Example:** `todo eat some seaweed`<br>
**Expected Behaviour:** Adds a new todo task - 'eat some seaweed' to the bottom of current task list.

### Deadline Task - `deadline <task_description> /by <YYYY-MM-DD>`
Adds a new deadline task with the given description and deadline. 

**Example:** `deadline search for nemo /by 2023-01-01`<br>
**Expected Behaviour:** Adds a new deadline task - 'search for nemo' with deadline - 01 Jan 2023 to the bottom of current task list.

### Event Task - `event <task_description> /from <YYYY-MM-DD> /to <YYYY-MM-DD>`
Adds a new event task with the given description, from date and to date. 

**Example:** `event explore the pacific ocean /from 2023-01-01 /to 2023-02-02`<br>
**Expected Behaviour:** Adds a new even task - 'explore the pacific ocean' with start date - 01 Jan 2023 and end date - 02 Feb 2023 to the bottom of current task list.

### List - `list`
Lists all tasks with index, type of task completion status, task description and dates (for deadline and event tasks). 

**Example:** `list`<br>
**Expected Behaviour:** Returns list of all existing tasks.

### Mark - `mark <task_index>`
Marks a task with the given index as completed

**Example:** `mark 1`<br>
**Expected Behaviour:** Task with index 1 is marked as completed.

### Unmark - `unmark <task_index>`
Unmarks a task with the given index, thus displaying it as incomplete.

**Example:** `unmark 1`<br>
**Expected Behaviour:** Task with index 1 is unmarked and displayed as incomplete.

### Delete - `delete <task_index>`
Deletes a task with the given index from the current task list.

**Example:** `delete 1`<br>
**Expected Behaviour:** Task with index 1 is deleted from the list.

### Find - `find <keyword>`
Searches for tasks that contain a keyword or phrase specified by the user. 

**Example:** `find seaweed`<br>
**Expected Behaviour:** Returns list of tasks with the keyword "seaweed" in the description.

### Exit - `bye`
Exits the Nemo app. Current task list is saved into data/nemo.txt

**Example:** `bye`<br>
**Expected Behaviour:** Nemo app terminates, good bye!
