# API Endpoints

## AccountDetailsController

### Get Account Details by User ID
- **Endpoint:** `/accountdetails/getbyid/{userId}`
- **Method:** GET

### Get Account Details by Account Number
- **Endpoint:** `/accountdetails/get-acc-by-accNum/{accNum}`
- **Method:** GET

### Add Account
- **Endpoint:** `/accountdetails/addaccount`
- **Method:** POST

----------------------------------------------------------------------------------------------------------------------------------------

# API Endpoints

## AdminController

### Get All Inactive Users
- **Endpoint:** `/admin-controller/get-inactive-user`
- **Method:** GET

### Get All Locked Users
- **Endpoint:** `/admin-controller/get-locked-user`
- **Method:** GET

### Get All Uncleared Cheques
- **Endpoint:** `/admin-controller/get-uncleared-cheque`
- **Method:** GET

### Activate User
- **Endpoint:** `/admin-controller/activate-user/{userId}`
- **Method:** PUT

### Update Cheque Status
- **Endpoint:** `/admin-controller/update-cheque-status`
- **Method:** PUT

### Verify Cheque Clearance
- **Endpoint:** `/admin-controller/verify-clearance`
- **Method:** POST

----------------------------------------------------------------------------------------------------------------------------------------

# API Endpoints

## ChequeDetailsController

### Create Cheque
- **Endpoint:** `/chequedetails/create-cheque`
- **Method:** PUT

### Get Cheque Detail by Cheque Number
- **Endpoint:** `/chequedetails/get-cheque/{chequeNumber}`
- **Method:** GET

### Get Cheques by Account Number
- **Endpoint:** `/chequedetails/get-cheques-by-acc/{accountNumber}`
- **Method:** GET
----------------------------------------------------------------------------------------------------------------------------------------
# API Endpoints

## FixedDepositDetailsController

### Create Fixed Deposit
- **Endpoint:** `/fddetails/create-fd`
- **Method:** POST

### Get All Fixed Deposits by User ID
- **Endpoint:** `/fddetails/getAll/{userId}`
- **Method:** GET

----------------------------------------------------------------------------------------------------------------------------------------
# API Endpoints

## UserDetailsController

### Get User Details by Email ID
- **Endpoint:** `/userdetails/getbyid/{emailId}`
- **Method:** GET

### Get User Details by User ID
- **Endpoint:** `/userdetails/getbyuserid/{userId}`
- **Method:** GET

### Verify Login
- **Endpoint:** `/userdetails/verifylogin`
- **Method:** POST

### Admin Login Verification
- **Endpoint:** `/userdetails/adminlogin`
- **Method:** POST

### Add User
- **Endpoint:** `/userdetails/adduser`
- **Method:** POST

----------------------------------------------------------------------------------------------------------------------------------------
# API Endpoints

## TransactionDetailsController

### Add Transaction Details
- **Endpoint:** `/transactionsdetails/add-details`
- **Method:** POST

### Get All Transaction Details by Account Number
- **Endpoint:** `/transactionsdetails/getall/{accNumber}`
- **Method:** GET

### Process Transaction
- **Endpoint:** `/transactionsdetails/process-transaction`
- **Method:** POST


