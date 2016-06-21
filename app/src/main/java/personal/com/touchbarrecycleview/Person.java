package personal.com.touchbarrecycleview;

/**
 * Created by Administrator on 2016/6/20.
 */
public class Person {

    private String name;
    private String indx;
    private boolean isFist;

    public boolean isFist() {
        return isFist;
    }

    public void setFist(boolean fist) {
        isFist = fist;
    }

    public Person(String name, String indx) {
        this.indx = indx;
        this.name = name;
    }

    public String getIndx() {
        return indx;
    }

    public void setIndx(String indx) {
        this.indx = indx;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
