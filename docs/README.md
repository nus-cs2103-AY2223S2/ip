# User Guide

Welcome to Duke, your friendly chatbot!<br>
Duke helps you manage your task in a convenient and simple way!

## Features 

### Feature-L

Description of the feature.

### Feature-XYZ

Description of the feature.

## Usage

### `list`

List out all the tasks that you currently have

Example of usage: 
`list`

Expected outcome:
An index of tasks and their indexes. <br>
First letter represents what type of task: E - Event, D - deadline, T- Todo<br>
![image](https://user-images.githubusercontent.com/78403168/218346936-7312bacf-54b6-4783-a1bb-f601b16629b5.png)

### `mark [index]` 

Mark a task as completed.
To get index of task, use the list command above

Example of usage: `mark 2`<br>
Mark task 2 as completed.

Expeted outcome: Prints out the task that you have marked. <br>
The [X] represents that the task is completed

![image](https://user-images.githubusercontent.com/78403168/218347063-d02bd98e-c0d6-4cfc-9a46-c5e84ca75010.png)

### `unmark [index]`

Unmark a task as completed (mark it as uncompleted)
To get index of task, use the list command above

Example of usage: `mark 2`<br>
Mark task 2 as completed.

Expeted outcome:  The task that you have unmarked. <br>
![image](https://user-images.githubusercontent.com/78403168/218347419-7934e840-a539-4698-998e-b6cbf828ea18.png)


### `delete [index]` 

Delete a task
To get index of task, use the list command above

Example of usage: `delete 1` <br>
Delete task 1

Expeted outcome: Prints out deleted task.<br>
![image](https://user-images.githubusercontent.com/78403168/218347487-0b3bdb89-f3f1-451b-bbf1-d32799d283a0.png)

### `deadline [deadline name] /by [yyyy-mm-dd HH:MM]`  

Add a task that is of type deadline

Example of usage: `deadline submit documents /by 2022-12-12 18:00` <br>
Add a task deadline called submit documents that is due 12 December 2022 6pm

Expeted outcome: Prints out deadline that you have just added<br>
The [D] represents that it is of type deadline. <br>
The [ ] represents that the task has not beenn completed.<br>
![image](https://user-images.githubusercontent.com/78403168/218347759-fbd75d55-2f92-450e-9a1f-6def5e7d10dd.png)

### `event [event name] /from [yyyy-mm-dd HH:MM] /to [yyyy-mm-dd HH:MM]` 

Add a task that is of type event

Example of usage:  `event holiday /from 2022-12-12 18:00 /to 2022-12-14 20:00` <br>
Add an event movie that is from 12 December 2022 6pm to 14 December 2022 6pm

Expeted outcome: Prints out event that you have just added<br>
The [E] represents that it is of type event. <br>
The [ ] represents that the task has not beenn completed.<br>
![image](https://user-images.githubusercontent.com/78403168/218348118-4daaa298-eb46-417a-924c-14d34a08046d.png)

### `todo [todo name]` - 

Add a task that is of type todo

Example of usage: `todo sweep floor` <br>
Add a todo sweep floor

Prints out todo that you have just added<br>
The [T] represents that it is of type todo. <br>
The [ ] represents that the task has not beenn completed.<br>
![image](https://user-images.githubusercontent.com/78403168/218348218-6f72f513-c574-4171-8551-8ac846834981.png)

### `find [task name]` - 

Find all tasks whose name contains the inputted task name

Example of usage: 

`find sweep`
Find all tasks that has sweep in their name

Expeted outcome:

![image](https://user-images.githubusercontent.com/78403168/218348353-9ffd81dc-828d-4569-970f-3d2571039158.png)

### `view [yyyy-mm-dd HH:MM]` - 

View deadline and event tasks whose begining or ending date falls on that day

Example of usage: 

`view 2022-12-12 18:00`<br>
Find<br>
i) Deadline whose deadline falls on 12 December 2022 6pm<br>
ii) Event tasks who begins or ends on 12 December 2022 6pm


Expeted outcome:

![image](https://user-images.githubusercontent.com/78403168/218348897-b1eba580-fded-45b3-b09e-851ee5344ba2.png)









