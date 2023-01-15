package scambio;

import com.google.api.client.auth.oauth2.Credential;
import com.google.api.client.extensions.java6.auth.oauth2.AuthorizationCodeInstalledApp;
import com.google.api.client.extensions.jetty.auth.oauth2.LocalServerReceiver;
import com.google.api.client.googleapis.auth.oauth2.GoogleAuthorizationCodeFlow;
import com.google.api.client.googleapis.auth.oauth2.GoogleClientSecrets;
import com.google.api.client.googleapis.javanet.GoogleNetHttpTransport;
import com.google.api.client.googleapis.json.GoogleJsonError;
import com.google.api.client.googleapis.json.GoogleJsonResponseException;
import com.google.api.client.http.javanet.NetHttpTransport;
import com.google.api.client.json.gson.GsonFactory;
import com.google.api.client.util.store.FileDataStoreFactory;
import com.google.api.services.gmail.Gmail;
import com.google.api.services.gmail.model.Message;
import org.apache.commons.codec.binary.Base64;

import javax.mail.Session;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.io.ByteArrayOutputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.file.Paths;
import java.util.Properties;
import java.util.Set;

import static com.google.api.services.gmail.GmailScopes.GMAIL_SEND;
import static javax.mail.Message.RecipientType.TO;
/**
 * /**
 * GMailer è una classe che consente l'invio di e-mail utilizzando l'API di Gmail.
 * Include un costruttore che configura il servizio Gmail e un metodo
 * per inviare e-mail, chiamato sendMail.
 * @author Michele Menzione
 */


public class GMailer {
    /**
     L'indirizzo email che verrà utilizzato come mittente per tutte le e-mail inviate utilizzando questa classe.
     */
    private static final String TEST_EMAIL = "CardeXchange2023@gmail.com";

    /**
     Il servizio Gmail utilizzato per inviare e-mail.
     */
    private final Gmail service;


    /**
     Costruttore per la classe GMailer. Imposta il servizio Gmail creando un nuovo trasporto affidabile e
     una nuova istanza di Gmail utilizzando i segreti del client e le credenziali.
     @throws Exception se si verifica un errore durante l'impostazione del servizio Gmail.
     */
    public GMailer() throws Exception {
        NetHttpTransport httpTransport = GoogleNetHttpTransport.newTrustedTransport();
        GsonFactory jsonFactory = GsonFactory.getDefaultInstance();
        service = new Gmail.Builder(httpTransport, jsonFactory, getCredentials(httpTransport, jsonFactory))
                .setApplicationName("Test Mailer")
                .build();
    }


    /**
     Un metodo helper per ottenere le credenziali necessarie per accedere all'API di Gmail.
     @param httpTransport il NetHttpTransport utilizzato per il servizio Gmail
     @param jsonFactory la GsonFactory utilizzata per il servizio Gmail
     @return le credenziali necessarie per accedere all'API di Gmail
     @throws IOException se si verifica un errore durante l'ottenimento delle credenziali
     */
    private static Credential getCredentials(final NetHttpTransport httpTransport, GsonFactory jsonFactory)
            throws IOException {
        // Da cambiare con la posizione del file secret.json
        GoogleClientSecrets clientSecrets = GoogleClientSecrets.load(jsonFactory, new InputStreamReader(new FileInputStream("/Users/michelemenzione/Documents/CardEXchange/src/main/java/scambio/secretFile.json")));

        GoogleAuthorizationCodeFlow flow = new GoogleAuthorizationCodeFlow.Builder(
                httpTransport, jsonFactory, clientSecrets, Set.of(GMAIL_SEND))
                .setDataStoreFactory(new FileDataStoreFactory(Paths.get("tokens").toFile()))
                .setAccessType("offline")
                .build();

        LocalServerReceiver receiver = new LocalServerReceiver.Builder().setPort(8888).build();
        return new AuthorizationCodeInstalledApp(flow, receiver).authorize("user");
    }

    /**
     * Metodo che permette l'invio dell'email
     * @param emailDestinatario - email a cui si deve inviare email
     * @param subject - Oggetto del messaggio
     * @param message - Corpo del messaggio
     * @throws Exception
     */
    public void sendMail(String emailDestinatario, String subject, String message) throws Exception {
        Properties props = new Properties();
        Session session = Session.getDefaultInstance(props, null);
        MimeMessage email = new MimeMessage(session);
        email.setFrom(new InternetAddress(TEST_EMAIL));
        email.addRecipient(TO, new InternetAddress(emailDestinatario));
        email.setSubject(subject);
        email.setText(message);

        ByteArrayOutputStream buffer = new ByteArrayOutputStream();
        email.writeTo(buffer);
        byte[] rawMessageBytes = buffer.toByteArray();
        String encodedEmail = Base64.encodeBase64URLSafeString(rawMessageBytes);
        Message msg = new Message();
        msg.setRaw(encodedEmail);

        try {
            msg = service.users().messages().send("me", msg).execute();
            System.out.println("Message id: " + msg.getId());
            System.out.println(msg.toPrettyString());
        } catch (GoogleJsonResponseException e) {
            GoogleJsonError error = e.getDetails();
            if (error.getCode() == 403) {
                System.err.println("Unable to send message: " + e.getDetails());
            } else {
                throw e;
            }
        }
    }
}