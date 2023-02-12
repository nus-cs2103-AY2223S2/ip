# willy Bot - How to use?

For the willy Bot, we can take and save 3 different types of task

Please enter commands into the terminal in the following format.

Task Type Name, Example 

todo -> todo borrow book
Note: "todo" keyword must be infront.

deadline -> deadline return book /by Sunday
Note: "deadline" keyword must be infront and the date must come after the first instance of "/"

event -> event project meeting /from Mon 2pm /to 4pm
Note: "event" keyword must be infront. the from and to must be in said order and come after the first and second instance of "/" in order accordingly.

# Additional commands

mark %d: It will mark the task in the index of the list as done
unmark %d: It will mark the task in the index of the list as undone
delete %d: It will delete the task in the said position in the list
list: to generate the list of Tasks
bye: to close the program


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
