package dataacess;

public class MyDatabaseException extends RuntimeException {
    public MyDatabaseException(String databaseError) {
        super(databaseError);
    }
}
