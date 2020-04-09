package com.com.xposedhook;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;

import de.robv.android.xposed.IXposedHookLoadPackage;
import de.robv.android.xposed.XC_MethodHook;
import de.robv.android.xposed.XposedBridge;
import de.robv.android.xposed.XposedHelpers;
import de.robv.android.xposed.callbacks.XC_LoadPackage;

import static de.robv.android.xposed.XposedHelpers.findAndHookMethod;

public class HookTest implements IXposedHookLoadPackage {
        public void handleLoadPackage(XC_LoadPackage.LoadPackageParam loadPackageParam) throws Throwable {

        if (loadPackageParam.packageName.equals("com.mystudy.searchnaber")) {
            XposedBridge.log(" has Hooked!");
            Class clazz = loadPackageParam.classLoader.loadClass("com.mystudy.searchnumber.acitvity.LoginActivity");

            XposedHelpers.findAndHookMethod(clazz, "LoginActivity", new XC_MethodHook() {
                //劫持前触发函数
                protected void beforeHookedMethod(MethodHookParam param) throws Throwable {
                    super.beforeHookedMethod(param);
                    XposedBridge.log("before你已被劫持");
                }
                //劫持后触发函数
                protected void afterHookedMethod(MethodHookParam param) throws Throwable {
                    XposedBridge.log("after你已被劫持");
                }

            });
        }
    }
}

