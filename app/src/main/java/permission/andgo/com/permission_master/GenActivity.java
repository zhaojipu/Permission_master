package permission.andgo.com.permission_master;

import android.Manifest;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.widget.Toast;

import kr.co.namee.permissiongen.PermissionFail;
import kr.co.namee.permissiongen.PermissionGen;
import kr.co.namee.permissiongen.PermissionSuccess;

/**
 * 依赖一韩国哥们写的库写的测试类
 */
public class GenActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gen);

        //使用步骤1、把需要的权限罗列下
        PermissionGen.with(this)
        .addRequestCode(100)//请求码，根据不同的请求码来判断显示哪些权限
        .permissions(Manifest.permission.CAMERA,
                Manifest.permission.CALL_PHONE)
        .request();
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        //2、回调第三方的onRequestPermissionsResult方法
        PermissionGen.onRequestPermissionsResult(this,requestCode,permissions,grantResults);
    }

    /**
     * 3、根据用户的选择  若允许开启权限后执行的方法
     */
    @PermissionSuccess(requestCode = 100)
    public void doCallback(){
        Toast.makeText(this,"成功",Toast.LENGTH_SHORT).show();
    }

    /**
     * 3、根据用户的选择  若拒绝后执行的方法
     */
    @PermissionFail(requestCode = 100)
    public void request(){

        Toast.makeText(this,"失败",Toast.LENGTH_SHORT).show();
    }
}
