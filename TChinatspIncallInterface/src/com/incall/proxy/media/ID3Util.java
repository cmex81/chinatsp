package com.incall.proxy.media;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.media.MediaMetadataRetriever;
import android.text.TextUtils;
import android.util.Log;

import java.io.File;
import java.io.UnsupportedEncodingException;

public class ID3Util {

	private static final String TAG = "ID3Util";
	private Context mContext;
	private String id3_album;
	private String id3_artist;
	private String id3_title;
	private long id3_duration;
	private byte[] buff = null;
	private String mPath = "";
	private Bitmap artWork = null;

	public ID3Util(Context context) {
		this.mContext = context.getApplicationContext();
	}

	public boolean parseID3Info(String filePath) {
		if(TextUtils.isEmpty(filePath)) {
			return false;
		} else {
			MediaMetadataRetriever mediaMetadataRetriever = null;
			String album = null;
			String artist = null;
			String title = null;
			String duration = null;
			Object artworkData = null;
			this.setID3Path(filePath);

			try {
				mediaMetadataRetriever = new MediaMetadataRetriever();
				mediaMetadataRetriever.setDataSource(filePath);
				title = mediaMetadataRetriever.extractMetadata(7);
				album = mediaMetadataRetriever.extractMetadata(1);
				artist = mediaMetadataRetriever.extractMetadata(2);
				duration = mediaMetadataRetriever.extractMetadata(9);
				byte[] artworkData1 = mediaMetadataRetriever.getEmbeddedPicture();
				if(this.isISO8859(title)) {
					title = new String(title.getBytes("ISO-8859-1"), "GBK");
				}

				this.setID3Title(title);
				if(this.isISO8859(album)) {
					album = new String(album.getBytes("ISO-8859-1"), "GBK");
				}

				this.setID3Album(album);
				if(this.isISO8859(artist)) {
					artist = new String(artist.getBytes("ISO-8859-1"), "GBK");
				}

				this.setID3Artist(artist);
				if(this.isISO8859(duration)) {
					duration = new String(duration.getBytes("ISO-8859-1"), "GBK");
				}

				this.setID3Duration(duration);
				this.setID3Buff(artworkData1);
				return true;
			} catch (UnsupportedOperationException var13) {
				var13.printStackTrace();
				return false;
			} catch (Exception var14) {
				var14.printStackTrace();
			} finally {
				if(mediaMetadataRetriever != null) {
					mediaMetadataRetriever.release();
					mediaMetadataRetriever = null;
				}

			}

			return false;
		}
	}

	private boolean isISO8859(String str) {
		if(TextUtils.isEmpty(str)) {
			return false;
		} else {
			try {
				return str.equals(new String(str.getBytes("ISO-8859-1"), "ISO-8859-1"));
			} catch (UnsupportedEncodingException var3) {
				var3.printStackTrace();
				return false;
			}
		}
	}

	private void setID3Album(String id3) {
		this.id3_album = id3;
	}

	private void setID3Artist(String id3) {
		this.id3_artist = id3;
	}

	private void setID3Duration(String id3) {
		if(TextUtils.isEmpty(id3)) {
			this.id3_duration = 0L;
		} else {
			this.id3_duration = Long.parseLong(id3);
		}

	}

	private void setID3Title(String id3) {
		this.id3_title = id3;
	}

	private void setID3Buff(byte[] data) {
		this.buff = data;
	}

	private void setID3Path(String filePath) {
		this.mPath = filePath;
	}

	private byte[] getID3Buff() {
		return this.buff;
	}

	public String getID3Album() {
		return this.id3_album;
	}

	public String getID3Artist() {
		return this.id3_artist;
	}

	public long getID3Duration() {
		return this.id3_duration;
	}

	public String getID3Title() {
		return this.getFileName(this.mPath);
	}

	private String getFileName(String path) {
		try {
			if(TextUtils.isEmpty(path)) {
				return null;
			} else {
				String e = (new File(path)).getName();
				return e.substring(0, e.lastIndexOf("."));
			}
		} catch (Exception var3) {
			var3.printStackTrace();
			return null;
		}
	}

	public Bitmap getID3ArtWork(int width, int height) {
		if(this.buff != null) {
			byte[] data = this.getID3Buff();
			if(data != null && data.length > 0) {
				BitmapFactory.Options opts = new BitmapFactory.Options();
				opts.inJustDecodeBounds = true;
				opts.inPreferredConfig = Bitmap.Config.RGB_565;
				this.artWork = BitmapFactory.decodeByteArray(data, 0, data.length, opts);
				opts.inJustDecodeBounds = false;
				if(opts.outHeight <= height && opts.outWidth <= width) {
					this.artWork = BitmapFactory.decodeByteArray(data, 0, data.length, opts);
				} else if(opts.outHeight > opts.outWidth) {
					opts.inSampleSize = Math.round((float)(opts.outHeight / height));
					this.artWork = BitmapFactory.decodeByteArray(data, 0, data.length, opts);
				} else {
					opts.inSampleSize = Math.round((float)(opts.outWidth / width));
					this.artWork = BitmapFactory.decodeByteArray(data, 0, data.length, opts);
				}

				if(this.artWork == null) {
					Log.d("ID3Util", "artWork == null");
				}
			}
		} else {
			this.artWork = null;
		}

		return this.artWork;
	}
}
