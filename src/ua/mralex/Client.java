package ua.mralex;

import java.io.IOException;

public class Client extends Thread {
    private static Parameters parameters = Parameters.getInstance();

    @Override
    public void run() {
        while (!isInterrupted()) {
            try {
                Server.getPage(parameters.getLink());
                Thread.sleep(100);
            } catch (InterruptedException ex) {
                System.err.println(ex.getClass().getSimpleName() + ": " + ex.getMessage());
                return;
            } catch (IOException ex) {
                ex.printStackTrace();
                if (!parameters.isReconnect()) {
                    interrupt();
                }
            }
        }
    }

    public static void setParameters(Parameters parameters) {
        Client.parameters = parameters;
    }

    public static Parameters getParameters() {
        return parameters;
    }
}
