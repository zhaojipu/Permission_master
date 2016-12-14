package permission.andgo.com.permission_master;

import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.widget.Toast;

/**
 * Created by Administrator on 2016/12/14 0014.
 */

abstract public class BaseActivity extends AppCompatActivity {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    /**
     * 为子类提供权限检查方法
     * @param permissions
     * @return
     */
    protected boolean hasPermission(String ... permissions){
        for (String permission :permissions){
          return  !(ContextCompat.checkSelfPermission(this,permission)!= PackageManager.PERMISSION_GRANTED);
        }
        return true;

    }

    protected void requestPermission(int code,String ... permissions){
        ActivityCompat.requestPermissions(this,permissions,code);
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {

        switch (requestCode){
            case Contants.PERMISSION_CALL_PHONR:
                if(grantResults[0]==PackageManager.PERMISSION_GRANTED){
                    doCallPhone();
                }else {
                    //...
                    Toast.makeText(this,"关闭了打电话",Toast.LENGTH_SHORT).show();
                }
                break;
            case Contants.PERMISSION_CAMRRA:
                if(grantResults[0]==PackageManager.PERMISSION_GRANTED){
                    useCamera();
                }else {
                    Toast.makeText(this,"关闭了相机",Toast.LENGTH_SHORT).show();
                }
                break;
        }
    }

    protected void doCallPhone(){
        this.startActivity(new Intent(Intent.ACTION_CALL, Uri.parse("tel:"+"110")));
    };
    protected  void useCamera(){
        this.startActivity(new Intent(MediaStore.ACTION_IMAGE_CAPTURE));
    };
}
