# CPSC 210 Personal Project

Check out a video demo [here](https://www.youtube.com/watch?v=-95utZrG1fg)!

## A budget organization app


For my CPSC 210 personal project, I created a desktop application with which one can design and visualize their
financial budget. The user is able to create their own individual budget and view details like the balance, expense and 
income totals, and a graphical representation of each. 

Many students receive loans, bursaries, and other forms of financial support, along with income from a part-time job. 
*All* spend large amounts of money on tuition, rent, textbooks, groceries, and more. I have created many complicated
Excel spreadsheets in frequent attempts to keep these costs organized, but almost always end up starting from scratch 
once it becomes unmanageable or outdated. With this project I hope to create an application that is clear, easy to use, 
and easily edited to maintain a higher level of financial organization. 

This app will be useful for: 
- students and professionals organizing their own personal budget
- small business owners aiming to keep track of startup costs


  
#### User Stories, Phase 1:
- As a user, I want to be able to add costs to a list of my expenses
- I want to be able to add one or multiple source(s) of income to a list of my incomes
- I want to be able to see the total of my expenses and income, and the balance of these totals
- I want to be able to see the description of my income sources in one list and my expenses in another



#### User Stories, Phase 2: 
- As a user, I want to be able to save my budget to a file
- As a user, I want to be able to reload my budget from a file in the future



#### Phase 4, Task 2:
For this task, I chose to design and test a class in the model package that is robust. I added this functionality to 
the User class. The addCost and addFund methods in this class will now throw a NegativeAmountException if the user
attempts to enter a negative amount, and will throw a NumberFormatException if the user attempts to enter an amount 
that includes letters or any other non-numerical characters. The removeTransaction method will only throw a 
NumberFormatException if necessary, as there are other guards that prevent a negative amount from reaching this method.

These exceptions are caught by methods in TransactionWindow and in the classes that extend TransactionWindow. When the 
user attempts to enter an amount that is negative or is not a number in the GUI, dialog windows will pop up with 
appropriate messages for the user. 


#### Phase 4, Task 3: 
For this task, students were told to reflect on the design in their UML class diagram and identify any refactoring they 
would have done if more time were available. My key changes are as follows:
- My current design includes a User class with three fields of type Transactions. Each Transactions object holds an 
ArrayList of Transaction objects. The three fields represent the income, expenses, and all transactions of the user. 
Because many of my methods rely on transactions being sorted into sources of income and expenses, I left the income and
expenses fields in the User class. When building the GUI, however, I realized that since I wanted the budget table to 
display transactions in the order in which they were added, I needed a master collection of all of that user's 
transactions. With more time, I would remove the income and expenses fields from the User class and make the 
allTransactions Transactions object implement the Map interface. The key for each transaction in the Map would be either
"income" or "expense". To preserve insertion order I would have to use a LinkedHashMap for the Transactions object and 
learn its associated methods. Then, for methods that operate on only income or only expenses, I could use the keySet() 
method to quickly separate the transactions in the Map into incomes and expenses. 
- The BudgetAppGUI class in this project is very long. I would have liked to simplify the class by creating new classes 
for the different panels present, each of which has distinct functionalities. This point is expanded on in the following
point. 
- In creating the GUI for this project, we were learning how to use Java Swing on our own along the way. Upon
completion, I realized that much of the code for setting up graphic details could be extracted from various methods in
the BudgetAppGUI class to decrease its length. My project's layout consists of four different panels placed in one
JFrame which uses a GroupLayout. Each of these panels have different sizes and distinct functionalities. To maintain
a clean aesthetic, however, features like colour, opacity, and border design are common amongst the four panels (and 
any overlaid panels used for organization). Code used for design of these features can be extracted into an abstract 
class, which can then be extended by the panel classes mentioned above. Together, these changes would greatly reduce the 
length of the BudgetAppGUI class and make progression through the class much easier to follow. 
- As it stands in the UML class design diagram, the StartUpWindow class and its field of type JsonReader is not 
associated with any others. Yet, this class represents the window that first opens to the user and prompts the user to 
create or load a previous user's budget. The StartUpWindow class uses a static method to set the User field in 
BudgetAppGUI, after which it calls the GUI constructor to create the GUI and advance to the other features of the 
application. This lack of visual association seems out of place with the other Window classes in the diagram; it works 
in the context of this application, but may be better practice to make the relationship between StartUpWindow and 
BudgetAppGUI congruent with the rest (i.e., through StartUpWindow having a field of type BudgetAppGUI, and initializing 
it or setting it visible after the user has been set). As well, if functionality is added to create a new user or to 
load a different user while the application is running, this will have to be changed. 
