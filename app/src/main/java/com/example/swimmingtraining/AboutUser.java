package com.example.swimmingtraining;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class AboutUser extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about_trainer);
        String abfamilia;
        String abname;
        String abotchestvo;
        String abemail;
        String abdr;
        String abstage;
        String ababout;

        //Получение данных
        abfamilia = getIntent().getExtras().getString("pfamilia");
        abname = getIntent().getExtras().getString("pname");
        abotchestvo = getIntent().getExtras().getString("potchestvo");
        abemail = getIntent().getExtras().getString("pemail");
        abdr = getIntent().getExtras().getString("pdr");
        abstage = getIntent().getExtras().getString("pstage");
        ababout = getIntent().getExtras().getString("pabout");

        //инициализация textview
        TextView infofamilia = (TextView)findViewById(R.id.aboutfamilia);
        TextView infoname = (TextView)findViewById(R.id.aboutname);
        TextView infootchestvo = (TextView)findViewById(R.id.aboutotchestvo);
        TextView infoemail = (TextView)findViewById(R.id.aboutemail);
        TextView infodr = (TextView)findViewById(R.id.aboutdr);
        TextView infostage = (TextView)findViewById(R.id.aboutstage);
        TextView infoabout = (TextView)findViewById(R.id.aboutobout);

        //запись в textview
        infofamilia.setText(abfamilia);
        infoname.setText(abname);
        infootchestvo.setText(abotchestvo);
        infoemail.setText(abemail);
        infodr.setText(abdr);
        infostage.setText("Ранг " + abstage);
        infoabout.setText(ababout);
    }
}
