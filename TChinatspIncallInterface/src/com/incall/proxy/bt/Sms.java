package com.incall.proxy.bt;

import android.os.Parcel;
import android.os.Parcelable;

public class Sms implements Parcelable {
	public static final int OUTBOX = 0;
	public static final int INBOX = 1;
	public Contact contact;
	public String txt;
	public int index;
	public int whichFolder;
	public boolean read;

	public static final Parcelable.Creator<Sms> CREATOR = new Creator<Sms>() {

		@Override
		public Sms[] newArray(int size) {
			// TODO Auto-generated method stub
			return new Sms[size];
		}

		@Override
		public Sms createFromParcel(Parcel source) {
			// TODO Auto-generated method stub
			return new Sms(source);
		}
	};

	public Sms() {

	}

	private Sms(Parcel source) {
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
		dest.writeParcelable(contact, flags);
		dest.writeString(txt);
		dest.writeInt(index);
		dest.writeInt(whichFolder);
		dest.writeValue(read);

	}

	public void readFromParcel(Parcel source) {
		contact = source.readParcelable(Contact.class.getClassLoader());
		txt = source.readString();
		index = source.readInt();
		whichFolder = source.readInt();
		read = (Boolean) source.readValue(Boolean.class.getClassLoader());
	}

}
