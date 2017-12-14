package com.newlife.jy.curtaincall;

import com.google.gson.Gson;

/**
 * @author JY
 *         2017/12/15 2:33
 */

public class Test {

    String a = "asdasdad";

    void Test(Class c){
        Object o = new Gson().fromJson(a, c);
    }

}
