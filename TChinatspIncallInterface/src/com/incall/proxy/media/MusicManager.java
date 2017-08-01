package com.incall.proxy.media;

import android.content.Context;
import android.graphics.Bitmap;
import android.os.IBinder;
import android.util.Log;

import com.incall.proxy.ServiceConnection;
import com.incall.proxy.ServiceNameManager;
import com.incall.proxy.constant.MediaConstantsDef.PLAY_MODE;
import com.incall.proxy.constant.MediaConstantsDef.PLAY_STATE;
import com.incall.proxy.media.scan.CoagentMediaScanManager;
import com.incall.proxy.binder.callback.IMediaStatusCallBack;
import com.incall.proxy.media.IMusicPlaybackService;
public class MusicManager extends  ServiceConnection<IMusicPlaybackService>
{
	private static MusicManager mMusicManager=null;
	private   Context mContext=null;
	private  IMusicPlaybackService mediaBinder;
	private OnConnectListener connectListener;
	private  MusicManager(String servicename)
	{
		super(servicename);
		//Log.e("ggg","--mMusicManager --" +servicename);
	}
	
	@Override
	protected boolean getServiceConnection() 
	{
		IBinder binder = connectService();
		if(binder != null){			
			this.mService = IMusicPlaybackService.Stub.asInterface(binder);
			mediaBinder=((IMusicPlaybackService)this.mService);
			Log.e("ggg","--getServiceConnection --" +mediaBinder);
			if(connectListener != null){
				connectListener.onConnected();
			}
			return true;
		}
		this.mService = null;
		mediaBinder=null;
		 return false;
	}

	@Override
	protected void serviceReConnected() {
		// TODO Auto-generated method stub
		mediaBinder=null;
		boolean flag = getServiceConnection();
	}
	
	public static interface OnConnectListener
	{
		void onConnected();
	}
	
	
	public long 	duration()
	{
		try {
		 return 	mediaBinder.duration();
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return 0;
	}
	public void 	fastForward()
	{
		new Thread(new Runnable() {			
			@Override
			public void run()
			{
				try {
				  	mediaBinder.fastForward();
				} catch (Exception e) {
					// TODO: handle exception
					e.printStackTrace();
				}
			}
		}).start();
		
	}
	public void 	fastRewind()
	{
		new Thread(new Runnable() {
			
			@Override
			public void run() {
				// TODO Auto-generated method stub
				try {
				  	mediaBinder.fastRewind();
				} catch (Exception e) {
					// TODO: handle exception
					e.printStackTrace();
				}
			}
		}).start();		
	}
	public long 	getAudioId()
	{
		try {
		  	return mediaBinder.getAudioId();
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return -1;
	}
	public String[] 	getCurrentPlayList()
	{
		try {
		  	return mediaBinder.getCurrentPlayList();
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return null;
	}
	public String 	getID3Album(String path)
	{
		try {
		  	return mediaBinder.getID3Album(path);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return null;
	}
	public String 	getID3Artist(String path)
	{
		try {
		  	return mediaBinder.getID3Artist(path);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return null;
	}
	public Bitmap 	getID3ArtWork(String path, int width, int height)
	{
		try {
		  	return mediaBinder.getID3ArtWork(path, width, height);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return null;
	}
	public String 	getID3Title(String path)
	{
		try {
		  	return mediaBinder.	getID3Title(path);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return null;
	}
	public long 	getIdByPath(String path)
	{
		try {
		  	return mediaBinder.	getIdByPath(path);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return -1;
	}
	
	public int 	getListLength()
	{
		try {
		  	return mediaBinder.getListLength();
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return -1;
	}
	public int 	getListPosition()
	{
		try {
		  	return mediaBinder.getListPosition();
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return -1;
	}
	public String 	getPathById(int id)
	{
		try {
		  	return mediaBinder.getPathById(id);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return  null;
	}
	
	public String 	getPlayPath()
	{
		try {
		  	return mediaBinder.getPlayPath();
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return  null;
	}
	
	public String 	getTrackInfo()
	{
		try {
		  	return mediaBinder.getTrackInfo();
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return  null;
	}
	
	public boolean 	isPlaying()
	{
		try {
		  	return mediaBinder.isPlaying();
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return  false;
	}
	public void 	next()
	{
		try {
		  	 mediaBinder.next();
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
	}
	public void 	pause()
	{
		try {
		  	 mediaBinder.pause();
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
	}
	public void 	play()
	{
		try {
		  	 mediaBinder.play();
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
	}
	public void 	playById(int id)
	{
		try {
		  	 mediaBinder.playById(id);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
	}
	public void 	playByPath(String path)
	{
		try
		{
			//Log.e("ggg","--mediaBinderplayPath---"+path+"--"+mediaBinder);
		  	 mediaBinder.playByPath(path);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			//Log.e("ggg","--erro---"+e.toString());
		}
	}
	public boolean 	playListPosition(int position)
	{
		try {
		  	return  mediaBinder.playListPosition(position);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return false;
	}
	public void 	playOnceById(int id)
	{
		try {
		  	  mediaBinder.playOnceById(id);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
	}
	public boolean 	playRemembrance()
	{
		try {
		  return 	  mediaBinder.playRemembrance();
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return false;
	}
	public long 	position()
	{
		try {
			  return 	  mediaBinder.position();
			} catch (Exception e) {
				// TODO: handle exception
				e.printStackTrace();
			}
			return 0;
	}
	public void 	prev()
	{
		try {
			   	  mediaBinder.prev();
			} catch (Exception e) {
				// TODO: handle exception
				e.printStackTrace();
			}			
	}
	public void 	release()
	{
		try {
		   	  mediaBinder.release();
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}	
	}
	public long 	seek(long pos)
	{
		try {
		  return 	  mediaBinder.seek(pos);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}	
		return pos;
	}
	public void 	setAndPlayList(long[] list, int position)
	{
		try {
			   	  mediaBinder.setAndPlayList(list,position);
			} catch (Exception e) {
				// TODO: handle exception
				e.printStackTrace();
			}	
	}
	
	public void 	setPlayMode(PLAY_MODE mode)
	{
		try {
		   	  mediaBinder.setPlayMode(mode.ordinal());
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}	
	}
	public void 	stop()
	{
		try {
		   	  mediaBinder.stop();
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}	
	}
	public PLAY_STATE 	getPlayState()
	{
		try {
		   	int state=  mediaBinder.getPlayState();
		   return	PLAY_STATE.values()[state];
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}	
		return null;
	}
	public PLAY_MODE 	getPlayMode()
	{
		try {
		   	int state=  mediaBinder.getPlayMode();
		   	return	PLAY_MODE.values()[state];
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}	
		return null;
	}
	
	
	public void 	setOnConnectListener(OnConnectListener listener)
	{
		connectListener=listener;
	}
	
	
	public boolean 	isConnected()
	{
		if(mediaBinder!=null)
		{
			return true;
		}
		return false;
	}
	
	public void 	setFavorite(String path, boolean favorite)
	{
		try {
			CoagentMediaScanManager.getMediaScanManager(mContext).setFavorite(path, favorite);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}		
	}	
	

	synchronized public static MusicManager 	getInstance(Context context)
	{	
		 if(mMusicManager==null)
		 {		
			// Log.e("ggg","--mMusicManager go to connetc---");
			 mMusicManager=new MusicManager(ServiceNameManager.MEDIA_MANAGER_SERVICENAME);			 
		 }	
		 else if(mMusicManager.mediaBinder==null)
		 {
			 mMusicManager=null;
			 mMusicManager=new MusicManager(ServiceNameManager.MEDIA_MANAGER_SERVICENAME);	
		 }
		 mMusicManager.mContext=context;
		 return mMusicManager;
	}
	
}
