package com.example.thi11_8_ph26008.adapter;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.thi11_8_ph26008.R;
import com.example.thi11_8_ph26008.api.ApiService;
import com.example.thi11_8_ph26008.models.Spmodel;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SpAdapter  extends RecyclerView.Adapter<SpAdapter.SpViewHolder>{

    private List<Spmodel> list;

    public void setData(List<Spmodel> list) {
        this.list = list;
    }

    public SpAdapter(Context context) {
        this.context = context;
    }

    private Context context;

    private TextView tvChiTietName, tvChiTietDes,tvChiTietColor,tvChiTietPrice;
    private EditText edtName, edtDes, edtImage, edtColor, edtPrice;
    private Button btnCancle, btnUpdate;
    private ImageView tvChiTietImg;
    @NonNull
    @Override
    public SpViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_iteam, parent, false);
        return new SpViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull SpViewHolder holder, int position) {
        Spmodel spmodel = list.get(position);
        if(spmodel == null){
            return;
        }
        Glide.with(context).load(spmodel.getImage()).error(R.drawable.ic_launcher_background).into(holder.imgAvartar);
        holder.tvName.setText(spmodel.getName());
        holder.tvPrice.setText( ""+  spmodel.getPrice());
        holder.tvDescription.setText(spmodel.getDescription());

        holder.imgDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder =new AlertDialog.Builder(context);
                builder.setTitle("xoa iteam");
                builder.setMessage("ban co chac chan muon xoa iteam");
                builder.setPositiveButton("yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        ApiService.apiService.DeleteData(spmodel.getId()).enqueue(new Callback<Spmodel>() {
                            @Override
                            public void onResponse(Call<Spmodel> call, Response<Spmodel> response) {
                                list.remove(holder.getAdapterPosition());
                                notifyItemRemoved(holder.getAdapterPosition());
                                Toast.makeText(context, "xóa thành công ", Toast.LENGTH_SHORT).show();

                            }

                            @Override
                            public void onFailure(Call<Spmodel> call, Throwable t) {

                            }
                        });

                    }
                });
                builder.setNegativeButton("no", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }
                });



                builder.show();






            }
        });
        holder.imgAvartar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Dialog dialog = new Dialog(context);
                dialog.setContentView(R.layout.layout_chitiet);
                Window window = dialog.getWindow();
                window.setLayout(ViewGroup.LayoutParams.MATCH_PARENT,ViewGroup.LayoutParams.WRAP_CONTENT);
                tvChiTietName = dialog.findViewById(R.id.tvChiTietName);
                tvChiTietDes = dialog.findViewById(R.id.tvChiTietDes);
                tvChiTietImg = dialog.findViewById(R.id.tvChiTietImg);
                tvChiTietColor = dialog.findViewById(R.id.tvChiTietColor);
                tvChiTietPrice = dialog.findViewById(R.id.tvChiTietPrice);

                tvChiTietName.setText(spmodel.getName());
                tvChiTietDes.setText(spmodel.getDescription());
                Glide.with(context).load(spmodel.getImage()).error(R.drawable.ic_launcher_background).into(tvChiTietImg);
                tvChiTietColor.setText(spmodel.getColor());
                tvChiTietPrice.setText(""+ spmodel.getPrice());

                dialog.show();

            }
        });
        holder.imgUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Dialog dialog= new Dialog(context);
                dialog.setContentView(R.layout.layout_update);
                edtName = dialog.findViewById(R.id.edtName);
                edtDes = dialog.findViewById(R.id.edtDes);
                edtImage = dialog.findViewById(R.id.edtImage);
                edtColor = dialog.findViewById(R.id.edtColor);
                edtPrice = dialog.findViewById(R.id.edtPrice);

                btnUpdate = dialog.findViewById(R.id.btnUpdate);
                btnCancle = dialog.findViewById(R.id.btnCancle);
                edtName.setText(spmodel.getName());
                edtDes.setText(spmodel.getDescription());
                edtImage.setText(spmodel.getImage());
                edtColor.setText(spmodel.getColor());
                edtPrice.setText(""+spmodel.getPrice());
                btnCancle.setOnClickListener(view->dialog.dismiss());
                btnUpdate.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        String name = edtName.getText().toString().trim();
                        String des = edtDes.getText().toString().trim();
                        String image = edtImage.getText().toString().trim();
                        String color = edtColor.getText().toString().trim();
                        int price = Integer.parseInt(edtPrice.getText().toString().trim());

                        spmodel.setName(name);
                        spmodel.setDescription(des);
                        spmodel.setImage(image);
                        spmodel.setColor(color);
                        spmodel.setPrice(price);

                        ApiService.apiService.UpdateData(spmodel.getId(), spmodel).enqueue(new Callback<Spmodel>() {
                            @Override
                            public void onResponse(Call<Spmodel> call, Response<Spmodel> response) {
                                Toast.makeText(context, "Update Data Thanh Cong", Toast.LENGTH_SHORT).show();
                                notifyDataSetChanged();
                                dialog.dismiss();

                            }

                            @Override
                            public void onFailure(Call<Spmodel> call, Throwable t) {

                            }
                        });

                    }
                });

                dialog.show();


            }
        });

    }


    @Override
    public int getItemCount() {
        if(list != null ){
            return list.size();
        }
        return 0;
    }

    public class SpViewHolder extends RecyclerView.ViewHolder{

        private TextView tvName, tvPrice, tvDescription;
        private ImageView imgDelete, imgUpdate, imgAvartar;

        public SpViewHolder(@NonNull View itemView) {
            super(itemView);
            tvName = itemView.findViewById(R.id.tv_name);
            tvPrice = itemView.findViewById(R.id.tv_price);
            tvDescription = itemView.findViewById(R.id.tv_description);
            imgDelete = itemView.findViewById(R.id.img_delete);
            imgUpdate = itemView.findViewById(R.id.img_update);
            imgAvartar = itemView.findViewById(R.id.img_avatar);
        }
    }
}
