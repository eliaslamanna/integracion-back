package com.integracion.backend.aws;


import org.springframework.stereotype.Service;
import software.amazon.awssdk.auth.credentials.AwsBasicCredentials;
import software.amazon.awssdk.auth.credentials.AwsSessionCredentials;
import software.amazon.awssdk.auth.credentials.StaticCredentialsProvider;
import software.amazon.awssdk.regions.Region;
import software.amazon.awssdk.services.eventbridge.EventBridgeClient;
import software.amazon.awssdk.services.eventbridge.model.PutEventsRequest;
import software.amazon.awssdk.services.eventbridge.model.PutEventsRequestEntry;
import software.amazon.awssdk.services.eventbridge.model.PutEventsResponse;
import software.amazon.awssdk.services.eventbridge.model.PutEventsResultEntry;

import java.util.List;
@Service
public class SendCreateArtistEvent {

    private final EventBridgeClient client;

    public SendCreateArtistEvent() {
        AwsSessionCredentials awsCreds= AwsSessionCredentials.create("accessKey", "secretKey",
                "sessionToken");
        this.client = EventBridgeClient.builder()
                .region(Region.US_EAST_1)
                .credentialsProvider(StaticCredentialsProvider.create(awsCreds))
                .build();
    }

    public void sendEvent(String detail) {
        PutEventsRequestEntry entry = PutEventsRequestEntry.builder()
                .eventBusName("arn:aws:events:us-east-1:442042507897:event-bus/default")
                .source("artist-module")
                .detailType("recital.created")
                .detail(detail)
                .build();

        PutEventsRequest request = PutEventsRequest.builder()
                .entries(entry)
                .build();

        try {
            PutEventsResponse response = client.putEvents(request);
            List<PutEventsResultEntry> resultEntries = response.entries();


            for (PutEventsResultEntry result : resultEntries) {
                if (result.eventId() != null) {
                    System.out.println("Evento enviado exitosamente. Event ID: " + result.eventId());
                } else {
                    System.err.println("Error al enviar evento: " + result.errorCode() + " - " + result.errorMessage());
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void closeClient() {
        client.close();
    }
}
