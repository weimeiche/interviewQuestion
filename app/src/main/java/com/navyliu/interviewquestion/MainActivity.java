package com.navyliu.interviewquestion;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.navyliu.interviewquestion.NumberOf1Between1AndN.NumberOf1Between1AndN;
import com.navyliu.interviewquestion.mopPrime.MopPrimeActivity;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private RecyclerView recycler;
    private SpinnerAdapter spinnerAdapter;

    private String[] spinnerId = {"mop_prime", "contain_1_n", "word_inversion"};
    private String[] spinnerItem = {"1.猫扑素数有哪些"
            , "2.从1到n整数中1出现的次数"
            , "3.单词反转"};
    private ArrayList<SpinnerBean> mlist = new ArrayList<SpinnerBean>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recycler = (RecyclerView) this.findViewById(R.id.recycler);

        SpinnerBean spinnerBean;
        for (int i = 0; i < spinnerId.length; i++) {
            spinnerBean = new SpinnerBean();
            spinnerBean.setItemId(spinnerId[i])
                    .setItemStr(spinnerItem[i]);
            mlist.add(spinnerBean);
        }

        LinearLayoutManager layoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        spinnerAdapter = new SpinnerAdapter(this, mlist);
        recycler.setLayoutManager(layoutManager);
        recycler.setAdapter(spinnerAdapter);
        spinnerAdapter.setOnItemClickListener(new SpinnerAdapter.OnRecyclerItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                MainActivity.this.onItemClick(position);
            }
        });
        spinnerAdapter.notifyDataSetChanged();
    }


    private void onItemClick(int position) {
        String itemId = mlist.get(position).getItemId();
        System.out.println("position = [" + position + "]" + itemId);
        if (itemId.equals("mop_prime")) {
            // 猫扑素数
            Intent intent = new Intent(this, MopPrimeActivity.class);
            startActivity(intent);
            return;
        }
        if (itemId.equals("contain_1_n")){
            // 2.从1到n整数中1出现的次数
            Intent intent = new Intent(this, NumberOf1Between1AndN.class);
            startActivity(intent);
            return;
        }

    }

}
