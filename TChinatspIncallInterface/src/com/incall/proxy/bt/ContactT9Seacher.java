package com.incall.proxy.bt;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator; 
import java.util.Locale;

import android.text.TextUtils;
import android.util.Log;

public class ContactT9Seacher {

	private static final String TAG = "ContactT9Seacher";
	private static final boolean DBG = true;
	private ArrayList<SearchedContact> allSearchedContacts = new ArrayList();
	private ArrayList<SearchedContact> backupSearchedContacts = new ArrayList();

	public void setContacts(ArrayList<Contact> contacts) {
		LogUtil.d("ContactT9Seacher", "+++ setContacts("
				+ (contacts == null ? 0 : contacts.size()) + ")");
		this.allSearchedContacts = (contacts == null ? new ArrayList()
				: getSearchedContacts(contacts));
		this.backupSearchedContacts = ((ArrayList) this.allSearchedContacts
				.clone());
		LogUtil.d("ContactT9Seacher", "--- setContacts("
				+ this.allSearchedContacts.size() + ")");
	}

	/**
	 * 符合条件的联系人SearchedContact集合
	 * 
	 * @param condition
	 *            null表示取消检索条件
	 * @param isT9
	 *            为true时condition必须是数字
	 * @return
	 */
	public ArrayList<SearchedContact> searchContact(String condition,
			boolean isT9) {
		LogUtil.d("ContactT9Seacher", "+++ searchContact(" + condition + ", "
				+ isT9 + ")");
		ArrayList<SearchedContact> resultContacts = new ArrayList();
		if (TextUtils.isEmpty(condition)) {
			resultContacts = this.backupSearchedContacts;
		} else {
			resultContacts = isT9 ? searchContactT9(condition)
					: searchContact(condition);
		}
		LogUtil.d("ContactT9Seacher", "--- searchContact(" + condition + ", "
				+ isT9 + ")");
		return resultContacts;
	}

	private ArrayList<SearchedContact> getSearchedContacts(
			ArrayList<Contact> contacts) {
		ArrayList<SearchedContact> searchedContacts = new ArrayList();
		if (contacts != null) {
			for (Contact contact : contacts) {
				searchedContacts.add(new SearchedContact(contact));
			}
		}
		return searchedContacts;
	}

	private ArrayList<SearchedContact> searchContactT9(String condition) {
		Log.d("ContactT9Seacher", "+++ searchContactT9(" + condition + ")");
		ArrayList<SearchedContact> searchedContacts = new ArrayList();
		if (TextUtils.isEmpty(condition)) {
			return searchedContacts;
		}
		for (SearchedContact searchedContact : this.allSearchedContacts) {
			int telIndex = getTelIndexInList(searchedContact.tel, condition);
			if ((!TextUtils.isEmpty(searchedContact.name))
					&& (searchedContact.abbrT9.contains(condition))) {
				searchedContact.searchType = 4;
				searchedContacts.add(searchedContact);
			} else if ((!TextUtils.isEmpty(searchedContact.name))
					&& (isContainFullNameT9(searchedContact, condition))) {
				searchedContact.searchType = 5;
				searchedContacts.add(searchedContact);
			} else if (telIndex > -1) {
				searchedContact.searchType = 6;
				searchedContact.searchTelIndex = telIndex;
				searchedContacts.add(searchedContact);
			}
		}
		Collections.sort(searchedContacts, this.comparator);
		Log.d("ContactT9Seacher", "--- searchContactT9(" + condition + ")"
				+ ", size() = " + searchedContacts.size());
		return searchedContacts;
	}

	private int getTelIndexInList(ArrayList<String> telList, String condition) {
		for (int i = 0; i < telList.size(); i++) {
			if (((String) telList.get(i)).contains(condition)) {
				return i;
			}
		}
		return -1;
	}

	private boolean isContainFullNameT9(SearchedContact searchedContact,
			String tel) {
		return searchedContact.fullT9.contains(SearchedContact.SEPARATOR_UNIT
				+ tel);
	}

	private boolean isContainFullName(SearchedContact searchedContact,
			String name) {
		return searchedContact.fullSearchKey
				.contains(SearchedContact.SEPARATOR_UNIT + name);
	}

	private ArrayList<SearchedContact> searchContact(String condition) {
		Log.d("ContactT9Seacher", "+++ searchContact(" + condition + ")");
		ArrayList<SearchedContact> searchedContacts = new ArrayList();
		if (TextUtils.isEmpty(condition)) {
			return searchedContacts;
		}
		String conditionUpperCase = condition.toUpperCase(Locale.CHINA);
		for (SearchedContact searchedContact : this.allSearchedContacts) {
			int telIndex = getTelIndexInList(searchedContact.tel, condition);
			if ((!TextUtils.isEmpty(searchedContact.name))
					&& (searchedContact.name.contains(condition))) {
				searchedContact.searchType = 1;
				searchedContacts.add(searchedContact);
			} else if ((!TextUtils.isEmpty(searchedContact.name))
					&& (searchedContact.abbrSearchKey
							.contains(conditionUpperCase))) {
				searchedContact.searchType = 2;
				searchedContacts.add(searchedContact);
			} else if ((!TextUtils.isEmpty(searchedContact.name))
					&& (isContainFullName(searchedContact, conditionUpperCase))) {
				searchedContact.searchType = 3;
				searchedContacts.add(searchedContact);
			} else if (telIndex > -1) {
				searchedContact.searchType = 6;
				searchedContact.searchTelIndex = telIndex;
				searchedContacts.add(searchedContact);
			}
		}
		Collections.sort(searchedContacts, this.comparator);
		Log.d("ContactT9Seacher", "--- searchContact(" + condition + ")"
				+ ", size() = " + searchedContacts.size());
		return searchedContacts;
	}

	private Comparator<SearchedContact> comparator = new Comparator<SearchedContact>() {

		@Override
		public int compare(SearchedContact lhs, SearchedContact rhs) {
			// TODO Auto-generated method stub
			if (lhs == null || rhs == null) {
				if (lhs == null && rhs == null) {
					return 0;
				}
				if (lhs == null) {
					return -1;
				}
				return 1;
			}
			return lhs.searchType - rhs.searchType;
		}
	};
}
