# User Guide

<img align="left" width="100" height="100" src="https://raw.githubusercontent.com/jamieeeleow/ip/master/src/main/resources/images/lion.jpeg">

## **Welcome to _Leo_.**  
Leo is an application used to manage your tasks!   

## Feature Summary   
- [Adding Tasks](https://github.com/jamieeeleow/ip/edit/master/docs/README.md#adding-tasks)
  - ToDo Tasks: ```todo```
  - Deadline Tasks: ```deadline```
  - Event Tasks: ```event```
- [Deleting Tasks](https://github.com/jamieeeleow/ip/edit/master/docs/README.md#deleting-tasks-delete): ```delete```
- [Exiting Program](https://github.com/jamieeeleow/ip/edit/master/docs/README.md#exiting-the-application-bye): ```bye```
- [Finding Tasks](https://github.com/jamieeeleow/ip/edit/master/docs/README.md#finding-tasks-find): ```find```
- [Listing Tasks](https://github.com/jamieeeleow/ip/edit/master/docs/README.md#listing-tasks-list): ```list```
- [Marking Tasks](https://github.com/jamieeeleow/ip/edit/master/docs/README.md#marking-tasks-mark): ```mark```
- [Unmarking Tasks](https://github.com/jamieeeleow/ip/edit/master/docs/README.md#unmarking-tasks-unmark): ```unmark```
- [Viewing Tasks](https://github.com/jamieeeleow/ip/edit/master/docs/README.md#viewing-tasks-view): ```view```  

## Features

### Adding Tasks    
1.  #### Add ToDo Tasks: ```todo```  
Add a ToDo task that you have to complete using the ```todo``` keyword.  
   
Example: ```todo run``` will add run to your list of tasks  

2. #### Add Deadline Tasks: ```deadline```  
Add a Deadline Task that you have to complete by a certain deadline using the ```deadline``` keyword.  
Deadline will be entered behind a slash, with the format of ***'ddMMyyyy HH:mm'***.  
   
Example: ```deadline project /23022023 14:00``` will add project to your list of tasks  

3. #### Add Event Tasks: ```event```
Add an Event that you have to attend using the ```event``` keyword.  
Duration of the event will be entered with behind a slash, with the first slash representing the start of the event and the second slash representing the end of the event.  
Duration format for the start and end of the event will follow ***'ddMMyyyy HH:mm'***.  
   
Example: ```event CS2103T Lecture /17022023 14:00 /17022023 16:00``` will add CS2103T Lecture to your list of tasks  

<img align="center" width="500" height="500" src="https://raw.githubusercontent.com/jamieeeleow/ip/master/images/add-tasks.png">

***  

### Deleting Tasks: ```delete```  

Delete a task in the tasklist using the ```delete``` keyword.  
A positive integer (ie. 1, 2, 3...) must be specified behind the keyword and the task to be deleted should exist.  
    
Example: ```delete 2``` will delete the second item in the list

<img align="center" width="500" height="300" src="https://raw.githubusercontent.com/jamieeeleow/ip/master/images/delete.png">
    
***    

### Exiting the Application: ```bye```  
    
Simply by using the keyword ```bye```, the program will exit on its own.  

Example: ```bye``` will exit the program  
   
***

### Finding Tasks: ```find```  

Find a task in the list using the ```find``` keyword.  
The task you are looking for should be entered behind the keyword.  

Example: ```find project``` will list out all the tasks that contains the word 'project'   

<img align="center" width="500" height="150" src="https://raw.githubusercontent.com/jamieeeleow/ip/master/images/find.png">

***

### Listing Tasks: ```list```

List out all the tasks using the ```list``` keyword.  

Example: ```list``` will list out all the tasks that exist   

<img align="center" width="500" height="280" src="https://raw.githubusercontent.com/jamieeeleow/ip/master/images/list.png">

***  

### Marking Tasks: ```mark```  

Mark a task that has been completed using the ```mark``` keyword.   
A positive integer (ie 1, 2, 3...) should be specified behind the keyword.   

Note: Task to be marked should exist and should not already be marked.   

Example: ```mark 3``` will mark the third task on the list  

<img align="center" width="500" height="130" src="https://raw.githubusercontent.com/jamieeeleow/ip/master/images/mark.png">

***

### Unmarking Tasks: ```unmark```  

Unmark a task that is not completed but accidentally marked as completed using the ```unmark``` keyword.  
A positive integer (ie 1, 2, 3...) should be specified behind the keyword.   

Note: Task to be unmarked should exist and should already be marked.   

Example: ```unmark 3``` will unmark the third task on the list   

<img align="center" width="500" height="130" src="https://raw.githubusercontent.com/jamieeeleow/ip/master/images/unmark.png">

***

### Viewing Tasks: ```view```   

View the tasks on a certain date using the ```view``` keyword. 
A valid date of format ***'ddMMyyyy'*** should be entered behind the keyword to view the schedule of that day.   

Example: ```view 20022023``` will show the tasks involving Feb 20, 2023  

<img align="center" width="500" height="150" src="https://raw.githubusercontent.com/jamieeeleow/ip/master/images/view.png">
