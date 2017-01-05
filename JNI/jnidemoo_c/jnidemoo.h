#include <stdio.h>
#include <string.h>
#include <stdlib.h>

#define SUCCESS 0
#define FAILURE -1
#define VAL_MAX_LEN 32

struct{
	char *key;
	char value[32];
}properties[] = {
	{"persist.um.a", "aaaa"},
	{"persist.um.b", "321"},
	{"persist.um.c", "fas"},
	{"ro.um.d", "bgrw3"},
	{"persist.um.e", "34r"},
	{"persist.um.f", "fjty4554r"},
	{"persist.um.g", "65efb"},
	{"persist.um.z", "h"}
};

int i;
long len;

void getProperty(char *key, char *val, char *defval);

int setProperty(char *key, char *val);

long getLength(){
	return sizeof properties / sizeof properties[0];
}