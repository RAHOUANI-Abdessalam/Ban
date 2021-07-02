package com.example.ban;

import android.app.Application;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.google.android.material.bottomsheet.BottomSheetDialog;

public class CategoriesFragment extends Fragment implements View.OnClickListener {
    CardView womenCard,menCard,phonesCard,pcCard,sportsCard,smartHomeCard,artCard,babytoyCard;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @NonNull ViewGroup container,
                             @NonNull Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v= inflater.inflate(R.layout.fragment_categories, container, false);

        womenCard = v.findViewById(R.id.womencardID);
        menCard = v.findViewById(R.id.mencardID);
        phonesCard = v.findViewById(R.id.phonecardID);
        pcCard = v.findViewById(R.id.pccardID);
        sportsCard = v.findViewById(R.id.sportscardID);
        smartHomeCard = v.findViewById(R.id.smarthomecardID);
        artCard = v.findViewById(R.id.artcardID);
        babytoyCard = v.findViewById(R.id.babytoycardID);

        womenCard.setOnClickListener(this);
        menCard.setOnClickListener(this);
        phonesCard.setOnClickListener(this);
        pcCard.setOnClickListener(this);
        sportsCard.setOnClickListener(this);
        smartHomeCard.setOnClickListener(this);
        artCard.setOnClickListener(this);
        babytoyCard.setOnClickListener(this);

        return v;
    }

    @Override
    public void onClick(View v) {
        TextView categoriesBottomText;

        BottomSheetDialog bottomSheetDialog = new BottomSheetDialog(
                getActivity(), R.style.BottomSheetDialogTheme
        );
        View bottomSheetView = LayoutInflater.from(getContext()).inflate(
                R.layout.categories_bottom_sheet,
                (LinearLayout) bottomSheetDialog.findViewById(R.id.bottomSheetContainer)
        );
        bottomSheetDialog.setContentView(bottomSheetView);
        categoriesBottomText= bottomSheetView.findViewById(R.id.categoriesBottomSheetTextId);
        switch (v.getId())
        {
            case R.id.womencardID :
                categoriesBottomText.setText("Women's Fashion");
                break;
            case R.id.mencardID:
                categoriesBottomText.setText("Men's Fashion");
                break;
            case R.id.phonecardID :
                categoriesBottomText.setText("Phons");
                break;
            case R.id.pccardID :
                categoriesBottomText.setText("PC");
                break;
            case R.id.sportscardID :
                categoriesBottomText.setText("Sports");
                break;
            case R.id.smarthomecardID :
                categoriesBottomText.setText("Smart Homes");
                break;
            case R.id.artcardID :
                categoriesBottomText.setText("Art");
                break;
            case R.id.babytoycardID :
                categoriesBottomText.setText("Baby Toys");
                break;
        }
        bottomSheetDialog.show();
    }
}