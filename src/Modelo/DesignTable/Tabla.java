package Modelo.DesignTable;

import Modelo.Design.ScrollBarCustom;
import java.awt.Color;
import java.awt.Component;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import static javax.swing.SwingConstants.CENTER;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellRenderer;
import javax.swing.table.TableRowSorter;

public class Tabla extends JTable{
    
    int enFlujoDeCaja = 0;
    
    public Tabla() {
        initializeTable();
    }
    
    public Tabla(int enFlujoDeCaja) {
        this.enFlujoDeCaja = enFlujoDeCaja;
        initializeTable();
    }
    
    private void initializeTable() {
        setShowHorizontalLines(true);
        setGridColor(new Color(225,225,225));
        setRowHeight(30);
        getTableHeader().setReorderingAllowed(false);
        getTableHeader().setDefaultRenderer(new DefaultTableCellRenderer() {
            @Override
            public Component getTableCellRendererComponent(JTable jtable, Object o, boolean bln, boolean bln1, int i, int i1) {
                TablaHead header = new TablaHead(o + "");
                header.setHorizontalAlignment(JLabel.LEFT);
                return header;
            }
        });
        setDefaultRenderer(Object.class, new CustomTableCellRenderer());
    }
    
    public void fixTable(JScrollPane scroll) {
        scroll.getViewport().setBackground(Color.WHITE);
        scroll.setVerticalScrollBar(new ScrollBarCustom());
        JPanel p = new JPanel();
        p.setBackground(new Color(0,112,168));
        scroll.setCorner(JScrollPane.UPPER_RIGHT_CORNER, p);
        scroll.setBorder(new EmptyBorder(20, 20, 20, 20));
        scroll.setBorder(null);
        scroll.setViewportBorder(null);
        scroll.getViewport().setOpaque(false);
        scroll.setVerticalScrollBar(new ScrollBarCustom());
        scroll.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
    }
    
    @Override
    public Component prepareRenderer(TableCellRenderer renderer, int row, int col) {
        Component c = super.prepareRenderer(renderer, row, col);
        if(enFlujoDeCaja == 1 && "-".equals(getValueAt(row, 0))) {
            c.setBackground(new Color(0,112,168)); // Color for total columns.
            c.setForeground(Color.WHITE);
        }
        return c;
    }
    
    public void addRow(Object[] row) {
        DefaultTableModel mod = (DefaultTableModel) getModel();
        mod.addRow(row);
    }

    class CustomTableCellRenderer extends DefaultTableCellRenderer {
        @Override
        public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
            Component com = super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);

            if (isSelected) {
                com.setBackground(new Color(168,202,237));
            } else {
                if (row % 2 == 0) {
                    com.setBackground(new Color(250,250,250));
                } else {
                    com.setBackground(new Color(225,238,244));
                }
            }
            setBorder(new EmptyBorder(20, 20, 20, 20));  // Padding of 5 pixels on all sides
            com.setForeground(new Color(40,40,40));
            setHorizontalAlignment(LEFT);
            return com; 
        }
    }
}
