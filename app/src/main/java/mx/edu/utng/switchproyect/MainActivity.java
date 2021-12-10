package mx.edu.utng.switchproyect;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.Cache;
import com.android.volley.Network;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.BasicNetwork;
import com.android.volley.toolbox.DiskBasedCache;
import com.android.volley.toolbox.HurlStack;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.firebase.crashlytics.buildtools.reloc.org.apache.http.HttpRequest;
import com.google.firebase.crashlytics.buildtools.reloc.org.apache.http.HttpResponse;
import com.google.firebase.crashlytics.buildtools.reloc.org.apache.http.StatusLine;
import com.google.firebase.crashlytics.buildtools.reloc.org.apache.http.client.HttpClient;
import com.google.firebase.crashlytics.buildtools.reloc.org.apache.http.client.methods.HttpGet;
import com.google.firebase.crashlytics.buildtools.reloc.org.apache.http.impl.client.DefaultHttpClient;
import com.prathameshmore.retrofit_android.Get;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.GET;

public class MainActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
    public interface JsonPlaceHolderApi {
        @GET("http://192.168.1.84/switch/status.php")
        //Call<List<Get>> getStatus();
        Call<String> getStatus();
    }

    public void change_status(View view){
        RequestQueue requestQueue;
        Cache cache = new DiskBasedCache(getCacheDir(), 1024*1024);
        Network network = new BasicNetwork(new HurlStack());
        requestQueue = new RequestQueue(cache, network);
        requestQueue.start();
        String url = "http://192.168.1.84/switch/status.php";

        StringRequest request = new StringRequest(Request.Method.GET,url, new Response.Listener<String>(){
            @Override
            public void onResponse(String response) {
                if (response.equals("On")){
                    Button button = (Button)findViewById(R.id.button);
                    button.setText("Off");
                    System.out.println(response);
                }else {
                    Button button = (Button)findViewById(R.id.button);
                    button.setText("On");
                    System.out.println(response);

                }

            }},
            new Response.ErrorListener() {
            @Override
                public void onErrorResponse(VolleyError Error){
                System.out.println(Error);
            }
        });
        requestQueue.add(request);
    }
}

