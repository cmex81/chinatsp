package com.incall.proxy.bt;



import android.os.Parcel;
import android.os.Parcelable;
import android.text.TextUtils;

public class BtDevice implements Parcelable {

	public String name;
	public String address;
	public int cod;

	public static final Parcelable.Creator<BtDevice> CREATOR = new Creator<BtDevice>() {

		@Override
		public BtDevice[] newArray(int size) {
			// TODO Auto-generated method stub
			return new BtDevice[size];
		}

		@Override
		public BtDevice createFromParcel(Parcel source) {
			// TODO Auto-generated method stub
			return new BtDevice(source);
		}
	};
   public BtDevice(){
	   
   }
	protected BtDevice(Parcel source) {
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
		dest.writeString(name);
		dest.writeString(address);
		dest.writeInt(cod);
	}

	public void readFromParcel(Parcel source) {
		name = source.readString();
		address = source.readString();
		cod = source.readInt();
	}
	
	
	  public boolean equals(Object obj)
	  {
	    if (obj == null) {
	      return false;
	    }
	    return TextUtils.equals(this.address, ((BtDevice)obj).address);
	  }
	  
	  public String toString()
	  {
	    return "name=" + this.name + ", address=" + this.address + ", cod=" + (this.cod > -1 ? "0x" + Integer.toHexString(this.cod) : Integer.valueOf(-1));
	  }

	  

}
