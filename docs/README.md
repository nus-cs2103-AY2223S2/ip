### Duke

**Duke frees your mind of having to remember things you need to do. It's,**

- text-based
- easy to learn
- interactive

**All you need to do is,**

1. download it from [here](https://nus-cs2103-ay2223s2.github.io/website/schedule/week4/project.html](https://github.com/Nicklelodeon/ip)).
2. open the JAR file 
3. interact with Duke to add and manage tasks
4. And it is FREE!

**Features:**

Things to take note of: 
- commands should only be in lower case
- words that are fully capitalised (e.g INDEX) refer to variable names
- all DATE variables must be formatted as yyyy-mm-dd

**List of Commands** <br>
1. **bye** <br>
Guides user on how to exit the chatbot

2. **list** <br>
Displays a list of current tasks stored in Duke

3. **mark INDEX** <br>
Sets the task at the specified INDEX as completed <br>
<ins>Format</ins>
    - INDEX refers to the task number, must be a valid positive integer greater than 0 and less than size of current task list

4. **unmark INDEX** <br>
Sets the task at specified INDEX as uncompleted <br>
<ins>Format</ins>
    - INDEX refers to the task number, must be a valid positive integer greater than 0 and less than size of current task list

5. **todo DESCRIPTION** <br>
Creates a ToDo task with the specified DESCRIPTION <br>
<ins>Format</ins>
    - DESCRIPTION refers to the details of the task

6. **deadline DESCRIPTION /by DATE** <br>
Creates a Deadline task with the specified DESCRIPTION and DEADLINE <br>
<ins>Format</ins>
    - DESCRIPTION refers to the details of the task
    - DATE refers to the deadline when the task is due, must be in yyyy-mm-dd format

7. **event DESCRIPTION /from STARTDATE /to ENDDATE** <br>
Creates an Event task with the specified DESCRIPTION, STARTDATE and ENDDATE <br>
<ins>Format</ins>
    - DESCRIPTION refers to the details of the task
    - STARTDATE refers to the starting date of the event, must be in yyyy-mm-dd format
    - ENDDATE refers to the ending date of the event, must be in yyyy-mm-dd format
    - STARTDATE cannot be later than ENDDATE
    
8. **reschedule INDEX VARARGS** <br>
Reschedules a task to a later date. Task must be of type Deadline or Event <br>
<ins>Format</ins>
    - INDEX refers to the task number, must be a valid positive integer greater than 0 and less than size of current task list
    - VARARGS refer to variable arguments. 
    - For Deadline, VARARGS = /by DATE (e.g Reschedule 2 /by 2022-01-01), DATE must be in yyyy-mm-dd format
    - For Event, VARARGS = /from STARTDATE /to ENDDATE (e.g reschedule 3 /from 2022-01-01 /to 2023-02-02), STARTDATE and ENDDATE must be in yyyy-mm-dd format
