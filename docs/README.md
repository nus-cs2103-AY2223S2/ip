# User Guide
**TrumpBot** is a desktop chatbot that will help you manage your tasks. It offers the convenience of a Command Line Interface (CLI) with the added benefit of a Graphical User Interface (GUI). With TrumpBot, you can manage your tasks and get more done in less time, especially if you're a fast typist. 

Here's to making task scheduling great again!

1. [Quick start](#quick-start)
2. [Features](#key-features)
3. [Command summary](#command-summary)
4. [Usage](#usage)
5. [Product screenshots](#product-screenshots)

## Quick Start
1. Ensure you have `Java 11` or above installed in your Computer.
2. Download the latest `duke.jar` from here.
3. Copy the file to the folder you want to use as the home folder for your **TrumpBot**.
4. Open a command terminal, cd into the folder you put the jar file in
5. Use the command: `java -jar duke.jar` to run the application.
6. A GUI similar to the below should appear in a few seconds.

<p align="center">
<img src="https://user-images.githubusercontent.com/96603198/218941064-9efb1d99-b150-464f-b0cf-65b1b4aae9fc.png" align="center" height=auto width="600">
</p>
<p align="center">
<em>Landing Page</em>
</p><br/>

<p align="center">
<img src="https://user-images.githubusercontent.com/96603198/218942814-7bc62150-79c9-4bb0-a9f1-23047674ab45.png" align="center" height=auto width="600">
</p>
<p align="center">
<em>Main Page</em>
</p><br/>

## Key Features 

### &check; _Case Insensitive Keywords_ :fire:

**TrumpBot** adopts the use of case insensitive keywords. Users can enter commands and options more easily without having to worry about getting the capitalization exactly right. This can reduce user frustration and lead to a more pleasant and enjoyable user experience.

### &check; _Auto-save_ :floppy_disk:

**TrumpBot** automatically save changes made by users to a text file named `duke.text` under the `data` folder. Not only does this prevents data loss, it removes the stress associated with the possibility of losing past progress. With auto-save, users can work more efficiently with fewer interruptions.

### &check; _Time Management_ :clock9:

**TrumpBot** is able to scan through user's list of tasks and check for the next available free time down to the minutes. By identifying free time slots, **TrumpBot** allows users to plan and schedule tasks more efficiently, making the most of your available time.

## Command Summary 


| Command                               | Description                                                                        |
| ------------------------------------- | ---------------------------------------------------------------------------------- |
| `mark <task index>`                   | Marks the specified task as completed.                                             |
| `unmark <task index>`                 | Marks the specified task as not completed.                                         |
| `delete <task index>`                 | Deletes the specified task.                                                        |
| `list`                                | Displays the list of logged tasks.                                                 |
| `sort`                                | Sorts and displays the list of logged tasks by nearest deadline.                   |
| `find <keyword> [more keywords...]`   | Finds the list of tasks whose name matches the provided keyword/s.                 |
| `todo <name>`                         | Adds a Todo to the list of tasks.                                                  |
| `deadline <name> /by <time>`          | Adds a Deadline to the list of tasks.                                              |
| `event <name> /from <time> /to <time>`| Adds an Event to the list of tasks.                                                |
| `seek <duration>`                     | Find the nearest time slot that is available for the specified duration.           |
| `bye`                                 | Exits **TrumpBot**                                                                 |


## Usage

### `mark` - Mark a task as done

Marks the specified task as completed.

Format: `mark <task index>`

Example of usage: 

- `mark 7`
- `mark 16`

---

### `unmark` - Mark a task as not done

Marks the specified task as not completed.

Format: `unmark <task index>`

Example of usage: 

- `unmark 4`
- `unmark 6`

---

### `delete` - Delete a task

Deletes the specified task.

Format: `delete <task index>`

Example of usage: 

- `delete 2`
- `delele 9`

---

### `list` - List all tasks

Displays the list of logged tasks.

Format: `list`

---

### `sort` - Sort all tasks

Sorts and displays the list of logged tasks by nearest deadline.

Format: `sort`

---

### `find` - Find tasks using keyword/s

Finds the list of tasks whose name matches the provided keyword/s.

Format: `find <keyword> [more keywords...]`

Example of usage: 

- find `CS2103`
- `find love`
- `find fault`

---

### `todo` - Add a Todo

Adds a Todo to the list of tasks.

Format: `todo <name>`

Example of usage: 

- `todo buy grocceries`
- `todo study for quiz`

---

### `deadline` - Add a Dealine

Adds a deadline to the list of tasks.

Format: `deadline <name> /by <time>`

Example of usage: 

- `deadline Complete User Guide /by 15/02/2023 2359`
- `deadline Assignment 1 /by 17/02/2023 2359`

---

### `event` - Add a Event

Adds an Event to the list of tasks

Format: `event <name> /from <time> /to <time>`

Example of usage: 

- `event Orbital /from 01/05/2022 0800 /to 01/08/2022 1900`
- `event Interview with Google /from 16/04/2023 1000 /to 16/04/2023 1200`

---

### `seek` - Find free time

Find the nearest time slot that is available for the specified duration.

Format: `seek <duration>`

Example of usage: 

- `seek 4 hours`
- `seek 1 day`

---

### `bye` - Exits the program

Exits **TrumpBot**

Format: `bye`

## Product Screenshots

<p align="middle">
  <img src="https://user-images.githubusercontent.com/96603198/218974263-132257a3-3019-44c2-aa6d-f41e97956b71.png" width="500" />
  <img src="https://user-images.githubusercontent.com/96603198/218974272-ff53d0e5-3449-47bc-b9a3-daaa98dc161d.png" width="500" /> 
</p>
