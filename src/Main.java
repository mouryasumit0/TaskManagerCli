import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class Main {
    static Scanner scanner = new Scanner(System.in);
    public static void main(String[] args){
        boolean isRunning = true;

        TaskManager taskManager = new TaskManager();

        System.out.println("************");
        System.out.println("Task Tracker");
        System.out.println("************");

        while(isRunning){
            int userChoice;

            System.out.println("1. Add task");
            System.out.println("2. Update task");
            System.out.println("3. Delete task");
            System.out.println("4. List task");
            System.out.println("5. Update Status");
            System.out.println("6. Exit");

            System.out.print("Enter your choice: ");
            userChoice = scanner.nextInt();

            switch (userChoice){
                case 1 -> taskManager.addTask(scanner);
                case 2 -> taskManager.updateTask(scanner);
                case 3 -> taskManager.deleteTask(scanner);
                case 4 -> taskManager.listTasks("All");
                case 5 -> taskManager.updateStatus(scanner);
                case 6 -> isRunning = false;
                default -> System.out.println("Invalid choice");
            }
        }

        taskManager.saveTasks();
        scanner.close();
    }

}
