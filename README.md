User Details
1. GET /userdetails/getbyid/{emailId}

Retrieves user details by email ID.
Example URL: http://localhost:8080/userdetails/getbyid/{emailId}
Method: GET
Path Variable: emailId (String)

2. GET /userdetails/getbyuserid/{userId}

Retrieves user details by user ID.
Example URL: http://localhost:8080/userdetails/getbyuserid/{userId}
Method: GET
Path Variable: userId (int)

3. POST /userdetails/verifylogin

Verifies user login.
Example URL: http://localhost:8080/userdetails/verifylogin
Method: POST
Request Body: UserDetails object

4. POST /userdetails/adminlogin

Verifies admin login.
Example URL: http://localhost:8080/userdetails/adminlogin
Method: POST
Request Body: UserDetails object

5.POST /userdetails/adduser

Adds user details.
Example URL: http://localhost:8080/userdetails/adduser
Method: POST
Request Body: UserDetails object

----------------------------------------------------------------------------------------------------------------------------------------

Here are the complete URLs for the endpoints in your `AdminController` class:

1. **GET /admin-controller/get-inactive-user**
   - Retrieves all inactive users.
   - Example URL: `http://localhost:8080/admin-controller/get-inactive-user`
   - Method: GET

2. **GET /admin-controller/get-locked-user**
   - Retrieves all locked users.
   - Example URL: `http://localhost:8080/admin-controller/get-locked-user`
   - Method: GET

3. **GET /admin-controller/get-uncleared-cheque**
   - Retrieves all uncleared cheques.
   - Example URL: `http://localhost:8080/admin-controller/get-uncleared-cheque`
   - Method: GET

4. **PUT /admin-controller/activate-user/{userId}**
   - Activates a user by user ID.
   - Example URL: `http://localhost:8080/admin-controller/activate-user/{userId}`
   - Method: PUT
   - Path Variable: userId (int)

5. **PUT /admin-controller/update-cheque-status**
   - Updates cheque status.
   - Example URL: `http://localhost:8080/admin-controller/update-cheque-status`
   - Method: PUT
   - Request Body: ChequeDetails object

6. **POST /admin-controller/verify-clearance**
   - Verifies cheque clearance.
   - Example URL: `http://localhost:8080/admin-controller/verify-clearance`
   - Method: POST
   - Request Body: ChequeDetails object

----------------------------------------------------------------------------------------------------------------------------------------

Here are the complete URLs for the endpoints in your `ChequeDetailsController` class:

1. **PUT /chequedetails/create-cheque**
   - Adds cheque details.
   - Example URL: `http://localhost:8080/chequedetails/create-cheque`
   - Method: PUT
   - Request Body: ChequeDetails object

2. **GET /chequedetails/get-cheque/{chequeNumber}**
   - Retrieves cheque details by cheque number.
   - Example URL: `http://localhost:8080/chequedetails/get-cheque/{chequeNumber}`
   - Method: GET
   - Path Variable: chequeNumber (String)

----------------------------------------------------------------------------------------------------------------------------------------

Here are the complete URLs for the endpoints in your `FixedDepositDetailsController` class:

1. **POST /fddetails/create-fd**
   - Adds fixed deposit details.
   - Example URL: `http://localhost:8080/fddetails/create-fd`
   - Method: POST
   - Request Body: FixedDepositDetails object

2. **GET /fddetails/getAll/{userId}**
   - Retrieves all fixed deposits by user ID.
   - Example URL: `http://localhost:8080/fddetails/getAll/{userId}`
   - Method: GET
   - Path Variable: userId (int)

----------------------------------------------------------------------------------------------------------------------------------------
Here are the complete URLs for the endpoints in your `TransactionDetailsController` class:

1. **POST /transactionsdetails/add-details**
   - Adds transaction details.
   - Example URL: `http://localhost:8080/transactionsdetails/add-details`
   - Method: POST
   - Request Body: TransactionDetails object

2. **GET /transactionsdetails/getall/{accNumber}**
   - Retrieves all transaction details by issuer account number.
   - Example URL: `http://localhost:8080/transactionsdetails/getall/{accNumber}`
   - Method: GET
   - Path Variable: accNumber (long)
----------------------------------------------------------------------------------------------------------------------------------------



