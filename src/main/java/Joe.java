import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class Joe {
    private Storage storage;
    private Ui ui;
    private Parser parser;
    private TaskList taskList;

    public Joe(String filePath) {
        this.storage = new Storage(filePath);
        this.ui = new Ui();
        this.parser = new Parser();
        this.taskList = new TaskList(new ArrayList<>());
    }

    public void run() {
        ui.printWelcome();
        try {
            taskList = new TaskList(storage.load());
        } catch (IOException e) {
            ui.printError("I have failed you :( I have failed to load tasks from the file: " + e.getMessage());
            return;
        }

        Scanner scanner = new Scanner(System.in);
        boolean isExit = false;

        while (!isExit) {
            String userInput = scanner.nextLine();
            String commandWord = parser.getCommandWord(userInput);

            switch (commandWord) {
                case "bye":
                    isExit = true;
                    break;
                case "list":
                    ui.printTasks(taskList.getTasks());
                    ui.printHelp();
                    break;
                case "mark":
                    markTask(userInput);
                    ui.printHelp();
                    break;
                case "unmark":
                    unmarkTask(userInput);
                    ui.printHelp();
                    break;
                case "todo":
                case "deadline":
                case "event":
                    addTask(userInput, commandWord);
                    ui.printHelp();
                    break;
                case "delete":
                    deleteTask(userInput);
                    ui.printHelp();
                    break;
<<<<<<< HEAD
=======
                case "find":
                    String keyword = parser.getTaskDescription(userInput);
                    List<Task> foundTasks = taskList.findTasksByKeyword(keyword);
                    ui.printFoundTasks(foundTasks);
                    break;
>>>>>>> parent of 73109e3 (added save functionality)
                default:
                    ui.printError("Babe come on, what am i gonna do with this???? For the love of God, please give me a valid command.");
            }
        }

        ui.printBye();
        scanner.close();
    }

    private void addTask(String userInput, String type) {
        try {
            Task task = parser.parseTask(userInput, type);
            taskList.addTask(task);
<<<<<<< HEAD
            storage.save(taskList.getTasks()); // Save tasks to storage
=======
            storage.save(taskList.getTasks());
>>>>>>> parent of 73109e3 (added save functionality)
            ui.printTaskAdded(task, taskList.getTasks());
        } catch (IOException e) {
            ui.printError("I have failed you :( I have failed to save the tasks: " + e.getMessage());
        } catch (JoeException e) {
            ui.printError(e.getMessage());
        }
    }

    private void markTask(String userInput) {
        try {
<<<<<<< HEAD
            int index = parser.parseTaskIndex(userInput); // This method needs to be implemented in Parser
=======
            int index = parser.parseTaskIndex(userInput);
>>>>>>> parent of 73109e3 (added save functionality)
            taskList.markTaskAsDone(index);
            storage.save(taskList.getTasks());
            ui.printMarkedTask(taskList.getTask(index), true);
        } catch (IndexOutOfBoundsException e) {
            ui.printError("That's not a valid task number babe");
        } catch (IOException e) {
            ui.printError("I have failed you :( I have failed to save the tasks: " + e.getMessage());
        } catch (JoeException e) {
            ui.printError(e.getMessage());
        }
    }

    private void unmarkTask(String userInput) {
        try {
<<<<<<< HEAD
            int index = parser.parseTaskIndex(userInput); // Implement this in Parser
=======
            int index = parser.parseTaskIndex(userInput);
>>>>>>> parent of 73109e3 (added save functionality)
            taskList.markTaskAsNotDone(index);
            storage.save(taskList.getTasks());
            ui.printMarkedTask(taskList.getTask(index), false);
        } catch (IndexOutOfBoundsException e) {
            ui.printError("That's not a valid task number babe");
        } catch (IOException e) {
            ui.printError("I have failed you :( I have failed to save the tasks: " + e.getMessage());
        } catch (JoeException e) {
            ui.printError(e.getMessage());
        }
    }

    private void deleteTask(String userInput) {
        try {
<<<<<<< HEAD
            int index = parser.parseTaskIndex(userInput); // Implement this in Parser
=======
            int index = parser.parseTaskIndex(userInput);
>>>>>>> parent of 73109e3 (added save functionality)
            Task task = taskList.removeTask(index);
            storage.save(taskList.getTasks());
            ui.printTaskRemoved(task, taskList.getTasks());
        } catch (IndexOutOfBoundsException e) {
            ui.printError("That's not a valid task number babe");
        } catch (IOException e) {
            ui.printError("I have failed you :( I have failed to save the tasks: " + e.getMessage());
        } catch (JoeException e) {
            ui.printError(e.getMessage());
        }
    }

    public static void main(String[] args) {
        new Joe("joe.txt").run();
    }
}
