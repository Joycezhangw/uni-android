package com.io.uniplugin.toastplus;

import android.app.Activity;
import android.widget.Toast;

import com.taobao.weex.annotation.JSMethod;
import com.taobao.weex.bridge.JSCallback;

import io.dcloud.feature.uniapp.common.UniModule;

public class ToastPlus extends UniModule {

    JSCallback jsCallback;
    String path = "";

    /**
     * 调用原生 toast ，显示传入内容
     *
     * @param message
     */
    @JSMethod(uiThread = true)
    public void showToast(String message) {
        if (mWXSDKInstance != null && mWXSDKInstance.getContext() instanceof Activity) {
            Toast.makeText(mUniSDKInstance.getContext(), message, Toast.LENGTH_SHORT).show();
        }
    }

    /**
     *简单回调示例
     * 传入一个String类型，处理并返回
     * @param data
     * @param jsCallback
     */
    @JSMethod(uiThread = true)
    public void processData(String data, JSCallback jsCallback) {
        this.jsCallback = jsCallback;
        if (mWXSDKInstance != null && mWXSDKInstance.getContext() instanceof Activity) {
            path = data;
            jsCallback.invoke(path);
        }
    }
}
