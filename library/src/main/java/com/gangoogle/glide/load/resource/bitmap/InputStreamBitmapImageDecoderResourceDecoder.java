package com.gangoogle.glide.load.resource.bitmap;

import android.graphics.Bitmap;
import android.graphics.ImageDecoder;
import android.graphics.ImageDecoder.Source;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import com.gangoogle.glide.load.Options;
import com.gangoogle.glide.load.ResourceDecoder;
import com.gangoogle.glide.load.engine.Resource;
import com.gangoogle.glide.util.ByteBufferUtil;
import java.io.IOException;
import java.io.InputStream;
import java.nio.ByteBuffer;

/** {@link InputStream} specific implementation of {@link BitmapImageDecoderResourceDecoder}. */
@RequiresApi(api = 28)
public final class InputStreamBitmapImageDecoderResourceDecoder
    implements ResourceDecoder<InputStream, Bitmap> {
  private final BitmapImageDecoderResourceDecoder wrapped = new BitmapImageDecoderResourceDecoder();

  @Override
  public boolean handles(@NonNull InputStream source, @NonNull Options options) throws IOException {
    return true;
  }

  @Nullable
  @Override
  public Resource<Bitmap> decode(
      @NonNull InputStream stream, int width, int height, @NonNull Options options)
      throws IOException {
    ByteBuffer buffer = ByteBufferUtil.fromStream(stream);
    Source source = ImageDecoder.createSource(buffer);
    return wrapped.decode(source, width, height, options);
  }
}
