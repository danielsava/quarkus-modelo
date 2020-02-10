package base.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

public class CalendarUtil {

    public final static String DATA_INICIO = "01/01/1900";

    public static int calcularIdade(final Date nascimento) {

        final Calendar dataNascimento = Calendar.getInstance();

        dataNascimento.setTime(nascimento);

        final Calendar dataAtual = Calendar.getInstance();

        dataAtual.setTime(new Date(System.currentTimeMillis()));

        int idade = dataAtual.get(Calendar.YEAR) - dataNascimento.get(Calendar.YEAR);

        if (dataAtual.get(Calendar.MONTH) < dataNascimento.get(Calendar.MONTH)) {

            idade--;

        } else if (dataAtual.get(Calendar.MONTH) == dataNascimento.get(Calendar.MONTH)) {

            if (dataAtual.get(Calendar.DAY_OF_MONTH) < dataNascimento.get(Calendar.DAY_OF_MONTH)) {

                idade--;
            }
        }

        return idade;
    }

    public static int diferencaEmDias(final Calendar c1, final Calendar c2) {

        final long m1 = c1.getTimeInMillis();

        final long m2 = c2.getTimeInMillis();

        return (int) ( ( m2 - m1 ) / ( 24 * 60 * 60 * 1000 ) );
    }

    public static Calendar calendar(Date data) {

        Calendar calendar = Calendar.getInstance();

        calendar.setTime(data);

        return calendar;
    }

    public static Date hojeSemHora() {

        Calendar calendar = Calendar.getInstance();

        calendar.set(Calendar.HOUR_OF_DAY, 0);

        calendar.set(Calendar.MINUTE, 0);

        calendar.set(Calendar.SECOND, 0);

        calendar.set(Calendar.MILLISECOND, 0);

        return calendar.getTime();
    }

    public static Date hoje() {

        return new Date();
    }

    public static int diferencaEmAno(final GregorianCalendar dataInicial) {

        final GregorianCalendar hj = new GregorianCalendar();

        Calendar.getInstance();

        final int anohj = hj.get(Calendar.YEAR);

        final int anoInicial = dataInicial.get(Calendar.YEAR);

        return new Integer(anohj - anoInicial);
    }

    public static String converterDataParaModeloPadrao(final Date data) {

        try {

            if (data == null) {

                return new String("NADA CONSTA");
            }

            final SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");

            return format.format(data);

        } catch (final Exception e) {

            return null;
        }
    }

    public static String converterDataParaModeloReduzido(final Date data) {

        try {

            if (data == null) {

                return new String("NADA CONSTA");
            }

            final SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");

            return format.format(data);

        } catch (final Exception e) {

            return null;
        }
    }

    public static String obterHorarioDaData(final Date data) {

        try {

            if (data == null) {

                return null;
            }

            final SimpleDateFormat format = new SimpleDateFormat("HH:mm:ss");

            return format.format(data);

        } catch (final Exception e) {

            return null;
        }
    }

    public static String obterDiaSemanaParaData(final Date data) {

        final Calendar calendar = Calendar.getInstance();

        calendar.setTime(data);

        switch (calendar.get(Calendar.DAY_OF_WEEK)) {

            case Calendar.SUNDAY:
                return "Domingo";
            case Calendar.MONDAY:
                return "Segunda-Feira";
            case Calendar.TUESDAY:
                return "Ter�a-Feira";
            case Calendar.WEDNESDAY:
                return "Quarta-Feira";
            case Calendar.THURSDAY:
                return "Quinta-Feira";
            case Calendar.FRIDAY:
                return "Sexta-Feira";
            case Calendar.SATURDAY:
                return "S�bado";
            default:
                return "";
        }
    }

    public static int obterDiferencaEntreDatasEmAnos(final Date dataFim, final Date dataInicio) {

        final Calendar calendarDataInicio = Calendar.getInstance();

        final Calendar calendarDataFim = Calendar.getInstance();

        calendarDataInicio.setTime(dataInicio);

        calendarDataFim.setTime(dataFim);

        final int anoDataInicio = calendarDataInicio.get(Calendar.YEAR);

        final int anoDataFim = calendarDataFim.get(Calendar.YEAR);

        int diferencaAnos = anoDataFim - anoDataInicio;

        if (calendarDataInicio.get(Calendar.MONTH) > calendarDataFim.get(Calendar.MONTH)) {

            diferencaAnos--;

        } else if (calendarDataFim.get(Calendar.MONTH) == calendarDataInicio.get(Calendar.MONTH)) {

            if (calendarDataInicio.get(Calendar.DAY_OF_MONTH) > calendarDataFim.get(Calendar.DAY_OF_MONTH)) {

                diferencaAnos--;
            }
        }

        return diferencaAnos;
    }

    public static Date converterModeloPadraoParaData(final String modeloData) {

        try {

            if (modeloData == null) {

                return null;
            }

            final SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");

            return format.parse(modeloData);

        } catch (final Exception e) {

            return null;
        }
    }

    public static Boolean dataEstaEntreOPeriodo(final Date dataReferencia, final Date dataInicio, final Date dataFim) {

        final Calendar calendarDataReferencia = Calendar.getInstance();

        final Calendar calendarDataInicio = Calendar.getInstance();

        final Calendar calendarDataFim = Calendar.getInstance();

        calendarDataReferencia.setTime(dataReferencia);

        calendarDataInicio.setTime(dataInicio);

        calendarDataFim.setTime(dataFim);

        return ( calendarDataReferencia.compareTo(calendarDataInicio) >= 0 ) && ( calendarDataReferencia.compareTo(calendarDataFim) <= 0 );

    }

    public Boolean dataEhAnteriorAReferencia(final Date data, final Date dataReferencia) {

        final Calendar calendarDataReferencia = Calendar.getInstance();

        final Calendar calendarData = Calendar.getInstance();

        calendarDataReferencia.setTime(dataReferencia);

        calendarData.setTime(data);

        return ( data.compareTo(dataReferencia) < 0 );
    }

    public static Date obterUltimaHora(final Date dataAtual) {

        final GregorianCalendar gc = new GregorianCalendar();

        gc.setTime(dataAtual);

        gc.set(Calendar.HOUR_OF_DAY, 23);

        gc.set(Calendar.MINUTE, 59);

        gc.set(Calendar.SECOND, 59);

        gc.set(Calendar.MILLISECOND, 999);

        return gc.getTime();
    }

    public static Date obterPrimeiraHora(final Date dataAtual) {

        final GregorianCalendar gc = new GregorianCalendar();

        gc.setTime(dataAtual);

        gc.set(Calendar.HOUR_OF_DAY, 00);

        gc.set(Calendar.MINUTE, 00);

        gc.set(Calendar.SECOND, 00);

        gc.set(Calendar.MILLISECOND, 000);

        return gc.getTime();
    }

    public static Date obterDecimaSegundaHora(final Date dataAtual) {

        final GregorianCalendar gc = new GregorianCalendar();

        gc.setTime(dataAtual);

        gc.set(Calendar.HOUR_OF_DAY, 12);

        gc.set(Calendar.MINUTE, 00);

        gc.set(Calendar.SECOND, 00);

        gc.set(Calendar.MILLISECOND, 000);

        return gc.getTime();
    }

    public static Date obterDecimaOitavaHora(final Date dataAtual) {

        final GregorianCalendar gc = new GregorianCalendar();

        gc.setTime(dataAtual);

        gc.set(Calendar.HOUR_OF_DAY, 18);

        gc.set(Calendar.MINUTE, 00);

        gc.set(Calendar.SECOND, 00);

        gc.set(Calendar.MILLISECOND, 000);

        return gc.getTime();
    }

    public static boolean horaEstaNoPeriodoMatutino(final Date dataReferencia) {

        return CalendarUtil.dataEstaEntreOPeriodo(dataReferencia, CalendarUtil.obterPrimeiraHora(dataReferencia), CalendarUtil.obterDecimaSegundaHora(dataReferencia));
    }

    public static boolean horaEstaNoPeriodoVespertino(final Date dataReferencia) {

        return CalendarUtil.dataEstaEntreOPeriodo(dataReferencia, CalendarUtil.obterDecimaSegundaHora(dataReferencia), CalendarUtil.obterDecimaOitavaHora(dataReferencia));
    }

    public static boolean horaEstaNoPeriodoNoturno(final Date dataReferencia) {

        return CalendarUtil.dataEstaEntreOPeriodo(dataReferencia, CalendarUtil.obterDecimaOitavaHora(dataReferencia), CalendarUtil.obterUltimaHora(dataReferencia));
    }

    public static boolean anoAnterior2009(final Date dataReferencia) {

        final SimpleDateFormat df = new SimpleDateFormat("dd/MM/yyyy");

        final GregorianCalendar gc = new GregorianCalendar();

        gc.setTime(dataReferencia);

        try {

            if (dataReferencia.after(df.parse("31/12/2008"))) {

                return false;
            }

        } catch (final ParseException e) {

            e.printStackTrace();
        }

        return true;
    }

    public static Calendar converteDateEmCalendar(final Date data) {

        final Calendar calendar = Calendar.getInstance();

        calendar.setTime(data);

        return calendar;

    }

    public static Boolean dataMaiorDataAtual(final Date data) {

        final Calendar dt = Calendar.getInstance();
        dt.setTime(data);

        final Calendar dtHoje = Calendar.getInstance();
        dtHoje.setTime(CalendarUtil.DDMMYYYY());

        return dt.compareTo(dtHoje) > 0;
    }

    public static Boolean dataMaiorOuIgualDataAtual(final Date data) {

        final Calendar dt = Calendar.getInstance();
        dt.setTime(data);

        final Calendar dtHoje = Calendar.getInstance();
        dtHoje.setTime(CalendarUtil.DDMMYYYY());

        return dt.compareTo(dtHoje) >= 0;
    }

    public static Calendar adicionarDias(final Calendar data, final int quantidade) {

        data.add(Calendar.DATE, quantidade);

        return data;
    }

    public static Boolean isDiaUtil(final Calendar data) {

        return !( data.get(Calendar.DAY_OF_WEEK) == Calendar.SATURDAY ) && !( data.get(Calendar.DAY_OF_WEEK) == Calendar.SUNDAY );
    }

    public static Date DDMMYYYY() {

        final Calendar calendar = Calendar.getInstance();

        calendar.setTime(new Date());

        calendar.set(Calendar.HOUR, 0);

        calendar.set(Calendar.HOUR_OF_DAY, 0);

        calendar.set(Calendar.MINUTE, 0);

        calendar.set(Calendar.SECOND, 0);

        calendar.set(Calendar.MILLISECOND, 0);

        return calendar.getTime();
    }

    public static String obterDataAtualFormatadaModeloPadrao() {

        return CalendarUtil.converterDataParaModeloPadrao(new Date());
    }
}
