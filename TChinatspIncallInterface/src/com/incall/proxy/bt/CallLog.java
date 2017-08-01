package com.incall.proxy.bt;

import java.util.Date;

import android.os.Parcel;
import android.os.Parcelable;

public class CallLog implements Parcelable {
	/** 用户电话薄 */
	public static final int USER = 0;
	/** 已接电话 */
	public static final int RC = 1;
	/** 已拨电话 */
	public static final int LD = 2;
	/** 未接电话 */
	public static final int MC = 3;
	/** 全部记录 */
	public static final int AC = 5;

	public int index;
	public java.lang.String name;
	public java.lang.String tel;
	/** 时间 毫秒数 */
	public long time;
	/** 通话记录类型 */
	public int type;

	public static final Parcelable.Creator<CallLog> CREATOR = new Creator<CallLog>() {

		@Override
		public CallLog[] newArray(int size) {
			// TODO Auto-generated method stub
			return new CallLog[size];
		}

		@Override
		public CallLog createFromParcel(Parcel source) {
			// TODO Auto-generated method stub
			return new CallLog(source);
		}
	};
public CallLog(){
	
}
	protected CallLog(Parcel source) {
		readFromParcel(source);
	}

	@Override
	public int describeContents() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public void writeToParcel(Parcel dest, int flags) {
		// TODO Auto-generated method stub
		dest.writeInt(index);
		dest.writeString(name);
		dest.writeString(tel);

	}

	public void readFromParcel(Parcel source) {
		index = source.readInt();
		name = source.readString();
		tel = source.readString();

	}
	
	
	public boolean equals(Object obj)
	  {
	    if (obj == null) {
	      return false;
	    }
	    CallLog other = (CallLog)obj;
	    if ((this.time == other.time) && (this.type == other.type)) {
	      return true;
	    }
	    return false;
	  }
	  
	  public String toString()
	  {
	    Date date = new Date(this.time);
	    return "index=" + this.index + ", name=" + this.name + ", tel=" + this.tel + ", type=" + this.type + ", time=" + date;
	  }
	  

}
