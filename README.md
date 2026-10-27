Grocery Inventory Management System
This console-based application is designed to help grocery businesses manage inventory, products, suppliers, orders, payments, refunds, and user accounts through a structured, menu-driven interface.

The project demonstrates the practical application of Object-Oriented Programming (OOP) principles, including encapsulation, inheritance, polymorphism, and abstraction.

Project Overview
The Grocery Inventory Management System aims to simplify inventory operations by providing an organized platform for managing grocery store activities.

The system supports two user roles:

Administrator: Manages user accounts and generates inventory reports.
Staff: Manages products, categories, suppliers, orders, payments, refunds, and personal account information.
The application uses Java file handling to store and retrieve information from local text files.

Technologies Used
Technology	Purpose
Java	Core application development
Object-Oriented Programming	System architecture and class design
Java File I/O	Local data storage and retrieval
Java Collections	Data organization and processing
Key Features
1. User Authentication and Management
Administrator

Register and log in to administrator accounts.
Add new staff members.
Delete existing users.
View user information.
Generate inventory reports.
Staff

Log in using registered credentials.
View personal account information.
Update passwords, phone numbers, and email addresses.
Access inventory management functions.
Log out securely through the application menu.
2. Product Management
Add new products with automatically generated product IDs.
View available products and their details.
Update product names, unit costs, selling prices, stock quantities, categories, and suppliers.
Delete products from the inventory.
Validate user input to reduce incorrect data entry.
3. Category Management
Add new product categories.
Automatically generate unique category IDs.
View existing categories.
Update category information.
Organize products into categories for easier inventory management.
4. Supplier Management
Register new suppliers with contact information.
Automatically generate supplier IDs.
View supplier records.
Update supplier names, phone numbers, and email addresses.
Associate products with suppliers.
5. Order Management
Create new product orders.
Automatically generate order IDs and order dates.
Record product quantities, prices, and order amounts.
View existing order records.
Delete orders when required.
6. Payment Management
Process payments based on order IDs.
Record payment amounts, dates, and payment methods.
Generate payment identifiers.
View payment transaction history.
7. Refund Management
Create refund records associated with payments.
Record refund amounts and reasons.
View refund information.
Cancel refund records when necessary.
8. Inventory Reporting
Generate product inventory reports.
Display product IDs, names, quantities, categories, and suppliers.
Present inventory information in formatted tables for easier monitoring.
Object-Oriented Programming Concepts
This project demonstrates several important OOP principles.

OOP Concept	Application
Encapsulation	Organizes data and related methods within classes
Inheritance	Supports shared user-related behavior through class relationships
Polymorphism	Allows specialized implementations of shared operations
Abstraction	Separates general user operations from their concrete implementations
Modularity	Divides the system into classes responsible for individual functions
The system uses multiple Java classes to separate responsibilities, improving code organization and maintainability.

System Architecture
The application is organized into functional components:

Component	Responsibility
UserMain	Main entry point and role selection
UserDriver	General user operations
ConcreteUserDriver	User authentication and profile updates
Administrator	Administrator operations and user management
Staff	Staff authentication and account management
Home	Main inventory menu and navigation
Product	Product and inventory management
Category	Product category management
Supplier	Supplier information management
Order	Order creation and management
Payment	Payment processing and transaction history
Refund	Refund processing and management
Report	Inventory report generation
Additional supporting Java classes handle data models and other application operations.

Application Workflow
Administrator Workflow
Launch the application.
Select the Administrator section.
Register or log in to an administrator account.
Access the administrator menu.
Manage staff accounts or generate product reports.
Log out after completing administrative tasks.
Staff Workflow
Launch the application.
Select the Staff section.
Log in using staff credentials.
Access the staff menu.
View or update personal account information.
Enter the inventory management system.
Manage products, categories, suppliers, orders, payments, and refunds.
Return to the staff menu and log out.
Installation and Setup
Prerequisites
Java Development Kit (JDK)
Java-compatible IDE or terminal
Windows, macOS, or Linux
Steps to Run
1. Clone the repository

git clone https://github.com/356Lqyu/Grocery-Inventory-Management-System.git
2. Navigate to the project directory

cd Grocery-Inventory-Management-System
3. Compile the Java source files

If the Java files are located in a src directory:

javac -d out src/*.java
4. Run the application

java -cp out UserMain
Note: These commands assume the source files use the default Java package and UserMain is the application entry point. Adjust the paths or commands if your local project structure differs.

The application uses local text files for data persistence. Some operations may require existing data files, and the repository may need additional initialization before the full workflow can be used.

Data Storage
The system uses Java file handling to manage application records.

Data is stored in local text files for operations such as:

User account management
Product information
Supplier information
Category information
Order records
Payment transactions
Refund records
This approach demonstrates file reading, writing, updating, and record management without requiring an external database server.
