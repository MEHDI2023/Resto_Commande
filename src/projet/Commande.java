package projet;

import java.awt.*;
import java.awt.event.*;
import java.sql.*;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;

public class Commande extends JFrame{
	Statement st;
	Conneccion con=new Conneccion();
	ResultSet rst;
	JScrollPane scroll,scroll2;
JLabel L1 =new JLabel(" Enregistrement Commande ") ;

JLabel L2 =new JLabel ("Aliments") ;

JLabel L3 =new JLabel("Quantite") ;

JLabel L4= new JLabel ("Num Table") ;

JTextField t1=new JTextField(10) ;

JTextField t2 =new JTextField(10) ;

JTextField t3 =new JTextField(10);

JComboBox c1=new JComboBox();

JTable table = new JTable();

JButton btenrg=new JButton("Enregistrer");

JButton btsupp=new JButton("ANNULER");

JButton btliberer=new JButton("LIBERER");

JButton bthistorique=new JButton("HISTORIQUE");



JButton bmontant=new JButton("Afficher Montant");
   
    JPanel pglobale , plabel_text ,p3 ,pbutton ,p5,ptitre;
   
    Commande(){
    this.setTitle("Restaurant Soltana");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setSize(800, 500);
        this.setResizable(true);
this.setLocationRelativeTo (null);
this.setAlwaysOnTop(true);
pglobale= new JPanel (new BorderLayout());

ptitre = new JPanel ();
ptitre.setLayout(new FlowLayout(FlowLayout.CENTER));
ptitre.add(L1);
    pglobale.add(ptitre,BorderLayout.NORTH);
    
    plabel_text = new JPanel();
    plabel_text.setLayout(new GridLayout(5,2,10,10));
    plabel_text.add(L2);
    plabel_text.add(c1);
    plabel_text.add(L3);
    plabel_text.add(t1);
    plabel_text.add(L4);
    plabel_text.add(t2);
    plabel_text.add(t3);
    plabel_text.add(btsupp);
       
        p3 = new JPanel ();
        p3.setLayout(new FlowLayout());
        p3.add(plabel_text);
        pglobale.add(p3,BorderLayout.WEST);
        
        
      //bouton pour actualiser la fenetre
        JButton btactu=new JButton("ACTUALISER");
btactu.addActionListener(new ActionListener(){
	public void actionPerformed(ActionEvent ev){
		dispose();
		Commande cd=new Commande();
		cd.setVisible(true);
	}
});


//bouton pour liberer une table
btliberer.addActionListener(new ActionListener(){
	public void actionPerformed(ActionEvent ev){
		//v�rifier le num�ro dans la base de donn�es
		String nb="";
		String req="select count(*) as nb from tb_commande where tab_num='"+t2.getText()+"'";
		try{
			st=con.laConnection().createStatement();
			rst=st.executeQuery(req);
			
			if(rst.next()){
				nb=rst.getString("nb");
			}
		}
		catch(SQLException ex){
	    	JOptionPane.showMessageDialog(null,"Erreur!",null,JOptionPane.ERROR_MESSAGE);	
	    }
		//
		String rq="delete from tb_commande where tab_num='"+t2.getText()+"'";
		String rq2="update tb_table set etat='libre' where num_tab='"+t2.getText()+"'";
		try{
			st=con.laConnection().createStatement();
			if(!t2.getText().equals("")){
				if(!nb.equals("0")){
			st.executeUpdate(rq2);
			st.executeUpdate(rq);
			System.out.println("Lib�ration de table confirm�e!");
				}
				else{
					System.out.println("Cet enregistrement n'existe pas dans la base de donn�es !");
				}
			}
			else{
				System.out.println("Indiquez le num�ro de table!");
				}
		}
		catch(SQLException ex){
			System.out.println("Erreur!");
	    }
		dispose();
		Commande rg=new Commande();
		rg.setVisible(true);
	}
});


//bouton pour annuler une commande
btsupp.addActionListener(new ActionListener(){
	public void actionPerformed(ActionEvent ev){
		//v�rifier le num�ro dans la base de donn�es
		String nb="";
		String req="select count(*) as nb from tb_commande where id_commande='"+t3.getText()+"'";
		try{
			st=con.laConnection().createStatement();
			rst=st.executeQuery(req);
			
			if(rst.next()){
				nb=rst.getString("nb");
			}
		}
		catch(SQLException ex){
	    	JOptionPane.showMessageDialog(null,"Erreur!",null,JOptionPane.ERROR_MESSAGE);	
	    }
		//
		String rq2="delete from tb_historique where id_commande='"+t3.getText()+"'";
		String rq="delete from tb_commande where id_commande='"+t3.getText()+"'";
		
		try{
			st=con.laConnection().createStatement();
			if(!t3.getText().equals("")){
				if(!nb.equals("0")){
			st.executeUpdate(rq);
			System.out.println("Suppression �ffectu�e avec succ�s !");
				}
				else{	
					System.out.println("Cet enregistrement n'existe pas dans la base de donn�es !");
				}
			}
			else{
				System.out.println("Indiquez l'identifiant (ID) de la commande!");

			}
		}
		catch(SQLException ex){
			System.out.println("Erreur!");	    }
		try{
			st=con.laConnection().createStatement();
			st.executeUpdate(rq2);
		}
		catch(SQLException ex){
	    	System.out.println("Erreur!");	
	    }
		dispose();
		Commande rg=new Commande();
		rg.setVisible(true);
	}	
});



//bouton pour enregistrer une commande
btenrg.addActionListener(new ActionListener(){
	public void actionPerformed(ActionEvent ev){
		
String rq="insert into tb_commande(aliment,qte_aliment,tab_num) values('"+c1.getSelectedItem().toString()+"','"+t1.getText()+"','"+t2.getText()+"')";
String rq1="insert into tb_historique(aliment,qte_aliment,tab_num,date_commande) values('"+c1.getSelectedItem().toString()+"','"+t1.getText()+"','"+t2.getText()+"',now())";

try{
	st=con.laConnection().createStatement();
if(!c1.getSelectedItem().toString().equals("")&&!t1.getText().equals("")&&!t2.getText().equals("")){
st.executeUpdate(rq);
st.executeUpdate(rq1);
System.out.println("Enregistrement affectue avec succes !");	
}
else{
System.out.println("Completez le formulaire!");
}
}
catch(SQLException ex){
	System.out.println("Erreur!");}
dispose();
Commande rg=new Commande();
rg.setVisible(true);
	}	
});


//remplir le comboBox de champs Aliment
String sql="select code_aliment from tb_aliment";
try{
	 st=con.laConnection().createStatement();
	 rst=st.executeQuery(sql);
	 while(rst.next())
	 {
		 c1.addItem(rst.getString("code_aliment")); 
	 }
}
catch(SQLException ex){
   	JOptionPane.showMessageDialog(null,"Erreur !",null,JOptionPane.ERROR_MESSAGE);	
   }
   

//afficher la liste des commandes 
        DefaultTableModel df=new  DefaultTableModel();
        table=new JTable();
		scroll=new JScrollPane();
		scroll.setViewportView(table);
		 df.addColumn("Num�ro de table");
		 df.addColumn("Commande");
		 df.addColumn("Quantit�");
		 df.addColumn("ID commande");
		 table.setModel(df);
		 pglobale.add(scroll,BorderLayout.CENTER);
		 
		 String req="select * from tb_commande ";
  try{
		 st=con.laConnection().createStatement();
		 rst=st.executeQuery(req);
		 while(rst.next()){
			 
			 df.addRow(new Object[]
					 {
rst.getString("tab_num"),rst.getString("aliment"),rst.getString("qte_aliment"),rst.getString("id_commande")
					 });
			 
		 }	 
		 }
		 
	 catch(SQLException ex){
	    	JOptionPane.showMessageDialog(null,"Erreur !",null,JOptionPane.ERROR_MESSAGE);	
	    }
  
  pbutton = new JPanel();
   
  pbutton.setLayout(new GridLayout(1,6,10,10));
       
  pbutton.add(bmontant);
  pbutton.add(btenrg);
  pbutton.add(btliberer);
  pbutton.add(bthistorique);
  pbutton.add(btactu);
    p5 = new JPanel ();
         p5.setLayout(new FlowLayout());
         p5.add(pbutton);
         pglobale.add(p5,BorderLayout.SOUTH);
         this.add(pglobale);
   
         
	bthistorique.addActionListener(new ActionListener(){
		historique  c = new historique();
		//fonction pour ouvrir l'interface historique
		public void actionPerformed(ActionEvent e) {
			dispose();
			c.actionPerformed(e);
			c.setVisible(true);	
		}
	});
	
	bmontant.addActionListener(new ActionListener(){
		Montant  c = new Montant();
		//fonction pour ouvrir l'interface Montant totaux
		public void actionPerformed(ActionEvent e) {
			dispose();
			c.actionPerformed(e);
			c.setVisible(true);	
		                                            }	
	});
}
    }