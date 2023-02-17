# User Guide
MakimaBot is a chatbot that enables you to manage their tasks easily in a compact app. A Graphical User Interface (GUI) is provided for ease of usage.
## Key Features

### ✅ Adding Different Types of Tasks

MakimaBot allows you to quickly tasks such as `deadline`, `event` and `todo`.

### 🔍 Find Tasks Quickly

MakimaBot can quickly filter any matching description of tasks that you have saved.

## Main GUI
<p align="center">
<img src="https://raw.githubusercontent.com/Acerizm/ip/master/src/main/resources/images/MainPage.png" align="center" height=auto width="600">
</p>
<p align="center">
<em>Main Page</em>
</p><br/>


## Usage

### `mark` - Mark task

Given the index of the task, mark it as done.

Format: `mark <index>`

Example:

- `mark 1`

---

### `unmark` - Unmark task

Given the index of the task, mark it as undone.

Format: `unmark <index>`

Example:

- `unmark 1`

---

### `delete` - Delete task

Given the index of the task, delete the task from the list of tasks.

Format: `delete <index>`

Example:

- `delete 1`

---

### `list` - List all tasks

List all the tasks that are saved.

Format: `list`

---

### `find` - Find all matching tasks

Finds the list of tasks that has descriptions that matches your input.

Format: `find <description>`

Example:

- `find hello world`

---

### `todo` - Create and add a todo task

Creates and add a todo task to the list of tasks. Todo tasks are generally non-specific.

Format: `todo <description>`

Example:

- `todo i hate CS`

---

### `deadline` - Create and add a deadline task

Creates and add a deadline task to the list of tasks.

Time Format Supported:
1. `h:mm a`: `1:30 PM`, `12:15 AM`, `7:45 PM`
2. `HH:mm`: `14:30`, `09:45`, `21:15`
3. `hh:mm a`: `01:30 PM`, `12:15 AM`, `07:45 PM`
4. `HH:mm:ss`: `14:30:00`, `09:45:23`, `21:15:59`
5. `HHmm`: `1430`, `0945`, `2115`
6. `h:mma`: `1:30PM`, `12:15AM`, `7:45PM`
7. `hh:mma`: `01:30PM`, `12:15AM`, `07:45PM`
8. `h:a`: `1:PM`, `12:AM`, `7:PM`

Format: `deadline <description> /by <date> <time>`

Example:

- `deadline submit nothing /by 20/05/2026 1400`

---

### `event` - Create and add an event task

Creates and add an event task to the list of tasks. 
Multiple formats are supported as shown below.

Time Format Supported:
1. `h:mm a`: `1:30 PM`, `12:15 AM`, `7:45 PM`
2. `HH:mm`: `14:30`, `09:45`, `21:15`
3. `hh:mm a`: `01:30 PM`, `12:15 AM`, `07:45 PM`
4. `HH:mm:ss`: `14:30:00`, `09:45:23`, `21:15:59`
5. `HHmm`: `1430`, `0945`, `2115`
6. `h:mma`: `1:30PM`, `12:15AM`, `7:45PM`
7. `hh:mma`: `01:30PM`, `12:15AM`, `07:45PM`
8. `h:a`: `1:PM`, `12:AM`, `7:PM`

Format:
1. `event <description> /from <day> /to <day>`
2. `event <description> /from <date> <time> /to <date> <time>`
3. `event <description> /from <date> <time> /to <time>`

Example:

1. `event career fair /from Monday /to Sunday`
2. `event hackathon /from Monday 2:PM /to Sunday 4:AM`
3. `event gaming competition /from Monday 2:PM /to 4:PM`
---

### `bye` - Exit the program

Quit **MakimaBot**

Format: `bye`