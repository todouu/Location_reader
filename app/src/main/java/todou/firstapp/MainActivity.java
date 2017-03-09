package todou.firstapp;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //First act:
        TextView listViewItem_ver = (TextView) findViewById(R.id.maintext1);
        listViewItem_ver.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick (View V){
                Intent papa = new Intent(MainActivity.this, Listview_var.class);
                startActivity(papa);
            }
        });

        //Second act:
        TextView listViewItem_hor = (TextView) findViewById(R.id.maintext2);
        listViewItem_hor.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v) {
                Intent papa = new Intent(MainActivity.this, Listview_hor.class);
                startActivity(papa);
            }
        });

        //Third act:
        TextView gridViewItem = (TextView) findViewById(R.id.maintext3);
        gridViewItem.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v) {
                Intent papa = new Intent(MainActivity.this, Gridview.class);
                startActivity(papa);
            }
        });

        //Fourth act:
        TextView relativwView = (TextView) findViewById(R.id.maintext4);
        relativwView.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v) {
                Intent papa = new Intent(MainActivity.this, Relativeview.class);
                startActivity(papa);
            }
        });

        //Fiveth act:
        TextView locationView = (TextView) findViewById(R.id.maintext5);
        locationView.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v) {
                Intent papa = new Intent(MainActivity.this, locationGet.class);
                startActivity(papa);
            }
        });

        //Sixth act:
        TextView sqlview = (TextView) findViewById(R.id.maintext6);
        sqlview.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v) {
                Intent papa = new Intent(MainActivity.this, sqlview.class);
                startActivity(papa);
            }
        });
    }
}
