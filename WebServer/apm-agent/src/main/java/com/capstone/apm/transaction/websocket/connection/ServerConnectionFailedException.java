package com.capstone.apm.transaction.websocket.connection;

public class ServerConnectionFailedException extends RuntimeException {
    public ServerConnectionFailedException() {
        super();
    }

    public ServerConnectionFailedException(String message) {
        super(message);
    }

    public ServerConnectionFailedException(String message, Throwable cause) {
        super(message, cause);
    }

    public ServerConnectionFailedException(Throwable cause) {
        super(cause);
    }

    protected ServerConnectionFailedException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
