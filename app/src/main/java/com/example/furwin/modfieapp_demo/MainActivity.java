package com.example.furwin.modfieapp_demo;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    TextView name, tlf;
    ImageView imagen;
    ListView lista;
    String url = "http://api.randomuser.me/?results=7";
    JSONObject jobjectText;
    JSONArray results;
    Persona p;
    Name nombre;
    Location location;
    Pictures pics;
    private ArrayList<Persona> personas;


    /*

        var http = new Http;

        var results = [];

        http.get('/users', function(response) {
            results = response.data;

            //
        }, function(response) {
           alert(response.errors);
        });

     */

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        new AsyncTaskExample().execute(url);


    }

    public void personaView() {
        ArrayAdapter<Persona> listapersonas = new myListAdapter();
        lista = (ListView) findViewById(R.id.listView);
        lista.setAdapter(listapersonas);
    }

    //async Task para que se ejecute en background
    public class AsyncTaskExample extends AsyncTask<String, String, ArrayList<Persona>> {

        @Override
        protected void onPreExecute() {

        }

        @Override
        protected ArrayList<Persona> doInBackground(String... url) {
            personas = new ArrayList<>();

            try {

                jobjectText = JsonPersonaParser.readJsonFromUrl(url[0]);
                results = jobjectText.getJSONArray("results");


                for (int i = 0; i < results.length(); i++) {
                    p = new Persona();
                    nombre = new Name();
                    location = new Location();
                    pics = new Pictures();
                    nombre = componerNombre(results, i);
                    location = rellenaLocation(results, i);
                    pics = rellenaImagenes(results, i);
                    p.setNombre(nombre);
                    p.setLocation(location);
                    p.setPicture(pics);
                    personas.add(p);


                }





                /*
                String [] resultado=new String[3];
                resultado[0]=jobjectText.getJSONArray("results").toString();
                JSONArray results = jobjectText.getJSONArray("results");
                objetos[0]=results+" "+jobjectText.getJSONArray("results").getJSONObject(0).getJSONObject("user").getJSONObject("name").getString("first")+" "+jobjectText.getJSONArray("results").getJSONObject(0).getJSONObject("user").getJSONObject("name").getString("last");
                objetos[1]=jobjectText.getJSONArray("results").getJSONObject(0).getJSONObject("user").getJSONObject("location").getString("city");
                objetos[2]=jobjectText.getJSONArray("results").getJSONObject(0).getJSONObject("user").getString("username");
                objetos[3]=jobjectText.getJSONArray("results").getJSONObject(0).getJSONObject("user").getString("password");
                objetos[4]=jobjectText.getJSONArray("results").getJSONObject(0).getJSONObject("user").getJSONObject("picture").getString("large");
                objetos[5]=jobjectText.getJSONArray("results").getJSONObject(0).getJSONObject("user").getJSONObject("picture").getString("medium");
                objetos[6]=jobjectText.getJSONArray("results").getJSONObject(0).getJSONObject("user").getJSONObject("picture").getString("thumbnail");*/
            } catch (IOException | JSONException e) {
                e.printStackTrace();
            }

            return personas;
        }

        @Override
        protected void onPostExecute(ArrayList<Persona> PersonaFromDoInBackground) {
/*
            name=(TextView) findViewById(R.id.textName);
            tlf=(TextView) findViewById(R.id.textTLF);
            imagen=(ImageView) findViewById(R.id.imagen);
            for(int x=0;x<=PersonaFromDoInBackground.size();x++){
                Persona p=PersonaFromDoInBackground.get(x);*/
            personaView();
            Toast.makeText(MainActivity.this,""+PersonaFromDoInBackground.size(), Toast.LENGTH_SHORT).show();

        }


    }


    //Metodo crearNombre

    public Name componerNombre(JSONArray results,int pos) {
        Name n = new Name();

            try {
                // n.set{i} = getString(i);
                n.setTitle(results.getJSONObject(pos).getJSONObject("user").getJSONObject("name").getString("title"));
                n.setFirst(results.getJSONObject(pos).getJSONObject("user").getJSONObject("name").getString("first"));
                n.setLast(results.getJSONObject(pos).getJSONObject("user").getJSONObject("name").getString("last"));

            } catch (JSONException e) {
                e.printStackTrace();
            }


        return n;
    }

    public Location rellenaLocation(JSONArray results,int pos) {
        Location l = new Location();

            //creamos el objetos de localidad

            try {
                l.setCity(results.getJSONObject(pos).getJSONObject("user").getJSONObject("location").getString("city"));
                l.setZip(results.getJSONObject(pos).getJSONObject("user").getJSONObject("location").getString("zip"));

            } catch (JSONException e) {
                e.printStackTrace();
            }

        return l;
    }


    public Pictures rellenaImagenes(JSONArray results,int pos) {
        Pictures p = new Pictures();


            //creamos el objetos de la Imagen

            try {
                p.setLarge(results.getJSONObject(pos).getJSONObject("user").getJSONObject("picture").getString("large"));
                p.setMedium(results.getJSONObject(pos).getJSONObject("user").getJSONObject("picture").getString("medium"));
                p.setThumb(results.getJSONObject(pos).getJSONObject("user").getJSONObject("picture").getString("thumbnail"));

            } catch (JSONException e) {
                e.printStackTrace();
            }
                return p;


    }




    private class myListAdapter extends ArrayAdapter<Persona> {
        public myListAdapter() {

            super(MainActivity.this, R.layout.parent_ex_layout,personas);
        }
        public View getView(int position, View convertView, ViewGroup parentView) {
            View itemView = convertView;
            if(itemView==null)
                itemView=getLayoutInflater().inflate(R.layout.parent_ex_layout,parentView,false);
            name = (TextView) itemView.findViewById(R.id.textName);
            tlf = (TextView) itemView.findViewById(R.id.textTLF);
            imagen = (ImageView) itemView.findViewById(R.id.imagen);

            Persona currentuser = personas.get(position);
            name.setText("Nombre: "+currentuser.getNombre().getTitle() + " " + currentuser.getNombre().getFirst() + " " + currentuser.getNombre().getLast());
            tlf.setText("Localidad: "+currentuser.getLocation().getCity() + " Código postal:" + currentuser.getLocation().getZip());
            Picasso.with(MainActivity.this).load(currentuser.getPicture().getMedium()).into(imagen);


            return itemView;

        }


    }




}
