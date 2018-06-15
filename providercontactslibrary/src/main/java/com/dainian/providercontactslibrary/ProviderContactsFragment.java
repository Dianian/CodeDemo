package com.dainian.providercontactslibrary;

import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

/**
 * @author By FuBowen
 * @date 2018/6/15 14:26
 */
public class ProviderContactsFragment extends Fragment {

    private ListView mContactsView;

    private ArrayAdapter<String> mAdapter;

    private List<String> mStrings = new ArrayList<>();

private Activity mActivity;

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        mActivity= (Activity) context;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.fragment_provider_contacts,container,false);
        initView(view);
        return view;
    }

    private void initView(View view) {
        mContactsView = view.findViewById(R.id.contacts_view);
        mAdapter = new ArrayAdapter<>(mActivity, android.R.layout.simple_list_item_1, mStrings);
        mContactsView.setAdapter(mAdapter);
        if (ContextCompat.checkSelfPermission(mActivity, Manifest.permission.READ_CONTACTS) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(mActivity, new String[]{Manifest.permission.READ_CONTACTS}, 1);
        } else {
            readCotacts();
        }
    }

    private void readCotacts() {
        Cursor cursor = null;
        cursor = mActivity.getContentResolver().query(ContactsContract.CommonDataKinds.Phone.CONTENT_URI, null, null, null, null);
        try {
            if (cursor != null) {
                if (cursor.moveToFirst()) {
                    do {
                        String name = cursor.getString(cursor.getColumnIndex(ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME));
                        String tel = cursor.getString(cursor.getColumnIndex(ContactsContract.CommonDataKinds.Phone.NUMBER));
                        mStrings.add(name + "\n" + tel);
                    } while (cursor.moveToNext());
                }
                mAdapter.notifyDataSetChanged();
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            cursor.close();
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        switch (requestCode) {
            case 1:
                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    readCotacts();
                } else {
                    Toast.makeText(mActivity, "请授权!", Toast.LENGTH_SHORT).show();
                }
                break;
            default:
                break;
        }
    }
}
