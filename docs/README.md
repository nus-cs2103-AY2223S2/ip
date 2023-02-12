# User Guide

`Kuromi クロミ` is a desktop application that acts as a *personal assistant chatbot* to **keep track of your tasks**. The character `Kuromi クロミ` (the bot) is taken from a [Sanrio Character](https://www.sanrio.com/collections/kuromi), and the user will play the role as `Kuromi クロミ`'s **rival**, `My Melody マイメロディ`.

## Main Features 

### Manage your tasks

Kuromi **keeps track** of your tasks by allowing you to `add`, `view`, `delete`, `mark`, and `unmark` by typing specific commands through the application. Kuromi is talkative and will give comments to your commands! :D

### Get upcoming deadlines

Kuromi can **remind** you of your upcoming deadlines by sending the command `remind`. You can set the number of upcoming tasks that should be shown.

### Find keyword

To easily search for specific tasks, Kuromi is able to find the tasks which include a specific keyword.

### Get My Melody's Mistakes

From this [source](https://hellokitty.fandom.com/wiki/Kuromi#With_Friends_and_Family), Kuromi has a notebook `Kuromi Note` that **lists all the mistakes** that My Melody made to Kuromi. You can get ~~My Melody's~~ your mistakes by specifying the command `mistakes`.

## Usage

## Section A: Add a task

### 1. `todo`

#### Function
Adds a ToDo &mdash; a task that does not have a deadline.

#### Format
`todo <description>`

#### Example of usage

- `todo borrow book`
- `todo learn hiragana`
- `todo week 6 iP tasks`

Expected outcome:

```
Got it. I've added this task:
[T][] borrow book
-----
Note:
Now you have 4 tasks in the list.
```

### 2. `deadline`

#### Function
Adds a Deadline &mdash; a task that has a deadline date.

#### Format
- `deadline <description> /by <deadline>`
- `<deadline>` format: `yyyy-MM-dd HH:mm`

#### Example of usage

- `deadline return book /by 2030-01-01 10:30`
- `deadline submit iP /by 2023-02-17 23:59`
- `deadline katakana practice /by 2023-03-01 14:00`

Expected outcome:

```
Got it. I've added this task:
[D][] return book (by: 2030-01-01 10:30)
-----
Note:
Now you have 5 tasks in the list.
```

### 3. `event`

#### Function
Adds an Event &mdash; a task that has a start date and end date.

#### Format
- `event <description> /from <start_date> /to <end_date>`
- `<start_date>` & `<end_date>` format: `yyyy-MM-dd HH:mm`

#### Example of usage

- `event project meeting /from 2030-01-01 10:30 /to 2030-01-01 12:30`
- `event tP meeting /from 2023-01-13 16:00 /to 2023-01-13 18:00`
- `event cmang's birthday party /from 2023-03-12 17:00 /to 2023-03-12 19:00`

Expected outcome:

```
Got it. I've added this task:
[E][] project meeting (from: 2030-01-01 10:30 to 2030-01-01 12:30)
-----
Note:
Now you have 6 tasks in the list.
```
