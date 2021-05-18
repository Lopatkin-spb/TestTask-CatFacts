package space.lopatkin.spb.testtask_catfacts;

import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import okhttp3.*;
import space.lopatkin.spb.testtask_catfacts.db.FactDao;
import space.lopatkin.spb.testtask_catfacts.utils.ClientOnly;
import space.lopatkin.spb.testtask_catfacts.logik.FactsAdapter;
import space.lopatkin.spb.testtask_catfacts.entities.Fact;
import space.lopatkin.spb.testtask_catfacts.utils.ClientUser;

import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class FragmentFacts extends Fragment {
    public static final String TAG = "myLogs";
    private RecyclerView recyclerView;
    private FactsAdapter adapter = new FactsAdapter();
    private List<Fact> list = new ArrayList<>();

//    private List<Fact> list = null;

    private FactDao factDao;
    private ProgressBar progressBar;

    public boolean room = false;
    public boolean internet = false;
    private boolean alreadyLoad = false;
    private boolean loadAfterCircle = false;

    private Integer flag = 0;


    public static FragmentFacts newInstance(Integer loadFromInternet) {

        Bundle args = new Bundle();
        args.putInt("loadFromInternet", loadFromInternet);
        FragmentFacts fragment = new FragmentFacts();
        fragment.setArguments(args);
        return fragment;
    }


    public static FragmentFacts newInstance(boolean loadRoom,
                                            boolean loadInternet) {
        Bundle args = new Bundle();
        args.putBoolean("loadRoom", loadRoom);
        args.putBoolean("loadInternet", loadInternet);
        FragmentFacts fragment = new FragmentFacts();
        fragment.setArguments(args);
        return fragment;
    }


//    @Override
//    public void onViewStateRestored(@Nullable Bundle savedInstanceState) {
//        super.onViewStateRestored(savedInstanceState);
//        if (savedInstanceState != null) {
//
////            list.addAll((Collection<? extends Fact>) savedInstanceState.getSerializable("onSaveInstanceState"));
////            adapter.addData(list);
//
//            flag = savedInstanceState.getInt("flag");
//            Log.d(TAG, "--------------fragment onViewStateRestored: flag " + flag);
//
////            adapter.addData(list);
//        }
////        else if (!loadAfterCircle) {
////            loadAfterCircle = true;
//////            loadDatabase();
////        }
//    }


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_facts, container, false);
        Log.d(TAG, "--------------fragment onCreateView перед загрузкой: flag " + flag);
        return v;
    }

    @Override
    public void onViewCreated(@NonNull View view,
                              @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        recyclerView = view.findViewById(R.id.recycler_view);
        progressBar = view.findViewById(R.id.progress_bar);
        Log.d(TAG, "--------------fragment onViewCreated перед загрузкой: flag " + flag);

    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

//        if (getArguments() != null) {
//            room = getArguments().getBoolean("loadRoom");
//            internet = getArguments().getBoolean("loadInternet");
//        }

        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerView.setAdapter(adapter);

        //создание интерфейса итемКлик 4)передача лисенера в адаптер
        adapter.setListener(itemClickListener);

        factDao = ((AppDelegate) getActivity().getApplicationContext())
                .getFactDatabase()
                .getFactDao();

//        if (savedInstanceState == null && !alreadyLoad) {
//            alreadyLoad = true;
//            if (internet == true && room == false && flag == 0) {
//
//                flag = 1;
//                Log.d(TAG, "--------------fragment getFacts: flag " + flag);
//
//                getFacts();
//            } else if (internet == false && room == true && flag ==1) {
//                Log.d(TAG, "--------------fragment loadDatabase: flag " + flag);
//
//                loadDatabase();
//            } else {
//            }
//        }
        if (savedInstanceState == null) {
            flag = getArguments().getInt("loadFromInternet");
        }
        Log.d(TAG, "--------------fragment onActivityCreated перед загрузкой: flag " + flag);

        if (flag == 1) {
            Log.d(TAG, "--------------fragment getFacts: flag " + flag);

            flag = 1;
            getFacts();
//            getUsers();
            progressBar.setVisibility(ProgressBar.VISIBLE);

        } else if (flag == 0) {
            Log.d(TAG, "--------------fragment loadDatabase: flag " + flag);

            loadDatabase();
        } else {
        }


    }


    private void loadDatabase() {
        final Handler handler2 = new Handler() {
            @Override
            public void handleMessage(@NonNull Message msg) {
                super.handleMessage(msg);
                Bundle bundle = msg.getData();
                list.clear();
                list.addAll((Collection<? extends Fact>) bundle.getSerializable("database load"));
                adapter.addData(list);
            }
        };

        Runnable runnable = new Runnable() {
            @Override
            public void run() {
                Message message = handler2.obtainMessage();
                Bundle bundle = new Bundle();
                List<Fact> data = factDao.getFacts();

                bundle.putSerializable("database load", (Serializable) data);

                message.setData(bundle);
                handler2.sendMessage(message);
            }
        };
        Thread thread = new Thread(runnable);
        thread.start();
    }

    private void getFacts() {
        final Handler handler = new Handler() {
            @Override
            public void handleMessage(@NonNull Message msg) {
                super.handleMessage(msg);
                Bundle bundle = msg.getData();
                Log.d(TAG, "--------------fragment handleMessage:  bundle ");
                list.clear();
                list.addAll((Collection<? extends Fact>) bundle.getSerializable("key"));
                progressBar.setVisibility(ProgressBar.INVISIBLE);

                adapter.addData(list);
            }
        };

        Runnable runnable = new Runnable() {
            @Override
            public void run() {
                Log.d(TAG, "--------------fragment runnable:  ");

//                progressBar.setVisibility(ProgressBar.VISIBLE);
                ClientOnly client = new ClientOnly();
                Message message = handler.obtainMessage();
                Bundle bundle = new Bundle();
                List<Fact> data = client.clientItems();
                bundle.putSerializable("key", (Serializable) data);

                factDao.deleteAll();
                factDao.insertFacts(data);
//                progressBar.setVisibility(ProgressBar.INVISIBLE);

                message.setData(bundle);
                handler.sendMessage(message);
            }
        };
        Thread thread = new Thread(runnable);
        thread.start();
    }


    private void getUsers() {
        final Handler handler = new Handler() {
            @Override
            public void handleMessage(@NonNull Message msg) {
                super.handleMessage(msg);
                Bundle bundle = msg.getData();
            }
        };

        Runnable runnable = new Runnable() {
            @Override
            public void run() {
                ClientUser client = new ClientUser();
                Message message = handler.obtainMessage();
                Bundle bundle = new Bundle();
                List data = client.clientItems();
                Log.d(TAG, "--------------fragment runnable: user " + data);

            }
        };
        Thread thread = new Thread(runnable);
        thread.start();
    }


    //создание интерфейса итемКлик 3)(прослойка между активити и адаптером)инициализация интерфейса из адаптера
    // и какаято муть с контекстом
    //затем нужно передать лисенер в адаптер
    private FactsAdapter.OnItemClickListener itemClickListener;

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        if (context instanceof FactsAdapter.OnItemClickListener) {   //?
            itemClickListener = (FactsAdapter.OnItemClickListener) context;
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        itemClickListener = null;
    }


//    @Override
//    public void onSaveInstanceState(@NonNull Bundle outState) {
//        super.onSaveInstanceState(outState);
//        flag = 1;
//        outState.putSerializable("onSaveInstanceState", (Serializable) list);
//        outState.putInt("flag", flag);
////        args.putSerializable("list", (Serializable) list);
//    }

//    @Override
//    public void onViewStateRestored(@Nullable Bundle savedInstanceState) {
//        super.onViewStateRestored(savedInstanceState);
//        if (savedInstanceState != null) {
//
////            list.addAll((Collection<? extends Fact>) savedInstanceState.getSerializable("onSaveInstanceState"));
////            adapter.addData(list);
//
//            flag = savedInstanceState.getInt("flag");
//            Log.d(TAG, "--------------fragment onViewStateRestored: flag " + flag);
//
////            adapter.addData(list);
//        }
////        else if (!loadAfterCircle) {
////            loadAfterCircle = true;
//////            loadDatabase();
////        }
//    }
}
