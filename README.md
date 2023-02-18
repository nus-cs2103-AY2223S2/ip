# Kuromi クロミ

Kuromi クロミ is a desktop application that acts as a personal assistant chatbot to keep track of your tasks, optimized for use via a Command Line Interface (CLI) while still having the benefits of a Graphical User Interface (GUI). The character Kuromi クロミ (the bot) is taken from a Sanrio Character, and the user will play the role as Kuromi クロミ’s rival, My Melody マイメロディ.

## Main Features

### Manage your tasks

Kuromi **keeps track** of your tasks by allowing you to `add`, `view`, `delete`, `mark`, and `unmark` by typing specific commands through the application. Kuromi is talkative and will give comments to your commands! :D

### Get upcoming deadlines

Kuromi can **remind** you of your upcoming deadlines by sending the command `remind`. You can set the number of upcoming tasks that should be shown.

### Find keyword

To easily search for specific tasks, Kuromi is able to find the tasks which include a specific keyword.

### Autosave

Data loss? Fret not! The tasks are autosaved everytime you send a command.

### Get My Melody's Mistakes

From this [source](https://hellokitty.fandom.com/wiki/Kuromi#With_Friends_and_Family), Kuromi has a notebook `Kuromi Note` that **lists all the mistakes** that My Melody made to Kuromi. You can get ~~My Melody's~~ your mistakes by specifying the command `mistakes`.

## Setting up in Intellij

Prerequisites: JDK 11, update Intellij to the most recent version.

1. Open Intellij (if you are not in the welcome screen, click `File` > `Close Project` to close the existing project first)
1. Open the project into Intellij as follows:
   1. Click `Open`.
   1. Select the project directory, and click `OK`.
   1. If there are any further prompts, accept the defaults.
1. Configure the project to use **JDK 11** (not other versions) as explained in [here](https://www.jetbrains.com/help/idea/sdk.html#set-up-jdk).<br>
   In the same dialog, set the **Project language level** field to the `SDK default` option.
3. After that, locate the `src/main/java/kuromi/gui/Launcher.java` file, right-click it, and choose `Run Launcher.main()` (if the code editor is showing compile errors, try restarting the IDE). If the setup is correct, you should see something like the below as the output:\
   <br>
   <img width="453" alt="Screenshot_20230214_114305" src="https://user-images.githubusercontent.com/63656207/219590581-61adcc12-534f-426e-9090-9bddfb21d7e9.png">
