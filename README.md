# 📝 Java Task Manager CLI

A simple command-line interface (CLI) Task Manager built with Java. Manage your tasks with features like adding, updating, deleting, listing, and status updates. Tasks are saved to a local `tasks.json` file for persistence across sessions.

---

## 📂 Project Structure

```
├── Main.java          # Entry point with the CLI interface
├── Task.java          # Task model with metadata and JSON support
├── Status.java        # Enum representing task status (TODO, IN_PROGRESS, DONE)
├── TaskManager.java   # Core logic for managing tasks (CRUD operations and file I/O)
├── tasks.json         # Automatically generated file to store tasks persistently
```

---

## 🚀 Features

- ✅ Add new tasks with title and description
- 📃 List all tasks with metadata
- ✏️ Update task title and description
- 🔁 Change task status: `ToDo` → `In Progress` → `Done`
- ❌ Delete tasks
- 💾 Persistent task storage in JSON format

---

## 🛠️ Requirements

- Java 17 or later
- Terminal or Command Prompt

---

## 🧪 How to Run

1. **Compile the code**:

```bash
javac Main.java Task.java TaskManager.java Status.java
```

2. **Run the application**:

```bash
java Main
```

---

## 📋 CLI Menu

```
************
Task Tracker
************
1. Add task
2. Update task
3. Delete task
4. List task
5. Update Status
6. Exit
Enter your choice:
```

---

## 🖨️ Sample Output

```
id: 1, title: Finish report, description: Prepare monthly sales report, status: In progress,
createdAt: 2025-05-13T10:25:30, updatedAt: 2025-05-13T10:40:00
```

---

## ✅ Status Enum

```java
public enum Status {
    TODO("ToDo"),
    IN_PROGRESS("In progress"),
    DONE("Done")
}
```

---

## 💾 Persistence

- Tasks are saved to `tasks.json` using a basic JSON structure.
- All data is reloaded on application startup.
- Make sure to use option `6` (Exit) to save changes before closing.

---

## 📃 License

This project is open-source and available under the [MIT License](LICENSE).

---

## 🙋‍♂️ Author

Made with 💡 for educational purposes.
