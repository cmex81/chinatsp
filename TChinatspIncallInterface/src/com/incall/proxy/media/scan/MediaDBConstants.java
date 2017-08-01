package com.incall.proxy.media.scan;

public class MediaDBConstants {

	/**
	 * 媒体数据库AUTHORITY
	 */
	public final static java.lang.String AUTHORITY = "CoagentMedia";

	/**
	 * usb根路径
	 */
	public final static java.lang.String USB_ROOT = "/storage/udisk";

	/**
	 * hdd根路径
	 */
	public final static java.lang.String HDD_ROOT = "/storage/extsd";//"/storage/emulated/0";

	// URI--------------start------------
	
	private static final android.net.Uri CONTENT_URI = android.net.Uri.parse("content://CoagentMedia");
	
	/**
	 * 音频文件uri
	 */
	public final static android.net.Uri AUDIO_URI = android.net.Uri.withAppendedPath(CONTENT_URI,"audio");
	/**
	 * 文件夹uri
	 */
	public final static android.net.Uri DIRECTORY_URI =  android.net.Uri.withAppendedPath(CONTENT_URI,"directory");

	/**
	 * 收藏
	 */
	public final static android.net.Uri FAVORITE_URI =  android.net.Uri.withAppendedPath(CONTENT_URI,"favorite");

	/**
	 * 媒体文件uri
	 */
	public final static android.net.Uri FILES_URI =  android.net.Uri.withAppendedPath(CONTENT_URI,"files");

	/**
	 * 扫描uri
	 */
	public final static android.net.Uri SCAN_URI =  android.net.Uri.withAppendedPath(CONTENT_URI,"scan");

	/**
	 * sql查询uri
	 */
	public final static android.net.Uri SQL_URI =  android.net.Uri.withAppendedPath(CONTENT_URI,"RawSql");

	/**
	 * 视频文件uri
	 */
	public final static android.net.Uri VIDEO_URI =  android.net.Uri.withAppendedPath(CONTENT_URI,"video");

	/**
	 * 图片文件uri
	 */
	public final static android.net.Uri IMAGE_URI =  android.net.Uri.withAppendedPath(CONTENT_URI,"image");

	/**
	 * 媒体所有媒体文件uri
	 */
	public final static android.net.Uri MEDIA_URI =  android.net.Uri.withAppendedPath(CONTENT_URI,"media");

	// URI--------------end------------

	// 表--------------start------------

	/**
	 * table
	 */
	public final static java.lang.String TABLE = "table";

	/**
	 * 收藏表
	 */
	public final static java.lang.String TABLE_FAVORITE = "favorite";

	// 表--------------end------------

	/**
	 * files表
	 */
	public final static java.lang.String TABLE_FILES = "files";

	// 视图--------------start------------

	/**
	 * audio视图
	 */
	public final static java.lang.String VIEW_AUDIO = "audio";

	/**
	 * image视图
	 */
	public final static java.lang.String VIEW_IMAGE = "image";

	/**
	 * media视图
	 */
	public final static java.lang.String VIEW_MEDIA = "media";

	/**
	 * video视图
	 */
	public final static java.lang.String VIEW_VIDEO = "video";

	// 视图--------------end------------

	// 列--------------start------------

	/**
	 * album of id3
	 */
	public final static java.lang.String C_FILES_AUDIO_ALBUM = "album";

	/**
	 * artist of id3
	 */
	public final static java.lang.String C_FILES_AUDIO_ARTIST = "artist";

	/**
	 * duration of id3
	 */
	public final static java.lang.String C_FILES_AUDIO_DURATION = "duration";

	/**
	 * 音频是否存在
	 */
	public final static java.lang.String C_FILES_AUDIO_EXIST = "audio_exist";

	/**
	 * title of id3
	 */
	public final static java.lang.String C_FILES_AUDIO_TITLE = "title";

	/**
	 * 文件是否存在
	 */
	public final static java.lang.String C_FILES_FILE_EXIST = "file_exist";

	/**
	 * id3是否存在
	 */
	public final static java.lang.String C_FILES_ID3_EXIST = "id3_exist";

	/**
	 * 图片是否存在
	 */
	public final static java.lang.String C_FILES_IMAGE_EXIST = "image_exist";

	/**
	 * 是否是文件夹 1:directory, 0:file
	 */
	public final static java.lang.String C_FILES_IS_DIRECTORY = "is_directory";

	/**
	 * 媒体类型 1:audio,2:video,3:image
	 */
	public final static java.lang.String C_FILES_MEDIA_TYPE = "media_type";

	/**
	 * mod_time 时间
	 */
	public final static java.lang.String C_FILES_MOD_TIME = "mod_time";

	/**
	 * 文件名
	 */
	public final static java.lang.String C_FILES_NAME = "name";

	/**
	 * name_py
	 */
	public final static java.lang.String C_FILES_NAME_PY = "name_py";

	/**
	 * 父文件夹路径
	 */
	public final static java.lang.String C_FILES_PARENT_PATH = "parent_path";

	/**
	 * 文件 path
	 */
	public final static java.lang.String C_FILES_PATH = "path";

	/**
	 * 视频是否存在
	 */
	public final static java.lang.String C_FILES_VIDEO_EXIST = "video_exist";

	/**
	 * ID
	 */
	public final static java.lang.String C_ID = "_id";

	/**
	 * 是否收藏曲目，1是收藏，否则不是收藏
	 */
	public final static java.lang.String C_IS_FAVORITE = "is_favorite";

	// 列--------------end------------

	/**
	 * colums 查询列 C_ID C_FILES_NAME C_FILES_PATH C_FILES_PARENT_PATH
	 * C_FILES_IS_DIRECTORY C_FILES_MEDIA_TYPE C_IS_FAVORITE
	 */
	public final static java.lang.String[] colums = { C_ID, C_FILES_NAME, C_FILES_PATH, C_FILES_PARENT_PATH, C_FILES_IS_DIRECTORY,
			C_FILES_MEDIA_TYPE, C_IS_FAVORITE };

	/**
	 * id3Colums 查询列 C_ID, C_FILES_PATH, C_FILES_AUDIO_TITLE,
	 * C_FILES_AUDIO_ALBUM, C_FILES_AUDIO_ARTIST, C_FILES_AUDIO_DURATION
	 */
	public final static java.lang.String[] id3Colums = { C_ID, C_FILES_PATH, C_FILES_AUDIO_TITLE, C_FILES_AUDIO_ALBUM,
			C_FILES_AUDIO_ARTIST, C_FILES_AUDIO_DURATION };

	/**
	 * 文件夹
	 */
	public final static java.lang.String DIRECTORY = "is_directory";

	/**
	 * 路径
	 */
	public final static java.lang.String PATH = "path";

	/**
	 * 扫描
	 */
	public final static java.lang.String SCAN = "scan";

	/**
	 * sql语句
	 */
	public final static java.lang.String SQL = "RawSql";

}
