# User Guide
***Duke*** is a desktop application used for managing tasks, optimized for users who
prefer the use of Command Line Interface (CLI) while still having the perks of 
Graphical User Interface (GUI). If you are able to type fast, ***Duke*** can help you
to keep track of tasks and remind you on the due dates.

+ [Quick Start](#quick-start)
+ [Features](#features)
+ [Usage](#usage)
+ [FAQ](#faq)
+ [Command Summary](#command-summary)

## Quick Start
1. Ensure you have Java `11` or later versions installed in your Computer.
2. Download the latest `duke.jar` from [here](https://github.com/MrTwit99/ip/releases).
   1. Instructions on downloading `duke.jar` can be found [here](https://github.com/nus-cs2103-AY2223S2/ip/pull/66).
3. Copy the `duke.jar` file from the folder you have downloaded into a new folder on
    your desktop for your own ***Duke***.
4. Double click the `duke.jar` file to launch the application!
   
   *The image below shows how it looks like if the application managed to run **SUCCESSFULLY**!

   ![image](https://github.com/MrTwit99/ip/blob/master/Images%20for%20User%20Guide/DukeBootupPhoto.jpg?raw=true)

5. (Optional) Alternative method to launch the application is to run via the command terminal.
   1. Type `cmd` on your desktop search bar to locate and open the command prompt.
   2. Type `java -jar duke.jar` to launch the application!

      ```ruby
      java -jar duke.jar
      ```
6. Type the command in the command box and press the `Send` button or press `Enter` on your keyboard to execute it.
   
   Some example commands you can try:
   - `list` : Lists all tasks that are stored, supposed to be empty on first launch
   - `reminder` : Displays the current date and time based on your computer's date and time and lists all tasks that are
   due.
   - `find [keyword]` : Lists all tasks that matches the `[keyword]` from the list of tasks.
   - `bye` : Exits the application.
7. Refer to the [Features](#Features) section below for details of each command.

## Features
This section aims to provide you with some brief description on `Duke` features.
For in-depth CLI command to call the features, refer to the [Usage](#Usage) section

### Adding a Task

There are 3 types of Tasks that you can add: a `ToDo task`, a `Deadline task` and an `Event task`.

### Marking a Task

You can mark a `Task` as completed whenever you complete a `Task`.

### Unmarking a Task

You can unmark a `Task` if the `Task` has been incorrectly marked as complete.

### List

Displays the task list which contains all `Tasks` with its full description. e.g. name, completion status, time and date.

### Deleting a Task

You can delete a `Task` by indicating their respective `INDEX_NUMBER` shown on the task list.

You can view the task list by running the List command.

### Finding Tasks

Quick search function to locate all `Tasks` that contains the input you gave to Duke and displays them in a list.

***STRONG*** quick search function that is able to detect all `Tasks` that contain a portion of your input.
```
Your input    -> "find assignment tests exams"
Duke's output -> All Tasks that contain either "assignment", "tests" or "exams" in their
                 description.
```

### Reminder
Displays the following:
1. Current date and time based on your system's date and time setting. 
2. All `Tasks` that are either `ToDo` or have yet past its `Deadline` or `Event` has yet to started / ended.

### Help
Displays information on help and troubleshooting section that will redirect you to this webpage.

## Usage
This section aims to provide you with in-depth CLI command to call the features in [Feature](#Feature).

Some examples are given as well for each feature to guide you.
```
Notes about the command format:
- Command lines supplied by the user are not case sensitive as the application will
  auto-translate it into lower_case.
  
- Items in <> brackets are COMPULSORY values that the user should field
  e.g. in todo <TASK_NAME>, `TASK_NAME` is a parameter that should be included to create 
  a ToDo Task
  e.g. creating a ToDo task -> todo borrow book
  
- Items in [] brackets are OPTIONAL values that the user can choose to field.
  e.g. in event Birthday /from <START_DATE> [START_TIME] /to <END_DATE> [END_TIME]
  e.g. creating an Event task -> event /from 2023-05-05 /to 2023-05-06
                              -> event /from 2023-05-05 00:00 /to 2023-05-05 23:59
```

### `ToDo` - Adds a ToDo Task
Adds a `ToDo` Task to the task list.

Format: `todo <TASK_NAME>`

Example of usage: `todo tutorial 1`

Expected outcome if **successful** creation of `ToDo` Task:
```
____________________________________________________________________________________
Got it. I've added this task to the list:
  [T][ ] tutorial 1
Now you have 1 tasks in the list.
____________________________________________________________________________________
```

### `Deadline` - Adds a Deadline Task
Adds a `Deadline` Task to the task list.

Format: `deadline <TASK_NAME> /by <DATE> [TIME]`

Example of usage: 

`deadline Assignment 1 /by 2023-05-05`

`deadline Assignment 1 /by 2023-05-05 15:00`

Expected outcome if **successful** creation of `Deadline` Task:
```
____________________________________________________________________________________
Got it. I've added this task to the list:
  [D][ ] Assignment 1(by: May 5, 2023 | 03:00:00 pm)
Now you have 2 tasks in the list.
____________________________________________________________________________________
```

### `Event` - Adds a Event Task
Adds a `Event` Task to the task list.

Format: `event <TASK_NAME> /from <START_DATE> [START_TIME] /to <END_DATE> [END_TIME]`

Example of usage:

`event Spring Festival Celebrations /from 2023-04-04 10:00 /to 2023-05-05`

`event Spring Festival Celebrations /from 2023-04-04 10:00 /to 2023-05-05 22:00`

Expected outcome if **successful** creation of `Event` Task:
```
____________________________________________________________________________________
Got it. I've added this task to the list:
  [E][ ] Spring Festival Celebrations(from: April 4, 2023 | 10:00:00 AM to: May 5, 
  2023 | 10:00:00 PM)
Now you have 3 tasks in the list.
____________________________________________________________________________________
```

### `Mark` - Marks a Task
Marks a `Task` as completed.

Format: `mark <TASK_NUMBER>`

Example of usage: `mark 2`

Expected outcome if marking a Task that is ***inside*** the task list:
```
____________________________________________________________________________________
 Nice! I've marked this task as done:
  [D][X] Assignment 1 (by: May 5, 2023 | 3:00:00 PM)
____________________________________________________________________________________
```

### `Unmark` - Marks a Task as incomplete
Marks a `Task` as incomplete.

Format: `unmark <TASK_NUMBER>`

Example of usage: `unmark 2`

Expected outcome if unmarking a Task that is ***inside*** the task list:
```
____________________________________________________________________________________
 Alright, I've marked this task as not done yet:
   [D][ ] Assignment 1 (by: May 5, 2023 | 3:00:00 PM)
____________________________________________________________________________________
```

### `List` - Displays all Tasks
Displays all `Tasks` in the task list.

Format: `list`

Example of usage: `list`

Expected outcome if it is in the same format as the example:
```
____________________________________________________________________________________
Here are the tasks in your list:
1.[T][ ] tutorial 1
2.[D][ ] Assignment 1 (by: May 5, 2023 | 3:00:00 PM)
3.[E][ ] Spring Festival Celebrations (from: April 4, 2023 | 10:00:00 AM to: May 5, 
  2023 | 10:00:00 PM)
____________________________________________________________________________________
```

### `Delete` - Delete a Task
Delete the selected Task from the task list.

Format: `delete <TASK_NUMBER>`

Example of usage: `delete 2`

Expected outcome if deleting a Task that is ***inside*** the task list:
```
____________________________________________________________________________________
Noted. I've removed this task:
  [D][ ] Assignment 1 (by: May 5, 2023 | 3:00:00 PM)
Now you have 2 tasks in the list.
____________________________________________________________________________________
```

### `Find` - Find all matching Tasks
Finds all Tasks in the task list that matches your input.

Format: `find <TEXT_TO_FIND>`

Example of usage: `find tutorial celebrations`

Expected outcome if found results matching the `TEXT_TO_FIND` otherwise output a empty list:
```
____________________________________________________________________________________
Here are the matching tasks in your list:
1.[T][ ] tutorial 1
2.[E][ ] Spring Festival Celebrations (from: April 4, 2023 | 10:00:00 AM to: May 5, 
  2023 | 10:00:00 PM)
____________________________________________________________________________________
```

### `Reminder` - Reminds you on all Tasks that are due
Finds all ToDo tasks, Deadline tasks (yet to end), Event tasks (yet to start / end) in the task list.

Format: `reminder`

Example of usage: `reminder`

Expected outcome if found results from the task list:
```
____________________________________________________________________________________
REMINDER!
It is currently Feb 15, 2023, 1:14:40 AM.
These are the tasks that you have to do!!!
____________________________________________________________________________________
Tasks:
1.[T][ ] tutorial 1
2.[E][ ] Spring Festival Celebrations (from: April 4, 2023 | 10:00:00 AM to: May 5, 
  2023 | 10:00:00 PM)
____________________________________________________________________________________
```

### `Help` - Help & Troubleshooting
Provides you with a help message that redirects you to this website.

Format: `help`

Example of usage: `help`

Expected outcome if correctly written as per the example:
```
____________________________________________________________________________________
Refer to the below link to view the User Guide for more help and troubleshooting!
https://mrtwit99.github.io/ip/
____________________________________________________________________________________
```

## FAQ
Here are some common questions that you might ask.

**Q**: Duke displayed an error message to me and I'm not sure what I did wrong.

**A**: Here are some possible scenarios that might have caused the error message:

    InvalidCommandException
    1. Command / Function that you are accessing is **NOT SUPPORTED** by Duke.
    
    Invalid Date / Time
    1. Wrong date / time format given ("Monday")
    2. Incorrect input of Date / Time ("2023-13-23" / "25:00")
    
    IncorrectNoOfArgumentException
    1. Incorrect number of arguments given for the command ("todo")
    2. Incorrect argument given for the command ("list 2")
    3. Use of blank spaces when not required ("reminder ")

**Q**: How do I edit the task description of a certain `Task`?

**A**: Unfortunately, the current version of Duke does not handle editing of task information besides `Mark` and 
`Unmark`. To edit the task description, you will have to `delete` the `Task` and recreate it.

**Q**: I still have other queries on troubleshooting, where do I address them to?

**A**: You can visit the following [link](https://github.com/MrTwit99/ip/issues) to create a `New Issue`. 
Expected working days before issue is answered: 2 - 3 working days.


## Command Summary

| Command      | Format                                                          | Expected Outcome                   |
|--------------|-----------------------------------------------------------------|------------------------------------|
| **ToDo**     | `todo tutorial 1`                                               | Adds a `ToDo` Task                 |
| **Deadline** | `deadline Assignment 1 /by 2023-05-05 15:00`                    | Adds a `Deadline` Task             |
| **Deadline** | `deadline Assignment 1 /by 2023-05-05`                          | Adds a `Deadline` Task             |
| **Event**    | `event Celebration /from 2023-04-04 /to 2023-05-05`             | Adds a `Event` Task                |
| **Event**    | `event Celebration /from 2023-04-04 10:00 /to 2023-05-05`       | Adds a `Event` Task                |
| **Event**    | `event Celebration /from 2023-04-04 /to 2023-05-05 15:00`       | Adds a `Event` Task                |
| **Event**    | `event Celebration /from 2023-04-04 10:00 /to 2023-05-05 15:00` | Adds a `Event` Task                |
| **Mark**     | `mark 2`                                                        | Marks `Task` with task no `2`      |
| **Unmark**   | `unmark 2`                                                      | Unmarks `Task` with task no `2`    |
| **List**     | `list`                                                          | Displays all `Tasks` in task list  |
| **Delete**   | `delete 2`                                                      | Deletes `Task` with task no `2`    |
| **Find**     | `find assignment test`                                          | Find all `Tasks` with either words |
| **Reminder** | `reminder`                                                      | Displays all `Tasks` that are due  |
| **Help**     | `help`                                                          | Display a help message             |
