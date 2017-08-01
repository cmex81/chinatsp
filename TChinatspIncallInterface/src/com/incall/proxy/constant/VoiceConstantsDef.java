package com.incall.proxy.constant;

public final class VoiceConstantsDef {
    public static String VOICE_MUSIC_PAUSE_TAG = "voice_music_pause_tag";
    public static String VOICE_TUNER_ONLINE_PAUSE_TAG = "voice_tuner_online_pause_tag";
    public static String VOICE_TUNER_PAUSE_TAG = "voice_tuner_pause_tag";
    public static final String VOICE_CMD = "voice_cmd";

    private VoiceConstantsDef() {
    }

    public static enum AirMode {
        CRYOGEN(0),
        HEATING(1),
        DEHUMIDIFICATION(2),
        DEFROST(3),
        AUTO(4),
        OUT_LOOP(5),
        IN_LOOP(6),
        PUT_WIND(7),
        PURIFY(8),
        AUTO_CLEAN(9),
        HEALTH(10),
        VENTILATE(11),
        COMFORTABLE_SLEEP(12),
        ELECTRIC_AUXILIARY_HEAT(13),
        SCREEN_SHOW(14),
        POWERFUL(15),
        RUI_FENG(16),
        DRIED(17),
        WIND_PEOPLE(18),
        WIND_AVOID_PEOPLE(19),
        INDUCTION(20),
        FRONT_DEFROST(23),
        REAR_DEFROST(24),
        AQS(25);

        public final int index;

        private AirMode(int aIndex) {
            this.index = aIndex;
        }
    }

    public static enum AirPower {
        ON(0),
        OFF(1);

        public final int index;

        private AirPower(int aIndex) {
            this.index = aIndex;
        }
    }

    public static enum AirTemperatureOperate {
        ADDITION(0),
        SUBSTRUCTION(1);

        public final int index;

        private AirTemperatureOperate(int aIndex) {
            this.index = aIndex;
        }
    }

    public static enum AirWindDirection {
        UP(0),
        DOWN(1),
        LEFT(2),
        RIGHT(3),
        SWEPT(4),
        LEFT_RIGHT_POINT(5),
        UP_DOWN_POINT(6),
        UP_DOWN(7),
        LEFT_RIGHT(8);

        public final int index;

        private AirWindDirection(int aIndex) {
            this.index = aIndex;
        }
    }

    public static enum AirWindSpeed {
        HIGH_WIND(0),
        WIND_ONE(1),
        WIND_TWO(2),
        WIND_THREE(3),
        WIND_FOUR(4),
        WIND_FIVE(5),
        WIND_SIX(6),
        WIND_SEVEN(7),
        WIND_EIGHT(8),
        ADDITION(9),
        SUBSTRUCTION(10);

        public final int index;

        private AirWindSpeed(int aIndex) {
            this.index = aIndex;
        }
    }

    public static enum EngineType {
        IFLYTEK(0),
        NUANCE(1);

        public final int index;

        private EngineType(int aIndex) {
            this.index = aIndex;
        }
    }

    public static enum MsgViewOperation {
        VIEW(0),
        SYNTH(1);

        public final int index;

        private MsgViewOperation(int aIndex) {
            this.index = aIndex;
        }
    }

    public static enum MsgViewType {
        UNREAD(0),
        READ(1),
        SENT(2);

        public final int index;

        private MsgViewType(int aIndex) {
            this.index = aIndex;
        }
    }

    public static enum MusicCostomDataType {
        MUSIC(0),
        ARTIST(1);

        public final int index;

        private MusicCostomDataType(int aIndex) {
            this.index = aIndex;
        }
    }

    public static enum MusicCtrl {
        PAUSE(0),
        PLAY(1),
        PREV(2),
        NEXT(3),
        REPLAY(4);

        public final int index;

        private MusicCtrl(int aIndex) {
            this.index = aIndex;
        }
    }

    public static enum MusicGenre {
        LYRIC(0),
        CLASSICAL(1),
        POPULAR(2),
        ROCK(3),
        JAZZ(4),
        SOFT(5),
        OLD(6);

        public final int index;

        private MusicGenre(int aIndex) {
            this.index = aIndex;
        }
    }

    public static enum MusicRepeat {
        REPEAT_ONE(0),
        REPEAT_ALL(1),
        SHUFFLE(2);

        public final int index;

        private MusicRepeat(int aIndex) {
            this.index = aIndex;
        }
    }

    public static enum MusicSource {
        SD(0),
        USB(1),
        DISC(2),
        IPOD(3),
        HOST(4),
        DTV(5),
        BT_MUSIC(6),
        AUX(7),
        NET(8);

        public final int index;

        private MusicSource(int aIndex) {
            this.index = aIndex;
        }
    }

    public static enum RadioBand {
        FM(0),
        AM(1);

        public final int index;

        private RadioBand(int aIndex) {
            this.index = aIndex;
        }
    }

    public static enum RadioType {
        MUSIC(0),
        ECONOMY(1),
        NEWS(2),
        TRAFFIC(3),
        SPORTS(4),
        ECONOMICS(5),
        EDUCATION(6),
        CITY(7),
        LIFE(8),
        RECREATION(9),
        CULTURE(10),
        CROSSTALK(11),
        STORYTELLING(12),
        STORY(13),
        TRAVEL(14),
        DRAMA(15),
        FOLK_ART(16),
        FOREIGN_LANGUAGE(17),
        DIALECT(18),
        COMPLEX(19),
        LOCAL(20),
        COUNTRY(21),
        CENTER(22),
        NET(23);

        public final int index;

        private RadioType(int aIndex) {
            this.index = aIndex;
        }
    }

    public static enum WorkMode {
        FUNC_MODE_PASSBY(0),
        FUNC_MODE_NOISECLEAN(1),
        FUNC_MODE_PHONE(2),
        FUNC_MODE_WAKEUP(3);

        public final int index;

        private WorkMode(int aIndex) {
            this.index = aIndex;
        }
    }
}