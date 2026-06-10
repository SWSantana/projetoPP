/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package decorator;
    
/**
 *
 * @author PICHAU
 */
public class NotificadorEmail implements Notificador {
    private String email;

    public NotificadorEmail(String email) {
        this.email = email;
    }

    @Override
    public String enviarNotificacao(String mensagem) {
        return "E-mail enviado para " + email;
    }
}
