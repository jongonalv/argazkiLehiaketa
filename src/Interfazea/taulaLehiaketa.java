package Interfazea;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellRenderer;

import DatuBasea.Konexioa;

import java.awt.Component;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;

// TODO: Auto-generated Javadoc
/**
 * The Class taulaLehiaketa.
 */
public class taulaLehiaketa extends Konexioa{

    /**
     * The Class TableInfo.
     */
    public static class taulaInfo {
        
        /** The table. */
        JTable table;
        
        /** The model. */
        DefaultTableModel model;
        
        /** The meta data. */
        ResultSetMetaData metaData;
        
        /** The stmt. */
        Statement stmt;
        
        /** The column count. */
        int columnCount;

        /**
         * Instantiates a new table info.
         *
         * @param table the table
         * @param model the model
         * @param metaData the meta data
         * @param columnCount the column count
         * @param stmt the stmt
         */
        public taulaInfo(JTable table, DefaultTableModel model, ResultSetMetaData metaData, int columnCount, Statement stmt) {
            this.table = table;
            this.model = model;
            this.metaData = metaData;
            this.columnCount = columnCount;
            this.stmt = stmt;
        }
        
        /**
         * Gets the table.
         *
         * @return the table
         */
        public JTable getTable() {
			return table;
		}
        
        /**
         * Gets the column count.
         *
         * @return the column count
         */
        public int getColumnCount() {
			return columnCount;
		}
        
        /**
         * Gets the meta data.
         *
         * @return the meta data
         */
        public ResultSetMetaData getMetaData() {
			return metaData;
		}
        
        /**
         * Gets the model.
         *
         * @return the model
         */
        public DefaultTableModel getModel() {
			return model;
		}       
        
        /**
         * Gets the stmt.
         *
         * @return the stmt
         */
        public Statement getStmt() {
			return stmt;
		}
    }

    /**
     * Taula bat sortzen du jTable erabiliz SQL query batenm informazio guztiarekin
     * Adibidez, SQL kontsulta lehiaketa guztiak erakusteko bada, taula bat sortuko du
     * Lehiaketa guztien informazioarekin.
     *
     * @return the table info
     * @throws SQLException the SQL exception
     */
    public static taulaInfo taulaSortu(String query) throws SQLException {
        
    	// Konexioa eta SQL querya exekutatu
        Connection con = getKonexioa();
        Statement stmt = con.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
        ResultSet rs = stmt.executeQuery(query);

        // Taulako celda guztiak editatzeko jarri
        DefaultTableModel model = new DefaultTableModel() {
            @Override
            public boolean isCellEditable(int row, int column) {
                return true;
            }
        };

        // Emaitzak hemen gorde
        ResultSetMetaData metaData = rs.getMetaData();
        
        // Kolumna kantitatea
        int columnCount = metaData.getColumnCount();
        
        // Gorako lerroan, izenak jarri (datuen izenak: izena, korreoa.. adibidez)
        String[] columnNames = new String[columnCount];
        for (int columnIndex = 1; columnIndex <= columnCount; columnIndex++) {
            columnNames[columnIndex - 1] = metaData.getColumnLabel(columnIndex);
            model.addColumn(metaData.getColumnLabel(columnIndex));
        }
        
        // Modeloari gehitu
        model.addRow(columnNames);

        // Emaitza guztiak taulan jarri, lerro eta kolumnak kontuan izanda
        while (rs.next()) {
            Object[] row = new Object[columnCount];
            for (int i = 1; i <= columnCount; i++) {
                row[i - 1] = rs.getObject(i);
            }
            model.addRow(row);
        }

        // Taula bat sortu modeloarekin
        JTable table = new JTable(model) {
            @Override
            
            // Goiko lerroa beltzez jarri
            public Component prepareRenderer(TableCellRenderer renderer, int row, int column) {
                Component component = super.prepareRenderer(renderer, row, column);

                if (convertRowIndexToModel(row) == 0) {
                    component.setFont(component.getFont().deriveFont(Font.BOLD));
                } else {
                    component.setFont(component.getFont().deriveFont(Font.PLAIN));
                }
                return component;
            }
        };

        return new taulaInfo(table, model, metaData, columnCount, stmt);
    }


    /**
     * Botoi gorde.
     *
     * @param tableInfo the table info
     * @return the j button
     */
    public static JButton botoiGorde(taulaInfo tableInfo, String update, String WHERE) {
    	
        JButton gordeButton = new JButton("Gorde aldaketak");
        gordeButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int rowCount = tableInfo.model.getRowCount();
                try {
                    // Lehenengo lerroti hasi, hasierako lerroa izenak dira eta.
                    for (int i = 1; i < rowCount; i++) {
                        StringBuilder queryBuilder = new StringBuilder(update);
                        for (int j = 1; j < tableInfo.columnCount; j++) {
                            String columnName = tableInfo.metaData.getColumnName(j + 1);
                            Object value = tableInfo.model.getValueAt(i, j);
                            if (j > 1) {
                                queryBuilder.append(", ");
                            }
                            queryBuilder.append(columnName).append(" = '").append(value).append("'");
                        }
                        queryBuilder.append(WHERE).append(tableInfo.getModel().getValueAt(i, 0));
                        String query = queryBuilder.toString();
                        tableInfo.getStmt().executeUpdate(query);
                    }
                    JOptionPane.showMessageDialog(null, "Datuak ondo gorde dira.");
                } catch (SQLException ex) {
                    ex.printStackTrace();
                }
            }
        });
        return gordeButton;
    }
}
