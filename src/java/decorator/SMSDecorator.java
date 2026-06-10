/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package decorator;

/**
 *
 * @author PICHAU
 */
public class SMSDecorator extends NotificadorDecorator {
    private String numero;
     
    public SMSDecorator(Notificador notificadorDecorado, String numero) {
        super(notificadorDecorado);
        this.numero = numero;
    }

    @Override
    public String enviarNotificacao(String mensagem) {
        return super.enviarNotificacao(mensagem) + " | SMS enviado para " + numero;
    }
}
