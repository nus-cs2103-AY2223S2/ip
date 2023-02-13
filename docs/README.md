# User Guide
<p align="center">
  <img src="https://github.com/zrei/ip/blob/master/src/main/resources/images/Colette_Happy.gif?raw=true">
</p>
Ever felt like you couldn't get a handle on *all* the things that you have to do? Not to worry, for Colette is here to help you! (And talk to you about dogs.)

* [Get Started](#get-started)
* [Features](#features)
* [FAQ](#faq)
* [Planned features](#planned-features)

---

## Get Started
1. Ensure you have `java 11` installed on your computer.
2. Download the latest `colette.jar` file from [here](https://github.com/zrei/ip/releases/tag/A-Release).
3. Copy the jar file to the folder you want to use as the home folder for the application, e.g. `Desktop`. 
4. Open a command line window, e.g. Terminal for MacOS or Command Prompt for Windows.
5. `cd` into the folder you placed the jar file in, e.g. `cd ~/Desktop`.
6. Start the application with the command `java -jar colette.jar`.
7. You should see the window below.

<p align="center">
  <img src="https://github.com/zrei/ip/blob/master/docs/Startup.png?raw=true" width="200" height="300"><br>
</p>

---

## Features

A broad overview of what Colette can do for you:
* [Viewing help](#viewing-help)
* [Listing all tasks](#listing-all-tasks)
* [Adding a task](#adding-a-task)
  * [Adding a todo](#adding-a-todo)
  * [Adding a deadline](#adding-a-deadline)
  * [Adding an event](#adding-an-event)
* [Marking a task as done or not done](#marking-a-task-as-done-or-not-done)
  * [Marking a task as done](#marking-a-task-as-done)
  * [Marking a task as not done](#marking-a-task-as-not-done)
* [Deleting a task](#deleting-a-task)
* [Finding tasks](#finding-tasks)
* [Exiting the app](#exiting-the-app)

### **Viewing help**
Brings up a list of all the commands that Colette knows.

Format: ```help```

Expected result:
<p align="center">
  <img src="https://github.com/zrei/ip/blob/master/docs/helpCommand.png?raw=true" width="150" height="200s"><br>
  <img src="https://github.com/zrei/ip/blob/master/docs/helpWindow.png?raw=true" width="200" height="300"><br>
</p>

### **Listing all tasks**
Lists all tasks that are currently in the task list.

Format: ```list```

Expected result:
<p align="center">
  <img src="https://github.com/zrei/ip/blob/master/docs/list.png?raw=true" width="200" height="300"><br>
</p>

### **Adding a task**
You can add three types of tasks: [todos](#adding-a-todo), [deadlines](#adding-a-deadline), and [events](#adding-an-event).

#### Adding a todo
Todos are tasks with a name/description. 

Format: ```todo [name/description]```

Expected result:
<p align="center">
  <img src="https://github.com/zrei/ip/blob/master/docs/todo.png?raw=true" width="200" height="300"><br>
</p>

#### Adding a deadline
Deadlines are tasks with a name/description and a date to do the task by.

Format: ```deadline [description] [date to do the task by]```

Expected result:
<p align="center">
  <img src="https://github.com/zrei/ip/blob/master/docs/deadline.png?raw=true" width="200" height="300"><br>
</p>

#### Adding an event
Events are tasks with a name/description, a start date, and an end date.

Format: ```event [description] [start date] [end date]```

Expected result:
<p align="center">
  <img src="https://github.com/zrei/ip/blob/master/docs/event.png?raw=true" width="200" height="300"><br>
</p>

### **Marking a task as done or not done**
Tasks can be marked as done to indicate they are completed, or not done to indicate that they have not been completed. This information will show up in the list. 

#### Marking a task as done
Format: ```mark [index]``` where index is the index of the task in the list.

Expected result:
<p align="center">
  <img src="https://github.com/zrei/ip/blob/master/docs/mark.png?raw=true" width="200" height="300"><br>
</p>

#### Marking a task as not done
Format: ```unmark [index]``` where index is the index of the task in the list. 

Expected result:
<p align="center">
  <img src="https://github.com/zrei/ip/blob/master/docs/unmark.png?raw=true" width="200" height="300"><br>
</p>

### **Deleting a task**
Format: ```delete [index]``` where index is the index of the task in the list.

Expected result:
<p align="center">
  <img src="https://github.com/zrei/ip/blob/master/docs/delete.png?raw=true" width="200" height="300"><br>
</p>

### **Finding tasks**
You can find a task using any number of keywords. Colette will search for any task in the list whose name/description matches any of the keywords provided.

Format: ```find [keyword1] [keyword2]``` where you can provide any number of keywords, separated by spaces.

Example: ```find pet dogs``` will find all tasks with either `pet` or `dogs` in the name/description.

Expected result:
<p align="center">
  <img src="https://github.com/zrei/ip/blob/master/docs/delete.png?raw=true" width="200" height="300"><br>
</p>

### **Exiting the app**
Format: ```bye```

Expected result:
<p align="center">
  <img src="https://github.com/zrei/ip/blob/master/docs/bye.png?raw=true" width="200" height="300"><br>
</p>

### **Remembering your tasks between sessions**
Colette can remember the state of your task list between sessions!
Simply ensure that if you move the `colette.jar` file, the `data` folder remains in the same directory
as the jar file.

---

## FAQ

### **Colette can't read my dates**
You've likely provided a date in the wrong format. Colette can only read dates in the format `YYYY-MM-DD`.

### **Colette keeps saying the index is too high**
There aren’t that many tasks in your list. You can check
the tasks in your list with the [list command](#listing-all-tasks).

---

## Planned Features
- [ ]  Ability to undo the previous feature
- [ ]  Read times
- [ ]  Read dates in different format