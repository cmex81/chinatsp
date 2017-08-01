package com.incall.proxy.bt;

import java.util.ArrayList;
import java.util.Locale;
import java.util.regex.Pattern;

import android.os.Parcel;
import android.os.Parcelable;
import android.text.TextUtils;

public class SearchedContact extends Contact {
	public static final java.lang.String NO_NAME = "\u001c\u001d\u001e\u001f";
	public static final char PREFIX_POUND_KEY_FIRST = 1;
	public static final char PREFIX_POUND_KEY_LAST = 127;
	public static final int SEARCH_TYPE_ABBR = 2;
	public static final int SEARCH_TYPE_ABBR_T9 = 4;
	public static final int SEARCH_TYPE_FULL = 3;
	public static final int SEARCH_TYPE_FULL_T9 = 5;
	public static final int SEARCH_TYPE_NAME = 1;
	public static final int SEARCH_TYPE_TEL = 6;
	public static final char SEPARATOR_GROUP = 31;
	public static final char SEPARATOR_PREFIX = 28;
	public static final char SEPARATOR_RECORD = 30;
	public static final char SEPARATOR_UNIT = 29;
	public int searchType;
	public int searchTelIndex;
	public java.lang.String abbrSearchKey;
	public java.lang.String fullSearchKey;
	public java.lang.String[] fullSearchKeyArray;
	public java.lang.String abbrT9;
	public java.lang.String fullT9;
	public java.lang.String[] fullT9Array;

	public SearchedContact() {
	};

	public SearchedContact(Contact contact) {
		this.raw_contact_id = contact.raw_contact_id;
		this.index = contact.index;
		this.type = contact.type;
		this.name = contact.name;
		this.photoBytes = contact.photoBytes;
		this.hasPhoto = contact.hasPhoto;
		this.sortKey = contact.sortKey;
		this.lastTimeContacted = contact.lastTimeContacted;
		this.tel = contact.tel;
		this.homeAddress = contact.homeAddress;
		this.workAddress = contact.workAddress;
		this.otherAddress = contact.otherAddress;
		this.status = contact.status;
		splitUnit();
	}

	public static final Parcelable.Creator<SearchedContact> CREATOR = new Creator<SearchedContact>() {

		@Override
		public SearchedContact[] newArray(int size) {
			// TODO Auto-generated method stub
			return new SearchedContact[size];
		}

		@Override
		public SearchedContact createFromParcel(Parcel source) {
			// TODO Auto-generated method stub
			return new SearchedContact(source);
		}
	};

	private SearchedContact(Parcel source) {
		readFromParcel(source);
	}

	@Override
	public void writeToParcel(Parcel dest, int flags) {
		// TODO Auto-generated method stub
		super.writeToParcel(dest, flags);
		dest.writeInt(searchType);
		dest.writeInt(searchTelIndex);
		dest.writeString(abbrSearchKey);
		dest.writeString(fullSearchKey);
		dest.writeStringArray(fullSearchKeyArray);
		dest.writeString(abbrT9);
		dest.writeStringArray(fullT9Array);
	}

	public void readFromParcel(Parcel source) {
		super.readFromParcel(source);
		searchType = source.readInt();
		searchTelIndex = source.readInt();
		abbrSearchKey = source.readString();
		fullSearchKey = source.readString();
		source.readStringArray(fullSearchKeyArray);
		abbrT9 = source.readString();
		source.readStringArray(fullT9Array);
	}

	public java.lang.String getFullKey() {
		if (fullSearchKeyArray == null) {
			return null;
		}
		StringBuilder ret = new StringBuilder();
		for (String unit : this.fullSearchKeyArray) {
			ret.append(unit);
		}
		return ret.toString();
	}

	public java.lang.String getFullT9() {
		if (fullT9Array == null) {
			return null;
		}
		StringBuilder ret = new StringBuilder();
		for (String unit : this.fullT9Array) {
			ret.append(unit);
		}
		return ret.toString();
	}
	
	private void splitUnit()
	  {
	    String[] spliter = this.sortKey.split(String.valueOf(SEPARATOR_GROUP));
	    String record;
	    if (spliter.length > 1) {
	      record = spliter[1];
	    } else {
	      record = spliter[0];
	    }
	    spliter = record.split(String.valueOf(SEPARATOR_RECORD));
	    
	    String fullPinYin = spliter.length > 0 ? spliter[0] : "";
	    this.abbrSearchKey = (spliter.length > 1 ? spliter[1] : "");
	    this.abbrT9 = (spliter.length > 2 ? spliter[2] : "");
	    String fullPinYinT9 = spliter.length > 3 ? spliter[3] : "";
	    this.fullSearchKey = (SEPARATOR_UNIT + (spliter.length > 4 ? spliter[4] : ""));
	    this.fullT9 = (SEPARATOR_UNIT + (spliter.length > 5 ? spliter[5] : ""));
	    
	    spliter = fullPinYin.split(String.valueOf(SEPARATOR_UNIT));
	    
	    this.fullSearchKeyArray = spliter;
	    
	    spliter = fullPinYinT9.split(String.valueOf(SEPARATOR_UNIT));
	    
	    this.fullT9Array = spliter;
	  }
	  
	  public String toString()
	  {
	    return   "searchType=" + this.searchType + ", searchTelIndex=" + this.searchTelIndex + "\nabbrSearchKey=" + this.abbrSearchKey + "\nfullSearchKey=" + this.fullSearchKey + "\nfullSearchKeyArray=" + stringArray2String(this.fullSearchKeyArray) + "\nabbrT9=" + this.abbrT9 + "\nfullT9=" + this.fullT9 + "\nfullT9Array=" + stringArray2String(this.fullT9Array) + "\n" + super.toString();
	  }
	  
	  private String stringArray2String(String [] strArray){
		  StringBuilder build = new StringBuilder();
		  for(String str:strArray){
			  if(!TextUtils.isEmpty(str)){
				  build.append(",");
			  }
			  build.append(str);
		  }
		  return build.toString();
	  }
	  
	  public SearchedContact clone()
			    throws CloneNotSupportedException
			  {
			    SearchedContact searchedContact = new SearchedContact();
			    searchedContact.raw_contact_id = this.raw_contact_id;
			    searchedContact.index = this.index;
			    searchedContact.type = this.type;
			    searchedContact.name = this.name;
			    searchedContact.photoBytes = (this.photoBytes == null ? null : (byte[])this.photoBytes.clone());
			    searchedContact.hasPhoto = this.hasPhoto;
			    searchedContact.sortKey = this.sortKey;
			    searchedContact.lastTimeContacted = this.lastTimeContacted;
			    searchedContact.tel = (this.tel == null ? null : (ArrayList)this.tel.clone());
			    searchedContact.status = this.status;
			    return searchedContact;
			  }
	  
	  
	  public static String getSortKey(String hanzi, boolean isPoundKeyFirst)
	  {
	    String prefexPoundKey = String.valueOf(isPoundKeyFirst ? PREFIX_POUND_KEY_FIRST : PREFIX_POUND_KEY_LAST);
	    if (TextUtils.isEmpty(hanzi)) {
	      return prefexPoundKey;
	    }
	    ArrayList<HanziToPinyin.Token> tokens = HanziToPinyin.getInstance().get(hanzi);
	    ArrayList<StringBuilder> fullSearchKeyList = new ArrayList();
	    StringBuilder fullSearchKey = new StringBuilder();
	    StringBuilder fullPinYin = new StringBuilder();
	    StringBuilder initialPinYin = new StringBuilder();
	    boolean hasPinYin = false;
	    StringBuilder prefixLatin = new StringBuilder();
	    HanziToPinyin.Token token;
	    if ((tokens != null) && (tokens.size() > 0)) {
	      for (int idx = 0; idx < tokens.size(); idx++)
	      {
	        token = (HanziToPinyin.Token)tokens.get(idx);
	        if (fullPinYin.length() > 0) {
	          fullPinYin.append(SEPARATOR_UNIT);
	        }
	        if (2 == token.type)
	        {
	          hasPinYin = true;
	          fullPinYin.append(token.target);
	          initialPinYin.append(token.target.charAt(0));
	          for (StringBuilder full : fullSearchKeyList) {
	            full.append(token.target);
	          }
	          fullSearchKeyList.add(new StringBuilder(token.target));
	        }
	        else
	        {
	          String latin = token.source.toUpperCase(Locale.CHINA);
	          fullPinYin.append(latin);
	          initialPinYin.append(latin.charAt(0));
	          for (StringBuilder full : fullSearchKeyList) {
	            full.append(latin);
	          }
	          fullSearchKeyList.add(new StringBuilder(latin));
	          if (!hasPinYin)
	          {
	            if (idx == 0)
	            {
	              if (!isLetter(initialPinYin.toString())) {
	                prefixLatin.append(prefexPoundKey);
	              }
	              prefixLatin.append(latin.charAt(0));
	              latin = latin.substring(1);
	            }
	            for (int i = 0; i < latin.length(); i++)
	            {
	              if (prefixLatin.length() > 0) {
	                prefixLatin.append(SEPARATOR_PREFIX);
	              }
	              char ch = latin.charAt(i);
	              prefixLatin.append(ch);
	            }
	          }
	        }
	      }
	    }
	    for (StringBuilder searchKey : fullSearchKeyList)
	    {
	      if (fullSearchKey.length() > 0) {
	        fullSearchKey.append(SEPARATOR_UNIT);
	      }
	      fullSearchKey.append(searchKey);
	    }
	    StringBuilder sortKey = new StringBuilder();
	    sortKey
	      .append(prefixLatin)
	      .append(prefixLatin.length() > 0 ? Character.valueOf(SEPARATOR_GROUP) : "")
	      .append(fullPinYin)
	      .append(SEPARATOR_RECORD)
	      .append(initialPinYin)
	      .append(SEPARATOR_RECORD)
	      .append(getDigitByAlpha(initialPinYin.toString()))
	      .append(SEPARATOR_RECORD)
	      .append(getDigitByAlpha(fullPinYin.toString()))
	      .append(SEPARATOR_RECORD)
	      .append(fullSearchKey)
	      .append(SEPARATOR_RECORD)
	      .append(getDigitByAlpha(fullSearchKey.toString()));
	    return sortKey.toString();
	  }
	  
	  private static boolean isLetter(String str)
	  {
	    Pattern pattern = Pattern.compile("[a-zA-Z]+");
	    return pattern.matcher(str).matches();
	  }
	  
	  private static final char[] AlphaToDigit = {
		    '2', '2', '2', 
		    '3', '3', '3', 
		    '4', '4', '4', 
		    '5', '5', '5', 
		    '6', '6', '6', 
		    '7', '7', '7', '7', 
		    '8', '8', '8', 
		    '9', '9', '9', '9' };
		  
		  private static String getDigitByAlpha(String alpha)
		  {
		    if (TextUtils.isEmpty(alpha)) {
		      return null;
		    }
		    StringBuilder digit = new StringBuilder();
		    for (int i = 0; i < alpha.length(); i++) {
		      try
		      {
		        char ch = alpha.charAt(i);
		        if ('+' == ch) {
		          digit.append('0');
		        } else if (SEPARATOR_UNIT == ch) {
		          digit.append(ch);
		        } else if (('*' == ch) || ('#' == ch) || (('0' <= ch) && (ch <= '9'))) {
		          digit.append(ch);
		        } else if (('A' <= ch) && (ch <= 'Z')) {
		          digit.append(AlphaToDigit[(ch - 'A')]);
		        } else {
		          digit.append('X');
		        }
		      }
		      catch (Exception e)
		      {
		        e.printStackTrace();
		      }
		    }
		    return digit.toString();
		  }
}
