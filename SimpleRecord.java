
public class SimpleRecord implements Record {

    private final long ID;
    private final String Details;

    public SimpleRecord(long ID, String Details) {
        this.ID = ID;
        this.Details = Details;
    }

    @Override
    public String toCsv() {
        return ID + "," + Details;
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
