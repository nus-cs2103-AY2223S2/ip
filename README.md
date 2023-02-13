# Duke
> “Your mind is for having ideas, not holding them.” – David Allen ([source](https://dansilvestre.com/productivity-quotes/))

Duke frees your mind by remembering things you need to do

It's 
- Chat-based
- Has a user-friendly interface
- easy to learn
- ~~Fast~~ _SUPER FAST_ to use

## Steps to run
1. Download the JAR file from the latest release [here](https://github.com/anchengyang/ip/releases)
2. Open it
3. Start adding your tasks
4. Let it manage your tasks for you 👍

| No. | Feature | Command |
| ------- | ------- | ------ |
| 1. | Create todo items | todo <description> |
| 1. | Create deadlines | deadline <description> /by <yyyy-MM-dd\n HHmm(optional)> |
| 1. | Create events | event <description> /from <yyyy-MM-dd\n HHmm(optional)> /to <yyyy-MM-dd\n HHmm(optional)> |
| 1. | Filter by keyword | find <keyword(s)> |
| 1. | Filter by date | finddate <date> |
| 1. | Mark items as completed | mark <task number> |
| 1. | Unmark items | unmark <task number> |
| 1. | Delete items | delete <task number> |
| 1. | Update description of existing tasks |  update <index> /description <new changes> |
| 1. | Update deadlines of existing deadlines |  update <index> /deadline <new changes> |


This is the main function:
   ```ruby
   public static void main(String[] args) {
        new Duke("tasks.txt").run();
    }
   ```
