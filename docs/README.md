# Duke 😸

#### A personal assistant chatbot that helps you to keep track of various things

> ###### _Why remember things, when Duke can do it for you? 🤔_

## Duke is:

- simple user interface
- text-based
- easy to learn
- ~~FAST~~ ***SUPER FAST*** to use

## Features

### Task Management 💻

Duke can perform many operations to help you manage tasks. Some examples
are `todo`, `deadline`, `event`, `find`, `delete`, and many more!

### Continuous Memory ✍️

Duke stores your tasks in a local memory, so you don't have to worry about losing track of your tasks!

## Usage

### `help` - Provides a list of commands

Shows the user a list of commands that Duke would understand

Example of usage: `help`

Expected outcome:

```
MY COMMANDS ARE:
01. ADD TODO TASK:       todo [descr]
02. ADD DEADLINE TASK:   deadline [descr] /by [YYYY-MM-DD HH:MM]
03. ADD EVENT TASK:      event [descr] /from [YYYY-MM-DD HH:MM] /to [YYYY-MM-DD HH:MM]
04. MARK A TASK:         mark [index]
05. UNMARK A TASK:       unmark [index]
06. DELETE A TASK:       delete [index]
07. FIND A TASK:         find [keyword]
08. LIST ALL TASKS:      list
09. DISPLAY COMMANDS:    help
10. EXIT PROGRAM:        bye
```

### `todo` - Adds a Todo task

Adds a Todo task to your task list

Example of usage: `todo read book`

Expected outcome:

```
Got it. I've added the this task:
read book
Now you have 1 tasks in your list.
```

These are just 2 of the many commands available!

## Command Summary

| Command | Format                                                         |
|---------|----------------------------------------------------------------|
| todo    | todo [descr]                                                   |
| deadline | deadline [descr] /by [YYYY-MM-DD HH:MM]                       |
| event   | event [descr] /from [YYYY-MM-DD HH:MM] /to [YYYY-MM-DD HH:MM]  |
| mark    | mark [index]                                                   |
| unmark  | unmark [index]                                                 |
| delete  | delete [index]                                                 |
| find    | find [keyword]                                                 |
| list    | list                                                           |
| help    | help                                                           |
| bye     | bye                                                            |
