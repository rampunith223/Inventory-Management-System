import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.*;

public class InventoryManagementSystem extends JFrame {

    private JTextField txtId, txtName, txtQuantity, txtPrice;
    private JButton btnAdd, btnUpdate, btnDelete, btnClear;
    private JTable table;
    private DefaultTableModel model;

    public InventoryManagementSystem() {

        setTitle("Inventory Management System");
        setSize(800, 500);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        // Input Panel
        JPanel panel = new JPanel(new GridLayout(5, 2, 10, 10));

        panel.add(new JLabel("Product ID"));
        txtId = new JTextField();
        panel.add(txtId);

        panel.add(new JLabel("Product Name"));
        txtName = new JTextField();
        panel.add(txtName);

        panel.add(new JLabel("Quantity"));
        txtQuantity = new JTextField();
        panel.add(txtQuantity);

        panel.add(new JLabel("Price"));
        txtPrice = new JTextField();
        panel.add(txtPrice);

        btnAdd = new JButton("Add");
        btnUpdate = new JButton("Update");

        panel.add(btnAdd);
        panel.add(btnUpdate);

        // Table
        String[] columns = {
                "Product ID",
                "Product Name",
                "Quantity",
                "Price"
        };

        model = new DefaultTableModel(columns, 0);
        table = new JTable(model);

        JScrollPane scrollPane = new JScrollPane(table);

        // Bottom Panel
        JPanel bottomPanel = new JPanel();

        btnDelete = new JButton("Delete");
        btnClear = new JButton("Clear");

        bottomPanel.add(btnDelete);
        bottomPanel.add(btnClear);

        add(panel, BorderLayout.NORTH);
        add(scrollPane, BorderLayout.CENTER);
        add(bottomPanel, BorderLayout.SOUTH);

        // Add Button
        btnAdd.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                addProduct();
            }
        });

        // Update Button
        btnUpdate.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                updateProduct();
            }
        });

        // Delete Button
        btnDelete.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                deleteProduct();
            }
        });

        // Clear Button
        btnClear.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                clearFields();
            }
        });

        // Table Row Selection
        table.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e) {

                int row = table.getSelectedRow();

                txtId.setText(model.getValueAt(row, 0).toString());
                txtName.setText(model.getValueAt(row, 1).toString());
                txtQuantity.setText(model.getValueAt(row, 2).toString());
                txtPrice.setText(model.getValueAt(row, 3).toString());
            }
        });

        setVisible(true);
    }

    private void addProduct() {

        if (txtId.getText().isEmpty() ||
            txtName.getText().isEmpty() ||
            txtQuantity.getText().isEmpty() ||
            txtPrice.getText().isEmpty()) {

            JOptionPane.showMessageDialog(this,
                    "Please fill all fields!");
            return;
        }

        model.addRow(new Object[] {
                txtId.getText(),
                txtName.getText(),
                txtQuantity.getText(),
                txtPrice.getText()
        });

        JOptionPane.showMessageDialog(this,
                "Product Added Successfully!");

        clearFields();
    }

    private void updateProduct() {

        int row = table.getSelectedRow();

        if (row == -1) {
            JOptionPane.showMessageDialog(this,
                    "Please select a row to update!");
            return;
        }

        model.setValueAt(txtId.getText(), row, 0);
        model.setValueAt(txtName.getText(), row, 1);
        model.setValueAt(txtQuantity.getText(), row, 2);
        model.setValueAt(txtPrice.getText(), row, 3);

        JOptionPane.showMessageDialog(this,
                "Product Updated Successfully!");

        clearFields();
    }

    private void deleteProduct() {

        int row = table.getSelectedRow();

        if (row == -1) {
            JOptionPane.showMessageDialog(this,
                    "Please select a row to delete!");
            return;
        }

        model.removeRow(row);

        JOptionPane.showMessageDialog(this,
                "Product Deleted Successfully!");

        clearFields();
    }

    private void clearFields() {
        txtId.setText("");
        txtName.setText("");
        txtQuantity.setText("");
        txtPrice.setText("");
        table.clearSelection();
    }

    public static void main(String[] args) {

        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                new InventoryManagementSystem();
            }
        });
    }
}