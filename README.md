# 🎓 SWE4201 – Task 3: Java Student Record & Enrollment System

This repository contains the completed submission for **Task 3** of the **SWE4201: Introduction to Software Development** module. The project demonstrates a modular Java application designed to manage student records, course offerings, and enrollment workflows. It applies core principles of **object-oriented programming**, **modular architecture**, and **academic presentation standards**.

---

## 📘 Table of Contents

- [Project Overview](#project-overview)  
- [System Architecture](#system-architecture)  
- [Features](#features)  
- [Development Environment](#development-environment)  
- [Installation & Execution](#installation--execution)  
- [Sample Output](#sample-output)  
- [Learning Outcomes](#learning-outcomes)  
- [Repository Standards](#repository-standards)  
- [License](#license)  
- [Acknowledgements](#acknowledgements)

---

## 📚 Project Overview

The system enables users to:

- Create, view, update, and delete student records  
- Manage course offerings  
- Enroll students in courses with validation  
- Interact via a structured console interface

This application is designed with **separation of concerns**, ensuring each class handles a distinct responsibility.

---

## 🧩 System Architecture

| Class Name               | Responsibility                                      |
|--------------------------|-----------------------------------------------------|
| `Student.java`           | Models student attributes and behaviors             |
| `CourseSystem.java`      | Manages course-related data and operations          |
| `EnrollmentSystem.java`  | Handles enrollment logic and validation             |
| `StudentSystem.java`     | Coordinates student record management               |
| `Main.java`              | Entry point; integrates all subsystems              |

Compiled `.class` files are located in the `out/` directory.

---

## ✨ Features

- ✅ Modular class design for scalability and clarity  
- 🔍 Input validation and error handling  
- 🧮 Enrollment logic with course-student mapping  
- 📁 Organized source and output directories  
- 📄 Academic formatting and professional documentation

---

## 🧰 Development Environment

- **Language**: Java (JDK 8+)  
- **IDE**: IntelliJ IDEA  
- **Version Control**: Git  
- **Project Structure**:
  - `src/` – Source code  
  - `out/` – Compiled output  
  - `.idea/`, `.iml`, `.classpath`, `.project` – IDE metadata  
  - `.gitignore` – Excludes build artifacts and IDE files

---

## ⚙️ Installation & Execution

### Manual Execution

```bash
javac src/*.java
java -cp src Main
