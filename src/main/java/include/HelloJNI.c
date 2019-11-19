#include <jni.h>
#include <stdio.h>
#include <ctype.h>
#include <string.h>
#include "com_myropcb_tocc_HelloJNI.h"

JNIEXPORT void JNICALL Java_com_myropcb_tocc_HelloJNI_sayHello(JNIEnv *env, jobject thisObj) {
   printf("Hello World!\n");
   return;
}

JNIEXPORT jstring JNICALL Java_com_myropcb_tocc_HelloJNI_getString(JNIEnv *env, jobject thisObj, jstring inputstr) {
    const char* str = env->GetStringUTFChars(inputstr, 0);
        char cap[128];
        strcpy(cap, "returned : ");
        strcat(cap, str);
        env->ReleaseStringUTFChars(inputstr, str);
        //uppercase(cap);
        return env->NewStringUTF(cap);
}
