package com.example.myapplication.utils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class DataUtils {
    private static volatile DataUtils singleData;
    private Map mAreaOption;

    private List<String> mJobTab=new ArrayList<>();

    public DataUtils(){
        mJobTab.add("推荐");
        mJobTab.add("广州");
        mJobTab.add("公司");
        mJobTab.add("要求");

        mAreaOption=new HashMap();
        List locationOption=new ArrayList<>();
        locationOption.add("黄石");
        locationOption.add("白云大道");
        locationOption.add("机场路");
        locationOption.add("同和");
        locationOption.add("同德");
        locationOption.add("永平");
        mAreaOption.put("白云区",locationOption);

        List locationOptionOne=new ArrayList<>();
        locationOptionOne.add("全天河区");
        locationOptionOne.add("珠江新城");
        locationOptionOne.add("棠下");
        locationOptionOne.add("东圃");
        locationOptionOne.add("石牌桥");
        locationOptionOne.add("体育中心");
        mAreaOption.put("天河区",locationOptionOne);

        List locationOptionTwo=new ArrayList<>();
        locationOptionTwo.add("全广州");
        mAreaOption.put("广州",locationOptionTwo);

        List locationOptionThree=new ArrayList<>();
        locationOptionThree.add("全1号线");
        locationOptionThree.add("西朗");
        locationOptionThree.add("坑口");
        locationOptionThree.add("花地湾");
        locationOptionThree.add("芳村");
        locationOptionThree.add("黄沙");
        locationOptionThree.add("长寿路");
        locationOptionThree.add("陈家祠");
        locationOptionThree.add("西门口");
        mAreaOption.put("1号线",locationOptionThree);


        List locationOptionFour=new ArrayList<>();
        locationOptionFour.add("全2号线");
        locationOptionFour.add("会江");
        locationOptionFour.add("南浦");
        locationOptionFour.add("洛溪");
        mAreaOption.put("2号线",locationOptionFour);

        List locationOptionFive=new ArrayList<>();
        locationOptionFive.add("广州东站");
        locationOptionFive.add("同和");
        locationOptionFive.add("梅花园");
        locationOptionFive.add("珠江新城");
        mAreaOption.put("3号线",locationOptionFive);

        List locationOptionSix=new ArrayList<>();
        locationOptionSix.add("车陂南");
        locationOptionSix.add("万胜围");
        locationOptionSix.add("官洲");
        locationOptionSix.add("大学城北");
        mAreaOption.put("4号线",locationOptionSix);
    }

    public List<String> getJobTab() {
        return mJobTab;
    }

    public Map getAreaOption() {
        return mAreaOption;
    }
}
