package com.github.zqhcxy.intentdemo;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

/**
 * 隱式intent实例
 */
public class ImplicitIntentActivity extends AppCompatActivity {


    //    public static final String WEB = "web";
//    public static final String MAP = "map";
//    public static final String MAPROUTE = "maproute";
//    public static final String TEL = "tel";
//    public static final String SMS = "sms";
//    public static final String SMSSYSTEM = "smssystem";
//    public static final String MMS = "mms";
//    public static final String EMAIL = "Email";
//    public static final String ENCLOSURE = "enclosure";
//    public static final String MEDIA = "media";
//    public static final String CONTACTS = "contacts";
//    public static final String RECORD = "record";
//    public static final String PHOTO = "photo";
    public static final String WEB = "显示网页";
    public static final String MAP = "显示地图";
    public static final String MAPROUTE = "道路规划";
    public static final String TEL = "拨打电话（调用拨号程序）";
    public static final String TELSYSTEM = "拨打电话";
    public static final String SMS = "发送短信（调用发送短信程序）";
    public static final String SMSSYSTEM = "发送短信";
    public static final String MMS = "发送彩信";
    public static final String EMAIL = "发送Email";
    public static final String ENCLOSURE = "添加附件";
    public static final String MEDIA = "播放多媒体";
    public static final String CONTACTS = "打开联系人";
    public static final String RECORD = "录音";
    public static final String PHOTO = "相机";

    private RecyclerView recyclerView;
    private MyRecyclerViewAdapter myRecyclerViewAdapter;
    private List<String> dataList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_implicit_intent);

        findView();
        initData();
    }

    private void findView() {
        recyclerView = (RecyclerView) findViewById(R.id.myrecy);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setHasFixedSize(true);
    }

    private void initData() {
        dataList = new ArrayList<>();
        dataList.add(WEB);
        dataList.add(MAP);
        dataList.add(MAPROUTE);
        dataList.add(TEL);
        dataList.add(TELSYSTEM);
        dataList.add(SMS);
        dataList.add(SMSSYSTEM);
        dataList.add(MMS);
        dataList.add(EMAIL);
        dataList.add(ENCLOSURE);
        dataList.add(MEDIA);
        dataList.add(CONTACTS);
        dataList.add(RECORD);
        dataList.add(PHOTO);
        myRecyclerViewAdapter = new MyRecyclerViewAdapter(dataList);
        recyclerView.setAdapter(myRecyclerViewAdapter);

        myRecyclerViewAdapter.setOnClickListener(new MyRecyclerViewAdapter.OnClickListener() {
            @Override
            public void onClick(View v, String note) {
                switch (note) {
                    case WEB:
                        Uri uri = Uri.parse("http://www.baidu.com");
                        Intent it = new Intent(Intent.ACTION_VIEW, uri);
                        startActivity(it);
                        break;
                    case MAP:
                        Uri uri_map = Uri.parse("geo:38.899533,-77.036476");
                        Intent it1 = new Intent(Intent.ACTION_VIEW, uri_map);
                        startActivity(it1);
                        break;
                    case MAPROUTE:
                        Uri uri_route = Uri.parse("http://maps.google.com/maps?f=d&saddr=startLat%20startLng&daddr=endLat%20endLng&hl=en");
                        Intent it2 = new Intent(Intent.ACTION_VIEW, uri_route);
                        startActivity(it2);
                        break;
                    case TEL:
                        Uri uri4 = Uri.parse("tel:10086");
                        Intent it4 = new Intent(Intent.ACTION_DIAL, uri4);
                        startActivity(it4);

                        break;
                    case TELSYSTEM:
                        // <uses-permission android:name="android.permission.CALL_PHONE" />
                        Uri uri5 = Uri.parse("tel:10086");
                        Intent it5 = new Intent(Intent.ACTION_CALL, uri5);
                        if (ActivityCompat.checkSelfPermission(ImplicitIntentActivity.this, Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED) {
                            // TODO: Consider calling
                            //    ActivityCompat#requestPermissions
                            // here to request the missing permissions, and then overriding
                            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
                            //                                          int[] grantResults)
                            // to handle the case where the user grants the permission. See the documentation
                            // for ActivityCompat#requestPermissions for more details.
                            return;
                        }
                        startActivity(it5);
                        break;
                    case SMS:
                        Intent it6 = new Intent(Intent.ACTION_VIEW);
                        it6.putExtra("sms_body", "The SMS text");
                        it6.setType("vnd.android-dir/mms-sms");
                        startActivity(it6);
                        break;
                    case SMSSYSTEM:
                        Uri uri7 = Uri.parse("smsto:10086");
                        Intent it7 = new Intent(Intent.ACTION_SENDTO, uri7);
                        it7.putExtra("sms_body", "The SMS text");
                        startActivity(it7);
                        break;

                    case MMS:
                        Uri uri8 = Uri.parse("content://media/external/images/media/23");
                        Intent it8 = new Intent(Intent.ACTION_SEND);
                        it8.putExtra("sms_body", "some text");
                        it8.putExtra(Intent.EXTRA_STREAM, uri8);
                        it8.setType("image/png");
                        startActivity(it8);
                        break;
                    case EMAIL:
                        //方式一
                        Uri uri9 = Uri.parse("mailto:xxx@abc.com");
                        Intent it9 = new Intent(Intent.ACTION_SENDTO, uri9);
                        startActivity(it9);
                        //方式二
//                        Intent it10 = new Intent(Intent.ACTION_SEND);
//                        it10.putExtra(Intent.EXTRA_EMAIL, "me@abc.com");
//                        it10.putExtra(Intent.EXTRA_TEXT, "The email body text");
//                        it10.setType("text/plain");
//                        startActivity(Intent.createChooser(it10, "Choose Email Client"));
                        //方式三
//                        Intent it11=new Intent(Intent.ACTION_SEND);
//                        String[] tos={"me@abc.com"};
//                        String[] ccs={"you@abc.com"};
//                        it11.putExtra(Intent.EXTRA_EMAIL, tos);
//                        it11.putExtra(Intent.EXTRA_CC, ccs);
//                        it11.putExtra(Intent.EXTRA_TEXT, "The email body text");
//                        it11.putExtra(Intent.EXTRA_SUBJECT, "The email subject text");
//                        it11.setType("message/rfc822");
//                        startActivity(Intent.createChooser(it11, "Choose Email Client"));
                        break;
                    case ENCLOSURE:
                        Intent it12 = new Intent(Intent.ACTION_SEND);
                        it12.putExtra(Intent.EXTRA_SUBJECT, "The email subject text");
                        it12.putExtra(Intent.EXTRA_STREAM, "file:///sdcard/mysong.mp3");
                        it12.setType("audio/mp3");
                        startActivity(Intent.createChooser(it12, "Choose Email Client"));
                        break;
                    case MEDIA:
                        Intent it13 = new Intent(Intent.ACTION_VIEW);
                        Uri uri13 = Uri.parse("file:///sdcard/song.mp3");
                        it13.setDataAndType(uri13, "audio/mp3");
                        startActivity(it13);

//                        Uri uri14 = Uri.withAppendedPath(MediaStore.Audio.Media.INTERNAL_CONTENT_URI, "1");
//                        Intent it14 = new Intent(Intent.ACTION_VIEW, uri14);
//                        startActivity(it14);
                        break;
                    case CONTACTS:
                        Intent i = new Intent();
                        i.setAction(Intent.ACTION_GET_CONTENT);
                        i.setType("vnd.android.cursor.item/phone");
                        startActivityForResult(i, 110);
                        break;
                    case RECORD:
                        try {
                            Intent mi = new Intent(MediaStore.Audio.Media.RECORD_SOUND_ACTION);
                            startActivity(mi);
                        }catch (Exception e){
                            Toast.makeText(ImplicitIntentActivity.this, "沒有录音设备", Toast.LENGTH_SHORT).show();
                        }
                        break;
                    case PHOTO:
                        Intent openCameraIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                        //定义的拍照目录
//                        openCameraIntent.putExtra(MediaStore.EXTRA_OUTPUT, Uri.fromFile(new File(takePicPath)));
                        startActivityForResult(openCameraIntent, 111);
                        break;
                }
            }
        });
    }

}
