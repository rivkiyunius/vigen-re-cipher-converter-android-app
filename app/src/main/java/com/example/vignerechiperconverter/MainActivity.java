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
                tvHasilPlain.setText(getString(R.string.index_plain_text) + Arrays.toString(getIndex()));
                tvHasilKey.setText(getString(R.string.index_key_text) + Arrays.toString(getIndexKey()));
                tvHasilEncrypt.setText(getString(R.string.index_hasil_encrypt) + Arrays.toString(encryptFunction()));
                convertToString();
                tvHasilDecrypt.setText("Hasil Decrypt: " + Arrays.toString(decryptFunction()));
                convertToStringDecrypt();
            }
        });
    }

    private int[] getIndex() {
        String temp1 = edPlainText.getText().toString().toUpperCase();
        char[] pxArray = temp1.toCharArray();
        int z = 0;
        int[] temp = new int[temp1.length()];
        for (int i = 0; i < temp1.length(); i++) {
            int a = 0;
            while (kamusArray[a] != pxArray[i]) {
                a++;
            }
            temp[z] = a;
            z++;
        }
        return temp;
    }

    public int[] getIndexKey() {
        String key = edKey.getText().toString().toUpperCase();
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

    public int[] encryptFunction() {
        int temp[] = new int[getIndex().length];
        int a = 0;
        int b = getIndexKey().length;
        int z = getIndex().length;
        for (int i = 0; i < getIndex().length; i++) {
            if (a % b == 0) {
                a = 0;
                temp[i] = (getIndex()[i] + getIndexKey()[a]) % 25;
                a++;
            } else {
                temp[i] = (getIndex()[i] + getIndexKey()[a]) % 25;
                a++;
            }
        }
        return temp;
    }

    public void convertToString() {
        int temp = 0;
        char[] tempL = new char[encryptFunction().length];
        for (int i = 0; i < encryptFunction().length; i++) {
            if (encryptFunction()[i] < 25) {
                temp = encryptFunction()[i];
                tempL[i] = KAMUS.charAt(temp);
                String a = String.valueOf(tempL);
                tvHasilText.setText("Hasil text: " + a);
            } else {
                temp = encryptFunction()[i];
                tempL[i] = KAMUS.charAt(temp % 25);
                String a = String.valueOf(tempL);
                tvHasilText.setText("Hasil Text: " + a);
            }
        }
    }

    public void convertToStringDecrypt() {
        int temp = 0;
        char[] tempL = new char[decryptFunction().length];
        for (int i = 0; i < decryptFunction().length; i++) {
            if (decryptFunction()[i] < 25) {
                temp = decryptFunction()[i];
                tempL[i] = KAMUS.charAt(temp);
                String a = String.valueOf(tempL);
                tvHasilDecryptText.setText("Hasil Text: " + a);
            } else {
                temp = decryptFunction()[i];
                tempL[i] = KAMUS.charAt(temp % 25);
                String a = String.valueOf(tempL);
                tvHasilDecryptText.setText("Hasil Text: " + a);
            }
        }
    }


    public int[] decryptFunction() {
        int temp[] = new int[encryptFunction().length];
        int a = 0;
        for (int i = 0; i < encryptFunction().length; i++) {
            if (a % getIndexKey().length == 0) {
                a = 0;
                temp[i] = encryptFunction()[i] - getIndexKey()[a];
                if (temp[i] < 0) {
                    temp[i] += 25;
                }
                a++;
            } else {
                temp[i] = encryptFunction()[i] - getIndexKey()[a];
                if (temp[i] < 0) {
                    temp[i] += 25;
                }
                a++;
            }
        }
        return temp;
    }
}
