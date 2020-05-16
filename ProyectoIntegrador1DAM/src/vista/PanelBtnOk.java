package vista;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

public class PanelBtnOk extends JPanel {
	
	private static final long serialVersionUID = -2916018382013617038L;

	private JButton btnOk;
	private JLabel labelTextoError;
	
	public PanelBtnOk() {

		this.setLayout(new FlowLayout(FlowLayout.RIGHT));
		
		this.setLabelTextoError(new JLabel(""));
		this.getLabelTextoError().setFont(new Font("SansSerif", Font.BOLD, 12));
		this.getLabelTextoError().setForeground(Color.RED);
		this.getLabelTextoError().setHorizontalAlignment(SwingConstants.RIGHT);
		this.getLabelTextoError().setPreferredSize(new Dimension(270, 16));
		this.add(getLabelTextoError());
	
		this.setBtnOk(new JButton("Ok"));
		this.getBtnOk().setActionCommand("btnOk");
		this.add(getBtnOk());
	
	}

	public JButton getBtnOk() {
		return btnOk;
	}

	public void setBtnOk(JButton btnOk) {
		this.btnOk = btnOk;
	}

	public JLabel getLabelTextoError() {
		return labelTextoError;
	}

	public void setLabelTextoError(JLabel labelTextoError) {
		this.labelTextoError = labelTextoError;
	}

}
