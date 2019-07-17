package com.kim344.utils.util;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface.OnCancelListener;

/**
 * 로딩 다이얼로그 띄우는거 관련 처리
 * @author genius
 * 
 */
public class LoadingDialogFactory 
{
	private static LoadingDialogFactory instance;
	
	private LoadingDialogFactory(){}
	
	/**
	 * 로딩 프로그래스 다이얼로그 리턴
	 * @param context
	 * @param message
	 * @param listener
	 * @return
	 */
	public ProgressDialog getLoadingDialog(Context context, String message, OnCancelListener listener)
	{
		ProgressDialog progressDialog = new ProgressDialog(context);
		progressDialog.setMessage(message);
		progressDialog.setCancelable(true);
		progressDialog.setOnCancelListener(listener);
		
		return progressDialog;
	}
	
	public static LoadingDialogFactory getInstance()
	{
		if(instance == null)
		{
			instance = new LoadingDialogFactory();
		}
		
		return instance;
	}
}
