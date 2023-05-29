package com.ootd.crowdpp;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.ootd.crowdpp.Retrofits.Result;
import com.ootd.crowdpp.Retrofits.RetrofitClient;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.TextView;

import java.util.regex.Pattern;

public class SignupActivity extends AppCompatActivity {
    Call<Result> call;
    EditText signupId, signupPw, signupCheckPw, signupName, signupEmail;
    TextView textviewFineId, textviewFinePw, textviewCheckPw;
    Button signupBtnSignup, signupBtnFineId;
    RadioButton radioAccept, radioNotAccept;
    boolean idFine = false, pressIdBtn = false, pwFine = false, checkPwFine = false;
    public static boolean isFineId(String id){
        boolean inEng = false, inNum = false, inSpecialNumber = false;
        if (id.length() < 6){
            return false;
        }
        for (int i = 0; i < id.length(); i++){
            if (('a' <= id.charAt(i) & id.charAt(i) <= 'z') | ('A' <= id.charAt(i) & id.charAt(i) <= 'Z')){
                inEng = true;
            }
            else if ('0' <= id.charAt(i) & id.charAt(i) <= '9'){
                inNum = true;
            }
            else{
                inSpecialNumber = true;
            }
        }
        if (inEng & inNum & !inSpecialNumber){
            return true;
        }
        return false;
    }

    public static boolean isFinePw(String pw){
        boolean inEng = false, inNum = false, inSpecialLetter = false, inElseLetter = false;
        if (pw.length() < 6){
            return false;
        }
        final String PASSWORD_REGEX = "^(?=.*[A-Za-z])(?=.*\\d)(?=.*[@$!%*#?&])[A-Za-z\\d@$!%*#?&]{6,}$";
        final Pattern PASSWORD_PATTERN = Pattern.compile(PASSWORD_REGEX);

        if (PASSWORD_PATTERN.matcher(pw).matches()) {
            return true;
        }
        else {
            return false;
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);
//        editText
        signupId = (EditText) findViewById(R.id.signupId);
        signupPw = (EditText) findViewById(R.id.signupPw);
        signupCheckPw = (EditText) findViewById(R.id.signupCheckPw);
        signupName = (EditText) findViewById(R.id.signupName);
        signupEmail = (EditText) findViewById(R.id.signupEmail);
//        textview
        textviewFineId = (TextView) findViewById(R.id.textviewFineId);
        textviewFinePw = (TextView) findViewById(R.id.textviewFinePw);
        textviewCheckPw = (TextView) findViewById(R.id.textviewCheckPw);
//        button
        signupBtnFineId = (Button) findViewById(R.id.signupBtnFineId);
        signupBtnSignup = (Button) findViewById(R.id.signupBtnSignup);
//        radioButton
        radioAccept = (RadioButton) findViewById(R.id.radioAccept);
        radioNotAccept = (RadioButton) findViewById(R.id.radioNotAccept);

//        check id is satisfied
        signupBtnFineId.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                String strJmId = signupId.getText().toString();
                boolean isDuplicatedId = false;
                pressIdBtn = true;
                idFine = false;
                //
                //중복 확인 필요
                //
                if (isDuplicatedId) {
                    textviewFineId.setText("중복된 아이디입니다");
                    textviewFineId.setTextColor(0xFFFF0000);
                }
                else if (!isFineId(strJmId)){
                    textviewFineId.setText("6자 이상의 영문, 숫자를 입력해주세요");
                    textviewFineId.setTextColor(0xFFFF0000);
                }
                else{
                    textviewFineId.setText("사용 가능한 아이디입니다");
                    textviewFineId.setTextColor(0xFF0000FF);
                    idFine = true;
                }
            }
        });

        signupId.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
            }
            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                idFine = false;
                textviewFineId.setText("ID 중복 확인을 해주세요");
                textviewFineId.setTextColor(0xFFFF0000);
            }
            @Override
            public void afterTextChanged(Editable editable) {
            }
        });

//        check pw is satisfied
        signupPw.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
            }
            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                if (isFinePw(signupPw.getText().toString())){
                    textviewFinePw.setText("사용 가능한 비밀번호입니다");
                    textviewFinePw.setTextColor(0xFF0000FF);
                    pwFine = true;
                }
                else{
                    textviewFinePw.setText("6자 이상의 영문, 숫자, 특수문자를 입력해주세요");
                    textviewFinePw.setTextColor(0xFFFF0000);
                    pwFine = false;
                }
                if (textviewCheckPw.getText()!="") {
                    if (signupPw.getText().toString().equals(signupCheckPw.getText().toString())){
                        textviewCheckPw.setText("비밀번호가 일치합니다");
                        textviewCheckPw.setTextColor(0xFF0000FF);
                        checkPwFine = true;
                    }
                    else{
                        textviewCheckPw.setText("비밀번호가 일치하지 않습니다");
                        textviewCheckPw.setTextColor(0xFFFF0000);
                        checkPwFine = false;
                    }
                }
            }
            @Override
            public void afterTextChanged(Editable editable) {
            }
        });

//        check checkPw is satisfied
        signupCheckPw.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
            }
            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                if (signupPw.getText().toString().equals(signupCheckPw.getText().toString())){
                    textviewCheckPw.setText("비밀번호가 일치합니다");
                    textviewCheckPw.setTextColor(0xFF0000FF);
                    checkPwFine = true;
                }
                else{
                    textviewCheckPw.setText("비밀번호가 일치하지 않습니다");
                    textviewCheckPw.setTextColor(0xFFFF0000);
                    checkPwFine = false;
                }
            }
            @Override
            public void afterTextChanged(Editable editable) {
            }
        });


//        join membership
        signupBtnSignup.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                String strJmId = signupId.getText().toString();
                String strJmPw = signupPw.getText().toString();
                String strName = signupName.getText().toString();
                String strEmail = signupEmail.getText().toString();

                if (!pressIdBtn){
                    AlertDialog.Builder builder = new AlertDialog.Builder(SignupActivity.this);
                    builder.setMessage("아이디 중복 확인을 해주세요");
                    builder.setPositiveButton("확인", new DialogInterface.OnClickListener(){
                        public void onClick(DialogInterface dialog, int whichButton){
                            dialog.cancel();
                        }
                    });
                    AlertDialog dialog = builder.create();
                    dialog.show();
                }
                else if (!idFine){
                    AlertDialog.Builder builder = new AlertDialog.Builder(SignupActivity.this);
                    builder.setMessage("올바른 아이디를 입력해주세요");
                    builder.setPositiveButton("확인", new DialogInterface.OnClickListener(){
                        public void onClick(DialogInterface dialog, int whichButton){
                            dialog.cancel();
                        }
                    });
                    AlertDialog dialog = builder.create();
                    dialog.show();
                }
                else if (!pwFine){
                    AlertDialog.Builder builder = new AlertDialog.Builder(SignupActivity.this);
                    builder.setMessage("올바른 비밀번호를 입력해주세요");
                    builder.setPositiveButton("확인", new DialogInterface.OnClickListener(){
                        public void onClick(DialogInterface dialog, int whichButton){
                            dialog.cancel();
                        }
                    });
                    AlertDialog dialog = builder.create();
                    dialog.show();
                }
                else if (!checkPwFine){
                    AlertDialog.Builder builder = new AlertDialog.Builder(SignupActivity.this);
                    builder.setMessage("비밀번호가 일치하지 않습니다");
                    builder.setPositiveButton("확인", new DialogInterface.OnClickListener(){
                        public void onClick(DialogInterface dialog, int whichButton){
                            dialog.cancel();
                        }
                    });
                    AlertDialog dialog = builder.create();
                    dialog.show();
                }
                else if (strName.length() == 0){
                    AlertDialog.Builder builder = new AlertDialog.Builder(SignupActivity.this);
                    builder.setMessage("이름을 입력해주세요");
                    builder.setPositiveButton("확인", new DialogInterface.OnClickListener(){
                        public void onClick(DialogInterface dialog, int whichButton){
                            dialog.cancel();
                        }
                    });
                    AlertDialog dialog = builder.create();
                    dialog.show();
                }
                else if (strEmail.length()==0){
                    AlertDialog.Builder builder = new AlertDialog.Builder(SignupActivity.this);
                    builder.setMessage("주소를 입력해주세요");
                    builder.setPositiveButton("확인", new DialogInterface.OnClickListener(){
                        public void onClick(DialogInterface dialog, int whichButton){
                            dialog.cancel();
                        }
                    });
                    AlertDialog dialog = builder.create();
                    dialog.show();
                }
                else if (!strEmail.contains("@")){
                    AlertDialog.Builder builder = new AlertDialog.Builder(SignupActivity.this);
                    builder.setMessage("올바른 이메일 주소를 입력해주세요");
                    builder.setPositiveButton("확인", new DialogInterface.OnClickListener(){
                        public void onClick(DialogInterface dialog, int whichButton){
                            dialog.cancel();
                        }
                    });
                    AlertDialog dialog = builder.create();
                    dialog.show();
                }
                else if (!radioAccept.isChecked()){
                    AlertDialog.Builder builder = new AlertDialog.Builder(SignupActivity.this);
                    builder.setMessage("개인정보 사용 약관에 동의해주세요");
                    builder.setPositiveButton("확인", new DialogInterface.OnClickListener(){
                        public void onClick(DialogInterface dialog, int whichButton){
                            dialog.cancel();
                        }
                    });
                    AlertDialog dialog = builder.create();
                    dialog.show();
                }
                else{
                    //
                    // 모든 조건이 갖춰짐. 회원가입 진행시키면 됨.
                    //
                  
                    String inputId = signupId.getText().toString();
                    String inputPw = signupPw.getText().toString();
                    String inputName = signupName.getText().toString();
                    String inputEmail = signupEmail.getText().toString();

                    call = RetrofitClient.getApiService().signup(inputId, inputPw, inputName, inputEmail);
                    call.enqueue(new Callback<Result>(){
                        @Override
                        public void onResponse(Call<Result> call, Response<Result> response) {
                            if (response.code() == 200) {
                                Result result = response.body();
                                String msg = result.getMsg();
                                if (msg.equals("success")) {
                                    Toast.makeText(SignupActivity.this, "회원가입 완료", Toast.LENGTH_SHORT).show();
                                    finish();
                                } else if (msg.equals("duplicated")) {
                                    Toast.makeText(SignupActivity.this, "ID가 중복되었습니다", Toast.LENGTH_SHORT).show();
                                } else {
                                    Toast.makeText(SignupActivity.this, msg ,Toast.LENGTH_SHORT).show();
                                }
                                System.out.println(msg);
                            } else {
                            }
                        }

                        @Override
                        public void onFailure(Call<Result> call, Throwable t) {
                            Log.e("request fail", t.getMessage());
                        }
                    });
                  
                    AlertDialog.Builder builder = new AlertDialog.Builder(SignupActivity.this);
                    builder.setMessage("회원가입이 완료되었습니다!");
                    builder.setPositiveButton("로그인하러가기", new DialogInterface.OnClickListener(){
                        public void onClick(DialogInterface dialog, int whichButton){
                            finish();
                        }
                    });
                    AlertDialog dialog = builder.create();
                    dialog.show();
                }
            }
        });
    }

}