# User Guide


## Contents
* toc
{:toc}

---
# 👋  Hello from Duke!
> What's on your mind today?

A simple chatbot that manages things you need to do. With all the productivity apps out there, sometimes _all you need_ is an application that is:
- Fuss free
- Easy to use
- Quick

### **Without** all the clutter, focus and get things done 💯.

---

# 🙋‍♂️ Getting Started

****[IMPORTANT]****: Ensure you have Java installed on your device.

1. Download `Duke.jar` [here](https://github.com/arkarsg/ip/releases/download/A-Jar/Duke.jar)

#### For Mac users
1. Launch your Terminal and navigate to `Duke.jar` file using the `cd` command.
2. Start Duke with `java -jar Duke.jar`

#### For Windows users
1. Launch your Powershell and navigate to `Duke.jar` file.
2. Start Duke with `java -jar Duke.jar`

---
# 🧐 Features

To help you manage your everyday tasks, here's what the Duke can do for you.
- [x] Note down a simple task to do.
- [x] Note down a task that has a deadline.
- [x] Note down an event.
- [x] Find a task with keywords.
- [x] Sort tasks by name or date

---
# 📖 Documentation

### Adding a To-Do: `todo {task description}`
- Adds a simple to-do task with the given description.
- Accepts Integers and String.

### Adding a Deadline: `deadline {task description} /by {date time}`
- Adds a deadline task with the given `{date time}` as the deadline
- `{date time}` follows ***dd/mm/yyyy hhmm*** (i.e 23/01/2023 1600)

### Adding an Event: `event {task description} /from {date time} /to {date time}`
- Adds an event task with the given `{date time}` as the deadline
- `{date time}` follows ***dd/mm/yyyy hhmm*** (i.e 23/01/2023 1600)

### View all tasks: `list`
- Shows an indexed list of all tasks the Duke remembers.

### Delete a task: `delete {task index}`
- Deletes the task associated with the task index from task list.

### Mark or unmark a task: `mark {task index}` and `unmark {task index}`
- Mark an unfinished task as completed.
- Unmark removes the completed status from a task.

### Finding a task: `find {search keywords}`
- Returns a list of tasks containing the keywords.

### Sorting tasks (by name): `sort name`
- Sorts the current list of tasks alphabetically in an ascending order.

### Sorting tasks (by date): `sort date`
- Sorts the current list of tasks by date. If the tasks do not have date associated with it, it will be sorted lexicographically.
- Therefore, `todo` will be grouped differently from `deadline` and `events` which are sorted chronologically.

### Quitting Duke: `bye`
- Exits the application.
- Task list from the current session is saved and loaded the next time Duke is opened.


## Other behaviours
Upon start, `Duke` loads save data from previous session if it exists. If tasks are loaded successfully, users will see the following message:
> Loaded successfully from previous session.

Otherwise, task list from previous session was not restored.

Task list is only saved after `bye` is typed. Else, forced termination will cause user to lose changes. Saving changes causes save file to be completely overwritten.


---
# 🛠 Under the Hood

Pseudocode snippet below shows the main mechanism of how Duke works.

``` java
// main loop to accept user inputs
while (isNotBye) {
    handle(input);
}

// Command handler
String[] args = paser.parse(input);
String command = args[0];
commandList.get(command).apply(args);
```
---
# 👷‍♂️ Adding Commands
> _Beware! Duke is not responsible for any errors or data lost due to modification._

### Command Formats
Commands should generally follow `{cmd} {...args}`.  
- `{cmd}` : STRING Command keyword
- `{...args}` : STRING Arguments for the command. Can be one or multiple. 

### Adding Commands
- Download the `source file`.
- Source file is organised as follows:
```
Duke
  \src
    \main
      \java
        \duke
          \commands
            \utils\parser.java
            \commands
```

1. Create the logic for the new command by creating a new `java` file under `/commnands`.
    - The file should inherit `Command` and override the following method signature:

``` java
    /**
     * Execute the command from given tokens produced by Parser.
     * The command is operated on the given TaskList.
     *
     * @param dukeIo UI class used to return the results.
     * @param tasks TaskList instance to be operated on.
     * @return Response output to GUI.
     */
    public abstract String exec(DukeIo dukeIo, TaskList tasks);
```
2. Open `parser.java`
3. Under `populateCommnads()`, add your `command` and pass `tokens` to your custom parsing method that creates your custom command.
4. Build and run via `Launcher.java`

---
