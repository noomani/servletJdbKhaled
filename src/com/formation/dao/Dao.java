package com.formation.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;

import com.formation.model.Client;

public class Dao implements Idao {

	@Override
	public void save(Client client) {
		PreparedStatement st = null;
		Connection cn = null;
		String prenom = client.getPrenom();
		String nom = client.getNom();
		int age = client.getAge();
		try {
			// se connecter a la base de données
			cn= connecter();
			//ecrire une requete
			String sql = "INSERT INTO client (nom, prenom, age) VALUES (?, ?, ?)";
			//etape 3 : création d'un statement
			st = cn.prepareStatement(sql);
			st.setString(1, nom);
			st.setString(2, prenom);
			st.setInt(3, age);
			//etape 4 : exécution requete:
		        st.execute();
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}finally {
			try {
				// etape 5 : liberer ressources de la mémoire
				cn.close();
				st.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}

	@Override
	public Client findById(int id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void update(Client client) {
		// TODO Auto-generated method stub

	}

	@Override
	public void delete(Client client) {
		// TODO Auto-generated method stub

	}

	@Override
	public Connection connecter() throws ClassNotFoundException, SQLException {
		//information d'acces à la base de données
		
				String url = "jdbc:mysql://localhost/vente";
				String login = "root";
				String passwd = "";
				Connection cn = null;
				Statement st = null;
					
					//etape 1 : Chargement du driver
					Class.forName("com.mysql.jdbc.Driver");
					//etape 2 : récuperation de la connexion
					cn = DriverManager.getConnection(url, login, passwd);
		return cn;
	}
public static void main(String[] args) {
	Client client = new Client();
	client.setNom("noomani");
	client.setPrenom("khaled");
	client.setAge(31);
	Client client2 = new Client();
	client2.setNom("bob");
	
	Idao dao = new Dao();
	dao.save(client);
}
}
