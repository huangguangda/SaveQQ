package cn.edu.gdmec.android.saveqq;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.io.FileNotFoundException;
import java.util.Map;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{
    private EditText etNumber;
    private EditText etPassword;
    private Button btnLogin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate ( savedInstanceState );
        setContentView ( R.layout.activity_main );
        //初始化view
        initView();
        //如果用户保存了信息，进行数据的回显
        Map<String,String> userInfo = FileSaveQQ.getUserInfo ( this );
        if (userInfo!=null){
            etNumber.setText ( userInfo.get ( "number" ) );
            etPassword.setText ( userInfo.get ( "password" ) );
        }
    }

    private void initView() {
        //完成控件的初始化
        etNumber = (EditText) findViewById ( R.id.et_number );
        etPassword = (EditText) findViewById ( R.id.et_password );
        btnLogin = (Button) findViewById ( R.id.btn_login );

        //给按钮设置点击事件
        btnLogin.setOnClickListener ( this );
    }
    @Override
    public void onClick(View v){
        //点击按钮 获取账号和密码
        String number = etNumber.getText ().toString ().trim ();
        String password = etPassword.getText ().toString ();
        //检查用户名密码是否为空
        if (TextUtils.isEmpty ( number )){
            Toast.makeText ( this,"请输入QQ账号",Toast.LENGTH_LONG ).show ();
            return;
        }
        if (TextUtils.isEmpty ( password )){
            Toast.makeText ( this,"请输入QQ密码",Toast.LENGTH_LONG ).show ();
            return;
        }
        Toast.makeText ( this,"登录成功",Toast.LENGTH_LONG ).show ();

        //保存用户的信息。
        boolean isSaveSucess =false;
        try {
            isSaveSucess=FileSaveQQ.saveUserInfo ( this,number,password );
        } catch (FileNotFoundException e) {
            e.printStackTrace ();
        }
        if (isSaveSucess){
            Toast.makeText ( this,"保存成功",Toast.LENGTH_LONG ).show ();
        }else {
            Toast.makeText ( this,"保存失败",Toast.LENGTH_LONG ).show ();
        }


    }
}
