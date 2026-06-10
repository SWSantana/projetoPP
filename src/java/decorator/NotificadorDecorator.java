/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package decorator;

/**
 *
 * @author PICHAU
 */
public abstract class NotificadorDecorator implements Notificador {
    protected Notificador notificadorDecorado;

    public NotificadorDecorator(Notificador notificadorDecorado) {
        this.notificadorDecorado = notificadorDecorado;
    }

    @Override
    public String enviarNotificacao(String mensagem) {
        return notificadorDecorado.enviarNotificacao(mensagem);
    }
}