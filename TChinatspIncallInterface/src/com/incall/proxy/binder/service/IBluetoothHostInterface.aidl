package com.incall.proxy.binder.service;

interface IBluetoothHostInterface{
 android.os.IBinder	getBtServiceClient() ;
 void	setBtServiceClient(android.os.IBinder aDiscClient) ;
 }