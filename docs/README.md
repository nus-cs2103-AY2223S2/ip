# User Guide

Duke is a desktop app that reminds you of all the tasks that you have to carry out!!

## Features 
1. Adding tasks
   - Todo
   - Deadline
   - Event
2. Viewing tasks
3. Editing task 
4. Locating task
5. Deleting task 
6. Marking task 
7. Unmarking task 
8. Exiting the program 

### Adding a task
Adds a task to Duke

Format: ```{task} {description} {startDate} {endDate}```

- task: either todo deadline/event
- description: description of the task
- startDate: start date of the task
- endDate: end date of the task

*{startDate} and {endDate} is optional for ToDo task.*

*{startDate} is required and {endDate} is optional for Deadline task.*

*{startDate} and {endDate} is required for Event task.*

### Viewing task
View all task stored in Duke

Format: ```list```

### Editing task
Updating an existing task in Duke

Format: ```update {index} {description} {startDate} {endDate}```

- index: index of the task 
- description: description of the task
- startDate: start date of the task
- endDate: end date of the task

*{startDate} and {endDate} is optional for ToDo task.*

*{startDate} is required and {endDate} is optional for Deadline task.*

*{startDate} and {endDate} is required for Event task.*

### Locating task
Finds any task that has a description that matches given keyword

Format: ```find {keyword}```

- keyword: keyword to be located in task's description


### Delete task
Delete task from Duke

Format: ```delete {index} ```

- index: index of the task 

### Mark task
Mark an existing task in Duke as completed

Format: ```mark {index}```

- index: index of the task 

### Unmark task
Unmark an existing task in Duke as incomplete

Format: ```Unmark {index}```

- index: index of the task 

### Killing the program
Terminating Duke

Format: ```Bye```




