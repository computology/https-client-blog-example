package io.packagecloud.https_client_example;


import javax.net.ssl.HttpsURLConnection;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.security.cert.Certificate;
import java.security.cert.X509Certificate;

public class Main {

    public static void main(String[] args) {
        URL url;
        HttpsURLConnection connection;
        try {
            url = new URL(System.getProperty("example_url", "https://www.google.com/"));
            connection = (HttpsURLConnection)url.openConnection();
            connection.connect();

            System.out.println(String.format("GET %s -> %s", connection.getURL(), connection.getResponseCode()));

            Certificate[] serverCertificates = connection.getServerCertificates();
            X509Certificate x509cert;
            for (Certificate serverCertificate : serverCertificates) {
                x509cert = (X509Certificate)serverCertificate;
                System.out.println(x509cert.getIssuerX500Principal().getName());
            }
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
