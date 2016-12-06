package layout;


import android.content.Context;
import android.content.DialogInterface;
import android.content.res.AssetManager;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;

import com.leonus96.joseph.tablistview.ListViewAdapter;
import com.leonus96.joseph.tablistview.Multa;
import com.leonus96.joseph.tablistview.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;



public class fragmentList extends Fragment {
    // TODO: Rename and change types of parameters
    private static final String ARG_SECTION_NUMBER = "section_number";

    public fragmentList() {
        // Required empty public constructor
    }

    // TODO: Rename and change types and number of parameters
    public static fragmentList newInstance(int sectionNumber) {
        fragmentList fragment = new fragmentList();
        Bundle args = new Bundle();
        args.putInt(ARG_SECTION_NUMBER, sectionNumber);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.fragment_list, container, false);

        ListView listView = (ListView) rootView.findViewById(R.id.listaMultas);

        String json = loadJSONFromAssets("datos.json");


        final List<Multa> multasLeves =  convertJSONToObjects(json, 1);
        final List<Multa> multasGraves = convertJSONToObjects(json, 2);
        final List<Multa> multasMuyGraves = convertJSONToObjects(json, 3);

        ListViewAdapter adapterList;

        switch (getArguments().getInt(ARG_SECTION_NUMBER)) {
            case 1:
                adapterList = new ListViewAdapter(getActivity(), R.layout.list_item, multasLeves);
                listView.setAdapter(adapterList);
                listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> adapterView, View view, int position, long id) {
                        //Toast.makeText(getActivity(), "Click en " + multasLeves.get(position).getCodigo(), Toast.LENGTH_SHORT).show();
                        Multa currentMulta = multasLeves.get(position);
                        LayoutInflater inflater  = (LayoutInflater) getActivity().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
                        View dialogo = inflater.inflate(R.layout.dialogo, null);


                        AlertDialog dialog = createDialog(currentMulta, dialogo);
                        dialog.show();
                    }
                });
                break;
            case 2:
                adapterList = new ListViewAdapter(getActivity(), R.layout.list_item, multasGraves);
                listView.setAdapter(adapterList);
                listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> adapterView, View view, int position, long id) {
                        Multa currentMulta = multasGraves.get(position);
                        LayoutInflater inflater  = (LayoutInflater) getActivity().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
                        View dialogo = inflater.inflate(R.layout.dialogo, null);


                        AlertDialog dialog = createDialog(currentMulta, dialogo);
                        dialog.show();
                    }
                });
                break;
            case 3:
                adapterList = new ListViewAdapter(getActivity(), R.layout.list_item, multasMuyGraves);
                listView.setAdapter(adapterList);
                listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> adapterView, View view, int position, long id) {
                        Multa currentMulta = multasMuyGraves.get(position);
                        LayoutInflater inflater  = (LayoutInflater) getActivity().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
                        View dialogo = inflater.inflate(R.layout.dialogo, null);


                        AlertDialog dialog = createDialog(currentMulta, dialogo);
                        dialog.show();
                    }
                });
                break;
        }


        return rootView;
    }

    public List<Multa> convertJSONToObjects(String json, int tipo) {
        List<Multa> objetos = new ArrayList<Multa>();
        String calificacion = null;
        try {
            JSONObject obj = new JSONObject(json);
            if(tipo == 1) calificacion = "leves";
            if(tipo == 2) calificacion = "graves";
            if(tipo == 3) calificacion = "muy_graves";
            JSONArray json_array = obj.optJSONArray(calificacion);

            for (int i = 0; i < json_array.length(); i++) {
                objetos.add(new Multa(json_array.getJSONObject(i)));
            }

        } catch (JSONException e) {
            e.printStackTrace();
        }
        return objetos;
    }

    public String loadJSONFromAssets(String nombre) {
        String json = null;
        try {
            AssetManager am = getActivity().getAssets();
            InputStream is = am.open(nombre);
            int size = is.available();
            byte[] buffer = new byte[size];
            is.read(buffer);
            is.close();
            json = new String(buffer, "UTF-8");
        } catch (java.io.IOException ex) {
            ex.printStackTrace();
            return null;
        }
        return json;
    }

    public AlertDialog createDialog(Multa multa, View dialogo) {
        final AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        TextView infraccion = (TextView) dialogo.findViewById(R.id.infraccion);
        TextView monto = (TextView) dialogo.findViewById(R.id.monto);
        TextView uit = (TextView) dialogo.findViewById(R.id.uit);
        TextView puntos = (TextView) dialogo.findViewById(R.id.puntos);
        TextView descuento = (TextView) dialogo.findViewById(R.id.descuento);
        TextView medidadPreventiva = (TextView) dialogo.findViewById(R.id.medida_preventiva);

        infraccion.setText(multa.getInfraccion());
        monto.setText("S./ " + multa.getMonto() + "0");
        uit.setText(" ("+ multa.getMonto()*100/3950 + "% del UIT).");
        puntos.setText("+" + multa.getPuntos() + " puntos a su record.");
        if (!(multa.getMonto() == multa.getConDescuento())){
            descuento.setText("S./ " + multa.getConDescuento() + " (" +  multa.getConDescuento()*100/multa.getMonto() + "%) hasta en 5 dias.");
        }
        if(!multa.getMedidaPreventiva().equals("")){
            medidadPreventiva.setText(multa.getMedidaPreventiva());
        }else {
            medidadPreventiva.setVisibility(View.GONE);
        }
        builder
                .setTitle(multa.getCodigo())
                //.setMessage(multa.getInfraccion())
                .setView(dialogo)
                .setNegativeButton("CERRAR",
                        new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                            }
                        }).setCancelable(true);

        return builder.create();
    }


}
