package com.example.dialogview;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.provider.Settings;
import android.provider.Settings.SettingNotFoundException;
import android.util.Log;
import android.view.LayoutInflater;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.CompoundButton.OnCheckedChangeListener;
import android.widget.RelativeLayout;
import android.widget.SeekBar;
import android.widget.SeekBar.OnSeekBarChangeListener;

import com.example.skiptext.R;

public class BrightnessDialogView implements OnSeekBarChangeListener, OnCheckedChangeListener{
	private Context dialogContext;
	private RelativeLayout brightnessLayout;
	public static int maxBrightness = 255;
	public static int minBrightness = 25;
	private int curBrightness ;
	private SeekBar brightnessSeekBar;
	private CheckBox autoCheckBox;
	public static final int SCREEN_BRIGHTNESS_MODE_AUTOMATIC = Settings.System.SCREEN_BRIGHTNESS_MODE_AUTOMATIC;
	private boolean curMode;
	public BrightnessDialogView(Context context){
		dialogContext = context;
		log.d("hell0","nihao");
		brightnessLayout = (RelativeLayout) LayoutInflater.
				from(dialogContext).inflate(R.layout.brightness_dialogview, null);
		autoCheckBox = (CheckBox) brightnessLayout.findViewById(R.id.brightnessBox);
		brightnessSeekBar = (SeekBar) brightnessLayout.findViewById(R.id.brightnessBar);
		try {
			curBrightness = Settings.System.getInt(dialogContext.
					getContentResolver(), Settings.System.SCREEN_BRIGHTNESS);
			curMode = Settings.System.getInt(dialogContext.getContentResolver(), 
					Settings.System.SCREEN_BRIGHTNESS_MODE)==SCREEN_BRIGHTNESS_MODE_AUTOMATIC;
		} catch (SettingNotFoundException e) {
			// TODO 自动生成的 catch 块
			e.printStackTrace();
		}
	}
	public void brightnessDialog(){
		log.d("hell0","nihao");
		
		log.d("hell0","nihao");
		log.d("hell0","nihao");
		brightnessSeekBar.setMax(maxBrightness-minBrightness);
		brightnessSeekBar.setProgress(curBrightness-minBrightness);
		brightnessSeekBar.setOnSeekBarChangeListener(this);
		autoCheckBox.setOnCheckedChangeListener(this);
		Log.d("brightness is", curBrightness +"...");
		Log.d("automatic is", curMode +"...");
		if (curMode) {
			autoCheckBox.setChecked(true);
		}
		new AlertDialog.Builder(dialogContext)
			.setIcon(R.drawable.settings_light)
			.setTitle("亮度调节")
			.setView(brightnessLayout)
			.setPositiveButton("确定", new OnClickListener() {
				
				@Override
				public void onClick(DialogInterface dialog, int which) {
					// TODO 自动生成的方法存根
					
				}
			})
			.create()
			.show();
	}
	@Override
	public void onCheckedChanged(CompoundButton buttonView, boolean isAuto) {
		// TODO 自动生成的方法存根
		curMode = isAuto;
		if (curMode) {
			Settings.System.putInt(dialogContext.getContentResolver(), 
					Settings.System.SCREEN_BRIGHTNESS_MODE, 1);
			brightnessSeekBar.setEnabled(false);
			Log.d("curMode is", curMode +"...");
		}
		else {			
			Settings.System.putInt(dialogContext.getContentResolver(), 
					Settings.System.SCREEN_BRIGHTNESS_MODE, 0);
			brightnessSeekBar.setEnabled(true);
			Log.d("curMode is", curMode +"...");
		}
	}
	@Override
	public void onProgressChanged(SeekBar seekBar, int progress,
			boolean fromUser) {
		// TODO 自动生成的方法存根
		curBrightness = progress+minBrightness;
		Settings.System.putInt(dialogContext.getContentResolver(), 
				Settings.System.SCREEN_BRIGHTNESS, curBrightness);
		Log.d("curBrightness is", curBrightness +"...");
	}
	@Override
	public void onStartTrackingTouch(SeekBar seekBar) {
		// TODO 自动生成的方法存根
		
	}
	@Override
	public void onStopTrackingTouch(SeekBar seekBar) {
		// TODO 自动生成的方法存根
		
	}
}
