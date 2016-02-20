package com.soso.dinner;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.view.KeyEvent;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.Toast;


public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_camera) {
           login();
        } else if (id == R.id.nav_gallery) {
//            login();
        } else if (id == R.id.nav_slideshow) {

        } else if (id == R.id.nav_manage) {

        } else if (id == R.id.nav_share) {
            startActivity(new Intent(MainActivity.this, system_config.class));
        } else if (id == R.id.nav_send) {
            startActivity(new Intent(MainActivity.this, system_config.class));
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    public void login(){
        String name = "soso";
        String password = "56858520";
        AlertDialog.Builder  builder = new AlertDialog.Builder(this);
        builder.setTitle("身份验证");

        final View view = getLayoutInflater().inflate(R.layout.check,null);
        builder.setView(view);

        builder.setPositiveButton("提交", new DialogInterface.OnClickListener(){
            @Override
            public void onClick(DialogInterface dialogInterface,int which){
                EditText editText_inputname = (EditText) view.findViewById(R.id.username);
                EditText editText_inputpassword = (EditText) view.findViewById(R.id.password);
                String inputname = editText_inputname.getText().toString();
                String inputpassword = editText_inputpassword.getText().toString();
//                Toast.makeText(MainActivity.this,inputname+ inputpassword,Toast.LENGTH_SHORT).show();
                    if(inputname.equals("soso")&&inputpassword.equals("56858520")){
                        dialogInterface.dismiss();
                        startActivity(new Intent(MainActivity.this, system_config.class));
                    }else{
                        Toast.makeText(MainActivity.this,"用户密码错误，请重新输入！",Toast.LENGTH_SHORT).show();
                    }
//
                }
            });

        builder.setNegativeButton("取消",new DialogInterface.OnClickListener(){
            public void onClick(DialogInterface dialogInterface,int which){
                Toast.makeText(MainActivity.this,"你放弃了输入！",Toast.LENGTH_SHORT).show();
                dialogInterface.dismiss();
            }
        });
//        builder.setCancelable(false);//点击其他地方不返回，点返回键也不返回
        builder.show();


    }
    //防止不小心按了退出键
    private long exitTime = 0;
    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if(keyCode == KeyEvent.KEYCODE_BACK && event.getAction() == KeyEvent.ACTION_DOWN){
            if((System.currentTimeMillis()-exitTime) > 2000){
                Toast.makeText(getApplicationContext(), "再按一次退出程序", Toast.LENGTH_SHORT).show();
                exitTime = System.currentTimeMillis();
            } else {
                finish();
                System.exit(0);
            }
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }
}
