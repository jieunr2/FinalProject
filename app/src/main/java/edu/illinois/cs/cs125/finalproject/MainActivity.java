package edu.illinois.cs.cs125.finalproject;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;
import com.google.gson.JsonObject;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/**
 * Main class for our UI design lab.
 */
public final class MainActivity extends AppCompatActivity {
    /** Default logging tag for messages from the main activity. */
    private static final String TAG = "Final_Project";

    public String json_api;
    TextView final_number;
    EditText number1;
    Button euro, yen, yuan, won, peso;

    double result_num;
    double num1;
    double num2;

    /** Request queue for our API requests. */
    private static RequestQueue requestQueue;

    /**
     * Run when this activity comes to the foreground.
     *
     * @param savedInstanceState unused
     */
    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Set up the queue for our API requests
        requestQueue = Volley.newRequestQueue(this);

        setContentView(R.layout.activity_main);


        final_number =(TextView)findViewById(R.id.final_num);
        number1 = (EditText)findViewById(R.id.number1);
        euro = (Button)findViewById(R.id.euro);
        yen = (Button)findViewById(R.id.yen);
        yuan = (Button)findViewById(R.id.yuan);
        won = (Button)findViewById(R.id.won);
        peso = (Button)findViewById(R.id.peso);

        find_exchangeRate();
    }
    public void find_exchangeRate() {
        String url = "http://www.apilayer.net/api/live?access_key=e427185cfacdf0c726b8ccbf02319748&format=1";

        JsonObjectRequest jor = new JsonObjectRequest(Request.Method.GET, url, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                try {
                    final JSONObject main_object = response.getJSONObject("quotes");
                    org.json.JSONObject object_json = main_object;
                    JsonParser jsonParser = new JsonParser();
                    final JsonObject json_object = (JsonObject) jsonParser.parse(object_json.toString());

                    euro.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            num1 = Double.parseDouble(number1.getText().toString());
                            num2 = json_object.get("USDEUR").getAsDouble();
                            result_num = num1 * num2;
                            final_number.setText(String.valueOf(result_num));
                        }
                    });

                    yen.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            num1 = Double.parseDouble(number1.getText().toString());
                            num2 = json_object.get("USDJPY").getAsDouble();
                            result_num = num1 * num2;
                            final_number.setText(String.valueOf(result_num));
                        }
                    });

                    yuan.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            num1 = Double.parseDouble(number1.getText().toString());
                            num2 = json_object.get("USDCNY").getAsDouble();
                            result_num = num1 * num2;
                            final_number.setText(String.valueOf(result_num));
                        }
                    });

                    won.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            num1 = Double.parseDouble(number1.getText().toString());
                            num2 = json_object.get("USDKRW").getAsDouble();
                            result_num = num1 * num2;
                            final_number.setText(String.valueOf(result_num));
                        }
                    });

                    peso.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            num1 = Double.parseDouble(number1.getText().toString());
                            num2 = json_object.get("USDMXN").getAsDouble();
                            result_num = num1 * num2;
                            final_number.setText(String.valueOf(result_num));
                        }
                    });
                } catch(JSONException e) {
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        }
        );
        RequestQueue queue = Volley.newRequestQueue(this);
        queue.add(jor);

    }
}