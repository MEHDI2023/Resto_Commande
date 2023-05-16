package projet;

import java.awt.*;
import java.awt.event.*;
import java.sql.*;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;

public class historique extends JFrame{
	Statement st;
	Conneccion con=new Conneccion();
	ResultSet rst;
	JScrollPane scroll;
	JTable table;
	public historique () {
		this.setTitle("Restaurant Soltana");
		this.setSize(700,500);
		this.setLocationRelativeTo(null);
		this.setResizable(false);
		
	}
	public void actionPerformed(ActionEvent ev){
		  JPanel pn=new JPanel();
		JLabel lbtitre1=new JLabel("Historiques des commandes");
		lbtitre1.setFont(new Font("Arial",Font.BOLD,18));
		pn.add(lbtitre1);
		//afficher la liste des commandes
		 DefaultTableModel df=new  DefaultTableModel();
		 table=new JTable();
			scroll=new JScrollPane();
			scroll.setViewportView(table);
			add(pn);
		  pn.add(scroll);
		 df.addColumn("Num�ro de table");
		 df.addColumn("Commande");
		 df.addColumn("Quantit�");
		 df.addColumn("Date commande");
		 table.setModel(df);
		 String req="select * from tb_historique order by date_commande desc ";
		 try{
			 st=con.laConnection().createStatement();
			 rst=st.executeQuery(req);
			 while(rst.next()){
				 df.addRow(new Object[]{
rst.getString("tab_num"),rst.getString("aliment"),rst.getString("qte_aliment"),rst.getString("date_commande")
						 });
			 }
			 }
		 catch(SQLException ex){
		    	JOptionPane.showMessageDialog(null,"Erreur !",null,JOptionPane.ERROR_MESSAGE);	
		    }
	
	
	 JButton Retour=new JButton("Retour");
		Retour.setBounds(400,430,230,25);
		Retour.addActionListener(new ActionListener(){
			Commande  c = new Commande ();

		
			public void actionPerformed(ActionEvent e) {
				dispose();
				c.setVisible(true);
				
			}
			
		});
	pn.add(Retour);
	}
	
	
}


