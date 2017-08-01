package com.incall.proxy.bt;

public class BtMusicPlayer {
	
	private static BtMusicPlayer instance;
	
	public static interface OnMetaDataChangedListener{
		void onChanged(A2dpMetaData metaData);
	};
	public static interface OnPlayStateListener{
		void	onPause();
		void	onPlay();
		void	onProgress(long pos, long time);
		void	onRepeatModeChanged(int mode);
		void	onShuffleModeChanged(int mode);
		void	onStop();
		void	onStreamChanged(boolean state);
	};
	
	BtMusicPlayer(){
		
	}
	
	public static BtMusicPlayer getInstance() {

		if (instance == null) {
			instance = new BtMusicPlayer();
		}
		return instance;
	}
	
	public void destroy(){
		
	}
	
	public String	getFriendlyName(){
		return BtPhoneManager.getInstance().getFriendlyName();
	}
	public A2dpMetaData getMetaData(){
		return BtPhoneManager.getInstance().getMetaData();
	}
	public int	getPlayerRepeatMode(){
		return BtPhoneManager.getInstance().getPlayerRepeatMode();
	}
	public int[]	getPlayerRepeatModeRange(){
		return BtPhoneManager.getInstance().getPlayerRepeatModeRange();
	}
	public int	getPlayerShuffleMode(){
		return BtPhoneManager.getInstance().getPlayerShuffleMode();
	}
	public int[]	getPlayerShuffleModeRange(){
		return BtPhoneManager.getInstance().getPlayerShuffleModeRange();
	}
	public String getPlayingAlbum(){
		return BtPhoneManager.getInstance().getPlayingAlbum();
	}
	public String getPlayingArtist(){
		return BtPhoneManager.getInstance().getPlayingArtist();
	}
	public String getPlayingGenre(){
		return BtPhoneManager.getInstance().getPlayingGenre();
	}
	public long getPlayingPosition(){
		return BtPhoneManager.getInstance().getPlayingPosition();
	}
	public long getPlayingTime(){
		return BtPhoneManager.getInstance().getPlayingTime();
	}
	public String getPlayingTitle(){
		return BtPhoneManager.getInstance().getPlayingTitle();
	}
	public int getPlayingTotal(){
		return BtPhoneManager.getInstance().getPlayingTotal();
	}
	public int getPlayingTrack(){
		return BtPhoneManager.getInstance().getPlayingTrack();
	}
	public int getPlayStatus(){
		return BtPhoneManager.getInstance().getPlayStatus();
	}
	public String getRemoteDeviceName(){
		return BtPhoneManager.getInstance().getRemoteDeviceName();
	}
	public boolean isA2dpAudioStreaming(){
		return BtPhoneManager.getInstance().isA2dpAudioStreaming();
	}
	public boolean isA2dpConnected(){
		return BtPhoneManager.getInstance().isA2dpConnected();
	}
	public boolean isSupportRepeat(){
		return BtPhoneManager.getInstance().isSupportRepeat();
	}
	public boolean isSupportShuffle(){
		return BtPhoneManager.getInstance().isSupportShuffle();
	}
	public boolean next(){
		return BtPhoneManager.getInstance().musicNext();
	}
	
	public boolean pause(){
		return BtPhoneManager.getInstance().musicPause();
	}
	
	public boolean play(){
		return BtPhoneManager.getInstance().musicPlay();
	}

	public boolean previous(){
		return BtPhoneManager.getInstance().musicPrev();
	}
	public boolean	stop()
	{
		return BtPhoneManager.getInstance().musicStop();
	}
	public boolean seekTo(int seconds){
		return BtPhoneManager.getInstance().musicSeekTo(seconds);
	}

	public boolean setMute(boolean mute){
		return BtPhoneManager.getInstance().musicSetMute(mute);
	}
	public void setOnMetaDataChangedListener(BtMusicPlayer.OnMetaDataChangedListener listener)
	{
		BtPhoneManager.getInstance().setOnMetaDataChangedListener(listener);
	}
	public void setOnPlayStateListener(BtMusicPlayer.OnPlayStateListener listener)
	{
		BtPhoneManager.getInstance().setOnPlayStateListener(listener);
	}
	public boolean	setPlayerRepeatMode(int mode)
	{
		return BtPhoneManager.getInstance().setPlayerRepeatMode(mode);
	}
	public boolean setPlayerShuffleMode(int mode)
	{
		return BtPhoneManager.getInstance().setPlayerShuffleMode(mode);
	}

}
