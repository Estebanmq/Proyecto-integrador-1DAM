package vista;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

public class DialogoDirectorAlta extends JDialog {

	private static final long serialVersionUID = 5428825630214882590L;

	private final JPanel contentPanel = new JPanel();

	private JButton btnAceptar;
	private JButton btnCancelar;
	private JPanel panel;
	private JLabel labelTexto;

	/**
	 * Create the dialog.
	 */
	public DialogoDirectorAlta() {

		this.setTitle("Alta de directores");
		this.setModal(true);
		this.setBounds(100, 100, 537, 309);
		this.getContentPane().setLayout(new BorderLayout());
		this.setLocationRelativeTo(null);
		getContentPanel().setLayout(new FlowLayout());
		getContentPanel().setBorder(new EmptyBorder(5, 5, 5, 5));
		
		JPanel buttonPane = new JPanel();
		buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
		getContentPane().add(buttonPane, BorderLayout.SOUTH);

		setBtnAceptar(new JButton("Aceptar"));
		getBtnAceptar().setActionCommand("btnAceptar");
		buttonPane.add(getBtnAceptar());
		getRootPane().setDefaultButton(getBtnAceptar());

		setBtnCancelar(new JButton("Cancelar"));
		getBtnCancelar().setActionCommand("btnCancelar");
		buttonPane.add(getBtnCancelar());

		this.setPanel(new JPanel());
		this.getContentPane().add(getPanel(), BorderLayout.CENTER);

		this.setLblLabelTexto(new JLabel("estamos en dialogo de alta de director"));
		this.getPanel().add(this.getLblLabelTexto());

	}

	public JButton getBtnAceptar() {
		return btnAceptar;
	}

	public void setBtnAceptar(JButton btnAceptar) {
		this.btnAceptar = btnAceptar;
	}

	public JButton getBtnCancelar() {
		return btnCancelar;
	}

	public void setBtnCancelar(JButton btnCancelar) {
		this.btnCancelar = btnCancelar;
	}

	private JPanel getContentPanel() {
		return contentPanel;
	}

	private JPanel getPanel() {
		return panel;
	}

	private void setPanel(JPanel panel) {
		this.panel = panel;
	}

	private JLabel getLblLabelTexto() {
		return labelTexto;
	}

	private void setLblLabelTexto(JLabel lblLabelTexto) {
		this.labelTexto = lblLabelTexto;
	}

}
