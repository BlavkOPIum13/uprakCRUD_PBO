/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;


import controller.controller_aset;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.MessageFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import view.Asset;

/**
 *
 * @author user
 */
public class model_aset implements controller_aset{

    @Override
    public void Input(Asset aset) throws SQLException {
       
        try {
            Connection con = koneksi.Koneksi.getKoneksi();
           
            String sql = "insert into aset values(?, ?, ?, ?, ?, ?, ?)";
            PreparedStatement prepare= con.prepareStatement(sql);
            
            prepare.setString(1, aset.txtId.getText());
            prepare.setString(2, aset.txtAsset.getText());
            prepare.setString(3, aset.txtQuantity.getText());
            prepare.setString(4, aset.date.getDateFormatString());
            prepare.setString(5, (String)aset.cbCondition.getSelectedItem());
            prepare.setString(6, aset.txtPhoto.getText());
            prepare.setString(7, aset.txtPrice.getText());
            prepare.executeUpdate();
            
            JOptionPane.showMessageDialog(null, "Data Berhasil di Input");
        } catch (Exception e) {
            System.out.println(e);
            JOptionPane.showMessageDialog(null, "Data Gagal di input" +e);
        }
        finally{
            Tampil(aset);
            aset.setLebarKolom();
        }
    }

    @Override
    public void Edit(Asset aset) throws SQLException {
        try {
            Connection con = koneksi.Koneksi.getKoneksi();
            String sql = "update aset set asset= ?, quantity= ?, date= ?, condition= ?, photo= ?, price= ? where id_asset= ?";
            PreparedStatement prepare = con.prepareStatement(sql);
            
            prepare.setString(7, aset.txtId.getText());
            prepare.setString(1, aset.txtAsset.getText());
            prepare.setString(2, aset.txtQuantity.getText());
            prepare.setString(3, aset.date.getDateFormatString());
            prepare.setString(4, (String)aset.cbCondition.getSelectedItem());
            prepare.setString(5, aset.txtPhoto.getText());
            prepare.setString(6, aset.txtPrice.getText());
            prepare.executeUpdate();
                
            JOptionPane.showMessageDialog(null, "data berhasil di ubah");
        } catch (Exception e) {
            System.out.println(e);
        }
        finally{
            Tampil(aset);
            aset.setLebarKolom();
            Reset(aset);
        }
    }

    @Override
    public void Reset(Asset aset) throws SQLException {
        aset.txtId.setText("");
        aset.txtAsset.setText("");
        aset.txtQuantity.setText("");
        aset.date.setDateFormatString("");
        aset.cbCondition.setSelectedIndex(0);
        aset.txtPhoto.setText("");
        aset.txtPrice.setText("");
        
    }

    @Override
    public void Delete(Asset aset) throws SQLException {
        try {
            Connection con = koneksi.Koneksi.getKoneksi();
            String sql = "delete from aset where Id_aset= ?";
            PreparedStatement prepare = con.prepareStatement(sql);
            
            prepare.setString(1, aset.txtId.getText());
            prepare.executeUpdate();
            
            JOptionPane.showMessageDialog(null, "Data Berhasil di Hapus");
            prepare.close();
            
        } catch (Exception e) {
            System.out.println(e);
        }
        finally{
            Tampil(aset);
            aset.setLebarKolom();
            Reset(aset);
        }
    }

    @Override
    public void Save(Asset aset) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void Print(Asset aset) throws SQLException {
        MessageFormat header = new MessageFormat("Inventory Management System");
        MessageFormat footer = new MessageFormat("page-{0}");
        try {
            aset.tbl.print(JTable.PrintMode.FIT_WIDTH, header, footer);
        } catch (java.awt.print.PrinterException e) {
            System.err.format("cannot print %$%n", e.getMessage());
        }
    }

    @Override
    public void Export(Asset aset) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void KlikTable(Asset aset) throws SQLException {
        try {
            int pilih = aset.tbl.getSelectedRow();
            if(pilih == -1){
                return;
            }
            aset.txtId.setText(aset.tblmodel.getValueAt(pilih, 0).toString());
            aset.txtAsset.setText(aset.tblmodel.getValueAt(pilih, 1).toString());
            aset.txtQuantity.setText(aset.tblmodel.getValueAt(pilih, 2).toString());
            aset.date.setDateFormatString(aset.tblmodel.getValueAt(pilih, 3).toString());
            aset.cbCondition.setSelectedItem(aset.tblmodel.getValueAt(pilih, 4).toString());
            aset.txtPhoto.setText(aset.tblmodel.getValueAt(pilih, 5).toString());
            aset.txtPrice.setText(aset.tblmodel.getValueAt(pilih, 6).toString());
        } catch (Exception e) {
        }
    }

    @Override
    public void Enter(Asset aset) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void Tampil(Asset aset) throws SQLException {
        aset.tblmodel.getDataVector().removeAllElements();
        aset.tblmodel.fireTableDataChanged();
        
        try {
            Connection con = koneksi.Koneksi.getKoneksi();
            Statement stt = con.createStatement();
            String sql = "select * from aset order by Id_aset asc";
            ResultSet res = stt.executeQuery(sql);
            while(res.next())
            {
                Object[] ob= new Object[8];
                ob[0] = res.getString(1);
                ob[1] = res.getString(2);
                ob[2] = res.getString(3);
                ob[3] = res.getString(4);
                ob[4] = res.getString(5);
                ob[5] = res.getString(6);
                ob[6] = res.getString(7);
                aset.tblmodel.addRow(ob);
            }
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    @Override
    public void CariData(Asset aset) throws SQLException {
        String key = aset.txtSrcTab.getText();
      
        try {
            Connection con = koneksi.Koneksi.getKoneksi();
            Statement stt = con.createStatement();
            aset.tblmodel.getDataVector().removeAllElements();
            
            ResultSet res = stt.executeQuery("SELECT * from aset WHERE Id_asset LIKE '%"+key+"%' OR asset LIKE '%"+key+"%' OR quantity LIKE '%"+key+"%' OR date LIKE '%"+key+"%' OR condition LIKE '%"+key+"%' OR price LIKE '%"+key+"%'");
            while(res.next()){
                Object[] data={
                    res.getString("Id_asset"),
                    res.getString("asset"),
                    res.getString("quantity"),
                    res.getString("date"),
                    res.getString("condition"),
                    res.getString("photo"),
                    res.getString("price")
                };
                aset.tblmodel.addRow(data);
            }
            
            
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
 
    }
    
}
