package com.example.javastudy.decimal;

import com.example.javastudy.exceptions.DecimalException;
import com.example.javastudy.storage.entity.DecimalEntity;

public class Decimal {

    private Double value;
    private String bin_form;

    public Double getDecForm() {
        return value;
    }

    public String getBinForm() {
        return bin_form;
    }

    public Decimal(Double value) {
        value = value;
        calcBin();
    }

    public Decimal(Double value, String bin_form) {
        if(Double.isNaN(value) || Double.isInfinite(value)){
            throw new DecimalException();
        }
        value = value;
        bin_form = bin_forn;
    }

    public Decimal(DecimalEntity ent) {
        if(Double.isNaN(ent.value()) || Double.isInfinite(ent.value())){
            throw new DecimalException();
        }
        value = ent.value();
        bin_form = ent.binary();
    }

    public void setValue(Double value) {
        if(Double.isNaN(value) || Double.isInfinite(value)){
            throw new DecimalException();
        }
        value = value;
        calcBin();
    }

    private void calcBin() {
        String binary = "";
        double copy = value;
        if (copy < 0) {
            copy = -copy;
            binary += '-';
        }
        binary += Integer.toBinaryString((int) (copy));
        copy -= (int) (copy);
        if (copy > 0) {
            binary += '.';
            int counter = 16; // точность
            while (copy != 0) {
                --counter;
                copy *= 2;
                if (copy >= 1) {
                    binary += '1';
                    copy -= 1;
                } else {
                    binary += '0';
                }
                if (counter == 0)
                    break;
            }
        }
        bin_form = binary;
    }
}
