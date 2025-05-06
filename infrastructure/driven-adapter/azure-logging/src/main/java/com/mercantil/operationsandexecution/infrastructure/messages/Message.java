package com.mercantil.operationsandexecution.infrastructure.messages;

import java.time.LocalDateTime;

/**
 * Representa un mensaje dentro de la aplicación.
 * <p>
 * Esta clase encapsula la información de un mensaje, incluyendo su contenido, tipo y marca de tiempo.
 * </p>
 *
 * @param <T> el tipo de contenido del mensaje
 */
public class Message<T> {
    
    /**
     * Tipo del mensaje (información, advertencia, error, entre otros.).
     */
    private MessageType type;
    
    /**
     * Contenido del mensaje.
     */
    private T content;
    
    /**
     * Fecha y hora en que se creó el mensaje.
     */
    private final LocalDateTime timestamp;
    
    /**
     * Constructor para crear un mensaje con tipo y contenido.
     *
     * @param type    el tipo de mensaje
     * @param content el contenido del mensaje
     */
    public Message(MessageType type, T content) {
        this.type = type;
        this.content = content;
        this.timestamp = LocalDateTime.now();
    }

    public MessageType getType() {
        return type;
    }

    public void setType(MessageType type) {
        this.type = type;
    }

    public T getContent() {
        return content;
    }

    public void setContent(T content) {
        this.content = content;
    }

    public LocalDateTime getTimestamp() {
        return timestamp;
    }
}
