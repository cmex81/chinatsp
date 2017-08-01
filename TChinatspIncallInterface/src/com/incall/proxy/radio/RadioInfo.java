package com.incall.proxy.radio;

/**
 * Created by zhangwei on 2017/6/22.
 */

import android.os.Parcel;
import android.os.Parcelable;

import com.incall.proxy.constant.RadioConstantsDef;

/**
 * 
 */
public class RadioInfo implements Parcelable {

	public final int frequent;
	public final int presetIndex;
	public final int highLightIndex;
	public final RadioConstantsDef.RadioBand band;
	public final RadioConstantsDef.RadioState state;
	public final boolean isStereo;
	public final boolean isLocal;

	@Override
	public int describeContents() {
		return 0;
	}

	@Override
	public void writeToParcel(Parcel dest, int flags) {
		dest.writeInt(this.frequent);
		dest.writeInt(this.presetIndex);
		dest.writeInt(this.highLightIndex);
		dest.writeInt(this.band == null ? -1 : this.band.ordinal());
		dest.writeInt(this.state == null ? -1 : this.state.ordinal());
		dest.writeByte(this.isStereo ? (byte) 1 : (byte) 0);
		dest.writeByte(this.isLocal ? (byte) 1 : (byte) 0);
	}

	public RadioInfo() {
		frequent = 0;
		presetIndex = 0;
		band = RadioConstantsDef.RadioBand.AM1;
		highLightIndex = 0;
		state = RadioConstantsDef.RadioState.PAUSE;
		isLocal = true;
		isStereo = false;
	}

	public RadioInfo(int currentFrequent, int currentIndex, int highLightIndex,
			RadioConstantsDef.RadioBand currentBand,
			RadioConstantsDef.RadioState radioState) {
		this.band = currentBand;
		this.frequent = currentFrequent;
		this.presetIndex = currentIndex;
		this.highLightIndex = highLightIndex;
		this.state = radioState;
		this.isLocal = true;
		this.isStereo = false;
	}

	public RadioInfo(int currentFrequent, int currentIndex, int highLightIndex,
			RadioConstantsDef.RadioBand currentBand,
			RadioConstantsDef.RadioState radioState, boolean stereo,
			boolean local) {
		this.band = currentBand;
		this.frequent = currentFrequent;
		this.presetIndex = currentIndex;
		this.highLightIndex = highLightIndex;
		this.state = radioState;
		this.isLocal = local;
		this.isStereo = stereo;
	}

	protected RadioInfo(Parcel in) {
		this.frequent = in.readInt();
		this.presetIndex = in.readInt();
		this.highLightIndex = in.readInt();
		int tmpBand = in.readInt();
		this.band = tmpBand == -1 ? null
				: RadioConstantsDef.RadioBand.values()[tmpBand];
		int tmpState = in.readInt();
		this.state = tmpState == -1 ? null : RadioConstantsDef.RadioState
				.values()[tmpState];
		this.isStereo = in.readByte() != 0;
		this.isLocal = in.readByte() != 0;
	}

	public static final Creator<RadioInfo> CREATOR = new Creator<RadioInfo>() {
		@Override
		public RadioInfo createFromParcel(Parcel source) {
			return new RadioInfo(source);
		}

		@Override
		public RadioInfo[] newArray(int size) {
			return new RadioInfo[size];
		}
	};

	private boolean isFM() {
		switch (band) {
		case AM1:
		case AM2:
			return false;
		default:
			return true;
		}
	}

	public boolean isBandFM() {
		return isFM();
	}

	public boolean isBandAM() {
		return !isFM();
	}

	@Override
	public boolean equals(Object obj) {
		return super.equals(obj);
	}
}
