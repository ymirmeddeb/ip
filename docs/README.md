# User Guide for Joe

Welcome to Joe, your personal assistant for managing tasks! Below is a guide on how to use all the important features of Joe.

## Features 

### Todo Tasks

Allows you to add tasks without a specific deadline. These tasks can be anything from daily chores to long-term goals.

### Deadlines

Enables you to set tasks that must be completed by a certain date and time, helping you manage your deadlines effectively.

### Events

Helps you schedule events for specific dates and times, ensuring you never forget important appointments.

### Listing Tasks

Provides an overview of all your tasks, including todos, deadlines, and events, in one place.

### Marking Tasks as Done

Gives you the satisfaction of marking tasks as completed once you finish them.

### Unmarking Tasks as Done

Mark tasks as not done in case you marked them as completed by mistake.

### Delete Tasks

Allows you to remove a task from your list once it's no longer relevant or has been mistakenly added.

### Finding Tasks by Keyword

Allows you to quickly find tasks by searching for a keyword.

## Usage

### `todo` - Add a Todo Task

Add a task without a deadline.

Example of usage:

`todo Read a book`

Expected outcome:

A new todo task is added to your list.

```
Added: [T][ ] Read a book
```

### `deadline` - Set a Deadline for a Task

Specify a task that needs to be completed by a certain date and time.

Example of usage:

`deadline Submit assignment /by 2023-03-21 2359`

Expected outcome:

A new task with a deadline is added to your list.

```
Added: [D][ ] Submit assignment (by: Mar 21 2023, 23:59)
```

### `event` - Schedule an Event

Schedule an event with a specific start and end time.

Example of usage:

`event Project meeting /from 2023-03-15 1400 /to 2023-03-15 1600`

Expected outcome:

A new event is added to your list.

```
Added: [E][ ] Project meeting (from: Mar 15 2023, 14:00 to Mar 15 2023, 16:00)
```

### `list` - List All Tasks

Displays all your tasks.

Example of usage:

`list`

Expected outcome:

A list of all tasks, including todos, deadlines, and events.

```
1. [T][ ] Read a book
2. [D][ ] Submit assignment (by: Mar 21 2023, 23:59)
3. [E][ ] Project meeting (from: Mar 15 2023, 14:00 to Mar 15 2023, 16:00)
```

### `mark` - Mark a Task as Done

Mark a specific task as done by its list number.

Example of usage:

`mark 1`

Expected outcome:

The first task in the list is marked as done.

```
Sure thinggg! I've marked this task as done, just for you:
  [T] [X] read book
```

### `unmark` - Mark a Task as Not Done

Mark a specific task as not done by its list number.

Example of usage:

`unmark 1`

Expected outcome:

The first task in the list is marked as not done.

```
Sure thinggg! I've marked this task as not done yet just for you:
  [T] [ ] read book
```

### `delete` - Remove a Task

Delete a task from your list by specifying its number.

Example of usage:

`delete 1`

Expected outcome:

The first task in your list is removed, and Joe confirms the deletion.

```
Okayyyy, I've removed this task:
  [T][ ] Read a book
Now you have X tasks in the list.
```

### `find` - Find Tasks by Keyword

Find and list all tasks that contain the specified keyword.

Example of usage:

`find book`

Expected outcome:

A list of tasks containing the keyword "book".

```
Ok cutie patootie, here are the matching tasks in your list:
1.[T] [X] read book
2.[D] [ ] return book (by: Dec 20 2023, 18:00)
```
