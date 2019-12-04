package br.com.isalvati.sistemaacademico.util;

import br.com.isalvati.sistemaacademico.exception.SistemaAcademicoException;
import io.micrometer.core.instrument.util.StringUtils;

import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;

public class Util {

    public static Timestamp convertDate(String date, String mask) throws SistemaAcademicoException {
        SimpleDateFormat sdf = new SimpleDateFormat(mask);
        Calendar c = Calendar.getInstance();
        if (StringUtils.isNotBlank(date) && StringUtils.isNotBlank(mask)) {
            try {
                c.setTime(sdf.parse(date));
            } catch (ParseException e) {
                e.printStackTrace();
                try {
                    sdf = new SimpleDateFormat("dd/MM/yyyy");
                    c.setTime(sdf.parse(date));
                } catch (Exception ex) {
                    ex.printStackTrace();
                    throw new SistemaAcademicoException("Erro ao converter data");
                }
            }
            return new Timestamp(c.getTimeInMillis());
        } else throw new SistemaAcademicoException("Erro ao converter data, parametro irregular.");
    }

    public static String dateConvert(Timestamp date, String mask) {
        SimpleDateFormat sdf = new SimpleDateFormat(mask);
        return sdf.format(date);
    }

}
