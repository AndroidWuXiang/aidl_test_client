package com.example.wuxiang_pc.aidl_test_client;

import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.IBinder;
import android.os.RemoteException;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;

import com.example.wuxiang_pc.aidl_test1.Book;
import com.example.wuxiang_pc.aidl_test1.IBookManager;

public class MainActivity extends AppCompatActivity {
    private IBookManager mIBookManager;
    public ServiceConnection conn = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName componentName, IBinder iBinder) {
            mIBookManager = IBookManager.Stub.asInterface(iBinder);
            try{
                if(mIBookManager != null) {
                    //mIBookManager.addBook(new Book(1, "挪威的森林"));
                    mIBookManager.test();
                    Toast.makeText(MainActivity.this, "Success", Toast.LENGTH_SHORT).show();
                }
            }
            catch(RemoteException e){

            }
        }

        @Override
        public void onServiceDisconnected(ComponentName componentName) {

        }
    };
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Intent intent = new Intent();
        intent.setAction("com.wuxiang_pc.remote");
        intent.setPackage(getPackageName());
        Boolean b = bindService(intent,conn,BIND_AUTO_CREATE);
        Toast.makeText(MainActivity.this, "===:"+b, Toast.LENGTH_SHORT).show();
    }
}
