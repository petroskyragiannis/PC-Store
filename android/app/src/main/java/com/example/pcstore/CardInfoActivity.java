package com.example.pcstore;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import com.example.pcstore.model.CardInfo;
import com.example.pcstore.model.Client;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

public class CardInfoActivity extends AppCompatActivity implements CatalogView {

    public static final String CARD = "card";

    Client client;
    CardInfo cardInfo;
    TextView txtCardInfo;
    TextView txtHolderName;
    EditText edtHolderName;
    TextView txtCardNumber;
    EditText edtCardNumber;
    TextView txtExpirationDate;
    EditText edtExpirationDate;
    TextView txtCsv;
    EditText edtCsv;
    Button btnCardInfoOk;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_card_info);
        txtCardInfo = findViewById(R.id.txt_card_info);
        txtHolderName = findViewById(R.id.txt_holder_name);
        edtHolderName = findViewById(R.id.edt_holder_name);
        txtCardNumber = findViewById(R.id.txt_card_number);
        edtCardNumber = findViewById(R.id.edt_card_number);
        txtExpirationDate = findViewById(R.id.txt_expiration_date);
        edtExpirationDate = findViewById(R.id.edt_expiration_date);
        txtCsv = findViewById(R.id.txt_csv);
        edtCsv = findViewById(R.id.edt_csv);
        btnCardInfoOk = findViewById(R.id.btn_card_info_ok);

        Intent intent = getIntent();
        client = (Client) intent.getSerializableExtra(MainActivity.SIGNED_IN_CLIENT);
        if (client.getCard() == null) cardInfo = new CardInfo();
        else {
            cardInfo = client.getCard();
            edtHolderName.setText(cardInfo.getCardHolderName());
            edtCardNumber.setText(cardInfo.getCardNumber());
            Calendar date = cardInfo.getExpirationDate();
            int day = date.get(Calendar.DAY_OF_MONTH);
            int month = date.get(Calendar.MONTH) + 1;
            int year = date.get(Calendar.YEAR);
            edtExpirationDate.setText(day + "/" + month + "/" + year);
            edtCsv.setText(cardInfo.getCsv());
        }

        edtHolderName.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                String name = edtHolderName.getText().toString();
                if (name!= null)
                    cardInfo.setCardHolderName(name);
            }
        });

        edtCardNumber.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                String number = edtCardNumber.getText().toString();
                if (number != null) {
                    //TODO SET 16
                    if (number.length() == 2)
                        cardInfo.setCardNumber(number);
                    else showStatus("Card number must be 16 digits.");
                }
            }
        });

        edtExpirationDate.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                String input = edtExpirationDate.getText().toString();
                SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
                try {
                    Date date = sdf.parse(input);
                    Calendar calendar = sdf.getCalendar();
                    if (!isExpired(calendar)) cardInfo.setExpirationDate(calendar);
                    else showStatus("Date is expired.");
                } catch (ParseException e) {
                    e.printStackTrace();
                    showStatus("Wrong date Format.");
                }
            }
        });

        edtCsv.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                String csv = edtCsv.getText().toString();
                if (csv != null) {
                    if (csv.length() == 3)
                        cardInfo.setCsv(csv);
                    else showStatus("CSV must be 3 digits.");
                }
            }
        });

        btnCardInfoOk.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                if (cardInfo.getCardNumber() == null || cardInfo.getCardHolderName() == null
                || cardInfo.getExpirationDate() == null || cardInfo.getCsv() == null)
                    showStatus("Missing required information.");
                else {
                    Intent intent = new Intent();
                    intent.putExtra(CARD, cardInfo);
                    setResult(RESULT_OK, intent);
                    finish();
                }
            }
        });

    }

    @Override
    public void showStatus(String msg) {
        Toast.makeText(this, msg, Toast.LENGTH_LONG).show();
    }

    private boolean isExpired(Calendar calendar) {
        Calendar curr = new GregorianCalendar();
        if (calendar.get(Calendar.YEAR) < curr.get(Calendar.YEAR))
            return true;
        else if (calendar.get(Calendar.YEAR) == curr.get(Calendar.YEAR))
            if (calendar.get(Calendar.MONTH) < curr.get(Calendar.MONTH))
                return true;
            else if (calendar.get(Calendar.MONTH) == curr.get(Calendar.MONTH))
                if (calendar.get(Calendar.DAY_OF_MONTH) <= curr.get(Calendar.DAY_OF_MONTH))
                    return true;
        return false;
    }

}