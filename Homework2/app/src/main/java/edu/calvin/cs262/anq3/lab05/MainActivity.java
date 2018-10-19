package edu.calvin.cs262.anq3.lab05;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import java.util.List;

public class MainActivity extends AppCompatActivity {

//    TextView mTitleText;
//    TextView mAuthorText;
    EditText idInput;
    TextView playerList;
    getMonopolyData theData;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

//        mTitleText = findViewById(R.id.titleText);
//        mAuthorText = findViewById(R.id.authorText);
        idInput = findViewById(R.id.IDinput);
        playerList = findViewById(R.id.playerList);
        theData = new getMonopolyData(playerList, idInput, this);
//
//        if(getSupportLoaderManager().getLoader(0)!=null){
//            getSupportLoaderManager().initLoader(0,null,this);
//        }

    }

    public void fetchData(View view) {

        if(theData != null){
            theData.cancel(true);
        }
        theData = new getMonopolyData(playerList, idInput, this);
        theData.execute();

    }

    //Some leftover code I pasted from lab5 to help me with the JSON connections
/*
    public void searchBooks(View view) {

        String queryString = mBookInput.getText().toString();

        InputMethodManager inputManager = (InputMethodManager)
                getSystemService(Context.INPUT_METHOD_SERVICE);
        inputManager.hideSoftInputFromWindow(getCurrentFocus().getWindowToken(),
                InputMethodManager.HIDE_NOT_ALWAYS);

        ConnectivityManager connMgr = (ConnectivityManager)
                getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = connMgr.getActiveNetworkInfo();

        if (networkInfo != null && networkInfo.isConnected() && queryString.length()!=0) {
//            new FetchBook(mTitleText, mAuthorText).execute(queryString);

            Bundle queryBundle = new Bundle();
            queryBundle.putString("queryString", queryString);
            getSupportLoaderManager().restartLoader(0, queryBundle,this);

            mAuthorText.setText("");
            mTitleText.setText("Loading...");
        }

        else {
            if (queryString.length() == 0) {
                mAuthorText.setText("");
                mTitleText.setText("Please enter a search term");
            } else {
                mAuthorText.setText("");
                mTitleText.setText("Please check your network connection and try again.");
            }
        }
    }

    @NonNull
    @Override
    public Loader<String> onCreateLoader(int i, @Nullable Bundle bundle) {
        //return new BookLoader(this, bundle.getString("queryString"));
        return null;
    }


    @Override
    public void onLoadFinished(@NonNull Loader<String> loader, String s) {
        /*
        try {
            JSONObject jsonObject = new JSONObject(s);
            JSONArray itemsArray = jsonObject.getJSONArray("items");
            for(int i = 0; i<itemsArray.length(); i++){
                JSONObject book = itemsArray.getJSONObject(i);
                String title=null;
                String authors=null;
                JSONObject volumeInfo = book.getJSONObject("volumeInfo");


                try {
                    title = volumeInfo.getString("title");
                    authors = volumeInfo.getString("authors");
                } catch (Exception e){
                    e.printStackTrace();
                }


                if (title != null && authors != null){
                    mTitleText.setText(title);
                    mAuthorText.setText(authors);
                    return;
                }
            }

            mTitleText.setText("No Results Found");
            mAuthorText.setText("");


        } catch (Exception e){
            mTitleText.setText("No Results Found");
            mAuthorText.setText("");
            e.printStackTrace();
        }
    }


    @Override
    public void onLoaderReset(@NonNull Loader<String> loader) {

    }*/

}
