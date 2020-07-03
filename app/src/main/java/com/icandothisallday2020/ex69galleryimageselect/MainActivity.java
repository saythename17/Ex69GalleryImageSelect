package com.icandothisallday2020.ex69galleryimageselect;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import com.bumptech.glide.Glide;

public class MainActivity extends AppCompatActivity {
    //1.glide(dump tech),design 라이브러리 추가
    //2.XML
    ImageView iv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        iv=findViewById(R.id.iv);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        switch (requestCode){
            case 10:
                //이미지 선택하면
                if(requestCode != RESULT_CANCELED) {
                    Uri uri=data.getData();//돌아온 인텐트(data)에게 이미지 get
                    if(uri==null) Toast.makeText(this, "null image", Toast.LENGTH_SHORT).show();
                    else Toast.makeText(this,
                            uri.toString()/*경로를 문자열로 보여줌:절대경로(제품번호) X, 리소스 관리 번호(장부 번호)*/, Toast.LENGTH_SHORT).show();

                    Glide.with(this).load(uri).into(iv);
                }
        }
    }

    public void clickFAB(View view) {
        //Gallery app 실행하는 인텐트 객체
        Intent intent = new Intent(Intent.ACTION_PICK);
        intent.setType("image/*");//image file- 모든 확장자 받음
        //("image/png") ("audio") ("video")...
        //선택된 사진 결과를 가져오는 메소드
        startActivityForResult(intent,10);//식별번호:10
        //startActivityForResult() 로 실행한 인텐트가 돌아오면 자동으로 실행되는 메소드

    }
}
