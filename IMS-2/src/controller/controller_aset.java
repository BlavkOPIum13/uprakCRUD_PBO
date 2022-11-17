/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import java.sql.SQLException;
import view.Asset;

/**
 *
 * @author user
 */
public interface controller_aset {
    public void Input(Asset aset) throws SQLException;
    public void Edit(Asset aset) throws SQLException;
    public void Reset(Asset aset) throws SQLException;
    public void Delete(Asset aset) throws SQLException;
    public void Save(Asset aset) throws SQLException;
    public void Print(Asset aset) throws SQLException;
    public void Export(Asset aset) throws SQLException;
    public void KlikTable(Asset aset)throws SQLException;
    public void Enter(Asset aset) throws SQLException;
    public void Tampil(Asset aset) throws SQLException;
    public void CariData(Asset aset) throws SQLException;
}
