package com.incall.proxy.bt;

import android.os.Parcel;
import android.os.Parcelable;
import android.text.TextUtils;

public class A2dpMetaData implements Parcelable {
/** 标题*/
	public String Title;
	/**演唱者*/
	public String Artist;
	/**专辑*/
	public String Album;
	/**类型*/
	public String Genre;
	/**第几曲*/
	public int Track =-1;
	/**曲目数*/
	public int Total= 0;
	/**歌曲时长 ms*/
	public long Time = 0L;
	/**当前播放进度 ms*/
	public long Pos =0L;
	/**当前播放状态*/
	public int PlayStatus =0;
	/**是否支持获取歌曲信息metadata*/
	public boolean bSupportMetadata =false;
	/**是否支持获取播放状态*/
	public boolean bSupportPlayStatus =false;
	/**A2dp音频流状态*/
	public boolean bA2dpAudioStream=false;
	/**是否支持随机播放*/
	public boolean bSupportShuffle =false;
	/**歌曲随机模式
	 * PLAYER_SHUFFLE_OFF=1 
      * PLAYER_SHUFFLE_ALL=2 
      * PLAYER_SHUFFLE_GROUP=3
	  */
	public int shuffleMode =0;
	/**是否支持循环播放*/
	public boolean bSupportRepeat = false;
	/**
	 * 歌曲循环模式
	 * PLAYER_REPEAT_OFF=1 
     *PLAYER_REPEAT_SINGLE=2 
     *PLAYER_REPEAT_ALL=3 
     *PLAYER_REPEAT_GROUP=4
	 */
	public int repeatMode = 0;
	public static final Parcelable.Creator<A2dpMetaData> CREATOR = new Creator<A2dpMetaData>() {

		@Override
		public A2dpMetaData[] newArray(int size) {
			// TODO Auto-generated method stub
			return new A2dpMetaData[size];
		}

		@Override
		public A2dpMetaData createFromParcel(Parcel source) {
			// TODO Auto-generated method stub
			return new A2dpMetaData(source);
		}
	};

	@Override
	public int describeContents() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public void writeToParcel(Parcel dest, int flags) {
		// TODO Auto-generated method stub
dest.writeString(Title);
dest.writeString(Artist);
dest.writeString(Album);
dest.writeString(Genre);
dest.writeInt(Track);
dest.writeInt(Total);
dest.writeLong(Time);
dest.writeLong(Pos);
dest.writeInt(PlayStatus);
dest.writeValue(bSupportMetadata);
dest.writeValue(bSupportPlayStatus);
dest.writeValue(bA2dpAudioStream);
dest.writeValue(bSupportShuffle);
dest.writeInt(shuffleMode);
dest.writeValue(bSupportRepeat);
dest.writeInt(repeatMode);

	}

	public void readFromParcel(Parcel source) {
		Title = source.readString();
		Artist = source.readString();
		Album = source.readString();
		Genre = source.readString();
		Track = source.readInt();
		Total = source.readInt();
		Time = source.readLong();
		Pos = source.readLong();
		PlayStatus = source.readInt();
		bSupportMetadata = (Boolean)source.readValue(Boolean.class.getClassLoader());
		bSupportPlayStatus =  (Boolean)source.readValue(Boolean.class.getClassLoader());
		bA2dpAudioStream =(Boolean)source.readValue(Boolean.class.getClassLoader());
		bSupportShuffle = (Boolean)source.readValue(Boolean.class.getClassLoader());
		shuffleMode = source.readInt();
		bSupportRepeat = (Boolean)source.readValue(Boolean.class.getClassLoader());
		repeatMode = source.readInt();
	}
public A2dpMetaData(){
	reset();
}
	private A2dpMetaData(Parcel source) {
		readFromParcel(source);
	}
	
	public void reset(){
		this.Title = null;
		this.Artist = null;
		this.Album = null;
		this.Genre = null;
		this.Track = -1;
		this.Total = 0;
		this.Time = 0L;
		this.Time = 0L;
		this.Pos  = 0L;
		this.PlayStatus = 0;
		this.bSupportMetadata = false;
		this.bA2dpAudioStream = false;
		this.bSupportPlayStatus = false;
		this.bSupportRepeat =false;
		this.bSupportShuffle = false;
		this.repeatMode = 0;
		this.shuffleMode =0;
	}
	public long getPosition(){
		
		return this.Pos;
	}
	
	 public boolean equals(Object obj)
	  {
	    if (obj == null) {
	      return false;
	    }
	    A2dpMetaData other = (A2dpMetaData)obj;
	    return (TextUtils.equals(this.Title, other.Title)) && 
	      (TextUtils.equals(this.Artist, other.Artist)) && 
	      (TextUtils.equals(this.Album, other.Album)) && 
	      (TextUtils.equals(this.Genre, other.Genre)) && 
	      (this.Track == other.Track) && 
	      (this.Total == other.Total) && 
	      (this.Time == other.Time) && 
	      (this.Pos == other.Pos) && 
	      (this.PlayStatus == other.PlayStatus) && 
	      (this.bSupportMetadata == other.bSupportMetadata) && 
	      (this.bSupportPlayStatus == other.bSupportPlayStatus) && 
	      (this.bA2dpAudioStream == other.bA2dpAudioStream) && 
	      (this.bSupportShuffle == other.bSupportShuffle) && 
	      (this.shuffleMode == other.shuffleMode) && 
	      (this.bSupportRepeat == other.bSupportRepeat) && 
	      (this.repeatMode == other.repeatMode);
	  }

	 public String toString()
	  {
	    String ret = "Title=" + this.Title + 
	      ", Artist=" + this.Artist + 
	      ", Album=" + this.Album + 
	      ", Genre=" + this.Genre + 
	      ", Track=" + this.Track + 
	      ", Total=" + this.Total + 
	      ", Time=" + this.Time + 
	      ", Pos=" + this.Pos + 
	      ", PlayStatus=" + this.PlayStatus + 
	      ", bSupportMetadata=" + this.bSupportMetadata + 
	      ", bSupportPlayStatus=" + this.bSupportPlayStatus + 
	      ", bA2dpAudioStream=" + this.bA2dpAudioStream + 
	      ", bSupportShuffle=" + this.bSupportShuffle + 
	      ", shuffleMode=" + this.shuffleMode + 
	      ", bSupportRepeat=" + this.bSupportRepeat + 
	      ", repeatMode=" + this.repeatMode;
	    
	    return ret;
	  }

}
