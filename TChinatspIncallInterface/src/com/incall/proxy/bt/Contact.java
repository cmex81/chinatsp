package com.incall.proxy.bt;

import java.io.ByteArrayOutputStream;
import java.util.ArrayList;
import java.util.List;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Parcel;
import android.os.Parcelable;
import android.text.TextUtils;
import android.util.Log;

public class Contact implements Parcelable { 
	
	private static final String TAG = "Contact";
	
	/** 存在手机 */
	public static final int PHONE = 0;
	/** 存在于SIM卡 */
	public static final int SIM = 1;
	/** 在DataBase中的联系人ID */
	public java.lang.String raw_contact_id;
	/** 在手机端的排序号 */
	public int index =0;
	/** 联系人存放类型 PHONE/SIM */
	public int type;
	/** 名字 */
	public String name;
	/** 二进制头像数据 */
	public byte[] photoBytes;
	/** 标记是否存在头像 */
	public boolean hasPhoto;
	/** 排序字串 */
	public java.lang.String sortKey;

	/** 最近一次联系的时间 */
	public long lastTimeContacted =0L;
	/** 号码列表 */
	public ArrayList<String> tel = new ArrayList();
	/** 联系人电话状态 */
	public java.lang.String homeAddress;
	public java.lang.String workAddress;
	public java.lang.String otherAddress;
	public int status = -1;

	public static final Parcelable.Creator<Contact> CREATOR = new Creator<Contact>() {

		@Override
		public Contact[] newArray(int size) {
			// TODO Auto-generated method stub
			return new Contact[size];
		}

		@Override
		public Contact createFromParcel(Parcel source) {
			// TODO Auto-generated method stub
			return new Contact(source);
		}
	};

	public Contact() {

	}

	public Contact(String name, String tel, int state) {
		this.name = name;
		this.tel.add(tel);
		this.status = state;
	}

	protected Contact(Parcel source) {
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
		dest.writeString(raw_contact_id);
		dest.writeInt(index);
		dest.writeInt(type);
		dest.writeString(name);
		dest.writeInt(hasPhoto?1:0);
		if(hasPhoto){
			dest.writeInt(photoBytes.length);
			dest.writeByteArray(photoBytes);
		}
		dest.writeString(sortKey);
		dest.writeLong(lastTimeContacted);
		dest.writeStringList(tel);
	}

	public void readFromParcel(Parcel source) {
		raw_contact_id = source.readString();
		index = source.readInt();
		type = source.readInt();
		name = source.readString();
		hasPhoto =(1==source.readInt());
		if(hasPhoto){
			int photolength = source.readInt();
			photoBytes = new byte[photolength];
			source.readByteArray(photoBytes);
		}
		sortKey = source.readString();
		lastTimeContacted = source.readLong();
		source.createStringArrayList();

	}

	public boolean isSameName(Contact other) {
		if (other == null) {
			return false;
		}
		if ((this.name != null && this.name.equals(other.name))
				|| (TextUtils.isEmpty(name) && TextUtils.isEmpty(other.name))) {
			return true;
		} else {
			return false;
		}
	}

	public java.lang.String getCallState() {
		return null;
	}

	public byte[] Bitmap2Bytes(Bitmap bitmap) {
		if (bitmap != null) {
			ByteArrayOutputStream baos = new ByteArrayOutputStream();
			bitmap.compress(Bitmap.CompressFormat.JPEG, 100, baos);
			return baos.toByteArray();
		}
		return null;
	}

	public Bitmap getPhoto() {
		if (photoBytes != null) {
			return BitmapFactory.decodeByteArray(photoBytes, 0,
					photoBytes.length);
		}
		return null;
	}
	
	  public boolean equals(Object obj)
	  {
	    if (obj == null) {
	      return false;
	    }
	    Contact other = (Contact)obj;
	    if (this.tel.size() != other.tel.size()) {
	      return false;
	    }
	    for (int i = 0; i < this.tel.size(); i++) {
	      if (!TextUtils.equals((CharSequence)this.tel.get(i), (CharSequence)other.tel.get(i))) {
	        return false;
	      }
	    }
	    String localName = this.name == null ? null : this.name.replace(" ", "");
	    String otherName = other.name == null ? null : other.name.replace(" ", "");
	    return TextUtils.equals(localName, otherName);
	  }

	  public String toString()
	  {
	    String ret = "index=" + this.index + ", type=" + this.type + 
	      ", status=" + getCallState() + 
	      ", name=" + this.name + ", sortKey=" + this.sortKey;
	    for (int i = 0; i < this.tel.size(); i++) {
	      ret = ret + ", tel" + i + "=" + (String)this.tel.get(i);
	    }
	    return ret;
	  }

}
