package library.ui.main;

public class CountStat {
    private String field;
    private long count;

    public CountStat(String field, long count) {
        this.field = field;
        this.count = count;
    }

    public String getField() {
        return field;
    }

    public void setField(String field) {
        this.field = field;
    }

    public long getCount() {
        return count;
    }

    public void setCount(long count) {
        this.count = count;
    }
}
