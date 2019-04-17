package Bean;

/**
 * @author dmrfcoder
 * @date 2019-04-17
 */
public class Moment {
    private String momentContent;
    private int momentType;

    public Moment(String momentContent, int momentType) {
        this.momentContent = momentContent;
        this.momentType = momentType;
    }

    public String getMomentContent() {
        return momentContent;
    }

    public int getMomentType() {
        return momentType;
    }
}
