package com.example.routetitanhomeassignment.fragments;

import android.os.Bundle;
import android.util.Log;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.routetitanhomeassignment.R;
import com.example.routetitanhomeassignment.api.Direction;
import com.example.routetitanhomeassignment.api.RestClient;
import com.example.routetitanhomeassignment.ui.main.Adresses;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ParentFragment extends Fragment {

    static List<Adresses> addressesList;


    public ParentFragment() {
        if (addressesList == null) {
            addressesList = new ArrayList<>();
            addressesList.add(new Adresses("1 Alek Manukyan St", 40.179912, 44.525087));
            addressesList.add(new Adresses("16 Sayat-Nova Avenue, 0001", 40.18182489088315, 44.52117313638205));
            addressesList.add(new Adresses("1 Opera playground, Freedom Square", 40.18540606992479, 44.51496211600948));
            addressesList.add(new Adresses("Paronyan street, 0002", 40.18577416229533, 44.50351542363983));
            addressesList.add(new Adresses("60 Marshal Baghramyan Avenue,", 40.19415479887871, 44.497398036983675));
            addressesList.add(new Adresses("3 Kievyan street", 40.193528969263845, 44.485615823578435));
            addressesList.add(new Adresses("2 Halabyan Street", 40.19154713871569, 44.478963946531174));
            addressesList.add(new Adresses("30 Leningradyan street,", 40.19144283050374, 44.473853119052826));
            addressesList.add(new Adresses("1 Azatutyan Avenue, ", 40.19903011335307, 44.51976278425748));
            addressesList.add(new Adresses("Cascade, Sarmen street, ", 40.19098089420896, 44.51565851464719));
            addressesList.add(new Adresses("21/1 Marshal Baghramyan avenue,", 40.19157694403375, 44.506412209375675));
            addressesList.add(new Adresses("13 Koryun street ", 40.188656245737654, 44.52303215606688));
            addressesList.add(new Adresses("42 Aram Street", 40.18019147400844, 44.51257641812871));
            addressesList.add(new Adresses("33/3 Khorenatsi street", 40.168804092082226, 44.513824865935284));
            addressesList.add(new Adresses("68/3 Teryan street, ", 40.188310527572355, 44.521666670698586));
            addressesList.add(new Adresses("7 Kajaznuni street, ", 40.16911414585448, 44.52018414252988));
            addressesList.add(new Adresses("23 Garegin Nzhdeh Street,", 40.152148845958266, 44.49399798213589));
            addressesList.add(new Adresses("9 Mesrop Mashtots Avenue,", 40.18079358976117, 44.50750460709995));
            addressesList.add(new Adresses("52 Leo street,", 40.17997686932223, 44.50285414386625));
            addressesList.add(new Adresses("13/2 Byuzand Street, ", 40.18032859961606, 44.509861048209395));

            addressesList.get(0).setType(1);
        }
    }
}
