# Learning-Management-System

## Description:
Developed a simulation of Learning management System for teachers and students. Allows teachers to generate MCQ tests for students to attempt and generate a cumulative report of the test attempted by the students.

## Attributes of this project include:

1. Creation  of a user ID for both teacher and student.
2. Teacher uploads number of quizes as per their respective subjects.
3. The students can attempt the test and they can review their marks over various quizes they have attempted.

JAVA FILES AND CLASS FILES

*loginTest.java

1. Contains the java source code for this project and contains the main class.
2. It contains the source code for creation of user ID.
3. Contains class for MCQ 
4. accountCreator is a class for creating an account
5. Person.class contains the details of teachers and students(super class of teacher &student)
6. Teacher.class contains the functionality of teachers.
7. Student.class contains the functionality of students.


*accountLoginException.java

1. The errors are handled for the above class.
2. ExistingIdException.class throws an error if an ID already exists.
3. ExistingMailException.class throws an error if an MailID already exists.
4. InvalidMailIdException.class throws an error if an entered mail ID doesnt match the format.
5. InvalidCourseException.class throws an error if the course is not available.
6. UnAvailableCourseException.class throws an error if the course is already assigned by another teacher.

OOPS Concepts Used

1. Classes and Objects
2. Polymorphism(Method overridding through intrefaces)
3. Inheritance
4. Exception Handling
5. Modularity

