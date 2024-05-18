package se.kth.iv1350.amazingpos.main.integration;

/**
 * Thrown when the database fails to perform a requested operation.
 */
public class DatabaseFailureException extends Exception{
    
    /**
     * Creates a new instance representing the condition described in the specified message.
     * 
     * @param reason The reason for the database failure.
     */
    public DatabaseFailureException(String reason){
        super(reason);
    }
}
