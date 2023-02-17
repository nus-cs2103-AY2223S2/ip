# User Guide
> Greetings, Empress. I am Shinsoo, a divine bot from Ereve. It is an honor to be in your presence.

Shinsoo is a desktop app for task management, optimized for use via a Command Line Interface (CLI) while still having the benefits of a Graphical User Interface (GUI). If you can type fast, Shinsoo can get your task management done faster than traditional GUI apps.

- [Quick Start](#quick-start)
* [Features](#features)
  - [Feature - Add Tasks](#feature---add-tasks)
  - [Feature - List](#feature---list)
  - [Feature - Find](#feature---find)
  - [Feature - Marking](#feature---marking)
  - [Feature - Delete](#feature---delete)
  - [Feature - Exit](#feature---exit)
+ [Command Summary](#command-summary)

## Quick Start
1. Ensure you have Java 11 or above installed in your Computer.
2. Download the latest addressbook.jar from here.
3. Copy the file to the folder you want to use as the home folder for your task management app.
4. Open a command terminal, cd into the folder you put the jar file in, and use the java -jar addressbook.jar command to run the application.
A GUI similar to the below should appear in a few seconds. Note how the app contains some sample data.
![QuickStart](https://user-images.githubusercontent.com/97417244/219500881-c5149da1-acb7-4177-bce3-500b0b7576d9.png)
5. Type the command in the command box and press Enter to execute it.
6. Refer to the Features below for details of each command.

## Features 

### Feature - Add Tasks

Adds a task to the app.

Format: <br/>
`todo DESCRIPTION`<br/>
`deadline DESCRIPTION /by DATE`<br/>
`event DESCRIPTION /from DATE /to DATE`.

Examples: <br/>
`event trip to Langkawi /from 2023-02-21 /to 2023-02-27`

---
### Feature - List

Shows a list of all the tasks.

Format: <br/>
`list`

---
### Feature - Find

Find tasks via certain criteria.

**keyword** <br/>
Format: <br/>
`find DESCRIPTION`

Examples: <br/>
`find trip`

**deadline** <br/>
Format: <br/>
`findBy DATE`

Examples: <br/>
`findBy 2023-02-25`

**day** <br/>
Format: <br/>
`viewSchedule DATE`

Examples: <br/>
`viewSchedule 2023-02-25`

---
### Feature - Marking

Marks a task from the app.

Format: <br/>
`mark INDEX` <br/>
`unmark INDEX`

Examples: <br/>
`mark 1`

---
### Feature - Delete

Deletes a task from the app.

Format: <br/>
`delete INDEX`

Examples: <br/>
`delete 1`

---
### Feature - Exit

Exit the program.

Format: <br/>
`bye`

## Command Summary
| Action | Format                     |
|--------|----------------------------|
| Add    | `todo DESCRIPTION`, `deadline DESCRIPTION`, `event DESCRIPTION /from DATE /to DATE`|
| List   | `list`                     |
| Find   | `find DESCRIPTION`, `findBy DATE`, `viewSchedule DATE`|
| Marking | `mark INDEX`, `unmark INDEX`|
| Delete | `delete INDEX` |
| Exit | `bye` |
