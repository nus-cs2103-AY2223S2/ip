# **BOTanic** 🍎

> “You were meant for greater things." 📈

Don't waste your time remembering tasks, let Duke do it for you. 👍 It is
- Easy to use
- Fast
- Convenient
- ****FREE****

## **Usage guide** 📚
1. Download *"duke.jar"* file from [here](https://github.com/HmuuMyatMoe/ip/releases/tag/A-Jar).
2. Double-click the downloaded file.
3. Start adding & managing your tasks!

## **Features** 📑

### Add tasks

* Categorize your tasks into ToDo, Deadline or Event tasks and add them into your task list.

### List out tasks

* Get an overview of all the tasks in your list.

### Mark tasks as done

* Mark your tasks as done when you have completed them.

### Unmark tasks

* Marked the wrong task as done? Don't worry, just unmark them.

### Delete tasks

* Delete tasks that you no longer need.

### Find tasks using names

* Find tasks with names that matches exactly or partially with the given keyword.

### Find tasks using dates

* Find tasks with dates that matches exactly with the given date.

## Usage 📱

### `todo {TASK_NAME}` - Add a ToDo task

- Adds a ToDo task with the given TASK_NAME
- Give your task any name you want (A word, a phrase, a number, a combination of them, you name it!)

### `deadline {TASK_NAME} /by {YYYY/MM/DD}` - Add a Deadline task

- Adds a Deadline task with the given TASK_NAME and deadline (YYYY/MM/DD)
- There are no restrictions for TASK_NAME field
- Deadline must follow the YYYY/MM/DD format, e.g., `2023/11/14`

### `event {TASK_NAME} /from {YYYY/MM/DD} /to {YYYY/MM/DD}` - Add an Event task

- Adds an Event task with the given TASK_NAME, start date (YYYY/MM/DD) and end date (YYYY/MM/DD)
- There are no restrictions for TASK_NAME field
- Start and end date must follow the "YYYY/MM/DD" format, e.g., `2023/11/14`

### `list` - List out all the tasks

- The list of existing tasks added will be shown on screen

### `mark {INDEX}` - Mark task as done

- Task at given INDEX will be marked as done
- INDEX needs to be an integer

### `unmark {INDEX}` - Mark task as done

- Task at given INDEX will be unmarked / marked as not done
- INDEX needs to be an integer

### `delete {INDEX}` - Delete task

- Task at given INDEX will be deleted from the list
- INDEX needs to be an integer

### `findallmatch {KEYWORD}` - Find tasks with names that completely match the given keyword

- Case-insensitive search
- Tasks with names that completely match the given keyword will be shown on screen
- Example, `findallmatch reflection` would match with `[T][ ] Reflection` but NOT with `[T][ ] Reflect`
- Keyword can be a word, phrase, integer or a combination of them

### `findflex {KEYWORD}` - Find tasks with names that partially match with given keyword

- Case-insensitive search
- Tasks with names that partially or completely match the given keyword will be shown on screen
- Example, `findallmatch reflect` would match with `[T][ ] Reflection` AND `[T][ ] Reflect`
- Keyword can be a word, phrase, integer or a combination of them

### `finddate {YYYY/MM/DD}` - Find tasks with dates that matches given date

- Deadline or Event tasks with matching due date, start date or end date will be shown
- Given date must follow the "YYYY/MM/DD" format, e.g., `2023/11/14`

### `bye` - Quit the program

- Stores data into hard drive
- TIP: Simply exiting the program using the window exit button would save the date too!


## **Try this** ⬇️
```
todo buy book
list
```
## Potential Upcoming Features 😉
- [ ] Reminders
- [ ] Sorting the tasks

## Program's main method [^1]
```
public class Main {
    public static void main(String[] args) {
        Application.launch(MainApp.class, args);
    }
}
```

✨ Get BOTanic today! ✨

## Acknowledgements
* Program was designed using images from Flaticon.com
  * Botanic's icon was taken from https://www.flaticon.com/free-icon/apple_4478115
  * Attribution for Botanic's icon: <a href="https://www.flaticon.com/free-icons/apple" title="apple icons">Apple icons created by Freepik - Flaticon</a>
  * User's icon was taken from https://www.flaticon.com/free-icon/sun_4478387?related_id=4478387&origin=pack
  * Attribution for User's icon: <a href="https://www.flaticon.com/free-icons/cloudy" title="cloudy icons">Cloudy icons created by Freepik - Flaticon</a>
* This user guide was inspired by https://nus-cs2103-ay2223s2.github.io/website/admin/ip-w4.html

[^1]: TIP! If you are a java programmer, feel free to practice your Java programming using this.
