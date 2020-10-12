package com.myproject.myapplication3;

import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class MyviewHolder extends RecyclerView.ViewHolder {

    TextView case_date;
    TextView case_time;
    TextView case_v_no;
    TextView case_details;
    View case_v;

    public MyviewHolder(@NonNull View itemView) {
        super(itemView);

        case_date =itemView.findViewById(R.id.case_date);
        case_time =itemView.findViewById(R.id.case_time);
        case_v_no =itemView.findViewById(R.id.case_vehicle_no);
        case_details =itemView.findViewById(R.id.case_data);

        case_v = itemView;
    }
}
