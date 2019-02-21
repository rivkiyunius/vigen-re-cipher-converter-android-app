package com.example.vignerechiperconverter;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.util.Arrays;

public class MainActivity extends AppCompatActivity {

    private TextView tvHasilPlain, tvHasilKey, tvHasilEncrypt, tvHasilText, tvHasilDecrypt, tvHasilDecryptText;
    private EditText edPlainText, edKey;
    private Button btnEncrypt;
    private final String KAMUS = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
    char[] kamusArray = KAMUS.toCharArray();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        init();
    }

    private void init() {
        tvHasilPlain = findViewById(R.id.tv_hasil_plain);
        tvHasilKey = findViewById(R.id.tv_hasil_key);
        tvHasilEncrypt = findViewById(R.id.tv_hasil_encrypt);
        tvHasilText = findViewById(R.id.tv_hasil_text);
        tvHasilDecrypt = findViewById(R.id.tv_hasil_decrypt);
        tvHasilDecryptText = findViewById(R.id.tv_hasil_decrypt_text);
        edPlainText = findViewById(R.id.ed_plain_text);
        edKey = findViewById(R.id.ed_key_text);
        btnEncrypt = findViewById(R.id.btn_encrypt);

        btnEncrypt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                tvHasilPlain.setText("Index plain text: " + Arrays.toString(getIndex(edPlainText.getText().toString().toUpperCase())));
                tvHasilKey.setText("Index key text: " + Arrays.toString(getIndexKey(edKey.getText().toString().toUpperCase())));
                tvHasilEncrypt.setText("Index hasil encrypt: " + Arrays.toString(encryptFunction(getIndex(edPlainText.getText().toString().toUpperCase()), getIndexKey(edKey.getText().toString().toUpperCase()))));
                convertToString(encryptFunction(getIndex(edPlainText.getText().toString().toUpperCase()), getIndexKey(edKey.getText().toString().toUpperCase())));
                tvHasilDecrypt.setText("Hasil Decrypt: " + Arrays.toString(decryptFunction(encryptFunction(getIndex(edPlainText.getText().toString().toUpperCase()), getIndexKey(edKey.getText().toString().toUpperCase())), getIndexKey(edKey.getText().toString().toUpperCase()))));
                convertToStringDecrypt(decryptFunction(encryptFunction(getIndex(edPlainText.getText().toString().toUpperCase()), getIndexKey(edKey.getText().toString().toUpperCase())), getIndexKey(edKey.getText().toString().toUpperCase())));
            }
        });
    }

    private int[] getIndex(String px) {
        char[] pxArray = px.toCharArray();
        int z = 0;
        int[] temp = new int[px.length()];
        for (int i = 0; i < px.length(); i++) {
            int a = 0;
            while (kamusArray[a] != pxArray[i]) {
                a++;
            }
            temp[z] = a;
            z++;
        }
        return temp;
    }

    public int[] getIndexKey(String key) {
        int z = 0;
        char[] keyArray = key.toCharArray();
        int[] temp = new int[key.length()];
        for (int i = 0; i < key.length(); i++) {
            int a = 0;
            while (kamusArray[a] != keyArray[i]) {
                a++;
            }
            temp[z] = a;
            z++;
        }
        return temp;
    }

    public int[] encryptFunction(int[] px, int[] key) {
        int temp[] = new int[px.length];
        int a = 0;
        int b = key.length;
        int z = 0;
        for (int i = 0; i < px.length; i++) {
            if (a % key.length == 0) {
                a = 0;
                temp[i] = (px[i] + key[a]) % 25;
                a++;
            } else {
                temp[i] = (px[i] + key[a]) % 25;
                a++;
            }
        }
        return temp;
    }

    public void convertToString(int[] index) {
        int temp = 0;
        char[] tempL = new char[index.length];
        for (int i = 0; i < index.length; i++) {
            if (index[i] < 25) {
                temp = index[i];
                tempL[i] = KAMUS.charAt(temp);
                String a = String.valueOf(tempL);
                tvHasilText.setText("Hasil text: " + a);
                tvHasilDecryptText.setText("Hasil Text: " + a);
            } else {
                temp = index[i];
                tempL[i] = KAMUS.charAt(temp % 25);
                String a = String.valueOf(tempL);
                tvHasilText.setText("Hasil Text: " + a);
                tvHasilDecryptText.setText("Hasil Text: " + a);
            }
        }
    }

    public void convertToStringDecrypt(int[] index) {
        int temp = 0;
        char[] tempL = new char[index.length];
        for (int i = 0; i < index.length; i++) {
            if (index[i] < 25) {
                temp = index[i];
                tempL[i] = KAMUS.charAt(temp);
                String a = String.valueOf(tempL);
                tvHasilDecryptText.setText("Hasil Text: " + a);
            } else {
                temp = index[i];
                tempL[i] = KAMUS.charAt(temp % 25);
                String a = String.valueOf(tempL);
                tvHasilDecryptText.setText("Hasil Text: " + a);
            }
        }
    }


    public int[] decryptFunction(int[] index, int[] key) {
        int temp[] = new int[index.length];
        int a = 0;
        for (int i = 0; i < index.length; i++) {
            if (a % key.length == 0) {
                a = 0;
                temp[i] = index[i] - key[a];
                if (temp[i] < 0) {
                    temp[i] += 25;
                }
                a++;
            } else {
                temp[i] = index[i] - key[a];
                if (temp[i] < 0) {
                    temp[i] += 25;
                }
                a++;
            }
        }
        return temp;
    }
}
