package com.aojing.ninegrid;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by wxw on 2017/3/24.
 */
public class NineGridRepairModel implements Serializable {
    private static final long serialVersionUID = 2189052605715370758L;

    public List<String> urlList = new ArrayList<>();
    public boolean isShowAll = false;
    public String content = "";
    public String address = "";
    public String time = "";
    public String repairID = "";
    public String projectID = "";
    public String company = "";
    public String proprietor = "";

}
