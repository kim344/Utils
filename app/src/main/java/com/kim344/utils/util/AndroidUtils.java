package com.kim344.utils.util;

import android.app.ActivityManager;
import android.app.ActivityManager.RunningServiceInfo;
import android.app.AlertDialog;
import android.content.ComponentName;
import android.content.Context;
import android.content.DialogInterface;
import android.content.pm.PackageManager;
import android.content.pm.PackageManager.NameNotFoundException;
import android.graphics.Bitmap;
import android.graphics.Bitmap.CompressFormat;
import android.graphics.Bitmap.Config;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.PorterDuff.Mode;
import android.graphics.PorterDuffXfermode;
import android.graphics.Rect;
import android.graphics.RectF;
import android.media.ExifInterface;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Environment;
import android.text.InputType;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;


import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;

public class AndroidUtils
{
	/**
	 * check network
	 * @param context
	 * @return
	 */
	public static boolean isUsableNetwork(Context context)
	{
		boolean isWifi = false, isMobile = false;
		
		ConnectivityManager manager = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);

		NetworkInfo infoWifi = manager.getNetworkInfo(ConnectivityManager.TYPE_WIFI);
		
		isWifi = (infoWifi != null) && infoWifi.isConnected();
		
		NetworkInfo infoMobile = manager.getNetworkInfo(ConnectivityManager.TYPE_MOBILE);
		
		isMobile = (infoMobile != null) && infoMobile.isConnected();
	 	
		return isWifi || isMobile;
	}
	
	/**
	 * toast short message
	 * @param context
	 * @param str
	 */
	public static void showToastLong(Context context, String str)
	{
		if(context != null)
			Toast.makeText(context, str, Toast.LENGTH_LONG).show();
	}

	/**
	 * toast long message
	 * @param context
	 * @param str
	 */
	public static void showToastShort(Context context, String str)
	{
		if(context != null)
			Toast.makeText(context, str, Toast.LENGTH_SHORT).show();
	}
	
	/**
	 * toast short message and gravity center vertical 
	 * @param context
	 * @param str
	 */
	public static void showToastShortCenter(Context context, String str)
	{
		if(context != null)
		{
			try
			{
				Toast toast= Toast.makeText(context, str, Toast.LENGTH_SHORT);  
				toast.setGravity(Gravity.CENTER_VERTICAL, 0, 0);
				toast.show();
			}
			catch(Exception e)
			{
				
			}
		}
		
	}
	
	/**
	 * toast long message and gravity center vertical 
	 * @param context
	 * @param str
	 */
	public static void showToastLongCenter(Context context, String str)
	{
		if(context != null)
		{
			try
			{
				Toast toast= Toast.makeText(context, str, Toast.LENGTH_LONG);  
				toast.setGravity(Gravity.CENTER_VERTICAL, 0, 0);
				toast.show();
			}
			catch(Exception e)
			{
				e.printStackTrace();
			}
		}
		
	}
	
	public static void showToastLongTop(Context context, String str)
	{
		if(context != null)
		{
			try
			{
				Toast toast= Toast.makeText(context, str, Toast.LENGTH_LONG);  
				toast.setGravity(Gravity.TOP, 0, 0);
				toast.show();
			}
			catch(Exception e)
			{
				
			}
		}
		
	}

//	/**
//	 * get device id
//	 * @param context
//	 * @return
//	 */
//	public static String getDeviceUUID(Context context)
//	{
//		TelephonyManager manager = (TelephonyManager) context.getSystemService(Context.TELEPHONY_SERVICE);
//
//		String tmDevice = "" + manager.getDeviceId();
//		String tmSerial = "" + manager.getSimSerialNumber();
//		String androidId = "" + android.provider.Settings.Secure.getString(context.getContentResolver(), android.provider.Settings.Secure.ANDROID_ID);
//
//		UUID devideUUID = new UUID(androidId.hashCode(), ((long) tmDevice.hashCode() << 32) | tmSerial.hashCode());
//
//		return devideUUID.toString();
//	}
	
	/**
	 * change dp to pixel 
	 * @param context
	 * @param dp
	 * @return
	 */
	public static int dpToPixels(Context context, int dp)
	{
		DisplayMetrics displayMetrics = context.getResources().getDisplayMetrics();
	    return (int)((dp * displayMetrics.density) + 0.5);
	}
	
	/**
	 * check existing image file
	 * @param context
	 * @param imageName
	 * @return
	 */
	public static boolean isImageExist(Context context, String imageName)
	{
//		File file = new File(getApplicationImagePath(context) + "/images/" + imageName);
		File file = new File(getApplicationImagePath(context) + imageName);
		Log.d("IMAGE", file.getAbsolutePath() + " file is exists? : [" + file.exists() + "]");
		return file.exists();
	}
	
	/**
	 * save bitmap image at external storage
	 * @param context
	 * @param bitmap
	 * @param imageName
	 * @return
	 */
	public static File saveImageExternal(Context context, Bitmap bitmap, String imageName)
	{
        File imageFile = new File(getApplicationImagePath(context) + imageName); 
        
		if(imageFile.exists())
		{
			imageFile.delete();
		}
		
		try
		{
			imageFile.createNewFile();

			BufferedOutputStream out = new BufferedOutputStream(new FileOutputStream(imageFile));
			bitmap.compress(CompressFormat.JPEG, 100, out);

			out.flush();
			out.close();
		}
		catch (IOException e)
		{
			e.printStackTrace();
		}
		
		return imageFile;
	}
	
	/**
	 * load bitmap from storage 
	 * @param context
	 * @param imageName
	 * @return
	 */
	public static Bitmap loadImageExternal(Context context, String imageName) 
	{ 
		Bitmap bitmap = null;

		try
		{
			File file = new File(getApplicationImagePath(context), imageName); 
			FileInputStream streamIn = new FileInputStream(file);
			bitmap = BitmapFactory.decodeStream(streamIn); 
		}
		catch (FileNotFoundException e)
		{
			e.printStackTrace();
		}

		return bitmap;
    }
	
	/**
	 * save bitmap image in application 
	 * (not used)
	 * @param context
	 * @param bitmap
	 * @param imageName
	 * @return
	 */
	public static boolean saveImageInternal(Context context, Bitmap bitmap, String imageName)
	{
		try
		{
			BufferedOutputStream out = new BufferedOutputStream(context.openFileOutput(imageName, 0));
			bitmap.compress(CompressFormat.JPEG, 100, out);

			out.flush();
			out.close();
		}
		catch (IOException e)
		{
			e.printStackTrace();
		}

		return true;
	}
	
	/**
	 * path application directory 
	 * @param context
	 * @return
	 */
	public static String getApplicationImagePath(Context context)
	{
		StringBuffer buffer = new StringBuffer();
		
		buffer.append(Environment.getExternalStorageDirectory().toString());
		buffer.append("/Android/data/");
        buffer.append(context.getPackageName());
        buffer.append("/images/");
        
        File storageDir = new File(buffer.toString());
        
		if(!storageDir.exists())
		{
			storageDir.mkdirs();
		}
        
        return buffer.toString();
	}
	
	/**
	 * 
	 * "확인", "취소" 버튼이 있는 대화상자 반환.
	 * @param context
	 * @param message
	 * @param positiveButtonText
	 * @param negativeButtonText
	 * @param positiveCallback
	 * @param negativeCallback
	 * @return
	 */
	public static AlertDialog makeYesNoAlertDialog(Context context, String message, 
			String positiveButtonText, String negativeButtonText, AlertDialog.OnClickListener positiveCallback, 
			AlertDialog.OnClickListener negativeCallback)
	{
		AlertDialog ad = new AlertDialog.Builder(context)
		.setMessage(message)
		.setPositiveButton(positiveButtonText, positiveCallback)
		.setNegativeButton(negativeButtonText, negativeCallback)
		.create();
		return ad;
	}
	/**
	 * "확인" 버튼만 있는 대화상자 반환.
	 * 
	 * @param context
	 * @param message
	 * @param okButtonText
	 * @param positiveCallback
	 * @return
	 */
	public static AlertDialog makeOKAlertDialog(Context context, String message,String okButtonText, AlertDialog.OnClickListener positiveCallback)
	{
		AlertDialog ad = new AlertDialog.Builder(context)
		.setMessage(message)
		.setPositiveButton(okButtonText, positiveCallback)
		.create();
		return ad;
	}
	/**
	 * 입력 다이얼로그
	 * @param context
	 * @param title
	 * @param callback
	 * @return
	 */
	public static AlertDialog makeInputDialog(Context context, String title, final IAlertInputCallback callback)
	{
		AlertDialog.Builder builder = new AlertDialog.Builder(context);
		builder.setTitle(title);
		// Set up the input
		final EditText input = new EditText(context);
		// Specify the type of input expected; this, for example, sets the input as a password, and will mask the text
//		input.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_PASSWORD);
		input.setInputType(InputType.TYPE_CLASS_TEXT);
		builder.setView(input);

		// Set up the buttons
		builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
			
			@Override
			public void onClick(DialogInterface dialog, int which) {
				callback.getText(input.getText().toString());
			}
		});
		builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
			
			@Override
			public void onClick(DialogInterface dialog, int which) {
				dialog.dismiss();
			}
		});

		return builder.create();
	}
	
	public static AlertDialog makeInputDialog(Context context, int inputType, String title, final IAlertInputCallback callback)
	{
		AlertDialog.Builder builder = new AlertDialog.Builder(context);
		builder.setTitle(title);
		// Set up the input
		final EditText input = new EditText(context);
		// Specify the type of input expected; this, for example, sets the input as a password, and will mask the text
//		input.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_PASSWORD);
		input.setInputType(inputType);
		builder.setView(input);

		// Set up the buttons
		builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
			
			@Override
			public void onClick(DialogInterface dialog, int which) {
				callback.getText(input.getText().toString());
			}
		});
		builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
			
			@Override
			public void onClick(DialogInterface dialog, int which) {
				dialog.dismiss();
			}
		});

		return builder.create();
	}
	
	public interface IAlertInputCallback
	{
		void getText(String message);
	}
	
	public static boolean isMyServiceRunning(Context context, Class<?> serviceClass) 
	{
	    ActivityManager manager = (ActivityManager) context.getSystemService(Context.ACTIVITY_SERVICE);
	    for (RunningServiceInfo service : manager.getRunningServices(Integer.MAX_VALUE)) 
	    {
	        if (serviceClass.getName().equals(service.service.getClassName())) 
	        {
	            return true;
	        }
	    }
	    return false;
	}
	
	public static boolean isMyAppRunning(Context context, String packageName)
	{
		ActivityManager manager = (ActivityManager) context.getSystemService(Context.ACTIVITY_SERVICE);
		List < ActivityManager.RunningTaskInfo > taskInfo = manager.getRunningTasks(1);

		ComponentName componentInfo = taskInfo.get(0).topActivity;
		//if  app is running
        return componentInfo.getPackageName().equalsIgnoreCase(packageName);
    }
	
	/**
	 * 설치된 앱 버전정보 가져오기
	 * @return
	 */
	public static String getAppVersion(Context context)
	{
		String appVersion = "";
		try 
        {
			appVersion = context.getPackageManager().getPackageInfo(context.getPackageName(), PackageManager.GET_META_DATA).versionName;
        }
        catch (NameNotFoundException e) 
        {  
        	e.printStackTrace();
        }
		return appVersion;
	}
	
	/**
	 *  특정 이미지 뷰 크기게 맞춰서 비트맵을 설정한다.
	 *  sd카드에 저장된 파일을 이용한다면 이 메서드를 사용하면 된다.
	 * @param v			: 이미지뷰
	 * @param path		: 파일경로
	 * @param isRounded : 라운딩 여부
	 */
	public static void setImageWithScale(ImageView v, String path, boolean isRounded)
	{
		try
		{
			BitmapFactory.Options options = new BitmapFactory.Options();
			options.inSampleSize = 2;
			
			Bitmap src = BitmapFactory.decodeFile(path, options);
//			
//			Log.d(TAG, "bitmap width : [" + src.getWidth() + "], height : [" + src.getHeight() + "]");
//			Log.d(TAG, "target image view width : [" + v.getWidth() + "], height : [" + v.getWidth() + "]");
			
			ExifInterface exif = new ExifInterface(path);
		    int exifOrientation = exif.getAttributeInt(ExifInterface.TAG_ORIENTATION, ExifInterface.ORIENTATION_NORMAL);
		    int exifDegree = exifOrientationToDegrees(exifOrientation);
		    src = rotate(src, exifDegree);
			
			Bitmap scaledBitmap = Bitmap.createScaledBitmap(src, v.getWidth(), v.getHeight(), false);
			if(isRounded)
			{
				v.setImageBitmap(getRoundedCornerBitmap(scaledBitmap));
			}
			else
			{
				v.setImageBitmap(scaledBitmap);
			}
		}
		catch(Exception e)
		{
			Log.e("AndroidUtils", "image scale exception : [" + e + "]", e);
		}
	}
	
	/**
	 * 특정 이미지 뷰 크기게 맞춰서 비트맵을 설정한다.
	 * 물리적 경로 sd카드가 아닌 Bitmap데이터를 이용하고자 할때 사용하면 된다.
	 * @param v			: 이미지뷰
	 * @param bitmap	: 비트맵
	 * @param isRounded : 라운딩 여부
	 */
	public static void setImageWithScale(ImageView v, Bitmap bitmap, boolean isRounded)
	{
		try
		{
			BitmapFactory.Options options = new BitmapFactory.Options();
			options.inSampleSize = 2;
			
			Bitmap scaledBitmap = Bitmap.createScaledBitmap(bitmap, v.getWidth(), v.getHeight(), false);
			if(isRounded)
			{
				v.setImageBitmap(getRoundedCornerBitmap(scaledBitmap));
			}
			else
			{
				v.setImageBitmap(scaledBitmap);
			}
		}
		catch(Exception e)
		{
			Log.e("AndroidUtils", "image scale exception : [" + e + "]", e);
		}
	}
	
	/**
	 * 특정 이미지 뷰 크기게 맞춰서 비트맵을 설정한다.
	 * resource 데이터를 이용하고자 할 때 사용하면 된다.
	 * @param context
	 * @param v			: 이미지뷰
	 * @param resourceId	: resource id
	 * @param isRounded : 라운딩 여부
	 */
	public static void setImageWithScale(Context context, ImageView v, int resourceId, boolean isRounded)
	{
		try
		{
			BitmapFactory.Options options = new BitmapFactory.Options();
			options.inSampleSize = 2;
			
			Bitmap bitmap = BitmapFactory.decodeResource(context.getResources(), resourceId, options);
			Bitmap scaledBitmap = Bitmap.createScaledBitmap(bitmap, v.getWidth(), v.getHeight(), false);
			v.setBackgroundResource(0);
			if(isRounded)
			{
				v.setImageBitmap(getRoundedCornerBitmap(scaledBitmap));
			}
			else
			{
				v.setImageBitmap(scaledBitmap);
			}
		}
		catch(Exception e)
		{
			Log.e("AndroidUtils", "image scale exception : [" + e + "]", e);
		}
	}
	
	/**
	 * 이미지를 회전시킵니다.
	 *
	 * @param bitmap 비트맵 이미지
	 * @param degrees 회전 각도
	 * @return 회전된 이미지
	 */
	public static Bitmap rotate(Bitmap bitmap, int degrees)
	{
	  if(degrees != 0 && bitmap != null)
	  {
	    Matrix m = new Matrix();
	    m.setRotate(degrees, (float) bitmap.getWidth() / 2,
	    (float) bitmap.getHeight() / 2);
	    
	    try
	    {
	      Bitmap converted = Bitmap.createBitmap(bitmap, 0, 0,
	      bitmap.getWidth(), bitmap.getHeight(), m, true);
	      if(bitmap != converted)
	      {
	        bitmap.recycle();
	        bitmap = converted;
	      }
	    }
	    catch(OutOfMemoryError ex)
	    {
	      // 메모리가 부족하여 회전을 시키지 못할 경우 그냥 원본을 반환합니다.
	    }
	  }
	  return bitmap;
	}
	
	/**
	 * EXIF정보를 회전각도로 변환하는 메서드
	 *
	 * @param exifOrientation EXIF 회전각
	 * @return 실제 각도
	 */
	public static int exifOrientationToDegrees(int exifOrientation)
	{
	  if(exifOrientation == ExifInterface.ORIENTATION_ROTATE_90)
	  {
	    return 90;
	  }
	  else if(exifOrientation == ExifInterface.ORIENTATION_ROTATE_180)
	  {
	    return 180;
	  }
	  else if(exifOrientation == ExifInterface.ORIENTATION_ROTATE_270)
	  {
	    return 270;
	  }
	  return 0;
	}
	
	public static Bitmap getRoundedCornerBitmap(Bitmap bitmap) 
	{
		Bitmap output = Bitmap.createBitmap(bitmap.getWidth(), bitmap.getHeight(), Config.ARGB_8888);
		Canvas canvas = new Canvas(output);

		final int color = 0xff424242;
		final Paint paint = new Paint();
		final Rect rect = new Rect(0, 0, bitmap.getWidth(), bitmap.getHeight());
		final RectF rectF = new RectF(rect);
		final float roundPx = 30;

		paint.setAntiAlias(true);
		canvas.drawARGB(0, 0, 0, 0);
		paint.setColor(color);
		canvas.drawRoundRect(rectF, roundPx, roundPx, paint);

		paint.setXfermode(new PorterDuffXfermode(Mode.SRC_IN));
		canvas.drawBitmap(bitmap, rect, rect, paint);

		return output;
	}
	
	public static <T extends View> T getView(View view, int id) 
	{
		View childView = view.findViewById(id);
		return (T) childView;
	}
	
//	public static String getAccountWithUUID(Context context, String tail)
//	{
//		String email = null;
//		String userID = null;
//		Account[] accounts =  AccountManager.get(context).getAccounts();
//		for(int i=0; i<accounts.length; i++)
//		{
//			if(accounts[i].type.equals("com.google"))
//			{
//				email = StringUtils.substringBeforeLast(accounts[i].name, "@");
//				break;
//			}
//	    }
//		if(email != null)
//		{
//			String uuid = AndroidUtils.getDeviceUUID(context);
//			if(StringUtils.isNotBlank(tail))
//			{
//				userID = email + "_" + uuid + "_" + tail;
//			}
//			else
//			{
//				userID = email + "_" + uuid;
//			}
//		}
//		return userID;
//	}
}