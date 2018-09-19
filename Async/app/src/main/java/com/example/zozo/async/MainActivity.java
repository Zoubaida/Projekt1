package com.example.zozo.async;

/**
 * @author Zoubida Alshekhly & Fetratullah Momand
 */
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

/**
 * Main activity kör hela applikation och har id som refererar till layouten för buttons osv, och är kopplad till client klass
 */
public class MainActivity extends AppCompatActivity {
    Client client;
    Button forwardbtn, backwardbtn, leftbtn, rightbtn, breakbtn, resultsbtn;
    TextView results;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.content_main);
        forwardbtn =  (Button) findViewById(R.id.forwardBtn);
        forwardbtn.setOnClickListener(new ButtonListner());
        backwardbtn = (Button)findViewById(R.id.backwardBtn);
        backwardbtn.setOnClickListener(new ButtonListner());
        leftbtn = (Button) findViewById(R.id.leftBtn);
        leftbtn.setOnClickListener(new ButtonListner());
        rightbtn = (Button) findViewById(R.id.rightBtn);
        rightbtn.setOnClickListener(new ButtonListner());
        breakbtn = (Button) findViewById(R.id.breakBtn);
        breakbtn.setOnClickListener(new ButtonListner());
        resultsbtn = (Button) findViewById(R.id.resultsBtn);
        resultsbtn.setOnClickListener(new ButtonListner());
        results = (TextView) findViewById(R.id.textView) ;

        client = new Client("192.168.254.52", 443);
        client.activity = this;
        client.execute();

    }

    /**
     * ButtonListener klass den har en switch-case i onclick metoden som skickar en olik sträng vid varje click med hjälp av sendmessage metoden som finns i client klass
     */
    private class ButtonListner implements View.OnClickListener{

        @Override
        public void onClick(View v) {
            switch(v.getId()) {
                case R.id.forwardBtn:

                    try {
                        client.sendMessage( "1");
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    break;
                case R.id.backwardBtn:
                    try {
                        client.sendMessage("2");
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    break;
                case R.id.leftBtn:
                    try {
                        client.sendMessage("3");
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    break;
                case R.id.rightBtn:
                    try{
                        client.sendMessage("4");
                    }catch (Exception e){
                        e.printStackTrace();
                    }
                    break;
                case R.id.breakBtn:
                    try{
                        client.sendMessage("5");
                    }catch (Exception e){
                    e.printStackTrace();
                }
                    break;
                case R.id.resultsbtn:
                    try{
                        client.sendMessage("11");
                        results.setText(client.type);
                    }catch (Exception e){
                        e.printStackTrace();
                    }
                    break;
            }
        }
    }
}

