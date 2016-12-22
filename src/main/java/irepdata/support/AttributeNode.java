package irepdata.support;

/**
 * Created by Lenovo on 22.12.2016.
 */
public class AttributeNode {
    private boolean found;
    private Object result;

    public AttributeNode(Object result) {
        this.found = true;
        this.result = result;
    }

    public AttributeNode() {
        this.found=false;
    }

    public boolean isFound() {
        return found;
    }

    public Object getResult() {
        return result;
    }
}
