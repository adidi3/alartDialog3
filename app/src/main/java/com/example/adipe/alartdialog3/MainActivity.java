package com.example.adipe.alartdialog3;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements AdapterView.OnItemClickListener {
    AlertDialog.Builder ad;
    EditText etfi;
    EditText etd;
    LinearLayout dialog;
    int type = 500;
    double ft;
    double dm;
    TextView tx, tn, td, ts;
    ListView lv;
    String[] cs = new String[20];
    Switch sw;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        tx = (TextView) findViewById(R.id.x);
        tn = (TextView) findViewById(R.id.n);
        td = (TextView) findViewById(R.id.d);
        ts = (TextView) findViewById(R.id.Sn);
        lv = (ListView) findViewById(R.id.lv);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.cred) {
            Intent t = new Intent(this, Main2Activity.class);
            startActivity(t);
        }

        return super.onOptionsItemSelected(item);
    }

    public void mm(View view) {

        dialog = (LinearLayout) getLayoutInflater().inflate(R.layout.dialog, null);
        etd = (EditText) dialog.findViewById(R.id.etdm);
        etfi = (EditText) dialog.findViewById(R.id.etfirst1);
        sw = (Switch) dialog.findViewById(R.id.switch1);
        ad = new AlertDialog.Builder(this);
        ad.setCancelable(false);

        ad.setTitle("Enter Data");
        ad.setView(dialog);
        ad.setNegativeButton("Reset", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                ft = 0.0;
                dm = 0.0;
                td.setText("");
                tn.setText("");
                tx.setText("");
                ts.setText("");
                lv.setAdapter(null);
                dialogInterface.dismiss();
            }
        });
        ad.setNeutralButton("Cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                dialogInterface.cancel();
            }
        });
        ad.setPositiveButton("Calculate", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int j) {

                Toast.makeText(MainActivity.this, "" + j, Toast.LENGTH_LONG).show();
                if ((etfi.getText().toString().equals("")) || (etfi.getText().toString().equals(".-")) || ((etfi.getText().toString().equals(".")) || (etfi.getText().toString().equals("-")) || (etfi.getText().toString().equals("-."))) ||
                        ((etd.getText().toString().equals("")) || (etd.getText().toString().equals(".-")) || ((etd.getText().toString().equals(".")) || (etd.getText().toString().equals("-")) || (etd.getText().toString().equals("-."))))) {
                    Toast.makeText(MainActivity.this, "Input is unavailable", Toast.LENGTH_SHORT).show();
                } else {
                    ft = Double.parseDouble(etfi.getText().toString());

                    dm = Double.parseDouble(etd.getText().toString());
                    tx.setText(Double.toString(ft));
                    td.setText(Double.toString(dm));
                    cs[0] = Double.toString(ft);
                    if (sw.isChecked())
                        for (int i = 1; i < 20; i++) {
                            cs[i] = Double.toString(Double.parseDouble(cs[i - 1]) * dm);
                        }
                    else {
                        for (int i = 1; i < 20; i++) {
                            cs[i] = Double.toString(Double.parseDouble(cs[i - 1]) + dm);
                        }
                    }
                    lv.setOnItemClickListener(MainActivity.this);
                    lv.setChoiceMode(ListView.CHOICE_MODE_SINGLE);
                    ArrayAdapter<String> adp = new ArrayAdapter<String>(MainActivity.this, R.layout.support_simple_spinner_dropdown_item, cs);
                    lv.setAdapter(adp);
                }

            }
        });



    AlertDialog ada = ad.create();
        ada.show();
}

    DialogInterface.OnClickListener myclick= new DialogInterface.OnClickListener() {
        @Override
        public void onClick(DialogInterface dialogInterface, int j) {

        }

    };

    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
        int n=i+1;
        tn.setText(Integer.toString(n)); double sum;
        if (type==1)
            sum=((n*((2*ft)+dm*(n-1)))/2);
        else{ if ((ft!=0)||(dm!=0)||(dm!=1))
            sum=(ft*(Math.pow(dm,n)-1))/(dm-1);
        else {
            if ((ft == 0) || (dm == 0))
                sum = 0;
            else sum = Double.parseDouble(cs[n]);
        }
        }
        ts.setText(Double.toString(sum));
    }




}
