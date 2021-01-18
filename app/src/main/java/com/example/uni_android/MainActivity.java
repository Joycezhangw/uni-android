package com.example.uni_android;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;

import com.alibaba.fastjson.JSONObject;
import com.io.uniplugin.serialport.libs.SerialPortHelper;
import com.io.uniplugin.serialport.listener.OnOpenSerialPortListener;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.lang.reflect.Array;

import android_serialport_api.SerialPort;
import android_serialport_api.SerialPortFinder;
import io.dcloud.feature.uniapp.bridge.UniJSCallback;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = MainActivity.class.getSimpleName();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);

        String[] path = new SerialPortHelper().getAllDeicesPath();
        Log.e(TAG, "---serialPort_path begin--");
        Log.v(TAG,arrayToString(path));
        Log.e(TAG, "---serialPort_path end--");
        String[]  devices= new SerialPortFinder().getAllDevices();
        Log.e(TAG, "---serialPort_Devices begin--");
        Log.v(TAG,arrayToString(devices));
        Log.e(TAG, "---serialPort_Devices end--");
        open("ttyMT3",9600);

    }

    String arrayToString(String[] f){
        String output = "";
        String delimiter = "\n"; // Can be new line \n tab \t etc...
        for (int i=0; i<f.length; i++)
        {
            output = output + f[i] + delimiter;
        }

        return output;
    }

    public void open(String port, int baudRate) {
        Log.e(TAG,"打开串口 begin");
        new SerialPortHelper().setIOpenSerialPortListener(new OnOpenSerialPortListener() {
            @Override
            public void onSuccess(final File device) {
                Log.e(TAG,"打开成功");
            }

            @Override
            public void onFail(final File device, Status status) {
                Log.e(TAG,"打开失败");
            }
        });
        try {
            new SerialPortHelper().openSerialPort(port, baudRate);
        } catch (Exception e) {
            Log.e(TAG,"NO_READ_WRITE_PERMISSION");
        }
        Log.e(TAG,"打开串口 end");
    }

}