package irepdata.ajaxcriterias;

/**
 * Created by Admin on 08.12.2016.
 */
public class IdeaSortByUserCriteria {
    private Boolean ascend;

    public boolean isAscend() {
        return ascend;
    }

    public void setAscend(boolean ascend) {
        this.ascend = ascend;
    }

    public boolean isValid(){
        if (ascend!=null) {
            return true;
        } else return false;
    }
}
