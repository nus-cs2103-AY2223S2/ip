# Johnny Chatbot. 

Welcome to the task management chatbot. Given below are instructions on how to use it.

## Setting up in Intellij IDEA

Prerequisites: JDK 11, update Intellij to the most recent version.

1. Open Intellij (if you are not in the welcome screen, click `File` > `Close Project` to close the existing project first)
1. Open the project into Intellij as follows:
   1. Click `Open`.
   1. Select the project directory, and click `OK`.
   1. If there are any further prompts, accept the defaults.
1. Configure the project to use **JDK 11** (not other versions) as explained in [here](https://www.jetbrains.com/help/idea/sdk.html#set-up-jdk).<br>
   In the same dialog, set the **Project language level** field to the `SDK default` option.
3. After that, locate the `src/main/java/duke.functions.Main.java` file, right-click it, and choose `Run duke.functions.Main.main()` (if the code editor is showing compile errors, try restarting the IDE). If the setup is correct, you should see something like the below as the output:
```
If it isn't your favourite astronaut lawyer doctor plumber cleaner, Johnny Sins.
Ready to go on a self-exploration adventure?
For more information on the commands available, type help.
```
