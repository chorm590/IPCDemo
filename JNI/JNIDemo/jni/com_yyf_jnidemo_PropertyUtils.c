#include <jni.h>
#include "jnidemoo.h"

JNIEXPORT jstring JNICALL Java_com_yyf_jnidemo_PropertyUtils_getProperty
  (JNIEnv *env, jclass clz, jstring jskey, jstring jsdefval){
	const char* key =  (*env)->GetStringUTFChars(env, jskey, 0);
	const char* defval =  (*env)->GetStringUTFChars(env, jsdefval, 0);
	char val[32] = {0};
	getProperty(key, val, defval);

	jstring jsval = (*env)->NewStringUTF(env, val);

	return jsval;
}


JNIEXPORT void JNICALL Java_com_yyf_jnidemo_PropertyUtils_setProperty
  (JNIEnv *env, jclass clz, jstring jskey, jstring jsval){
	const char* key =  (*env)->GetStringUTFChars(env, jskey, 0);
	const char* val =  (*env)->GetStringUTFChars(env, jsval, 0);

	setProperty(key, val);
}
