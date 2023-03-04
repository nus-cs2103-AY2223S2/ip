# BorzAI User Guide

<div style="text-align:center">
   <img width="590" alt="Ui" src="https://user-images.githubusercontent.com/97421565/220763976-1de030d8-524c-47a3-a534-2852a775d466.png">
</div>

BorzAI is a web-based chatbot that can help manage your todos, deadline and events in a tasklist. All you have to do is enter commands to tell it what to do and it will help you manage your tasklist accordingly. With this, you can focus on other things without forgetting what you have to do.

- [Quick Start](#quick-start)
- [Commands](#commands)
  - [Adding a todo: `todo`](#adding-a-todo--todo)
  - [Adding a deadline: `deadline`](#adding-a-deadline--deadline)
  - [Adding a event: `event`](#adding-a-event--event)
  - [Listing all tasks: `list`](#listing-all-tasks--list)
  - [Marking a task: `mark`](#marking-a-task--mark)
  - [Unmarking a task: `unmark`](#unmarking-a-task--unmark)
  - [Deleting a task: `delete`](#deleting-a-task--delete)
  - [Filtering by description: `filter`](#filtering-by-description--filter)
  - [Filtering by date: `filterdate`](#filtering-by-date--filterdate)
  - [Sorting by alphabet: `sort`](#sorting-by-alphabet--sort)
  - [Sorting by date: `sortdate`](#sorting-by-date--sortdate)
  - [Sorting by task type: `sorttask`](#sorting-by-task-type--sorttask)
  - [Sorting by task status: `sortdone`](#sorting-by-task-status--sortdone)
  - [Saving the data](#saving-the-data)
- [Command Summary](#command-summary)

## Quick Start
1. Download the latest version from [here](https://github.com/linustws/ip/releases).
2. Copy the jar file into an empty folder.
3. Open a command window in that folder.
4. Run the command `java -jar BorzAi-{version}.jar` e.g., `java -jar BorzAI-v1.0.jar` (i.e., run the command in the same folder as the jar file).
5. Let BorzAI handle your tasks for you!

## Commands
### Adding a todo: `todo`
Adds a todo to the tasklist.

Format: `todo <description>`

Examples:
- `todo cut hair`
- `todo buy gift`

### Adding a deadline: `deadline`
Adds a deadline to the tasklist.

Format: `deadline <description> /by <date/time>`
- `<date/time>` must be in the form of `ddMMyyyy HHmm`, `dd/MM/yyyy HHmm`, `dd-MM-yyyy HHmm`, `yyyy/MM/dd HHmm`, or `yyyy-MM-dd HHmm`
- If time of `<date/time>` not specified, it will be set to 2359

Examples:
- `deadline essay /by 01022023 1800`
- `deadline assignment /by 2023-02-02`

### Adding a event: `event`
Adds an event to the tasklist.

Format: `event <description> /from <date/time> /to <date/time>`
- `<date/time>` must be in the form of `ddMMyyyy HHmm`, `dd/MM/yyyy HHmm`, `dd-MM-yyyy HHmm`, `yyyy/MM/dd HHmm`, or `yyyy-MM-dd HHmm`
- If time of start `<date/time>` not specified, it will be set to 0000
- If time of end `<date/time>` not specified, it will be set to 2359

Examples:
- `event open day /from 04032023 0900 /to 04/03/2023 1700`
- `event apply for internships /from 2023/01/01 /to 2023-04-31`

### Listing all tasks: `list`
Shows a list of all tasks in the tasklist.

Format: `list`

### Marking a task: `mark`
Marks a task as done in the tasklist.

Format: `mark <number>`
- Marks the task at the specified `<number>`
- The `<number>` refers to the index number shown in the displayed tasklist
- `<number>` **must be a positive integer** 1, 2, 3, ...

Examples:
- `list` followed by `mark 2` marks the 2nd task in the tasklist
- `list` followed by `mark 4` marks the 4th task in the tasklist

### Unmarking a task: `unmark`
Unmarks a task as done in the tasklist.

Format: `unmark <number>`
- Unmarks the task at the specified `<number>`
- The `<number>` refers to the index number shown in the displayed tasklist
- `<number>` **must be a positive integer** 1, 2, 3, ...

Examples:
- `list` followed by `unmark 2` unmarks the 2nd task in the tasklist
- `list` followed by `unmark 4` unmarks the 4th task in the tasklist

### Deleting a task: `delete`
Deletes a task in the tasklist.

Format: `delete <number>`
- Deletes the task at the specified `<number>`
- The `<number>` refers to the index number shown in the displayed tasklist
- `<number>` **must be a positive integer** 1, 2, 3, ...

Examples:
- `list` followed by `delete 2` deletes the 2nd task in the tasklist
- `list` followed by `delete 4` deletes the 4th task in the tasklist

### Filtering by description: `filter`
Filters tasks whose description contain any of the given keywords. 

Format: `filter <keywords...>`
- Separate multiple keywords with commas
- The search is case-sensitive
- The order of the keywords does not matter
- Only the description of the task is searched
- Tasks matching at least one keyword will be returned

Examples:
- `filter cut` returns `cut hair (by: Jan 01 2023  23:59)`
- `find slides, script` returns `prepare script` and `prepare slides`

### Filtering by date: `filterdate`
Filters tasks whose date matches/is ongoing on any of the given dates.

Format: `filterdate <dates...>`
- Separate multiple dates with commas
- Works only for tasks with dates i.e., deadlines and events
- `<dates...>` must be in the form of `ddMMyyyy`, `dd/MM/yyyy`, `dd-MM-yyyy`, `yyyy/MM/dd`, or `yyyy-MM-dd`
- The order of the dates does not matter
- Only the date(s) of the task is searched
- Tasks matching at least one date will be returned

Examples:
- `filterdate 01012023` returns `cut hair (by: Jan 01 2023  23:59)`
- `flterdate 2023-02-01` returns `prepare script (from: Jan 01 2023 00:00 to: Jan 03 2023 23:59)`

### Sorting by alphabet: `sort`
Sorts all tasks by alphabetical order and displays them.

Format: `sort`
- Sorts by task description

### Sorting by date: `sortdate`
Sorts all tasks by date and displays them.

Format: `sortdate`
- All todos will be displayed last
- Sorts events by their start date

### Sorting by task type: `sorttask`
Sorts all tasks by task type and displays them.

Format: `sorttask`
- All todos will be displayed first, followed by deadlines and then events

### Sorting by task status: `sortdone`
Sorts all tasks by status (marked/unmarked) and displays them.

Format: `sortdone`
- All unmarked tasks will be displayed first, followed by marked tasks

## Saving the data
BorzAI data are saved in the hard disk automatically after any command that changes the data. There is no need to save manually.

## Command Summary

| Command | Description |
| --- | --- |
| `todo <description>` | Add todo. |
| `deadline <description> /by <date/time>` | Add deadline. |
| `event <description> /from <date/time> /to <date/time>` | Add event. |
| `list` | Show tasks. |
| `mark <number>` | Mark task. |
| `unmark <number>` | Unmark task. |
| `delete <number>` | Delete task. |
| `filter <keywords...>` | Filter tasks by keyword(s), separate multiple keywords with commas. |
| `filterdate <dates...>` | Filter tasks by date(s), separate multiple dates with commas. |
| `sort` | Sort tasks by alphabet. |
| `sortdate` | Sort tasks by date. |
| `sorttask` | Sort tasks by task type. |
| `sortdone` | Sort tasks by status. |