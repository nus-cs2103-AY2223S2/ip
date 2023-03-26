# **DOOK**


**“My unmatched perspicacity, coupled with sheer indefatigability, makes me a feared opponent in any realm of human endeavor.”**

– Andrew Tate


## **NEW UPDATED UI**

![UI](Ui.png)


DOOK frees your mind of having to remember things you need to do. It's,
  * text-based
  * easy to learn
  * FAST SUPER FAST to use

**All** you need to do is,

  * download it from here.
  * double-click it.
  * add your tasks.
  * let it manage your tasks for you 😉

And it is **FREE**!

## Features:

### Viewing help : ``help``

Brings users to the help page.

Format: ``help``

### Adding a task: ``add``

Adds a task to the list.

Format: ``add <taskType> <task>``
 
 * Task types supported :
   * ``todo`` : The simplest type of task, only requires a name for the task!
   * ``deadline`` : A task that has a due date. use ``/by`` to indicate the due date and time.
   * ``event`` : Similar to a deadline, but also has a start date and time. Use ``/from`` to indicate the start date and time, and ``/to`` for the end date and time.
 * Date formating :
   * 2 date formatting are accepted : ``dd/MM/yy`` and ``dd/MM/yyyy``
   * Supports both 12 hour and 24 hour time : ``hh:mm a`` and ``HHmm``
   * Date before time : ``dd/MM/yy hh:mm a`` or ``dd/MM/yyyy HHmm``

Examples:

``add todo buy onions and garlic``

``add event Jack Birthday Party /from 10/10/23 1700 /to 10/10/23 2200``

### Listing all tasks : ``list``

Shows a list of all tasks in the list.

Format: ``list``

### Finding via keywords : ``find ``

Finds tasks in the list with sepcified keywords.

Format: ``find <keyword 1> <keyword 2> <...>``

Examples :

``find buy marked``

 * Note: ``find`` returns the tasks with their original indexing, ie the tasks that ``find`` returns may not have consecutive indexing.

### Marking tasks : ``mark ``

Marks the task at specified index as done.

Format : ``mark <index>``

Examples :

``mark 2``

### Unmarking : ``unmark``

Unmarks the task with specified index.

Format : ``unmark 2``

### Deleting a person : ``delete``

Deletes the specified task from the list.

Format: ``delete <index>``

### Saving the data : ``bye``

Saves the current list for next bootup of DOOK.

Format : ``bye``

