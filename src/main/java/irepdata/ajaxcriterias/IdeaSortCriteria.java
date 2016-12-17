package irepdata.ajaxcriterias;

/**
 * Created by Admin on 08.12.2016.
 */
public class IdeaSortCriteria {
    private String orderingParameter;
    private Boolean ascend;

    public String getOrderingParameter() {
        return orderingParameter;
    }

    public void setOrderingParameter(String orderingParameter) {
        this.orderingParameter = orderingParameter;
    }

    public boolean isAscend() {
        return ascend;
    }

    public void setAscend(boolean ascend) {
        this.ascend = ascend;
    }

    public boolean isValid(){
        if ((orderingParameter !=null) & (ascend!=null)) {
            return true;
        } else return false;
    }
}
