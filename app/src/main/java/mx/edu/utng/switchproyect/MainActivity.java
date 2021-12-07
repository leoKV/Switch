package mx.edu.utng.switchproyect;

import androidx.appcompat.app.AppCompatActivity;

import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;

/*import com.google.firebase.crashlytics.buildtools.reloc.org.apache.http.HttpResponse;
import com.google.firebase.crashlytics.buildtools.reloc.org.apache.http.NameValuePair;
import com.google.firebase.crashlytics.buildtools.reloc.org.apache.http.client.ClientProtocolException;
import com.google.firebase.crashlytics.buildtools.reloc.org.apache.http.client.HttpClient;
import com.google.firebase.crashlytics.buildtools.reloc.org.apache.http.client.entity.UrlEncodedFormEntity;
import com.google.firebase.crashlytics.buildtools.reloc.org.apache.http.client.methods.HttpPost;
import com.google.firebase.crashlytics.buildtools.reloc.org.apache.http.impl.client.DefaultHttpClient;
import com.google.firebase.crashlytics.buildtools.reloc.org.apache.http.message.BasicNameValuePair;*/

import com.google.firebase.crashlytics.buildtools.reloc.org.apache.http.HttpResponse;
import com.google.firebase.crashlytics.buildtools.reloc.org.apache.http.client.HttpClient;
import com.google.firebase.crashlytics.buildtools.reloc.org.apache.http.client.methods.HttpPut;
import com.google.firebase.crashlytics.buildtools.reloc.org.apache.http.entity.StringEntity;
import com.google.firebase.crashlytics.buildtools.reloc.org.apache.http.impl.client.DefaultHttpClient;

import org.json.JSONObject;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
    public void postFunction(View view){
     new DownloadFilesTask().postFunction();
    }
}

class DownloadFilesTask extends AsyncTask<URL, Integer, Long> {

    protected Long doInBackground(URL... urls) {
// code that will run in the background
        return null;
    }

    protected void onProgressUpdate(Integer... progress) {
// receive progress updates from doInBackground
    }

    protected void onPostExecute(Long result) {
// update the UI after background processes completes
    }

    public void postFunction(){
        String result = null;
        try {
            // 1. create HttpClient
            HttpClient httpclient = new DefaultHttpClient();
            // 2. make POST request to the given URL

            HttpPut httpPut = new
                    HttpPut("http://192.168.1.78:3000/switch/1");
            String json;
            //              // 3. build jsonObject
            //              JSONObject jsonObject2 = new JSONObject();
            //              jsonObject2.put("idGuarderias", idG);
            // 3. build jsonObject
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("status",1);
            json = jsonObject.toString();
            StringEntity se = new StringEntity(json);
            // 6. set httpPost Entity
            httpPut.setEntity(se);
            // 7. Set some headers to inform server about the type of the content
            httpPut.addHeader("Accept", "application/json");
            httpPut.addHeader("Content-type", "application/json");
            // 8. Execute POST request to the given URL
            HttpResponse httpResponse = httpclient.execute(httpPut);


            //Try to add this
            InputStream inputStream = httpResponse.getEntity().getContent();

            if(inputStream != null)
                result = "Si jaló" + inputStream.toString();
            else
                result = "Did not work!";

            System.out.println(result);

        } catch (Exception e) {
            System.out.println("Error del catch: "+e);
            //Log.d("InputStream", e.getLocalizedMessage());
        }

    }
}

/*
    public void changeSwitch(View view) {
        HttpClient client= new DefaultHttpClient();
        HttpPost post= new HttpPost("http://localhost:3000/peliculas/reAp");
        try {
            List<NameValuePair> nameValuePairs= new ArrayList<NameValuePair>(2);
            nameValuePairs.add(new BasicNameValuePair("id","reAp"));
            nameValuePairs.add(new BasicNameValuePair("titulo","Resident Evil Apocalipsis"));
            nameValuePairs.add(new BasicNameValuePair("director","Alexander Witt"));
            nameValuePairs.add(new BasicNameValuePair("productora","Sony"));
            nameValuePairs.add(new BasicNameValuePair("anio_salida","2004"));
            nameValuePairs.add(new BasicNameValuePair("character_main","Alice Abernathy"));
            nameValuePairs.add(new BasicNameValuePair("sinopsis","Prueba 1"));
            post.setEntity(new UrlEncodedFormEntity(nameValuePairs));
            HttpResponse response = client.execute(post);
        }catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        } catch (ClientProtocolException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        ;


    }
}*/