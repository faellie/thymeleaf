package com.myropcb.tocc;

/****************************************************************************
 *
 *
 * zihuangw
 ****************************************************************************
 *
 *
 *
 *
 * Created by zihuangw on 11/19/19.
 *
 ****************************************************************************
 * Copyright (c) 2019 Nokia. All Rights Reserved.
 * Please read the associated COPYRIGHTS file for more details.
 *
 ****************************************************************************
 */

public class HelloJNI {
    static {
        System.loadLibrary("Hello"); // hello.dll (Windows) or libhello.so (Unixes)
    }
    // A native method that receives nothing and returns void
    public native void sayHello();

    // A native method that receives nothing and returns void
    public native String getString(String input);

    public static void main(String[] args) {
        new HelloJNI().sayHello();  // invoke the native method
        System.out.println(new HelloJNI().getString("this is input"));
    }
}
