# User Guide 

Duke chatbot is a **desktop app for managing tasks** (CLI) while still having the benefits of a Graphical User Interface (GUI). It has a Cristiano Ronaldo personality.

--------------------------------------------------------------------------------------------------------------------

## Quick start

1. Ensure you have Java `11` or above installed in your Computer.

1. Download the latest `duke.jar` from [here](https://github.com/ARPspoofing/ip/releases/tag/v0.2).

1. Copy the file to the folder you want to use as the _home folder_ for your Duke.

1. Open a command terminal, `cd` into the folder you put the jar file in, and use the `java -jar duke.jar` command to run the application.<br>
   A GUI similar to the below should appear in a few seconds.<br>
   ![Ui](Ui.png)

1. There are also a alternatives to run the Duke chatbot:
    1. Go to the root directory. Then do ./gradlew build. After the test cases has
       passed, do ./gradlew run

    1. The other way is to use the run scripts written in bash

    1. The last way is to manually navigate to src/main/java/duke folder. Complie
       using javac. Then go back to src/main/java. To run, do java Duke duke.

1. Type the command in the command box and press Enter to execute it. e.g. typing **`list`** and pressing Enter will list all the tasks.<br>
   Some example commands you can try:

    * `list` : Lists all tasks.

    * `Borrow book` : Adds a task named `Borrow book` to the Duke chatbot.

    * `delete 1` : Deletes the 1st task shown in the current list.

    * `exit` : Exits the chatbot.

1. Refer to the [Features](#features) below for details of each command.

--------------------------------------------------------------------------------------------------------------------

## Features

<div markdown="block" class="alert alert-info">

**:information_source: Notes about the command format:**<br>

* Words in `UPPER_CASE` are the parameters to be supplied by the user.<br>
  e.g. in `find TASK`, `TASK` is a parameter which can be used as `find borrow book`.

* Parameters cannot be in any order.<br>
  e.g. if the command specifies `event project meeting /from Mon 2pm /to 4pm`, `event project meeting /to 4pm /from Mon 4pm` is unacceptable.

</div>

### Adding a task: ` `

Adds a task to Duke chatbot.

Format:
- `NAME`
- `event NAME /from START_TIME_AND_DATE /to ENDTIME`
- `deadline NAME /by DUEDATE`
- `todo NAME`

Examples:
* `Borrow book`
* `todo borrow book`
* `deadline return book /by Sunday`
* `event project meeting /from Mon 2pm /to 4pm`

**The new customised task added is a recur task. Please follow the
instructions below to test it out**

1. To add a recur task, type "recur", followed by a single word task name, and
   the period of repetition in ms.
2. For example, if I want a recur task study to run 1s periodically, I will
   type "recur study 1000".
3. You should see the recur task popping up. You will be able to do other
   tasks meanwhile since the recur task runs on a different thread.
4. To delete the recur task, type "recur delete", followed by the nth recur
   task that you intend to delete. The list starts from index 1.
5. For example, if I want to delete the first recur task, I will type "recur
   delete 1"

### Adding a recurring / reminder task: `recur`

Adds a recurring task to Duke chatbot. The period is in ms.

Format:
- `recur NAME PERIOD`

Examples:
* `recur book 1000`
* `recur sleep 10000`

### Delete a recurring / reminder task: `recur delete`

Deletes a recurring task from Duke chatbot. Index is the index of the recur list and not the index of the list of all tasks.

Format:
- `recur delete INDEX`

Examples:
* `recur delete 1`
* `recur delete 5`

### Listing all tasks : `list`

Shows a list of all tasks in Duke.

Format: `list`

### Listing tasks by name: `find`

Finds tasks whose names contain any of the given keywords.

Format: `find KEYWORD [MORE_KEYWORDS]`

* The search is case-sensitive. e.g `book` will not match `Book`
* The order of the keywords matter. e.g. `borrow book` will not match `book borrow`
* Only the name is searched.
* Persons matching all keywords will be returned (i.e. `AND` search).
  e.g. `borrow book` will return `book book mythical-man-month`

Examples:
* `find borrow book` returns `borrow book mockingbird` and `borrow book mythical-man-month`<br>

### Deleting a task : `delete`

Deletes the specified task from Duke chatbot.

Format: `delete INDEX`

* Deletes the task at the specified `INDEX`.
* The index refers to the index number shown in the list of all tasks.
* The index **must be a positive integer** 1, 2, 3, …​

Examples:
* `list` followed by `delete 2` deletes the 2nd task in Duke chatbot.

### Exiting the program : `exit`

Exits the program.

Format: `exit`

### Saving the data

Duke data are saved in the hard disk automatically after any command that changes the list of tasks. There is no need to save manually.

### Editing the data file

AddressBook data are saved as a txt file `[JAR file location]/dukeList.txt`. Advanced users are welcome to update data directly by editing that data file.

<div markdown="span" class="alert alert-warning">:exclamation: **Caution:**
If your changes to the data file makes its format invalid, Duke will not be able to run properly and will throw errors.
</div>

--------------------------------------------------------------------------------------------------------------------

## Command summary

Action | Format, Examples
--------|------------------
**Add** | e.g., `event project meeting /from Mon 2pm /to 4pm`
**Delete** | `delete INDEX`<br> e.g., `delete 3`
**Find** | `find KEYWORD [MORE_KEYWORDS]`<br> e.g., `find borrow book`
**List** | `list`
**Exit** | `exit`
