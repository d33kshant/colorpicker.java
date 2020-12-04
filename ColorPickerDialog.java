package com.company.app;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.SeekBar;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatDialogFragment;

public class ColorPickerDialog extends AppCompatDialogFragment {
    private int Red,Green,Blue;
    private SeekBar redSeekBar,greenSeekBar,blueSeekBar;
    private ImageView preview;
    private Button set,cancel;
    private setColorPickerListener listener;

    public ColorPickerDialog(int color) {
        Red = Color.red(color);
        Green = Color.green(color);
        Blue = Color.blue(color);
    }

    public interface setColorPickerListener{
        void applyColor(int color);
    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        listener = (setColorPickerListener) context;
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        View view = getActivity().getLayoutInflater().inflate(R.layout.layout_dialog_color_picker, null);

        set = view.findViewById(R.id.setPicker);
        cancel = view.findViewById(R.id.cancelPicker);
        preview = view.findViewById(R.id.previewColor);
        redSeekBar = view.findViewById(R.id.seekBarRed);
        greenSeekBar = view.findViewById(R.id.seekBarGreen);
        blueSeekBar = view.findViewById(R.id.seekBarBlue);

        preview.setColorFilter(Color.rgb(Red,Green,Blue));
        redSeekBar.setProgress(Red);
        greenSeekBar.setProgress(Green);
        blueSeekBar.setProgress(Blue);

        redSeekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                Red = progress;
                preview.setColorFilter(Color.rgb(Red,Green,Blue));
            }
            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {}
            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {}
        });

        greenSeekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                Green = progress;
                preview.setColorFilter(Color.rgb(Red,Green,Blue));
            }
            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {}
            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {}
        });

        blueSeekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                Blue = progress;
                preview.setColorFilter(Color.rgb(Red,Green,Blue));
            }
            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {}
            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {}
        });

        set.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listener.applyColor(Color.rgb(Red,Green,Blue));
                dismiss();
            }
        });

        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dismiss();
            }
        });

        builder.setView(view);
        return builder.create();
    }
}
