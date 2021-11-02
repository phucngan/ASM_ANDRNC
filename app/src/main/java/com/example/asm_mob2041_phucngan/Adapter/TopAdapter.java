package com.example.asm_mob2041_phucngan.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.asm_mob2041_phucngan.Fragment.TopFragment;
import com.example.asm_mob2041_phucngan.R;
import com.example.asm_mob2041_phucngan.entity.Top;

import java.util.ArrayList;

public class TopAdapter extends ArrayAdapter<Top> {
    TopFragment fragment;
    ArrayList<Top> lists;
    private Context context;
    TextView tvSach,tvSL;

    public TopAdapter(@NonNull Context context,TopFragment fragment, ArrayList<Top> lists) {
        super(context, 0,lists);
        this.context=context;
        this.fragment=fragment;
        this.lists=lists;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View v = convertView;
        if (v==null){
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            v=inflater.inflate(R.layout.top_item,null);
        }
        final Top item = lists.get(position);
        if (item!=null){
            tvSach=v.findViewById(R.id.tvSachTop);
            tvSach.setText("Tên Sách: "+item.tenSach);
            tvSL=v.findViewById(R.id.tvSLTop);
            tvSL.setText("Số Lượng: "+item.soLuong);
        }
        return v;
    }
}
