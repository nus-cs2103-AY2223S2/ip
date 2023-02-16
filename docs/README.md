# User Guide
```
Hello From:
____         _        
|  _ \ _   _| | _____ 
| | | | | | | |/ / _ \
| |_| | |_| |   <  __/
|____/ \__,_|_|\_\___|
```
## Features 

### Actionable List

Duke will help you track a list of actionable items similar to this:
- [X] todo a 
- [ ] deadline b (by: Sat 22 Jan 22)
- [ ] event c (from: Thu, 01 Dec to Tue, 10 Jan 23)
- [ ] todo b

You can add new tasks, delete tasks, mark and unmark task for your convinience.

### Saved List

The actionable list is saved during the user session and can be retrieved when Duke restarts after being terminated by the user.

## Usage

### `Echo` - Echoes the user input

Example of usage:   
`echo (*sentence*)`  
`echo Hello!`

Expected outcome:  
Duke will say the `sentence`

```
expected output
Duke: Hello!
```

### `Todo` - Creates a todo item

Example of usage:   
`todo (*description*)`  
`todo work`

Expected outcome:  
Duke adds the todo item to the list and tells you how many items you have.

```
expected output
Got it. I've added this task:
    [T][ ] work
You have * tasks in the list
```

### `Deadline` - Creates a Deadline item

Example of usage:   
`Deadline (*description*) /by (*YYYY-MM-DD*)`  
`Deadline work /by 2022-01-22`

Expected outcome:  
Duke adds the Deadline item to the list and tells you how many items you have.

```
expected output
Got it. I've added this task:
    [D][ ] work (by: Sat, 22 Jan 22) 
You have * tasks in the list
```

### `Event` - Creates a Event item

Example of usage:   
`Event (*description*) /from (*YYYY-MM-DD*) /to (*YYYY-MM-DD*)`  
`Event comicon /from 2022-12-01 /to 2023-01-10`

Expected outcome:  
Duke adds the Event item to the list and tells you how many items you have.

```
expected output
Got it. I've added this task:
    [E][ ] comicon (from: Thu, 01 Dec to: 10 Jan 23) 
You have * tasks in the list
```

### `Mark` - Marks an item as done

Example of usage:   
`Mark (*item number*)`  
`Mark 1`

Expected outcome:  
Duke marks the item number as done

```
expected output
Nice! I've marked this task as done:
    [E][X] comicon (from: Thu, 01 Dec to: 10 Jan 23)
```

### `Unmark` - Unmarks an item, making it not done

Example of usage:   
`Unmark (*item number*)`  
`Unmark 1`

Expected outcome:  
Duke unmarks the item number as done

```
expected output
OK! I've marked this task as not done yet:
    [E][ ] comicon (from: Thu, 01 Dec to: 10 Jan 23)
```

### `List` - Lists all the items

Example of usage:   
`List`

Expected outcome:  
Duke lists out all the items

```
expected output
Here are the tasks in your list:
1. [E][ ] comicon (from: Thu, 01 Dec to: 10 Jan 23)
2. [D][ ] work (by: Sat, 22 Jan 22)
3. [T][ ] work
```

### `Delete` - Deletes a task

Example of usage:   
`Delete (*item number*)`  
`Delete 1`

Expected outcome:  
Duke deletes the item number.

```
expected output
Noted. I've removed this task:
    [E][ ] comicon (from: Thu, 01 Dec to: 10 Jan 23)
```
