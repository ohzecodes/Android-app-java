package com.example.studentprogresstracking.UI.adpaters;

import static com.example.studentprogresstracking.UI.util.DatetoNiceString;
import static com.example.studentprogresstracking.UI.util.capitalize;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.studentprogresstracking.R;
import com.example.studentprogresstracking.UI.TERM.TermDetails;
import com.example.studentprogresstracking.entity.OneTerm;
import java.util.ArrayList;


public class TermAdpater  extends RecyclerView.Adapter<TermAdpater.termholder> {
        class termholder extends RecyclerView.ViewHolder {
                TextView Title, startdate,enddate;

                public termholder( @NonNull View itemView) {
                        super(itemView);
                        Title=itemView.findViewById(R.id.title);
                        startdate=itemView.findViewById(R.id.ETstartdate);
                        enddate=itemView.findViewById(R.id.ETEnddate);
                        itemView.setOnClickListener(e-> {
                                int position = getAdapterPosition();
                                OneTerm ClickedTerm =items.get(position);
//                                Toast.makeText(c, ClickedTerm.getTitle(), Toast.LENGTH_SHORT).show();
                                Intent i=new Intent(c, TermDetails.class);

                                i.putExtra("termid",ClickedTerm.getTermid());
                                i.putExtra("termStartDate",ClickedTerm.getStartDate());
                                i.putExtra("termEndDate",ClickedTerm.getEndDate());
                                i.putExtra("term_title",ClickedTerm.getTitle());

                                i.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                                c.startActivity(i);
                        });

                }
        }


        ArrayList<OneTerm> items;
        final Context c;
        final LayoutInflater inflater;
        public TermAdpater(Context c, ArrayList<OneTerm> items) {
                inflater=LayoutInflater.from(c);
                this.c = c;
                this.items = items;
        }

        @NonNull
        @Override
        public termholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
                return new termholder(inflater.inflate(R.layout.one_term,parent,false ));
        }

        @Override
        public void onBindViewHolder(@NonNull termholder holder, int position) {
                if (items != null) {
                        holder.Title.setText(capitalize(items.get(position).getTitle()));
                        holder.startdate.setText(DatetoNiceString(items.get(position).getStartDate()));
                        holder.enddate.setText(DatetoNiceString(items.get(position).getEndDate()));
                }else{
                        Toast.makeText(c, "No term Found ", Toast.LENGTH_LONG).show();
                }
        }

        @Override
        public int getItemCount() {
                return items.size();
        }

        @SuppressLint("NotifyDataSetChanged")
        public void setTerms(ArrayList<OneTerm> items) {
                this.items = items;
                notifyDataSetChanged();
        }
}
