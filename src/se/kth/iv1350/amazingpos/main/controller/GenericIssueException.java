package se.kth.iv1350.amazingpos.main.controller;

/**
 * Thrown when an issue is caught but the cause of the issue is unknown.
 */
public class GenericIssueException extends Exception{
    
    /**
     * Creates a new instance with a specified message and the original exception which caught the issue.
     * 
     * @param reason The message describing the issue caught by the exception
     * @param exceptionCausedByIssue The exception that caught the issue and resulted in this exception
     */
    public GenericIssueException(String reason, Exception exceptionCausedByIssue){
        super(reason, exceptionCausedByIssue);
    }
}
