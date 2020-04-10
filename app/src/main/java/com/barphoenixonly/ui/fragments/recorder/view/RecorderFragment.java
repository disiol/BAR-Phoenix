package com.barphoenixonly.ui.fragments.recorder.view;


import android.Manifest;
import android.annotation.SuppressLint;
import android.content.Context;
import android.content.pm.PackageManager;

import android.media.MediaPlayer;
import android.media.MediaRecorder;
import android.os.Bundle;
import android.os.Environment;
import android.os.SystemClock;
import android.util.Log;
import android.view.View;
import android.widget.Chronometer;
import android.widget.EditText;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import com.barphoenixonly.R;
import com.barphoenixonly.databinding.FragmentWebBinding;
import com.barphoenixonly.routers.main.MainActivityRouter;
import com.barphoenixonly.ui.base.BaseBindingFragment;
import com.barphoenixonly.ui.fragments.recorder.presenter.RecorderPresenter;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;




public class RecorderFragment extends BaseBindingFragment<RecorderPresenter, FragmentWebBinding> implements RecorderView {
    private static final String TAG = "SoundRecordingDemo";

    private ImageView mStartRecordingButton, mStopRecordingButton, playButton;
    private MediaRecorder mediaRecorder;
    private File fileName;
    private MediaPlayer mediaPlayer;
    private EditText titelRecordEditText;
    private Chronometer chronometer;

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        mediaRecorder = new MediaRecorder();

        mStartRecordingButton = getActivity().findViewById(R.id.record_button);
        mStopRecordingButton = getActivity().findViewById(R.id.stop_button);
        titelRecordEditText = getActivity().findViewById(R.id.record_title_textView);
        chronometer = getActivity().findViewById(R.id.time_record_chronometer);

        mStartRecordingButton.setOnClickListener(view1 -> {
            try {


                if (ContextCompat.checkSelfPermission(getActivity().getApplicationContext(),
                        Manifest.permission.RECORD_AUDIO) != PackageManager.PERMISSION_GRANTED && ContextCompat.checkSelfPermission(getActivity().getApplicationContext(),
                        Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {

                    String[] permissions = {Manifest.permission.RECORD_AUDIO, Manifest.permission.WRITE_EXTERNAL_STORAGE, Manifest.permission.READ_EXTERNAL_STORAGE};
                    ActivityCompat.requestPermissions(getActivity(), permissions, 0);
                } else {
                    mStartRecordingButton.setEnabled(false);
                    mStopRecordingButton.setEnabled(true);
                    mStopRecordingButton.requestFocus();

                    recordStart();
                }
            } catch (Exception e) {
                e.printStackTrace();
                Log.e(TAG, "Caught io exception " + e.getMessage());
            }
        });

        mStopRecordingButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                mStartRecordingButton.setEnabled(true);
                mStopRecordingButton.setEnabled(false);
                mStartRecordingButton.requestFocus();
                recordStop();
                chronometer.stop();
            }
        });

        mStopRecordingButton.setEnabled(false);
        mStartRecordingButton.setEnabled(true);
    }


    public void recordStart() {
        if (fileName == null) {

            chronometer.setBase(SystemClock.elapsedRealtime());
            chronometer.start();
            try {
                File sampleDir = new File(
                        Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_MUSIC), getActivity().getPackageName() + getResources().getString(R.string.app_name));
                if (!sampleDir.exists()) {
                    sampleDir.mkdirs();
                }
                @SuppressLint("SimpleDateFormat") SimpleDateFormat timeStampFormat = new SimpleDateFormat("yyyy-MM-dd-HH.mm.ss");


                fileName = File.createTempFile(titelRecordEditText.getText().toString() +  timeStampFormat.format(new Date()), ".3gp", sampleDir);
            } catch (IOException e) {

                Log.e(TAG, "sdcard access error");
                return;
            }
        }

        try {
            releaseRecorder();

            if (fileName.exists()) {
                fileName.delete();
            }

            mediaRecorder = new MediaRecorder();
            mediaRecorder.setAudioSource(MediaRecorder.AudioSource.MIC);
            mediaRecorder.setOutputFormat(MediaRecorder.OutputFormat.THREE_GPP);
            mediaRecorder.setAudioEncoder(MediaRecorder.AudioEncoder.AMR_NB);
            mediaRecorder.setOutputFile(fileName.getPath());
            mediaRecorder.prepare();
            mediaRecorder.start();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public void recordStop() {
        if (mediaRecorder != null) {
            mediaRecorder.stop();
            mediaRecorder.release();
            fileName = null;
        }
    }

    private void releaseRecorder() {
        if (mediaRecorder != null) {
            mediaRecorder.release();
            mediaRecorder = null;
        }
    }

    private void releasePlayer() {
        if (mediaPlayer != null) {
            mediaPlayer.release();
            mediaPlayer = null;
        }
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        releasePlayer();
        releaseRecorder();
    }

    @Override
    public int getLayoutResId() {
        return R.layout.fragment_record;
    }

    @Override
    public void showSite() {

    }

    @Override
    public void showMessage(String message) {

    }

    @Override
    public void showError(Throwable throwable, MainActivityRouter mainActivityRouter) {

    }
}