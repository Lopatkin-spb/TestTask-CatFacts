package space.lopatkin.spb.testtask_catfacts.utils;

import android.net.Uri;
import android.util.Log;
import okhttp3.*;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import space.lopatkin.spb.testtask_catfacts.entities.Fact;


import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class ClientUser {
    public static final String TAG = "myLogs";
    private OkHttpClient client;
    private int mockListSize = 7;

    //взаимодействие с сервером
    public String getJSONString(String UrlSpec) throws IOException {
        client = new OkHttpClient();
        //zapros
        Request request = new Request.Builder()
                .url(UrlSpec)
                .build();
        //otvet
        Response response = client.newCall(request).execute();
        //save result
        String result = response.body().string();
        return result;
    }

    //получение от сервера обьектов
    public List<Fact> clientItems() {
        List<Fact> list = new ArrayList();
        try {
//            String url = Uri.parse("https://cat-fact.herokuapp.com/users")
//                    .buildUpon()
//                    //параметры запроса
//                    //какой формат хотим получить
//                    .appendQueryParameter("format", "json")
//                    //просим сервер не ждать от нас ответов
//                    .appendQueryParameter("nojsoncallback", "1")
//                    .build().toString();

                        String url = "https://cat-fact.herokuapp.com/users";


            String jsonString = getJSONString(url).toString();
            Log.d(TAG, "--------------clientUser clientItems: jsonString ---" + jsonString);
//            JSONArray jsonArray = new JSONArray(jsonString);
//                        Log.d(TAG, "--------------clientUser clientItems: JSONArray jsonArray---" + jsonArray);
//            parseItems(list, jsonArray);
        } catch (IOException ioe) {
            Log.d(TAG, "ошибка загрузки данных USER", ioe);

//            list.addAll(MockGenerator.generate(mockListSize));


//            Toast.makeText(ActivityMain.getApplication(), "ошибка загрузки данных", Toast.LENGTH_SHORT).show();
//        } catch (JSONException jsone) {
//            Log.d(TAG, "ошибка парсинга JSON USER", jsone);
        }
        return list;
    }

    //распарсиваем жсон
    private void parseItems(List<Fact> items, JSONArray jsonArray) throws IOException, JSONException {
        for (int i = 0; i < jsonArray.length(); i++) {
            JSONObject factJSONObject = jsonArray.getJSONObject(i);
            //            Log.d(TAG, "--------------parseItems factJSONObject---" + factJSONObject);
            Fact item = new Fact();

            item.setId(factJSONObject.getString("_id"));
            item.setUser(factJSONObject.getString("user"));
            item.setText(factJSONObject.getString("text"));
            item.setSource(factJSONObject.getString("source"));
            item.setUpdatedAt(factJSONObject.getString("updatedAt"));
            item.setCreatedAt(factJSONObject.getString("createdAt"));

            items.add(item);

//            Log.d(TAG, "---------------------" + item.toString());
        }
    }


}

