# :sparkles: Saturday User Guide :sparkles:

## :trophy: Features 

### Task Creation

Saturday allows users to create 3 different types of tasks:
* Todos
* Deadlines
* Events

Each task created has a description and a due date if the task is a Deadline or
a start and end date and time if the task is an Event.

### Task Listing

Saturday can list all tasks that are currently saved in the user's list of tasks.

### Task Completion

Users can mark or unmark tasks as complete.

### Search

Users can search for tasks with keyword search.

### Minimalist Ui

Our Ui design approach emphasizes simplicity and functionality over unnecessary decoration
or visual elements. It features a clean and uncluttered layout, with only the essential elements
to improve usability and user experience as it reduces cognitive load and eliminates distractions.

## :technologist: Usage

### :wave: `hello` - Say hello to Saturday

The `hello` command serves as an initializer for Saturday.

This is the **first command** that should be given on every startup. It will request Saturday to attempt
to fetch the saved list of tasks from the previous session, if any.

Example of usage: 

`hello`

Expected outcome:

Saturday will greet the user with a welcome message and state if a save file was successfully loaded.
Here's an example of Saturday's response to a new user:

```
Hi! I'm Sunday, pleasure to meet you!
How can I help?
It appears we haven't met!
Start typing away your tasks and I'll note them down accordingly :)
```

### :page_with_curl: `list` - View your list of tasks

The `list` command alerts Saturday to display your current list of tasks.

Example of usage:

`list`

Expected outcome:

Each task is prefixed with 2 fields, the first to denote the task type,
the second to denote the completion status of the task.

```
Here's everything I've noted down for you:
1. [D][] Complete CS2103 Individual Project Week 2 (by: Fri, Jan 20 2023, 11:59 PM)
2. [T][X] Finalise DJ setlist
3. [T][] Buy snacks
4. [E][X] DJ gig at Cherry Discotheque (from: Thu, Jan 21 2023, 10:00 PM to: Sat, Jan 21 2023, 4:00 AM)
5. [T][] Check email for updates on NOC
```

### :alarm_clock: `deadline` - Create a Deadline task

Use the `deadline` command to create a new Deadline task.

The due date must be given in the format of _"dd/MM/yyyy HHmm"_

Example of usage:

`deadline (description) /by (due date)`

Expected outcome:

Saturday will create and show the created Deadline task along with the number of
uncompleted tasks currently in the list of tasks.

The due date is shown in the format _"E, MMM dd yyyy, h:mm aa"_

```
Got it. I've added this task:
  [D][] Complete CS2103 Individual Project Week 2 (by: Fri, Jan 20 2023, 11:59 PM)
Now you have 1 task(s) in the list.
```

### :tickets: `event` - Create an Event task

Use the `event` command to create a new Event task.

The event start and end dates must be given in the format of _"dd/MM/yyyy HHmm"_

Example of usage:

`event (description) /from (start) /to (end)`

Expected outcome:

Saturday will create and show the created Event task along with the number of
uncompleted tasks currently in the list of tasks.

The event start and end dates are shown in the format _"E, MMM dd yyyy, h:mm aa"_

```
Got it. I've added this task:
  [E][] DJ gig at Cherry Discotheque (from: Thu, Jan 21 2023, 10:00 PM to: Sat, Jan 21 2023, 4:00 AM)
Now you have 1 task(s) in the list.
```

### :memo: `todo` - Create a Todo task

Use the `todo` command to create a new Todo task.

Example of usage:

`todo (description)`

Expected outcome:

Saturday will create and show the created Todo task along with the number of
uncompleted tasks currently in the list of tasks.

```
Got it. I've added this task:
  [T][] Buy snacks
Now you have 1 task(s) in the list.
```

### :white_check_mark: `mark` - Mark a task as complete

Use the `mark` command to mark a task in the list as complete.

Example of usage:

`mark (task number)`

Expected outcome:

Saturday will mark and show the completed task along with the number of
uncompleted tasks currently in the list of tasks.

```
Well Done! I've marked this task as done:
  [T][X] Buy snacks
Now you have 0 task(s) in the list.
```

### :negative_squared_cross_mark: `unmark` - Unmark a task as complete

Use the `unmark` command to mark a task in the list as in progress.

Example of usage:

`unmark (task number)`

Expected outcome:

Saturday will unmark and show the in progress task along with the number of
uncompleted tasks currently in the list of tasks.

```
OK, I've marked this task as not done yet:
  [T][] Buy snacks
Now you have 1 task(s) in the list.
```

### :no_good: `delete` - Delete a task from the list

Use the `delete` command to remove a task from the list.

Example of usage:

`delete (task number)`

Expected outcome:

Saturday will delete and show the deleted task along with the number of
uncompleted tasks currently in the list of tasks.

```
Noted. I've removed this task:
  [T][] Buy snacks
Now you have 0 task(s) in the list.
```

### :detective: `find` - Search with a keyword

Use the `find` command to search for tasks that contain a certain keyword.

Example of usage:

`find (keyword)`

Expected outcome:

Saturday will search and show a list of tasks that contains the specified keyword
along with the number of uncompleted tasks currently in the list of tasks.

```
Here are the task(s) I've found:
1. [E][X] DJ gig at Cherry Discotheque (from: Thu, Jan 21 2023, 10:00 PM to: Sat, Jan 21 2023, 4:00 AM)
2. [T][X] Finalise DJ setlist
```

### :magic_wand: `sort` - Sort the list of tasks

Use the `sort` command to sort the tasks in alphabetical order.

Example of usage:

`sort`

Expected outcome:

Saturday will sort the tasks in the list in alphabetical order of their descriptions and list them.

```
Here's everything I've noted down for you:
1. [T][] Buy snacks
2. [T][] Check email for updates on NOC
3. [D][] Complete CS2103 Individual Project Week 2 (by: Fri, Jan 20 2023, 11:59 PM)
4. [E][X] DJ gig at Cherry Discotheque (from: Thu, Jan 21 2023, 10:00 PM to: Sat, Jan 21 2023, 4:00 AM)
5. [T][X] Finalise DJ setlist
```

### :wave: `bye` - Say goodbye to Saturday

The `bye` command to serves as the terminator for Saturday.

This is the **final command** that will request Saturday to save the current list of tasks and close itself.

Example of usage:

`bye`

Expected outcome:

Saturday will save the current list of tasks and close itself.

```
Okay, I've save your list for the next session!
Goodbye and have a pleasant day!
```
