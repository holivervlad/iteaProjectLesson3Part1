package main;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;

public class MyFrame extends JFrame {
    private static MyFrameOverlay myFrameOverlay = new MyFrameOverlay();
    private static final String GERMAN_FLAG_IMAGE_NAME = "FLAG_OF_GERMAN.png";
    private static final String AMERICAN_FLAG_IMAGE_NAME = "FLAG_OF_USA.png";
    private static final String UKRAINE_FLAG_IMAGE_NAME = "FLAG_OF_UKRAINE.png";

    private String firstLabelString = "Це";
    private String secondLabelString = " э ";
    private String thirdLabelString = "украiнська";

    private JLabel firstLabel = new JLabel(firstLabelString);
    private JLabel secondLabel = new JLabel(secondLabelString);
    private JLabel thirdLabel = new JLabel(thirdLabelString);

    private JButton saveButton, loadButton, button;
    private JComponent ukraineFlag, germanFlag, americanFlag;

    private String commonImagePath = "/Users/vladyslavholiver/Documents/itea/src/main/resources";
    private String filePath = commonImagePath + "/data.txt";

    MyFrame() {
        super("ITEA");
        setLayout(new GridLayout(3, 3));
        addDescriptionToLayout();
        changeDescriptionToLayout();
        addButtonsToLayout();
        setSize(1000, 1000);
        setVisible(true);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
    }

    private void addButtonsToLayout() {
        saveButton = createSaveButton();
        loadButton = createLoadButton();
        add(saveButton);
        add(loadButton);
    }

    private void addDescriptionToLayout() {
        ukraineFlag = addUkraineFlag();
        germanFlag = addGermanFlag();
        americanFlag = addAmericanFlag();

        add(ukraineFlag);
        add(germanFlag);
        add(americanFlag);
    }

    private void changeDescriptionToLayout() {
        add(firstLabel);
        add(secondLabel);
        add(thirdLabel);
    }

    public void setTextToComponents() {
        firstLabel.setText(firstLabelString);
        secondLabel.setText(secondLabelString);
        thirdLabel.setText(thirdLabelString);
    }

    private JButton addGermanFlag() {
        button = myFrameOverlay.addImageToLayOut(GERMAN_FLAG_IMAGE_NAME);
        button.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                firstLabelString = "Das";
                secondLabelString = " ist ";
                thirdLabelString = "deutsch";
                setTextToComponents();
            }
        });
        return button;
    }

    private JButton addAmericanFlag() {
        button = myFrameOverlay.addImageToLayOut(AMERICAN_FLAG_IMAGE_NAME);
        button.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                firstLabelString = "This";
                secondLabelString = " is ";
                thirdLabelString = "american";
                setTextToComponents();
            }
        });
        return button;
    }

    private JButton addUkraineFlag() {
        button = myFrameOverlay.addImageToLayOut(UKRAINE_FLAG_IMAGE_NAME);
            button.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    firstLabelString = "Це";
                    secondLabelString = " э ";
                    thirdLabelString = "украiнська";
                    setTextToComponents();
                }
            });
        return button;
    }

    public JButton createSaveButton() {
        JButton button = new JButton("Save");
        button.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent e) {
                try {
                    DataOutputStream outputStream = new DataOutputStream(new FileOutputStream(filePath));
                    outputStream.writeBytes(firstLabelString);
                    outputStream.writeBytes(secondLabelString);
                    outputStream.writeBytes(thirdLabelString);

                    System.out.println(String.format("File is written successfully with values: '%s', '%s', '%s'", firstLabelString, secondLabelString, thirdLabelString));
                } catch (IOException j) {
                    j.printStackTrace();
                }
            }
        });
        return button;
    }

    public JButton createLoadButton() {
        JButton button = new JButton("Load");
        button.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent e) {
                try {
                    File file = new File(filePath);
                    FileInputStream fileInputStream = new FileInputStream(file);
                    byte[] data = new byte[fileInputStream.available()];
                    fileInputStream.read(data);
                    fileInputStream.close();
                    String str = new String(data);
                    String[] values = str.split(" ");
                    if (values.length > 0) {
                        System.out.println("File is read successfully");
                    }
                    firstLabel.setText(values[0]);
                    secondLabel.setText(values[1]);
                    thirdLabel.setText(values[2]);

                    System.out.println("Previous saved values are set successfully");

                } catch (IOException j) {
                    System.out.println("File is empty");
                }
            }
        });
        return button;
    }
}
