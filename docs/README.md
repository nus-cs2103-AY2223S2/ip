# User Guide
Duke is a personal assistant chatbot that that helps a person to keep track of various tasks.

- [Quick Start](#quick-start)
- [Features](#features)
- [FAQ](#faq)
- [Command Summary](#command-summary)

## Quick Start
1. Ensure you have Java 11 or above installed in your computer.
2. Download the latest duke.jar from [here](https://github.com/SeanChinJunKai/ip/releases)
3. Copy the file to the folder you want to use as the home folder for your Duke chatbot.
4. Open a command terminal, cd into the folder you put the jar file in, and use the java -jar duke.jar command to run the application.

## Features 

### Adding a todo: `todo`

Adds a todo to Duke task manager.

Format: `todo DESCRIPTION`

### Adding a deadline: `deadline`

Adds a deadline to Duke task manager.

Note: DATE has to be in YYYY-MM-DD format!

Format: `deadline DESCRIPTION /by DATE`

### Adding an event: `event`

Adds an event to Duke task manager.

Format: `event DESCRIPTION /from START /to END`

### Listing all tasks: `list`

Shows a list of all tasks in the Duke task manager.

Format: `list`

### Mark a task as done: `mark`

Marks specified task as done.

Format: `mark INDEX` 

### Mark a task as not done: `unmark`

Marks specified task as not done.

Format: `unmark INDEX`

### Delete a task: `delete`

Deletes the specified task from the Duke task manager.

Format: `delete INDEX`

### Find matching tasks using keyword: `find`

Finds tasks whose description contains the given keyword.

Format: `find KEYWORD`

### Exiting the program: `bye`

Exits the program.

## FAQ
**Q**: How do I transfer my data to another Computer? <br/>
**A**: Install the app in the other computer and overwrite the empty data file it creates with the file that contains the data of your previous AddressBook home folder.

## Command Summary

| Action   | Format, Examples                                                             |
|----------|------------------------------------------------------------------------------|
| Todo     | todo DESCRIPTION <br/> e.g. todo run 5km                                     |
| Deadline | deadline DESCRIPTION /by DATE e.g. deadline write essay /by 2023-02-20       |
| Event    | event DESCRIPTION /from START /to END e.g. event book fest /from 2pm /to 5pm |
| List     | list                                                                         |
| Mark     | mark INDEX e.g. mark 2                                                       |
| Unmark   | unmark INDEX e.g. unmark 1                                                   |
| Delete   | delete INDEX e.g. delete 3                                                   |
| Find     | find KEYWORD e.g. find run                                                   |
| Bye      | bye                                                                          |