package space.lopatkin.spb.testtask_catfacts.utils;

import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.DialogFragment;
import space.lopatkin.spb.testtask_catfacts.FragmentFacts;
import space.lopatkin.spb.testtask_catfacts.entities.Fact;

public class SelectLoadingDialog extends DialogFragment {

    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {

        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity())
                .setTitle("диалоговое окно")
                .setMessage("выберите исходную загрузку данных")
                .setCancelable(false)
                .setPositiveButton("из бд", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        boolean room = true;
                        boolean internet = false;
                        Integer flag = 0;
                        dialogListener.onDialogClick(flag);
                        dialogInterface.cancel();
                    }
                })
                .setNegativeButton("с сайта", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        boolean room = false;
                        boolean internet = true;
                        Integer flag = 1;

                        dialogListener.onDialogClick(flag);
                        dialogInterface.cancel();
                    }
                });
        return builder.create();
    }


    //1 создаем интерфейс
    public interface OnDialogClickListener {
        void onDialogClick(Integer flag);
    }

    //2 инициализируем интерфейс
    private OnDialogClickListener dialogListener;

//    //3 создаем сеттер для интерфейса
//    public void setDialogListener(OnDialogClickListener dialogListener) {
//        this.dialogListener = dialogListener;
//    }

    //3 исключаем налл поинтер ексепшион
    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        try {
            dialogListener = (OnDialogClickListener) getContext();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        dialogListener = null;
    }

}
