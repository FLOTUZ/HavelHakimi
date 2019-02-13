package Llavel;

import javax.swing.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

class Venatana extends JDialog {
    private JPanel contentPane;
    private JButton agregarBoton;
    private JButton buttonCancel;
    private JLabel titulo;
    private JLabel instrucciones;
    private JTextField entrada;
    private JPanel panelSuperior;
    private JPanel panelDeBotones;
    private JPanel panelDeTitulo;
    private JLabel almacenados;
    private JTextArea areaRespuesta;
    private JButton CALCULARButton;
    private JPanel PanelReespuesta1;
    private JButton NUEVOButton;
    public static ArrayList <Integer> datos = new ArrayList<>();
    public Venatana() {
        setContentPane(contentPane);
        setModal(true);
        getRootPane().setDefaultButton(agregarBoton);
        agregarBoton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                onOK();
            }
        });

        buttonCancel.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                onCancel();
            }
        });

        // call onCancel() when cross is clicked
        setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
        addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                onCancel();
            }
        });

        // call onCancel() on ESCAPE
        contentPane.registerKeyboardAction(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                onCancel();
            }
        }, KeyStroke.getKeyStroke(KeyEvent.VK_ESCAPE, 0), JComponent.WHEN_ANCESTOR_OF_FOCUSED_COMPONENT);
        NUEVOButton.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent mouseEvent) {
                datos.clear();
                almacenados.setText("");
                areaRespuesta.setVisible(false);
                areaRespuesta.setText("");
            }
        });
        CALCULARButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                areaRespuesta.setVisible(true);
                operacion();
            }
        });
    }

    private void operacion() {
       try {
            ordenar();//Ordena los numeros con Collections
            int resta = del();  //Regresa el numero de veces a restar y elimina el primer nodo
            Integer resultado;
            for (int i = 0; i < resta; i++) {
                resultado = datos.get(i) - 1;
                datos.set(i, resultado);
            }
        if ((datos.isEmpty())) {} else {areaRespuesta.append(datos.toString() + " | Elimina nodo\n");}
        }catch (Exception e){areaRespuesta.append(" \n Es una secuencia imposible");}
        if ((datos.get(0) != 0)) {
            operacion();
        }else{
            areaRespuesta.append("\n Es una secuencia posible ");
        }
    }
    private int del() {
        int resta = datos.get(0);
        datos.remove(0);
        for (int i = 0; i <datos.size() ; i++) {
            if(datos.get(i) == 0) datos.remove(i);
        }
        return resta;
    }
    private void ordenar() {
        ArrayList <Integer> clon= new ArrayList<>();
        for (int i = 0; i < datos.size(); i++) {
            clon.add(datos.get(i));
        }
        Comparator<Integer> comparador = Collections.reverseOrder();
        Collections.sort(datos, comparador);
        if (datos.equals(clon)) {}
        else {areaRespuesta.append(datos.toString() + " | Ordenar\n");}
    }
    private void onOK() {
        llenado();
    }
    private void llenado() {
        try {
            int numero = Integer.parseInt(entrada.getText());
            datos.add(numero);
            almacenados.setText("");
            almacenados.setText(datos.toString());


            entrada.setText("");
        }catch (Exception e){
            almacenados.setText("Error! se ingresó dato incorrecto \n" +
                    " reinicie la entrada de datos");
        }
    }
    private void onCancel() {
        // add your code here if necessary
        dispose();
    }
    public static void main(String[] args) {
        Venatana dialog = new Venatana();
        dialog.pack();
        //dialog.setLocationRelativeTo(null);
        dialog.setAlwaysOnTop(true);
        dialog.setSize(700, 650);
        dialog.setLocation(400,70);
        dialog.setTitle("Havel Hakimi by Emmanuel Esquivel");
        dialog.setVisible(true);
        System.exit(0);
    }
}
