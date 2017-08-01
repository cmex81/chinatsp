package com.incall.proxy.tbox;

public class TboxManager 
{

	public static interface TboxChangedListener 
	{
		void 	onCallStatusChanged(int status, int callType);
	}
	
	
	public void 	addTBoxChangedListener(TboxManager.TboxChangedListener aListener)
	{
		
	}
	
	public String 	checkRegistered()
	{
		return "";
	}
	
	public void 	chomdFile(String filePath)
	{
		
	}
	public String 	getAuthenticationPath(com.incall.proxy.constant.TboxConstantsDef.PATH_TYPE path_type)
	{
		return "";
	}
	public static TboxManager 	getInstance() 
	{
		return new TboxManager();
	}
	public String 	getServicePhone(int type)
	{
		return "";
	}
	public void 	removeTBoxChangedListener(TboxManager.TboxChangedListener aListener)
	{
		
	}
	public void 	sendOneCallReqest(byte requestType)
	{
		
	}
	public boolean 	tboxIsExist()
	{
		return false;
	}
	
}
