package com.example.asm_mob2041_phucngan.Fragment;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.example.asm_mob2041_phucngan.Adapter.TopAdapter;
import com.example.asm_mob2041_phucngan.DAO.ThongKeDAO;
import com.example.asm_mob2041_phucngan.R;
import com.example.asm_mob2041_phucngan.entity.Top;

import java.util.ArrayList;


public class TopFragment extends Fragment {
    ListView lv;
    ArrayList<Top> list;
    TopAdapter adapter;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v= inflater.inflate(R.layout.fragment_top, container, false);
        lv=v.findViewById(R.id.lvTop);
        ThongKeDAO thongKeDAO = new ThongKeDAO(getActivity());
        list = (ArrayList<Top>) thongKeDAO.getTop();
        adapter = new TopAdapter(getActivity(),this,list);
        lv.setAdapter(adapter);
        return v;
    }
}