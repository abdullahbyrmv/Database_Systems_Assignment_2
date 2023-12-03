# Database_Systems_Assignment_2

Follow these steps to set up and run the application:
<ol>
<li>
Clone the repository by using the command <b>git clone https://github.com/abdullahbyrmv/Database_Systems_Assignment_2.git</b> or download the source code as zip file.
</li>
<li>
Open the project in your IDE (IntelliJ IDEA, VisualStudio Code (VSCode), Eclipse etc.)
</li>
<li>
Add the <b>postgresql-42.6.0.jar</b> file to your project as a library or dependency from <b>src - > dependencies</b> package.
</li>
<li>
Create postgresql database inside docker server or local pgadmin. 
</li>
<li>
Generate tables by using sql scripts from <b>sql_scripts.sql</b> file which is placed inside <b>src - > SQL</b> package.
</li>
<li>
After the tables are successfully created in database then we can connect to that database from java application.
</li>
<li>
Go to <b>AbstractDao</b> class which is inside <b>src - > abstractDao</b> package.
</li>
<li>
You should fill <b>url, user</b> and <b>password</b> variables based on your database. 
</li>
<li>
In url variable, write your hostname and port number in hostname:port_number part and write your database name in Database_Name part. Url example, String url = "jdbc:postgresql://localhost:5432/Database_Systems_10344";
</li>
<li>
Write your username in user variable. For example, String user = "postgres";
</li>
<li>
Write the password of your database in password variable. For example, String password = "myPassword";
</li>
<li>
Now after doing all these steps, you can run JDBC application from <b>Main</b> class which resides in <b>src - > main</b> package.
</li>
<li>
After running the application, you can see main menu in console and perform actions which are listed on menu. You should type the number of the action in the menu to perform that action.
For example, When you run the application you should see that menu in console:

Welcome to JDBC Application

In which table would you like to operate?<br>
1.book table<br>
2.author table<br>
3.customer table<br>
4.order table<br>
5.book_detail table<br>
6.order_detail table<br>
7.Show table names and columns<br>
8.Show column details<br>
9.Show primary keys<br>
10.Show foreign keys<br>
11.Close application<br>
For example, if you want to perform actions in book table, then you should type 1 in console as input in order to go
book table.
</li>
</ol>
