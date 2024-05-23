import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

abstract class Carro {
    private String marca;
    private String modelo;

    public Carro(String marca, String modelo) {
        this.marca = marca;
        this.modelo = modelo;
    }

    public String getMarca() {
        return marca;
    }

    public String getModelo() {
        return modelo;
    }

    public abstract void dirigir();
}

class HondaCivic extends Carro {
    public HondaCivic() {
        super("Honda", "Civic");
    }

    @Override
    public void dirigir() {
        JOptionPane.showMessageDialog(null, "Dirigindo um Honda Civic...");
    }
}

class ToyotaCorolla extends Carro {
    public ToyotaCorolla() {
        super("Toyota", "Corolla");
    }

    @Override
    public void dirigir() {
        JOptionPane.showMessageDialog(null, "Dirigindo um Toyota Corolla...");
    }
}

class CarroPersonalizado extends Carro {
    public CarroPersonalizado(String marca, String modelo) {
        super(marca, modelo);
    }

    @Override
    public void dirigir() {
        JOptionPane.showMessageDialog(null, "Dirigindo um carro personalizado: " + getMarca() + " " + getModelo());
    }
}

public class ListaCarrosGUI extends JFrame {
    private JList<String> listaCarros;
    private DefaultListModel<String> model;
    private List<Carro> listaDeCarros;

    public ListaCarrosGUI() {
        setTitle("Lista de Carros");
        setSize(300, 200);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        listaDeCarros = new ArrayList<>();
        model = new DefaultListModel<>();

        listaDeCarros.add(new HondaCivic());
        listaDeCarros.add(new ToyotaCorolla());

        for (Carro carro : listaDeCarros) {
            model.addElement(carro.getMarca() + " " + carro.getModelo());
        }

        listaCarros = new JList<>(model);
        JScrollPane scrollPane = new JScrollPane(listaCarros);

        JButton btnAdicionar = new JButton("Adicionar Carro");
        JButton btnRemover = new JButton("Remover Carro");
        JButton btnAlterar = new JButton("Alterar Carro");
        JButton btnSair = new JButton("Sair");

        btnAdicionar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String marca = JOptionPane.showInputDialog("Digite a marca do carro:");
                String modelo = JOptionPane.showInputDialog("Digite o modelo do carro:");
                Carro novoCarro = new CarroPersonalizado(marca, modelo);
                listaDeCarros.add(novoCarro);
                model.addElement(novoCarro.getMarca() + " " + novoCarro.getModelo());
            }
        });

        btnRemover.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int selectedIndex = listaCarros.getSelectedIndex();
                if (selectedIndex != -1) {
                    listaDeCarros.remove(selectedIndex);
                    model.remove(selectedIndex);
                }
            }
        });

        btnAlterar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int selectedIndex = listaCarros.getSelectedIndex();
                if (selectedIndex != -1) {
                    String novaMarca = JOptionPane.showInputDialog("Digite a nova marca do carro:");
                    String novoModelo = JOptionPane.showInputDialog("Digite o novo modelo do carro:");
                    Carro carroSelecionado = listaDeCarros.get(selectedIndex);
                    carroSelecionado = new CarroPersonalizado(novaMarca, novoModelo);
                    listaDeCarros.set(selectedIndex, carroSelecionado);
                    model.set(selectedIndex, carroSelecionado.getMarca() + " " + carroSelecionado.getModelo());
                }
            }
        });

        btnSair.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
            }
        });

        JPanel panelBotoes = new JPanel(new GridLayout(1, 4));
        panelBotoes.add(btnAdicionar);
        panelBotoes.add(btnRemover);
        panelBotoes.add(btnAlterar);
        panelBotoes.add(btnSair);

        JPanel panelPrincipal = new JPanel(new BorderLayout());
        panelPrincipal.add(scrollPane, BorderLayout.CENTER);
        panelPrincipal.add(panelBotoes, BorderLayout.SOUTH);

        add(panelPrincipal);

        setVisible(true);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(ListaCarrosGUI::new);
    }
}