package com.company;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.util.*;

public class MainFrame extends JFrame {
    private TreeSet<Light> lights;
    private HashSet<String> colorsSet;
    private HashMap<Integer, String> hashMap;
    public final String INPUT_LED_LAMPS = "src//InputLED.txt";
    public final String INPUT_POWERSAVE_LAMPS = "src//InputPowersave.txt";

    public MainFrame() {
        super("Lights");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        pack();
        setLayout(new BorderLayout());
        setBounds(500, 200, 500, 500);

        colorsSet = new HashSet<>();
        hashMap = new HashMap<>();
        lights = new TreeSet<>();

        JTextArea lightsList = new JTextArea();
        lightsList.setEditable(false);

        JTextArea colors = new JTextArea();
        colors.setEditable(false);

        JTextArea powerList = new JTextArea();
        powerList.setEditable(false);

        JPanel listsPanel = new JPanel(new GridLayout(1, 3));
        listsPanel.add(lightsList);
        listsPanel.add(colors);
        listsPanel.add(powerList);
        this.add(listsPanel, BorderLayout.CENTER);

        JButton openFile = new JButton("Open");
        JRadioButton powersaveLampsInput = new JRadioButton("Powersave lamps");
        JRadioButton ledLampsInput = new JRadioButton("LED lamps");
        ButtonGroup buttonGroup = new ButtonGroup();
        buttonGroup.add(ledLampsInput);
        buttonGroup.add(powersaveLampsInput);
        ledLampsInput.setSelected(true);

        JPanel labelsPanel = new JPanel(new GridLayout(2, 3));
        labelsPanel.add(ledLampsInput);
        labelsPanel.add(openFile);
        labelsPanel.add(powersaveLampsInput);
        labelsPanel.add(new JLabel("List of lights"));
        labelsPanel.add(new JLabel("List of colors"));
        labelsPanel.add(new JLabel("List of manufacturers"));
        this.add(labelsPanel, BorderLayout.NORTH);

        JButton setPower = new JButton("Set power");
        JTextField getPower = new JTextField();
        JPanel buttonPanel = new JPanel(new BorderLayout());
        buttonPanel.add(setPower, BorderLayout.WEST);
        buttonPanel.add(getPower, BorderLayout.CENTER);
        this.add(buttonPanel, BorderLayout.SOUTH);

        openFile.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    Scanner scanner;
                    if (ledLampsInput.isSelected()) {
                        scanner = new Scanner(new File(INPUT_LED_LAMPS));
                    } else {
                        scanner = new Scanner(new File(INPUT_POWERSAVE_LAMPS));
                    }
                    while (scanner.hasNext()) {
                        StringTokenizer st = new StringTokenizer(scanner.nextLine(), " ");
                        if(ledLampsInput.isSelected()) {
                            LEDLamp ledLamp = new LEDLamp(st.nextToken(), st.nextToken(), Integer.parseInt(st.nextToken()), st.nextToken(), Integer.parseInt(st.nextToken()));
                            lights.add(ledLamp);
                            colorsSet.add(ledLamp.getColor());
                        } else {
                            PowersaveLamp pL = new PowersaveLamp(st.nextToken(), st.nextToken(), Integer.parseInt(st.nextToken()), st.nextToken(), st.nextToken());
                            lights.add(pL);
                            colorsSet.add(pL.getColor());
                        }
                    }
                    lightsList.setText("");
                    for (Light l : lights) {
                        lightsList.append(l.toString());
                        lightsList.append("\n");
                    }
                    colors.setText("");
                    for (String s : colorsSet) {
                        colors.append(s);
                        colors.append("\n");
                    }
                } catch (IOException exp) {
                    JOptionPane.showMessageDialog(null, "Opening error");
                }

            }
        });

        setPower.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (!getPower.getText().isEmpty()) {
                    try {
                        int power = Integer.parseInt(getPower.getText());
                        if(hashMap.containsKey(power)){
                            powerList.setText(hashMap.get(power));
                        } else {
                            HashSet<String> hashSet = new HashSet<>();
                            for (Light l : lights) {
                                if (power == l.getPower()) {
                                    hashSet.add(l.getManufacturer());
                                }
                            }
                            StringBuilder sb = new StringBuilder();
                            for (String s : hashSet) {
                                sb.append(s).append("\n");
                            }
                            hashMap.put(power, sb.toString());
                            powerList.setText(sb.toString());
                        }
                    } catch (NumberFormatException exp) {
                        JOptionPane.showMessageDialog(null, "Wrong power format");
                    }
                }
            }
        });
    }

    public static void main(String[] args){
        MainFrame mainFrame = new MainFrame();
        mainFrame.setVisible(true);
    }
}
