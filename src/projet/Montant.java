package projet;

import java.awt.*;
import java.awt.event.*;
import java.sql.*;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;


public class Montant extends JFrame{
	Statement st;
	Conneccion con=new Conneccion();
	ResultSet rst;
	JScrollPane scroll2;
	JTable table2;
	public Montant () {
		this.setTitle("Restaurant Soltana");
		this.setSize(700,500);
		this.setLocationRelativeTo(null);
		this.setResizable(false);
		
	}
	public void actionPerformed(ActionEvent ev){
		  JPanel pn=new JPanel();
		JLabel lbtitre1=new JLabel("Montant total de commande");
		lbtitre1.setFont(new Font("Arial",Font.BOLD,18));
		pn.add(lbtitre1);
		//afficher la liste des commandes
		DefaultTableModel df2=new  DefaultTableModel();
		table2=new JTable();
		scroll2=new JScrollPane();
		scroll2.setViewportView(table2);
		  pn.add(scroll2);
		  this.add(pn);
		 df2.addColumn("Num�ro table");
		 df2.addColumn("Montant total (fcfa)");
		 table2.setModel(df2);
		 String req2="select tab_num,sum(qte_aliment*prix_aliment) as montant from tb_aliment inner join tb_commande on tb_aliment.code_aliment=tb_commande.aliment group by tab_num  ";
		 try{
			 st=con.laConnection().createStatement();
			 rst=st.executeQuery(req2);
			 while(rst.next()){
				 df2.addRow(new Object[]{
rst.getString("tab_num"),rst.getString("montant")

						 });
			 } 
			 }
		 catch(SQLException ex){
		    	JOptionPane.showMessageDialog(null,"Erreur !",null,JOptionPane.ERROR_MESSAGE);	
		    }
	
	//button pour retour au interface commande
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


