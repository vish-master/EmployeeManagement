package com.dima.employeemanager.enums;

public enum ErrorType {

    GENERAL_ERROR(601, "GENERAL ERROR", "General error", true),
    FAIL_TO_GENERATE_ID(602, "FAIL TO GENERATE ID", "Couldn't generate an ID", false),
    ID_DOES_NOT_EXIST(607, "ID DOES NOT EXIST", "This ID does'nt exist", false),
    INVALID_AGE(608, "INCORRECT AGE VALUE", "Age should be bigger than 0", false),
    EMPLOYEE_DOES_NOT_EXIST(609,"EMPLOYEE WITH INSERTED ID DOES NOT EXIST", "Employee with inserted ID does not exist", false),
    MUST_INSERT_A_VALUE(610,"Employee Object null value","Employee object can nor be null", false);

    private int errorNumber;
    private String ErrorName;
    private String errorMessage;
    private boolean isShowStackTrace;

    private ErrorType(int errorNumber, String errorName, String internalMessage, boolean isShowStackTrace) {
        this.errorNumber = errorNumber;
        this.ErrorName = errorName;
        this.errorMessage = internalMessage;
        this.isShowStackTrace = isShowStackTrace;
    }

    private ErrorType(int errorNumber, String internalMessage) {
        this.errorNumber = errorNumber;
        this.errorMessage = internalMessage;
    }

    public String getErrorMessage() {
        return errorMessage;
    }

    public boolean isShowStackTrace() {
        return isShowStackTrace;
    }

    public int getErrorNumber() {
        return errorNumber;
    }

    public String getErrorName() {
        return ErrorName;
    }

    public void setErrorName(String errorName) {
        ErrorName = errorName;
    }

}
