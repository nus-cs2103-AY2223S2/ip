# Duke project template

> "Your mind is for having ideas, not holding them." - David Allen

Duke frees your mind of having to remember things you need to do. It's 

- text-based
- easy to learn
- SUPER FAST to use

This site was built using [GitHub Pages](https://pages.github.com/).

All you need to do is, 

1. download it from [here](https://nus-cs2103-ay2223s2.github.io/website/schedule/week4/project.html)
2. double-click it.
3. add your tasks.
4. let it manage your tasks for you 👍

And it is **FREE**!

Features:
- [x] Managing tasks
- [X] Managing deadlines
- [ ] Reminders (coming soon)

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
3. After that, locate the `src/main/java/duke.Duke.java` file, right-click it, and choose `Run duke.Duke.main()` (if the code editor is showing compile errors, try restarting the IDE). If the setup is correct, you should see something like the below as the output:
   ```
   Hello from
    ____        _        
   |  _ \ _   _| | _____ 
   | | | | | | | |/ / _ \
   | |_| | |_| |   <  __/
   |____/ \__,_|_|\_\___|
   ```
