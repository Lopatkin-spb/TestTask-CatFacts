package space.lopatkin.spb.testtask_catfacts.logik;

import android.view.View;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import space.lopatkin.spb.testtask_catfacts.R;
import space.lopatkin.spb.testtask_catfacts.entities.Fact;

public class Holder extends RecyclerView.ViewHolder {

    private TextView viewName;
    private TextView viewValue;
    private Fact data;

    public Holder(@NonNull View itemView) {
        super(itemView);

        viewName = itemView.findViewById(R.id.item_name);
        viewValue = itemView.findViewById(R.id.item_value);

    }

    public void bind(Fact model) {
        viewName.setText(model.getText());
        viewValue.setText(model.getId());

        data = model;
    }


    //создание интерфейса итемКлик 6)не инициализируем чтобы не было ссылки на лисенер
    public void setListener(final FactsAdapter.OnItemClickListener listener) {
        itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                listener.onItemClick(data);
            }
        });
    }
}
