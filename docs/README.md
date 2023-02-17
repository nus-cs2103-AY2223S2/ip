# User Guide
## Duke
Duke is a todo Command Line Interface (CLI) app that helps you manage your todos, deadlines 
and events while still having the benefits of a Graphical User Interface (GUI).

### Features 
* list
* mark
* unmark
* todo
* deadline
* event
* delete
* find
* help
* bye

####Things to take note about the format:
Words in `<>` are parameters that **must be supplied** by the user.
e.g. mark <task number>, the user must key in a task number when using mark commands.

Adding **extra parameters** for list and bye commands will be **ignored**.
e.g. Entering list 123 will be interpreted as list.

Commands supplied must be in **lowercase**.

### Usage

#### list - Lists all the tasks added

###### Examples:
`list`

###### Expected outcome:
Displays the list of all the tasks added

```
Here are the tasks in your list:
1.[T][ ] ip
2.[D][ ] ip (by:Feb-17-2023 23:59)
```

#### mark - Marks the selected task

###### Format:
`mark <task number>`

###### Examples:
`mark 2`

###### Expected outcome:
Shows that task 2 has been marked

```
Nice! I've marked this task as done:
[D][X] ip (by:Feb-17-2023 23:59)
```

#### unmark - Unmark the selected task

###### Format:
`unmark <task number>`

###### Examples:
`unmark 2`

###### Expected outcome:
Shows that task 2 has been unmarked

```
OK, I've marked this task as not done yet:
[D][ ] ip (by:Feb-17-2023 23:59)
```

#### todo - Creates a todo task

###### Format:
`todo <task desc>`

###### Examples:
`todo tp`

###### Expected outcome:
Shows that the tp todo task has been added

```
Got it. I've added this task:
[T][ ] tp
Now you have 3 tasks in the list.
```

#### deadline - Creates a deadline task

###### Format:
`deadline <deadline desc> /by <yyyy-MM-dd>T<HH:mm>`

###### Examples:
`deadline cs2106 lab /by 2023-03-05T23:59`

###### Expected outcome:
Shows that the deadline for cs2106 lab has been added

```
Got it. I've added this task:
[D][ ] cs2106 lab (by: Mar-5-2023 23:59)
Now you have 4 tasks in the list.
```

#### event - Creates an event

###### Format:
`event <event desc> /from <yyyy-MM-dd>T<HH:mm> /to <yyyy-MM-dd>T<HH:mm>`

###### Examples:
`event career fest /from 2023-02-07T09:00 /to 2023-02-09T17:00`

###### Expected outcome:
Shows that the career fest has been added

```
Got it. I've added this task:
[E][ ] career fest (from: Feb-7-2023 09:00 to: Feb-9-2023 17:00)
Now you have 5 tasks in the list.
```

#### delete - Deletes the selected task

###### Format:
`delete <task number>`

###### Examples:
`delete 1`

###### Expected outcome:
Shows that the task with id 1 has been deleted

```
Noted. I've removed this task:
[T][ ] ip
Now you have 4 tasks in the list.
```

#### find - Filters the list according to the keyword

###### format:
`find <keyword>`

###### Examples:

`find lab`

###### Expected outcome:
Shows the filtered list

```
Here are the matching tasks in your list:
[D][ ] cs2106 lab (by: Mar-5-2023 23:59)
```

#### help - Provides help with the selected command

###### format:
`help <command>`

###### Examples:
`help mark`

###### Expected outcome:
Shows the format to use the mark command

```
Enter: mark <task number>
```

#### bye - Terminates the program

###### Examples:
`bye`

###### Expected outcome:
Closes the app
