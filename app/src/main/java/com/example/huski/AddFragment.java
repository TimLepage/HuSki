package com.example.huski;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.util.SparseArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.google.android.gms.vision.barcode.Barcode;

import java.util.List;

import info.androidhive.barcode.BarcodeReader;


public class AddFragment extends Fragment implements BarcodeReader.BarcodeReaderListener {

    private BarcodeReader barcodeReader;
    private final String fragmentClass = "class com.example.huski.ListFragment";

    public static AddFragment newInstance() {
        return (new AddFragment());
    }

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }


    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_add, container, false);

        barcodeReader = (BarcodeReader) getChildFragmentManager().findFragmentById(R.id.barcode_scanner);
        barcodeReader.setListener(this);
        return v;
    }

    /**
     * get the barecord scanned and go back to ListFragment to display the list with the new scanned card
     * @param barcode barcode scanned by the user
     */
    @Override
    public void onScanned(final Barcode barcode) {
        ListFragment frag =(ListFragment) getActivity().getSupportFragmentManager().findFragmentByTag(fragmentClass);
        Bundle arguments = new Bundle();
        arguments.putString( "uuidCard" , barcode.displayValue);
        frag.setArguments(arguments);
        final FragmentTransaction ft = getFragmentManager().beginTransaction();
        ft.replace(R.id.fragment_view, frag);
        ft.commit();
    }


    @Override
    public void onScannedMultiple(List<Barcode> barcodes) {
        Toast.makeText(getContext(), "Only scan one QRcode please", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onBitmapScanned(SparseArray<Barcode> sparseArray) {

    }

    @Override
    public void onScanError(String errorMessage) {

    }

    @Override
    public void onCameraPermissionDenied() {
        Toast.makeText(getContext(), "Set permission for the camera", Toast.LENGTH_SHORT).show();
    }
}
