
public class SimpleRecord implements Record {

    private final long ID;
    private String Details;
    private final String password;

    public SimpleRecord(long ID, String Details, String password) {
        this.ID = ID;
        this.Details = Details;
        this.password = password;
    }

    public void setDetails(String Details) {
        this.Details = Details;
    }

    @Override
    public boolean updateDetails(String newDetails, String passwordAttempt) {
        if (checkPassword(passwordAttempt)) {
            setDetails(newDetails);
            return true;
        }
        return false;
    }

    @Override
    public boolean checkPassword(String password) {
        return this.password.equals(password);
    }

    @Override
    public String toCsv() {
        return ID + "," + Details + "," + password;
    }

    @Override
    public long getId() {
        return ID;
    }

    @Override
    public String getDetails() {
        return Details;
    }
}
