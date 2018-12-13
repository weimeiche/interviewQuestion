package com.navyliu.interviewquestion;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;

import com.navyliu.interviewquestion.NumberOf1Between1AndN.NumberOf1Between1AndN;
import com.navyliu.interviewquestion.array_find.ArrayFind;
import com.navyliu.interviewquestion.mopPrime.MopPrimeActivity;
import com.navyliu.interviewquestion.replace_blank.ReplanceBlankActivity;
import com.navyliu.interviewquestion.reverse_word.ReverseWordActivity;
import com.navyliu.interviewquestion.single_ton.SingleTonActivity;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private RecyclerView recycler;
    private SpinnerAdapter spinnerAdapter;

    private ArrayList<SpinnerBean> mlist = new ArrayList<SpinnerBean>();
    private String[] spinnerId = {"mop_prime", "contain_1_n", "word_inversion", "single_ton"
            , "ArrayFind", "ReplanceBlankActivity"};
    private String[] spinnerItem = {"1.猫扑素数有哪些", "2.从1到n整数中1出现的次数", "3.单词反转"
            , "4.单例模式（SingleTon）", "5.二位数组中的查找", "6.替换空格"};
    private Class<?>[] activityArr = {MopPrimeActivity.class, NumberOf1Between1AndN.class, ReverseWordActivity.class
            , SingleTonActivity.class, ArrayFind.class, ReplanceBlankActivity.class};

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
        Intent intent = new Intent(this, activityArr[position]);
        startActivity(intent);
    }

}
