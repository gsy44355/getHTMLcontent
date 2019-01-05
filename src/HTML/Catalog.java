package HTML;

/**
 * Created by gsy on 2017/10/28.
 */

public class Catalog {
    private String order;
    private int sectionorder;
    private String sectionname;
    private String sectionURL;
    public Catalog()
    {

    }
    public Catalog(String order, String sectionname, String sectionURL,int sectionorder)
    {
        this.order = order;
        this.sectionname = sectionname;
        this.sectionURL = sectionURL;
        this.sectionorder = sectionorder;
    }

    public int getSectionorder() {
        return sectionorder;
    }

    public void setSectionorder(int sectionorder) {
        this.sectionorder = sectionorder;
    }

    public String getOrder() {
        return order;
    }

    public void setOrder(String order) {
        this.order = order;
    }

    public String getSectionname() {
        return sectionname;
    }

    public void setSectionname(String sectionname) {
        this.sectionname = sectionname;
    }

    public String getSectionURL() {
        return sectionURL;
    }

    public void setSectionURL(String sectionURL) {
        this.sectionURL = sectionURL;
    }
    public String toString()
    {
    	return order+sectionname+sectionURL+sectionorder;
    }
}
