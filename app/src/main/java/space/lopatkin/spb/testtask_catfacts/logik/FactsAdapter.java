package space.lopatkin.spb.testtask_catfacts.logik;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import space.lopatkin.spb.testtask_catfacts.R;
import space.lopatkin.spb.testtask_catfacts.entities.Fact;

import java.util.ArrayList;
import java.util.List;

public class FactsAdapter extends RecyclerView.Adapter<Holder> {

    private List<Fact> list = new ArrayList<>();

    @NonNull
    @Override
    public Holder onCreateViewHolder(@NonNull ViewGroup parent,
                                     int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View v = inflater.inflate(R.layout.item_list, parent, false);


        return new Holder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull Holder holder,
                                 int position) {
        holder.bind(list.get(position));

        holder.setListener(listener);
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public void addData(List<Fact> l) {
        list.clear();
        list.addAll(l);
        notifyDataSetChanged();


    }


    //создание интерфейса итемКлик 1)содаем интерф с методом
    public interface OnItemClickListener {
        void onItemClick(Fact data);
    }

    //создание интерфейса итемКлик 5)инициализация интерфейса
    //устанавливаем в onBindViewHolder сеттер лисенера для холдера (один из способов)
    // и передаем его дальше в холдер
    private OnItemClickListener listener;


    //создание интерфейса итемКлик 4)устанавливаем сеттер лисенера для фрагмента
    public void setListener(OnItemClickListener onItemClickListener) {
        listener = onItemClickListener;
    }

}
