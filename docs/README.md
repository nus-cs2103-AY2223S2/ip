# About Hachi

### Hachi is a desktop app that helps you organize your tasks 🐝

![IMAGE](tangyuantyy/ip/docs/Ui.png)

## Get started with Hachi
1. Download `hachi-v1.0.jar` from this page
2. Navigate to the directory containing the jar file in terminal
3. Run `java -jar hachi-v1.0.jar`
4. Give Hachi commands to complete your tasks!
5. Say `bye` to Hachi to exit and save your tasks for next time 


## User Guide

## 1. todo

Create a new 'todo'.

Tell Hachi your command in the format:

`todo <task description>`

Example:
```java
Input: 
    todo ip
Output:
    okie dokie. I've added this task:
     [T][ ]ip
    Now you have 1 task in the list.
```
  
## 2. deadline

Create a new deadline task with a date to complete the task by.

Tell Hachi your command in the format:

`deadline <task description> /by <yyyy-MM-dd>`

Example:
```java
Input: 
    deadline ip /by 2023-02-17
Output: 
    okie dokie. I've added this task:
     [D][ ]ip
    Now you have 1 task in the list.
  ```
  
## 3. event 

Create a new event with its occurrence date.
 
Tell Hachi your command in the format:

`event <event description> /at <yyyy-MM-dd>`

Example:
```java
Input: 
    event meeting /from 2023-02-16 /to 2023-02-17
Output: 
    okie dokie. I've added this task:
     [E][ ] meeting (from: 2023-02-16 (to: 2023-02-17 )
    Now you have 1 task in the list.
  ```
  
## 4. list

View all your tasks.

Tell Hachi your command in the format:

`list`

Example:
```java
Input: 
    list
Output: 
    Here are the tasks in your list:
  
        1. [T][ ] ip
        2. [D][ ] ip (by: 2023-02-17)
        3. [E][ ] meeting (from: 2023-02-16 (to: 2023-02-17 )
```

## 5. mark

Update the status of a specific task in your list as completed.

Tell Hachi your command in the format:

`mark <task number>`

Example:
```java
Input: 
    mark 2
Output: 
    good job! I've marked this task as done: 
    [T][X] ip
 ```
 
## 6. unmark

Update the status of a specific task in your list as uncompleted.

Tell Hachi your command in the format:

`unmark <task number>`

Example:
```java
Input: 
    unmark 2
Output: 
    okie dokie. I've marked this task as not done yet: 
    [T][ ] ip
  ```
  
## 7. find

Search for specific tasks in your list by a keyword (partial match supported).

Tell Hachi your command in the format:

`find <keyword>`

Example:
```java
Input:
    find ip
Output:
    Here are the matching tasks in your list:
        1. [T][ ] ip
```

## 8. bye

Exit Hachi.

⚡️ Please only use this command to exit Hachi instead of closing the window, or Hachi will not be able to save your tasks to storage! ⚡️

Tell Hachi your command in the format:

`bye`

![IMAGE](https://i.pinimg.com/originals/dd/3b/b5/dd3bb51121232626d9e3899082bbbadb.jpg)
