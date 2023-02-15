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
# User guide

You may skip this user guide if you deem it lengthy or do not have time to read it.
Duke itself does provide all the guide you need :wink:
### Available commands :

#### **todo** -  format: todo [task name]
#### **deadline** - format: deadline [task name] /by [dd/MM/yyyy:HHmm]
#### **event** - format: event [task name] /from [dd/MM/yyyy:HHmm] /to [dd/MM/yyyy:HHmm]
#### **list** - format: list
#### **mark** - format: mark [task index]
#### **delete** - format: delete [task index]
#### **find** - format: find [task name]
#### **archive** - format: archive

### Features :
Add a task - todo,deadline,event

List all the tasks - list

Mark the tasks that you have completed - mark

Delete tasks - delete

Find tasks - find

Archive all the current tasks, stores these existing tasks into a file in the directory "data" - archive



