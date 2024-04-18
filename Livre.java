package adminis;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

import conn.Connec;

import java.sql.*;

public class Livre extends JFrame implements ActionListener {
	JLabel lb1,lb2,lb3,lb4,lb5,lbtitre,lb33;
	JTextField tf1,tf2,tf3,tf4,tf5,tf33;
	JButton bt1,bt2,bt3,bt4,bt5,bt6,babonne,bpret;
	ResultSet rst;
	Statement st;
	Connec cn=new Connec();
	JTable jt;
	JScrollPane js;
	
	public Livre(){
		this.setTitle("Gestion de bibliotheque");
		this.setSize(1000,1000);
		this.setLocationRelativeTo(null);
		JPanel pn=new JPanel();
		pn.setLayout(null);
		pn.setBackground(new Color(100,100,150));
		add(pn);
		//titre
				lbtitre=new JLabel("Enregistrement des livres");
				lbtitre.setBounds(10,10,300,30);
				lbtitre.setFont(new Font("Arial",Font.BOLD,16));
				pn.add(lbtitre);
		//label
		lb1=new JLabel("Isbn");
		lb1.setBounds(30,50,100,30);
		pn.add(lb1);
		tf1=new JTextField();
		tf1.setBounds(100,50,100,30);
		pn.add(tf1);
		//
		lb2=new JLabel("Titre");
		lb2.setBounds(30,90,100,30);
		pn.add(lb2);
		
		tf2=new JTextField();
		tf2.setBounds(100,90,200,30);
		pn.add(tf2);
		//
		lb33=new JLabel("Auteur");
		lb33.setBounds(30,120,100,30);
		pn.add(lb33);
		
		tf33=new JTextField();
		tf33.setBounds(100,130,205,30);
		pn.add(tf33);
		
		//
		pn.add(tf33);
		//button
		bt1=new JButton("Insertion");
		bt1.setBounds(180,200,110,30);
		bt1.addActionListener(this);
		pn.add(bt1);
		
		bt2=new JButton("Suppression");
		bt2.setBounds(50,170,110,30);
		bt2.addActionListener(this);
		pn.add(bt2);
		
		bt4=new JButton("Recherche");
		bt4.setBounds(200,50,100,30);
		bt4.addActionListener(this);
		pn.add(bt4);
		
		bt3=new JButton("Requetes");
		bt3.setBounds(50,240,110,30);
		bt3.addActionListener(this);
		pn.add(bt3);
		
		bt5=new JButton("Modification");
		bt5.setBounds(50,205,110,30);
		bt5.addActionListener(this);
		pn.add(bt5);
		
		bt6=new JButton("Actualiser");
		bt6.setBounds(50,280,110,30);
		bt6.addActionListener(this);
		pn.add(bt6);
		
		babonne=new JButton("Utilisateurs");
		babonne.setBounds(180,280,110,30);
		babonne.addActionListener(this);
		pn.add(babonne);
		
		bpret=new JButton("Emprunt");
		bpret.setBounds(180,240,110,30);
		bpret.addActionListener(this);
		pn.add(bpret);
		
		DefaultTableModel df=new DefaultTableModel();
		init();
		df.addColumn("ISBN");
		df.addColumn("TITRE");
		df.addColumn("auteur");
		jt.setModel(df);
		pn.add(js);
		
		String qr="select * from livre";
		
		try{
			st=cn.connecion().createStatement();
			rst=st.executeQuery(qr);
			while(rst.next()){
				df.addRow(new Object[]{
						rst.getString("idlivre"),rst.getString("titre"),rst.getString("auteur")
				});
			}
			
		}
		catch(SQLException ex){
			
		}
		
	}
	public void init(){
		jt=new JTable();
		js=new JScrollPane();
		js.setViewportView(jt);
		js.setBounds(310,10,350,300);
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
    Livre lv=new Livre();
    lv.setVisible(true);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		//ajout
		if(e.getSource()==bt1){
		String a,b,c;
		a=tf1.getText();b=tf2.getText();c=tf33.getText();
		String qr="insert into livre(idlivre,titre,auteur,disponible)"
				+ "values('"+a+"','"+b+"','"+c+"','OUI')";
		try{
			st=cn.connecion().createStatement();
			if(JOptionPane.showConfirmDialog(this,"voulez vous inserer?",null,JOptionPane.OK_CANCEL_OPTION)==JOptionPane.OK_OPTION){
				st.executeUpdate(qr);
				JOptionPane.showMessageDialog(this,"Insertion reussie!");
			}
		}
		catch(SQLException ex){
			JOptionPane.showMessageDialog(this,"Erreur,insertion impossible!",null,JOptionPane.ERROR_MESSAGE);
		}
			
		}
		if(e.getSource()==bt2){
			String a;
			a=tf1.getText();
			String qr="delete from livre where idlivre='"+a+"'";
			try{
				st=cn.connecion().createStatement();
				if(JOptionPane.showConfirmDialog(this,"voulez vous supprimer?",null,JOptionPane.OK_CANCEL_OPTION)==JOptionPane.OK_OPTION){
					st.executeUpdate(qr);
					JOptionPane.showMessageDialog(this,"suppression reussie!");
				}
			}
			catch(SQLException ex){
				JOptionPane.showMessageDialog(this,"Erreur,supression impossible!",null,JOptionPane.ERROR_MESSAGE);
			}
		}
		 if(e.getSource()==bt4){
	    	  String a;
				a=tf1.getText();
				
				String qr="select * from livre where idlivre='"+a+"'";
				try{
					st=cn.connecion().createStatement();
					rst=st.executeQuery(qr);
					if(rst.next()){
						tf2.setText(rst.getString("titre"));
						tf33.setText(rst.getString("auteur"));
						
						
					}
					else
						JOptionPane.showMessageDialog(this,"introuvable!",null,JOptionPane.ERROR_MESSAGE);
				}
				catch(SQLException ex){
				
				}
	    	  
	      }
		if(e.getSource()==bt3){
			dispose();
			Requete rq=new Requete();
			rq.setVisible(true);
			
		}
		//modification
		if(e.getSource()==bt5){
			String a,b,c;
			a=tf1.getText();b=tf2.getText();c=tf2.getText();
			String qr="update livre set titre='"+b+"'where idlivre='"+a+"'";
			String qrs="update livre set titre='"+c+"'where idlivre='"+a+"'";
			try{
				st=cn.connecion().createStatement();
				if(JOptionPane.showConfirmDialog(this,"voulez vous modifier?",null,JOptionPane.OK_CANCEL_OPTION)==JOptionPane.OK_OPTION){
					st.executeUpdate(qr);
					JOptionPane.showMessageDialog(this,"modification reussie!");
					
					}
				
				else if(JOptionPane.showConfirmDialog(this,"voulez vous modifier?",null,JOptionPane.OK_CANCEL_OPTION)==JOptionPane.OK_OPTION){
					st.executeUpdate(qrs);
					JOptionPane.showMessageDialog(this,"modification reussie!");
					
				
				
				
				
			}}
			catch(SQLException ex){
				JOptionPane.showMessageDialog(this,"Erreur,modification impossible!",null,JOptionPane.ERROR_MESSAGE);
			}
				
			}
		if(e.getSource()==bt6){
			dispose();
			Livre lv=new Livre();
			lv.setVisible(true);
			
		}
		//aller vers l'interface graphique de gestion des abonn�s
				if(e.getSource()==babonne){
					
					try{
						dispose();
						Utilisateurs ab=new Utilisateurs();
						ab.setVisible(true);
						
					}
					catch(Exception ex){
						
					}
						
					}
				//
				//emprunt
				if(e.getSource()==bpret){
					dispose();
					Emprunt ep=new Emprunt();
					ep.setVisible(true);	
				}
		
	
}
}
