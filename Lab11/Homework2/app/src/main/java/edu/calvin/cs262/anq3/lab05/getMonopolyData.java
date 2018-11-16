package edu.calvin.cs262.anq3.lab05;

import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONObject;

import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class getMonopolyData extends AsyncTask<String, Void, String> {

    private String[] myId = null;
    private String[] myEmail = null;
    private String[] myName = null;

    String errorToast = null;
    TextView myText;
    EditText myEdit;
    List<String> finalOutput;
    Context myContext;

    public getMonopolyData(TextView theText, EditText theEdit, Context theContext) {
        myText = theText;
        myEdit = theEdit;
        myContext = theContext;
    }

    @Override
    protected String doInBackground(String... strings) {

        retrieveData();

        final ArrayList<String> idArray = new ArrayList<String>();

        for (int i = 0; i < myId.length; i++) {
            String fullString = "";
            fullString += myId[i] + ", ";

            //Name string
            if (myName[i] != "" && myName[i] != null) {
                fullString += myName[i] + ", ";
            } else {
                fullString += "no name, ";
            }

            //email string
            if (myEmail[i] != "" && myEmail[i] != null) {
                fullString += myEmail[i];
            } else {
                fullString += "no email";
            }

            idArray.add(fullString);
        }

//        BaseAdapter myAdapter = new BaseAdapter() {
//            @Override
//            public int getCount() {
//                return myId.length;
//            }
//
//            @Override
//            public Object getItem(int position) {
//                String[] aNewStringArray = new String[1];
//                aNewStringArray[0] = idArray.get(position);
//                return aNewStringArray;
//            }
//
//            @Override
//            public long getItemId(int position) {
//                return Integer.parseInt(idArray.get(position));
//            }
//
//            @Override
//            public View getView(int position, View convertView, ViewGroup parent) {
//                return myView;
//            }
//        };
        finalOutput = idArray;
        return "";
        //return null;
//        myActivity.setPlayerList(idArray);
//
//        return null;

    }

    @Override
    protected void onPostExecute(String s) {
        setPlayerList(finalOutput);
        if (errorToast != null) {
            Toast toast = Toast.makeText(myContext, errorToast, Toast.LENGTH_LONG);
            toast.show();

        }
    }

    public void setPlayerList(List<String> lines) {
        String finalList = "";

        try {
            String entryText = myEdit.getText().toString();
            int playerEntry = Integer.parseInt(entryText);
            finalList += lines.get(playerEntry - 1);
        } catch (Exception e) {
            for (int i = 0; i < lines.size(); i++) {
                finalList += lines.get(i) + "\n" + "______________________________________" + "\n";
            }
        }
        myText.setText(finalList);
    }

    void retrieveData() {
        String log_tag = getMonopolyData.class.getSimpleName();

        String playerStuff = getData("https://calvincs262-monopoly.appspot.com/monopoly/v1/players");
        Log.d(log_tag, playerStuff);

        try {
            if (playerStuff != "" && playerStuff != null) {
                myId = parseData(playerStuff, "id");
                myEmail = parseData(playerStuff, "emailAddress");
                myName = parseData(playerStuff, "name");
            }
        } catch (Exception e) {
            Log.d(log_tag, e.toString());
        }
    }

    String getData(String newUrl) {

        String outString = "";

        try {
            URL url = new URL(newUrl);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("GET");
            conn.connect();
            int responsecode = conn.getResponseCode();

            if (responsecode != 200) {
                throw new RuntimeException("HttpResponseCode: " + responsecode);
            } else {
                Scanner sc = new Scanner(url.openStream());
                while (sc.hasNext()) {
                    outString += sc.nextLine();
                }

                sc.close();
            }

        } catch (Exception e) {
            errorToast = e.toString();
        }

        return outString;
    }

    String[] parseData(String input, String itemName) {

        String[] result = new String[0];
        String log_tag = getMonopolyData.class.getSimpleName();

        try {

            JSONObject jsonObject = new JSONObject(input);
            JSONArray itemsArray = jsonObject.getJSONArray("items");
            result = new String[itemsArray.length()];
            for (int i = 0; i < itemsArray.length(); i++) {
                try {
                    JSONObject item = itemsArray.getJSONObject(i);
                    result[i] = item.getString(itemName);
                    Log.d(log_tag, result[i]);
                } catch (Exception e) {
                    //
                }
            }

        } catch (Exception e) {
            Log.d(log_tag, e.toString());
        }
        return result;

    }
}
