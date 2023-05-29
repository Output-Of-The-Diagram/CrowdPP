package com.ootd.crowdpp;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class RecyclerAdapter extends RecyclerView.Adapter<RecyclerAdapter.ViewHolder> {
    ArrayList<CrowdData> CrowdDataArray = new ArrayList<>();
    Context context;

    class ViewHolder extends RecyclerView.ViewHolder {

        private ImageView image;
        private TextView textviewName;
        private TextView textviewIntroduction;

        ViewHolder(View view) {
            super(view);
            // Define click listener for the ViewHolder's View
            image = view.findViewById(R.id.item_image);
            textviewName = view.findViewById(R.id.item_name);
            textviewIntroduction = view.findViewById(R.id.item_introduction);
        }

        void onBind(CrowdData crowdData){
//            image.setImageResource(crowdData.getImage());
            textviewName.setText(crowdData.getName());
            textviewIntroduction.setText(crowdData.getIntroduction());
        }
    }
    void addItem(CrowdData crowdData) {
        // 외부에서 item을 추가시킬 함수입니다.
        CrowdDataArray.add(crowdData);
    }

    public RecyclerAdapter(ArrayList<CrowdData> CrowdDataArray, Context context) {
        this.CrowdDataArray = CrowdDataArray;
        this.context = context;
    }

    // Create new views (invoked by the layout manager)
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {
        // Create a new view, which defines the UI of the list item
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.card_crowd, viewGroup, false);

        Button applyButton = view.findViewById(R.id.applyButton);
        applyButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(view.getContext(), "MESSI", Toast.LENGTH_SHORT).show();
            }
        });

        // Crowd를 누르면 해당 Crowd의 멤버들 출력
        CardView cardView = view.findViewById(R.id.card_view);
        cardView.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                Intent intent = new Intent(context.getApplicationContext(), CrowdActivity.class);
                context.startActivity(intent);
            }
        });
        return new ViewHolder(view);
    }

    // Replace the contents of a view (invoked by the layout manager)
    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int position) {

        // Get element from your dataset at this position and replace the
        // contents of the view with that element
        viewHolder.onBind(CrowdDataArray.get(position));
    }

    // Return the size of your dataset (invoked by the layout manager)
    @Override
    public int getItemCount() {
        return CrowdDataArray.size();
    }
}

