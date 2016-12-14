package permission.andgo.com.permission_master;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import static permission.andgo.com.permission_master.Contants.PERMISSION_CALL_PHONR;
import static permission.andgo.com.permission_master.Contants.PERMISSION_CAMRRA;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initView();
    }

    /**
     * 初始化控件并给按钮设置点击监听事件
     */
    private void initView(){
        //打电话
        findViewById(R.id.btn_call).setOnClickListener(this);
        //拍照
        findViewById(R.id.btn_camera).setOnClickListener(this);
        //进入下一个界面
        findViewById(R.id.btn_toPackageActivity).setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.btn_call:
                //1、检查打电话的权限是否开启(granted--授权，同意)
                if(ActivityCompat.checkSelfPermission(this, Manifest.permission.CALL_PHONE)
                        != PackageManager.PERMISSION_GRANTED){
                    //2、未开启则需要开启
                    ActivityCompat.requestPermissions(this,
                            new String[]{Manifest.permission.CALL_PHONE},
                            Contants.PERMISSION_CALL_PHONR);
                }else{
                    //3、需要开启则直接拨打电话
                    callPhone();
                }
                break;
            case R.id.btn_camera:
                if(ActivityCompat.checkSelfPermission(this,Manifest.permission.CAMERA)
                        !=PackageManager.PERMISSION_GRANTED){
                    ActivityCompat.requestPermissions(this,
                            new String[]{Manifest.permission.CAMERA},
                            Contants.PERMISSION_CAMRRA);
                }else{
                    useCamera();
                }
                break;
            case R.id.btn_toPackageActivity:
                startActivity(new Intent(this,PackageActivity.class));
                break;
        }
    }

    /**
     * 用户是否允许开启权限后的回调方法，异步处理
     *
     * @param requestCode--请求码
     * @param permissions--请求的权限列表
     * @param grantResults--是否允许的结果
     */
    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        switch (requestCode){
            case PERMISSION_CALL_PHONR:
                if(grantResults[0]==PackageManager.PERMISSION_GRANTED){
                    callPhone();
                }else{
                    Toast.makeText(this,"关闭此权限将不能拨打电话,可去设置里自行打开",Toast.LENGTH_SHORT).show();
                }
                break;
            case PERMISSION_CAMRRA:
                if(grantResults[0]==PackageManager.PERMISSION_GRANTED){
                    useCamera();
                }else{
                    Toast.makeText(this,"关闭此权限将不能打开相机,可去设置里自行打开",Toast.LENGTH_SHORT).show();
                }
                break;
        }
    }

    private void callPhone(){
        Intent intent=new Intent(Intent.ACTION_CALL,Uri.parse("tel:"+"110"));
        this.startActivity(intent);//提示检查权限
    };

    private void useCamera(){
        Intent intent=new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        this.startActivity(intent);
    }
}
