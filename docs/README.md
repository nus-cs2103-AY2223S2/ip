# Duke

Duke is your personal chat box that allows you to easily note down and manage your daily task.

## - Features
+ **todo**
+ **deadline**
+ **event**
+ **list**
+ **mark**
+ **unmark**
+ **delete**
+ **check**
+ **find**
+ **sort**
+ **bye**

## - Usage

### `todo`
Add in the task that you are planing to do.

**Format:** `todo + [task]`

**Example of usage:** `todo study CS2103`


---
### `deadline`
Record down a deadline for a task.

**Format:** `deadline + [task] + /by + [date + time]`

**Example of usage:** `deadline return book /by 2019-10-15 18:00`

**Attention:** Date and time must be in the format of **yyyy-MM-dd hh:mm.** Else your date input will be deemed as invalid.

---
### `event`
Record down the start date and end date for an event.

**Format:** `event + [task] + /from + [date + time] + /to + [date + time]`

**Example of usage:** `event project meeting /from 2015-03-17 09:00 /to 2015-05-03 12:00`

**Attention:** Date and time must be in the format of **yyyy-MM-dd hh:mm.** Else your date input will be deemed as invalid.

---
### `list`
Display all the tasks.

**Format:** `list`

**Example of usage:** `list`

**Sample result:**
```
1.[T][] study CS2103
2.[D][] return book (by:Oct 15 2019 06:00pm)
3.[E][] project meeting (from:Mar 17 2015 09:00am to:May 03 2015 12:00pm)
```

---
### `mark`
Mark a task as done.

**Format:** `mark + [task number]`

**Example of usage:** `mark 1`

**Expected result:**
```
1.[T][X] study CS2103
```

---
### `unmark`
Unmark a task as done.

**Format:** `unmark + [task number]`

**Example of usage:** `unmark 1`

**Expected result:**
```
1.[T][] study CS2103
```

---
### `delete`
Delete a task.

**Format:** `delete + [task number]`

**Example of usage:** `delete 1`

---
### `check`
Check if a deadline exist.

**Format:** `Check + deadline + / + [date]`

**Example of usage:** `check deadline /2019-10-15`

**Expected result:**
```
2.[D][] return book (by:Oct 15 2019 06:00pm)
```

**Attention:**
+ Date and time must be in the format of **yyyy-MM-dd hh:mm.** Else your date input will be deemed as invalid.
+ check can only be used to check for deadline.

---
### `find`
Find tasks.

**Format:** `Find + [description]`

**Example of usage:** `Find study`

**Expected result:**
```
1.[T][] study CS2103
```

---
### `sort`
Display deadline or event in a sorted order.

**Format:** `sort + deadline/event`

**Example of usage:** `sort deadline`

**Expected result:**
```
1.[T][] study CS2103
```

**Attention:** sort can only be used to sort deadline or event.

---
### `bye`
Exit from Duke.

**Format:** `bye`

**Example of usage:** `bye`

## - Setting up in Intellij
1. Open Intellij.
2. Open the project in Intellij.
3. Configure the project to use **JDK 11** (for Mac user, use zulu might be preferred). You can refer to [here](https://www.jetbrains.com/help/idea/sdk.html#set-up-jdk).
4. Set the project language level to `SDK default`.
5. To run the project inside Intellij, locate `src/main/java/duke/Launcher.java` file and run `Launcher.main()`.


## - Run .jar
1. Download the released .jar file.
2. Put .jar inside a folder.
3. Right-click on the folder and open the terminal. 
4. After the terminal is opened, key in commend `java -jar myIP.jar`

## Enjoy using Duke!















