package com.incall.proxy.media.scan;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;

import com.incall.log.MjLog;
import com.incall.proxy.constant.MediaConstantsDef;
import com.incall.proxy.constant.SourceConstantsDef;

import android.text.TextUtils;

public class CoagentMediaScanManager {

	private android.content.Context mContext;

	private static String TAG = CoagentMediaScanManager.class.getName();

	private CoagentMediaScanManager() {
		// 私有构造函数 单例模式
	}

	private static CoagentMediaScanManager INSTANCE = new CoagentMediaScanManager();

	/**
	 * 获取单例
	 * 
	 * @param context
	 *            系统上下文
	 * @return CoagentMediaScanManager
	 */
	public static CoagentMediaScanManager getMediaScanManager(android.content.Context context) {
		INSTANCE.mContext = context;
		return INSTANCE;
	}

	/**
	 * 根据文件路径删除数据库内容
	 * 
	 * @param path
	 *            路径
	 * @return
	 */
	public int delDbDataByPath(java.lang.String path) {
		path=this.replaceHBSChars(path);
		MjLog.i(TAG, "---delDbDataByPath>>>：" + path);		
		int cnt = mContext.getContentResolver().delete(MediaDBConstants.FILES_URI, MediaDBConstants.C_FILES_PATH + "=?",
				new String[] { path });
		MjLog.i(TAG, "---delDbDataByPath>>> cnt：" + cnt);
		return cnt;
	}

	/**
	 * 删除所有收藏曲目
	 */
	public void deleteAllFavorite() {

		MjLog.i(TAG, "---deleteAllFavorite");
		int cnt = mContext.getContentResolver().delete(MediaDBConstants.FAVORITE_URI, null, null);
		MjLog.i(TAG, "---deleteAllFavorite>>> cnt：" + cnt);
	}

	private android.net.Uri getMediaUri(MediaConstantsDef.MEDIA_TYPE mediaType) {

		MjLog.i(TAG, "---getMediaUri>>> mediaType：" + mediaType);
		android.net.Uri uri = MediaDBConstants.MEDIA_URI;
		if (mediaType == MediaConstantsDef.MEDIA_TYPE.MUSIC) {
			uri = MediaDBConstants.AUDIO_URI;
		} else if (mediaType == MediaConstantsDef.MEDIA_TYPE.VIDEO) {
			uri = MediaDBConstants.VIDEO_URI;
		} else if (mediaType == MediaConstantsDef.MEDIA_TYPE.IMAGE) {
			uri = MediaDBConstants.IMAGE_URI;
		}
		MjLog.i(TAG, "---getMediaUri>>> uri：" + uri);
		return uri;
	}

	private String getPathLikeSelection() {
		return MediaDBConstants.C_FILES_PATH + " LIKE ?";
	}

	/**
	 * 获取设备下面的所有媒体
	 * 
	 * @param path
	 *            路径
	 * @param mediaType
	 *            媒体类型
	 * @return
	 */
	public android.database.Cursor getAllMedia(java.lang.String path, MediaConstantsDef.MEDIA_TYPE mediaType) {
		path=this.replaceHBSChars(path);
		MjLog.i(TAG, "---getAllMedia>>> path：" + path + ",mediaType:" + mediaType);		
		android.net.Uri mediaUri = getMediaUri(mediaType);
		String selection = getPathLikeSelection();
		String[] selectionArgs = { path + "%" };
		android.database.Cursor cursor=mContext.getContentResolver().query(mediaUri, MediaDBConstants.colums, selection, selectionArgs, null);
		if(cursor==null){
			MjLog.e(TAG, "---getAllMedia>>> path：" + path + ",mediaType:" + mediaType+",cursor is NULL");
		}else{
			MjLog.e(TAG, "---getAllMedia>>> path：" + path + ",mediaType:" + mediaType+",cnt: "+cursor.getCount());
		}
		return cursor;
	}

	/**
	 * 根据id3信息模糊查询数据库
	 * 
	 * @param title
	 *            名称
	 * @param artist
	 *            艺术家
	 * @param album
	 *            专辑名称
	 * @return
	 */
	public android.database.Cursor getCursorByID3(java.lang.String title, java.lang.String artist,
			java.lang.String album,SourceConstantsDef.SourceID sourceID) {

		MjLog.i(TAG, "---getCursorByID3>>> title：" + title + ",artist:" + artist + ",album:" + album);
		if ((TextUtils.isEmpty(title)) && (TextUtils.isEmpty(artist)) && (TextUtils.isEmpty(album))) {
			return null;
		}
		StringBuilder sql_condition = new StringBuilder();
		List<String> sql_args = new ArrayList<String>(3);
		if (!TextUtils.isEmpty(title)) {
			sql_args.add("%" + title + "%");
			sql_condition.append(MediaDBConstants.C_FILES_AUDIO_TITLE + " LIKE ? AND ");
		}

		if (!TextUtils.isEmpty(artist)) {
			sql_args.add("%" + artist + "%");
			sql_condition.append(MediaDBConstants.C_FILES_AUDIO_ARTIST + " LIKE ? AND ");
		}
		if (!TextUtils.isEmpty(album)) {
			sql_args.add("%" + album + "%");
			sql_condition.append(MediaDBConstants.C_FILES_AUDIO_ALBUM + " LIKE ? AND ");
		}
		int length = sql_condition.length();
		sql_condition = sql_condition.delete(length - 4, length - 1);

		MjLog.i(TAG, "---getCursorByID3>>> sql_condition：" + sql_condition);

		android.database.Cursor cursor = mContext.getContentResolver().query(MediaDBConstants.AUDIO_URI,
				MediaDBConstants.id3Colums, sql_condition.toString(), (String[]) sql_args.toArray(), null);
		if (cursor == null) {
			MjLog.e(TAG, "---getCursorByID3>>> title：" + title + ",artist:" + artist + ",album:" + album+",cursor is NULL");
			return null;
		}else{
			int cnt=cursor.getCount();
			if(cnt>0){
				cursor.moveToFirst();
			}
			MjLog.i(TAG, "---getCursorByID3>>> title：" + title + ",artist:" + artist + ",album:" + album+",cnt:"+cnt);
		}
		return cursor;
	}
	public android.database.Cursor getCursorByID3(String title, String artist, String album){
	    return getCursorByID3(title, artist, album, SourceConstantsDef.SourceID.NULL);
	}
	/**
	 * 获取收藏列表，目前只适用于音乐收藏
	 * 
	 * @param mediaType
	 *            媒体类型
	 * @return
	 */
	public android.database.Cursor getFavoriteList(MediaConstantsDef.MEDIA_TYPE mediaType) {
		
		MjLog.i(TAG, "---getFavoriteList>>> mediaType：" + mediaType);

		String sql = "SELECT t.* FROM " + MediaDBConstants.TABLE_FILES + " t JOIN " + MediaDBConstants.TABLE_FAVORITE
				+ " t2 ON t." + MediaDBConstants.C_FILES_PATH + "=t2." + MediaDBConstants.C_FILES_PATH + " ORDER BY t2."
				+ MediaDBConstants.C_ID + " DESC";

		MjLog.i(TAG, "---getFavoriteList>>> sql：" + sql);

		android.database.Cursor cursor = mContext.getContentResolver().query(MediaDBConstants.SQL_URI, null, sql, null,
				null);
		if(cursor==null){
			MjLog.e(TAG, "---getFavoriteList>>> cursor is NULL" );
		}else{
			int cnt=cursor.getCount();
			MjLog.i(TAG, "---getFavoriteList>>> mediaType：" + mediaType+",cnt:"+cnt);
		}
		return cursor;
	}

	/**
	 * 获取该路径下所有音乐文件的id3Cursor
	 * 
	 * @param path
	 *            路径
	 * @return
	 */
	public android.database.Cursor getID3CursorByPath(java.lang.String path) {
		MjLog.e(TAG, "---getID3CursorByPath>>> path：" + path);
		if (TextUtils.isEmpty(path)) {
			MjLog.e(TAG, "---getID3CursorByPath>>> path is NULL");
			return null;
		}
		android.database.Cursor cursor = mContext.getContentResolver().query(MediaDBConstants.AUDIO_URI,
				MediaDBConstants.id3Colums, getPathLikeSelection(), new String[] { path + "%" }, null);
		if (cursor == null) {
			MjLog.e(TAG, "---getID3CursorByPath>>> return cursor by path fail :" + path);
			return null;
		}else{
			int cnt=cursor.getCount();
			if(cnt>0){
				cursor.moveToFirst();
			}
			MjLog.i(TAG, "---getID3CursorByPath>>> path :" + path+",cnt:"+cnt);
		}
		
		return cursor;
	}

	private String getTableViewName(MediaConstantsDef.MEDIA_TYPE mediaType) {

		String table = MediaDBConstants.VIEW_MEDIA;
		if (mediaType == MediaConstantsDef.MEDIA_TYPE.MUSIC) {
			table = MediaDBConstants.VIEW_AUDIO;
		} else if (mediaType == MediaConstantsDef.MEDIA_TYPE.VIDEO) {
			table = MediaDBConstants.VIEW_VIDEO;
		} else if (mediaType == MediaConstantsDef.MEDIA_TYPE.IMAGE) {
			table = MediaDBConstants.VIEW_IMAGE;
		}
		return table;
	}

	/**
	 * 获取有媒体文件的文件夹
	 * 
	 * @param path
	 * @param mediaType
	 * @return
	 */
	public android.database.Cursor getMediaDirectorys(java.lang.String path, MediaConstantsDef.MEDIA_TYPE mediaType) {
		path=this.replaceHBSChars(path);
		if(!path.endsWith("/")){
			path=path+"/";
		}
		MjLog.i(TAG, "---getMediaDirectorys>>> path :" + path+",mediaType:"+mediaType);
		String table = getTableViewName(mediaType);
		try {
			path=URLEncoder.encode(path, "UTF-8");
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		android.net.Uri dirUri = android.net.Uri.parse(MediaDBConstants.DIRECTORY_URI+"?table=" + table + "&path=" + path);
		MjLog.i(TAG, "---getMediaDirectorys>>> dirUri :" + dirUri);
		android.database.Cursor cursor=mContext.getContentResolver().query(dirUri, MediaDBConstants.colums, null, null, null);
		if(cursor==null){
			MjLog.e(TAG, "---getMediaDirectorys>>> path :" + path+",mediaType:"+mediaType+",cursor is NULL");
		}else{
			int cnt=cursor.getCount();
			MjLog.i(TAG, "---getMediaDirectorys>>> path :" + path+",mediaType:"+mediaType+",cnt:"+cnt);
		}
		return cursor;
	}

	/**
	 * 获取有媒体文件
	 * 
	 * @param mediaType
	 *            媒体类型
	 * @return
	 */
	public android.database.Cursor getMediaFiles(java.lang.String path, MediaConstantsDef.MEDIA_TYPE mediaType) {
		path=this.replaceHBSChars(path);
		MjLog.i(TAG, "---getMediaFiles>>> path :" + path);
		if(!path.endsWith("/")){
			path=path+"/";
		}
		android.net.Uri mediaUri = getMediaUri(mediaType);
		android.database.Cursor cursor=mContext.getContentResolver().query(mediaUri, MediaDBConstants.colums, MediaDBConstants.C_FILES_PARENT_PATH+" = ?",
				new String[] { path }, null);
		if(cursor==null){
			MjLog.e(TAG, "---getMediaFiles>>> path :" + path+",mediaType:"+mediaType+",cursor is NULL");
		}else{
			int cnt=cursor.getCount();
			MjLog.i(TAG, "---getMediaFiles>>> path :" + path+",mediaType:"+mediaType+",cnt:"+cnt);
		}
		return cursor;
	}

	/**
	 * 获取媒体列表
	 * 
	 * @param path
	 *            路径
	 * @param mediaType
	 *            媒体类型
	 * @param isFileMode
	 * @return
	 */
	public android.database.Cursor getMediaList(java.lang.String path, MediaConstantsDef.MEDIA_TYPE mediaType,
			boolean isFileMode) {
		MjLog.i(TAG, "---getMediaList>>> path :" + path + ",mediaType:" + mediaType);
		if (TextUtils.isEmpty(path)) {
			path = MediaDBConstants.USB_ROOT;
		}
		if(!path.endsWith("/")){
			path=path+"/";
		}
		path=this.replaceHBSChars(path);
		MjLog.i(TAG, "---getMediaList next>>> path :" + path + ",mediaType:" + mediaType);
		android.database.Cursor cursor;
		if (isFileMode) {
			android.database.Cursor[] cursors = new android.database.Cursor[2];
			android.database.Cursor dirCursor = getMediaDirectorys(path, mediaType);
			cursors[0] = dirCursor;

			android.database.Cursor fileCursor = getMediaFiles(path, mediaType);
			cursors[1] = fileCursor;

			cursor = new android.database.MergeCursor(cursors);
		}else{
			cursor=getMediaFiles(path, mediaType);
		}
		if(cursor==null){
			MjLog.e(TAG, "---getMediaList>>> path :" + path+",mediaType:"+mediaType+",cursor is NULL");
		}else{
			int cnt=cursor.getCount();
			MjLog.i(TAG, "---getMediaList>>> path :" + path+",mediaType:"+mediaType+",cnt:"+cnt);
		}
		return cursor;
	}

	/**
	 * 获取音乐文件夹
	 * 
	 * @param mediaType
	 * @param source
	 * @return
	 */
	public android.database.Cursor getMusicFolder(MediaConstantsDef.MEDIA_TYPE mediaType,
			SourceConstantsDef.SourceID source) {

		MjLog.i(TAG, "---getMusicFolder>>> mediaType:" + mediaType);
		StringBuilder sql = new StringBuilder("SELECT DISTINCT ");
		sql.append(MediaDBConstants.C_FILES_PARENT_PATH).append(" FROM ").append(MediaDBConstants.VIEW_AUDIO);
		if (source == SourceConstantsDef.SourceID.HDD) {
			sql.append(" WHERE ").append(MediaDBConstants.C_FILES_PARENT_PATH).append(" LIKE '")
					.append(MediaDBConstants.HDD_ROOT).append("/%'");
		} else if (source == SourceConstantsDef.SourceID.USB) {
			sql.append(" WHERE ").append(MediaDBConstants.C_FILES_PARENT_PATH).append(" LIKE '")
					.append(MediaDBConstants.USB_ROOT).append("/%'");
		}
		MjLog.i(TAG, "---getMusicFolder>>> sql:" + sql);
		android.database.Cursor cursor=mContext.getContentResolver().query(MediaDBConstants.SQL_URI, null, sql.toString(), null, null);
		
		if(cursor==null){
			MjLog.e(TAG, "---getMusicFolder>>> source :" + source+",mediaType:"+mediaType+",cursor is NULL");
		}else{
			int cnt=cursor.getCount();
			MjLog.i(TAG, "---getMusicFolder>>> source :" + source+",mediaType:"+mediaType+",cnt:"+cnt);
		}
		return cursor;
	}

	/**
	 * 获取扫描状态
	 * 
	 * @return
	 */
	public MediaConstantsDef.SCAN_STATUS getScanStatus() {
		MjLog.i(TAG, "---getScanStatus>>> enter");
		android.database.Cursor cursor = mContext.getContentResolver().query(MediaDBConstants.SCAN_URI, null, null,
				null, null);
		try{
			if (cursor != null&&cursor.moveToFirst()) {
				String status = cursor.getString(0);
				cursor = null;
				MjLog.i(TAG, "---getScanStatus>>> status:" + status);
				return MediaConstantsDef.SCAN_STATUS.valueOf(status);
			}else{
				MjLog.e(TAG, "---getScanStatus>>> have no record");
				return null;
			}
		}finally{
			if (cursor!=null&&!cursor.isClosed()) {
				cursor.close();
			}
			cursor=null;
		}
		
	}

	/**
	 * 根据路径判断是否被收藏
	 * 
	 * @return
	 */
	public boolean isFavorite(java.lang.String path) {
		path=this.replaceHBSChars(path);
		MjLog.i(TAG, "---isFavorite>>> path:" + path);
		boolean result = false;
		String selection = MediaDBConstants.C_FILES_PATH + "=?";
		String[] selectionArgs = { path };
		android.database.Cursor cursor = mContext.getContentResolver().query(MediaDBConstants.FAVORITE_URI,
				new String[] { MediaDBConstants.C_IS_FAVORITE }, selection, selectionArgs, null);
		try{
			if (cursor != null&&cursor.moveToFirst()) {
				Integer is_favorite = cursor.getInt(0);
				if (is_favorite == 1) {
					result = true;
				}
				MjLog.i(TAG,"---isFavorite>>> path:" + path + "," + MediaDBConstants.C_IS_FAVORITE + ":" + is_favorite);
			}
		}finally{
			if (cursor!=null&&!cursor.isClosed()) {
				cursor.close();
			}
			cursor=null;
		}
		MjLog.i(TAG, "---isFavorite>>> path:" + path + ",result:" + result);
		return result;
	}

	/**
	 * 查询语句
	 * 
	 * @param sql
	 * @return
	 */
	public android.database.Cursor queryWithSQL(java.lang.String sql) {
		sql=this.replaceHBSChars(sql);
		MjLog.i(TAG, "---queryWithSQL>>> sql:" + sql);
	    android.database.Cursor cursor=mContext.getContentResolver().query(MediaDBConstants.SQL_URI, null, sql, null, null);
	    if(cursor==null){
			MjLog.e(TAG, "---queryWithSQL>>> sql :" + sql+",cursor is NULL");
		}else{
			int cnt=cursor.getCount();
			MjLog.i(TAG, "---queryWithSQL>>> sql :" + sql+",cnt:"+cnt);
		}
	    return cursor;
	}

	/**
	 * 设置收藏曲目
	 * 
	 * @param path
	 *            曲目路径
	 * @param favorite
	 *            是否收藏
	 */
	public void setFavorite(java.lang.String path, boolean favorite) {
		path=this.replaceHBSChars(path);
		MjLog.i(TAG, "---setFavorite>>> path:" + path+",favorite:"+favorite);
		android.content.ContentValues values = new android.content.ContentValues();
	    values.put(MediaDBConstants.C_FILES_PATH, path);
	    values.put(MediaDBConstants.C_IS_FAVORITE, favorite);
	    mContext.getContentResolver().insert(MediaDBConstants.FAVORITE_URI, values);
	}

	/**
	 * 替换好帮手字符
	 * @param path
	 * @return
	 */
	private String replaceHBSChars(String path){
		if(path==null){
			return path;
		}
		path=path.replace("/storage/emulated/0/incall/media", "/storage/extsd");
		path=path.replace("/storage/emulated/0", "/storage/extsd");
		return path;
	}
}
