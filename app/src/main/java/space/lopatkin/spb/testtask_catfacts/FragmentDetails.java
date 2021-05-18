package space.lopatkin.spb.testtask_catfacts;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import space.lopatkin.spb.testtask_catfacts.entities.Fact;

public class FragmentDetails extends Fragment {
    private TextView viewId;
    private TextView viewUser;
    private TextView viewText;
    private TextView viewSource;
    private TextView viewUpdatedAt;
    private TextView viewCreatedAt;

    //входные данный в инстанс
    public static FragmentDetails newInstance(Fact data) {
        Bundle args = new Bundle();
        args.putSerializable("data", data);
        FragmentDetails fragment = new FragmentDetails();
        fragment.setArguments(args);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_details, container, false);
        return v;
    }

    @Override
    public void onViewCreated(@NonNull View view,
                              @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        viewId = view.findViewById(R.id.view_id);
        viewUser = view.findViewById(R.id.view_user);
        viewText = view.findViewById(R.id.view_text);
        viewSource = view.findViewById(R.id.view_source);
        viewUpdatedAt = view.findViewById(R.id.view_updatedAt);
        viewCreatedAt = view.findViewById(R.id.view_createdAt);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        Fact message = (Fact) getArguments().getSerializable("data");

        viewId.setText(message.getId());
        viewUser.setText(message.getUser());
        viewText.setText(message.getText());
        viewSource.setText(message.getSource());
        viewUpdatedAt.setText(message.getUpdatedAt());
        viewCreatedAt.setText(message.getCreatedAt());

    }
}
