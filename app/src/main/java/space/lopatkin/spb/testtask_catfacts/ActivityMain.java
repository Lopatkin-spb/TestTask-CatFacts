package space.lopatkin.spb.testtask_catfacts;

import android.util.Log;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import androidx.fragment.app.FragmentManager;
import space.lopatkin.spb.testtask_catfacts.db.FactDao;
import space.lopatkin.spb.testtask_catfacts.logik.FactsAdapter;
import space.lopatkin.spb.testtask_catfacts.entities.Fact;
import space.lopatkin.spb.testtask_catfacts.utils.SelectLoadingDialog;

public class ActivityMain extends AppCompatActivity implements FactsAdapter.OnItemClickListener, SelectLoadingDialog.OnDialogClickListener {

//-добавить и подключить фрагмент+++
//    -создать лист+++
//    -создать адаптер и холдер+++
    //-подключение ресайклера,адаптера и холдера+++
    //-добавить разделитель в итем+++
    //-жсон-okhttp3 -dependenses+++
    //-создание клиент-сервер+++
    //-подключиться к адресу: https://cat-fact.herokuapp.com и получение JSON+++
    //еndpoints: -/facts   -/users (аутентификация - придется зарегиться на сайте)
    //модели: Fact     , User
    //первое подключение должно занимать несколько секунд (?),
    // последующие без замедлений
    //    при нажатии на итем открывается подробный итем на страничке
    //-создание модели -факты по пришедшему JSON+++
    //-передел адаптера и холдера+++
    //-поиск и решение передачи данных по многопоточенсти+++
//-забор фактов из жсона+++
    //-создать фрагмент с подробностями+++
    //-создание обработчика на итем+++
    //-передать данные в фрагмент детальный+++
    //    -при нажатии на бек кнопку -переход на предыдущую страницу+++
    //-room dependences+++
    //-добавление рум+++
//    -подключить бд+++
//    -сохранять лист в бд+++
    //-при включении приложения появляется окно с выбором загрузки с сайта или из бд+++
    //- добавить прогрессбар при подключении к сайту+++
//    -при повороте и перезагрузке приложения лист загружается и показывается+++


    public static final String TAG = "myLogs";
    private FragmentManager fragmentManager = getSupportFragmentManager();
    private FragmentFacts fragmentFacts = new FragmentFacts();
    private FragmentDetails fragmentDetails = new FragmentDetails();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        if (savedInstanceState == null) {

            SelectLoadingDialog dialog = new SelectLoadingDialog();
            dialog.show(fragmentManager, "dialog");


//            fragmentManager.beginTransaction()
//                    .replace(R.id.fragment_container, fragmentFacts)
//                    .addToBackStack("backstack")
//                    .commit();
        }

//        SelectLoadingDialog dialog = new SelectLoadingDialog();
//        dialog.show(fragmentManager, "dialog");


//        OkHttpClient client = new OkHttpClient();
//        String urlTest = "https://reqres.in/api/users?page=2";
//        String urlTask = "https://cat-fact.herokuapp.com/#/cat/facts";
//        String urlTaskEndpoint = "https://cat-fact.herokuapp.com/facts";
//
//
//        Request request = new Request.Builder()
//                .url(urlTaskEndpoint)
//                .build();
//
//        client.newCall(request).enqueue(new Callback() {
//            @Override
//            public void onFailure(Call call, IOException e) {
//                Log.d(TAG, "------owibka-----", e);
//            }
//
//            @Override
//            public void onResponse(Call call, Response response) throws IOException {
//
//                if (response.isSuccessful()) {
//                    final String myResponse = response.body().string();
//
//                    MainActivity.this.runOnUiThread(new Runnable() {
//                        @Override
//                        public void run() {
//                            Toast.makeText(getApplicationContext(), myResponse, Toast.LENGTH_LONG).show();
//
//                            Log.d(TAG, myResponse);
//
//                        }
//                    });
//
//
//                }
//
//            }
//
//        }); //client


//        final String urlTaskEndpoint = "https://cat-fact.herokuapp.com/facts";
//        final Client client = new Client();
//
////        String result = null;
//
//        try {
//            client.getJSONString(urlTaskEndpoint);
//        } catch (IOException e) {
//            e.printStackTrace();
//        }


//        try {
//            result = client.getJSONString(urlTaskEndpoint);
//        } catch (IOException e) {
//            e.printStackTrace();
//        }

//        Toast.makeText(getApplicationContext(), result, Toast.LENGTH_LONG).show();

//        if (result !=null) {
//            Log.d(TAG, result);
//        }



    }

    //создание интерфейса итемКлик 2)имплементируем в активити метод итемКлик
    @Override
    public void onItemClick(Fact data) {
        fragmentManager.beginTransaction()
                .replace(R.id.fragment_container, fragmentDetails.newInstance(data))
                .hide(fragmentFacts)
                .show(fragmentDetails)
                .addToBackStack("backstack")
                .commit();
    }

    @Override
    public void onBackPressed() {
        if (fragmentManager.getBackStackEntryCount() == 1) {
            finish();
        } else {
            fragmentManager.popBackStack();
        }
    }


    //4 имплементим интерфейс и импортируем метод
    @Override
    public void onDialogClick(Integer flag) {
        fragmentManager.beginTransaction()
                .replace(R.id.fragment_container, fragmentFacts.newInstance(flag))
                .show(fragmentFacts)
                .hide(fragmentDetails)
                .addToBackStack("backstack")
                .commit();
    }
}
