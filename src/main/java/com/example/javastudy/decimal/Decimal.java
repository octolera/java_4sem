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

    public Decimal(Double val) {
        value = val;
        calcBin();
    }

    public Decimal(Double val, String bin) {
        value = val;
        bin_form = bin;
    }

    public Decimal(DecimalEntity ent) {
        value = ent.value();
        bin_form = ent.binary();
    }

    public void setValue(Double val) {
        if(Double.isNaN(val) || Double.isInfinite(val)){
            throw new DecimalException();
        }
        value = val;
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
