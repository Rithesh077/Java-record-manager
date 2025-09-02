
public class SimpleRecord implements Record {

    private final long ID;
    private String Details;
    private final String password;

    private void validatePassword(String password) {
        if (password == null || password.length() < 8) {
            throw new IllegalArgumentException("Password must be at least 8 characters long.");
        }

        boolean hasUpper = false;
        boolean hasLower = false;
        boolean hasDigit = false;
        boolean hasSpecial = false;
        String specialChars = "!@#$%^&*()";

        for (char c : password.toCharArray()) {
            if (Character.isUpperCase(c)) {
                hasUpper = true;
            }
            if (Character.isLowerCase(c)) {
                hasLower = true;
            }
            if (Character.isDigit(c)) {
                hasDigit = true;
            }
            if (specialChars.contains(String.valueOf(c))) {
                hasSpecial = true;
            }
        }

        if (!hasUpper || !hasLower || !hasDigit || !hasSpecial) {
            throw new IllegalArgumentException("Password must contain an uppercase letter, a lowercase letter, a number, and a special character.");
        }
    }

    public SimpleRecord(long ID, String Details, String password) {
        if (Details == null || Details.trim().isEmpty() || Details.contains(",")) {
            throw new IllegalArgumentException("Details cannot be null, empty, or contain commas.");
        }

        validatePassword(password);

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
        return "Simple," + ID + "," + Details + "," + password;
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
