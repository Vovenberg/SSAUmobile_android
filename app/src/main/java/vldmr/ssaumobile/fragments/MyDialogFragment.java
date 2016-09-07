package vldmr.ssaumobile.fragments;

import android.app.AlertDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.content.DialogInterface;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.j256.ormlite.dao.BaseDaoImpl;

import java.sql.SQLException;
import java.util.Collection;
import java.util.List;

import vldmr.ssaumobile.activities.Catalog;
import vldmr.ssaumobile.database.CatalogEntity;
import vldmr.ssaumobile.database.DatabaseHelper;
import vldmr.ssaumobile.database.DatabaseHelperFactory;
import vldmr.ssaumobile.database.EmailEntity;
import vldmr.ssaumobile.database.PhoneEntity;

/**
 * Created by Vladimir on 09.06.2016.
 */
public class MyDialogFragment extends android.support.v4.app.DialogFragment {
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        AlertDialog.Builder builder=new AlertDialog.Builder(getActivity());

        String s= (String) getArguments().getCharSequence("name");
        List<CatalogEntity> list = null;
        try {
            list=DatabaseHelperFactory.getHelper().getCatalogDAO().queryForEq("name",s);
        } catch (SQLException e) {
            e.printStackTrace();
        }

        CatalogEntity catalogEntity=list.get(0);
        Collection<EmailEntity> listEmail=catalogEntity.getEmails();
        Collection<PhoneEntity> listPhones=catalogEntity.getPhones();
        int size=listEmail.size()+listPhones.size();
        String[] array=new String[size];
        int i=0;
        for (PhoneEntity p:listPhones){
            array[i]=p.getPhone();
            i++;
        }
        for (EmailEntity e:listEmail){
            array[i]=e.getEmail();
            i++;
        }

        builder.setTitle(s)
                .setItems(array, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Toast.makeText(getActivity(),
                                "Скопированн в буфер",
                                Toast.LENGTH_SHORT).show();
                    }
                });


        return builder.create();
    }
}
