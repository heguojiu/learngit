package com.example.dialogview;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.media.AudioManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.widget.RelativeLayout;
import android.widget.SeekBar;
import android.widget.SeekBar.OnSeekBarChangeListener;

import com.example.skiptext.R;

public class VolumDialogView implements OnSeekBarChangeListener {
	private Context volumDialogContext;
	private SeekBar volumSeekBar;
	private int maxVolum = 15 ;
	private int curVolum ;
	private int stepVolum = 1;
	private AudioManager audioManager;
	private RelativeLayout volumLayout;
	public VolumDialogView(Context context){
		volumDialogContext = context;
		volumLayout = (RelativeLayout) LayoutInflater.from(volumDialogContext).inflate(R.layout.volum_dialogview, null);
		volumSeekBar = (SeekBar) volumLayout.findViewById(R.id.volumBar);
		audioManager = (AudioManager) volumDialogContext.getSystemService(Context.AUDIO_SERVICE);
		maxVolum = audioManager.getStreamMaxVolume(AudioManager.STREAM_MUSIC);
		curVolum = audioManager.getStreamVolume(AudioManager.STREAM_MUSIC);
		Log.d("maxVolum is :", maxVolum + "...");
		Log.d("curVolum is :", curVolum + "...");
	}
	public void volumDialog(){
		volumSeekBar.setMax(maxVolum);
		volumSeekBar.setProgress(curVolum);
		volumSeekBar.setOnSeekBarChangeListener(this);
		new AlertDialog.Builder(volumDialogContext)
			.setIcon(R.drawable.settings_sound)
			.setTitle("声音调节")
			.setView(volumLayout)
			.setPositiveButton("确定", new OnClickListener() {
				
				@Override
				public void onClick(DialogInterface dialog, int which) {
					// TODO 自动生成的方法存根
					
				}
			})
			.create()
			.show();
	}
	public void adjustVolum(){
		audioManager.setStreamVolume(AudioManager.STREAM_MUSIC, 
				curVolum, AudioManager.FLAG_PLAY_SOUND);
	}
	@Override
	public void onProgressChanged(SeekBar seekBar, int myVolum,
			boolean fromUser) {
		// TODO 自动生成的方法存根
		curVolum = myVolum;
		adjustVolum();
		Log.d("myVolum is :", myVolum + "...");
		
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
