> _If you wish to forget anything on the spot, make a note that this thing is to be remembered._
> ― Edgar Allan Poe

# Duke++ User Guide

**Duke++** is a command line interface for you to create and manage reminders for yourself, built on top of [Project Duke](https://nus-cs2103-ay2223s2.github.io/website/se-book-adapted/projectDuke/index.html#project-duke).

## Features 

### Manage Your Tasks

Add and manage your tasks, including defining deadlines and events.

### Save and Load Your Tasks

Save your tasks to a file and load them back when you start the program again.


## Usage

### `add todo` - Add a todo task

`add todo <description>` will add a todo task with the given description to the list. Duke++ will output a conformation message and details of the task.

Example of usage: `add todo make dinner`

Expected output:
```
	added: [T][ ] make dinner
```

### `add deadline` - Add a deadline task

`add dealine <description> /by <date>` will add a deadline task with the given description and due date to the list. Duke++ will output a conformation message and details of the task. Dates have to follow the `YYYY-MM-DD` format.

Example of usage: `add deadline do homework /by 2022-02-02`

Expected output:
```
	added: [D][ ] do homework (by: Wed, 2 Feb 2022)
```

### `add event` - Add an event task

`add event <description> /from <start_date> /to <end_date>` will add an event task with the given description, start date and end date to the list. Duke++ will output a conformation message and details of the task. Dates have to follow the `YYYY-MM-DD` format.

Example of usage: `add event holiday cruise /from 2023-02-23 /to 2023-03-10`

Expected output:
```
	added: [E][ ] holiday cruise (from: Thu, 23 Feb 2023) (to: Fri, 10 Mar 2023)
```

### `list` - List your tasks

Enter `list` and Duke++ will list all your tasks by the order they are added.

Example output:
```
	tasks: 
		1. [E][ ] holiday cruise (from: Thu, 23 Feb 2023) (to: Fri, 10 Mar 2023)
		2. [T][ ] make dinner
		3. [D][ ] do homework (by: Wed, 2 Feb 2022)
```

### `mark` - Mark or Unmark a task as done

`mark [indexes...] [/done [indexes...]] [/notdone [indexes...]]` will perform certain operations related to completion status on tasks, given the indexes as they appear in `list`. By default, the statuses are toggled, but the optional flags `/done` and `/notdone` allow you to specify the operation done. Duke++ will output a conformation message with updated details of the tasks.

1. Tasks succeeding `mark` will have their status toggled.
2. Tasks succeeding `/done` will instead be marked as done.
3. Tasks succeeding `/notdone` will instead be marked as not done (unmarked).

Example of usage: `mark 1 2 /done 3 4 /notdone 5 6`

Expected output:
```
> list
	tasks: 
		1. [E][ ] holiday cruise (from: Thu, 23 Feb 2023) (to: Fri, 10 Mar 2023)
		2. [T][X] make dinner
		3. [D][ ] do homework (by: Wed, 2 Feb 2022)
		4. [T][X] cut hair
		5. [E][ ] hackathon (from: Mon, 2 Oct 2023) (to: Wed, 4 Oct 2023)
		6. [D][X] birthday (by: Mon, 17 Apr 2023)
> mark 1 2 /done 3 4 /notdone 5 6
	marked: 
		1. [E][X] holiday cruise (from: Thu, 23 Feb 2023) (to: Fri, 10 Mar 2023)
		2. [T][ ] make dinner
	marked: 
		3. [D][X] do homework (by: Wed, 2 Feb 2022)
		4. [T][X] cut hair
	marked: 
		5. [E][ ] hackathon (from: Mon, 2 Oct 2023) (to: Wed, 4 Oct 2023)
		6. [D][ ] birthday (by: Mon, 17 Apr 2023)
> list
    tasks: 
		1. [E][X] holiday cruise (from: Thu, 23 Feb 2023) (to: Fri, 10 Mar 2023)
		2. [T][ ] make dinner
		3. [D][X] do homework (by: Wed, 2 Feb 2022)
		4. [T][X] cut hair
		5. [E][ ] hackathon (from: Mon, 2 Oct 2023) (to: Wed, 4 Oct 2023)
		6. [D][ ] birthday (by: Mon, 17 Apr 2023)
```

### `delete` - Delete tasks

`delete [indexes...]` deletes the tasks with the given indexes as they appear in `list`. Duke++ will output a conformation message with updated details of the tasks.

Example of usage: `delete 3 6`

Expected output:
```
	deleted:
		3. [D][X] do homework (by: Wed, 2 Feb 2022)
		6. [T][ ] cut hair
```

### `find` - Search for tasks

`find [string]` finds and lists the tasks with descriptions that (partially) match the search string. A description with a substring exactly matching the search string is considered a match.

Example of usage: `find day`

Expected output:
```
	2 matches found:
		1. [E][X] holiday cruise (from: Thu, 23 Feb 2023) (to: Fri, 10 Mar 2023)
		4. [D][ ] birthday (by: Mon, 17 Apr 2023)
```

### `alias` - Aliases for commands

`alias` performs operations related to command aliases. Aliases are used to rename or shorten the commands you type. For example, `delete` can be aliased to `del` so that you can type `del [indexes...]` instead of `delete [indexes...]`.

1. `alias /list` lists all currently active aliases.
    
    Example output:
    ```
    	aliases:
                pop -> delete
                drop -> delete
                q -> exit
                search -> find
                rename -> alias
                ls -> list
                X -> mark
                quit -> exit
                rm -> delete
                put -> add
                push -> add
                remove -> delete
    ```
2. `alias /add <alias> /is <command>` adds an alias for the given command.
    
    Example of usage: `alias /add del /is delete`
    
    Expected output:
    ```
    	alias added: del -> delete
    ```
3. `alias /delete <alias>` deletes the given alias.

    Example of usage: `alias /delete del`
    
    Expected output:
    ```
    	alias deleted: del -> delete
    ```

Example of usage: `alias /add del /is delete`

Expected output:
```
	2 matches found:
		1. [E][X] holiday cruise (from: Thu, 23 Feb 2023) (to: Fri, 10 Mar 2023)
		4. [D][ ] birthday (by: Mon, 17 Apr 2023)
```

### `help` - Help menu

`help` displays a help menu with a list of commands and their usage.

Example output:

```
	Usage: <command> [<args>]
		 add : add task
		help : show this help message
		exit : exit the app
		find : find tasks by description
		alias : add / remove command alias
		list : list tasks
		delete : delete task at index
		mark : toggle task mark
```

### `exit` - Exit the app

`exit` quits the app after an exit message.

Example output:
```
    Goodbye.
```