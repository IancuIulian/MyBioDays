import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MoreInfo {

    public MoreInfo(int width, int height){
        int moreInfoWidth = width - 200;
        int moreInfoHeight = height - 200;
        Font font = new Font("Serif", Font.PLAIN,20);
        JFrame moreInfoFrame = new JFrame("More Information");

        moreInfoFrame.setPreferredSize(new Dimension(moreInfoWidth,moreInfoHeight));
        moreInfoFrame.setLayout(new BorderLayout());
        moreInfoFrame.setResizable(false);

        JPanel panel = new JPanel();
        panel.setBounds(0, 0, moreInfoWidth, moreInfoHeight);
        panel.setBackground(new Color(204,153,255));
        panel.setOpaque(true);
        panel.setLayout(null);

        //Add 'more info' from file using InputStream
        ReadFromFile_is rff = new ReadFromFile_is("data/moreInfo.txt");
        String dataString = rff.getStringData();
        Method.populatePanel(panel, moreInfoWidth, moreInfoHeight, 204, 153, 255, dataString);


        JButton cancelButton = new JButton("Cancel");
        cancelButton.setBounds(moreInfoWidth/2-60,moreInfoHeight-70, 120, 25);
        cancelButton.setFont(font);
        moreInfoFrame.add(cancelButton);

        moreInfoFrame.add(panel, BorderLayout.CENTER);
        moreInfoFrame.pack();
        moreInfoFrame.setLocationRelativeTo(null);
        moreInfoFrame.getRootPane().setDefaultButton(cancelButton);
        moreInfoFrame.setVisible(true);

        cancelButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                moreInfoFrame.dispose();
            }
        });
    }
}
