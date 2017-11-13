import javax.swing.*;
import java.awt.*;
import java.util.Calendar;

public class Method {

    static void infoBox(String titleBar, String infoMessage){
        JOptionPane.showMessageDialog(null, infoMessage, titleBar, JOptionPane.INFORMATION_MESSAGE);
    }
    static boolean validInput(JTextField tf, String dateString){
        String pattern = "[1-9](\\d)?\\D\\d(\\d)?\\D\\d\\d\\d\\d";
        if (!dateString.matches(pattern)) {
            tf.setForeground(Color.red);
            return false;
        }

        String separatorPattern = "\\D";
        String[] dayMonth = dateString.split(separatorPattern);
        int day = Integer.parseInt(dayMonth[0]);
        int month = Integer.parseInt(dayMonth[1]);
        if (day > 31 || month > 12 || month == 0) {
            tf.setForeground(Color.red);
            return false;
        }

        tf.setForeground(Color.black);
        return true;
    }
    static long dateInMillis(String dateString){
        Calendar date = Calendar.getInstance();

        //getting year, month, day values from user input
        String year = dateString.substring(dateString.length()-4);
        String month = dateString.substring(dateString.length()-7, dateString.length()-5);
        String day = dateString.substring(0,2);
        if (!Character.isDigit(month.charAt(0))){ month = month.substring(1,2); }
        if (!Character.isDigit(day.charAt(1))){ day = day.substring(0,1); }

        date.set(Calendar.DATE, Integer.parseInt(day));
        date.set(Calendar.MONTH, Integer.parseInt(month)-1);
        date.set(Calendar.YEAR, Integer.parseInt(year));
        date.set(Calendar.HOUR, 0);
        date.set(Calendar.MINUTE, 0);
        date.set(Calendar.SECOND, 0);
        date.set(Calendar.MILLISECOND, 0);

        return date.getTimeInMillis();
    }
    static void populatePanel(JPanel panel, int width, int height, int r, int g, int b, String dataString){
        JTextArea infoTextArea = new JTextArea();
        Font font = new Font("Serif", Font.PLAIN,20);
        infoTextArea.setBounds(20, 20, width-45, height-100);
        infoTextArea.setEditable(false);
        infoTextArea.setBackground(new Color(r,g,b));
        infoTextArea.setForeground(Color.black);
        infoTextArea.setFont(font);

        infoTextArea.setText(dataString);

        panel.add(infoTextArea);
    }
    static void addResultsToPanel(JPanel panel, int width, int height, JTextArea textArea, int numOfDays){
        Font font = new Font("Serif", Font.PLAIN,20);
        textArea.setFont(font);

        if (textArea.getText().equals("")){
            textArea.setBounds(20, 80, width-20, height-20);
            textArea.setEditable(false);
            textArea.setBackground(new Color(255, 180, 100));
            textArea.setForeground(Color.black);
        }

        int pDay = pDayOfCicle(numOfDays);
        int eDay = eDayOfCicle(numOfDays);
        int iDay = iDayOfCicle(numOfDays);

        String configString = pConfigSymbol(pDay) + " / "
                            + eConfigSymbol(eDay) + " / "
                            + iConfigSymbol(iDay);

        textArea.setText("Number of days: " + numOfDays + "\n\n"
                + "Physical cicle      - day " + pDay + " -\n"
                + "Emotional cicle   - day " + eDay + " -\n"
                + "Intelectual cicle   - day " + iDay + " -\n\n"
                + "Configuration:  " + configString
        );

        panel.add(textArea);
    }

    private static String pConfigSymbol(int dayNumber){
        if (dayNumber == 0 || dayNumber == 11 || dayNumber == 12){
            return "x";
        }else if (dayNumber < 11){
            return "+";
        }
        else{
            return "-";
        }
    }
    private static String eConfigSymbol(int dayNumber){
        if (dayNumber == 0 || dayNumber == 14){
            return "x";
        }else if (dayNumber < 14){
            return "+";
        }
        else{
            return "-";
        }
    }
    private static String iConfigSymbol(int dayNumber){
        if (dayNumber == 0 || dayNumber == 15 || dayNumber == 16){
            return "x";
        }else if (dayNumber < 15){
            return "+";
        }
        else{
            return "-";
        }
    }

    private static int pDayOfCicle(int numOfDays){
        return numOfDays % 22;
    }
    private static int eDayOfCicle(int numOfDays){
        return numOfDays % 28;
    }
    private static int iDayOfCicle(int numOfDays){
        return numOfDays % 33;
    }

}
