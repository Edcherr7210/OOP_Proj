# Overview
PrioritCal is a desktop-based academic task visualization tool designed to help students view and manage assingments within a calendar interface, while supporting prioritiy based task ordering.

The project focuses on calendar-driven visualization, CSV-based data ingestion, and event-driven UI design, allowing users to interact with assignments by date and view relevant details in a structured layout.

> This project was developed collaboratively.

# Problem Statement
​The current SNHU system does not provide students with a clear way to view or prioritize their assignments based on grade impact.  Without a prioritized assignment list, students struggle to determine which tasks require immediate attention.​

Student have trouble seeing their grades on the SNHU calendar causing them to miss their assignment making their grade worse in class.  As a result, students often miss deadlines, negatively affecting their overall course performance.

PrioritiCal addresses this by combining:
- A full calendar UI
- CSV-imported assignment data
- A priority-aware task list surfaced through the interface

## Core Functionality:
- Full-screen calendar interface with month navigation
- Clickable date buttons that display assignments for the selected day
- CSV-based assignment import
- Right-panel assignment detail view
- Priority-based ordering of assignments (collaborative logic)

## Tech Stack
-Language: Java
-UI Framework: Swing
-Database: SQLite3
-Data Input: CSV
-Platform: Cross-platform (desktop)

## Contribution:
  ### Mirza:
  - Designed and integrate a full-screen Swing calendar interface
  - Built month and day navigation using YearMonth and LocalDate
  - Implemented even-driven UI logic
  - - Date Selection
    - Month switching
    - file import
  ### Eduardo:
  - Designed and integrated a full-screen Sign-up and login system
  - Integrated a SQLite3 database
  ### John (JAMSalmon):
  - Integrated CSV parser
  - Created logic to show prioritization of assignements based of points
  - Helped with integrating the SQLite3 database
  - Formated assignment strings to show class name, assignment, and points using regex and appending strings

## Running the Project
> Javac Main.java

> java Main

> Requires a JDK (11+ recommended)

## Disclaimers
ProritiCal is a personal academic tool and not intended for production use
