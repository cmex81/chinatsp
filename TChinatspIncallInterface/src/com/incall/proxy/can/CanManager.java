package com.incall.proxy.can;

import android.os.Handler;
import android.os.IBinder;
import android.os.Looper;
import android.os.RemoteException;

import com.incall.proxy.ServiceConnection;
import com.incall.proxy.binder.callback.ICanCallBackInterface;
import com.incall.proxy.binder.service.ICanInterface;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

/** CanManager can协议管理 */
public final class CanManager extends ServiceConnection<ICanInterface> {
    public static final String SERVICE_NAME_CAN = "coagent.can";
    private static CanManager instance = null;
    
    private Map<CanChangedListener, CallBack> mCallbacks = new HashMap<CanChangedListener, CallBack>();

    protected CanManager() {
        super(SERVICE_NAME_CAN);
    }

    @Override
    protected boolean getServiceConnection() {
        IBinder binder = connectService();
        if (binder != null) {
            this.mService = ICanInterface.Stub.asInterface(binder);
            return true;
        }
        this.mService = null;
        return false;
    }

    @Override
    protected void serviceReConnected() {
        getServiceConnection();
        registerCallbacks();
    }
    
    private void registerCallbacks() {
        if (this.mService == null) {
            return;
        }
        synchronized (mCallbacks) {
            Collection<CallBack> collection = mCallbacks.values();
            for (CallBack callback : collection) {
                callback.registerICanInterface(this.mService);
            }
        }
    }
    
    /** 获取类实例 */
    public static CanManager getInstance() {
        if (instance == null) {
            synchronized (CanManager.class) {
                if (instance == null) {
                    instance = new CanManager();
                }
            }
        }
        return instance;
    }

    /** 空气净化动画语音提示音 */
    public static enum AC_PlasmaSt {
        /** 关闭 */
        INACTIVE((short) 0),
        /** 开启 */
        ACTIVE((short) 1),
        /** invalid */
        INVALID((short) 255);
        
        public final short value;

        private AC_PlasmaSt(short value) {
            this.value = value;
        }
        
        public static AC_PlasmaSt getByIndex(short value){
            AC_PlasmaSt[] values = values();
            for (AC_PlasmaSt item : values) {
                if (item.value == value) {
                    return item;
                }
            }
            return null;
        }
    }
    
    /** AHBA 智能远光功能开启 */
    public static enum AHBA {
        /** 关闭 */
        OFF((short) 0),
        /** 开启 */
        ON((short) 1),
        /** invalid */
        INVALID((short) 255);
        
        public final short value;

        private AHBA(short value) {
            this.value = value;
        }
        
        public static AHBA getByIndex(short value){
            AHBA[] values = values();
            for (AHBA item : values) {
                if (item.value == value) {
                    return item;
                }
            }
            return null;
        }
    }
    
    /** 氛围灯亮度 设置信号 */
    public static enum AtmosphereLightBrightnessSet {
        /** inactive */
        Inactive((short)0),
        /** 关闭 */
        OFF((short)1),
        /** Level1 */
        Level1((short)2),
        /** Level2 */
        Level2((short)3),
        /** Level3 */
        Level3((short)4),
        /** Level4 */
        Level4((short)5),
        /** Level5 */
        Level5((short)6),
        /** Level6 */
        Level6((short)7),
//        /** 打开 */
//        ON,
        /** invalid */
        INVALID((short)255);
        
        public final short value;

        private AtmosphereLightBrightnessSet(short value)
        {
            this.value = value;
        }

        public static AtmosphereLightBrightnessSet getByIndex(short value) {
            AtmosphereLightBrightnessSet[] values = values();
            for (AtmosphereLightBrightnessSet item : values) {
                if (item.value == value) {
                    return item;
                }
            }
            return null;
        }
    }
    
    /** 氛围灯颜色 */
    public static enum AtmosphereLightColorSet {
        /** color1 */
        color1((short) 1),
        /** color2 */
        color2((short) 2),
        /** color3 */
        color3((short) 3),
        /** color4 */
        color4((short) 4),
        /** color5 */
        color5((short) 5),
        /** color6 */
        color6((short) 6),
        /** color7 */
        color7((short) 7),
        /** invalid */
        INVALID((short) 255);
        
        public final short value;

        private AtmosphereLightColorSet(short value) {
            this.value = value;
        }

        public static AtmosphereLightColorSet getByIndex(short value) {
            AtmosphereLightColorSet[] values = values();
            for (AtmosphereLightColorSet aState : values) {
                if (aState.value == value) {
                    return aState;
                }
            }
            return null;
        }
    }
    
    /** AtmosphereLightStaus 行车期间调暗（默认） darkle； 行车期间关闭 close */
    public static enum AtmosphereLightStaus {
        /** 开启 */
        Darkle((short) 0),
        /** 关闭 */
        Close((short) 1),
        /** invalid */
        INVALID((short) 255);
        
        public final short value;
        
        private AtmosphereLightStaus (short value){
            this.value = value;
        }
        
        public static AtmosphereLightStaus getByIndex(short value){
            AtmosphereLightStaus[] values = values();
            for(AtmosphereLightStaus item : values){
                if(item.value == value){
                    return item;
                }
            }
            return null;
        }
    }
    
    /** 碰撞缓解 自动制动使能开关 */
    public static enum AutoBrakeEnableStatus {
        /** 关闭 */
        OFF((short) 0),
        /** 低 */
        ON((short) 1),
        /** invalid */
        INVALID((short) 255);
        
        public final short value;

        private AutoBrakeEnableStatus(short value) {
            this.value = value;
        }

        public static AutoBrakeEnableStatus getByIndex(short value) {
            AutoBrakeEnableStatus[] values = values();
            for (AutoBrakeEnableStatus item : values) {
                if (item.value == value) {
                    return item;
                }
            }
            return null;
        }
    }
    
    /** 自动氛围灯调节 */
    public static enum AutoFWDTJ {
        /** 关闭 */
        OFF((short) 0),
        /** 开启 */
        ON((short) 1),
        /** invalid */
        INVALID((short) 255);
        
        public final short value;
        
        private AutoFWDTJ (short value){
            this.value = value;
        }
        
        public static AutoFWDTJ getByIndex(short value){
            AutoFWDTJ[] values = values();
            for(AutoFWDTJ item : values){
                if(item.value == value){
                    return item;
                }
            }
            return null;
        }
    }

    /** 熄火自动解锁 状态 */
    public static enum AutoIGNOFFUnlockSet {
        /** 关闭 */
        OFF((short) 1),
        /** 开启 */
        ON((short) 2),
        /** invalid */
        INVALID((short) 255);
        
        public final short value;
        
        private AutoIGNOFFUnlockSet (short value){
            this.value = value;
        }
        
        public static AutoIGNOFFUnlockSet getByIndex(short value){
            AutoIGNOFFUnlockSet[] values = values();
            for(AutoIGNOFFUnlockSet item : values){
                if(item.value == value){
                    return item;
                }
            }
            return null;
        }
    }

    /** 驻车自动解锁 状态 */
    public static enum AutoParkUnlockSet {
        /** 关闭 */
        OFF((short) 1),
        /** 开启 */
        ON((short) 2),
        /** invalid */
        INVALID((short) 255);

        public final short value;

        private AutoParkUnlockSet(short value) {
            this.value = value;
        }

        public static AutoParkUnlockSet getByIndex(short value) {
            AutoParkUnlockSet[] values = values();
            for (AutoParkUnlockSet item : values) {
                if (item.value == value) {
                    return item;
                }
            }
            return null;
        }
    }

    /** 行车自动落锁 自动落锁设置 */
    public static enum AutoSpeedLockSet {
        /** 关闭 */
        OFF((short) 1),
        /** 开启 */
        ON((short) 2),
        /** invalid */
        INVALID((short) 255);
        
        public final short value;

        private AutoSpeedLockSet(short value) {
            this.value = value;
        }

        public static AutoSpeedLockSet getByIndex(short value) {
            AutoSpeedLockSet[] values = values();
            for (AutoSpeedLockSet item : values) {
                if (item.value == value) {
                    return item;
                }
            }
            return null;
        }
    }

    /** 钥匙开锁通风设置 */
    public static enum AutowindSet1 {
        /** invalid */
        INVALID((short) 0),
        /** 开启 */
        ON((short) 1),
        /** 关闭 */
        OFF((short) 2);
        
        public final short value;

        private AutowindSet1(short value) {
            this.value = value;
        }

        public static AutowindSet1 getByIndex(short value) {
            AutowindSet1[] values = values();
            for (AutowindSet1 item : values) {
                if (item.value == value) {
                    return item;
                }
            }
            return null;
        }
    }

    /** 钥匙长按通风设置 */
    public static enum AutowindSet2 {
        /** invalid */
        INVALID((short) 0),
        /** 开启 */
        ON((short) 1),
        /** 关闭 */
        OFF((short) 2);
        
        public final short value;

        private AutowindSet2(short value) {
            this.value = value;
        }

        public static AutowindSet2 getByIndex(short value) {
            AutowindSet2[] values = values();
            for (AutowindSet2 item : values) {
                if (item.value == value) {
                    return item;
                }
            }
            return null;
        }
    }

    /** 钥匙闭锁通风设置 */
    public static enum AutowindSet3 {
        /** invalid */
        INVALID((short) 0),
        /** 开启 */
        ON((short) 1),
        /** 关闭 */
        OFF((short) 2);
        
        public final short value;

        private AutowindSet3(short value) {
            this.value = value;
        }

        public static AutowindSet3 getByIndex(short value) {
            AutowindSet3[] values = values();
            for (AutowindSet3 item : values) {
                if (item.value == value) {
                    return item;
                }
            }
            return null;
        }
    }

    /** 调光类型 */
    public static enum Bright {
        /** 小灯信号ILL */
        ILL((short) 0),
        /** 调光控制器 */
        TGKZQ((short) 1),
        /** 小灯信号ILL+调光控制器 */
        ILL_TGKZQ((short) 2);
        
        public final short value;

        private Bright(short value) {
            this.value = value;
        }

        public static Bright getByIndex(short value) {
            Bright[] values = values();
            for (Bright item : values) {
                if (item.value == value) {
                    return item;
                }
            }
            return null;
        }
    }

    /** 调光类型 */
    public static enum Bright_Type {
        /** 小灯信号ILL */
        ILL((short) 0),
        /** 调光控制器 */
        TGKZQ((short) 1),
        /** 小灯信号ILL+调光控制器 */
        ILL_TGKZQ((short) 2);
        
        public final short value;

        private Bright_Type(short value) {
            this.value = value;
        }

        public static Bright_Type getByIndex(short value) {
            Bright_Type[] values = values();
            for (Bright_Type item : values) {
                if (item.value == value) {
                    return item;
                }
            }
            return null;
        }
    }
    
    /** 并线辅助（BSD/LCA） */
    public static enum BSD_LCA {
        /** 关闭 */
        OFF((short) 1),
        /** 开启 */
        ON((short) 2),
        /** invalid */
        INVALID((short) 255);
        
        public final short value;

        private BSD_LCA(short value) {
            this.value = value;
        }

        public static BSD_LCA getByIndex(short value) {
            BSD_LCA[] values = values();
            for (BSD_LCA item : values) {
                if (item.value == value) {
                    return item;
                }
            }
            return null;
        }
    }
    
    /** 摄像头 */
    public static enum Camera {
        /** 无摄像头 */
        None((short) 0),
        /** 低配：UI 无泊车模式按钮及其控制 */
        Low_NoUI((short) 1),
        /** 高配：UI 有泊车模式按钮及其控制 */
        High_UI((short) 2);
        
        public final short value;

        private Camera(short value) {
            this.value = value;
        }

        public static Camera getByIndex(short value) {
            Camera[] values = values();
            for (Camera item : values) {
                if (item.value == value) {
                    return item;
                }
            }
            return null;
        }
    }

    /** 摄像头种类 */
    public static enum Camera_Type {
        /** 无效 */
        None((short) 0),
        /** 摄像头1 */
        Camera1((short) 1),
        /** 摄像头2 */
        Camera2((short) 2),
        /** 摄像头3 */
        Camera3((short) 3);
        
        public final short value;

        private Camera_Type(short value) {
            this.value = value;
        }

        public static Camera_Type getByIndex(short value) {
            Camera_Type[] values = values();
            for (Camera_Type item : values) {
                if (item.value == value) {
                    return item;
                }
            }
            return null;
        }
    }
    
    /** 车型(用于匹配按键) */
    public static enum Car_Type {
        Planform((short) 0),
        CS35((short) 1),
        CS75((short) 2),
        CD101((short) 3),
        C201((short) 4),
        S401((short) 5),
        B211((short) 6);
        
        public final short value;

        private Car_Type(short value) {
            this.value = value;
        }

        public static Car_Type getByIndex(short value) {
            Car_Type[] values = values();
            for (Car_Type item : values) {
                if (item.value == value) {
                    return item;
                }
            }
            return null;
        }
    }
    
    /** 行车电脑数据开关  */
    public static enum CarComputerData {
        /** 关闭 */
        OFF((short) 0),
        /** 开启 */
        ON((short) 1),
        /** invalid */
        INVALID((short) 255);
        
        public final short value;

        private CarComputerData(short value) {
            this.value = value;
        }

        public static CarComputerData getByIndex(short value) {
            CarComputerData[] values = values();
            for (CarComputerData item : values) {
                if (item.value == value) {
                    return item;
                }
            }
            return null;
        }
    }

    /** 车辆设置使能 */
    public static enum CarSetting {
        /** 关闭 */
        OFF((short) 0),
        /** 开启 */
        ON((short) 1),
        /** invalid */
        INVALID((short) 255);

        public final short value;

        private CarSetting(short value) {
            this.value = value;
        }

        public static CarSetting getByIndex(short value) {
            CarSetting[] values = values();
            for (CarSetting item : values) {
                if (item.value == value) {
                    return item;
                }
            }
            return null;
        }
    }
    
    /** 巡航控制 */
    public static enum CruiseControl{
        /** 自适应巡航 */
        Adaptive((short) 0),
        /** 定速巡航 */
        Cruise((short) 1),
        /** invalid */
        INVALID((short) 255);
        
        public final short value;

        private CruiseControl(short value) {
            this.value = value;
        }

        public static CruiseControl getByIndex(short value) {
            CruiseControl[] values = values();
            for (CruiseControl item : values) {
                if (item.value == value) {
                    return item;
                }
            }
            return null;
        }
    }
    
    /** 倒车横向预警（CTA） */
    public static enum CTA{
        /** 关闭 */
        OFF((short) 0),
        /** 开启 */
        ON((short) 1),
        /** invalid */
        INVALID((short) 255);
        
        public final short value;

        private CTA(short value) {
            this.value = value;
        }

        public static CTA getByIndex(short value) {
            CTA[] values = values();
            for (CTA item : values) {
                if (item.value == value) {
                    return item;
                }
            }
            return null;
        }
    }
    
    /** 车距提醒 */
    public static enum DistanceRemind {
        /** 关闭 */
        OFF((short) 0),
        /** 开启 */
        ON((short) 1),
        /** invalid */
        INVALID((short) 255);
        
        public final short value;

        private DistanceRemind(short value) {
            this.value = value;
        }

        public static DistanceRemind getByIndex(short value) {
            DistanceRemind[] values = values();
            for (DistanceRemind item : values) {
                if (item.value == value) {
                    return item;
                }
            }
            return null;
        }
    }
    
    /** DVD 区域码 */
    public static enum DVD_Region {
        /** 无DVD */
        None((short) 0),
        Region1((short) 1),
        Region2((short) 2),
        Region3((short) 3),
        Region4((short) 4),
        Region5((short) 5),
        Region6((short) 6);
        
        public final short value;

        private DVD_Region(short value) {
            this.value = value;
        }

        public static DVD_Region getByIndex(short value) {
            DVD_Region[] values = values();
            for (DVD_Region item : values) {
                if (item.value == value) {
                    return item;
                }
            }
            return null;
        }
    }
    
    /** 座椅舒适进出 */
    public static enum EasyEntrySet{
        /** 关闭 */
        OFF((short) 0),
        /** 开启 */
        ON((short) 1),
        /** invalid */
        INVALID((short) 255);
        
        public final short value;

        private EasyEntrySet(short value) {
            this.value = value;
        }

        public static EasyEntrySet getByIndex(short value) {
            EasyEntrySet[] values = values();
            for (EasyEntrySet item : values) {
                if (item.value == value) {
                    return item;
                }
            }
            return null;
        }
    }
    
    /** 前碰撞预警 */
    public static enum FCWEnableStatus{
        /** 关闭 */
        OFF((short) 0),
        /** 开启 */
        ON((short) 1),
        /** invalid */
        INVALID((short) 255);
        
        public final short value;

        private FCWEnableStatus(short value) {
            this.value = value;
        }

        public static FCWEnableStatus getByIndex(short value) {
            FCWEnableStatus[] values = values();
            for (FCWEnableStatus item : values) {
                if (item.value == value) {
                    return item;
                }
            }
            return null;
        }
    }
    
    /** 前碰撞预警灵敏度 */
    public static enum FCWSensitivityStatus {
        /** 高 */
        High((short) 0),
        /** 标准 */
        Standard((short) 1),
        /** 低 */
        Low((short) 2),
        /** invalid */
        INVALID((short) 255);
        
        public final short value;

        private FCWSensitivityStatus(short value) {
            this.value = value;
        }

        public static FCWSensitivityStatus getByIndex(short value) {
            FCWSensitivityStatus[] values = values();
            for (FCWSensitivityStatus item : values) {
                if (item.value == value) {
                    return item;
                }
            }
            return null;
        }
    }
    /** 前照灯延时 跟随回家设置 */
    public static enum HeadlampsDelay {
        /** 关闭 */
        OFF((short) 0),
        /** 10s */
        second10((short) 1),
        /** 30s */
        second30((short) 2),
        /** 60s */
        second60((short) 3),
        /** 120s */
        second120((short) 4),
        /** invalid */
        INVALID((short) 255);
        
        public final short value;

        private HeadlampsDelay(short value) {
            this.value = value;
        }

        public static HeadlampsDelay getByIndex(short value) {
            HeadlampsDelay[] values = values();
            for (HeadlampsDelay item : values) {
                if (item.value == value) {
                    return item;
                }
            }
            return null;
        }
    }
    
    /** IGN */
    public static enum IGN {
        /** 关闭 */
        OFF((short) 0),
        /** ACC */
        ACC((short) 0),
        /** 开启 */
        ON((short) 0),
        /** invalid */
        INVALID((short) 255);
        
        public final short value;

        private IGN(short value) {
            this.value = value;
        }

        public static IGN getByIndex(short value) {
            IGN[] values = values();
            for (IGN item : values) {
                if (item.value == value) {
                    return item;
                }
            }
            return null;
        }
    }
    
    /** 按键种类 */
    public static enum Key_Type {
        Type1((short) 0),
        Type2((short) 1),
        Type3((short) 2);
        
        public final short value;

        private Key_Type(short value) {
            this.value = value;
        }

        public static Key_Type getByIndex(short value) {
            Key_Type[] values = values();
            for (Key_Type item : values) {
                if (item.value == value) {
                    return item;
                }
            }
            return null;
        }
    }
    
    /** KeyNumber 钥匙编号 */
    public static enum KeyNumber {
        /** invalid */
        invalid((short) 0),
        /** Key1 */
        Key1((short) 1),
        /** Key2 */
        Key2((short) 2),
        /** Key3 */
        Key3((short) 3),
        /** Key4 */
        Key4((short) 4),
        /** Key5 */
        Key5((short) 5),
        /** Key6 */
        Key6((short) 6);
        
        public final short value;

        private KeyNumber(short value) {
            this.value = value;
        }

        public static KeyNumber getByIndex(short value) {
            KeyNumber[] values = values();
            for (KeyNumber item : values) {
                if (item.value == value) {
                    return item;
                }
            }
            return null;
        }
    }
    
    /** 一键转向次数设置  */
    public static enum LaneChangeTurnLightSetStatus {
        /** 关闭 */
        OFF((short) 1),
        /** 3次 */
        Times3((short) 2),
        /** 5次 */
        Times5((short) 3),
        /** 7次 */
        Times7((short) 4),
        /** invalid */
        INVALID((short) 255);
        
        public final short value;

        private LaneChangeTurnLightSetStatus(short value) {
            this.value = value;
        }

        public static LaneChangeTurnLightSetStatus getByIndex(short value) {
            LaneChangeTurnLightSetStatus[] values = values();
            for (LaneChangeTurnLightSetStatus item : values) {
                if (item.value == value) {
                    return item;
                }
            }
            return null;
        }
    }
    
    /** 语言 */
    public static enum Language {
        /** 中文 */
        Chinese((short) 0),
        /** 英文 */
        English((short) 1);
        
        public final short value;

        private Language(short value) {
            this.value = value;
        }

        public static Language getByIndex(short value) {
            Language[] values = values();
            for (Language item : values) {
                if (item.value == value) {
                    return item;
                }
            }
            return null;
        }
    }
    
    /** 超速预警提示音（交通标志识别声音报警） */
    public static enum LAS_OverspeedSoundWarningEnable {
        /** 关闭 */
        OFF((short) 1),
        /** 标准 */
        ON((short) 2),
        /** invalid */
        INVALID((short) 255);
        
        public final short value;

        private LAS_OverspeedSoundWarningEnable(short value) {
            this.value = value;
        }

        public static LAS_OverspeedSoundWarningEnable getByIndex(short value) {
            LAS_OverspeedSoundWarningEnable[] values = values();
            for (LAS_OverspeedSoundWarningEnable item : values) {
                if (item.value == value) {
                    return item;
                }
            }
            return null;
        }
    }

    /** 超速预警（道路标识识别使能） 状态 */
    public static enum LAS_OverspeedWarningEnableStatus {
        /** 关闭 */
        OFF((short) 1),
        /** 开启 */
        ON((short) 2),
        /** invalid */
        INVALID((short) 2);
        
        public final short value;

        private LAS_OverspeedWarningEnableStatus(short value) {
            this.value = value;
        }

        public static LAS_OverspeedWarningEnableStatus getByIndex(short value) {
            LAS_OverspeedWarningEnableStatus[] values = values();
            for (LAS_OverspeedWarningEnableStatus item : values) {
                if (item.value == value) {
                    return item;
                }
            }
            return null;
        }
    }
    
    /** 超速预警偏差 */
    public static enum LAS_OverspeedWarningOffset {
        /** Over -10 km/h */
        KM_D_10((short) 0),
        /** Over -9 km/h */
        KM_D_9((short) 1),
        /** Over -8 km/h */
        KM_D_8((short) 2),
        /** Over -7 km/h */
        KM_D_7((short) 3),
        /** Over -6 km/h */
        KM_D_6((short) 4),
        /** Over -5 km/h */
        KM_D_5((short) 5),
        /** Over -4 km/h */
        KM_D_4((short) 6),
        /** Over -3 km/h */
        KM_D_3((short) 7),
        /** Over -2 km/h */
        KM_D_2((short) 8),
        /** Over -1 km/h */
        KM_D_1((short) 9),
        /** Over 0 km/h */
        KM_0((short) 10),
        /** Over 1 km/h */
        KM_O_1((short) 11),
        /** Over 2 km/h */
        KM_O_2((short) 12),
        /** Over 3 km/h */
        KM_O_3((short) 13),
        /** Over 4 km/h */
        KM_O_4((short) 14),
        /** Over 5 km/h */
        KM_O_5((short) 15),
        /** Over 6 km/h */
        KM_O_6((short) 16),
        /** Over 7 km/h */
        KM_O_7((short) 17),
        /** Over 8 km/h */
        KM_O_8((short) 18),
        /** Over 9 km/h */
        KM_O_9((short) 19),
        /** Over 10 km/h */
        KM_O_10((short) 20);
        
        public final short value;

        private LAS_OverspeedWarningOffset(short value) {
            this.value = value;
        }

        public static LAS_OverspeedWarningOffset getByIndex(short value) {
            LAS_OverspeedWarningOffset[] values = values();
            for (LAS_OverspeedWarningOffset item : values) {
                if (item.value == value) {
                    return item;
                }
            }
            return null;
        }
    }
    
    /** 灵敏度 LAS SensitivityStatus 状态 */
    public static enum LAS_SensitivityStatus {
        /** 高灵敏 */
        Early_Early_Aktiv((short) 0),
        /** 正常灵敏 */
        Early_Late_Aktiv((short) 1),
        /** Fault 低灵敏度 */
        Fault((short) 3),
        /** invalid */
        INVALID((short) 255);
        
        public final short value;

        private LAS_SensitivityStatus(short value) {
            this.value = value;
        }

        public static LAS_SensitivityStatus getByIndex(short value) {
            LAS_SensitivityStatus[] values = values();
            for (LAS_SensitivityStatus item : values) {
                if (item.value == value) {
                    return item;
                }
            }
            return null;
        }
    }
    
    /** LAS开关使能信号 */
    public static enum LASEnable{
        /** 关闭 */
        OFF((short) 0),
        /** 开启 */
        ON((short) 1),
        /** invalid */
        INVALID((short) 255);
        
        public final short value;

        private LASEnable(short value) {
            this.value = value;
        }

        public static LASEnable getByIndex(short value) {
            LASEnable[] values = values();
            for (LASEnable item : values) {
                if (item.value == value) {
                    return item;
                }
            }
            return null;
        }
    }
    
    /** LASModeSelectionStatus 状态 */
    public static enum LASModeSelectionStatus {
        /** （LDW，仅预警） */
        LDW((short) 1),
        /** LKA，仅纠偏 */
        LKA((short) 2),
        /** LDW+LKA，纠偏+预警 */
        LDW_LKA((short) 5),
        /** invalid */
        INVALID((short) 255);
        
        public final short value;

        private LASModeSelectionStatus(short value) {
            this.value = value;
        }

        public static LASModeSelectionStatus getByIndex(short value) {
            LASModeSelectionStatus[] values = values();
            for (LASModeSelectionStatus item : values) {
                if (item.value == value) {
                    return item;
                }
            }
            return null;
        }
    }
    
    /** 振动强度 */
    public static enum LASShakeLevStatus {
        /** 低 */
        Low((short) 1),
        /** 标准 */
        Normal((short) 2),
        /** 高 */
        High((short) 3),
        /** invalid */
        INVALID((short) 255);
        
        public final short value;

        private LASShakeLevStatus(short value) {
            this.value = value;
        }

        public static LASShakeLevStatus getByIndex(short value) {
            LASShakeLevStatus[] values = values();
            for (LASShakeLevStatus item : values) {
                if (item.value == value) {
                    return item;
                }
            }
            return null;
        }
    }
    
    /** 预警方式 */
    public static enum LASWarningModeSelectStas {
        /** 图像+声音 */
        Sound((short) 2),
        /** 图像+振动 */
        vibration((short) 3),
        /** 图像+声音+振动 */
        sound_vibration((short) 4),
        /** invalid */
        INVALID((short) 255);
        
        public final short value;

        private LASWarningModeSelectStas(short value) {
            this.value = value;
        }

        public static LASWarningModeSelectStas getByIndex(short value) {
            LASWarningModeSelectStas[] values = values();
            for (LASWarningModeSelectStas item : values) {
                if (item.value == value) {
                    return item;
                }
            }
            return null;
        }
    }
    
    /** 后追尾预警提示音 */
    public static enum LFWS {
        /** 关闭 */
        OFF((short) 1),
        /** 开启 */
        ON((short) 2),
        /** invalid */
        INVALID((short) 255);
        
        public final short value;

        private LFWS(short value) {
            this.value = value;
        }

        public static LFWS getByIndex(short value) {
            LFWS[] values = values();
            for (LFWS item : values) {
                if (item.value == value) {
                    return item;
                }
            }
            return null;
        }
    }

    public static enum MIC {
        /** 无MIC */
        None((short) 0),
        /** 单MIC */
        MIC1((short) 1),
        /** 双MIC */
        MIC2((short) 2);
        
        public final short value;

        private MIC(short value) {
            this.value = value;
        }

        public static MIC getByIndex(short value) {
            MIC[] values = values();
            for (MIC item : values) {
                if (item.value == value) {
                    return item;
                }
            }
            return null;
        }
    }
    
    /** 外后视镜自动折叠 */
    public static enum MirrorAutoFoldSetStatus {
        /** 关闭 */
        OFF((short) 9),
        /** 开启 */
        ON((short) 8),
        /** invalid */
        INVALID((short) 0);
        
        public final short value;

        private MirrorAutoFoldSetStatus(short value) {
            this.value = value;
        }

        public static MirrorAutoFoldSetStatus getByIndex(short value) {
            MirrorAutoFoldSetStatus[] values = values();
            for (MirrorAutoFoldSetStatus item : values) {
                if (item.value == value) {
                    return item;
                }
            }
            return null;
        }
    }
    
    /** 外后视镜倒车辅助 */
    public static enum MirrorSetStatus{
        /** 关闭 */
        OFF((short) 7),
        /** 开启 */
        ON((short) 6),
        /** invalid */
        INVALID((short) 255);
        
        public final short value;

        private MirrorSetStatus(short value) {
            this.value = value;
        }

        public static MirrorSetStatus getByIndex(short value) {
            MirrorSetStatus[] values = values();
            for (MirrorSetStatus item : values) {
                if (item.value == value) {
                    return item;
                }
            }
            return null;
        }

    }
    
    /** 下线配置其他操作选项 */
    public static enum OfLineOption {
        /** 调光类型 0: 小灯信号ILL 1：调光控制器 2：小灯信号ILL+调光控制器 */
        Option_Bright((short)5, (short)1, (short)2, CanManager.Bright.class),
        /** 调光控制器类型 0：无 1: PWM 信号 2: CAN 信号 */
        Option_Bright_Type((short)5, (short)3, (short)2, CanManager.Bright_Type.class),
        /** 摄像头 0：无摄像头 1：低配：UI 无泊车模式按钮及其控制 2：高配：UI 有泊车模式按钮及其控制 */
        Option_Camera((short)7, (short)1, (short)2, CanManager.Camera.class),
        /** 摄像头种类 0：无效 1：摄像头1 2：摄像头2 3：摄像头3 */
        Option_Camera_Type((short)7, (short)4, (short)3, CanManager.Camera_Type.class),
        /** 车型(用于匹配按键) 0：平台 1：CS35 2：CS75 3：CD101 4：C201 5：S401 6：B211 */
        Option_Car_Type((short)13, (short)1, (short)4, CanManager.Car_Type.class),
        /** DVD 区域码 0：无DVD 1~6：区域码 */
        Option_DVD_Region((short)1, (short)0, (short)4, CanManager.DVD_Region.class),
        /** 按键种类 0：形态二/101方向盘种类一 1：形态三/101方向盘种类二 2：101的方向盘种类三 */
        Option_Key_Type((short)13, (short)5, (short)2, CanManager.Key_Type.class),
        /** 语言 0: 中文 1: 英文 */
        Option_Language((short)0, (short)0, (short)4, CanManager.Language.class),
        /** 销售区域 0：中国大陆 1：日本 2：海湾地区 3：东南亚 4：西欧 5：东欧 6：北美 7：南美 8：非洲 9：澳洲 */
        Option_Sale_Region((short)0, (short)4, (short)4, CanManager.Sale_Region.class),
        /** 扬声器额定功率 0：无效 1：25W 2：xxx */
        Option_SPK_POWER((short)14, (short)1, (short)2, CanManager.SPK_POWER.class);
        
        public final short idx_param;
        public final short idx_bit;
        public final short length;
        public final Class<?> ret_class;

        private OfLineOption(short idx_param, short idx_bit, short length, Class<?> aClass) { this.idx_param = idx_param;
          this.idx_bit = idx_bit;
          this.length = length;
          this.ret_class = aClass;
        }
        
        public static OfLineOption getByIndex(short idx_param, short idx_bit){
            OfLineOption[] values = values();
            for (OfLineOption item : values) {
                if (item.idx_param == idx_param && item.idx_bit == idx_bit) {
                    return item;
                }
            }
            return null;
        }
    }
    
    /** 下线配置开关枚举  */
    public static enum OfLineSwitch
    {
        /** ECO */
        Switch_ECO((short) 3, (short) 0),
        /** 语音识别 */
        Switch_Voice((short) 3, (short) 1),
        /** 收音RDS */
        Switch_RDS((short) 3, (short) 2),
        /** AppStore */
        Switch_Store((short) 3, (short) 3),
        /** 时间显示 */
        Switch_Time_Show((short) 4, (short) 0),
        /** 车外温度显示 */
        Switch_OutTemp_Show((short) 4, (short) 1),
        /** 空调状态显示 */
        Switch_ACstate_Show((short) 4, (short) 2),
        /** 保养提示 */
        Switch_baoyang_alert((short) 4, (short) 3),
        /** 能量流app */
        Switch_lenliangliu_App((short) 4, (short) 4),
        /** 预约充电 */
        Switch_yuyuechongdian((short) 4, (short) 5),
        /** 盲区检测 */
        Switch_mangqu_check((short) 4, (short) 6),
        /** 导航功能 */
        Switch_Navi((short) 5, (short) 0),
        /** AVM */
        Switch_AVM((short) 7, (short) 0),
        /** 盲区 */
        Switch_mangqu((short) 7, (short) 3),
        /** 自动泊车（APA 2.0） */
        Switch_AutoPark((short) 7, (short) 7),
        /** CA_link */
        Switch_CA_link((short) 9, (short) 0),
        /** mirror_link */
        Switch_mirror_link((short) 9, (short) 1),
        /** carlife */
        Switch_carlife((short) 9, (short) 2),
        /** CarPlay */
        Switch_CarPlay((short) 9, (short) 3),
        /** DTV 功能 */
        Switch_DTV((short) 11, (short) 0),
        /** DVD-BOX 功能 */
        Switch_DVD((short) 11, (short) 1),
        /** CD-BOX */
        Switch_CD((short) 11, (short) 2),
        /**  行车记录仪 */
        Switch_DVR((short) 11, (short) 3),
        /** TBOX */
        Switch_TBOX((short) 11, (short) 4),
        /** AUX */
        Switch_AUX((short) 11, (short) 5),
        /** MIC配置 */
        Switch_MicConfig((short) 11, (short) 6),
        /** 折叠后视镜 */
        Switch_ZDHSJ((short) 12, (short) 0),
        /** 显示屏分辨率 */
        Switch_XSBFBL((short) 12, (short) 1),
        /** 触摸屏类型 */
        Switch_CMPLX((short) 12, (short) 2),
        /** 后枕屏接续 */
        Switch_HZPLX((short) 12, (short) 3),
        /** 后视镜倒车辅助设置 */
        Switch_HSJDCFZSZ((short) 12, (short) 4),
        /** 座椅礼让设置 */
        Switch_ZYLRSZ((short) 12, (short) 5),
        /** 氛围灯调节 */
        Switch_FWDTJ((short) 12, (short) 6),
        /** 倒车后雨刮刮刷设置 */
        Switch_DCHYGGSSZ((short) 12, (short) 7),
        /** 面板控制器通信方式 */
        Switch_MBKZQTXFS((short) 13, (short) 0),
        /** AMP 配置 */
        Switch_AMP((short) 14, (short) 0),
        /** 收音天线 */
        Switch_SYTX((short) 14, (short) 3),
        /** GPS 天线 */
        Switch_GPSTX((short) 14, (short) 4),
        /** 导航天线（集成式天线） */
        Switch_GPSTX_jichen((short) 14, (short) 5),
        /** 高性能音响系统 */
        Switch_Good_Sound((short) 14, (short) 6),
        /** 日间行车灯 */
        Switch_RJXCD((short) 15, (short) 0),
        /** 自适应巡航 */
        Switch_ZSYXH((short) 15, (short) 1),
        /** 车道辅助 */
        Switch_CDFZ((short) 15, (short) 2),
        /** 并线辅助 */
        Switch_BXFZ((short) 15, (short) 3);

        public final short idx_param;
        public final short idx_bit;

        private OfLineSwitch(short idx_param, short idx_bit) {
            this.idx_param = idx_param;
            this.idx_bit = idx_bit;
        }

        public static OfLineSwitch getByIndex(short idx_param, short idx_bit)
        {
            OfLineSwitch[] values = values();
            for (OfLineSwitch item : values) {
                if ((item.idx_param == idx_param) && (item.idx_bit == idx_bit)) {
                    return item;
                }
            }
            return null;
        }
    }
    
    public static enum CarSettingOption {
        /** IGN 1、 ON；2、OFF。 */
        Option_IGN((short)0, (short)0, (short)4, (short)178, (short)11, CanManager.IGN.class),
        /** 巡航 0: 自适应 1: 定速 short idx_save, short idx_getmcu, short idx_setmcu, short gid, short sid, Class */
        Option_CruiseControl((short)1, (short)0, (short)20, (short)178, (short)11, CanManager.CruiseControl.class),
        /** 车距提醒 */
        Option_DistanceRemind((short)2, (short)0, (short)109, (short)178, (short)11, CanManager.DistanceRemind.class),
        /** 前碰撞预警 */
        Option_FCWEnableStatus((short)3, (short)0, (short)45, (short)178, (short)11, CanManager.FCWEnableStatus.class),
        /** 前碰撞预警灵敏度 */
        Option_FCWSensitivityStatus((short)4, (short)0, (short)55, (short)178, (short)11, CanManager.FCWSensitivityStatus.class),
        /** 碰撞缓解 */
        Option_AutoBrakeEnableStatus((short)5, (short)0, (short)46, (short)178, (short)11, CanManager.AutoBrakeEnableStatus.class),
        /** 并线辅助（BSD/LCA） 参数值为{@link CanManager#BSD_LCA} */
        Option_BSD_LCA((short)6, (short)6, (short)47, (short)178, (short)11, CanManager.BSD_LCA.class),
        /** 倒车横向预警（CTA） */
        Option_CTA((short)7, (short)7, (short)49, (short)178, (short)11, CanManager.CTA.class),
        /** 后追尾预警（RCW） */
        Option_RCW((short)8, (short)8, (short)50, (short)178, (short)11, CanManager.RCW.class),
        /** 后追尾预警提示音 LFWS */
        Option_LFWS((short)9, (short)9, (short)110, (short)178, (short)11, CanManager.LFWS.class),
        /** 开门预警（SEA） */
        Option_SEA((short)10, (short)10, (short)111, (short)178, (short)11, CanManager.SEA.class),
        /** 预警方式 */
        Option_LASWarningModeSelectStas((short)11, (short)11, (short)56, (short)178, (short)11, CanManager.LASWarningModeSelectStas.class),
        /** 振动强度 */
        Option_LASShakeLevStatus((short)12, (short)0, (short)57, (short)178, (short)11, CanManager.LASShakeLevStatus.class),
        /** 超速预警（道路标识识别使能） */
        Option_LAS_OverspeedWarningEnableStatus((short)13, (short)0, (short)58, (short)178, (short)11, CanManager.LAS_OverspeedWarningEnableStatus.class),
        /** 超速预警偏差 */
        Option_LAS_OverspeedWarningOffset((short)14, (short)0, (short)59, (short)178, (short)11, CanManager.LAS_OverspeedWarningOffset.class),
        /** LAS开关使能信号 */
        Option_LASEnable((short)15, (short)0, (short)1, (short)178, (short)11, CanManager.LASEnable.class),
        /** 行车自动落锁 自动落锁设置{@link CanManager#AutoSpeedLockSet} */
        Option_AutoSpeedLockSet((short)16, (short)0, (short)41, (short)178, (short)11, CanManager.AutoSpeedLockSet.class),
        /** 二次落锁落锁设置 */
        Option_SecondLockSet((short)17, (short)0, (short)61, (short)178, (short)11, CanManager.SecondLockSet.class),
        /** 驻车自动解锁 解锁设置{@link CanManager#AutoParkUnlockSet} */
        Option_AutoParkUnlockSet((short)18, (short)0, (short)42, (short)178, (short)11, CanManager.AutoParkUnlockSet.class),
        /** 熄火自动解锁 {@link CanManager#AutoIGNOFFUnlockSet} */
        Option_AutoIGNOFFUnlockSet((short)19, (short)0, (short)4, (short)178, (short)11, CanManager.AutoIGNOFFUnlockSet.class),
        /** 氛围灯亮度 设置信号{@link CanManager#AtmosphereLightBrightnessSet} */
        Option_AtmosphereLightBrightnessSet((short)20, (short)0, (short)1, (short)178, (short)11, CanManager.AtmosphereLightBrightnessSet.class),
        /** 氛围灯颜色 设置信号{@link CanManager#AtmosphereLightColorSet} */
        Option_AtmosphereLightColorSet((short)21, (short)0, (short)1, (short)178, (short)11, CanManager.AtmosphereLightColorSet.class),
        /** 前照灯延时 跟随回家设置 &HeadlampsDelay */
        Option_HeadlampsDelay((short)22, (short)22, (short)38, (short)178, (short)11, CanManager.HeadlampsDelay.class),
        /** 一键转向次数设置 */
        Option_LaneChangeTurnLightSetStatus((short)23, (short)0, (short)39, (short)178, (short)11, CanManager.LaneChangeTurnLightSetStatus.class),
        /** 倒车后雨刮辅助自动刮刷 自动刮刷设置 */
        Option_RearWiperSet((short)24, (short)0, (short)62, (short)178, (short)11, CanManager.RearWiperSet.class),
        /** 座椅舒适进出 */
        Option_EasyEntrySet((short)25, (short)0, (short)31, (short)178, (short)11, CanManager.EasyEntrySet.class),
        /** 外后视镜自动折叠 外后视镜自动折叠设置 */
        Option_MirrorAutoFoldSetStatus((short)26, (short)0, (short)19, (short)178, (short)11, CanManager.MirrorAutoFoldSetStatus.class),
        /** 外后视镜倒车辅助 后视镜倒车辅助设置 */
        Option_MirrorSetStatus((short)27, (short)0, (short)19, (short)178, (short)11, CanManager.MirrorSetStatus.class),
        /** 灵敏度 */
        Option_LAS_SensitivityStatus((short)28, (short)2, (short)33, (short)178, (short)11, CanManager.LAS_SensitivityStatus.class),
        /** 目标提示音 TargetPrompt 状态 */
        Option_TargetPrompt((short)29, (short)3, (short)44, (short)178, (short)11, CanManager.TargetPrompt.class),
        /** 钥匙开锁通风设置 */
        Option_AutowindSet1((short)30, (short)3, (short)101, (short)178, (short)11, CanManager.AutowindSet1.class),
        /** 钥匙长按通风设置 */
        Option_AutowindSet2((short)31, (short)3, (short)102, (short)178, (short)11, CanManager.AutowindSet2.class),
        /** 钥匙闭锁通风 */
        Option_AutowindSet3((short)32, (short)3, (short)67, (short)178, (short)11, CanManager.AutowindSet3.class),
        /** 钥匙编号 */
        Option_KeyNumber((short)33, (short)3, (short)109, (short)178, (short)11, CanManager.KeyNumber.class),
        /** 超速预警提示音（交通标志识别声音报警） */
        Option_LAS_OverspeedSoundWarningEnable((short)34, (short)60, (short)60, (short)178, (short)11, CanManager.LAS_OverspeedSoundWarningEnable.class),
        /** 自动氛围灯调节 */
        Option_LAS_AutoFWDTJ((short)35, (short)3, (short)1, (short)178, (short)11, CanManager.AutoFWDTJ.class),
        /** 日间行车灯 */
        Option_Switch_RJXCD((short)36, (short)3, (short)54, (short)178, (short)11, CanManager.Switch_RJXCD.class),
        /** 智能远光灯 */
        Option_AHBA((short)37, (short)3, (short)53, (short)178, (short)11, CanManager.AHBA.class),
        /** 遥控钥匙解锁模式设置 */
        Option_RKEUnlockDoorTypeSet((short)38, (short)3, (short)40, (short)178, (short)11, CanManager.RKEUnlockDoorTypeSet.class),
        /** 道路标识识别使能 */
        Option_SLASwitch((short)39, (short)3, (short)52, (short)178, (short)11, CanManager.SLASwitch.class),
        /** 氛围灯自动调节 */
        Option_AtmosphereLightStaus((short)40, (short)3, (short)1, (short)178, (short)11, CanManager.AtmosphereLightStaus.class),
        /** 行车电脑数据开关 */
        Option_CarComputerData((short)41, (short)3, (short)33, (short)178, (short)11, CanManager.CarComputerData.class),
        /** 车辆设置使能 */
        Option_CarSetting((short)42, (short)3, (short)108, (short)178, (short)11, CanManager.CarSetting.class),
        /** 迎宾灯 */
        Option_WelcomeLight((short)43, (short)3, (short)43, (short)178, (short)11, CanManager.WelcomeLight.class),
        /** 空气净化动画语音提示音 */
        Option_AC_PlasmaSt((short)44, (short)3, (short)43, (short)178, (short)11, CanManager.AC_PlasmaSt.class);
        
        public final byte idx_getmcu;
        public final byte idx_setmcu;
        public final byte idx_save;
        public final byte gid;
        public final byte sid;
        public final Class<?> ret_class;

        private CarSettingOption(short idx_save, short idx_getmcu, short idx_setmcu, short gid, short sid, Class<?> aClass) {
          this.idx_getmcu = ((byte)idx_getmcu);
          this.idx_save = ((byte)idx_save);
          this.idx_setmcu = ((byte)idx_setmcu);
          this.gid = ((byte)gid);
          this.sid = ((byte)sid);
          this.ret_class = aClass;
        }

        public static CarSettingOption getByIndex(short gid, short sid, short idx_getmcu) {
            CarSettingOption[] values = values();
            for (CarSettingOption item : values) {
                if ((item.gid == gid) && (item.sid == sid) && (item.idx_getmcu == idx_getmcu)) {
                    return item;
                }
            }
            return null;
        }
    }
    
    /** 后追尾预警（RCW） */
    public static enum RCW {
        /** 关闭 */
        OFF((short) 1),
        /** 开启 */
        ON((short) 2),
        /** invalid */
        INVALID((short) 255);

        public final short value;

        private RCW(short value) {
            this.value = value;
        }

        public static RCW getByIndex(short value) {
            RCW[] values = values();
            for (RCW item : values) {
                if (item.value == value) {
                    return item;
                }
            }
            return null;
        }
    }
    
    /** 倒车后雨刮辅助自动刮刷 */
    public static enum RearWiperSet {
        /** 关闭 */
        OFF((short) 1),
        /** 开启 */
        ON((short) 2),
        /** invalid */
        INVALID((short) 255);

        public final short value;

        private RearWiperSet(short value) {
            this.value = value;
        }

        public static RearWiperSet getByIndex(short value) {
            RearWiperSet[] values = values();
            for (RearWiperSet item : values) {
                if (item.value == value) {
                    return item;
                }
            }
            return null;
        }
    }
    
    /** 遥控钥匙解锁模式设置 */
    public static enum RKEUnlockDoorTypeSet {
        /** 所有车门 */
        UnlockFourDoor((short) 1),
        /** 驾驶员车门 */
        UnlockDriverDoor((short) 2),
        /** invalid */
        INVALID((short) 255);

        public final short value;

        private RKEUnlockDoorTypeSet(short value) {
            this.value = value;
        }

        public static RKEUnlockDoorTypeSet getByIndex(short value) {
            RKEUnlockDoorTypeSet[] values = values();
            for (RKEUnlockDoorTypeSet item : values) {
                if (item.value == value) {
                    return item;
                }
            }
            return null;
        }
    }
    
    /** 销售区域 */
    public static enum Sale_Region {
        /** 中国大陆 */
        Chinese((short) 0),
        /** 日本 */
        Japan((short) 1),
        /** 海湾地区 */
        GulfArea((short) 2),
        /** 东南亚 */
        SoutheastAsia((short) 3),
        /** 西欧 */
        WestEurope((short) 4),
        /** 东欧 */
        EastEurope((short) 5),
        /** 北美 */
        NorthAmerica((short) 6),
        /** 南美 */
        SouthAmerica((short) 7),
        /** 非洲 */
        Africa((short) 8),
        /** 澳洲 */
        Australia((short) 9);

        public final short value;

        private Sale_Region(short value) {
            this.value = value;
        }

        public static Sale_Region getByIndex(short value) {
            Sale_Region[] values = values();
            for (Sale_Region item : values) {
                if (item.value == value) {
                    return item;
                }
            }
            return null;
        }
    }
    
    /** 开门预警（SEA） */
    public static enum SEA {
        /** 关闭 */
        OFF((short) 1),
        /** 开启 */
        ON((short) 2),
        /** invalid */
        INVALID((short) 255);

        public final short value;

        private SEA(short value) {
            this.value = value;
        }

        public static SEA getByIndex(short value) {
            SEA[] values = values();
            for (SEA item : values) {
                if (item.value == value) {
                    return item;
                }
            }
            return null;
        }
    }
    
    /** 二次落锁 */
    public static enum SecondLockSet {
        /** 关闭 */
        OFF((short) 2),
        /** 开启 */
        ON((short) 1),
        /** invalid */
        INVALID((short) 255);

        public final short value;

        private SecondLockSet(short value) {
            this.value = value;
        }

        public static SecondLockSet getByIndex(short value) {
            SecondLockSet[] values = values();
            for (SecondLockSet item : values) {
                if (item.value == value) {
                    return item;
                }
            }
            return null;
        }
    }
    
    /** SLASwitch 道路标识识别使能 */
    public static enum SLASwitch {
        /** 关闭 */
        OFF((short) 0),
        /** 开启 */
        ON((short) 1),
        /** invalid */
        INVALID((short) 255);

        public final short value;

        private SLASwitch(short value) {
            this.value = value;
        }

        public static SLASwitch getByIndex(short value) {
            SLASwitch[] values = values();
            for (SLASwitch item : values) {
                if (item.value == value) {
                    return item;
                }
            }
            return null;
        }
    }
    
    /**  扬声器额定功率 */
    public static enum SPK_POWER {
        /** 无效 */
        None((short) 0),
        /** 25W */
        W25((short) 1),
        Other((short) 2);

        public final short value;

        private SPK_POWER(short value) {
            this.value = value;
        }

        public static SPK_POWER getByIndex(short value) {
            SPK_POWER[] values = values();
            for (SPK_POWER item : values) {
                if (item.value == value) {
                    return item;
                }
            }
            return null;
        }
    }
    
    /** 雨量关天窗设置 */
    public static enum SunroofRainDetectClostSet {
        /** 关闭 */
        OFF((short) 1),
        /** 开启 */
        ON((short) 2),
        /** invalid */
        INVALID((short) 255);

        public final short value;

        private SunroofRainDetectClostSet(short value) {
            this.value = value;
        }

        public static SunroofRainDetectClostSet getByIndex(short value) {
            SunroofRainDetectClostSet[] values = values();
            for (SunroofRainDetectClostSet item : values) {
                if (item.value == value) {
                    return item;
                }
            }
            return null;
        }
    }
    
    /** 日间行车灯 */
    public static enum Switch_RJXCD {
        /** 关闭 */
        OFF((short) 1),
        /** 开启 */
        ON((short) 2),
        /** invalid */
        INVALID((short) 255);

        public final short value;

        private Switch_RJXCD(short value) {
            this.value = value;
        }

        public static Switch_RJXCD getByIndex(short value) {
            Switch_RJXCD[] values = values();
            for (Switch_RJXCD item : values) {
                if (item.value == value) {
                    return item;
                }
            }
            return null;
        }
    }
    
    /** 目标提示音 */
    public static enum TargetPrompt {
        /** 关闭 */
        OFF((short) 0),
        /** 仅识别提示 */
        DiscernW((short) 1),
        /** 仅消失提示 */
        DisappearW((short) 2),
        /** 识别和消失均提示 */
        DiscernAndDisappearW((short) 3),
        /** invalid */
        INVALID((short) 255);

        public final short value;

        private TargetPrompt(short value) {
            this.value = value;
        }

        public static TargetPrompt getByIndex(short value) {
            TargetPrompt[] values = values();
            for (TargetPrompt item : values) {
                if (item.value == value) {
                    return item;
                }
            }
            return null;
        }
    }
    
    /** 迎宾灯 */
    public static enum WelcomeLight {
        /** 关闭 */
        OFF((short) 2),
        /** 开启 */
        ON((short) 1),
        /** invalid */
        INVALID((short) 255);

        public final short value;

        private WelcomeLight(short value) {
            this.value = value;
        }

        public static WelcomeLight getByIndex(short value) {
            WelcomeLight[] values = values();
            for (WelcomeLight item : values) {
                if (item.value == value) {
                    return item;
                }
            }
            return null;
        }
    }
    
    /** can数据变化监听 */
    public static class CanChangedListener {
        /** 空调信息更新 */
        public void onAirConditionInfoChanged(AirConditionInfo aAirConditionInfo) {
        }

        /** 车门信息更新 */
        public void onDoorInfoChanged(DoorInfo aDoorInfo) {
        }

        /** 雷达信息更新 */
        public void onRadarInfoChanged(RadarInfo aRadarInfo) {
        }

        /** 胎压信息更新 */
        public void onTpmsInfoChanged(TpmsInfo aTpmsInfo) {
        }

        /** EcuInfo信息更新 */
        public void onEcuInfoChanged(EcuInfo aEcuInfo) {
        }

        /** MaintainInfo 信息更新 */
        public void onMaintainInfoChanged(MaintainInfo aMaintainInfo) {
        }

        /** 车锁信息改变 */
        public void onLockInfoChanged(byte[] param) {
        }

        /** 车速信息改变 */
        public void onSpeedInfoChanged(byte[] param) {
        }

        /** EcoInfoChanged */
        public void onEcoInfoChanged(byte[] pama) {
        }

        /** PM25InfoChange */
        public void onCarPM25InfoChange(CarPM25Info carPM25Info) {
        }

        /**
         * 下线配置开关更新
         * 
         * @param aOfLineSwitch 开关枚举
         * @param enable 开关状态
         */
        public void onOfLineSwitchChanged(OfLineSwitch aOfLineSwitch, boolean enable) {
        }

        /** 下线配置其他选项更新 */
        public void onOfLineOptionChanged(OfLineOption aOfLineOption,
                String aOfLineOptionValue) {
        }

        /**
         * onWheelAngleInfoNotify
         * 
         * @param angle 车轮转向角 (左 -45 ~ +45 右)
         */
        public void onWheelAngleInfoNotify(float angle) {
        }

        /**
         * 车辆设置更新
         * 
         * @param aOfLineOptionName 开关枚举
         * @param aOptionValue 开关状态
         */
        public void onCarSettingsChangeNotify(String aOfLineOptionName, String aOptionValue) {
        }

        /**
         * 行车电脑信息更新
         * 
         * @param info 行车电脑信息
         */
        public void onCarComputerInfoNotify(CarComputerInfo info) {
        }
    }
    
    /** 添加监听 */
    public void addCanChangedListener(CanChangedListener aListener) {
        if(aListener != null){
            synchronized (mCallbacks) {
                if(!mCallbacks.containsKey(aListener)){
                    mCallbacks.put(aListener, new CallBack(aListener, this.mService));
                }
            }
        }
    }
    
    /** 删除监听  */
    public void removeCanChangedListener(final CanChangedListener aListener) {
        if(aListener != null){
            synchronized (mCallbacks) {
                CallBack callback = mCallbacks.remove(aListener);
                if(this.mService != null){
                    try {
                        this.mService.unregisterCallBack(callback);
                    } catch (RemoteException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
    }
    
    /** 获取空调信息 */
    public AirConditionInfo getAirConditionInfo() {
        if (this.mService == null)
            return null;
        try {
            return this.mService.getAirConditionInfo();
        } catch (RemoteException e) {
            e.printStackTrace();
        }
        return null;
    }
    
    /**
     * 获取车辆设置开关
     * @param aCarSettingOption 想要获取的设置项 <p>eg. 获取目标提示音的当前状态 <p>mCanManager.getCarSettingOption(CarSettingOption.Option_TargetPrompt); 
     * @return 所要获取设置项当前值的枚举（string）
     */
    public String getCarSettingOption(CarSettingOption aCarSettingOption){
        if (this.mService == null)
            return null;
        try {
            return this.mService.getCarSettingOption(aCarSettingOption.name());
        } catch (RemoteException e) {
            e.printStackTrace();
        }
        return null;
    }
    
    /** 获取车门信息  */
    public DoorInfo getDoorInfo() {
        if (this.mService == null)
            return null;
        try {
            return this.mService.getDoorInfo();
        } catch (RemoteException e) {
            e.printStackTrace();
        }
        return null;
    }
    
    /** ECO信息 */
    public byte[] getEcoInfo() {
        if (this.mService == null)
            return null;
        try {
            return this.mService.getEcoInfo();
        } catch (RemoteException e) {
            e.printStackTrace();
        }
        return null;
    }
    
    /** 获取锁信息 */
    public byte[] getLockInfo(){
        if (this.mService == null)
            return null;
        try {
            return this.mService.getLockInfo();
        } catch (RemoteException e) {
            e.printStackTrace();
        }
        return null;
    }
    
    /** 获取保养信息 */
    public MaintainInfo getMaintainInfo(){
        if (this.mService == null)
            return null;
        try {
            return this.mService.getMaintainInfo();
        } catch (RemoteException e) {
            e.printStackTrace();
        }
        return null;
    }
    
    /** 获取下线配置其他选项值 */
    public String getOfLineOption(OfLineOption aOfLineOption){
        if (this.mService == null)
            return null;
        try {
            return this.mService.getOfLineOption(aOfLineOption.name());
        } catch (RemoteException e) {
            e.printStackTrace();
        }
        return null;
    }
    
    /** 获取下线配置开关 */
    public boolean getOfLineSwitch(OfLineSwitch aOfLineSwitch) {
        if (this.mService == null)
            return false;
        try {
            return this.mService.getOfLineSwitch(aOfLineSwitch.name());
        } catch (RemoteException e) {
            e.printStackTrace();
        }
        return false;
    }
    
    /** 获取雷达信息 */
    public RadarInfo getRadarInfo() {
        if(this.mService == null){
            return null;
        }
        try {
            return this.mService.getRadarInfo();
        } catch (RemoteException e) {
            e.printStackTrace();
        }
        return null;
    }

    /** 获取数度 */
    public byte[] getSpeedInfo() {
        if(this.mService == null){
            return null;
        }
        try {
            return this.mService.getSpeedInfo();
        } catch (RemoteException e) {
            e.printStackTrace();
        }
        return null;
    }
    
    /** 获取胎压信息 */
    public TpmsInfo getTpmsInfo(){
        if(this.mService == null){
            return null;
        }
        try {
            return this.mService.getTpmsInfo();
        } catch (RemoteException e) {
            e.printStackTrace();
        }
        return null;
    }
    
    /** 获取车轮转向角 (左 -45 ~ +45 右),若返回180,则表示结果无效  */
    public float getWheelAngle(){
        if (this.mService == null)
            return 180.0f;
          try {
            return this.mService.getWheelAngle();
          } catch (RemoteException e) {
            e.printStackTrace();
          }
        return 180f;
    }
    
    /**
     * 设置车辆设置开关
     * @param aCarSettingOption 车辆设置操作选项
     * @param aCarSettingOptionValue 该设置项的值
     * <p>eg. 设置巡航模式为 自适应巡航
     * <p>mCanManager.setCarSettingOption(CarSettingOption.Option_CruiseControl, CruiseControl.Adaptive.name());
     * <p> 执行结果请监听 CanChangedListeneron&CarSettingsChangeNotify
     */
    public void setCarSettingOption(CarSettingOption aCarSettingOption, String aCarSettingOptionValue){
        if(this.mService == null){
            return ;
        }
        try {
            this.mService.setCarSettingOption(aCarSettingOption.name(), aCarSettingOptionValue);
        } catch (RemoteException e) {
            e.printStackTrace();
        }
    }
    
    /** 设置下线配置开关  */
    public void setOfLineOption(OfLineOption aOfLineOption, String aOfLineOptionValue){
        if(this.mService == null){
            return ;
        }
        try {
            this.mService.setOfLineOption(aOfLineOption.name(), aOfLineOptionValue);
        } catch (RemoteException e) {
            e.printStackTrace();
        }
    }
    
    /** 设置下线配置开关 */
    public void setOfLineSwitch(OfLineSwitch aOfLineSwitch, boolean enable) {
        if(this.mService == null){
            return ;
        }
        try {
            this.mService.setOfLineSwitch(aOfLineSwitch.name(), enable);
        } catch (RemoteException e) {
            e.printStackTrace();
        }
    }
    
    /**
     * 
     * @param day 剩余保养天数
     * @param KM 剩余保养里程
     * @param hint 提示方式 0x0=无请求；0x1=保养预警请求；0x2=保养到期提示请求；0x3=Invalid；
     */
    public void setRemainderMaintainInfo(int day, int KM, int hint) {
        if(this.mService == null){
            return ;
        }
        try {
            this.mService.setRemainderMaintainInfo(day, KM, hint);
        } catch (RemoteException e) {
            e.printStackTrace();
        }
    }
    
    /** 轨迹划线切换 */
    public void trajectorySwitch(){
        if(this.mService == null){
            return ;
        }
        try {
            this.mService.trajectorySwitch();
        } catch (RemoteException e) {
            e.printStackTrace();
        }
    }
    
    private static final class CallBack extends ICanCallBackInterface.Stub{
        private Handler mHandler = null;
        private CanChangedListener mListener = null;
        
        private CallBack(CanChangedListener listener, ICanInterface service){
            mHandler = new Handler(Looper.getMainLooper());
            this.mListener = listener;
            registerICanInterface(service);
        }
        
        private void registerICanInterface(ICanInterface service){
            if(service == null){
                return;
            }
            try {
                service.registerCallBack(this);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        
        @Override
        public void onAirConditionInfoNotify(final AirConditionInfo aAirConditionInfo)
                throws RemoteException {
            this.mHandler.post(new Runnable() {
                public void run() {
                    CallBack.this.mListener.onAirConditionInfoChanged(aAirConditionInfo);
                }
            });
        }

        @Override
        public void onCarComputerInfoNotify(final CarComputerInfo info) throws RemoteException {
            this.mHandler.post(new Runnable() {
                public void run() {
                    CallBack.this.mListener.onCarComputerInfoNotify(info);
                }
            });
        }

        @Override
        public void onCarPM25InfoNotify(final CarPM25Info carPM25Info) throws RemoteException {
            this.mHandler.post(new Runnable() {
                public void run() {
                    CallBack.this.mListener.onCarPM25InfoChange(carPM25Info);
                }
            });
        }

        @Override
        public void onCarSettingsChangeNotify(final String aCarSettingsOptionName,
                final String aCarSettingsOptionValue) throws RemoteException {
            this.mHandler.post(new Runnable() {
                public void run() {
                    CallBack.this.mListener.onCarSettingsChangeNotify(aCarSettingsOptionName, aCarSettingsOptionValue);
                }
            });
        }

        @Override
        public void onDoorInfoNotify(final DoorInfo aDoorInfo) throws RemoteException {
            this.mHandler.post(new Runnable() {
                public void run() {
                    CallBack.this.mListener.onDoorInfoChanged(aDoorInfo);
                }
            });
        }

        @Override
        public void onEcoInfoNotify(final byte[] param) throws RemoteException {
            this.mHandler.post(new Runnable() {
                public void run() {
                    CallBack.this.mListener.onEcoInfoChanged(param);
                }
            });
        }

        @Override
        public void onEcuInfoNotify(final EcuInfo aEcuInfo) throws RemoteException {
            this.mHandler.post(new Runnable() {
                public void run() {
                    CallBack.this.mListener.onEcuInfoChanged(aEcuInfo);
                }
            });
        }

        @Override
        public void onLockInfoNotify(final byte[] param) throws RemoteException {
            this.mHandler.post(new Runnable() {
                public void run() {
                    CallBack.this.mListener.onLockInfoChanged(param);
                }
            });
        }

        @Override
        public void onMaintainInfoNotify(final MaintainInfo aMaintainInfo) throws RemoteException {
            this.mHandler.post(new Runnable() {
                public void run() {
                    CallBack.this.mListener.onMaintainInfoChanged(aMaintainInfo);
                }
            });
        }

        @Override
        public void onOfLineOptionNotify(final String aOfLineOptionName, final String aOfLineOptionValue)
                throws RemoteException {
            this.mHandler.post(new Runnable() {
                public void run() {
                    CallBack.this.mListener.onOfLineOptionChanged(
                            OfLineOption.valueOf(aOfLineOptionName), aOfLineOptionValue);
                }
            });
        }

        @Override
        public void onOfLineSwitchNotify(final String aOfLineSwitchName, final boolean enable)
                throws RemoteException {
            this.mHandler.post(new Runnable() {
                public void run() {
                    CallBack.this.mListener.onOfLineSwitchChanged(OfLineSwitch.valueOf(aOfLineSwitchName), enable);
                }
            });
        }

        @Override
        public void onRadarInfoNotify(final RadarInfo aRadarInfo) throws RemoteException {
            this.mHandler.post(new Runnable() {
                public void run() {
                    CallBack.this.mListener.onRadarInfoChanged(aRadarInfo);
                }
            });
        }

        @Override
        public void onSpeedInfoNotify(final byte[] param) throws RemoteException {
            this.mHandler.post(new Runnable() {
                public void run() {
                    CallBack.this.mListener.onSpeedInfoChanged(param);
                }
            });
        }

        @Override
        public void onTpmsInfoNotify(final TpmsInfo aTpmsInfo) throws RemoteException {
            this.mHandler.post(new Runnable() {
                public void run() {
                    CallBack.this.mListener.onTpmsInfoChanged(aTpmsInfo);
                }
            });
        }

        @Override
        public void onWheelAngleInfoNotify(final float angle) throws RemoteException {
            this.mHandler.post(new Runnable() {
                public void run() {
                    CallBack.this.mListener.onWheelAngleInfoNotify(angle);
                }
            });
        }
        
    }
}
