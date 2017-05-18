package it.polito.tdp.metrodeparis.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.javadocmd.simplelatlng.LatLng;

import it.polito.tdp.metrodeparis.model.Fermata;
import it.polito.tdp.metrodeparis.model.FermateAdiacenti;
import it.polito.tdp.metrodeparis.model.Linea;

public class MetroDAO {

	public List<Fermata> getAllFermate() {

		final String sql = "SELECT id_fermata, nome, coordx, coordy FROM fermata ORDER BY nome ASC";
		List<Fermata> fermate = new ArrayList<Fermata>();

		try {
			Connection conn = DBConnect.getInstance().getConnection();
			PreparedStatement st = conn.prepareStatement(sql);
			ResultSet rs = st.executeQuery();

			while (rs.next()) {
				Fermata f = new Fermata(rs.getInt("id_Fermata"), rs.getString("nome"), new LatLng(rs.getDouble("coordx"), rs.getDouble("coordy")));
				fermate.add(f);
			}

			st.close();
			conn.close();

		} catch (SQLException e) {
			e.printStackTrace();
			throw new RuntimeException("Errore di connessione al Database.");
		}

		return fermate;
	}
	
	public List<FermateAdiacenti> listStazioniAdiacenti() {
		final String sql ="SELECT f1.id_fermata as id1, f1.nome as nome1, f1.coordX as x1, f1.coordy as y1, "+
           "f2.id_fermata as id2, f2.nome as nome2, f2.coordX as x2, f2.coordy as y2, "+
				"l1.id_linea, l1.nome, l1.velocita "+
			 "FROM connessione, fermata f1, fermata f2, linea l1 "+
	        "WHERE connessione.id_stazP=f1.id_fermata "+
			  "AND connessione.id_stazA=f2.id_fermata "+
	        " AND connessione.id_linea=l1.id_linea";
			List<FermateAdiacenti> list= new ArrayList<FermateAdiacenti>();
		try {
			Connection conn = DBConnect.getInstance().getConnection();
			PreparedStatement st = conn.prepareStatement(sql);
			ResultSet rs = st.executeQuery();
			

			while (rs.next()) {
				Fermata a= new Fermata(rs.getInt("id1"),rs.getString("nome1"), new LatLng(rs.getDouble("x1"), rs.getDouble("y1")));
				Fermata b= new Fermata(rs.getInt("id2"),rs.getString("nome2"), new LatLng(rs.getDouble("x2"), rs.getDouble("y2")));
				Linea c=new Linea(rs.getInt("l1.id_linea"), rs.getString("l1.nome"),rs.getDouble("l1.velocita"));
				FermateAdiacenti temp= new FermateAdiacenti(a,b);
				temp.setL1(c);
				list.add(temp);
			}

			st.close();
			conn.close();

		} catch (SQLException e) {
			e.printStackTrace();
			throw new RuntimeException("Errore di connessione al Database.");
		}
	
		return list;
	}
	
}
