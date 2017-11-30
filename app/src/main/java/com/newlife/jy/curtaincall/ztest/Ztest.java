package com.newlife.jy.curtaincall.ztest;

import android.support.design.widget.TabLayout;
import android.view.View;

import java.lang.reflect.Field;

/**
 * @author JY
 *         2017/11/19 23:53
 */

public class Ztest {
    public View getTabView(TabLayout tabLayout, int index){
        View tabView = null;
        TabLayout.Tab tab = tabLayout.getTabAt(index);
        Field view = null;
        try {
            view = TabLayout.Tab.class.getDeclaredField("mView");
        } catch (NoSuchFieldException e) {
            e.printStackTrace();
        }
        assert view != null;
        view.setAccessible(true);
        try {
            tabView = (View) view.get(tab);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
        return tabView;
    }
}
