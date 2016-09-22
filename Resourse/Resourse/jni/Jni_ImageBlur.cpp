#include <jni.h>
#include <android/bitmap.h>
#include "stackblur.h"
#include "com_example_resources_ImgUtils_ImageBlur.h"

/*
 * Class:     com_example_resources_ImgUtils_ImageBlur
 * Method:    blurIntArray
 * Signature: ([IIII)V
 */
JNIEXPORT void JNICALL Java_com_example_resources_ImgUtils_ImageBlur_blurIntArray
  (JNIEnv *env, jclass, jintArray arrIn, jint w , jint h , jint r)
{
	jint *pix;
	pix = env->GetIntArrayElements(arrIn, 0);
	if (pix == NULL)
		return;
	pix = blur(pix, w, h, r);

	env->ReleaseIntArrayElements(arrIn, pix, 0);
}

/*
 * Class:     com_example_resources_ImgUtils_ImageBlur
 * Method:    blurBitMap
 * Signature: (Landroid/graphics/Bitmap;I)V
 */
JNIEXPORT void JNICALL Java_com_example_resources_ImgUtils_ImageBlur_blurBitMap
  (JNIEnv *env, jclass, jobject bitmapIn, jint r)
{
	AndroidBitmapInfo infoIn;
	void *pixelsIn;
	int ret;
	// Get image info
	if ((ret = AndroidBitmap_getInfo(env, bitmapIn, &infoIn)) != 0)
		return;
	// Check image
	if (infoIn.format != ANDROID_BITMAP_FORMAT_RGBA_8888)
		return;
	// Lock all images
	if (( ret = AndroidBitmap_lockPixels(env, bitmapIn, &pixelsIn) ) != 0)
	//AndroidBitmap_lockPixels failed!
		return;
	// height width
	int h = infoIn.height;
	int w = infoIn.width;

	// Start
	pixelsIn = blur((int *) pixelsIn, w, h, r);
	// End

	// Unlocks everything
	AndroidBitmap_unlockPixels(env, bitmapIn);
}
