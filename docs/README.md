# User Guide

## Usage

### Commands Available

- `bye`: Exit the program
- `list`: Show all recorded tasks
- `datetime <date> <time>`: Show all tasks that falls on the specified date and time

  - datetime format can be in (24-hour)
    1. dd/MM/YYYY hhmm
    2. YYYY-MM-dd hhmm
    3. dd/MM/YYYY
    4. YYYY-MM-dd
  - If the time is not specified, a default 0000hrs will be considered.
  - Example of usage:

	```
    datetime 2023-02-07 1530
    datetime 07/02/2023 1530
  	```

- `mark/unmark <task number from 'list'>`: Mark or unmark a task created as done

  - Example of usage:

	```
    mark 1
    unmark 2
  	```

- `delete <task number from 'list'>`: Delete a task from recorded tasks

  - Example of usage:

    ```
    delete 1
    ```

- `find <Search Key>`: Show all tasks that has descriptions that matches the `Search Key`

  - Example of usage:

    ```
    find books
    ```

- `todo <description>`: Add a new todo task

  - Example of usage:

    ```
    todo Read books
    ```

- `deadline <description> /by <date> <time>`: Add a new deadline task with it's due datetime

  - Example of usage:

	```
    deadline Return book /by 2023-03-01 0815
    ```

- `event <description> /from <date> <time> /to <date> <time>`: Add a new event task with it's start and end datetime

  - Example of usage:

	```
    event project meeting /from 2/12/2023 1800 /to 2023-12-02 2200
  	```

## Other Features

### Feature-C-DetectDuplicates

- Duplicated records are not allowed.
- When creating a task, it will be checked against the existing tasks. If any of the tasks has similar (description/by/from/to) information depending on the task type of task being created, this task will not be created.