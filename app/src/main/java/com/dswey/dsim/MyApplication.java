package com.dswey.dsim;

import com.orhanobut.logger.AndroidLogAdapter;
import com.orhanobut.logger.Logger;
import com.wangenyong.mvp.base.BaseApplication;

/**
 *
 * @author wangenyong
 * @date 2017/11/30
 */

public class MyApplication extends BaseApplication {

    @Override
    public void onCreate() {
        super.onCreate();
        Logger.addLogAdapter(new AndroidLogAdapter());
    }
}
