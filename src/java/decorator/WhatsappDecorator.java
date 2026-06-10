/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package decorator;

/**
 *
 * @author PICHAU
 */
public class WhatsappDecorator extends NotificadorDecorator {
    private String numero;
    
    public WhatsappDecorator(Notificador notificadorDecorado, String numero) {
        super(notificadorDecorado);
        this.numero = numero;
    }

    @Override
    public String enviarNotificacao(String mensagem) {
        return super.enviarNotificacao(mensagem) + " | Notificação via Whatsapp enviada para o numero:  " + numero;
    }
}
