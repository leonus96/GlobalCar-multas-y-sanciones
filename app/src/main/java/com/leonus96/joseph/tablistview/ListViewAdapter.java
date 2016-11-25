package com.leonus96.joseph.tablistview;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.List;

public class ListViewAdapter extends BaseAdapter {
    private Context context;
    private int layout;
    private List<Multa> multas;

    public ListViewAdapter(Context context, int layout, List<Multa> multas){
        this.context = context;
        this.layout = layout;
        this.multas = multas;
    }

    @Override
    public int getCount() {
        return this.multas.size();
    }

    @Override
    public Object getItem(int position) {
        return this.multas.get(position);
    }

    @Override
    public long getItemId(int id) {
        return id;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup viewGroup) {
        View rootView = convertView;

        LayoutInflater layoutInflater = LayoutInflater.from(this.context);
        rootView = layoutInflater.inflate(layout, null);

        Multa currentMulta = multas.get(position);

        TextView codigo = (TextView) rootView.findViewById(R.id.TextViewCodigo);
        TextView descripcion = (TextView) rootView.findViewById(R.id.TextViewDescripcion);

        codigo.setText(currentMulta.getCodigo());
        descripcion.setText(currentMulta.getInfraccion());

        return rootView;
    }
}
