package com.ootd.crowdpp;

import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;
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

import com.ootd.crowdpp.Retrofits.Result;
import com.ootd.crowdpp.Retrofits.RetrofitClient;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class RecyclerAdapter extends RecyclerView.Adapter<RecyclerAdapter.ViewHolder> {
    private ImageView image;
    private TextView textviewName;
    private TextView textviewIntroduction;
    private Button applyButton;
    private CardView cardView;
    ArrayList<CrowdData> CrowdDataArray = new ArrayList<>();

    String userId;
//    Context context;
    Context context;

    class ViewHolder extends RecyclerView.ViewHolder {


        ViewHolder(View view) {
            super(view);
            // Define click listener for the ViewHolder's View
            image = view.findViewById(R.id.item_image);
            textviewName = view.findViewById(R.id.item_name);
            textviewIntroduction = view.findViewById(R.id.item_introduction);
            applyButton = view.findViewById(R.id.applyButton);
            cardView = view.findViewById(R.id.card_view);
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
        SharedPreferences sharedPreferences = viewGroup.getContext().getSharedPreferences("userInfo", Context.MODE_PRIVATE);
        userId = sharedPreferences.getString("id", "");

        Button applyButton = view.findViewById(R.id.applyButton);
        applyButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(view.getContext(), "MESSI", Toast.LENGTH_SHORT).show();
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
        int crowdId = CrowdDataArray.get(position).getCrowdID();

        // 신청하기 버튼 누르면 서버 통신
        applyButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Call<Result> call;
                call = RetrofitClient.getApiService().applycrowd(userId, crowdId);
                call.enqueue(new Callback<Result>(){
                    @Override
                    public void onResponse(Call<Result> call, Response<Result> response) {
                        if (response.code() == 200) {
                            Result result = response.body();
                            String msg = result.getMsg();
                            if (msg.equals("success")) {
                                Toast.makeText(view.getContext(), "신청이 완료되었습니다", Toast.LENGTH_SHORT).show();
                            } else if (msg.equals("duplicated")) {
                                Toast.makeText(view.getContext(), "이미 신청되었습니다", Toast.LENGTH_SHORT).show();
                            } else {
                                Toast.makeText(view.getContext(), "신청 실패", Toast.LENGTH_SHORT).show();
                            }
                            System.out.println(msg);
                        } else {
                        }
                    }

                    @Override
                    public void onFailure(Call<Result> call, Throwable t) {
                        Log.e("request fail", t.getMessage());
                    }
                });
            }
        });

        // Crowd를 누르면 해당 Crowd의 멤버들 출력
        cardView.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                Intent intent = new Intent(context.getApplicationContext(), CrowdActivity.class);
                intent.putExtra("crowdId", CrowdDataArray.get(position).getCrowdID());
                context.startActivity(intent);
            }
        });
    }

    // Return the size of your dataset (invoked by the layout manager)
    @Override
    public int getItemCount() {
        return CrowdDataArray.size();
    }
}
