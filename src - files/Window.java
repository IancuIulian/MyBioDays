import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Window {
    private int width = 700;
    private int height = 700;

    public Window(){
        JFrame frame = new JFrame("My Biorhythmic Days");
        frame.setPreferredSize(new Dimension(width, height));
        frame.setResizable(false);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(new BorderLayout());

        Font font = new Font("Serif", Font.PLAIN,20);

        JLayeredPane layeredPanel = new JLayeredPane();
        layeredPanel.setBounds(0, 0, width, height);

        //Input Panel  -  getting data from user
        JPanel inputPanel = new JPanel();
        int inputPanelWidth = width/2;
        int inputPanelHeight = (int)(height/(2.6));
        inputPanel.setBounds(0, 0, inputPanelWidth, inputPanelHeight);
        inputPanel.setBackground(new Color(135,255,255));
        inputPanel.setOpaque(true);
        inputPanel.setLayout(null);

        JLabel birthday = new JLabel("Birth Date (dd/mm/yyyy): ");
        birthday.setLocation(20,20);
        birthday.setSize(250,25);

        JTextField birthday_tf = new JTextField();
        birthday_tf.setBounds(20, 55, 130, 25);

        JLabel currDate = new JLabel("Current Date (dd/mm/yyyy): ");
        currDate.setLocation(20,100);
        currDate.setSize(250,25);

        JTextField currDate_tf = new JTextField();
        currDate_tf.setBounds(20, 135, 130, 25);

        JButton today = new JButton("Today");
        today.setBounds(160,135, 90, 25);

        JButton calculate = new JButton("Calculate");
        calculate.setBounds(20,195, 130, 25);

        birthday.setFont(font);
        birthday_tf.setFont(font);
        currDate.setFont(font);
        currDate_tf.setFont(font);
        today.setFont(font);
        calculate.setFont(font);

        inputPanel.add(birthday);
        inputPanel.add(birthday_tf);
        inputPanel.add(currDate);
        inputPanel.add(currDate_tf);
        inputPanel.add(today);
        inputPanel.add(calculate);

        // Information panel  -  display data from a text file
        JPanel infoPanel = new JPanel();
        int infoPanelWidth = width/2;
        int infoPanelHeight = height;
        infoPanel.setBounds(width/2, 0, infoPanelWidth, infoPanelHeight);
        infoPanel.setBackground(new Color(255, 255, 85));
        infoPanel.setOpaque(true);
        infoPanel.setLayout(null);

        //Add cicles information from file using InputStream
        ReadFromFile_is rff = new ReadFromFile_is("data/ciclesInfo.txt");
        String dataString = rff.getStringData();
        Method.populatePanel(infoPanel, infoPanelWidth, infoPanelHeight, 255, 255, 85, dataString);

        JButton moreInfoButton = new JButton("More Info");
        moreInfoButton.setBounds(infoPanelWidth-145,infoPanelHeight-70, 120, 25);
        moreInfoButton.setFont(font);
        infoPanel.add(moreInfoButton);

        //Result panel  -  display results
        JPanel resultPanel = new JPanel();
        int resultPanelWidth = width/2;
        int resultPanelHeight = height - inputPanelHeight;
        resultPanel.setBounds(0, height - resultPanelHeight, resultPanelWidth, resultPanelHeight);
        resultPanel.setBackground(new Color(255, 180, 100));
        resultPanel.setOpaque(true);
        resultPanel.setLayout(null);

        JLabel resultLabel = new JLabel("Result:");
        JTextArea resultTextArea = new JTextArea();
        resultLabel.setLocation(20,30);
        resultLabel.setSize(250,25);
        resultLabel.setFont(font);
        resultPanel.add(resultLabel);

        JButton exitButton = new JButton("Exit");
        exitButton.setBounds(20,resultPanelHeight-70, 90, 25);
        exitButton.setFont(font);

        resultPanel.add(exitButton);


        layeredPanel.add(inputPanel);
        layeredPanel.add(infoPanel);
        layeredPanel.add(resultPanel);

        frame.add(layeredPanel, BorderLayout.CENTER);
        frame.getRootPane().setDefaultButton(calculate);
        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);

        today.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
                Date date = new Date();
                currDate_tf.setText(dateFormat.format(date));
            }
        });
        calculate.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String birthdayTextValue = birthday_tf.getText();
                String currentDateTextValue = currDate_tf.getText();

                if (Method.validInput(birthday_tf, birthdayTextValue)
                  & Method.validInput(currDate_tf, currentDateTextValue) )
                {
                    long timeInMillis = Method.dateInMillis(currentDateTextValue) - Method.dateInMillis(birthdayTextValue);
                    long timeInDays = timeInMillis / 1000 / 60 / 60 / 24;

                    Method.addResultsToPanel(resultPanel, resultPanelWidth, resultPanelHeight, resultTextArea, (int)timeInDays);
                    resultPanel.revalidate();
                    resultPanel.repaint();
                }
                else{
                    Method.infoBox("Something went wrong :(", "Wrong date format!");
                }
            }
        });
        moreInfoButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new MoreInfo(width, height);
            }
        });
        exitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });
    }
}
