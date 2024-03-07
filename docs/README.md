# User Guide

**Duke** is a **desktop app for managing tasks, optimised for Command Line Interface**(CLI) and Graphical User Interface(GUI).

# Quick Start

1. Ensure you have `Java 11` installed in your computer.

2. Download the latest `duke.jar`.

3. Copy the file to the folder you want to use as the *home folder* for your Duke app.

4. Open a command terminal, load into the folder you put the jar file in, and use the `java -jar duke.jar` command to run the application. A GUI similar to the below should appear in a few seconds. Note the app contains some sample data.


   <img src="http://yitong241.github.io/ip/Ui.png" style="zoom:50%;" />

# Features

## Add a todo**

- Syntax: todo [task name]
- Example: todo sleep

## Add a deadline**

- Syntax: deadline [deadline name] /by [time]
- [time] should be in the format of YYYY-MM-DD HH:MM
- Example: deadline finish homework /by 2023-01-01 2359

## Add an event**

- Syntax: event [event name] /from [startTime] /to [endTime]
- [startTime] and [endTime] should be in the format of YYYY-MM-DD HH:MM
- Example: event SOC Career Fair /from 2020-02-11 1500 /to 2023-02-15 1000


## List all tasks

- Syntax: list

## Mark a task as done

- Syntax: mark [index of task]
- Example: mark 1

## Mark a task as not done

- Syntax: unmark [index of task]
- Example: unmark 1

## Delete a task

- Syntax: delete [index of task]
- Example: delete 2


## Find a task with specific keyword

- Syntax: find [keyword]
- Example: find homework
- Explanation: this retrieves all tasks containing the "homework" keyword



** task with duplicated names will be not added