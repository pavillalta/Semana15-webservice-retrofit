package com.example.semana13_webservice_retrofit;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

import com.example.semana13_webservice_retrofit.Interface.JsonPlaceHolderApi;
import com.example.semana13_webservice_retrofit.Modelo.Posts;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {

    private TextView mJsonTextView;

    @Override
    protected void onCreate( Bundle savedInstanceState ) {
        super.onCreate ( savedInstanceState );
        setContentView ( R.layout.activity_main );

        mJsonTextView = findViewById ( R.id.jsonText );
        getPosts ();
    }

    private void getPosts(){
        Retrofit retrofit = new Retrofit.Builder ()
                .baseUrl ( "https://jsonplaceholder.typicode.com/" )
                .addConverterFactory ( GsonConverterFactory.create () )
                .build ();

        JsonPlaceHolderApi jsonPlaceHolderApi = retrofit.create ( JsonPlaceHolderApi.class );
        Call<List<Posts>> call = jsonPlaceHolderApi.getPost ();
        call.enqueue ( new Callback<List<Posts>> ( ) {
            @Override
            public void onResponse( Call<List<Posts>> call, Response<List<Posts>> response ) {
                if (!response.isSuccessful ()){
                    mJsonTextView.setText ( "codigo: " + response.code () );
                }

                List<Posts> postsList = response.body ();
                for(Posts posts:postsList){
                    String content = "";
                    content += "userId:"+ posts.getUserId () + "\n";
                    content += "id:"+ posts.getUserId () + "\n";
                    content += "title:"+ posts.getUserId () + "\n";
                    content += "body:"+ posts.getUserId () + "\n\n";
                    mJsonTextView.append ( content );
                }
            }

            @Override
            public void onFailure( Call<List<Posts>> call, Throwable t ) {
                mJsonTextView.setText ( t.getMessage () );

            }
        } );
    }
}