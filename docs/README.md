# User Guide

## Usage

### Commands Available

- `bye`: Exit the program
- `list`: Show all recorded tasks


- `todo <description>`: Add a new todo task

    - Example of usage:

      ```
      todo Read books
      ```

- `deadline <description> /by <date> <time>`: Add a new deadline task with a deadline time

    - Example of usage:

      ```
      deadline submit assignment /by 25/12/2023 1800
      ```

- `event <description> /from <date> <time> /to <date> <time>`: Add a new event task with the start and end datetime

    - Example of usage:

      ```
      event career fair /from 13/02/2023 1500 to 13/02/2023 1700
        ```

- `mark/unmark <task number from 'list'>`: Mark or unmark a task as done

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
- `undo <task number from 'list'>`: undo last operation performed

    - Example of usage:

      ```
      delete 1
      undo
      ```
- `find <keyword>`: Show all tasks that has descriptions that matches the `keyword`

    - Example of usage:

      ```
      find books
      ```
