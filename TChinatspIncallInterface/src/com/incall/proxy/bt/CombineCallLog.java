package com.incall.proxy.bt;

import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;

/**
 * 用于统计同一个联系人同类型CallLog的次数
 * 
 * @author liueg
 * 
 */
public class CombineCallLog extends CallLog {
	public int total;

	public CombineCallLog() {

	}

	public CombineCallLog(CallLog callLog) {
		this.index = callLog.index;
		this.name = callLog.name;
		this.tel = callLog.tel;
		this.time = callLog.time;
		this.type = callLog.type;
		this.total = 1;
	}

	public void increase() {
		this.total += 1;
	}

	public static final Parcelable.Creator<CallLog> CREATOR = new Creator<CallLog>() {

		@Override
		public CallLog[] newArray(int size) {
			// TODO Auto-generated method stub
			return new CombineCallLog[size];
		}

		@Override
		public CallLog createFromParcel(Parcel source) {
			// TODO Auto-generated method stub
			return new CombineCallLog(source);
		}
	};

	protected CombineCallLog(Parcel in) {
		readFromParcel(in);
	}

	public void readFromParcel(Parcel in) {
		super.readFromParcel(in);
		total = in.readInt();
	}

	@Override
	public void writeToParcel(Parcel dest, int flags) {
		// TODO Auto-generated method stub
		super.writeToParcel(dest, flags);
		dest.writeInt(total);
	}
}
