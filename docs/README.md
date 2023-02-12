# ![sun](https://user-images.githubusercontent.com/97398371/218306096-d9eff358-7a50-4a34-a442-747fbd423ad7.png) **XyDuke's User Guide** ![sun](https://user-images.githubusercontent.com/97398371/218306096-d9eff358-7a50-4a34-a442-747fbd423ad7.png)

## Features 
XyDuke is equipped with 4 main features to enhance your task-tracking experience! :notebook_with_decorative_cover:

**TRY NOW** to become a **MASTER** at tracking commitments!

Please find details on what they are and how they can be used below.

### 1. Tracking Tasks :pencil:

The most basic but also the most useful feature. Add and delete tasks as you wish! Customise your task descriptions! Add deadlines to your tasks!

### 2. Marking Tasks :pencil2:

Too many commitments and can't remember which you have completed? Fret not as XyDuke is equipped with a marking feature that allows you to mark tasks as *complete* or *incomplete*. Don't worry if you marked wrongly, you can change it just as easily!

### 3. Listing Tasks :pen:

This is probably the best feature of XyDuke! List your tasks in the order in which you they were added, or sort them by order of their deadlines! You'll never have to worry about missing an assignment deadline again!

### 4. Saving Tasks 	:fountain_pen:

XyDuke's final feature is the ability to save your tasks. XyDuke automatically saves all changes you've made in a particular session when you bid it farewell, and reflects these changes the next time you boot it up!

## Usage
Here are XyDuke's commands. These commands will enable you to use and explore all of XyDuke's features!

### `todo` - Adds a ToDo task.

A ToDo task is the most basic task type. It is simply as tasks that needs to be done! Use command `todo` to add a ToDo task.

Example of usage: 

`todo This is a ToDo task.`

Expected outcome:

XyDuke will notify you when your ToDo task has been added successfully!

![image](https://user-images.githubusercontent.com/97398371/218305082-c044d146-6877-4afb-81a5-b3b630454fd4.png)


### `deadline` - Adds a Deadline task.

A Deadline task, as the name suggests, must be completed before a deadline. Use command `deadline` to add a task description, and set a deadline for a deadline task.

Example of usage: 

`deadline This is a Deadline task. /by 2023-12-14 0800`

Expected outcome:

XyDuke will notify you when your Deadline task has been added successfully!

![image](https://user-images.githubusercontent.com/97398371/218305122-36493a24-0c65-4fcf-8a01-ca0c235240bb.png)


### `event` - Adds an Event task.

Event tasks are less like a task, but more like an event. Event's have a starting and ending time. User command `event` to add a task description, and set start and end times for your event.

Example of usage: 

`event This is an Event to attend. /from 2023-02-14 0800 /to 2023-02-14 1000`

Expected outcome:

XyDuke will notify you when your Event has been added successfully!

![image](https://user-images.githubusercontent.com/97398371/218305276-95277820-650a-4ac3-8080-23c4ef059ae5.png)


### `mark` and `unmark` - Mark tasks as complete or incomplete.

Tasks can be marked as complete or incomplete. Use command `mark` and `unmark` to mark or unmark a task respectively. Use the Task ID to identify a task to mark. Task ID's can be found at the end of a task description, tagged with a `#`.

Example of usage: 

`mark 2`

Expected outcome:

XyDuke will notify you when Task #2 has been marked as complete!

![image](https://user-images.githubusercontent.com/97398371/218305436-dadd5472-b60a-46ea-b2ce-a0a84164bdf6.png)


### `list` and `orderedlist` - Lists all tasks that you have recorded.

Show all your existing tasks. Use command `list` to show your tasks in the order you've added them. Use variant command `orderedlist` to show your tasks in the order of *deadline*.

Note: ToDo tasks do not have deadlines, an Event's *deadline* is the starting time of that Event.

Example of usage: 

`orderedlist`

Expected outcome:

XyDuke will list out your tasks in order of *deadline*.

![image](https://user-images.githubusercontent.com/97398371/218305580-79b09ad2-0c92-49b9-9a08-ec3f91e5e2dd.png)


### `delete` - Deletes a task.

Tasks you've previously added can be deleted. Use command `delete` to delete an existing tasks from record. Specify the task to delete using Task ID. Task ID's can be found at the end of a task description, tagged with a `#`.

Example of usage: 

`delete 2`

Expected outcome:

XyDuke will notify you when Task #2 has been deleted!

![image](https://user-images.githubusercontent.com/97398371/218305729-745ae69a-33db-4ccd-a7b7-6435d1f10fb4.png)


### `bye` - Bid farewell to XyDuke. :cry:

Say goodbye to XyDuke! This command will also save all changes you've made to your tasks in this session. Remember to use command `bye` or your changes will not be saved! Yes, XyDuke values manners very much! :grin:

Example of usage: 

`bye`

Expected outcome:

XyDuke will notify you when your tasks have been successfully saved! The GUI Window will close. 


## Enjoy!

Thank you for using XyDuke's user guide! We wish you all the best in your task-tracking journey with XyDuke! 

If you have any feedback for XyDuke, or come across any bugs :lady_beetle: when using it, please let me know via my Telegram handle: @XylusChen.

Thank you so much for your support! Have a great day!
