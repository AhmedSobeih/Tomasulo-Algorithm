package tomasulo;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Vector;

public class GUI implements ActionListener {
    int cycle = 0;
    JLabel cycles = new JLabel("Cycle: " + cycle);

    JFrame frame;
    JButton goCycle = new JButton("Go to cycle");
    JButton next = new JButton("Next Cycle");
    JButton newInstruction = new JButton("+ Add instruction");

    JTextField gotoCycleField = new JTextField(20);

    Table instructionQueue;

    Vector<String> instructions = new Vector<>();
    ImagePanel panel;
    public GUI()
    {
        frame = new JFrame();
        BufferedImage myImage = null;
        try {
            myImage = ImageIO.read(new File("D:\\GUC S7\\Microprocessors\\Project\\src\\tomasulo\\background.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }

        frame.setSize(1500,800);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        panel = new ImagePanel(myImage);
        frame.getContentPane().add(panel);

        panel.setLayout(null);

        //add latency
        JLabel addLatency = new JLabel("Add latency");
        addLatency.setBounds(580,50,100,25);
        panel.add(addLatency);
        JTextField addLatencyText = new JTextField(20);
        addLatencyText.setBounds(650,50,50,25);
        panel.add(addLatencyText);

        //mul latency
        JLabel mulLatency = new JLabel("Multiply latency");
        mulLatency.setBounds(880,50,100,25);
        panel.add(mulLatency);
        JTextField mulLatencyText = new JTextField(20);
        mulLatencyText.setBounds(970,50,50,25);
        panel.add(mulLatencyText);

        //Sub latency
        JLabel subLatency = new JLabel("Sub latency");
        subLatency.setBounds(580,100,100,25);
        panel.add(subLatency);
        JTextField subLatencyText = new JTextField(20);
        subLatencyText.setBounds(650,100,50,25);
        panel.add(subLatencyText);

        //Div latency
        JLabel divLatency = new JLabel("Divide latency");
        divLatency.setBounds(880,100,100,25);
        panel.add(divLatency);
        JTextField divLatencyText = new JTextField(20);
        divLatencyText.setBounds(970,100,50,25);
        panel.add(divLatencyText);

        //Load latency
        JLabel loadLatency = new JLabel("Load latency");
        loadLatency.setBounds(575,150,100,25);
        panel.add(loadLatency);
        JTextField loadLatencyText = new JTextField(20);
        loadLatencyText.setBounds(650,150,50,25);
        panel.add(loadLatencyText);

        //store latency
        JLabel storeLatency = new JLabel("Store latency");
        storeLatency.setBounds(880,150,100,25);
        panel.add(storeLatency);
        JTextField storeLatencyText = new JTextField(20);
        storeLatencyText.setBounds(970,150,50,25);
        panel.add(storeLatencyText);


        //next cycle button
        next.setBounds(600,290,200,25);
        next.addActionListener(this);

        panel.add(next);

        //go to cycle button
        goCycle.setBounds(850,290,200,25);
        goCycle.addActionListener(this);
        panel.add(goCycle);
        gotoCycleField.setBounds(1070,290,50,25);
        panel.add(gotoCycleField);

        JLabel message1 = new JLabel("Please enter the instructions in this table in order of execution:");
        message1.setBounds(10,-80,500,200);
        message1.setFont(new Font("Serif", Font.BOLD, 18));
        panel.add(message1);

        JLabel message2 = new JLabel("Enter instructions in 'Inst', 'Dest', 'J', 'K' fields");
        message2.setBounds(10,-40,500,200);
        message2.setFont(new Font("Serif", Font.BOLD, 18));
        panel.add(message2);

        JLabel message3 = new JLabel("If it is load/store, type in 'inst', 'Dest', 'J' only.");
        message3.setBounds(10,0,500,200);
        message3.setFont(new Font("Serif", Font.BOLD, 18));
        panel.add(message3);

        //draw Instruction Queue table
        String[] InstructionColumnNames = {"Instruction", "Dest",
                "J",
                "K",
                "Issue",
                "Write Result"};

        newInstruction.setBounds(10,120,200,25);
        newInstruction.addActionListener(this);
        panel.add(newInstruction);


        int[]instructionQueueBounds = {10,150,500,200};
        instructionQueue =  drawTable(InstructionColumnNames,1,instructionQueueBounds,"");
        instructionQueue.setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 0));

        System.out.println(instructionQueue.getModel().getValueAt(0, 0));


       drawRegFile();

        cycles.setBounds(550,550,200,200);
        cycles.setFont(new Font("Serif", Font.BOLD, 34));
        panel.add(cycles);


        //drawing loadBuffer
        String[] loadBuffersColumnNames = {"Station","Busy",
                "Address",
        };
        int[] loadBuffersBounds = {50,400,200,150};
        drawTable(loadBuffersColumnNames,4,loadBuffersBounds, "L");

        String[] storeBuffersColumnNames = {"Station","Busy",
                "Address",
        };
        int[] storeBuffersBounds = {950,400,200,150};
        drawTable(storeBuffersColumnNames,4,storeBuffersBounds, "S");


        //drawing add stations
        String[] addStationColumnNames = {"Station","Busy","Op","Vj","Vk","Qj","Qk"};
        int[] addStationBounds = {50,600,350,120};
        drawTable(addStationColumnNames,3,addStationBounds,"A");

        //drawing mul stations
        String[] mulStationColumnNames = {"Station","Busy","Op","Vj","Vk","Qj","Qk"};
        int[] mulStationBounds = {800,600,350,90};
        drawTable(mulStationColumnNames,2,mulStationBounds, "M");
        frame.setVisible(true);


        //drawing labels of the tables
        JLabel loadBufferLabel = new JLabel("Load Stations");
        loadBufferLabel.setBounds(50,285,500,200);
        loadBufferLabel.setFont(new Font("Serif", Font.BOLD, 16));
        panel.add(loadBufferLabel);

        JLabel storeBufferLabel = new JLabel("Store Stations");
        storeBufferLabel.setBounds(950,285,500,200);
        storeBufferLabel.setFont(new Font("Serif", Font.BOLD, 16));
        panel.add(storeBufferLabel);

        JLabel addBufferLabel = new JLabel("Add Stations");
        addBufferLabel.setBounds(50,485,500,200);
        addBufferLabel.setFont(new Font("Serif", Font.BOLD, 16));
        panel.add(addBufferLabel);

        JLabel mulBufferLabel = new JLabel("Multiply Stations");
        mulBufferLabel.setBounds(800,485,500,200);
        mulBufferLabel.setFont(new Font("Serif", Font.BOLD, 16));
        panel.add(mulBufferLabel);
    }

    public Table drawTable(String[] columnNames, int rowsNumber, int[] bounds, String stationType)
    {

        String[][] tableData = new String[rowsNumber][columnNames.length];

        for(int i=0; i<rowsNumber; i++) {
            tableData[i][0] = stationType.equals("")?"":stationType+i;
            for (int j = 1; j < tableData[0].length; j++)
                tableData[i][j] = "";
        }

//        panel.add(loadBufferLabel);
        Table table = new Table(tableData, columnNames,bounds);
        table.setBounds(bounds[0],bounds[1],bounds[2],bounds[3]);



//        table.setBackground(Color.CYAN);
        panel.add(table);
        return table;
    }

    public JTable drawRegFile()
    {
        String[] RegFileColumnNames = {"Reg",
                "Qi",
        };
        int[] RegFileBounds = {1200,5,100,1000};
        Object[][] tableData = new Object[33][RegFileColumnNames.length];
        tableData[0]=RegFileColumnNames;

        for(int i=1; i<33; i++) {
            tableData[i][0]="F"+ (i-1);
            for (int j = 1; j < tableData[0].length; j++)
                tableData[i][j] = 0;
        }
        JLabel RegFileLabel = new JLabel("Register File");
        RegFileLabel.setBounds(RegFileBounds[0],RegFileBounds[1]-100,RegFileBounds[2],RegFileBounds[3]);
//        panel.add(loadBufferLabel);
        JTable RegFile = new JTable(tableData, RegFileColumnNames);
        RegFile.setBounds(RegFileBounds[0],RegFileBounds[1],RegFileBounds[2],RegFileBounds[3]);
        RegFile.setRowHeight(23);



//        RegFile.setRowHeight(15);

//        table.setBackground(Color.CYAN);
        panel.add(RegFile);
        return RegFile;
    }

    public void getInstructions()
    {
        for(int i=0; i<instructionQueue.getModel().getRowCount(); i++)
        {
            String op = (String) instructionQueue.getModel().getValueAt(i, 0);
            String dest = (String) instructionQueue.getModel().getValueAt(i, 1);
            dest = dest.substring(1,dest.length());
            String j = (String) instructionQueue.getModel().getValueAt(i, 2);
            j = j.substring(1,j.length());
            String k = (String) instructionQueue.getModel().getValueAt(i, 3);
            k = k.substring(1,k.length());

            switch (op)
            {
                case "ADD":
                    op="0";
                case "SUB":
                    op="1";
                case "MUL":
                    op="2";
                case "DIV":
                    op="3";
                case "L.D":
                    op="4";
                case "S.D":
                    op="5";
            }
            instructions.add(op + " " + dest + " " + j + " " + k);
        }
        System.out.println(instructions.toString());
    }
    public static void main(String[] args) {
        try {
            System.out.println(new File(".").getCanonicalPath());
        } catch (IOException ioException) {
            ioException.printStackTrace();
        }
        new GUI();
    }

    @Override
    public void actionPerformed(ActionEvent e) {


        if(e.getSource()==newInstruction) {
            DefaultTableModel model = (DefaultTableModel) instructionQueue.getModel();
            model.addRow(new Object[]{"", "", "", "", ""});
        }
        else if(e.getSource()==next)
        {
            cycles.setText("Cycle: " + ++cycle);
            getInstructions();
        }
        else if(e.getSource()==goCycle)
        {
            try {
                cycle=Integer.parseInt(gotoCycleField.getText());
                cycles.setText("Cycle: " + cycle);
            }
            catch(Exception ex)
            {

            }



        }
    }
}
