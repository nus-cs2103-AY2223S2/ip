# User Guide

# Johnny Chatbot.

Welcome to the task management chatbot. Given below are instructions on how to use it.

## Content Page
1. [Setting Up](#1-setting-up-in-intellij)
2. [Features](#2-features)
   1. [Todo](#todo)
   2. [Deadline](#deadline)
   3. [Event](#event)
   4. [Help](#help)
3. [FAQ](#3-faq)
4. [Sample GUI Snapshot](#4-snapshot)

## 1. Setting up in Intellij

Prerequisites: JDK 11, update Intellij to the most recent version.

1. Open Intellij (if you are not in the welcome screen, click `File` > `Close Project` to close the existing project first)
2. Open the project into Intellij as follows:
    1. Click `Open`.
    2. Select the project directory, and click `OK`.
    3. If there are any further prompts, accept the defaults.
3. Configure the project to use **JDK 11** (not other versions) as explained in [here](https://www.jetbrains.com/help/idea/sdk.html#set-up-jdk).<br>
   In the same dialog, set the **Project language level** field to the `SDK default` option.
4. After that, locate the `src/main/java/duke.functions.Main.java` file, right-click it, and choose `Run duke.functions.Main.main()` (if the code editor is showing compile errors, try restarting the IDE). If the setup is correct, you should see something like the below as the output:
```
If it isn't your favourite astronaut lawyer doctor plumber cleaner, Johnny Sins.
Ready to go on a self-exploration adventure?
For more information on the commands available, type help.
```



## 2. Features

## `todo` 


Adds a todo task into your todo list.

Example of usage: 

`todo read`

Expected output:

```
 Got it. I've added this task: read
 You now have 1 task left.
 ```

## `deadline` 
Adds a deadline task into your todo list.

Example of usage:

`deadline finish homework /by 31/12/23 1200`


Expected output:

```
 Got it. I've added this task: finish homework 31/12/23 1200
 You now have 2 tasks left.
 ```

## `event`
Adds a event task into your todo list.

Example of usage:

`event party /from 31/12/22 1200 /to 01/01/23 2359.`


Expected output:

```
 Got it. I've added this task: party from 31/12/23 1200 to 01/01/23 2359.
 You now have 3 tasks left.
 ```

## `help`
Displays a help page with all commands available. 

## 3. `FAQ`
**Q**: How can I transfer data from one computer to another?

**A**: The data is stored within the `duke.txt` file under `/data`. 
Just export it to the other device and you're good to go!

## 4. `Snapshot`
All done properly, it should look like this.
![Display](Ui.png)