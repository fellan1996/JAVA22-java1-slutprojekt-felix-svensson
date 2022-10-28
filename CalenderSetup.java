package slutprojekt;

import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.time.DayOfWeek;
import java.time.LocalDate;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;

import java.util.ArrayList;

//contains everything needed in order to create the calender
class CalenderSetup {
	private static LocalDate today = LocalDate.now();
	private static DayOfWeek dayOfWeek = DayOfWeek.from(today);
	private static LocalDate date;

	private static Color darkerBlue = new Color(0, 0, 250, 150);
	private static Color blue = new Color(0, 0, 250, 75);

	private static GridLayout layoutOfPanels = new GridLayout();
	private static GridLayout layoutOfComponents = new GridLayout(8, 0);

	private static String[] week = { "Monday", "Tuesday", "Wednesday", "Thursday", "Friday", "Saturday", "Sunday" };
	private static String instructions = "Write here, to remove event... write only: -";

	private static ArrayList<JPanel> listOfPanels = new ArrayList<JPanel>();
	private static ArrayList<JTextArea> textAreaList;

	private static JFrame frame = new JFrame("Calender");
	private static JButton button;
	private static JTextArea textArea;

	// The method that creates the calender by using the methods listed below
	static void getWeek() {

		for (int i = 0; i < 7; i++) {

			frame.add(createPanel(i));
			listOfPanels.get(i).add(createDayOfWeekLabel(i));
			listOfPanels.get(i).add(createDateLabel(i));
			textAreaList = new ArrayList<JTextArea>();
			for (int j = 0; j < 4; j++) {
				listOfPanels.get(i).add(createGrayTextArea());
			}
			listOfPanels.get(i).add(createLastTextArea(i));
			listOfPanels.get(i).add(createButton(i));

			highlightToday(i);
			eventAdder(button, textAreaList, textArea, darkerBlue);
			textAreaMouseListener(textArea);
		}
		frameSettings();
	}

	private static JPanel createPanel(int i) {
		JPanel panel = new JPanel();
		panel.setBorder(BorderFactory.createMatteBorder(10, 10, 10, 10, blue));
		panel.setLayout(layoutOfComponents);
		listOfPanels.add(panel);
		return listOfPanels.get(i);

	}

	private static JLabel createDayOfWeekLabel(int i) {
		JLabel dayOfWeekLabel = new JLabel(week[i]);
		return dayOfWeekLabel;

	}

	private static JLabel createDateLabel(int i) {
		date = today.plusDays(i + 1 - dayOfWeek.getValue());
		JLabel dateLabel = new JLabel(date.getMonth() + " " + date.getDayOfMonth());
		return dateLabel;

	}

	private static JTextArea createGrayTextArea() {
		textArea = new JTextArea();
		textAreaList.add(textArea);
		textArea.setBackground(Color.lightGray);
		textArea.setBorder(BorderFactory.createMatteBorder(1, 1, 1, 1, Color.GRAY));
		textArea.setEditable(false);
		textArea.setLineWrap(true);
		textArea.setWrapStyleWord(true);
		return textArea;

	}

//Creates the white texArea
	private static JTextArea createLastTextArea(int i) {
		textArea = new JTextArea(instructions);
		textArea.setLineWrap(true);
		textArea.setWrapStyleWord(true);
		return textArea;

	}

	private static JButton createButton(int i) {
		button = new JButton("add event");
		button.setFont(button.getFont().deriveFont(0, 11f));
		return button;
	}

	private static void highlightToday(int i) {
		if (date == today) {
			listOfPanels.get(i).setBorder(BorderFactory.createMatteBorder(10, 10, 10, 10, darkerBlue));
		}
	}

	private static void eventAdder(JButton button, ArrayList<JTextArea> textAreaList, JTextArea textArea,
			Color darkerBlue) {
		ActionListener buttonListener = new ActionListener() {
			int count = 0;

			@Override
			public void actionPerformed(ActionEvent e) {
				textAreaList.get(count).setBorder(BorderFactory.createMatteBorder(1, 1, 1, 1, Color.GRAY));
				if (textArea.getText().equals("")) {
				}else if(textArea.getText().equals("-")) {
					textAreaList.get(count).setText("");
				}else if(!textArea.getText().equals(instructions))
					textAreaList.get(count).setText(textArea.getText());
				textArea.setText(instructions);
				count = count == 3 ? count -= 3 : ++count;
				textAreaList.get(count).setBorder(BorderFactory.createMatteBorder(0, 2, 0, 2, darkerBlue));
			}
		};
		button.addActionListener(buttonListener);
	}

	private static void textAreaMouseListener(JTextArea textArea) {
		MouseAdapter textAreaListener = new MouseAdapter() {

			@Override
			public void mousePressed(MouseEvent e) {
				if (textArea.getText().equals(instructions)) {
					textArea.setText("");
				}
			}
		};
		textArea.addMouseListener(textAreaListener);
	}

	private static void frameSettings() {
		frame.pack();
		frame.setSize(770, 500);
		frame.setLayout(layoutOfPanels);
		frame.setVisible(true);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

	}
}
