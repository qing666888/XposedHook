package com.com.xposedhook;

import de.robv.android.xposed.IXposedHookLoadPackage;
import de.robv.android.xposed.XC_MethodHook;
import de.robv.android.xposed.XposedBridge;
import de.robv.android.xposed.XposedHelpers;
import de.robv.android.xposed.callbacks.XC_LoadPackage;

public class HookTest implements IXposedHookLoadPackage {
    public void handleLoadPackage(XC_LoadPackage.LoadPackageParam loadPackageParam) throws Throwable {

        if (loadPackageParam.packageName.equals("com.mystudy.searchnumber")) {
            XposedBridge.log(" has Hooked!");
            Class clazz = loadPackageParam.classLoader.loadClass(
                    "com.mystudy.searchnumber.acitvity.LoginActivity");

            XposedHelpers.findAndHookMethod(clazz, "login", new XC_MethodHook() {
                //劫持前触发函数
                protected void beforeHookedMethod(MethodHookParam param) throws Throwable {
                    super.beforeHookedMethod(param);
                    //XposedBridge.log(" has Hooked!");
                    XposedBridge.log("参数1 = " + param.args[0]);
                }
                //劫持后触发函数
                protected void afterHookedMethod(MethodHookParam param) throws Throwable {
                    param.setResult("你已被劫持");
                    XposedBridge.log("参数1 = " + param.args[0]);
                }

            });
        }
    }
}
