package com.gangoogle.glide.load.resource.bitmap;

import android.graphics.Bitmap;
import android.graphics.ImageDecoder;
import android.graphics.ImageDecoder.OnHeaderDecodedListener;
import android.graphics.ImageDecoder.Source;
import android.util.Log;
import android.support.annotation.RequiresApi;
import com.gangoogle.glide.load.engine.Resource;
import com.gangoogle.glide.load.engine.bitmap_recycle.BitmapPool;
import com.gangoogle.glide.load.engine.bitmap_recycle.BitmapPoolAdapter;
import com.gangoogle.glide.load.resource.ImageDecoderResourceDecoder;
import java.io.IOException;

/** {@link Bitmap} specific implementation of {@link ImageDecoderResourceDecoder}. */
@RequiresApi(api = 28)
public final class BitmapImageDecoderResourceDecoder extends ImageDecoderResourceDecoder<Bitmap> {
  private static final String TAG = "BitmapImageDecoder";
  private final BitmapPool bitmapPool = new BitmapPoolAdapter();

  @Override
  protected Resource<Bitmap> decode(
      Source source,
      int requestedResourceWidth,
      int requestedResourceHeight,
      OnHeaderDecodedListener listener)
      throws IOException {
    Bitmap result = ImageDecoder.decodeBitmap(source, listener);
    if (Log.isLoggable(TAG, Log.VERBOSE)) {
      Log.v(
          TAG,
          "Decoded"
              + " ["
              + result.getWidth()
              + "x"
              + result.getHeight()
              + "]"
              + " for ["
              + requestedResourceWidth
              + "x"
              + requestedResourceHeight
              + "]");
    }
    return new BitmapResource(result, bitmapPool);
  }
}
