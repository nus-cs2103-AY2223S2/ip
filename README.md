## User Guide

Elise :spider: is a chat-bot for managing your tasks easily via a Command Line Interface, 
alongside a Graphical User Interface (GUI) for display

### Getting started

1. Ensure that you have Java 11 or higher installed
2. Download the latest `elise.jar` from [here](https://github.com/jiexuanc/ip/releases/tag/Level-10)
3. If you do not have JavaFX bundled, you should download `elise-fat.jar`
4. Copy the file to a folder of your choice
5. Open a command terminal and run `java -jar elise.jar`
6. Enter commands to interact with Elise!

### Features

# Notes
* [] are to be replaced completely according to the label and cannot be empty
* Date and time formats are flexible, / and - can be interchanged
  * dd/MM/YYYY hhmm `eg. 12/05/2000 1359`
  * dd/MM/YYYY `eg. 05/20/2023`
  * DDD `eg. Mon, tue` - Indicates next day of week, exclusive today
* Incorrect date format will be treated as plain text
* A folder to store data between instances will be created in the same folder

# Labels
[M] - Message
[D] - Date/Time
[R] - Rank
[K] - Keyword

# View Help `help`

# List all tasks `list`

# Create a todo task `todo [M]`

# Create a deadline task `deadline [M] /by [D]`

# Create an event task `event [M] /from [D] /to [D]`

# Mark task `mark [R]`

# Unmark task `unmark [R]`

# Delete task `delete [R]`

# Find task `find [K]`

# Exit the application `bye`

### Command Summary
|  Action  | Format, Examples                                                                            |
| --- |---------------------------------------------------------------------------------------------|
| add todo | `todo [M]`<br/>eg. `todo work`                                                              |
| add deadline | `deadline [M] /by [D]`<br/>eg. `deadline assignment /by Mon`                                |
| add event | `event [M] /from [D] /to [D]`<br/>eg. `event playtest /from 12/02/2023 /to 17/02/2023 1459` |
| list tasks | `list`                                                                                      |
| mark task | `mark [R]` <br/>eg. `mark 1`                                                                |
| unmark task | `unmark [R]`<br/>eg. `unmark 2`                                                             |
| delete task | `delete [R]`<br/>eg. `delete 2`                                                             |
| find task | `find [K]` <br/>eg. `find work`                                                              |
| help message | `help` |
| exit | `bye` |

