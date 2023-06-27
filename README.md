# Employee Management System (EMS)

The Employee Management System (EMS) is a web-based application that allows HR (Human Resources) personnel to manage employee data, mark attendance, generate reports, and perform other administrative tasks.

## Technologies Used

- Java
- Spring Boot
- Spring MVC
- Hibernate
- MySQL

## Features

- User Authentication: HR personnel can register and sign in to the system using their email and password.
- Employee Management: HR personnel can add new employees, update their details, and delete employee records.
- Attendance Tracking: HR personnel can mark attendance for employees and track their attendance history.
- Reports Generation: HR personnel can generate attendance reports for individual employees.
- Data Validation: Input data is validated using validation annotations to ensure data integrity.

## Getting Started

To get started with the EMS application, follow these steps:

1. Clone the repository: `git clone <repository-url>`
2. Set up the database: Create a MySQL database and update the database configuration in the `application.properties` file.
3. Build and run the application: Use an IDE (Integrated Development Environment) like IntelliJ IDEA or Eclipse to import the project and run it.
4. Access the application: Open a web browser and navigate to `http://localhost:8080` to access the EMS application.

## Usage

- Register HR Admin: Use the HRController's `/admin/registerAdmin` endpoint to register an HR admin with their email and password.
- Add Employee: Use the HRController's `/admin/registerEmployee` endpoint to add a new employee with their details.
- Mark Attendance: Use the HRController's `/admin/markAttendance` endpoint to mark attendance for an employee.
- Generate Attendance Report: Use the HRController's `/admin/attendanceReport/{id}` endpoint to generate an attendance report for an employee.
- Update Employee Data : Employee can update his/her information `/employee/update`
- Get All Employee List : Employee can get all emploees information `/employee/getAll`
- Delete Employee : Use the HRController `/admin/delete/empId` endpoint to delete the employee.


## API Endpoints

- `POST /admin/registerAdmin`: Register an HR admin.
- `POST /admin/registerEmployee`: Add a new employee.
- `POST /admin/markAttendance`: Mark attendance for an employee.
- `GET /admin/attendanceReport/{id}`: Generate an attendance report for an employee.
- `POST /signIn`: Sign in as a user.
- `DELETE /signOut/{email}`: Sign out as a user.
- `GET /employee/getAll`: Get a list of all employees.
- `PUT /employee/update`: Update an employee's details.
- `GET /admin/emp/{id}`: Get an employee by ID.
- `DELETE /admin/delete/{empId}`: Delete an employee by ID.

## Code Details

### Models

1. Employee: Attributes: `empId`, `empFirstName`, `empLastName`, `employeeEmail`, `employeePhoneNumber`, `employeeAddress`, `employeeJobRole`, `employeeSalary`, `employeeHireDate`
2. HR: Attributes: `adminId`, `adminEmail`, `adminPassword`
3. Status (Enum): Values: `Present`, `Absent`, `Late`
4. User: Attributes: `userId`, `userEmail`, `userPassword`
5. Attendance: Attributes: `attendanceId`, `localDate`, `employeeId`, `status`
6. AuthenticationToken: Attributes: `authTokenId`, `authToken`, `authTokenDate`, `user`

### Controllers

1. EmployeeController:
   - Method: `GET`, Method Name: `getAllEmployee`, Endpoint: `/employee`, Parameters: `email`, `token`, Response: `List<Employee>`
2. HRController:
   - Method: `POST`, Method Name: `registerHR`, Endpoint: `/admin`, Parameters: `hr`, Response: `String`
   - Method: `POST`, Method Name: `createEmployee`, Endpoint: `/admin`, Parameters: `employee`, Response: `String`
   - Method: `POST`, Method Name: `markAttendance`, Endpoint: `/admin`, Parameters: `attendanceRequest`, Response: `String`
   - Method: `GET`, Method Name: `generateAttendanceReport`, Endpoint: `/admin`, Parameters: `id`, Response: `byte[]`
   - Method: `GET`, Method Name: `getEmployeeById`, Endpoint: `/admin`, Parameters: `id`, Response: `Employee`
   - Method: `DELETE`, Method Name: `deleteEmployeeById`, Endpoint: `/admin`, Parameters: `empId`, Response: `String`
3. UserController:
   - Method: `POST`, Method Name: `login`, Endpoint: `/`, Parameters: `user`, Response: `String`
   - Method: `GET`, Method Name: `logout`, Endpoint: `/`, Parameters: `token`, Response: `String`

### Services

1. EmployeeService:
   - Method Name: `getAllEmployees`, Parameters: None, Return Type: `List<Employee>`
2. HRService:
   - Method Name: `registerHR`, Parameters: `hr`, Return Type: `String`
   - Method Name: `createEmployee`, Parameters: `employee`, Return Type: `String`
   - Method Name: `markAttendance`, Parameters: `attendanceRequest`, Return Type: `String`
   - Method Name: `generateAttendanceReport`, Parameters: `id`, Return Type: `byte[]`

## Contributing

Contributions to the Employee Management System (EMS) application are welcome! If you have any ideas, improvements, or bug fixes, please submit a pull request or open an issue.

## License

The EMS application is open-source and available under the [MIT License](https://opensource.org/licenses/MIT).
