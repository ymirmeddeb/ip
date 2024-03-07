import java.util.List;


public class Task {
    public String description;
    private boolean isDone;

    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    public String getDescription() {
        return description;
    }

    public String getIcon() {
        return (isDone ? "[X]" : "[ ]");
    }

    public boolean isDone() {
        return isDone;
    }

    public void markAsDone() {
        isDone = true;
    }

    public void markAsNotDone() {
        isDone = false;
    }

    public static Task getTask(Task[] tasks, int index) {
        if (index >= 0 && index < tasks.length) {
            return tasks[index];
        } else {
            throw new IllegalArgumentException("Invalid task index");
        }
    }

    public static void removeTask(Task[] tasks, int index) {
        for (int i = index; i < tasks.length - 1; i++) {
            tasks[i] = tasks[i + 1];
        }
    }

    public static int getTaskCount(List<Task> tasks) {
        return tasks.size();
    }

    @Override
    public String toString() {
        return getIcon() + " " + description;
    }
}

