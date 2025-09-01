package com.example.demo.util;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class Util {
    public static String formatarCPF(String mCpf){
        mCpf = mCpf.replaceAll("[./-]", "");
        return mCpf.replaceAll("(\\d{3})(\\d{3})(\\d{3})(\\d{2})", "$1.$2.$3-$4");
    }

    public static LocalDate formatarData(String mDataEntrada){
        try {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
            return LocalDate.parse(mDataEntrada, formatter);
        } catch (DateTimeParseException e) {
            System.err.println("Data inválida: " + mDataEntrada);
            return null;
        }
    }
}
