
public class AnalyzedTextRecord implements Record {

    private final long ID;
    private String Details;
    private final String password;
    private int wordCount;
    private int characterCount;
    private int vowelCount;

    public AnalyzedTextRecord(long ID, String Details, String password) {
        RecordManager.validateDetails(Details);
        this.ID = ID;
        this.Details = Details;
        this.password = password;
        this.performAnalysis();
    }

    @Override
    public long getId() {
        return ID;
    }

    @Override
    public String getDetails() {
        return Details + " (Words: " + wordCount + ", Characters: " + characterCount + ", Vowels: " + vowelCount + ")";
    }

    @Override
    public boolean updateDetails(String newDetails, String passwordAttempt) {
        if (checkPassword(passwordAttempt)) {
            this.Details = newDetails;
            this.performAnalysis();
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
        return "Analyzed," + ID + "," + RecordManager.escapeCsv(Details) + "," + RecordManager.escapeCsv(password);
    }

    private void performAnalysis() {
        if (Details == null || Details.trim().isEmpty()) {
            this.wordCount = 0;
            this.characterCount = 0;
            this.vowelCount = 0;
            return;
        }

        this.wordCount = countWords(this.Details);
        this.characterCount = countCharacters(this.Details);
        this.vowelCount = countVowels(this.Details);
    }

    private boolean isVowel(char c) {
        return "AEIOUaeiou".indexOf(c) != -1;
    }

    private int countWords(String text) {
        if (text == null || text.trim().isEmpty()) {
            return 0;
        }
        String[] words = text.trim().split("\\s+");
        return words.length;
    }

    private int countCharacters(String text) {
        if (text == null) {
            return 0;
        }
        return text.length();
    }

    private int countVowels(String text) {
        if (text == null || text.trim().isEmpty()) {
            return 0;
        }
        int count = 0;
        for (char c : text.toCharArray()) {
            if (isVowel(c)) {
                count++;
            }
        }
        return count;
    }

}
