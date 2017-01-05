#include "jnidemoo.h"

/*

*/
void getProperty(char *key, char *val, char *defval){
	len = getLength();
	for(i = 0; i<len; i++){
		if(strcmp(properties[i].key, key) == SUCCESS){
			strcpy(val, properties[i].value);
			return;
		}
	}

	strcpy(val, defval);
}

/*

*/
int setProperty(char *key, char *val){
	len = getLength();
	int vallen = strlen(val);
	printf("len:%ld, vallen:%d, maxlen:%d\n", len, vallen, VAL_MAX_LEN);
	if(vallen > VAL_MAX_LEN){
		return FAILURE;
	}
	for(i = 0; i<len; i++){
		if(SUCCESS == strcmp(key, properties[i].key)){
			strcpy(properties[i].value, val);
			return SUCCESS;
		}
	}
	
	return FAILURE;
}

/* void main(){
	char v[32] = {0};
	getProperty("persist.um.f", v, "empty...");
	printf("v=%s\n", v);
} */