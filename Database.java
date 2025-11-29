
            
                CREATE TABLE "Student" (
                "First_name" TEXT PRIMARY KEY NOT NULL,
                "Last_name" TEXT NOT NULL,
                "Password" TEXT NOT NULL,
                "Confirmation_Password" TEXT  NOT NULL
            );
            CREATE TABLE "StudentCourse"(
                "StudentCourseID" TEXT PRIMARY KEY NOT NULL,
                "First_name" TEXT  NOT NULL,
                "CurrentGrade" REAL NOT NULL,
                "AttendancePercentage" REAL NOT NULL,
                "ParticipationPercentage" REAL NOT NULL,
                "DiscussionPercentage" REAL NOT NULL,
                "HomeworkPercentage" REAL NOT NULL,
                "EvaluationPercentage" REAL NOT NULL,
                "QuizzesPercentage" REAL NOT NULL,
                "ExamPercentage" REAL NOT NULL,
                "MidTermPercentage" REAL NOT NULL,
                "ProjectPercentage" REAL NOT NULL,
                "FinalPercentage" REAL NOT NULL,
                FOREIGN KEY (First_name) REFERENCES Student(First_name)
            );
            CREATE TABLE "Course" (
                "CourseID" INTEGER PRIMARY KEY NOT NULL,
                "StudentCourseID" TEXT NOT NULL,
                "CourseName" TEXT NOT NULL,
                "ClassTimes" TEXT NOT NULL,
                "Year" INTEGER NOT NULL,
                FOREIGN KEY (StudentCourseID) REFERENCES StudentCourse(StudentCourseID)
            
            );
            CREATE TABLE "StudentAssignment"(
                "AssignmentID" INTEGER PRIMARY KEY NOT NULL,
                "AssignmentName" TEXT NOT NULL,
                "CourseID" INTEGER NOT NULL,
                "AssignmentType" TEXT NOT NULL,
                "TimeDue" TIME NOT NULL,
                "DueDate" DATE NOT NULL,
                "PossiblePointsPerAssignment" REAL NOT NULL,
                FOREIGN KEY (CourseID) REFERENCES Course(CourseID)
            
            );
            
            
