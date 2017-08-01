package com.incall.proxy.constant;

/**
 * 描述：广播相关的常量定义 作者：Micheal 修改时间：2017/6/6
 */

public class RadioConstantsDef {

	public static enum RadioBand {
		AM1, AM2, FM1, FM2, FM3;
		public final byte index;

		RadioBand() {
			index = (byte) this.ordinal();
		}

		public static RadioBand valueOf(byte index) {
			if (index >= 0 && index < values().length) {
				return values()[index];
			} else {
				return AM1;
			}
		}
	}

	public static enum RadioState {
		PAUSE, PLAY, SCAN, SEARCH, SEEK;
	}
}
