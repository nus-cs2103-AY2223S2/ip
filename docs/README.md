Duke is a desktop tasks lists to allow you to keep track of your tasks and events easily.
It provides an easy to use Graphical User Interface for users to interact with Duke.

# User Guide
1. Ensure that you have `Java 11` or above installed 
2. Download the latest `duke.jar` from [here](https://github.com/benedict733/ip/releases)
3. Copy `duke.jar` to the folder you want to use.
4. Double-click the file to start Duke.

## Features
* Keep track of your tasks easily
  * Tasks include todos, tasks with deadlines and events
* Mark tasks as done/not done
* Snooze on tasks with deadlines
* Delete tasks when not needed 
* Find tasks by keywords
* Save details into text file for easily retrieval

## Usage

### Adding a task: `Todo`, `Event`, `Deadline`

Adds a task into Skylark.

The format for the command is as per follows:
1. `Todo`: `todo DESCRIPTION`
2. `Event`: `event DESCRIPTION /from WHEN /to WHEN`
3. `Deadline`: `deadline DESCRIPTION /by DATETIME`

**NOTE** 
* There is no particular format for `WHEN` used in `Event`
* The format for `DATETIME` used in `Deadline`:
  * `DATETIME`: `yyy-mm-ddThh:mm:ss`


Example of usage:
* `todo read book`
* `deadline return book /by 2023-03-28T23:59:59`
* `event cs2103 finals /from 26 April 9am /to 11am`

Expected outcome:
* After running `todo read book`
  ```
  Got it. I've added this task:
    [T][ ] read book
  Now you have 1 tasks in the list.
  ```
* After running `deadline return book /by 2023-03-28T23:59:59`
  ```
  Got it. I've added this task:
    [D][ ] return book (by:2023-02-28T23:59:59)
  Now you have 2 tasks in the list.
  ```
* After running `event cs2103 finals /from 26 April 9am /to 11am`
  ```
  Got it. I've added this task:
    [E][ ] cs2103 finals (from : 26 April 9am to: 11am)
  Now you have 3 tasks in the list.
  ```

### Lists the current tasks - `list`

Shows the list of tasks saved in Skylark

Example of usage:
* `list`

Expected outcome:
```
Here are the tasks in your list:
1. [T][ ] borrow book
2. [D][ ] return book (by:2023-02-28T23:59:59)
3. [E][ ] cs2103 finals (from : 26 April 9am to: 11am)
```

### Note for the following commands: `delete`, `mark`, `unmark`, `snooze`
1. If the index does not exist:
   * After running `delete/mark/unmark/snooze 8`
       ```
       Please enter a number from 1 to 2
       ```
2. If an integer is not entered:
   * After running `delete/mark/unmark/snooze six`
      ```
     Please enter a valid number
     ```

### Delete a Task - `delete`

Delete a particular task 

Example of usage:
* `delete INDEX`

**NOTE:** The index must be a valid `integer` representing the index of the task

Expected outcome:
* After running `delete 1`
    ```
    Noted. I've removed this task:
        [T][ ] borrow book
    Now you have 2 tasks in the list
    ```

### Marking/Unmarking a Task as complete - `unmark`, `mark`

Marks/Unmarks a particular task from as done/undone

Example of usage:
* `mark INDEX`
* `unmark INDEX`

**NOTE:** The index must be a valid `integer` representing the index of the task

Expected outcome:
* After running `mark 1`
    ```
    Noted. I've marked this task as done:
        [D][X] return book (by:2023-03-28T23:59:59)
    ```
* After running `unmark 1`
    ```
    Noted. I've unmarked this task as done:
        [D][ ] return book (by:2023-03-28T23:59:59)
    ```

### Snoozing a Task - `snooze`

Snoozes a particular task for 1 day

Example of usage:
* `snooze INDEX`

**NOTE:** The index must be a valid `integer` representing the index of the task

Expected outcome:
* After running `snooze 1`
    ```
    Nice!. I've snoozed this task:
        [D][ ] return book (by:2023-03-29T23:59:59)
    ```
**NOTE:** If the task at the index is not a `Deadline` task:
* After running `snooze 2`
    ```
    Please enter the index of a task which is a deadline
    ```

### Finding a particular task - `find`

Finds any task that contains the keyword(s) given by the user

Example of usage:
* `find KEYWORDS`

Expected outcome:
* After running `find CS2103`

    ```
    1. [E][ ] CS2103 finals (from : 26 April 9am to: 11am)
    ```

**NOTE:** If there are no matching tasks:
* After running `find hehe`
    ```
    There are no tasks found with words:
    hehe
    ```

### Exiting the program - `bye`

Exits the program

Example of usage:
* `bye`

### Saving data

Tasks stored in Duke are automatically saved after any command.