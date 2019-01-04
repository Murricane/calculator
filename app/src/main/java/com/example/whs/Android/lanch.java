package com.example.whs.Android;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.murricane.android.R;

public class lanch extends AppCompatActivity {

    TextView tv;
    int[] buttons;      //数字按钮数组
    int result;
    int result0;
    int  result1;

    //按钮对象声明
    Button buttonC;
    Button buttonJia;
    Button buttonJian;
    Button buttonCheng;
    Button buttonChu;
    Button buttonDengyu;

    String str1;    //旧输入的值
    String str2;    //新输入的值

    int flag=0;     //计算标志位，0第一次输入；1加； 2减； 3乘； 4除； 5等于
    Button temp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initButton();
        //清空按钮点击事件
        buttonC.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                str1 = "";
                str2 = "";
                tv.setText("0");
                result = 0;
                result1 = 0;
                result0 = 0;
                flag = 0;
            }
        });

        //监听
        for (int i = 0; i < buttons.length; i++){
            temp = getBtnForId(buttons[i]);
            temp.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (flag != 0){
                        str1 = "";
                    }else {
                        str1 = tv.getText().toString().trim();
                        if (str1.equals("0")){
                            str1 = "";
                        }
                    }

                    str1 = str1 + String.valueOf(((Button)view).getText()); //获取新值
                    tv.setText(str1);
                }
            });
        }

        buttonListener(buttonJia, 1);
        buttonListener(buttonJian, 2);
        buttonListener(buttonCheng, 3);
        buttonListener(buttonChu, 4);

        buttonDengyu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                result1 = Integer.parseInt(str1);

                if (flag == 1){
                    result = result0 + result1;
                }else if (flag == 2){
                    result = result0 - result1;
                }else if (flag == 3){
                    result = result0 * result1;
                }else if (flag == 4){
                    if (result1 == 0){
                        Toast.makeText(lanch.this, "除数不能为0", Toast.LENGTH_SHORT).show();
                    }else {
                        result = result0 / result1;
                    }

                }else if (flag == 0){
                    result = result1;
                }
                String str = (result + "").trim();

                if (result1 == 0 && flag == 4){
                    str = "错误";
                }
                tv.setText(str);
                Toast.makeText(lanch.this, "结果是：" + result, Toast.LENGTH_SHORT).show();
            }
        });
    }

    //初始化控件资源
    public void initButton(){
        tv = (TextView)this.findViewById(R.id.tv);
        str1 = String.valueOf(tv.getText());
        str2 = "";
        buttonC = getBtnForId(R.id.ButtonC);
        buttonJia = getBtnForId(R.id.ButtonJia);
        buttonJian = getBtnForId(R.id.ButtonJian);
        buttonCheng = getBtnForId(R.id.ButtonCheng);
        buttonChu = getBtnForId(R.id.ButtonChu);
        buttonDengyu = getBtnForId(R.id.ButtonDeng);

        buttons = new int[]{
                R.id.Button00,R.id.Button01,R.id.Button02,
                R.id.Button03,
                R.id.Button04,R.id.Button05,R.id.Button06,
                R.id.Button07,R.id.Button08,R.id.Button09
        };
    }
    //根据id获取Button
    public Button getBtnForId(int rID){
        Button btn = (Button)this.findViewById(rID);
        return btn;
    }
    //按钮监听
    public void buttonListener(Button button, final int id){
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String str = tv.getText().toString().trim();
                result0 = Integer.parseInt(str);
                //tv.setText("");
                flag = id;
            }
        });
    }
}