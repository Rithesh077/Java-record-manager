
public interface Record {

    long getId();

    String getDetails();

    String toCsv();

    public boolean checkPassword(String password);

    public boolean updateDetails(String newDetails, String passwordAttempt);
}
