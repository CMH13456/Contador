package com.PMDM.contador.utiles;

import android.annotation.SuppressLint;

import androidx.annotation.NonNull;

import java.math.BigDecimal;
import java.math.BigInteger;

public class CBigInteger extends BigInteger {

    public CBigInteger(@NonNull String val) {
        super(val);
    }

    @NonNull
    @Override
    // This override, and next overrides are just to avoid casting on the activities multiple times
    public CBigInteger add(@NonNull BigInteger val) {
        return new CBigInteger(super.add(val).toString());
    }

    @NonNull
    @Override
    public CBigInteger subtract(@NonNull BigInteger val) {
        return new CBigInteger(super.subtract(val).toString());
    }

    public static CBigInteger toCustomBigInteger(BigDecimal val) {
        return new CBigInteger(val.toBigInteger().toString());
    }

    public static CBigInteger toCustomBigInteger(BigInteger val) {
        return new CBigInteger(val.toString());
    }

    /**
     * Converts a BigInteger value to a formatted string which simplifies the reading
     * reducing the number to five digits at most and assigning it a numeric scale and a suffix
     *
     * @param msg the suffix being added
     * @return the formatted string
     */
    @SuppressLint("DefaultLocale")
    public String withSuffix(String msg) {
        if (this.compareTo(BigInteger.valueOf(1000)) < 0) {
            return String.format("%s%s", this, msg);
        }

        int exp = (int) (Math.log(this.doubleValue()) / Math.log(1000));

        String result;
        try {
            result = String.format("%.2f%c%s", this.doubleValue() / Math.pow(1000, exp), "kMGTPEZYRQ".charAt(exp - 1), msg);
        } catch (StringIndexOutOfBoundsException e) {
            result = "MAX";
        }
        return result;
    }
}

