package permission.andgo.com.permission_master;

import android.Manifest;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

/**
 * Created by Administrator on 2016/12/14 0014.
 */

public class PackageActivity extends BaseActivity implements View.OnClickListener{
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setTitle("包装类");
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
        ((Button)findViewById(R.id.btn_toPackageActivity)).setText("关闭");
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.btn_call:
                if(hasPermission(Manifest.permission.CALL_PHONE)){
                    doCallPhone();
                }else {
                    requestPermission(Contants.PERMISSION_CALL_PHONR,Manifest.permission.CALL_PHONE);
                }
                break;
            case R.id.btn_camera:
                if(hasPermission(Manifest.permission.CAMERA)){
                    useCamera();
                }else {
                    requestPermission(Contants.PERMISSION_CAMRRA,Manifest.permission.CAMERA);
                }
                break;
            case R.id.btn_toPackageActivity:
                this.finish();
                break;
        }
    }
}
