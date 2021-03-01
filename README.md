# JDBC-Attendance-System
An Attendance Management Software System for making the manual attendance work in ease. This JDBC application is filled with the details of the students of IOT(Sem-IV) for the demonstration. It allows a subject teacher to perform various basic operations such as: Take Every Day Attendance, Update Attendance, Get the debar list (<75%), Displaying the single person attendance percentage, and can add or delete a single record in the database.
Unique Features: 1. Can Take Attendance in any order
                             2. Can Update Attendance of any student of any day
                             3. Debar list till that date at any instance (<75%)
                             4. Can Insert or Delete a student details (due to late join or early exit)

# Assumptions and Dependencies
- The teacher must be having MYSQL with jdbc database and Java Environment
- The teacher should create a table IOT(Roll int,Name VARCHAR(30));
- The teacher must have the Roll no, Name details of the students in his jdbc.IOT
- INSERT INTO IOT(Roll,Name) VALUES(1,’Student 1’),(3,’Student 2’),()……(n,’Student X’); is the statement for entering the values inside a databse
- This software is developed only for a single class and single subject manual attendance
- Teacher must look into the code and change his/her “root” password of his individual database

# Components (Functions)
1. **reqDate():** It is the method which returns a string in “Ddd_mm_yy” for the database colomun names each and every day
2. **getAttendance():** This method takes statements variable and date string and creates an column with default null vales and then asks teacher to enter the roll.no and updates the required student’s attendance as 1.
3. **getDebarlist():** This method makes an complex SQL query which calculates all the present classes and that data is retrieved and less than 75% rollno’s and percentages are displayed.
4. **getPercentage():** Similar logic as getDebarlist() method but which is simple for a single required student is calculated.
5. **updateDetails() & deleteDetails():** To Insert or Delete functions of an entry in the database
