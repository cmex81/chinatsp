package com.incall.proxy.setting;

import android.os.Parcel;
import android.os.Parcelable;

/** 音效信息类 */
public class AudioSettingsInfo implements Parcelable {

    /** AVC level:音量随车速调节状态信息 0x00 -OFF; 0X01~0X03 : LEVEL 1~3 */
	public int AVC_level;
	/** 音场均衡度调节 0~18: (F9-R9) */
	public int Balance;
	/** 低音中心频率 0: 60Hz 1: 80Hz 2: 100Hz 3: 200Hz */
	public int BASS_Central_frequency;
	/** 低音值 */
	public int BASS_Gain;
	/** 音场衰减度调节 0~18: (L9-R9) */
	public int Fade;
	/** 高音切换开关 0- off; 1- open */
	public int LoudnessMode;
	/** 音效模式 */
	public AudioSettingsInfo.EQ_Mode mEQ_Mode;
	/** 中音中心频率 0: 60Hz 1: 80Hz 2: 100Hz 3: 200Hz */
	public int MIDDLE_Central_frequency;
	/** 中音值 */
	public int MIDDLE_Gain;
	/** 3D音效开关状态 0x00-off; 0x01-on; */
	public int Power_Bass_State;
	/** 低音切换开关 0- off; 1- open */
	public int Sub_Woofer_switch;
	/** 高音中心频率 0: 60Hz 1: 80Hz 2: 100Hz 3: 200Hz */
	public int TREBLE_Central_frequency;
	/** 高音值 */
	public int TREBLE_Gain;

	public static enum EQ_Mode {
		/** G_EQ_MODE_Classic */
		Classic,
		/** G_EQ_MODE_Customer */
		customer,
		/** G_EQ_MODE_Human */
		Human,
		/** G_EQ_MODE_Jazz */
		Jazz,
		/** G_EQ_MODE_POP */
		Pop,
		/** G_EQ_MODE_Rock */
		Rock,
		/** G_EQ_MODE_Standard */
		standard
	}

	public static final Parcelable.Creator<AudioSettingsInfo> CREATOR = new Parcelable.Creator<AudioSettingsInfo>() {

		@Override
		public AudioSettingsInfo createFromParcel(Parcel source) {
			return new AudioSettingsInfo(source);
		}

		@Override
		public AudioSettingsInfo[] newArray(int size) {
			return new AudioSettingsInfo[size];
		}

	};

	@Override
	public int describeContents() {
		return 0;
	}

	@Override
	public void writeToParcel(Parcel dest, int flags) {
		dest.writeInt(AVC_level);// AVC level:音量随车速调节状态信息 0x00 -OFF; 0X01~0X03 : LEVEL 1~3
		dest.writeInt( Balance);// 音场均衡度调节 0~18: (F9-R9)
		dest.writeInt( BASS_Central_frequency);// 低音中心频率 0: 60Hz 1: 80Hz 2: 100Hz 3:200Hz
		dest.writeInt( BASS_Gain);// 低音值
		dest.writeInt( Fade);// 音场衰减度调节 0~18: (L9-R9)
		dest.writeInt( LoudnessMode);// 高音切换开关 0- off; 1- open
		dest.writeInt( MIDDLE_Central_frequency);// 中音中心频率 0: 60Hz 1: 80Hz 2: 100Hz 3:200Hz
		dest.writeInt( MIDDLE_Gain);// 中音值
		dest.writeInt(Power_Bass_State);// 3D音效开关状态 0x00-off; 0x01-on;
		dest.writeInt( Sub_Woofer_switch);// 低音切换开关 0- off; 1- open
		dest.writeInt( TREBLE_Central_frequency);// 高音中心频率 0: 60Hz 1: 80Hz 2: 100Hz 3: 200Hz
		dest.writeInt(TREBLE_Gain);// 高音值
	    dest.writeInt(mEQ_Mode.ordinal());
	}

	public void readFromParcel(Parcel source) {
		 AVC_level = source.readInt();// AVC level:音量随车速调节状态信息 0x00 -OFF; 0X01~0X03 : LEVEL 1~3
		 Balance = source.readInt();// 音场均衡度调节 0~18: (F9-R9)
		 BASS_Central_frequency = source.readInt();// 低音中心频率 0: 60Hz 1: 80Hz 2: 100Hz 3:200Hz
		 BASS_Gain= source.readInt();// 低音值
		 Fade= source.readInt();// 音场衰减度调节 0~18: (L9-R9)
		 LoudnessMode= source.readInt();// 高音切换开关 0- off; 1- open
		 MIDDLE_Central_frequency= source.readInt();// 中音中心频率 0: 60Hz 1: 80Hz 2: 100Hz 3:200Hz
		 MIDDLE_Gain= source.readInt();// 中音值
		 Power_Bass_State= source.readInt();// 3D音效开关状态 0x00-off; 0x01-on;
		 Sub_Woofer_switch= source.readInt();// 低音切换开关 0- off; 1- open
		 TREBLE_Central_frequency= source.readInt();// 高音中心频率 0: 60Hz 1: 80Hz 2: 100Hz 3: 200Hz
		 TREBLE_Gain= source.readInt();// 高音值
		 mEQ_Mode = AudioSettingsInfo.EQ_Mode.values()[source.readInt()];
	}

	public AudioSettingsInfo() {

	}

	public AudioSettingsInfo(EQ_Mode aEQ_Mode, int BASS_Gain, int MIDDLE_Gain,
			int TREBLE_Gain, int Fade, int Balance, int Sub_Woofer_switch,
			int LoudnessMode, int BASS_Central_frequency,
			int MIDDLE_Central_frequency, int TREBLE_Central_frequency,
			int AVC_level, int Power_Bass_State) {
		this.mEQ_Mode =aEQ_Mode;
		this.BASS_Gain =BASS_Gain;
		this.MIDDLE_Gain = MIDDLE_Gain;
		this.TREBLE_Gain = TREBLE_Gain;
		this.Fade = Fade;
		this.Balance =Balance;
		this.Sub_Woofer_switch = Sub_Woofer_switch;
		this.LoudnessMode=LoudnessMode;
		this.BASS_Central_frequency =  BASS_Central_frequency;
		this.MIDDLE_Central_frequency = MIDDLE_Central_frequency;
		this.TREBLE_Central_frequency = TREBLE_Central_frequency;
		this.AVC_level = AVC_level;
		this.Power_Bass_State = Power_Bass_State;
		

	}

	private AudioSettingsInfo(Parcel in) {
		readFromParcel(in);
	}

	/** 音效信息打印 */
	public void mtoString() {

	}

}
