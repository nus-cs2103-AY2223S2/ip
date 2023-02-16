# Duke project template

This is a project template for a greenfield Java project. It's named after the Java mascot _Duke_. Given below are instructions on how to use it.

## Setting up in Intellij

Prerequisites: JDK 11, update Intellij to the most recent version.

1. Open Intellij (if you are not in the welcome screen, click `File` > `Close Project` to close the existing project first)
1. Open the project into Intellij as follows:
   1. Click `Open`.
   1. Select the project directory, and click `OK`.
   1. If there are any further prompts, accept the defaults.
1. Configure the project to use **JDK 11** (not other versions) as explained in [here](https://www.jetbrains.com/help/idea/sdk.html#set-up-jdk).<br>
   In the same dialog, set the **Project language level** field to the `SDK default` option.
3. After that, locate the `src/main/java/Duke.java` file, right-click it, and choose `Run Duke.main()` (if the code editor is showing compile errors, try restarting the IDE). If the setup is correct, you should see something like the below as the output:
   ```
   Hello from
    ____        _        
   |  _ \ _   _| | _____ 
   | | | | | | | |/ / _ \
   | |_| | |_| |   <  __/
   |____/ \__,_|_|\_\___|
   ```
# User Guide

Feel free to skip this user guide if you deem it lengthy or
do not have time to read. Duke provides all the guide you need
:wink:

## Commands available

* `todo <description>` Adds a new todo task
   - Example of Usage
     ```
       todo CS2103 Tutorial
     ```
* `deadline <description> /by <dd/MM/yyyy:HHmm>` Adds a new deadline task
   - Example of Usage
     ```
     deadline CS2103 Assignment /by 05/03/2023:2359
     ```
* `event <description> /from <dd/MM/yyyy:HHmm> /to <dd/MM/yyyy:HHmm>` Adds a new event task
   - Example of Usage
     ```
     event CS2103 Exam /from 05/04/2023:1800 /to 05/04/2023:2000
     ```
* `list` List down all the tasks
   - Example of Usage
     ```
     list
     ```
* `mark <index>` Mark a task as done
   - Example of Usage
     ```
     mark 1
     ```
* `delete <index>` Delete a task
   - Example of Usage
     ```
     delete 1
     ```
* `find <name of task>` Find a task
   - Example of Usage
     ```
     find CS2103
     ```
*  `archive` Archive all the existing tasks into a file stored in data
   - Example of Usage
     ```
     archive
     ```
*  `exit` Exit the program
   - Example of Usage
     ```
     exit
     ```



